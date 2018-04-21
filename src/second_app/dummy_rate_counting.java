/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package second_app;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NEXT
 */
public class dummy_rate_counting extends javax.swing.JFrame {

    /**
     * Creates new form dummy_rate_counting
     */
    public dummy_rate_counting() {
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
            Statement st18=con.createStatement();
            Calendar cal=GregorianCalendar.getInstance();
            int year=cal.get(Calendar.YEAR);
            int month=cal.get(Calendar.MONTH);
            int date=cal.get(Calendar.DATE);
            int hour=cal.get(Calendar.HOUR);
            int min=cal.get(Calendar.MINUTE);
            int sec=cal.get(Calendar.SECOND),last_id=0;
            String cur_time=date+"-"+month+"-"+year+" "+hour+":"+min+":"+sec;
            System.out.println("Current time is "+cur_time);
            int u_id=54,cnt=0,flag=0,id=0,count=0,p_id=0,card_id=0,card_amt=0,day=0,total_cut_perday=0,remain_amt=0,rem=0;
            int day_cnt=0,month_cnt=0,dayid=0,monthid=0,dayTime=0,monthTime=0,pr_id=0,day1=0,Extra=0;
            int dayE=0,monthE=0,yearE=0,dayArrival=0,yearArrival=0,monthArrival=0;
            String get_qry="select * from card_purchase_trans";            
             ResultSet repeat_rs=st1.executeQuery(get_qry);
            String user_idq="",cardtrnsq="",costq="",exist="",return_time="",arrival_time="",costq1="",expire_date="",renew_date=""; 
            while(repeat_rs.next())
            {
              id=repeat_rs.getInt("user_id");
              
              if(u_id==id)
              {
              //System.out.println("ID is "+id);
              count=repeat_rs.getInt("cnt");
              if(count==2)
              {
                  //System.out.println("third time entry");
                  flag=0;
              }
              }
             if(u_id==id && count!=2)
              {
              System.out.println("Match Found For "+id);
              cnt=2;
              String update_idq="update card_purchase_trans set return_date='"+cur_time+"',cnt="+cnt+" where user_id='"+u_id+"' and cnt=1";
              System.out.println("update is "+update_idq);
              st2.execute(update_idq);
              flag=1;
              }
            }
            String xtra="select * from card_purchase_trans where user_id="+u_id+" order by ID desc limit 0,1";
            ResultSet r17=st17.executeQuery(xtra);
            while(r17.next())
            {
                System.out.println("Arrival date "+r17.getString("arrival_date")+" Return Date "+r17.getString("return_date")+ "ID is "+r17.getInt("ID"));
                return_time=r17.getString("return_date");
                arrival_time=r17.getString("arrival_date");
                String dayA=arrival_time.substring(0, 2);
                String dayR=return_time.substring(0, 2);
                dayArrival=Integer.parseInt(dayR);
                monthArrival=Integer.parseInt(arrival_time.substring(3,4));
                yearArrival=Integer.parseInt(arrival_time.substring(5,9));
                
                //monthArrival=Integer.parseInt(dayR);
                System.out.println("DayA is "+dayA+" dayR"+dayR);
                if(!dayA.equals(dayR))
                {
                   
                    user_idq="select * from user_db where User_DB_ID='"+u_id+"'";
                    ResultSet r12=st12.executeQuery(user_idq);
                    while(r12.next())
                    {
                        pr_id=r12.getInt("Price_ID");
                    }
                    costq1="select total from dumy where id="+pr_id+"";
                    ResultSet r16=st16.executeQuery(costq1);
                    while(r16.next())
                    {
                        day1=r16.getInt("total");
                    }
                    Extra=day1;
                    System.out.println("Extra charges calculated "+Extra);
                }
            }
            if(flag==0)
            {
                cnt=1;
                ResultSet r10=st10.executeQuery(get_qry);
                
                while(r10.next())
                {
                   
                    if(u_id==r10.getInt("user_id"))
                    {
                      exist="true";  
                      break;
                    }
                    else            
                    {
                        exist="false";
                    }
                }
                if(exist.equals("false"))
                {
                    System.out.println("cut from main amount "+u_id);
                    user_idq="select * from user_db where User_DB_ID='"+u_id+"'";
                    //String remq1="select * from card_purchase_trans where user_id='"+u_id+"' order by ID desc limit 0,1";
                    ResultSet r2=st2.executeQuery(user_idq);
                    while(r2.next())
                    {
                        p_id=r2.getInt("Price_ID");
                        card_id=r2.getInt("Card_ID");
                        expire_date=r2.getString("Expire_Date");
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
                    //System.out.println("before extra amount")
                    total_cut_perday=card_amt-day-Extra;
                    System.out.println("before extra amount "+total_cut_perday);
                    String demo_insert="insert into card_purchase_trans(user_id,arrival_date,return_date,remain_amt,renewal_alrt,cnt) values("+u_id+",'18-11-2014 8:00','18-11-2014 8:00',"+total_cut_perday+",'"+expire_date+"',"+cnt+")";
                    st1.execute(demo_insert);
                    
                    System.out.println("insert is "+demo_insert);
                }
               
                if(exist.equals("true"))
                {
                    System.out.println("cut from REMAINING amount "+ u_id);
                    String remq="select * from card_purchase_trans where user_id='"+u_id+"' order by ID desc limit 0,1";
                    ResultSet r6=st6.executeQuery(remq);
                    while(r6.next())
                    {
                        rem=r6.getInt("remain_amt");
                    }
                    user_idq="select * from user_db where User_DB_ID='"+u_id+"'";
                    ResultSet r7=st7.executeQuery(user_idq);
                    while(r7.next())
                    {
                        p_id=r7.getInt("Price_ID");
                       card_id=r7.getInt("Card_ID");
                       expire_date=r7.getString("Expire_Date");
                    }
                    
                    String c="select * from card_type where Card_ID="+card_id+"";
                    ResultSet r13=st13.executeQuery(c);
                    while(r13.next())
                    {
                        dayid=r13.getInt("Day_ID");
                        monthid=r13.getInt("Month_ID");
                        
                    }
                    String dayT="select Days from day_duration where Day_ID="+dayid+"";
                    String monthT="select Times from month_duration where Month_ID="+monthid+"";
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
                    costq="select total from dumy where id="+p_id+"";
                    ResultSet r9=st9.executeQuery(costq);
                    while(r9.next())
                    {
                        day=r9.getInt("total");
                    }
                    remain_amt=rem-day-Extra;
                    //System.out.println("again remaining amount is "+remain_amt);
                    if(remain_amt<0)
                    {
                        System.out.println("NOT ENOUGH BALANCE"); //print on LCD 
                    }
                    String demo_insert="insert into card_purchase_trans(user_id,arrival_date,return_date,remain_amt,renewal_alrt,cnt) values("+u_id+",'18-11-2014 8:00','18-11-2014 8:00',"+remain_amt+",'"+expire_date+"',"+cnt+")";
                    st1.execute(demo_insert);
                    ResultSet r18=st18.executeQuery(remq);
                    while(r18.next())
                    {
                        renew_date=r18.getString("renewal_alrt");
                        last_id=r18.getInt("ID");
                    }
                    System.out.println("0-2 day "+renew_date.substring(0,2)+" 3-5 month "+renew_date.substring(3, 4)+" 5-9 year "+renew_date.substring(5, 9) + "for user id "+last_id);
                    dayE=Integer.parseInt(renew_date.substring(0,2));
                    monthE=Integer.parseInt(renew_date.substring(3,4));
                    yearE=Integer.parseInt(renew_date.substring(5,9));
                    if(dayE>dayArrival && monthE>monthArrival && yearE>yearArrival)
                    {
                        System.out.println("Expire OR Renewal Date");
                    }
                    day_cnt++;
                    month_cnt++; 
                    System.out.println("insert is "+demo_insert);
                    System.out.println("Day time "+dayTime+"Month Time "+monthTime);
                    if(dayTime<day_cnt)
                    {
                        System.out.println("your time occurance expire");//print on LCD
                    }
                    if(monthTime<month_cnt)
                    {
                        System.out.println("your time occurance expire");//print on LCD
                    }
                }
            }
           
            
        }
        
        catch (SQLException ex) {
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
            java.util.logging.Logger.getLogger(dummy_rate_counting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dummy_rate_counting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dummy_rate_counting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dummy_rate_counting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new dummy_rate_counting().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
