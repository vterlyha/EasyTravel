package com.easytravel.jdbc;

import com.easytravel.jdbc.DatabaseConnect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetAllVisasForOneClient extends DatabaseConnect {

    private static GetAllVisasForOneClient getAllVisasForOneClient;

    private GetAllVisasForOneClient() {
    }

    public static GetAllVisasForOneClient getGetAllVisasForOneClient() {
        if (getAllVisasForOneClient == null) {
            getAllVisasForOneClient = new GetAllVisasForOneClient();
        }

        return getAllVisasForOneClient;
    }

    public boolean selectClientsInfo(Integer id) {
        String sql = "SELECT clients.firstName, clients.lastName, count(visas.id) AS countVisas " +
                     "FROM clients " + 
                     "JOIN clientsvisas " +
                     "ON (clients.id = clientsvisas.clientsId) " +
                     "JOIN visas " + 
                     "ON (visas.id = clientsvisas.visasId) " +
                     "WHERE clients.id = ?";


        try {
            ResultSet info = getResultSetPreparedStatementById(sql, id);
            while (info.next()) {
                System.out.print(info.getString("firstName") + " ");
                System.out.print(info.getString("lastName") + " ");
                System.out.print(info.getInt("countVisas") + " ");
                System.out.println();
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
