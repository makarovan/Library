/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.mycomponents;

import app.GuiApp;
import java.awt.Dimension;
import javax.swing.BoxLayout;
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
        //this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        listBooksComponent = new ListBooksComponent(true, "Список книг библиотеки", GuiApp.width_windows, GuiApp.height_window-100, 400);//now showing
        //listBooksComponent.getJList().setModel(listBooksComponent.getListModel(true));
        //listBooksComponent.getJList().setCellRenderer(listBooksComponent.createListBooksRenderer());
        this.add(listBooksComponent);

    }

    
    
    
    public ListBooksComponent getListBooksComponent() {
        return listBooksComponent;
    }

}
