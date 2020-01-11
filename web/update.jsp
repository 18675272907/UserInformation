<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>修改用户</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-3.3.1.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container" style="width: 400px;">
        <h3 style="text-align: center">修改联系人页面</h3>
        <form action="${pageContext.request.contextPath}/updateUserServlet" method="post">
            <!--  隐藏域 提交id-->
            <input type="hidden" name="id" value="${user.id}">

            <div class="form-group">
                <label for="name">姓名：</label>
                <input type="text" value="${user.name}" class="form-control" id="name" name="name" placeholder="请输入姓名" readonly>
            </div>

            <div class="form-group">
                <label>性别：</label>
                <c:if test="${user.gender == '男'}">
                    <input type="radio" name="gender" value="男"checked="checked"/> 男
                    <input type="radio" name="gender" value="女"/> 女
                </c:if>
                <c:if test="${user.gender == '女'}">
                    <input type="radio" name="gender" value="男"/> 男
                    <input type="radio" name="gender" value="女"checked="checked"/> 女
                </c:if>
            </div>

            <div class="form-group">
                <label for="age">年龄：</label>
                <input type="text" value="${user.age}" class="form-control" id="age" name="age" placeholder="请输入年龄">
            </div>

            <div class="form-group">
                <label for="address">籍贯：</label>
                <select name="address" id="address" class="form-control" >
                    <c:if test="${user.address == '北京'}">
                        <option selected>北京</option>
                        <option>上海</option>
                        <option>杭州</option>
                        <option>黑龙江</option>
                    </c:if>
                    <c:if test="${user.address == '上海'}">
                        <option>北京</option>
                        <option selected>上海</option>
                        <option>杭州</option>
                        <option>黑龙江</option>
                    </c:if>
                    <c:if test="${user.address == '杭州'}">
                        <option>北京</option>
                        <option>上海</option>
                        <option selected>杭州</option>
                        <option>黑龙江</option>
                    </c:if>
                    <c:if test="${user.address == '黑龙江'}">
                        <option>北京</option>
                        <option>上海</option>
                        <option>杭州</option>
                        <option selected>黑龙江</option>
                    </c:if>
                </select>
            </div>

            <div class="form-group">
                <label for="qq">QQ:</label>
                <input type="text" value="${user.qq}" class="form-control" id="qq" name="qq" placeholder="请输入QQ号">
            </div>

            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" value="${user.email}" class="form-control" id="email" name="email" placeholder="请输入邮箱地址">
            </div>

            <div class="form-group" style="text-align: center">
                <input type="submit" class="btn btn-primary" value="提交"/>
                <input type="reset" class="btn btn-default" value="重置"/>
                <input type="button" class="btn btn-default" value="返回"/>
            </div>
        </form>
    </div>
</body>
</html>
