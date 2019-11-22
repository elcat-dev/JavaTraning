package com.ertc.taskman;

import com.ertc.taskman.exceptions.TaskAlreadyExistsException;

import java.sql.*;
import java.util.*;
import java.util.ArrayList;

public class RepositoryBd extends Repository{
    private static Connection conn;
    private static Statement stmt;

    public RepositoryBd() {
    }

    private static void connect() throws Exception{
        //Class.forName ("oracle.jdbc.driver.OracleDriver");
        conn = DriverManager.getConnection("jdbc:oracle:thin:@desktop-779t2cr:1521:xe", "taskman", "taskman");
        stmt = conn.createStatement();
    }

    public static void disconnect() {
        try {
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Task.Status getStatusForStr(String strStatus){
        for (int i = 0; i < Task.Status.values().length; i++) {
            if(Task.Status.values()[i].getEngTitle().equals(strStatus)){
                return Task.Status.values()[i];
            }
        }
        return null;
    }

    @Override
    public List<Task> getTasks() {
        List<Task> tasks = new ArrayList<>();
        Task task;
        try {
            connect();
            ResultSet rs = stmt.executeQuery("select * from tasks");
            while (rs.next()) {
                task = new Task(
                        rs.getInt(1)
                        ,rs.getString(2)
                        ,rs.getString(3)
                        ,rs.getString(4)
                        ,rs.getString(5)
                );
                task.updTaskStatus(getStatusForStr(rs.getString(6)));
                tasks.add(task);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            disconnect();
        }
        return tasks;
    }

    @Override
    public String getTitle() {
        return "Database";
    }

    private boolean addTaskExc(Task task){
        try {
            connect();
            stmt.executeQuery("insert into tasks values (" + task.toStringInBd() + ")");
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            disconnect();
        }
        return true;
    }

    @Override
    public boolean addTask(Task task) {
        if( !isTaskExists(task) ){
            return addTaskExc(task);
        }
        return false;
    }

    @Override
    public boolean addTask(long id, String name, String owner, String executor, String description) {
        Task task = new Task(id, name, owner, executor, description);
        if( !isTaskExists(task) ){
            return addTaskExc(task);
        }
        return false;
    }

    @Override
    public boolean isTaskExists(Task task) {
        List<Task> tasks = getTasks();
        for (Task listTask: tasks) {
            if(listTask.equals(task)){
                throw new TaskAlreadyExistsException("Task Already Exists");
            }
        }
        return false;
    }

    @Override
    public boolean updTask(Task uTask) {
        try {
            connect();
            stmt.executeQuery("update tasks set " + uTask.toStringUpdBd() + "where id = " + uTask.getId());
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            disconnect();
        }
        return true;
    }
}
