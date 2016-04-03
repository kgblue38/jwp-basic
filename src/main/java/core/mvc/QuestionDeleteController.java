package core.mvc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import next.controller.user.UserNameNotMatchedException;
import next.controller.user.UserNotFoundException;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.User;

public class QuestionDeleteController extends AbstractController {
	private QuestionDao questionDao;
	private AnswerDao answerDao;
	private static final Logger logger = LoggerFactory.getLogger(QuestionDeleteController.class);
	
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		questionDao = new QuestionDao();
		answerDao = new AnswerDao();
		String entryUrl = request.getServletPath();
		User user = (User)request.getSession().getAttribute("user");
		String writer = request.getParameter("writer");
		Long questionId = Long.parseLong(request.getParameter("questionId"));
		String exitUrl = "redirect:/qna/show?questionId=" + questionId;
		
		try {
			userExistCheck(user);
			userNameCheck(user, writer);
			List<Answer> answers = answerDao.findAllByQuestionId(questionId);
			answerUsersNameCheck(user, answers);
		} catch (UserNotFoundException e) {
			return getExitMav(entryUrl, exitUrl);
		} catch (UserNameNotMatchedException e) {
			return getExitMav(entryUrl, exitUrl);
		} 
	
		questionDao.delete(questionId);
		answerDao.deleteByQuestionId(questionId);
		exitUrl = "redirect:/";
		return getExitMav(entryUrl, exitUrl);
	}
	
	private ModelAndView getExitMav(String entryUrl, String exitUrl) {
		if (entryUrl.equals("/api/qna/delete")) {
			return jsonView();
		}
		return jspView(exitUrl);
	}
	
	private void userExistCheck(User user) throws UserNotFoundException {
		if (user == null) {
			throw new UserNotFoundException();
		}
	}
	private void answerUsersNameCheck(User user, List<Answer> answers) throws UserNameNotMatchedException {
		for (Answer answer : answers) {
			userNameCheck(user, answer.getWriter());
		}
	}
	private void userNameCheck(User user, String userName) throws UserNameNotMatchedException {
		if(!user.isUserName(userName)) {
			throw new UserNameNotMatchedException();
		}
	}
}
