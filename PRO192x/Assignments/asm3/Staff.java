abstract class Staff implements ICalculator{
    protected int id;
    protected int age;
    protected int factor;
    protected int allowedLeave;
    protected int joinDate;
    protected int department;
    protected int salary;
    protected String role;
    protected String name;
    
    public abstract void displayInformation();

    public String getStaffName(){
        return (this.name);
    }

    public String getRole() {
        return this.role;
    }

    public int getDept() {
        return this.department;
    }

    public int getID() {
        return this.id;
    }
}
