package cn.cerc.sample.services;

import cn.cerc.core.DataSet;
import cn.cerc.core.IHandle;
import cn.cerc.core.TDateTime;
import cn.cerc.mis.core.AbstractService;
import cn.cerc.mis.core.IStatus;
import cn.cerc.mis.core.ServiceException;

public class HelloWorld extends AbstractService {

    @Override
    public IStatus execute(DataSet dataIn, DataSet dataOut) throws ServiceException {
        dataOut.getHead().setField("tbNo", "OD-20160930-0001");
        dataOut.getHead().setField("tbDate", TDateTime.now());
        dataOut.append();
        dataOut.setField("code", "a001");
        dataOut.setField("num", 100);
        dataOut.append();
        dataOut.setField("code", "a002");
        dataOut.setField("num", 200);
        return this.success("hello world: " + dataIn.getHead());
    }

    @Override
    public boolean checkSecurity(IHandle handle) {
        return true;
    }
}
