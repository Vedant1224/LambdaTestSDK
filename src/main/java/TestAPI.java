import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.HashMap;
import java.util.Map;


public class TestAPI extends DependencyProvider {
    private final Credentials credentials;

    public TestAPI(String userName, String password) {
        super();
        credentials = new Credentials(userName, password);
    }

    public String getTestApiUrl() {
        return "automation/api/v1/test";
    }

    public TestResponse uploadExceptionLogs(String testId, List<String> exceptions) throws HTTPException {
        String endpoint = "automation/api/v1/tests/" + testId + "/exceptions";

        Map<String, List<String>> requestBodyMap = new HashMap<>();
        requestBodyMap.put("exception", exceptions);

        String requestBody = gson.toJson(requestBodyMap);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(ApiConstants.BASE_URL + endpoint))
                .method("POST", HttpRequest.BodyPublishers.ofString(requestBody))
                .header(ApiConstants.HEADER_ACCEPT, ApiConstants.APPLICATION_JSON)
                .header(ApiConstants.HEADER_AUTHORIZATION, ApiConstants.BASIC + credentials.generateKey())
                .header(ApiConstants.HEADER_CONTENT_TYPE, ApiConstants.APPLICATION_JSON)
                .build();

        HttpResponse<String> httpResponse = sendRequest(httpRequest);

        if (httpResponse != null) {
            String responseBody = httpResponse.body();
            System.out.println(responseBody);
            return gson.fromJson(responseBody, TestResponse.class);

        }

        throw new HTTPException(ApiConstants.HTTP_EXCEPTION_MESSAGE + httpResponse.statusCode());
    }

    public TestResponse fetchRecordedVideo(String testId) throws HTTPException {

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(ApiConstants.BASE_URL + getTestApiUrl() + "/" + testId + "/" + ApiConstants.HEADER_VIDEO))
                .GET()
                .header(ApiConstants.HEADER_ACCEPT, ApiConstants.APPLICATION_JSON)
                .header(ApiConstants.HEADER_AUTHORIZATION, ApiConstants.BASIC + credentials.generateKey())
                .build();

        HttpResponse<String> httpResponse = sendRequest(httpRequest);

        if (httpResponse != null) {
            String responseBody = httpResponse.body();
            return gson.fromJson(responseBody, TestResponse.class);
        }

        throw new HTTPException(ApiConstants.HTTP_EXCEPTION_MESSAGE + httpResponse.statusCode());
    }
    protected HttpResponse<String> sendRequest(HttpRequest httpRequest) throws HTTPException {
        try {
            return httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            throw new HTTPException(ApiConstants.ERROR_500_DESCRIPTION);
        }
    }
}
