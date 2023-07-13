public class OrganisationResponse {
    private Data data;
    private String status;

    public Data getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }

    public static class Data {
        private int queued;
        private int running;

        public int getQueued() {
            return queued;
        }

        public int getRunning() {
            return running;
        }
    }
}
