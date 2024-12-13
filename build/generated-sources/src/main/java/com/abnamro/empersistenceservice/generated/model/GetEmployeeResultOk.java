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
 * Return the employee.
 */

@Schema(name = "getEmployee.result.ok", description = "Return the employee.")
@JsonTypeName("getEmployee.result.ok")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-12-13T16:50:30.255967+01:00[Europe/Amsterdam]", comments = "Generator version: 7.10.0")
public class GetEmployeeResultOk {

  private Integer id;

  private Optional<String> firstName = Optional.empty();

  private Optional<String> surname = Optional.empty();

  private Integer roleId;

  public GetEmployeeResultOk() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public GetEmployeeResultOk(Integer id, Integer roleId) {
    this.id = id;
    this.roleId = roleId;
  }

  public GetEmployeeResultOk id(Integer id) {
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

  public GetEmployeeResultOk firstName(String firstName) {
    this.firstName = Optional.of(firstName);
    return this;
  }

  /**
   * Get firstName
   * @return firstName
   */
  
  @Schema(name = "first_name", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("first_name")
  public Optional<String> getFirstName() {
    return firstName;
  }

  public void setFirstName(Optional<String> firstName) {
    this.firstName = firstName;
  }

  public GetEmployeeResultOk surname(String surname) {
    this.surname = Optional.of(surname);
    return this;
  }

  /**
   * Get surname
   * @return surname
   */
  
  @Schema(name = "surname", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("surname")
  public Optional<String> getSurname() {
    return surname;
  }

  public void setSurname(Optional<String> surname) {
    this.surname = surname;
  }

  public GetEmployeeResultOk roleId(Integer roleId) {
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
    GetEmployeeResultOk getEmployeeResultOk = (GetEmployeeResultOk) o;
    return Objects.equals(this.id, getEmployeeResultOk.id) &&
        Objects.equals(this.firstName, getEmployeeResultOk.firstName) &&
        Objects.equals(this.surname, getEmployeeResultOk.surname) &&
        Objects.equals(this.roleId, getEmployeeResultOk.roleId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, surname, roleId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetEmployeeResultOk {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    surname: ").append(toIndentedString(surname)).append("\n");
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

