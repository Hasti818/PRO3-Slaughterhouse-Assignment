package animalregistration.controller;

import animalregistration.model.Animal;
import animalregistration.service.AnimalService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/animals")
public class AnimalController
{
  private final AnimalService animalService;

  public AnimalController(AnimalService animalService)
  {
    this.animalService = animalService;
  }

  @PostMapping
  public ResponseEntity<Animal> registerAnimal(
      @RequestBody Animal animal)
  {
    Animal registeredAnimal =
        animalService.registerAnimal(animal);

    return new ResponseEntity<>(
        registeredAnimal,
        HttpStatus.CREATED
    );
  }

  @GetMapping("/{registrationNumber}")
  public ResponseEntity<Animal> getByRegistrationNumber(
      @PathVariable String registrationNumber)
  {
    Animal animal =
        animalService.getByRegistrationNumber(registrationNumber);

    return new ResponseEntity<>(
        animal,
        HttpStatus.OK
    );
  }

  @GetMapping(params = "date")
  public ResponseEntity<List<Animal>> getByArrivalDate(
      @RequestParam("date")
      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
      LocalDate arrivalDate)
  {
    List<Animal> animals =
        animalService.getByArrivalDate(arrivalDate);

    return new ResponseEntity<>(
        animals,
        HttpStatus.OK
    );
  }

  @GetMapping(params = "origin")
  public ResponseEntity<List<Animal>> getByOrigin(
      @RequestParam("origin") String origin)
  {
    List<Animal> animals =
        animalService.getByOrigin(origin);

    return new ResponseEntity<>(
        animals,
        HttpStatus.OK
    );
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<String> handleInvalidInformation(
      IllegalArgumentException exception)
  {
    return new ResponseEntity<>(
        exception.getMessage(),
        HttpStatus.BAD_REQUEST
    );
  }

  @ExceptionHandler(NoSuchElementException.class)
  public ResponseEntity<String> handleAnimalNotFound(
      NoSuchElementException exception)
  {
    return new ResponseEntity<>(
        exception.getMessage(),
        HttpStatus.NOT_FOUND
    );
  }

  @ExceptionHandler(IllegalStateException.class)
  public ResponseEntity<String> handleDuplicateAnimal(
      IllegalStateException exception)
  {
    return new ResponseEntity<>(
        exception.getMessage(),
        HttpStatus.CONFLICT
    );
  }
}