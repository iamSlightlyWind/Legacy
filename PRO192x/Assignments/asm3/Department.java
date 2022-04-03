public class Department { // save relevant infos about dept
    private int deptID;
    private String dept;
    private int employeeCount;

    public Department(int DeptID, String Dept) { // initial
        this.deptID = DeptID;
        this.dept = Dept;
    }

    public String getDeptName() { // for listing depts
        return this.dept;
    }

    public int getDeptID() { // for comparing with staff deptID
        return this.deptID;
    }

    public void setCount(int count) { // for updating staff count
        this.employeeCount = count;
    }

    public void toSring() { // for listing depts
        System.out.println("Department: " + this.dept);
        System.out.println("Department ID: " + this.deptID);
        System.out.println("Employee count: " + this.employeeCount);
    }
}