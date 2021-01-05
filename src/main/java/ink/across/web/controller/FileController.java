package ink.across.web.controller;

import ink.across.web.dto.FileDeleteByPathList;
import ink.across.web.dto.FileMkdirRequestBean;
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
        return Result.success();
    }

    @RequestMapping("/mkdir")
    @ResponseBody
    public Response fileMkdir(FileMkdirRequestBean fileMkdirRequestBean) throws IOException{
        String filename = fileMkdirRequestBean.getFilename();
        String path = ResourceUtils.getURL("classpath:").getPath()
                + fileMkdirRequestBean.getPath() + "/" + filename;
        File dest = new File(path);
        if (!dest.exists())
            if (dest.mkdirs())
                return Result.success();

        return Result.error("文件夹创建失败");
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Response fileDeleteByPath(FileDeleteByPathList fileDeleteByPathList) {
        List<String> pathList = fileDeleteByPathList.getPaths();
        for (String s : pathList) {
            File file = new File(s);
            if (!fileDelete(file))
                return Result.error("删除失败" + file.getName());
        }
        return Result.success();
    }

    public Boolean fileDelete(File file) {
        if (!file.exists()){
            return false;
        }
        File[] files = file.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()){
                    fileDelete(f);
                } else {
                    f.delete();
                }
            }
        }
        return file.delete();
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
