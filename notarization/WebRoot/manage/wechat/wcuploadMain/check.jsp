<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,user-scalable=no">
    <title>杭州市国立公证处</title>
    <link rel="stylesheet" href="../cssjsimg/css/wx-index.css"/>
    <script src="../cssjsimg/js/jquery-1.9.1.min.js"></script>
</head>
<body>
<article>
    <div class="start-center">
        <a href="${basePath}/manage/wechat/wcuploadMain/toCheckType.action?gz_type=0" id="notarization">
            <span class="start-shadow">国内公证</span>
        </a>
        <a href="${basePath}/manage/wechat/wcuploadMain/toCheckType.action?gz_type=1" id="foreign">
            <span class="start-shadow">涉外、涉港澳台</span>
        </a>
    </div>
</article>
</body>
</html>