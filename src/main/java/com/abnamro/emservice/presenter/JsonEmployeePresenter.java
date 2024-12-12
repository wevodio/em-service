package com.abnamro.emservice.presenter;

import com.abnamro.empersistenceservice.generated.model.GetEmployeeResultOk;
import com.abnamro.emservice.entities.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public class JsonEmployeePresenter implements EmployeePresenter {

    private ResponseEntity<GetEmployeeResultOk> responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .build();

    @Override
    public void success(Employee employee) {
        responseEntity = ResponseEntity.ok(constructGetEmployeeResultOk(employee));
    }

    @Override
    public void failed() {
        responseEntity = ResponseEntity.badRequest().build();
    }

    public ResponseEntity<GetEmployeeResultOk> toResponseEntity() {
        return responseEntity;
    }

    private GetEmployeeResultOk constructGetEmployeeResultOk(Employee employee) {
        var getEmployeeResultOk = new GetEmployeeResultOk();
        getEmployeeResultOk.setId(employee.getId());
        getEmployeeResultOk.setFirstName(Optional.of(employee.getFirstName()));
        getEmployeeResultOk.setSurname(Optional.of(employee.getSurname()));

        getEmployeeResultOk.setRoleId(employee.getRoleId());
        return getEmployeeResultOk;
    }
}
