package automato.chatbot;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class StopWatch extends JFrame implements ActionListener,KeyListener
{

	private JPanel contentPane;
	private JTextField minutetime;
	private JScrollPane sp;
	private JButton button,activate;
	private int microseconds=00,seconds=00,minutes=00,hour=00;
	String strhour,strminute,strseconds,strmicseconds,strlap;
	private boolean activatetime=false;
	private JTextField secondtime;
	private JTextField microsecondtime;
	private JTextField hourtime;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JTextArea textarea;
	private int lapnumber=1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StopWatch frame = new StopWatch();
					frame.setVisible(true);
					frame.setLocation(100,100);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StopWatch() {
		
		addKeyListener(this);
		setFocusable(true);
		Timer timer=new Timer(10,this);
		timer.start();
		setTitle("Stopwatch");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel stopwatchnamelabel = new JLabel("Stopwatch");
		stopwatchnamelabel.setFont(new Font("Square721 BT", Font.BOLD, 25));
		stopwatchnamelabel.setForeground(new Color(0, 0, 128));
		stopwatchnamelabel.setBackground(new Color(255, 255, 255));
		stopwatchnamelabel.setHorizontalAlignment(SwingConstants.CENTER);
		stopwatchnamelabel.setBounds(0, 29, 484, 45);
		contentPane.add(stopwatchnamelabel);
		
		minutetime = new JTextField();
		minutetime.setForeground(new Color(255, 255, 255));
		minutetime.setBackground(Color.GRAY);
		minutetime.setFont(new Font("Square721 BT", Font.BOLD, 25));
		minutetime.setHorizontalAlignment(SwingConstants.CENTER);
		minutetime.setEditable(false);
		minutetime.setText("00");
		minutetime.setBounds(146, 100, 74, 61);
		contentPane.add(minutetime);
		minutetime.setColumns(10);
//		
		
		activate = new JButton("Activate");
		activate.setForeground(new Color(255, 255, 255));
		activate.setFont(new Font("Calibri", Font.PLAIN, 17));
		activate.setBackground(new Color(0, 128, 128));
		activate.setBounds(115, 203, 121, 45);
		activate.addActionListener(this);
		activate.setFocusPainted(false);
		contentPane.add(activate);
		
		button = new JButton("Back");
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Calibri", Font.PLAIN, 17));
		button.setBackground(new Color(0, 128, 128));
		button.setBounds(253, 203, 121, 45);
	    button.addActionListener(this);
	    button.setFocusPainted(false);
	    
		contentPane.add(button);
		textarea=new JTextArea();
		textarea.setFont(new Font("Square721 BT", Font.PLAIN, 20));
		textarea.setEditable(false);
		textarea.setBounds(10,286,464,164);
		getContentPane().add(textarea);
		
		sp=new JScrollPane(textarea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		sp.setBounds(10,286,464,164);
		getContentPane().add(sp);
		
		secondtime = new JTextField();
		secondtime.setText("00");
		secondtime.setHorizontalAlignment(SwingConstants.CENTER);
		secondtime.setForeground(Color.WHITE);
		secondtime.setFont(new Font("Square721 BT", Font.BOLD, 25));
		secondtime.setEditable(false);
		secondtime.setColumns(10);
		secondtime.setBackground(Color.GRAY);
		secondtime.setBounds(260, 100, 74, 61);
		contentPane.add(secondtime);
		
		microsecondtime = new JTextField();
		microsecondtime.setText("00");
		microsecondtime.setHorizontalAlignment(SwingConstants.CENTER);
		microsecondtime.setForeground(Color.WHITE);
		microsecondtime.setFont(new Font("Square721 BT", Font.BOLD, 25));
		microsecondtime.setEditable(false);
		microsecondtime.setColumns(10);
		microsecondtime.setBackground(Color.GRAY);
		microsecondtime.setBounds(373, 100, 74, 61);
		contentPane.add(microsecondtime);
		
		hourtime = new JTextField();
		hourtime.setText("00");
		hourtime.setHorizontalAlignment(SwingConstants.CENTER);
		hourtime.setForeground(Color.WHITE);
		hourtime.setFont(new Font("Square721 BT", Font.BOLD, 25));
		hourtime.setEditable(false);
		hourtime.setColumns(10);
		hourtime.setBackground(Color.GRAY);
		hourtime.setBounds(35, 100, 74, 61);
		contentPane.add(hourtime);
		
		label = new JLabel(":");
		label.setFont(new Font("Tahoma", Font.PLAIN, 30));
		label.setBounds(123, 114, 28, 32);
		contentPane.add(label);
		
		label_1 = new JLabel(":");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		label_1.setBounds(235, 112, 28, 32);
		contentPane.add(label_1);
		
		label_2 = new JLabel(".");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 30));
		label_2.setBounds(346, 114, 28, 32);
		contentPane.add(label_2);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		if(e.getSource()==activate)
		{
			if(activate.getText()=="Activate")
			{
			activatetime=true;
			button.setText("Lap");
			activate.setText("Stop");
			activate.setBackground(Color.red);
			button.setBackground(Color.white);
			activate.setForeground(Color.white);
			button.setForeground(Color.black);
			
			}
			else if(activate.getText()=="Stop")
			{
			activate.setText("Activate");
			activatetime=false;
			activate.setBackground(new Color(0, 128, 128));
			button.setBackground(Color.white);
			button.setForeground(Color.black);
			button.setText("Reset");
			}
			
			
		}
		if(activatetime)
		{
			microseconds++;
			if(microseconds==100)
			{
				seconds++;
				microseconds=0;
			}
			if(seconds==60)
			{
				minutes++;
				seconds=0;
			}
			if(minutes==60)
			{
				hour++;
				minutes=0;
			}
			
			if(hour>=0 && hour<=9)
			{
				strhour="0"+hour;
			}
			else
			{
				strhour=hour+"";
			}
			if(minutes>=0 && minutes<=9)
			{
				strminute="0"+minutes;
			}
			else
			{
				strminute=minutes+"";
			}
			if(seconds>=0 && seconds<=9)
			{
				strseconds="0"+seconds;
			}
			else
			{
				strseconds=seconds+"";
			}
			if(microseconds>=0 && microseconds<=9)
			{
				strmicseconds="0"+microseconds;
			}
			else
			{
				strmicseconds=microseconds+"";
			}
			hourtime.setText(strhour+"");
			minutetime.setText(strminute+"");
			secondtime.setText(strseconds+"");
			microsecondtime.setText(strmicseconds+"");
		}
		if(e.getSource()==button)
		{
			if(button.getText()=="Lap")
			{
				
				
				if(hour>=0 && hour<=9)
				{
					strhour="0"+hour;
				}
				else
				{
					strhour=hour+"";
				}
				if(minutes>=0 && minutes<=9)
				{
					strminute="0"+minutes;
				}
				else
				{
					strminute=minutes+"";
				}
				if(seconds>=0 && seconds<=9)
				{
					strseconds="0"+seconds;
				}
				else
				{
					strseconds=seconds+"";
				}
				if(microseconds>=0 && microseconds<=9)
				{
					strmicseconds="0"+microseconds;
				}
				else
				{
					strmicseconds=microseconds+"";
				}
				if(lapnumber>=0 && lapnumber<=9)
				{
					strlap="0"+lapnumber;
				}
				else
				{
					strlap=lapnumber+"";
				}
				String currenttime=strlap+"                       "+strhour+" : "+strminute+" : "+strseconds+" . "+strmicseconds;
				textarea.setText(textarea.getText()+currenttime+"\n");
				lapnumber++;
				
			}
			else if(button.getText()=="Reset")
			{
				hour=0;
				minutes=0;
				seconds=0;
				microseconds=0;
				hourtime.setText(hour+"");
				minutetime.setText(minutes+"");
				secondtime.setText(seconds+"");
				microsecondtime.setText(microseconds+"");
				textarea.setText("");
				lapnumber=1;
				button.setBackground(new Color(0, 128, 128));
				button.setForeground(new Color(255,255, 255));
				button.setText("Back");
				
			}
			else if(button.getText()=="Back")
			{
				ClockTimerAlarm m=new ClockTimerAlarm();
				m.setVisible(true);
				m.setLocation(this.getX(),this.getY());
				this.dispose();
			}
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE)
		{
			
			ClockTimerAlarm m=new ClockTimerAlarm();
			m.setVisible(true);	
			m.setLocation(this.getX(),this.getY());
			this.dispose();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}