package com.bkpp.model;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "book")
public class Book {
    @XmlAttribute(name = "bookId")
    private String bookId;
    @XmlAttribute(name = "authorId")
    @XmlIDREF
    private Author author;
    @XmlAttribute(name = "category")
    private Category category;
    @XmlAttribute(name = "pages")
    private Integer pages;
    @XmlAttribute(name = "rating")
    private Rating rating;
    @XmlElement(name = "title")
    private String title;
    @XmlElement(name = "releaseDate")
    private ReleaseDate releaseDate;
}
