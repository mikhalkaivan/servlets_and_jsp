package main.java.utils;

import main.java.models.Employee;
import main.java.models.QueryForHoliday;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class DatabaseService {


    public static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/stuff?serverTimezone=UTC";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "admin";


    public static void saveEmployee(Employee employee) {
        Session session = SessionFactoryWrapper.getInstance().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(employee);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void saveEmployee(String firstName, String lastName, String password, String department, String position, double salary) {

        Session session = SessionFactoryWrapper.getInstance().getSessionFactory().openSession();
        Employee employee = new Employee(firstName, lastName, password, department, position, salary);

        try {
            session.beginTransaction();
            session.save(employee);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    public static void saveQueryForHoliday(String firstName, String lastName, String department) {
        Session session = SessionFactoryWrapper.getInstance().getSessionFactory().openSession();
        QueryForHoliday queryForHoliday = new QueryForHoliday(firstName, lastName, department);
        try {
            session.beginTransaction();
            session.save(queryForHoliday);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    public static void createMockQueryForHoliday(Employee employee) {
        Session session = SessionFactoryWrapper.getInstance().getSessionFactory().openSession();
        QueryForHoliday queryForHoliday = new QueryForHoliday(employee);
        try {
            session.beginTransaction();
            session.save(queryForHoliday);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static Connection getConnection() {
        try {
            Class.forName(MYSQL_DRIVER);
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static List<Employee> getAllEmployees() {
        Session session = SessionFactoryWrapper.getInstance().getSessionFactory().openSession();
        List<Employee> allEmployees = null;
        try {
            Criteria criteria = session.createCriteria(Employee.class);
            allEmployees = criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        } return allEmployees;
    }

    public static List<Employee> getAllWorkers() {
        Session session = SessionFactoryWrapper.getInstance().getSessionFactory().openSession();
        List<Employee> allWorkers = null;
        try {
            Criteria criteria = session.createCriteria(Employee.class)
                    .add(Restrictions.eq("position", "worker"));
            allWorkers = criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            return allWorkers;
        }
    }

    public static List getAllManagers() {
        Session session = SessionFactoryWrapper.getInstance().getSessionFactory().openSession();
        List<Employee> allManagers = null;
        try {
            Criteria criteria = session.createCriteria(Employee.class)
                    .add(Restrictions.eq("position", "manager"));
            allManagers = criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            return allManagers;
        }
    }

    public static List<Employee> getAllTopManagers() {
        Session session = SessionFactoryWrapper.getInstance().getSessionFactory().openSession();
        List<Employee> allTopManagers = null;
        try {
            Criteria criteria = session.createCriteria(Employee.class)
                    .add(Restrictions.eq("position", "manager"));
            allTopManagers = criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            return allTopManagers;
        }
    }

    public static List<Employee> getAllFromDepartment(String department) {
        Session session = SessionFactoryWrapper.getInstance().getSessionFactory().openSession();
        List<Employee> allFromDepartment = null;
        try {
            Criteria criteria = session.createCriteria(Employee.class)
                    .add(Restrictions.eq("department", department));
            allFromDepartment = criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            return allFromDepartment;
        }
    }

    public static List<Employee> getAllWorkersFromDepartment(String department) {
        Session session = SessionFactoryWrapper.getInstance().getSessionFactory().openSession();
        List<Employee> allWorkersFromDepartment = null;
        try {
            Criteria criteria = session.createCriteria(Employee.class)
                    .add(Restrictions.eq("department", department))
                    .add(Restrictions.eq("position", "worker"));
            allWorkersFromDepartment = criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            return allWorkersFromDepartment;
        }
    }

    public static List<Employee> getAllManagersFromDepartment(String department) {
        Session session = SessionFactoryWrapper.getInstance().getSessionFactory().openSession();
        List<Employee> allManagersFromDepartment = null;
        try {
            Criteria criteria = session.createCriteria(Employee.class)
                    .add(Restrictions.eq("department", department))
                    .add(Restrictions.eq("position", "manager"));
            allManagersFromDepartment = criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            return allManagersFromDepartment;
        }
    }

    public static Employee getWorker(String firstName, String lastName, String department) {
        Session session = SessionFactoryWrapper.getInstance().getSessionFactory().openSession();
        List<Employee> workers = null;
        int zero_index = 0;
        try {
            Criteria criteria = session.createCriteria(Employee.class)
                    .add(Restrictions.eq("firstName", firstName))
                    .add(Restrictions.eq("lastName", lastName))
                    .add(Restrictions.eq("department", department))
                    .add(Restrictions.eq("position", "worker"));
            workers = criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            return workers.get(zero_index);
        }
    }

    public static Employee getManager(String firstName, String lastName, String department) {
        Session session = SessionFactoryWrapper.getInstance().getSessionFactory().openSession();
        List<Employee> managers = null;
        int zero_index = 0;
        try {
            Criteria criteria = session.createCriteria(Employee.class)
                    .add(Restrictions.eq("firstName", firstName))
                    .add(Restrictions.eq("lastName", lastName))
                    .add(Restrictions.eq("department", department))
                    .add(Restrictions.eq("position", "manager"));
            managers = criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            return managers.get(zero_index);
        }
    }

    public static Employee getTopManager(String firstName, String lastName) {
        Session session = SessionFactoryWrapper.getInstance().getSessionFactory().openSession();
        List<Employee> managers = null;
        int zero_index = 0;
        try {
            Criteria criteria = session.createCriteria(Employee.class)
                    .add(Restrictions.eq("firstName", firstName))
                    .add(Restrictions.eq("lastName", lastName))
                    .add(Restrictions.eq("position", "top_manager"));
            managers = criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            return managers.get(zero_index);
        }
    }

    public static String getDepartment(String firstName, String lastName, String password) {
        Session session = SessionFactoryWrapper.getInstance().getSessionFactory().openSession();
        List<Employee> workers = null;
        int zero_index = 0;
        try {
            Criteria criteria = session.createCriteria(Employee.class)
                    .add(Restrictions.eq("firstName", firstName))
                    .add(Restrictions.eq("lastName", lastName))
                    .add(Restrictions.eq("password", password));
            workers = criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            return workers.get(zero_index).getDepartment();
        }
    }

    public static String getPosition(String firstName, String lastName, String password) {
        Session session = SessionFactoryWrapper.getInstance().getSessionFactory().openSession();
        List<Employee> workers = null;
        int zero_index = 0;
        try {
            Criteria criteria = session.createCriteria(Employee.class)
                    .add(Restrictions.eq("firstName", firstName))
                    .add(Restrictions.eq("lastName", lastName))
                    .add(Restrictions.eq("password", password));
            workers = criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            return workers.get(zero_index).getPosition();
        }
    }

    public static List<QueryForHoliday> getAllQueriesForHoliday() {
        Session session = SessionFactoryWrapper.getInstance().getSessionFactory().openSession();
        List<QueryForHoliday> allQueries = null;
        try {
            Criteria criteria = session.createCriteria(QueryForHoliday.class);
            allQueries = criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            return allQueries;
        }
    }


    public static List<QueryForHoliday> getQueriesForHolidayInDepartment(String department) {
        Session session = SessionFactoryWrapper.getInstance().getSessionFactory().openSession();
        List<QueryForHoliday> allQuerisInDepartment = null;
        try {
            Criteria criteria = session.createCriteria(QueryForHoliday.class)
                    .add(Restrictions.eq("department", department));
            allQuerisInDepartment = criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            return allQuerisInDepartment;
        }
    }

    public static QueryForHoliday getQueryForHoliday(String firstName, String lastName, String department) {
        Session session = SessionFactoryWrapper.getInstance().getSessionFactory().openSession();
        List<QueryForHoliday> queriesForHoliday = null;
        int zero_index = 0;
        try {
            Criteria criteria = session.createCriteria(QueryForHoliday.class)
                    .add(Restrictions.eq("firstName", firstName))
                    .add(Restrictions.eq("lastName", lastName))
                    .add(Restrictions.eq("department", department));
            queriesForHoliday = criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (queriesForHoliday.size() == 0){
                QueryForHoliday mockQuery = new QueryForHoliday(firstName, lastName, department);
                queriesForHoliday.add(mockQuery);
            }
            session.close();
            return queriesForHoliday.get(zero_index);
        }
    }


    public static int getIdForQueryTable(String firstName, String lastName, String department) throws Exception {
        int zero_index = 0;
        Session session = SessionFactoryWrapper.getInstance().getSessionFactory().openSession();
        List<QueryForHoliday> queriesForHoliday = null;
        try {
            Criteria criteria = session.createCriteria(QueryForHoliday.class)
                    .add(Restrictions.eq("firstName", firstName))
                    .add(Restrictions.eq("lastName", lastName))
                    .add(Restrictions.eq("department", department));
            queriesForHoliday = criteria.list();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            session.close();
            return queriesForHoliday.get(zero_index).getId();
        }
    }


    public static void sendQuery(String firstName, String lastName, String department, String query) throws Exception {
        String managerResponse = "PENDING";
        int id = DatabaseService.getIdForQueryTable(firstName, lastName, department);
        Session session = SessionFactoryWrapper.getInstance().getSessionFactory().openSession();
        try {
            QueryForHoliday rawQuery = session.load(QueryForHoliday.class, id);
            rawQuery.setHolidayQuery(query);
            rawQuery.setManagerResponce(managerResponse);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}

