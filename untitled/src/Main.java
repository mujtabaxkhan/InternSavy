import javax.swing.table.TableRowSorter;
import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        studentManagement manage = new studentManagement();
        boolean choice = false;
        while(!choice){
            Scanner input =  new Scanner(System.in);
            System.out.println("-------------------------------------------------------------------------------------\n\n");
            System.out.println("\t\t\tWELCOME TO STUDENT MANAGEMENT SYSTEM");
            System.out.println("-------------------------------------------------------------------------------------\n\n");
            System.out.println("Kindly input the function: \nPress 1 to Add Student\nPress 2 for Editing Details of a Student\nPress 3 for Deleting student Record\nPress 4 for Searching the Student\nPress 5 for Exit");
            System.out.println("Enter Choice:");
            int in = input.nextInt();
            if(in == 1){
                manage.addStudent();
            }
            else if(in == 2){
                manage.EditData();
            }
            else if(in == 3){
                manage.DeleteData();
            } else if (in == 4) {
                manage.SearchData();
            }
            else{
                choice = true;
            }
        }
    }
}

class studentManagement {

    //FUNCTION TO SEARCH DATA
    public void SearchData() {
        Scanner input = new Scanner(System.in);
        boolean check = true;
        while (check) {
            try{
                System.out.println("Enter The Choice From the Following You want to SEARCH:\nPress 1 for Roll Numbers\nPress 2 for Name\nEnter Choice: ");
                int choice = input.nextInt();
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/internshipdata", "root", "mujtaba123");

                if (choice == 1) {

                    String searcher = "SELECT * FROM studentmanagement WHERE RollNumber = ?";
                    PreparedStatement statement = connection.prepareStatement(searcher);
                    boolean checker = true;
                    System.out.println("Enter the Roll Number You want to Edit data of: ");
                    int RollNumber = input.nextInt();

                    while (checker) {
                        try {
                            check = checkRollNumber(RollNumber);
                            if (check) {
                                System.out.println("ERROR");
                                System.out.println("Roll Number Does not Exist");
                                System.out.println("Enter the Roll Number You want to Edit data of: ");
                                RollNumber = input.nextInt();
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("ERROR");
                            System.out.println("Kindly Enter Data in Correct Format");
                            check = true;
                        }
                    }

                    statement.setInt(1, RollNumber);
                    ResultSet resultSet = statement.executeQuery();
                    while (resultSet.next()) {
                        System.out.println("Roll Number" + resultSet.getInt("RollNumber"));
                        System.out.println("Name: " + resultSet.getString("Name"));
                        System.out.println("GPA: " + resultSet.getFloat("Gpa"));
                        System.out.println("Gmail: " + resultSet.getString("Gmail"));
                        System.out.println("Phone Number: " + resultSet.getInt("contactnumber"));
                        System.out.println("\n");
                    }
                    check = false;

                }
                else if (choice == 2) {

                    String searcher = "SELECT * FROM studentmanagement WHERE Name = ?";
                    PreparedStatement statement2 = connection.prepareStatement(searcher);
                    boolean checker = true;
                    System.out.println("Enter the Name You want to Search: ");
                    String name = input.next();
                    check = false;
                    while(!check || (checkName(name) != name.length())){
                        if(name == null){
                            System.out.println("Field Cannot be left Empty");
                            System.out.println("Enter New Name: ");
                            name = input.next();
                            check = false;
                        }
                        else{
                            check = true;
                        }
                    }
                    statement2.setString(1, name);
                    ResultSet resultSet = statement2.executeQuery();
                    System.out.println("\n\n--------------------------------------------");
                    System.out.println("\tROLL NUMBER\t\tNAME\t\tGPA\t\tGMAIL\t\tPHONE NUMBER");
                    while (resultSet.next()) {
                        System.out.println("Roll Number" + resultSet.getInt("RollNumber"));
                        System.out.println("Name: " + resultSet.getString("Name"));
                        System.out.println("GPA: " + resultSet.getFloat("Gpa"));
                        System.out.println("Gmail: " + resultSet.getString("Gmail"));
                        System.out.println("Phone Number: " + resultSet.getInt("contactnumber"));
                        System.out.println("\n");
                    }
                    check = false;
                }
                else {
                    System.out.println("\nINPUT VALID CHOICE");
                    check = true;
                }
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("UNKNOWN ERROR OCCURRED KINDLY TRY AGAIN");
                check = true;
            }
        }

    }

    //FUNCTION TO EDIT DATA:
    public void EditData(){
        Scanner input = new Scanner(System.in);
        boolean check = true;
        System.out.println("Enter the Roll Number You want to Edit data of: ");
        int number =  input.nextInt();
        while(check){
            try{
                check = checkRollNumber(number);
                if(check){
                    System.out.println("ERROR");
                    System.out.println("Roll Number Does not Exist");
                    System.out.println("Enter the Roll Number You want to Edit data of: ");
                    number =  input.nextInt();
                }
            }catch(NumberFormatException e) {
                System.out.println("ERROR");
                System.out.println("Kindly Enter Data in Correct Format");
                check = true;
            }
        }

        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/internshipdata","root","mujtaba123");
            String query = "SELECT * FROM studentmanagement WHERE RollNumber = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,number);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                int num = result.getInt("RollNumber");
                String name = result.getString("Name");
                float grade = result.getFloat("Gpa");
                String gmail = result.getString("Gmail");
                int phoneNumber = result.getInt("contactnumber");
                System.out.println("Roll Number: "+num);
                System.out.println("Name: "+name);
                System.out.println("Gpa: "+grade);
                System.out.println("Gmail: "+gmail);
                System.out.println("Phone Number: "+phoneNumber);
            }
            result.close();
            statement.close();
            boolean in = false;
            while(!in){
                System.out.println("Enter the Data You Want To Edit\nPress 1 for Name\nPress 2 for Gpa\nPress 3 for Gmail\nPress 4 for Phone Number\nEnter Choice:");
                int choice = input.nextInt();
                if(choice == 1){
                    System.out.println("Enter New Name: ");
                    String newName = input.next();
                    check = false;
                    while(!check || (checkName(newName) != newName.length())){
                        if(newName == null){
                            System.out.println("Field Cannot be left Empty");
                            System.out.println("Enter New Name: ");
                            newName = input.next();
                            check = false;
                        }
                        else{
                            check = true;
                        }
                    }
                    String sql = "UPDATE studentmanagement SET Name = ? WHERE RollNumber = ?";
                    PreparedStatement statement1 = connection.prepareStatement(sql);
                    statement1.setString(1,newName);
                    statement1.setInt(2,number);
                    int temp = statement1.executeUpdate();
                    in = true;
                    statement1.close();
                }
                else if(choice == 2){
                    System.out.println("Enter New GPA: ");
                    float grade = input.nextFloat();
                    check = false;
                    while(!check){
                        try{
                            check = checkGPA(grade);
                            if(!check){
                                System.out.println("Enter New GPA: ");
                                grade = input.nextFloat();
                            }
                        }catch(NumberFormatException e){
                            check = false;
                            System.out.println("ERROR");
                            System.out.println("Kindly Enter Data in Correct Format");
                            System.out.println("Enter New GPA: ");
                            grade = input.nextFloat();
                        }
                    }
                    String sql = "UPDATE studentmanagement SET Gpa = ? WHERE RollNumber = ?";
                    PreparedStatement statement1 = connection.prepareStatement(sql);
                    statement1.setFloat(1,grade);
                    statement1.setInt(2,number);
                    int temp = statement1.executeUpdate();
                    statement1.close();
                    in = true;
                }
                else if (choice == 3) {
                    System.out.println("Enter New Gmail: ");
                    String gmail = input.next();
                    check = false;
                    while(!check){
                        if(gmail == null){
                            check = false;
                            System.out.println("Field Cannot be left empty");
                            System.out.println("Enter New Gmail: ");
                            gmail = input.next();
                        }
                        else{
                            check = true;
                        }
                    }

                    String sql = "UPDATE studentmanagement SET Gpa = ? WHERE RollNumber = ?";
                    PreparedStatement statement1 = connection.prepareStatement(sql);
                    statement1.setString(1,gmail);
                    statement1.setInt(2,number);
                    int temp = statement1.executeUpdate();
                    statement1.close();
                    in = true;
                }
                else if (choice == 4) {
                    System.out.println("Enter New Phone Number: ");
                    int num = input.nextInt();
                    check = false;
                    while(!check){
                        try{
                            check = true;
                        }catch(NumberFormatException e){
                            System.out.println("ERROR");
                            System.out.println("Kindly Enter Data in Correct Format");
                            System.out.println("Enter New Phone Number: ");
                            num = input.nextInt();
                        }
                    }

                    String sql = "UPDATE studentmanagement SET Gpa = ? WHERE RollNumber = ?";
                    PreparedStatement statement1 = connection.prepareStatement(sql);
                    statement1.setInt(1,num);
                    statement1.setInt(2,number);
                    int temp = statement1.executeUpdate();
                    statement1.close();
                    in = true;
                }
                else{
                    in = false;
                    System.out.println("\nIn valid Input...KINDLY Choose from the given options only\n");
                }
                if(in){
                    String stat = "SELECT * FROM studentmanagement WHERE RollNumber = ?";
                    PreparedStatement statement2 = connection.prepareStatement(stat);
                    statement2.setInt(1,number);
                    ResultSet resultNew = statement2.executeQuery();
                    System.out.println("\n\nUPDATED DATA: ");
                    while(resultNew.next()){
                        int num = resultNew.getInt("RollNumber");
                        String name = resultNew.getString("Name");
                        float grade = resultNew.getFloat("Gpa");
                        String gmail = resultNew.getString("Gmail");
                        int phoneNumber = resultNew.getInt("contactnumber");
                        System.out.println("Roll Number: "+num);
                        System.out.println("Name: "+name);
                        System.out.println("Gpa: "+grade);
                        System.out.println("Gmail: "+gmail);
                        System.out.println("Phone Number: "+phoneNumber);
                    }
                    resultNew.close();
                    statement2.close();
                }
                connection.close();
            }

        }catch(Exception e){
            e.printStackTrace();
            System.out.println("UNKNOWN ERROR OCCURRED");
            System.out.println("KINDLY REBOOT THE PROGRAM");
        }
    }

    //FUNCTION TO DELETE DATA:
    public void DeleteData(){
        Scanner input = new Scanner(System.in);
        boolean check = true;
        System.out.println("Enter the Roll Number You want to delete data of: ");
        int number =  input.nextInt();
        while(check){
            try{
                check = checkRollNumber(number);
                if(check){
                    System.out.println("ERROR");
                    System.out.println("Roll Number Does not Exist");
                    System.out.println("Enter the Roll Number You want to delete data of: ");
                    number =  input.nextInt();
                }
            }catch(NumberFormatException e) {
                System.out.println("ERROR");
                System.out.println("Kindly Enter Data in Correct Format");
                check = true;
            }
        }
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/internshipdata","root","mujtaba123");
            String query = "DELETE FROM studentmanagement WHERE RollNumber = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,number);
            int temp = statement.executeUpdate();
            System.out.println("DATA DELETED SUCCESSFULLY");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("UNKNOWN ERROR OCCURRED KINDLY REBOOT THE PROGRAM");
        }
    }

    //FUNCTION TO ADD STUDENT:
    public void addStudent(){
        try{
            //Connection for MYSQL SERVER
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/internshipdata","root","mujtaba123");
            System.out.println("CONNECTION MADE");

            //Setting Statement
            String query = "INSERT INTO studentmanagement (RollNumber,Name,Gpa,Gmail,contactnumber) VALUES (?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            System.out.println("STATEMENT MADE");

            //INPUT Data:
            student Student = inputData();

            //Addition of data:
            statement.setInt(1,Student.roll_number);
            System.out.println("DATA ADDED");
            statement.setString(2,Student.name);
            System.out.println("DATA ADDED");
            statement.setFloat(3,Student.grade);
            System.out.println("DATA ADDED");
            statement.setString(4,Student.gmail);
            System.out.println("DATA ADDED");
            statement.setInt(5,Student.phoneNumber);
            System.out.println("DATA ADDED");

            //Executing Statement
            int temp = statement.executeUpdate();
            System.out.println("Statement executed");

            //Closing Connection:
            statement.close();
            connection.close();
            System.out.println("Connection closed");

        }catch(Exception e){
            e.printStackTrace();
            System.out.println("!!Could Not add Data Due to Unknown Error!!");
            System.out.println("KINDLY REBOOT THE PROGRAM");
        }
    }


    //INPUT AND CHECKER FUNCTIONS FOR CORRECT SYNTAX (VALIDATION CHECKS APPLIED HERE):
    public student inputData(){
        Scanner input = new Scanner(System.in);
        student Student = new student();
        boolean check = false;
        while(!check){
            try{
                System.out.println("Enter Roll Number: ");
                Student.setRoll_number(input.nextInt());
                check = checkRollNumber(Student.roll_number);
                if(!check){
                    System.out.println("ERROR");
                    System.out.println("Roll Number Already Exist");
                }
            }catch(NumberFormatException e) {
                System.out.println("ERROR");
                System.out.println("Kindly Enter Data in Correct Format");
            }
        }
        check = false;
        while(!check){
            System.out.println("Enter Name: ");
            Student.setName(input.next());
            if(Student.name == null || (checkName(Student.name) != Student.name.length())){
                System.out.println("Enter Correctly");
                check = false;
            }
            else{
                check = true;
            }
        }
        check = false;
        while(!check){
            try{
                System.out.println("Enter Phone Number: ");
                Student.setPhoneNumber(input.nextInt());
                check = true;
            }catch(NumberFormatException e){
                System.out.println("ERROR");
                System.out.println("Kindly Enter Data in Correct Format");
            }
        }
        check = false;
        while(!check){
            try{
                System.out.println("Enter Grade: ");
                Student.setGrade(input.nextFloat());
                check = checkGPA(Student.grade);
            }catch(NumberFormatException e){
                System.out.println("ERROR");
                System.out.println("Kindly Enter Data in Correct Format");
            }
        }
        check = false;
        while(!check){
            System.out.println("Enter Email: ");
            Student.setGmail(input.next());
            if(Student.gmail == null){
                check = false;
                System.out.println("Field Cannot be left empty");
            }
            else{
                check = true;
            }
        }
        return Student;
    }

    public int checkName(String input) {
        int count = 0;
        String temp = input.toLowerCase();
        for (int i = 0; i < temp.length(); i++) {
            if (temp.charAt(i) >= 97 && temp.charAt(i) <= 122 || temp.charAt(i) == ' ') {
                count++;
            }
        }
        return count;
    }

    public boolean checkRollNumber(int rollnumber){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/internshipdata","root","mujtaba123");
            String query = "SELECT * FROM studentmanagement WHERE RollNumber = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,rollnumber);
            ResultSet result = statement.executeQuery();
            if(result.next()){
                statement.close();
                connection.close();
                return false;
            }
            else{
                statement.close();
                connection.close();
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("ERROR OCCURRED KINDLY TRY AGAIN");
            return false;
        }
    }

    public boolean checkGPA(float GPA){
        if((GPA >= 0.0F) && (GPA <= 4.0F)){
            return true;
        }
        else{
            System.out.println("Enter Correct GPA");
            return false;
        }
    }
}

class student {
    String name,gmail;
    int roll_number,phoneNumber;
    float grade;

    //Setter Functions
    public void setName(String name) {
        this.name = name;
    }
    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setRoll_number(int roll_number) {
        this.roll_number = roll_number;
    }
    public void setGrade(float grade) {
        this.grade = grade;
    }
    public void setGmail(String gmail) {
        this.gmail = gmail;
    }
}