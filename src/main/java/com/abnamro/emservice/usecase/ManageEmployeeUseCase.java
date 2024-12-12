package com.abnamro.emservice.usecase;

import com.abnamro.empersistenceservice.generated.model.CreateUpdateEmployeeRequest;
import com.abnamro.emservice.entities.Employee;
import com.abnamro.emservice.entities.RolesEnum;
import com.abnamro.emservice.exception.ErrorMessageException;
import com.abnamro.emservice.mapper.CreateUpdateEmployeeMapper;
import com.abnamro.emservice.presenter.EmployeePresenter;
import com.abnamro.emservice.presenter.GenericSuccessPresenter;
import com.abnamro.emservice.services.empersistence.entities.EmPersistenceCreateUpdateEmployeeRequest;
import feign.FeignException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import static com.abnamro.emservice.utils.EmployeeUtils.validateAndRetrieveRoleEnum;

@Service
@AllArgsConstructor
@Slf4j
public class ManageEmployeeUseCase {

    private final FeignEmPersistenceClient feignEmPersistenceClient;
    private final CreateUpdateEmployeeMapper mapper;

    public void getEmployee(String role, Integer employeeId, EmployeePresenter employeePresenter){
        try{
            var employee = feignEmPersistenceClient.getEmployee(employeeId);
            validateResponse(employee);
            setNamesBasedOnFullName(employee);
            employeePresenter.success(employee);
        } catch (FeignException e){
            log.error(e.getMessage(), e);
            throw new ErrorMessageException("Retrieving employee failed");
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void createEmployee(String role, CreateUpdateEmployeeRequest createUpdateEmployeeRequest, EmployeePresenter presenter){
        try {
            var employee = feignEmPersistenceClient.createEmployee(constructEmPersistenceCreateUpdateEmployeeRequest(role, createUpdateEmployeeRequest));
            setNamesBasedOnFullName(employee);
            presenter.success(employee);
        } catch (FeignException e){
            log.error(e.getMessage(), e);
            throw new ErrorMessageException("Creating employee failed");
        }
    }

    private void setNamesBasedOnFullName(Employee employee) {
        var names = employee.getFullName().split(" ");
        employee.setFirstName(names[0]);
        employee.setSurname(names[1]);
    }

    public void updateEmployee(String role, Integer employeeId, CreateUpdateEmployeeRequest createUpdateEmployeeRequest, EmployeePresenter presenter){
        try{
            var employee = feignEmPersistenceClient.updateEmployee(employeeId, constructEmPersistenceCreateUpdateEmployeeRequest(role, createUpdateEmployeeRequest));
            setNamesBasedOnFullName(employee);
            presenter.success(employee);
        } catch (FeignException e){
            log.error(e.getMessage(), e);
            throw new ErrorMessageException("Creating employee failed");
        }

    }

    @NotNull
    private EmPersistenceCreateUpdateEmployeeRequest constructEmPersistenceCreateUpdateEmployeeRequest(String role, CreateUpdateEmployeeRequest createUpdateEmployeeRequest) {
        var roleEnum = validateAndRetrieveRoleEnum(role);
        var emPersistenceCreateUpdateEmployeeRequest = mapper.toEmPersistenceCreateUpdateEmployeeRequest(createUpdateEmployeeRequest);
        emPersistenceCreateUpdateEmployeeRequest.setRoleId(roleEnum.getId());
        return emPersistenceCreateUpdateEmployeeRequest;
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void removeEmployee(String role, Integer employeeId, GenericSuccessPresenter presenter){
        try {
            feignEmPersistenceClient.deleteEmployee(employeeId);
            presenter.success("Employee deleted successfully");
        } catch (FeignException e){
            log.error(e.getMessage(), e);
            throw new ErrorMessageException("Removing employee failed");
        }

    }

    private void validateResponse(Employee employee){
        if(StringUtils.isBlank(employee.getFullName())
                || !employee.getFullName().contains(" ")
                || employee.getFullName().split(" ").length != 2){
            throw new ErrorMessageException("The employee requested is corrupted.");
        }
    }





}
