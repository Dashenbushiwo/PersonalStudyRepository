package main.java.mapper;

import main.java.entity.Blog;

public interface BlogMapper {

    // 多参数时会封装成一个map，为了不混乱，可以用@Param来指定封装时使用的key
//    @Select("SELECT * FROM blog WHERE id = #{id}")
    Blog selectBlog(Integer id);
}
