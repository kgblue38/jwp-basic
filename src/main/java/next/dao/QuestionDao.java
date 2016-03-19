package next.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import core.jdbc.JdbcTemplate;
import core.jdbc.RowMapper;
import next.model.Question;

public class QuestionDao {
	public void insert(Question question) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "INSERT INTO QUESTIONS (writer, title, contents, createdDate, countOfAnswer) VALUES (?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, question.getWriter(), question.getTitle(), question.getContents(),
				question.getcreatedDate(), question.getCountOfAnswer());
	}

	public Question findByQuestionId(int questionId) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "SELECT questionId, writer, title, contents, createdDate, countOfAnswer FROM QUESTIONS WHERE questionId = ?";

		RowMapper<Question> rm = new RowMapper<Question>() {
			@Override
			public Question mapRow(ResultSet rs) throws SQLException {
				return new Question(rs.getInt("questionId"), rs.getString("writer"),
						rs.getString("title"), rs.getString("contents"), new SimpleDateFormat("yyyy-MM-dd HH:mm")
								.format(new Date(rs.getTimestamp("createdDate").getTime())),
						rs.getInt("countOfAnswer"));
			}
		};
		return jdbcTemplate.queryForObject(sql, rm, questionId);
	}

	public List<Question> findAll() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "SELECT questionId, writer, title, contents, createdDate, countOfAnswer FROM QUESTIONS";

		RowMapper<Question> rm = new RowMapper<Question>() {
			@Override
			public Question mapRow(ResultSet rs) throws SQLException {
				return new Question(rs.getInt("questionId"), rs.getString("writer"),
						rs.getString("title"), rs.getString("contents"), new SimpleDateFormat("yyyy-MM-dd HH:mm")
								.format(new Date(rs.getTimestamp("createdDate").getTime())),
						rs.getInt("countOfAnswer"));
			}
		};
		return jdbcTemplate.query(sql, rm);
	}
}
