package cn.cerc.sample.config;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.cerc.mis.core.HandleDefault;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
// @Scope(WebApplicationContext.SCOPE_REQUEST)
public class Handle extends HandleDefault {

}
