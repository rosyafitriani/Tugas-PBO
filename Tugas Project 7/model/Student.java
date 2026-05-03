package model;

public class Student extends Person {

    private String nim;

    public Student(String name, String nim) {
        super(name);
        this.nim = nim;
    }

    public String getNim() {
        return nim;
    }

    @Override
    public void display() {
        System.out.println("Nama: " + name);
        System.out.println("NIM: " + nim);
    }
}