package sk.stuba.fei.uim.vsa.pr2.web.response.interfaceFactory;

import sk.stuba.fei.uim.vsa.pr2.web.response.abstractDto.CPDto;

public interface CPResponseFactory<R, T extends CPDto> {
    T transformToDto(R entity);

    R transformToEntity(T dto);
}
