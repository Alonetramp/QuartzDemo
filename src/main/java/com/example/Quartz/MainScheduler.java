package com.example.Quartz;

import com.example.pojo.QuartzConfig;
import com.example.pojo.Schedule;
import com.example.pojo.Schedules;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import utils.xml.XmlFileReader;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainScheduler {

    private static String workSpace = System.getProperty("user.dir");
    private static String resource = workSpace + "\\src\\main\\resources";

    public static void main(String[] args) throws Exception {

        File file = new File(resource,"test.xml");

        XmlFileReader xmlFileReader = new XmlFileReader();
        StringBuffer stringBuffer = xmlFileReader.read(file);
        QuartzConfig quartzConfig = (QuartzConfig) xmlFileReader.Builder(QuartzConfig.class,stringBuffer.toString());
        Schedules schedules = quartzConfig.getSchedules();
        List<Schedule> scheduleList = schedules.getSchedules();

        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler scheduler = sf.getScheduler();


        for (Schedule schedule: scheduleList) {
            Class<? extends Job> jobClass = (Class<? extends Job>) (Class.forName(schedule.getJobDetail().getClazz()).newInstance().getClass());
            JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(schedule.getJobDetail().getName(), schedule.getJobDetail().getGroup())// 任务名称和组构成任务key
                    .build();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = sdf.parse(schedule.getTrigger().getStartTime());
            // 定义调度触发规则
            // 使用cornTrigger规则
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(schedule.getJobDetail().getName(), schedule.getJobDetail().getGroup())// 触发器key
                    .startAt(date)
                    .withDescription(schedule.getTrigger().getDescription())
                    .withSchedule(CronScheduleBuilder.cronSchedule(schedule.getTrigger().getCornExpression())).startNow().build();
            // 把作业和触发器注册到任务调度中
            scheduler.scheduleJob(jobDetail, trigger);
            // 启动
            if (!scheduler.isShutdown()) {
                scheduler.start();
            }
        }


    }
}
