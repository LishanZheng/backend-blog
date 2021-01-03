package ink.across.web.controller;

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
import java.util.Date;

@RestController
@RequestMapping("/file")
public class FileController {

    @RequestMapping("/upload")
    @ResponseBody
    public Response fileUpload(MultipartFile file,
                               HttpServletRequest request) throws IOException{
        if (file == null) {
            return Result.error("请选择文件");
        }
        String path = ResourceUtils.getURL("classpath:").getPath() + "resources/"+ new Date().getTime();;
        path = path + file.getOriginalFilename();
        File newFile = new File(path);
        if(newFile.mkdirs()){
            file.transferTo(newFile);
            return Result.success();
        } else {
            return Result.error("上传失败");
        }
    }
}
