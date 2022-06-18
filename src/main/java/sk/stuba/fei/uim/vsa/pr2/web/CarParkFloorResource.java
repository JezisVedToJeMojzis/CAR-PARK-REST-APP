package sk.stuba.fei.uim.vsa.pr2.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
//import sk.stuba.fei.uim.vsa.pr2.service.CarParkService;
import jakarta.ws.rs.core.Response;
import sk.stuba.fei.uim.vsa.pr2.domain.CarParkFloor;
import sk.stuba.fei.uim.vsa.pr2.service.CarParkService;
import sk.stuba.fei.uim.vsa.pr2.web.response.dto1.CarParkFloorDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.factory.CarParkFloorResponseFactory;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/carparks/{id}/floors")
public class CarParkFloorResource {
    public static final String EMPTY_RESPONSE = "{}";

    CarParkService service = new CarParkService();
    private final ObjectMapper json = new ObjectMapper();
    private final CarParkFloorResponseFactory factory = new CarParkFloorResponseFactory();

    @GET
    //@Path("/{id}/floors")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAll(@PathParam("id")Long id) {
       // System.out.println("KKT");
        List<Object> carParkFloors = service.getCarParkFloors(id);
        if(carParkFloors == null){
            return EMPTY_RESPONSE;
        }
        List<CarParkFloor> carParkFloorsEntity = carParkFloors.stream().map(element -> (CarParkFloor)element).collect(Collectors.toList());
        List<CarParkFloorDto> carParkFloorsDto = carParkFloorsEntity.stream().map(factory::transformToDto).collect(Collectors.toList());
        try {
            return json.writeValueAsString(carParkFloorsDto);
        } catch (JsonProcessingException e) {
//            LOGGER.log(Level.SEVERE, null, e);
            return EMPTY_RESPONSE;
        }
    }

    //FUNGUJE LEN TAKTO
    @GET
    @Path("/?identifier={cpfIdentifier}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCarParkFloorById(@PathParam("id") Long id,@PathParam("cpfIdentifier") String identifier) {
        CarParkFloor cpf = (CarParkFloor) service.getCarParkFloor(id,identifier);
        if(cpf == null){
            return EMPTY_RESPONSE;
        }
        List<Object> carParkFloors = new ArrayList<>();
        carParkFloors.add(cpf);

        List<CarParkFloor> carParkFloorEntity = carParkFloors.stream().map(element -> (CarParkFloor)element).collect(Collectors.toList());
        List<CarParkFloorDto> carParkFloorDto = carParkFloorEntity.stream().map(factory::transformToDto).collect(Collectors.toList());

        try {
            return json.writeValueAsString(carParkFloorDto);
        } catch (JsonProcessingException e) {
//            LOGGER.log(Level.SEVERE, null, e);
            return EMPTY_RESPONSE;
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCarParkFloor(String carParkFloor, @PathParam("id")Long id) {
        try {
            CarParkFloorDto dto = json.readValue(carParkFloor, CarParkFloorDto.class);
            dto.setCarPark(id);
            CarParkFloor cpf = service.saveCarParkFloor(factory.transformToEntity(dto));

            if(cpf == null){
                return Response.noContent().build();
            }
            dto = factory.transformToDto(cpf);
            return  Response.status(Response.Status.CREATED).entity(json.writeValueAsString(dto)).build();
        } catch (JsonProcessingException e) {
            // LOGGER.log(Level.SEVERE, null, e);
            return Response.noContent().build();
        }
    }


    @DELETE
    @Path("/{identifier}")
    public void deleteById(@PathParam("id")Long id,@PathParam("identifier") String identifier) {
        service.deleteCarParkFloor(id,identifier);
    }

}
