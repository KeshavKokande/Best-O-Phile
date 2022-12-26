/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectahp;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
/**
 *
 * @author Keshav Kokande
 */
public class consoleBasedAhp {
     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        Scanner sc=new Scanner(System.in);
        // TODO code application logic here
        try
            {
       
          
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String dburl="jdbc:sqlserver://KESHAV;databaseName=Nehra;integratedSecurity=true;encrypt=true;trustServerCertificate=true";
            Connection con=DriverManager.getConnection(dburl);
            int crtNum;
            String name="Student";
            System.out.println("Enter the no of criterias:");//Done
            crtNum=sc.nextInt();//to get number of Criterias
            ResultSet rs;
            String query;
            String query2;
           // System.out.println("Enter how many no. of Records u want to enter : ");
            String attName[] = new String[crtNum];      
           
            for(int i = 0; i <crtNum; i++) 
            {
                 System.out.println("Enter the Column Name"+ (i+1));
                 String col=sc.next();
                 query= "ALTER TABLE Student  ADD "+col+" DECIMAL(4, 3)";
                 query2 = "ALTER TABLE Swingg  ADD "+ col +" DECIMAL(5,3)";
                 PreparedStatement ps=con.prepareStatement(query);
                 PreparedStatement ps2=con.prepareStatement(query2);
                 ps.executeUpdate();
                 ps2.executeUpdate();
                 System.out.println("Enter Attribute Name " + (i+1));      
                 attName[i] = col;
                 System.out.println("Column Added....");
            }
            //DONE TILL HERE
            StringBuilder vari2 = new StringBuilder("(,?)");//////////////////////////////////////////////////////////////////////////////
            for(int i =0;i<=crtNum;i++){
                if(i<crtNum){
                    vari2.insert(1, ",");
                    vari2.insert(2, "?");
                }
                else{
                    vari2.deleteCharAt(1);
                }
            }
            double compMatrix[][] = new double[crtNum][crtNum];
            for(int i = 0; i< crtNum;i++){
                for(int j = 0;j < crtNum; j++)
                if(compMatrix[i][j] == 0){
                    if(i == j){
                        compMatrix[i][j] = 1;
                    }
                    else{
                        System.out.println("Enter prefrence of " + attName[i] + " with respect to "+attName[j]);
                        compMatrix[i][j] = sc.nextFloat();
                        compMatrix[j][i] = 1/compMatrix[i][j];
                    }
                }
            }
            for(int i = 0;i<crtNum;i++){
                query2 ="insert into Swingg values "+vari2;
                PreparedStatement ps2 = con.prepareStatement(query2);
                for(int j =0;j<crtNum;j++){
                    ps2.setString(1,attName[i]);
                    String ds = String.format("%.2f",compMatrix[i][j]);
                    Double ds1 = Double.parseDouble(ds);
                    ps2.setDouble(j+2, compMatrix[i][j]);
                }
                ps2.executeUpdate();
                System.out.println();
            }
            query2 = "delete from Swingg where " + attName[1] +" IS NULL";
            PreparedStatement ps2 = con.prepareStatement(query2);
            ps2.execute();
    //***************************************************************************** */
    //Creation and Calculation Of A1 Matrix
    //***************************************************************************** */
            double A1Matrix[] = new double[crtNum];
            double sum = 0;
            double number = (double) crtNum;
            double exp = (double)1/crtNum;
            for(int i = 0;i<crtNum; i++){
                double temp = 1;
                for(int j = 0;j<crtNum;j++){
                    temp *= compMatrix[i][j];
                }
                A1Matrix[i] = Math.pow(temp,exp);
                sum += A1Matrix[i];
            }
    /*         for(int i = 0; i < crtNum;i++){          //Print A1 Matrix
                System.out.printvfdln(A1[i]);
            } */  
    //***************************************************************************** */
    //Calculation of Weights
    //***************************************************************************** */         
            double weightsMatrix[] = new double[crtNum];
            double num1 = 0;
            for(int i = 0;i< crtNum;i++){
                weightsMatrix[i] = A1Matrix[i]/sum;
                num1 += weightsMatrix[i];
            }
    /*         for(int i =0;i<crtNum;i++){                 //Print Calculation of Weights
                System.out.println(weightsMatrix[i]);
            }*/
    //***************************************************************************** */
    //TOPSIS Starts From Now
    //***************************************************************************** */
            String Name="";
            int numOfData;
            System.out.println("How many no. of data you want to enter: ");
            numOfData = sc.nextInt();
            double usrMatrix[][] = new double[numOfData][crtNum];
            StringBuilder vari = new StringBuilder("(,?)");
            for(int i =0;i<=crtNum;i++){
                if(i<crtNum){
                    vari.insert(1, ",");
                    vari.insert(2, "?");
                }
                else{
                    vari.deleteCharAt(1);
                }
            }
            for(int i = 0;i< numOfData ; i++){                   //User Inputs Data
                System.out.println("Enter data for " + name + " "+(i+1));
                Name=name+" "+(i+1);
                query="insert into Student values "+vari;
                PreparedStatement ps=con.prepareStatement(query);
                ps.setString(1,Name);
                for(int j = 0;j<crtNum; j++)
                {
                 /*PreparedStatement ps=con.prepareStatement(query);
                 ps.executeUpdate();
                 System.out.println("Enter Attribute Name " + (i+1));      
                 attName[i] = col;*/
                    System.out.print(attName[j]+"   ");
                    usrMatrix[i][j] = sc.nextDouble();
                 
                    ps.setDouble(j+2,usrMatrix[i][j]);
                   
                  }
                 ps.executeUpdate();
                System.out.println();
            }
            query = "delete from Student where " + attName[1] +" IS NULL";
            PreparedStatement ps = con.prepareStatement(query);
            ps.execute();
    //***************************************************************************** */
    //SquareRoot Matrix
    //***************************************************************************** */        
            double sqrMatrix[] = new double[crtNum];
            for(int i = 0;i<crtNum;i++){
                double temp = 0;
                for(int j = 0;j<numOfData;j++){
                    temp = temp + (usrMatrix[j][i]*usrMatrix[j][i]);
                }
                sqrMatrix[i] = Math.sqrt(temp);             //ToDo: Round off its value
            }
    //***************************************************************************** */
    //Normalised Matrix
    //***************************************************************************** */
            double normMatrix[][] = new double[numOfData][crtNum];
            for(int i = 0;i<numOfData;i++){
                for(int j = 0;j<crtNum;j++){
                    normMatrix[i][j] = usrMatrix[i][j]/sqrMatrix[j];
                }
            }
    //***************************************************************************** */
    //Weighted Normalised Matrix
    //***************************************************************************** */
            double weightNormMatrix[][] = new double[numOfData][crtNum];
            for(int i =0;i<numOfData;i++){
                for(int j= 0;j<crtNum;j++){
                    weightNormMatrix[i][j] = (normMatrix[i][j] * weightsMatrix[j]);
                }
            }
    //***************************************************************************** */
    //MinMax Matrix
    //***************************************************************************** */
            double minMaxMatrix[][] = new double[2][crtNum];
            for(int i = 0;i<crtNum;i++){
                double min = weightNormMatrix[0][i];
                double max = 0;
                for(int j = 0;j<numOfData;j++){
                    if(min > weightNormMatrix[j][i]){
                        min = weightNormMatrix[j][i];
                    }
                    if(max < weightNormMatrix[j][i]){
                        max = weightNormMatrix[j][i];
                    }
                }
                minMaxMatrix[0][i] = max;
                minMaxMatrix[1][i] = min;
            }
    //***************************************************************************** */
    //Euclidean Distance
    //***************************************************************************** */
            double siplus[] = new double[numOfData];
            double siminus[] = new double[numOfData];
            double sumSiMatrix[] = new double[numOfData];                  //SUM of Si+ and Si-
            double piMatrix[] = new double[numOfData];
            for(int i = 0;i< numOfData;i++){
                double sumEuc = 0;
                double diff = 0;
                for(int j =0;j<crtNum;j++){
                    sumEuc += ((weightNormMatrix[i][j] - minMaxMatrix[0][j])*(weightNormMatrix[i][j] - minMaxMatrix[0][j]));
                }
                for(int j =0;j<crtNum;j++){
                    diff += ((weightNormMatrix[i][j] - minMaxMatrix[1][j]) * (weightNormMatrix[i][j] - minMaxMatrix[1][j]));
                }
                siplus[i] = Math.sqrt(sumEuc);
                siminus[i] = Math.sqrt(diff);
            }
            for(int i = 0;i<numOfData;i++){
                sumSiMatrix[i] = siplus[i] + siminus[i];
            }
            for(int i = 0;i < numOfData;i++){
                piMatrix[i] = siminus[i]/sumSiMatrix[i];
                String ds3 = String.format("%.2f",piMatrix[i]);
                Double ds4 = Double.parseDouble(ds3);
            }
            int piMax = 0;
            for(int i = 0;i< numOfData;i++){;
                if(piMatrix[i] > piMatrix[piMax]){
                    piMax = i;
                }
            }
            System.out.println("Student "+ (piMax+1)+ " is the best student.");

    }
        catch(ClassNotFoundException e)
        {
            Logger.getLogger(Projectahp.class.getName()).log(Level.SEVERE,null,e);
        }
    }
}