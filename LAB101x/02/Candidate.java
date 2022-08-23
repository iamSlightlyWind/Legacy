public class Candidate {
    protected int ID;
    protected String firstName, lastName, type;
    protected String birthYear, number, email;

    public Candidate(boolean isExperienced, int candID) {
        if (isExperienced)
            type = "Experienced";

        if (!isExperienced)
            type = "Fresher";
        
        ID = candID;
    }

    public void setName(String candFirstName, String candLastName) {
        firstName = candFirstName;
        lastName = candLastName;
    }

    public void setInfo(String candBirthYear, String candNumber, String candEmail) {
        birthYear = candBirthYear;
        number = candNumber;
        email = candEmail;
    }
}