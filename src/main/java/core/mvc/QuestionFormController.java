package core.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class QuestionFormController extends AbstractController {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			return jspView("redirect:/");
		}
		ModelAndView mav = jspView("/qna/form.jsp");
		mav.addObject("question", null);
		return mav;
	}
}
