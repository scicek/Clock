package clock;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/***************************
 * Written by: Simon Cicek
 * Last changed: 2012-03-28
 ***************************/

public class AnalogClockPanel extends JPanel
{
    public static final int clockX = 3; 
    public static final int clockY = 3; 
    public static final int clockSize = 400;
    public static Polygon hourPolygon = new Polygon();
    public static Polygon minutePolygon = new Polygon();
    Shape h,m;
    int hour,minute,second;
    BufferedImage clockFace;
    AffineTransform af = new AffineTransform();
    Color secColors[] = {Color.lightGray,Color.gray};
    int index = 1,prev = 0;
    
    public AnalogClockPanel()
    {
        try
        {
            clockFace = ImageIO.read(new File("clockface.png"));
        }
        catch(Exception e){e.printStackTrace();}
        
        this.setBackground(Color.black);
        // Middle point (base of the pointer)
        hourPolygon.addPoint(clockSize/2, clockSize/2);
        // Left point
        hourPolygon.addPoint(clockSize/2 - 15, clockSize/2 - 20);
        // Top
        hourPolygon.addPoint(clockSize/2, clockY + 66);
        // Right point
        hourPolygon.addPoint(clockSize/2 + 15, clockSize/2 - 20);
        
        // Middle point (base of the pointer)
        minutePolygon.addPoint(clockSize/2, clockSize/2);
        // Left point
        minutePolygon.addPoint(clockSize/2 - 10, clockSize/2 - 20);
        // Top
        minutePolygon.addPoint(clockSize/2, clockY + 48);
        // Right point
        minutePolygon.addPoint(clockSize/2 + 10, clockSize/2 - 20);    
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
        RenderingHints.VALUE_ANTIALIAS_ON);
               
        g2.setColor(Color.red);
        g2.fillOval(clockX + 48, clockY + 46, clockSize - 100,clockSize - 100);
        g2.setColor(Color.red);
        g2.fillOval(clockX - 3, clockY - 3, clockSize,clockSize);
        g2.setColor(secColors[prev]);
        g2.fillOval(61, 59, clockSize - 121, clockSize - 120);
        g2.setColor(secColors[index]);
        if(second == 0)
        {
            prev = index;
            index++;
            index = index % secColors.length;
            g2.fillOval(61, 59, clockSize - 121, clockSize - 120);
            g2.setColor(secColors[index]);
        }   
        g2.fill(new Arc2D.Double(61, 59, clockSize - 121, clockSize - 120, 90, -second*6,Arc2D.PIE));
        g2.setColor(Color.black);
        af = new AffineTransform();
        af.rotate(Math.toRadians(Logic.getHourDegree(hour,minute)), clockSize/2, clockSize/2);
        h = af.createTransformedShape(hourPolygon);
        af = new AffineTransform();
        af.rotate(Math.toRadians(Logic.getMinuteDegree(minute)), clockSize/2, clockSize/2);
        m = af.createTransformedShape(minutePolygon);
        
        
        g2.setStroke(new BasicStroke(3,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER));
        g2.setColor(Color.red);
        g2.fill(m);
        g2.setColor(Color.black);
        g2.draw(m);
        g2.setStroke(new BasicStroke(3,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER));
        g2.setColor(Color.red);
        g2.fill(h);
        g2.setColor(Color.black);
        g2.draw(h);
        g2.setColor(Color.white);
        g2.fillOval(clockSize/2 - 6, clockSize/2 - 5, 10,10);
        g2.setColor(Color.black);
        g2.drawOval(clockSize/2 - 6, clockSize/2 - 5, 10,10);
        g2.drawImage(clockFace, 7, 10, null);
        g2.setStroke(new BasicStroke(2,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER));
        g2.drawOval(clockX + 46, clockY + 47, clockSize - 99,clockSize - 100);
        g2.drawOval(clockX + 58, clockY + 55, clockSize - 120,clockSize - 120);
        g2.drawOval(clockX + 4, clockY + 5, clockSize - 17,clockSize - 16);
    }
    
    public void setTime(int h, int m, int s)
    {
        this.hour = h;
        this.minute = m;
        this.second = s;
        this.repaint();
    }
}
