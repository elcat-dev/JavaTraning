package com.ertc.taskmangwt.gwt.client.widgets;

import com.ertc.taskmangwt.common.JwtAuthRequestDto;
import com.ertc.taskmangwt.gwt.client.funcs.Operations;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.*;

public class LoginFormWidget extends Composite {
    @UiField
    FormPanel form;

    @UiField
    TextBox textUsername;

    @UiField
    PasswordTextBox textPassword;

    @UiTemplate("xml/LoginForm.ui.xml")
    interface LoginFormBinder extends UiBinder<Widget, LoginFormWidget> {
    }

    private Operations operations;

    private static LoginFormBinder uiBinder = GWT.create(LoginFormBinder.class);

    public LoginFormWidget() {
        this.initWidget(uiBinder.createAndBindUi(this));
        this.operations = Operations.getOperations();
    }

    @UiHandler("btnSubmit")
    public void submitClick(ClickEvent event) {
        JwtAuthRequestDto jwtAuthRequestDto = new JwtAuthRequestDto(textUsername.getValue(), textPassword.getValue());
        operations.clickLogin(jwtAuthRequestDto);
        form.reset();
    }
}
