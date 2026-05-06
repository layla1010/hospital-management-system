package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import control.Hospital;
import enums.BiologicalSex;
import enums.Specialization;
import exceptions.FutureDateException;
import exceptions.ObjectAlreadyExistsException;
import exceptions.ObjectDoesNotExist;
import utils.MyFileLogWriter;
import utils.UtilsMethods;

public abstract class StaffMember extends Person implements Serializable {

	private Date workStartDate;
	private HashSet<Department> departments;
	private double salary;
	private String UserName;
	private String Password;
	private String profilePicturePath;
	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	//Constructors
	public StaffMember(int id, String firstName, String lastName, Date birthDate, String address, String phoneNumber,
			String email, String gender, Date workStartDate,String username,String password,String profilePicturePath,
			HashSet<Department> departments, double salary) {
		super(id, firstName, lastName, birthDate, address, phoneNumber, email, gender);
		setWorkStartDate(workStartDate);
		this.departments = departments;
		this.salary = salary;
		this.UserName=username;
		this.Password=password;
		this.profilePicturePath= profilePicturePath;
	}

	public StaffMember(int id, String firstName, String lastName, Date birthDate, String address, String phoneNumber,
			String email, String gender, Date workStartDate,String username,String password,String profilePicturePath,
			double salary) {
		super(id, firstName, lastName, birthDate, address, phoneNumber, email, gender);
		setWorkStartDate(workStartDate);
		this.departments = new HashSet<Department>();
		this.salary = salary;
		this.UserName=username;
		this.Password=password;
		this.profilePicturePath= profilePicturePath;

	}

	/**
	 * Sets the profile picture path.
	 * @param path The file path to the profile picture.
	 */
	public void setProfilePicture(String path) {
		this.profilePicturePath = path;
	}

	/**
	 * Gets the profile picture path.
	 * @return The file path to the profile picture.
	 */
	public String getProfilePicture() {
		return this.profilePicturePath;
	}

	//getters
	public Date getWorkStartDate() {
		return workStartDate;
	}

	public HashSet<Department> getDepartments() {
		return departments;
	}

	public double getSalary() {
		return salary;
	}

	public double getWorkTime() {
		//returns the WorkTime of the StaffMember, in days
		return UtilsMethods.dateDiffInDays(Hospital.TODAY, workStartDate);
	}

	//setters
	public void setWorkStartDate(Date workStartDate) {
		//if the workStartDate is after "today", it set to "today"
		if(workStartDate.after(Hospital.TODAY)) {
			throw new FutureDateException(workStartDate);
		}
		this.workStartDate = workStartDate;
	}

	public void setDepartments(HashSet<Department> departments) {
		this.departments = departments;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	//add
	public boolean addDepartment(Department department) {
		if(department==null) {
			throw new NullPointerException();
		}
		if(departments.contains(department)) {
			throw new ObjectAlreadyExistsException(department, this);

		}
		return departments.add(department);

	}

	//remove
	public boolean removeDepartment(Department department) {
		if(department==null) {
			throw new NullPointerException();
		}
		if(departments.contains(department)) {
			throw new ObjectDoesNotExist(department.getNumber(), department.getClass().getSimpleName(), this);

		}
		return departments.remove(department);

	}

	//toString based on the super.toString()
	@Override
	public String toString() {
		return super.toString()+", workStartDate=" + workStartDate +" departments=" + departments + ", salary=" + salary
				+ ", UserName=" + UserName + ", Password=" + Password + ", profilePicturePath=" + profilePicturePath;

				
	}

	public Department getDepartmentBySpecialization(Specialization specialization) {
		for(Department department:departments) {
			if (department.getSpecialization().equals(specialization)) {
				return department;
			}
		}
		return null;
	}



}
