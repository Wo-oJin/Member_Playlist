package servlet.web.frontcontroller.v4.controller;

import java.util.Map;

import servlet.domain.Repository.MemberRepository;
import servlet.domain.member.Member;
import servlet.web.frontcontroller.ModelView;
import servlet.web.frontcontroller.v4.ControllerV4;

public class MemberLoginControllerV4 implements ControllerV4 {
	
	@Override
	public String process(Map<String, String> paraMap, Map<String, Object> model) {
		return "login";
	}

}
