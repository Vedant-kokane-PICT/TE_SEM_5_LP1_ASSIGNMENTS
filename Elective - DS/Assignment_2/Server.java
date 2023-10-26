import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class server implements RemoteInterface {
    public server() {
    }

    public String sayHello(String name) throws RemoteException {
        return "Hello " + name;
    }

    public static void main(String[] args) {
        try {
            server obj = new server();
            RemoteInterface stub = (RemoteInterface) UnicastRemoteObject.exportObject(obj, 0);

            // Create a registry on the default port (1099)
            Registry registry = LocateRegistry.createRegistry(1099);

            // Bind the remote object's stub in the registry
            registry.bind("RemoteInterface", stub);

            System.out.println("Server ready");
        } catch (Exception e) {
            System.out.println("Server exception: " + e.getMessage());
        }
    }
}
