import java.util.List;

public class ProjectResponse {
    private List<ProjectData> data;
    private String status;

    public List<ProjectData> getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }

    public static class ProjectData {
        private int id;
        private String name;
        private int userId;
        private String status;
        private String create_timestamp;
        private String update_timestamp;

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getUserId() {
            return userId;
        }

        public String getStatus() {
            return status;
        }

        public String getCreateTimestamp() {
            return create_timestamp;
        }

        public String getUpdateTimestamp() {
            return update_timestamp;
        }
    }
}

class ParticularProjectResponse {
    private List<ProjectResponseBuild> data;
    private String status;

    public List<ProjectResponseBuild> getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }

    public static class ProjectResponseBuild{
        private String id;
        private String name;
        private int userId;
        private String status;
        private String create_timestamp;
        private String update_timestamp;
        private List<ProjectResponseBuildData> build;

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getUserId() {
            return userId;
        }

        public String getStatus() {
            return status;
        }

        public String getCreateTimestamp() {
            return create_timestamp;
        }

        public String getUpdateTimestamp() {
            return update_timestamp;
        }

        public List<ProjectResponseBuildData> getBuild() {
            return build;
        }

        public static class ProjectResponseBuildData {
            private int build_id;
            private String name;
            private int user_id;
            private String username;
            private String status_ind;
            private String update_timestamp;
            private List<ProjectResponseTestData> test;

            public int getBuildId() {
                return build_id;
            }

            public String getName() {
                return name;
            }

            public int getUserId() {
                return user_id;
            }

            public String getUsername() {
                return username;
            }

            public String getStatusInd() {
                return status_ind;
            }

            public String getUpdateTimestamp() {
                return update_timestamp;
            }

            public List<ProjectResponseTestData> getTest() {
                return test;
            }

            public static class ProjectResponseTestData {
                private int user_id;
                private int org_id;
                private String status_ind;
                private String name;
                private String test_id;
                private String start_timestamp;

                public int getUserId() {
                    return user_id;
                }

                public int getOrgId() {
                    return org_id;
                }

                public String getStatusInd() {
                    return status_ind;
                }

                public String getName() {
                    return name;
                }

                public String getTestId() {
                    return test_id;
                }

                public String getStartTimestamp() {
                    return start_timestamp;
                }
            }
        }
    }
}

class CreateProjectResponse {
    private String message;
    private String status;

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }
}

