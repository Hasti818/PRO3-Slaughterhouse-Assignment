package animalregistration.repository;

import animalregistration.model.Animal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class AnimalRepositoryTest
{
  @Autowired
  private AnimalRepository animalRepository;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @BeforeEach
  void clearDatabase()
  {
    jdbcTemplate.update("DELETE FROM animal");
  }

  @Test
  void shouldSaveAndFindAnimalByRegistrationNumber()
  {
    Animal animal = new Animal(
        "DK-1001",
        LocalDate.of(2026, 10, 29),
        520.5,
        "Farm A"
    );

    animalRepository.save(animal);

    Optional<Animal> result =
        animalRepository.findByRegistrationNumber("DK-1001");

    assertTrue(result.isPresent());
    assertEquals(
        "DK-1001",
        result.get().getRegistrationNumber()
    );
    assertEquals(
        LocalDate.of(2026, 10, 29),
        result.get().getArrivalDate()
    );
    assertEquals(
        520.5,
        result.get().getWeight(),
        0.001
    );
    assertEquals(
        "Farm A",
        result.get().getOrigin()
    );
  }

  @Test
  void shouldFindAnimalsByArrivalDate()
  {
    Animal firstAnimal = new Animal(
        "DK-1001",
        LocalDate.of(2026, 10, 29),
        520.5,
        "Farm A"
    );

    Animal secondAnimal = new Animal(
        "DK-1002",
        LocalDate.of(2026, 10, 29),
        480.0,
        "Farm B"
    );

    Animal thirdAnimal = new Animal(
        "DK-1003",
        LocalDate.of(2026, 10, 30),
        510.0,
        "Farm C"
    );

    animalRepository.save(firstAnimal);
    animalRepository.save(secondAnimal);
    animalRepository.save(thirdAnimal);

    List<Animal> result =
        animalRepository.findByArrivalDate(
            LocalDate.of(2026, 10, 29)
        );

    assertEquals(2, result.size());

    assertTrue(result.stream().anyMatch(
        animal -> animal.getRegistrationNumber()
            .equals("DK-1001")
    ));

    assertTrue(result.stream().anyMatch(
        animal -> animal.getRegistrationNumber()
            .equals("DK-1002")
    ));
  }

  @Test
  void shouldFindAnimalsByOrigin()
  {
    Animal firstAnimal = new Animal(
        "DK-1001",
        LocalDate.of(2026, 10, 29),
        520.5,
        "Farm A"
    );

    Animal secondAnimal = new Animal(
        "DK-1002",
        LocalDate.of(2026, 10, 30),
        490.0,
        "Farm A"
    );

    Animal thirdAnimal = new Animal(
        "DK-1003",
        LocalDate.of(2026, 10, 30),
        510.0,
        "Farm B"
    );

    animalRepository.save(firstAnimal);
    animalRepository.save(secondAnimal);
    animalRepository.save(thirdAnimal);

    List<Animal> result =
        animalRepository.findByOrigin("Farm A");

    assertEquals(2, result.size());

    assertTrue(result.stream().allMatch(
        animal -> animal.getOrigin().equals("Farm A")
    ));
  }
}