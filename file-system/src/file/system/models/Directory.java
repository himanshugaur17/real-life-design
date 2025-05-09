package file.system.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Directory extends FileSystemNode {
    private final Map<String, FileSystemNode> children;

    public Directory(String name, Directory parent) {
        super(name, parent);
        this.children = new HashMap<>();
    }

    public void addChild(FileSystemNode node) {
        touch();
        children.put(node.getName(), node);
    }

    public FileSystemNode getChild(String name) {
        return children.get(name);
    }

    public List<FileSystemNode> getChildren() {
        return new ArrayList<>(children.values());
    }

    public void removeChild(String name) {
        children.remove(name);
    }

    @Override
    public boolean isDirectory() {
        return true;
    }

}
