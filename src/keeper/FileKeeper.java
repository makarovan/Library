/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keeper;

import entity.Author;
import entity.Book;
import entity.History;
import entity.Reader;
import interfaces.Keeping;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pupil
 */
public class FileKeeper implements Keeping{

    @Override//переопределение
    public void saveBooks(List<Book> books) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream("books");//использование текущей директории, букс будет создан в папке проекта
            oos = new ObjectOutputStream(fos);
            oos.writeObject(books);
            oos.flush();//проталкивание данных на жесткий диск
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileKeeper.class.getName()).log(Level.SEVERE, "file books not found", ex);
        } catch (IOException ex) {
            Logger.getLogger(FileKeeper.class.getName()).log(Level.SEVERE, "input error", ex);
        }
    }

    @Override
    public List<Book> loadBooks() {
       List <Book> listBooks = new ArrayList();
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream("books");
            ois = new ObjectInputStream(fis);
            listBooks = (List<Book>) ois.readObject();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileKeeper.class.getName()).log(Level.SEVERE, "file books not found", ex);
        } catch (IOException ex) {
            Logger.getLogger(FileKeeper.class.getName()).log(Level.SEVERE, "output error", ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FileKeeper.class.getName()).log(Level.SEVERE, "class not found", ex);
        }
        
       return listBooks;
    }

    @Override
    public void saveReaders(List<Reader> readers) {
        
    }

    @Override
    public List<Reader> loadReaders() {
       List <Reader> listReaders = new ArrayList();
       return listReaders;
    }

    @Override
    public void saveHistories(List<History> histories) {
        
    }

    @Override
    public List<History> loadHistories() {
        List <History> listHistories = new ArrayList();
        return listHistories;
    }
    //авторы сохраняются в книгу
    
    
}
