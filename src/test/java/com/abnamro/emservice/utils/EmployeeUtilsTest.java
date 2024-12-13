package com.abnamro.emservice.utils;

import com.abnamro.emservice.entities.RolesEnum;
import com.abnamro.emservice.exception.ErrorMessageException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeUtilsTest {


    @Test
    void testValidRole(){
        var admin = EmployeeUtils.validateAndRetrieveRoleEnum("ADMIN");
        assertThat(admin).isEqualTo(RolesEnum.ADMIN);
    }

    @Test
    void testInvalidRole(){
        var error = assertThrows(ErrorMessageException.class,
                () -> EmployeeUtils.validateAndRetrieveRoleEnum("DOESNTEXIST"));
        assertThat(error.getErrorMessages().get(0)).isEqualTo("A valid Role needs to be provided in the header");
    }

    @Test
    void testRole(){
        assertTrue(EmployeeUtils.isRoleValid("ADMIN"));
    }

    @Test
    void testRoleNotExist(){
        assertFalse(EmployeeUtils.isRoleValid("ADMINNN"));
    }

}