package next.model;

public class Question {
	private int questionId;
	private String writer;
	private String title;
	private String contents;
	private String createdDate;
	private int countOfAnswer;
	
	public Question(int questionId, String writer, String title, String contents, String createdDate, int countOfAnswer) {
		this.questionId = questionId;
		this.writer = writer;
		this.title = title;
		this.contents = contents;
		this.createdDate = createdDate;
		this.countOfAnswer = countOfAnswer;
	}
	
	public Question(String writer, String title, String contents, String createdDate, int countOfAnswer) {
		this(0, writer, title, contents, createdDate, countOfAnswer);
	}
	
	public int getQuestionId() {
		return questionId;
	}

	public String getWriter() {
		return writer;
	}

	public String getTitle() {
		return title;
	}

	public String getContents() {
		return contents;
	}

	public String getcreatedDate() {
		return createdDate;
	}

	public int getCountOfAnswer() {
		return countOfAnswer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contents == null) ? 0 : contents.hashCode());
		result = prime * result + countOfAnswer;
		result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result + questionId;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Question other = (Question) obj;
		if (contents == null) {
			if (other.contents != null)
				return false;
		} else if (!contents.equals(other.contents))
			return false;
		if (countOfAnswer != other.countOfAnswer)
			return false;
		if (createdDate == null) {
			if (other.createdDate != null)
				return false;
		} else if (!createdDate.equals(other.createdDate))
			return false;
		if (questionId != other.questionId)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (writer == null) {
			if (other.writer != null)
				return false;
		} else if (!writer.equals(other.writer))
			return false;
		return true;
	}
	
	
}
