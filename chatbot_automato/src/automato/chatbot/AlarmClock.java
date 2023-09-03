package automato.chatbot;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class AlarmClock extends JFrame implements ActionListener,KeyListener 
{

	private JPanel contentPane;
	private JButton addalarm;
	private JTextField textfield[]=new JTextField[7];
	private JButton set[]=new JButton[7];
	private int messagenumber=0;
	private JButton delete[]=new JButton[7];
	private int alarmnumber=0;
	private static AlarmClock frame;
	private LocalTime now=LocalTime.now();
	private JLabel message;
	private static String alarmfromuser;
	private JButton back;
	private String stram[]=new String[7];
	private  String strpm[]=new String[7];
	private  int am=0,pm=0;
	private  int alarmnum=0;
	private String alarmtimesort[]=new String[7];
	
	private String alarmfromfile[]=new String[7];
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new AlarmClock();
					frame.setVisible(true);
					frame.setLocation(100,100);
				    }
				catch (Exception e) 
				{
					e.printStackTrace();
				}
				
			}
		});
		
		
	}

	/**
	 * Create the frame.
	 */
	
	public AlarmClock() {
		
		setResizable(false);
		addKeyListener(this);
		setFocusable(true);
		
		Timer timer=new Timer(1000,this);
		timer.start();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
		setTitle("Alarm Clock");
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 220));
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		addalarm = new JButton("+ Add Alarm");
		addalarm.addActionListener(this);
		addalarm.setForeground(new Color(255, 255, 255));
		addalarm.setBackground(new Color(0, 128, 128));
		addalarm.setFont(new Font("Calibri", Font.PLAIN, 15));
		addalarm.setBounds(235, 383, 120, 41);
		
		addalarm.setFocusPainted(false);
		
		back = new JButton("Back");
		back.addActionListener(this);
		back.setForeground(new Color(255, 255, 255));
		back.setBackground(new Color(0, 128, 128));
		back.setFont(new Font("Calibri", Font.PLAIN, 15));
		back.setBounds(130, 383, 100, 41);
		
		back.setFocusPainted(false);
		add(back);
		
		contentPane.add(addalarm);
		
		message = new JLabel("add new alarm with clicking this button");
		message.setFont(new Font("Serif", Font.BOLD, 17));
		message.setForeground(Color.gray);
