import java.util.Calendar;


public class Person {

//Variables	
private String firstname; 
private String lastname;
private String ssn;
private Calendar dob = Calendar.getInstance(); 
private String gender; 

//Constructors
Person(){}; 
Person(String firstname, String lastname){
	this.firstname = firstname; 
	this.lastname = lastname; }
Person(String firstname, String lastname, String gender){
	this.firstname = firstname; 
	this.lastname = lastname;
	this.gender = gender;
	}
Person(String firstname, String lastname, String gender, String ssn){
	this.firstname = firstname; 
	this.lastname = lastname;
	this.gender = gender;
	this.ssn = ssn;
	}
Person(String firstname, String lastname, String gender, String ssn, Calendar dob){
	this.firstname = firstname; 
	this.lastname = lastname;
	this.gender = gender;
	this.ssn = ssn;
	this.dob = dob; 
	}

//Set get
public void setFirstname(String firstname){this.firstname = firstname;}
public String getFirstname(){return this.firstname;}
public void setLastname(String lastname){this.lastname = lastname;}
public String getLastname(){return this.lastname;}
public void setSSN(String ssn){this.ssn = ssn;}
public String getSSN(){return this.ssn;}
public void setDOB(int yyyy, int mm, int dd){
	this.dob.set(Calendar.YEAR, yyyy);
	this.dob.set(Calendar.MONTH, mm);
	this.dob.set(Calendar.DAY_OF_MONTH, dd);
}
public void setDOB(Calendar dob){this.dob = dob;}
public Calendar getDOB(){return this.dob;}
public void setGender(String gender){this.gender = gender;}
public String getGender(){return this.gender;}

}



