import java.util.Map;

public class ResolutionsResponse {
    private String message;
    private Map<String, String[]> resolutions;
    private String status;

    public String getMessage() {
        return message;
    }

    public Map<String, String[]> getResolutions() {
        return resolutions;
    }

    public String getStatus() {
        return status;
    }

}