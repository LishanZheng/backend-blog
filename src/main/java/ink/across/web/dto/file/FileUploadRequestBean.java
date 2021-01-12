package ink.across.web.dto.file;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileUploadRequestBean {

    private String path;
    private MultipartFile[] files;
}
