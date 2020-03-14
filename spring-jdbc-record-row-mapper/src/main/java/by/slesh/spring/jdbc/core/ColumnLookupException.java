package by.slesh.spring.jdbc.core;

import org.springframework.dao.DataAccessException;

public class ColumnLookupException extends DataAccessException {
    public ColumnLookupException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
