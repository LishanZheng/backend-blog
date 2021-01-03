package ink.across.web.controller;

import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping("/file")
public class FileController {

    @RequestMapping("/upload")
    @ResponseBody
    public String fileUpload(@RequestParam("file") MultipartFile file,
                             HttpServletRequest request) throws IOException{
        if (file == null) {
            return "文件为空";
        }
        String path = ResourceUtils.getURL("classpath:").getPath() + "resources/"+ new Date().getTime();;
        path = path + file.getOriginalFilename();
        File newFile = new File(path);
        if(newFile.mkdirs()){
            file.transferTo(newFile);
            return path;
        } else {
            return "文件上传失败";
        }
    }
}
