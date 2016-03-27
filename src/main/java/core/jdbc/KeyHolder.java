package core.jdbc;

public class KeyHolder {
	private long id;

	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return id;
	}
	
//	public boolean querySuccess() {
//		if (id == 0) {
//			return false;
//		}
//		return true;
//	}
}
