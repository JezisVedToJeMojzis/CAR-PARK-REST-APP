package sk.stuba.fei.uim.vsa.pr2.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sk.stuba.fei.uim.vsa.pr2.domain.CarPark;
//import sk.stuba.fei.uim.vsa.pr2.service.CarParkService;
import sk.stuba.fei.uim.vsa.pr2.service.CarParkService;
import sk.stuba.fei.uim.vsa.pr2.web.response.dto1.CarParkDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.factory.CarParkResponseFactory;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/carparks")
public class CarParkResource {
    public static final String EMPTY_RESPONSE = "{}";

    CarParkService service = new CarParkService();
    private final ObjectMapper json = new ObjectMapper();
    private final CarParkResponseFactory factory = new CarParkResponseFactory();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAll() {
        List<Object> carParks = service.getCarParks();
        if(carParks == null){
            return EMPTY_RESPONSE;
        }
        List<CarPark> carParksEntity = carParks.stream().map(element -> (CarPark)element).collect(Collectors.toList());
        List<CarParkDto> carParksDto = carParksEntity.stream().map(factory::transformToDto).collect(Collectors.toList());
        try {
            return json.writeValueAsString(carParksDto);
        } catch (JsonProcessingException e) {
//            LOGGER.log(Level.SEVERE, null, e);
            return EMPTY_RESPONSE;
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCarParkById(@PathParam("id") Long id) {
        CarPark cp = (CarPark) service.getCarPark(id);

        if(cp == null){
            return EMPTY_RESPONSE;
        }
        List<Object> carParks = new ArrayList<>();
        carParks.add(cp);

        List<CarPark> carParkEntity = carParks.stream().map(element -> (CarPark)element).collect(Collectors.toList());
        List<CarParkDto> carParkDto = carParkEntity.stream().map(factory::transformToDto).collect(Collectors.toList());
        try {
            return json.writeValueAsString(carParkDto);
        } catch (JsonProcessingException e) {
//            LOGGER.log(Level.SEVERE, null, e);
            return EMPTY_RESPONSE;
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCarPark(String carPark) {
        try {
            CarParkDto dto = json.readValue(carPark, CarParkDto.class);
            CarPark cp = service.saveCarPark(factory.transformToEntity(dto));
            if(cp == null){
                return Response.noContent().build();
            }
            dto = factory.transformToDto(cp);
            return  Response.status(Response.Status.CREATED).entity(json.writeValueAsString(dto)).build();
           // return json.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
           // LOGGER.log(Level.SEVERE, null, e);
            return Response.noContent().build();
        }
    }

    @DELETE
    @Path("/{id}")
    public void deleteById(@PathParam("id")Long id) {
        service.deleteCarPark(id);
    }

}
