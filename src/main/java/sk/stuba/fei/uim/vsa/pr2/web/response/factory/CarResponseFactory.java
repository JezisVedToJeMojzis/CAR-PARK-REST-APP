package sk.stuba.fei.uim.vsa.pr2.web.response.factory;

import sk.stuba.fei.uim.vsa.pr2.domain.*;
import sk.stuba.fei.uim.vsa.pr2.service.CarParkService;
import sk.stuba.fei.uim.vsa.pr2.web.response.dto1.CarDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.dto1.CarParkFloorDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.dto1.ReservationDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.interfaceFactory.CResponseFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CarResponseFactory implements CResponseFactory<Car, CarDto> {

    CarParkService service = new CarParkService();
    //private final CarParkResponseFactory floorFactory = new CarParkResponseFactory();

    @Override
    public CarDto transformToDto(Car entity) {
        CarDto dto = new CarDto();
        dto.setId(entity.getId());
        dto.setBrand(entity.getBrand());
        dto.setModel(entity.getModel());
        dto.setVrp(entity.getVehicleRegistrationPlate());
        dto.setColour(entity.getColour());

        dto.setOwner(entity.getUserId().getId());
        dto.setType(entity.getCarType().getId());

        List<Object> resObject = service.getReservationByCarId(entity.getId());
        if(resObject == null){
            List<ReservationDto> resDto= new ArrayList<>();
            dto.setReservations(resDto);
        }
        else{
            List<Reservation> resEntity = resObject.stream().map(element -> (Reservation)element).collect(Collectors.toList());
            ReservationResponseFactory resFactory = new ReservationResponseFactory();

            List<ReservationDto> resDto = resEntity.stream().map(resFactory::transformToDto).collect(Collectors.toList());
            dto.setReservations(resDto);
        }

        return dto;
    }

    @Override
    public Car transformToEntity(CarDto dto) {
        Car entity = new Car();
        entity.setId(dto.getId());
        entity.setBrand(dto.getBrand());
        entity.setModel(dto.getModel());
        entity.setVehicleRegistrationPlate(dto.getVrp());
        entity.setColour(dto.getColour());

        User us = new User();
        us.setId(dto.getOwner());
        entity.setUserId(us);


        CarType ct = new CarType();
        ct.setId(dto.getType());
        entity.setCarType(ct);

        return entity;
    }
}
