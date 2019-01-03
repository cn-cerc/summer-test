package cn.cerc.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import cn.cerc.sample.config.Handle;

@RestController
@Scope(WebApplicationContext.SCOPE_REQUEST)
@RequestMapping("/app")
public class HelloWorld {
    @Autowired
    private Handle handle;

    @RequestMapping("/FrmTest.{func}")
    @ResponseBody
    public String hello(@PathVariable String func) {
        return handle.getUserName() + ":" + func;
    }
}
