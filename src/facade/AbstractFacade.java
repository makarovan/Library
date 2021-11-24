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
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author pupil
 */
public abstract class AbstractFacade<T> {
    private Class<T> entityClass;
    
    public AbstractFacade(Class<T> entityClass){
            this.entityClass = entityClass;//transaction
    }
    
    protected abstract EntityManager getEntityManager();
    
    public void create(T entity){
        getEntityManager().getTransaction().begin();
            getEntityManager().persist(entity);
        getEntityManager().getTransaction().commit();
    }
    
    public void edit(T entity){
        getEntityManager().getTransaction().begin();
            getEntityManager().merge(entity);
        getEntityManager().getTransaction().commit();
    }
   
    public T find(Long id){
        return getEntityManager().find(entityClass, id);
    }
    
    public List<T> findAll(){
        try{
            //jpql =! sql, it uses entities, here jpql is used
            return getEntityManager().createQuery("SELECT entity FROM"+entityClass.getName()+"entity").getResultList();
        }catch(Exception e){
            return new ArrayList<>();
        }
    }
}
