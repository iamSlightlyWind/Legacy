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

@SuppressWarnings("unchecked")
public abstract class GenericDao<T, ID> {

    public Class<T> entityClass;
    public Class<ID> idClass;

    public GenericDao() {
        this.entityClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.idClass = (Class<ID>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    public List<T> findAll() {
        List<T> result = new ArrayList<>();
        try (Connection connection = JavaM301DbContext.getConnection()) {
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT ");
            sql.append(generateFieldForQuery());
            sql.append(" FROM ");
            sql.append(this.getTableNameFromAnnotation());
            sql.append(" ORDER BY ");
            sql.append(this.getIdColumnName());
            System.out.println(sql.toString());
            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                T entity = this.mapResultSetToEntity(resultSet);
                result.add(entity);
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public Optional<T> findById(ID id) {
        try (Connection connection = JavaM301DbContext.getConnection()) {
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT ");
            sql.append(generateFieldForQuery());
            sql.append(" FROM ");
            sql.append(this.getTableNameFromAnnotation());
            sql.append(" WHERE ");
            sql.append(this.getIdColumnName());
            sql.append(" = ?");
            System.out.println(sql.toString());
            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
            setParameter(preparedStatement, 1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                T entity = this.mapResultSetToEntity(resultSet);
                return Optional.of(entity);
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    // VALUES (?, ?, ?, ?)
    public T insert(T entity) {
        return null;
    }

    // SET name = ? , age = ?,
    public T update(T entity) {
        return null;
    }

    public void delete(T entity) throws IllegalAccessException {
        Map<String, Field> fieldMap = this.getFieldMap();
        String idColumnName = this.getIdColumnName();
        Field field = fieldMap.get(idColumnName);
        field.setAccessible(true);
        ID id = (ID) field.get(entity);
        deleteById(id);
    }

    public void deleteById(ID id) {
        try (Connection connection = JavaM301DbContext.getConnection()) {
            StringBuilder sql = new StringBuilder();
            sql.append(" DELETE FROM ");
            sql.append(this.getTableNameFromAnnotation());
            sql.append(" WHERE ");
            sql.append(this.getIdColumnName());
            sql.append(" = ? ");
            System.out.println(sql.toString());
            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
            setParameter(preparedStatement, 1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * public Long count() {
     * try (Connection connection = JavaM301DbContext.getConnection()) {
     * StringBuilder sql = new StringBuilder();
     * sql.append(" SELECT COUNT(*) FROM ");
     * sql.append(this.getTableNameFromAnnotation());
     * sql.append(" WHERE ");
     * sql.append(this.getIdColumnName());
     * System.out.println(sql.toString());
     * PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
     * ResultSet resultSet = preparedStatement.executeQuery();
     * resultSet.next();
     * return resultSet.getLong(1);
     * } catch (SQLException e) {
     * throw new RuntimeException(e);
     * }
     * }
     */

    public String generateFieldForQuery() {
        Map<String, Field> fieldMap = this.getFieldMap();
        StringBuilder result = new StringBuilder();
        for (String columnName : fieldMap.keySet()) {
            result.append(columnName);
            result.append(",");
        }
        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }

    public String getTableNameFromAnnotation() {
        return this.entityClass.getAnnotation(DatabaseTable.class).name();
    }

    public Field[] getFields() {
        if (this.entityClass.getSuperclass() != null) {
            Field[] superClassFields = getSuperClassFields();
            Field[] entityFields = this.entityClass.getDeclaredFields();
            Field[] allFields = Arrays.copyOf(superClassFields, superClassFields.length + entityFields.length);
            System.arraycopy(entityFields, 0, allFields, superClassFields.length, entityFields.length);
            return allFields;
        }
        return this.entityClass.getDeclaredFields();
    }

    public Field[] getSuperClassFields() {
        return this.entityClass.getSuperclass().getDeclaredFields();
    }

    public Map<String, Field> getFieldMap() {
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

    public String getIdColumnName() {
        Map<String, Field> fieldMap = getFieldMap();
        for (Field field : fieldMap.values()) {
            if (field.isAnnotationPresent(IDColumn.class)) {
                DatabaseColumn column = field.getAnnotation(DatabaseColumn.class);
                return column.name();
            }
        }
        throw new RuntimeException("No @IDColumn annotation found");
    }

    public T mapResultSetToEntity(ResultSet resultSet) throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
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

    public static void setParameter(PreparedStatement preparedStatement, int parameterIndex, Object value) throws SQLException {
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
