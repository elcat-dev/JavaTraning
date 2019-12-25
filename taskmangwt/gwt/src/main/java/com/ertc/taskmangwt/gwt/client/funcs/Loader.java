package com.ertc.taskmangwt.gwt.client.funcs;

import com.ertc.taskmangwt.common.*;
import com.ertc.taskmangwt.gwt.client.clients.*;
import com.ertc.taskmangwt.gwt.client.widgets.FilterTaskFormWidget;
import com.ertc.taskmangwt.gwt.client.widgets.TaskInfoFormWidget;
import com.ertc.taskmangwt.gwt.client.widgets.TasksTableWidget;
import com.google.gwt.core.client.GWT;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ListBox;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.List;

public class Loader {
    private static Loader loader;
    private TasksClient tasksClient;
    private UserClient userClient;
    private StatusClient statusClient;
    private TaskInfoFormWidget taskInfoFormWidget;
    private TasksTableWidget tasksTableWidget;
    private FilterTaskFormWidget filterTaskFormWidget;

    public void setTaskInfoFormWidget(TaskInfoFormWidget taskInfoFormWidget) {
        this.taskInfoFormWidget = taskInfoFormWidget;
    }

    public void setTasksTableWidget(TasksTableWidget tasksTableWidget) {
        this.tasksTableWidget = tasksTableWidget;
    }

    public void setFilterTaskFormWidget(FilterTaskFormWidget filterTaskFormWidget) {
        this.filterTaskFormWidget = filterTaskFormWidget;
    }

    private Loader(){
        tasksClient = GWT.create(TasksClient.class);
        userClient = GWT.create(UserClient.class);
        statusClient = GWT.create(StatusClient.class);
    }

    public static Loader getLoader(){
        synchronized (Loader.class) {
            if (loader == null) {
                loader = new Loader();
            }
        }
        return loader;
    }

    public void refresh() {
        tasksClient.getAllTasks(Storage.getLocalStorageIfSupported().getItem("jwt")
                ,filterTaskFormWidget.getExecutorId()
                ,filterTaskFormWidget.getStatusId()
                ,new MethodCallback<List<TaskDto>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                GWT.log(throwable.toString());
                GWT.log(throwable.getMessage());
                Window.alert("Невозможно получить список tasks: Сервер не отвечает");
            }

            @Override
            public void onSuccess(Method method, List<TaskDto> listTaskDto) {
                GWT.log("Received " + listTaskDto.size() + " tasks");
                tasksTableWidget.getTable().setRowData(listTaskDto);
//                table.setRowStyles();
            }
        });
    }

    public void getTaskInfo(TaskDto taskDto){
        tasksClient.getTask(Storage.getLocalStorageIfSupported().getItem("jwt"),
                taskDto.getId().toString(), new MethodCallback<TaskDto>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                GWT.log(throwable.toString());
                GWT.log(throwable.getMessage());
                Window.alert("Невозможно получить task info");
            }
            @Override
            public void onSuccess(Method method, TaskDto taskDto) {
                taskInfoFormWidget.setTaskDto(taskDto);
                GuideShow.getGuideShow().itsVisible(taskInfoFormWidget);
            }
        });
    }

    public void getExecutors(ListBox executor, String selectVal) {
        userClient.getUsers(Storage.getLocalStorageIfSupported().getItem("jwt")
                ,2L, new MethodCallback<List<UserDto>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                GWT.log(throwable.toString());
                GWT.log(throwable.getMessage());
                Window.alert("Невозможно получить список Executor: Сервер не отвечает");
            }

            @Override
            public void onSuccess(Method method, List<UserDto> arrExecutors) {
                GWT.log("Received " + arrExecutors.size() + " executor");
                int indexSelect = 0;
                executor.addItem("", "-1");
                for (int i = 0; i < arrExecutors.size(); i++) {
                    executor.addItem(arrExecutors.get(i).getName(), String.valueOf(arrExecutors.get(i).getId()));
                    if(selectVal != null && selectVal.equals(arrExecutors.get(i).getName())){
                        indexSelect = i + 1;
                    }
                }
                executor.setSelectedIndex(indexSelect);
            }
        });
    }

    public void getOwner(ListBox owner, String selectVal) {
        userClient.getUsers(Storage.getLocalStorageIfSupported().getItem("jwt")
                ,1L, new MethodCallback<List<UserDto>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                GWT.log(throwable.toString());
                GWT.log(throwable.getMessage());
                Window.alert("Невозможно получить список Owner: Сервер не отвечает");
            }

            @Override
            public void onSuccess(Method method, List<UserDto> arrOwner) {
                GWT.log("Received " + arrOwner.size() + " owner");
                int indexSelect = 0;
                owner.addItem("", "-1");
                for (int i = 0; i < arrOwner.size(); i++) {
                    owner.addItem(arrOwner.get(i).getName(), String.valueOf(arrOwner.get(i).getId()));
                    if(selectVal != null && selectVal.equals(arrOwner.get(i).getName())){
                        indexSelect = i + 1;
                    }
                }
                owner.setSelectedIndex(indexSelect);
            }
        });
    }

    public void getStatus(ListBox status, String selectVal) {
        statusClient.getStatus(Storage.getLocalStorageIfSupported().getItem("jwt")
                , new MethodCallback<List<StatusDto>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                GWT.log(throwable.toString());
                GWT.log(throwable.getMessage());
                Window.alert("Невозможно получить список Status: Сервер не отвечает");
            }

            @Override
            public void onSuccess(Method method, List<StatusDto> arrStatus) {
                int indexSelect = 0;
                status.addItem("", "-1");
                for (int i = 0; i < arrStatus.size(); i++) {
                    status.addItem(arrStatus.get(i).getName(), String.valueOf(arrStatus.get(i).getId()));
                    if(selectVal != null && selectVal.equals(arrStatus.get(i).getName())){
                        indexSelect = i + 1;
                    }
                }
                status.setSelectedIndex(indexSelect);
            }
        });
    }

    public void loadFiltr(){
        getExecutors(filterTaskFormWidget.getExecutor(), null);
        getStatus(filterTaskFormWidget.getStatus(), null);
    }
}
