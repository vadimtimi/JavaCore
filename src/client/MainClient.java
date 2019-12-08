package client;

import java.io.*;
import java.net.Socket;

public class MainClient {

    private static Socket clientSocket;     //сокет для общения
    private static BufferedReader reader;   // ридер читающий с консоли

    private static BufferedReader in; // поток чтения из сокета
    private static DataOutputStream out; // поток записи в сокет

    private static final int tcpPort = 4444;

    private static boolean runClient = true;

    public static void main(String[] args) {
        try {
            try {
                // адрес - локальный хост, порт - 4444, такой же как у сервера
                clientSocket = new Socket("localhost", tcpPort); // этой строкой мы запрашиваем
                //  у сервера доступ на соединение
                reader = new BufferedReader(new InputStreamReader(System.in));
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new DataOutputStream(clientSocket.getOutputStream());

                new Thread(() -> {
                    try {
                        while (runClient) {
                            String str = in.readLine();
                            if(str != null) {
                                if(!str.isEmpty()) {
                                    System.out.println(str);
                                }
                            }else {
                                System.out.println("Сервер отключился");
                                runClient = false;
                                break;
                            }
                        }
                    } catch (Exception e) {
                        runClient = false;
                        System.out.println("Сервер отключился");
                    }
                }).start();

                while (runClient) {
                    System.out.println("Введите сообщение для сервера:");
                    // если соединение произошло и потоки успешно созданы - мы можем
                    //  работать дальше и предложить клиенту что то ввести
                    // если нет - вылетит исключение
                    String text = reader.readLine(); // ждём пока клиент что-нибудь
                    // не напишет в консоль
                    out.writeUTF(text + "\n"); // отправляем сообщение на сервер
                    out.flush();

//                    String str = in.readUTF();
//                    System.out.println(str);
                }

            } finally { // в любом случае необходимо закрыть сокет и потоки
                System.out.println("Клиент был закрыт...");
                clientSocket.close();
                in.close();
                out.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
