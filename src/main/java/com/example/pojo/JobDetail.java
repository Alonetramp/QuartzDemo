package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "JobDetail")
@XmlAccessorType(XmlAccessType.FIELD)
public class JobDetail {
    @XmlAttribute(name = "name")
    private String name;
    @XmlAttribute(name = "group")
    private String group;
    @XmlAttribute(name = "Jobclazz")
    private String clazz;
}
