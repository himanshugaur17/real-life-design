package file.system.service.resolver;

import file.system.models.Directory;
import file.system.models.File;
import file.system.models.FileSystemNode;

public interface PathResolver {
    public Directory resolveParentDirectory(String path, Directory currentDir);

    public File resolveToFile(String path, Directory currentDir);

    public Directory resolveToDirectory(String path, Directory currentDir);

    public String getAbsolutePathString(FileSystemNode node);
}
