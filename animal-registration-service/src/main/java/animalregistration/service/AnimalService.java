package animalregistration.service;

import animalregistration.model.Animal;
import animalregistration.repository.AnimalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AnimalService
{
  private final AnimalRepository animalRepository;

  public AnimalService(AnimalRepository animalRepository)
  {
    this.animalRepository = animalRepository;
  }

  public Animal registerAnimal(Animal animal)
  {
    validateAnimal(animal);

    boolean alreadyExists = animalRepository
        .findByRegistrationNumber(animal.getRegistrationNumber())
        .isPresent();

    if (alreadyExists)
    {
      throw new IllegalStateException(
          "An animal with registration number "
              + animal.getRegistrationNumber()
              + " already exists"
      );
    }

    return animalRepository.save(animal);
  }

  public Animal getByRegistrationNumber(String registrationNumber)
  {
    if (registrationNumber == null || registrationNumber.isBlank())
    {
      throw new IllegalArgumentException(
          "Registration number cannot be empty"
      );
    }

    return animalRepository
        .findByRegistrationNumber(registrationNumber)
        .orElseThrow(() -> new NoSuchElementException(
            "Animal with registration number "
                + registrationNumber
                + " was not found"
        ));
  }

  public List<Animal> getByArrivalDate(LocalDate arrivalDate)
  {
    if (arrivalDate == null)
    {
      throw new IllegalArgumentException(
          "Arrival date cannot be empty"
      );
    }

    return animalRepository.findByArrivalDate(arrivalDate);
  }

  public List<Animal> getByOrigin(String origin)
  {
    if (origin == null || origin.isBlank())
    {
      throw new IllegalArgumentException(
          "Origin cannot be empty"
      );
    }

    return animalRepository.findByOrigin(origin);
  }

  private void validateAnimal(Animal animal)
  {
    if (animal == null)
    {
      throw new IllegalArgumentException(
          "Animal information is required"
      );
    }

    if (animal.getRegistrationNumber() == null
        || animal.getRegistrationNumber().isBlank())
    {
      throw new IllegalArgumentException(
          "Registration number cannot be empty"
      );
    }

    if (animal.getArrivalDate() == null)
    {
      throw new IllegalArgumentException(
          "Arrival date cannot be empty"
      );
    }

    if (animal.getWeight() <= 0)
    {
      throw new IllegalArgumentException(
          "Weight must be greater than zero"
      );
    }

    if (animal.getOrigin() == null
        || animal.getOrigin().isBlank())
    {
      throw new IllegalArgumentException(
          "Origin cannot be empty"
      );
    }
  }
}