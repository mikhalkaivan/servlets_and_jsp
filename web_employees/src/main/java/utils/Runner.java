package main.java.utils;

public class Runner {

    public static void main(String [] args) throws Exception {
        String dep = DatabaseService.getDepartment("Semen","Semenov","aaa");
        System.out.println(dep);
        }
}
