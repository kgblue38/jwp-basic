package core.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.QuestionDao;
import next.model.Question;

public class AddQuestionController extends AbstractController {
	private QuestionDao questionDao;
	
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		String writer = request.getParameter("writer");
		Question question = new Question(writer, title, contents);
		questionDao = new QuestionDao();
		questionDao.insert(question);
		return jspView("redirect:/");
	}

}
