package controllers;

//REST7 new
//新しいリソースの入力画面（フォーム）
//_form.jspへ

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Message;

/**
 * Servlet implementation class NewServlet
 */
@WebServlet("/new")
public class NewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // CSRF対策 セキュリティー対策
        request.setAttribute("_token", request.getSession().getId());

        //インスタンスの生成 文字数0のデータの生成
        request.setAttribute("message", new Message());


        //ビューとなるJSPを呼び出している。
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/messages/new.jsp");
        rd.forward(request, response);




    /* CreateServletに移行。
        EntityManager em = DBUtil.createEntityManager();
        em.getTransaction().begin();

        //Messageのインスタンス生成
        Message m = new Message();

        //mの各フィールドにデータの代入
        String title = "taro";
        m.setTitle(title);

        String content = "hello";
        m.setContent(content);

        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        m.setCreated_at(currentTime);
        m.setUpdated_at(currentTime);

        //DBへ保存し、コミット。
        em.persist(m);
        em.getTransaction().commit();

        //自動でつけられたIDの表示
        response.getWriter().append(Integer.valueOf(m.getId()).toString());

        em.close();

    */
    }
}
