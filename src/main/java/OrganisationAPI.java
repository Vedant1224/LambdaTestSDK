import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class OrganisationAPI extends DependencyProvider {

    private final Credentials credentials;
    public OrganisationAPI(String userName, String password) {
        super();
        credentials = new Credentials(userName, password);
    }

    public OrganisationResponse fetchOrganisationConcurrency() throws HTTPException {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(ApiConstants.BASE_URL + ApiConstants.ORGANISATION_ENDPOINT))
                .GET()
                .header(ApiConstants.HEADER_ACCEPT, ApiConstants.APPLICATION_JSON)
                .header(ApiConstants.HEADER_AUTHORIZATION, ApiConstants.BASIC + credentials.generateKey())
                .build();

        HttpResponse<String> httpResponse = sendRequest(httpRequest);

        if (httpResponse != null) {
            String responseBody = httpResponse.body();
            return gson.fromJson(responseBody, OrganisationResponse.class);
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
        // Enter your own credentials and try it out
        OrganisationAPI organisationAPI = new OrganisationAPI("","");

        try {
            OrganisationResponse response = organisationAPI.fetchOrganisationConcurrency();
            System.out.println("Status: " + response.getStatus());

            OrganisationResponse.Data data = response.getData();
            if (data != null) {
                System.out.println("Queued: " + data.getQueued());
                System.out.println("Running: " + data.getRunning());
            }
        } catch (HTTPException e) {
            e.printStackTrace();
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }
}
