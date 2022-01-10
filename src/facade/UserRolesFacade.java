/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Role;
import entity.User;
import entity.UserRoles;
import java.util.List;
import javax.persistence.EntityManager;
import tools.Singleton;

/**
 *
 * @author pupil
 */
public class UserRolesFacade extends AbstractFacade<UserRoles>{
    private EntityManager em;
    
    public UserRolesFacade() {//constructor
        super(UserRoles.class);
        Singleton singleton = Singleton.getInstance();
        em = singleton.getEntityManager();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public String topRole(User user) {//error
        try {
            List<String> roles = em.createQuery("SELECT ur.roleName FROM UserRoles ur WHERE ur.user = :user")
                    .setParameter("user", user)
                    .getResultList();
            if(roles.contains("ADMINISTRATOR")){
                return "ADMINISTRATOR";
            }else if(roles.contains("MANAGER")){
                return "MANAGER";
            }else if(roles.contains("READER")){
                return "READER";
            }else{
                return "";
            }
        } catch (Exception e) {
            return "";
        }
    }
    
}
