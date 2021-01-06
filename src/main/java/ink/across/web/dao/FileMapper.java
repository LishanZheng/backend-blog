package ink.across.web.dao;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface FileMapper {

    void uploadFile(String fileName, String path, String size, String type,
                     String url, String uploadTime, Integer state);

}