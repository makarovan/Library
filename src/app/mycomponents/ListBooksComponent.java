/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.mycomponents;

import entity.Author;
import entity.Book;
import facade.AuthorFacade;
import facade.BookFacade;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import java.util.Locale;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;

/**
 *
 * @author pupil
 */
public class ListBooksComponent extends JPanel{
    private JLabel caption;
    private JList<Book> list;

    
    
    
    public ListBooksComponent(String text, int widthWindow, int heightPanel, int listWidth) {
        initComponents(false, text, widthWindow, heightPanel, listWidth);
    }

    public ListBooksComponent(boolean guest, String text, int widthWindow, int heightPanel, int listWidth) {
        this.initComponents(guest, text, widthWindow, heightPanel, listWidth);
    }
    
    private void initComponents(boolean guest, String text, int widthWindow, int heightPanel, int listWidth) {
        if(guest){//error
            this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            this.setPreferredSize(new Dimension(widthWindow, heightPanel));//width equals widthwindow width
            this.setMinimumSize(this.getPreferredSize());
            this.setMaximumSize(this.getPreferredSize());
            
            caption = new JLabel(text);
            caption.setPreferredSize(new Dimension(widthWindow/3, heightPanel));
            caption.setMaximumSize(caption.getPreferredSize());
            caption.setMinimumSize(caption.getPreferredSize());
            caption.setHorizontalAlignment(JLabel.CENTER);

            caption.setVerticalAlignment(JLabel.TOP);

            caption.setFont(new Font("Tahoma", 0,12)); // 0 - not bold
            this.add(caption);
        }else{
//            this.setPreferredSize(new Dimension(widthWindow, heightPanel));//width equals widthwindow width
//            this.setMinimumSize(this.getPreferredSize());
//            this.setMaximumSize(this.getPreferredSize());
            this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

            caption = new JLabel(text);
            caption.setPreferredSize(new Dimension(widthWindow/3, heightPanel));
            caption.setMaximumSize(caption.getPreferredSize());
            caption.setMinimumSize(caption.getPreferredSize());
            caption.setHorizontalAlignment(JLabel.RIGHT);

            caption.setVerticalAlignment(JLabel.TOP);


            caption.setFont(new Font("Tahoma", 0,12)); // 0 - not bold
            this.add(caption);
            
            list = new JList<>();
            list.setModel(getListModel());
            list.setCellRenderer(createListBooksRenderer());
            list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            list.setLayoutOrientation(JList.HEIGHT);
            list.setVisibleRowCount(4);
            list.setPreferredSize(new Dimension(listWidth, heightPanel-250));
            list.setMaximumSize(list.getPreferredSize());
            list.setMinimumSize(list.getPreferredSize());

            JScrollPane listScroller = new JScrollPane(list);
            //listScroller.setViewportView(list);
            listScroller.setPreferredSize(new Dimension(listWidth,120));
            listScroller.setMaximumSize(listScroller.getPreferredSize());
            listScroller.setMinimumSize(listScroller.getPreferredSize());
            listScroller.setAlignmentX(LEFT_ALIGNMENT);
            listScroller.setAlignmentY(TOP_ALIGNMENT);
            this.add(listScroller);
        }
            this.add(Box.createRigidArea(new Dimension(5,0))); //space between components in box

            list = new JList<>();
            list.setModel(getListModel());
            list.setCellRenderer(createListBooksRenderer());
            list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            list.setLayoutOrientation(JList.HEIGHT);
            list.setVisibleRowCount(4);
            list.setPreferredSize(new Dimension(listWidth, heightPanel));
            list.setMaximumSize(list.getPreferredSize());
            list.setMinimumSize(list.getPreferredSize());

            JScrollPane listScroller = new JScrollPane(list);
            //listScroller.setViewportView(list);
            listScroller.setPreferredSize(new Dimension(listWidth,120));
            listScroller.setMaximumSize(listScroller.getPreferredSize());
            listScroller.setMinimumSize(listScroller.getPreferredSize());
            listScroller.setAlignmentX(LEFT_ALIGNMENT);
            listScroller.setAlignmentY(TOP_ALIGNMENT);
            this.add(listScroller);
        
    }

    public ListModel<Book> getListModel() {
        return getListModel(false);
    }
    
    public ListModel<Book> getListModel(boolean allBooks) {
        BookFacade bookFacade = new BookFacade(Book.class); //инициировали фасад
        List<Book> books=null;
        if(allBooks){
            books = bookFacade.findAll();
        }else{
            books = bookFacade.findEnabledBook();
        }
        
        DefaultListModel<Book> defaultListModel = new DefaultListModel<>();
        for (Book book : books){
            defaultListModel.addElement(book); 
        }
        return defaultListModel;
    }

    private ListCellRenderer<? super Book> createListBooksRenderer() {
        return new DefaultListCellRenderer(){
            private final Color background = new Color(0,100,255,15);
            private final Color defaultbackground = (Color) UIManager.getColor("List.background");
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                                boolean isSelected, boolean cellHasFocus){
                Component component = super.getListCellRendererComponent(list, value, TOP, isSelected, cellHasFocus);
                if(component instanceof JLabel){
                    JLabel label = (JLabel) component;
                    Book book = (Book) value;
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < book.getAuthor().size(); i++) {
                        Author author = book.getAuthor().get(i);
                        sb.append(author.getName()).append(" ").append(author.getLastname()).append(". ");
                    }
                    if(book.getCount() > 0){
                       label.setText(String.format("%d. %s. %s %d. В наличии %d", 
                                                book.getId(),
                                                book.getCaption(),
                                                sb.toString(),
                                                book.getPublishedYear(),
                                                book.getCount()
                                )); 
                    }else{
                        label.setText(String.format("%d. %s. %s %d. Нет в наличии до ", 
                                                book.getId(),
                                                book.getCaption(),
                                                sb.toString(),
                                                book.getPublishedYear(),
                                                book.getCount()
                                )); 
                        label.setForeground(Color.RED);
                        
                    }
                    
                    if(!isSelected){
                        label.setBackground(index % 2 == 0? background : defaultbackground);
                    }
                }
                return component;
            }
        };
    }
    public JList<Book> getJList() {
        return list;
    }
}
    
