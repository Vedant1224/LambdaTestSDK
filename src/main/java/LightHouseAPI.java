import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class LightHouseAPI extends DependencyProvider{

    private final Credentials credentials;

    public LightHouseAPI(String userName, String password) {
        super();
        credentials = new Credentials(userName, password);
    }
    public LightHouseResponse getLightHousePerformance(String sessionId) throws HTTPException {

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(ApiConstants.BASE_URL + ApiConstants.LIGHTHOUSE_ENDPOINT + "/"+ sessionId))
                .GET()
                .header(ApiConstants.HEADER_ACCEPT, ApiConstants.APPLICATION_JSON)
                .header(ApiConstants.HEADER_AUTHORIZATION, ApiConstants.BASIC + credentials.generateKey())
                .build();

        HttpResponse<String> httpResponse = sendRequest(httpRequest);

        if (httpResponse != null) {
            String responseBody = httpResponse.body();
            return gson.fromJson(responseBody, LightHouseResponse.class);
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
        LightHouseAPI lighthouseAPI = new LightHouseAPI("vedantg", "oqqNJaT8rZL0C3wuMTIlIhujqogN1yh5DB56pCNTSdO5b7bJjH");
        String sessionId = "YAAS1-TG8LA-UZOVZ-2X3CU";

        try {
            // Invoke the getSessionDetails method
            LightHouseResponse response = lighthouseAPI.getLightHousePerformance(sessionId);

            // Print the output
            System.out.println("Get Session Details Response: ");
            System.out.println("Message: " + response.getMessage());
            System.out.println("Status: " + response.getStatus());
            System.out.println("Data: " + response.getData());
        } catch (HTTPException e) {
            e.printStackTrace();
        }
    }
}