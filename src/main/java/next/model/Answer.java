package next.model;

import java.util.Date;

public class Answer {
	private int answerId; 			
	private String writer;			
	private String contents;			
	private Date createdDate;			
	private int questionId;

	public Answer(int answerId, String writer, String contents, Date createdDate, int questionId) {
		this.answerId = answerId;
		this.writer = writer;
		this.contents = contents;
		this.createdDate = createdDate;
		this.questionId = questionId;
	}

	public Answer(String writer, String contents, Date createdDate, int questionId) {
		this(0, writer, contents, createdDate, questionId);
	}

	public int getAnswerId() {
		return answerId;
	}

	public String getWriter() {
		return writer;
	}

	public String getContents() {
		return contents;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public int getQuestionId() {
		return questionId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + answerId;
		result = prime * result + ((contents == null) ? 0 : contents.hashCode());
		result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result + questionId;
		result = prime * result + ((writer == null) ? 0 : writer.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Answer other = (Answer) obj;
		if (answerId != other.answerId)
			return false;
		if (contents == null) {
			if (other.contents != null)
				return false;
		} else if (!contents.equals(other.contents))
			return false;
		if (createdDate == null) {
			if (other.createdDate != null)
				return false;
		} else if (!createdDate.equals(other.createdDate))
			return false;
		if (questionId != other.questionId)
			return false;
		if (writer == null) {
			if (other.writer != null)
				return false;
		} else if (!writer.equals(other.writer))
			return false;
		return true;
	}
}
