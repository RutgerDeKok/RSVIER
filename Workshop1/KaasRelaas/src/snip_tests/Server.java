package snip_tests;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private boolean isConnected = true;

	int[] clientIds = new int[20];

	private final int SERVER_ID = -1;
	private ServerSocket ss;
	private Socket socket;

	public void start() {
		try {
			startConnection();

			do {
				Command comIn = receiveCommand();
				ExcecuteCommand(comIn);
			} while (isConnected);

			sendCommand(new Command(SERVER_ID,2));
			System.out.println("Closing");
			ss.close();
			socket.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public Command receiveCommand() throws ClassNotFoundException, IOException {
		System.out.println("receiving command");
		DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
		Command comIn = (Command) deserialize(streamToByteArray(dataInputStream));

		return comIn;
	}

	private void ExcecuteCommand(Command comIn) {
		int clientId = comIn.getClientId();
		if (clientId == 0) {
			assignId();
			return;
		}

		switch (comIn.getCommandId()) {
		default:
			isConnected=false;
			break;
		case 0:
			System.out.println("execute command 0");
			break;
		case 1:
			System.out.println("execute command 1");
			System.out.println(comIn.getArguments()[0]);
			System.out.println(comIn.getArguments()[1]);
			isConnected = false;
			break;
		
		}

	}

	private void assignId() {
		Command comOut = new Command(SERVER_ID, 0, getAvailableId());
		try {
			System.out.println("sending new client ID");
			sendCommand(comOut);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private int getAvailableId() {
		Integer id = 0;
		for (int i = 1; i < clientIds.length; i++) {
			if (clientIds[i] == 0) {
				clientIds[i] = i;
				id = i;
				break;
			}
		}
		return id;
	}

	public void startConnection() throws Exception {

		System.out.println("S: Server is started");
		ss = new ServerSocket(11111);

		System.out.println("S: Server is waiting for client request");
		socket = ss.accept();

		System.out.println("S: Client Connected");
	}



	private byte[] streamToByteArray(DataInputStream dIn) throws IOException {
		int arrayLength = dIn.readInt();
		byte[] returnData = new byte[arrayLength];
		dIn.readFully(returnData, 0, arrayLength); // read the message

		return returnData;
	}

	private byte[] serialize(Object obj) throws IOException {
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
