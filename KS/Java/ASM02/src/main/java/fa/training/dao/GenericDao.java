package fa.training.dao;

import fa.training.annotation.DatabaseColumn;
import fa.training.annotation.DatabaseTable;
import fa.training.annotation.IDColumn;
import fa.training.utils.JavaM301DbContext;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public abstract class GenericDao<T, ID> {

    private final Class<T> entityClass;
    private final Class<ID> idClass;

    public GenericDao() {
        this.entityClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.idClass = (Class<ID>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    /**
     * Handles the action and returns results.
     *
     * @param action the action to handle, e.g., "findAll".
     * @return a list of results or null if unsupported.
     */
    public List<T> handle(String action) {
        switch (action) {
            case "findAll":
                return findAll();
        }
        return null;
    }

    /**
     * Handles the action on the entity.
     *
     * @param action the action to handle. Supported actions: "findById", "insert", "update", "delete".
     * @param entity the entity to process.
     * @return the result of the action or null if unsupported.
     */
    public T handle(String action, Object entity) throws IllegalAccessException {
        if (idClass.isInstance(entity)) {
            switch (action) {
                case "findByID":
                    return findById((ID) entity);
                default:
                    System.out.println("Unsupported action");
            }
        } else {
            handleInDepth(action, (T) entity);
        }

        return null;
    }

    public T handleInDepth(String action, T entity) throws IllegalAccessException {
        switch (action) {
            /*
             * case "findByID":
             * return findById(getID(entity));
             */

            case "insert":
                insert(entity);
                break;

            case "update":
                update(entity);
                break;

            case "delete":
                if (idClass.isInstance(entity)) {
                    delete((ID) entity);
                } else {
                    delete(getID(entity));
                }
                break;
        }
        return null;
    }

    private T findById(ID id) {
        try (Connection connection = JavaM301DbContext.getConnection()) {
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT ");
            sql.append(generateFieldForQuery());
            sql.append(" FROM ");
            sql.append(this.tableName());
            sql.append(" WHERE ");
            sql.append(this.IDCol());
            sql.append(" = ?");
            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
            setParameter(preparedStatement, 1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                T entity = this.mapResultSetToEntity(resultSet);
                return entity;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private void insert(T entity) {
        if (!tableExists() || recordExists(entity)) {
            return;
        }

        try (Connection connection = JavaM301DbContext.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery());
            List<T> values = getValues(entity, false);

            for (int i = 0; i < values.size(); i++) {
                setParameter(preparedStatement, i + 1, values.get(i));
            }
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private String insertQuery() {
        StringBuilder query = new StringBuilder();

        query.append(" insert into ").append(this.tableName()).append(" (");

        for (Field field : getFields()) {
            query.append(field.getName()).append(",");
        }
        query.deleteCharAt(query.length() - 1).append(") values (");

        for (int i = 0; i < getFields().length; i++) {
            query.append("?,");
        }
        query.deleteCharAt(query.length() - 1).append(")");
        return query.toString();
    }

    private void update(T entity) {
        if (!tableExists() || !recordExists(entity)) {
            return;
        }

        try (Connection connection = JavaM301DbContext.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery(entity));
            List<T> values = getValues(entity, true);

            for (int i = 0; i < values.size(); i++) {
                setParameter(preparedStatement, i + 1, values.get(i));
            }
            setParameter(preparedStatement, values.size() + 1, getID(entity));

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private String updateQuery(T object) {
        StringBuilder attr = new StringBuilder();

        attr.append(" update ").append(this.tableName()).append(" set ");
        for (Field field : getFields()) {
            if (!field.isAnnotationPresent(IDColumn.class)) {
                attr.append(field.getName()).append(" = ?,");
            }
        }
        attr.deleteCharAt(attr.length() - 1).append(" where ").append(IDCol()).append(" = ?");

        return attr.toString();
    }

    private void delete(ID id) {
        try (Connection connection = JavaM301DbContext.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery());
            setParameter(preparedStatement, 1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String deleteQuery() {
        StringBuilder query = new StringBuilder();
        query.append(" delete from ").append(this.tableName()).append(" where ").append(IDCol()).append(" = ?");
        return query.toString();
    }

    private List<T> findAll() {
        List<T> result = new ArrayList<>();
        try (Connection connection = JavaM301DbContext.getConnection()) {
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT ");
            sql.append(generateFieldForQuery());
            sql.append(" FROM ");
            sql.append(this.tableName());
            sql.append(" ORDER BY ");
            sql.append(this.IDCol());
            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                T entity = this.mapResultSetToEntity(resultSet);
                result.add(entity);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    private boolean recordExists(T entity) {
        try (Connection connection = JavaM301DbContext.getConnection()) {
            StringBuilder query = new StringBuilder();
            query.append("select 1 from ").append(this.tableName()).append(" where ").append(IDCol()).append(" = ? ");
            PreparedStatement preparedStatement = connection.prepareStatement(query.toString());
            setParameter(preparedStatement, 1, getID(entity));
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                System.out.println("Record exists");
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Record does not exist");
        return false;
    }

    private boolean tableExists() {
        try (Connection connection = JavaM301DbContext.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(" select 1 from information_schema.tables where table_name = ? ");
            preparedStatement.setString(1, this.tableName());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                System.out.println("Table exists");
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Table does not exist");
        return false;
    }

    private String tableName() {
        return this.entityClass.getAnnotation(DatabaseTable.class).name();
    }

    private List<T> getValues(T entity, boolean skipID) {
        List<T> values = new ArrayList<>();

        for (Field field : getFields()) {
            if (field.isAnnotationPresent(DatabaseColumn.class)) {
                if (!skipID || !field.isAnnotationPresent(IDColumn.class)) {
                    field.setAccessible(true);
                    try {
                        Object value = field.get(entity);
                        values.add((T) value);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return values;
    }

    private ID getID(T entity) throws IllegalArgumentException, IllegalAccessException {
        Map<String, Field> fieldMap = this.getFieldMap();
        String idColumnName = this.IDCol();
        Field field = fieldMap.get(idColumnName);
        field.setAccessible(true);
        ID id = (ID) field.get(entity);
        return id;
    }

    private String generateFieldForQuery() {
        Map<String, Field> fieldMap = this.getFieldMap();
        StringBuilder result = new StringBuilder();
        for (String columnName : fieldMap.keySet()) {
            result.append(columnName).append(",");
        }
        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }

    private Field[] getFields() {
        if (this.entityClass.getSuperclass() != null) {
            Field[] superClassFields = getSuperClassFields();
            Field[] entityFields = this.entityClass.getDeclaredFields();
            Field[] allFields = Arrays.copyOf(superClassFields, superClassFields.length + entityFields.length);
            System.arraycopy(entityFields, 0, allFields, superClassFields.length, entityFields.length);
            return allFields;
        }
        return this.entityClass.getDeclaredFields();
    }

    private Field[] getSuperClassFields() {
        return this.entityClass.getSuperclass().getDeclaredFields();
    }

    private Map<String, Field> getFieldMap() {
        Field[] fields = getFields();
        Map<String, Field> fieldMap = new HashMap<>();
        for (Field field : fields) {
            if (field.isAnnotationPresent(DatabaseColumn.class)) {
                DatabaseColumn column = field.getAnnotation(DatabaseColumn.class);
                fieldMap.put(column.name(), field);
            }
        }
        return fieldMap;
    }

    private String IDCol() {
        Map<String, Field> fieldMap = getFieldMap();
        for (Field field : fieldMap.values()) {
            if (field.isAnnotationPresent(IDColumn.class)) {
                DatabaseColumn column = field.getAnnotation(DatabaseColumn.class);
                return column.name();
            }
        }
        throw new RuntimeException("No @IDColumn annotation found");
    }

    private T mapResultSetToEntity(ResultSet resultSet) throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        T entity = this.entityClass.getDeclaredConstructor().newInstance();
        Map<String, Field> fieldMap = this.getFieldMap();
        for (Map.Entry<String, Field> entry : fieldMap.entrySet()) {
            String columnName = entry.getKey();
            Field field = entry.getValue();
            Object value = resultSet.getObject(columnName);
            field.setAccessible(true);
            try {
                field.set(entity, value);
            } catch (IllegalArgumentException e) {
                Long temp = ((Double) value).longValue();
                field.set(entity, temp);
            }
        }
        return entity;
    }

    private static void setParameter(PreparedStatement preparedStatement, int parameterIndex, Object value) throws SQLException {
        if (value == null) {
            preparedStatement.setNull(parameterIndex, java.sql.Types.NULL);
        } else if (value instanceof Integer) {
            preparedStatement.setInt(parameterIndex, (Integer) value);
        } else if (value instanceof Long) {
            preparedStatement.setLong(parameterIndex, (Long) value);
        } else if (value instanceof String) {
            preparedStatement.setString(parameterIndex, (String) value);
        } else if (value instanceof Double) {
            preparedStatement.setDouble(parameterIndex, (Double) value);
        } else if (value instanceof Float) {
            preparedStatement.setFloat(parameterIndex, (Float) value);
        } else if (value instanceof Boolean) {
            preparedStatement.setBoolean(parameterIndex, (Boolean) value);
        } else if (value instanceof java.sql.Date) {
            preparedStatement.setDate(parameterIndex, (java.sql.Date) value);
        } else if (value instanceof java.sql.Timestamp) {
            preparedStatement.setTimestamp(parameterIndex, (java.sql.Timestamp) value);
        } else if (value instanceof java.sql.Time) {
            preparedStatement.setTime(parameterIndex, (java.sql.Time) value);
        } else {
            preparedStatement.setObject(parameterIndex, value);
        }
    }
}
