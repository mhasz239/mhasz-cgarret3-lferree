package persist.dbmod;

public class ObjectIDCommandResponse {
	private int objectID;
	private String command;
	private String response;
	
	public ObjectIDCommandResponse() {
		
	}
	
	public void setObjectID(int objectID) {
		this.objectID = objectID;
	}
	
	public int getObjectID() {
		return this.objectID;
	}
	
	public void setCommand(String command) {
		this.command = command;
	}
	
	public String getCommand() {
		return this.command;
	}
	
	public void setResponse(String response) {
		this.response = response;
	}
	
	public String getResponse() {
		return this.response;
	}
}
