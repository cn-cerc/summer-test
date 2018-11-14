package cn.cerc.sample;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
@Slf4j
public class WebHandle implements AutoCloseable{

    public WebHandle() {
        log.info("create");
    }

    public String getName() {
        return "web handle, hello.";
    }

    @Override
    public void close() throws Exception {
        log.info("close");
    }
}
