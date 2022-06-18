package sk.stuba.fei.uim.vsa.pr2.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sk.stuba.fei.uim.vsa.pr2.domain.Car;
import sk.stuba.fei.uim.vsa.pr2.domain.CarPark;
import sk.stuba.fei.uim.vsa.pr2.domain.ParkingSpot;
import sk.stuba.fei.uim.vsa.pr2.domain.User;
import sk.stuba.fei.uim.vsa.pr2.service.CarParkService;
import sk.stuba.fei.uim.vsa.pr2.web.response.dto1.CarDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.dto1.CarParkDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.dto1.ParkingSpotDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.dto1.UserDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.factory.CarParkResponseFactory;
import sk.stuba.fei.uim.vsa.pr2.web.response.factory.CarResponseFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/cars")
public class CarResource {
    public static final String EMPTY_RESPONSE = "{}";

    CarParkService service = new CarParkService();
    private final ObjectMapper json = new ObjectMapper();
    private final CarResponseFactory factory = new CarResponseFactory();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAll() {
        List<Object> cars = service.getCars();
        if(cars == null){
            return EMPTY_RESPONSE;
        }
        List<Car> carsEntity = cars.stream().map(element -> (Car)element).collect(Collectors.toList());
        List<CarDto> carsDto = carsEntity.stream().map(factory::transformToDto).collect(Collectors.toList());
        try {
            return json.writeValueAsString(carsDto);
        } catch (JsonProcessingException e) {
//            LOGGER.log(Level.SEVERE, null, e);
            return EMPTY_RESPONSE;
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCarById(@PathParam("id") Long id) {
        Car car = (Car) service.getCar(id);

        if(car == null){
            return EMPTY_RESPONSE;
        }
        List<Object> cars = new ArrayList<>();
        cars.add(car);

        List<Car> carEntity = cars.stream().map(element -> (Car)element).collect(Collectors.toList());
        List<CarDto> carDto = carEntity.stream().map(factory::transformToDto).collect(Collectors.toList());
        try {
            return json.writeValueAsString(carDto);
        } catch (JsonProcessingException e) {
//            LOGGER.log(Level.SEVERE, null, e);
            return EMPTY_RESPONSE;
        }
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCar(String car) {
        try {
            if(car == null){
                return Response.noContent().build();
            }
            CarDto dto = json.readValue(car, CarDto.class);
            Object c = service.createCar(dto.getOwner(), dto.getBrand(),dto.getModel(), dto.getColour(), dto.getVrp(),dto.getType());
            if(c == null){
                return Response.noContent().build();
            }
            List<Object> cars = new ArrayList<>();
            cars.add(c);

            List<Car> carEntity = cars.stream().map(element -> (Car)element).collect(Collectors.toList());
            // List<ReservationDto> resDto = resEntity.stream().map(factory::transformToDto).collect(Collectors.toList());

            for(Car cc : carEntity){
                dto = factory.transformToDto(cc);
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
        service.deleteCar(id);
    }
}
