public enum View {
    LOGIN ("login-page.fxml"),
    TASK_MANAGER("task-manager-page.fxml");

    private String filename;

    View(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }
}
