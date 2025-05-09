package file.system.service;

import java.util.List;

public interface FileMgr {
    public String pwd();

    public List<String> ls(String path);

    public void mkdir(String path);

    public void touch(String path);

    public void cd(String path);

    public void rm(String path);

    public void cp(String src, String dest);

    public void mv(String src, String dest);

    public void cat(String path);

    public void writeToFile(String path, String content);

    public String readFromFile(String path);

}
