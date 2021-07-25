package Lesson2_8.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class ClientHandler {
    private static Connection connection;

    private MainServ serv;
    private Socket socket;
    private String nick;
    DataInputStream in;
    DataOutputStream out;


    List<String> listNick= new ArrayList<>();

    public ClientHandler(MainServ serv, Socket socket) {
        try {
            this.serv = serv;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String str = in.readUTF();
                            if (str.startsWith("/auth")) {
                                String[] tokens = str.split(" ");
                                String currentNick = AuthService.getNickByLoginAndPass(tokens[1], tokens[2]);
                                if (currentNick != null && !serv.chekNick(currentNick)) {
                                    sendMsg("/authok");
                                    nick = currentNick;
                                    listNick.add(nick);
                                    serv.subscribe(ClientHandler.this);
                                    break;
                                } else {
                                    sendMsg((!serv.chekNick(currentNick)?"Неверный логин/пароль" :nick +" уже авторизован"));
                                }
                            }
                        }

                        while (true) {
                            String str = in.readUTF();
                            System.out.println("Client " + str);
                            if (str.equals("/end")) {
                                out.writeUTF("/serverclosed");
                                break;
                            }
                            if (str.startsWith("/w ")){
                                serv.sendLs(ClientHandler.this, str);
                            }
                            if (str.startsWith("/bl ")) { // /blacklist nick3
                                String[] tokens = str.split(" ");
                                String idNick = getIdNick(nick);
                                String idNickBl = getIdNick(tokens[1]);

                                if (Integer.parseInt(idNick)==Integer.parseInt(idNickBl)){
                                    sendMsg("Нельзя добавить в черный список самого себя");
                                } else if (!checkBlackList(idNick,idNickBl)) {
                                    addBlacklist(idNick, idNickBl);
                                    sendMsg("Вы добавили пользователя " + tokens[1] + " в черный список");
                                } else {
                                    sendMsg("Пользователь " + tokens[1] + " уже добавлен в черный список");
                                }

                            } else {
                                serv.broadcastMsg(ClientHandler.this,nick + " : " + str);
                            }

                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        serv.unsubscribe(ClientHandler.this);
                    }

                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getNick() {return nick;}


    public static String getIdNick (String nick){
        String idNick1 = "";
        try {
        ResultSet idNick = AuthService.stmt.executeQuery("SELECT id FROM users WHERE nickname = '" + nick + "'");
        idNick1 = idNick.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idNick1;
    }

    public boolean checkBlackList(String idNick1, String idNick2) {
        try {
           ResultSet chek = AuthService.stmt.executeQuery("SELECT id FROM blackList WHERE user LIKE '" + idNick1 + "' and userB LIKE '" + idNick2 + "';");
           if (chek.next()) {
               return true;
           }

       } catch (SQLException e) {
           e.printStackTrace();
       }
       return false;
    }
   public void addBlacklist (String idNick1, String idNick2){
       try {
           String query = "INSERT INTO blackList (user, userB) VALUES (?, ?);";
           PreparedStatement add = AuthService.getConnection().prepareStatement(query);
           add.setString(1, String.valueOf(idNick1));
           add.setString(2, String.valueOf(idNick2));
           add.executeUpdate();
       } catch (SQLException throwables) {
           throwables.printStackTrace();
       }
   }
}
