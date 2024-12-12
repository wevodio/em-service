package com.abnamro.emservice.utils;

import com.abnamro.emservice.entities.RolesEnum;
import com.abnamro.emservice.exception.ErrorMessageException;

import java.util.Arrays;

public class EmployeeUtils {

    public static RolesEnum validateAndRetrieveRoleEnum(String role){
        return Arrays.stream(RolesEnum.values()).
                filter(rolesEnum -> rolesEnum.toString().equalsIgnoreCase(role))
                .findFirst()
                .orElseThrow(() -> new ErrorMessageException("A valid Role needs to be provided in the header"));
    }

    public static boolean isRoleValid(String role){
        return Arrays.stream(RolesEnum.values()).
                anyMatch(rolesEnum -> rolesEnum.toString().equalsIgnoreCase(role));

    }
}
