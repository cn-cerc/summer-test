package cn.cerc.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class WebHandle implements AutoCloseable {
    private static final Logger log = LoggerFactory.getLogger(WebHandle.class);

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
