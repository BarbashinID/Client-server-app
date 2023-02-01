
package org.example;

import java.util.HashSet;

public class Parser {
    public HashSet<Student> parseData(String ftpUrl, String host, String user, String pass, String uploadPath) {
        DataUpload upload = new DataUpload();
        String json = upload.DataUpload(ftpUrl, host, user, pass, uploadPath);
        HashSet<Student> students = new HashSet<Student>();
        int idIndex = json.indexOf("\"id\":");
        int nameIndex = json.indexOf("\"name\":");
        int closeBracketIndex = json.indexOf("}");
        while (idIndex != -1){

            try {
                //   System.out.println(json);

                int id = Integer.parseInt(json.substring(idIndex + 5, nameIndex - 1));
                String name = json.substring(nameIndex + 8, closeBracketIndex - 1);
                json = json.substring(closeBracketIndex + 1);

                Student student = new Student(id, name);
                students.add(student);
                idIndex = json.indexOf("\"id\":");
                nameIndex = json.indexOf("\"name\":");
                closeBracketIndex = json.indexOf("}");
                id = Integer.parseInt(json.substring(idIndex + 5, nameIndex - 1));
                name = json.substring(nameIndex + 8, closeBracketIndex - 1);

            }
            catch (StringIndexOutOfBoundsException e){
                System.out.println("Data received");
            }
        }
        return students;
    }
}

