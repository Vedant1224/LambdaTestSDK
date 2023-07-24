import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SessionAPI extends DependencyProvider {

    private final Credentials credentials;

    public SessionAPI(String userName, String password) {
        super();
        credentials = new Credentials(userName, password);
    }

    public SessionResponse getSessionDetails(String sessionId) throws HTTPException {

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(ApiConstants.BASE_URL + ApiConstants.SESSION_ENDPOINT + "/"+ sessionId))
                .GET()
                .header(ApiConstants.HEADER_ACCEPT, ApiConstants.APPLICATION_JSON)
                .header(ApiConstants.HEADER_AUTHORIZATION, ApiConstants.BASIC + credentials.generateKey())
                .build();

        HttpResponse<String> httpResponse = sendRequest(httpRequest);

        if (httpResponse != null) {
            String responseBody = httpResponse.body();
            return gson.fromJson(responseBody, SessionResponse.class);
        }

        throw new HTTPException(ApiConstants.HTTP_EXCEPTION_MESSAGE + httpResponse.statusCode());
    }

    public SessionResponse deleteSession(String sessionId) throws HTTPException {

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(ApiConstants.BASE_URL + ApiConstants.SESSION_ENDPOINT +"/"+sessionId))
                .DELETE()
                .header(ApiConstants.HEADER_ACCEPT, ApiConstants.APPLICATION_JSON)
                .header(ApiConstants.HEADER_AUTHORIZATION, ApiConstants.BASIC + credentials.generateKey())
                .build();

        HttpResponse<String> httpResponse = sendRequest(httpRequest);

        if (httpResponse != null) {
            String responseBody = httpResponse.body();
            return gson.fromJson(responseBody, SessionResponse.class);
        }

        throw new HTTPException(ApiConstants.HTTP_EXCEPTION_MESSAGE + httpResponse.statusCode());
    }

    public SessionResponse patchName(String sessionId, String newName, String status) throws ValidationException, HTTPException {

        String requestBody = "{\"name\":\"" + newName + "\",\"status_ind\":\"" + status.toLowerCase() + "\",\"custom_data\":{}}";

        // Build the HTTP request for updating the session name and status
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(ApiConstants.BASE_URL + ApiConstants.SESSION_ENDPOINT+ "/" + sessionId))
                .method("PATCH", HttpRequest.BodyPublishers.ofString(requestBody))
                .header(ApiConstants.HEADER_ACCEPT, ApiConstants.APPLICATION_JSON)
                .header(ApiConstants.HEADER_AUTHORIZATION, ApiConstants.BASIC + credentials.generateKey())
                .header(ApiConstants.HEADER_CONTENT_TYPE, ApiConstants.APPLICATION_JSON)
                .build();

        // Send the request and process the response
        HttpResponse<String> httpResponse = sendRequest(httpRequest);

        if (httpResponse != null) {
            String responseBody = httpResponse.body();

            return gson.fromJson(responseBody, SessionResponse.class);
        }

        throw new HTTPException(ApiConstants.HTTP_EXCEPTION_MESSAGE + httpResponse.statusCode());
    }

    public SessionResponse stopSession(String sessionId) throws HTTPException {

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(ApiConstants.BASE_URL + ApiConstants.SESSION_ENDPOINT + "/"+ sessionId + "/" + "stop"))
                .PUT(HttpRequest.BodyPublishers.noBody())
                .header(ApiConstants.HEADER_ACCEPT, ApiConstants.APPLICATION_JSON)
                .header(ApiConstants.HEADER_AUTHORIZATION, ApiConstants.BASIC + credentials.generateKey())
                .build();

        HttpResponse<String> httpResponse = sendRequest(httpRequest);

        if (httpResponse != null) {
            String responseBody = httpResponse.body();
            return gson.fromJson(responseBody, SessionResponse.class);
        }

        throw new HTTPException(ApiConstants.HTTP_EXCEPTION_MESSAGE + httpResponse.statusCode());
    }

    public SessionScreenshotResponse getScreenshots(String sessionId) throws HTTPException {

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(ApiConstants.BASE_URL + ApiConstants.SESSION_ENDPOINT + "/"+ sessionId + "/"+ "screenshots"))
                .GET()
                .header(ApiConstants.HEADER_ACCEPT, ApiConstants.APPLICATION_JSON)
                .header(ApiConstants.HEADER_AUTHORIZATION, ApiConstants.BASIC + credentials.generateKey())
                .build();

        HttpResponse<String> httpResponse = sendRequest(httpRequest);

        if (httpResponse != null) {
            String responseBody = httpResponse.body();
            return gson.fromJson(responseBody, SessionScreenshotResponse.class);
        }

        throw new HTTPException(ApiConstants.HTTP_EXCEPTION_MESSAGE + httpResponse.statusCode());
    }

    public SessionVideoResponse getVideo(String sessionId) throws HTTPException {

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(ApiConstants.BASE_URL + ApiConstants.SESSION_ENDPOINT + "/"+ sessionId + "/"+ "video"))
                .GET()
                .header(ApiConstants.HEADER_ACCEPT, ApiConstants.APPLICATION_JSON)
                .header(ApiConstants.HEADER_AUTHORIZATION, ApiConstants.BASIC + credentials.generateKey())
                .build();

        HttpResponse<String> httpResponse = sendRequest(httpRequest);

        if (httpResponse != null) {
            String responseBody = httpResponse.body();
            return gson.fromJson(responseBody, SessionVideoResponse.class);
        }

        throw new HTTPException(ApiConstants.HTTP_EXCEPTION_MESSAGE + httpResponse.statusCode());
    }

    public SessionCommandResponse getCommandLogs(String sessionId) throws HTTPException {

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(ApiConstants.BASE_URL + ApiConstants.SESSION_ENDPOINT + "/"+ sessionId + "/"+ "log" +"/"+ "command"))
                .GET()
                .header(ApiConstants.HEADER_ACCEPT, ApiConstants.APPLICATION_JSON)
                .header(ApiConstants.HEADER_AUTHORIZATION, ApiConstants.BASIC + credentials.generateKey())
                .build();

        HttpResponse<String> httpResponse = sendRequest(httpRequest);

        if (httpResponse != null) {
            String responseBody = httpResponse.body();
            return gson.fromJson(responseBody, SessionCommandResponse.class);
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

    public static void main(String[] args) {
        // Initialize the SessionAPI instance with your credentials
        SessionAPI sessionAPI = new SessionAPI("vedantg", "oqqNJaT8rZL0C3wuMTIlIhujqogN1yh5DB56pCNTSdO5b7bJjH");
        String sessionId = "YAAS1-TG8LA-UZOVZ-2X3CU";

        try {
            // Invoke the getSessionDetails method
            SessionCommandResponse response = sessionAPI.getCommandLogs(sessionId);

            // Print the output
            System.out.println("Get Session Details Response:");
            System.out.println("Message: " + response.getData());
           // System.out.println("Status: " + response.getStatus());
            System.out.println("Data: " + response.getData().getClass());
        } catch (HTTPException e) {
            e.printStackTrace();
        }
    }

}