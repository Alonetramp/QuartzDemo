package com.example.Quartz;

public enum JobStatusEnum {

    RUNNING("运行",1),
    STOP("停止",0);

    //成员变量
    private String name;
    private int status;

    //构造方法
    private JobStatusEnum(String name,int status)
    {
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public int getStatus() {
        return status;
    }



}
