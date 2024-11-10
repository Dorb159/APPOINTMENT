package doctorappointmentdelima;

import java.util.Scanner;


public class Appointment {
    public void apTransaction(){
        
        Scanner sc = new Scanner (System.in);
        String response;
        do{
          
        System.out.println("");      
        System.out.println("||||||||||||||||||||||||||||||||||||||||||||");
        System.out.println("     ----- WELCOME TO APPOINTMENT -----     ");   
        System.out.println("||||||||||||||||||||||||||||||||||||||||||||");
        System.out.println("");
        System.out.println("========================");
        System.out.println("|1.  ADD APPOINTMENT   |");
        System.out.println("|2.  VIEW APPOINTMENT  |");
        System.out.println("|3.  UPDATE APPOINTMENT|");
        System.out.println("|4.  DELETE APPOINTMENT|");
        System.out.println("|5.  EXIT APPOINTMENT  |");
        System.out.println("========================");
        System.out.println("");
        
        System.out.println("|============|");
        System.out.println("|Enter Action|");
        System.out.println("|============|");
        System.out.println(":");
        
        int action = sc.nextInt();
        Appointment ap = new Appointment ();

        switch(action){
            case 1:
                ap.addAppointments();
                break;
            case 2:       
                ap.viewAppointments();
                break;
            case 3:
                ap.viewAppointments();
                ap.updateAppointments();
                ap.viewAppointments();
                break;
            case 4:
                ap.viewAppointments();
                ap.deleteAppointments();
                ap.viewAppointments();  
                break;
        }
        System.out.println("Do you want to continue? (yes/no)");
        response = sc.next();
    }while(response.equalsIgnoreCase("yes"));
    System.out.println("Thank You, See you soonest!");
    
    }
    
    
    public void addAppointments() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        
        Doctor doctor = new Doctor();
        doctor.viewDoctors();
        
        System.out.print("Enter the ID of the Doctor: ");
        int did = sc.nextInt();
        
        String dsql = "SELECT d_id FROM tbl_doctor WHERE d_id = ?";
        while (conf.getSingleValue(dsql, did) == 0) {
            System.out.print("Doctor does not exist, Select Again: ");
            did = sc.nextInt();
        }
        
        Patient patient = new Patient();
        patient.viewPatients();
        
        System.out.print("Enter the ID of the Patient: ");
        int pid = sc.nextInt();
        
        String psql = "SELECT p_id FROM tbl_patient WHERE p_id = ?";
        while (conf.getSingleValue(psql, pid) == 0) {
            System.out.print("Patient does not exist, Select Again: ");
            pid = sc.nextInt();
        }

        System.out.print("Appointment Date: ");
        String date = sc.next();
        sc.nextLine();
        
        System.out.print("Appointment Time: ");
        String time = sc.next();
        sc.nextLine();
        
        System.out.print("Appointment Status: ");
        String status = sc.nextLine();
        sc.nextLine();


    
    String qry = "INSERT INTO tbl_appointment (d_id, p_id, a_date, a_time, a_status) VALUES (?, ?, ?, ?, ?)";
        conf.addRecord(qry, did, pid, date, time, status);
        
     }
    
    public void viewAppointments() {
        
        String qry = "SELECT a_id, d_fname, p_fname, a_date, a_time, a_status FROM tbl_appointment "
                + "LEFT JOIN tbl_doctor ON tbl_doctor.d_id = tbl_appointment.d_id "
                + "LEFT JOIN tbl_patient ON tbl_patient.p_id = tbl_appointment.p_id";
       
        String[] hrds = {"Appointment ID", "Doctor Name", "Patient Name", "Appointment Date", "Appointment Time", "Appointment Status"};
        String[] clms = {"a_id", "d_fname", "p_fname", "a_date", "a_time", "a_status"};
        
        config conf = new config();
        conf.viewRecords(qry, hrds, clms);
    
}
     
    private void updateAppointments() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.print("Enter the ID to update: ");
        int id = sc.nextInt();
        
        while(conf.getSingleValue("SELECT a_id FROM tbl_appointment WHERE a_id = ?", id) == 0){
        System.out.println("Selected ID doesn't exist!");
        System.out.print("Select Appoinment ID Again: ");
        id = sc.nextInt();
        } 
        
        System.out.print("Enter New Appointment Status: ");
        String status = sc.next();
        
    
        String qry = "UPDATE tbl_appointment SET a_status = ? WHERE a_id = ?";
        
        
        conf.updateRecord(qry, status, id);         
}
     private void deleteAppointments() {
        Scanner sc = new Scanner (System.in);
        config conf = new config();
        System.out.print("Enter the ID  to delete: ");
        int id = sc.nextInt();
        
        while(conf.getSingleValue("SELECT a_id FROM tbl_appointment WHERE a_id = ?", id) == 0){
        System.out.println("Selected ID doesn't exist!");
        System.out.print("Select Appointment ID Again: ");
        id = sc.nextInt();
        }
        String qry = "DELETE FROM tbl_appointment WHERE a_id = ?";
        
       
        conf.deleteRecord(qry, id);
    }
}