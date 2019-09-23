package controllers;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Message;
import utils.DBUtil;

/**
 * Servlet implementation class EditServlet
 */
@WebServlet("/edit")
public class EditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //EntityManagerの作成 Entityは箱。create
        EntityManager em = DBUtil.createEntityManager();

        //modelのmessageクラスから、指定したidのメッセージ1件のみをDBから取得
                                        //Interger.parseInt()でrequest.getParameter()を整数型化
        //em.findでmに情報が入れられている。 show
        Message m = em.find(Message.class, Integer.parseInt(request.getParameter("id")));

        em.close();

        //リクエストスコープに、メッセージ情報とセッションIDを登録
        request.setAttribute("message", m);
        request.setAttribute("_token", request.getSession().getId());

        //セッションスコープに、メッセージIDを登録 のちに/updateへ引き渡し
        request.getSession().setAttribute("message_id", m.getId());

        //ビューのedit.jspへ呼び出し。
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/messages/edit.jsp");
        rd.forward(request, response);
    }

}
