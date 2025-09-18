package br.com.bertanj.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class GenderSerializer extends JsonSerializer<String> {
    @Override
    public void serialize(String gender, JsonGenerator gen, SerializerProvider serializers) throws java.io.IOException {
        String formatGender = "Male".equals(gender) ? "M" : "F";
        gen.writeString(formatGender);
    }
}
