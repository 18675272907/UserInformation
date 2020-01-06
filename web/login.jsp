<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- HTML5文档-->
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>登录</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <style>
        .show {
            display: block !important;
        }
    </style>
    <script type="text/javascript">
        //切换验证码
        function refreshCode() {
            var vcode = document.getElementById("vcode");
            vcode.src = "${pageContext.request.contextPath}/checkCodeServlet?time=" + new Date().getTime();
        }
    </script>
</head>
<body>
    <div class="container" style="width: 400px;">
        <h3 style="text-align: center">管理员登录</h3>
        <form action="${pageContext.request.contextPath}/loginServlet" method="post">
            <div class="form-group">
                <label for="user">用户名：</label>
                <input type="text" class="form-control" id="user"  name="username"  placeholder="请输入用户名">
                <span></span>
            </div>
            <div class="form-group">
                <label for="password">密码：</label>
                <input type="text" class="form-control" id="password" name="password"  placeholder="请输入密码">
            </div>
            <div class="form-inline">
                <label for="vcode">验证码:</label>
                <input type="text" class="form-control" id="verifycode" name="verifycode" placeholder="请输入验证码" style="width: 120px;">
                <a href="javascript:refreshCode()">
                    <img src="${pageContext.request.contextPath}/checkCodeServlet" title="看不清点击刷新" id="vcode"/>
                </a>
            </div>

            <div class="form-group" style="text-align: center">
                <input type="submit" class="btn btn-primary" value="登录"/>
            </div>
        </form>

        <c:if test="${login_msg ne null}">
            <div class="show">
                <!-- 出错显示的信息框 -->
                <div class="alert alert-warning alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" >
                        <span>&times;</span>
                    </button>
                    <strong>${login_msg}</strong>
                </div>
            </div>
        </c:if>
    </div>

</body>
</html>
