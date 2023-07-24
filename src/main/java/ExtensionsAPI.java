import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExtensionsAPI extends DependencyProvider{

    private final Credentials credentials;

    public ExtensionsAPI(String userName, String password) {
        super();
        // Create a new instance of Credentials with the provided userName and password
        credentials = new Credentials(userName, password);
    }

    public ExtensionsResponse fetchAllExtensions() throws HTTPException {

        // Build the HTTP request for retrieving specific build data
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(ApiConstants.BASE_URL + ApiConstants.EXTENSIONS_ENDPOINT))
                .GET()
                .header(ApiConstants.HEADER_ACCEPT, ApiConstants.APPLICATION_JSON)
                .header(ApiConstants.HEADER_AUTHORIZATION, ApiConstants.BASIC + credentials.generateKey())
                .build();

        // Send the request and process the response
        HttpResponse<String> httpResponse = sendRequest(httpRequest);
        if (httpResponse != null) {
            String responseBody = httpResponse.body();

            return gson.fromJson(responseBody, ExtensionsResponse.class);
        }
        throw new HTTPException(ApiConstants.HTTP_EXCEPTION_MESSAGE+httpResponse.statusCode());
    }

    public DeleteExtensionsResponse deleteExtension(String file_path) throws HTTPException {

        String requestBody = "{\"file_path\":\"" + file_path + "\"}";
        // Build the HTTP request for deleting a build
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(ApiConstants.BASE_URL + ApiConstants.EXTENSIONS_ENDPOINT + "/delete"))
                .method("DELETE", HttpRequest.BodyPublishers.ofString(requestBody))
                .header(ApiConstants.HEADER_ACCEPT, ApiConstants.APPLICATION_JSON)
                .header(ApiConstants.HEADER_AUTHORIZATION, ApiConstants.BASIC + credentials.generateKey())
                .header(ApiConstants.HEADER_CONTENT_TYPE, ApiConstants.APPLICATION_JSON)
                .build();

        // Send the request and process the response
        HttpResponse<String> httpResponse = sendRequest(httpRequest);
        if (httpResponse != null) {
            String responseBody = httpResponse.body();
            return gson.fromJson(responseBody, DeleteExtensionsResponse.class);
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

    public static void main(String[] args) {
        ExtensionsAPI extensionsAPI = new ExtensionsAPI("", "");

        try {
            // Delete an extension and print the response
            String extensionKeyToDelete = "extension_1.zip";
            DeleteExtensionsResponse deleteResponse = extensionsAPI.deleteExtension(extensionKeyToDelete);
            System.out.println("Delete Extension Message: " + deleteResponse.getMessage());
            System.out.println("Delete Extension Status: " + deleteResponse.getStatus());
        } catch (HTTPException e) {
            e.printStackTrace();
        }
    }

}
