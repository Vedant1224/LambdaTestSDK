import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class UserFilesAPI extends DependencyProvider {
    private final Credentials credentials;

    public UserFilesAPI(String userName, String password) {
        super();
        // Create a new instance of Credentials with the provided userName and password
        credentials = new Credentials(userName, password);
    }

    public UserFilesResponse fetchAllUserFiles() throws Exception {

        // Build the HTTP request for retrieving specific build data
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(ApiConstants.BASE_URL + ApiConstants.USERFILES_ENDPOINT))
                .GET()
                .header(ApiConstants.HEADER_ACCEPT, ApiConstants.APPLICATION_JSON)
                .header(ApiConstants.HEADER_AUTHORIZATION, ApiConstants.BASIC + credentials.generateKey())
                .build();

        // Send the request and process the response
        HttpResponse<String> httpResponse = sendRequest(httpRequest);
        if (httpResponse != null)  {
            String responseBody = httpResponse.body();
            return gson.fromJson(responseBody, UserFilesResponse.class);
        }
        throw new HTTPException(ApiConstants.HTTP_EXCEPTION_MESSAGE+httpResponse.statusCode());
    }

    public DeleteUserFilesResponse deleteUserFiles(String key) throws Exception {
        // Build the HTTP request for deleting a build
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(ApiConstants.BASE_URL + ApiConstants.EXTENSIONS_ENDPOINT + "/delete?key=" + key))
                .DELETE()
                .header(ApiConstants.HEADER_ACCEPT, ApiConstants.APPLICATION_JSON)
                .header(ApiConstants.HEADER_AUTHORIZATION, ApiConstants.BASIC + credentials.generateKey())
                .header(ApiConstants.HEADER_CONTENT_TYPE, ApiConstants.APPLICATION_JSON)
                .build();

        // Send the request and process the response
        HttpResponse<String> httpResponse = sendRequest(httpRequest);
        if (httpResponse != null) {
            String responseBody = httpResponse.body();
            return gson.fromJson(responseBody, DeleteUserFilesResponse.class);
        }
        throw new HTTPException(ApiConstants.HTTP_EXCEPTION_MESSAGE + httpResponse.statusCode());
    }

    public DownloadUserFilesResponse downloadUserFiles(String key) throws Exception {
        // Build the HTTP request for deleting a build
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(ApiConstants.BASE_URL + ApiConstants.EXTENSIONS_ENDPOINT + "/delete?key=" + key))
                .DELETE()
                .header(ApiConstants.HEADER_ACCEPT, ApiConstants.APPLICATION_JSON)
                .header(ApiConstants.HEADER_AUTHORIZATION, ApiConstants.BASIC + credentials.generateKey())
                .header(ApiConstants.HEADER_CONTENT_TYPE, ApiConstants.APPLICATION_JSON)
                .build();

        // Send the request and process the response
        HttpResponse<String> httpResponse = sendRequest(httpRequest);
        if (httpResponse != null) {
            String responseBody = httpResponse.body();
            return gson.fromJson(responseBody, DownloadUserFilesResponse.class);
        }
        throw new HTTPException(ApiConstants.HTTP_EXCEPTION_MESSAGE + httpResponse.statusCode());
    }

    public static void main(String[] args) {
        UserFilesAPI userFilesAPI = new UserFilesAPI("vedantg", "oqqNJaT8rZL0C3wuMTIlIhujqogN1yh5DB56pCNTSdO5b7bJjH");
        try {
            DownloadUserFilesResponse response = userFilesAPI.downloadUserFiles("file_example_JPG_2500kB.jpg");

            System.out.println("Message: " + response.getMessage());
            System.out.println("Status: " + response.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
