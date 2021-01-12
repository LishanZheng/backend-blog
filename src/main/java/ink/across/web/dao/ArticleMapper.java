package ink.across.web.dao;

import ink.across.web.entity.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleMapper {

    List<Article> getArticleList(Integer state);
}
