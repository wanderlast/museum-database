package MuseumGUI;


//imports
import java.sql.*;
import java.sql.DriverManager;
import java.io.*;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class OracleConnection
{
    static boolean connected = false; //tracks if connected
    static Connection connect = null;
    static Statement stmt = null;
    static ResultSet res = null;
        
    static String url = "jdbc:oracle:thin:@delphi.cs.csubak.edu:1521:dbs01",
        user = "cs342", passwd = "c3m4p2s";

    static void connectToDatabase() {
       try {
        // Class.forName(driverName);
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            connect = DriverManager.getConnection(url, user, passwd);
            stmt = connect.createStatement();
            
            connected = true;
            System.out.println("Connection successful.");
       } catch (SQLException e ) { 
           e.printStackTrace();
           System.exit(-1);
       }
    }
    
    static void executeSQL(String sql) {
       try {
            if ( sql.charAt(sql.length() -1) == ';' ) sql = sql.substring(0, sql.length() -1);
            res = stmt.executeQuery(sql);
       } catch (SQLException e) { System.err.println("\nError occured in executing SQL: \n\t" + sql + "\n"); }
    }
     
    static void addItem(String n, String d, String y, String t, String c, String m) {
        int id = getNextCollectID();
        
        try{
            PreparedStatement ps = connect.prepareStatement("Insert into LLRP_Collection Values (?,?,?,?,?,?,?)");
            ps.setInt(1, id);
            System.out.println("Next id is: " + id);
            ps.setString(2, n);
            ps.setString(3, d);
            ps.setString(4, y);
            ps.setString(5, t);
            ps.setString(6, c);
            ps.setString(7, m);
            
            ps.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Error:" + e);
        }
    }
    
        static void addMaint() {
        /*
            dummy code from addItem
        int id = getNextCollectID();
        
        try{
            PreparedStatement ps = connect.prepareStatement("Insert into LLRP_Collection Values (?,?,?,?,?,?,?)");
            ps.setInt(1, id);
            System.out.println("Next id is: " + id);
            ps.setString(2, n);
            ps.setString(3, d);
            ps.setString(4, y);
            ps.setString(5, t);
            ps.setString(6, c);
            ps.setString(7, m);
            
            ps.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Error:" + e);
        }
        */
    }
    
    static int getNextCollectID(){
        int id = -1;
        try{
            res = stmt.executeQuery("Select collectID from LLRP_Collection WHERE rownum = 1 order by CollectID desc");
            if(res.next()){
                id = Integer.parseInt(res.getString(1))+1;
            }
        }
        catch(SQLException e){
            System.out.println("Error:" + e);
        }
        return id;
    }
    
    static DefaultTableModel getFurnitureItems() {
        Vector items = new Vector();
        Vector column = new Vector();
        try {
            res = stmt.executeQuery("select collectID, name, description, types from LLRP_COLLECTION where types = 'Furniture'");
            while(res.next()) {
                Vector temp = new Vector();
                for(int i=1; i < 5; i++) {
                    temp.addElement(res.getString(i));
                }
                items.addElement(temp);
            }
        }catch(SQLException e) {
            System.out.println("Error: " + e);
        }
        column.addElement("ID");
        column.addElement("Name");
        column.addElement("Description");
        column.addElement("Type");
        DefaultTableModel model = new DefaultTableModel(items, column){boolean[] canEdit = new boolean [] {
        false, false, false, false};
            
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }};
        
        return model;
    };
    
    static DefaultTableModel getCommonMaintItems() {
    Vector items = new Vector();
    Vector column = new Vector();
    try {
        res = stmt.executeQuery("select unique c.collectID, c.Name, c.Description\n" +
                                "from LLRP_Collection c\n" +
                                "where exists ( select * from LLRP_Staff s, LLRP_Performed_On p, LLRP_Does d    \n" +
                                "    where s.SSN = d.SSN\n" +
                                "    and p.MaintainID = d.MaintainID\n" +
                                "    and p.collectID = c.collectID\n" +
                                ")");
        while(res.next()) {
            Vector temp = new Vector();
            for(int i=1; i < 4; i++) {
                temp.addElement(res.getString(i));
            }
            items.addElement(temp);
        }
    }catch(SQLException e) {
        System.out.println("Error: " + e);
    }
    column.addElement("ID");
    column.addElement("Name");
    column.addElement("Description");
    DefaultTableModel model = new DefaultTableModel(items, column){boolean[] canEdit = new boolean [] {
    false, false, false};

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
    }};
        
        return model;
    };
    
    static DefaultTableModel getLoan( PreparedStatement str ) {
    Vector items = new Vector();
    Vector column = new Vector();
    try {
        res = str.executeQuery();
        while(res.next()) {
            Vector temp = new Vector();
            for(int i=1; i < 8; i++) {
                temp.addElement(res.getString(i));
            }
            items.addElement(temp);
        }
    }catch(SQLException e) {
        System.out.println("Error: " + e);
    }
    column.addElement("Item Name");
    column.addElement("Loan Start");
    column.addElement("Loan End");
    column.addElement("Purpose");
    column.addElement("Location");
    column.addElement("Recipient Name");
    column.addElement("Receiving Organization");
    DefaultTableModel model = new DefaultTableModel(items, column){boolean[] canEdit = new boolean [] {
    false, false, false, false, false, false, false};

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
    }};
        
        return model;
    };
    
    static DefaultTableModel getMaint( PreparedStatement str ) {
    Vector items = new Vector();
    Vector column = new Vector();
    try {
        res = str.executeQuery();
        while(res.next()) {
            Vector temp = new Vector();
            for(int i=1; i < 6; i++) {
                temp.addElement(res.getString(i));
            }
            items.addElement(temp);
        }
    }catch(SQLException e) {
        System.out.println("Error: " + e);
    }
    column.addElement("Item Name");
    column.addElement("Procedure");
    column.addElement("Start Date");
    column.addElement("End Date");
    column.addElement("Worker");
    DefaultTableModel model = new DefaultTableModel(items, column){boolean[] canEdit = new boolean [] {
    false, false, false, false, false};

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
    }};
        
        return model;
    };
    
    static DefaultTableModel getExhibit( PreparedStatement str ) {
        Vector items = new Vector();
        Vector column = new Vector();
        try {
            res = str.executeQuery();
            while(res.next()) {
                Vector temp = new Vector();
                for(int i=1; i < 6; i++) {
                    temp.addElement(res.getString(i));
                }
                items.addElement(temp);
            }
        }catch(SQLException e) {
            System.out.println("Error: " + e);
        }
        column.addElement("Item Name");
        column.addElement("Exhibit Concept");
        column.addElement("Exhibit Start Date");
        column.addElement("Exhibit End Date");
        column.addElement("Location");
        DefaultTableModel model = new DefaultTableModel(items, column){boolean[] canEdit = new boolean [] {
        false, false, false, false, false};

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }};

            return model;
    };
    
    static void getAvgEstValue(){
        try{
            CallableStatement cs = connect.prepareCall("{call avg_estValue}");
            ResultSet rs = cs.executeQuery();
        } catch(SQLException e) {
            System.out.println("Error: " + e);
        }
    }

    static void closeConnection() {
       try {
            if ( stmt != null ) stmt.close();
            if ( res != null ) res.close();
            if ( connect != null ) connect.close();
            connected = false;
            System.out.println("Disconnected from database.");
       } catch (SQLException e) {
            System.out.println("Error:" + e);
       }
    }
}
