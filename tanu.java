import java.sql.*;
import java.util.Scanner;

public class SalesManagement {

    // ================= DATABASE CONNECTION =================

    public static Connection getConnection() {

        String url =
                "jdbc:oracle:thin:@localhost:1521:XE1";

        String username = "System";
        String password = "tanu";

        Connection con = null;

        try {

            Class.forName("oracle.jdbc.OracleDriver");

            con =
                    DriverManager.getConnection(
                            url,
                            username,
                            password);

            System.out.println(
                    "Connected to Oracle database successfully!");

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }

        return con;
    }

    // ================= ADD CUSTOMER =================

    public static void addCustomer(Connection con, Scanner sc) {

        try {

            System.out.print("Customer ID: ");
            int cid = Integer.parseInt(sc.nextLine());

            System.out.print("First Name: ");
            String fname = sc.nextLine();

            System.out.print("Last Name: ");
            String lname = sc.nextLine();

            System.out.print("City: ");
            String city = sc.nextLine();

            System.out.print("Email: ");
            String email = sc.nextLine();

            String sql =
                    "INSERT INTO customers1(cid, first_name, last_name, city, email) VALUES(?,?,?,?,?)";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, cid);
            ps.setString(2, fname);
            ps.setString(3, lname);
            ps.setString(4, city);
            ps.setString(5, email);

            int rows = ps.executeUpdate();

            if (rows > 0) {

                System.out.println(
                        "Customer added successfully!");
            }

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    // ================= ADD PRODUCT =================

    public static void addProduct(Connection con, Scanner sc) {

        try {

            System.out.print("Product ID: ");
            int pid = Integer.parseInt(sc.nextLine());

            System.out.print("Product Name: ");
            String pname = sc.nextLine();

            System.out.print("Category: ");
            String category = sc.nextLine();

            System.out.print("Price: ");
            double price =
                    Double.parseDouble(sc.nextLine());

            String sql =
                    "INSERT INTO products1(pid, pname, category, price) VALUES(?,?,?,?)";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, pid);
            ps.setString(2, pname);
            ps.setString(3, category);
            ps.setDouble(4, price);

            int rows = ps.executeUpdate();

            if (rows > 0) {

                System.out.println(
                        "Product added successfully!");
            }

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    // ================= PLACE ORDER =================

    public static void placeOrder(Connection con, Scanner sc) {

        try {

            System.out.print("Order ID: ");
            int oid = Integer.parseInt(sc.nextLine());

            System.out.print("Customer ID: ");
            int cid = Integer.parseInt(sc.nextLine());

            System.out.print("Product ID: ");
            int pid = Integer.parseInt(sc.nextLine());

            System.out.print("Quantity: ");
            int qty = Integer.parseInt(sc.nextLine());

            String sql =
                    "INSERT INTO orders1(oid, cid, pid, quantity) VALUES(?,?,?,?)";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, oid);
            ps.setInt(2, cid);
            ps.setInt(3, pid);
            ps.setInt(4, qty);

            int rows = ps.executeUpdate();

            if (rows > 0) {

                System.out.println(
                        "Order placed successfully!");
            }

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    // ================= UPDATE CUSTOMER =================

    public static void updateCustomer(Connection con, Scanner sc) {

        try {

            System.out.print("Enter Customer ID: ");
            int cid = Integer.parseInt(sc.nextLine());

            System.out.print("Enter New City: ");
            String city = sc.nextLine();

            System.out.print("Enter New Email: ");
            String email = sc.nextLine();

            String sql =
                    "UPDATE customers1 SET city=?, email=? WHERE cid=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, city);
            ps.setString(2, email);
            ps.setInt(3, cid);

            int rows = ps.executeUpdate();

            if (rows > 0) {

                System.out.println("Customer updated");
            } else {

                System.out.println("Customer not found");
            }

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    // ================= DELETE CUSTOMER =================

    public static void deleteCustomer(Connection con, Scanner sc) {

        try {

            System.out.print("Enter Customer ID: ");
            int cid = Integer.parseInt(sc.nextLine());

            PreparedStatement ps =
                    con.prepareStatement(
                            "DELETE FROM customers1 WHERE cid=?");

            ps.setInt(1, cid);

            int rows = ps.executeUpdate();

            if (rows > 0) {

                System.out.println("Customer deleted");
            } else {

                System.out.println("Customer not found");
            }

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    // ================= VIEW CUSTOMERS =================

    public static void viewCustomers(Connection con) {

        try {

            Statement stmt =
                    con.createStatement();

            ResultSet rs =
                    stmt.executeQuery(
                            "SELECT * FROM customers1");

            System.out.println(
                    "\nCID\tFIRSTNAME\tLASTNAME\tCITY\tEMAIL");

            while (rs.next()) {

                System.out.println(
                        rs.getInt("cid") + "\t" +
                        rs.getString("first_name") + "\t" +
                        rs.getString("last_name") + "\t" +
                        rs.getString("city") + "\t" +
                        rs.getString("email"));
            }

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    // ================= MAIN METHOD =================

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Connection con = getConnection();

        if (con == null) {

            System.out.println("Connection Failed");
            return;
        }

        int choice;

        do {

            System.out.println("\n===== SALES MANAGEMENT SYSTEM =====");

            System.out.println("1. Add Customer");
            System.out.println("2. Add Product");
            System.out.println("3. Place Order");
            System.out.println("4. Update Customer");
            System.out.println("5. Delete Customer");
            System.out.println("6. View Customers");
            System.out.println("7. Exit");

            System.out.print("Enter choice: ");

            choice =
                    Integer.parseInt(sc.nextLine());

            switch (choice) {

                case 1:

                    addCustomer(con, sc);
                    break;

                case 2:

                    addProduct(con, sc);
                    break;

                case 3:

                    placeOrder(con, sc);
                    break;

                case 4:

                    updateCustomer(con, sc);
                    break;

                case 5:

                    deleteCustomer(con, sc);
                    break;

                case 6:

                    viewCustomers(con);
                    break;

                case 7:

                    System.out.println("Exiting...");
                    break;

                default:

                    System.out.println("Invalid Choice");
            }

        } while (choice != 7);

        try {

            con.close();
            sc.close();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
}