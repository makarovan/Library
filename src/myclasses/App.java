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
import interfaces.Keeping;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import keeper.FileKeeper;

/**
 *
 * @author pupil
 */
public class App {
    Scanner scanner = new Scanner(System.in);
    List<Book> books = new ArrayList<>();
    List<Reader> readers = new ArrayList<>();
    List<History> histories = new ArrayList<>();
    Keeping keeper = new FileKeeper(); //ctrl + r чтобы изменить везде в программе название переменной 
    //Keeping keeper = new BaseKeeper(); //изменим реализацию кипера
    
    public App() {//constructor
        books = keeper.loadBooks(); //подгружаем книги из файла
        histories = keeper.loadHistories();
        readers = keeper.loadReaders();
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
            System.out.println("5: Выдать книгу");
            System.out.println("6: Список выданных книг");
            System.out.println("7: Вернуть книгу");
            System.out.println("8: Редактирование книги");
            
            int task = scanner.nextInt();
            scanner.nextLine();
            switch(task){
                case 0: 
                    repeat ="q"; // в конце проверяется, равна ли эта строка "y". если не равна, то программа заканчивается
                    System.out.println("Программа закончена");
                    break;
               
                case 1: 
                    addBook();
                    
                    break;
                case 2:
                    booksList();
                    break;
                
                case 3:
                    addReader();
                    break;
                    
                case 4:
                    readersList();
                    break;
                
                case 5:
                    addHistory();
                    break;
                case 6:
                    printGivenBooks();
                    break;
                
                case 7:
                    returnBook();
                    break;
            }
        }while("y".equals(repeat));
    }
    
   
    
    private void addHistory(){ //error
        System.out.println("Выдача книги");
        History history = new History();
        /**
         * 1. вывести нумированный список книг
         * 2. получить от читателя номер книги: bookNumber
         * 3. вывести список читателей
         * 4. получить номер читателя: readerNumber
         * 5. в history инициировать поле book объектом, который  лежит в массиве books[bookNumber-1]
         * 6. в history инициировать поле reader объектом, который лежит в массиве readers[readerNumber-1]
         * 7. полуичть текущую дату и положить ее в поле history.givenDate
         */
        
        booksList();
        Set <Integer> setNumbersBooks = booksList();
        if(setNumbersBooks.isEmpty()){//если все книги выданы, то не дает выдать
            return;
        }
        int bookNumber;
        do{
            System.out.println("Book number:");
            String strBookNumber = scanner.nextLine();
            try{
                bookNumber = Integer.parseInt(strBookNumber);
            }catch(Exception e){
                bookNumber=0;
            }
        }while(!setNumbersBooks.contains(bookNumber));

        if(books.get(bookNumber-1).getCount()==0){
            System.out.println("в библиотеке нет экземпляров книги");
        }else{
            history.setBook(books.get(bookNumber-1));

            readersList();
            System.out.print("Номер читателя: ");
            int readerNumber = scanner.nextInt(); scanner.nextLine();
            history.setReader(readers.get(readerNumber-1)); 

            //задать сегодняшнюю дату
            Calendar c = new GregorianCalendar();
            history.setGivenDate(c.getTime());
            //задать дату возврата
    //        c.add(Calendar.WEEK_OF_YEAR, 2);
    //        history.setReturnDate(c.getTime());
            history.getBook().setCount(history.getBook().getCount()-1);
            histories.add(history);
            keeper.saveBooks(books);
            keeper.saveHistories(histories);
        }
    }
    
    
    private void addBook(){
        System.out.println("Добавление книги");
        Book book = new Book();
        System.out.print("Название книги: ");
        book.setCaption(scanner.nextLine());
        System.out.print("Год публикации: ");
        book.setPublishedYear(scanner.nextInt());
        scanner.nextLine();
/**
 * считает знак новой строки и очистит буфер от знака новой строки, чтобы он не попал в след. сканер и не пропустил след. строку. 
 * нужен только если после считывания цифры считывается строка
*/
        System.out.print("Кол-во авторов: ");
        int authorsNum = scanner.nextInt();
        scanner.nextLine();
        List<Author> authors = new ArrayList<>();
        for (int i = 0; i < authorsNum ; i++) {
            System.out.print("Имя автора "+(i+1)+": ");//сложение в скобках
            Author author = new Author();
            author.setName(scanner.nextLine());
            System.out.print("Фамилия автора: ");
            author.setLastname(scanner.nextLine());
            System.out.print("День рождения: ");
            author.setDay(scanner.nextInt());scanner.nextLine();
            System.out.print("Месяц рождения: ");
            author.setMonth(scanner.nextInt());scanner.nextLine();
            System.out.print("Год рождения: ");
            author.setYear(scanner.nextInt());
            scanner.nextLine();
            authors.add(author);
        }
        book.setAuthor(authors);
        System.out.print("Кол-во экземпляров: ");
        book.setQuantity(scanner.nextInt());scanner.nextLine();
        book.setCount(book.getQuantity());
        books.add(book);
        keeper.saveBooks(books);
    }
            
    private void addReader(){
        System.out.println("Добавления читателя");
        Reader reader = new Reader();
        System.out.print("Имя читателя: ");
        reader.setFirstname(scanner.nextLine());
        System.out.println("Фамилия читателя: ");
        reader.setLastname(scanner.nextLine());
        System.out.println("телефон читателя: ");
        reader.setPhone(scanner.nextLine());
        readers.add(reader);
        keeper.saveReaders(readers);
    }
    
    private Set<Integer> printGivenBooks(){ //ERROR
        System.out.println("Выданные книги:");
        Set<Integer> setNumberGivenBooks = new HashSet<>();
        for (int i = 0; i < histories.size(); i++) {
            if (histories.get(i)!=null && histories.get(i).getReturnDate() == null 
                    && histories.get(i).getBook().getCount()<histories.get(i).getBook().getQuantity() &&  histories.get(i).getBook().getCount()<0){ //если книг в наличии меньше, чем записано в кол-ве
                System.out.printf("%d. Книгу %s читает %s %s%n",
                                (i+1),
                                histories.get(i).getBook().getCaption(), 
                                histories.get(i).getReader().getFirstname(), 
                                histories.get(i).getReader().getLastname());
               setNumberGivenBooks.add(i++);
               //экземпляры закончились
            }
        }
        if (setNumberGivenBooks.isEmpty()){
            System.out.println("Выданных книг нет");
        }
        return setNumberGivenBooks;
    }
    
    private Set<Integer> booksList(){ //ERROR
        System.out.println("Список книг:");
        Set <Integer> setNumbersBooks = new HashSet();
        for (int i = 0; i < books.size(); i++) {
            StringBuilder sbAuthors = new StringBuilder();
            for (int j = 0; j < books.get(i).getAuthor().size(); j++) {
                sbAuthors.append(books.get(i).getAuthor().get(j).getName())
                        .append(" ")
                        .append(books.get(i).getAuthor().get(j).getLastname())
                        .append(". ");
                ;
            }
            if (books.get(i)!=null && books.get(i).getCount()>0){//во избежание ошибки nullpointerexception, когда печатаешь массив из 10 ячеек, но только 2 заполнены
                System.out.printf("%d. %s. %s В наличии экземпляров: %d%n",
                        i+1,
                        books.get(i).getCaption(),
                        sbAuthors.toString(),
                        books.get(i).getCount()); 
                setNumbersBooks.add(i+1);
            }else if(books.get(i)!=null){
                System.out.printf("%d. %s. %s Нет в наличии. будет возвращена: %s%n",
                        i+1,
                        books.get(i).getCaption(),
                        sbAuthors.toString(),
                        getReturnDate(books.get(i))); 
            }
        }
        return setNumbersBooks;
    }

    //указываем дату когда будет возвращена книга, если экземпляров больше нет
    private String getReturnDate(Book book){
        for (int i = 0; i < histories.size(); i++) {
            if (book.getCaption(). equals(histories.get(i).getBook().getCaption())){
                LocalDate localGivenDate = histories.get(i).getGivenDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                localGivenDate = localGivenDate.plusDays(14);
                return localGivenDate.format(DateTimeFormatter.ofPattern("dd.MM.yyy"));
            }
        }
        return "";
    }
    
    private void readersList() {
        System.out.println("Список читателей");
        for (int i = 0; i < readers.size(); i++) {
            if (readers.get(i)!=null){
                System.out.printf("%d. %s%n", i+1,readers.get(i).toString());
            }
        }
    }

    private void returnBook() {
        System.out.println("Возвращение книги");
        Set<Integer> numbersGivenBooks = printGivenBooks();
        if (numbersGivenBooks.isEmpty()){
            return;
        }

        int historyNumber;
        
        do{
            System.out.print("Выберите возвращаемую книгу: ");
            String strHistoryNumber = scanner.nextLine();
            //проверка, что вводится число
            try{
                historyNumber = Integer.parseInt(strHistoryNumber);
            }catch (Exception e){
                historyNumber = 0;
            }
            
        }while (!numbersGivenBooks.contains(historyNumber-1));
        
        Calendar c = new GregorianCalendar();
        histories.get(historyNumber-1).setReturnDate(c.getTime());
        //histories.get(historyNumber-1).getBook().setCount(histories.get(historyNumber-1).getBook().getCount()+1);
        // https://coderoad.ru/40480/%D0%AD%D1%82%D0%BE-Java-pass-by-reference-%D0%B8%D0%BB%D0%B8-pass-by-value
        //находим книгу из истории в массиве книг и меняем ей кол-во
        for (int i = 0; i < books.size(); i++) {
          if(books.get(i).getCaption().equals(histories.get(historyNumber-1).getBook().getCaption())){
            books.get(i).setCount(books.get(i).getCount()+1);
          }
        }
        keeper.saveBooks(books);
        keeper.saveHistories(histories);                    
    }
}
