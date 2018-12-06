package cn.cerc.sample.services;

import cn.cerc.core.DataSet;
import cn.cerc.core.IHandle;
import cn.cerc.core.Record;
import cn.cerc.mis.core.AbstractService;
import cn.cerc.mis.core.IStatus;
import cn.cerc.mis.core.ServiceException;

public class ServiceLogin extends AbstractService {

    @Override
    public IStatus execute(DataSet dataIn, DataSet dataOut) throws ServiceException {
        Record headIn = dataIn.getHead();
        String userCode = headIn.getString("usercode");
        String password = headIn.getString("password");
        if (!"admin".equals(userCode))
            return fail("用户帐号不存在！");
        if (!"123456".equals(password))
            return fail("用户密码不正确！");
        dataOut.getHead().setField("token", "000000");
        dataOut.append();
        dataOut.setField("num", 123);
        dataOut.setField("remark", "OK");
        return this.success();
    }

    @Override
    public boolean checkSecurity(IHandle handle) {
        return true;
    }
}
