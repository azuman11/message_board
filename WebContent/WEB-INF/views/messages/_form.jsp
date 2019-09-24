<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- new editで使用--%>
<%-- 新しいリソースの入力画面（フォーム） 既存のリソースの編集画面（フォーム）--%>

<%--未入力時 --%>
<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>
</c:if>

<%--all入力時 --%>

<label for="title">タイトル</label><br />
<input type="text" name="title" value="${message.title}" />
<%-- value="${message.title}"はリクエストスコープ(controller)でセットされたmessageから参照 --%>
<%--valueに中身が入っている場合は、既に入ってる状態で表示される --%>
<br /><br />

<label for="content">メッセージ</label><br />
<input type="text" name="content" value="${message.content}" />
<br /><br />


<input type="hidden" name="_token" value="${_token}" />
<button type="submit">投稿</button>

