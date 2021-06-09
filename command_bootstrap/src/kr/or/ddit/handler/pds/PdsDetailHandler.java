package kr.or.ddit.handler.pds;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.dto.PdsVO;
import kr.or.ddit.handler.Handler;
import kr.or.ddit.service.PdsService;

public class PdsDetailHandler implements Handler {
	private PdsService pdsService;
	public void setPdsService(PdsService pdsService) {
		this.pdsService = pdsService;
	}

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "pds/detail";
		int pno = Integer.parseInt(request.getParameter("pno"));
		String from = request.getParameter("from");
		
		try {
			PdsVO pds = null;
			if(from!=null && from.equals("modify")) {
				pds = pdsService.getPdsForModify(pno);
			}else {
				pds = pdsService.getPds(pno);
			}
			request.setAttribute("pds", pds);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return url;
	}

}
