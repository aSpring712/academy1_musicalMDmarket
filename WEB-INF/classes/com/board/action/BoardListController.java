package com.board.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDTO;
import com.board.model.SBoardDAO;
import com.board.model.SBoardDAOImpl;
import com.member.util.PageUtil;

/**
 * Servlet implementation class BoardListController
 */
@WebServlet("/board/boardlist")
public class BoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 넘어오는 게 없어서 DAO 만들어서 뿌리기만 하면 됨 --> word, field 값 넘어옴
		SBoardDAO sdao = SBoardDAOImpl.getInstance();
		String word = request.getParameter("word") == null ? "" : request.getParameter("word");
		String field = request.getParameter("field") == null ? "subject" : request.getParameter("field");
		String pageNum = request.getParameter("pageNum")== null ? "1" : request.getParameter("pageNum"); // 클릭 안했을 때 1 페이지
		int currentPage = Integer.parseInt(pageNum); // 현재 1페이지 -> 1~5, 2페이지 2~6
		int pageSize = 5;
		int startRow = (currentPage-1)*pageSize+1;  // 1                     6
		int endRow = (currentPage)*pageSize;        // 5                     10
		
		ArrayList<BoardDTO> arr = sdao.boardList(startRow, endRow, field, word);
		int count = sdao.boardCount(field, word);
		
		// 총 페이지 수
		int totPage = count/pageSize + (count%pageSize==0 ? 0 : 1);
		int blockPage = 3; // [이전] 4 5 6 [다음]     이전 ~ 다음 사이에 몇개의 숫자가 들어갈 건지
		int startPage = ((currentPage-1)/blockPage)*blockPage+1; // [이전] startPage ? endPage [다음]
		int endPage = startPage + blockPage -1;
		if(endPage > totPage) endPage = totPage;
		
		// 번호(글 번호 - 차례대로)
		int rowNo = count-(currentPage-1)*pageSize;
		
		/* 방법1 : 구한건 여기서 구한것이지만 뿌리는 건 jsp에서 뿌림 -> 저장해야 함 */
//		request.setAttribute("totPage", totPage); // 저장
//		request.setAttribute("blockPage", blockPage); // 저장
//		request.setAttribute("startPage", startPage); // 저장
//		request.setAttribute("endPage", endPage); // 저장
//		request.setAttribute("currentPage", currentPage); // 저장
		/* 방법2 : 객체에 담고, 객체 하나 전달 */
		PageUtil page = new PageUtil();
		page.setBlockPage(blockPage);
		page.setCurrentPage(currentPage);
		page.setEndPage(endPage);
		page.setStartPage(startPage);
		page.setTotPage(totPage);
		page.setField(field);
		page.setWord(word); // 7개가 p에 담겨서 
		
		request.setAttribute("rowNo", rowNo);
		request.setAttribute("p", page); // p라는 이름으로 boardList.jsp로 넘어감
		request.setAttribute("boards", arr); // 저장
		request.setAttribute("count", count); // 저장
		request.getRequestDispatcher("boardList.jsp").forward(request, response); // 저장한 값들 들고감
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
