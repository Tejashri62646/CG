import java.sql.*;
import java.util.*;
class jdbc1{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        String username = "system";
        String password = "hr";
        
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(url,username,password);
            System.out.println("Connection established successfully!");
        
            // Statement stmt = con.createStatement();
            // stmt.executeUpdate("Insert into student values(1,'rohan',97,'pune')");
            //stmt.executeUpdate("update student set name='ROHAN' where id=1");

            int ch;
            do{
                System.out.println("1.Insert data ");
                System.out.println("2.update record ");
                System.out.println("3.delete record");
                System.out.println("4.Display data ");
                System.out.println("5.Exit");
                System.out.println("Enter choice: ");
                ch = sc.nextInt();
                switch(ch){
                    case 1 :{
                         System.out.println("Enter id: ");
                            int id = sc.nextInt();
                            System.out.println("Enter name: ");
                            String name = sc.next();
                            System.out.println("Enter marks: ");
                            int marks = sc.nextInt();
                            System.out.println("Enter city: ");
                            String city = sc.next();
                            PreparedStatement ps = con.prepareStatement("insert into student values(?,?,?,?)");
                            ps.setInt(1,id);
                            ps.setString(2,name);
                            ps.setInt(3,marks);
                            ps.setString(4,city);
                            ps.executeUpdate();
                            System.out.println("data inserted");
                            break;
                    }

                    case 2:{
                         System.out.println("Enter id: ");   
                            int id = sc.nextInt();
                            System.out.println("Enter name: ");
                            String name = sc.next();
                            PreparedStatement ps = con.prepareStatement("update student set name=? where id=?");
                            
                            ps.setString(1,name);
                            ps.setInt(2,id);
                            ps.executeUpdate();
                            System.out.println("data updated");
                            break;
                    }

                    case 3: {
                        System.out.println("Enter id: ");
                            int id = sc.nextInt();
                            PreparedStatement ps = con.prepareStatement("delete from student where id=?");
                            ps.setInt(1,id);
                            ps.executeUpdate();
                            System.out.println("record deleted");
                            break;
                    }

                    case 4: Statement stmt = con.createStatement();
                            ResultSet rs = stmt.executeQuery("select * from student");
                            while(rs.next()){
                                System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getString(4)+" ");
                            }
                            break;
                    case 5 : System.out.println("Exit...");
                    
                    default : System.out.println("Enter valid choice");

                }
            }while(ch!=5);
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

}