package cn.cerc.sample.services;

import cn.cerc.core.DataSet;
import cn.cerc.mis.core.AbstractService;
import cn.cerc.mis.core.IStatus;
import cn.cerc.mis.core.ServiceException;

public class ServiceUserList extends AbstractService {

    @Override
    public IStatus execute(DataSet dataIn, DataSet dataOut) throws ServiceException {
        return this.success();
    }

}
