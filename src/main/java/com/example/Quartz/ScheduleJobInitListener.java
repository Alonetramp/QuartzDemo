package com.example.Quartz;

import com.example.service.QuartzTastService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 1)
public class ScheduleJobInitListener implements CommandLineRunner {

    private static Logger LOGGER = LoggerFactory.getLogger(ScheduleJobInitListener.class);

    @Autowired
    QuartzTastService scheduleJobService;

    @Override
    public void run(String... args) throws Exception {

        LOGGER.info("ScheduleJobInitListener执行.....");

        try {
            scheduleJobService.initSchedule();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
