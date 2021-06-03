package kr.or.ddit.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.controller.JSONResolver;
import kr.or.ddit.dto.MenuVO;
import kr.or.ddit.service.MenuService;

// RESTFUL 방식
public class GetMcodeByMnameHandler implements Handler{ // 회원 등록같은 폼에서 포워드 할 때, 메인메뉴, 서브메뉴 세팅
	private MenuService menuService;
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = null;
		
		String mname = request.getParameter("mName");
		
		MenuVO menu = menuService.getMenuByMname(mname);
		
		JSONResolver.view(response, menu);
		
		return url;
	} // 

}
