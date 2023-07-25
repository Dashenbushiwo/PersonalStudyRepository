package main.java.service;

import main.java.dao.BlogDao;
import main.java.entity.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogService {

    @Autowired
    private BlogDao blogDao;

    public Blog query(Integer id) {
        return blogDao.selectOne(id);
    }

    public void add(Blog blog) {
        System.out.println("insert:" + blogDao.insert(blog));
    }
}
