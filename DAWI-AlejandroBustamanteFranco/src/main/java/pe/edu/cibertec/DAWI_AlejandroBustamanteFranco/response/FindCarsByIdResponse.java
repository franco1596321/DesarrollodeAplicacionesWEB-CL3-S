package pe.edu.cibertec.DAWI_AlejandroBustamanteFranco.response;

import pe.edu.cibertec.DAWI_AlejandroBustamanteFranco.dto.CarDetailDto;

public record FindCarsByIdResponse(String code,
                                   String error,
                                   CarDetailDto cars) {
}
