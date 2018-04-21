/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package second_app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicComboBoxUI;

/**
 *
 * @author NEXT
 */


public class user_temp extends javax.swing.JFrame {
    
    
    /**
     * Creates new form user_temp
     */
 
    JLabel driver_namel,genderl,addressl,cityl,districtl,statel,countryl,mobilel,emaill,telel;
    JLabel vtypel,vnumberl,vbrandl,vnamel,vmnumberl,monthl,dayl,sourcel,destl;
    JLabel co_nml,addl,eml,tele,numl,doc_1l,doc_2l,driver,owner;
    JTextField co_nmt,mailt,teleot,mobileot;
    JTextField driver_namet,mobilet,emailt,telet,vnumbert;    
    TextArea address,addt;
    JComboBox city,district,state,country,brand,name,number,vmnumber;
    JComboBox month_combo,day;
    JCheckBox vtype,source,dest,pan4,pan3;
    ArrayList<JCheckBox> chka=new ArrayList<JCheckBox>();
    ArrayList<JCheckBox> sourcea=new ArrayList<JCheckBox>();
    ArrayList<JCheckBox> desta=new ArrayList<JCheckBox>();
    ArrayList<JCheckBox> pan4a=new ArrayList<JCheckBox>();
    ArrayList<JCheckBox> pan3a=new ArrayList<JCheckBox>();
    int id,flag=0;
      public user_temp() {
        initComponents();        
    }     
      public void jpan4()
      {
          try
          {          
          Connection con=connection.getConnect();
          Statement st=con.createStatement();
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
          Statement st50=con.createStatement();   
          Statement st51=con.createStatement();  
          Statement st52=con.createStatement();
          Statement st53=con.createStatement();
          String typeq="select Vehicle_Type from vehicle_type" ;
          String countryq="select Country_Name from country";
          String brandq="select Brand_Name from vehicle_brand";
          String monthq="select Times from month_duration";
          String dayq="select Days from day_duration";          
          String sourceq="select City_Name from city";
          String destq="select City_Name from city";          
          ResultSet r1=st.executeQuery(countryq);  
          ResultSet r2=st1.executeQuery(typeq);
          ResultSet r3=st2.executeQuery(brandq);
          ResultSet r4=st3.executeQuery(monthq);
          ResultSet r5=st4.executeQuery(dayq);
          ResultSet r6=st5.executeQuery(sourceq);    
          ResultSet r7=st6.executeQuery(destq);  
          jPanel4.setLayout(new GridLayout(9, 1));
          city=new JComboBox();
          district=new JComboBox();
          state=new JComboBox();
          country=new JComboBox();
          brand=new JComboBox();
          name=new JComboBox();
          number=new JComboBox();
          vmnumber=new JComboBox();
          month_combo=new JComboBox();
          day=new JComboBox();
         // city.addItem("Select City");     
          driver_namel=new JLabel("Driver Name");
          driver_namet=new JTextField(4);
          addressl=new JLabel("Address");
          address=new TextArea(2, 4);
          cityl=new JLabel("City");
          districtl=new JLabel("District");
          statel=new JLabel("State");
          countryl=new JLabel("Country");
          mobilel=new JLabel("Mobile");
          mobilet=new JTextField(4);
          telel=new JLabel("Telephone");
          telet=new JTextField(4);
          emaill=new JLabel("E-mail");
          emailt=new JTextField(4);
          vtypel=new JLabel("Vehicle Type");
          vnumberl=new JLabel("Vehicle Number");
          vnumbert=new JTextField(4);
          vbrandl=new JLabel("Vehicle Brand");
          vnamel=new JLabel("Vehicle Name");
          vnumberl=new JLabel("Vehicle Number");
          vmnumberl=new JLabel("Vehicle Model Number");
          monthl=new JLabel("How many times per MONTH?");
          dayl=new JLabel("How many times per DAY?");
          doc_1l=new JLabel("1) Driver With Owner");
          doc_2l=new JLabel("2) Driver But Not Owner");
          sourcel=new JLabel("Check Your Source");
          destl=new JLabel("Check Your Destination");
          driver=new JLabel("Driver");
          owner=new JLabel("Owner");
          country.removeAllItems();
          country.addItem("select country");
          state.removeAllItems();
         // state.addItem("select state");
          district.removeAllItems();
         // district.addItem("select district");                 
          city.removeAllItems();
          brand.removeAllItems();
          brand.addItem("Select Brand");
         // city.addItem("select City");
          while(r1.next())
          {
                country.addItem(r1.getString("Country_Name"));                     
          }                                    
          jPanel4.add(driver_namel);
          jPanel4.add(driver_namet);
          jPanel4.add(addressl);
          jPanel4.add(address);
          jPanel4.add(countryl);
          jPanel4.add(country);         
                 /* country.addItemListener(new ItemListener() {
              @Override
              public void itemStateChanged(ItemEvent e) {
                 System.out.println("selceted country  " + country.getSelectedItem());
                 String nm=country.getSelectedItem().toString();
                 String countryidq="select Country_ID from country where Country_Name='"+nm+"'";              
                 try
                      {
                              ResultSet r8=st7.executeQuery(countryidq);
                              while(r8.next())
                              {                                  
                                  if(id==0)
                                  id=r8.getInt("Country_ID");
                              }
                     //         System.out.println("id is "+ id); 
                   /*           stateq="select State_Name from state where Country_ID="+id+"";
                              //state.removeAllItems();
                              ResultSet r9=st8.executeQuery(stateq);                              
                              while(r9.next())
                              {
                                  System.out.println(r9.getString("State_Name"));
                                  state.addItem(r9.getString("State_Name"));
                              }
                          } 
                        
                 catch (SQLException ex) {
            Logger.getLogger(user_temp.class.getName()).log(Level.SEVERE, null, ex);
        }                                                         
              }
          });*/
                country.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                   System.out.println("selceted country  " + country.getSelectedItem());
                   String nm=country.getSelectedItem().toString();
                  
                    String countryidq="select Country_ID from country where Country_Name='"+nm+"'";    
                    String stateq;
                 try
                      {
                              ResultSet r8=st7.executeQuery(countryidq);
                              while(r8.next())
                              {                                
                                 id=r8.getInt("Country_ID");
                              }
                            stateq="select State_Name from state where Country_ID="+id+"";
                             ResultSet r9=st8.executeQuery(stateq); 
                             state.removeAllItems();
                              while(r9.next())
                              {
                                  System.out.println(r9.getString("State_Name"));
                                  state.addItem(r9.getString("State_Name"));
                              }
                           
                
                      }
                 catch (SQLException ex) {
            Logger.getLogger(user_temp.class.getName()).log(Level.SEVERE, null, ex);
        }        
              }
          });
                  jPanel4.add(statel);
                  jPanel4.add(state);
                  state.addItemListener(new ItemListener() {
              @Override
              public void itemStateChanged(ItemEvent e) {
                 System.out.println("selected state is " + e.getItem().toString());
                 String nm1=e.getItem().toString();
                    int id1=0;
                   String stateq="select State_ID from state where State_Name='"+nm1+"'";    
                   String cityq;
                   
                 try
                      {
                              ResultSet r10=st9.executeQuery(stateq);
                              while(r10.next())
                              {                                
                                 id1=r10.getInt("State_ID");
                                 System.out.println("State id is "+id1);
                              }
                            cityq="select City_Name from city where State_ID="+id1+"";
                             ResultSet r11=st10.executeQuery(cityq); 
                             city.removeAllItems();
                              while(r11.next())
                              {
                                  System.out.println(r11.getString("City_Name"));
                                  city.addItem(r11.getString("City_Name"));
                              } 
                      }
                 catch (SQLException ex) {
            Logger.getLogger(user_temp.class.getName()).log(Level.SEVERE, null, ex);
        }        
              }             
              
          });                                
              jPanel4.add(cityl);
               jPanel4.add(city);               
                   city.addItemListener(new ItemListener() {
              @Override
              public void itemStateChanged(ItemEvent e) {
                 System.out.println("selected city is " + e.getItem().toString());
                 String nm2=e.getItem().toString();
                    int id2=0;
                   String cityidq="select City_ID from city where City_Name='"+nm2+"'";    
                  String distq;                   
                 try
                      {
                              ResultSet r12=st11.executeQuery(cityidq);
                              while(r12.next())
                              {                                
                                 id2=r12.getInt("City_ID");
                                 System.out.println("City id is "+id2);
                              }
                            distq="select District_Name from district where City_ID="+id2+"";
                             ResultSet r13=st12.executeQuery(distq); 
                             district.removeAllItems();
                              while(r13.next())
                              {
                                  System.out.println(r13.getString("District_Name"));
                                  district.addItem(r13.getString("District_Name"));
                              } 
                      }
                 catch (SQLException ex) {
            Logger.getLogger(user_temp.class.getName()).log(Level.SEVERE, null, ex);
        }                     
              
              }
          });                  
                  jPanel4.add(districtl);
                  jPanel4.add(district);                              
                  jPanel4.add(mobilel);jPanel4.add(mobilet);
                  jPanel4.add(telel);jPanel4.add(telet);    
                  jPanel4.add(emaill);jPanel4.add(emailt);    
                  jPanel4.add(vtypel);
                  while(r2.next())
                  {
                      vtype=new JCheckBox();
                      vtype.setText(r2.getString("Vehicle_Type"));  
                      chka.add(vtype);
                      jPanel4.add(vtype);    
                  }                  
                  jPanel4.add(vnumberl);
                  jPanel4.add(vnumbert);
                  jPanel4.add(vbrandl);
                  while(r3.next())
                  {
                      brand.addItem(r3.getString("Brand_Name"));
                  }
                  jPanel4.add(brand);
                   brand.addItemListener(new ItemListener() {
              @Override
              public void itemStateChanged(ItemEvent e) {
                 System.out.println("selected brand is " + e.getItem().toString());
                 String nm3=e.getItem().toString();
                    int id9=0;
                    String nameq;
                   String brandidq="select Brand_ID from vehicle_brand where Brand_Name='"+nm3+"'";    
                   try
                   {
                       ResultSet r50=st50.executeQuery(brandidq);
                       while(r50.next())
                       {
                           id9=r50.getInt("Brand_ID");
                           System.out.println("Brand id is "+id9); 
                       }
                             nameq="select Vehicle_Name from vehicle_name where Brand_ID="+id9+"";
                             ResultSet r51=st51.executeQuery(nameq); 
                             name.removeAllItems();
                             while(r51.next())
                             {
                                 name.addItem(r51.getString("Vehicle_Name"));
                             }                            
                   }
                   catch(SQLException ex)
                           {
                               Logger.getLogger(user_temp.class.getName()).log(Level.SEVERE, null, ex);
                           }
                   
              }
                   });
                 
                  jPanel4.add(vnamel);                  
                  jPanel4.add(name);
                  name.addItemListener(new ItemListener() {
              @Override
              public void itemStateChanged(ItemEvent e) {
                 System.out.println("selected name is " + e.getItem().toString());
                 String nm9=e.getItem().toString();
                    int id8=0;
                   String nameidq="select V_Name_ID from vehicle_name where Vehicle_Name='"+nm9+"'"; 
                   String numberq;
                   try
                   {
                       ResultSet r52=st52.executeQuery(nameidq);
                       while(r52.next())
                       {
                           id8=r52.getInt("V_Name_ID");
                           System.out.println("selected name is id"+id8);
                       }
                       numberq="select Model_Number from model_number where vname_id="+id8+"";
                       ResultSet r53=st53.executeQuery(numberq); 
                            vmnumber.removeAllItems();
                             while(r53.next())
                             {
                                 System.out.println("Model Number is "+r53.getString("Model_Number"));
                                 vmnumber.addItem(r53.getString("Model_Number"));
                             }
                       
                   }
                   catch (SQLException ex) {
            Logger.getLogger(user_temp.class.getName()).log(Level.SEVERE, null, ex);
        }        
              }
              });
                  jPanel4.add(vmnumberl);
                  jPanel4.add(vmnumber);                  
                  jPanel4.add(sourcel);
                  while(r6.next())
                  {
                       source=new JCheckBox();
                      source.setText(r6.getString("City_Name"));  
                      sourcea.add(source);
                      jPanel4.add(source);
                  }                  
                  jPanel4.add(destl);
                  while(r7.next())
                  {
                       dest=new JCheckBox();
                      dest.setText(r7.getString("City_Name"));  
                      desta.add(dest);
                      jPanel4.add(dest);
                  }
                  jPanel4.add(monthl);
                 while(r4.next())
                  {
                    System.out.println("month :" + r4.getString("Times"));
                    month_combo.addItem(r4.getString("Times"));
                  }
                  jPanel4.add(month_combo);
                  while(r5.next())
                  {
                    System.out.println(r5.getString("Days"));                      
                    day.addItem(r5.getString("Days"));
                  }
                  jPanel4.add(dayl);
                  jPanel4.add(day);                             
                  jPanel4.revalidate();
                  jPanel4.repaint();
                  
          }
          catch (ClassNotFoundException ex) {
            Logger.getLogger(user_temp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(user_temp.class.getName()).log(Level.SEVERE, null, ex);
        }    
      }
      public void doc3()
      {
          try              
          {
                   Connection con1=connection.getConnect();
                   /*Statement st13=con2.createStatement();                    
                   Statement st14=con2.createStatement();             
                   Statement st20=con2.createStatement();             
                   Statement st21=con2.createStatement();             
                   jPanel3.add(doc_1l);
                   jPanel3.add(driver);
                   String doc_id="select Doc_ID from document_allocation_form where Person_ID=1";
                   String doc_name;
                   ResultSet r14=st13.executeQuery(doc_id);
                   int id3;
                   while(r14.next())
                   {                      
                      id3=r14.getInt("Doc_ID");
                      doc_name="select Doc_Name from documents where Doc_ID="+id3+"";
                      ResultSet r15=st14.executeQuery(doc_name);
                      while(r15.next())
                      {
                          System.out.println("Needed doc are "+r15.getString("Doc_Name"));
                          pan3=new JCheckBox();
                          pan3.setText(r15.getString("Doc_Name"));
                          pan3a.add(pan3);
                          jPanel3.add(pan3);
                      }
                  }
                   jPanel3.add(owner);
                    String doc_id1="select Doc_ID from document_allocation_form where Person_ID=2";
                   String doc_name1;
                   ResultSet r20=st20.executeQuery(doc_id1);
                   int id4;
                   while(r20.next())
                   {                      
                      id4=r20.getInt("Doc_ID");
                      doc_name1="select Doc_Name from documents where Doc_ID="+id4+"";
                      ResultSet r21=st21.executeQuery(doc_name1);
                      while(r21.next())
                      {
                          System.out.println("Needed doc are "+r21.getString("Doc_Name"));
                          pan3=new JCheckBox();
                          pan3.setText(r21.getString("Doc_Name"));
                          pan3a.add(pan3);
                          jPanel3.add(pan3);
                      }
                  }*/
               String doc_id2="select Doc_ID from document_allocation_form where Person_ID=1";
                    String doc_name2;
                    jPanel3.add(doc_2l);
                     Statement st15=con1.createStatement();                    
                    Statement st16=con1.createStatement();  
                    ResultSet r16=st15.executeQuery(doc_id2);
                    int id4;
                    jPanel3.add(driver);
                    while(r16.next())
                    {                    
                  
                      id4=r16.getInt("Doc_ID");
                      doc_name2="select Doc_Name from documents where Doc_ID="+id4+"";
                      ResultSet r17=st16.executeQuery(doc_name2);
                      while(r17.next())
                      {
                          System.out.println("Driver without owner(Driver) "+r17.getString("Doc_Name"));
                          pan3=new JCheckBox();
                          pan3.setText(r17.getString("Doc_Name"));
                          pan3a.add(pan3);
                          jPanel3.add(pan3);
                      }
                  }
                    jPanel3.add(owner);
                    Statement st45=con1.createStatement();
                    Statement st46=con1.createStatement();
                    String doc_id3="select Doc_ID from document_allocation_form where Person_ID=2";
                    String doc_name3;
                    ResultSet r20=st45.executeQuery(doc_id3);
                    int id5;
                   
                    while(r20.next())
                    {

                        id5=r20.getInt("Doc_ID");
                        System.out.println("needed for owner doc are "+ id5);
                        doc_name3="select Doc_Name from documents where Doc_ID="+id5+"";
                        
                        ResultSet r21=st46.executeQuery(doc_name3);
                        while(r21.next())
                        {
                          System.out.println("Driver Without owner(Owner)"+r21.getString("Doc_Name"));
                          pan3=new JCheckBox();
               
                          pan3.setText("owner " + r21.getString("Doc_Name"));
                          pan3a.add(pan3);
                          jPanel3.add(pan3);                            
                        }
                    }
                   jPanel3.revalidate();
                   jPanel3.repaint();     
                   pack();
          }
          catch (ClassNotFoundException ex) {
            Logger.getLogger(user_temp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(user_temp.class.getName()).log(Level.SEVERE, null, ex);
        } 
      }
    public void doc4()
      {
          try              
          {
                     Connection con4=connection.getConnect();
                     Statement st40=con4.createStatement();                    
                     Statement st41=con4.createStatement();             
                     jPanel4.add(doc_1l);
                     String doc_id4="select Doc_ID from document_allocation_form where Person_ID=3";
                     String doc_name4;
                     ResultSet r40=st40.executeQuery(doc_id4);
                     int id4;
                     while(r40.next())
                    {                      
                        id4=r40.getInt("Doc_ID");
                        doc_name4="select Doc_Name from documents where Doc_ID="+id4+"";
                        ResultSet r41=st41.executeQuery(doc_name4);
                        while(r41.next())
                        {
                          System.out.println("Needed doc are "+r41.getString("Doc_Name"));
                          pan4=new JCheckBox();
                          pan4.setText(r41.getString("Doc_Name"));
                          pan4a.add(pan4);
                          jPanel4.add(pan4);
                       }
                  }
                  jPanel4.revalidate();
                  jPanel4.repaint();                  
          }  
          catch (ClassNotFoundException ex) {
            Logger.getLogger(user_temp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(user_temp.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
     public void jpan3()
      {         
                    System.out.println("select No");
                    jPanel3.setLayout(new GridLayout(9,1));
                    co_nml=new JLabel("Company/Person Name");
                    co_nmt=new JTextField(4);
                    addl=new JLabel("Adrresss");
                    addt=new TextArea(2,4);
                    eml=new JLabel("E-mail");
                    mailt=new JTextField(4);
                    tele=new JLabel("Telephone");
                    teleot=new JTextField(4);
                    numl=new JLabel("Moobile");
                    mobileot=new JTextField(4);
                  /*  jPanel3.add(co_nml);
                    jPanel3.add(co_nmt);
                    jPanel3.add(addl);
                    jPanel3.add(addt);
                    jPanel3.add(eml);
                    jPanel3.add(mailt);
                    jPanel3.add(tele);
                    jPanel3.add(teleot);
                    jPanel3.add(numl);
                    jPanel3.add(mobileot);*/                                                                
              
                    jPanel3.revalidate();
                    jPanel3.repaint();
      }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        radiopanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        yes = new javax.swing.JRadioButton();
        no = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 212, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(93, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.setBorder(new javax.swing.border.MatteBorder(null));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 4, Short.MAX_VALUE)
        );

        jLabel1.setText("Are You Driver With Owner?");

        jButton1.setText("SUBMIT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        buttonGroup3.add(yes);
        yes.setText("Yes");

        buttonGroup3.add(no);
        no.setText("No");

        javax.swing.GroupLayout radiopanelLayout = new javax.swing.GroupLayout(radiopanel);
        radiopanel.setLayout(radiopanelLayout);
        radiopanelLayout.setHorizontalGroup(
            radiopanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(radiopanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(57, 57, 57)
                .addComponent(yes)
                .addGap(29, 29, 29)
                .addComponent(no)
                .addGap(35, 35, 35)
                .addComponent(jButton1)
                .addContainerGap())
        );
        radiopanelLayout.setVerticalGroup(
            radiopanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(radiopanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(radiopanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton1)
                    .addComponent(yes)
                    .addComponent(no))
                .addContainerGap())
        );

        jPanel4.setBorder(new javax.swing.border.MatteBorder(null));

        jScrollPane1.setBorder(new javax.swing.border.MatteBorder(null));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 815, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(radiopanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(112, 112, 112)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(radiopanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
           for (Enumeration<AbstractButton> buttons = buttonGroup3.getElements(); buttons.hasMoreElements();) 
             {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) 
            {
                System.out.println("selected button is "+button.getText());
                if(button.getText().equals("Yes"))
                {
                 System.out.println("Yes button selected");
                 jpan4();
                 doc4();
            //      jPanel4.setLayout(new GridLayout(9, 1));
               /*not work  JPanel p1=new JPanel();
                  p1.setLayout(new BorderLayout());
                  p1.setName("yespanel");
                  p1.setBackground(Color.red);*/
                  /* city.addActionListener(new ActionListener() {
                          @Override
                          public void actionPerformed(ActionEvent e) {
                             
                              System.out.println("selected combo is "+nm);                              
                              String cityidq="select City_ID from city where City_Name='"+nm+"'";
                              try
                              {
                              ResultSet r2=st1.executeQuery(cityidq);
                              while(r2.next())
                              {
                                  System.out.println("id is "+r2.getInt("City_ID"));
                                  break;
                              }
                              long id;
                              String districtq;
                              ResultSet r3;
                              if(r2.next())
                              {
                                  id=r2.getLong("City_ID");
                                districtq="select district_name from disrict where City_ID='+id+'";  
                                 r3=st2.executeQuery(districtq);
                                 while(r3.next())
                                 {
                                     System.out.println(r3.getString("District_Name"));
                                 }//r3 
                                
                              }//r2
                              }
                              catch(SQLException ex)
                              {
                                  Logger.getLogger(user_temp.class.getName()).log(Level.SEVERE, null, ex);
                              }
                          }
                      });//action listnr*/
                  
                  
                }//button.gettext=yes
                else if(button.getText().equals("No"))
                {
                    jpan4();
                    jpan3();
                    doc3();
                }
             //   pack();
    }//GEN-LAST:event_jButton1ActionPerformed
     }//for
   
   /*   catch (ClassNotFoundException ex) {
            Logger.getLogger(user_temp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(user_temp.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    
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
            java.util.logging.Logger.getLogger(user_temp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(user_temp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(user_temp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(user_temp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new user_temp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton no;
    private javax.swing.JPanel radiopanel;
    private javax.swing.JRadioButton yes;
    // End of variables declaration//GEN-END:variables
}
