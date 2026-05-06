package enums;

import model.StaffMember;
import model.Doctor;
import model.Nurse;

public enum Role {
	Admin(3),
	Doctor(2),
	Nurse(1);
	
	private int value;
	
	private Role(int value) {
		this.value = value;
	}
	
	public static Role getUserRole(StaffMember user, boolean isAdmin) {
		if(isAdmin) {
			return Role.Admin;
		}
		
		if(user instanceof Doctor) {
			return Role.Doctor;
		}
		
		if(user instanceof Nurse) {
			return Role.Nurse;
		}
		
		return Role.Nurse; // TODO: raise exception?
	}
}
