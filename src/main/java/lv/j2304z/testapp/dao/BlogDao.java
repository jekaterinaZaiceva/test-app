package lv.j2304z.testapp.dao;

import lv.j2304z.testapp.domain.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by jekaterina.zaiceva on 23.03.15.
 */

@Repository
public class BlogDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addBlog(Blog blog) {
        this.jdbcTemplate.update(
                "insert into blogs (name, userId) values(?, ?)",
                blog.getName(),
                blog.getUserId()
        );
    }

    RowMapper<Blog> rowMapper = new RowMapper<Blog>() {
        @Override
        public Blog mapRow(ResultSet resultSet, int i) throws SQLException {
            Blog result = new Blog();
            result.setBlogId(resultSet.getLong(1));
            result.setName(resultSet.getString(2));
            result.setUserId(resultSet.getLong(3));
            //result.setText(resultSet.getString(4));
            return result;
        }
    };

    public List<Blog> getAllByUser(long userId) {
        List<Blog> result = this.jdbcTemplate.query(
                "select id, name, userId from blogs where userId =?",
                rowMapper,
                userId
        );
        return result;


    }

    public Blog getBlogById(long blogId) {

        List<Blog> result = this.jdbcTemplate.query(
                "select id, name, userId  from blogs where id = ? ",
                rowMapper,
                blogId
        );
        if (result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }


    public void deleteUserBlogs(long userId) {
        jdbcTemplate.update("DELETE FROM blogs WHERE user_id=?", userId);
    }

    public void editBlogName(String blogName, long blogId) {
        this.jdbcTemplate.update(
                "update blog_text set name=? where blog_id = ?",
                blogName,
                blogId
        );
    }
}
