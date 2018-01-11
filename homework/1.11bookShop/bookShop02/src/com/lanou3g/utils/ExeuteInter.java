package com.lanou3g.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public interface ExeuteInter {
    Statement exevtue(Connection connection) throws SQLException;
}
