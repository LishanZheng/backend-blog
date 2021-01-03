package ink.across.web.service;

import ink.across.web.dao.FileMapper;
import ink.across.web.entity.File_;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class FileServiceImp implements FileService {

    SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd" );

    @Autowired
    FileMapper fileMapper;

    @Override
    public File_ UploadFile(MultipartFile file, String path) throws IOException {

        String uploadTime = sdf.format(new Date());
        String fileName = uploadTime + file.getOriginalFilename();
        String newPath = ResourceUtils.getURL("classpath:").getPath() + path + fileName;;
        File newFile = new File(newPath);
        if(newFile.mkdirs()){
            file.transferTo(newFile);
        }
        String url = path + fileName;
        String size = file.getSize() + "Kb";
        String type = fileName.split("\\.")[1];
//        return fileMapper.uploadFile(fileName, path, size, type, url, uploadTime, 1);
        File_ file_ = new File_(1, fileName, path, size, type, url, uploadTime, 1);
        return file_;
    }
}
