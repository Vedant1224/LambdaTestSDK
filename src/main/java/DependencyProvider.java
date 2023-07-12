import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DependencyProvider {
    protected final HttpClient httpClient;
    protected final Gson gson;

    public DependencyProvider() {
        httpClient = HttpClient.newBuilder().build();
        gson = new Gson();
    }

    protected HttpResponse<String> sendRequest(HttpRequest httpRequest) throws Exception {
        // Send the request using the HttpClient
        return httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }
}
