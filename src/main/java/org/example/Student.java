package org.example;

public class Student implements Comparable<Student> {
    private final Long id;
    private final String name;

    public Student(long id, String name) {
        this.id = id;
        this.name = name;
    }
    @Override
    public int compareTo(Student o) {
        int result = this.name.compareTo(o.name);
        if (result == 0) {
            result = this.id.compareTo(o.id);
        }
        return result;
    }
    public String toJSONObject() {
        return "\n{\n"
                + "\"id\":" + id + ",\n"
                + "\"name\":\"" + name + "\"\n"
                + "},";
    }
    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "\n-------------------------\n"
                + "name: " + name + "\n"
                + "id: " + id;
    }
}
