package com.hqsoft.esales.LoginJDBC.Controller;

import java.sql.Connection;
import  com.hqsoft.esales.LoginJDBC.Model.JDBCModel;

public class JDBCController {
    JDBCModel JdbcModel = new JDBCModel();

    public Connection ConnnectionData() {
        return JdbcModel.getConnectionOf();
    }
}
