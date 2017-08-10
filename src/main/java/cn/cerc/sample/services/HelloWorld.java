package cn.cerc.sample.services;

import cn.cerc.jbean.core.AbstractService;
import cn.cerc.jbean.core.IStatus;
import cn.cerc.jbean.core.ServiceException;
import cn.cerc.jdb.core.DataSet;
import cn.cerc.jdb.core.IHandle;
import cn.cerc.jdb.core.TDateTime;

public class HelloWorld extends AbstractService {

    @Override
    public IStatus execute(DataSet dataIn, DataSet dataOut) throws ServiceException {
        dataOut.getHead().setField("tbNo", "OD-20160930-0001");
        dataOut.getHead().setField("tbDate", TDateTime.Now());
        dataOut.append();
        dataOut.setField("code", "a001");
        dataOut.setField("num", 100);
        dataOut.append();
        dataOut.setField("code", "a002");
        dataOut.setField("num", 200);
        return this.success("hello world: " + dataIn.getHead());
    }

    public boolean checkSecurity(IHandle handle) {
        return true;
    }
}
