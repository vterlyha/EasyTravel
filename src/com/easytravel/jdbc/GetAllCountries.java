package com.easytravel.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetAllCountries extends DatabaseConnect {
    private static GetAllCountries getAllCountries;

    private GetAllCountries() {
    }

    public static GetAllCountries getGetAllCountries() {
        if (getAllCountries == null) {
            getAllCountries = new GetAllCountries();
        }

        return getAllCountries;
    }

    public boolean getAllCountries() {
        String sql = "select countries.name from countries";


        try {
            ResultSet info = getResultSet(sql);
            while (info.next()) {
                System.out.println(info.getString("name") + " ");
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAllDBConnections();
        }
        return false;
    }
}
