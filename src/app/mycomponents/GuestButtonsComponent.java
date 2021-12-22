/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.mycomponents;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author pupil
 */
public class GuestButtonsComponent extends JPanel{//error commentary
    private JLabel caption;
    private JButton button;
    /**
     * 
     * @param text - надпись на кнопке
     * @param widthWindow - ширина панели, в которой находится кнопка
     * @param heightPanel - высота панели
     * @param left - отступ слева от кнопки
     * @param buttonWidth - ширина кнопки
     */
    
    
    public GuestButtonsComponent(String text1, String text2, int widthWindow, int heightPanel, int left, int between, int buttonWidth) {
        initComponents(text1, text2, widthWindow, heightPanel, left, between, buttonWidth);
    }

    private void initComponents(String text1, String text2, int widthWindow, int heightPanel, int left,int between, int buttonWidth) { //textfield error
        this.setPreferredSize(new Dimension(widthWindow, heightPanel));//width equals widthwindow width
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        
        this.add(Box.createRigidArea(new Dimension(left, 27)));
        
        button = new JButton(text1);
        button.setPreferredSize(new Dimension(buttonWidth, 27));
        button.setMaximumSize(button.getPreferredSize());
        button.setMinimumSize(button.getPreferredSize());
        this.add(button);
        
        button = new JButton(text2);
        button.setPreferredSize(new Dimension(buttonWidth, 27));
        button.setMaximumSize(button.getPreferredSize());
        button.setMinimumSize(button.getPreferredSize());
        this.add(button);
        
        
    }

    public JButton getButton() {
        return button;
    }

    
    
}
