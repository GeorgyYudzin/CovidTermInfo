package ai.azati.client;

import ai.azati.enums.Status;
import ai.azati.exception.RequestException;
import ai.azati.mapper.StringToResponseMapper;
import ai.azati.property.PropertySingleton;
import ai.azati.response.StateCasesInfo;
import ai.azati.response.StateHistoryInfo;
import ai.azati.response.StateVaccinesInfo;
import ai.azati.util.URIBuilder;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Properties;

/**
 * This class is responsible for making requests to API and returning responses.
 */
@Slf4j
public class CovidInfoClient {

    private static final String casesPath = "/cases";
    private static final String historyPath = "/history";
    private static final String vaccinesPath = "/vaccines";

    private final String baseUrl;

    public CovidInfoClient() {
        Properties properties = PropertySingleton.getProperties();
        baseUrl = properties.getProperty("covid.baseUrl");
    }

    /**
     * Base method to make a request.
     * Checks whether the status code is in 200 - 300 range.
     *
     * @param uri -- URI to make a request
     * @return response from API
     * @throws RequestException if status code is not in range or error occurred
     */
    private static HttpResponse<String> makeRequest(URI uri) throws RequestException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();

        HttpClient client = HttpClient.newHttpClient();

        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception ex) {
            throw new RequestException("Failed to make a request", ex);
        }

        if (response.statusCode() < 200 || response.statusCode() >= 300) {
            throw new RequestException(String.format("Failed to make a request, status code %d",
                    response.statusCode()));
        }

        return response;

    }

    public List<StateCasesInfo> getCases(String countryName) throws RequestException {
        URI uri = null;
        try {
            uri = new URIBuilder()
                    .baseUrl(baseUrl)
                    .path(casesPath)
                    .addParameter("country", countryName)
                    .build();
        } catch (URISyntaxException exception) {
            log.error("Failed to construct uri: ", exception);
        }


        HttpResponse<String> response = makeRequest(uri);

        return StringToResponseMapper.stringToListOfStateCasesInfo(response.body());
    }

    public List<StateHistoryInfo> getHistory(Status status, String countryName) throws RequestException {
        URI uri = null;
        try {
            uri = new URIBuilder()
                    .baseUrl(baseUrl)
                    .path(historyPath)
                    .addParameter("country", countryName)
                    .addParameter("status", status.toString())
                    .build();
        } catch (URISyntaxException exception) {
            log.error("Failed to construct uri: ", exception);
        }

        HttpResponse<String> response = makeRequest(uri);

        return StringToResponseMapper.stringToListOfStateHistoryInfo(response.body());
    }

    public List<StateVaccinesInfo> getVaccinesInfo(String countryName) throws RequestException {
        URI uri = null;
        try {
            uri = new URIBuilder()
                    .baseUrl(baseUrl)
                    .path(vaccinesPath)
                    .addParameter("country", countryName)
                    .build();
        } catch (URISyntaxException exception) {
            log.error("Failed to construct uri: ", exception);
        }

        HttpResponse<String> response = makeRequest(uri);

        return StringToResponseMapper.stringToListOfStateVaccinesInfo(response.body());
    }
}
