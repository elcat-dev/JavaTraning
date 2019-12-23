package com.ertc.taskmangwt.gwt.client.widgets;

import com.ertc.taskmangwt.common.TaskDto;
import com.ertc.taskmangwt.gwt.client.funcs.GuideShow;
import com.ertc.taskmangwt.gwt.client.funcs.Operations;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class TaskInfoFormWidget extends Composite {
    @UiField
    Label id;
    @UiField
    Label name;
    @UiField
    Label owner;
    @UiField
    Label executor;
    @UiField
    Label description;
    @UiField
    Label status;

    private TaskCangeFormWidget taskCangeFormWidget;
    private TaskDto taskDto;
    private Operations operations;

    @UiTemplate("xml/TaskInfoForm.ui.xml")
    interface TaskInfoFormBinder extends UiBinder<Widget, TaskInfoFormWidget> {
    }

    private static TaskInfoFormBinder uiBinder = GWT.create(TaskInfoFormBinder.class);

    public void setTaskDto(TaskDto taskDto) {
        this.taskDto = taskDto;
        setLabel();
    }

    public TaskInfoFormWidget(TaskCangeFormWidget taskCangeFormWidget) {
        this.initWidget(uiBinder.createAndBindUi(this));
        this.setVisible(false);
        this.taskCangeFormWidget = taskCangeFormWidget;
        this.operations = Operations.getOperations();
    }

    private void setLabel(){
        id.setText(taskDto.getId().toString());
        name.setText(taskDto.getName());
        owner.setText(taskDto.getOwner());
        executor.setText(taskDto.getExecutor());
        description.setText(taskDto.getDescription());
        status.setText(taskDto.getStatus());
    }

    @UiHandler("btnHf")
    public void click(ClickEvent event) {
        this.setVisible(false);
    }

    @UiHandler("btnChn")
    public void changeClick(ClickEvent event) {
        this.setVisible(false);
        taskCangeFormWidget.setTaskDto(taskDto);
        GuideShow.getGuideShow().itsVisible(taskCangeFormWidget);
    }

    @UiHandler("btnRmv")
    public void removeClick(ClickEvent event) {
        operations.removeTask(this.id.getText());
        this.setVisible(false);
    }

}
