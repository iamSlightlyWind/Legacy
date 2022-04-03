public interface ICalculator {
    public int calculateSalary(); // calculate salary

    public abstract String getStaff(); // print staff name with role

    public abstract String getStaffName(); // for staff search by name

    public abstract int getDept(); // for staff list by dept

    public abstract int getID(); // for staff search by ID
}