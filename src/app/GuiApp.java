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
import app.mycomponents.TabAddReaderComponents;
import app.mycomponents.TabLibrarianComponent;
import app.mycomponents.TabDirectorComponent;
import app.mycomponents.TabReaderComponent;
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
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author pupil
 */
public class GuiApp extends JFrame{
    public static final int width_windows = 600;
    public static final int height_window = 450;
    private GuestComponent guestComponent;
    private TabAddReaderComponents tabAddReaderComponents;
    private GuestButtonsComponent guestButtonsComponent;
    private GuiApp guiApp = this;
    
    
    public GuiApp() {
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
        guestComponent = new GuestComponent();
        
        guestButtonsComponent = new GuestButtonsComponent("Войти", "Зарегистрироваться", GuiApp.width_windows, 50,100,10, 200);
        this.add(guestButtonsComponent);
       
        //войти
        guestButtonsComponent.getButton1().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
            }
        });
        
        //зарегестрироваться
        guestButtonsComponent.getButton2().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                guiApp.getContentPane().remove(guestComponent);
                tabAddReaderComponents = new TabAddReaderComponents(GuiApp.width_windows);
                guiApp.getContentPane().add(tabAddReaderComponents);
                //guiApp.repaint();
                guiApp.revalidate();
            }
        });
        this.add(guestComponent);
        
                
        
        
//        JTabbedPane jTabbedPane = new JTabbedPane();
//        this.setPreferredSize(new Dimension(width_windows,height_window));
//        this.setMinimumSize(jTabbedPane.getPreferredSize());
//        this.setMaximumSize((jTabbedPane.getPreferredSize()));
//        this.getContentPane().add(jTabbedPane);
//        
//        TabReaderComponent tabReaderComponent = new TabReaderComponent(this.getWidth());
//        jTabbedPane.addTab("Читатель", tabReaderComponent);
//        
//        TabLibrarianComponent tabLibrarianComponent = new TabLibrarianComponent(this.getWidth());
//        jTabbedPane.addTab("Библиотекарь", tabLibrarianComponent);
//        this.getContentPane().add(jTabbedPane);
//        
//        TabDirectorComponent tabDirectorComponent = new TabDirectorComponent(this.getWidth());
//        jTabbedPane.addTab("Директор", tabDirectorComponent);
    }
    
    public static void main(String[] args) {//psvm + tab
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GuiApp().setVisible(true);//show window
            } //project properties - run - browse main class - guiapp
        });
    }

}
