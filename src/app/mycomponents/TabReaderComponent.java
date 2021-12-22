/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.mycomponents;

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
public class TabReaderComponent extends JPanel{
    private CaptionComponent captionComponent;
    private InfoComponent infoComponent;
    private ComboBoxModel comboBoxModel;
    private EditorComponent readerNameComponent;
    private EditorComponent readerLastnameComponent;
    private EditorComponent readerPhoneComponent;
    private ComboBoxReadersComponents comboBoxReadersComponents;
    private ListBooksComponent listBooksComponent;
    private ButtonComponent buttonComponent;
    private TabTakeOnBooksComponents tabTakeOnBooksComponents;
    private TabReturnBookComponents tabReturnBookComponents;
    private Reader reader;
    
    public TabReaderComponent(int widthPanel) {
//        this.comboBoxReadersComponents = comboBoxReadersComponents;
//        this.comboBoxModel = comboBoxModel;
        initComponents(widthPanel);
    }

    private void initComponents(int widthPanel) {//error
        this.setPreferredSize(new Dimension(widthPanel, 450));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        
        JTabbedPane tabReader = new JTabbedPane();
        tabReader.setPreferredSize(new Dimension(widthPanel-20, 450));
        tabReader.setMaximumSize(this.getPreferredSize());
        tabReader.setMinimumSize(this.getPreferredSize());
        tabReader.setAlignmentX(CENTER_ALIGNMENT);
        tabTakeOnBooksComponents = new TabTakeOnBooksComponents(widthPanel);
        tabReader.addTab("Выдача книги", tabTakeOnBooksComponents);
        tabReturnBookComponents = new TabReturnBookComponents(widthPanel);
        tabReader.addTab("Возврат книги", tabReturnBookComponents);
        
        this.add(tabReader);
        
        tabReader.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent ce) {
                if(tabReader.indexOfTab("Взять книгу") >0){
                    tabTakeOnBooksComponents.addComboBoxModel();
                }else if(tabReader.indexOfTab("Возврат книги")>0){
                    tabReturnBookComponents.addComboBoxModel();
                }
            }
            
        });
    }
}