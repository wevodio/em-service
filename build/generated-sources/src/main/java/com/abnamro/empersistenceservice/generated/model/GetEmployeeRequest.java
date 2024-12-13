package com.abnamro.empersistenceservice.generated.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * GetEmployeeRequest
 */

@JsonTypeName("getEmployeeRequest")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-12-13T16:50:30.255967+01:00[Europe/Amsterdam]", comments = "Generator version: 7.10.0")
public class GetEmployeeRequest {

  private Integer id;

  private String name;

  private Integer roleId;

  public GetEmployeeRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public GetEmployeeRequest(Integer id, String name, Integer roleId) {
    this.id = id;
    this.name = name;
    this.roleId = roleId;
  }

  public GetEmployeeRequest id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   */
  @NotNull 
  @Schema(name = "id", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("id")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public GetEmployeeRequest name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   */
  @NotNull 
  @Schema(name = "name", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public GetEmployeeRequest roleId(Integer roleId) {
    this.roleId = roleId;
    return this;
  }

  /**
   * Get roleId
   * @return roleId
   */
  @NotNull 
  @Schema(name = "role_id", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("role_id")
  public Integer getRoleId() {
    return roleId;
  }

  public void setRoleId(Integer roleId) {
    this.roleId = roleId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetEmployeeRequest getEmployeeRequest = (GetEmployeeRequest) o;
    return Objects.equals(this.id, getEmployeeRequest.id) &&
        Objects.equals(this.name, getEmployeeRequest.name) &&
        Objects.equals(this.roleId, getEmployeeRequest.roleId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, roleId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetEmployeeRequest {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    roleId: ").append(toIndentedString(roleId)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

