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
    private RoleFacade roleFacade;
    private EntityManager em;
    
    public UserRolesFacade() {//constructor
        super(UserRoles.class);
        Singleton singleton = Singleton.getInstance();
        em = singleton.getEntityManager();
        roleFacade = new RoleFacade();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

     public String topRole(User user) {
        try {
            List<String> roles = em.createQuery("SELECT ur.role.roleName FROM UserRoles ur WHERE ur.user = :user")
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
    
     //dont work
    public void setRole(String roleName, User newUser){
        UserRoles userRoles= new UserRoles();
        userRoles.setUser(newUser);
        try {
            this.removeRole(newUser);
            if(roleName.equals("ADMINISTRATOR")){
                userRoles.setRole(roleFacade.find("ADMINISTRATOR"));
                this.create(userRoles);
                userRoles.setRole(roleFacade.find("MANAGER"));
                this.create(userRoles);
                userRoles.setRole(roleFacade.find("READER"));
                this.create(userRoles);
            }else if(roleName.equals("MANAGER")){
                userRoles.setRole(roleFacade.find("MANAGER"));
                this.create(userRoles);
                userRoles.setRole(roleFacade.find("READER"));
                this.create(userRoles);
            }else if(roleName.equals("READER")){
                userRoles.setRole(roleFacade.find("READER"));
                this.create(userRoles);
            }
        } catch (Exception e) {
            new Exception("Не удалось установить роль пользователю "+ newUser.getLogin());
        }
    }
    
    private void removeRole(User user){
        em.createQuery("DELETE FROM UserRoles ur WHERE ur.user =:user")
                .setParameter("user", user)
                .executeUpdate();
    }
    
    public boolean isRole(String roleName, User user){
        List<String> listRolesForUser = em.createQuery("SELECT ur.role.roleName FROM UserRoles ur WHERE ur.user = :user")
                .setParameter("user", user)
                .getResultList();
        if(listRolesForUser.contains(roleName)){
            return true;
        }else{
            return false;
        }
    }
}
