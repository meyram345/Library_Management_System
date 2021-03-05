package kz.aitu.oop.endterm.library.entities;

public class Student {
    private String uuid;
    private String name;

    public Student() {}

    public Student(String name) {
        setName(name);
    }

    public Student(String uuid, String name) {
        setUuid(uuid);
        setName(name);
    }

    public String getName() {
        return name;
    }

    public String getUuid() {
        return uuid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUuid(String uuid) {
        this.uuid = String.valueOf(java.util.UUID.randomUUID());
    }
}
