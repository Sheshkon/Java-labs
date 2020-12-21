import java.io.*;
import java.net.*;
import java.util.*;


public class Server {

	private static TicTacToe game;
	public static final int PORT = 1488;
	public static final String quitMsg = "q";
	public static final String helpMsg = "?";
	public static final int USERS_MAX = 2;
	private static List<ServerClient> clientsList = new ArrayList<>();

	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(PORT);
			System.out.println("Server started...");
			try {
				game = new TicTacToe();
			} catch (Exception e) {
				System.out.println("Unable to create a game (invalid parameter)");
				e.printStackTrace();
				server.close();
				System.exit(-1);
			}

			while (Server.clientsList.size() < USERS_MAX) {
				Socket socket = server.accept();
				try {
					System.out.println("Player" + ServerClient.nextID + " connects ");
					clientsList.add(new ServerClient(socket));

				} catch (IOException e) {
					e.printStackTrace();
	

				}
				

			}
			 System.out.println("Reached maximum number of clients\nClosing server's socket");
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static class ServerClient extends Thread {
		private static int nextID = 1;

		private int ID;
		private Socket socket;
		private BufferedReader in;
		private BufferedWriter out;

		private ServerClient(Socket inSocket) throws IOException {
				ID = nextID++;
				socket = inSocket;
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				System.out.println("Player" + ID + "\'s input/output streams initialized.");
				start();
			
		}

		@Override
		public void run() {
			
			String msg = null;
			sendToThis("<Server>: You're Player" + ID + ".\n<Server>: To exit this session enter \"" + Server.quitMsg
					+ "\"." + "\n<Server>: For help enter \"" + Server.helpMsg + "\"");
			try {
				while (true) {
					
					try {

						if (game.getCurrentPlayer() == this.ID && game.isOn())
							sendToThis("<Server>: Your turn, enter position:");
					
						msg = in.readLine();
	

						if (msg.equals(Server.helpMsg)) {
							sendToThis(game.rules());
						}
						if (msg.equals(Server.quitMsg)) {
							sendToAnother("<Server>: Player" + ID + " has left");
							sendToAnother("<Server>: Please quit the game too");
							this.quitSession();
							System.out.println("Player" + ID + " disconnected");
							
							break;
						}

						sendToAll("<Player" + ID + ">: " + msg);

						if (game.getCurrentPlayer() == this.ID && game.isOn()) {

							try {
								if (game.isOn()) {
									sendToAnother("<Server>: Your turn, enter position");
								}
								if (game.takePosition(Integer.parseInt(msg))) {
									sendToAll(game.currentState());
									
									if (game.isOn()) {
										sendToAnother("<Server>: Your turn, enter position");
									}
								}
							} catch (TicTacToe.GameException e) {
								sendToThis("<Server>: " + e.getMessage());
							} catch (NumberFormatException ignored) {
							}

							try {
								game.checkGrid();
							} catch (TicTacToe.GameException e) {
								sendToAll("<Server>: " + e.getMessage());

								sendToAll("<Server>: Game is over. Enter \"" + Server.quitMsg + "\" to exit");
							}
						}
					} catch (SocketException e) {
						return;
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} catch (NullPointerException ignored) {

			}
		}

		private void quitSession() {
			try {
				
					Server.clientsList.remove(this);
					socket.close();
					in.close();
					out.flush();
					out.close();
					interrupt();
					
					
				

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		private void sendToAll(String msg) {
			for (ServerClient sc : Server.clientsList) {

				try {
					sc.out.write(msg + '\n');
					sc.out.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		private void sendToAnother(String msg) {
			for (ServerClient sc : Server.clientsList) {
				if (sc != this) {
					try {
						sc.out.write(msg + '\n');
						sc.out.flush();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

		private void sendToThis(String msg) {
			for (ServerClient sc : Server.clientsList) {
				if (sc == this) {
					try {
						sc.out.write(msg + '\n');
						sc.out.flush();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

}
