package com.bkpp.model;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;

@Data

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "bookstore")
public class BookStore {
    @XmlElementWrapper(name = "authors")
    @XmlElement(name = "author")
    private List<Author> authors;
    @XmlElementWrapper(name = "books")
    @XmlElement(name = "book")
    private List<Book> books;
}
