package com.codeflu.api;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/sample")
@Produces(MediaType.APPLICATION_JSON)
public class SampleResource {

    @GET
    public Response getSample() {
        return Response.ok("{\"message\": \"Hello Shrey\"}").build();
    }
}
