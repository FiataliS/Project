package Lesson2_8.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class MainServ {
    private Vector<ClientHandler> clients;

    public MainServ() {
        clients = new Vector<>();
        ServerSocket server = null;
        Socket socket = null;

        try {
            AuthService.connect();
            server = new ServerSocket(8189);
            System.out.println("Сервер запущен!");
            while (true) {
                //String login = AuthService.getNickByLoginAndPass("login1", "pass1");
                socket = server.accept();
                System.out.println("Клиент подключился!");
                new ClientHandler(this, socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            AuthService.disconnect();
        }
    }

    public void subscribe(ClientHandler client) {
        clients.add(client);
        broadcastClientsList();
    }

    public void unsubscribe(ClientHandler client) {
        clients.remove(client);
        broadcastClientsList();
    }
    public void broadcastMsg(ClientHandler from, String msg) {
        for (ClientHandler o : clients) {
            if (!o.checkBlackList(ClientHandler.getIdNick(o.getNick()), ClientHandler.getIdNick(from.getNick()))) {
                o.sendMsg(msg);
            }
        }
    }

    public void sendLs(ClientHandler from, String str) {
        String[] msg = str.split(" ", 3);
        for (ClientHandler o : clients) {
            if (!o.checkBlackList(ClientHandler.getIdNick(o.getNick()), ClientHandler.getIdNick(from.getNick()))) {
                if (o.getNick().equals(msg[1])) {
                    o.sendMsg("ЛС от " + from.getNick() + ": " + msg[2]);
                    from.sendMsg("ЛС на " + msg[1] + ": " + msg[2]);
                    return;
                }
            }
            from.sendMsg("Ник не найден в чате");
        }
    }

    public boolean chekNick (String nick){
        for (ClientHandler o : clients) {
            if (o.getNick().equals(nick)) {
                return true;
            }
        }
        return false;
    }


    public void broadcastClientsList() {
        StringBuilder sb = new StringBuilder();
        sb.append("/clientslist ");
        for (ClientHandler o : clients) {
            sb.append(o.getNick() + " ");
        }
        String out = sb.toString();
        for (ClientHandler o : clients) {
            o.sendMsg(out);
        }
    }
}
