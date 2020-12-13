package bg.petarh.interview.sirma.employees.utils;

public class ArgumentsHolder {
    private String filePath;
    private boolean withUI;

    private ArgumentsHolder(Builder builder) {
        this.filePath = builder.filePath;
        this.withUI = builder.withUI;
    }

    public static class Builder {
        private String filePath;
        private boolean withUI;

        public Builder setFilePath(String filePath) {
            this.filePath = filePath;
            return this;
        }

        public Builder setWithUi(boolean withUi) {
            this.withUI = withUi;
            return this;
        }

        public ArgumentsHolder build() {
            return new ArgumentsHolder(this);
        }

    }

    public String getFilePath() {
        return filePath;
    }

    public boolean getWithUI() {
        return withUI;
    }
}
