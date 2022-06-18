package sk.stuba.fei.uim.vsa.pr2.web.response.factory;

import sk.stuba.fei.uim.vsa.pr2.domain.CarPark;
import sk.stuba.fei.uim.vsa.pr2.domain.CarParkFloor;
import sk.stuba.fei.uim.vsa.pr2.domain.ParkingSpot;
import sk.stuba.fei.uim.vsa.pr2.service.CarParkService;
import sk.stuba.fei.uim.vsa.pr2.web.response.dto1.CarParkFloorDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.dto1.ParkingSpotDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.interfaceFactory.CPFResponseFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CarParkFloorResponseFactory implements CPFResponseFactory<CarParkFloor, CarParkFloorDto> {

    CarParkService service = new CarParkService();

    @Override
    public CarParkFloorDto transformToDto(CarParkFloor entity) {
        CarParkFloorDto dto = new CarParkFloorDto();
        dto.setId(entity.getId());
        dto.setIdentifier(entity.getFloorIdentifier());
        dto.setCarPark(entity.getCarParkId().getId());

        List<Object> spotsObject = service.getParkingSpots(entity.getCarParkId().getId(),entity.getFloorIdentifier());
        if(spotsObject == null){
            List<ParkingSpotDto> spotsDto = new ArrayList<>();
            dto.setSpots(spotsDto);
        }
        else{
            List<ParkingSpot> spotsEntity = spotsObject.stream().map(element -> (ParkingSpot)element).collect(Collectors.toList());
            ParkingSpotResponseFactory spotFactory = new ParkingSpotResponseFactory();
            List<ParkingSpotDto> spotsDto = spotsEntity.stream().map(spotFactory::transformToDto).collect(Collectors.toList());
            dto.setSpots(spotsDto);
        }
        return dto;
    }

    @Override
    public CarParkFloor transformToEntity(CarParkFloorDto dto) {
        CarParkFloor entity = new CarParkFloor();
        entity.setId(dto.getId());
        entity.setFloorIdentifier(dto.getIdentifier());

        CarPark cp = new CarPark();
        cp.setId(dto.getCarPark());
        entity.setCarParkId(cp);

//        List<Object> spotsObject = service.getParkingSpots(dto.getCarPark(), dto.getIdentifier());
//
//        List<ParkingSpot> spotsEntity = spotsObject.stream().map(element -> (ParkingSpot)element).collect(Collectors.toList());
//
//        entity.setSpots(spotsEntity);

        return entity;
    }

}
