package com.abnamro.emservice.mapper;

import com.abnamro.empersistenceservice.generated.model.CreateUpdateEmployeeRequest;
import com.abnamro.emservice.services.empersistence.entities.EmPersistenceCreateUpdateEmployeeRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CreateUpdateEmployeeMapper {

    @Mapping(target = "name", expression = "java(createUpdateEmployeeRequest.getFirstName() + \" \" + createUpdateEmployeeRequest.getSurname())")
    EmPersistenceCreateUpdateEmployeeRequest toEmPersistenceCreateUpdateEmployeeRequest(CreateUpdateEmployeeRequest createUpdateEmployeeRequest);

}
