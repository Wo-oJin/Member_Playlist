package servlet.domain.member;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Member {

	private Long id;
	private String username;
	private String password;
	private int age;
		
	public Member(String username, String password, int age) {
		this.username = username;
		this.password = password;
		this.age = age;
	}
	
}
