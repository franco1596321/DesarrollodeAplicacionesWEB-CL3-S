package pe.edu.cibertec.DAWI_AlejandroBustamanteFranco.response;

import pe.edu.cibertec.DAWI_AlejandroBustamanteFranco.dto.CarDto;

public record FindCarsResponse(String code,
                               String error,
                               Iterable<CarDto> cars) {
}
