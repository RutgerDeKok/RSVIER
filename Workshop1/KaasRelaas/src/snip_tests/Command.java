package snip_tests;
import java.io.Serializable;

public class Command implements Serializable{
	

	private static final long serialVersionUID = -6711206790972611017L;
	
	private int clientId;
	private int commandId;
	private Object[] arguments;
	
	public Command(int clientId, int commandId, Object... args){
		this.clientId = clientId;
		this.commandId = commandId;
		this.arguments = args;

	}

	public Object[] getArguments() {
		return arguments;
	}

	public void setArguments(Object[] arguments) {
		this.arguments = arguments;
	}

	public int getCommandId() {
		return commandId;
	}

	public void setCommandId(int commandId) {
		this.commandId = commandId;
	}
	
	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder("Command = "+commandId);
		for(Object arg : arguments){
			sb.append(", "+arg.getClass());
		}
		
		return sb.toString();
		
	}
}
