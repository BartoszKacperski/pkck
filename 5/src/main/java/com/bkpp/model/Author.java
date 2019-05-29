package com.bkpp.model;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;

@Data

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "author")
public class Author {
    @XmlID
    @XmlAttribute(name = "authorId")
    private String authorId;
    @XmlElement(name = "firstName")
    private List<String> firstNames;
    @XmlElement(name = "lastName")
    private String lastName;
    @XmlElement(name = "pseudonym")
    private String pseudonym;

    public void clearFirstNames() {
        firstNames.clear();
    }

    public boolean addFirstName(String name) {
        return firstNames.add(name);
    }
}
