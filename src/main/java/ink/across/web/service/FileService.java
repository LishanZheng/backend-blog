package ink.across.web.service;

import ink.across.web.entity.File_;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface FileService {

    File_ UploadFile(MultipartFile file, String path) throws IOException;
}
