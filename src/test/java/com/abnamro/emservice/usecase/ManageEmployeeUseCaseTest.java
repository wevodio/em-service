package com.abnamro.emservice.usecase;

import com.abnamro.emservice.exception.ErrorMessageException;
import com.abnamro.emservice.mapper.CreateUpdateEmployeeMapper;
import com.abnamro.emservice.mapper.CreateUpdateEmployeeMapperImpl;
import com.abnamro.emservice.presenter.JsonEmployeePresenter;
import com.abnamro.emservice.presenter.JsonGenericSuccessPresenter;
import com.abnamro.emservice.services.empersistence.entities.EmPersistenceSuccessResponse;
import feign.FeignException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static com.abnamro.emservice.utils.TestUtil.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ManageEmployeeUseCaseTest {

    @Mock
    private FeignEmPersistenceClient feignEmPersistenceClient;

    private ManageEmployeeUseCase manageEmployeeUseCase;

    private CreateUpdateEmployeeMapper createUpdateEmployeeMapper;

    @BeforeEach
    void setup(){
        createUpdateEmployeeMapper = new CreateUpdateEmployeeMapperImpl();
        manageEmployeeUseCase = new ManageEmployeeUseCase(feignEmPersistenceClient, createUpdateEmployeeMapper);
    }

    @Test
    void getEmployeeByIdSuccess() {
        when(feignEmPersistenceClient.getEmployee(1)).thenReturn(constructEmPersistenceEmployee());
        var presenter = new JsonEmployeePresenter();
        manageEmployeeUseCase.getEmployee(1, presenter);
        assertThat(presenter.toResponseEntity().getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void getEmployeeByIdResponseCorrupted() {
        when(feignEmPersistenceClient.getEmployee(1)).thenReturn(null);
        var presenter = new JsonEmployeePresenter();
        var error = assertThrows(ErrorMessageException.class,
                () -> manageEmployeeUseCase.getEmployee(1, presenter));

        assertThat(error.getErrorMessages().get(0)).isEqualTo("The employee requested is corrupted.");
    }

    @Test
    void getEmployeeByIdFailed() {
        when(feignEmPersistenceClient.getEmployee(1)).thenThrow(FeignException.class);
        var presenter = new JsonEmployeePresenter();
        var error = assertThrows(ErrorMessageException.class,
                () -> manageEmployeeUseCase.getEmployee(1, presenter));
        assertThat(error.getErrorMessages().get(0)).isEqualTo("Retrieving employee failed");
    }

    @Test
    void createEmployee() {
        var presenter = new JsonEmployeePresenter();
        when(feignEmPersistenceClient.createEmployee(any())).thenReturn(constructEmPersistenceEmployee());
        manageEmployeeUseCase.createEmployee("ADMIN", constructCreateUpdateEmployeeRequest(), presenter);
        assertThat(presenter.toResponseEntity().getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void createEmployeeFailed() {
        var presenter = new JsonEmployeePresenter();
        var createUpdateEmployeeRequest = constructCreateUpdateEmployeeRequest();
        when(feignEmPersistenceClient.createEmployee(any())).thenThrow(FeignException.class);
        var error = assertThrows(ErrorMessageException.class,
                () -> manageEmployeeUseCase.createEmployee("ADMIN", constructCreateUpdateEmployeeRequest(), presenter));
        assertThat(error.getErrorMessages().get(0)).isEqualTo("Creating employee failed");
    }

    @Test
    void updateEmployee() {
        var createUpdateEmployeeRequest = constructCreateUpdateEmployeeRequest();
        var presenter = new JsonEmployeePresenter();
        when(feignEmPersistenceClient.updateEmployee(anyInt(), any())).thenReturn(constructEmPersistenceEmployee());
        manageEmployeeUseCase.updateEmployee("ADMIN", 1, createUpdateEmployeeRequest, presenter);
        assertThat(presenter.toResponseEntity().getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void updateEmployeeFailed() {
        var createUpdateEmployeeRequest = constructCreateUpdateEmployeeRequest();
        var presenter = new JsonEmployeePresenter();
        when(feignEmPersistenceClient.updateEmployee(anyInt(), any())).thenThrow(FeignException.class);
        var error = assertThrows(ErrorMessageException.class,
                () -> manageEmployeeUseCase.updateEmployee("ADMIN", 1, createUpdateEmployeeRequest, presenter));
        assertThat(error.getErrorMessages().get(0)).isEqualTo("Update employee failed");
    }

    @Test
    void removeEmployee() {
        var presenter = new JsonGenericSuccessPresenter();
        when(feignEmPersistenceClient.deleteEmployee(anyInt())).thenReturn(EmPersistenceSuccessResponse.builder().message("").build());
        manageEmployeeUseCase.removeEmployee(1, presenter);
        assertThat(presenter.toResponseEntity().getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void removeEmployeeFailed() {
        var presenter = new JsonGenericSuccessPresenter();
        when(feignEmPersistenceClient.deleteEmployee(anyInt())).thenThrow(FeignException.class);
        var error = assertThrows(ErrorMessageException.class,
                () -> manageEmployeeUseCase.removeEmployee(1, presenter));
        assertThat(error.getErrorMessages().get(0)).isEqualTo("Removing employee failed");
    }

}