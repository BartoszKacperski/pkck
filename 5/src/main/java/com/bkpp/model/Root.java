package com.bkpp.model;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;

@Data

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "root")
public class Root {
    @XmlElementWrapper(name = "owners")
    @XmlElement(name = "owner")
    private List<Owner> owners;
    @XmlElement(name = "bookstore")
    private BookStore bookStore;
}
