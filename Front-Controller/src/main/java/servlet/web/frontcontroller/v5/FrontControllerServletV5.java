package servlet.web.frontcontroller.v5;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet.web.frontcontroller.ModelView;
import servlet.web.frontcontroller.MyView;
import servlet.web.frontcontroller.v4.controller.MemberCheckControllerV4;
import servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import servlet.web.frontcontroller.v4.controller.MemberHomeControllerV4;
import servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import servlet.web.frontcontroller.v4.controller.MemberLoginControllerV4;
import servlet.web.frontcontroller.v4.controller.MemberLoginFailedControllerV4;
import servlet.web.frontcontroller.v4.controller.MemberLoginedControllerV4;
import servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import servlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/members/*")
public class FrontControllerServletV5 extends HttpServlet{
	
	private final Map<String, Object> handlerMappingMap = new HashMap<>();
	private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();
	
	public FrontControllerServletV5(){
		initHandlerMappingMap();
		initHandlerAdapters();
	}

	private void initHandlerMappingMap() {
		
		handlerMappingMap.put("/members/home", new MemberHomeControllerV4());
		handlerMappingMap.put("/members/register", new MemberFormControllerV4());
		handlerMappingMap.put("/members/save", new MemberSaveControllerV4());
		handlerMappingMap.put("/members/login", new MemberLoginControllerV4());
		handlerMappingMap.put("/members/check", new MemberCheckControllerV4());
		handlerMappingMap.put("/members/list", new MemberListControllerV4());
		handlerMappingMap.put("/members/main", new MemberLoginedControllerV4());
		handlerMappingMap.put("/members/login_fail", new MemberLoginFailedControllerV4());
	}
	
	private void initHandlerAdapters() {
		handlerAdapters.add(new ControllerV4HandlerAdapter());
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. 핸들러 조회
		Object handler = getHandler(request);
		
		if(handler == null) {
			//response.setStatus(response.SC_FOUND);
			//String redirection = "https://www.naver.com";
			//response.setHeader("Location", redirection);
			
			response.setStatus(response.SC_NOT_FOUND);
			return;
		}
		
		//2. 핸들러를 처리할 수 있는 핸들러 어댑터 조회
		MyHandlerAdapter adapter = getHandlerAdapter(handler);
		
		//3. 어댑터를 이용해 ModelView 얻기
		ModelView mv = adapter.handle(request, response, handler);
		
		//4. viewName을 이용해 viewPath 얻기
		String viewName = mv.getViewName(); // View의 논리 이름만 얻음	
		MyView view = viewResolver(viewName);
		
		view.render(mv.getModel(), request, response);
	}

	private MyHandlerAdapter getHandlerAdapter(Object handler) {
		for(MyHandlerAdapter adapter : handlerAdapters) {
			if(adapter.supports(handler)) {
				return adapter;
			}
		}
		
		throw new IllegalArgumentException("handler adpater를 찾을 수 없습니다. handler= "+handler);
	}

	private Object getHandler(HttpServletRequest request) {
		String requestURI = request.getRequestURI();
		return handlerMappingMap.get(requestURI);
	}
	
	private MyView viewResolver(String viewName) {
		return new MyView("/WEB-INF/views/" + viewName + ".jsp");
	}
	
}
