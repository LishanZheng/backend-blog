package ink.across.web.service;

import ink.across.web.entity.Article;

import java.util.Map;

public interface ArticleService {
    Map<String, Object> getArticleList(Integer currentPage, Integer limit, Integer state);
    Article getArticleBy(Integer id);
}
