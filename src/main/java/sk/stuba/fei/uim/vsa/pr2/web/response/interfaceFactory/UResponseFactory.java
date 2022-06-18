package sk.stuba.fei.uim.vsa.pr2.web.response.interfaceFactory;

import sk.stuba.fei.uim.vsa.pr2.web.response.abstractDto.PSDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.abstractDto.UDto;

public interface UResponseFactory<R, T extends UDto>{
    T transformToDto(R entity);

    R transformToEntity(T dto);
}
