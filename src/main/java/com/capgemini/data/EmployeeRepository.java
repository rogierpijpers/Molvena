package com.capgemini.data;

import com.capgemini.domain.Employee;
import com.capgemini.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeRepository {
    @Autowired
    private PersonRepository personRepository;

    public List<Employee> getAllEmployee(){
        return personRepository.getPersons()
                .stream().filter(x -> x instanceof Employee)
                .map(x-> (Employee) x)
                .collect(Collectors.toList());
    }

    public void addEmployee(Employee employee){
        personRepository.addPerson((Person) employee);
    }
}
