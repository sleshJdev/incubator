package by.slesh.spring.jdbc.core;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

/**
 * Unit test for simple App.
 */
@SpringJUnitConfig
@DataJdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AppTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testRecordRowMapper() {
        final List<Billionaire> billionaires =
                jdbcTemplate.query(
                        """
                                select * from billionaires
                                """,
                        new RecordRowMapper<>(Billionaire.class)
                );

        assertIterableEquals(
                billionaires,
                List.of(
                        new Billionaire(
                                1,
                                "Aliko",
                                "Dangote",
                                "Billionaire Industrialist"
                        ),
                        new Billionaire(
                                2,
                                "Bill",
                                "Gates",
                                "Billionaire Tech Entrepreneur"
                        ),
                        new Billionaire(
                                1,
                                "Folrunsho",
                                "Alakija",
                                "Billionaire Oil Magnate"
                        )
                )
        );
    }
}
