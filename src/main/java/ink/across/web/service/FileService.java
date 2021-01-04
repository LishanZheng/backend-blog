package ink.across.web.service;


import ink.across.web.entity.File_;

import java.io.IOException;
import java.util.List;

public interface FileService {

    List<File_> getFileList(String path) throws IOException;
}
