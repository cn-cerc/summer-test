package cn.cerc.sample;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

@RestController
@Scope(WebApplicationContext.SCOPE_REQUEST)
@RequestMapping("/app")
public class HelloWorld2 {

    @RequestMapping("/FrmTest2.{func}")
    @ResponseBody
    public String hello(@PathVariable String func) {
        return "test2:" + func;
    }
}
