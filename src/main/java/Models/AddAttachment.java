package Models;

public class AddAttachment {

    private String filePath;
    private String comment;

    public AddAttachment(String filePath, String comment) {
        this.filePath = filePath;
        this.comment = comment;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
