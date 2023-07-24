import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PreRunAPI extends DependencyProvider{
    private final Credentials credentials;

    public PreRunAPI(String userName, String password) {
        super();
        // Create a new instance of Credentials with the provided userName and password
        credentials = new Credentials(userName, password);
    }

    public PreRunResponse fetchAllPreRun() throws HTTPException {

        // Build the HTTP request for retrieving specific build data
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(ApiConstants.BASE_URL + ApiConstants.PRERUN_ENDPOINT))
                .GET()
                .header(ApiConstants.HEADER_ACCEPT, ApiConstants.APPLICATION_JSON)
                .header(ApiConstants.HEADER_AUTHORIZATION, ApiConstants.BASIC + credentials.generateKey())
                .build();

        // Send the request and process the response
        HttpResponse<String> httpResponse = sendRequest(httpRequest);
        if (httpResponse != null) {
            String responseBody = httpResponse.body();

            return gson.fromJson(responseBody, PreRunResponse.class);
        }
        throw new HTTPException(ApiConstants.HTTP_EXCEPTION_MESSAGE+httpResponse.statusCode());
    }

    public PreRunResponse deletePreRun(String file_path) throws HTTPException {
        // Create the JSON request body
        String requestBody = "{\"file_path\":\"" + file_path + "\"}";

        // Build the HTTP request for deleting a pre-run file
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(ApiConstants.BASE_URL + ApiConstants.PRERUN_ENDPOINT + "/delete"))
                .method("DELETE", HttpRequest.BodyPublishers.ofString(requestBody))
                .header(ApiConstants.HEADER_ACCEPT, ApiConstants.APPLICATION_JSON)
                .header(ApiConstants.HEADER_AUTHORIZATION, ApiConstants.BASIC + credentials.generateKey())
                .header(ApiConstants.HEADER_CONTENT_TYPE, ApiConstants.APPLICATION_JSON)
                .build();

        // Send the request and process the response
        HttpResponse<String> httpResponse = sendRequest(httpRequest);
        if (httpResponse != null) {
            String responseBody = httpResponse.body();
            return gson.fromJson(responseBody, PreRunResponse.class);
        }

        throw new HTTPException(ApiConstants.HTTP_EXCEPTION_MESSAGE + httpResponse.statusCode());
    }

    public ValidatePrerunResponse isFileApprovedByLambdaTest(String file_path) throws HTTPException {
        // Create the JSON request body
        String requestBody = "{\"file_path\":\"" + file_path + "\"}";

        // Build the HTTP request for deleting a pre-run file
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(ApiConstants.BASE_URL + ApiConstants.PRERUN_ENDPOINT + "/validate"))
                .method("POST", HttpRequest.BodyPublishers.ofString(requestBody))
                .header(ApiConstants.HEADER_ACCEPT, ApiConstants.APPLICATION_JSON)
                .header(ApiConstants.HEADER_AUTHORIZATION, ApiConstants.BASIC + credentials.generateKey())
                .header(ApiConstants.HEADER_CONTENT_TYPE, ApiConstants.APPLICATION_JSON)
                .build();

        // Send the request and process the response
        HttpResponse<String> httpResponse = sendRequest(httpRequest);
        if (httpResponse != null) {
            String responseBody = httpResponse.body();
            return gson.fromJson(responseBody, ValidatePrerunResponse.class);
        }

        throw new HTTPException(ApiConstants.HTTP_EXCEPTION_MESSAGE + httpResponse.statusCode());
    }

    public DownloadPreRunResponse downloadPreRunExecutable(String key) throws Exception {
        // Create the JSON request body
        String requestBody = "{\"file_path\":\"" + key + "\"}";

        // Build the HTTP request for downloading the pre-run executable
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(ApiConstants.BASE_URL + ApiConstants.PRERUN_ENDPOINT + "/download"))
                .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                .header(ApiConstants.HEADER_ACCEPT, ApiConstants.APPLICATION_JSON)
                .header(ApiConstants.HEADER_AUTHORIZATION, ApiConstants.BASIC + credentials.generateKey())
                .header(ApiConstants.HEADER_CONTENT_TYPE, ApiConstants.APPLICATION_JSON)
                .build();

        // Send the request and process the response
        HttpResponse<String> httpResponse = sendRequest(httpRequest);
        if (httpResponse != null) {
            String responseBody = httpResponse.body();
            return gson.fromJson(responseBody, DownloadPreRunResponse.class);
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