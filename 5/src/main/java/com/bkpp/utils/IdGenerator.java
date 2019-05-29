package com.bkpp.utils;

import com.bkpp.model.Author;
import com.bkpp.model.Book;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class IdGenerator {
    private IdGenerator(){

    }

    public static String generateAuthorId(List<Author> authors){
        List<Integer> idWithoutPrefix = authors.stream()
                .map(a -> Integer.valueOf(a.getAuthorId().substring(1)))
                .collect(Collectors.toList());

        int max = Collections.max(idWithoutPrefix);

        return "a" + (max + 1);
    }

    public static String generateBookId(List<Book> books){
        List<Integer> idWithoutPrefix = books.stream()
                .map(a -> Integer.valueOf(a.getBookId().substring(1)))
                .collect(Collectors.toList());

        int max = Collections.max(idWithoutPrefix);

        return "b" + (max + 1);
    }

}
