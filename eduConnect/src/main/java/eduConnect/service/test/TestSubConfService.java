package eduConnect.service.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import eduConnect.domain.AttendDTO;
import eduConnect.domain.AuthInfoDTO;
import eduConnect.domain.TestDTO;
import eduConnect.mapper.AttendMapper;
import eduConnect.mapper.TestMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class TestSubConfService {
	@Autowired
	TestMapper testMapper;
	@Autowired
	AttendMapper attendMapper;
	
	public void execute(String sessionNum, String courseNum, HttpSession session, Model model) {
		AuthInfoDTO auth= (AuthInfoDTO) session.getAttribute("auth");
		String studentNum = auth.getUserNum();
		// 시험을 학생이 봤는 지 안봤는지 확인하는 Mapper
		Integer i[] = testMapper.testsubConf(sessionNum, courseNum, studentNum);
		Integer j = null;
		if(i.length != 0) {
			 j = i[0];
		}
		if(j != null) {
			
			// 학생이 답한 답안
			String answer [] = testMapper.selectStuAnswer(sessionNum, courseNum, studentNum);
			model.addAttribute("answer", answer);
			
			// 학생 답안과 정답 동시 select
			List<TestDTO> list = testMapper.answerResult(sessionNum, courseNum, studentNum);
			List<String> result = new ArrayList<>();
			int totalQuestion = 0;
			int rightAnswer = 0;
			for(TestDTO dto : list) {
				totalQuestion += 1;
				if(dto.getTestQuestionAnswer().equals(dto.getStudentAnswer())) {
				 result.add("정답");
				 rightAnswer += 1;
				}else {
					result.add("오답");
				}
		}
		int score = (int)((double)rightAnswer/totalQuestion *100);
		
		System.out.println("실행");
		System.out.println(courseNum);
		System.out.println(studentNum);
		System.out.println(sessionNum);
		System.out.println(totalQuestion);
		System.out.println(rightAnswer);
		System.out.println(score);
		
		model.addAttribute("score", score);
		model.addAttribute("totalQuestion", totalQuestion);
		model.addAttribute("rightAnswer", rightAnswer);
		model.addAttribute("result", result);
		model.addAttribute("conf",j);
	}
	}}
