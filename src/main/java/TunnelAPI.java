import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class TunnelAPI extends DependencyProvider {
    private final Credentials credentials;

    private final int tunnelId;

    public TunnelAPI(String userName, String password) {
        super();
        credentials = new Credentials(userName, password);
        tunnelId =0;
    }

    public TunnelResponse fetchRunningTunnels() throws HTTPException {

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(ApiConstants.BASE_URL + ApiConstants.TUNNEL_ENDPOINT))
                .GET()
                .header(ApiConstants.HEADER_ACCEPT, ApiConstants.APPLICATION_JSON)
                .header(ApiConstants.HEADER_AUTHORIZATION, ApiConstants.BASIC + credentials.generateKey())
                .build();

        HttpResponse<String> httpResponse = sendRequest(httpRequest);

        if (httpResponse != null) {
            String responseBody = httpResponse.body();
            return gson.fromJson(responseBody, TunnelResponse.class);
        }

        throw new HTTPException(ApiConstants.HTTP_EXCEPTION_MESSAGE + httpResponse.statusCode());
    }

    public TunnelResponse deleteRunningTunnels(int tunnelId) throws HTTPException {

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(ApiConstants.BASE_URL + ApiConstants.TUNNEL_ENDPOINT +"/"+ tunnelId))
                .DELETE()
                .header(ApiConstants.HEADER_ACCEPT, ApiConstants.APPLICATION_JSON)
                .header(ApiConstants.HEADER_AUTHORIZATION, ApiConstants.BASIC + credentials.generateKey())
                .build();

        HttpResponse<String> httpResponse = sendRequest(httpRequest);

        if (httpResponse != null) {
            String responseBody = httpResponse.body();
            return gson.fromJson(responseBody, TunnelResponse.class);
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
        // Try it with your credentials
            String userName = "";
            String password = "";

            // Create an instance of TunnelAPI
            TunnelAPI tunnelAPI = new TunnelAPI(userName, password);

            try {
                // Call the fetchRunningTunnels method and store the returned value
                TunnelResponse response = tunnelAPI.deleteRunningTunnels(0);

                // Print the response
                System.out.println("Message: " + response.getMessage());
                System.out.println("Status: " + response.getStatus());
                System.out.println("Data:" + response.getData());

            } catch (HTTPException e) {
                e.printStackTrace();
                System.out.println("Exception occurred: " + e.getMessage());
            }
        }
}
