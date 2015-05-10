package org.lamedh.pos.app.konsole;

import org.lamedh.pos.domain.Employee;
import org.lamedh.pos.domain.Product;
import org.lamedh.pos.domain.Sale;

public class Scratch {
    public static void mainMain(String[] args) {
        Product momogi = new Product();
        momogi.setId(1);
        momogi.setCode("P01");
        momogi.setName("Momogi");
        momogi.setUnitPriceByInt(500);

        Product pepsi = new Product();
        pepsi.setId(2);
        pepsi.setCode("P02");
        pepsi.setName("Pepsi");
        pepsi.setUnitPriceByInt(5000);

        Product ayam = new Product();
        ayam.setId(3);
        ayam.setCode("P03");
        ayam.setName("Ayam");
        ayam.setUnitPriceByInt(50000);

        Employee suyama = new Employee();
        suyama.setId(1);
        suyama.setCode("E01");
        suyama.setName("Suyama");

        Employee nancy = new Employee();
        nancy.setId(2);
        nancy.setCode("E02");
        nancy.setName("Nancy");

        Sale sale01 = new Sale();
        sale01.setId(1);
        sale01.setCode("S01");
        sale01.setCashier(suyama);
        sale01.addLineItem(momogi, 2);
        sale01.addLineItem(pepsi, 1);
        sale01.addLineItem(momogi, 1);

        System.out.println(sale01.getTotal());
        System.out.println(sale01.getLineItems().stream().count());
    }
}

