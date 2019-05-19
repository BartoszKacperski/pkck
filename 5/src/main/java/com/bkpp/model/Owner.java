package com.bkpp.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "owner")
public class Owner {
    @XmlElement(name = "firstName")
    private List<String> firstNames;
    @XmlElement(name = "lastName")
    private String lastName;

    public boolean addFirstName(String firstName) {
        return firstNames.add(firstName);
    }

    public void clearFirstNames() {
        firstNames.clear();
    }
}
