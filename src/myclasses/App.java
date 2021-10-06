/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myclasses;

import entity.Reader;
import entity.Book;
import entity.Author;
import entity.History;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 *
 * @author pupil
 */
public class App {
    Scanner scanner = new Scanner(System.in);
    Book[] books = new Book[10];
    Reader[] readers = new Reader[10];
    
    public App() {
    }
    public void run(){
        String repeat = "y";
        do{
            System.out.println("Выберите задачу");
            System.out.println("0: закончить программу");
            System.out.println("1: добавить книгу");
            System.out.println("2: Вывести список книг");
            System.out.println("3: Добавить читателя");
            System.out.println("4: Список читателей");
            int task = scanner.nextInt();
            scanner.nextLine();
            switch(task){
                case 0: 
                    repeat ="q";
                    System.out.println("Программа закончена");
                    break;
               
                case 1: 
                    System.out.println("Добавление книги");
                    for (int i = 0; i < books.length; i++) {
                        if (books[i]==null) {
                            books[i] = addBook();
                        break;
                        }
                    }
                    break;
                
                case 2:
                    repeat ="q";
                    System.out.println("Список книг:");
                    for (int i = 0; i < books.length; i++) {
                        if (books[i]!=null){//во избежание ошибки nullpointerexception, когда печатаешь массив из 10 ячеек, но только 2 заполнены
                           System.out.println(books[i].toString()); 
                        }
                    }
                    break;
                
                case 3:
                    System.out.println("Добавления читателя");
                    for (int i = 0; i < readers.length; i++) {
                        if (readers[i]==null) {
                            readers[i] = addReader();
                        break;
                        }
                    }
                    break;
                case 4:
                    System.out.println("Список читателей");
                    for (int i = 0; i < readers.length; i++) {
                        if (readers[i]!=null){//во избежание ошибки nullpointerexception, когда печатаешь массив из 10 ячеек, но только 2 заполнены
                           System.out.println(readers[i].toString()); 
                        }
                    }
                    break;
                //default:
//                    throw new AssernionError();
            }
        }while("y".equals(repeat));
        
//        for (int i = 0; i < books.length; i++) {
//            if (books[i]==null) {
//                books[i] = addBook();
//            }
//        }
//        addBook();
    }
    
    private Book addBook(){
        Book book = new Book();
        
        System.out.print("Название книги: ");
        book.setCaption(scanner.nextLine());
        System.out.print("Год публикации: ");
        book.setPublishedYear(scanner.nextInt());
        scanner.nextLine();
//считает знак новой строки и очистит буфер от знака новой строки, чтобы он не попал в след. сканер и не пропустил след. строку. 
//нужен только если после считывания цифры считывается строка
        System.out.print("Кол-во авторов: ");
        int authorsNum = scanner.nextInt();
        scanner.nextLine();
        Author[] authors = new Author[authorsNum];
        for (int i = 0; i < authors.length; i++) {
            System.out.print("Имя автора "+(i+1)+": ");//сложение в скобках
            Author author = new Author();
            author.setName(scanner.nextLine());
            System.out.print("Фамилия автора: ");
            author.setLastname(scanner.nextLine());
            System.out.print("День рождения: ");
            author.setDay(scanner.nextInt());
            System.out.print("Месяц рождения: ");
            author.setMonth(scanner.nextInt());
            System.out.print("Год рождения: ");
            author.setYear(scanner.nextInt());
            scanner.nextLine();
            authors[i]=author;
            
        }
        book.setAuthor(authors);
        return book;
    }
            
    private Reader addReader(){
        Reader reader = new Reader();
        System.out.print("Имя читателя: ");
        reader.setFirstname(scanner.nextLine());
        System.out.println("Фамилия читателя: ");
        reader.setLastname(scanner.nextLine());
        System.out.println("телефон читателя: ");
        reader.setPhone(scanner.nextLine());
        return reader;
    }
            
        //System.out.println("Hello");
//        Book book1 = new Book();
//        book1.setCaption("Voina i Mir");
//        Author author1 = new Author();
//        author1.setName("Lev");
//        author1.setLastname("Tolstoi");
//        author1.setYear(1828);
//        author1.setDay(9);
//        author1.setMonth(9);
//        Author[] authors = new Author[1];
//        authors[0]=author1;  
//        book1.setAuthor(authors);
//        book1.setPublishedYear(2005);
//        
//        Book book2 = new Book();
//        book2.setCaption("Otsi i deti");
//        Author author2 = new Author();
//        author2.setName("Ivan");
//        author2.setLastname("Turgenev");
//        author2.setYear(1818);
//        author2.setDay(9);
//        author2.setMonth(11);
//        Author[] authorTurgenev = new Author[1];
//        authorTurgenev[0]=author2;
//        book2.setAuthor(authorTurgenev);
//        book2.setPublishedYear(2012);
//        
//        Reader reader1 = new Reader();
//        reader1.setFirstname("Ivan");
//        reader1.setLastname("Ivanov");
//        reader1.setPhone("45478787");
//        
//        History history1 = new History();
//        history1.setBook(book1);
//        history1.setReader(reader1);
//        //задать сегодняшнюю дату
//        Calendar c = new GregorianCalendar();
//        history1.setGivenDate(c.getTime());
//        System.out.println(history1.toString());
//        //задать дату возврата
//        c.add(Calendar.WEEK_OF_YEAR, 2);
//        history1.setReturnDate(c.getTime());
//        System.out.println("___________________________");
//        //вывод
//        System.out.println(history1.toString());
        
        
    //}
}
