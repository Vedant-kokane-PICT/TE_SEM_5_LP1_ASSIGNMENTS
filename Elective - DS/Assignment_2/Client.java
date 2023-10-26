import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) {
        try {
            // Obtain a reference to the RMI registry on the server
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);

            // Look up the remote object by its binding name
            RemoteInterface stub = (RemoteInterface) registry.lookup("RemoteInterface");

            // Invoke the remote method
            String response = stub.sayHello("Alice");
            System.out.println("Message from server: " + response);
        } catch (Exception e) {
            System.out.println("Client exception: " + e.getMessage());
        }
    }
}
