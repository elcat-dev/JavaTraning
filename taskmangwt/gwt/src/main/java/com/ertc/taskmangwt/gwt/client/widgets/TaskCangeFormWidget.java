package com.ertc.taskmangwt.gwt.client.widgets;

import com.ertc.taskmangwt.common.TaskDto;
import com.ertc.taskmangwt.gwt.client.funcs.Loader;
import com.ertc.taskmangwt.gwt.client.funcs.Operations;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;

public class TaskCangeFormWidget extends Composite {
    @UiField
    Label id;
    @UiField
    TextBox name;
    @UiField
    ListBox owner;
    @UiField
    ListBox executor;
    @UiField
    TextArea description;
    @UiField
    ListBox status;

    private TaskDto taskDto;
    private Loader loader;
    private Operations operations;

    @UiTemplate("xml/TaskChangeForm.ui.xml")
    interface TaskCangeFormBinder extends UiBinder<Widget, TaskCangeFormWidget> {
    }

    private static TaskCangeFormBinder uiBinder = GWT.create(TaskCangeFormBinder.class);

    public void setTaskDto(TaskDto taskDto) {
        this.taskDto = taskDto;
        setParam();
    }

    public TaskCangeFormWidget() {
        this.initWidget(uiBinder.createAndBindUi(this));
        taskDto = new TaskDto();
        this.setVisible(false);
        this.loader = Loader.getLoader();
        this.operations = Operations.getOperations();
        description.setVisibleLines(5);
    }

    private void setParam(){
        id.setText(taskDto.getId().toString());
        name.setText(taskDto.getName());
        loader.getOwner(owner, taskDto.getOwner());
        loader.getExecutors(executor, taskDto.getExecutor());
        description.setText(taskDto.getDescription());
        loader.getStatus(status, taskDto.getStatus());
    }

    @UiHandler("btnCnl")
    public void cancelClick(ClickEvent event) {
        this.setVisible(false);
    }

    public boolean checkParam(TaskDto taskDto) {
        int cnt = 0;
        if (name.getText().length() == 0) {
            Window.alert("Необходимо заполнить поле name");
            cnt++;
        }
        else {
            taskDto.setName(name.getText());
        }
        if (description.getText().length() == 0) {
            Window.alert("Необходимо заполнить поле description");
            cnt++;
        }
        else {
            taskDto.setDescription(description.getText());
        }
        if (Long.parseLong(owner.getSelectedValue()) == -1){
            Window.alert("Необходимо заполнить поле owner");
            cnt++;
        }
        else {
            taskDto.setOwner(owner.getSelectedValue());
        }
        if (Long.parseLong(executor.getSelectedValue()) == -1){
            Window.alert("Необходимо заполнить поле executor");
            cnt++;
        }
        else {
            taskDto.setExecutor(executor.getSelectedValue());
        }
        if (Long.parseLong(status.getSelectedValue()) == -1){
            Window.alert("Необходимо заполнить поле executor");
            cnt++;
        }
        else {
            taskDto.setStatus(status.getSelectedValue());
        }
        return cnt == 0;
    }

    @UiHandler("btnSave")
    public void saveClick(ClickEvent event) {
        if(!checkParam(taskDto)){
            return;
        }
        operations.saveTask(taskDto);
        this.setVisible(false);
    }
}
