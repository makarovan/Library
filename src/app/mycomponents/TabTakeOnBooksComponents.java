/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.mycomponents;

import app.GuiApp;
import entity.Book;
import entity.History;
import entity.Reader;
import facade.BookFacade;
import facade.HistoryFacade;
import facade.ReaderFacade;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

/**
 *
 * @author pupil
 */
class TabTakeOnBooksComponents extends JPanel{
    private CaptionComponent captionComponent;
    private InfoComponent infoComponent;
    private ComboBoxReadersComponents comboBoxReadersComponents;
    private Reader reader;
    private ListBooksComponent listBooksComponent;
    private ButtonComponent buttonComponent;
    private ComboBoxModel comboBoxModel;
    private HistoryFacade historyFacade = new HistoryFacade(History.class);
    private BookFacade bookFacade = new BookFacade(Book.class); 
    
    public TabTakeOnBooksComponents(int widthPanel) {
        setPreferredSize(new Dimension(GuiApp.width_windows, GuiApp.height_window));
        setMinimumSize(getPreferredSize());
        setMaximumSize(getPreferredSize());
        setComboBoxModel();
        initComponents(widthPanel);
    }

    private void initComponents(int widthPanel) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createRigidArea((new Dimension(0,15))));
        captionComponent = new CaptionComponent("Выбор книги", widthPanel, 25);
        this.add(captionComponent);

        infoComponent = new InfoComponent("", widthPanel, 25);
        this.add(infoComponent);
        this.add(Box.createRigidArea(new Dimension(0,15)));

        comboBoxReadersComponents = new ComboBoxReadersComponents("Читатель", widthPanel, 30, 300);
        comboBoxReadersComponents.getComboBox().setModel(comboBoxModel);
        comboBoxReadersComponents.getComboBox().setSelectedIndex(-1);
        comboBoxReadersComponents.getComboBox().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                reader=(Reader) ie.getItem();

            }
        });
        this.add(comboBoxReadersComponents);
        
        JCheckBox checkBoxAllBooks = new JCheckBox("Показать все книги");
        checkBoxAllBooks.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                if(ie.getStateChange() == ItemEvent.SELECTED){
                    listBooksComponent.getJList().setModel(listBooksComponent.getListModel(true));
                    buttonComponent.getButton().setEnabled(false);
                    listBooksComponent.getJList().setEnabled(false);
                }else{
                    listBooksComponent.getJList().setModel(listBooksComponent.getListModel(false));
                    listBooksComponent.getJList().setEnabled(true);
                }
            }
        });
        this.add(checkBoxAllBooks);
        
        this.listBooksComponent = new ListBooksComponent("Книги", widthPanel, 80, 300);
        this.add(listBooksComponent);

        this.add(Box.createRigidArea((new Dimension(0,15))));
        buttonComponent = new ButtonComponent("Взять книгу", widthPanel, 35, 5, 300);
        this.add(buttonComponent);
        buttonComponent.getButton().addActionListener(clickToButtonGiveBook());
    }

    private ActionListener clickToButtonGiveBook(){
        return new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(comboBoxReadersComponents.getComboBox().getSelectedIndex() == -1){
                   infoComponent.getInfo().setForeground(Color.RED);
                   infoComponent.getInfo().setText("Вы не выбрали читателей");
                }
                List<Book> books = listBooksComponent.getJList().getSelectedValuesList();
                if(books.isEmpty()){
                    infoComponent.getInfo().setText("вы не выбрали книги");
                    return;
                }
                try {
                    for (Book book:books){
                        History history = new History();
                        book.setCount(book.getCount()-1);
                        bookFacade.edit(book);
                        history.setBook(book);
                        history.setReader(reader);
                        Calendar c = new GregorianCalendar();
                        history.setGivenDate(c.getTime());
                        historyFacade.create(history);
                        infoComponent.getInfo().setForeground(Color.black);
                        infoComponent.getInfo().setText("Книги выданы");
                        comboBoxReadersComponents.getComboBox().setSelectedIndex(-1);
                        listBooksComponent.getJList().clearSelection();
                    }
                } catch (Exception e) {
                    infoComponent.getInfo().setForeground(Color.RED);
                    infoComponent.getInfo().setText("Книги выдать не удалось");
                }
            }
        };
    }
    private void setComboBoxModel(){
        ReaderFacade readerFacade = new ReaderFacade(Reader.class);
        List<Reader> readers = readerFacade.findAll();
        DefaultComboBoxModel<Reader> defaultComboBoxModel = new DefaultComboBoxModel<>();
        for (Reader reader : readers) {
            defaultComboBoxModel.addElement(reader);
        }
        comboBoxModel = defaultComboBoxModel;
    }
    public void addComboBoxModel() {
        infoComponent.getInfo().setText("");
        setComboBoxModel();
        //comboBoxReadersComponents.getComboBox().setModel(comboBoxModel);
        comboBoxReadersComponents.getComboBox().setSelectedIndex(-1);
    }
}
    

