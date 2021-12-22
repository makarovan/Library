/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.UserRoles;
import javax.persistence.EntityManager;
import tools.Singleton;

/**
 *
 * @author pupil
 */
public class UserRolesFacade extends AbstractFacade<UserRoles>{
    private EntityManager em;
    
    public UserRolesFacade(Class<UserRoles> entityClass) {//constructor
        super(UserRoles.class);
        Singleton singleton = Singleton.getInstance();
        em = singleton.getEntityManager();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
