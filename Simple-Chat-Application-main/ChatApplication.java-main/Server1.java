import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server1 extends JFrame{
    ServerSocket serverSocket;
    Socket socket;
    BufferedReader bufferedReader;
    PrintWriter printWriter;
    JLabel heading =  new JLabel("Server Area");
    JTextArea messegearea = new JTextArea();
    JTextField messegeinput = new JTextField();
    Font font = new Font("Roboto",Font.PLAIN,20);

    public Server1(){
        try
        {
            ServerSocket serverSocket = new ServerSocket(7777);
            System.out.println("Server is ready to accept connection");
            socket = serverSocket.accept();

            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream());

            GUI();
            HandleEvents();
            startreading();
            startwriting();
        }
        catch (IOException e) {
            System.out.println("Server socket Exception");
        }

    }
    public void GUI()
    {
        this.setTitle("Client Messenger");
        this.setSize(600,700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        heading.setFont(font);
        messegearea.setFont(font);
        messegeinput.setFont(font);

        heading.setIcon(new ImageIcon());
        heading.setHorizontalTextPosition(SwingConstants.CENTER);
        heading.setVerticalTextPosition(SwingConstants.BOTTOM);
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        messegearea.setEditable(false);
        messegeinput.setHorizontalAlignment(SwingConstants.CENTER);

        this.setLayout(new BorderLayout());


        this.add(heading,BorderLayout.NORTH);
        JScrollPane jScrollPane = new JScrollPane(messegearea);
        this.add(jScrollPane,BorderLayout.CENTER);
        this.add(messegeinput,BorderLayout.SOUTH);

        this.setVisible(true);
    }
    public void HandleEvents() {
        messegeinput.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

                if (e.getKeyCode() == 10){

                    String contentToSend = messegeinput.getText();
                    messegearea.append("Me :" + contentToSend + "\n");
                    printWriter.println(contentToSend);
                    printWriter.flush();
                    messegeinput.setText("");
                    messegeinput.requestFocus();
                }
            }
        });
    }
    public void startreading()
    {Runnable r1 = ()->
    {
        System.out.println("reader started");
        try {
            while (true) {
                String msg = null;

                msg = bufferedReader.readLine();
                if (msg.equals("exit")) {
                    System.out.println("client terminated the chat");
                    socket.close();
                    break;
                }
                System.out.println("Client : " + msg);
            }
        }
                catch (IOException e) {
                    System.out.println("Start read Exception");
                }




    };
        new Thread(r1).start();
    }
    private void startwriting()
    {Runnable r2 = ()->
    {

        System.out.println("Writer Started");
        try{
            while (!socket.isClosed()) {
                BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
                String content = br1.readLine();
                printWriter.println(content);
                printWriter.flush();

                if (content.equals("exit")) {
                socket.close();
                break;
                }
            }
        }
        catch (IOException e) {
            System.out.println("Server Start Write Exception");
        }

    };
        new Thread(r2).start();
    }
    public static void main(String[] args) {
        System.out.println("this is server .... Starting  .....");
        new Server1();
    }
}
