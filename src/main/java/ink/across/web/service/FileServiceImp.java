package ink.across.web.service;

import ink.across.web.dao.FileMapper;
import ink.across.web.entity.File_;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class FileServiceImp implements FileService {

    SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd" );

    @Autowired
    FileMapper fileMapper;

    @Override
    public List<File_> getFileList(String path) throws IOException{

        File file = new File(path);
        if(!file.exists()){
            return null;
        }
        File[] files = file.listFiles();
        List<File_> list = new LinkedList<>();
        String uploadTime = sdf.format(new Date());
        String newPath = ResourceUtils.getURL("classpath:").getPath() + path;;
        if(files != null) {
            for (int i = 0;i < files.length;i++) {
                File_ file_ = new File_();
                file_.setFilename(files[i].getName());
                file_.setId(i);
                file_.setPath(path + "/" + files[i].getName());
                String[] type = file_.getFilename().split("\\.");
                if(type.length == 1) {
                    file_.setType("文件夹");
                    file_.setSize(" ");
                } else {
                    file_.setType(type[1]);
                    file_.setSize(files[i].length() + "KB");
                }
                file_.setUploadTime(uploadTime);
                file_.setUrl(newPath + file_.getFilename());
                list.add(file_);
            }
        }
        return list;
    }
}
