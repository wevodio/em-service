package com.abnamro.emservice.controller;

import com.abnamro.empersistenceservice.generated.api.EmployeeManagementServiceApi;
import com.abnamro.empersistenceservice.generated.model.CreateUpdateEmployeeRequest;
import com.abnamro.empersistenceservice.generated.model.GetEmployeeResultOk;
import com.abnamro.empersistenceservice.generated.model.SuccessResponse;
import com.abnamro.emservice.presenter.JsonEmployeePresenter;
import com.abnamro.emservice.presenter.JsonGenericSuccessPresenter;
import com.abnamro.emservice.usecase.ManageEmployeeUseCase;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@AllArgsConstructor
public class EmployeeManagementController implements EmployeeManagementServiceApi {

    private static final String ROLE = "Role";
    private ManageEmployeeUseCase manageEmployeeUseCase;

    //Due to a bug in openapi generator, the header is not present,
    //hence we need the HttpServletRequest to retrieve the header values.
    private HttpServletRequest request;

    @Override
    public ResponseEntity<GetEmployeeResultOk> apiEmployeesPost(CreateUpdateEmployeeRequest createUpdateEmployeeRequest) {
        var role = request.getHeader(ROLE);
        var presenter = new JsonEmployeePresenter();
        manageEmployeeUseCase.createEmployee(role, createUpdateEmployeeRequest, presenter);
        return presenter.toResponseEntity();
    }

    @Override
    public ResponseEntity<GetEmployeeResultOk> apiEmployeesIdGet(Integer id) {
        var presenter = new JsonEmployeePresenter();
        manageEmployeeUseCase.getEmployee(id, presenter);
        return presenter.toResponseEntity();
    }

    @Override
    public ResponseEntity<SuccessResponse> apiEmployeesIdDelete(Integer id) {
        var presenter = new JsonGenericSuccessPresenter();
        manageEmployeeUseCase.removeEmployee(id, presenter);
        return presenter.toResponseEntity();
    }

    @Override
    public ResponseEntity<GetEmployeeResultOk> apiEmployeesIdPut(Integer id, CreateUpdateEmployeeRequest createUpdateEmployeeRequest) {
        var role = request.getHeader(ROLE);
        var presenter = new JsonEmployeePresenter();
        manageEmployeeUseCase.updateEmployee(role, id, createUpdateEmployeeRequest, presenter);
        return presenter.toResponseEntity();
    }
}
