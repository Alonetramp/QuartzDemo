package com.example.Quartz;

import org.quartz.*;

@PersistJobDataAfterExecution
//作业不并发
@DisallowConcurrentExecution
public class HelloWorldJob implements Job {

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //Job能通过JobExecutionContext对象访问到Quartz运行时候的环境以及Job本身的明细数据
        JobDataMap data = jobExecutionContext.getTrigger().getJobDataMap();
        String invokeParam =(String) data.get("invokeParam");
        //在这里实现业务逻辑
        System.out.println("HelloWorldJob定时任务执行....");
    }
}
