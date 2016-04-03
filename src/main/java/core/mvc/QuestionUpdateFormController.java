package core.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import next.dao.QuestionDao;
import next.model.Question;
import next.model.User;

public class QuestionUpdateFormController extends AbstractController {
	private QuestionDao questionDao;
	private static final Logger logger = LoggerFactory.getLogger(QuestionUpdateFormController.class);
	
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav;
		User user = (User)request.getSession().getAttribute("user");
		Long questionId = Long.parseLong(request.getParameter("questionId"));
		String url = "redirect:/qna/show?questionId=" + questionId;
		mav = jspView(url);
		if (user == null) {
			return mav;
		}
		
		String writer = request.getParameter("writer");
		questionDao = new QuestionDao();
		Question question = questionDao.findById(questionId);
		if (!user.isUserName(writer)) {
			return mav;
		}
		
		mav = jspView("/qna/form.jsp");
		mav.addObject("question", question);
		return mav;
	}
}
