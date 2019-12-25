package com.ertc.taskmangwt.gwt.client.widgets;

import com.ertc.taskmangwt.common.TaskDto;
import com.ertc.taskmangwt.gwt.client.funcs.Loader;
import com.google.gwt.cell.client.ClickableTextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.cellview.client.*;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class TasksTableWidget extends Composite {
    @UiField
    CellTable<TaskDto> table;

    private Loader loader;

    @UiTemplate("xml/TasksTable.ui.xml")
    interface TasksTableBinder extends UiBinder<Widget, TasksTableWidget> {
    }

    private static TasksTableBinder uiBinder = GWT.create(TasksTableBinder.class);

    public CellTable<TaskDto> getTable() {
        return table;
    }

    public TasksTableWidget() {
        initWidget(uiBinder.createAndBindUi(this));
        this.loader = Loader.getLoader();

        Column<TaskDto, String> idColumn = new Column<TaskDto, String>(new ClickableTextCell()) {
            @Override
            public String getValue(TaskDto taskDto)  {
                return taskDto.getId().toString();
            };
        };
        idColumn.setFieldUpdater((index, taskDto, value) -> loader.getTaskInfo(taskDto));
        table.addColumn(idColumn, "ID");

        Column<TaskDto, String> titleColumn = new Column<TaskDto, String>(new ClickableTextCell()) {
            @Override
            public String getValue(TaskDto taskDto)  {
                return taskDto.getName();
            };
        };
        titleColumn.setFieldUpdater((index, taskDto, value) -> loader.getTaskInfo(taskDto));
        table.addColumn(titleColumn, "Title");

        Column<TaskDto, String> executorColumn = new Column<TaskDto, String>(new ClickableTextCell()) {
            @Override
            public String getValue(TaskDto taskDto)  {
                return taskDto.getExecutor();
            };
        };
        executorColumn.setFieldUpdater((index, taskDto, value) -> loader.getTaskInfo(taskDto));
        table.addColumn(executorColumn, "Executor");

        Column<TaskDto, String> statusColumn = new Column<TaskDto, String>(new ClickableTextCell()) {
            @Override
            public String getValue(TaskDto taskDto)  {
                return taskDto.getStatus();
            };
        };
        statusColumn.setFieldUpdater((index, taskDto, value) -> loader.getTaskInfo(taskDto));
        table.addColumn(statusColumn, "Status");

        table.setColumnWidth(idColumn, 50, Style.Unit.PX);
        table.setColumnWidth(titleColumn, 200, Style.Unit.PX);
        table.setColumnWidth(executorColumn, 200, Style.Unit.PX);
        table.setColumnWidth(statusColumn, 100, Style.Unit.PX);
    }

}
