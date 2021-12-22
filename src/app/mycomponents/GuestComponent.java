/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.mycomponents;

import app.GuiApp;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author pupil
 */
public class GuestComponent extends JPanel{
    private ListBooksComponent listBooksComponent;
    private GuestButtonsComponent guestButtonsComponent;

    public GuestComponent() {
        setPreferredSize(new Dimension(GuiApp.width_windows, GuiApp.height_window));
        setMinimumSize(getPreferredSize());
        setMaximumSize(getPreferredSize());
        initComponents();
    }

    private void initComponents() {
        listBooksComponent = new ListBooksComponent(true, "Книги", GuiApp.width_windows, GuiApp.height_window, GuiApp.width_windows);
        this.add(listBooksComponent);
        guestButtonsComponent = new GuestButtonsComponent("Войти", "Зарегистрироваться", GuiApp.width_windows, 50, WIDTH);
    }
    
    
}
