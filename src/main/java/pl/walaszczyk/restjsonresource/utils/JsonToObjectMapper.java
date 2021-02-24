package pl.walaszczyk.restjsonresource.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class JsonToObjectMapper {

    private static final DateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd'T'HH:mm:ssXXX");

    public static <T> List<T> getObjectsFromJsonFile(String jsonResourceLocation, Class<T> elementClass) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(dateFormat);
        CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, elementClass);
        return objectMapper.readValue(new File(jsonResourceLocation), listType);
    }
}
