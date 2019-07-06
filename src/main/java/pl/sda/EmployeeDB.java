package pl.sda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeDB {

	private List<Employee> employeeList;

	public EmployeeDB() {
		employeeList = new ArrayList<>();
	}

	public void add(Employee employee) {
		employeeList.add(employee);

	}

	public Iterable<Employee> findAll() {

		return employeeList;
	}

	public Iterable<Employee> find(String name) {
		return employeeList.stream()
			.filter(employee -> employee.getName().equals(name))
			.collect(Collectors.toList());
	}
}
