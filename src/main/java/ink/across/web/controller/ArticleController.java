package ink.across.web.controller;

import ink.across.web.dto.article.ArticleListRequestBean;
import ink.across.web.dto.article.ArticleListRespBean;
import ink.across.web.entity.Article;
import ink.across.web.entity.Response;
import ink.across.web.service.ArticleService;
import ink.across.web.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @RequestMapping("/get")
    @ResponseBody
    public Response getArticleList(ArticleListRequestBean articleListRequestBean){
        Integer currentPage = articleListRequestBean.getCurrentPage();
        Integer limit = articleListRequestBean.getLimit();
        Integer state = articleListRequestBean.getState();

        Map<String, Object> map = articleService.getArticleList(currentPage, limit, state);
        List<Article> articleList = (List<Article>) map.get("list");
        Integer totalPage = (Integer) map.get("totalPage");
        ArticleListRespBean articleListRespBean = new ArticleListRespBean();
        articleListRespBean.setArticleList(articleList);
        articleListRespBean.setTotalPage(totalPage);
        return Result.success(articleListRespBean);
    }
}
