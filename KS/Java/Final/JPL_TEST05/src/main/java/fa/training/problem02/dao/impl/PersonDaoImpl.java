package fa.training.problem02.dao.impl;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import fa.training.problem02.dao.PersonDao;
import fa.training.problem02.entities.Person;
import fa.training.problem02.utils.Database;

public class PersonDaoImpl implements PersonDao {

    public void save(Person Person) {
        try {
            String query = "INSERT INTO Person (person_name, bod) VALUES (?, ?)";
            PreparedStatement statement = Database.getConnection().prepareStatement(query);
            statement.setString(1, Person.getPersonName());
            statement.setDate(2, Person.getBod());
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean personExist(int personId) {
        try {
            String query = "{call personExist(?, ?)}";
            CallableStatement statement = Database.getConnection().prepareCall(query);
            statement.setInt(1, personId);
            statement.registerOutParameter(2, java.sql.Types.BIT);
            statement.execute();
            return statement.getBoolean(2) == true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Person getPersonById(int personId) {
        Person person = null;

        try {
            String query = "select personid, person_name, bod from person where personid = ?";
            PreparedStatement statement = Database.getConnection().prepareStatement(query);
            statement.setInt(1, personId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                person = new Person();
                person.setPersonId(resultSet.getInt("personid"));
                person.setPersonName(resultSet.getString("person_name"));
                person.setBod(resultSet.getDate("bod"));
            }
            return person;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
