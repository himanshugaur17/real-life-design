package file.system.models;

public class File extends FileSystemNode {
    private StringBuilder content;

    public File(String name, Directory parent) {
        super(name, parent);
        this.content = new StringBuilder();
    }

    @Override
    public boolean isDirectory() {
        return false;
    }

    public String getContent() {
        return content.toString();
    }

    public void setContent(String content) {
        this.content = new StringBuilder(content);
    }

    public void appendContent(String content) {
        this.content.append(content);
    }
}
