package com.ertc.taskmangwt.gwt.client.clients;

import com.ertc.taskmangwt.common.StatusDto;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import java.util.List;

@Path("/api/v1/status")
public interface StatusClient extends RestService {
    @GET
    void getStatus(@HeaderParam("Authorization") String token
            , MethodCallback<List<StatusDto>> status);
}
