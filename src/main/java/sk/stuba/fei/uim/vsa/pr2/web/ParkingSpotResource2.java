package sk.stuba.fei.uim.vsa.pr2.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import sk.stuba.fei.uim.vsa.pr2.domain.ParkingSpot;
import sk.stuba.fei.uim.vsa.pr2.service.CarParkService;
import sk.stuba.fei.uim.vsa.pr2.web.response.dto1.ParkingSpotDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.factory.ParkingSpotResponseFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/parkingspots/{id}")
public class ParkingSpotResource2 {
    public static final String EMPTY_RESPONSE = "{}";

    CarParkService service = new CarParkService();
    private final ObjectMapper json = new ObjectMapper();
    private final ParkingSpotResponseFactory factory = new ParkingSpotResponseFactory();


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getPS(@PathParam("id") Long id) {
        Object parkingSpot = service.getParkingSpot(id);
        if(parkingSpot == null){
            return EMPTY_RESPONSE;
        }
        List<Object> parkingSpots = new ArrayList<>();
        parkingSpots.add(parkingSpot);

        List<ParkingSpot> parkingSpotEntity = parkingSpots.stream().map(element -> (ParkingSpot)element).collect(Collectors.toList());
        List<ParkingSpotDto> parkingSpotDto = parkingSpotEntity.stream().map(factory::transformToDto).collect(Collectors.toList());
        try {
            return json.writeValueAsString(parkingSpotDto);
        } catch (JsonProcessingException e) {
//            LOGGER.log(Level.SEVERE, null, e);
            return EMPTY_RESPONSE;
        }
    }

    @DELETE
    public void deleteById(@PathParam("id")Long id) {
        service.deleteParkingSpot(id);
    }

}
