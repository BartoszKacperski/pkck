package com.bkpp.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "releaseDate")
public class ReleaseDate {
    @XmlElement(name = "year")
    private int year;
    @XmlElement(name = "month")
    private int month;
    @XmlElement(name = "day")
    private int day;
}
