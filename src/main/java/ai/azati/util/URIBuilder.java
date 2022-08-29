package ai.azati.util;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is responsible for creating URI
 * to use in Java HttpClient.
 */
public class URIBuilder {

    private String baseUrl;
    private String path;
    private final Map<String, String> parameters = new HashMap<>();

    public URIBuilder baseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    public URIBuilder path(String path) {
        this.path = path;
        return this;
    }

    public URIBuilder addParameter(String key, String value) {
        parameters.put(key, value);
        return this;
    }

    public URI build() throws URISyntaxException {
        StringBuilder builder = new StringBuilder();
        builder.append(baseUrl);
        builder.append(path);
        builder.append('?');
        for(Map.Entry<String, String> entry : parameters.entrySet()) {
            builder.append(entry.getKey());
            builder.append('=');
            builder.append(entry.getValue());
            builder.append('&');
        }
        String resultUrl = builder.toString();
        resultUrl = resultUrl.substring(0, resultUrl.length() - 1); // without & at the end
        return new URI(resultUrl);
    }
}
