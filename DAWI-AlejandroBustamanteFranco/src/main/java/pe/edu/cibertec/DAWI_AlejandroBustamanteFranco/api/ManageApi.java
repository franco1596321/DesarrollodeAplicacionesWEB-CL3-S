package pe.edu.cibertec.DAWI_AlejandroBustamanteFranco.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.DAWI_AlejandroBustamanteFranco.dto.CarDetailDto;
import pe.edu.cibertec.DAWI_AlejandroBustamanteFranco.dto.CarDto;
import pe.edu.cibertec.DAWI_AlejandroBustamanteFranco.response.*;
import pe.edu.cibertec.DAWI_AlejandroBustamanteFranco.service.ManageService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/manage-car")
public class ManageApi {

    @Autowired
    ManageService manageService;

    @GetMapping("/all")
    public FindCarsResponse findCars(@RequestParam(value = "id", defaultValue = "0")String id){
        try {

            if(Integer.parseInt(id) > 0){
                Optional<CarDto> optional = manageService.getAllCarsById(Integer.parseInt(id));
                if(optional.isPresent()){
                    CarDto carDto = optional.get();
                    return new FindCarsResponse("01", "", List.of(carDto));
                }else {
                    return new FindCarsResponse("02", "Car has been not found", null);
                }

            } else {
                List<CarDto> cars = manageService.getAllCars();
                if(!cars.isEmpty())
                    return new FindCarsResponse("01", "", cars);
                else
                    return new FindCarsResponse("03", "Cars list not found", cars);
            }

        } catch (Exception e) {
            return new FindCarsResponse("99", "Cars service not found", null);
        }
    }

    ///

    @GetMapping("/detail")
    public FindCarsByIdResponse findCarsById(@RequestParam(value = "id", defaultValue = "0")String id){
        try {

            if(Integer.parseInt(id) > 0){
                Optional<CarDetailDto>optional = manageService.getCarById(Integer.parseInt(id));
                if(optional.isPresent()){
                    CarDetailDto carDetailDto = optional.get();
                    return new FindCarsByIdResponse("01", "", carDetailDto);
                }else {
                    return new FindCarsByIdResponse("02", "Car has been not found", null);
                }

            } else
                return new FindCarsByIdResponse("03", "Parameter not found", null);


        } catch (Exception e) {
            return new FindCarsByIdResponse("99", "Cars service not found", null);
        }
    }

    ///
    @PostMapping("/update")
    public UpdateCarResponse updateCar(@RequestBody CarDto carDto){
        try {
            if (manageService.updateCar(carDto)) {
                return new UpdateCarResponse("01", "");
            } else {
                return new UpdateCarResponse("02", "Car has been not found");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new UpdateCarResponse("99", "Cars service not found");
        }
    }

    ///
    @DeleteMapping("/delete")
    public DeleteCarResponse deleteCar(@RequestParam int id) {
        try {
            boolean deleted = manageService.deleteCarById(id);
            if (deleted) {
                return new DeleteCarResponse("01", "Car deleted successfully");
            } else {
                return new DeleteCarResponse("02", "Car not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new DeleteCarResponse("99", "Service error");
        }
    }

    ///
    @PostMapping("/create")
    public CreateCarResponse addCar(@RequestBody CarDetailDto carDetailDto) {
        try {

            if (carDetailDto.id() == null) {
                return new CreateCarResponse("03", "Car ID cannot be null", null);
            }

            boolean isAdded = manageService.addCar(carDetailDto);
            if (isAdded) {
                return new CreateCarResponse("01", "Car created successfully", carDetailDto.id());
            } else {
                return new CreateCarResponse("02", "Car with the same ID already exists", null);
            }
        } catch (IllegalArgumentException e) {
            return new CreateCarResponse("04", "Invalid input: " + e.getMessage(), null);
        } catch (Exception e) {
            e.printStackTrace();
            return new CreateCarResponse("99", "Unexpected error occurred", null);
        }
    }
}

