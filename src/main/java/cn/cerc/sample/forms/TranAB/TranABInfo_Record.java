package cn.cerc.sample.forms.TranAB;

public class TranABInfo_Record {
    private String uid;
    private String tbno;
    private String tb;
    private String tbdate;
    private String supname;
    private String cusname;
    private String deptname;

    private String appuser;
    private String appdate;

    private int it;
    private String code;
    private String desc;
    private String spec;
    private String unit;
    private Double num;
    private Double Stock;

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public Double getStock() {
        return Stock;
    }

    public void setStock(Double stock) {
        Stock = stock;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getIt() {
        return it;
    }

    public void setIt(int it) {
        this.it = it;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getNum() {
        return num;
    }

    public void setNum(Double num) {
        this.num = num;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTbno() {
        return tbno;
    }

    public void setTbno(String tbno) {
        this.tbno = tbno;
    }

    public String getTb() {
        return tb;
    }

    public void setTb(String tb) {
        this.tb = tb;
    }

    public String getTbdate() {
        return tbdate;
    }

    public void setTbdate(String tbdate) {
        this.tbdate = tbdate;
    }

    public String getSupname() {
        return supname;
    }

    public void setSupname(String supname) {
        this.supname = supname;
    }

    public String getAppuser() {
        return appuser;
    }

    public String getCusname() {
        return cusname;
    }

    public void setCusname(String cusname) {
        this.cusname = cusname;
    }

    public void setAppuser(String appuser) {
        this.appuser = appuser;
    }

    public String getAppdate() {
        return appdate;
    }

    public void setAppdate(String appdate) {
        this.appdate = appdate;
    }

}
