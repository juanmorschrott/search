package io.github.juanmorschrott.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
public class JacksonConfig {

    private static final String DATE_FORMAT = "dd/MM/yyyy";

    @Bean
    public ObjectMapper objectMapper() {
        JavaTimeModule module = new JavaTimeModule();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        module.addSerializer(LocalDate.class, new LocalDateSerializer(formatter));
        module.addDeserializer(LocalDate.class, new LocalDateDeserializer(formatter));

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(module);
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper;
    }
}
