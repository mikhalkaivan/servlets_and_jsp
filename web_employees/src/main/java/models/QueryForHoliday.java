package main.java.models;

import javax.persistence.*;

@Entity
@Table(name = "queries_for_holidays")
public class QueryForHoliday {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "department")
    private String department;
    @Column(name = "holiday_query")
    private String holidayQuery;
    @Column(name = "manager_response")
    private String managerResponce;

    public QueryForHoliday() {
    }

    public QueryForHoliday(Employee employee) {
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.department = employee.getDepartment();
        this.holidayQuery = "DEFAULT";
        this.managerResponce = "DEFAULT";
    }

    public QueryForHoliday(String firstName, String lastName, String department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.holidayQuery = "DEFAULT";
        this.managerResponce = "DEFAULT";
    }

    public QueryForHoliday(String firstName, String lastName, String department, String holidayQuery) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.managerResponce = "PENDING";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return firstName;
    }

    public void setFirst_name(String first_name) {
        this.firstName = first_name;
    }

    public String getLast_name() {
        return lastName;
    }

    public void setLast_name(String last_name) {
        this.lastName = last_name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getHolidayQuery() {
        return holidayQuery;
    }

    public void setHolidayQuery(String holidayQuery) {
        this.holidayQuery = holidayQuery;
    }

    public String getManagerResponce() {
        return managerResponce;
    }

    public void setManagerResponce(String managerResponce) {
        this.managerResponce = managerResponce;
    }

    @Override
    public String toString() {
        return "QueryForHoliday{" +
                "id=" + id +
                ", first_name='" + firstName + '\'' +
                ", last_name='" + lastName + '\'' +
                ", department='" + department + '\'' +
                ", holidayQuery='" + holidayQuery + '\'' +
                ", managerResponce='" + managerResponce + '\'' +
                '}';
    }
}
