package com.board.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.QnAboardDAO;
import com.board.model.QnAboardDAOImpl;
import com.board.model.QnAboardDTO;
import com.board.model.SBoardDAO;
import com.board.model.SBoardDAOImpl;
import com.member.util.PageUtil;

/**
 * Servlet implementation class QnAListController
 */
@WebServlet("/board/qnaList")
public class QnAListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnAListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		QnAboardDAO sdao = QnAboardDAOImpl.getInstance();
		String word = request.getParameter("word") == null ? "" : request.getParameter("word");
		String field = request.getParameter("field") == null ? "subject" : request.getParameter("field");
		String pageNum = request.getParameter("pageNum") == null ? "1" : request.getParameter("pageNum");
		int currentPage = Integer.parseInt(pageNum);
		int pageSize = 5;
		int startRow = (currentPage-1)*pageSize+1;
		int endRow = (currentPage)*pageSize;
		
		ArrayList<QnAboardDTO> arr = sdao.boardList(startRow, endRow, field, word);
		int count = sdao.boardCount(field, word);
		
		int totPage = count/pageSize + (count%pageSize==0 ? 0 : 1);
		int blockPage = 3; // [이전] 4 5 6 [다음]     이전 ~ 다음 사이에 몇개의 숫자가 들어갈 건지
		int startPage = ((currentPage-1)/blockPage)*blockPage+1; // [이전] startPage ? endPage [다음]
		int endPage = startPage + blockPage -1;
		if(endPage > totPage) endPage = totPage;
		
		// 번호(글 번호 - 차례대로)
		int rowNo = count-(currentPage-1)*pageSize;
		
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
		request.getRequestDispatcher("QnABoardList.jsp").forward(request, response); // 저장한 값들 들고감
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
