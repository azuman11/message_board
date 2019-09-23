<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <c:用 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- IndexServlet(controller)から関与 --%>

<%-- url属性に指定したファイルの内容を読み込む app用に指定してる--%>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <%-- app.jspの${param.content}になる --%>
        <h2>メッセージ一覧</h2>
        <ul>
            <c:forEach var="message" items="${messages}">
                <%--var=変数 item=リストや配列のようなもの --%>
                <li>
                    <%--${p.r.contextPath} /message_board というコンテキストパスの文字列に置き換わる --%> <a
                    href="${pageContext.request.contextPath}/show?id=${message.id}">
                        <c:out value="${message.id}" />
                </a> :<c:out value="${message.title}"></c:out> &gt; <c:out
                        value="${message.content}" />
                </li>
            </c:forEach>
        </ul>

        <p>
            <a href="${pageContext.request.contextPath}/new">新規メッセージの投稿</a>
        </p>
        <%--<a href="<c:url value='/new' />">新規メッセージの投稿</a> --%>

    </c:param>
</c:import>