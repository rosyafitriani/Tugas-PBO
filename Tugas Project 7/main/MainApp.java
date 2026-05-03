package main;

import model.*;
import service.DataManager;

public class MainApp {
    public static void main(String[] args) {

        // =========================
        // 1. GENERIC
        // =========================
        Mahasiswa<String, String, Integer> m = new Mahasiswa<>();
        m.setNim("11020200");
        m.setName("Ferdi");
        m.setClas(21);

        System.out.println("=== GENERIC ===");
        System.out.println("NIM  : " + m.getNim());
        System.out.println("Nama : " + m.getName());
        System.out.println("Kelas: " + m.getClas());

        // =========================
        // 2. POLYMORPHISM
        // =========================
        System.out.println("\n=== POLYMORPHISM ===");
        Person p = new Student("Rosya", "12345");
        p.display();

        // =========================
        // 3. COLLECTION
        // =========================
        System.out.println("\n=== COLLECTION ===");
        DataManager dm = new DataManager();
        dm.demoArrayList();
        dm.demoArrayDeque();
    }
}