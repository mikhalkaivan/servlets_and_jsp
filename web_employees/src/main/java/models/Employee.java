package main.java.models;

import main.java.utils.SessionFactoryWrapper;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "password")
    private String password;
    @Column(name = "department")
    private String department;
    @Column(name = "position")
    private String position;
    @Column(name = "salary")
    private double salary;

    public Employee() {
    }

    public Employee(String firstName, String lastName, String password, String department, String position, double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.department = department;
        this.position = position;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getQueryStatus() {
        Session session = SessionFactoryWrapper.getInstance().getSessionFactory().openSession();
        List<QueryForHoliday> queriesForHoliday = null;
        int zero_index = 0;
        String status;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(QueryForHoliday.class)
                    .add(Restrictions.eq("firstName", this.firstName))
                    .add(Restrictions.eq("lastName", this.lastName))
                    .add(Restrictions.eq("department", this.department));
            queriesForHoliday = criteria.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            if (queriesForHoliday.size() == 0) {
                QueryForHoliday mockQuery = new QueryForHoliday(firstName, lastName, department);
                queriesForHoliday.add(mockQuery);
            }
            session.close();
            if (queriesForHoliday.size() == 0) {
                status = "DEFAULT";
            } else status = queriesForHoliday.get(zero_index).getHolidayQuery();
            return status;
        }
    }

    public String getManagerResponse() {
        Session session = SessionFactoryWrapper.getInstance().getSessionFactory().openSession();
        List<QueryForHoliday> queriesForHoliday = null;
        int zero_index = 0;
        String response;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(QueryForHoliday.class)
                    .add(Restrictions.eq("firstName", this.firstName))
                    .add(Restrictions.eq("lastName", this.lastName))
                    .add(Restrictions.eq("department", this.department));
            queriesForHoliday = criteria.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            if (queriesForHoliday.size() == 0) {
                QueryForHoliday mockQuery = new QueryForHoliday(firstName, lastName, department);
                queriesForHoliday.add(mockQuery);
            }
            session.close();
            if (queriesForHoliday.size() == 0) {
                response = "DEFAULT";
            } else response = queriesForHoliday.get(zero_index).getManagerResponce();
            return response;
        }
    }


    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", department='" + department + '\'' +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                '}';
    }
}
