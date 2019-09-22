<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%--雛型 --%>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <title>メッセージボード</title>
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <h1>メッセージボード アプリケーション</h1>
            </div>
            <div id="content">
                ${param.content}
                <%--各ページのビューが入る <c:outは使わない index--%>
            </div>
            <div id="footer">
                by Taro Kitameki
            </div>

        </div>

    </body>
</html>