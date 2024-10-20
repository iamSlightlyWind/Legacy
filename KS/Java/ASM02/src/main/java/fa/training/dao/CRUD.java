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
                        .append(" set ");
                // .append("person.name = ?, ")
                // .append("person.gender = ?, ")
                // .append("person.phone = ?, ")
                // .append("person.email = ?, ");

                for (int i = 5; i < info.length - 1; i++) {
                    query.append(info[i][0]).append(" = ?");
                    if (i != info.length - 2) {
                        query.append(", ");
                    }
                }

                query.append(" from ")
                        .append(type)
                        .append(" join person on person.id = ")
                        .append(type)
                        .append(".person_id ")
                        .append(" where person.id = ? ");

                break;
        }

        CallableStatement statement = Database.connection.prepareCall(query.toString());
        setValues(statement, info);

        return statement;
    }

    default void setValues(CallableStatement statement, String[][] info) throws SQLException {
        int argCount = 1;
        int valueCount = 5;

        while (valueCount < info.length - 1) {
            switch (info[valueCount][2]) {
                case "string":
                    statement.setString(argCount, info[valueCount][1]);
                    break;
                case "double":
                case "long":
                    statement.setFloat(argCount, Float.parseFloat(info[valueCount][1]));
                    break;
            }

            argCount++;
            valueCount++;
        }

        statement.setString(argCount, info[0][1]);
    }
}