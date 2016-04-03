package next.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import next.model.Question;
import core.jdbc.JdbcTemplate;
import core.jdbc.RowMapper;

public class QuestionDao {
	public List<Question> findAll() {
		JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();
		String sql = "SELECT questionId, writer, title, createdDate, countOfAnswer FROM QUESTIONS "
				+ "order by questionId desc";
		
		RowMapper<Question> rm = new RowMapper<Question>() {
			@Override
			public Question mapRow(ResultSet rs) throws SQLException {
				return new Question(rs.getLong("questionId"),
						rs.getString("writer"), rs.getString("title"), null,
						rs.getTimestamp("createdDate"),
						rs.getInt("countOfAnswer"));
			}
			
		};
		
		return jdbcTemplate.query(sql, rm);
	}
	
	public void updateCountOfAnswer(Long questionId, int countOfAnswer) {
		JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();
		String sql = "UPDATE QUESTIONS SET countOfAnswer = ? WHERE questionId = ?";
		jdbcTemplate.update(sql, countOfAnswer, questionId);
	}

	public Question findById(long questionId) {
		JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();
		String sql = "SELECT questionId, writer, title, contents, createdDate, countOfAnswer FROM QUESTIONS "
				+ "WHERE questionId = ?";
		
		RowMapper<Question> rm = new RowMapper<Question>() {
			@Override
			public Question mapRow(ResultSet rs) throws SQLException {
				return new Question(rs.getLong("questionId"),
						rs.getString("writer"), rs.getString("title"),
						rs.getString("contents"),
						rs.getTimestamp("createdDate"),
						rs.getInt("countOfAnswer"));
			}
		};
		
		return jdbcTemplate.queryForObject(sql, rm, questionId);
	}
	
	public void insert(Question question) {
		JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();
		String sql = "INSERT INTO QUESTIONS (writer, title, contents, createdDate) VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(sql, question.getWriter(), question.getTitle(), question.getContents(), new Timestamp(System.currentTimeMillis()));
	}
	
	public void update(Long questionId, String writer, String title, String contents) {
		JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();
		String sql = "UPDATE QUESTIONS SET writer = ?, title = ?, contents = ? WHERE questionId = ?";
		jdbcTemplate.update(sql, writer, title, contents, questionId);
	}
	
	public void delete(Long questionId) {
		JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();
		String sql = "DELETE FROM QUESTIONS WHERE questionId = ?";
		jdbcTemplate.update(sql, questionId);
	}
}






