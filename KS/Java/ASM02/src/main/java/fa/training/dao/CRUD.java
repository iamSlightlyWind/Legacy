package fa.training.dao;

import java.sql.CallableStatement;
import java.sql.SQLException;

import fa.training.entities.Student;
import fa.training.utils.Database;

public interface CRUD {

    default void update(Student current) throws SQLException {
        CallableStatement statement = build(current.info(), "updateStudent");
        statement.execute();
    }

    default CallableStatement build(String[][] info, String action) throws SQLException {
        String type = info[info.length - 1][1];
        StringBuilder query = new StringBuilder();

        switch (action) {
            case "updateStudent":
                query.append(" update ")
                        .append(type)
                        .append(" set ");

                for (int i = 1; i < info.length - 1; i++) {
                    query.append(info[i][0]).append(" = ?");
                    if (i != info.length - 2) {
                        query.append(", ");
                    }
                }

                query.append(" where id = ? ");
                break;

            case "updatePerson":
                query.append(" update person set ")
                        .append("name = ?, ")
                        .append("gender = ?, ")
                        .append("phone = ?, ")
                        .append("email = ? ")
                        .append("where id = ? ");
                break;

        }

        CallableStatement statement = Database.connection.prepareCall(query.toString());
        setValues(statement, info);

        return statement;
    }

    default void setValues(CallableStatement statement, String[][] info) throws SQLException {
        for (int i = 1; i < info.length - 1; i++) {
            switch (info[i][2]) {
                case "long":
                    statement.setLong(i, Long.parseLong(info[i][1]));
                    break;
                case "string":
                    statement.setString(i, info[i][1]);
                    break;
                case "double":
                    statement.setFloat(i, Float.parseFloat(info[i][1]));
                    break;
            }
        }
        statement.setLong(info.length - 1, Long.parseLong(info[0][1]));
    }
}