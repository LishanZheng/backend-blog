package ink.across.web.controller;

import ink.across.web.dto.FileMkdirRequestBean;
import ink.across.web.dto.FilePathListBean;
import ink.across.web.dto.FilePathRequestBean;
import ink.across.web.dto.FileUploadRequestBean;
import ink.across.web.entity.File_;
import ink.across.web.entity.Response;
import ink.across.web.service.FileService;
import ink.across.web.util.Result;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Map;

import static ink.across.web.constant.GlobalResponseCode.URL;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    FileService fileService;

    @RequestMapping("/upload")
    @ResponseBody
    public Response fileUpload(FileUploadRequestBean fileUploadRequestBean) throws IOException{
        MultipartFile[] files = fileUploadRequestBean.getFiles();
        String path = URL + fileUploadRequestBean.getPath();
        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            File dest = new File(path + "/" + fileName);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile();
            }
            try {
                FileUtils.copyInputStreamToFile(file.getInputStream(), dest);
            } catch (Exception e) {
                return Result.error(e.toString());
            }
        }
        return Result.success();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Response fileDeleteByPath(FilePathListBean fileDeleteByPathList) {
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
                } else
                    f.delete();
            }
        }
        return file.delete();
    }

    @RequestMapping("/get")
    @ResponseBody
    public Response fileGetByPath(FilePathRequestBean filePathRequestBean) throws IOException{
        System.out.println(URL);
        String path = URL + filePathRequestBean.getPath();

        List<File_> list = fileService.getFileList(path);
        return Result.success(list);
    }

    @RequestMapping("/mkdir")
    @ResponseBody
    public Response fileMkdir(FileMkdirRequestBean fileMkdirRequestBean) throws IOException{
        String filename = fileMkdirRequestBean.getFilename();
        String path = URL + fileMkdirRequestBean.getPath() + "/" + filename;
        File dest = new File(path);
        if (!dest.exists())
            if (dest.mkdirs())
                return Result.success();

        return Result.error("文件夹创建失败");
    }

    @RequestMapping("/download")
    @ResponseBody
    public Response fileDownLoad(FilePathListBean filePathListBean, HttpServletResponse response) {
        List<String> pathList = filePathListBean.getPaths();
        for (String s : pathList) {
            File file = new File(s);
            if (!file.exists()) {
                return Result.error("文件不存在");
            }
            String[] fileSplit = s.split("/");
            String fileName = fileSplit[fileSplit.length - 1];
            response.reset();
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.setContentType("application/octet-stream");
            response.setCharacterEncoding("utf-8");
            response.setContentLength((int) file.length());
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);

            try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));) {
                byte[] buff = new byte[1024];
                OutputStream os = response.getOutputStream();
                int i = 0;
                while ((i = bis.read(buff)) != -1) {
                    os.write(buff, 0, i);
                    os.flush();
                }
            } catch (IOException e) {
                return Result.error("下载失败");
            }
        }
        return Result.success();
    }
}

