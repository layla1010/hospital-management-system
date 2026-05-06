package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import enums.BiologicalSex;
import utils.MyFileLogWriter;

public class Nurse extends StaffMember implements Serializable {
	
	private int licenseNumber;
	
	
	//constructors
	public Nurse(int id, String firstName, String lastName, Date birthDate, String address, String phoneNumber,
			String email, String gender, Date workStartDate,String username,String password,String profilePicturePath,
			HashSet<Department> departments, double salary, int licenseNumber) {
		super(id, firstName, lastName, birthDate, address, phoneNumber, email, gender, workStartDate,username,password,profilePicturePath
				,departments, salary);
		this.licenseNumber = licenseNumber;
	}
	
	public Nurse(int id, String firstName, String lastName, Date birthDate, String address, String phoneNumber,
			String email, String gender, Date workStartDate,String username,String password, String profilePicturePath,
			 double salary, int licenseNumber) {
		super(id, firstName, lastName, birthDate, address, phoneNumber, email, gender, workStartDate,username,password, profilePicturePath
				 ,salary);
		this.licenseNumber = licenseNumber;
	}

	//getters
	public int getLicenseNumber() {
		return licenseNumber;
	}


	//setters
	public void setLicenseNumber(int licenseNumber) {
		this.licenseNumber = licenseNumber;
	}


	//toString based on the super.toString()
	@Override
	public String toString() {
		return "Nurse ["+ super.toString()+", licenseNumber=" + licenseNumber 
				+  "]";
	}
	
	

}
