import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SessionAPI extends DependencyProvider {

    private final Credentials credentials;

    public SessionAPI(String userName, String password) {
        super();
        credentials = new Credentials(userName, password);
    }

    public boolean validateValue(int num) {
        if (num < 0) {
            System.out.println(ApiConstants.VALIDATION_EXCEPTION_MESSAGE);
            return false;
        }
        return true;
    }

    public BuildIdResponses fetchAllBuilds(int buildId, String username, int offset, int limit, String status, String fromDate, String toDate, String sort, String tags) throws ValidationException, HTTPException {

        // Validation check on offset and limit parameters
        if (!validateValue(offset) || !validateValue(limit) || !validateValue(buildId) ) {
            throw new ValidationException(ApiConstants.VALIDATION_EXCEPTION_MESSAGE);
        }

        // Validation check on the fromDate and toDate parameters
        LocalDate from = LocalDate.parse(fromDate);
        LocalDate to = LocalDate.parse(toDate);
        long days = ChronoUnit.DAYS.between(from, to);

        if (days > ApiConstants.DATE_DIFFERENCE) {
            throw new ValidationException(ApiConstants.DATE_EXCEPTION_MESSAGE);
        }

        // Build the query parameters string
        String query = "?buildId="+buildId+
                "&username="+username+
                "&offset=" + offset +
                "&limit=" + limit +
                "&status=" + status +
                "&fromdate=" + fromDate +
                "&todate=" + toDate +
                "&sort=" + sort+
                "&tags="+tags;

        // Build the HTTP request for fetching all builds
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(ApiConstants.BASE_URL + ApiConstants.SESSION_ENDPOINT + query))
                .GET()
                .header(ApiConstants.HEADER_ACCEPT, ApiConstants.APPLICATION_JSON)
                .header(ApiConstants.HEADER_AUTHORIZATION, ApiConstants.BASIC + credentials.generateKey())
                .build();

        // Send the request and process the response
        HttpResponse<String> httpResponse = sendRequest(httpRequest);
        if (httpResponse != null) {
            String responseBody = httpResponse.body();

            // Using fromJson() method to convert json to java object
            return gson.fromJson(responseBody, BuildIdResponses.class);
        }
        throw new HTTPException(ApiConstants.HTTP_EXCEPTION_MESSAGE+httpResponse.statusCode());
    }

    /**
     * Works, Just check for customData
     * @param sessionId
     * @return
     * @throws HTTPException
     */
    public SessionResponse getParticularSessionDetails(String sessionId) throws HTTPException {

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


    /**
     * @param sessionId
     * @return
     * @throws HTTPException
     */
    public SessionDeleteResponse deleteSession(String sessionId) throws HTTPException {

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(ApiConstants.BASE_URL + ApiConstants.SESSION_ENDPOINT +"/"+sessionId))
                .DELETE()
                .header(ApiConstants.HEADER_ACCEPT, ApiConstants.APPLICATION_JSON)
                .header(ApiConstants.HEADER_AUTHORIZATION, ApiConstants.BASIC + credentials.generateKey())
                .build();

        HttpResponse<String> httpResponse = sendRequest(httpRequest);

        if (httpResponse != null) {
            String responseBody = httpResponse.body();
            return gson.fromJson(responseBody, SessionDeleteResponse.class);
        }

        throw new HTTPException(ApiConstants.HTTP_EXCEPTION_MESSAGE + httpResponse.statusCode());
    }

    /**
     * Works!
     * @param sessionId
     * @param newName
     * @param status
     * @return
     * @throws ValidationException
     * @throws HTTPException
     */

    public SessionUpdateResponse patchName(String sessionId, String newName, String status) throws ValidationException, HTTPException {

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

            return gson.fromJson(responseBody, SessionUpdateResponse.class);
        }

        throw new HTTPException(ApiConstants.HTTP_EXCEPTION_MESSAGE + httpResponse.statusCode());
    }

    /**
     * Works!
     * @param sessionId
     * @return
     * @throws HTTPException
     */
    public SessionStopResponse stopSession(String sessionId) throws HTTPException {

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(ApiConstants.BASE_URL + ApiConstants.SESSION_ENDPOINT + "/"+ sessionId + "/" + "stop"))
                .PUT(HttpRequest.BodyPublishers.noBody())
                .header(ApiConstants.HEADER_ACCEPT, ApiConstants.APPLICATION_JSON)
                .header(ApiConstants.HEADER_AUTHORIZATION, ApiConstants.BASIC + credentials.generateKey())
                .build();

        HttpResponse<String> httpResponse = sendRequest(httpRequest);

        if (httpResponse != null) {
            String responseBody = httpResponse.body();
            return gson.fromJson(responseBody, SessionStopResponse.class);
        }

        throw new HTTPException(ApiConstants.HTTP_EXCEPTION_MESSAGE + httpResponse.statusCode());
    }


    /**
     * Works
     * @param sessionId
     * @return
     * @throws HTTPException
     */
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

    /**
     * Works
     * @param sessionId
     * @return
     * @throws HTTPException
     */
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

    /**
     * Should Work!
     * @param sessionId
     * @return
     * @throws HTTPException
     */
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

    /**
     * Should Work
     * @param sessionId
     * @return
     * @throws HTTPException
     */

    public SeleniumLogResponse getSeleniumLogs(String sessionId) throws HTTPException {

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(ApiConstants.BASE_URL + ApiConstants.SESSION_ENDPOINT + "/"+ sessionId + "/"+ "log" +"/"+ "selenium"))
                .GET()
                .header(ApiConstants.HEADER_ACCEPT, ApiConstants.APPLICATION_JSON)
                .header(ApiConstants.HEADER_AUTHORIZATION, ApiConstants.BASIC + credentials.generateKey())
                .build();

        HttpResponse<String> httpResponse = sendRequest(httpRequest);

        if (httpResponse != null) {
            String responseBody = httpResponse.body();
            return gson.fromJson(responseBody, SeleniumLogResponse.class);
        }

        throw new HTTPException(ApiConstants.HTTP_EXCEPTION_MESSAGE + httpResponse.statusCode());
    }

    /**
     * Should Work
     * @param sessionId
     * @return
     * @throws HTTPException
     */
    public SeleniumLogResponse getNetworkLogs(String sessionId) throws HTTPException {

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(ApiConstants.BASE_URL + ApiConstants.SESSION_ENDPOINT + "/"+ sessionId + "/"+ "log" +"/"+ "network"))
                .GET()
                .header(ApiConstants.HEADER_ACCEPT, ApiConstants.APPLICATION_JSON)
                .header(ApiConstants.HEADER_AUTHORIZATION, ApiConstants.BASIC + credentials.generateKey())
                .build();

        HttpResponse<String> httpResponse = sendRequest(httpRequest);

        if (httpResponse != null) {
            String responseBody = httpResponse.body();
            return gson.fromJson(responseBody, SeleniumLogResponse.class);
        }

        throw new HTTPException(ApiConstants.HTTP_EXCEPTION_MESSAGE + httpResponse.statusCode());
    }

    /**
     * Should work
     * @param sessionId
     * @return
     * @throws HTTPException
     */
    public SeleniumLogResponse getConsoleLogs(String sessionId) throws HTTPException {

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(ApiConstants.BASE_URL + ApiConstants.SESSION_ENDPOINT + "/"+ sessionId + "/"+ "log" +"/"+ "console"))
                .GET()
                .header(ApiConstants.HEADER_ACCEPT, ApiConstants.APPLICATION_JSON)
                .header(ApiConstants.HEADER_AUTHORIZATION, ApiConstants.BASIC + credentials.generateKey())
                .build();

        HttpResponse<String> httpResponse = sendRequest(httpRequest);

        if (httpResponse != null) {
            String responseBody = httpResponse.body();
            return gson.fromJson(responseBody, SeleniumLogResponse.class);
        }

        throw new HTTPException(ApiConstants.HTTP_EXCEPTION_MESSAGE + httpResponse.statusCode());
    }

    /**
     * Working on it
     * @param sessionId
     * @return
     * @throws HTTPException
     */
    public SeleniumLogResponse getNetworkHarLogs(String sessionId) throws HTTPException {

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(ApiConstants.BASE_URL + ApiConstants.SESSION_ENDPOINT + "/"+ sessionId + "/"+ "log" +"/"+ "network.har"))
                .GET()
                .header(ApiConstants.HEADER_ACCEPT, ApiConstants.APPLICATION_JSON)
                .header(ApiConstants.HEADER_AUTHORIZATION, ApiConstants.BASIC + credentials.generateKey())
                .build();

        HttpResponse<String> httpResponse = sendRequest(httpRequest);

        if (httpResponse != null) {
            String responseBody = httpResponse.body();
            return gson.fromJson(responseBody, SeleniumLogResponse.class);
        }

        throw new HTTPException(ApiConstants.HTTP_EXCEPTION_MESSAGE + httpResponse.statusCode());
    }

    /**
     * @param sessionId
     * @return
     * @throws HTTPException
     */
    public SeleniumLogResponse getFullNetworkHarLogs(String sessionId) throws HTTPException {

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(ApiConstants.BASE_URL + ApiConstants.SESSION_ENDPOINT + "/"+ sessionId + "/"+ "log" +"/"+ "full-har"))
                .GET()
                .header(ApiConstants.HEADER_ACCEPT, ApiConstants.APPLICATION_JSON)
                .header(ApiConstants.HEADER_AUTHORIZATION, ApiConstants.BASIC + credentials.generateKey())
                .build();

        HttpResponse<String> httpResponse = sendRequest(httpRequest);

        if (httpResponse != null) {
            String responseBody = httpResponse.body();
            return gson.fromJson(responseBody, SeleniumLogResponse.class);
        }

        throw new HTTPException(ApiConstants.HTTP_EXCEPTION_MESSAGE + httpResponse.statusCode());
    }

    /**
     * @param testId
     * @param exceptions
     * @return
     * @throws HTTPException
     */
    public SessionUploadResponse uploadAssertionLogs(String sessionId, List<String> exceptions) throws HTTPException {
        Map<String, List<String>> requestBodyMap = new HashMap<>();
        requestBodyMap.put("exception", exceptions);

        String requestBody = gson.toJson(requestBodyMap);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(ApiConstants.BASE_URL + ApiConstants.SESSION_ENDPOINT+"/"+sessionId+"/"+"exceptions"))
                .method("POST", HttpRequest.BodyPublishers.ofString(requestBody))
                .header(ApiConstants.HEADER_ACCEPT, ApiConstants.APPLICATION_JSON)
                .header(ApiConstants.HEADER_AUTHORIZATION, ApiConstants.BASIC + credentials.generateKey())
                .header(ApiConstants.HEADER_CONTENT_TYPE, ApiConstants.APPLICATION_JSON)
                .build();

        HttpResponse<String> httpResponse = sendRequest(httpRequest);

        if (httpResponse != null) {
            String responseBody = httpResponse.body();
            System.out.println(responseBody);
            return gson.fromJson(responseBody, SessionUploadResponse.class);
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
            SessionResponse response = sessionAPI.getParticularSessionDetails(sessionId);

            // Print the output
            System.out.println("Get Session Details Response:");
            System.out.println("Message: " + response.getData());
           // System.out.println("Status: " + response.getStatus());
            System.out.println("Data: " + response.getData().getVideo_url());
        } catch (HTTPException e) {
            e.printStackTrace();
        }
    }

}