package file.system.models;

public abstract class FileSystemNode {
    protected String name;
    protected Directory parent;
    protected final long creationTimeStamp;
    protected long lastModifiedTimeStamp;
    protected long size;

    public FileSystemNode(String name, Directory parent) {
        this.name = name;
        this.parent = parent;
        this.creationTimeStamp = System.currentTimeMillis();
    }

    protected void touch() {
        this.lastModifiedTimeStamp = System.currentTimeMillis();
    }

    public String getName() {
        return name;
    }

    public Directory getParent() {
        return parent;
    }

    public long getCreationTimeStamp() {
        return creationTimeStamp;
    }

    public long getLastModifiedTimeStamp() {
        return lastModifiedTimeStamp;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public abstract boolean isDirectory();

}
