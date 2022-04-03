public class Department {
    private int deptID;
    private String dept;
    private int employeeCount;

    public Department(int DeptID, String Dept){
        this.deptID = DeptID;
        this.dept = Dept;
    }

    public String getDeptName(){
        return dept;
    }

    public void toSring(){
        System.out.println("Department: " + this.dept);
        System.out.println("Department ID: " + this.deptID);
        System.out.println("Employee count: " + this.employeeCount);
    }
}