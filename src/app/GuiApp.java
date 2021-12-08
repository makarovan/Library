/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import app.mycomponents.ButtonComponent;
import app.mycomponents.CaptionComponent;
import app.mycomponents.EditorComponent;
import app.mycomponents.ListComponent;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JFrame;

/**
 *
 * @author pupil
 */
public class GuiApp extends JFrame{

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
        
        CaptionComponent captionComponent = new CaptionComponent("Добавить новую книгу", this.getWidth(), 40);
        this.add(captionComponent);
        
        EditorComponent booknameComponent = new EditorComponent("Заголовок книги", this.getWidth(), 31, 300);
        this.add(booknameComponent);
        
        EditorComponent publishedYearComponent = new EditorComponent("Год издания", this.getWidth(), 31, 100);
        this.add(publishedYearComponent);
        
        EditorComponent quantityComponent = new EditorComponent("Колличество экземпляров", this.getWidth(), 31, 50);
        this.add(quantityComponent);
        
        ListComponent listComponent = new ListComponent("Список авторов", this.getWidth(), 150, 300);
        this.add(listComponent);
        
        ButtonComponent buttonComponent = new ButtonComponent("Добавить книгу", this.getWidth(), 31, 178, 150);
        this.add(buttonComponent);
        
        
    }
    
    
    
    
    public static void main(String[] args) {//psvm + tab
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GuiApp().setVisible(true);//show window
            } //project properties - run - browse main class - guiapp
        });
    }
}
