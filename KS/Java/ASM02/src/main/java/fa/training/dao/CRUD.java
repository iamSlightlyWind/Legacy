package fa.training.dao;

import java.sql.CallableStatement;
import java.sql.SQLException;

import fa.training.entities.Student;
import fa.training.utils.Database;

public interface CRUD {

    default void update(Student current) throws SQLException {
        CallableStatement statement = build(current.info(), "update");
        statement.execute();
    }

    default CallableStatement build(String[][] info, String action) throws SQLException {
        String type = info[info.length - 1][1];
        StringBuilder query = new StringBuilder();

        switch (action) {
            case "update":
                query.append(" update ")
                        .append(type)
                        .append(" set")
                        .append("person.name = ?, ")
                        .append("person.gender = ?, ")
                        .append("person.phone = ?, ")
                        .append("person.email = ?, ");

                for (int i = 4; i < info.length - 1; i++) {
                    query.append(info[i][0] + " = ?");
                    if (i != info.length - 2) {
                        query.append(", ");
                    }
                }

                query.append(" where person.id = ? ")
                        .append("join person on person.id = ")
                        .append(type)
                        .append(".person_id ");

                break;
        }

        CallableStatement statement = Database.connection.prepareCall(query.toString());
        setValues(statement, info);

        return statement;
    }

    default void setValues(CallableStatement statement, String[][] info) throws SQLException {
        int count = 1;

        while (count < info.length - 1) {
            switch (info[count][2]) {
                case "string":
                    statement.setString(count + 1, info[count][1]);
                    break;
                case "double":
                    statement.setFloat(count + 1, Float.parseFloat(info[count][1]));
                    break;
            }
            count++;
        }

        statement.setString(count + 1, info[0][1]);
    }
}