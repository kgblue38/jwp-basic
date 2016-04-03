package core.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.QuestionDao;

public class QuestionUpdateController extends AbstractController {
	private QuestionDao qustionDao;
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		qustionDao = new QuestionDao();
		qustionDao.update(Long.parseLong(request.getParameter("questionId")), request.getParameter("writer"), request.getParameter("title"), request.getParameter("contents"));
		return jspView("redirect:/");
	}
}