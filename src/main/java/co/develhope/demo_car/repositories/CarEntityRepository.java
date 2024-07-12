package co.develhope.demo_car.repositories;

import co.develhope.demo_car.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarEntityRepository extends JpaRepository<Car, Integer> {
}