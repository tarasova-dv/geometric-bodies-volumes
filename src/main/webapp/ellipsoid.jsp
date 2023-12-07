<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 05.11.2023
  Time: 21:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Объемы тел</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body bgcolor=#ffdaf6>
<header>
    Вычисление объемов тел
</header>
<nav>
    <table class="table">
        <td><a class="change-color-link" href="index.jsp">Главная</a></td>
        <td><a class="change-color-link" href="sphere.jsp">Шар</a></td>
        <td><a class="change-color-link" href="ellipsoid.jsp">Эллипсоид</a></td>
        <td><a class="change-color-link" href="thor.jsp">Тор</a></td>
        <td><a class="change-color-link" href="cube.jsp">Куб</a></td>
        <td><a class="change-color-link" href="parallelepiped.jsp">Параллелепипед</a></td>
        <td><a class="change-color-link" href="tetrahedron.jsp">Тетраэдр</a></td>
        <td><a class="change-color-link" href="cylinder.jsp">Цилиндр</a></td>
        <td><a class="change-color-link" href="cone.jsp">Конус</a></td>
    </table>
</nav>
<div>
    <br>
    <h3>Эллипсоид</h3>
    <form action="figure?f=эллипсоид" method="post">
        <p>Введите полуось а в метрах:</p>
        <input type="number" step="any" min="0" value="0" id="a" name="a" placeholder="полуось">
        <p>Введите полуось b в метрах:</p>
        <input type="number" step="any" min="0" value="0" id="b" name="b" placeholder="полуось">
        <p>Введите полуось c в метрах:</p>
        <input type="number" step="any" min="0" value="0" id="c" name="c" placeholder="полуось">
        <p>Введите количество знаков после запятой (от 0 до 4):</p>
        <input type="number" min="0" max="4" value="2" id="amount" name="amount" placeholder="точность">
        <br><output name="out" >Полуось а: ${a}м <br>
        Полуось b: ${b}м <br>
        Полуось с: ${c}м <br>
        Объем эллипсоида: ${volume} м3</output>
        <br><button type="submit">Отправить</button>
    </form>
</div>
</body>
</html>
