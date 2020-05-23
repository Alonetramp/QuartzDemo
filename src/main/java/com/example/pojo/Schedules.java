package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "Schedules")    //根标签，此类对应city
@XmlAccessorType(XmlAccessType.FIELD)
public class Schedules{
    @XmlElement(name = "Schedule")
    private List<Schedule> schedules;
}
