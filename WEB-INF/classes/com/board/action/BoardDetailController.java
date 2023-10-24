package com.board.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDTO;
import com.board.model.SBoardDAO;
import com.board.model.SBoardDAOImpl;

/**
 * Servlet implementation class BoardDetailController
 */
@WebServlet("/board/boardDetail")
public class BoardDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 상세보기 한 후 boardDetail.jsp에 출력하기
		request.setCharacterEncoding("utf-8");
		int num = Integer.parseInt(request.getParameter("num")); // 넘어오는 num을 int형으로 받음
		// DAO 만들기
		SBoardDAO sdao = SBoardDAOImpl.getInstance();
		// 상세보기 함수 수행 결과를 board에 담음
		BoardDTO board = sdao.findByNum(num);
		// board를 저장
		request.setAttribute("board", board);
		// 저장한 board를 boardDetail.jsp로 보냄
		request.getRequestDispatcher("boardDetail.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
