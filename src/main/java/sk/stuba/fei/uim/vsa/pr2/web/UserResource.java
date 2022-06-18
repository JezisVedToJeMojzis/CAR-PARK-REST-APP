package sk.stuba.fei.uim.vsa.pr2.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sk.stuba.fei.uim.vsa.pr2.domain.User;
import sk.stuba.fei.uim.vsa.pr2.service.CarParkService;
import sk.stuba.fei.uim.vsa.pr2.web.response.dto1.UserDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.factory.UserResponseFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/users")
public class UserResource {
    public static final String EMPTY_RESPONSE = "{}";

    CarParkService service = new CarParkService();
    private final ObjectMapper json = new ObjectMapper();
    private final UserResponseFactory factory = new UserResponseFactory();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAll() {
        List<Object> users = service.getUsers();
        if(users == null){
            return EMPTY_RESPONSE;
        }
        List<User> usersEntity = users.stream().map(element -> (User)element).collect(Collectors.toList());
        List<UserDto> usersDto = usersEntity.stream().map(factory::transformToDto).collect(Collectors.toList());
        try {
            return json.writeValueAsString(usersDto);
        } catch (JsonProcessingException e) {
//            LOGGER.log(Level.SEVERE, null, e);
        return EMPTY_RESPONSE;
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUserById(@PathParam("id") Long id) {
        User user = (User) service.getUser(id);

        if(user == null){
            return EMPTY_RESPONSE;
        }
        List<Object> users = new ArrayList<>();
        users.add(user);

        List<User> userEntity = users.stream().map(element -> (User)element).collect(Collectors.toList());
        List<UserDto> userDto = userEntity.stream().map(factory::transformToDto).collect(Collectors.toList());
        try {
            return json.writeValueAsString(userDto);
        } catch (JsonProcessingException e) {
//            LOGGER.log(Level.SEVERE, null, e);
            return EMPTY_RESPONSE;
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(String user) {
        try {
            if(user == null){
                return Response.noContent().build();
            }
            UserDto dto = json.readValue(user, UserDto.class);
            User us = service.saveUser(factory.transformToEntity(dto));
            if(us == null){
                return Response.noContent().build();
            }
            dto = factory.transformToDto(us);
            return  Response.status(Response.Status.CREATED).entity(json.writeValueAsString(dto)).build();
        } catch (JsonProcessingException e) {
            // LOGGER.log(Level.SEVERE, null, e);
            return Response.noContent().build();
        }
    }

    @DELETE
    @Path("/{id}")
    public void deleteById(@PathParam("id")Long id) {
        service.deleteUser(id);
    }
}
