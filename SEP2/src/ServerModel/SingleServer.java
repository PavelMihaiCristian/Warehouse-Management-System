package ServerModel;

import java.io.Serializable;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import ServerController.ServerController;
import ServerController.ServerInterface;
import ServerView.ServerGUI;

public class SingleServer {

	private static SingleServer instance;

	private SingleServer() {
		runServer();
	}

	public static SingleServer getInstance() {
		if (instance == null) {
			instance = new SingleServer();
		}
		return instance;
	}

	private void runServer() {
		try {
			Registry reg = LocateRegistry.createRegistry(1099);
			ServerGUI view = new ServerGUI();
			IServerModel model = new ServerModel();
			ServerInterface server = new ServerController(model, view);
			UnicastRemoteObject.exportObject(server, 1099);
			Naming.rebind("Warehouse", server);
			view.showMessage("The server is Started....");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
