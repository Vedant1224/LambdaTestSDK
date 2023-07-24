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

        private int pqeued;

        private int max_queue;

        private int max_concurrency;

        private int created;

        public int getQueued() {
            return queued;
        }

        public int getRunning() {
            return running;
        }

        public int getPqeued() {
            return pqeued;
        }

        public int getMaxConcurrency() {
            return max_concurrency;
        }

        public int getCreated() {
            return created;
        }

        public int getMaxqueue() {
            return max_queue;
        }
    }
}
