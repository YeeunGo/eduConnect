package eduConnect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import eduConnect.domain.AuthInfoDTO;
import eduConnect.service.attend.AttendListService;
import eduConnect.service.attend.AttendRatioService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("attend")
public class AttendController {
	@Autowired
	AttendListService attendListService;
	@Autowired
	AttendRatioService attendRatioService;
	
	@GetMapping("attendList")
	public String attendList(@RequestParam("courseNum") String courseNum, HttpSession session, Model model) {
		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
		String studentNum = auth.getUserNum();
		attendListService.execute(courseNum, studentNum, model);
		attendRatioService.execute(courseNum, studentNum, model);
		return "thymeleaf/attend/attendList";
	}
}
