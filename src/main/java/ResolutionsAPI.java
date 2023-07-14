import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class ResolutionsAPI extends DependencyProvider{
    private final Credentials credentials;
    public ResolutionsAPI(String userName, String password) {
        super();
        credentials = new Credentials(userName, password);
    }

    public ResolutionsResponse fetchAllResolutions() throws HTTPException {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(ApiConstants.BASE_URL + ApiConstants.RESOLUTION_ENDPOINT))
                .GET()
                .header(ApiConstants.HEADER_ACCEPT, ApiConstants.APPLICATION_JSON)
                .header(ApiConstants.HEADER_AUTHORIZATION, ApiConstants.BASIC + credentials.generateKey())
                .build();

        HttpResponse<String> httpResponse = sendRequest(httpRequest);

        if (httpResponse != null) {
            String responseBody = httpResponse.body();
            return gson.fromJson(responseBody, ResolutionsResponse.class);
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
        // Enter your credentials here
        String userName = "";
        String password = "";

        // Create an instance of ResolutionsAPI
        ResolutionsAPI resolutionsAPI = new ResolutionsAPI(userName, password);

        try {
            // Call the fetchAllResolutions method and store the returned value
            ResolutionsResponse response = resolutionsAPI.fetchAllResolutions();

            // Print the returned value
            System.out.println("Message: " + response.getMessage());
            System.out.println("Status: " + response.getStatus());

            Map<String, String[]> resolutions = response.getResolutions();
            if (resolutions != null) {
                for (Map.Entry<String, String[]> entry : resolutions.entrySet()) {
                    String platform = entry.getKey();
                    String[] resolutionArray = entry.getValue();

                    System.out.println("Platform: " + platform);
                    System.out.println("Resolutions: ");
                    for (String resolution : resolutionArray) {
                        System.out.println("- " + resolution);
                    }
                }
            }
        } catch (HTTPException e) {
            e.printStackTrace();
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }

}
