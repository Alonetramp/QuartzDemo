package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "Trigger")
@XmlAccessorType(XmlAccessType.FIELD)
public class Trigger {

    @XmlAttribute(name = "name")
    private String name;
    @XmlAttribute(name = "group")
    private String group;

    @XmlElement(name = "description")
    private String description;

    @XmlElement(name = "startTime")
    private String startTime;

    @XmlElement(name = "corn-expression")
    private String cornExpression;

}
