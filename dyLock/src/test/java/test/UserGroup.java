package test;

import java.util.ArrayList;
import java.util.List;

public class UserGroup {
	private String aame;  
    private List<User> date = new ArrayList<User>();  
  
  
  
    public String getAame() {
		return aame;
	}

	public void setAame(String aame) {
		this.aame = aame;
	}

	public List<User> getDate() {
		return date;
	}

	public void setDate(List<User> date) {
		this.date = date;
	}

	@Override  
    public String toString() {  
        return "UserGroup [aame=" + aame + ", date=" + date + "]";  
    } 
}
