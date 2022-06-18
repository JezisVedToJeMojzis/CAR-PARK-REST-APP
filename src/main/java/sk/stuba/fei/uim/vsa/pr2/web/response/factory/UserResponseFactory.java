package sk.stuba.fei.uim.vsa.pr2.web.response.factory;

import sk.stuba.fei.uim.vsa.pr2.domain.*;
import sk.stuba.fei.uim.vsa.pr2.service.CarParkService;
import sk.stuba.fei.uim.vsa.pr2.web.response.dto1.CarDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.dto1.CarParkFloorDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.dto1.UserDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.interfaceFactory.UResponseFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserResponseFactory implements UResponseFactory<User, UserDto> {
    CarParkService service = new CarParkService();


    @Override
    public UserDto transformToDto(User entity) {
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setFirstName(entity.getFirstname());
        dto.setLastName(entity.getLastname());

        List<Object> carsObject = service.getCars(entity.getId());
        if(carsObject == null){
            List<CarDto> carDto = new ArrayList<>();
            dto.setCars(carDto);
        }
        else{
            List<Car> carsEntity = carsObject.stream().map(element -> (Car)element).collect(Collectors.toList());
            CarResponseFactory carFactory = new CarResponseFactory();

            List<CarDto> carDto = carsEntity.stream().map(carFactory::transformToDto).collect(Collectors.toList());
            dto.setCars(carDto);
        }

        return dto;
    }

    @Override
    public User transformToEntity(UserDto dto) {
        User entity = new User();
        entity.setId(dto.getId());
        entity.setEmail(dto.getEmail());
        entity.setFirstname(dto.getFirstName());
        entity.setLastname(dto.getLastName());


        return entity;
    }
}
