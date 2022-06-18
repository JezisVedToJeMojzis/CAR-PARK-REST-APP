package sk.stuba.fei.uim.vsa.pr2.web.response.factory;

import sk.stuba.fei.uim.vsa.pr2.domain.CarPark;
import sk.stuba.fei.uim.vsa.pr2.domain.CarParkFloor;
import sk.stuba.fei.uim.vsa.pr2.service.CarParkService;
import sk.stuba.fei.uim.vsa.pr2.web.response.dto1.CarParkDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.dto1.CarParkFloorDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.interfaceFactory.CPResponseFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CarParkResponseFactory implements CPResponseFactory<CarPark, CarParkDto> {

    CarParkService service = new CarParkService();
    //private final CarParkResponseFactory floorFactory = new CarParkResponseFactory();

    @Override
    public CarParkDto transformToDto(CarPark entity) {
        CarParkDto dto = new CarParkDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setAddress(entity.getAddress());
        dto.setPrices(entity.getPricePerHour());

        List<Object> floorsObject = service.getCarParkFloors(entity.getId());
        if(floorsObject == null){
            List<CarParkFloorDto> floorDto= new ArrayList<>();
            dto.setFloors(floorDto);
        }
        else{
            List<CarParkFloor> floorsEntity = floorsObject.stream().map(element -> (CarParkFloor)element).collect(Collectors.toList());
            CarParkFloorResponseFactory floorFactory = new CarParkFloorResponseFactory();

            List<CarParkFloorDto> floorDto = floorsEntity.stream().map(floorFactory::transformToDto).collect(Collectors.toList());
            dto.setFloors(floorDto);
        }

        return dto;
    }

    @Override
    public CarPark transformToEntity(CarParkDto dto) {
        CarParkService service = new CarParkService();
        CarPark entity = new CarPark();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setAddress(dto.getAddress());
        entity.setPricePerHour(dto.getPrices());

//        List<Object> floorsObject = service.getCarParkFloors(entity.getId());
//
//        List<CarParkFloor> floorsEntity = floorsObject.stream().map(element -> (CarParkFloor)element).collect(Collectors.toList());
//
//        entity.setFloors(floorsEntity);

        return entity;
    }

}
