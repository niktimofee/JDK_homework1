package seminar1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;

/*
 * Создать окно клиента чата. Окно должно содержать JtextField для ввода логина, пароля,
 * IP-адреса сервера, порта подключения к серверу, область ввода сообщений, JTextArea
 * область просмотра сообщений чата и JButton подключения к серверу и отправки сообщения в чат.
 * Желательно сразу сгруппировать компоненты, относящиеся к серверу сгруппировать на JPanel
 * сверху экрана, а компоненты, относящиеся к отправке сообщения – на JPanel снизу
 */

public class Chat extends JFrame {
    private static final int WINDOW_HEIGHT = 700;
    private static final int WINDOW_WIDTH = 500;
    private static final int WINDOW_POSX = 800;
    private static final int WINDOW_POSY = 300;
    JButton btnSend = new JButton("Send");
    JButton btnConnect = new JButton("Connect to server");
    JLabel lblLogin = new JLabel("Login:");
    JLabel lblPassword = new JLabel("Password:");
    JLabel lblIP = new JLabel("IP:");
    JLabel lblMessage = new JLabel("Message:");
    JTextField txtFieldLogin = new JTextField(22);
    JTextField txtFieldPassword = new JTextField(22);
    JTextField txtFieldIP = new JTextField(16);
    JTextField txtFieldMessage = new JTextField();
    JTextArea areaMessage = new JTextArea(30, 40);
    JPanel panServer;
    JPanel panMessage;
    JPanel panClient;
    String login;
    String password;
    String IP;
    String message;
    String file = "./chat.txt";
    Boolean serverStatus = false;

    Chat() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("ChatWindow");
        setResizable(false);
        areaMessage.setLineWrap(true);
        areaMessage.setWrapStyleWord(true);
        panServer = new JPanel(new GridLayout(3, 2, 5, 10));
        panServer.add(lblLogin);
        panServer.add(txtFieldLogin);
        panServer.add(lblPassword);
        panServer.add(txtFieldPassword);
        panServer.add(lblIP);
        panServer.add(txtFieldIP);
        panMessage = new JPanel();
        panMessage.add(lblMessage);
        panMessage.add(new JScrollPane(areaMessage));
        panClient = new JPanel(new GridLayout(3, 1, 5, 10));
        panClient.add(txtFieldMessage);
        panClient.add(btnSend);
        panClient.add(btnConnect);

        btnConnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (BufferedReader fileReader = new BufferedReader(new FileReader(file))){
                    String data = fileReader.readLine();
                    while (data != null) {
                        data = data + "\n";
                        System.out.printf(data);
                        areaMessage.append(data);
                        data = fileReader.readLine();
                    }
                }catch (IOException f){
                    throw new RuntimeException(f);
                }
                serverStatus = true;

            }
        });
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        txtFieldMessage.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    sendMessage();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        add(panServer, BorderLayout.NORTH);
        add(panMessage, BorderLayout.CENTER);
        add(panClient, BorderLayout.SOUTH);
        setVisible(true);
    }

    private void sendMessage() {
        if (!serverStatus) {
            new ChatError("Server status \"stopped\", start server");

        } else {
            message = txtFieldLogin.getText() + ": " + txtFieldMessage.getText() + "\n";
            areaMessage.append(message);
            System.out.println("Message sent: " + message);
            txtFieldMessage.setText(null);
            try (FileWriter fileWriter = new FileWriter(file, true)) {
                fileWriter.write(message);
            } catch (IOException e) {
                throw new RuntimeException();
            }
        }
    }
    public static void main(String[] args) {
        new Chat();
    }
}