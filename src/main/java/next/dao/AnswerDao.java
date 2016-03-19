package next.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import core.jdbc.JdbcTemplate;
import core.jdbc.RowMapper;
import next.model.Answer;

public class AnswerDao {
	public List<Answer> findByQuestionId(int questionId) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "SELECT answerId, writer, contents, createdDate, questionId FROM ANSWERS WHERE questionId = ?";

		RowMapper<Answer> rm = new RowMapper<Answer>() {
			@Override
			public Answer mapRow(ResultSet rs) throws SQLException {
				return new Answer(rs.getInt("answerId"), rs.getString("writer"), rs.getString("contents"),
						new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
								.format(new Date(rs.getTimestamp("createdDate").getTime())),
						rs.getInt("questionId"));
			}
		};
		return jdbcTemplate.query(sql, rm, questionId);
	}
}
