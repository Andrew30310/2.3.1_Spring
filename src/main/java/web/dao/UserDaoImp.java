package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.model.User;

import javax.persistence.*;
import java.util.List;

@Component
public class UserDaoImp implements UserDao {

    @Autowired
    EntityManagerFactory emf;

    @Override
    public boolean updateUser(int oldUsersId, User newUser) {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        User user = em.find(User.class, oldUsersId);
        if (user != null) {
            user.setName(newUser.getName());
            user.setLastName(newUser.getLastName());
            user.setAge(newUser.getAge());
            em.getTransaction().commit();
            em.close();
            return true;
        } else {
            em.close();
            return false;
        }
    }

    @Override
    public User getUserById(int id) {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        User user = em.find(User.class, id);
        em.close();
        return user;
    }

    @Override
    public List<User> getUsersList() {

        EntityManager em = emf.createEntityManager();
        List<User> usersList = em.createQuery("FROM User").getResultList();
        em.close();
        return usersList;
    }

    @Override
    public void addUser(User user) {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void deleteUserById(int id) {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        User deletedUser = em.find(User.class, id);
        em.remove(deletedUser);
        em.getTransaction().commit();
        em.close();
    }
}