package servlet.web.frontcontroller.v4.controller;

import java.util.Map;

import servlet.domain.Repository.MemberRepository;
import servlet.web.frontcontroller.v4.ControllerV4;

public class MemberPlayListV4 implements ControllerV4{
	MemberRepository memberRepository = MemberRepository.getInstance();

	@Override
	public String process(Map<String, String> paraMap, Map<String, Object> model) {
		return null;
	}
	
}
