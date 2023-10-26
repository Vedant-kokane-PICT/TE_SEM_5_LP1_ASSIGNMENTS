import java.rmi.*;

public interface RemoteInterface extends Remote{
    public String sayHello(String name) throws Exception;
}
