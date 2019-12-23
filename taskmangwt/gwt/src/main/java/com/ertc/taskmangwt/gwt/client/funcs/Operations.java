package com.ertc.taskmangwt.gwt.client.funcs;

import com.ertc.taskmangwt.common.TaskDto;
import com.ertc.taskmangwt.gwt.client.clients.TasksClient;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

public class Operations {
    private static Operations operations;
    private TasksClient tasksClient;
    private Loader loader;

    private Operations(){
        tasksClient = GWT.create(TasksClient.class);
        loader = Loader.getLoader();
    }

    public static Operations getOperations(){
        synchronized (Operations.class) {
            if (operations == null) {
                operations = new Operations();
            }
        }
        return operations;
    }

    public void saveTask(TaskDto taskDto){
        tasksClient.createTask(taskDto, new MethodCallback<Long>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                GWT.log(throwable.toString());
                GWT.log(throwable.getMessage());
                Window.alert("Невозможно сохранить задачу");
            }

            @Override
            public void onSuccess(Method method, Long taskId) {
                GWT.log("taskId= " + taskId);
                loader.refresh();
            }
        });
    }

    public void removeTask(String taskId) {
        tasksClient.delete(taskId, new MethodCallback<Void>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                GWT.log(throwable.toString());
                GWT.log(throwable.getMessage());
                GWT.log("Status code: " + method.getResponse().getStatusCode());
                Window.alert("Невозможно удалить задачу");
            }

            @Override
            public void onSuccess(Method method, Void result) {
                GWT.log("Status code: " + method.getResponse().getStatusCode());
                loader.refresh();
            }
        });
    }

}
