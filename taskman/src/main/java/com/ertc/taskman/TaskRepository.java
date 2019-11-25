package com.ertc.taskman;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;

@Repository
public class TaskRepository {

    @Autowired
    private SessionFactory factory;

    @PostConstruct
    public void init(){
    }

    public Task getTasksById(Long id) {
        Session session = null;
        Task task;
        try {
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
