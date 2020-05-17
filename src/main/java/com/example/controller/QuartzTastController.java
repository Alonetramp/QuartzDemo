package com.example.controller;
import com.example.Quartz.QuartzManager;
import com.example.core.Result;
import com.example.core.ResultGenerator;
import com.example.pojo.QuartzTast;
import com.example.service.QuartzTastService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
* Created by CodeGenerator on 2020/05/16.
*/
@RestController
@RequestMapping("/quartz/tast")
public class QuartzTastController {

    @Autowired
    private QuartzManager quartzManager;

    @Autowired
    private QuartzTastService quartzTastService;

    @PostMapping("/add")
    public Result add(QuartzTast quartzTast) {
        quartzTastService.save(quartzTast);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        quartzTastService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping("/pauseJob")
    public Result pauseJob(QuartzTast quartzTast) throws SchedulerException {

        quartzTast = new QuartzTast();
        quartzTast.setJobName("helljude");
        quartzTast.setJobGroup("group");
        quartzManager.pauseJob(quartzTast);
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping("/resumeJob")
    public Result resumeJob(QuartzTast quartzTast) throws SchedulerException {
        quartzTast = new QuartzTast();
        quartzTast.setJobName("helljude");
        quartzTast.setJobGroup("group");
        quartzManager.resumeJob(quartzTast);
        return ResultGenerator.genSuccessResult(quartzTast);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<QuartzTast> list = quartzTastService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
