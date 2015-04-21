package br.com.kaintegrados.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionFactory {

	public final static String USER = "focus";
	public final static String PASS = "focusnetworks";
	public final static String DB_FOLDER = "hibernate_db";
	public final static String HSQLDB_DRIVER = "org.hsqldb.jdbcDriver";
	public final static String HSQLDB = "jdbc:hsqldb:file:";
	public final static String USER_DIR = System.getProperty("user.dir");
	public final static String SEPARATOR = System.getProperty("file.separator");
	
	private StringBuffer db = new StringBuffer(HSQLDB);
	private Connection connection;
	
	
	/**
	 * 
	 * @return
	 */
    public Connection getConnection() {
    	
        try {
        	
        	Class.forName(HSQLDB_DRIVER);
        	
        	if(connection != null)
        		return connection;
        	
        	db.append(USER_DIR)
        		.append(SEPARATOR)
        		.append(DB_FOLDER)
        		.append(SEPARATOR)
        		.append(DB_FOLDER);
        	
            connection = DriverManager.getConnection(db.toString(), USER, PASS);
            
            return connection;
            
        } catch (Exception e) {
        	throw new RuntimeException(e);
        }
    }
    
    /**
     * Fecha a conexão com o Banco de dados
     */
    public void close() {
    	try {
			connection.close();
			connection = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
    
    /**
     * Fecha o ResultSet
     * @param rs
     */
    public void close(ResultSet rs) {
    	try {
			rs.close();
			rs = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * Fecha o ResultSet e em seguida a conexão
     * @param rs
     */
    public void closeAll(ResultSet rs) {
    	close(rs);
    	close();
    }
    
}
