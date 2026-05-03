package service;

import java.util.ArrayList;
import java.util.ArrayDeque;

public class DataManager {

    public void demoArrayList() {
        ArrayList<String> list = new ArrayList<>();

        list.add("Andi");
        list.add("Budi");
        list.add("Citra");

        System.out.println("=== ArrayList ===");
        for (String nama : list) {
            System.out.println(nama);
        }
    }

    public void demoArrayDeque() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        deque.add(10);
        deque.add(20);
        deque.add(30);

        System.out.println("=== ArrayDeque ===");
        for (Integer angka : deque) {
            System.out.println(angka);
        }
    }
}