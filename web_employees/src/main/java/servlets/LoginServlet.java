package main.java.servlets;

import main.java.utils.DataContainer;
import main.java.utils.DatabaseService;
import main.java.utils.ProfileEmployeeService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;


public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String password = request.getParameter("password");
        checkInputAndProcessing(request, response, firstName, lastName, password);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private void checkInputAndProcessing(HttpServletRequest request, HttpServletResponse response, String firstName, String lastName, String password) throws ServletException, IOException {
        if (ProfileEmployeeService.isCredentialsCorrect(firstName, lastName, password)){
            correctCredentialsProcessing(request, response, firstName, lastName, password);
        } else {
            incorrectCredentialsProcessing(request, response, firstName, lastName, password);
        }
    }


    private void correctCredentialsProcessing(HttpServletRequest request, HttpServletResponse response, String firstName, String lastName, String password) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        String position = DatabaseService.getPosition(firstName, lastName, password);
        if(position.equalsIgnoreCase("worker")){
            Map<String, Object> data = DataContainer.getDataForWorkerPage(firstName, lastName, password);
            request.setAttribute("data", data);
            dispatcher = request.getRequestDispatcher("/worker.jsp");
            dispatcher.forward(request, response);
        } else if (position.equalsIgnoreCase("manager")){
            Map<String, Object> data = DataContainer.getDataForManagerPage(firstName, lastName, password);
            request.setAttribute("data", data);
            dispatcher = request.getRequestDispatcher("/manager.jsp");
            dispatcher.forward(request, response);
        } else {
            Map<String, Object> data = DataContainer.getDataForTopManagerPage(firstName, lastName);
            request.setAttribute("data", data);
            dispatcher = request.getRequestDispatcher("/top_manager.jsp");
            dispatcher.forward(request, response);
        }

    }

    private void incorrectCredentialsProcessing(HttpServletRequest request, HttpServletResponse response, String firstName, String lastName, String password) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out  = response.getWriter();
        RequestDispatcher dispatcher;
        if (ProfileEmployeeService.isEmptyField(firstName, lastName, password)){
            dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.include(request, response);
            out.println("<p>Fields cannot be empty!</p>");
            out.close();
        } else if (ProfileEmployeeService.isIncorrectPassword(firstName, lastName, password)){
            dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.include(request, response);
            out.println("<p>Incorrect password!</p>");
            out.close();
        } else {
            dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.include(request, response);
            out.println("<p>Incorrect credentials!</p>");
            out.close();
        }
    }


}
