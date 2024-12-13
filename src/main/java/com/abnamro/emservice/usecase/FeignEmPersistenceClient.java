package com.abnamro.emservice.usecase;

import com.abnamro.emservice.entities.Employee;
import com.abnamro.emservice.services.empersistence.entities.EmPersistenceCreateUpdateEmployeeRequest;
import com.abnamro.emservice.services.empersistence.entities.EmPersistenceSuccessResponse;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface FeignEmPersistenceClient {


    @RequestLine("GET /api/employees/{employeeId}")
    @Headers({
            "Accept: application/json;charset=UTF-8",
            "Content-Type: application/json"
    })
    Employee getEmployee(@Param("employeeId") Integer employeeId);

    @RequestLine("POST /api/employees")
    @Headers({
            "Accept: application/json;charset=UTF-8",
            "Content-Type: application/json"
    })
    Employee createEmployee(EmPersistenceCreateUpdateEmployeeRequest createUpdateEmployeeRequest);

    @RequestLine("PUT /api/employees/{employeeId}")
    @Headers({
            "Accept: application/json;charset=UTF-8",
            "Content-Type: application/json"
    })
    Employee updateEmployee(@Param("employeeId") Integer employeeId, EmPersistenceCreateUpdateEmployeeRequest createUpdateEmployeeRequest);

    @RequestLine("DELETE /api/employees/{employeeId}")
    @Headers({
            "Accept: application/json;charset=UTF-8",
            "Content-Type: application/json"
    })
    EmPersistenceSuccessResponse deleteEmployee(@Param("employeeId") Integer employeeId);

}
