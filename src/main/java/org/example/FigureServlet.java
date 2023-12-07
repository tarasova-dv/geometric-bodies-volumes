package org.example;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/figure")
public class FigureServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF8");
        req.setCharacterEncoding("UTF-8");
        String figure = req.getParameter("f"); // получение фигуры из адресной строки
        countFigureVolume(req, resp, figure);
    }

    private void countFigureVolume(HttpServletRequest req, HttpServletResponse resp, String figure) throws ServletException, IOException {
        java.util.Formatter formatter = new java.util.Formatter();
        double result;
        String out;
        int amount; // количество знаков после запятой
        if (req.getParameter("amount").isEmpty()) { amount = 2;}
        else { amount = Integer.parseInt(req.getParameter("amount")); }
        switch (figure) {
            case "шар":
                double sphereRadius;
                if (req.getParameter("radius").isEmpty()) {sphereRadius = 0; }
                else { sphereRadius = Double.parseDouble(req.getParameter("radius"));}
                result = ellipsoidVolume(sphereRadius, sphereRadius, sphereRadius);

                out = formatter.format("%." + amount + "f", result).toString();
                req.setAttribute("volume", out);
                req.setAttribute("rad", sphereRadius);
                getServletContext().getRequestDispatcher("/sphere.jsp").forward(req, resp);
                break;

            case "эллипсоид":
                double ellipseA, ellipseB, ellipseC;
                if (req.getParameter("a").isEmpty()) { ellipseA = 0;}
                else { ellipseA = Double.parseDouble(req.getParameter("a"));}

                if (req.getParameter("b").isEmpty()) { ellipseB = 0;}
                else { ellipseB = Double.parseDouble(req.getParameter("b"));}

                if (req.getParameter("c").isEmpty()) { ellipseC = 0;}
                else { ellipseC = Double.parseDouble(req.getParameter("c"));}

                result = ellipsoidVolume(ellipseA, ellipseB, ellipseC);
                out = formatter.format("%." + amount + "f", result).toString();

                req.setAttribute("volume", out);
                req.setAttribute("a", ellipseA);
                req.setAttribute("b", ellipseB);
                req.setAttribute("c", ellipseC);
                getServletContext().getRequestDispatcher("/ellipsoid.jsp").forward(req, resp);
                break;

            case "тор":
                double thorRadius, thorRadiusSechenia;
                if (req.getParameter("r1").isEmpty()) {thorRadius = 0;}
                else { thorRadius = Double.parseDouble(req.getParameter("r1"));}

                if (req.getParameter("r2").isEmpty()) {thorRadiusSechenia = 0;}
                else {thorRadiusSechenia = Double.parseDouble(req.getParameter("r2"));}

                result = thorVolume(thorRadius, thorRadiusSechenia);
                out = formatter.format("%." + amount + "f", result).toString();

                req.setAttribute("volume", out);
                req.setAttribute("rad", thorRadius);
                req.setAttribute("sech", thorRadiusSechenia);
                getServletContext().getRequestDispatcher("/thor.jsp").forward(req, resp);
                break;

            case "куб":
                double cubeLen; // длина стороны куба
                if (req.getParameter("len").isEmpty()) {cubeLen = 0;}
                else { cubeLen = Double.parseDouble(req.getParameter("len")); }

                result = parallelepipedVolume(cubeLen, cubeLen, cubeLen);
                out = formatter.format("%." + amount + "f", result).toString(); // форматирование с учетом заданной точности

                req.setAttribute("volume", out); // устнановка атрибута
                req.setAttribute("len", cubeLen);
                getServletContext().getRequestDispatcher("/cube.jsp").forward(req, resp); // пересылка запроса в jsp файл
                break;

            case "параллелепипед":
                double firstLineLen, secondLineLen, thirdLineLen;
                if (req.getParameter("a").isEmpty()) { firstLineLen = 0;}
                else {firstLineLen = Double.parseDouble(req.getParameter("a"));}

                if (req.getParameter("b").isEmpty()) {secondLineLen = 0;}
                else {secondLineLen = Double.parseDouble(req.getParameter("b"));}

                if (req.getParameter("c").isEmpty()) { thirdLineLen = 0;}
                else {thirdLineLen = Double.parseDouble(req.getParameter("c")); }

                result = parallelepipedVolume(firstLineLen, secondLineLen, thirdLineLen);
                out = formatter.format("%." + amount + "f", result).toString();

                req.setAttribute("volume", out);
                req.setAttribute("a", firstLineLen);
                req.setAttribute("b", secondLineLen);
                req.setAttribute("c", thirdLineLen);
                getServletContext().getRequestDispatcher("/parallelepiped.jsp").forward(req, resp);
                break;

            case "тетраэдр":
                double tetrahedronLen;
                if (req.getParameter("a").isEmpty()) {tetrahedronLen = 0;}
                else {tetrahedronLen = Double.parseDouble(req.getParameter("a"));}

                result = tetrahedronVolume(tetrahedronLen);
                out = formatter.format("%." + amount + "f", result).toString();

                req.setAttribute("volume", out);
                req.setAttribute("len", tetrahedronLen);
                getServletContext().getRequestDispatcher("/tetrahedron.jsp").forward(req, resp);
                break;

            case "цилиндр":
                double cylinderRadius, cylinderHeight;
                if (req.getParameter("radius").isEmpty()) {cylinderRadius = 0;}
                else { cylinderRadius = Double.parseDouble(req.getParameter("radius"));}

                if (req.getParameter("h").isEmpty()) { cylinderHeight = 0;}
                else {cylinderHeight = Double.parseDouble(req.getParameter("h"));}

                result = cylinderVolume(cylinderRadius, cylinderHeight);
                out = formatter.format("%." + amount + "f", result).toString();

                req.setAttribute("volume", out);
                req.setAttribute("rad", cylinderRadius);
                req.setAttribute("h", cylinderHeight);
                getServletContext().getRequestDispatcher("/cylinder.jsp").forward(req, resp);
                break;
            case "конус":
                double coneRadius, coneHeight;
                if (req.getParameter("radius").isEmpty()) {coneRadius = 0;}
                else { coneRadius = Double.parseDouble(req.getParameter("radius"));}

                if (req.getParameter("h").isEmpty()) { coneHeight = 0;}
                else {coneHeight = Double.parseDouble(req.getParameter("h"));}

                result = cylinderVolume(coneRadius, coneHeight) / 3; // объем конуса = объем цилиндра / 3
                out = formatter.format("%." + amount + "f", result).toString();

                req.setAttribute("volume", out);
                req.setAttribute("rad", coneRadius);
                req.setAttribute("h", coneHeight);
                getServletContext().getRequestDispatcher("/cone.jsp").forward(req, resp);
                break;
        }

    }

    private double parallelepipedVolume(double a, double b, double c) {
        return a*b*c;
    }

    private double ellipsoidVolume(double a, double b, double c) {
        return 4*a*b*c*Math.PI/3;
    }

    private double thorVolume(double thorRadius, double sechenieRadius) {
        return 2*Math.PI*Math.PI*thorRadius*sechenieRadius*sechenieRadius;
    }

    private double cylinderVolume(double r, double h) {
        return r*r*Math.PI*h;
    }

    private double tetrahedronVolume(double a) {
        return a*a*a*Math.sqrt(2)/12;
    }
}
