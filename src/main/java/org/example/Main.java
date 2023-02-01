package org.example;

import java.util.*;


public class Main {
    private static final int BUFFER_SIZE = 128;

    public static void main(String[] args) throws Exception {
        Scanner scaninput = new Scanner(System.in);




        String ftpUrl = "ftp://%s:%s@%s/%s;type=i";
        System.out.println("Enter host");
        String host = scaninput.nextLine(); //barbasxg.beget.tech
        System.out.println("Enter login");
        String user = scaninput.nextLine(); //barbasxg_user
        System.out.println("Enter pass");
        String pass = scaninput.nextLine(); //useruser1U!
        String uploadPath = "/students.json";


        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter commands \"1\",\"2\",\"3\",\"4\" or \"5\"");
        System.out.println("1 - Getting a list of students by name");
        System.out.println("2 - Getting information about a student by id");
        System.out.println("3 - Adding a student");
        System.out.println("4 - Deleting a student by id");
        System.out.println("5 - Exit");

        int command = 1;
    while (command != 5){
        command = Integer.parseInt(scanner.nextLine());
    if (command == 1) {
            Parser parser = new Parser();
            HashSet <Student> s = new HashSet<Student>(parser.parseData(ftpUrl, host, user, pass, uploadPath));
        TreeSet sorted = new TreeSet<>();
        sorted.addAll(s);
            System.out.println(sorted);
            scanner = new Scanner(System.in);
        }
        if (command == 2) {
            Parser parser = new Parser();
            HashSet <Student> s = new HashSet<Student>(parser.parseData(ftpUrl, host, user, pass, uploadPath));
            System.out.println("Enter id");
            long id = Long.parseLong(scanner.nextLine());
            System.out.println(s.stream().filter(student -> Objects.equals(student.getId(), id)).findFirst().get());
        }
        if (command == 3) {
            Parser parser = new Parser();
            HashSet <Student> s = parser.parseData(ftpUrl, host, user, pass, uploadPath);
            long genId = s.size()+2;
            System.out.println("Enter name");
            String name = scanner.nextLine();
            Student student1 = new Student(genId, name);
            s.add(student1);
            StringBuilder jsonBuilder = new StringBuilder();
            jsonBuilder.append("{\n");
            jsonBuilder.append("\"Students\": [");

            for (Student student : s) {
                jsonBuilder.append(student.toJSONObject());
            }
            jsonBuilder.setLength(jsonBuilder.length() - 1);
            jsonBuilder.append("\n]\n}");

            String json = jsonBuilder.toString();
            DataDownload dataDownload = new DataDownload(json, ftpUrl, host, user, pass, uploadPath);

        }
        if (command == 4) {
            System.out.println("Enter id");
            Parser parser = new Parser();
            HashSet <Student> s = new HashSet<Student>(parser.parseData(ftpUrl, host, user, pass, uploadPath));
            long id = Long.parseLong(scanner.nextLine());
            s.remove(s.stream().filter(student -> Objects.equals(student.getId(), id)).findFirst().get());
            StringBuilder jsonBuilder = new StringBuilder();
            jsonBuilder.append("{\n");
            jsonBuilder.append("\"Students\": [");

            for (Student student : s) {
                jsonBuilder.append(student.toJSONObject());
            }
            jsonBuilder.setLength(jsonBuilder.length() - 1);
            jsonBuilder.append("\n]\n}");
            String json = jsonBuilder.toString();
            DataDownload dataDownload = new DataDownload(json, ftpUrl, host, user, pass, uploadPath);
        }
        if (command == 5) {
            System.out.println("Exit");
            System.exit(0);
        }
        }
        }

    }
