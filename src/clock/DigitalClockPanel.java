package clock;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Calendar;
import javax.swing.JPanel;

public class DigitalClockPanel extends JPanel implements MouseListener
{
    int hour,minute,second,amPm;
    String h,m,s;
    Font DSDigital;
    Rectangle green = new Rectangle(35, 320,30,30);
    Rectangle red = new Rectangle(100, 320,30,30);
    Rectangle orange = new Rectangle(165, 320,30,30);
    Rectangle blue = new Rectangle(225, 320,30,30);
    Rectangle[] buttons = {green,red,orange,blue};
    Color[] colors = {Color.green,Color.red,Color.orange,Color.blue};
    Color currentColor = Color.green;
    
    public DigitalClockPanel()
    {
        this.setBackground(Color.black);
        try
        {
            DSDigital = Font.createFont(Font.TRUETYPE_FONT, new File("DS-DIGIB.ttf"));
            DSDigital = DSDigital.deriveFont(Font.PLAIN,100);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        this.addMouseListener(this);
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
        RenderingHints.VALUE_ANTIALIAS_ON);
                
        g2.setColor(currentColor);
        
        if(hour > 23)
            this.hour = 0;
        
        if(hour < 10)
            h = "0" + hour;
        else
            h = "" + hour;
        
        if(minute < 10)
            m = "0" + minute;
        else
            m = "" + minute;
        
        if(second < 10)
            s = "0" + second;
        else
            s = "" + second;
        
        g2.setFont(DSDigital);
        g2.drawString(h + ":" + m + ":" + s, 35, 220);
        
        g2.drawString(h + ":" + m + ":" + s, 35, 220);
        g2.setColor(Color.green);
        g2.fill(green);
        g2.setColor(Color.red);
        g2.fill(red);
        g2.setColor(Color.orange);
        g2.fill(orange);
        g2.setColor(Color.blue);
        g2.fill(blue);
    }
    
    public void setTime(int h, int m, int s, int ap)
    {
        if(ap == Calendar.PM)
            if(Logic.selectedTime == Logic.Time.SYSTEM)
                h = 10 + (h + 2);
                    
        
        this.hour = h;
        this.minute = m;
        this.second = s;
        this.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) 
    {
        for(int i = 0; i < buttons.length; i++)
            if(e.getX() >= buttons[i].getX() && e.getX() <= buttons[i].getMaxX() &&
               e.getY() >= buttons[i].getY() && e.getY() <= buttons[i].getMaxY())
            {
                currentColor = colors[i];
                this.repaint();
                break;
            }
    }

    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}
