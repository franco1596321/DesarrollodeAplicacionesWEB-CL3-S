package pe.edu.cibertec.DAWI_AlejandroBustamanteFranco.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.DAWI_AlejandroBustamanteFranco.dto.CarDetailDto;
import pe.edu.cibertec.DAWI_AlejandroBustamanteFranco.dto.CarDto;
import pe.edu.cibertec.DAWI_AlejandroBustamanteFranco.entity.Car;
import pe.edu.cibertec.DAWI_AlejandroBustamanteFranco.repository.CarRepository;
import pe.edu.cibertec.DAWI_AlejandroBustamanteFranco.service.ManageService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ManageServiceImpl implements ManageService{

    @Autowired
    CarRepository carRepository;

    @Override
    public List<CarDto> getAllCars() throws Exception {
        List<CarDto> cars = new ArrayList<>();
        Iterable<Car> iterable = carRepository.findAll();
        iterable.forEach(car -> {
            CarDto carDto = new CarDto(car.getCarId(),
                    car.getMake(),
                    car.getModel(),
                    car.getOwnerName(),
                    car.getLicensePlate());
            cars.add(carDto);
        });

        return cars;
    }

    @Override
    public Optional<CarDto> getAllCarsById(int id) throws Exception {
        Optional<Car> optional = carRepository.findById(id);
        return optional.map(car -> new CarDto(car.getCarId(),
                car.getMake(),
                car.getModel(),
                car.getOwnerName(),
                car.getLicensePlate()));
    }

    @Override
    public Optional<CarDetailDto> getCarById(int id) throws Exception {
        Optional<Car> optional = carRepository.findById(id);
        return optional.map(car -> new CarDetailDto(car.getCarId(),
                car.getMake(),
                car.getModel(),
                car.getYear(),
                car.getVin(),
                car.getLicensePlate(),
                car.getOwnerName(),
                car.getOwnerContact(),
                car.getPurchaseDate(),
                car.getMileage(),
                car.getEngineType(),
                car.getColor(),
                car.getInsuranceCompany(),
                car.getInsurancePolicyNumber(),
                car.getRegistrationExpirationDate(),
                car.getServiceDueDate()));

    }

    @Override
    public boolean updateCar(CarDto carDto) throws Exception {
        Optional<Car> optional = carRepository.findById(carDto.id());
        return optional.map(car -> {
            car.setMake(carDto.make());
            car.setModel(carDto.model());
            car.setOwnerName(carDto.ownerName());
            car.setLicensePlate(carDto.licensePlate());
            carRepository.save(car);
            return true;
        }).orElse(false);

    }

    @Override
    public boolean deleteCarById(int id) throws Exception {
        Optional<Car> optional = carRepository.findById(id);
        return optional.map(user -> {
            carRepository.delete(user);
            return true;
        }).orElse(false);
    }

    @Override
    public boolean addCar(CarDetailDto carDetailDto) throws Exception {
        Optional<Car> optional = carRepository.findById(carDetailDto.id());
        if (optional.isPresent()) {
            return false;
        } else {
            Car car = new Car();
            car.setMake(carDetailDto.make());
            car.setModel(carDetailDto.model());
            car.setYear(carDetailDto.year());
            car.setVin(carDetailDto.vin());
            car.setLicensePlate(carDetailDto.licensePlate());
            car.setOwnerName(carDetailDto.ownerName());
            car.setOwnerContact(carDetailDto.ownerContact());
            car.setPurchaseDate(carDetailDto.purchaseDate());
            car.setMileage(carDetailDto.mileage());
            car.setEngineType(carDetailDto.engineType());
            car.setColor(carDetailDto.color());
            car.setInsuranceCompany(carDetailDto.insuranceCompany());
            car.setInsurancePolicyNumber(carDetailDto.insurancePolicyNumber());
            car.setRegistrationExpirationDate(carDetailDto.registrationExpirationDate());
            car.setServiceDueDate(carDetailDto.serviceDueDate());
            carRepository.save(car);

            return true;
        }
    }
}
