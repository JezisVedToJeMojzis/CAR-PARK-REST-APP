package sk.stuba.fei.uim.vsa.pr2.web.response.factory;

import lombok.SneakyThrows;
import sk.stuba.fei.uim.vsa.pr2.domain.*;
import sk.stuba.fei.uim.vsa.pr2.service.CarParkService;
import sk.stuba.fei.uim.vsa.pr2.web.response.dto1.CarParkFloorDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.dto1.ParkingSpotDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.dto1.ReservationDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.interfaceFactory.PSResponseFactory;
import sk.stuba.fei.uim.vsa.pr2.web.response.interfaceFactory.RResponseFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ReservationResponseFactory  implements RResponseFactory<Reservation, ReservationDto> {
    CarParkService service = new CarParkService();

    @Override
    public ReservationDto transformToDto(Reservation entity) {
     //   SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy");

        ReservationDto dto = new ReservationDto();
        dto.setId(entity.getId());

        dto.setStart(entity.getStartTime());

        dto.setEnd(entity.getEndTime());
        dto.setCar(entity.getCarId().getId());
        dto.setSpot(entity.getParkingSpotId().getId());
        dto.setPrices(entity.getPrice());

        return dto;
    }

    @Override
    public Reservation transformToEntity(ReservationDto dto) {
     //   SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy");

        Reservation entity = new Reservation();
        entity.setId(dto.getId());
        entity.setStartTime(dto.getStart());
        entity.setEndTime(dto.getEnd());

        Car car = new Car();
        car.setId(dto.getCar());
        entity.setCarId(car);

        ParkingSpot spot = new ParkingSpot();
        spot.setId(dto.getSpot());
        entity.setParkingSpotId(spot);

        entity.setPrice(dto.getPrices());

        return null;
    }
}
