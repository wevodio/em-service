package com.abnamro.emservice.services.empersistence.entities;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmPersistenceCreateUpdateEmployeeRequest {

    private String name;

    @SerializedName("role_id")
    private Integer roleId;


}
