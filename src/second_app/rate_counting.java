/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package second_app;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author NEXT
 */
public class rate_counting extends javax.swing.JFrame {

    /**
     * Creates new form rate_counting
     */
    SerialPort serialPort;
     private static final String PORT_NAMES[] = {
        "COM4" // Windows
    };
    private BufferedReader input;
    
    private OutputStream output;
    private static final int TIME_OUT = 2000;
    /**
     * Default bits per second for COM port.
     */
    private static final int DATA_RATE = 9600;
    public rate_counting() {
        initComponents();
        CountRate();
    }
    public void CountRate()
    {
        try
        {
            Connection con=connection.getConnect();
            Statement st1=con.createStatement();
            Statement st2=con.createStatement();
            Statement st3=con.createStatement();
            Statement st4=con.createStatement();
            Statement st5=con.createStatement();
            Statement st6=con.createStatement();
            Statement st7=con.createStatement();
            Statement st8=con.createStatement();
            Statement st9=con.createStatement();
            Statement st10=con.createStatement();
            Statement st11=con.createStatement();
            Statement st12=con.createStatement();
            Statement st13=con.createStatement();
            Statement st14=con.createStatement();
            Statement st15=con.createStatement();
            Statement st16=con.createStatement();
            Statement st17=con.createStatement();
            Calendar cal=GregorianCalendar.getInstance();
            int year=cal.get(Calendar.YEAR);
            int month=cal.get(Calendar.MONTH);
            int date=cal.get(Calendar.DATE);
            int hour=cal.get(Calendar.HOUR);
            int min=cal.get(Calendar.MINUTE);
            int sec=cal.get(Calendar.SECOND);
            String cur_time=date+"-"+month+"-"+year+" "+hour+":"+min+":"+sec;
            System.out.println("Current time is "+cur_time);
            /*jTextField5.setText(hour+":"+min+":"+sec);
            jTextField3.setText(date+"-"+month+"-"+(year+1)+" ");*/
            int u_id=54,p_id=0,day=0,card_amt=0,card_id=0,total_cut_perday=0,flag=0,id=0,cnt=1,day_cnt=0,month_cnt=0,dayid=0,monthid=0,change=0,update=0,insert=0;
            int extra_charge=50;
            int monthTime=0,dayTime=0;
            int rem=0,remain_amt=0;
            String repeat_idq="select User_ID from card_purchase_transaction";
            String update_idq="update card_purchase_transaction set Return_Date='"+cur_time+"' where User_ID='"+u_id+"'";
            String r_time="select * from card_purchase_transaction where User_ID='"+u_id+"'";
            ResultSet r5=st5.executeQuery(repeat_idq);
            String user_idq="",cardtrnsq="",costq="",return_time="",arrival_time="",return_time17="",arrival_time17="";
            while(r5.next())// if already exist
            {
                id=r5.getInt("User_ID");                
                if(id==u_id)
                {
                    cnt++;
                   // System.out.println("cnt is:" + cnt);
                }
                    
                    if(/*cnt==1*/ cnt%2==0)//2nd 4th 6th do update 
                    {                 
                        ResultSet r17=st17.executeQuery(r_time);
                        while(r17.next())
                        {
                            return_time17=r17.getString("Return_Date");
                            arrival_time17=r17.getString("Arrival_Date");
                        } 
                        String dayA17=arrival_time17.substring(0, 2);
                        String dayR17=return_time17.substring(0, 2);
                        String timeA17=arrival_time17.substring(10,14);
                        String timeR17=return_time17.substring(11,15);
                        if(timeA17.equals("timeR17"));
                        {
                        st11.execute(update_idq);                             
                        update=1;
                        flag=3;
                        }
                        cnt++;
                      } 
                    if(cnt>=2)
                    {
                        System.out.println("IN cnt >2");
                        ResultSet r12=st12.executeQuery(r_time);
                       while(r12.next())
                        {
                            return_time=r12.getString("Return_Date");
                            arrival_time=r12.getString("Arrival_Date");
                        } 
                        String dayA=arrival_time.substring(0, 2);
                        String dayR=return_time.substring(0, 2);
                        String timeA=arrival_time.substring(10,14);
                        String timeR=return_time.substring(11,15);
                     /*   System.out.println("hour of arrival is "+timeA);
                        System.out.println("hour of return is "+timeR);*/
                       /* if(timeA.equals(timeR))
                        {
                            System.out.println("both time are equal");
                            update=2;//not update so insert
                            flag=1;
                        }*/
                        if(update!=1 && flag!=3)
                        {
                            System.out.println("not same update "+update+" flag"+flag);
                            update=2;
                            flag=1;
                        }
                    }   
            }
            System.out.println("UPDATE "+update+"FLAG"+flag);
            if(flag==0 && update==0)//very first time 
            {
            System.out.println("first time enter update value "+update+"flag "+flag);
            user_idq="select * from user_db where User_DB_ID='"+u_id+"'";
            ResultSet r2=st2.executeQuery(user_idq);
            while(r2.next())
            {
                p_id=r2.getInt("Price_ID");
                card_id=r2.getInt("Card_ID");
            }
            cardtrnsq="select Card_Prise from card_price_trans where Card_ID='"+card_id+"'";
             ResultSet r4=st4.executeQuery(cardtrnsq);
                while(r4.next())
                {
                    card_amt=r4.getInt("Card_Prise");
                }
                costq="select total from dumy where id="+p_id+"";
                ResultSet r3=st3.executeQuery(costq);
                while(r3.next())
                {
                    day=r3.getInt("total");
                }
                total_cut_perday=card_amt-day;
                String demo_insert="insert into card_purchase_transaction(User_ID,Arrival_Date,Return_Date,Remaining_Amount,Renewal_Alert) values("+u_id+",'25-08-1994 4:00','25-08-1994 4:00',"+total_cut_perday+",'25-12-2014')";
                st1.execute(demo_insert);
                day_cnt++;
                month_cnt++;
                String c="select * from card_type where Card_ID='+card_id+'";
                ResultSet r13=st13.executeQuery(c);
                while(r13.next())
                {
                    dayid=r13.getInt("Day_ID");
                    monthid=r13.getInt("Month_ID");
                }
                String dayT="select Days from day_duration where Day_ID='+dayid+'";
                String monthT="select Times from month_duration where Month_ID='+monthid+'";
                String remainAmtq="select Remaining_Amount from card_purchase_transaction";
                ResultSet r16=st16.executeQuery(remainAmtq);
                ResultSet r14=st14.executeQuery(dayT);
                ResultSet r15=st15.executeQuery(monthT);
                while(r14.next())
                {
                    dayTime=r14.getInt("Days");
                }
                while(r15.next())
                {
                    monthTime=r15.getInt("Times");
                }
                while(r16.next())
                {
                    remain_amt=r16.getInt("Remaining_Amount");
                }
                /*if(day_cnt>dayTime)
                {
                    //extra charge calculated per day and per   
                    remain_amt=remain_amt-extra_charge;                    
                }
                if(month_cnt>monthTime)
                {
                    //extra charge calculated
                    remain_amt=remain_amt-extra_charge;
                }*/
                
            }
            if(flag==1 && update==2)//2,3,4,5,6..
            {
                System.out.println("more than first time enter update value "+update+"flag "+flag);
                String remq="select Remaining_Amount from card_purchase_transaction where User_ID='"+u_id+"'";
                ResultSet r6=st6.executeQuery(remq);
                while(r6.next())
                {
                    rem=r6.getInt("Remaining_Amount");
                    System.out.println("remaining amt in loooop "+rem);
                }
                
                 user_idq="select * from user_db where User_DB_ID='"+u_id+"'";
                ResultSet r7=st7.executeQuery(user_idq);
                while(r7.next())
                {
                 p_id=r7.getInt("Price_ID");
                 card_id=r7.getInt("Card_ID");
                }
                 cardtrnsq="select Card_Prise from card_price_trans where Card_ID='"+card_id+"'";
                 ResultSet r8=st8.executeQuery(cardtrnsq);
                while(r8.next())
                {
                    card_amt=r8.getInt("Card_Prise");
                }
                costq="select total from dumy where id="+p_id+"";
                ResultSet r9=st9.executeQuery(costq);
                while(r9.next())
                {
                    day=r9.getInt("total");
                }
                 System.out.println("again per cost is "+day);
                remain_amt=rem-day;
                System.out.println("again remaining amt is"+remain_amt);//update tbl_nm set colmnm=var where u_id = uid
             
                
                String demo_insert1="insert into card_purchase_transaction(User_ID,Arrival_Date,Return_Date,Remaining_Amount,Renewal_Alert) values("+u_id+",'29-11-2014 7:00','29-11-2014 7:00',"+remain_amt+",'25-12-2014')";
                st10.execute(demo_insert1);
                System.out.println(" inserted again remaining amt is"+remain_amt);//update tbl_nm set colmnm=var where u_id = uid
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(rate_counting.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(rate_counting.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
        public void initialize() {
        // the next line is for Raspberry Pi and
        // gets us into the while loop and was suggested here was suggested
        // http://www.raspberrypi.org/phpBB3/viewtopic.php?f=81&t=32186
        System.setProperty("gnu.io.rxtx.SerialPorts", "COM4");

        CommPortIdentifier portId = null;
        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

        // First, Find an instance of serial port as set in PORT_NAMES.
        while (portEnum.hasMoreElements()) {
            CommPortIdentifier currPortId = (CommPortIdentifier) portEnum
                    .nextElement();
            for (String portName : PORT_NAMES) {
                if (currPortId.getName().equals(portName)) {
                    portId = currPortId;
                    break;
                }
            }
        }
        if (portId == null) {
            System.out.println("Could not find COM port.");
            return;
        }

        try {
            // open serial port, and use class name for the appName.
            serialPort = (SerialPort) portId.open(this.getClass().getName(),
                    TIME_OUT);

            // set port parameters
            serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

            // open the streams
            input = new BufferedReader(new InputStreamReader(
                    serialPort.getInputStream()));
            /*
             * String s; while((s = input.readLine())!=null) {
             * System.out.println(s); }
             */

            output = serialPort.getOutputStream();

            // add event listeners
            serialPort.addEventListener((SerialPortEventListener) this);
            serialPort.notifyOnDataAvailable(true);
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
 public synchronized void close() {
        if (serialPort != null) {
            serialPort.removeEventListener();
            serialPort.close();
        }
    }

    public synchronized void serialEvent(SerialPortEvent oEvent) {
        int i = 0;
        Integer x[] = new Integer[23];
        String sta = "A Card";
        byte[] bytesa = sta.getBytes();
        String stb = "B Card";
        byte[] bytesb = stb.getBytes();
        String stc = "C Card";
        byte[] bytesc = stc.getBytes();
        String ste = "E Card";
        byte[] bytese = ste.getBytes();
        String std = "D Card";
        byte[] bytesd = std.getBytes();

        if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
            try {
                String inputLine = input.readLine();
                System.out.println(inputLine + "Hey");
                if (inputLine.equals("505748485355566968535053")) {
                    System.out.println("A");
                    try (OutputStream out = new BufferedOutputStream(new BufferedOutputStream(output))) {
                        out.write(bytesa);
              
                    } catch (Exception e) {
                        e.getMessage();
                    }
                } else if (inputLine.contains("505748485749504868545269")) {
                    System.out.println("B");
                    try (OutputStream out = new BufferedOutputStream(new BufferedOutputStream(output))) {
                        out.write(bytesb);
                    } catch (Exception e) {
                        e.getMessage();
                    }

                } else if (inputLine.contains("495048485656705154694855")) {
                    System.out.println("C");
                    try (OutputStream out = new BufferedOutputStream(new BufferedOutputStream(output))) {
                        out.write(bytesc);
                    } catch (Exception e) {
                        e.getMessage();
                    }
                } else if (inputLine.contains("495048485656705165556769")) {
                    System.out.println("D");
                    try (OutputStream out = new BufferedOutputStream(new BufferedOutputStream(output))) {
                        out.write(bytesd);
                    } catch (Exception e) {
                        e.getMessage();
                    }
                } else if (inputLine.contains("495048485656705151685352")) {
                    System.out.println("E");
                    try (OutputStream out = new BufferedOutputStream(new BufferedOutputStream(output))) {
                        out.write(bytese);
                    } catch (Exception e) {
                        e.getMessage();
                    }
                } else {
                    System.out.println("Invalide");
                }

            } catch (Exception e) {
                // System.err.println(e.toString());
            }

        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(rate_counting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(rate_counting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(rate_counting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(rate_counting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        SerialTest main=new SerialTest();
        main.initialize();
        Thread t=new Thread(){
            public void run()
            {
                try{
                    Thread.sleep(10000000);
                   }
                catch(InterruptedException ie){
                    
                }
                
            }
        };
        t.start();
        System.out.println("Started");
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new rate_counting().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
