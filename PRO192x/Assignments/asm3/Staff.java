abstract class Staff {
    protected int id;
    protected int age;
    protected int factor;
    protected int allowedLeave;
    protected int joinDate;
    protected int department;
    protected String role;
    protected String name;
    
    public abstract void displayInformation();

    public abstract String getRole();

    public abstract String getStaff();

    public abstract int getDept();
}
