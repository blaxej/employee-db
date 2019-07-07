package pl.sda;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryEmployeeDB implements EmployeeDb {

    private List<Employee> employeeList;

    public InMemoryEmployeeDB() {
        employeeList = new ArrayList<>();
    }

    public long add(Employee employee) {
        long id = generateId();
        Employee employeeCopy = new Employee(id, employee.getName());
        employeeList.add(employeeCopy);
        return id;
    }


    public Iterable<Employee> findAll() {

        return employeeList;
    }

    public Iterable<Employee> findAllByName(String name) {
        return employeeList.stream()
                .filter(employee -> employee.getName().equals(name))
                .collect(Collectors.toList());
    }

    private long generateId() {
        return employeeList.size() + 1;
    }

    public Optional<Employee> findById(long employeeId) {
        return employeeList.stream().filter(employee -> employee.getId() == employeeId).findAny();
    }
}