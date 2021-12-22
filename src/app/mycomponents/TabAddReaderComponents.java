/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.mycomponents;

import app.GuiApp;
import entity.Reader;
import facade.ReaderFacade;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 *
 * @author pupil
 */
public class TabAddReaderComponents extends JPanel{
    private CaptionComponent captionComponent;
    private InfoComponent infoComponent;
    private EditorComponent readerNameComponent;
    private EditorComponent readerLastnameComponent;
    private EditorComponent loginComponent;
    private EditorComponent passwordComponent;
    private EditorComponent readerPhoneComponent;
    private ButtonComponent buttonComponent;
    
    public TabAddReaderComponents(int widthPanel) {
        setPreferredSize(new Dimension(GuiApp.width_windows, GuiApp.height_window));
        setMinimumSize(getPreferredSize());
        setMaximumSize(getPreferredSize());
        initComponents(widthPanel);
    }

    private void initComponents(int widthPanel) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        captionComponent = new CaptionComponent("Регистрация нового читателя", widthPanel, 31);
        this.add(captionComponent);
        
        infoComponent = new InfoComponent("", widthPanel, 31);
        this.add(infoComponent);
        
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        
        readerNameComponent = new EditorComponent("Имя читателя", widthPanel, 31, 300);
        this.add(readerNameComponent);
        
        readerLastnameComponent = new EditorComponent("Фамилия читателя", widthPanel, 31, 300);
        this.add(readerLastnameComponent);
        
        loginComponent = new EditorComponent("Логин", widthPanel, 31, 200);
        this.add(loginComponent);
        
        passwordComponent = new EditorComponent("Пароль", widthPanel, 31, 200);
        this.add(passwordComponent);
        
        readerPhoneComponent = new EditorComponent("Номер телефона читателя", widthPanel, 31, 240);
        this.add(readerPhoneComponent);
        
        buttonComponent = new ButtonComponent("Добавить читателя", widthPanel, 31, 160, 150);
        this.add(buttonComponent);
        buttonComponent.getButton().addActionListener(clickToButtonAddReader());
    }
    
    private ActionListener clickToButtonAddReader(){
        return new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                Reader reader = new Reader();
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
                    readerFacade.create(reader);
                    infoComponent.getInfo().setText("Читатель успешно добавлен");     
                    readerNameComponent.getEditor().setText("");
                    readerLastnameComponent.getEditor().setText("");
                    readerPhoneComponent.getEditor().setText("");
                }catch(Exception e){
                    infoComponent.getInfo().setText("читателя добавить не удалось");      
                }
                
            }
        };
    }
    
}
