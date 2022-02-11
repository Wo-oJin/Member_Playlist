package hello.servlet.domain.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import servlet.domain.Repository.MemberRepository;
import servlet.domain.member.Member;
import servlet.web.frontcontroller.v4.controller.MemberLoginControllerV4;

public class MemberRepositoryTest {
	
	MemberRepository memberRepository = MemberRepository.getInstance();
	
	@AfterEach // 각 Test가 실행된 이후 적용
	void afterEach() {
		memberRepository.clearStore();
	}
	
	@Test
	void save() {
		//given ~가 주어졌을 때
		Member member = new Member("woojin","1234",24);
		
		//when ~를 실행했을 때
		Member saveMember = memberRepository.save(member);
		
		//then 결과가 ~해야함
		Member findMember = memberRepository.findById(saveMember.getId());
		Assertions.assertThat(findMember).isEqualTo(saveMember);
	}
	
	@Test
	void findAll() {
		//given
		Member member1 = new Member("member1","1234", 21);
		Member member2 = new Member("member2","2345", 22);
		
		memberRepository.save(member1);
		memberRepository.save(member2);
		
		//when
		List<Member> result = memberRepository.findAll();
		
		//then
		Assertions.assertThat(result.size()).isEqualTo(2);
		Assertions.assertThat(result).contains(member1, member2);
	}
	
	@Test
	void login() {
		//given
		Member member1 = new Member("member1","1234",23);
		MemberLoginControllerV4 controller = new MemberLoginControllerV4();
		memberRepository.save(member1);
		
		Map<String, String> paraMap = new HashMap<>();
		Map<String, Object> model = new HashMap<>();
		
		paraMap.put("username", "member1");
		paraMap.put("password", "1234");
		
		//when
		String viewName = controller.process(paraMap, model);
		
		//then
		Assertions.assertThat(viewName).isEqualTo("save-result");
	}
}
