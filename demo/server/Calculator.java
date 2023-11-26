import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Calculator extends Remote {
    Double sum(Double x, Double y) throws RemoteException;

    Double subtract(Double x, Double y) throws RemoteException;

    Double multiply(Double x, Double y) throws RemoteException;

    Double divide(Double x, Double y) throws RemoteException;
}