package doctorappointmentdelima;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Scanner;


public class DOCTORAPPOINTMENTDELIMA {

    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);    
        boolean exit = true;
        do{
        System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||"); 
        System.out.println("     ----- DOCTOR APPOINTMENT RECORDS -----     ");
        System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||"); 
        System.out.println("");
        System.out.println("================");
        System.out.println("|1. DOCTOR     |");
        System.out.println("|2. PATIENT    |");
        System.out.println("|3. APPOINTMENT|");
        System.out.println("|4. EXIT       |");
        System.out.println("================");
        System.out.println("");
        System.out.println("|============|");
        System.out.println("|Enter Action|");
        System.out.println("|============|");
        System.out.println(":");
        int act = sc.nextInt();
        
        switch(act){
            case 1:
                Doctor doctor = new Doctor ();
                doctor.doctorTransaction();
            break;
            
            case 2:
                Patient patient = new Patient ();
                patient.patientTransaction();
            break;
            
            case 3:
                Appointment ap = new Appointment ();
                ap.apTransaction();
            break;
          
            case 4:
                System.out.print("You want to exit? (yes/no): ");
                String resp = sc.next();
                    if(resp.equalsIgnoreCase("yes")){
                           exit = false;
                }
            break;   
            
        }
        }while (exit);
        System.out.print("Thank you, See you soonest!");               
    
    }
} 