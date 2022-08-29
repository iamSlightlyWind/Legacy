public class Intern extends Candidate {

    protected String major;
    protected String semester;
    protected String uni;

    public void setIntern(String candMajor, String candSemester, String candUni) {
        major = candMajor;
        semester = candSemester;
        uni = candUni;
        super.setType("2");
    }
}