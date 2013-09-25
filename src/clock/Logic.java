package clock;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

/***************************
 * Written by: Simon Cicek
 * Last changed: 2012-03-28
 ***************************/

public class Logic implements ActionListener
{   
    public static int hour = 0;
    public static int minute = 0;
    public static int second = 0;
    public static int amPM = 0;
    public static Time selectedTime = Time.SYSTEM;
    public static boolean timeSelected = false;
    
    public static enum Time
    {
        SYSTEM(0), USER(1);
        private int value;
        private Time(int value) 
        {
            this.value = value;
        }
    }
    
    public static double getHourDegree(int h, int m)
    {
        return 0.5*(60*h + m);
    }
   
    public static double getMinuteDegree(int m)
    {
        return 6*m;
    }
   
    public static int[] getSystemTime()
    {
        Calendar c = Calendar.getInstance();
        int t[] = {c.get(Calendar.HOUR),c.get(Calendar.MINUTE),c.get(Calendar.SECOND), c.get(Calendar.AM_PM)};
        return t;
    }
    
    public static int[] getUserTime()
    {
        int t[] = {hour,minute,second,3};
        return t;
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
