package com.ertc.taskman.repositories;

import com.ertc.taskman.entities.Task;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.procedure.ProcedureCall;
import org.hibernate.result.Output;
import org.hibernate.result.ResultSetOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.persistence.ParameterMode;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Repository
@Transactional
public class TaskRepository {

    @Autowired
    private SessionFactory factory;

    @PostConstruct
    public void init(){
    }

    public List<Task> getTasks(Long id, String executor, Task.Status status) {
        Session session = null;
        List<Task> tasks = Collections.emptyList();
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            ProcedureCall call = session.createStoredProcedureCall("task_support.get_tasks", Task.class);
            call.registerParameter("tasks_cur$cr", Task.class, ParameterMode.REF_CURSOR);
            call.registerParameter("task_id$i", Long.class, ParameterMode.IN).enablePassingNulls(true);
            call.registerParameter("t_executor$c", String.class, ParameterMode.IN).enablePassingNulls(true);
            call.registerParameter("t_status$c", String.class, ParameterMode.IN).enablePassingNulls(true);

            call.setParameter("task_id$i", id);
            call.setParameter("t_executor$c", executor);
            if(status == null) {
                call.setParameter("t_status$c", null);
            }
            else {
                call.setParameter("t_status$c", status.name());
            }

            Output call_out = call.getOutputs().getCurrent();
            if (call_out.isResultSet()) {
                tasks = ((ResultSetOutput) call_out)
                        .getResultList();
            }
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
        return tasks;
    }

    public Long addTask(Task task){
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
        return task.getId();
    }

    public boolean updTask(Task task){
        Session session = null;
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.merge(task);
            session.getTransaction().commit();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
        return true;
    }

    public boolean delTaskById(Long id){
        Session session = null;
        int delCount = 0;
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            delCount = session.createQuery("DELETE FROM Task t WHERE t.id = :id").setParameter("id", id).executeUpdate();
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
