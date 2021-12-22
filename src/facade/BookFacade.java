/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Book;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import tools.Singleton;

/**
 *
 * @author pupil
 */
public class BookFacade extends AbstractFacade<Book>{

    private EntityManager em;
    
    public BookFacade(Class<Book> entityClass) {
        super(entityClass);
        Singleton singleton = Singleton.getInstance();
        em = singleton.getEntityManager();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<Book> findEnabledBook() {
        try {
            return em.createQuery("SELECT b FROM Book b WHERE b.count > 0")
                    .getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
        
    }
    
}