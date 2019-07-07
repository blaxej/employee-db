package pl.sda;

import java.util.Optional;

interface EmployeeDb {

    /**
     * Adds a new employee.
     *
     * @param employee an employee to add
     * @return the generated id
     */
    long add(Employee employee);

    Iterable<Employee> findAll();

    Iterable<Employee> findAllByName(String name);

    Optional<Employee> findById(long id);

}