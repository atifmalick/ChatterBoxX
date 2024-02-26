import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends JFrame{
    Socket socket;
    BufferedReader bufferedReader;
    PrintWriter printWriter;

    JLabel heading =  new JLabel("Client Area");
    JTextArea messegearea = new JTextArea();
    JTextField messegeinput = new JTextField();
    Font font = new Font("Roboto",Font.PLAIN,20);


    Client(){
        try {
            System.out.println("Sending request to server");
            socket = new Socket("127.0.0.1",7777);
            System.out.println("Connected");

            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream());
            GUI();
            HandleEvents();
            startreading();
            startwriting();

        }
        catch (IOException e) {
            System.out.println("Client constructor Exception");
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
                    System.out.println("Server terminated the chat");
                    JOptionPane.showMessageDialog(this,"Server terminated the Chat");
                    messegeinput.setEnabled(false);
                    socket.close();
                    break;
                }
//                System.out.println("Server : " + msg);
                messegearea.append("Server :" + msg+"\n");
            }
        }
        catch (IOException e) {
            System.out.println("Client Start read Exception");
        }

    };
        new Thread(r1).start();
    }

    public void startwriting()
    {Runnable r2 = ()->
    {


        String content = null;
        System.out.println("Writer Started");
        try {
            while (!socket.isClosed()) {
                BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
                content = br1.readLine();
                printWriter.println(content);
                printWriter.flush();

                if (content.equals("exit")) {
                    socket.close();
                    break;
                }
            }
        }
        catch (IOException e) {
            System.out.println("Client start write Exception");
        }
    };
        new Thread(r2).start();
    }
    public static void main(String[] args) {
        System.out.println("this is CLient");
        new Client();
    }
}
