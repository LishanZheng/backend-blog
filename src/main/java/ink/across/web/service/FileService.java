package ink.across.web.service;

import ink.across.web.entity.File_;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface FileService {

    void UploadFile(MultipartFile file, String path) throws IOException;
}
