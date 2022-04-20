package com.example.musify.dto.userdto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserViewDTOTest {
    @Test
    public void givenValidUserViewDTO_whenSerializingAndDeserializing_thenResultToSame() throws JsonProcessingException {
        UserViewDTO userViewDTO = new UserViewDTO(1, "Ana pop", "anap@yahoo.com", false);

        ObjectMapper objectMapper = new ObjectMapper();
        String valueAsString = objectMapper.writeValueAsString(userViewDTO);
        UserViewDTO userViewDTODeserialized = objectMapper.readValue(valueAsString, UserViewDTO.class);

        assertEquals(userViewDTO, userViewDTODeserialized);
    }

}