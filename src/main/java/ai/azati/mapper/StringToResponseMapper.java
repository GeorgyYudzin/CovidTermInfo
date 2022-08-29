package ai.azati.mapper;

import ai.azati.response.StateCasesInfo;
import ai.azati.response.StateHistoryInfo;
import ai.azati.response.StateVaccinesInfo;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This class maps response from API to Java objects representing responses.
 */
public class StringToResponseMapper {

    private static class LocalDateTimeDeserializer implements JsonDeserializer<LocalDateTime> {
        private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


        @Override
        public LocalDateTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return LocalDateTime.parse(jsonElement.getAsString(), formatter);
        }
    }

    private static class LocalDateTimeDeserializer2 implements JsonDeserializer<LocalDateTime> {
        private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ssx");


        @Override
        public LocalDateTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return LocalDateTime.parse(jsonElement.getAsString(), formatter);
        }
    }

    public static List<StateCasesInfo> stringToListOfStateCasesInfo(String response) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer())
                .create();
        Type type = new TypeToken<Map<String, StateCasesInfo>>() {}.getType();
        Map<String, StateCasesInfo> map = gson.fromJson(response, type);
        List<StateCasesInfo> result = new ArrayList<>();
        for (Map.Entry<String, StateCasesInfo> entry : map.entrySet()) {
            String stateName = entry.getKey();
            StateCasesInfo info = entry.getValue();
            info.setStateName(stateName);
            result.add(info);
        }
        return result;
    }

    public static List<StateHistoryInfo> stringToListOfStateHistoryInfo(String response) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer())
                .create();
        Type type = new TypeToken<Map<String, StateHistoryInfo>>() {}.getType();
        Map<String, StateHistoryInfo> map = gson.fromJson(response, type);
        List<StateHistoryInfo> result = new ArrayList<>();
        for (Map.Entry<String, StateHistoryInfo> entry : map.entrySet()) {
            String stateName = entry.getKey();
            StateHistoryInfo info = entry.getValue();
            info.setStateName(stateName);
            result.add(info);
        }
        return result;
    }

    public static List<StateVaccinesInfo> stringToListOfStateVaccinesInfo(String response) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer2())
                .create();
        Type type = new TypeToken<Map<String, StateVaccinesInfo>>() {}.getType();
        Map<String, StateVaccinesInfo> map = gson.fromJson(response, type);
        List<StateVaccinesInfo> result = new ArrayList<>();
        for (Map.Entry<String, StateVaccinesInfo> entry : map.entrySet()) {
            String stateName = entry.getKey();
            StateVaccinesInfo info = entry.getValue();
            info.setStateName(stateName);
            result.add(info);
        }
        return result;
    }
}
