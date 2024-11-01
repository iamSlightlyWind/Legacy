package fa.training.problem02.entities;

import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Person {
    private int personId;
    private String personName;
    private Date bod;
}
