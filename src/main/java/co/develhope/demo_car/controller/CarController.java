package co.develhope.demo_car.controller;

import co.develhope.demo_car.entities.Car;
import co.develhope.demo_car.repositories.CarEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarEntityRepository carEntityRepository;

    @PostMapping()
    public Car create(@RequestBody Car car) {
        return carEntityRepository.saveAndFlush(car);
    }

    @GetMapping()
    public List<Car> getCarsList() {
        return carEntityRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Integer id) {
        Optional<Car> car = carEntityRepository.findById(id);
        if (car.isPresent()) {
            return new ResponseEntity<>(car.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Car(), HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCarType(@PathVariable Integer id, @RequestBody String type) {
        if (carEntityRepository.existsById(id)) {
            Car car = carEntityRepository.findById(id).get();
            car.setType(type);
            carEntityRepository.save(car);
            return new ResponseEntity<>(car, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Car(), HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Integer id) {
        if (carEntityRepository.existsById(id)) {
            carEntityRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping(params = "ids")
    public ResponseEntity<Void> deleteMultipleCars(@RequestParam List<Long> ids) {
        // Logica per eliminare più auto in base agli ID forniti
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAllCars() {
        carEntityRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}








