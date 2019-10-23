package com.lesson3.hometask.DAO;

import com.lesson3.hometask.Model.File;
import com.lesson3.hometask.Model.Storage;
import com.lesson3.hometask.validate.Validate;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class DAO<T> {
    private T t;
    private static SessionFactory sessionFactory;

    public T save(T t) {
        Transaction tr = null;

        try (Session session = createSessionFactory().openSession()) {
            //create session/tr
            tr = session.getTransaction();
            tr.begin();

            //action
            session.save(t);

            //close session/tr
            tr.commit();
        } catch (HibernateException e) {
            System.err.println("Save is failed");
            System.out.println(e.getMessage());

            if (tr != null)
                tr.rollback();
        }

        System.out.println("Save is done");

        return t;
    }

    public void update(T t) {
        Transaction tr = null;

        try (Session session = createSessionFactory().openSession()) {
            //create session/tr

            tr = session.getTransaction();
            tr.begin();

            //action
            session.update(t);

            //close session/tr
            tr.commit();
        } catch (HibernateException e) {
            System.err.println("Update is failed");
            System.out.println(e.getMessage());

            if (tr != null)
                tr.rollback();
        }

        System.out.println("Update is done");
    }

    public void delete(Class t, int id) {
        Transaction tr = null;

        try (Session session = createSessionFactory().openSession()) {
            //create session/tr

            tr = session.getTransaction();
            tr.begin();

            //action
            session.delete(findById(t, id));

            //close session/tr
            tr.commit();
        } catch (HibernateException e) {
            System.err.println("Delete is failed");
            System.out.println(e.getMessage());

            if (tr != null)
                tr.rollback();
        }
        System.out.println("Delete is done");

    }

    public T findById(Class<T> t, int id) {
        Transaction tr = null;
        T t1 = null;

        try (Session session = createSessionFactory().openSession()) {
            //create session/tr

            tr = session.getTransaction();
            tr.begin();

            //action

            t1 = session.get(t, id);

            //close session/tr
            tr.commit();
        } catch (HibernateException e) {
            System.err.println("Read is failed");
            System.out.println(e.getMessage());

            if (tr != null)
                tr.rollback();
        }

        System.out.println("Read is done");

        return t1;
    }

    public void put(Storage storage, File file) {
        Transaction tr = null;

        try (Session session = createSessionFactory().openSession()) {
            //create session/tr

            tr = session.getTransaction();
            tr.begin();

            //action
            Query query = session.createQuery("UPDATE File SET storage.id =: storage_id WHERE id =: file_id")
                    .setParameter("storage_id", storage.getId())
                    .setParameter("file_id", file.getId());
            query.executeUpdate();

            //close session/tr
            tr.commit();
        } catch (HibernateException e) {
            System.err.println("Put file " + file.getId() + " is failed");
            System.out.println(e.getMessage());

            if (tr != null)
                tr.rollback();
        }
        System.out.println("Put file is done");
    }

    public void delete(Storage storage, File file) {
        Transaction tr = null;

        try (Session session = createSessionFactory().openSession()) {
            //create session/tr

            tr = session.getTransaction();
            tr.begin();

            //action
            Query query = session.createQuery("UPDATE File SET storage.id =: storage_id WHERE id =: file_id")
                    .setParameter("storage_id", null)
                    .setParameter("file_id", file.getId());
            query.executeUpdate();

            //close session/tr
            tr.commit();
        } catch (HibernateException e) {
            System.err.println("Delete file " + file.getId() + " is failed");
            System.out.println(e.getMessage());

            if (tr != null)
                tr.rollback();
        }
        System.out.println("Delete file is done");
    }

    public void transferAll(Storage storageFrom, Storage storageTo) {
        Transaction tr = null;
        List<Object> files = new ArrayList<>();

        try (Session session = createSessionFactory().openSession()) {
            //create session/tr

            tr = session.getTransaction();
            tr.begin();

            //action
            Query query = session.createQuery("SELECT id, name, format, sizeFile, storage.id FROM File WHERE storage.id =: storage_id")
                    .setParameter("storage_id", storageFrom.getId());
            files = (List<Object>) query.list();

            Validate.checkMaxSizeInStorage(storageTo, files);

            Iterator itr = files.iterator();
            while (itr.hasNext()) {
                Object[] obj = (Object[]) itr.next();
                int fileId = Integer.parseInt(String.valueOf(obj[0]));
                Query query1 = session.createQuery("UPDATE File SET storage.id =: storage_id WHERE id =: file_id")
                        .setParameter("storage_id", storageTo.getId())
                        .setParameter("file_id", fileId);
                query1.executeUpdate();
            }

            //close session/tr
            tr.commit();
        } catch (HibernateException e) {
            System.err.println("Transfer storage is failed");
            System.out.println(e.getMessage());

            if (tr != null)
                tr.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Transfer storage is done");
    }

    public void transferFile(Storage storageFrom, Storage storageTo, int id) {
        Transaction tr = null;
        File file = null;

        try (Session session = createSessionFactory().openSession()) {
            //create session/tr

            tr = session.getTransaction();
            tr.begin();

            //action
            Query query = session.createQuery("UPDATE File SET storage.id =: storageTo_id WHERE id =: file_id " +
                    "AND storage.id =: storageFrom_id")
                    .setParameter("storageTo_id", storageTo.getId())
                    .setParameter("file_id", id)
                    .setParameter("storageFrom_id", storageFrom.getId());
            query.executeUpdate();

            //close session/tr
            tr.commit();
        } catch (HibernateException e) {
            System.err.println("Transfer file " + id + " is failed");
            System.out.println(e.getMessage());

            if (tr != null)
                tr.rollback();
        }
        System.out.println("Transfer file is done");
    }

    public static List<Integer> listFileSize(Storage storage) throws SQLException {
        Transaction tr = null;
        List result = new ArrayList<>();

        try (Session session = createSessionFactory().openSession()) {
            //create session/tr

            tr = session.getTransaction();
            tr.begin();

            Query query = session.createQuery("SELECT sizeFile FROM File WHERE storage.id =: storage_id", Integer.class)
                    .setParameter("storage_id", storage.getId());

            result = query.getResultList();

            //close session/tr
            tr.commit();
        } catch (HibernateException e) {
            System.err.println("Get size from file is failed");
            System.out.println(e.getMessage());

            if (tr != null)
                tr.rollback();
        }
        System.out.println("Get size from file is done");

        return result;
    }

    public static SessionFactory createSessionFactory() {
        //singleton pattern
        if (sessionFactory == null)
            sessionFactory = new Configuration().configure().buildSessionFactory();
        return sessionFactory;
    }
}
