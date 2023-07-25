import java.util.List;
class SessionData {
    private String test_id;
    private int build_id;
    private String name;
    private int user_id;
    private String username;
    private int duration;
    private String platform;
    private String browser;
    private String browser_version;
    private String status_ind;
    private String session_id;
    private String build_name;
    private String create_timestamp;
    private String start_timestamp;
    private String end_timestamp;
    private String remark;
    private String console_logs_url;
    private String network_logs_url;
    private String command_logs_url;
    private String selenium_logs_url;
    private String screenshot_url;
    private String video_url;

    public String getTest_id() {
        return test_id;
    }

    public int getBuild_id() {
        return build_id;
    }

    public String getName() {
        return name;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getUsername() {
        return username;
    }

    public int getDuration() {
        return duration;
    }

    public String getPlatform() {
        return platform;
    }

    public String getBrowser() {
        return browser;
    }

    public String getBrowser_version() {
        return browser_version;
    }

    public String getStatus_ind() {
        return status_ind;
    }

    public String getSession_id() {
        return session_id;
    }

    public String getBuild_name() {
        return build_name;
    }

    public String getCreate_timestamp() {
        return create_timestamp;
    }

    public String getStart_timestamp() {
        return start_timestamp;
    }

    public String getEnd_timestamp() {
        return end_timestamp;
    }

    public String getRemark() {
        return remark;
    }

    public String getConsole_logs_url() {
        return console_logs_url;
    }

    public String getNetwork_logs_url() {
        return network_logs_url;
    }

    public String getCommand_logs_url() {
        return command_logs_url;
    }

    public String getSelenium_logs_url() {
        return selenium_logs_url;
    }

    public String getVideo_url() {
        return video_url;
    }

    public String getScreenshot_url() {
        return screenshot_url;
    }
}


public class SessionResponse {
    public SessionData data;
    public String message;
    public String status;

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public SessionData getData() {
        return data;
    }
}

class SessionScreenshotResponse{

    public String url;
    public String message;
    public String status;

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public String getUrl() {
        return url;
    }

}

class SessionVideoResponse{

    public String url;
    public String message;
    public String status;
    public String view_video_url;

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public String getUrl() {
        return url;
    }

    public String getVideoUrl(){
        return view_video_url;
    }

}

    class SessionCommandResponse {
        private List<LogResponseData> data;

        public List<LogResponseData> getData() {
            return data;
        }
    }

 class LogResponseData {
            private String logType;
            private String testID;
            private int status;
            private long timestamp;
            private LogResponseValue Value;

            public String getLogType() {
                return logType;
            }

            public String getTestID() {
                return testID;
            }

            public int getStatus() {
                return status;
            }

            public long getTimestamp() {
                return timestamp;
            }

            public LogResponseValue getValue() {
                return Value;
            }
        }

 class LogResponseValue {
            private String requestId;
            private long RequestStartTime;
            private String requestMethod;
            private String requestPath;
            private int duration;
            private String requestBody;
            private String responseBody;
            private String responseStatus;
            private String screenshotId;

            public String getRequestId() {
                return requestId;
            }

            public long getRequestStartTime() {
                return RequestStartTime;
            }

            public String getRequestMethod() {
                return requestMethod;
            }

            public String getRequestPath() {
                return requestPath;
            }

            public int getDuration() {
                return duration;
            }

            public String getRequestBody() {
                return requestBody;
            }

            public String getResponseBody() {
                return responseBody;
            }

            public String getResponseStatus() {
                return responseStatus;
            }

            public String getScreenshotId() {
                return screenshotId;
            }
        }

class SeleniumLogResponse {

    private List<SeleniumLogData> data;

    private String message;
    private String status;

    public List<SeleniumLogData> getData() {
        return data;
    }

    public String getMessage(){
        return message;
    }

    public String getStatus(){
        return status;
    }

    public static class SeleniumLogData {
        private String logType;
        private String testID;
        private int status;
        private long timestamp;
        private SeleniumLogResponseValue value;

        public String getLogType() {
            return logType;
        }

        public String getTestID() {
            return testID;
        }

        public int getStatus() {
            return status;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public SeleniumLogResponseValue getValue() {
            return value;
        }
    }

    public static class SeleniumLogResponseValue {
        private String level;
        private String message;
        private long timestamp;

        public String getLevel() {
            return level;
        }

        public String getMessage() {
            return message;
        }

        public long getTimestamp() {
            return timestamp;
        }
    }
}

class SessionDeleteResponse {
    private String message;
    private String status;

    public String getMessage(){
        return message;
    }

    public String getStatus(){
        return status;
    }
}

class SessionUpdateResponse {
    private String message;
    private String status;

    public String getMessage(){
        return message;
    }

    public String getStatus(){
        return status;
    }
}

class SessionStopResponse {
    private String message;
    private String status;

    private String url;

    public String getMessage(){
        return message;
    }

    public String getStatus(){
        return status;
    }

    public String getUrl(){
        return url;
    }
}

class SessionUploadResponse {
    private String data;

    private String message;
    private String status;

    public String getData(){
        return data;
    }

    public String getMessage(){
        return message;
    }

    public String getStatus(){
        return status;
    }
}
