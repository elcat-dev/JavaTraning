package com.ertc.taskmangwt.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.Defaults;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

public class _InCodeGUIAndRequestBuilder implements EntryPoint {
//    VerticalPanel verticalPanel;
//    Label label;

    public void onModuleLoad() {
////        Defaults.setServiceRoot(com.google.gwt.core.client.GWT.getHostPageBaseURL());
//        // Defaults.setServiceRoot("http://localhost:8189/market");
//
//        Defaults.setServiceRoot("http://localhost:8189/gwt-rest");
//
//        verticalPanel = new VerticalPanel();
//        label = new Label();
//
//        final FormPanel form = new FormPanel();
//        form.setAction("/myFormHandler");
//        form.setMethod(FormPanel.METHOD_POST);
//
//        VerticalPanel panel = new VerticalPanel();
//        panel.setSpacing(10);
//        form.setWidget(panel);
//
//        final TextBox textBox = new TextBox();
//        textBox.setWidth("220");
//        textBox.setName("textBoxFormElement");
//        panel.add(textBox);
//
//        ListBox listBox = new ListBox();
//        listBox.setName("listBoxFormElement");
//        listBox.addItem("item1", "item1");
//        listBox.addItem("item2", "item2");
//        listBox.addItem("item3", "item3");
//        listBox.setWidth("220");
//        panel.add(listBox);
//
//        panel.add(new Button("Submit", new ClickHandler() {
//            @Override
//            public void onClick(ClickEvent event) {
//                form.submit();
//            }
//        }));
//
//        form.addSubmitHandler(new FormPanel.SubmitHandler() {
//            @Override
//            public void onSubmit(FormPanel.SubmitEvent event) {
//                if (textBox.getText().length() == 0) {
//                    Window.alert("The text box must not be empty");
//                    event.cancel();
//                }
//            }
//        });
//
//        form.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
//            @Override
//            public void onSubmitComplete(FormPanel.SubmitCompleteEvent event) {
//                Window.alert(event.getResults());
//            }
//        });
//
//        DecoratorPanel decoratorPanel = new DecoratorPanel();
//        decoratorPanel.add(form);
//        RootPanel.get().add(decoratorPanel);
//
//        Button btn = new Button("GWT Button");
//        btn.addClickHandler(new ClickHandler() {
//            @Override
//            public void onClick(ClickEvent event) {
//                label.setText("You clicked GWT Button!");
//                GWT.log("AAAAAAAAAA!!!");
//            }
//        });
//
//        RequestBuilder request = new RequestBuilder(RequestBuilder.POST, "http://localhost:8189/market/gwt");
//        try {
//            request.sendRequest(null, new RequestCallback(){
//                @Override
//                public void onResponseReceived(Request request, Response response) {
//                    GWT.log(response.getText());
//
//                    verticalPanel.add(new Label("> " + response.getText()));
//                    verticalPanel.add(new Label("OK"));
//                    // You get the response as a String so more processing required to convert/extract data
//                }
//
//                @Override
//                public void onError(Request request, Throwable exception) {
//                    verticalPanel.add(new Label("ERROR"));
//                }
//            });
//        } catch (RequestException e) {
//            e.printStackTrace();
//        }
//
//
////
////        Resource gwtResource = new Resource("http://localhost:8189/market/gwt");
////        gwtResource.get().send(new TextCallback() {
////            @Override
////            public void onFailure(Method method, Throwable throwable) {
////                verticalPanel.add(new Label("TEXT ERROR"));
////            }
////
////            @Override
////            public void onSuccess(Method method, String s) {
////                verticalPanel.add(new Label("TEXT RECEIVED"));
////            }
////        });
//
//        ItemsClient client = GWT.create(ItemsClient.class);
////        Resource resource = new Resource("localhost:8189/market");
////        ((RestServiceProxy) client).setResource(resource);
//        client.getHello(new MethodCallback<ItemDto>() {
//            @Override
//            public void onFailure(Method method, Throwable throwable) {
//                for (int i = 0; i < method.getResponse().getHeaders().length; i++) {
//                    verticalPanel.add(new Label(method.getResponse().getHeaders()[i].toString()));
//                }
//                verticalPanel.add(new Label(throwable.toString()));
//                verticalPanel.add(new Label(throwable.getCause().toString()));
//            }
//
//            @Override
//            public void onSuccess(Method method, ItemDto i) {
//                verticalPanel.add(new Label("GOT IT"));
//                verticalPanel.add(new Label(i.getId() + " " + i.getTitle()));
//            }
//        });
//
//        verticalPanel.add(label);
//        verticalPanel.add(btn);
//
//        RootPanel.get().add(verticalPanel);
    }
}
