package eduConnect.service.attend;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import eduConnect.domain.AttendDTO;
import eduConnect.domain.AuthInfoDTO;
import eduConnect.mapper.AttendMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class AttendListService {
	@Autowired
	AttendMapper attendMapper;
	
	public void execute(String courseNum, HttpSession session, Model model) {
		
		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
		String studentNum = auth.getUserNum();
		List<AttendDTO> list = attendMapper.attendList(courseNum,studentNum );
		model.addAttribute("list", list);
	}
}
