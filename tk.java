package jdbc;

import java.sql.*;
import java.util.Scanner;

public class OracleJdbc {

    static final String URL =
        "jdbc:oracle:thin:@localhost:1521:xe";

    static final String USER = "system";
    static final String PASS = "password";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {

            // Load Oracle Driver
            Class.forName(
                "oracle.jdbc.driver.OracleDriver"
            );

            // Create Connection
            Connection con =
                DriverManager.getConnection(
                    URL, USER, PASS
                );

            while(true) {

                System.out.println("\n1.Insert");
                System.out.println("2.View");
                System.out.println("3.Update");
                System.out.println("4.Delete");
                System.out.println("5.Search");
                System.out.println("6.Exit");

                System.out.print("Enter choice: ");

                int ch = sc.nextInt();

                switch(ch) {

                    case 1:

                        System.out.print("Enter ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter Age: ");
                        int age = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter Course: ");
                        String course = sc.nextLine();

                        PreparedStatement ps1 =
                            con.prepareStatement(
                                "INSERT INTO student VALUES (?, ?, ?, ?)"
                            );

                        ps1.setInt(1, id);
                        ps1.setString(2, name);
                        ps1.setInt(3, age);
                        ps1.setString(4, course);

                        ps1.executeUpdate();

                        System.out.println(
                            "Record Inserted!"
                        );

                        break;

                    case 2:

                        Statement stmt =
                            con.createStatement();

                        ResultSet rs =
                            stmt.executeQuery(
                                "SELECT * FROM student"
                            );

                        while(rs.next()) {

                            System.out.println(
                                rs.getInt(1) + " " +
                                rs.getString(2) + " " +
                                rs.getInt(3) + " " +
                                rs.getString(4)
                            );
                        }

                        break;

                    case 3:

                        System.out.print(
                            "Enter ID to update: "
                        );

                        int uid = sc.nextInt();
                        sc.nextLine();

                        System.out.print(
                            "Enter new Name: "
                        );

                        String newName =
                            sc.nextLine();

                        PreparedStatement ps2 =
                            con.prepareStatement(
                                "UPDATE student SET name=? WHERE id=?"
                            );

                        ps2.setString(1, newName);
                        ps2.setInt(2, uid);

                        ps2.executeUpdate();

                        System.out.println(
                            "Record Updated!"
                        );

                        break;

                    case 4:

                        System.out.print(
                            "Enter ID to delete: "
                        );

                        int did = sc.nextInt();

                        PreparedStatement ps3 =
                            con.prepareStatement(
                                "DELETE FROM student WHERE id=?"
                            );

                        ps3.setInt(1, did);

                        ps3.executeUpdate();

                        System.out.println(
                            "Record Deleted!"
                        );

                        break;

                    case 5:

                        System.out.print(
                            "Enter ID to search: "
                        );

                        int sid = sc.nextInt();

                        PreparedStatement ps4 =
                            con.prepareStatement(
                                "SELECT * FROM student WHERE id=?"
                            );

                        ps4.setInt(1, sid);

                        ResultSet rs1 =
                            ps4.executeQuery();

                        if(rs1.next()) {

                            System.out.println(
                                rs1.getInt(1) + " " +
                                rs1.getString(2) + " " +
                                rs1.getInt(3) + " " +
                                rs1.getString(4)
                            );

                        } else {

                            System.out.println(
                                "Record Not Found!"
                            );
                        }

                        break;

                    case 6:

                        con.close();

                        System.out.println(
                            "Connection Closed!"
                        );

                        return;

                    default:

                        System.out.println(
                            "Invalid Choice!"
                        );
                }
            }

        } catch(Exception e) {

            System.out.println(e);
        }
    }
}