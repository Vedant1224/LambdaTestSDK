/** Importing all dependencies*/
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BuildAPI extends DependencyProvider {

    /*Field to store the data */
    private final Credentials credentials;

    /**
     * Constructs a new BuildAPI instance with the provided userName and password.
     *
     * @param userName userName credential
     * @param password password credential
     */
    public BuildAPI(String userName, String password) {
        super();
        // Create a new instance of Credentials with the provided userName and password
        credentials = new Credentials(userName, password);
    }

    /**
     * Sends an HTTP request and returns the response.
     *
     * @param httpRequest HTTP request to be sent
     * @return response
     */
    protected HttpResponse<String> sendRequest(HttpRequest httpRequest) throws HTTPException {
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
        throw new HTTPException(ApiConstants.HTTP_EXCEPTION_MESSAGE + httpResponse.statusCode());
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
        throw new HTTPException(ApiConstants.HTTP_EXCEPTION_MESSAGE + httpResponse.statusCode());
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
        throw new HTTPException(ApiConstants.HTTP_EXCEPTION_MESSAGE + httpResponse.statusCode());
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
        throw new HTTPException(ApiConstants.HTTP_EXCEPTION_MESSAGE + httpResponse.statusCode());
    }

    /**
     * Main method to demonstrate how to use the LambdaTest SDK to fetch and display
     * information about a specific build from LambdaTest's automation API.
     */
    public static void main(String[] args) {
        // Replace the empty strings with your actual LambdaTest username and password
        String userName = "your_username_here";
        String password = "your_password_here";

        // Create an instance of the BuildAPI class with the provided credentials
        BuildAPI buildAPI = new BuildAPI(userName, password);
        try {
            // Replace the buildId with the ID of the specific build you want to retrieve details for
            int buildId = 16648943;

            // Call the getSpecificBuildData() method to fetch information about the specific build
            BuildIdResponse buildResponse = buildAPI.getSpecificBuildData(buildId);

            // Access the Response object from the buildResponse to retrieve build details
            Response buildData = buildResponse.getData();

            // Print the retrieved build information
            System.out.println("Build ID: " + buildData.getBuildId());
            System.out.println("Build Name: " + buildData.getBuildName());
            System.out.println("User ID: " + buildData.getUserId());
            System.out.println("Username: " + buildData.getUserName());
            System.out.println("Status ID: " + buildData.getStatusId());
            System.out.println("Create Timestamp: " + buildData.getCreateTimestamp());
            System.out.println("End Timestamp: " + buildData.getEndTimestamp());
            System.out.println("Tags: " + buildData.getTags());

        } catch (ValidationException | HTTPException e) {
            // Handle any validation or HTTP exception that may occur during the API call
            e.printStackTrace();
        }
    }
}
