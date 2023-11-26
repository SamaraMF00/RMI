import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
    private static Scanner sc = new Scanner(System.in, "UTF-8");

    private Client() {
    }

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 9400);
            Calculator stub = (Calculator) registry.lookup("Calculator");

            while (true) {
                printMenu();
                int resp = sc.nextInt();

                if (resp == 0) {
                    break;
                }

                performOperation(resp, stub);
            }

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }

    private static void printMenu() {
        System.out.println("\nEscolha uma operação:\n" +
                "1) Soma\n" +
                "2) Subtração\n" +
                "3) Multiplicação\n" +
                "4) Divisão\n" +
                "0) Sair\n");
    }

    private static void performOperation(int resp, Calculator stub) throws RemoteException {
        Double x, y;
        String response;

        System.out.println(getOperationDescription(resp));
        System.out.println("Preencha o valor de x:");
        x = sc.nextDouble();
        System.out.println("Preencha o valor de y:");
        y = sc.nextDouble();

        switch (resp) {
            case 1:
                response = stub.sum(x, y).toString();
                break;
            case 2:
                response = stub.subtract(x, y).toString();
                break;
            case 3:
                response = stub.multiply(x, y).toString();
                break;
            case 4:
                response = stub.divide(x, y).toString();
                break;
            default:
                response = "Nenhuma opcao valida selecionada.";
        }

        System.out.println("Response: " + response);
    }

    private static String getOperationDescription(int resp) {
        switch (resp) {
            case 1:
                return "Esta operação realizará uma soma de x + y";
            case 2:
                return "Esta operação realizará uma subtração de x - y";
            case 3:
                return "Esta operação realizará uma multiplicação de x * y";
            case 4:
                return "Esta operação realizará uma divisão de x / y";
            default:
                return "Operação não reconhecida.";
        }
    }
}
