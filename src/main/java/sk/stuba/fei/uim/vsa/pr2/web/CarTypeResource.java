package sk.stuba.fei.uim.vsa.pr2.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sk.stuba.fei.uim.vsa.pr2.domain.CarType;
import sk.stuba.fei.uim.vsa.pr2.domain.Reservation;
import sk.stuba.fei.uim.vsa.pr2.domain.User;
import sk.stuba.fei.uim.vsa.pr2.service.CarParkService;
import sk.stuba.fei.uim.vsa.pr2.web.response.dto1.CarTypeDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.dto1.ReservationDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.dto1.UserDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.factory.CarTypeResponseFactory;
import sk.stuba.fei.uim.vsa.pr2.web.response.factory.UserResponseFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/cartypes")
public class CarTypeResource {
    public static final String EMPTY_RESPONSE = "{}";

    CarParkService service = new CarParkService();
    private final ObjectMapper json = new ObjectMapper();
    private final CarTypeResponseFactory factory = new CarTypeResponseFactory();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAll() {
        List<Object> types = service.getCarTypes();
        if(types == null){
            return EMPTY_RESPONSE;
        }
        List<CarType> typesEntity = types.stream().map(element -> (CarType)element).collect(Collectors.toList());
        List<CarTypeDto> typesDto = typesEntity.stream().map(factory::transformToDto).collect(Collectors.toList());
        try {
            return json.writeValueAsString(typesDto);
        } catch (JsonProcessingException e) {
//            LOGGER.log(Level.SEVERE, null, e);
            return EMPTY_RESPONSE;
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getTypeById(@PathParam("id") Long id) {
        CarType type = (CarType) service.getCarType(id);

        if(type == null){
            return EMPTY_RESPONSE;
        }
        List<Object> types = new ArrayList<>();
        types.add(type);

        List<CarType> typeEntity = types.stream().map(element -> (CarType)element).collect(Collectors.toList());
        List<CarTypeDto> typeDto = typeEntity.stream().map(factory::transformToDto).collect(Collectors.toList());
        try {
            return json.writeValueAsString(typeDto);
        } catch (JsonProcessingException e) {
//            LOGGER.log(Level.SEVERE, null, e);
            return EMPTY_RESPONSE;
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createType(String type) {
        try {
            if(type == null){
                return Response.noContent().build();
            }
            CarTypeDto dto = json.readValue(type, CarTypeDto.class);
            Object ct = service.createCarType(dto.getName());
            if(ct == null){
                return Response.noContent().build();
            }
            List<Object> types = new ArrayList<>();
            types.add(ct);

            List<CarType> ctEntity = types.stream().map(element -> (CarType)element).collect(Collectors.toList());

            for(CarType c : ctEntity){
                dto = factory.transformToDto(c);
            }

            return  Response.status(Response.Status.CREATED).entity(json.writeValueAsString(dto)).build();
        } catch (JsonProcessingException e) {
            // LOGGER.log(Level.SEVERE, null, e);
            return Response.noContent().build();
        }
    }

    @DELETE
    @Path("/{id}")
    public void deleteById(@PathParam("id")Long id) {
        service.deleteCarType(id);
    }
}
