package ink.across.web.dto.article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleListRequestBean {

    private Integer currentPage;
    private Integer limit;
    private Integer state;
}
