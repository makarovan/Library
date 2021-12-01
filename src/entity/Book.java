/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Melnikov
 */


/**
 * mysql-connector
 * eclipselink jpa 2.1
 * persistence jpa 2.1
 * @author pupil
 */
@Entity
public class Book implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String caption;
    @OneToOne//cascade больше не нужен
    private List<Author> author;
    private int publishedYear;
    private int quantity;
    private int count;
  
    
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Book: " + caption 
                + ", author: " + Arrays.toString(author.toArray())
                + ", publishedYear: " + publishedYear 
                + ", quantity: " + quantity 
                + ", count: " + count;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.caption);
        hash = 47 * hash + Objects.hashCode(this.author);
        hash = 47 * hash + this.publishedYear;
        hash = 47 * hash + this.quantity;
        hash = 47 * hash + this.count;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Book other = (Book) obj;
        if (this.publishedYear != other.publishedYear) {
            return false;
        }
        if (this.quantity != other.quantity) {
            return false;
        }
        if (this.count != other.count) {
            return false;
        }
        if (!Objects.equals(this.caption, other.caption)) {
            return false;
        }
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}