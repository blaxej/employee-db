package pl.sda.employee;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

public class FileEmployeeDb implements EmployeeDb {
    private final Path path;
    private final Charset charset;

    /* ponizszy konstruktor sprawia ze path i charset beda wymagane
    podczas tworzenia obiektu klasy FileEmployeeDb
    dzieki temu ze nie istanieje konstruktor bez tych pol,
    pola finalne musza byc zainicjalizowane podczas tworzenia obiektu klasy */

    public FileEmployeeDb(Path path, Charset charset) {
        this.path = path;
        this.charset = charset;
    }

    @Override
    public long add(Employee employee) {
        //klasa do operowania na plikach
        try {
            long id = Files.readAllLines(path).size();
            Employee employeeCopy = new Employee(id, employee.getName());
            Files.write(path, Arrays.asList(employeeCopy.asLine()), charset);
            /*lapiemy wyjatek IOException i zwracamy nasz wyjatek z wiadomoscia
            co poszlo nie tak. Dodatkowo poprzez zapis "employee" wiemy ktorego
            pracownika nie udalo sie dodac, a "e" daje nam informacje o tym
            co wywolalo wyjatek.
             */
            return id;
        } catch (IOException e) {
            throw new EmployeeDbException(String.format("fail to add new employee %s",
                    employee), e);
        }
    }

    @Override
    public Iterable<Employee> findAll() {
        try {
            return Files.lines(path, charset).map(line -> {
                String[] employeeData = line.split(",");
                Employee employee = new Employee(Long.parseLong(employeeData[0]), employeeData[1]);
                return employee;
            }).collect(Collectors.toList());
        } catch (IOException e) {
            throw new EmployeeDbException("Can not find employees", e);
        }
    }

    @Override
    public Iterable<Employee> findAllByName(String name) {
        return null;
    }

    @Override
    public Optional<Employee> findById(long id) {
        return Optional.empty();
    }
}
