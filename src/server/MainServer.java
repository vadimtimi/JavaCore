package server;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {

    private static Socket clientSocket; //сокет для общения
    private static ServerSocket server; // серверсокет
    private static BufferedReader in; // поток чтения из сокета
    private static BufferedWriter out; // поток записи в сокет
    private static final int tcpPort = 4444;
    private static BufferedReader reader; // для чтения с консоли
    private static boolean runServer = true;

    public static void main(String[] args) {
        try {
                InetAddress inetAddress = InetAddress.getLocalHost();
                server = new ServerSocket(tcpPort); // серверсокет прослушивает порт 4444
            reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Сервер "+ inetAddress.getHostAddress() + ":" + tcpPort + " запущен!"); // хорошо бы серверу
                clientSocket = server.accept(); // accept() будет ждать пока не подключится клиент
                System.out.println("Клиент подключился: " + clientSocket.getInetAddress().getHostAddress());
                try {
                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                    new Thread(() -> {
                        try {
                            while (runServer) {
                                String str = in.readLine();
                                if(str != null) {
                                    if(!str.isEmpty()) {
                                        if (str.equals("/end")) {
                                            System.out.println("Клиент отключился");
                                            runServer = false;
                                            break;
                                        }
                                        System.out.println(str);
//                                        out.write(str);
//                                        out.flush();
                                    }
                                }else {
                                    System.out.println("Клиент отключился");
                                    runServer = false;
                                    break;
                                }
                            }
                        } catch (Exception e) {
                            runServer = false;
                            System.out.println("Клиент отключился");
                        }
                    }).start();

                    while (runServer) {
                        try {
                            System.out.println("Введите сообщение для клиента: ");
                            String text = reader.readLine(); // Можно заменить reader.read() и тогда отловить закрытие клиента runServer не жать нажатия клавиши
                            if(!runServer) {
                                break;
                            }
                            out.write(text + "\n");
                            out.flush();
                        }catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                } finally { // в любом случае сокет будет закрыт
                    System.out.println("Заверщаем работу сервера");
                    clientSocket.close();
                    // потоки тоже хорошо бы закрыть
                    in.close();
                    out.close();
                    System.out.println("Сервер закрыт!");
                    server.close();
                }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
