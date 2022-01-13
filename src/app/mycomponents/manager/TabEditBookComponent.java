/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.mycomponents.manager;

import app.mycomponents.ButtonComponent;
import app.mycomponents.CaptionComponent;
import app.mycomponents.EditorComponent;
import app.mycomponents.InfoComponent;
import app.mycomponents.ListComponent;
import entity.Author;
import entity.Book;
import facade.BookFacade;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 *
 * @author pupil
 */
public class TabEditBookComponent extends JPanel{
    private CaptionComponent captionComponent;
    private ButtonComponent buttonComponent;
    private InfoComponent infoComponent;
    private ListComponent listComponent;
    private EditorComponent bookNameComponent;
    private EditorComponent publishedYearComponent;
    private EditorComponent quantityComponent;
    
    public TabEditBookComponent(int widthWindow) {
        initComponents(widthWindow);
        
    }

    private void initComponents(int widthWindow) {
        this.setPreferredSize(new Dimension(widthWindow, 300));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        captionComponent = new CaptionComponent("Добавить новую книгу", widthWindow, 25);
        this.add(captionComponent);
        
        infoComponent = new InfoComponent("", widthWindow, 25);
        this.add(infoComponent);
        
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        
        bookNameComponent = new EditorComponent("Заголовок книги", widthWindow, 31, 300);
        this.add(bookNameComponent);
        
        listComponent = new ListComponent("Список авторов", widthWindow, 100, 300);
        this.add(listComponent);
        
        publishedYearComponent = new EditorComponent("Год издания", widthWindow, 31, 100);
        this.add(publishedYearComponent);
        
        quantityComponent = new EditorComponent("Колличество экземпляров", widthWindow, 31, 50);
        this.add(quantityComponent);
        
        buttonComponent = new ButtonComponent("Добавить книгу", widthWindow, 31, 178, 150);
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
                    book.setCount(book.getQuantity());
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
                
                BookFacade bookFacade = new BookFacade();
                try{
                    bookFacade.create(book);
                    infoComponent.getInfo().setText("Книга успешно добавлена" + Arrays.toString(bookAuthors.toArray()));     
                    listComponent.getJList().clearSelection();
                    infoComponent.getInfo().setText("");
                    bookNameComponent.getEditor().setText("");
                    quantityComponent.getEditor().setText("");
                    publishedYearComponent.getEditor().setText("");
                }catch(Exception e){
                    infoComponent.getInfo().setText("книгу добавить не удалось");      
                }
                
            }
        };
    }
    
}
