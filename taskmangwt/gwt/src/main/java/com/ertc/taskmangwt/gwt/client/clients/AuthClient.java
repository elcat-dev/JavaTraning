package com.ertc.taskmangwt.gwt.client.clients;

import com.ertc.taskmangwt.common.JwtAuthRequestDto;
import com.ertc.taskmangwt.common.JwtAuthResponseDto;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.*;

@Path("/authenticate")
public interface AuthClient extends RestService {

    @POST
    void authenticate(@BeanParam() JwtAuthRequestDto authRequest, MethodCallback<JwtAuthResponseDto> result);
}
