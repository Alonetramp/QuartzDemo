package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "Schedule")
@XmlAccessorType(XmlAccessType.FIELD)
public class Schedule {
    @XmlAttribute(name = "c1")
    private String c1;

    @XmlElement(name = "JobDetail")
    private JobDetail jobDetail;

    @XmlElement(name = "Trigger")
    private Trigger trigger;

}
