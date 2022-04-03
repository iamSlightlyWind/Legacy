abstract class Staff implements ICalculator{ //implement ICalc
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

    public String getStaffName(){ // for staff search by name
        return (this.name);
    }

    public int getDept() { // for staff list by dept
        return this.department;
    }

    public int getID() { // for staff search by ID
        return this.id;
    }
}
