package main.java.dao;

import main.java.entity.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BlogDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Blog selectOne(Integer id) {
        String sql = "select * from blog where id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Blog.class), id);
    }

    // 删除和修改与新增类似，故不再做演示
    public Integer insert(Blog blog) {
        String sql = "insert into blog(name) values (?)";
        return jdbcTemplate.update(sql, blog.getName());
    }

}
