package com.bkpp.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "category")
@XmlEnum
public enum Category {
    @XmlEnumValue("dramat")
    drama,
    @XmlEnumValue("horror")
    horror,
    @XmlEnumValue("kryminał")
    criminal,
    @XmlEnumValue("powieść")
    novel,
    @XmlEnumValue("akcja")
    action;



    public Category fromValue(String v){
        return valueOf(v);
    }
}
