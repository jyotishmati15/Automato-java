package automato.chatbot;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class WorldClock extends JFrame implements ActionListener,KeyListener
{

	private JPanel contentPane;
	private JButton searchcity;
	private JButton back;
	String timeofcity="Asia/Kolkata";
	private JComboBox cityname;
	private JLabel citytime;
	private static String timezonecityname[];
	DateTimeFormatter dateformat=DateTimeFormatter.ofPattern("hh:mm:ss a");
	Date date=new Date();
	private JLabel lblWorldClock;
	private JLabel timemessage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WorldClock frame = new WorldClock();
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
	public WorldClock() {
		
		Timer timer=new Timer(1000,this);
		timer.start();
		addKeyListener(this);
		setFocusable(true);
		setResizable(false);
		setTitle("World Clock");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		timezonecityname=TimeZone.getAvailableIDs();
		
				
		
		cityname = new JComboBox(timezonecityname);
		cityname.setToolTipText("");
		cityname.setFont(new Font("Calibri", Font.PLAIN, 18));
		cityname.setMaximumRowCount(10);
		cityname.setSelectedIndex(278);
		cityname.setBackground(new Color(255, 255, 255));
		cityname.setBounds(20, 113, 430, 49);
	
		contentPane.add(cityname);
		
		citytime = new JLabel("");
		citytime.setHorizontalAlignment(SwingConstants.CENTER);
		citytime.setFont(new Font("Square721 BT", Font.PLAIN, 25));
		citytime.setBounds(10, 264, 484, 49);
		contentPane.add(citytime);
		
		searchcity = new JButton("Search");
		searchcity.setForeground(new Color(255, 255, 255));
		searchcity.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		searchcity.setBackground(new Color(0, 128, 128));
		searchcity.setBounds(231, 173, 99, 49);
		contentPane.add(searchcity);
		searchcity.addActionListener(this);
		searchcity.setFocusPainted(false);
		getRootPane().setDefaultButton(searchcity);
		
		lblWorldClock = new JLabel("World Clock");
		lblWorldClock.setForeground(new Color(0, 0, 128));
		lblWorldClock.setFont(new Font("Square721 BT", Font.BOLD, 29));
		lblWorldClock.setHorizontalAlignment(SwingConstants.CENTER);
		lblWorldClock.setBounds(106, 24, 286, 49);
		contentPane.add(lblWorldClock);
		
		timemessage = new JLabel("");
		timemessage.setFont(new Font("Square721 BT", Font.BOLD, 25));
		timemessage.setHorizontalAlignment(SwingConstants.CENTER);
		timemessage.setBounds(20, 307, 430, 69);
		contentPane.add(timemessage);
		
		back = new JButton("Back");
		back.addActionListener(this);
		back.setForeground(Color.WHITE);
		back.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		back.setBackground(new Color(0, 128, 128));
		back.setBounds(122, 173, 99, 49);
		back.setFocusPainted(false);
		contentPane.add(back);
		String timeofcity=(String) cityname.getSelectedItem();
		
		
		citytime.setText("Current Time of "+timeofcity+" is " );
		timemessage.setText(LocalTime.now(ZoneId.of(timeofcity)).format(dateformat));

		
	}
	public void actionPerformed(ActionEvent e)
	{
		
		timemessage.setText(LocalTime.now(ZoneId.of(timeofcity)).format(dateformat));
	
		if(e.getSource()==searchcity)
		{
		
			 timeofcity=(String) cityname.getSelectedItem();
		citytime.setText("Current Time of "+timeofcity+" is " );
		timemessage.setText(LocalTime.now(ZoneId.of(timeofcity)).format(dateformat));
		
		}
		if(e.getSource()==back)
		{
			ClockTimerAlarm m=new ClockTimerAlarm();
			m.setVisible(true);
			m.setLocation(this.getX(),this.getY());
			this.dispose();
		}
			
	repaint();
	}

	@Override
	public void keyPressed(KeyEvent ke) {
		// TODO Auto-generated method stub
		if(ke.getKeyCode()==KeyEvent.VK_BACK_SPACE)
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