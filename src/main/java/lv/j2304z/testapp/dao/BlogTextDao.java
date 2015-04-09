package lv.j2304z.testapp.dao;

import lv.j2304z.testapp.domain.Blog;
import lv.j2304z.testapp.domain.BlogText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by jekaterina.zaiceva on 09.04.15.
 */

@Repository
public class BlogTextDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addBlogText(BlogText blogText) {
        this.jdbcTemplate.update(
                "insert into blog_text (blog_id,text) values(?, ?)",
                blogText.getBlogId(),
                blogText.getText()
        );
    }

    RowMapper<BlogText> rowMapper = new RowMapper<BlogText>() {
        @Override
        public BlogText mapRow(ResultSet resultSet, int i) throws SQLException {
            BlogText result = new BlogText();
            result.setBlogId(resultSet.getLong(1));
            result.setText(resultSet.getString(2));
            return result;
        }
    };

    public List<BlogText> getBlogTextByBlogId(long blogId) {
        List<BlogText> result = this.jdbcTemplate.query(
                "select blog_id, text from blog_text where blog_id= ? ",
                rowMapper,
                blogId
        );
        if (result.isEmpty()) {
            return null;
        }
        return result;
    }

}
