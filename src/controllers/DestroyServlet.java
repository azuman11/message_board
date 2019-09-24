package controllers;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Message;//多分41行目の為。モデルから引っ張て来ている？
import utils.DBUtil;

/**
 * Servlet implementation class DestroyServlet
 */
@WebServlet("/destroy")
public class DestroyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DestroyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            //EditServlet でセッションスコープに保存した message_idから、idを取得
            //そのidのメッセージ1件のみをDBから取得
            //em.findでmに情報が入れられている。 show
            Message m = em.find(Message.class, (Integer)(request.getSession().getAttribute("message_id")));

            //DBの方を更新
            em.getTransaction().begin();
            //データ削除
            em.remove(m);
            em.getTransaction().commit();
            //リダイレクト時に消えてしまうので、フラッシュメッセージをセッションスコープに保存し、index.jspを呼出時にセッションスコープ表示
            request.getSession().setAttribute("flush", "削除が完了しました。");
            em.close();

            // セッションスコープ上の不要になったデータを削除
            request.getSession().removeAttribute("message_id");

            // indexページへリダイレクト
            response.sendRedirect(request.getContextPath() + "/index");
        }
    }

}
