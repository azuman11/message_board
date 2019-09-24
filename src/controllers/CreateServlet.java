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

//create（挿入処理）の作成
/**
 * Servlet implementation class CreateServlet
 */
@WebServlet("/create")
public class CreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //此処から追加
        String _token = (String)request.getParameter("_token");
        //CSRF対策 値が無し、又はセッションIDと値が異なる場合はじく。
        if(_token != null && _token.equals(request.getSession().getId())) {

            //EntityManagerの作成 Entityは箱。
            EntityManager em = DBUtil.createEntityManager();

            Message m = new Message();

            //id はMySQLの auto_increment の採番
            //入力されたtitleをMessage m に入れる。
            String title = request.getParameter("title");
            m.setTitle(title);

            String content = request.getParameter("content");
            m.setContent(content);

            //現在日時の情報を持つ日付型のオブジェクトを取得
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            m.setCreated_at(currentTime);
            m.setUpdated_at(currentTime);


            em.getTransaction().begin();
            //persist = INSERT エンティティオブジェクトをDBに追加
            em.persist(m);
            //commit 登録
            em.getTransaction().commit();
            //リダイレクト時に消えてしまうので、フラッシュメッセージをセッションスコープに保存し、index.jspを呼出時にセッションスコープ表示
            request.getSession().setAttribute("flush", "登録が完了しました。");
            em.close();

            //indexページへリダイレクト
            response.sendRedirect(request.getContextPath() + "/index");
        }
    }
}
