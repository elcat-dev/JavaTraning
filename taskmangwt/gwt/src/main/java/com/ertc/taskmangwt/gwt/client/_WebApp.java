package com.ertc.taskmangwt.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.Defaults;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.ArrayList;
import java.util.List;

public class _WebApp implements EntryPoint {
//    CellTable<ItemDto> itemsTable;
//    VerticalPanel verticalPanel;
//    Label label;
//
    public void onModuleLoad() {
//        Defaults.setServiceRoot("http://localhost:8189/gwt-rest");
//        verticalPanel = new VerticalPanel();
//        label = new Label();
//        itemsTable = createItemDtoTable();
//        updateItemsList();
//        RootPanel.get().add(createAddItemForm());
//        verticalPanel.add(label);
//        verticalPanel.add(itemsTable);
//        verticalPanel.setWidth("800");
//        RootPanel.get().add(verticalPanel);
    }
//
//    public void updateItemsList() {
//        ItemsClient client = GWT.create(ItemsClient.class);
//        List<ItemDto> items = new ArrayList<>();
//
//        client.getAllItems(new MethodCallback<List<ItemDto>>() {
//            @Override
//            public void onFailure(Method method, Throwable throwable) {
//                GWT.log(throwable.toString());
//                GWT.log(throwable.getMessage());
//            }
//
//            @Override
//            public void onSuccess(Method method, List<ItemDto> i) {
//                items.addAll(i);
//                GWT.log("Items received: " + items);
//                itemsTable.setRowData(0, items);
//            }
//        });
//    }
//
//    public CellTable<ItemDto> createItemDtoTable() {
//        CellTable<ItemDto> table = new CellTable<>();
//        table.setKeyboardSelectionPolicy(HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.ENABLED);
//
//        TextColumn<ItemDto> idColumn = new TextColumn<ItemDto>() {
//            @Override
//            public String getValue(ItemDto itemDto) {
//                return itemDto.getId().toString();
//            }
//        };
//        table.addColumn(idColumn, "ID");
//
//        TextColumn<ItemDto> titleColumn = new TextColumn<ItemDto>() {
//            @Override
//            public String getValue(ItemDto itemDto) {
//                return itemDto.getTitle();
//            }
//        };
//        table.addColumn(titleColumn, "Title");
//
//        return table;
//    }
//
//    public DecoratorPanel createAddItemForm() {
//        final FormPanel form = new FormPanel();
//        form.setAction(Defaults.getServiceRoot().concat("items"));
//        form.setMethod(FormPanel.METHOD_POST);
//
//
//        VerticalPanel panel = new VerticalPanel();
//        panel.setSpacing(10);
//        form.setWidget(panel);
//
//        Label idLabel = new Label("ID:");
//        panel.add(idLabel);
//
//        final TextBox idTextBox = new TextBox();
//        idTextBox.setWidth("220");
//        idTextBox.setName("id");
//        panel.add(idTextBox);
//
//        Label titleLabel = new Label("Title:");
//        panel.add(titleLabel);
//
//        final TextBox titleTextBox = new TextBox();
//        titleTextBox.setWidth("220");
//        titleTextBox.setName("title");
//        panel.add(titleTextBox);
//
//        ListBox listBox = new ListBox();
//        listBox.setName("category");
//        listBox.addItem("FOOD", "1");
//        listBox.addItem("DEVICE", "2");
//        listBox.addItem("ANOTHER", "3");
//        listBox.setWidth("220");
//        panel.add(listBox);
//
//        panel.add(new Button("Создать", new ClickHandler() {
//            @Override
//            public void onClick(ClickEvent event) {
//                form.submit();
//            }
//        }));
//
//        form.addSubmitHandler(new FormPanel.SubmitHandler() {
//            @Override
//            public void onSubmit(FormPanel.SubmitEvent event) {
//                if (idTextBox.getText().length() == 0) {
//                    Window.alert("Необходимо заполнить поле ID");
//                    event.cancel();
//                }
//                if (titleTextBox.getText().length() < 4) {
//                    Window.alert("Название товара должно быть не менее 4 символов");
//                    event.cancel();
//                }
//            }
//        });
//
//        form.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
//            @Override
//            public void onSubmitComplete(FormPanel.SubmitCompleteEvent event) {
//                updateItemsList();
//            }
//        });
//
//        DecoratorPanel decoratorPanel = new DecoratorPanel();
//        decoratorPanel.add(form);
//        return decoratorPanel;
//    }
}