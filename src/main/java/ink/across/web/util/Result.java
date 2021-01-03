package ink.across.web.util;

import ink.across.web.constant.GlobalResponseCode;
import ink.across.web.entity.Response;

public class Result {
    /**
     * 接口请求成功返回
     */
    public static Response success(Object object) {
        Response response = new Response();
        response.setCode(GlobalResponseCode.SUCCESS);
        response.setMsg("请求成功");
        response.setData(object);
        return response;
    }

    /**
     * 接口请求成功返回
     */
    public static Response success() {
        Response response = new Response();
        response.setCode(GlobalResponseCode.SUCCESS);
        response.setMsg("请求成功");
        return response;
    }

    /**
     * 接口请求失败返回
     */
    public static Response error(String code, String resultMessage) {
        Response response = new Response();
        response.setCode(code);
        response.setMsg(resultMessage);
        return response;
    }

    /**
     * 接口请求失败返回
     */
    public static Response error(String resultMessage) {
        Response response = new Response();
        response.setCode(GlobalResponseCode.FAILED);
        response.setMsg(resultMessage);
        return response;
    }
}
