package pe.edu.cibertec.DAWI_AlejandroBustamanteFranco.repository;

import org.springframework.data.repository.CrudRepository;
import pe.edu.cibertec.DAWI_AlejandroBustamanteFranco.entity.Car;

public interface CarRepository extends CrudRepository<Car, Integer> {
}