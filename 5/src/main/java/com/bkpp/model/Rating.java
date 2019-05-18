package com.bkpp.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "rating")
@XmlEnum
public enum Rating {
    @XmlEnumValue("1")
    one,
    @XmlEnumValue("2")
    two,
    @XmlEnumValue("3")
    three,
    @XmlEnumValue("4")
    four,
    @XmlEnumValue("5")
    five,
    @XmlEnumValue("brak")
    empty;

    public Rating fromValue(String v){
        return valueOf(v);
    }
}
