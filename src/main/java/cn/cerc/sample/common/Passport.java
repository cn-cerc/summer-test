package cn.cerc.sample.common;

import cn.cerc.jbean.core.AbstractHandle;
import cn.cerc.jbean.core.IPassport;
import cn.cerc.jbean.rds.PassportRecord;

public class Passport extends AbstractHandle implements IPassport {

    @Override
    public boolean passProc(String versions, String procCode) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean passAction(String procCode, String action) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public PassportRecord getRecord(String procCode) {
        // TODO Auto-generated method stub
        PassportRecord result = new PassportRecord();
        result.setAdmin(true);
        return result;
    }

    @Override
    public boolean passsMenu(String menuCode) {
        // TODO Auto-generated method stub
        return true;
    }

}
