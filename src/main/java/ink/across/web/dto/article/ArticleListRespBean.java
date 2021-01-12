package ink.across.web.dto.article;

import ink.across.web.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleListRespBean {

    private List<Article> articleList;
    private Integer totalPage;
}
