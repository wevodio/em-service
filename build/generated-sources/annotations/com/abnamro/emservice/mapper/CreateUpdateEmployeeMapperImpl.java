package com.abnamro.emservice.mapper;

import com.abnamro.empersistenceservice.generated.model.CreateUpdateEmployeeRequest;
import com.abnamro.emservice.services.empersistence.entities.EmPersistenceCreateUpdateEmployeeRequest;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-13T16:50:32+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.3 (Homebrew)"
)
@Component
public class CreateUpdateEmployeeMapperImpl implements CreateUpdateEmployeeMapper {

    @Override
    public EmPersistenceCreateUpdateEmployeeRequest toEmPersistenceCreateUpdateEmployeeRequest(CreateUpdateEmployeeRequest createUpdateEmployeeRequest) {
        if ( createUpdateEmployeeRequest == null ) {
            return null;
        }

        EmPersistenceCreateUpdateEmployeeRequest.EmPersistenceCreateUpdateEmployeeRequestBuilder emPersistenceCreateUpdateEmployeeRequest = EmPersistenceCreateUpdateEmployeeRequest.builder();

        emPersistenceCreateUpdateEmployeeRequest.name( createUpdateEmployeeRequest.getFirstName() + " " + createUpdateEmployeeRequest.getSurname() );

        return emPersistenceCreateUpdateEmployeeRequest.build();
    }
}
