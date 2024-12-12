package com.abnamro.emservice.entities;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Employee {

    private Integer id;

    private String firstName;
    private String surname;

    @SerializedName("name")
    private String fullName;

    private Project project;

    @SerializedName("role_id")
    private Integer roleId;

}