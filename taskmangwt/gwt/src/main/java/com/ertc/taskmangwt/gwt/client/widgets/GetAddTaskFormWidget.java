package com.ertc.taskmangwt.gwt.client.widgets;

import com.ertc.taskmangwt.gwt.client.funcs.GuideShow;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class GetAddTaskFormWidget extends Composite {
    private AddTaskFormWidget addTaskFormWidget;

    @UiTemplate("xml/GetAddTaskForm.ui.xml")
    interface GetAddTaskFormBinder extends UiBinder<Widget, GetAddTaskFormWidget> {
    }

    private static GetAddTaskFormBinder uiBinder = GWT.create(GetAddTaskFormBinder.class);

    public GetAddTaskFormWidget(AddTaskFormWidget addTaskFormWidget) {
        this.initWidget(uiBinder.createAndBindUi(this));
        this.addTaskFormWidget = addTaskFormWidget;
    }

    @UiHandler("btnGetForm")
    public void click(ClickEvent event) {
        if(addTaskFormWidget.isVisible()){
            addTaskFormWidget.setVisible(false);
        }
        else {
            GuideShow.getGuideShow().itsVisible(addTaskFormWidget);
        }
    }
}
