import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    private Client() {
        // Private constructor
    }

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry();
            RemoteInterface stub = (RemoteInterface) registry.lookup("RemoteInterface");
            String response = stub.sayHello("Alice");
            System.out.println("Message from the server: " + response);
        } 
        catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
