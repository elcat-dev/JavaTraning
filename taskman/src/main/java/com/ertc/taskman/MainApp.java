package com.ertc.taskman;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {

        TaskRepository repository = new TaskRepository();
        TaskService service = new TaskService(repository);

        Task task001 = new Task(1L,"t001", "Vovka", "Oleg", "Work");
        Task task002 = new Task(2L,"t002", "Vovka", "Ivan", "Work");
        Task task003 = new Task(3L,"t003", "Vovka", "Oleg", "Work");
        Task task004 = new Task(4L,"t004", "Vovka", "Ivan", "Work");
        Task task0041 = new Task(5L,"t004", "Vovka", "Ivan", "Work");
        Task task005 = new Task(6L,"t005", "Vovka", "Ivan", "Work");
        Task task006 = new Task(7L,"t006", "Vovka", "Oleg", "Work");
        Task task007 = new Task(8L,"t007", "Vovka", "Ivan", "Work");
        Task task008 = new Task(9L,"t008", "Vovka", "Ivan", "Work");
        Task task009 = new Task(10L,"t009", "Vovka", "Ivan", "Work");
        Task task010 = new Task(11L,"t010", "Vovka", "Oleg", "Work");
        Task task011 = new Task(12L,"t011", "Vovka", "Ivan", "Work");
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
        service.delTask(1L);
        System.out.println("del task false");
        service.delTask(303L);
//
        service.printTaskRep();
//
//        System.out.println("upd task false");
//        NoResultException ??
//        service.updTask(222L, "not", "not", "not", Task.Status.CLOSE);
        System.out.println("upd task true");
        service.updTask(2L, "upd t010", "Vovka", "sleep", Task.Status.CLOSE);
        service.updTask(3L, "upd t010", "Vovka", "sleep", Task.Status.CLOSE);
        service.updTask(4L, "upd t010", "Vovka", "sleep", Task.Status.REJECTED);
        service.updTask(5L, "upd t010", "Vovka", "sleep", Task.Status.REJECTED);
        service.updTask(6L, "upd t010", "Vovka", "sleep", Task.Status.REJECTED);
//
        service.printTaskRep();
        System.out.println("get task by status");
        service.getTaskByStatus(Task.Status.CLOSE);
//
        System.out.println("exists task by id = 4 : " + service.isTaskExistsById(4L));
        System.out.println("exists task by id = 32 : " + service.isTaskExistsById(32L));
//
//        System.out.println("Export tasks");
//        Long[] arrId = new Long[]{};
//        Long[] arrId = new Long[]{1L};
//        service.exportTasks(arrId);

//        System.out.println("Import tasks");
//        service.importTasks(arrId);

//        service.printTaskRep();

        GetFactory.getInstance().closeFactory();
    }
}
