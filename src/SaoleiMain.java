import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

import java.util.Random;

public class SaoleiMain extends JFrame implements MouseListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
//	public static void main(String[] args) {
//		SwingUtilities.invokeLater(new Runnable() {
//			public void run() {
//				SaoleiMain s=new SaoleiMain();
//				s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//				s.setVisible(true);
//			}
//		});
//	}
	
	private final int EASY=1,NORMAL=2,HARD=3;
	private JButton[] b=new JButton[480];
	private JPanel p1,p2;
	private JButton l1,l2;
	private JLabel[] l=new JLabel[480];
	private int[] score=new int[480];
	private boolean[] opened=new boolean[480];
	private int dangers,flags,rest;
	private Random ran=new Random();
	private int mode=EASY;//1-easy;2-normal;3-hard;
	private midMouse mid=new midMouse();
	private boolean[] act=new boolean[480];
	private actMouse a=new actMouse();
	private JButton restart=new JButton("Restart");
	//init
	
	public SaoleiMain(){
		super("Bird is not a SB");
		
		p1=new JPanel();
		p1.setLayout(new BoxLayout(p1, BoxLayout.X_AXIS));
		Component horizontalStrut1=Box.createHorizontalStrut(30);
		Component horizontalStrut2=Box.createHorizontalStrut(30);
		Component glue1=Box.createHorizontalGlue();
		Component glue2=Box.createHorizontalGlue();
		l1=new JButton();//rest flags number 
		l2=new JButton();//state
		l1.setEnabled(false);
		l2.setEnabled(false);
		restart.addMouseListener(new startNewGame());
		p1.add(horizontalStrut1);
		p1.add(l1);p1.add(glue1);
		p1.add(restart);
		p1.add(glue2);
		p1.add(l2);
		p1.add(horizontalStrut2);
		add(p1,BorderLayout.NORTH);
		//NORTH PART;
		
		menuMouse m=new menuMouse();
		JMenuBar menu=new JMenuBar();
		JMenu modeMenu=new JMenu("MODE");
		JMenuItem mItem;
		mItem=new JMenuItem("EASY");
		mItem.addMouseListener(m);
		modeMenu.add(mItem);
		mItem=new JMenuItem("NORMAL");
		mItem.addMouseListener(m);
		modeMenu.add(mItem);
		mItem=new JMenuItem("HARD");
		mItem.addMouseListener(m);
		modeMenu.add(mItem);
		menu.add(modeMenu);
		setJMenuBar(menu);
		
		for (int i=0;i<480;i++) act[i]=false;
		easy();
		
	}//更夛！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
	
	private void easy(){
		dangers=0;flags=10;rest=71;l1.setText(""+flags);l2.setText("preparing...");
		for (int i=0;i<480;i++){
			score[i]=0;
			opened[i]=false;
		}
		setSize(450,550);
//		setResizable(false);
		//init
		p2=new JPanel();
		p2.setLayout(new GridLayout(9,9,3,3));
		for (int i=0;i<81;i++){
			b[i]=new JButton("");
//			ImageIcon icon=new ImageIcon("D:\\eclipse-java-luna-SR2-win32-x86_64\\eclipse\\campl\\SaoleiSwing\\src\\1.jpg");
//			b[i].setIcon(icon);
			b[i].setActionCommand(""+i);
			b[i].addMouseListener(this);
			b[i].addActionListener(a);
			p2.add(b[i]);
		}
		add(p2,BorderLayout.CENTER);
		//CENTER PART
		for (int i=0;i<81;i++) score[i]=0;
		while(dangers<10){
			int i=ran.nextInt(81);
			if (score[i]==-1) continue;
			else {
				score[i]=-1;
				dangers++;
				addDangers(mode,i);
			}
		}
		for (int i=0;i<81;i++) opened[i]=false;
		//ADD DANGERS
	}
	private void normal(){
		dangers=0;flags=40;rest=216;l1.setText(""+flags);l2.setText("preparing...");
		for (int i=0;i<480;i++){
			score[i]=0;
			opened[i]=false;
		}
		setSize(730,800);
//		setResizable(false);
		//init
		p2=new JPanel();
		p2.setLayout(new GridLayout(16,16,3,3));
		for (int i=0;i<256;i++){
			b[i]=new JButton("");
			b[i].setActionCommand(""+i);
			b[i].addMouseListener(this);
			b[i].addActionListener(a);
			p2.add(b[i]);
		}
		add(p2,BorderLayout.CENTER);
		//CENTER PART
		for (int i=0;i<256;i++) score[i]=0;
		while(dangers<40){
			int i=ran.nextInt(256);
			if (score[i]==-1) continue;
			else {
				score[i]=-1;
				dangers++;
				addDangers(mode,i);
			}
		}
		for (int i=0;i<256;i++) opened[i]=false;
		//ADD DANGERS
	}
	private void hard(){
		dangers=0;flags=99;rest=381;l1.setText(""+flags);l2.setText("preparing...");
		for (int i=0;i<480;i++){
			score[i]=0;
			opened[i]=false;
		}
		setSize(1350,800);
//		setResizable(false);
		//init
		p2=new JPanel();
		p2.setLayout(new GridLayout(16,30,3,3));
		for (int i=0;i<480;i++){
			b[i]=new JButton("");
			b[i].setActionCommand(""+i);
			b[i].addMouseListener(this);
			b[i].addActionListener(a);
			p2.add(b[i]);
		}
		add(p2,BorderLayout.CENTER);
		//CENTER PART
		for (int i=0;i<480;i++) score[i]=0;
		while(dangers<99){
			int i=ran.nextInt(480);
			if (score[i]==-1) continue;
			else {
				score[i]=-1;
				dangers++;
				addDangers(mode,i);
			}
		}
		for (int i=0;i<480;i++) opened[i]=false;
		//ADD DANGERS
	}
	
	private void addD(int mode,int pos){
		switch (mode ) {
		case EASY:
			if (pos>=0 && pos<=80 && score[pos]>=0) score[pos]++;
			break;
		case NORMAL:
			if (pos>=0 && pos<=255 && score[pos]>=0) score[pos]++;
			break;
		case HARD:
			if (pos>=0 && pos<=479 && score[pos]>=0) score[pos]++;
			break;
		}
	}
	private void addDangers(int mode,int pos){
		switch (mode) {
		case EASY:
			if (pos%9!=0){
				addD(mode,pos-10);addD(mode,pos-1);addD(mode,pos+8);
			}
			if ((pos+1)%9!=0){
				addD(mode,pos-8);addD(mode,pos+1);addD(mode,pos+10);
			}
			addD(mode,pos-9);addD(mode,pos+9);
			break;
		case NORMAL:
			if (pos%16!=0){
				addD(mode,pos-17);addD(mode,pos-1);addD(mode,pos+15);
			}
			if ((pos+1)%16!=0){
				addD(mode,pos-15);addD(mode,pos+1);addD(mode,pos+17);
			}
			addD(mode,pos-16);addD(mode,pos+16);
			break;
		case HARD:
			if (pos%30!=0){
				addD(mode,pos-31);addD(mode,pos-1);addD(mode,pos+29);
			}
			if ((pos+1)%30!=0){
				addD(mode,pos-29);addD(mode,pos+1);addD(mode,pos+31);
			}
			addD(mode,pos-30);addD(mode,pos+30);
			break;
		}
		
	}//ADD DANGERS
	
	private void bfs(int mode,int n){
		int l=0,r=1,now;
		boolean[] f=new boolean[480],wf=new boolean[8];
		int[] q=new int[1000];
		int[] w1={-10,-9,-8,-1,1,8,9,10},w2={-17,-16,-15,-1,1,15,16,17},w3={-31,-30,-29,-1,1,29,30,31};
		
		f[n]=true;q[1]=n;
		do{
			l++;
			rest--;
			
			for (int i=0;i<8;i++) wf[i]=true;
			if ((mode==EASY && q[l]%9==0)||(mode==NORMAL && q[l]%16==0)||(mode==HARD && q[l]%30==0)){
				wf[0]=false;wf[3]=false;wf[5]=false;
			}
			if ((mode==EASY && (q[l]+1)%9==0)||(mode==NORMAL && (q[l]+1)%16==0)||(mode==HARD && (q[l]+1)%30==0)){
				wf[2]=false;wf[4]=false;wf[7]=false;
			}
			
			for (int i=0;i<8;i++)
				if(wf[i]==true){
					if (mode ==EASY) now=q[l]+w1[i];
					else if (mode==NORMAL) now=q[l]+w2[i];
					else now=q[l]+w3[i];
					if (now>=0 && ((mode==EASY&&now<81)||(mode==NORMAL && now<256)||(mode==HARD && now<480)))
						if (score[now]==0 && f[now]==false && opened[now]==false){
							r++;
							q[r]=now;
							f[now]=true;
						}else if (score[now]>0 && opened[now]==false){
							rest--;
							p2.remove(b[now]);
//							this.l[now]=new JLabel(""+score[now],SwingConstants.CENTER);
//							this.l[now].setBorder(new LineBorder(Color.BLACK,1));
//							p2.add(this.l[now],now);
							b[now]=new JButton(""+score[now]);
							b[now].setActionCommand(""+now);
							b[now].setBackground(Color.WHITE);
							b[now].addMouseListener(mid);
							p2.add(b[now],now);
							p2.updateUI();
							opened[now]=true;
						}
				}//SEARCH
			
//			this.l[q[l]]=new JLabel("");
//			this.l[q[l]].setBorder(new EmptyBorder(1,1,1,1));
			p2.remove(b[q[l]]);
//			p2.add(this.l[q[l]],q[l]);
			b[q[l]]=new JButton("");
			b[q[l]].setActionCommand(""+q[l]);
			b[q[l]].setBackground(Color.WHITE);
			b[q[l]].addMouseListener(mid);
			p2.add(b[q[l]],q[l]);
			p2.updateUI();
			opened[q[l]]=true;
			//UPDATE
		}while (l<r);
		
	}
	//BFS
	
	private void lose(int mode){
		int maxi;
		if (mode==EASY) maxi=81;
		else if (mode == NORMAL) maxi=256;
		else maxi=480;
		for (int i=0;i<maxi;i++){
			b[i].removeMouseListener(this);
			if (score[i]==-1 && opened[i]==false){
				p2.remove(b[i]);
				l[i]=new JLabel("B");
				l[i].setBorder(new LineBorder(Color.RED,1));
				p2.add(l[i],i);
			}
		}
		l2.setText("YOU LOSE!!!");
		l2.updateUI();
	}
	private void win(int mode){
		int maxi;
		if (mode==EASY) maxi=81;
		else if (mode==NORMAL) maxi=256;
		else maxi=480;
		for (int i=0;i<maxi;i++){
			b[i].removeMouseListener(this);
		}
		l2.setText("YOU WIN!!!");
		l2.updateUI();
	}
	
	public void mouseReleased(MouseEvent e) {
		JButton b=(JButton)e.getSource();
		int btype=e.getButton();
		int  bnum=Integer.parseInt(b.getActionCommand());
		
		if (btype==MouseEvent.BUTTON1){
			if (l2.getText().equals("preparing..."))
				l2.setText("gaming...");
			if (opened[bnum]==false){
				if (score[bnum]==-1) lose(mode);
				else if (score[bnum]==0){
					bfs(mode,bnum);
					if (rest==0) win(mode);
				}
				else if (score[bnum]>0) {
					p2.remove(this.b[bnum]);
					this.b[bnum]=new JButton(""+score[bnum]);
					this.b[bnum].setBackground(Color.WHITE);
					this.b[bnum].setActionCommand(""+bnum);
					this.b[bnum].addMouseListener(mid);
					p2.add(this.b[bnum],bnum);
					p2.updateUI();
					opened[bnum]=true;
					rest--;
					if (rest==0) win(mode);
				}
			}
		}else if (btype==MouseEvent.BUTTON3){
			if (flags>0){
				if (opened[bnum]==false){
					flags--;
					b.setText("F");
					score[bnum]+=600;
					opened[bnum]=true;
				}else if(score[bnum]>=500){
					b.setText("");
					flags++;
					score[bnum]-=600;
					opened[bnum]=false;
				}
				l1.setText(""+flags);
			}
		}
			
		//CLICKED EVENT
	}
	public void mousePressed(MouseEvent e) {
	}
	public void mouseClicked(MouseEvent e) {
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
	
	class menuMouse extends MouseAdapter{
		public void mousePressed(MouseEvent e) {
			int button=e.getButton();
			JMenuItem menu=(JMenuItem)e.getSource();
			String s=menu.getText();
			
			if (button==MouseEvent.BUTTON1){
				switch (s) {
				case "EASY":
					remove(p2);
					mode=EASY;
					easy();
					validate();
					break;
				case "NORMAL":
					remove(p2);
					mode=NORMAL;
					normal();
					validate();
					break;
				case "HARD":
					remove(p2);
					mode=HARD;
					hard();
					validate();
					break;
				}
			}
		}
	}
	
	class midMouse extends MouseAdapter{
		boolean[] wf=new boolean[8];
		int[] w1={-10,-9,-8,-1,1,8,9,10},w2={-17,-16,-15,-1,1,15,16,17},w3={-31,-30,-29,-1,1,29,30,31};
		int flagsnum;
	
		public void mousePressed(MouseEvent e) {
			JButton button=(JButton)e.getSource();
			int n=Integer.parseInt(button.getActionCommand());
			
			
			if (e.getModifiersEx()==(InputEvent.BUTTON1_DOWN_MASK+InputEvent.BUTTON3_DOWN_MASK)){
//			if(e.getButton()==MouseEvent.BUTTON1){
				for (int i=0;i<8;i++) wf[i]=true;
				flagsnum=0;
				
				if ((mode==EASY && n%9==0)||(mode==NORMAL && n%16==0)||(mode==HARD && n%30==0)){
					wf[0]=false;wf[3]=false;wf[5]=false;
				}
				if ((mode==EASY && (n+1)%9==0)||(mode==NORMAL && (n+1)%16==0)||(mode==HARD && (n+1)%30==0)){
					wf[2]=false;wf[4]=false;wf[7]=false;
				}
				switch (mode) {
				case EASY:
					for (int i=0;i<8;i++)
						if (wf[i]==true && n+w1[i]>=0 && n+w1[i]<81){
							if (score[n+w1[i]]>=500) flagsnum++;
						}
					if (flagsnum==score[n])
						for (int i=0;i<8;i++)
							if (wf[i]==true && n+w1[i]>=0 && n+w1[i]<81){
								if (opened[n+w1[i]]==false){
									act[n+w1[i]]=true;
									b[n+w1[i]].doClick();
									validate();
								}
							}
					break;
				case NORMAL:
					for (int i=0;i<8;i++)
						if (wf[i]==true && n+w2[i]>=0 && n+w2[i]<256){
							if (score[n+w2[i]]>=500) flagsnum++;
						}
					if (flagsnum==score[n])
						for (int i=0;i<8;i++)
							if (wf[i]==true && n+w2[i]>=0 && n+w2[i]<256){
								if (opened[n+w2[i]]==false){
									act[n+w2[i]]=true;
									b[n+w2[i]].doClick();
									validate();
								}
							}
					break;
				case HARD:
					for (int i=0;i<8;i++)
						if (wf[i]==true && n+w3[i]>=0 && n+w3[i]<480){
							if (score[n+w3[i]]>=500) flagsnum++;
						}
					if (flagsnum==score[n])
						for (int i=0;i<8;i++)
							if (wf[i]==true && n+w3[i]>=0 && n+w3[i]<480){
								if (opened[n+w3[i]]==false){
									act[n+w3[i]]=true;
									b[n+w3[i]].doClick();
									validate();
								}
							}
					break;
				}
			}
		}
	}
	
	class actMouse implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			JButton	 button=(JButton)e.getSource();
			int n=Integer.parseInt(button.getActionCommand());
			
			if (act[n]==true){
				if (opened[n]==false){
					if (score[n]==-1) lose(mode);
					else if (score[n]==0){
						bfs(mode,n);
						if (rest==0) win(mode);
					}
					else if (score[n]>0) {
						p2.remove(b[n]);
						b[n]=new JButton(""+score[n]);
						b[n].setBackground(Color.WHITE);
						b[n].setActionCommand(""+n);
						b[n].addMouseListener(mid);
						p2.add(b[n],n);
						p2.updateUI();
						opened[n]=true;
						rest--;
						if (rest==0) win(mode);
					}
				}
				act[n]=false;
			}
		}	
	}
	
	class startNewGame extends MouseAdapter{
		public void mouseClicked(MouseEvent e) {
			if (e.getButton()==MouseEvent.BUTTON1){
				switch (mode) {
				case EASY:
					remove(p2);
					easy();
					validate();
					break;
				case NORMAL:
					remove(p2);
					normal();
					validate();
					break;
				case HARD:
					remove(p2);
					hard();
					validate();
					break;
				}
			}
		}
	}
}
