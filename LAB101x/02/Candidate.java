public class Candidate {

    protected String firstName, lastName, type;
    protected String birthYear, number, email, address;
    protected int ID;

    public void setName(String candFirstName, String candLastName) {
        firstName = candFirstName;
        lastName = candLastName;
    }

    public void setInfo(String candBirthYear, String candNumber, String candEmail, String candAddress) {
        birthYear = candBirthYear;
        number = candNumber;
        email = candEmail;
        address = candAddress;
    }

    public String getInfo(String info) {
        switch (info) {
            case "name":
                return (firstName + " " + lastName);
        }

        return "";
    }

    public void setID(int candID) {
        ID = candID;
    }
}