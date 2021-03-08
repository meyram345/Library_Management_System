package kz.aitu.oop.endterm.library.entities;

import java.util.UUID;

public class Student {
    private UUID uuid;
    private String name;

    public Student() {}

    public Student(String name) {
        setName(name);
    }

    public Student(UUID uuid, String name) {
        setUuid(uuid);
        setName(name);
    }

    public String getName() {
        return name;
    }

    public UUID getUuid() {
        return UUID.randomUUID();
    }

    public UUID getSimpleUUId() {return this.uuid;}

    public void setName(String name) {
        this.name = name;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getStudent_id() {return uuid.toString();}

    @Override
    public String toString() {
        return "id: " + getSimpleUUId() + ", name: " + getName();
    }
}
