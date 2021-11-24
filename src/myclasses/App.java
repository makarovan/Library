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
import facade.AuthorFacade;
import facade.BookFacade;
import facade.HistoryFacade;
import facade.ReaderFacade;
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
import keeper.BaseKeeper;
import keeper.FileKeeper;

/**
 *
 * @author pupil
 */
public class App {
    public static boolean isBase;
    private Scanner scanner = new Scanner(System.in);
    //при использовании массивов при работе с большим кол-вом данных компьютер будет очень долно их обрабатывать
//    private List<Book> books = new ArrayList<>();
//    private List<Reader> readers = new ArrayList<>();
//    private List<History> histories = new ArrayList<>();
//    private List<Author> authors = new ArrayList<>();
    private BookFacade bookFacade = new BookFacade(Book.class);
    private ReaderFacade readerFacade = new ReaderFacade(Reader.class);
    private HistoryFacade historyFacade = new HistoryFacade(History.class);
    private AuthorFacade authorFacade = new AuthorFacade(Author.class);
    private Keeping keeper;
    
    public App() {//constructor ERROR
        if(App.isBase){
            keeper = new BaseKeeper(); //база данных
        }else{
            keeper = new FileKeeper(); //файлы
        }
        books = keeper.loadBooks(); //подгружаем книги из файла
        authors = keeper.loadAuthors();
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
            System.out.println("8: Добавить автора");
            System.out.println("9: Список авторов");
            System.out.println("10: Редактирование книги");
            
            int task = getNumber();
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
                
                case 8:
                    addAuthor();
                    break;
                    
                case 9:
                    printListAuthors();
                    break;
                    
                case 10:
                    updateBook();
                    break;
                    
                default:
                    //System.out.print("Выберите из списка задач");
                    break;
            }
        }while("y".equals(repeat));
    }
    
   
    
    private void addHistory(){
        System.out.println("Выдача книги");
        if(quit()) return;
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
        
        //booksList();
        Set <Integer> setNumbersBooks = booksList();
        if(setNumbersBooks.isEmpty()){//если все книги выданы, то не дает выдать
            return;
        }
        System.out.print("Введите номер книги из списка: ");
        int bookNumber = insertNumber(setNumbersBooks);
        history.setBook(bookFacade.find((long)bookNumber));
        
        Set<Integer> setNumbersReaders = readersList();
        if(setNumbersReaders.isEmpty()){
            return;
        } 
        int readerNumber = insertNumber(setNumbersReaders);
        history.setReader(readerFacade.find((long)readerNumber)); 

        //задать сегодняшнюю дату
        Calendar c = new GregorianCalendar();
        history.setGivenDate(c.getTime());
        
        Book book = bookFacade.find((long)bookNumber);
        book.setCount(history.getBook().getCount()-1);
        bookFacade.edit(book);
        historyFacade.create(history);
    }

    
    
    private void addBook(){
        System.out.println("Добавление книги");
        Book book = new Book();
        if(quit()) return;
        Set<Integer> setNumberAuthors=printListAuthors();
        if (setNumberAuthors.isEmpty()) {
            System.out.println("Добавьте автора.");
            return;
        }
        System.out.println("Если есть автор в списке, нажмите 1.");
        if(getNumber() != 1){
            System.out.println("Добавьте автора.");
            return;
        }
                
        System.out.print("Кол-во авторов: ");
        List<Author> authorsBook = new ArrayList<>();
        
        int authorsNum = getNumber();
        for (int i = 0; i < authorsNum ; i++) {
            System.out.println("Введите номер автора "+(i+1)+": ");
            int numberAuthor = insertNumber(setNumberAuthors);
            authorsBook.add(authorFacade.find((long)(numberAuthor)));
        }
        book.setAuthor(authorsBook);
        
        System.out.print("Название книги: ");
        book.setCaption(scanner.nextLine());
        System.out.print("Год публикации: ");
        book.setPublishedYear(getNumber());
        
        System.out.print("Кол-во экземпляров: ");
        book.setQuantity(getNumber());
        book.setCount(book.getQuantity());
    }
            
    private void addReader(){
        System.out.println("Добавления читателя");
        if(quit()) return;
        Reader reader = new Reader();
        System.out.print("Имя читателя: ");
        reader.setFirstname(scanner.nextLine());
        System.out.println("Фамилия читателя: ");
        reader.setLastname(scanner.nextLine());
        System.out.println("телефон читателя: ");
        reader.setPhone(scanner.nextLine());
        readerFacade.create(reader);
    }
    
    private Set<Integer> printGivenBooks(){
        System.out.println("Выданные книги:");
        Set<Integer> setNumberGivenBooks = new HashSet<>();
        for (int i = 0; i < histories.size(); i++) {
            if (histories.get(i)!=null && histories.get(i).getReturnDate() == null 
                    && histories.get(i).getBook().getCount()<histories.get(i).getBook().getQuantity()){ //если книг в наличии меньше, чем записано в кол-ве
                System.out.printf("%d. Книгу %s читает %s %s%n",
                                (i+1),
                                histories.get(i).getBook().getCaption(), 
                                histories.get(i).getReader().getFirstname(), 
                                histories.get(i).getReader().getLastname());
               setNumberGivenBooks.add(i+1);
               //экземпляры закончились
            }
        }
        if (setNumberGivenBooks.isEmpty()){
            System.out.println("Выданных книг нет");
        }
        return setNumberGivenBooks;
    }
    
    private Set<Integer> booksList(){
        System.out.println("Список книг:");
        List<Book> books = bookFacade.findAll();
        Set <Integer> setNumbersBooks = new HashSet();
        for (int i = 0; i < books.size(); i++) {
            StringBuilder sbAuthors = new StringBuilder();
            for (int j = 0; j < books.get(i).getAuthor().size(); j++) {
                sbAuthors.append(books.get(i).getAuthor().get(j).getName())
                        .append(" ")
                        .append(books.get(i).getAuthor().get(j).getLastname())
                        .append(". ");    
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
        History history = historyFacade.findHistoryByGivenBook(book);
        LocalDate localGivenDate = history.getGivenDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        localGivenDate = localGivenDate.plusDays(14);
        return localGivenDate.format(DateTimeFormatter.ofPattern("dd.MM.yyy"));
    }
    
    private Set<Integer> readersList() {
        Set<Integer> setNumbersReaders = new HashSet();
        System.out.println("Список читателей: ");
        List<Reader> readers = readerFacade.findAll();
        for (int i = 0; i < readers.size(); i++) {
            if (readers.get(i)!=null){
                System.out.printf("%d. %s %s. Телефон: %s%n"
                        , readers.get(i).getId()
                        , readers.get(i).getFirstname()
                        , readers.get(i).getLastname()
                        , readers.get(i).getPhone()
                );
                setNumbersReaders.add(i+1);
            }
        }
        if(setNumbersReaders.isEmpty()){
            System.out.println("Список читателей пуст");
        }
        return setNumbersReaders;
    }

    private void returnBook() {
        System.out.println("Возвращение книги");
        if(quit()) return;
        Set<Integer> numbersGivenBooks = printGivenBooks();
        if (numbersGivenBooks.isEmpty()){
            return;
        }

        int historyNumber = insertNumber(numbersGivenBooks);

        Calendar c = new GregorianCalendar();
        History history = historyFacade.find((long)historyNumber);
        history.setReturnDate(c.getTime());
        Book book = history.getBook();
        book.setCount(book.getCount()+1);
        bookFacade.edit(book);
        historyFacade.edit(history);
    }
    
    private int getNumber(){
        do{
            try{
                String strNumber = scanner.nextLine();
                return Integer.parseInt(strNumber);
            }catch(Exception e){
                System.out.println("Попробуйте еще раз");
            }
        }while(true);
    }
    
    private int insertNumber(Set<Integer> setNumbers){
        do{
            int historyNumber = getNumber();
            if (setNumbers.contains(historyNumber)){
                return historyNumber; 
            }
            System.out.println("Попробуй еще раз");
        }while(true);  
    }
    
    private void addAuthor(){
        Author author = new Author();
        System.out.print("Имя автора ");
        author.setName(scanner.nextLine());
        System.out.print("Фамилия автора: ");
        author.setLastname(scanner.nextLine());
        System.out.print("День рождения: ");
        author.setDay(getNumber());
        System.out.print("Месяц рождения: ");
        author.setMonth(getNumber());
        System.out.print("Год рождения: ");
        author.setYear(getNumber());
        authorFacade.create(author);
    }

    private Set<Integer> printListAuthors() {
        Set<Integer> setNumbersAuthors = new HashSet();
        System.out.println("Список авторов");
        List<Author> authors = authorFacade.findAll();
        for (int i = 0; i < authors.size(); i++) {
            if (authors.get(i)!=null){
                System.out.printf("%d. %s %s%n"
                        , authors.get(i).getId()
                        , authors.get(i).getName()
                        , authors.get(i).getLastname()
                );
                setNumbersAuthors.add((int)authors.get(i).getId().intValue());
            }
        }
        return setNumbersAuthors;
    }
    
    private boolean quit(){
        System.out.println("Чтобы закончить операцию нажмите \"q\", для продолжения любой другой символ");
        String quit = scanner.nextLine();
        if("q".equals(quit)) return true;
      return false;
    }
    
    private void updateBook(){
        /**
         * алгоритм изменения книги:
         * 1. вывести список книг и выбрать нужную
         * 2. вывести редиктируемое поле
         * 3. спросить нужно ли его редактировать
         * 4. попросить ввести новое содержимое поля
         * 5. инициировать новое содержимое поля
         * 6. повторить для каждого поля
         */
        System.out.println("Изменения книги");
        Set <Integer> setNumbersBooks = booksList();
        if(setNumbersBooks.isEmpty()){//если книг нет то закроется
            return;
        }
        System.out.print("Введите номер книги из списка: ");
        int bookNumber = insertNumber(setNumbersBooks);
        Book book = bookFacade.find((long)bookNumber);
        
        System.out.println("Редактировать поле название:" + book.getCaption());
        System.out.print("y/n: ");
        String answer = scanner.nextLine();
        if("y".equals(answer)){
            System.out.print("Введите новое название: ");
            book.setCaption(scanner.nextLine());
        }
        System.out.println("Редактировать поле год:" + book.getPublishedYear());
        System.out.print("y/n: ");
        answer = scanner.nextLine();
        if("y".equals(answer)){
            System.out.print("Введите новый год: ");
            book.setPublishedYear(getNumber());
        }
        System.out.println("Редактировать поле количество:" + book.getQuantity());
        System.out.print("y/n: ");
        answer = scanner.nextLine();
        if("y".equals(answer)){
            System.out.print("Введите новое количество: ");
            int oldQuantity = book.getQuantity();
            int oldCount = book.getCount();
            int newQuantity;
            do{
                newQuantity = getNumber();
                if(newQuantity >= 0 &&  newQuantity>=oldCount){
                    break;
                }
                System.out.println("Попробуй еще");
            }while(true);
            book.setQuantity(newQuantity);
            int newCount = oldCount + (newQuantity - oldQuantity);
            book.setCount(newCount);
        }
        
        System.out.println("Авторы книги:");
        for (int i =0; i<book.getAuthor().size();i++){
            System.out.printf("%d. %s %s %d%n", i+1,book.getAuthor().get(i).getName(),
                    book.getAuthor().get(i).getLastname(),
//                    books.get(bookNumber-1).getAuthor().get(i).getDay(),
//                    books.get(bookNumber-1).getAuthor().get(i).getMonth(),
                    book.getAuthor().get(i).getYear()
            );
        }
        System.out.println("Редактировать количество авторов:");
        System.out.print("y/n: ");
        answer = scanner.nextLine();
        if("y".equals(answer)){
            book.getAuthor().clear();
            Set<Integer> setNumbersAuthors = printListAuthors();
            if(setNumbersAuthors.isEmpty()){
                return;
            }
            System.out.print("Введите новое количество: ");
            int countAuthors;
            do{
                countAuthors = getNumber();
                if(countAuthors>0){
                    break;
                }
                System.out.println("Попробуй еще");
            }while(true);
            List <Author> bookAuthors = new ArrayList<>();
            for (int i=0; i<countAuthors; i++){
                System.out.println("Выберите "+ (i+1) +"-го автора: ");
                int numAuthor = insertNumber(setNumbersBooks);
                bookAuthors.add(authorFacade.find((long)numAuthor));
            }
            bookFacade.edit(book);
        }
    }
}
