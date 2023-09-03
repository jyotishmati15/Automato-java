package automato.chatbot;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;

import java.util.Random;
import javax.swing.*;


public class automato_chatbot extends JFrame implements ActionListener
{
	static JTextArea area=new JTextArea();
	JTextField field=new JTextField();
	JScrollPane sp;
	JButton send;
	LocalTime time=LocalTime.now();
	LocalDate date=LocalDate.now();
	Random random=new Random();

	public automato_chatbot(String title)
	{
		super(title);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setResizable(false);
		getContentPane().setBackground(Color.pink);
		field=new JTextField();
		send=new JButton(">");
		send.setFont(new Font("Serif",Font.BOLD,25));
		send.setBackground(Color.white);
		send.setBounds(735,520,50,35);
		add(send);
		//For Text area
		area.setEditable(false);
		area.setBackground(Color.white);
		add(area);
		area.setFont(new Font("Serif",Font.PLAIN,20));
		//scrollbar
		sp=new JScrollPane(area,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		sp.setBounds(10,20,775,470);
		add(sp);
			
		//For TextField
		field.setSize(725,35);
		field.setLocation(10,520);
		field.setForeground(Color.black);
		field.setFont(new Font("Serif",Font.BOLD,25));
		add(field);
		
		send.addActionListener(this);
		getRootPane().setDefaultButton(send);
		
		
	}
	public void actionPerformed(ActionEvent e)
	{
		String message=field.getText().toLowerCase();
	   
		area.append("You : "+field.getText()+"\n");
		field.setText("");
		Socket sock=new Socket();
		if(message.equals("6")||message.contains("bye")||message.contains("exit"))
		{
			dispose();
		}
		
		else if(message.equals("1")||message.contains("help"))
		{
			area.append("Bot: here is a bunch of stuff i can help with:\n");
			area.append("Bot: 1. type 'help' for more info\nBot: 2. clock functions\nBot: 3. make a to do list\nBot: 4. play rock paper scissors\nBot: 5. search the web\nBot: 6. type 'exit' to close the chatbox\n");

		}
		else if(message.equals("2")||message.contains("timer")||message.contains("clock"))
		{
			Timerclock tc=new Timerclock();
			tc.setVisible(true);
			tc.setLocation(this.getX(),this.getY());
		}
		
		else if(message.equals("3")||message.contains("to do list")) {
		//todolist
			new ToDoListGui().setVisible(true);
		}
		else if(message.equals("4")||message.contains("rock paper scissors")) {
		//rockpaperscissors
			// instantiate a RockPaperScissorGUI obj
            RockPaperScissorGUI rockPaperScissorGUI = new RockPaperScissorGUI();

            // display the GUI
            rockPaperScissorGUI.setVisible(true);
		}
		
		else
			bot(message);
	}
	public static void searchweb(ActionEvent e,String message)
	{
		try
		{
			try
			{
				URL url=new URL("https://google.co.in");
				URLConnection connection=url.openConnection();
				connection.connect();
				area.append("Bot: Opening browser..\n");
				java.awt.Desktop.getDesktop().browse(java.net.URI.create("http://www.google.com/search?hl=en&q="+message.replace(" ", "+")+"&btnG=Google+Search"));
		
			}
			catch(Exception ee)
			{
				area.append("Bot: Connect to internet to get better results...\n");
			}
			
		}
		catch(Exception eee)
		{
			
				area.append("Bot: Sorry, I don't understand\n");
		}
	}
	public void bot(String message)
	{
		if(message.equals("5")||message.contains("search web")) {
			searchweb(null, field.getText());
		}
		else
		area.append("Bot : "+chatGPT(message)+"\n");
	}
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		automato_chatbot cb=new automato_chatbot("Chat Bot");
		cb.setSize(800,605);
		cb.setLocation(50,50);
		area.append("Bot: Greetings!\nBot: This is automato, your personal chatbot :)\nBot: type anything or press 1 to get help.\n");
		// Prints out a response to the question.
    }
	
    public static String chatGPT(String message) {
        String url = "https://api.openai.com/v1/chat/completions"
;
        String apiKey = "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"; // API key goes here
        String model = "gpt-3.5-turbo"; // current model of chat GPT API

        try {
            // Create the HTTP POST request
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Authorization", "Bearer " + apiKey);
            con.setRequestProperty("Content-Type", "application/json");

            // Build the request body
            String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + message + "\"}]}";
            con.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
            writer.write(body);
            writer.flush();
            writer.close();

            // Get the response
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // returns the extracted contents of the response.
            return extractContentFromResponse(response.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // This method extracts the response expected from chat GPT. and returns it.
    public static String extractContentFromResponse(String response) {
        int startMarker = response.indexOf("content")+11; // Marker for where the content starts.
        int endMarker = response.indexOf("\"", startMarker); // Marker for where the content ends.
        return response.substring(startMarker, endMarker); // Returns the substring containing only the response.
    }	
    
    
}
