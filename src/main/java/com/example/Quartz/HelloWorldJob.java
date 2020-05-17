package com.example.Quartz;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@PersistJobDataAfterExecution
//作业不并发
@DisallowConcurrentExecution
public class HelloWorldJob implements Job {

    private static Logger LOGGER = LoggerFactory.getLogger(HelloWorldJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //Job能通过JobExecutionContext对象访问到Quartz运行时候的环境以及Job本身的明细数据
        JobDataMap data = jobExecutionContext.getTrigger().getJobDataMap();
        String invokeParam =(String) data.get("invokeParam");
        //在这里实现业务逻辑
        LOGGER.info("这是一个定时任务:{} /n {}",data,invokeParam);
    }
}
