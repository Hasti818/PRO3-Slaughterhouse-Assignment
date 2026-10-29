package animalregistration.repository;
import animalregistration.model.Animal;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Repository
public class AnimalRepository
{
  private final JdbcTemplate jdbcTemplate;
  private final RowMapper<Animal> animalRowMapper = (resultSet, rowNumber) ->
      new Animal(
          resultSet.getString("registration_number"),
          resultSet.getDate("arrival_date").toLocalDate(),
          resultSet.getDouble("weight"),
          resultSet.getString("origin")
      );
  public AnimalRepository (JdbcTemplate jdbcTemplate)
  {
    this.jdbcTemplate=jdbcTemplate;
  }
  public Animal save(Animal animal)
  {
    String sql = """
        INSERT INTO animal
        (registration_number, arrival_date, weight, origin)
        VALUES (?, ?, ?, ?)
        """;
    jdbcTemplate.update(
        sql,
        animal.getRegistrationNumber(),
        Date.valueOf(animal.getArrivalDate()),
        animal.getWeight(),
        animal.getOrigin()
    );
    return animal;
  }
  public Optional<Animal> findByRegistrationNumber(String registrationNumber)
  {
    String sql = """
        SELECT registration_number, arrival_date, weight, origin
        FROM animal
        WHERE registration_number = ?
        """;

    List<Animal> animals = jdbcTemplate.query(
        sql,
        animalRowMapper,
        registrationNumber
    );

    return animals.stream().findFirst();
  }
  public List<Animal> findByArrivalDate(LocalDate arrivalDate)
  {
    String sql = """
        SELECT registration_number, arrival_date, weight, origin
        FROM animal
        WHERE arrival_date = ?
        """;

    return jdbcTemplate.query(
        sql,
        animalRowMapper,
        Date.valueOf(arrivalDate)
    );
  }
  public List<Animal> findByOrigin(String origin)
  {
    String sql = """
        SELECT registration_number, arrival_date, weight, origin
        FROM animal
        WHERE origin = ?
        """;

    return jdbcTemplate.query(
        sql,
        animalRowMapper,
        origin
    );
  }
}

