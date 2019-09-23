<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <c:用 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../layout/app.jsp">
    <c:param name="content">
        <%-- app.jspの${param.content}になる --%>
        <h2>メッセージ新規作成ページ</h2>

        <form method="POST" action="${pageContext.request.contextPath}/create">
            <%--${p.r.contextPath} /message_board というコンテキストパスの文字列に置き換えて、create --%>
            <c:import url="_form.jsp" />
        </form>

        <p>
            <a href="${pageContext.request.contextPath}/index">一覧に戻る</a>
        </p>


    </c:param>
</c:import>