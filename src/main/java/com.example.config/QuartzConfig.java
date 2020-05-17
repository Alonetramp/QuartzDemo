package com.example.config;

import com.example.Quartz.HelloWorldJob;
import org.quartz.Scheduler;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.quartz.spi.JobFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
public class QuartzConfig implements ApplicationListener<ContextRefreshedEvent> {

    private static Logger LOGGER = LoggerFactory.getLogger(QuartzConfig.class);

    @Autowired
    private JobFactory jobFactory;

    @Autowired
    private DataSource dataSource;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("任务已经启动..."+event.getSource());
    }

    @Bean("schedulerFactoryBean")
    public SchedulerFactoryBean schedulerFactoryBean(@Qualifier("quartzProperties") Properties quartzProperties) throws IOException {
        LOGGER.info("3.initSchedulerFactoryBean...");
        //创建SchedulerFactoryBean
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setQuartzProperties(quartzProperties);
        factory.setJobFactory(jobFactory);
        factory.setWaitForJobsToCompleteOnShutdown(true);//这样当spring关闭时，会等待所有已经启动的quartz job结束后spring才能完全shutdown。
        factory.setOverwriteExistingJobs(false);
//        factory.setStartupDelay(1);
        return factory;
    }


    //获取配置属性
    @Bean("quartzProperties")
    public Properties quartzProperties() throws IOException {
        LOGGER.info("2.initProperties.....");
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        //在quartz.properties中的属性被读取并注入后再初始化对象
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }


    /*
     * 通过SchedulerFactoryBean获取Scheduler的实例
     */
    @Bean("scheduler")
    public Scheduler scheduler(@Qualifier("schedulerFactoryBean")SchedulerFactoryBean schedulerFactoryBean) throws IOException {
        LOGGER.info("4.initScheduler...");
        return schedulerFactoryBean.getScheduler();
    }


    @Bean
    public QuartzInitializerListener executorListener() {
        LOGGER.info("1.initQuartzInitializerListener......");
        return new QuartzInitializerListener();
    }

}
