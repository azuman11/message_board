package controllers;

// REST7 index
//登録済みのリソースを一覧表示する画面

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;//forward
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Message;
import utils.DBUtil;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    // doGetメソッド
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        /*
        List<Message> messages = em.createNamedQuery("getAllMessages", Message.class)
                //Message.javaのgetAllMessagesを、createNamedQueryメソッドの引数に指定
                                   .getResultList();
                //結果をgetResultList()メソッドで、リスト形式で取得

        /* 過去の遺物
        response.getWriter().append(Integer.valueOf(messages.size()).toString());
        //データの登録件数表示の実行


        em.close();

        //modelsのmessagesをリクエストスコープ(controller)にセット。
        //models messageに"message"クラスがあり、それを引っ張て来てjps(view)に渡す。
        request.setAttribute("messages", messages);
        */

        int page = 1;
        //try catch tryで例外を吐いたら、catchへ
        try {
            //getParameter page=2 それをInteger化
            page = Integer.parseInt(request.getParameter("page"));
        } catch(NumberFormatException e) {}

        // 最大件数と開始位置を指定してメッセージを取得
        //Message.javaのgetAllMessages(モデル問い合わせ)を、createNamedQueryメソッドの引数に指定
        List<Message> messages = em.createNamedQuery("getAllMessages", Message.class)
                                   .setFirstResult(15 * (page - 1))
                                   .setMaxResults(15)
                //結果をgetResultList()メソッドで、リスト形式で取得
                                   .getResultList();
        // 全件数を取得
        long messages_count = (long)em.createNamedQuery("getMessagesCount", Long.class)
                                      //getMessagesCount(モデル問い合わせ)
                                      .getSingleResult();

        em.close();

        //modelsのmessagesをリクエストスコープ(controller)にセット。
        //ほかにも、全件数とページ数もセット。
        //models messageに"message"クラスがあり、それを引っ張て来てjps(view)に渡す。
        request.setAttribute("messages", messages);
        request.setAttribute("messages_count", messages_count);
        request.setAttribute("page", page);





        //セッションにフラッシュがあったら、フラをセッションからリクエストに移動し、セッションからは削除。
        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        //ビューとなるJSPを呼び出している。
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/messages/index.jsp");
        rd.forward(request, response);

    }

}
