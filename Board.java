import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
 *
 * @author OK
 */
public class Board extends JPanel implements ActionListener
{
    private Image apple;
    private Image dot;
    private Image head;
    
    private int dots;
    private final int DOT_SIZE=10;
    
    private final int x[]=new int[1600];
    private final int y[]=new int[1600];
    
    private final int RANDOM_POSITION=40;
    
    private int appleX;
    private int appleY;
    
    private Timer timer;
    
    private boolean leftdirection=false;
    private boolean rightdirection=true;
    private boolean updirection=false;
    private boolean downdirection=false;
    private int Scores;
    private boolean Ingame=true;
    
     Board()
     {
         addKeyListener(new TAdapter());
         setBackground(Color.BLACK);
         setPreferredSize(new Dimension(400,400));
         
         setFocusable(true);
         loadImage();
         insidegame();
     }
    public void loadImage()
    {
 ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Snakegame/icons/apple.png")); 
 apple=i1.getImage();
 
 ImageIcon i2=new ImageIcon(ClassLoader.getSystemResource("Snakegame/icons/dot.png"));
 dot=i2.getImage();
 
 ImageIcon i3=new ImageIcon(ClassLoader.getSystemResource("Snakegame/icons/head.png"));
    head=i3.getImage();
    }
    
    public void insidegame()
    {
        dots=3;
        for(int z=0;z<dots;z++)
        {
            x[z]=50-z*DOT_SIZE;
            y[z]=50;
        }
    locateApple();
    
    timer=new Timer(140,this);
    timer.start();
    }
    
    
    public void locateApple()
    {
        int r=(int)(Math.random()* RANDOM_POSITION);
        appleX=(r*DOT_SIZE);
        
    }
    
    public void checkApple()
    {
        if(x[0]==appleX && y[0]==appleY)
        { 
            dots++;  
            Scores++;
            locateApple();
        }
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        draw(g);
        
    }
    public void draw(Graphics g)
    {
        if(Ingame)
        {
            g.drawImage(apple,appleX,appleY,this);
            
            for(int i=0;i<dots;i++)
            {
                if(i==0)
                    g.drawImage(head, x[i], y[i], this);
                else
                    g.drawImage(dot,x[i],y[i], this);
            }
            Toolkit.getDefaultToolkit().sync();
        }
        else
            gameover(g); 
    }
    
    public void gameover(Graphics g)
    {
        String str="GAME OVER";
        String msg="Scores are";
        Font f=new Font("SAN SERIF",Font.BOLD,20);
        FontMetrics metrices=getFontMetrics(f);
        g.setColor(Color.CYAN);
        g.setFont(f);
        
       g.drawString(msg+": " +Scores, 150, 150);
        g.drawString(str,(400-metrices.stringWidth(str))/2,400/2);
    }
    
    public void checkCollision()
    {
        for(int z=dots;z>0;z--)
        {
            if(z>4 && x[0]==x[z] && y[0]==y[z])
            {
        Ingame=false;
            }
        }
        
                 if(y[0]>=400)
            {
            Ingame=false;
            }
           if(x[0]>=400)
           {
            Ingame=false;
            }
           if(x[0]<0){
               Ingame=false;
           }
           if(y[0]<0)
           {
               Ingame=false;
           }
           
           if(!Ingame)
           {
               timer.stop();
           }
    }
    
    public void move()
    {
        for(int z=dots;z>0;z--)
        {
            x[z]=x[z-1];
            y[z]=y[z-1];
        }
        
        if(leftdirection){
            x[0]=x[0]-DOT_SIZE;
        }
        if(rightdirection){
            x[0]=x[0]+DOT_SIZE;
        }
        if(updirection){
            y[0]=y[0]-DOT_SIZE;
        }
        if(downdirection){
            y[0]=y[0]+DOT_SIZE;
        }
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        if(Ingame)
        {
            checkApple();
             checkCollision();
             move();
        }
        repaint();
        
    }
    
    private class TAdapter extends KeyAdapter{

        public void keyPressed(KeyEvent e)
        {
    int key = e.getKeyCode();
            
             if(key==KeyEvent.VK_LEFT && (!rightdirection))
             {
                 leftdirection=true;
                 updirection=false;
                 downdirection=false;
             }
               
             if(key==KeyEvent.VK_RIGHT && (!leftdirection))
             {
                 rightdirection=true;
                 updirection=false;
                 downdirection=false;
             }
             
             if(key==KeyEvent.VK_UP && (!downdirection))
             {
                 updirection=true;
                 rightdirection=false;
                 leftdirection=false;
             }
             
             if(key==KeyEvent.VK_DOWN && (!updirection))
             {
                 downdirection=true;
                 rightdirection=false;
                 leftdirection=false;
             }
        }
        
    }
}
