import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server implements Calculator {

    public Server() throws RemoteException {
    }

    @Override
    public Double sum(Double x, Double y) {
        return x + y;
    }

    @Override
    public Double subtract(Double x, Double y) {
        return x - y;
    }

    @Override
    public Double multiply(Double x, Double y) {
        return x * y;
    }

    @Override
    public Double divide(Double x, Double y) {
        if (y == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return x / y;
    }

    public static void main(String args[]) {
        try {
            Server obj = new Server();
            Calculator stub = (Calculator) UnicastRemoteObject.exportObject(obj, 0);

            // Use a constant for the RMI registry port and server name
            int registryPort = 9400;
            String serverName = "Calculator";

            // Locate or create the RMI registry on the specified port
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", registryPort);

            // Bind the remote object's stub in the registry
            registry.bind(serverName, stub);

            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
