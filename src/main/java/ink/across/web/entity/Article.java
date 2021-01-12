package ink.across.web.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    private Integer id;
    private String title;
    private String time;
    private String sort;
    private String author;
    private String text;
    private Integer frequency;
    private String label;
    private String url;
    private Integer state;
}
