package main.java.mapper;

import main.java.entity.Blog;

public interface BlogMapper {

//    @Select("SELECT * FROM blog WHERE id = #{id}")
    Blog selectBlog(Integer id);
}
