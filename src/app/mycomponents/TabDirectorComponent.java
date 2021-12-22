/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.mycomponents;

import entity.Author;
import entity.Book;
import entity.Reader;
import facade.BookFacade;
import facade.ReaderFacade;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author pupil
 */
public class TabDirectorComponent extends JPanel{
    private CaptionComponent captionComponent;
    private InfoComponent infoComponent;
    private EditorComponent readerNameComponent;
    private EditorComponent readerLastnameComponent;
    private EditorComponent readerPhoneComponent;
    private ButtonComponent buttonComponent;
    private ComboBoxModel comboBoxModel;
    private ComboBoxReadersComponents comboBoxReadersComponents;

    public TabDirectorComponent(int widthWindow) {
        setComboBoxModel();
        initComponents(widthWindow);
    }

    private void initComponents(int widthPanel) {
        this.setPreferredSize(new Dimension(widthPanel, 450));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        JTabbedPane tabDirector = new JTabbedPane();
        tabDirector.setPreferredSize(new Dimension(widthPanel-20, 450));
        tabDirector.setMaximumSize(this.getPreferredSize());
        tabDirector.setMinimumSize(this.getPreferredSize());
        tabDirector.setAlignmentX(CENTER_ALIGNMENT);
        TabAddReaderComponents tabAddReaderComponents = new TabAddReaderComponents(widthPanel);
        tabDirector.addTab("Регистрация", tabAddReaderComponents);
        
        //comboBoxReadersComponents = new ComboBoxReadersComponents("Читатели", widthPanel, 30, 300);
        TabEditReaderComponents tabEditReaderComponents = new TabEditReaderComponents(widthPanel, comboBoxReadersComponents);
        tabDirector.addTab("Редактировать читателя", tabEditReaderComponents);
        this.add(tabDirector);
        
        tabDirector.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent ce) {
                //setComboBoxModel();
                tabEditReaderComponents.addComboBoxModel();
//                ReaderFacade readerFacade = new ReaderFacade(Reader.class);
//                List<Reader> readers = readerFacade.findAll();
//                DefaultComboBoxModel<Reader> defaultComboBoxModel = new DefaultComboBoxModel<>();
//                for (Reader reader:readers){
//                    defaultComboBoxModel.addElement(reader);
//                }
//                comboBoxModel = defaultComboBoxModel;
//                //comboBoxReadersComponents.getComboBox().setSelectedIndex(-1);
//                comboBoxReadersComponents.getComboBox().setModel(comboBoxModel);
            }
        });        
        
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
}
