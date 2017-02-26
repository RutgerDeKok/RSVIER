package snip_tests;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

	private Socket socket;

	private static int clientID = 0;
	private boolean isConnected = true;
	private Command comOut;

	public void start() {
		try {
			startConnection();
			
			comOut = new Command(clientID, 1, new String("Zomaar een argument"), new String("arg2"));
			sendCommand(comOut);
			

			do {
				//receive and esecute command
				ExcecuteCommand(receiveCommand());
			} while (isConnected);

//			socket.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void startConnection() throws Exception {

		System.out.println("C: starting client");
		String ip = "localhost";
		int port = 11111; // 1024 -65535

		System.out.println("C:  connecting to socket");
		socket = new Socket(ip, port);

	}

	

	private void ExcecuteCommand(Command comIn) throws IOException {

		if (comIn.getClientId() != -1)
			return;

		switch (comIn.getCommandId()) {
		case 0:
			System.out.println("execute command 0");
			clientID = (Integer) comIn.getArguments()[0];
			System.out.println("my id  = " + clientID);
			// resnd command with updated id
			int command = comOut.getCommandId();
			Object[] args = comOut.getArguments();
			Command comNew = new Command(clientID, command, args);
			sendCommand(comNew);
			break;
			
		case 1:
			System.out.println("execute command 1");
			break;
			
		case 2:
			System.out.println("execute command 2, closing");
			isConnected = false;
			break;
			
		default:
			break;
		}

	}

	public Command receiveCommand() throws ClassNotFoundException, IOException {

		System.out.println("receiving command");
		DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
		Command comIn = (Command) deserialize(streamToByteArray(dataInputStream));

		return comIn;
	}

	public void sendCommand(Command command) throws IOException {

		// test Object

		byte[] data = serialize(command);

		DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());
		dOut.writeInt(data.length); // write length of the message
		dOut.write(data);
		dOut.flush();
		System.out.println("bytes send : " + data.length);

	}

	private byte[] streamToByteArray(DataInputStream dIn) throws IOException {
		int arrayLength = dIn.readInt();
		byte[] returnData = new byte[arrayLength];
		dIn.readFully(returnData, 0, arrayLength); // read the message

		return returnData;
	}

	/**
	 * Creates object from byte array
	 */
	public byte[] serialize(Object obj) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectOutputStream os = new ObjectOutputStream(out);
		os.writeObject(obj);
		return out.toByteArray();
	}

	/**
	 * Creates object from byte array
	 */
	private Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
		ByteArrayInputStream in = new ByteArrayInputStream(data);
		ObjectInputStream is = new ObjectInputStream(in);
		return is.readObject();
	}

}
