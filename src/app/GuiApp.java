/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import app.mycomponents.ButtonComponent;
import app.mycomponents.CaptionComponent;
import app.mycomponents.EditorComponent;
import app.mycomponents.InfoComponent;
import app.mycomponents.ListComponent;
import entity.Author;
import entity.Book;
import facade.BookFacade;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;

/**
 *
 * @author pupil
 */
public class GuiApp extends JFrame{
    private CaptionComponent captionComponent;
    private ButtonComponent buttonComponent;
    private EditorComponent editorComponent;
    private InfoComponent infoComponent;
    private ListComponent listComponent;
    private EditorComponent bookNameComponent;
    private EditorComponent publishedYearComponent;
    private EditorComponent quantityComponent;

    public GuiApp() {
        initComponents();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    private void initComponents() {
        this.setPreferredSize(new Dimension(600,400));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        
        captionComponent = new CaptionComponent("Добавить новую книгу", this.getWidth(), 25);
        this.add(captionComponent);
        
        infoComponent = new InfoComponent("", this.getWidth(),25);
        this.add(infoComponent);
        
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        
        bookNameComponent = new EditorComponent("Заголовок книги", this.getWidth(), 31, 300);
        this.add(bookNameComponent);
        
        listComponent = new ListComponent("Список авторов", this.getWidth(), 100, 300);
        this.add(listComponent);
        
        publishedYearComponent = new EditorComponent("Год издания", this.getWidth(), 31, 100);
        this.add(publishedYearComponent);
        
        quantityComponent = new EditorComponent("Колличество экземпляров", this.getWidth(), 31, 50);
        this.add(quantityComponent);
        
        buttonComponent = new ButtonComponent("Добавить книгу", this.getWidth(), 31, 178, 150);
        this.add(buttonComponent);
        buttonComponent.getButton().addActionListener(clickToButtonAddBook());
    }
    
    private ActionListener clickToButtonAddBook(){
        return new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                /**
                 * создать объект book
                 * инициировать поля книги, используя элементы компонентов
                 * добавить книгу в базу данных
                 * сообщтить пользователю о результате
                 * если true очистить результаты
                 */
                Book book = new Book();
                if(bookNameComponent.getEditor().getText().isEmpty()){
                   infoComponent.getInfo().setText("введите название книги");
                   return; //exit from action
                }
                book.setCaption(bookNameComponent.getEditor().getText());
                
                try{
                    book.setPublishedYear(Integer.parseInt(publishedYearComponent.getEditor().getText()));
                }catch(Exception e){
                    infoComponent.getInfo().setText("Введите год издания цифрами");  
                    return;
                }
                
                try{
                    book.setQuantity(Integer.parseInt(quantityComponent.getEditor().getText()));
                }catch(Exception e){
                    infoComponent.getInfo().setText("Введите колличество цифрами");  
                    return;
                }
                
                List<Author> bookAuthors = listComponent.getJList().getSelectedValuesList();
                if(bookAuthors.isEmpty()){
                    infoComponent.getInfo().setText("Вы не выбрали автора");  
                    return;
                }
                book.setAuthor(bookAuthors);
                
                BookFacade bookFacade = new BookFacade(Book.class);
                try{
                    bookFacade.create(book);
                    infoComponent.getInfo().setText("Кига успешно добавлена" + Arrays.toString(bookAuthors.toArray()));          
                }catch(Exception e){
                    infoComponent.getInfo().setText("книгу добавить не удалось");      
                }
                
            }
        };
    }
    
    
    
    public static void main(String[] args) {//psvm + tab
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GuiApp().setVisible(true);//show window
            } //project properties - run - browse main class - guiapp
        });
    }
}
