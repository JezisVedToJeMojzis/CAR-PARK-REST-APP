package sk.stuba.fei.uim.vsa.pr2.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sk.stuba.fei.uim.vsa.pr2.domain.ParkingSpot;
import sk.stuba.fei.uim.vsa.pr2.domain.Reservation;
import sk.stuba.fei.uim.vsa.pr2.service.CarParkService;
import sk.stuba.fei.uim.vsa.pr2.web.response.dto1.ParkingSpotDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.dto1.ReservationDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.factory.ParkingSpotResponseFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/carparks/{id}/floors/{identifier}/spots")
public class ParkingSpotResource {
    public static final String EMPTY_RESPONSE = "{}";

    CarParkService service = new CarParkService();
    private final ObjectMapper json = new ObjectMapper();
    private final ParkingSpotResponseFactory factory = new ParkingSpotResponseFactory();


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAll(@PathParam("id") Long id,@PathParam("identifier") String identifier) {
        List<Object> parkingSpots = service.getParkingSpots(id,identifier);
        if(parkingSpots == null){
            return EMPTY_RESPONSE;
        }
        List<ParkingSpot> parkingSpotsEntity = parkingSpots.stream().map(element -> (ParkingSpot)element).collect(Collectors.toList());
        List<ParkingSpotDto> parkingSpotsDto = parkingSpotsEntity.stream().map(factory::transformToDto).collect(Collectors.toList());
        try {
            return json.writeValueAsString(parkingSpotsDto);
        } catch (JsonProcessingException e) {
//            LOGGER.log(Level.SEVERE, null, e);
            return EMPTY_RESPONSE;
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createParkingSpot(@PathParam("id") Long id,@PathParam("identifier") String identifier, String parkingSpot) {
        try {
            if(parkingSpot == null){
                return Response.noContent().build();
            }
            ParkingSpotDto dto = json.readValue(parkingSpot, ParkingSpotDto.class);
            dto.setCarPark(id);
            dto.setCarParkFloor(identifier);
            Object ps = service.createParkingSpot(id,identifier,dto.getIdentifier(),dto.getType());
            if(ps == null){
                return Response.noContent().build();
            }
            List<Object> spots = new ArrayList<>();
            spots.add(ps);

            List<ParkingSpot> spotEntity = spots.stream().map(element -> (ParkingSpot)element).collect(Collectors.toList());
            // List<ReservationDto> resDto = resEntity.stream().map(factory::transformToDto).collect(Collectors.toList());

            for(ParkingSpot p : spotEntity){
                dto = factory.transformToDto(p);
            }
            return  Response.status(Response.Status.CREATED).entity(json.writeValueAsString(dto)).build();
        } catch (JsonProcessingException e) {
            // LOGGER.log(Level.SEVERE, null, e);
            return Response.noContent().build();
        }
    }
}
