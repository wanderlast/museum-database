package MuseumGUI;


//imports
import java.sql.*;
import java.sql.DriverManager;
import java.io.*;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;

import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

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
    
    static void addMaint(String proc, String name, String start, String end, int collectID) {

        int id = getNextMaintID();
        int ssn = getWorkersSSN(name);
        System.out.println(name + " " + ssn);
        
        try{
            PreparedStatement ps = connect.prepareStatement("Insert into LLRP_Maintenance Values (?,?)");
            ps.setInt(1, id);
            System.out.println("Next id is: " + id);
            ps.setString(2, proc);
            
            ps.executeUpdate();
        
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            try{
                Date startDate = df.parse(start);
                Date endDate = df.parse(end);
                java.sql.Date d1 = new java.sql.Date(startDate.getTime());
                java.sql.Date d2 = new java.sql.Date(endDate.getTime());
                ps = connect.prepareStatement("Insert into LLRP_Does Values (?,?,?,?)");
                ps.setInt(1, ssn);
                ps.setInt(2, id);
                ps.setDate(3, d1);
                ps.setDate(4, d2);
                ps.executeUpdate();
            
                ps = connect.prepareStatement("Insert into LLRP_Performed_On Values (?,?,?,?)");
                ps.setInt(1, id);
                ps.setInt(2, collectID);
                ps.setDate(3, d1);
                ps.setDate(4, d2);
                ps.executeUpdate();
            } catch (ParseException e){
                System.out.println("Error:" + e);
            }
                    
        } catch (SQLException e) {
            System.out.println("Error:" + e);
        }
    }
    
        
    static void addRecipient(String name, String street1, String street2, String city, String state, String zip, String email, String organization) {
              
        int id = getNextRecID();
        
        try{
            PreparedStatement ps = connect.prepareStatement("Insert into LLRP_Recipient Values (?,?,?,?,?,?,?,?,?)");
            ps.setInt(1, id);
            System.out.println("Next id is: " + id);
            ps.setString(2, name);
            ps.setString(3, street1);
            ps.setString(4, street2);
            ps.setString(5, city);
            ps.setString(6, state);
            ps.setString(7, zip);
            ps.setString(8, email);
            ps.setString(9, organization);
            
            ps.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Error:" + e);
        }
    }
    
    static void addContributor(String name, String street1, String street2, String city, String state, String zip, String email, String organization) {
                
        int id = getNextContributeID();
        
        try{
            PreparedStatement ps = connect.prepareStatement("Insert into LLRP_Contributor Values (?,?,?,?,?,?,?,?,?)");
            ps.setInt(1, id);
            System.out.println("Next id is: " + id);
            ps.setString(2, name);
            ps.setString(3, street1);
            ps.setString(4, street2);
            ps.setString(5, city);
            ps.setString(6, state);
            ps.setString(7, zip);
            ps.setString(8, email);
            ps.setString(9, organization);
            
            ps.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Error:" + e);
        }
    }
    
    static void addWorker(int ssn, String fname, String mname, String lname, String street, String street2, String city, String zip, int age, char gender) {
        
        try{
            PreparedStatement ps = connect.prepareStatement("Insert into LLRP_Worker Values (?,?,?,?,?,?,?,?,?,?,TRUNC(sysdate))");
            ps.setInt(1, ssn);
            ps.setString(2, fname);
            ps.setString(3,mname);
            ps.setString(4,lname);
            ps.setString(5,street);
            ps.setString(6,street2);
            ps.setString(7,city);
            ps.setString(8,zip);
            ps.setInt(9,age);
            ps.setString(10, gender + "");
            
            ps.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Error:" + e);
        }
    }
    
    static void addVolunteer(int ssn, String email) {
        
        try{
            PreparedStatement ps = connect.prepareStatement("Insert into LLRP_Volunteer Values (?,?)");
            ps.setInt(1, ssn);
            ps.setString(2, email);
            
            ps.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Error:" + e);
        }
    }
    
    static void addStaff(int ssn, int salary) {
        
        try{
            PreparedStatement ps = connect.prepareStatement("Insert into LLRP_Staff Values (?,?)");
            ps.setInt(1, ssn);
            ps.setInt(2, salary);
            
            ps.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Error:" + e);
        }
    }
    
    static void addLoan(String purpose, String location, String name, int cID, String start, String end) {
        int id = getNextLoanID();
        int recID = getRecID(name);
        
        
        try{
            PreparedStatement ps = connect.prepareStatement("Insert into LLRP_Loan Values (?,?,?,?)");
            ps.setInt(1,id);
            ps.setString(2, purpose);
            ps.setString(3, location);
            ps.setInt(4, recID);
            
            ps.executeUpdate();
            
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            try{
                Date startDate = df.parse(start);
                Date endDate = df.parse(end);
                java.sql.Date d1 = new java.sql.Date(startDate.getTime());
                java.sql.Date d2 = new java.sql.Date(endDate.getTime());
                ps = connect.prepareStatement("Insert into LLRP_Makes_A Values (?,?,?,?)");
                ps.setInt(1,cID);
                ps.setInt(2,id);
                ps.setDate(3,d1);
                ps.setDate(4,d2);
                ps.executeUpdate();
            } catch (ParseException e){
                System.out.println("Error:" + e);
            }
            
        } catch (SQLException e) {
            System.out.println("Error:" + e);
        }
    }
    
    static void addAcquisition(String value, float price, String contributeID, String cID, String date) {
        int id = getNextAcquireID();      
        
        try{
            PreparedStatement ps = connect.prepareStatement("Insert into LLRP_Acquisition Values (?,?,?,?)");
            ps.setInt(1, id);
            ps.setInt(2, Integer.parseInt(value));
            ps.setFloat(3, price);
            ps.setInt(4,Integer.parseInt(cID));
            
            ps.executeUpdate();
            
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            try{
                Date acquireDate = df.parse(date);
                java.sql.Date d1 = new java.sql.Date(acquireDate.getTime());
                ps = connect.prepareStatement("Insert into LLRP_Gains_An Values (?,?,?)");
                ps.setInt(1,Integer.parseInt(cID));
                ps.setInt(2,id);
                ps.setDate(3,d1);
                ps.executeUpdate();
            } catch (ParseException e){
                System.out.println("Error:" + e);
            }
            
        } catch (SQLException e) {
            System.out.println("Error:" + e);
        }
    }
    
    static int getNextAcquireID(){
        int id = -1;
        try{
            res = stmt.executeQuery("Select AcquireID from LLRP_ACQUISITION WHERE rownum = 1 order by AcquireID desc");
            if(res.next()){
                id = Integer.parseInt(res.getString(1))+1;
            }
        }
        catch(SQLException e){
            System.out.println("Error:" + e);
        }
        return id;
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
    
    static int getNextMaintID(){
        int id = -1;
        try{
            res = stmt.executeQuery("Select MaintainID from LLRP_Maintenance WHERE rownum = 1 order by MaintainID desc");
            if(res.next()){
                id = Integer.parseInt(res.getString(1))+1;
            }
        }
        catch(SQLException e){
            System.out.println("Error:" + e);
        }
        return id;
    }
    
    static int getNextRecID(){
        int id = -1;
        try{
            res = stmt.executeQuery("Select RecipientID from LLRP_Recipient WHERE rownum = 1 order by RecipientID desc");
            if(res.next()){
                id = Integer.parseInt(res.getString(1))+1;
            }
        }
        catch(SQLException e){
            System.out.println("Error:" + e);
        }
        return id;
    }
    
    static int getNextContributeID(){
        int id = -1;
        try{
            res = stmt.executeQuery("Select ContributeID from LLRP_Contributor WHERE rownum = 1 order by ContributeID desc");
            if(res.next()){
                id = Integer.parseInt(res.getString(1))+1;
            }
        }
        catch(SQLException e){
            System.out.println("Error:" + e);
        }
        return id;
    }
    static int getNextLoanID(){
        int id = -1;
        try{
            res = stmt.executeQuery("Select LoanID from LLRP_Loan WHERE rownum = 1 order by LoanID desc");
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
    
    static DefaultTableModel getContributors() {
        Vector items = new Vector();
        Vector column = new Vector();
        try {
            res = stmt.executeQuery("select name, street1, street2, city, state, zip, email, organization from LLRP_CONTRIBUTOR");
            while(res.next()) {
                Vector temp = new Vector();
                for(int i=1; i < 9; i++) {
                    temp.addElement(res.getString(i));
                }
                items.addElement(temp);
            }
        }catch(SQLException e) {
            System.out.println("Error: " + e);
        }
        column.addElement("Name");
        column.addElement("Street");
        column.addElement("PO Box/APT");
        column.addElement("City");
        column.addElement("State");
        column.addElement("Zip Code");
        column.addElement("Email Address");
        column.addElement("Organization");
        DefaultTableModel model = new DefaultTableModel(items, column){boolean[] canEdit = new boolean [] {
        false, false, false, false};
            
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
    
    static DefaultComboBoxModel getWorkers() {

        DefaultComboBoxModel model = new DefaultComboBoxModel();
        try {
        res = stmt.executeQuery("select fname || ' ' || mname || ' ' || lname AS name from LLRP_WORKER");
        while(res.next()){
            model.addElement(res.getString("name"));
        }
        }catch(SQLException e) {
            System.out.println("Error: " + e);
        }
        
        return model;
    };
    
    static DefaultComboBoxModel getRecipients() {

        DefaultComboBoxModel model = new DefaultComboBoxModel();
        try {
        res = stmt.executeQuery("select name AS name from LLRP_RECIPIENT");
        while(res.next()){
            model.addElement(res.getString("name"));
        }
        }catch(SQLException e) {
            System.out.println("Error: " + e);
        }
        
        return model;
    };
    
    static int getWorkersSSN( String name ) {
        String[] arr2 = new String[100];
        int value = 0;

        try {
        res = stmt.executeQuery("select ssn, fname || ' ' || mname || ' ' || lname AS name from LLRP_WORKER");
        while(res.next()){
            arr2[res.getRow()] = res.getString("name");
            if(arr2[res.getRow()].equals(name)){
                value = res.getInt("ssn");
                break;
            }
        }
        }catch(SQLException e) {
            System.out.println("Error: " + e);
        }
        
        return value;
    };
    
    static int getRecID( String name ) {
        String[] arr2 = new String[100];
        int value = 0;

        try {
        res = stmt.executeQuery("select RECIPIENTID, name FROM LLRP_RECIPIENT");
        while(res.next()){
            arr2[res.getRow()] = res.getString("name");
            if(arr2[res.getRow()].equals(name)){
                value = res.getInt("RECIPIENTID");
                break;
            }
        }
        }catch(SQLException e) {
            System.out.println("Error: " + e);
        }
        
        return value;
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
