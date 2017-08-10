package cn.cerc.sample.services;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.rds.StubHandle;
import cn.cerc.jdb.core.Record;

public class ServiceLoginTest {

    private StubHandle handle = new StubHandle();

    @Test
    public void testExecute() {
        LocalService app = new LocalService(handle, "ServiceLogin");
        Record headIn = app.getDataIn().getHead();
        headIn.setField("userCode", "admin");
        headIn.setField("password", "admin");
        assertTrue(app.exec());
        System.out.println(app.getDataOut());
    }

}
