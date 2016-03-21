package com.easytravel.model;

import java.util.Scanner;

import com.easytravel.jdbc.GetAllCountries;
import com.easytravel.jdbc.GetAllVisasForOneClient;

public class TestClass {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        GetAllVisasForOneClient gavfoc = GetAllVisasForOneClient.getGetAllVisasForOneClient();
        GetAllCountries gac = GetAllCountries.getGetAllCountries();
        while (true) {
            gavfoc.selectClientsInfo(sc.nextInt());
            gac.getAllCountries();
        }
    }
}
