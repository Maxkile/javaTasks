package ru.skillbench.tasks.basics.entity;

public class EmployeeImpl implements Employee {

    private String firstName;
    private String lastName;
    private int salary = 1000;
    private Employee manager;//above standing manager

    public EmployeeImpl(String first, String last,int salary,Employee manager){
        this.firstName = first;
        this.lastName = last;
        this.salary = salary;
        this.manager = manager;
    }


    //constructor made specially for top manager
    public EmployeeImpl(String first, String last,int salary){
        this.firstName = first;
        this.lastName = last;
        this.salary = salary;
        this.manager = null;
    }

    public EmployeeImpl(){}


    @Override
    public int getSalary() {
        return salary;
    }

    @Override
    public void increaseSalary(int value) {
        this.salary+=value;
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getFullName() {
        return (this.firstName + " " + this.lastName);
    }

    @Override
    public void setManager(Employee manager) {
        this.manager = manager;
    }

    @Override
    public String getManagerName() {
        if (manager == null){
            return "No manager";
        }
        else return manager.getFullName();
    }

    @Override
    public Employee getTopManager() {
        if (manager == null) return this;
        else return manager.getTopManager();
    }

    public static void main(String[] args) {
        Employee empl1 = new EmployeeImpl("Karl","von Habsburg",10000,null);
        Employee empl2 = new EmployeeImpl("Felippe Maria","Visconti",2000,empl1);
        Employee empl3 = new EmployeeImpl("Poor","Citizen",3000,empl2);
        Employee empl4 = new EmployeeImpl("Very Poor","Citizen",100);

        Employee empl = empl4.getTopManager();
        System.out.print(empl4.getManagerName());
    }
}
