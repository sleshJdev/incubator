package by.slesh.spring.jdbc.core;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for simple App.
 */
@SpringJUnitConfig
@DataJdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AppTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void testRecordRowMapperWithNoRecordClassThenShouldThrow() {
        class Pojo {

        }
        final var exception = assertThrows(
                IllegalArgumentException.class,
                () -> new RecordRowMapper<>(Pojo.class),
                "Should throw exception if none-record was passed");
        assertEquals(exception.getMessage(),
                "class by.slesh.spring.jdbc.core.AppTest$1Pojo should be a record class");
    }

    @Test
    void testRecordRowMapper() {
        final List<Billionaire> billionaires =
                jdbcTemplate.query(
                        """
                                select * from billionaires
                                order by id
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
                                3,
                                "Folrunsho",
                                "Alakija",
                                "Billionaire Oil Magnate"
                        )
                )
        );
    }
}
