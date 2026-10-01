package dk.via.pro3.springbootgrpcserverexample.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TraceabilityRepositoryTest
{
  private final TraceabilityRepository repository;

  @Autowired
  public TraceabilityRepositoryTest(
      TraceabilityRepository repository)
  {
    this.repository = repository;
  }

  @Test
  public void shouldFindAnimalsForProduct()
  {
    List<String> actualAnimals =
        repository.findAnimalsForProduct("P-1001");

    List<String> expectedAnimals =
        List.of("A-1001", "A-1002", "A-1003");

    assertEquals(expectedAnimals, actualAnimals);
  }

  @Test
  public void shouldFindProductsForAnimal()
  {
    List<String> actualProducts =
        repository.findProductsForAnimal("A-1002");

    List<String> expectedProducts =
        List.of("P-1001", "P-1002");

    assertEquals(expectedProducts, actualProducts);
  }
}