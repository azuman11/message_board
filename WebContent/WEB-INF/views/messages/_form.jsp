<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- new editで使用--%>
<%-- 新しいリソースの入力画面（フォーム） 既存のリソースの編集画面（フォーム）--%>

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

