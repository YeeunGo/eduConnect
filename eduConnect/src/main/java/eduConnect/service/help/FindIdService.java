package eduConnect.service.help;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import eduConnect.command.FindIdCommand;
import eduConnect.mapper.FindMapper;
@Service
public class FindIdService {
	@Autowired
	FindMapper findMapper;
	public void execute(FindIdCommand findIdCommand,
			Model model) {
		String userPhone = findIdCommand.getUserPhone();
		String userEmail = findIdCommand.getUserEmail();
		String userId = findMapper.findId(userPhone, userEmail);
		model.addAttribute("userId", userId);
	}
}