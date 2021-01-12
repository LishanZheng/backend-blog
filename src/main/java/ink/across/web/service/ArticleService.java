package ink.across.web.service;

import java.util.Map;

public interface ArticleService {
    Map<String, Object> getArticleList(Integer currentPage, Integer limit, Integer state);
}
