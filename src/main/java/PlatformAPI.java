/** Importing all dependencies*/
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PlatformAPI extends DependencyProvider {

    public PlatformAPI() {
        super();
    }

    public PlatformResponse fetchPlatforms() throws HTTPException {

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(ApiConstants.BASE_URL + ApiConstants.PLATFORM_ENDPOINT))
                .GET()
                .header(ApiConstants.HEADER_ACCEPT, ApiConstants.APPLICATION_JSON)
                .build();

        HttpResponse<String> httpResponse = sendRequest(httpRequest);
        System.out.println(httpResponse);

        if (httpResponse != null) {
            String responseBody = httpResponse.body();
            return gson.fromJson(responseBody, PlatformResponse.class);
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
        // Create an instance of PlatformAPI
        PlatformAPI platformAPI = new PlatformAPI();

        try {
            // Call the fetchPlatforms method and store the returned value
            PlatformResponse response = platformAPI.fetchPlatforms();

            // Print the returned value
            System.out.println(response);
        } catch (HTTPException e) {
            e.printStackTrace();
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }



}
