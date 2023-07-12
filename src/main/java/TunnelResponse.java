import java.util.List;

public class TunnelResponse {
    private List<Tunnel> data;
    private String message;
    private String status;

    public List<Tunnel> getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

 class Tunnel {
        private String dns;
        private String email;
        private String username;
        private boolean shared_tunnel;
        private String folder_path;
        private String local_domains;
        private int org_id;
        private String start_timestamp;
        private String status_ind;
        private int tunnel_id;
        private String tunnel_name;
        private int user_id;

        public String getDns() {
            return dns;
        }

        public String getEmail() {
            return email;
        }

        public String getUsername() {
            return username;
        }

        public boolean isSharedTunnel() {
            return shared_tunnel;
        }

        public String getFolderPath() {
            return folder_path;
        }

        public String getLocalDomains() {
            return local_domains;
        }

        public int getOrgId() {
            return org_id;
        }

        public String getStartTimestamp() {
            return start_timestamp;
        }

        public String getStatusInd() {
            return status_ind;
        }

        public int getTunnelId() {
            return tunnel_id;
        }

        public String getTunnelName() {
            return tunnel_name;
        }

        public int getUserId() {
            return user_id;
        }
    }
}
