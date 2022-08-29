package ai.azati.util;

import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class URIBuilderTest {

    private static final String testString = "http://example.com/test?secondArg=two&firstArg=1";

    @Test
    void build() throws URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder();

        URI uri = uriBuilder
            .baseUrl("http://example.com")
            .path("/test")
            .addParameter("firstArg", "1")
            .addParameter("secondArg", "two")
            .build();

        assertEquals(new URI(testString), uri);
    }
}