public class Experienced extends Candidate {
    public Experienced(boolean isExperienced, int ID) {
        super(isExperienced, ID);
    }

    protected String skill, exp;

    public void setExperienced(String yearsOfExp, String candSkill) {
        exp = yearsOfExp;
        skill = candSkill;
    }
}
