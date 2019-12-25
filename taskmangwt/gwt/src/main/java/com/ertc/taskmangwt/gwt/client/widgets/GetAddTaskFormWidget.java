package com.ertc.taskmangwt.gwt.client.widgets;

import com.ertc.taskmangwt.gwt.client.funcs.GuideShow;
import com.ertc.taskmangwt.gwt.client.funcs.Loader;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class GetAddTaskFormWidget extends Composite {
    private AddTaskFormWidget addTaskFormWidget;
    private Loader loader;

    @UiTemplate("xml/GetAddTaskForm.ui.xml")
    interface GetAddTaskFormBinder extends UiBinder<Widget, GetAddTaskFormWidget> {
    }

    private static GetAddTaskFormBinder uiBinder = GWT.create(GetAddTaskFormBinder.class);

    public GetAddTaskFormWidget(AddTaskFormWidget addTaskFormWidget) {
        this.initWidget(uiBinder.createAndBindUi(this));
        this.addTaskFormWidget = addTaskFormWidget;
        loader = Loader.getLoader();
    }

    @UiHandler("btnGetForm")
    public void click(ClickEvent event) {
        if(addTaskFormWidget.isVisible()){
            addTaskFormWidget.setVisible(false);
        }
        else {
            GuideShow.getGuideShow().itsVisible(addTaskFormWidget);
            addTaskFormWidget.getExecutor().clear();
            addTaskFormWidget.getOwner().clear();
            loader.getExecutors(addTaskFormWidget.getExecutor(), null);
            loader.getOwner(addTaskFormWidget.getOwner(), null);
        }
    }
}
