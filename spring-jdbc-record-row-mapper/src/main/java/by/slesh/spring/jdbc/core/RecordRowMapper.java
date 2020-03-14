package by.slesh.spring.jdbc.core;

import org.springframework.jdbc.IncorrectResultSetColumnCountException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.JdbcUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.RecordComponent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

public class RecordRowMapper<T> implements RowMapper<T> {
    private final Constructor<T> ctor;
    private final List<Arg> args;

    public RecordRowMapper(final Class<T> model) {
        if (!model.isRecord()) {
            throw new IllegalArgumentException(
                    model + " should be a record class");
        }
        final RecordComponent[] components = model.getRecordComponents();
        this.args = new ArrayList<>(components.length);
        final Class<?>[] argTypes = new Class[components.length];
        for (int i = 0; i < components.length; ++i) {
            final RecordComponent c = components[i];
            this.args.add(new Arg(i, c.getName(), c.getType()));
            argTypes[i] = c.getType();
        }
        try {
            this.ctor = model.getConstructor(argTypes);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(
                    "Couldn resolve constructor for types " + Arrays.toString(argTypes));
        }
    }

    @Override
    public T mapRow(final ResultSet resultSet, final int rowNumber) throws SQLException {
        final var metaData = resultSet.getMetaData();
        final int columnCount = metaData.getColumnCount();
        if (columnCount < args.size()) {
            throw new IncorrectResultSetColumnCountException(
                    args.size(), columnCount);
        }
        try {
            return ctor.newInstance(extractCtorParams(
                    resultSet, createPropertyToColumnIndexMap(
                            metaData, columnCount)));
        } catch (InstantiationException
                | IllegalAccessException
                | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private Object[] extractCtorParams(
            final ResultSet resultSet,
            final Map<String, Integer> propertyToColumnIndexMap)
            throws SQLException {
        final var params = new Object[args.size()];
        for (final var arg : args) {
            final int columnIndex = propertyToColumnIndexMap.get(arg.name);
            params[arg.order] = JdbcUtils.getResultSetValue(
                    resultSet, columnIndex, arg.type);
        }
        return params;
    }

    private Map<String, Integer> createPropertyToColumnIndexMap(
            final ResultSetMetaData metaData,
            final int columnCount)
            throws SQLException {
        final Map<String, Integer> columnPropertyToIndexMap = new HashMap<>(columnCount);
        for (int columnIndex = 1; columnIndex <= columnCount; ++columnIndex) {
            final String propertyName = JdbcUtils.convertUnderscoreNameToPropertyName(
                    JdbcUtils.lookupColumnName(metaData, columnIndex));
            columnPropertyToIndexMap.put(propertyName, columnIndex);
        }
        return columnPropertyToIndexMap;
    }

    private static record Arg(int order, String name, Class<?>type) {
    }
}
