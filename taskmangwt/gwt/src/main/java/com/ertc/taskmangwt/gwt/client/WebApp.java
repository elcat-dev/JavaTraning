package com.ertc.taskmangwt.gwt.client;

import com.ertc.taskmangwt.gwt.client.funcs.GuideShow;
import com.ertc.taskmangwt.gwt.client.funcs.Loader;
import com.ertc.taskmangwt.gwt.client.funcs.Operations;
import com.ertc.taskmangwt.gwt.client.widgets.*;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.*;

public class WebApp implements EntryPoint {
    public void onModuleLoad() {
        Defaults.setServiceRoot("http://localhost:8189/barkapp");
        GuideShow guideShow = GuideShow.getGuideShow();
        Loader loader = Loader.getLoader();
        Operations operations = Operations.getOperations();

        TasksTableWidget tasksTableWidget = new TasksTableWidget();
        FilterTaskFormWidget filterTaskFormWidget = new FilterTaskFormWidget();
        TaskCangeFormWidget taskCangeFormWidget = new TaskCangeFormWidget();
        TaskInfoFormWidget taskInfoFormWidget = new TaskInfoFormWidget(taskCangeFormWidget);
        AddTaskFormWidget addTaskFormWidget = new AddTaskFormWidget();
        GetAddTaskFormWidget getAddTaskFormWidget = new GetAddTaskFormWidget(addTaskFormWidget);

        guideShow.setObjects(taskCangeFormWidget, taskInfoFormWidget, addTaskFormWidget);
        loader.setTaskInfoFormWidget(taskInfoFormWidget);
        loader.setTasksTableWidget(tasksTableWidget);
        loader.setFilterTaskFormWidget(filterTaskFormWidget);
        loader.refresh();
        //////
        HorizontalPanel horizontalPanel = new HorizontalPanel();

        VerticalPanel verticalPanel = new VerticalPanel();
        verticalPanel.add(filterTaskFormWidget);
        verticalPanel.add(tasksTableWidget);

        horizontalPanel.add(verticalPanel);

        VerticalPanel taskPanel = new VerticalPanel();
        taskPanel.add(getAddTaskFormWidget);
        taskPanel.add(addTaskFormWidget);
        taskPanel.add(taskInfoFormWidget);
        taskPanel.add(taskCangeFormWidget);

        horizontalPanel.add(taskPanel);

        RootPanel.get().add(horizontalPanel);
    }
}