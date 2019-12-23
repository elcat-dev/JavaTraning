package com.ertc.taskmangwt.gwt.client.clients;

import com.ertc.taskmangwt.common.TaskDto;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.*;
import java.util.List;

@Path("/api/v1/tasks")
public interface TasksClient extends RestService {

    @GET
    void getAllTasks(@QueryParam("executorId") String executorId
            , @QueryParam("statusId") String statusId
            , MethodCallback<List<TaskDto>> task);

    @GET
    @Path("/{id}")
    void getTask(@PathParam("id") String id, MethodCallback<TaskDto> task);

    @POST
    void createTask(@BeanParam() TaskDto taskDto, MethodCallback<Long> taskId);

    @DELETE
    @Path("/{id}")
    void delete(@PathParam("id") String id, MethodCallback<Void> result);

}
