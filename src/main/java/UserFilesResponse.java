import java.util.List;

public class UserFilesResponse {
    private MetaData Meta;
    private List<ExtensionData> data;
    private String message;
    private String status;

    public MetaData getMeta() {
        return Meta;
    }

    public List<ExtensionData> getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public static class MetaData {
        private int org_id;
        private int total;

        public int getOrgId() {
            return org_id;
        }

        public int getTotal() {
            return total;
        }
    }

    public static class ExtensionData {
        private String key;
        private String last_modified_at;
        private int size;

        public String getKey() {
            return key;
        }

        public String getLastModifiedAt() {
            return last_modified_at;
        }

        public int getSize() {
            return size;
        }
    }
}

class DeleteUserFilesResponse {

    private String message;
    private String status;

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }
}

class DownloadUserFilesResponse {

    private String message;
    private String status;

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }
}