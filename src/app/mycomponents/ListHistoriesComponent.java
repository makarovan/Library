/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.mycomponents;

import entity.Author;
import entity.Book;
import entity.History;
import entity.Reader;
import facade.AuthorFacade;
import facade.BookFacade;
import facade.HistoryFacade;
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
public class ListHistoriesComponent extends JPanel{
    private JLabel caption;
    private JList<History> list;

    
    
    
    public ListHistoriesComponent(String text, int widthWindow, int heightPanel, int listWidth) {
        initComponents(text, widthWindow, heightPanel, listWidth);
    }

    private void initComponents(String text, int widthWindow, int heightPanel, int listWidth) {
        this.setPreferredSize(new Dimension(widthWindow, heightPanel));//width equals widthwindow width
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        
        caption = new JLabel(text);
        caption.setPreferredSize(new Dimension(widthWindow/3, heightPanel));
        caption.setMaximumSize(caption.getPreferredSize());
        caption.setMinimumSize(caption.getPreferredSize());
        caption.setHorizontalAlignment(JLabel.RIGHT);

        caption.setVerticalAlignment(JLabel.TOP);
        

        caption.setFont(new Font("Tahoma", 0,12)); // 0 - not bold
        this.add(caption);
        
        this.add(Box.createRigidArea(new Dimension(5,0))); //space between components in box
        


        list = new JList<>();
        list.setModel(getListModel());
        list.setCellRenderer(createListHistoriesRenderer());
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.HEIGHT);
        //list.setVisibleRowCount(4);
        //list.setPreferredSize(new Dimension(listWidth, heightPanel));
        //list.setMaximumSize(list.getPreferredSize());
        //list.setMinimumSize(list.getPreferredSize());
        
        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setViewportView(list);
        listScroller.setPreferredSize(new Dimension(listWidth,120));
        listScroller.setMaximumSize(listScroller.getPreferredSize());
        listScroller.setMinimumSize(listScroller.getPreferredSize());
        listScroller.setAlignmentX(LEFT_ALIGNMENT);
        listScroller.setAlignmentY(TOP_ALIGNMENT);
        this.add(listScroller);
    }

    /**
     * ?????????? ???????????????????? ???????????? ???? ?????????????? ?????????????????? ?????? ???????????? ????????
     * @return ???????????? DefaultListModel
     */
    public ListModel<History> getListModel(){
       return getListModel(null);
    }
    /**
     * ?????????? ???????????????????? ???????????? ???? ?????????????? ???????? ???? ???????? ????????????
     * @param allBooks true ?????? ??????????, false - ???????????? ?????????????????? ?? ????????????
     * @return ???????????? DefaultListModel
     */
    public ListModel<History> getListModel(Reader reader) {
        if(reader == null){
            return new DefaultListModel<>();
        }
        HistoryFacade historyFacade = new HistoryFacade();
        List<History> histories=null;
        histories = historyFacade.findAll(reader);
        DefaultListModel<History> defaultListModel = new DefaultListModel<>();
        for (History history : histories) {
            defaultListModel.addElement(history);
        }
        return defaultListModel;
    }

    private ListCellRenderer<? super History> createListHistoriesRenderer() {
      return new DefaultListCellRenderer(){
        private final Color background = new Color(0, 100, 255, 15);
        private final Color defaultBackground = (Color) UIManager.get("List.background");
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                        boolean isSelected, boolean cellHasFocus){
          Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
          if(component instanceof JLabel){
              JLabel label = (JLabel) component;
              History history = (History) value;
              StringBuilder sb = new StringBuilder();
              for (int i = 0; i < history.getBook().getAuthor().size(); i++) {
                  Author author = history.getBook().getAuthor().get(i);
                  sb.append(author.getName())
                    .append(" ")
                    .append(author.getLastname())
                    .append(". ");
              }
              label.setText(String.format("%d. %s. %s %d."
                          ,history.getBook().getId()
                          ,history.getBook().getCaption()
                          ,sb.toString()
                          ,history.getBook().getPublishedYear()
                          ,history.getBook().getCount()
              ));
              
              if(!isSelected){
                  label.setBackground(index % 2 == 0 ? background : defaultBackground);
              }
          }
          return component;
        }
      }; 
    }

    public JList<History> getJList() {
        return list;
    }
    
}