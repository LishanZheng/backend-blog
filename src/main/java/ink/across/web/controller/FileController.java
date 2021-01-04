package ink.across.web.controller;

import ink.across.web.dto.FilePathRequestBean;
import ink.across.web.dto.FileUploadRequestBean;
import ink.across.web.entity.File_;
import ink.across.web.entity.Response;
import ink.across.web.service.FileService;
import ink.across.web.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    FileService fileService;

    @RequestMapping("/upload")
    @ResponseBody
    public Response fileUpload(FileUploadRequestBean fileUploadRequestBean) throws IOException{
        MultipartFile[] files = fileUploadRequestBean.getFiles();
        String path = ResourceUtils.getURL("classpath:").getPath()
                + fileUploadRequestBean.getPath();
        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            File dest = new File(path + "/" + fileName);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                file.transferTo(dest);
            } catch (Exception e) {
                return Result.error("文件上传失败：" + fileName);
            }
        }
//        fileService.UploadFile(file, path);
        return Result.success();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Response fileDeleteByPath(FilePathRequestBean filePathRequestBean) throws Exception {
        String path = ResourceUtils.getURL("classpath:").getPath()
                + "/" + filePathRequestBean.getPath();

        File file = new File(path);
        FileDelete(file);
        return Result.success();
    }

    public void FileDelete(File file) {
        if (!file.exists()){
            System.out.println("目录不存在");
            return;
        }
        File[] files = file.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()){
                    FileDelete(f);
                } else {
                    f.delete();
                }
            }
        }
        file.delete();
    }

    @RequestMapping("/get")
    @ResponseBody
    public Response fileGetByPath(FilePathRequestBean filePathRequestBean) throws IOException{
        String path = ResourceUtils.getURL("classpath:").getPath()
                 + filePathRequestBean.getPath();

        List<File_> list = fileService.getFileList(path);
        return Result.success(list);
    }
}
