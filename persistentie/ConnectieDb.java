package persistentie;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConnectieDb {

    private static final String JDBC_DRIVER = "sun.jdbc.odbc.JdbcOdbcDriver";
    //private static final String DATABASE_URL = "jdbc:odbc:quoridor";
    private static final String DATABASE_URL = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=QuoridorBen";
    private Connection connection;

    public ConnectieDb() {

        //verbinding met database maken
        try {

            //laad de database driver klasse
            Class.forName(JDBC_DRIVER);

            //maakt de verbinding en wijst ze toe aan connection
            connection = DriverManager.getConnection(DATABASE_URL);

        }//end try
        catch (SQLException sqlException) {

            JOptionPane.showMessageDialog(null, sqlException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

            System.exit(1);
        }//end catch
        catch (ClassNotFoundException classNotFound) {

            JOptionPane.showMessageDialog(null, classNotFound.getMessage(), "Driver onvindbaar", JOptionPane.ERROR_MESSAGE);

            System.exit(1);
        }//end catch

    }//end constructor Connectie

    //GETCONNECTION()
    public Connection getConnection() {

        return connection;
    }//end method getConnection

    //CLOSECONNECTION
    public void closeConnection() {

        try {
            connection.close();
        }//end try
        catch (SQLException sqlException) {

            JOptionPane.showMessageDialog(null, sqlException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

            System.exit(1);
        }//end catch
    }//end method closeConnection
}//end class Connectie

