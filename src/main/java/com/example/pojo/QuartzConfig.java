package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@AllArgsConstructor
@NoArgsConstructor

@XmlRootElement(name = "QuartzConfig")   //根标签，此类对应configuration
@XmlAccessorType(XmlAccessType.FIELD)
public class QuartzConfig {

    @XmlElement(name = "Schedules")   //子节点
    private Schedules schedules;
}
