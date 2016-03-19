package next.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.Controller;
import next.dao.QuestionDao;
import next.model.Question;

public class QuestionController implements Controller {
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Question question = new Question(req.getParameter("writer"), 
				req.getParameter("title"), 
				req.getParameter("contents"), 
				new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(new Timestamp(System.currentTimeMillis()).getTime())), 
				0);
		QuestionDao questionDao = new QuestionDao();
		questionDao.insert(question);
		return "redirect:/";
	}
}
