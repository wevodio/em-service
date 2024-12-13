package com.abnamro.emservice.controller;

import com.abnamro.empersistenceservice.generated.model.CreateUpdateEmployeeRequest;
import com.abnamro.emservice.presenter.EmployeePresenter;
import com.abnamro.emservice.presenter.GenericSuccessPresenter;
import com.abnamro.emservice.usecase.ManageEmployeeUseCase;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static com.abnamro.emservice.utils.TestUtil.constructCreateUpdateEmployeeRequest;
import static com.abnamro.emservice.utils.TestUtil.constructEmployee;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeManagementControllerTest {

    @Mock
    private ManageEmployeeUseCase manageEmployeeUseCase;
    @Mock
    private HttpServletRequest httpServletRequest;
    private EmployeeManagementController controller;

    @BeforeEach
    void setup() {
        controller = new EmployeeManagementController(manageEmployeeUseCase, httpServletRequest);
    }

    @Test
    void apiEmployeesIdGet() {
        doAnswer(invocation -> {
            var presenter = (EmployeePresenter)invocation.getArgument(1);
            presenter.success(constructEmployee());
            return null;
        }).when(manageEmployeeUseCase).getEmployee(anyInt(), any(EmployeePresenter.class));

        var getEmployeeResultOkResponseEntity = controller.apiEmployeesIdGet(1);
        assertThat(getEmployeeResultOkResponseEntity.getStatusCode().value()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void apiEmployeesIdDelete() {
        doAnswer(invocation -> {
            var presenter = (GenericSuccessPresenter)invocation.getArgument(1);
            presenter.success("Employee deleted successfully");
            return null;
        }).when(manageEmployeeUseCase).removeEmployee(anyInt(), any(GenericSuccessPresenter.class));

        var getEmployeeResultOkResponseEntity = controller.apiEmployeesIdDelete(1);
        assertThat(getEmployeeResultOkResponseEntity.getStatusCode().value()).isEqualTo(HttpStatus.OK.value());
    }



    @Test
    void apiEmployeesIdPut() {
        when(httpServletRequest.getHeader("Role")).thenReturn("ADMIN");
        doAnswer(invocation -> {
            var presenter = (EmployeePresenter)invocation.getArgument(3);
            presenter.success(constructEmployee());
            return null;
        }).when(manageEmployeeUseCase).updateEmployee(anyString(), anyInt(), any(CreateUpdateEmployeeRequest.class), any(EmployeePresenter.class));

        var result = controller.apiEmployeesIdPut(1, constructCreateUpdateEmployeeRequest());
        assertThat(result.getStatusCode().value()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void apiEmployeesIdPost() {
        when(httpServletRequest.getHeader("Role")).thenReturn("ADMIN");
        doAnswer(invocation -> {
            var presenter = (EmployeePresenter)invocation.getArgument(2);
            presenter.success(constructEmployee());
            return null;
        }).when(manageEmployeeUseCase).createEmployee(anyString(), any(CreateUpdateEmployeeRequest.class), any(EmployeePresenter.class));

        var result = controller.apiEmployeesPost(constructCreateUpdateEmployeeRequest());
        assertThat(result.getStatusCode().value()).isEqualTo(HttpStatus.OK.value());
    }
}