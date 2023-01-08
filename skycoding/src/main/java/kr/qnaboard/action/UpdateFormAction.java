package kr.qnaboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.qnaboard.dao.QnaBoardDAO;
import kr.qnaboard.vo.QnaBoardVO;
import kr.controller.Action;
import kr.util.StringUtil;

public class UpdateFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 회원 부분 완료 후 주석 풀 예정
		/*
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) { //로그인 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		*/
		
		//로그인 된 경우
		//글번호 반환
		int qna_id = Integer.parseInt(request.getParameter("qna_id"));

		QnaBoardDAO qnaDao = QnaBoardDAO.getInstance();
		QnaBoardVO qnaBoard = qnaDao.getBoard(qna_id);
		
		/*회원처리 완료 후 주석 풀 예정
		if(user_num!=qnaBoard.getMem_num()) { //로그인한 회원번호와 작성자 회원번호가 불일치
			return "/WEB-INF/views/common/notice.jsp";
		}
		*/
		
		//로그인이 되어 있고 로그인한 회원번호와 작성자 회원번호가 일치
		//제목에 큰따옴표가 있으면 input 태그에 데이터를 표시할 때 오동작이 일어나기 때문에 "를 &quot;로 변환
		qnaBoard.setQna_title(StringUtil.parseQuot(qnaBoard.getQna_title()));

		request.setAttribute("qnaBoard", qnaBoard);

		return "/WEB-INF/views/board_qna/updateForm.jsp";
	}

}