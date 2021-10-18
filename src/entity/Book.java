/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import entity.Author;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author pupil
 */
public class Book implements Serializable{ //сериализация переводит в байты
    private String caption;
    private List<Author> author;
    private int publishedYear;
    public Book() {
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public List<Author> getAuthor() {
        return author;
    }

    public void setAuthor(List<Author> author) {
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
        return "Book:" + "\ncaption: " + caption + Arrays.toString(author.toArray()) + ",\npublishedYear=" + publishedYear;
    }


    
}
