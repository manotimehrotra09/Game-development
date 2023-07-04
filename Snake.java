import javax.swing.*;

public class Snake extends JFrame{
    Snake()
    {
        Board b=new Board();
        add(b);
        pack();
        setLocationRelativeTo(null);
        setTitle("SNAKE GAME");
        setResizable(false);
        
    }
    public static void main(String args[])
    {    
      Snake obj=new Snake();
        obj.setVisible(true);
    }
}
