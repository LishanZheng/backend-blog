package ink.across.web.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class File {
    private Integer id;
    private String FileName;
    private String path;
    private String size;
    private String type;
    private String url;
    private Integer downCounts;
    private Date uploadTime;
}
