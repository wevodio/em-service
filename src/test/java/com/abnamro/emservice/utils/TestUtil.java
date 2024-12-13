package com.abnamro.emservice.utils;


import com.abnamro.empersistenceservice.generated.model.CreateUpdateEmployeeRequest;
import com.abnamro.emservice.entities.Employee;
import com.abnamro.emservice.services.empersistence.entities.EmPersistenceCreateUpdateEmployeeRequest;

public class TestUtil {

    public static CreateUpdateEmployeeRequest constructCreateUpdateEmployeeRequest(){
        var createUpdateEmployeeRequest = new CreateUpdateEmployeeRequest();
        createUpdateEmployeeRequest.setFirstName("John");
        createUpdateEmployeeRequest.setSurname("Doe");
        return createUpdateEmployeeRequest;
    }

    public static Employee constructEmPersistenceEmployee() {
        return Employee.builder()
                .fullName("John Doe")
                .id(1)
                .roleId(1)
                .build();
    }

    public static Employee constructEmployee() {
        return Employee.builder()
                .firstName("John")
                .surname("Doe")
                .id(1)
                .roleId(1)
                .build();
    }

    public static EmPersistenceCreateUpdateEmployeeRequest constructEmPersistenceCreateUpdateEmployeeRequest(){
        return EmPersistenceCreateUpdateEmployeeRequest.builder()
                .name("John Doe")
                .roleId(1)
                .build();
    }

}
