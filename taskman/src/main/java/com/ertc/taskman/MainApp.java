package com.ertc.taskman;

public class MainApp {
    public static void main(String[] args) {
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

        service.printTaskRep();
    }
}
