package controllers;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Message;
import utils.DBUtil;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //EntityManagerの作成 Entityは箱。
        EntityManager em = DBUtil.createEntityManager();

        //EditServlet でセッションスコープに保存した message_idから、idを取得
        //そのidのメッセージ1件のみをDBから取得
        //em.findでmに情報が入れられている。 show
        Message m = em.find(Message.class, (Integer)(request.getSession().getAttribute("message_id")));

        //基本creatServlet
        //入力されたtitle, content, currerntTimeをMessage m に入れ更新。
        String title = request.getParameter("title");
        m.setTitle(title);

        String content = request.getParameter("content");
        m.setContent(content);

        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        m.setUpdated_at(currentTime);

        //DBの方を更新
        em.getTransaction().begin();
        //commit 登録
        em.getTransaction().commit();
        em.close();

        // セッションスコープ上の不要になったデータを削除
        request.getSession().removeAttribute("message_id");

        // indexページへリダイレクト
        response.sendRedirect(request.getContextPath() + "/index");



    }

}
