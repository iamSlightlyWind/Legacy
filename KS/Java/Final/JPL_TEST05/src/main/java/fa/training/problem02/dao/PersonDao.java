package fa.training.problem02.dao;

import fa.training.problem02.entities.Person;

public interface PersonDao {
    public void save(Person person);
    public boolean personExist(int personId);
    public Person getPersonById(int personId);
    
}