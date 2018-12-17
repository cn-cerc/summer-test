package cn.cerc.sample.config;

import org.springframework.stereotype.Component;

import cn.cerc.mis.config.AppConfigDefault;

@Component
public class AppConfig extends AppConfigDefault {

    @Override
    public String getFormWelcome() {
        return "FrmWelcome";
    }

    @Override
    public String getFormDefault() {
        return "FrmDefault";
    }

}
