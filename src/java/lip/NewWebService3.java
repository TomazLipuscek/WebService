/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lip;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.annotation.Resource;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.sql.DataSource;

/**
 *
 * @author Tomaž
 */
@WebService(serviceName = "NewWebService3")
public class NewWebService3 {

    @Resource(name = "data3")
    private DataSource data3;
    int zadnjiID;
    /**
     * Web service operation
     */
    @WebMethod(operationName = "operation")
    public String operation(@WebParam(name = "email") String email, 
            @WebParam(name = "sporocilo") String sporocilo) {
        //TODO write your implementation code here:
    
    try {
      
    Class.forName("com.mysql.jdbc.Driver");
    Connection con = DriverManager.getConnection
        ("jdbc:mysql://localhost:3306/sporocila1"
                + "?zeroDateTimeBehavior=convertToNull", 
    "root", "root");
    
    PreparedStatement st = con.prepareStatement("insert into sporocila1 (email,"
            + " sporocilo) values (?,?)",Statement.RETURN_GENERATED_KEYS);

    st.setString(1, email);
    st.setString(2, sporocilo);
    st.executeUpdate();
    ResultSet rs = st.getGeneratedKeys();
        if (rs.next()){
        zadnjiID = rs.getInt(1);
    }
    } catch (Exception e) {
    System.out.println(e.getMessage());
    return "ERROR";
    }
    
    return "Demand with ID number: "+zadnjiID+" SAVED.";
    }
        
    

    /**
     * This is a sample web service operation
     */

}
