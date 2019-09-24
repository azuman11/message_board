package controllers;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Message;
import models.validators.MessageValidator;
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

        // バリデーション
        List<String> errors = MessageValidator.validate(m);
            //編集画面のフォームに戻る
        if(errors.size() > 0) {
                em.close();

            // フォームに初期値を設定、さらにエラーメッセージを送る
            request.setAttribute("_token", request.getSession().getId());
            request.setAttribute("message", m);
            request.setAttribute("errors", errors);

            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/messages/edit.jsp");
            rd.forward(request, response);
        } else {

        //DBの方を更新
        em.getTransaction().begin();
        //commit 登録
        em.getTransaction().commit();
        //リダイレクト時に消えてしまうので、フラッシュメッセージをセッションスコープに保存し、index.jspを呼出時にセッションスコープ表示
        request.getSession().setAttribute("flush", "更新が完了しました。");
        em.close();

        // セッションスコープ上の不要になったデータを削除
        request.getSession().removeAttribute("message_id");

        // indexページへリダイレクト
        response.sendRedirect(request.getContextPath() + "/index");

        }



    }

}
