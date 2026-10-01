package dk.via.pro3.springbootgrpcserverexample.service;

import dk.via.pro3.springbootgrpcserverexample.repository.TraceabilityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TraceabilityService
{
  private final TraceabilityRepository repository;

  public TraceabilityService(TraceabilityRepository repository)
  {
    this.repository = repository;
  }

  public List<String> getAnimalsForProduct(String productId)
  {
    return repository.findAnimalsForProduct(productId);
  }

  public List<String> getProductsForAnimal(
      String animalRegistrationNumber)
  {
    return repository.findProductsForAnimal(
        animalRegistrationNumber);
  }
}
