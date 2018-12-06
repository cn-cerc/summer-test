package cn.cerc.sample.config;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.cerc.core.DataSet;
import cn.cerc.core.IHandle;
import cn.cerc.core.Record;
import cn.cerc.db.mysql.SqlQuery;
import cn.cerc.mis.core.Application;
import cn.cerc.mis.core.IAppMenu;
import cn.cerc.mis.core.IPassport;
import cn.cerc.mis.core.ISystemTable;
import cn.cerc.mis.core.LocalService;
import cn.cerc.mis.core.MenuData;
import cn.cerc.mis.core.MenuItem;
import cn.cerc.mis.other.BookOptions;

@Component
public class AppMenu implements IAppMenu {
    // private static final Logger log = Logger.getLogger(MenuFactory.class);
    @Autowired
    private ISystemTable systemTable;

    private static final String menuFile = "app-menus.xml";
    private static final Map<String, MenuData> menus = new LinkedHashMap<>();
    private DataSet cusMenus;
    private IHandle handle;
    private IPassport passport;

    public static final String TAcc = "TAcc";

    static {
        try {
            menus.clear();
            String filepath = AppMenu.class.getClassLoader().getResource("").toURI().getPath() + menuFile;
            File f = new File(filepath);
            SAXReader reader = new SAXReader();
            Document doc = reader.read(f);

            Element body = doc.getRootElement().element("body");
            if (body == null)
                throw new RuntimeException(menuFile + " 格式不正确！");

            for (Iterator<?> j = body.elementIterator("item"); j.hasNext();) {
                Element item = (Element) j.next();

                if (!"true".equals(item.attributeValue("delete"))) {
                    MenuData menuItem = new MenuData();
                    menuItem.setId(item.attributeValue("code"));
                    menuItem.setCaption(item.attributeValue("name"));
                    if (item.attributeValue("security") != null)
                        menuItem.setSecurity("true".equals(item.attributeValue("security")));
                    else
                        menuItem.setSecurity(true);
                    menuItem.setHide("true".equals(item.attributeValue("hide")));
                    menuItem.setFolder("true".equals(item.attributeValue("folder")));
                    menuItem.setCustom("true".equals(item.attributeValue("custom")));
                    menuItem.setProccode(item.attributeValue("proccode"));
                    menuItem.setParent(menuItem.getHide() ? "hidden" : item.attributeValue("parent"));

                    Element remark = item.element("remark");
                    menuItem.setDescribe(remark != null ? remark.getText() : "");

                    menuItem.setWin(true);
                    Element web = item.element("web");
                    if (web != null) {
                        menuItem.setClazz(web.attributeValue("class"));
                        menuItem.setFormNo(web.attributeValue("pageno"));
                        menuItem.setImage(web.attributeValue("image"));
                        menuItem.setProcess(web.attributeValue("process"));
                        menuItem.setWeb(!"none".equals(menuItem.getProcess()));
                        menuItem.setWin(!"release".equals(menuItem.getProcess()));
                    }

                    Element access = item.element("access");
                    menuItem.setVersions(access.attributeValue("verlist"));
                    menus.put(menuItem.getId(), menuItem);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Map<String, MenuData> getItems() {
        return menus;
    }

    public static MenuData get(String beanID) {
        return menus.get(beanID);
    }

    @Override
    public MenuItem getItem(String menuId) {
        MenuData item = get(menuId);
        if (item == null)
            throw new RuntimeException(String.format("menu %s not find!", menuId));

        MenuItem menu = new MenuItem();
        menu.setId(menuId);
        copyMenuData(item, menu);
        return menu;
    }

    @Override
    public List<MenuItem> getList(IHandle handle, String parentMenu, boolean security) {
        this.handle = handle;
        if (this.passport == null)
            this.passport = Application.getPassport(handle);

        Map<String, MenuData> menus = AppMenu.getItems();
        List<MenuItem> result = new ArrayList<MenuItem>();
        // 初筛出符合要求的菜单项
        for (String menuId : menus.keySet()) {
            MenuData item = menus.get(menuId);
            if (passItem(item, parentMenu, security)) {
                MenuItem menu = new MenuItem();
                menu.setId(menuId);
                copyMenuData(item, menu);
                result.add(menu);
            }
        }
        return result;
    }

    protected boolean passItem(MenuData item, String parentId, boolean security) {
        // 不返回首页
        if (item.getId().equals(Application.getAppConfig().getFormDefault()))
            return false;

        // 不返回隐藏菜单
        if (item.getHide())
            return false;

        // 是否是客制菜单
        if (item.getCustom()) {
            if (!this.getCusMenus(handle).locate("Code_", item.getId()))
                return false;
        }

        if (!parentId.equals(item.getParent()))
            return false;

        // 当前用户是否拥有权限
        if (item.isSecurity() != security)
            return false;

        // 进行权限检查
        if (!passport.passProc(item.getVersions(), item.getProccode()))
            return false;

        if (item.getId().equals(TAcc)) {
            if (!BookOptions.isEnableAccBook(handle))
                return false;
        }
        return true;
    }

    protected DataSet getCusMenus(IHandle handle) {
        if (cusMenus == null) {
            SqlQuery ds = new SqlQuery(handle);
            ds.add("select Code_ from %s", systemTable.getCustomMenus());
            ds.add("where CorpNo_='%s'", handle.getCorpNo());
            ds.open();
            this.cusMenus = ds;
        }
        return cusMenus;
    }

    private void copyMenuData(MenuData item, MenuItem menu) {
        menu.setParam(MenuItem.TITLE, item.getCaption());
        menu.setParam(MenuItem.PAGENO, item.getFormNo());
        menu.setParam(MenuItem.SECURITY, item.isSecurity() ? "true" : "false");
        menu.setParam(MenuItem.SOFTWARE, item.getVersions());
        menu.setParam(MenuItem.PERMISSION, item.getProccode());
        menu.setParam(MenuItem.PARENT, item.getParent());
        menu.setParam(MenuItem.IMAGE, item.getImage());
    }

    /**
     * 添加菜单
     * 
     * @author rick_zhou
     */
    public static List<MenuData> getMainMenu(IHandle handle) {
        IPassport passport = Application.getPassport(handle);
        List<MenuData> menus = new ArrayList<>();
        List<MenuData> items = getList("manage", false, "");
        for (MenuData item : items) {
            if (passport.passProc(item.getVersions(), item.getProccode())) {
                menus.add(item);
                List<MenuData> args = getList(item.getId(), false, "");
                for (MenuData child : args) {
                    if (passport.passProc(child.getVersions(), child.getProccode()))
                        menus.add(child);
                }
            }
        }
        return menus;
    }

    /**
     * 获取菜单列表
     * 
     * @author rick_zhou
     * @param parentMenu
     * @param security
     * @param device
     * @return
     */
    private static List<MenuData> getList(String parentMenu, boolean security, String device) {
        Map<String, MenuData> menus = AppMenu.getItems();
        List<MenuData> result = new ArrayList<MenuData>();
        for (String key : menus.keySet()) {
            MenuData item = menus.get(key);
            if (item.getParent().equals(parentMenu)) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * 后台获取菜单列表
     * 
     * @author lubiao
     * @param handle
     * @return menus
     */
    public static List<MenuData> getMenuList(IHandle handle) {
        List<MenuData> menusList = new ArrayList<>();
        LocalService svr = new LocalService(handle, "SvrMenuQuery");
        if (!svr.exec())
            throw new RuntimeException(svr.getMessage());
        List<Record> records = svr.getDataOut().getRecords();
        for (Record record : records) {
            MenuData menuData = new MenuData();
            menuData.setId(record.getString("Class_"));
            menuData.setCaption(record.getString("Name_"));
            menuData.setParent(record.getString("Parent_"));
            menuData.setImage(record.getString("Image_"));
            menuData.setProccode(record.getString("Code_"));
            menusList.add(menuData);
            // menus.put(record.getSafeString("Code_"), menuData);
        }
        return menusList;
    }
}
