package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.RenameMeDTO;
import entities.RenameMe;
import utils.EMF_Creator;
import facades.FacadeExample;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//Todo Remove or change relevant parts before ACTUAL use
@Path("xxx")
public class RenameMeResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
       
    private static final FacadeExample FACADE =  FacadeExample.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getRenameMeCount() {
       
        long count = FACADE.getRenameMeCount();
        //System.out.println("--------------->"+count);
        return "{\"count\":"+count+"}";  //Done manually so no need for a DTO
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getRenameMeById(@PathParam("id") int id){
        RenameMeDTO rmto = FACADE.getById(id);
        return GSON.toJson(rmto);
    }



    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public String putRenameMe(@PathParam("id") int id, String input) throws Exception {
        RenameMeDTO rmdto = GSON.fromJson(input, RenameMeDTO.class);
        System.out.println(rmdto);
        rmdto.setId(id);
        rmdto = FACADE.update(rmdto);
        return GSON.toJson(rmdto);
    }




    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response postExample(String input){
        RenameMeDTO rmdto = GSON.fromJson(input, RenameMeDTO.class);
        System.out.println(rmdto);
        rmdto = FACADE.create(rmdto);
        return Response.ok().entity(rmdto).build();
    }
}
