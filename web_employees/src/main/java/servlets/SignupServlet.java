package main.java.servlets;

import main.java.models.Employee;
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


public class SignupServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NumberFormatException {
        checkInputAndProcessing(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private void checkInputAndProcessing(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String password = request.getParameter("password");
        String department = request.getParameter("department");
        String position = request.getParameter("position");
        double salary = Double.parseDouble(request.getParameter("salary"));
        Employee employee = new Employee(firstName, lastName, password, department, position, salary);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        if (!ProfileEmployeeService.isProfileAlreadyExists(employee) && !ProfileEmployeeService.isEmptyField(employee)) {
            checkTypeOfWorkerPosition(request, response, employee);
        } else {
            incorrectInputProcessing(request, response, employee);
        }
    }


    private void checkTypeOfWorkerPosition(HttpServletRequest request, HttpServletResponse response, Employee employee) {
        RequestDispatcher dispatcher;
        try {
            DatabaseService.saveEmployee(employee);
            DatabaseService.createMockQueryForHoliday(employee);
            if (employee.getPosition().equalsIgnoreCase("worker")) {
                Map<String, Object> data = DataContainer.getDataForWorkerPage(employee);
                request.setAttribute("data", data);
                dispatcher = request.getRequestDispatcher("/worker.jsp");
                dispatcher.forward(request, response);
            } else if (employee.getPosition().equalsIgnoreCase("manager")) {
                Map<String, Object> data = DataContainer.getDataForManagerPage(employee);
                request.setAttribute("data", data);
                dispatcher = request.getRequestDispatcher("/manager.jsp");
                dispatcher.forward(request, response);
            } else {
                Map<String, Object> data = DataContainer.getDataForTopManagerPage(employee);
                request.setAttribute("data", data);
                dispatcher = request.getRequestDispatcher("/top_manager.jsp");
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void incorrectInputProcessing(HttpServletRequest request, HttpServletResponse response, Employee employee) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        RequestDispatcher dispatcher;
        if (ProfileEmployeeService.isProfileAlreadyExists(employee)) {
            dispatcher = request.getRequestDispatcher("/signup.jsp");
            dispatcher.include(request, response);
            out.println("<p>This profile already exists!</p>");
            out.close();
        } else if (ProfileEmployeeService.isEmptyField(employee)) {
            dispatcher = request.getRequestDispatcher("/signup.jsp");
            dispatcher.include(request, response);
            out.println("<p>Fields cannot be empty!</p>");
            out.close();
        }
    }


}
