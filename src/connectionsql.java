import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class connectionsql {


    public static void main(String[] args) throws Exception {
        autorization();
        getConnection();
    }

    public static void autorization(){
        try {
            Connection con = getConnection();
            assert con != null;
            Statement create = con.createStatement();
            ResultSet data = create.executeQuery("SELECT * FROM `course_work`.user where password = '';");

            while (data.next()) {
                String name = data.getString("firstname");
                System.out.println(name);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Mission Completed");
        }

    }

    public static Connection getConnection(){
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/course_work?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String username = "root";
            String password = "";
            Class.forName(driver);
            return DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static ObservableList<oop_materials> getDatamaterials() {
        Connection conn = getConnection();
        ObservableList<oop_materials> list = FXCollections.observableArrayList();
        try {
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement("select * from `course_work`.materials");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new oop_materials(rs.getInt("number"), rs.getString("name"), rs.getString("manufacturer"), rs.getString("type"), rs.getInt("quantity"), rs.getInt("weight (kg)"), rs.getInt("cost")));
            }
            ps.close();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public static ObservableList<oop_objects> getDataobjects() {
        Connection conn = getConnection();
        ObservableList<oop_objects> list = FXCollections.observableArrayList();
        try {
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement("select * from `course_work`.objects_constructing;");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new oop_objects(rs.getString("Name_of_areas"), rs.getInt("quantity_in_areas"), rs.getString("destination")));
            }
            ps.close();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public static ObservableList<oop_finish_objects> getDatafobjects() {
        Connection conn = getConnection();
        ObservableList<oop_finish_objects> list = FXCollections.observableArrayList();
        try {
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement("select * from `course_work`.`finished_objects`;");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new oop_finish_objects( rs.getInt("number"), rs.getString("objects"), rs.getString("company"), rs.getString("address"), rs.getInt("square (m2)"), rs.getDate("date")));
            }
            ps.close();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public static ObservableList<oop_req_deliver> getReqdeliver() {
        Connection conn = getConnection();
        ObservableList<oop_req_deliver> list = FXCollections.observableArrayList();
        try {
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement("select * from `course_work`.`materials_to_deliver`;");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new oop_req_deliver(rs.getInt("quantity_for_delivery"), rs.getString("name_of_material") ));
            }
            ps.close();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public static ObservableList<oop_del_mater> getDelmaterials() {
        Connection conn = getConnection();
        ObservableList<oop_del_mater> list = FXCollections.observableArrayList();
        try {
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM `course_work`.delivered_materials;");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new oop_del_mater(rs.getInt("number"), rs.getString("name"), rs.getString("manufacturer"), rs.getInt("quantity"),rs.getInt("weight (kg)"), rs.getDate("date")));
            }
            ps.close();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
}