import java.util.List;
import java.util.Map;

public class PlatformResponse {
    private String message;
    private String status;
    private Map<String, List<Platform>> platforms;

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public Map<String, List<Platform>> getPlatforms() {
        return platforms;
    }

    public static class Platform {
        private String platform;
        private List<Browser> browsers;

        public String getPlatform() {
            return platform;
        }

        public List<Browser> getBrowsers() {
            return browsers;
        }
    }

    public static class Browser {
        private String browser_name;
        private String version;

        public String getBrowserName() {
            return browser_name;
        }

        public String getVersion() {
            return version;
        }
    }
}
