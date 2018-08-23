package main.java.utils;

import main.java.models.Employee;

import java.util.List;

public class ProfileEmployeeService {

    public static boolean isProfileAlreadyExists(Employee employee){
        boolean exists=false;
        List<Employee> employees = DatabaseService.getAllEmployees();
        for(Employee employee1: employees){
            if (employee1.getFirstName().equals(employee.getFirstName()) && employee1.getLastName().equals(employee.getLastName())
                    && employee1.getDepartment().equals(employee.getDepartment()) && employee1.getPosition().equals(employee.getPosition())
                    && employee1.getSalary()==employee.getSalary()){
                exists = true;
            } else {
                if(isEmptyField(employee)){
                    System.out.println("Fields cannot be empty!");
                }
            }
        }
        return exists;
    }

    public static boolean isCredentialsCorrect(String firstName, String lastName, String password) {
        boolean isCredentialsCorrect = false;
        List<Employee> employees = DatabaseService.getAllEmployees();
        for (Employee employee1 : employees) {
            if (employee1.getFirstName().equalsIgnoreCase(firstName) && employee1.getLastName().equalsIgnoreCase(lastName) && employee1.getPassword().equals(password)) {
                isCredentialsCorrect = true;
            }
        } return isCredentialsCorrect;
    }

    public static boolean isIncorrectPassword(String firstName, String lastName, String password){
        boolean isIncorrectPassword = false;
        List<Employee> employees = DatabaseService.getAllEmployees();
        for (Employee employee1 : employees) {
            if (employee1.getFirstName().equalsIgnoreCase(firstName) && employee1.getLastName().equalsIgnoreCase(lastName) && !employee1.getPassword().equals(password)) {
                isIncorrectPassword = true;
            }
        } return isIncorrectPassword;
    }

    public static boolean isLoginSuccessful(String firstName, String lastName, String password){
        boolean loginSuccessful = false;
        List<Employee> employees = DatabaseService.getAllEmployees();
        for(Employee employee1: employees){
            if (employee1.getFirstName().equals(firstName) && employee1.getLastName().equals(lastName) && employee1.getPassword().equals(password)){
                loginSuccessful = true;
            } else if (isEmptyField(firstName, lastName, password)){
                System.out.println("Fields cannot be empty!");
                loginSuccessful = false;
            } else return false;
        }
        return loginSuccessful;
    }

    public static boolean isEmptyField(String firstName, String lastName, String password){
        boolean isEmptyField = false;
        if (firstName == "" || lastName == "" || password == "" ) isEmptyField = true;
        return isEmptyField;
    }

    public static boolean isEmptyField(Employee employee){
        String firstName = employee.getFirstName();
        String lastName = employee.getLastName();
        String password = employee.getPassword();
        String department = employee.getDepartment();
        String position = employee.getPosition();
        double salary = employee.getSalary();
        boolean isEmptyField;
        if (firstName == "" || lastName == "" || password == "" || department == ""
                || position == "" || salary == 0) isEmptyField = true;
        else isEmptyField = false;
        return isEmptyField;
    }

}
