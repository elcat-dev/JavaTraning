package com.ertc.taskmangwt.gwt.client.funcs;

import com.ertc.taskmangwt.common.JwtAuthRequestDto;
import com.ertc.taskmangwt.common.JwtAuthResponseDto;
import com.ertc.taskmangwt.common.TaskDto;
import com.ertc.taskmangwt.gwt.client.clients.AuthClient;
import com.ertc.taskmangwt.gwt.client.clients.TasksClient;
import com.google.gwt.core.client.GWT;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.user.client.Window;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

public class Operations {
    private static Operations operations;
    private TasksClient tasksClient;
    private Loader loader;
    private AuthClient authClient;

    private Operations(){
        tasksClient = GWT.create(TasksClient.class);
        loader = Loader.getLoader();
        authClient = GWT.create(AuthClient.class);
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
        tasksClient.createTask(Storage.getLocalStorageIfSupported().getItem("jwt")
                ,taskDto, new MethodCallback<Long>() {
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
        tasksClient.delete(Storage.getLocalStorageIfSupported().getItem("jwt")
                , taskId, new MethodCallback<Void>() {
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

    public void clickLogin(JwtAuthRequestDto jwtAuthRequestDto){
        authClient.authenticate(jwtAuthRequestDto, new MethodCallback<JwtAuthResponseDto>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                GWT.log(method.getResponse().getText());
                Window.alert("Авторизация не пройдена: " + method.getResponse().getText());
            }

            @Override
            public void onSuccess(Method method, JwtAuthResponseDto jwtAuthResponseDto) {
                GWT.log(jwtAuthResponseDto.getToken());
                Storage.getLocalStorageIfSupported().setItem("jwt", "Bearer " +  jwtAuthResponseDto.getToken());
                loader.refresh();
                loader.loadFiltr();
                GuideShow.getGuideShow().switchTab();
            }
        });
    }

}
