package com.skptech.soap.service;

import com.skptech.soap.exception.EmployeeNotFoundException;
import com.skptech.soap.repository.EmployeeRepository;
import com.skptech.xml.employee.Employee;
import com.skptech.xml.employee.InsertEmployeeRequest;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.lang.reflect.InvocationTargetException;

@Service
public class EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee getEmployee(Integer id) {
        LOGGER.info("&&&&&&&& EmployeeService->getEmployee &&&&&&&&");
        Employee employee = new Employee();
        try {
            BeanUtils.copyProperties(employee,
                    employeeRepository.findById(id)
                            .orElseThrow(() -> new EmployeeNotFoundException("Employee with id:" + id + " not found"))
            );
        } catch (IllegalAccessException | InvocationTargetException e) {
            LOGGER.error("Error occurred while copying properties from entity to jaxb");
            e.printStackTrace();
        }
        return employee;
    }

    public Employee insertEmployee(InsertEmployeeRequest insertEmployeeRequest) {
        LOGGER.info("&&&&&&&& EmployeeService->saveEmployee &&&&&&&&");
        Employee employee = insertEmployeeRequest.getEmployee();
        com.skptech.soap.model.Employee employeeToInsert = new com.skptech.soap.model.Employee();
        try {
            BeanUtils.copyProperties(employeeToInsert, employee);
            BeanUtils.copyProperties(employee, employeeRepository.save(employeeToInsert));
        } catch (IllegalAccessException | InvocationTargetException e) {
            LOGGER.error("Error occurred while copying properties from entity to jaxb");
            e.printStackTrace();
        }
        return employee;
    }

}
