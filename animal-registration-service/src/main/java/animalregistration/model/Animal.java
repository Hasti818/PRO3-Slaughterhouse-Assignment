package animalregistration.model;

import java.time.LocalDate;

public class Animal
{
  private String registrationNumber;
  private LocalDate arrivalDate;
  private double weight;
  private String origin;
  public Animal()
  {
  }
  public Animal(String registrationNumber, LocalDate arrivalDate, double weight,
      String origin)
  {
    this.registrationNumber = registrationNumber;
    this.arrivalDate = arrivalDate;
    this.weight = weight;
    this.origin = origin;
  }

  public String getRegistrationNumber()
  {
    return registrationNumber;
  }

  public LocalDate getArrivalDate()
  {
    return arrivalDate;
  }

  public double getWeight()
  {
    return weight;
  }

  public String getOrigin()
  {
    return origin;
  }

  public void setRegistrationNumber(String registrationNumber)
  {
    this.registrationNumber = registrationNumber;
  }

  public void setArrivalDate(LocalDate arrivalDate)
  {
    this.arrivalDate = arrivalDate;
  }

  public void setWeight(double weight)
  {
    this.weight = weight;
  }

  public void setOrigin(String origin)
  {
    this.origin = origin;
  }
}
