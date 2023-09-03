package automato.chatbot;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class AlarmRing extends JDialog implements ActionListener
{

	private JPanel contentPane;
	private JButton ok;
	private int number=1;
	private static String alarmtime;
	private String pathname[];
	private JLabel lblTime,alarmringlabel;
	String path="F:\\Home java\\alarm folder\\";
	AudioInputStream audio;;
	Clip player;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlarmRing frame = new AlarmRing("No Alarm","Alarm type",3);
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

	public AlarmRing(String stralarmtime,String clocktype,int songnumber) 
	{
		alarmtime=stralarmtime;
		
		
		this.setResizable(false);
		Timer timer=new Timer(400,this);
		timer.start();
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Music Player");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(350, 250);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		File f=new File("F:\\Home java\\alarm folder");
		pathname=f.list();
		
		path=path+pathname[songnumber];
		alarmringlabel = new JLabel(clocktype);
		alarmringlabel.setForeground(Color.BLACK);
		alarmringlabel.setFont(new Font("Square721 BT", Font.PLAIN, 20));
		alarmringlabel.setHorizontalAlignment(SwingConstants.CENTER);
		alarmringlabel.setBounds(0, 67, 339, 59);
		contentPane.add(alarmringlabel);
		
		ok = new JButton("Stop");
		ok.addActionListener(this);
		ok.setFont(new Font("Square721 BT", Font.BOLD, 20));
		ok.setForeground(new Color(255, 255, 255));
		ok.setBackground(new Color(0, 128, 128));
		ok.setBounds(120, 140, 100, 41);
		contentPane.add(ok);
		ok.setFocusPainted(false);
		
		lblTime = new JLabel(alarmtime);
		lblTime.setForeground(new Color(0, 0, 0));
		lblTime.setFont(new Font("Square721 BT", Font.BOLD, 25));
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setBounds(0, 24, 339, 32);
		contentPane.add(lblTime);
		try
		{
			
			
		audio=AudioSystem.getAudioInputStream(new File(path));
		player=AudioSystem.getClip();		
		player.open(audio);
		player.loop(Clip.LOOP_CONTINUOUSLY);
		player.start();
		
		}

		catch(Exception ee)
		
		{
			
			System.out.println("Exception");
		}

		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		if(e.getSource()==ok)
		{
			player.stop();
			this.dispose();
		}

		if(number==1)
		{
		lblTime.setForeground(Color.red);
		alarmringlabel.setFont(new Font("Square721 BT", Font.PLAIN, 16));
		number++;
		}
		else if(number==2)
		{
		lblTime.setForeground(Color.orange);
		alarmringlabel.setFont(new Font("Square721 BT", Font.PLAIN, 20));
		number++;
		}
		else if(number==3)
		{
		lblTime.setForeground(Color.blue);
		alarmringlabel.setFont(new Font("Square721 BT", Font.PLAIN, 24));
		number++;
		}
		else if(number==4)
		{
		lblTime.setForeground(Color.darkGray);
		alarmringlabel.setFont(new Font("Square721 BT", Font.PLAIN, 20));
		number=1;
		}
		
	
			
	}
	public void stopmusic()
	{
		player.stop();
	}
	public void setAlarm(String stralarmtime)
	{
		alarmtime=stralarmtime;
	}


}