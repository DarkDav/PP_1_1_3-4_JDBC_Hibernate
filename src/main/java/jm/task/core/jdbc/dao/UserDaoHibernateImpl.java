package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    SessionFactory sessionFactory = Util.getSessionFactory();
    private List<User> userList;

    public UserDaoHibernateImpl() {


    }


    @Override
    public void createUsersTable() {


        Transaction transaction = null;
        try (Session session = sessionFactory.openSession();

        ) {
            transaction = session.beginTransaction();
            session.createNativeQuery("CREATE TABLE IF NOT EXISTS users" +
                    " (id mediumint not null auto_increment, name VARCHAR(50), " +
                    "lastname VARCHAR(50), " +
                    "age tinyint, " +
                    "PRIMARY KEY (id))").executeUpdate();
            transaction.commit();
            System.out.println("������� �������");
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }

        }

    }


    @Override
    public void dropUsersTable() {

        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.createNativeQuery("DROP TABLE IF EXISTS users").executeUpdate();
            transaction.commit();
            System.out.println("������� �������");
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
            System.out.println("User � ������ � " + name + " �������� � ���� ������");
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void removeUserById(long id) {

        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(session.get(User.class, id));
            transaction.commit();
            System.out.println("User ������");
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {


        Transaction transaction = null;

        try (Session session = sessionFactory.openSession();) {
            CriteriaQuery<User> criteriaQuery = session.getCriteriaBuilder().createQuery(User.class);
            criteriaQuery.from(User.class);
            transaction = session.beginTransaction();
            transaction.commit();
            List<User> userList = session.createQuery(criteriaQuery).getResultList();
            return userList;
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {

        Transaction transaction = null;
        try (Session session = sessionFactory.openSession();) {
            transaction = session.beginTransaction();
            session.createNativeQuery("TRUNCATE TABLE users;").executeUpdate();
            transaction.commit();
            System.out.println("������� �������");
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}