package clock;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MainFrame extends JFrame 
{
    AnalogClockPanel p;
    DigitalClockPanel dp;
    SettingsPanel sp;
    JTabbedPane tp = new JTabbedPane();
    
    public MainFrame()
    {
        p = new AnalogClockPanel();
        dp = new DigitalClockPanel();
        sp = new SettingsPanel();
        tp.addTab("Analog", p);
        tp.addTab("Digital", dp);
        tp.add("Settings",sp);
        this.add(tp);
        this.setSize(AnalogClockPanel.clockSize + 13,AnalogClockPanel.clockSize + 57);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Simon Cicek - Clock");
        this.setResizable(false);
    }
    
    public static void main(String[] args) throws InterruptedException 
    {
        try 
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } 
        catch (Exception e) {}
        
        final MainFrame frame = new MainFrame();
        
        int time[] = Logic.getSystemTime();
        
        frame.p.setTime(time[0], time[1], time[2]);
        frame.dp.setTime(time[0], time[1], time[2],time[3]);
        
        int delay = 1000; 
        ActionListener taskPerformer = new ActionListener() 
        {
            int time[] = Logic.getSystemTime();
            
            public void actionPerformed(ActionEvent evt) 
            {
                if(!Logic.timeSelected)
                {
                    if(Logic.selectedTime == Logic.Time.SYSTEM)
                    {
                        time = Logic.getSystemTime();
                        Logic.timeSelected = true;
                    }   
                    else if(Logic.selectedTime == Logic.Time.USER)
                    {
                        time = Logic.getUserTime();
                        Logic.timeSelected = true;
                    }
                }
                
                time[2]++;
                if(time[2] == 60)
                {
                    time[2] = 0;
                    time[1]++;
                }
                if(time[1] == 60)
                {
                    time[1] = 0;
                    time[0]++;
                }
                
                frame.p.setTime(time[0], time[1], time[2]);
                frame.dp.setTime(time[0], time[1], time[2],time[3]);
            }
        };
        new Timer(delay, taskPerformer).start();
    }
}
