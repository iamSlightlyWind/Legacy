package fa.training.problem01.entities;

import java.util.Date;

public class Author {
    private int id;
    private String firstName;
    private String lastName;
    private Date dob;
    private String gender;
    private String desc;

    public Author() {
    }

    public Author(int id, String firstName, String lastName, Date dob, String gender, String desc) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.gender = gender;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDob() {
        return dob;
    }

    public String getGender() {
        return this.gender;
    }

    public String getDesc() {
        return desc;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setGender(String gender){
        this.gender = gender;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
