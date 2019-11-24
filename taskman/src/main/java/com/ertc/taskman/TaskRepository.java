package com.ertc.taskman;

import com.ertc.taskman.exceptions.NoSuchTaskException;
import com.ertc.taskman.exceptions.RepositorySpaceException;
import com.ertc.taskman.exceptions.TaskAlreadyExistsException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {

    public Task getTasksById(Long id) {
        Session session = null;
        Task task;
        try {
            SessionFactory factory = GetFactory.getInstance().getFactory();
            session = factory.getCurrentSession();
            session.beginTransaction();
            task = (Task) session
                    .createQuery("SELECT t FROM Task t WHERE t.id = :id")
                    .setParameter("id", id)
                    .getSingleResult();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
        return task;
    }

    public boolean isTaskExistsById(Long id) {
        Session session = null;
        Long checkExists;
        try {
            SessionFactory factory = GetFactory.getInstance().getFactory();
            session = factory.getCurrentSession();
            session.beginTransaction();
            checkExists = (Long) session
                    .createQuery("SELECT count(*) FROM Task t WHERE t.id = :id")
                    .setParameter("id", id)
                    .getSingleResult();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
        return checkExists > 0;
    }

    public List<Task> getTaskByStatus(Task.Status status){
        Session session = null;
        List<Task> tasks;
        try {
            SessionFactory factory = GetFactory.getInstance().getFactory();
            session = factory.getCurrentSession();
            session.beginTransaction();
            tasks = session
                    .createQuery("SELECT t FROM Task t WHERE t.status = :t_status")
                    .setParameter("t_status", status)
                    .getResultList();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
        return tasks;
    }

    public List<Task> getTasks() {
        Session session = null;
        List<Task> tasks;
        try {
            SessionFactory factory = GetFactory.getInstance().getFactory();
            session = factory.getCurrentSession();
            session.beginTransaction();
            tasks = session
                    .createQuery("SELECT t FROM Task t", Task.class)
                    .getResultList();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
        return tasks;
    }

    public boolean addTask(Task task){
        if( !isTaskExistsById(task.getId()) ){
            return addTaskExc(task);
        }
        return false;
    }

    private boolean addTaskExc(Task task){
        Session session = null;
        try {
            SessionFactory factory = GetFactory.getInstance().getFactory();
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.persist(task);
            session.getTransaction().commit();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
        return true;
    }

    public boolean updTask(Task task){
        Session session = null;
        Task uTask;
        try {
            SessionFactory factory = GetFactory.getInstance().getFactory();
            session = factory.getCurrentSession();
            session.beginTransaction();
            uTask = (Task) session.merge(task);
            session.getTransaction().commit();
        }
//        catch (NoResultException e){
//            e.fillInStackTrace();
//        }
        finally {
            if (session != null) {
                session.close();
            }
        }
        return uTask != null;
    }

    public boolean delTaskById(Long id){
        Session session = null;
        int delCount = 0;
        try {
            SessionFactory factory = GetFactory.getInstance().getFactory();
            session = factory.getCurrentSession();
            session.beginTransaction();
            delCount = session.createQuery("DELETE FROM Task t WHERE t.id = :id").setParameter("id", id).executeUpdate();
//            session.delete(session.get(Task.class, id));
            session.getTransaction().commit();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
        return delCount > 0;
    }
}
