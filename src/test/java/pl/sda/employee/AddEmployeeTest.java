package pl.sda.employee;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.sda.DbTestUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

import static org.assertj.core.api.Assertions.assertThat;

public class AddEmployeeTest {
    // @formatter:off
	@DisplayName(
		"Given user Wojtek " +
		"when add Wojtek to database" +
		"then Wojtek can be found in the list of all users"
	)
	// @formatter:on
    @Test
    void add() throws Exception {
        //given
        EmployeeDb employeeDB = emptyDb();
        Employee wojtek = new Employee("Wojtek");

        //when
        employeeDB.add(wojtek);

        //then
        Iterable<Employee> allEmployees = employeeDB.findAll();
        assertThat(allEmployees).hasSize(1);
        assertThat(allEmployees.iterator().next().getName()).isEqualTo("Wojtek");

    }

    @DisplayName(
            "Given user Wojtek and Kamila" +
                    "When add Wojtek and Kamila to database" +
                    "then Wojtek and Kamila can be found in the list of all users "
    )
    @Test
    void addMany() throws Exception {
        //given
        EmployeeDb employeeDb = emptyDb();

        //when
        employeeDb.add(employeeWithName("Kamila"));
        employeeDb.add(employeeWithName("Wojtek"));

        //then
        assertThat(employeeDb.findAll()).hasSize(2);
    }

    private Employee employeeWithName(String name) {
        return new Employee(name);
    }

    private EmployeeDb emptyDb() throws IOException {
        //w zaleznosci od tego co ponizej jest w komentarzu,
        //metoda testowa sprawdza dana implementacje bazy albo w pamieci, albo w pliku.
        //return DbTestUtils.emptyInMemoryEmployeeDb();
        return DbTestUtils.emptyFileEmployeeDb();
    }
}
