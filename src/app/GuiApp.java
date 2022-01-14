/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import app.mycomponents.ButtonComponent;
import app.mycomponents.CaptionComponent;
import app.mycomponents.EditorComponent;
import app.mycomponents.GuestButtonsComponent;
import app.mycomponents.GuestComponent;
import app.mycomponents.InfoComponent;
import app.mycomponents.ListComponent;
import app.mycomponents.director.TabAddReaderComponents;
import app.mycomponents.manager.TabManagerComponent;
import app.mycomponents.director.TabDirectorComponent;
import app.mycomponents.reader.TabReaderComponent;
import entity.Author;
import entity.Book;
import entity.Reader;
import entity.Role;
import entity.User;
import entity.UserRoles;
import facade.BookFacade;
import facade.ReaderFacade;
import facade.RoleFacade;
import facade.UserFacade;
import facade.UserRolesFacade;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author pupil
 */
public class GuiApp extends JFrame{//error
    public static final int width_windows = 600;
    public static final int height_window = 450;
    public static User user;
    private static String role;
    private InfoComponent infoTopComponent;
    private GuestComponent guestComponent;
    private TabAddReaderComponents tabAddReaderComponents;
    private TabReaderComponent tabReaderComponent;
    private TabManagerComponent tabManagerComponent;
    private TabDirectorComponent tabDirectorComponent;
    private GuestButtonsComponent guestButtonsComponent;
    private GuiApp guiApp = this;
    private UserFacade userFacade = new UserFacade();
    private ReaderFacade readerFacade = new ReaderFacade();
    private RoleFacade roleFacade = new RoleFacade();
    private UserRolesFacade userRolesFacade = new UserRolesFacade();
    
