/** Importing all dependencies*/
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import com.google.gson.Gson;



public class BuildAPI {

    /*Fields to store the data */
    private final HttpClient httpClient;
    private final Credentials credentials;
    private final Gson gson;

    /**
     * Constructs a new BuildAPI instance with the provided userName and password.
     *
     * @param userName userName credential
     * @param password password credential
     */
    public BuildAPI(String userName, String password) {
        // Create a new instance of HttpClient
        httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(ApiConstants.REQUEST_TIMEOUT))
                .build();

        // Create a new instance of Credentials with the provided userName and password
        credentials = new Credentials(userName, password);
        gson = new Gson();
    }

    /**
     * Sends an HTTP request and returns the response.
     *
     * @param httpRequest HTTP request to be sent
     * @return response
     */
    private HttpResponse<String> sendRequest(HttpRequest httpRequest) throws HTTPException {
        try {
            // Send the HTTP request using HttpClient and return the response
            return httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            throw new HTTPException(ApiConstants.ERROR_500_DESCRIPTION);
        }
    }

    /**
     * Validation check for valid parameter
     *
     * @param num the integer value to validate
     * @return true if the value is greater than zero
     */
    public boolean validateValue(int num) {
        if (num < 0) {
            System.out.println(ApiConstants.VALIDATION_EXCEPTION_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * Returns the base URL of the LambdaTest build API.
     *
     * @return URL
     */
    public String getBuildApiUrl() {
        return "automation/api/v1/builds";
    }

    /**
     * Fetches all builds based on the specified parameters.
     *
     * @param offset   the offset value
     * @param limit    maximum number of builds to fetch
     * @param status   build status
     * @param fromDate start date for filtering builds
     * @param toDate   end date for filtering builds
     * @param sort     sorting criteria
     * @return response with the list of builds
     * @throws ValidationException if validation of parameters fails
     * @throws HTTPException       if HTTP request fails
     */
    public BuildIdResponses fetchAllBuilds(int offset, int limit, String status, String fromDate, String toDate, String sort) throws ValidationException, HTTPException {

        // Validation check on offset and limit parameters
        if (!validateValue(offset) || !validateValue(limit)) {
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
        String query = "?offset=" + offset +
                "&limit=" + limit +
                "&status=" + status +
                "&fromdate=" + fromDate +
                "&todate=" + toDate +
                "&sort=" + sort;

        // Build the HTTP request for fetching all builds
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(ApiConstants.BASE_URL + getBuildApiUrl() + query))
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
     * Returns specific build data based on build ID.
     *
     * @param buildId the ID of the build
     * @return response with the specific build data
     * @throws ValidationException if validation of the build ID fails
     * @throws HTTPException       if HTTP request fails
     */
    public BuildIdResponse getSpecificBuildData(int buildId) throws ValidationException, HTTPException {

        if (!validateValue(buildId)) {
            throw new ValidationException(ApiConstants.VALIDATION_EXCEPTION_MESSAGE);
        }

        // Build the HTTP request for retrieving specific build data
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(ApiConstants.BASE_URL + getBuildApiUrl() + "/" + buildId))
                .GET()
                .header(ApiConstants.HEADER_ACCEPT, ApiConstants.APPLICATION_JSON)
                .header(ApiConstants.HEADER_AUTHORIZATION, ApiConstants.BASIC + credentials.generateKey())
                .build();

        // Send the request and process the response
        HttpResponse<String> httpResponse = sendRequest(httpRequest);
        if (httpResponse != null) {
            String responseBody = httpResponse.body();

            return gson.fromJson(responseBody, BuildIdResponse.class);
        }
        throw new HTTPException(ApiConstants.HTTP_EXCEPTION_MESSAGE+httpResponse.statusCode());
    }

    /**
     * Deletes a build with the respective build ID.
     *
     * @param buildId ID of the build to delete
     * @return response indicating success or failure
     * @throws ValidationException if validation of the build ID fails
     * @throws HTTPException       if HTTP request fails
     */
    public BuildIdResponse deleteBuild(int buildId) throws ValidationException, HTTPException {

        if (!validateValue(buildId)) {
            throw new ValidationException(ApiConstants.VALIDATION_EXCEPTION_MESSAGE);
        }

        // Build the HTTP request for deleting a build
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(ApiConstants.BASE_URL + getBuildApiUrl() + "/" + buildId))
                .DELETE()
                .header(ApiConstants.HEADER_ACCEPT, ApiConstants.APPLICATION_JSON)
                .header(ApiConstants.HEADER_AUTHORIZATION, ApiConstants.BASIC + credentials.generateKey())
                .build();

        // Send the request and process the response
        HttpResponse<String> httpResponse = sendRequest(httpRequest);
        if (httpResponse != null) {
            String responseBody = httpResponse.body();

            return gson.fromJson(responseBody, BuildIdResponse.class);
        }
        throw new HTTPException(ApiConstants.HTTP_EXCEPTION_MESSAGE+httpResponse.statusCode());
    }

    /**
     * Updates the name and/or status of a build with the specified build ID.
     *
     * @param buildId the ID of the build to update
     * @param newName the new name for the build
     * @param status  the new status for the build
     * @return response indicating the success or failure of the update
     * @throws ValidationException if validation of the build ID fails
     * @throws HTTPException       if HTTP request fails
     */
    public BuildIdResponse patchName(int buildId, String newName, String status) throws ValidationException, HTTPException {
        if (!validateValue(buildId)) {
            throw new ValidationException(ApiConstants.VALIDATION_EXCEPTION_MESSAGE);
        }

        String requestBody = "{\"name\":\"" + newName + "\",\"status\":\"" + status.toLowerCase() + "\"}";
        // Build the HTTP request for updating the name of the build
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(ApiConstants.BASE_URL + getBuildApiUrl() + "/" + buildId))
                .method("PATCH", HttpRequest.BodyPublishers.ofString(requestBody))
                .header(ApiConstants.HEADER_ACCEPT, ApiConstants.APPLICATION_JSON)
                .header(ApiConstants.HEADER_AUTHORIZATION, ApiConstants.BASIC + credentials.generateKey())
                .header(ApiConstants.HEADER_CONTENT_TYPE, ApiConstants.APPLICATION_JSON)
                .build();

        // Send the request and process the response
        HttpResponse<String> httpResponse = sendRequest(httpRequest);
        if (httpResponse != null) {
            String responseBody = httpResponse.body();

            return gson.fromJson(responseBody, BuildIdResponse.class);
        }
        throw new HTTPException(ApiConstants.HTTP_EXCEPTION_MESSAGE+httpResponse.statusCode());
    }

    public static void main(String[] args){
        BuildAPI buildAPI = new BuildAPI("vedantg", "oqqNJaT8rZL0C3wuMTIlIhujqogN1yh5DB56pCNTSdO5b7bJjH");
        System.out.println(buildAPI);
    }
}
