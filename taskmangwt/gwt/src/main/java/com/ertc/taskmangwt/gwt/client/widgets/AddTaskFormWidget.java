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

public class AddTaskFormWidget extends Composite {
    @UiField
    FormPanel form;

    @UiField
    TextBox name;

    @UiField
    ListBox owner;

    @UiField
    ListBox executor;

    @UiField
    TextArea description;

    private TaskDto taskDto;
    private Operations operations;

    @UiTemplate("xml/AddTaskForm.ui.xml")
    interface AddTaskFormBinder extends UiBinder<Widget, AddTaskFormWidget> {
    }

    private static AddTaskFormBinder uiBinder = GWT.create(AddTaskFormBinder.class);

    public AddTaskFormWidget() {
        this.initWidget(uiBinder.createAndBindUi(this));
        this.setVisible(false);
        Loader loader = Loader.getLoader();
        this.operations = Operations.getOperations();
        taskDto = new TaskDto();

        loader.getExecutors(executor, null);
        loader.getOwner(owner, null);

        description.setVisibleLines(5);
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
        return cnt == 0;
    }

    @UiHandler("btnSubmit")
    public void submitClick(ClickEvent event) {
        if(!checkParam(taskDto)){
            return;
        }
        operations.saveTask(taskDto);
        form.reset();
        this.setVisible(false);
    }

}
