package cn.cerc.sample.config;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import cn.cerc.mis.core.StartFormDefault;

@Controller
@Scope(WebApplicationContext.SCOPE_REQUEST)
@RequestMapping("/forms")
public class StartForms extends StartFormDefault {

}
