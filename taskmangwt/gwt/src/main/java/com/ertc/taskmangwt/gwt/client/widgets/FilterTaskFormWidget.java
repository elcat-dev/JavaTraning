package com.ertc.taskmangwt.gwt.client.widgets;

import com.ertc.taskmangwt.gwt.client.funcs.Loader;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.*;

public class FilterTaskFormWidget extends Composite {
    @UiField
    ListBox executor;

    @UiField
    ListBox status;

    private Loader loader;
    private String executorId;
    private String statusId;

    @UiTemplate("xml/FilterTask.ui.xml")
    interface FilterTaskFormBinder extends UiBinder<Widget, FilterTaskFormWidget> {
    }

    private static FilterTaskFormBinder uiBinder = GWT.create(FilterTaskFormBinder.class);

    public String getExecutorId() {
        return executorId;
    }

    public String getStatusId() {
        return statusId;
    }

    public ListBox getExecutor() {
        return executor;
    }

    public ListBox getStatus() {
        return status;
    }

    public FilterTaskFormWidget() {
        this.initWidget(uiBinder.createAndBindUi(this));
        this.loader = Loader.getLoader();
        executorId = null;
        statusId = null;
    }

    @UiHandler("btnFilter")
    public void click(ClickEvent event) {
        executorId = executor.getSelectedValue();
        if(executorId == "-1"){
            executorId = null;
        }
        statusId = status.getSelectedValue();
        if(statusId == "-1"){
            statusId = null;
        }
        loader.refresh();
    }
}
