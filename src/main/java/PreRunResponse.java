import java.util.List;

public class PreRunResponse {
    private Meta meta;
    private List<PreRunData> data;

    public Meta getMeta() {
        return meta;
    }

    public List<PreRunData> getData() {
        return data;
    }

    public static class Meta {
        private int total;
        private int org_id;
        private String download_url;

        public int getTotal() {
            return total;
        }

        public int getOrgId() {
            return org_id;
        }

        public String getDownloadUrl() {
            return download_url;
        }
    }

    public static class PreRunData {
        private String name;
        private String last_modified_at;
        private int size;
        private String capability_url;
        private String file_path;

        public String getName() {
            return name;
        }

        public String getLastModifiedAt() {
            return last_modified_at;
        }

        public int getSize() {
            return size;
        }

        public String getCapabilityUrl() {
            return capability_url;
        }

        public String getFilePath() {
            return file_path;
        }
    }
}

class PreRunDeleteResponse {
    private String message;
    private String status;

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }
}

class ValidatePrerunResponse {
    private Data data;
    private String message;
    private String status;

    public Data getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public static class Data {
        private String post_run_file_path;

        public String getPostRunFilePath() {
            return post_run_file_path;
        }
    }
}

class DownloadPreRunResponse {
    private String message;
    private String status;

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }
}

