package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        // При использовании Hibernate таблица создаётся автоматически
        // через настройку hibernate.hbm2ddl.auto = "update"
        // Этот метод можно оставить пустым или удалить
        System.out.println("Таблица создаётся автоматически через Hibernate (hbm2ddl.auto=update)");
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // HQL для удаления всех записей (очистка таблицы)
            // ВНИМАНИЕ: Hibernate не поддерживает DROP TABLE через HQL
            // Поэтому здесь используем очистку, а не удаление таблицы
            session.createQuery("DELETE FROM User").executeUpdate();

            transaction.commit();
            System.out.println("Таблица users очищена (Hibernate не поддерживает DROP TABLE через HQL)");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // Работа с сущностью User, а не с полями БД
            User user = new User(name, lastName, age);
            session.persist(user); // JPA-метод, работает с сущностью

            transaction.commit();
            System.out.println("User с именем " + name + " добавлен в базу данных");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // Работа с сущностью User по ID
            User user = session.get(User.class, id);
            if (user != null) {
                session.remove(user); // JPA-метод удаления сущности
                System.out.println("User с id " + id + " удален");
            } else {
                System.out.println("User с id " + id + " не найден");
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSessionFactory().openSession()) {
            // HQL-запрос к сущности User, а не к таблице users
            Query<User> query = session.createQuery("SELECT u FROM User u", User.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // HQL для удаления всех сущностей User
            session.createQuery("DELETE FROM User").executeUpdate();

            transaction.commit();
            System.out.println("Таблица users очищена");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}