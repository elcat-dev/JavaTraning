package com.ertc.taskman;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        /*
        RepService repository = new TaskRepository("Rep 1");
        TaskService service = new TaskService((TaskRepository) repository);

        Task task001 = new Task(26, "t001", "Vovka", "Oleg", "Work");
        Task task002 = new Task(1, "t002", "Vovka", "Ivan", "Work");
        Task task003 = new Task(2, "t003", "Vovka", "Oleg", "Work");
        Task task004 = new Task(3, "t004", "Vovka", "Ivan", "Work");
        Task task0041 = new Task(3, "t004", "Vovka", "Ivan", "Work");
        Task task005 = new Task(4, "t005", "Vovka", "Ivan", "Work");
        Task task006 = new Task(5, "t006", "Vovka", "Oleg", "Work");
        Task task007 = new Task(6, "t007", "Vovka", "Ivan", "Work");
        Task task008 = new Task(7, "t008", "Vovka", "Ivan", "Work");
        Task task009 = new Task(8, "t009", "Vovka", "Ivan", "Work");
        Task task010 = new Task(9, "t010", "Vovka", "Oleg", "Work");
        Task task011 = new Task(10, "t011", "Vovka", "Ivan", "Work");
        service.addTask(task001);
        service.addTask(task002);
        service.addTask(task003);
        service.addTask(task004);
        service.addTask(task0041);
        service.addTask(task005);
        service.addTask(task006);
        service.addTask(task007);
        service.addTask(task008);
        service.addTask(task009);
        service.addTask(task010);
        service.addTask(task011);

        service.printTaskRep();

        System.out.println("del task true");
        service.delTask(7L);
        System.out.println("del task false");
        service.delTask(23L);

        service.printTaskRep();

        System.out.println("upd task false");
        service.updTask(222L, "not", "not", "not", Task.Status.CLOSE);
        System.out.println("upd task true");
        service.updTask(9L, "upd t010", "Vovka", "sleep", Task.Status.CLOSE);
        service.updTask(26, "upd t010", "Vovka", "sleep", Task.Status.CLOSE);
        service.updTask(4, "upd t010", "Vovka", "sleep", Task.Status.REJECTED);
        service.updTask(5, "upd t010", "Vovka", "sleep", Task.Status.REJECTED);
        service.updTask(6, "upd t010", "Vovka", "sleep", Task.Status.REJECTED);

        service.printTaskRep();
        System.out.println("get task by status");
        service.getTaskByStatus(Task.Status.CLOSE);

        System.out.println("get Count task by status");
        service.getCountTaskByStatus(Task.Status.CLOSE);

        System.out.println("exists task by id = 4 : " + service.isTaskExistsById(4L));
        System.out.println("exists task by id = 404 : " + service.isTaskExistsById(404L));

        System.out.println("sorted by status");
        service.getTaskOrderByStatus();

        System.out.println("Export tasks from " + ((TaskRepository) repository).getTitle());
        //Long[] arrId = new Long[]{};
        Long[] arrId = new Long[]{4L, 5L};
        service.exportTasks(arrId);

        System.out.println("del task true");
        service.delTask(4L);
        System.out.println("upd task false");
        service.updTask(5L, "upd v2 t010", "Vovka", "sldhs csdhcbshbce", Task.Status.REJECTED);

        service.printTaskRep();

        System.out.println("Import tasks in " + ((TaskRepository) repository).getTitle());
        service.importTasks(arrId);

        service.printTaskRep();

        RepService repository2 = new TaskRepository("Rep 2");
        TaskService service2 = new TaskService((TaskRepository) repository2);
        System.out.println("Import tasks in " + ((TaskRepository) repository2).getTitle());
        arrId = new Long[]{};
        service2.importTasks(arrId);
        service2.printTaskRep();

         */

        ///
        Scanner scanner = new Scanner(System.in);

        SessionFactory factory = new Configuration()
                .configure("config/oracle.cfg.xml")
                .buildSessionFactory();

        Session session = null;

        try {

            //  insert
            Task task021 = new Task("t021", "Vovka", "Ivan", "Testing");
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.persist(task021);
            session.getTransaction().commit();
            System.out.println("ok");

            /*
            // update
            session = factory.getCurrentSession();
            session.beginTransaction();
            Task task022 = session.get(Task.class, 2001L);
            System.out.println(task022);
            task022.updTask("upd t021", "Vovka", "testinggg", Task.Status.CREATED);
            session.getTransaction().commit();
            System.out.println("ok");
             */
        } finally {
            session.close();
            factory.close();
            if (session != null) {
                session.close();
            }
        }

    }
}
