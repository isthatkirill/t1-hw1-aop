package isthatkirill.hwoneaop.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

/**
 * @author Kirill Emelyanov
 */

@Converter
@RequiredArgsConstructor
public class ArgsConverter implements AttributeConverter<Object, String> {

    private final ObjectMapper mapper;

    @Override
    @SneakyThrows
    public String convertToDatabaseColumn(Object o) {
        return mapper.writeValueAsString(o);
    }

    @Override
    public Object convertToEntityAttribute(String s) {
        return mapper.convertValue(s, Object.class);
    }

}