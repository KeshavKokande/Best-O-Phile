/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projectahp;
/**
 *
 * @author Keshav Kokande
 */
import java.util.Scanner;
import java.sql.SQLException;

public class Projectahp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        Scanner sc=new Scanner(System.in);
        // TODO code application logic here
          
            loginPage log = new loginPage();
            log.show();
    }
}