package dk.via.pro3.springbootgrpcserverexample.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TraceabilityRepository
{
  private final JdbcTemplate jdbcTemplate;

  public TraceabilityRepository(JdbcTemplate jdbcTemplate)
  {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<String> findAnimalsForProduct(String productId)
  {
    String sql = """
            SELECT DISTINCT ap.animal_registration_number
            FROM animal_part ap
            JOIN product_tray_usage ptu
                ON ap.tray_id = ptu.tray_id
            WHERE ptu.product_id = ?
            ORDER BY ap.animal_registration_number
            """;

    return jdbcTemplate.queryForList(sql, String.class, productId);
  }

  public List<String> findProductsForAnimal(String animalRegistrationNumber)
  {
    String sql = """
            SELECT DISTINCT ptu.product_id
            FROM animal_part ap
            JOIN product_tray_usage ptu
                ON ap.tray_id = ptu.tray_id
            WHERE ap.animal_registration_number = ?
            ORDER BY ptu.product_id
            """;

    return jdbcTemplate.queryForList(
        sql,
        String.class,
        animalRegistrationNumber
    );
  }
}
