package com.potato.core.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Builder
@AllArgsConstructor
@Slf4j
public class JpaUtil<V, D, C> {

	public D update(V v, D d, C c ) {
        try {
            ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
            mapper.registerModule(new JavaTimeModule());
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            String jsonSample = mapper.writeValueAsString(v);
            D sample = mapper.readerFor((Class)c).withValueToUpdate(d).readValue(jsonSample);
            return sample;
        }
        catch (Exception e){
            log.error(e.toString());
        }
        return null;
    }

}
