package file.system.service;

import java.util.List;
import java.util.stream.Collectors;

import file.system.models.Directory;
import file.system.models.File;
import file.system.models.FileSystemNode;
import file.system.service.resolver.PathResolver;
import file.system.service.resolver.PathResolverImpl;

public class FileMgrImpl implements FileMgr {
    private final Directory root;
    private Directory currentDir;
    private final PathResolver pathResolver;

    public FileMgrImpl() {
        this.root = new Directory("/", null);
        this.currentDir = root;
        this.pathResolver = new PathResolverImpl(root);
    }

    @Override
    public String pwd() {
        return pathResolver.getAbsolutePathString(currentDir);
    }

    @Override
    public List<String> ls(String path) {
        Directory dir = pathResolver.resolveToDirectory(path, currentDir);
        return dir.getChildren().stream().map(FileSystemNode::getName).collect(Collectors.toList());
    }

    @Override
    public void mkdir(String path) {
        Directory parentDir = pathResolver.resolveParentDirectory(path, currentDir);
        Directory newDir = new Directory(pathResolver.resolveToFile(path, currentDir).getName(), parentDir);
        parentDir.addChild(newDir);
    }

    @Override
    public void touch(String path) {
        Directory parentDir = pathResolver.resolveParentDirectory(path, currentDir);
        File newFile = new File(pathResolver.resolveToFile(path, currentDir).getName(), parentDir);
        if (parentDir.getChild(newFile.getName()) != null)
            throw new IllegalArgumentException("File already exists");
        parentDir.addChild(newFile);
    }

    @Override
    public void cd(String path) {
        currentDir = pathResolver.resolveToDirectory(path, currentDir);
    }

    @Override
    public void rm(String path) {
        Directory parentDir = pathResolver.resolveParentDirectory(path, currentDir);
        String fileName = path.split("/").length > 1 ? path.split("/")[path.split("/").length - 1] : "";
        parentDir.removeChild(fileName);
    }

    @Override
    public void cp(String src, String dest) {
        Directory parentDir = pathResolver.resolveParentDirectory(dest, currentDir);
        File file = pathResolver.resolveToFile(src, currentDir);
        parentDir.addChild(file);
    }

    @Override
    public void mv(String src, String dest) {
        Directory parentDir = pathResolver.resolveParentDirectory(dest, currentDir);
        File file = pathResolver.resolveToFile(src, currentDir);
        parentDir.removeChild(file.getName());
        parentDir.addChild(file);
    }

    @Override
    public void cat(String path) {
        File file = pathResolver.resolveToFile(path, currentDir);
        System.out.println(file.getContent());
    }

    @Override
    public void writeToFile(String path, String content) {
        File file = pathResolver.resolveToFile(path, currentDir);
        file.setContent(content);
    }

    @Override
    public String readFromFile(String path) {
        File file = pathResolver.resolveToFile(path, currentDir);
        return file.getContent().toString();
    }

}
