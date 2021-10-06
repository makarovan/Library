/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import entity.Author;
import java.util.Arrays;

/**
 *
 * @author pupil
 */
public class Book {
    private String caption;
    private Author[] author;
    private int publishedYear;
    public Book() {
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Author[] getAuthor() {
        return author;
    }

    public void setAuthor(Author[] author) {
        this.author = author;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    //вывод текста
    @Override
    public String toString() {
        return "Book:" + "\ncaption: " + caption + Arrays.toString(author) + ",\npublishedYear=" + publishedYear + "}";
    }
    
    
}
