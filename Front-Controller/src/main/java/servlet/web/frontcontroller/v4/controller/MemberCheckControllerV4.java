package servlet.web.frontcontroller.v4.controller;

import java.util.Map;

import servlet.domain.Repository.MemberRepository;
import servlet.domain.member.Member;
import servlet.web.frontcontroller.v4.ControllerV4;

public class MemberCheckControllerV4 implements ControllerV4{

	MemberRepository memberRepository = MemberRepository.getInstance();
	
	@Override
	public String process(Map<String, String> paraMap, Map<String, Object> model) {
		String username = paraMap.get("username");
		String input_pw = paraMap.get("password");
		
		Member member = memberRepository.findByName(username);
		
		String viewName = "save-result"; // 로그인 성공 시 이동할 메인 페이지
		String fail = "login_fail"; // 로그인 실패 시 이동할 메인 페이지
		
		if(login(member, username, input_pw)) {
			model.put("member",member);
			return viewName;
		}
		else
			return fail;
	}
	
	private boolean login(Member member, String username, String input_pw) {		
		if(member != null && member.getPassword().equals(input_pw)) 
			return true;
		else {
			//System.out.println(member.getUsername()+" "+username+" "+member.getPassword()+" "+input_pw);
			return false;
		}
	}
	
}
