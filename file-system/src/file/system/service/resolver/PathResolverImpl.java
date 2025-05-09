package file.system.service.resolver;

import java.util.Arrays;
import java.util.LinkedList;

import file.system.models.Directory;
import file.system.models.File;
import file.system.models.FileSystemNode;

public class PathResolverImpl implements PathResolver {
    private static final String SEPARATOR = "/";
    private final Directory root;

    public PathResolverImpl(Directory root) {
        this.root = root;
    }

    @Override
    public Directory resolveParentDirectory(String path, Directory currentDir) {
        if (path == null || path.isEmpty())
            throw new IllegalArgumentException("Path is empty");
        String normalizedPath = path;
        if (normalizedPath.length() > 1 && normalizedPath.endsWith(SEPARATOR)) {
            normalizedPath = normalizedPath.substring(0, normalizedPath.length() - 1);
        }
        int lastSlashIndex = normalizedPath.lastIndexOf(SEPARATOR);
        if (lastSlashIndex == -1)
            if (currentDir.getChild(normalizedPath) != null)
                return currentDir;
            else
                throw new IllegalArgumentException("Path is not found");
        String parentPath = normalizedPath.substring(0, lastSlashIndex);
        return (Directory) resolve(parentPath, currentDir);
    }

    @Override
    public File resolveToFile(String path, Directory currentDir) {
        if (path.equals(root.getName()))
            return null;
        if (path.startsWith(root.getName()))
            currentDir = root;
        FileSystemNode node = resolve(path, currentDir);
        if (node.isDirectory())
            throw new IllegalArgumentException("Path is a directory");
        return (File) node;
    }

    @Override
    public Directory resolveToDirectory(String path, Directory currentDir) {
        if (path.equals(root.getName()))
            return root;
        if (path.startsWith(root.getName()))
            currentDir = root;
        return (Directory) resolve(path, currentDir);
    }

    private FileSystemNode resolve(String path, Directory currentDir) {
        if (path == null || path.isEmpty())
            throw new IllegalArgumentException("Path is empty");
        String normalizedPath = path;
        Directory startDir = currentDir;
        String[] components;
        if (normalizedPath.length() > 1 && normalizedPath.endsWith(SEPARATOR)) {
            normalizedPath = normalizedPath.substring(0, normalizedPath.length() - 1);
        }
        if (normalizedPath.startsWith(SEPARATOR)) {
            startDir = root;
            if (normalizedPath.equals(SEPARATOR))
                return root;
            components = Arrays.stream(normalizedPath.substring(1).split(SEPARATOR)).filter(s -> !s.isEmpty())
                    .toArray(String[]::new);
        } else {
            components = Arrays.stream(normalizedPath.split(SEPARATOR)).filter(s -> !s.isEmpty())
                    .toArray(String[]::new);
        }
        int n = components.length - 1;
        for (int i = 0; i < n; i++) {
            String component = components[i];
            if (component.equals("."))
                continue;
            if (component.equals("..")) {
                startDir = startDir.getParent();
            } else {
                startDir = (Directory) startDir.getChild(component);
            }
        }
        return startDir.getChild(components[n]);
    }

    @Override
    public String getAbsolutePathString(FileSystemNode node) {
        if (node == null)
            throw new IllegalArgumentException("Node is null");
        if (node.equals(root))
            return SEPARATOR.concat(root.getName());
        LinkedList<String> path = new LinkedList<>();
        while (node != null) {
            path.addFirst(node.getName());
            node = node.getParent();
        }
        return String.join(SEPARATOR, path);
    }

}
