package domain;

public class Existitu_adminDa {
	boolean existitu;
	boolean adminDa;
	
	public Existitu_adminDa(boolean existitu, boolean adminDa) {
		super();
		this.existitu = existitu;
		this.adminDa = adminDa;
	}

	public boolean isExistitu() {
		return existitu;
	}

	public void setExistitu(boolean existitu) {
		this.existitu = existitu;
	}

	public boolean isAdminDa() {
		return adminDa;
	}

	public void setAdminDa(boolean adminDa) {
		this.adminDa = adminDa;
	}
}
