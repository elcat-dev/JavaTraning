package com.ertc.taskmangwt.gwt.client.clients;

import com.ertc.taskmangwt.common.UserDto;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@Path("/api/v1/users")
public interface UserClient extends RestService {
    @GET
    @Path("/{roleId}")
    void getUsers(@PathParam("roleId") Long roleId, MethodCallback<List<UserDto>> user);
}
