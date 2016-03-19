package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.Controller;
import next.dao.AnswerDao;
import next.dao.QuestionDao;

public class AnswerFormController implements Controller {
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		int questionId = Integer.parseInt(req.getParameter("questionId"));
		QuestionDao questionDao = new QuestionDao();
		AnswerDao answerDao = new AnswerDao();
		req.setAttribute("question", questionDao.findByQuestionId(questionId));
		req.setAttribute("answers", answerDao.findByQuestionId(questionId));
		return "/qna/show.jsp";
	}
}