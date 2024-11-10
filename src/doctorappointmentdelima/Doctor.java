package doctorappointmentdelima;

import java.util.Scanner;


public class Doctor {
    public void doctorTransaction(){
        
        Scanner sc = new Scanner (System.in);
        String response;
        do{
            
        System.out.println("");    
        System.out.println("|||||||||||||||||||||||||||||||||||||||"); 
        System.out.println("     ----- WELCOME TO DOCTOR -----     ");   
        System.out.println("|||||||||||||||||||||||||||||||||||||||"); 
        System.out.println("");
        System.out.println("==================");
        System.out.println("|1. ADD DOCTOR   |");
        System.out.println("|2. VIEW DOCTOR  |");
        System.out.println("|3. UPDATE DOCTOR|");
        System.out.println("|4. DELETE DOCTOR|");
        System.out.println("|5. EXIT DOCTOR  |");
        System.out.println("==================");
        System.out.println("");
        
        System.out.println("|============|");
        System.out.println("|Enter Action|");
        System.out.println("|============|");
        System.out.println(":");
        int action = sc.nextInt();
        Doctor doctor = new Doctor ();

        switch(action){
            case 1:
                doctor.addDoctors();
                break;
            case 2:       
                doctor.viewDoctors();
                break;
            case 3:
                doctor.viewDoctors();
                doctor.updateDoctors();
                doctor.viewDoctors();
                break;
            case 4:
                doctor.viewDoctors();
                doctor.deleteDoctors();
                doctor.viewDoctors();    
                break;
        }
        System.out.println("Do you want to continue? (yes/no)");
        response = sc.next();
    }while(response.equalsIgnoreCase("yes"));
    System.out.println("Thank You, See you soonest!");
    
    }
    
    
    public void addDoctors () { 
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        
        System.out.print("First Name: ");
        String fname = sc.nextLine();
        System.out.print("Last Name: ");
        String lname = sc.next();
        System.out.print("Email: ");
        String email = sc.next();
        System.out.print("Status: ");
        String stat = sc.next();

        String sql = "INSERT INTO tbl_doctor (d_fname, d_lname, d_email, d_status) VALUES (?, ?, ?, ?)";
        conf.addRecord(sql, fname, lname, email, stat);


    }

    public void viewDoctors() {
        config conf = new config();
        String Query = "SELECT * FROM tbl_doctor";
        String[] Headers = {"Doctors ID","First Name", "Last Name", "Email", "Status"};
        String[] Columns = {"d_id", "d_fname", "d_lname", "d_email", "d_status"};
        
        
        conf.viewRecords(Query, Headers, Columns);
    }
    private void updateDoctors() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.println("Enter the ID to update: ");
        int id = sc.nextInt();
  
        while(conf.getSingleValue("SELECT d_id FROM tbl_doctor WHERE d_id = ?", id) == 0){
        System.out.println("Selected ID doesn't exist!");
        System.out.print("Select Doctor ID Again: ");
        id = sc.nextInt();
        }
        
        System.out.println("New Doctor First Name: ");
        String nfname = sc.next();
        System.out.println("New Last Name: ");
        String nlname = sc.next();
        System.out.println("New Email: ");
        String nemail = sc.next();
        System.out.println("New Status: ");
        String nstat = sc.next();
        String qry = "UPDATE tbl_doctor SET d_fname = ?, d_lname = ?, d_email = ?, d_status = ? WHERE d_id = ?";
        
        
        conf.updateRecord(qry, nfname, nfname, nemail, nstat, id);         
        
        
    }
    
    private void deleteDoctors() {
        Scanner sc = new Scanner (System.in);
        config conf = new config();
        System.out.println("Enter the ID  to delete: ");
        int id = sc.nextInt();
        
        while(conf.getSingleValue("SELECT d_id FROM tbl_doctor WHERE d_id = ?", id) == 0){
        System.out.println("Selected ID doesn't exist!");
        System.out.print("Select Doctor ID Again: ");
        id = sc.nextInt();
        }
        
        String qry = "DELETE FROM tbl_doctor WHERE d_id = ?";
        
       
        conf.deleteRecord(qry, id);
    }
}



