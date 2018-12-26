package cn.cerc.sample;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.cerc.core.TDateTime;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ScheduledDemo {

    @Scheduled(fixedDelay = 3000)
    public void run() {
        log.info("{}", "" + TDateTime.Now());
    }
}
