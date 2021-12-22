/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.mycomponents;

import entity.Reader;
import facade.ReaderFacade;
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
import org.omg.CosNaming.NameComponent;

/**
 *
 * @author pupil
 */
class TabEditReaderComponents extends JPanel{
    private CaptionComponent captionComponent;
    private InfoComponent infoComponent;
    private ComboBoxModel comboBoxModel;
    private ComboBoxReadersComponents comboBoxReadersComponents;
    private EditorComponent readerNameComponent;
    private EditorComponent readerLastnameComponent;
    private EditorComponent readerPhoneComponent;
    private ButtonComponent buttonComponent;
    private Reader reader;
    
    public TabEditReaderComponents(int widthPanel, ComboBoxReadersComponents comboBoxReadersComponents) {
//        this.comboBoxReadersComponents = comboBoxReadersComponents;
        this.comboBoxModel = comboBoxModel;
        initComponents(widthPanel);
    }

    private void initComponents(int widthPanel) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createRigidArea((new Dimension(0,15))));
        captionComponent = new CaptionComponent("Редактировать данные читателя", widthPanel, 25);
        this.add(captionComponent);
        
        infoComponent = new InfoComponent("", widthPanel, 25);
        this.add(infoComponent);
        this.add(Box.createRigidArea(new Dimension(0,15)));
        //comboBoxReadersComponents.getComboBox().setSelectedIndex(-1);
//        this.add(comboBoxReadersComponents);
//        
//        
//        
//        comboBoxReadersComponents.getComboBox().addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent ie) {
//                reader = (Reader) ie.getItem();//инициировали
//                //вытаскиваем значения полей, чтобы вставить 
//                readerNameComponent.getEditor().setText(reader.getFirstname());   
//                readerLastnameComponent.getEditor().setText(reader.getLastname());
//                readerPhoneComponent.getEditor().setText(reader.getPhone());
//            }
//        });
//        
//        this.add(Box.createRigidArea(new Dimension(0, 10)));
        comboBoxReadersComponents = new ComboBoxReadersComponents("Читатели", widthPanel, 30, 300);
//        comboBoxReadersComponents.getComboBox().setModel(comboBoxModel);
        comboBoxReadersComponents.getComboBox().setSelectedIndex(-1);
        comboBoxReadersComponents.getComboBox().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                reader=(Reader) ie.getItem();
                readerNameComponent.getEditor().setText(reader.getFirstname());
                readerLastnameComponent.getEditor().setText(reader.getLastname());
                readerPhoneComponent.getEditor().setText(reader.getPhone());
            }
        });
        this.add(comboBoxReadersComponents);
        
        readerNameComponent = new EditorComponent("Имя читателя", widthPanel, 31, 300);
        this.add(readerNameComponent);
        
        readerLastnameComponent = new EditorComponent("Фамилия читателя", widthPanel, 31, 300);
        this.add(readerLastnameComponent);
        
        readerPhoneComponent = new EditorComponent("Номер телефона читателя", widthPanel, 31, 195);
        this.add(readerPhoneComponent);
        
        
        this.add(Box.createRigidArea((new Dimension(0,15))));
        buttonComponent = new ButtonComponent("Изменить данные читателя", widthPanel, 35, 5, 300);
        this.add(buttonComponent);
        buttonComponent.getButton().addActionListener(clickToButtonEditReader());
    }
    
    private ActionListener clickToButtonEditReader(){//error
        return new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(readerNameComponent.getEditor().getText().isEmpty()){
                   infoComponent.getInfo().setText("введите имя читателя");
                   return; 
                }
                reader.setFirstname(readerNameComponent.getEditor().getText());
                
                if(readerLastnameComponent.getEditor().getText().isEmpty()){
                   infoComponent.getInfo().setText("введите фамилию читателя");
                   return; 
                }
                reader.setLastname(readerLastnameComponent.getEditor().getText());
                
                if(readerPhoneComponent.getEditor().getText().isEmpty()){
                   infoComponent.getInfo().setText("введите телефон читателя");
                   return;
                }
                reader.setPhone(readerPhoneComponent.getEditor().getText());
                
                ReaderFacade readerFacade = new ReaderFacade(Reader.class);
                try{
                    readerFacade.edit(reader);
                    infoComponent.getInfo().setText("Читатель успешно изменен");     
                    comboBoxReadersComponents.getComboBox().setSelectedIndex(-1);
                    readerNameComponent.getEditor().setText("");
                    readerLastnameComponent.getEditor().setText("");
                    readerPhoneComponent.getEditor().setText("");
                }catch(Exception e){
                    infoComponent.getInfo().setText("читателя изменить не удалось");      
                }
            }
        };
    }
    public void addComboBoxModel() {
        ReaderFacade readerFacade = new ReaderFacade(Reader.class);
        List<Reader> readers = readerFacade.findAll();
        DefaultComboBoxModel<Reader> defaultComboBoxModel = new DefaultComboBoxModel<>();
        for (Reader reader : readers) {
            defaultComboBoxModel.addElement(reader);
        }

        comboBoxReadersComponents.getComboBox().setModel(defaultComboBoxModel);
        comboBoxReadersComponents.getComboBox().setSelectedIndex(-1);
        readerNameComponent.getEditor().setText("");
        readerLastnameComponent.getEditor().setText("");
        readerPhoneComponent.getEditor().setText("");
        infoComponent.getInfo().setText("");
    }
}
