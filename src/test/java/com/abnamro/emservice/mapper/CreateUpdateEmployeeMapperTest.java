package com.abnamro.emservice.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.abnamro.emservice.utils.TestUtil.constructCreateUpdateEmployeeRequest;
import static org.assertj.core.api.Assertions.assertThat;

class CreateUpdateEmployeeMapperTest {

    private CreateUpdateEmployeeMapper createUpdateEmployeeMapper;

    @BeforeEach
    void init(){
        createUpdateEmployeeMapper = new CreateUpdateEmployeeMapperImpl();
    }

    @Test
    void testEmployeeMapper(){
        var emPersistenceCreateUpdateEmployeeRequest = createUpdateEmployeeMapper.toEmPersistenceCreateUpdateEmployeeRequest(constructCreateUpdateEmployeeRequest());
        assertThat(emPersistenceCreateUpdateEmployeeRequest.getName()).isEqualTo("John Doe");
    }
}