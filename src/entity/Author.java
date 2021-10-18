/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author pupil
 */
public class Author implements Serializable{
    private String name;
    private String lastname;
    private int year;
    private int day;
    private int month;
    
    public Author() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
    
    //вывод текста
    @Override
    public String toString() {
        return "\nAuthor: " + name +" "+ lastname;
        //return "\nAuthor{" + "name=" + name + ",\n lastname=" + lastname + ",\n year=" + year + ",\n day=" + day + ",\n month=" + month + '}';
    }
    
    
}
