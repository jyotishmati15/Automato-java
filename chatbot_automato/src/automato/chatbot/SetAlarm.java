package automato.chatbot;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class SetAlarm extends JFrame implements ActionListener
{

	private JPanel contentPane;
	private String  hourarr[]= {"01","02","03","04","05","06","07","08","09","10","11","12"};
	private String  minutearr[]= {"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30",
			"31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59"};
	private String  secondarr[]= {"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30",
			"31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59"};
	private String ampmarr[]= {"AM","PM"};
	private JButton btnset,playpause,back;
	private String pathname[];
	private static String alarm;
	private JLabel message;
	private int alarmnumber=0;
    private String stralarmtime;
	private static JComboBox hourcombo,minutecombo,secondcombo,ampmcombo,musiclist;
	private boolean editalarm=false;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					SimpleDateFormat sw=new SimpleDateFormat("hh:mm:ss a");
					Date date=new Date();
					String str=sw.format(date)+" 0";
					SetAlarm frame = new SetAlarm(str,false);
					frame.setLocation(100,100);

					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SetAlarm(String alarmtime,boolean streditalarm)
	{
		
		
		super("Alarm Clock");
		
		stralarmtime=alarmtime;
		editalarm=streditalarm;
		Timer timer=new Timer(1000,this);
		timer.start();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
	
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		File f=new File("F:\\Home java\\alarm folder");
		pathname=f.list();
		
		 hourcombo = new JComboBox(hourarr);
		 hourcombo.setMaximumRowCount(12);
		hourcombo.setBackground(new Color(255, 255, 255));
		hourcombo.setFont(new Font("Square721 BT", Font.PLAIN, 15));
		hourcombo.setBounds(43, 97, 78, 50);
		contentPane.add(hourcombo);
		
		minutecombo = new JComboBox(minutearr);
		minutecombo.setFont(new Font("Square721 BT", Font.PLAIN, 15));
		minutecombo.setBackground(new Color(255, 255, 255));
		minutecombo.setBounds(152, 97, 78, 50);
		contentPane.add(minutecombo);
		
		 secondcombo = new JComboBox(secondarr);
		 secondcombo.setFont(new Font("Square721 BT", Font.PLAIN, 15));
		secondcombo.setBackground(new Color(255, 255, 255));
		secondcombo.setBounds(254, 97, 78, 50);
		contentPane.add(secondcombo);
		
		 ampmcombo = new JComboBox(ampmarr);
		 ampmcombo.setFont(new Font("Square721 BT", Font.PLAIN, 15));
		ampmcombo.setBackground(new Color(255, 255, 255));
		ampmcombo.setBounds(364, 97, 72, 50);
		contentPane.add(ampmcombo);
		
		musiclist = new JComboBox(pathname);
		musiclist.setFont(new Font("Square721 BT", Font.PLAIN, 15));
		musiclist.setForeground(new Color(0, 0, 0));
		musiclist.setBackground(new Color(255, 255, 255));
		musiclist.setBounds(43, 201, 317, 57);
		contentPane.add(musiclist);
		
		 playpause = new JButton("Play");
		playpause.setForeground(new Color(255, 255, 255));
		playpause.setBackground(new Color(0, 128, 128));
		playpause.setFont(new Font("Square721 BT", Font.PLAIN, 20));
		playpause.setBounds(370, 201, 89, 57);
		contentPane.add(playpause);
		playpause.setFocusPainted(false);
		playpause.addActionListener(this);
		
		 btnset = new JButton("Set");
		btnset.setForeground(new Color(255, 255, 255));
		btnset.setBackground(new Color(0, 128, 128));
		btnset.setFont(new Font("Candara", Font.PLAIN, 17));
		btnset.setBounds(254, 331, 100, 41);
		contentPane.add(btnset);
		btnset.addActionListener(this);
		btnset.setFocusPainted(false);
		
		 back = new JButton("Back");
		back.setForeground(new Color(255, 255, 255));
		back.setBackground(new Color(0, 128, 128));
		back.setFont(new Font("Candara", Font.PLAIN, 17));
		back.setBounds(130, 331, 100, 41);
		contentPane.add(back);
		back.setFocusPainted(false);
		back.addActionListener(this);
		message = new JLabel("add new alarm with clicking this button");
		message.setFont(new Font("Serif", Font.BOLD, 17));
		message.setForeground(Color.gray);
//		message.setBackground(Color.green);
		message.setHorizontalAlignment(SwingConstants.CENTER);
		message.setBounds(0, 435, 500, 14);
		contentPane.add(message);
		
		JLabel lblAlarmClock = new JLabel("Alarm Clock");
		lblAlarmClock.setForeground(new Color(0, 0, 128));
		lblAlarmClock.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlarmClock.setFont(new Font("Square721 BT", Font.BOLD, 25));
		lblAlarmClock.setBounds(0, 31, 484, 27);
		contentPane.add(lblAlarmClock);
		hourcombo.setSelectedItem(alarmtime.substring(0,2));
		minutecombo.setSelectedItem(alarmtime.substring(3, 5));
		secondcombo.setSelectedItem(alarmtime.substring(6, 8));
		ampmcombo.setSelectedItem(alarmtime.substring(9, 11));
		musiclist.setSelectedIndex(Integer.parseInt(alarmtime.substring(12,alarmtime.length())));
		try
		{
		FileReader fr=new FileReader("C:\\Users\\Sohansinh Rathod\\eclipse-workspace\\Clockalarmfile.txt");
		BufferedReader br=new BufferedReader(fr);
	
		while(br.readLine()!=null)
		{
			alarmnumber++;
		}
		br.close();
		}
		catch(Exception ee)
		{
			
		}
		
		
	}
	public static String getAlarm()
	{
		 alarm=hourcombo.getSelectedItem()+":"+minutecombo.getSelectedItem()+":"+secondcombo.getSelectedItem()+" "+ampmcombo.getSelectedItem()+" "+musiclist.getSelectedIndex();
		return alarm;
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		LocalTime now=LocalTime.now();
		int hour=now.getHour();
		int minute=now.getMinute();
		int second=now.getSecond();
		if(now.getHour()>=12)
		{
			if(now.getHour()==12)
			{
			message.setText("Current Time = 12:"+now.getMinute()+":"+now.getSecond()+" PM");
			}
			else
			{
				message.setText("Current Time = "+(now.getHour()-12)+":"+now.getMinute()+":"+now.getSecond()+" PM");
			}	
		}
		else
		{
			message.setText("Current Time ="+now.getHour()+":"+now.getMinute()+":"+now.getSecond()+" AM");
		}
		if(e.getSource()==playpause)
		{
			
//			r.setVisible(false);
			if(playpause.getText()=="Play")
			{
				AlarmRing r=new AlarmRing("Playing Music...",pathname[musiclist.getSelectedIndex()],musiclist.getSelectedIndex());
				r.setLocation(this.getX()+80,this.getY()+100);
				r.setVisible(true);
				
			}
			
		}
		if(e.getSource()==btnset && (editalarm || alarmnumber<6) )
		{
			alarm=hourcombo.getSelectedItem()+":"+minutecombo.getSelectedItem()+":"+secondcombo.getSelectedItem()+" "+ampmcombo.getSelectedItem()+" "+musiclist.getSelectedIndex();
			editalarm=false;
			try
			{
			FileWriter fr=new FileWriter("C:\\Users\\Sohansinh Rathod\\eclipse-workspace\\Clock\\alarmfile.txt",true);
			BufferedWriter bw=new BufferedWriter(fr);
			fr.write(alarm+"\n");
			fr.close();
			}
			catch(Exception eee)
			{
				
			}
			
			
			AlarmClock ac=new AlarmClock();
			ac.setVisible(true);
			ac.setLocation(this.getX(),this.getY());
			this.dispose();
			
			
		}
		else if(alarmnumber>=6)
		{
			message.setText("Reached at maximum Alarm limit ");
			message.setForeground(Color.red);
		}
		if(e.getSource()==back)
		{
			if(editalarm==true)
			{
				try
				{
						FileWriter fw=new FileWriter("C:\\Users\\Sohansinh Rathod\\eclipse-workspace\\Clock\\alarmfile.txt",true);
				
							
						BufferedWriter bf=new BufferedWriter(fw);
						fw.write(stralarmtime+"\n");
					
						fw.close();
				
		
				}
				catch(Exception dfe)
				{
					
				}
			}
			
			AlarmClock ac=new AlarmClock();
			ac.setVisible(true);
			ac.setLocation(this.getX(),this.getY());
			this.dispose();
		}
		
	}
}