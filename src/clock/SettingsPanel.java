package clock;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/***************************
 * Written by: Simon Cicek
 * Last changed: 2012-03-28
 ***************************/

public class SettingsPanel extends JPanel implements ActionListener
{
    JLabel hourL = new JLabel("Hour:");
    JLabel minuteL = new JLabel("Minute:");
    JLabel secondL = new JLabel("Second:");
    JTextField hour = new JTextField(2);
    JTextField minute = new JTextField(2);
    JTextField second = new JTextField(2);
    JButton set = new JButton("Set");
    
    public SettingsPanel()
    {
        this.add(hourL);
        this.add(hour);
        
        this.add(minuteL);
        this.add(minute);
        
        this.add(secondL);
        this.add(second);
     
        this.add(set);
        
        set.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        try
        {
            if(hour.getText().isEmpty() && minute.getText().isEmpty() && second.getText().isEmpty())
                Logic.selectedTime = Logic.Time.SYSTEM;
            else if (hour.getText().length() > 2 || minute.getText().length() > 2 || second.getText().length() > 2)
                throw new Exception();
            else if (Integer.parseInt(hour.getText()) > 23   || Integer.parseInt(hour.getText()) < 0 ||
                     Integer.parseInt(minute.getText()) > 59 || Integer.parseInt(minute.getText()) < 0 || 
                     Integer.parseInt(second.getText()) > 59 || Integer.parseInt(second.getText()) < 0)
                throw new Exception();
            else
            {
                Logic.hour = Integer.parseInt(this.hour.getText());
                Logic.minute = Integer.parseInt(this.minute.getText());
                Logic.second = Integer.parseInt(this.second.getText()) - 1; 
                Logic.selectedTime = Logic.Time.USER;
                Logic.timeSelected = false;
            }
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, "You enter invalid information!");
        }
    }
}
