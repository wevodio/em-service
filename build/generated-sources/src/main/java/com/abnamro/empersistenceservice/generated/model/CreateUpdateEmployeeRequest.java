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
 * CreateUpdateEmployeeRequest
 */

@JsonTypeName("createUpdateEmployeeRequest")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-12-13T16:50:30.255967+01:00[Europe/Amsterdam]", comments = "Generator version: 7.10.0")
public class CreateUpdateEmployeeRequest {

  private String firstName;

  private String surname;

  public CreateUpdateEmployeeRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public CreateUpdateEmployeeRequest(String firstName, String surname) {
    this.firstName = firstName;
    this.surname = surname;
  }

  public CreateUpdateEmployeeRequest firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  /**
   * Get firstName
   * @return firstName
   */
  @NotNull 
  @Schema(name = "first_name", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("first_name")
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public CreateUpdateEmployeeRequest surname(String surname) {
    this.surname = surname;
    return this;
  }

  /**
   * Get surname
   * @return surname
   */
  @NotNull 
  @Schema(name = "surname", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("surname")
  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateUpdateEmployeeRequest createUpdateEmployeeRequest = (CreateUpdateEmployeeRequest) o;
    return Objects.equals(this.firstName, createUpdateEmployeeRequest.firstName) &&
        Objects.equals(this.surname, createUpdateEmployeeRequest.surname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, surname);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateUpdateEmployeeRequest {\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    surname: ").append(toIndentedString(surname)).append("\n");
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

