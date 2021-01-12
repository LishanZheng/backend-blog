package ink.across.web.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import ink.across.web.dao.ArticleMapper;
import ink.across.web.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImp implements ArticleService {

    @Autowired
    ArticleMapper articleMapper;

    @Override
    public Map<String, Object> getArticleList(Integer currentPage, Integer limit, Integer state) {
        Page page = PageHelper.startPage(currentPage, limit);
        List<Article> articleList = articleMapper.getArticleList(state);
        Map<String , Object> map = new HashMap<>();
        map.put("list", articleList);
        map.put("totalPage", page.getPages());
        return map;
    }
}
