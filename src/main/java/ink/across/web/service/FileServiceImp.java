package ink.across.web.service;

import ink.across.web.entity.File_;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class FileServiceImp implements FileService {

    SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd" );


    @Override
    public List<File_> getFileList(String path) throws IOException{

        File file = new File(path);
        if(!file.exists()){
            return null;
        }
        File[] files = file.listFiles();
        List<File_> list = new LinkedList<>();
        String uploadTime = sdf.format(new Date());
        if(files != null) {
            for (int i = 0;i < files.length;i++) {
                File_ file_ = new File_();
                file_.setId(i);
                file_.setPath(path + "/" + files[i].getName());
                String[] type = files[i].getName().split("\\.");
                file_.setFilename(type[0]);
                if(type.length == 1)
                    file_.setType("文件夹");
                 else
                    file_.setType(type[1]);
                file_.setSize(files[i].length() + "");
                file_.setUploadTime(uploadTime);
                file_.setUrl(file_.getPath());
                list.add(file_);
            }
        }
        return list;
    }
}
