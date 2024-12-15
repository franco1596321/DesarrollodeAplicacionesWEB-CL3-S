package pe.edu.cibertec.DAWI_AlejandroBustamanteFranco.service;

import pe.edu.cibertec.DAWI_AlejandroBustamanteFranco.dto.CarDetailDto;
import pe.edu.cibertec.DAWI_AlejandroBustamanteFranco.dto.CarDto;

import java.util.List;
import java.util.Optional;

public interface ManageService {
    List<CarDto> getAllCars() throws Exception;

    Optional<CarDto> getAllCarsById(int id) throws Exception;

    Optional<CarDetailDto> getCarById(int id) throws Exception;

    boolean updateCar(CarDto carDto) throws Exception;

    boolean deleteCarById(int id) throws Exception;

    boolean addCar(CarDetailDto carDetailDto) throws Exception;
}