    public GuiApp() {
        List<User> users = userFacade.findAll();
        if(users.isEmpty()){//creating admin
            User user = new User();
            user.setLogin("admin");
            user.setPassword("12345");
            Reader reader = new Reader();
            reader.setFirstname("name");
            reader.setLastname("lastname");
            reader.setPhone("1234567");
            readerFacade.create(reader);
            user.setReader(reader);
            userFacade.create(user);
            Role role = new Role();
            UserRoles userRoles = new UserRoles();
            role.setRoleName("ADMINISTRATOR");//ROLES SHOULD BE WRITTEN IN CAPS
            userRoles.setRole(role);
            userRoles = new UserRoles();
            userRoles.setUser(user);
            roleFacade.create(role);
            userRolesFacade.create(userRoles);
            role = new Role();
            role.setRoleName("MANAGER");
            userRoles.setRole(role);
            userRoles = new UserRoles();
            userRoles.setUser(user);
            roleFacade.create(role);
            userRolesFacade.create(userRoles);
            role = new Role();
            role.setRoleName("READER");
            userRoles.setRole(role);
            userRoles = new UserRoles();
            userRoles.setUser(user);
            roleFacade.create(role);
            userRolesFacade.create(userRoles);
        }
        initComponents();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    private void initComponents() {
        this.setTitle("JKTV20 LIBRARY");
        this.setPreferredSize(new Dimension(width_windows,height_window));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        
        infoTopComponent = new InfoComponent("", width_windows, 27);
        this.add(infoTopComponent);
        
        guestComponent = new GuestComponent();
        
        guestButtonsComponent = new GuestButtonsComponent("Войти", "Зарегистрироваться", GuiApp.width_windows, 50,100,10, 200);
        this.add(guestButtonsComponent);
       
        //войти
        guestButtonsComponent.getButton1().addActionListener(new ActionListener() {//actionlistener прописать для кнопки входа
            @Override
            public void actionPerformed(ActionEvent ae) {
                int widthWindows = 350;
                JDialog dialogLogin = new JDialog(guiApp, "Введите логин и пароль", Dialog.ModalityType.DOCUMENT_MODAL);
                
                dialogLogin.setPreferredSize(new Dimension(400,250));
                dialogLogin.setMinimumSize(dialogLogin.getPreferredSize());
                dialogLogin.setMaximumSize(dialogLogin.getPreferredSize());
                dialogLogin.getContentPane().setLayout(new BoxLayout(dialogLogin.getContentPane(), BoxLayout.Y_AXIS));
                dialogLogin.setLocationRelativeTo(null);
                
                CaptionComponent captionComponent = new CaptionComponent("Введите логин и пароль", 400, 27);
                InfoComponent infoComponent = new InfoComponent("", 400, 27);
                EditorComponent loginComponent = new EditorComponent("Логин", widthWindows, 27, 80, 200);
                EditorComponent passwordComponent = new EditorComponent("Пароль", widthWindows, 27, 80, 200);
                ButtonComponent enterComponent = new ButtonComponent("Войти", widthWindows, 27, 85, 200);
                
                dialogLogin.getContentPane().add(Box.createRigidArea(new Dimension(0,10)));
                dialogLogin.getContentPane().add(captionComponent);
                dialogLogin.getContentPane().add(infoComponent);
                dialogLogin.getContentPane().add(Box.createRigidArea(new Dimension(0,10)));
                dialogLogin.getContentPane().add(loginComponent);
                dialogLogin.getContentPane().add(passwordComponent);
                dialogLogin.getContentPane().add(Box.createRigidArea(new Dimension(0,10)));
                dialogLogin.getContentPane().add(enterComponent);
                
                enterComponent.getButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        //аутентификация - узнать есть ли такой пользователь
                        User user = userFacade.find(loginComponent.getEditor().getText().trim());
                        if(user == null){
                            infoComponent.getInfo().setText("Пользователь не найден");
                            return;
                        }
                        //авторизация - он ли этот пользователь и кукие имеет права
                        if(!user.getPassword().equals(passwordComponent.getEditor().getText().trim())){
                            infoComponent.getInfo().setText("Пользователь не найден или неверный пароль");
                            return;
                        }
                        
                        GuiApp.user = user;
                        //пользователь тот за кого себя выдает, устнавливаем разрешения
                        String role = userRolesFacade.topRole(user);
                        GuiApp.role = role;
                        infoTopComponent.getInfo().setText("Hello "+user.getReader().getFirstname());
                        guiApp.getContentPane().remove(guestComponent);
                        guiApp.getContentPane().remove(guestButtonsComponent);
                        
                        
                        JTabbedPane jTabbedPane = new JTabbedPane();
                        jTabbedPane.setPreferredSize(new Dimension(width_windows,height_window));
                        jTabbedPane.setMinimumSize(jTabbedPane.getPreferredSize());
                        jTabbedPane.setMaximumSize(jTabbedPane.getPreferredSize());
                        
                        if("READER".equals(GuiApp.role)){
                            tabReaderComponent = new TabReaderComponent(GuiApp.width_windows);
                            jTabbedPane.addTab("Читатель", tabReaderComponent);
                        }else if("MANAGER".equals(GuiApp.role)){
                            tabReaderComponent = new TabReaderComponent(GuiApp.width_windows);
                            jTabbedPane.addTab("Читатель", tabReaderComponent);
                            tabManagerComponent = new TabManagerComponent(GuiApp.width_windows);
                            jTabbedPane.addTab("Библиотекарь", tabManagerComponent);
                        }else if("ADMINISTRATOR".equals(GuiApp.role)){
                            tabReaderComponent = new TabReaderComponent(GuiApp.width_windows);
                            jTabbedPane.addTab("Читатель", tabReaderComponent);
                            tabManagerComponent = new TabManagerComponent(GuiApp.width_windows);
                            jTabbedPane.addTab("Библиотекарь", tabManagerComponent);
                            tabDirectorComponent = new TabDirectorComponent(GuiApp.width_windows);
                            jTabbedPane.addTab("Директор", tabDirectorComponent);
                        }
                        guiApp.getContentPane().add(jTabbedPane);
                        guiApp.repaint();
                        guiApp.revalidate();
                        dialogLogin.setVisible(false);
                        dialogLogin.dispose();
                    }
                });
                dialogLogin.pack();
                dialogLogin.setVisible(true);
                
                
            }
        });
        
        //зарегестрироваться
        guestButtonsComponent.getButton2().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                guiApp.getContentPane().remove(guestComponent);
                tabAddReaderComponents = new TabAddReaderComponents(GuiApp.width_windows);
                guiApp.getContentPane().add(tabAddReaderComponents);
                guiApp.repaint();
                guiApp.revalidate();
            }
        });
        if(GuiApp.user == null){
            this.add(guestComponent);
            
        }
        
//        JTabbedPane jTabbedPane = new JTabbedPane();
//        this.setPreferredSize(new Dimension(width_windows,height_window));
//        this.setMinimumSize(jTabbedPane.getPreferredSize());
//        this.setMaximumSize((jTabbedPane.getPreferredSize()));
//        this.getContentPane().add(jTabbedPane);
//        guiApp.getContentPane().add(jTabbedPane);
//        guiApp.getContentPane().remove(infoTopComponent);
//        
//        if("READER".equals(GuiApp.role)){
//            TabReaderComponent tabReaderComponent = new TabReaderComponent(this.getWidth());
//            jTabbedPane.addTab("Читатель", tabReaderComponent);
//        }
//        
//        else if(){
//        TabManagerComponent tabLibrarianComponent = new TabManagerComponent(this.getWidth());
//        jTabbedPane.addTab("Библиотекарь", tabLibrarianComponent);
//        this.getContentPane().add(jTabbedPane);
//        }
//        
//        TabDirectorComponent tabDirectorComponent = new TabDirectorComponent(this.getWidth());
//        jTabbedPane.addTab("Директор", tabDirectorComponent);
    }
    public static void main(String[] args) {//psvm + tab
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GuiApp().setVisible(true);//show window
            } //project properties - run - browse main class - guiapp
        });
    }

}
