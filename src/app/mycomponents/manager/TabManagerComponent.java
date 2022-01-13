/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.mycomponents.manager;

import app.mycomponents.reader.*;
import app.mycomponents.ButtonComponent;
import app.mycomponents.CaptionComponent;
import app.mycomponents.ComboBoxReadersComponents;
import app.mycomponents.EditorComponent;
import app.mycomponents.InfoComponent;
import app.mycomponents.ListBooksComponent;
//import app.mycomponents.reader.TabTakeOnBooksComponents;
import entity.Reader;
import facade.ReaderFacade;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.omg.CosNaming.NameComponent;

/**
 *
 * @author pupil
 */
public class TabManagerComponent extends JPanel{
    private CaptionComponent captionComponent;
    private InfoComponent infoComponent;
    private ComboBoxModel comboBoxModel;
    private EditorComponent readerNameComponent;
    private EditorComponent readerLastnameComponent;
    private EditorComponent readerPhoneComponent;
    private ComboBoxReadersComponents comboBoxReadersComponents;
    private ListBooksComponent listBooksComponent;
    private ButtonComponent buttonComponent;
    private TabAddBookComponent tabAddBookComponent;
    private TabEditBookComponent tabEditBookComponent;
    private TabAddAuthorComponent tabAddAuthorComponent;
    private TabEditAuthorComponent tabEditAuthorComponent;
    
    public TabManagerComponent(int widthPanel) {
//        this.comboBoxReadersComponents = comboBoxReadersComponents;
//        this.comboBoxModel = comboBoxModel;
        initComponents(widthPanel);
    }

    private void initComponents(int widthPanel) {//error
        this.setPreferredSize(new Dimension(widthPanel, 450));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        
        JTabbedPane tabManagerTabbed = new JTabbedPane();
        tabManagerTabbed.setPreferredSize(new Dimension(widthPanel-20, 450));
        tabManagerTabbed.setMaximumSize(this.getPreferredSize());
        tabManagerTabbed.setMinimumSize(this.getPreferredSize());
        tabManagerTabbed.setAlignmentX(CENTER_ALIGNMENT);
        tabAddBookComponent = new TabAddBookComponent(widthPanel);
        tabManagerTabbed.addTab("Добавить новую книгу", tabAddBookComponent);
        tabEditBookComponent = new TabEditBookComponent(widthPanel);
        tabManagerTabbed.addTab("Редактировать книгу", tabEditBookComponent);
        tabAddAuthorComponent = new TabAddAuthorComponent(widthPanel);
        tabManagerTabbed.addTab("Добавить нового автора", tabAddAuthorComponent);
        tabEditAuthorComponent = new TabEditAuthorComponent(widthPanel);
        tabManagerTabbed.addTab("Редактировать автора", tabEditAuthorComponent);
        
        this.add(tabManagerTabbed);
        
        tabManagerTabbed.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent ce) {
                infoComponent.getInfo().setText("");
            }
            
        });
    }
}