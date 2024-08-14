import com.mysql.cj.Query;

import java.sql.*;
import java.util.Objects;
import java.util.Scanner;

public class Main {



    public static void main(String[] args) {

        try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/loginschema"
                    , "root"
                    , "artinarashi85@gmail.com");


            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");


            Scanner sc = new Scanner(System.in);
            String  name , pass;
            Integer id;
            int option;

            System.out.println("for adding press 1");
            System.out.println("for viewing press 2");
            System.out.println("for removing press 3");
            Boolean found = false;
            option = sc.nextInt();
            if(option == 1)
            {
                //System.out.println("Please write your id");

                //id = sc.next();
                Boolean exists = true;
                //String a;




            /*while(resultSet.next())
            {
                a = resultSet.getObject("idusers").toString();
                if(Objects.equals(a, id))
                {
                    System.out.println("this id already exists");
                    //id = getId(id , resultSet , exists , sc , a , statement);
                    break;
                }
            }*/

                System.out.println("please enter a name (your name cant have a space in it)");
                name = sc.next();
                System.out.println("please enter a password");
                pass = sc.next();



                statement.executeUpdate("INSERT INTO `loginschema`.`users`\n" +
                        "(\n" +
                        "`username`,\n" +
                        "`password`)\n" +
                        "VALUES\n" +
                        "(\n" +
                        "\"" + name + "\",\n" +
                        "\"" + pass + "\");");

            }

            else if(option == 2)
            {
                while(resultSet.next())
                {
                    System.out.println(resultSet.getInt("idusers"));
                    System.out.println(resultSet.getString("username"));
                    System.out.println(resultSet.getString("password"));
                    System.out.println();
                }
            }
            else if(option == 3)
            {
                while(!found)
                {
                    while(resultSet.next())
                    {
                        System.out.println(resultSet.getInt("idusers"));
                        System.out.println(resultSet.getString("username"));
                        System.out.println(resultSet.getString("password"));
                        System.out.println();
                    }
                    System.out.println("please choose which id you want to delete");
                    id = sc.nextInt();
                    resultSet = statement.executeQuery("SELECT * FROM users");

                    while(resultSet.next())
                    {
                        if(id == resultSet.getInt("idusers"))
                        {
                            found = true;
                            statement.executeUpdate("DELETE FROM `loginschema`.`users` WHERE (`idusers` = " + id.toString() + ");");

                            break;
                        }
                    }
                    if(!found)
                        System.out.println("sorry! the id that you entered does not exist");
                }
            }
            else
                System.out.println("sorry! the option you chose does not exist");


        }catch(SQLException e)
        {
            e.printStackTrace();
        }

    }

   /* public static String getId(String gid , ResultSet rs , Boolean e , Scanner s , String b , Statement st) throws SQLException {
        while(e)
        {
            System.out.println("please enter another id");
            gid = s.next();
            rs = st.executeQuery("SELECT * FROM users");
            while(rs.next())
            {
                b = rs.getObject("idusers").toString();
                if(Objects.equals(b, gid))
                {
                    System.out.println("this id already exists");
                    getId(b , rs , e , s , b , st);
                }
            }
            e = false;
        }
        return gid;
    }*/


}