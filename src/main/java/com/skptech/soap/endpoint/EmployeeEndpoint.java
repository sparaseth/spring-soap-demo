package com.skptech.soap.endpoint;

import com.skptech.soap.service.EmployeeService;
import com.skptech.xml.employee.GetEmployeeRequest;
import com.skptech.xml.employee.GetEmployeeResponse;
import com.skptech.xml.employee.InsertEmployeeRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class EmployeeEndpoint {

    private static final String NAMESPACE_URL = "http://www.skptech.com/xml/employee";
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeEndpoint.class);

    private final EmployeeService employeeService;

    public EmployeeEndpoint(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PayloadRoot(namespace = NAMESPACE_URL, localPart = "GetEmployeeRequest")
    @ResponsePayload
    public GetEmployeeResponse getEmployee(@RequestPayload GetEmployeeRequest getEmployeeRequest) {
        LOGGER.info("&&&&&&&& EmployeeEndpoint->getEmployeeResponse &&&&&&&&");
        GetEmployeeResponse getEmployeeResponse = new GetEmployeeResponse();
        getEmployeeResponse.setEmployee(employeeService.getEmployee(getEmployeeRequest.getId()));
        return getEmployeeResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URL, localPart = "InsertEmployeeRequest")
    @ResponsePayload
    public GetEmployeeResponse insertEmployee(@RequestPayload InsertEmployeeRequest insertEmployeeRequest) {
        LOGGER.info("&&&&&&&& EmployeeEndpoint->insertEmployee &&&&&&&&");
        GetEmployeeResponse getEmployeeResponse = new GetEmployeeResponse();
        getEmployeeResponse.setEmployee(employeeService.insertEmployee(insertEmployeeRequest));
        return getEmployeeResponse;
    }

}
