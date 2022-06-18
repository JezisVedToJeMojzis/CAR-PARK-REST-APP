package sk.stuba.fei.uim.vsa.pr2.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sk.stuba.fei.uim.vsa.pr2.domain.Reservation;
import sk.stuba.fei.uim.vsa.pr2.domain.User;
import sk.stuba.fei.uim.vsa.pr2.service.CarParkService;
import sk.stuba.fei.uim.vsa.pr2.web.response.dto1.ReservationDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.dto1.UserDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.factory.ReservationResponseFactory;
import sk.stuba.fei.uim.vsa.pr2.web.response.factory.UserResponseFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/reservations")
public class ReservationResource {
    public static final String EMPTY_RESPONSE = "{}";

    CarParkService service = new CarParkService();
    private final ObjectMapper json = new ObjectMapper();
    private final ReservationResponseFactory factory = new ReservationResponseFactory();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAll() {
        List<Object> reservations = service.getReservations();
        if(reservations == null){
            return EMPTY_RESPONSE;
        }
        List<Reservation> reservationsEntity = reservations.stream().map(element -> (Reservation)element).collect(Collectors.toList());
        List<ReservationDto> reservationsDto = reservationsEntity.stream().map(factory::transformToDto).collect(Collectors.toList());
        try {
            return json.writeValueAsString(reservationsDto);
        } catch (JsonProcessingException e) {
//            LOGGER.log(Level.SEVERE, null, e);
            return EMPTY_RESPONSE;
        }
    }


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getReservationById(@PathParam("id") Long id) {
        Reservation res = (Reservation) service.getReservationById(id);

        if(res == null){
            return EMPTY_RESPONSE;
        }
        List<Object> reservations = new ArrayList<>();
        reservations.add(res);

        List<Reservation> reservationEntity = reservations.stream().map(element -> (Reservation)element).collect(Collectors.toList());
        List<ReservationDto> reservationDto = reservationEntity.stream().map(factory::transformToDto).collect(Collectors.toList());
        try {
            return json.writeValueAsString(reservationDto);
        } catch (JsonProcessingException e) {
//            LOGGER.log(Level.SEVERE, null, e);
            return EMPTY_RESPONSE;
        }
    }

    @POST
    @Path("/{id}/end")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response endRes(@PathParam("id")Long id) {
        try {
            Reservation res = (Reservation) service.getReservationById(id);
            if(res == null){
                return Response.noContent().build();
            }
            if(res.getEndTime()!=null){
                return Response.noContent().build();
            }
            service.endReservation(id);
            res = (Reservation) service.getReservationById(id);
            ReservationDto dto = factory.transformToDto(res);
            return  Response.status(Response.Status.CREATED).entity(json.writeValueAsString(dto)).build();
        } catch (JsonProcessingException e) {
            // LOGGER.log(Level.SEVERE, null, e);
            return Response.noContent().build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createRes(String res) {
        try {
            if(res == null){
                return Response.noContent().build();
            }
            ReservationDto dto = json.readValue(res, ReservationDto.class);
            Object rs = service.createReservation(dto.getSpot(), dto.getCar());
            if(rs == null){
                return Response.noContent().build();
            }
            List<Object> reservations = new ArrayList<>();
            reservations.add(rs);

            List<Reservation> resEntity = reservations.stream().map(element -> (Reservation)element).collect(Collectors.toList());
           // List<ReservationDto> resDto = resEntity.stream().map(factory::transformToDto).collect(Collectors.toList());

            for(Reservation r : resEntity){
                dto = factory.transformToDto(r);
            }

            return  Response.status(Response.Status.CREATED).entity(json.writeValueAsString(dto)).build();
        } catch (JsonProcessingException e) {
            // LOGGER.log(Level.SEVERE, null, e);
            return Response.noContent().build();
        }
    }


}
