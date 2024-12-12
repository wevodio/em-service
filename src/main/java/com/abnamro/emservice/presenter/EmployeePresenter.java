package com.abnamro.emservice.presenter;

import com.abnamro.emservice.entities.Employee;

public interface EmployeePresenter {

    void success(Employee employee);

    void failed();

}
