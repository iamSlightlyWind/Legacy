package fa.training.entities;

import fa.training.annotation.DatabaseColumn;
import fa.training.annotation.IDColumn;
import fa.training.utils.Constant;
import fa.training.utils.InputValidate;

/**
 * This class represent a Person with 5 properties {@link #id}, {@link #name}, {@link #gender}, {@link #phone}, {@link #email}
 * 
 * @author LamDT9
 */

public abstract class Person {

    /**
     * Representing the unique ID of a Person
     */
    @DatabaseColumn(name = "id")
    @IDColumn
    private Long id;

    /**
     * Representing the name of a person
     * Must be normalized by followed the rule of naming i.e (name: Duong Tung Lam)
     */
    @DatabaseColumn(name = "name")
    private String name;

    /**
     * Representing the gender of a person
     * There are only 2 valid gender: "Male" or "Female"
     */
    @DatabaseColumn(name = "gender")
    private String gender;

    /**
     * Representing the Mobile Phone number of a person
     * In this particular case, ONE Person may own ONE phone number
     */
    @DatabaseColumn(name = "phone")
    private String phone;

    /**
     * Representing the Email number of a person
     * In this particular case, ONE Person may own ONE email address
     */
    @DatabaseColumn(name = "email")
    private String email;

    /**
     * This method use to register parking pass
     */
    public void purchaseParkingPass() {
        System.out.println("Purchasing parking pass");
    }

    /**
     * Default constructor of a new Person instance
     * Using this constructor will prompt user to enter Person's properties values from keyboard
     */
    public Person(boolean promptFromKeyboard) {
        this.name = InputValidate.inputString("Enter name: ");
        this.gender = InputValidate.inputString("Enter gender: ");
        this.phone = InputValidate.inputRegex("Enter phone number: ", "Not a valid Phone number", Constant.PHONE_REGEX);
        this.email = InputValidate.inputRegex("Enter email: ", "Not a valid email address", Constant.EMAIL_REGEX);
    }

    public Person() {
    }

    /**
     * Constructor to create a Person with {@link #name}, {@link #gender}, {@link #phone}, {@link #email}
     * 
     * @param id:     Person's ID, must be unique
     * @param name:   Person's name, cannot be blank
     * @param gender: Person's gender, there are 2 valid gender: "Male" and "Female"
     * @param phone:  Person's phone number, must be valid mobile phone number
     * @param email:  Person's email, must be valid format email
     */
    public Person(Long id, String name, String gender, String phone, String email) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
    }

    /**
     * @return A String represent Person's name
     */
    public String getName() {
        return name;
    }

    /**
     * Set value for Person's {@link #name}
     * 
     * @param name: Name of a Person
     * @see Person#name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return A String represent Person's gender with expected value "Male" or "Female"
     */
    public String getGender() {
        return gender;
    }

    /**
     * Set value for Person's {@link #gender}
     * 
     * @param gender: Gender of a Person
     * @see Person#gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return A String represent Person's phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Set value for Person's {@link #phone}
     * 
     * @param phone: Phone Number of a Person
     * @see Person#phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return A String represent Person's email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set value for Person's {@link #email}
     * 
     * @param email: Email Address of a Person
     * @see Person#email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return A Long Number represent Person's id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set value for Person's {@link #email}
     * 
     * @param id: ID of a Person
     * @see Person#id
     */
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
