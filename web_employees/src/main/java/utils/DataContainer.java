package main.java.utils;

import main.java.models.Employee;
import main.java.models.QueryForHoliday;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataContainer {

    static Map<String, Object> data;
    List<Object> params;
    List<Employee> employees;
    Employee employee;

    public DataContainer(List<Employee> employees, Employee employee) {
        this.employees = employees;
        this.employee = employee;
    }

    public DataContainer(List<Object> params, List<Employee> employees, Employee employee) {
        this.params = params;
        this.employees = employees;
        this.employee = employee;
    }

    public static Map<String, Object> getDataForWorkerPage(String firstName, String lastName, String password) {
        data = new HashMap<String, Object>();
        String department = DatabaseService.getDepartment(firstName, lastName, password);
        Employee myself = DatabaseService.getWorker(firstName, lastName, department);
        QueryForHoliday queryForHoliday = DatabaseService.getQueryForHoliday(firstName, lastName, department);
        data.put("myself", myself);
        data.put("query", queryForHoliday);
        return data;
    }

    public static Map<String, Object> getDataForWorkerPage(Employee employee) {
        data = new HashMap<String, Object>();
        QueryForHoliday queryForHoliday = new QueryForHoliday(employee.getFirstName(), employee.getLastName(), employee.getDepartment());
        data.put("myself", employee);
        data.put("query", queryForHoliday);
        return data;
    }

    public static Map<String, Object> getDataForManagerPage(String firstName, String lastName, String password) {
        data = new HashMap<String, Object>();
        String department = DatabaseService.getDepartment(firstName, lastName, password);
        List<Employee> employees = DatabaseService.getAllFromDepartment(department);
        List<QueryForHoliday> queriesForHolidays = DatabaseService.getQueriesForHolidayInDepartment(department);
        Employee myself = DatabaseService.getManager(firstName, lastName, department);
        data.put("myself", myself);
        data.put("listEmployees", employees);
        data.put("queries", queriesForHolidays);
        return data;
    }

    public static Map<String, Object> getDataForManagerPage(Employee employee) {
        data = new HashMap<String, Object>();
        List<Employee> employees = DatabaseService.getAllFromDepartment(employee.getDepartment());
        data.put("myself", employee);
        data.put("listEmployees", employees);
        return data;
    }


    public static Map<String, Object> getDataForTopManagerPage(String firstName, String lastName) {
        data = new HashMap<String, Object>();
        List<Employee> employees = DatabaseService.getAllEmployees();
        List<QueryForHoliday> queriesForHolidays = DatabaseService.getAllQueriesForHoliday();
        Employee myself = DatabaseService.getTopManager(firstName, lastName);
        data.put("myself", myself);
        data.put("listEmployees", employees);
        data.put("queries", queriesForHolidays);
        return data;
    }

    public static Map<String, Object> getDataForTopManagerPage(Employee employee) {
        data = new HashMap<String, Object>();
        List<Employee> employees = DatabaseService.getAllEmployees();
        data.put("myself", employee);
        data.put("listEmployees", employees);
        return data;
    }


    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

}
