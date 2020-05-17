package com.example.service.impl;

import com.example.Quartz.JobStatusEnum;
import com.example.Quartz.QuartzManager;
import com.example.dao.QuartzTastMapper;
import com.example.pojo.QuartzTast;
import com.example.service.QuartzTastService;
import com.example.core.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by CodeGenerator on 2020/05/16.
 */
@Service
@Transactional
public class QuartzTastServiceImpl extends AbstractService<QuartzTast> implements QuartzTastService {

    @Autowired
    private QuartzManager quartzManager;


    @Autowired
    private QuartzTastMapper plmQuartzTastMapper;


    @Override
    public void initSchedule() {
        // 这里获取任务信息数据
        List<QuartzTast> jobList = plmQuartzTastMapper.selectAll();
        for (QuartzTast task : jobList) {
            if (JobStatusEnum.RUNNING.getStatus() == task.getJobStatus()) {
                quartzManager.addJob(task);
            }
        }
    }
}
