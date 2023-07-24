import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ProjectAPI extends DependencyProvider {

    private final Credentials credentials;

    public ProjectAPI(String userName, String password) {
        super();
        // Create a new instance of Credentials with the provided userName and password
        credentials = new Credentials(userName, password);
    }
    public boolean validateValue(int num) {
        if (num < 0) {
            System.out.println(ApiConstants.VALIDATION_EXCEPTION_MESSAGE);
            return false;
        }
        return true;
    }

    public ProjectResponse fetchAllProjects(int offset, int limit, String fromDate, String toDate) throws Exception {

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
                "&fromdate=" + fromDate +
                "&todate=" + toDate ;

        // Build the HTTP request for fetching all builds
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(ApiConstants.BASE_URL + ApiConstants.PROJECT_ENDPOINT+query))
                .GET()
                .header(ApiConstants.HEADER_ACCEPT, ApiConstants.APPLICATION_JSON)
                .header(ApiConstants.HEADER_AUTHORIZATION, ApiConstants.BASIC + credentials.generateKey())
                .build();

        // Send the request and process the response
        HttpResponse<String> httpResponse = sendRequest(httpRequest);
        if (httpResponse != null) {
            String responseBody = httpResponse.body();

            // Using fromJson() method to convert json to java object
            return gson.fromJson(responseBody, ProjectResponse.class);
        }
        throw new HTTPException(ApiConstants.HTTP_EXCEPTION_MESSAGE+httpResponse.statusCode());
    }

    public CreateProjectResponse createProject(String id, String name) throws HTTPException {
        String requestBody = "{\"id\":\"" + id + "\",\"name\":\"" + name + "\"}";

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(ApiConstants.BASE_URL + ApiConstants.PROJECT_ENDPOINT))
                .method("POST", HttpRequest.BodyPublishers.ofString(requestBody))
                .header(ApiConstants.HEADER_ACCEPT, ApiConstants.APPLICATION_JSON)
                .header(ApiConstants.HEADER_AUTHORIZATION, ApiConstants.BASIC + credentials.generateKey())
                .header(ApiConstants.HEADER_CONTENT_TYPE, ApiConstants.APPLICATION_JSON)
                .build();

        HttpResponse<String> httpResponse = sendRequest(httpRequest);

        if (httpResponse != null) {
            String responseBody = httpResponse.body();
            System.out.println(responseBody);
            return gson.fromJson(responseBody, CreateProjectResponse.class);

        }

        throw new HTTPException(ApiConstants.HTTP_EXCEPTION_MESSAGE + httpResponse.statusCode());
    }

    public ParticularProjectResponse fetchParticularProjects(String id, int offset, int limit, String fromDate, String toDate, String sort) throws Exception {

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
        String query = "?id=" +id+
                "&offset=" + offset +
                "&limit=" + limit +
                "&fromdate=" + fromDate +
                "&todate=" + toDate +
                "&sort=" + sort;

        // Build the HTTP request for fetching all builds
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(ApiConstants.BASE_URL + ApiConstants.PROJECT_ENDPOINT+query))
                .GET()
                .header(ApiConstants.HEADER_ACCEPT, ApiConstants.APPLICATION_JSON)
                .header(ApiConstants.HEADER_AUTHORIZATION, ApiConstants.BASIC + credentials.generateKey())
                .build();

        // Send the request and process the response
        HttpResponse<String> httpResponse = sendRequest(httpRequest);
        if (httpResponse != null) {
            String responseBody = httpResponse.body();

            // Using fromJson() method to convert json to java object
            return gson.fromJson(responseBody, ParticularProjectResponse.class);
        }
        throw new HTTPException(ApiConstants.HTTP_EXCEPTION_MESSAGE+httpResponse.statusCode());
    }

    public CreateProjectResponse updateProjectName(int projectId, String name, String status) throws HTTPException {
        String requestBody = "{\"name\":\"" + name + "\",\"status\":\"" + status + "\"}";

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(ApiConstants.BASE_URL + ApiConstants.PROJECT_ENDPOINT + "/" + projectId))
                .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                .header(ApiConstants.HEADER_ACCEPT, ApiConstants.APPLICATION_JSON)
                .header(ApiConstants.HEADER_AUTHORIZATION, ApiConstants.BASIC + credentials.generateKey())
                .header(ApiConstants.HEADER_CONTENT_TYPE, ApiConstants.APPLICATION_JSON)
                .build();

        HttpResponse<String> httpResponse = sendRequest(httpRequest);

        if (httpResponse != null) {
            String responseBody = httpResponse.body();
            return gson.fromJson(responseBody, CreateProjectResponse.class);
        }

        throw new HTTPException(ApiConstants.HTTP_EXCEPTION_MESSAGE + httpResponse.statusCode());
    }

    protected HttpResponse<String> sendRequest(HttpRequest httpRequest) throws HTTPException {
        try {
            // Send the HTTP request using HttpClient and return the response
            return httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            throw new HTTPException(ApiConstants.ERROR_500_DESCRIPTION);
        }
    }
}