//		message.setBackground(Color.green);
		message.setHorizontalAlignment(SwingConstants.CENTER);
		message.setBounds(0, 435, 500, 14);
		contentPane.add(message);
		
		int hour=now.getHour();
		int minute=now.getMinute();
		int second=now.getSecond();
		String time=hour+":"+minute+":"+second;
		
		
	
	
	int column=0;
	for(int i=0; i<7; i++)
	{

		textfield[i]=new JTextField();
		textfield[i].setFont(new Font("Serif",Font.BOLD,17));
		textfield[i].setBounds(20,20+column,240,40);	
		textfield[i].setVisible(false);
		textfield[i].setHorizontalAlignment(JTextField.CENTER);
		contentPane.add(textfield[i]);
		set[i]=new JButton("Set");
		set[i].setFont(new Font("Serif",Font.BOLD,20));
		set[i].setBounds(275,20+column,100,40);
		set[i].setBackground(Color.white);
		set[i].setForeground(Color.black);
		set[i].setVisible(false);
		set[i].setFocusPainted(false);
		set[i].addActionListener(this);
		delete[i]=new JButton("Delete");
		delete[i].setFont(new Font("Serif",Font.BOLD,18));

		delete[i].setBounds(380,20+column,100,40);
		delete[i].setBackground(Color.DARK_GRAY);
		delete[i].setForeground(Color.white);
		delete[i].setVisible(false);
		delete[i].addActionListener(this);
		delete[i].setFocusPainted(false);
		
		getContentPane().add(delete[i]);
		
		getContentPane().add(set[i]);

		column+=60;
		
	}
	 
	try
	{
	FileReader fr=new FileReader("C:/Users/Sohansinh Rathod/eclipse-workspace/AlarmClock/alarmfile.txt");
	BufferedReader bf=new BufferedReader(fr);
	 
	
	while((alarmtimesort[alarmnum]=bf.readLine())!=null)
	{
		alarmnum++;
	}
	


	
	}
	catch(Exception e)
	{

	}

	am=0;
	pm=0;
    for(int i=0; i<alarmnum; i++)
    {
    	if(alarmtimesort[i].substring(9,11).equals("AM"))
    	{
    	  stram[am]=alarmtimesort[i];
    		am++;

    	}
    	else  
    	{
    		strpm[pm]=alarmtimesort[i];
    		
    			pm++;
    	}

    }
    for(int i=0; i<am; i++)
    {
    	if(stram[i].substring(0,2).equals("12"))
    	{
    		stram[i]="00"+stram[i].substring(2,stram[i].length());
    	}
    }
    for(int i=0; i<am-1; i++)
    {
    	for(int j=i+1; j<am; j++)
    	{
    	
    		if((Integer.parseInt(stram[i].substring(0,2)))>(Integer.parseInt(stram[j].substring(0,2))))
    		{
    				
    				String str;
    				str=stram[i];
    				stram[i]=stram[j];
    				stram[j]=str;


    		}
    		else if((Integer.parseInt(stram[i].substring(0,2)))==(Integer.parseInt(stram[j].substring(0,2))))
    		{
    			if((Integer.parseInt(stram[i].substring(3,5)))>(Integer.parseInt(stram[j].substring(3,5))))
    			{
    				String str;
    				str=stram[i];
    				stram[i]=stram[j];
    				stram[j]=str;
    			}
    			else if((Integer.parseInt(stram[i].substring(3,5)))==(Integer.parseInt(stram[j].substring(3,5))))
    			{
    			if((Integer.parseInt(stram[i].substring(6,8)))>(Integer.parseInt(stram[j].substring(6,8))))
    			{
    				String str;
    				str=stram[i];
    				stram[i]=stram[j];
    				stram[j]=str;
    			}
    			}
    		}

    	}
    }
     for(int i=0; i<am; i++)
    {
    	if(stram[i].substring(0,2).equals("00"))
    	{
    		stram[i]="12"+stram[i].substring(2,stram[i].length());
    	}
    }

    for(int i=0; i<pm; i++)
    {
    	if(strpm[i].substring(0,2).equals("12"))
    	{
    		strpm[i]="00"+strpm[i].substring(2,strpm[i].length());
    	}
    }
    for(int i=0; i<pm-1; i++)
    {
    	for(int j=i+1; j<pm; j++)
    	{
    	
    		if((Integer.parseInt(strpm[i].substring(0,2)))>(Integer.parseInt(strpm[j].substring(0,2))))
    		{
    				
    				String str;
    				str=strpm[i];
    				strpm[i]=strpm[j];
    				strpm[j]=str;


    		}
    		else if((Integer.parseInt(strpm[i].substring(0,2)))==(Integer.parseInt(strpm[j].substring(0,2))))
    		{
    			if((Integer.parseInt(strpm[i].substring(3,5)))>(Integer.parseInt(strpm[j].substring(3,5))))
    			{
    				String str;
    				str=strpm[i];
    				strpm[i]=strpm[j];
    				strpm[j]=str;
    			}
    			else if((Integer.parseInt(strpm[i].substring(3,5)))==(Integer.parseInt(strpm[j].substring(3,5))))
    			{
    			if((Integer.parseInt(strpm[i].substring(6,8)))>(Integer.parseInt(strpm[j].substring(6,8))))
    			{
    				String str;
    				str=strpm[i];
    				strpm[i]=strpm[j];
    				strpm[j]=str;
    			}
    			}
    		}

    	}
    }
     for(int i=0; i<pm; i++)
    {
    	if(strpm[i].substring(0,2).equals("00"))
    	{
    		strpm[i]="12"+strpm[i].substring(2,strpm[i].length());
    	}
    }
    
	alarmnum=0;
	for(int i=0; i<am; i++)
    {
    	alarmtimesort[alarmnum]=stram[i];
    	alarmnum++;
    }
	for(int i=0; i<pm; i++)
    {
    	alarmtimesort[alarmnum]=strpm[i];
    	alarmnum++;
    }
	
	
    try
	{
	 FileWriter fw;
	for( int j=0; j<alarmnum; j++)	
	{
	
		
		
			if(j==0)
			{
					fw=new FileWriter("C:\\Users\\Sohansinh Rathod\\eclipse-workspace\\Clock\\alarmfile.txt",false);
			}
			else
			{
				fw=new FileWriter("C:\\Users\\Sohansinh Rathod\\eclipse-workspace\\Clock\\alarmfile.txt",true);
			}
			BufferedWriter bf=new BufferedWriter(fw);
			fw.write(alarmtimesort[j]+"\n");
		
			fw.close();
	
		
	
		
	}
	}
	catch(Exception dfe)
	{
		
	}
	alarmnumber=0;
	try
	{
	FileReader fread=new FileReader("C:\\Users\\Sohansinh Rathod\\eclipse-workspace\\Clock\\alarmfile.txt");
	BufferedReader bfr=new BufferedReader(fread);
	
	while((alarmfromfile[alarmnumber]=bfr.readLine())!=null&&alarmnumber<6)
	{
		textfield[alarmnumber].setVisible(true);
		textfield[alarmnumber].setText(alarmfromfile[alarmnumber].substring(0,11));
		set[alarmnumber].setVisible(true);
		delete[alarmnumber].setVisible(true);
		textfield[alarmnumber].setEditable(false);
		set[alarmnumber].setText("Edit");
		int alarmhour=Integer.parseInt(alarmfromfile[alarmnumber].substring(0,2));
		int alarmminute=Integer.parseInt(alarmfromfile[alarmnumber].substring(3,5));
		int alarmsecond=Integer.parseInt(alarmfromfile[alarmnumber].substring(6,8));
		String alarmampm=alarmfromfile[alarmnumber].substring(9,11);
		
	
		
		
		
		alarmnumber++;
		
	
		
	}
	}
	catch(IOException ie)
	{
		
	}
	
	
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		LocalTime now=LocalTime.now();

		String alarmtimeforring;
		if(e.getSource()==back)
		{
				
			ClockTimerAlarm m=new ClockTimerAlarm();
			m.setVisible(true);
			m.setLocation(this.getX(),this.getY());
			this.dispose();
			}
		if(now.getHour()>=12)
		{
			message.setForeground(Color.gray);
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
		for(int i=0; i<alarmnumber; i++)
		{
			
			alarmtimeforring=alarmfromfile[i];
			int alarmhour=Integer.parseInt(alarmtimeforring.substring(0,2));
			int alarmminute=Integer.parseInt(alarmtimeforring.substring(3,5));
			int alarmsecond=Integer.parseInt(alarmtimeforring.substring(6,8));
			String ampm=alarmtimeforring.substring(9,11);
			int musicnumber=Integer.parseInt(alarmtimeforring.substring(12,alarmtimeforring.length()));

			
			int nowhour=now.getHour();
			if(nowhour>12)
			{
				nowhour-=12;
			}
			
			
			if(set[i].getText()=="Edit"&&nowhour==alarmhour&&now.getMinute()==alarmminute&&now.getSecond()==alarmsecond)
			
			{
			
			message.setText(alarmtimeforring+" Alarm ringing");
	
			
			AlarmRing ar=new AlarmRing(alarmtimeforring.substring(0, 11),"Alarm is Ringing...",musicnumber);
			ar.setLocation(this.getX()+80,this.getY()+100);
			ar.setVisible(true);
			
			
			
		
		}
			
		}
		if(e.getSource()==addalarm )
		{
//			String str;
			
			if(alarmnumber<6)
			{
		
				
				SimpleDateFormat sw=new SimpleDateFormat("hh:mm:ss a");
				Date date=new Date();
				String str=sw.format(date)+" 0";
				SetAlarm sframe = new SetAlarm(str,false);
				sframe.setLocation(this.getX(),this.getY());
				sframe.setVisible(true);
			
			
				this.dispose();
				
				
			}
			else if(alarmnumber>=6)
			{
				
				message.setText("Reached at maximum alarm limit");
				message.setForeground(Color.red);
				
			}
			
		}
	
	for(int i=0; i<6; i++)
	{
	
		 if(e.getSource()==set[i]&&set[i].getText()=="Edit")
		{
			 
			try
			{
			 FileWriter fw;
			for( int j=0; j<alarmnumber; j++)	
			{
			
				
				if(i!=j)
				{
					if(j==0)
					{
							fw=new FileWriter("C:\\Users\\Sohansinh Rathod\\eclipse-workspace\\Clock\\alarmfile.txt",false);
					}
					else
					{
						fw=new FileWriter("C:\\Users\\Sohansinh Rathod\\eclipse-workspace\\Clock\\alarmfile.txt",true);
					}
					BufferedWriter bf=new BufferedWriter(fw);
					fw.write(alarmfromfile[j]+"\n");
				
					fw.close();
				}
				else
				{
					
				}
			
				
			}
			}
			catch(Exception dfe)
			{
				
			}
			
			SetAlarm s=new SetAlarm(alarmfromfile[i],true);
			s.setLocation(this.getX(),this.getY());
			s.setVisible(true);
			this.dispose();
			
			

	
				
		}
	
		if(e.getSource()==delete[i])
		{
			for(int j=0; j<alarmnumber; j++)
			{
				
			
				FileWriter fw;
				try {
				if(j==0)
				{
					fw = new FileWriter("C:\\Users\\Sohansinh Rathod\\eclipse-workspace\\Clock\\alarmfile.txt",false);
				}
				else
				{
					fw = new FileWriter("C:\\Users\\Sohansinh Rathod\\eclipse-workspace\\Clock\\alarmfile.txt",true);
				}
				BufferedWriter bf=new BufferedWriter(fw);
				if(i!=j&&set[j].getText()=="Edit")
				{
				bf.write(alarmfromfile[j]+"\n");
				
				}
				bf.close();
				}
				catch(IOException ie)
				{
					
				}
				
				
				
			
			}
			AlarmClock main=new AlarmClock();
			main.setVisible(true);
			main.setLocation(this.getX(),this.getY());
			this.dispose();
		
		
			
			
		}
	
	}
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
