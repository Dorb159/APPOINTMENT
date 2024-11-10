package doctorappointmentdelima;

import java.util.Scanner;


public class Patient {
    public void patientTransaction(){
        
        Scanner sc = new Scanner (System.in);
        String response;
        do{
          
        System.out.println("");     
        System.out.println("||||||||||||||||||||||||||||||||||||||||");
        System.out.println("     ----- WELCOME TO PATIENT -----     ");   
        System.out.println("||||||||||||||||||||||||||||||||||||||||");
        System.out.println("");
        System.out.println("====================");
        System.out.println("|1.  ADD PATIENT   |");
        System.out.println("|2.  VIEW PATIENT  |");
        System.out.println("|3.  UPDATE PATIENT|");
        System.out.println("|4.  DELETE PATIENT|");
        System.out.println("|5.  EXIT PATIENT  |");
        System.out.println("====================");
        System.out.println("");
        
        System.out.println("|============|");
        System.out.println("|Enter Action|");
        System.out.println("|============|");
        System.out.println(":");
      
        int action = sc.nextInt();
        Patient patient = new Patient ();

        switch(action){
            case 1:
                patient.addPatients();
                break;
            case 2:       
                patient.viewPatients();
                break;
            case 3:
                patient.viewPatients();
                patient.updatePatients();
                patient.viewPatients();
                break;
            case 4:
                patient.viewPatients();
                patient.deletePatients();
                patient.viewPatients();    
                break;
        }
        System.out.println("Do you want to continue? (yes/no)");
        response = sc.next();
    }while(response.equalsIgnoreCase("yes"));
    System.out.println("Thank You, See you soonest!");
    
    }
    
    
    public void addPatients () { 
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

        String sql = "INSERT INTO tbl_patient (p_fname, p_lname, p_email, p_status) VALUES (?, ?, ?, ?)";
        conf.addRecord(sql, fname, lname, email, stat);


    }

    public void viewPatients() {
        config conf = new config();
        String Query = "SELECT * FROM tbl_patient";
        String[] Headers = {"Patients ID","First Name", "Last Name", "Email", "Status"};
        String[] Columns = {"p_id", "p_fname", "p_lname", "p_email", "p_status"};
        
        
        conf.viewRecords(Query, Headers, Columns);
    }
    private void updatePatients() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.println("Enter the ID to update: ");
        int id = sc.nextInt();
  
        while(conf.getSingleValue("SELECT p_id FROM tbl_patient WHERE p_id = ?", id) == 0){
        System.out.println("Selected ID doesn't exist!");
        System.out.print("Select Patient ID Again: ");
        id = sc.nextInt();
        }
        
        System.out.println("New Patient First Name: ");
        String nfname = sc.next();
        System.out.println("New Last Name: ");
        String nlname = sc.next();
        System.out.println("New Email: ");
        String nemail = sc.next();
        System.out.println("New Status: ");
        String nstat = sc.next();
        String qry = "UPDATE tbl_patient SET p_fname = ?, p_lname = ?, p_email = ?, p_status = ? WHERE p_id = ?";
        
        
        conf.updateRecord(qry, nfname, nfname, nemail, nstat, id);         
        
        
    }
    
    private void deletePatients() {
        Scanner sc = new Scanner (System.in);
        config conf = new config();
        System.out.println("Enter the ID  to delete: ");
        int id = sc.nextInt();
        
        while(conf.getSingleValue("SELECT p_id FROM tbl_patient WHERE p_id = ?", id) == 0){
        System.out.println("Selected ID doesn't exist!");
        System.out.print("Select Patient ID Again: ");
        id = sc.nextInt();
        }
        
        String qry = "DELETE FROM tbl_patient WHERE p_id = ?";
        
       
        conf.deleteRecord(qry, id);
    }
}



