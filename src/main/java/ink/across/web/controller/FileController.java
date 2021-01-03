package ink.across.web.controller;

import ink.across.web.bean.FileUploadRequestBean;
import ink.across.web.entity.Response;
import ink.across.web.util.Result;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/file")
public class FileController {

    SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd" );

    @RequestMapping("/upload")
    @ResponseBody
    public Response fileUpload(FileUploadRequestBean fileUploadRequestBean,
                               HttpServletRequest request) throws IOException{
        MultipartFile file = fileUploadRequestBean.getFile();
        String path = fileUploadRequestBean.getPath();
        if (file == null) {
            return Result.error("请选择文件");
        }
        path = ResourceUtils.getURL("classpath:").getPath() + path;
        String fileName = sdf.format(new Date()) + file.getOriginalFilename();
        String newPath = path + fileName;
        File newFile = new File(newPath);
        if(newFile.mkdirs()){
            file.transferTo(newFile);
            return Result.success(newPath);
        } else {
            return Result.error("上传失败");
        }
    }
}
