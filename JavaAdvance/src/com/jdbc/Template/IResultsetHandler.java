package com.jdbc.Template;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IResultsetHandler<T> {

    public <T> T handle(ResultSet resultSet) throws SQLException;

}
