package ticTactoe_GAME;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.*;

public class myGame extends JFrame implements ActionListener
{
	JLabel heading,clockLabel;
	Font font = new Font("",Font.BOLD,40);
	JPanel mainPanel;
	
	JButton[] btns = new JButton[9];
	
//	Game instance variable
	
	int gameChances[] = {2,2,2,2,2,2,2,2,2};				// by default sbme 2 hai jbtk kisi ne uspar kuch chala na ho
	int activePlayer = 0;
	
	int wps[][] = {
			{0,1,2},
			{3,4,5},
			{7,7,8},
			{0,3,6},
			{1,4,7},
			{2,5,8},
			{0,4,8},
			{2,4,6}};
	int winner=2;
	
	boolean gameOver = false;
	
	myGame()
	{
		System.out.println("Creating Instance of Game");
		setTitle("My TIC TAC TOE Game");
		setSize(850,850);
		ImageIcon icon = new ImageIcon("src/img/icon.png");
		setIconImage(icon.getImage());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		createGUI();
		setVisible(true);
		
//		Represemting Frame with this class
//		setting Layout to Border Layout toh frame ke 5 hisse ho jayenge
//		J-panel me hm Grid Layout use krenge containing 9 buttons
//		Clock ko run krwayenge Thread se.
		
		
		
	}
	
	private void createGUI()
	{
		this.setLayout(new BorderLayout());
		this.getContentPane().setBackground(Color.decode("#2196f3"));
//		North Heading
		heading = new JLabel("TIC TAC TOE ");
		heading.setFont(font);
		heading.setHorizontalAlignment(SwingConstants.CENTER);
		heading.setForeground(Color.white);
//		heading.setIcon(new ImageIcon("src/img/icon.png"));
		this.add(heading,BorderLayout.NORTH);
		heading.setHorizontalTextPosition(SwingConstants.CENTER);
		heading.setVerticalTextPosition(SwingConstants.BOTTOM);
		
		//creating object of JLabel for clock
		clockLabel = new JLabel("Clock");
		clockLabel.setFont(font);
		clockLabel.setHorizontalAlignment(SwingConstants.CENTER);
		clockLabel.setForeground(Color.white);
		this.add(clockLabel,BorderLayout.SOUTH);
		
//		Creating thread for Clock
		Thread obj = new Thread() {
		public void run()
		{
				try
				{
					while(true)
					{
						String dateTime = new Date().toLocaleString();
						clockLabel.setText(dateTime);
						Thread.sleep(1000);					// 100ms = 1s
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}	
		};
		obj.start();				// thread ko start krne ke liye.
		
		
		// panel section
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(3,3));
		for(int i=1 ; i<=9 ; i++)
		{
			JButton btn = new JButton();
//			btn.setIcon(new ImageIcon("src/img/Zero.png"));
			btn.setBackground(Color.decode("#90cF9"));
			btn.setFont(font);
			mainPanel.add(btn);
			btns[i-1] = btn;
			btn.addActionListener(this);
			btn.setName(String.valueOf(i-1));
		}
		this.add(mainPanel,BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
//		this mtlb framr ar this mtlb listener
		JButton currentButton = (JButton) e.getSource();
		String nameStr = currentButton.getName();
//		System.out.println(nameStr);
		
		int name = Integer.parseInt(nameStr.trim());
		
		if(gameOver)
		{
			JOptionPane.showMessageDialog(this, "Game Already Over...");
			return;
		}
		
		if(gameChances[name]==2)
		{
			
			if(activePlayer==1)
			{
				currentButton.setIcon(new ImageIcon("TicTacToeGame/img/Cross.png"));
				
				gameChances[name] = activePlayer;
				activePlayer = 0;
			}
			else
			{
				currentButton.setIcon(new ImageIcon("TicTacToeGame/img/Zero.png"));
				gameChances[name] = activePlayer;
				activePlayer = 1;
			}
//		Find the Winner
			for(int []temp:wps)
			{
				if((gameChances[temp[0]]==gameChances[temp[1]])&&(gameChances[temp[1]]==gameChances[temp[2]])&&gameChances[temp[2]]!=2)
				{
					winner = gameChances[temp[0]];
					
					gameOver = true;
					
					JOptionPane.showMessageDialog(null,"Player "+winner+" has won the Game" );
					int i= JOptionPane.showConfirmDialog(this, "Do you want to play More?");
					if(i==0)
					{
						this.setVisible(false);
						new myGame();
					}
					else if(i==1)
					{
						System.exit(0);
					}
					else
					{
						
					}
					System.out.println(i);
					break;
				}
			}
//			Draw Logic
			
			
			int count = 0;
			
			for(int x:gameChances)
			{
				if(x==2)
				{
					count++;
					break;
				}
			}
			
			if(count==0 && gameOver==false)
			{
				JOptionPane.showMessageDialog(null, "Its Draw" );
				int i=JOptionPane.showConfirmDialog(this, "Play Again?");
				if(i==0)
				{
					this.setVisible(false);
					new myGame();
				}
				else if(i==1) 
				{
					System.exit(0);
				}
				else
				{
//					
				}
				gameOver=true;
			}
			
			}
		else
		{
			JOptionPane.showMessageDialog(this, "Position Already Occupied");
		}
				
			
			
		}
		
	}
