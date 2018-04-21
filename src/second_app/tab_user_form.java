/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package second_app;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Character.digit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;

/**
 *
 * @author NEXT
 */
public class tab_user_form extends javax.swing.JFrame {

    /**
     * Creates new form tab_user_form
     */
    long l_id=1;
    ArrayList<JRadioButton> cardrl = new ArrayList<JRadioButton>();
    JRadioButton cardr;
    ButtonGroup b5 = new ButtonGroup();
    JRadioButton chk1, ver;
    JCheckBox d_chk, o_chk, ychk;
    ArrayList<JRadioButton> chkl = new ArrayList<JRadioButton>();
    ArrayList<JRadioButton> vl = new ArrayList<JRadioButton>();
    String flag=" ";
    ArrayList<JCheckBox> d_chkl = new ArrayList<JCheckBox>();
    ArrayList<JCheckBox> o_chkl = new ArrayList<JCheckBox>();
    ArrayList<JCheckBox> ychkl = new ArrayList<JCheckBox>();
    ButtonGroup b1 = new ButtonGroup();
    ButtonGroup b2 = new ButtonGroup();
    ButtonGroup b6 = new ButtonGroup();
    String selected_card,cardqry,time,i_date,e_date;
  //  String[] col={"User_DB_ID","Gender","District_ID","Mobile","Telephone","Vehicle_ID","Model_Number_ID","Selected_Option","Verify_ID","Source_ID","Dest_ID","Month_ID","Day_ID","Doc_ID"};
    String docstr="",o_insert="",type1="";
    String owner_doc="",o_tr="",tr="";
    int diff=0,h=0,price_id=0;
    String yes_doc="",rsn="",y_tr="";
    String d_name;
    String gender, add, email, v_number,mobile, tele;
    int dist_id, v_type_id, v_model_id, verify_id,flg=0;
    String o_name, o_add, o_gender,o_email;
    int source_id, dest_id, m_id=4, d_id=5, doc_id, o_city_id, o_mobile,temp=0;
    int card_id=0;
    String vnm = null;
    String vnmq = null;
    String bnmq = null;
    int vid = 0;
    String qry="";
    String qry1="",qry2="",qry3="";
    Calendar cal=GregorianCalendar.getInstance();
    int year=cal.get(Calendar.YEAR);
    int mon=cal.get(Calendar.MONTH)+1;
    int date=cal.get(Calendar.DATE);
    int hour=cal.get(Calendar.HOUR);
    int min=cal.get(Calendar.MINUTE);
    int sec=cal.get(Calendar.SECOND);
        
    public tab_user_form() {
        initComponents();
     /*  jTabbedPane1.remove(1); // onwer Panel hide
       jTabbedPane1.remove(4); // No tab pane hide
       jTabbedPane1.remove(3); // Yes tab pane hide */
        jTabbedPane1.setEnabledAt(1,false); // onwer Panel show
        jTabbedPane1.setEnabledAt(4,false); // No tab pane show
        jTabbedPane1.setEnabledAt(5,false); // Yes tab pane hide
        
        jTabbedPane1.revalidate();
        jTabbedPane1.repaint();
         jLabel35.setText(" ");
            jLabel36.setText(" ");
            jLabel39.setText(" ");
            jLabel40.setText(" ");
       try
        {
            String cardq="select Card_Name from card_type ";
            Connection con=connection.getConnect();
            Statement st41=con.createStatement();
            Statement st=con.createStatement();
            Statement st45 = con.createStatement();
            ResultSet r41 = st41.executeQuery(cardq);
            ResultSet r45 = st45.executeQuery(cardq);
           
            Statement st1 = con.createStatement();
            Statement st12 = con.createStatement();
            Statement st16 = con.createStatement();
            Statement st14 = con.createStatement();
            Statement st17 = con.createStatement();
            Statement st18 = con.createStatement();
            Statement st19 = con.createStatement();
            Statement st20 = con.createStatement();
            Statement st21 = con.createStatement();
            Statement st22 = con.createStatement();
            Statement st23 = con.createStatement();
            Statement st24 = con.createStatement();
            Statement st25 = con.createStatement();
            Statement st50 = con.createStatement();
            String countryq = "select Country_Name from country";
            String cityq = "select City_Name from city";
            String v_type = "select Vehicle_Type from vehicle_type";
            String month1 = "select Times from month_duration";
            String day1 = "select Days from day_duration";
            String doc_id1 = "select Doc_ID from document_allocation_form where Person_ID=1";//driver
            String doc_id2 = "select Doc_ID from document_allocation_form where Person_ID=2";//owner
            String doc_id3 = "select Doc_ID from document_allocation_form where Person_ID=3";//owner with driver
            String verifyq = "select Reason from verification";
           String verifyidq="select Verify_ID from verification where Reason='"+rsn+"'"; 
            ResultSet r1 = st1.executeQuery(countryq);
            ResultSet r16 = st16.executeQuery(cityq);
            ResultSet r17 = st17.executeQuery(month1);
            ResultSet r18 = st18.executeQuery(day1);
            ResultSet r19 = st19.executeQuery(doc_id1);
            ResultSet r20 = st20.executeQuery(doc_id2);
            ResultSet r23 = st23.executeQuery(doc_id3);
            
            source.removeAllItems();
            dest.removeAllItems();
            day.removeAllItems();
            month.removeAllItems();
            month.addItem("select month");
            day.addItem("select day");
            while (r16.next()) {
                source.addItem(r16.getString("City_Name"));
                dest.addItem(r16.getString("City_Name"));
            }
            while (r17.next()) {
                month.addItem(r17.getString("Times"));
            }
            while (r18.next()) {
                day.addItem(r18.getString("Days"));
            }
            //Yes_Doc_Panel.setLayout(new GridLayout(5,1));
            driver.setLayout(new GridLayout(5, 1));
            owner.setLayout(new GridLayout(5, 1));
            dopanel.setLayout(new GridLayout(5, 1));
            int idd = 0;
            int ido = 0, idod = 0;
            //otwner document
            while (r19.next()) {
                idd = r19.getInt("Doc_ID");
                String doc_name1 = "select Doc_Name from documents where Doc_ID=" + idd + "";
                ResultSet r21 = st21.executeQuery(doc_name1);
                while (r21.next()) {
                    //System.out.println("driver doc are "+r21.getString("Doc_Name"));
                    d_chk = new JCheckBox();
                    d_chk.setText(r21.getString("Doc_Name"));
                    d_chkl.add(d_chk);
                    driver.add(d_chk);
                    driver.revalidate();
                    driver.repaint();
                }
            }
            //driver doc
            while (r20.next()) {
                ido = r20.getInt("Doc_ID");
                String doc_name2 = "select Doc_Name from documents where Doc_ID=" + ido + "";
                ResultSet r22 = st22.executeQuery(doc_name2);
                while (r22.next()) {
                    //System.out.println("driver doc are "+r22.getString("Doc_Name"));
                    o_chk = new JCheckBox();
                    o_chk.setText(r22.getString("Doc_Name"));
                    o_chkl.add(o_chk);
                    owner.add(o_chk);
                    owner.revalidate();
                    owner.repaint();
                }
            }
            //driver with owner
            while (r23.next()) {
                idod = r23.getInt("Doc_ID");
                String doc_name3 = "select Doc_Name from documents where Doc_ID=" + idod + "";
                ResultSet r24 = st24.executeQuery(doc_name3);
                while (r24.next()) {
                    //System.out.println("driver doc are "+r22.getString("Doc_Name"));
                    ychk = new JCheckBox();
                    ychk.setText(r24.getString("Doc_Name"));
                    ychkl.add(ychk);
                    dopanel.add(ychk);
                    dopanel.revalidate();
                    dopanel.repaint();
                }
            }
            //verify_panel
            verify_panel.setLayout(new FlowLayout());
            ResultSet r25 = st25.executeQuery(verifyq);
            while (r25.next()) {
                ver = new JRadioButton();
                ver.setText(r25.getString("Reason"));
                b2.add(ver);
                vl.add(ver);
                verify_panel.add(ver);
                verify_panel.revalidate();
                verify_panel.repaint();
            }
              for (JRadioButton jradi : vl) {
                jradi.addActionListener(new ActionListener() {
                    
                    @Override
                    public void actionPerformed(ActionEvent e) {
                           if (jradi.isSelected() == true) {
                               try {
                                   rsn=jradi.getText();
                                   System.out.println("reason for verification"+rsn);
                                   
                                   ResultSet r50=st50.executeQuery(verifyidq);
                                   while(r50.next())
                                   {
                                       verify_id=r50.getInt("Verify_ID");
                                       
                                   }
                                   System.out.println("verify id is"+verify_id);
                               } catch (SQLException ex) {
                                   Logger.getLogger(tab_user_form.class.getName()).log(Level.SEVERE, null, ex);
                               }
                           }
                    }
                });
                        }
            //card type
            card.setLayout(new GridLayout(4,1));
            
            while(r41.next())
            {
                    cardr = new JRadioButton();
                    cardr.setText(r41.getString("Card_Name"));
                    b5.add(cardr);
                    cardrl.add(cardr);
                    card.add(cardr);
                   // card1.add(cardr);
                    card.revalidate();
                  // card1.revalidate();
                    card.repaint();
                   // card1.repaint();
            }
            jPanel7.setLayout(new GridLayout(4,1));
            while(r45.next())
            {
                    cardr = new JRadioButton();
                    cardr.setText(r45.getString("Card_Name"));
                    b6.add(cardr);
                    cardrl.add(cardr);
                   // card.add(cardr);
                    jPanel7.add(cardr);
                   // card.revalidate();
                  jPanel7.revalidate();
                  //  card.repaint();
                   jPanel7.repaint();
            }
            
            for(JRadioButton obj:cardrl)
            {
                obj.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        if(obj.isSelected()==true)
                        {
                            selected_card=obj.getText().toString();
                            cardqry = "select * from card_type where Card_Name='" + selected_card + "'";
                            try
                            {
                            ResultSet r=st.executeQuery(cardqry);
                            while(r.next())
                            {
                                time=r.getString("Card_Duration");
                                card_id=r.getInt("Card_ID");
                                System.out.println("Card Duration is "+time);
                                
                            }
                            String[] digit=time.split(" ");
                            System.out.println("digit is "+digit[0]+ " intial is "+digit[1]);
                            diff=Integer.parseInt(digit[0]);
                            if(digit[1].equalsIgnoreCase("month"))
                            {
                                jLabel35.setText(date+"-"+mon+"-"+year+" ");
                                
                                jLabel39.setText(date+"-"+mon+"-"+year+" ");
                                if(mon+diff>=13)
                                {
                                h=(mon+diff)-12;    
                                jLabel36.setText(date+"-"+h+"-"+(year+1)+" ");
                                jLabel40.setText(date+"-"+h+"-"+(year+1)+" ");
                                }
                                else
                                {
                                    jLabel36.setText(date+"-"+(mon+diff)+"-"+year+" ");
                                    jLabel40.setText(date+"-"+(mon+diff)+"-"+year+" ");
                                }
                            }
                            if(digit[1].equalsIgnoreCase("year"))
                            {
                                jLabel35.setText(date+"-"+mon+"-"+year+" ");
                                jLabel36.setText(date+"-"+mon+"-"+(year+diff)+" ");
                                jLabel39.setText(date+"-"+mon+"-"+year+" ");
                                jLabel40.setText(date+"-"+mon+"-"+(year+diff)+" ");
                            }
                            }
                            catch (SQLException ex) {
                                Logger.getLogger(user_temp.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                });
            }
            Country_Combo.removeAllItems();
           /* jLabel35.setText(" ");
            jLabel36.setText(" ");
            jLabel39.setText(" ");
            jLabel40.setText(" ");*/
            Country_Combo.addItem("Select Country");
            o_country.removeAllItems();
            o_country.addItem("Select Country");
            while (r1.next()) {
                Country_Combo.addItem(r1.getString("Country_Name"));
                o_country.addItem(r1.getString("Country_Name"));
            }
            ResultSet r12 = st12.executeQuery(v_type);
            type_panel.setLayout(new GridLayout(8, 1));
            while (r12.next()) {
                chk1 = new JRadioButton();
                chk1.setText(r12.getString("Vehicle_Type"));
                b1.add(chk1);
                chkl.add(chk1);
                type_panel.add(chk1);
                type_panel.revalidate();
                type_panel.repaint();
            }
            v_brand.removeAllItems();
            for (JRadioButton jradio1 : chkl) {
                jradio1.addActionListener(new ActionListener() {
                    
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        
                         if (jradio1.isSelected() == true) {
                            v_brand.removeAllItems();
                            vnm = jradio1.getText().toString();
                            System.out.println("selected vehicle name " + vnm);
                            vnmq = "select Vehicle_ID from vehicle_type where Vehicle_Type='" + vnm + "'";
                            try {
                                Connection con6 = connection.getConnect();
                                Statement st13 = con6.createStatement();
                                ResultSet r13 = st13.executeQuery(vnmq);
                                while (r13.next()) {
                                    vid = r13.getInt("Vehicle_ID");
                                    
                                }
                                System.out.println("vehicle id is"+vid);
                                bnmq = "select Brand_Name from vehicle_Brand where Vehicle_ID=" + vid + "";
                                ResultSet r14 = st14.executeQuery(bnmq);
                                while (r14.next()) {
                                    v_brand.addItem(r14.getString("Brand_Name"));
                                }
                            } catch (SQLException ex) {
                                Logger.getLogger(user_temp.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (ClassNotFoundException ex) {
                                Logger.getLogger(user_temp.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                });
                 Vehicle_Panel.revalidate();
                Vehicle_Panel.repaint();
                card.setLayout(new GridLayout(4,1));
            while(r41.next())
            {
                    cardr = new JRadioButton();
                    cardr.setText(r41.getString("Card_Name"));
                    b5.add(cardr);
                    cardrl.add(cardr);
                    card.add(cardr);
                    card.revalidate();
                    card.repaint();
            }
        }
        }
        catch (SQLException ex) {
            Logger.getLogger(user_temp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(user_temp.class.getName()).log(Level.SEVERE, null, ex);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        Personal_Panel = new javax.swing.JPanel();
        driver_name = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        d_txt = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        address = new javax.swing.JTextArea();
        Country_Combo = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        state = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        district = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        mob = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        mail = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tel = new javax.swing.JTextField();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel22 = new javax.swing.JLabel();
        city = new javax.swing.JComboBox();
        jLabel21 = new javax.swing.JLabel();
        jRadioButton5 = new javax.swing.JRadioButton();
        jRadioButton6 = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        Owner_Panel = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        o_namet = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        o_addt = new javax.swing.JTextArea();
        jLabel18 = new javax.swing.JLabel();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jLabel19 = new javax.swing.JLabel();
        o_mailt = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        telly = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        o_country = new javax.swing.JComboBox();
        jLabel23 = new javax.swing.JLabel();
        o_state = new javax.swing.JComboBox();
        jLabel24 = new javax.swing.JLabel();
        o_cityt = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        Vehicle_Panel = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        v_brand = new javax.swing.JComboBox();
        v_name = new javax.swing.JComboBox();
        v_model = new javax.swing.JComboBox();
        type_panel = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        verify_panel = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        Source_Panel = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        source = new javax.swing.JComboBox();
        dest = new javax.swing.JComboBox();
        month = new javax.swing.JComboBox();
        day = new javax.swing.JComboBox();
        jRadioButton7 = new javax.swing.JRadioButton();
        jRadioButton8 = new javax.swing.JRadioButton();
        jPanel8 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        No_Doc_Panel = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        driver = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        owner = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        card = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        button_panel1 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        Yes_Doc_Panel = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        dopanel = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        button_panel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Personal_Panel.setBackground(new java.awt.Color(255, 255, 255));

        driver_name.setText("Driver Name : ");

        jLabel2.setText("Gender : ");

        jLabel3.setText("Address : ");

        jLabel4.setText("District : ");

        address.setColumns(20);
        address.setRows(5);
        jScrollPane1.setViewportView(address);

        Country_Combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        Country_Combo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Country_ComboItemStateChanged(evt);
            }
        });
        Country_Combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Country_ComboActionPerformed(evt);
            }
        });

        jLabel1.setText("State : ");

        state.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        state.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stateActionPerformed(evt);
            }
        });

        jLabel5.setText("Country : ");

        district.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        district.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                districtActionPerformed(evt);
            }
        });

        jLabel6.setText("Mobile Number : ");

        mob.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mobActionPerformed(evt);
            }
        });

        jLabel7.setText("E-Mail : ");

        jLabel8.setText("Telephone :");

        buttonGroup2.add(jRadioButton1);
        jRadioButton1.setText("Male");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        buttonGroup2.add(jRadioButton2);
        jRadioButton2.setText("Female");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jLabel22.setText("City :");

        city.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        city.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cityActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Personal_PanelLayout = new javax.swing.GroupLayout(Personal_Panel);
        Personal_Panel.setLayout(Personal_PanelLayout);
        Personal_PanelLayout.setHorizontalGroup(
            Personal_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Personal_PanelLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(Personal_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel1)
                    .addComponent(jLabel22)
                    .addGroup(Personal_PanelLayout.createSequentialGroup()
                        .addGroup(Personal_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(driver_name))
                        .addGap(54, 54, 54)
                        .addGroup(Personal_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Personal_PanelLayout.createSequentialGroup()
                                .addComponent(jRadioButton1)
                                .addGap(72, 72, 72)
                                .addComponent(jRadioButton2))
                            .addComponent(d_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(Personal_PanelLayout.createSequentialGroup()
                        .addGroup(Personal_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel4))
                        .addGap(51, 51, 51)
                        .addGroup(Personal_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(district, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Country_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(state, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(city, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mob, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(Personal_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(tel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                                .addComponent(mail, javax.swing.GroupLayout.Alignment.LEADING)))))
                .addContainerGap(274, Short.MAX_VALUE))
        );
        Personal_PanelLayout.setVerticalGroup(
            Personal_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Personal_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Personal_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(driver_name)
                    .addComponent(d_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(Personal_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2))
                .addGap(18, 18, 18)
                .addGroup(Personal_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(Personal_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Personal_PanelLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel5))
                    .addGroup(Personal_PanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(Country_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(Personal_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Personal_PanelLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel1)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel22))
                    .addGroup(Personal_PanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(state, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(city, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22)
                .addGroup(Personal_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(district, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(Personal_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(mob, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(Personal_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(Personal_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(tel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54))
        );

        jLabel21.setText("Are you driver With Owner?");

        buttonGroup1.add(jRadioButton5);
        jRadioButton5.setText("Yes");
        jRadioButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton5ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton6);
        jRadioButton6.setText("No");
        jRadioButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(jLabel21)
                        .addGap(107, 107, 107)
                        .addComponent(jRadioButton5)
                        .addGap(85, 85, 85)
                        .addComponent(jRadioButton6))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(Personal_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(111, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jRadioButton5)
                    .addComponent(jRadioButton6))
                .addGap(59, 59, 59)
                .addComponent(Personal_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(284, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Personal Detail", jPanel2);

        jLabel15.setText("Company Name/Person Name");

        jLabel16.setText("Address : ");

        o_addt.setColumns(20);
        o_addt.setRows(5);
        jScrollPane2.setViewportView(o_addt);

        jLabel18.setText("Gender(If Person is owner) : ");

        buttonGroup3.add(jRadioButton3);
        jRadioButton3.setText("Male");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        buttonGroup3.add(jRadioButton4);
        jRadioButton4.setText("Female");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });

        jLabel19.setText("Email : ");

        jLabel20.setText("Telephone");

        jLabel17.setText("Country");

        o_country.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        o_country.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                o_countryActionPerformed(evt);
            }
        });

        jLabel23.setText("State");

        o_state.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        o_state.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                o_stateActionPerformed(evt);
            }
        });

        jLabel24.setText("City");

        o_cityt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout Owner_PanelLayout = new javax.swing.GroupLayout(Owner_Panel);
        Owner_Panel.setLayout(Owner_PanelLayout);
        Owner_PanelLayout.setHorizontalGroup(
            Owner_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Owner_PanelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(Owner_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24)
                    .addGroup(Owner_PanelLayout.createSequentialGroup()
                        .addGroup(Owner_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel15)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19)
                            .addComponent(jLabel20)
                            .addComponent(jLabel17)
                            .addComponent(jLabel23))
                        .addGap(109, 109, 109)
                        .addGroup(Owner_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Owner_PanelLayout.createSequentialGroup()
                                .addComponent(jRadioButton3)
                                .addGap(48, 48, 48)
                                .addComponent(jRadioButton4))
                            .addComponent(o_state, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(o_country, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(telly, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(o_mailt, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(o_namet, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(o_cityt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Owner_PanelLayout.setVerticalGroup(
            Owner_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Owner_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Owner_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(o_namet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(Owner_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Owner_PanelLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel16))
                    .addGroup(Owner_PanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(Owner_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Owner_PanelLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel17))
                    .addGroup(Owner_PanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(o_country, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(Owner_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel23)
                    .addComponent(o_state, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(Owner_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(o_cityt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                .addGroup(Owner_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jRadioButton3)
                    .addComponent(jRadioButton4))
                .addGap(18, 18, 18)
                .addGroup(Owner_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(o_mailt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addGap(37, 37, 37)
                .addGroup(Owner_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(telly, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addGap(41, 41, 41))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 844, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(102, 102, 102)
                    .addComponent(Owner_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(102, 102, 102)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 908, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(79, 79, 79)
                    .addComponent(Owner_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(428, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Owner Detail", jPanel3);

        jLabel9.setText("Vehicle Type");

        jLabel10.setText("Vehicle Number");

        jLabel11.setText("Vehicle Brand");

        jLabel12.setText("Vehicle Name");

        jLabel13.setText("Vehicle Model Number");

        v_brand.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        v_brand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                v_brandActionPerformed(evt);
            }
        });

        v_name.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        v_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                v_nameActionPerformed(evt);
            }
        });

        v_model.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        v_model.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                v_modelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout type_panelLayout = new javax.swing.GroupLayout(type_panel);
        type_panel.setLayout(type_panelLayout);
        type_panelLayout.setHorizontalGroup(
            type_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 145, Short.MAX_VALUE)
        );
        type_panelLayout.setVerticalGroup(
            type_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 37, Short.MAX_VALUE)
        );

        jLabel26.setText("Verification for originality and no crime");

        javax.swing.GroupLayout verify_panelLayout = new javax.swing.GroupLayout(verify_panel);
        verify_panel.setLayout(verify_panelLayout);
        verify_panelLayout.setHorizontalGroup(
            verify_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 230, Short.MAX_VALUE)
        );
        verify_panelLayout.setVerticalGroup(
            verify_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 66, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout Vehicle_PanelLayout = new javax.swing.GroupLayout(Vehicle_Panel);
        Vehicle_Panel.setLayout(Vehicle_PanelLayout);
        Vehicle_PanelLayout.setHorizontalGroup(
            Vehicle_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Vehicle_PanelLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(Vehicle_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel26))
                .addGap(97, 97, 97)
                .addGroup(Vehicle_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(verify_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(v_name, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(v_model, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(type_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(v_brand, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        Vehicle_PanelLayout.setVerticalGroup(
            Vehicle_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Vehicle_PanelLayout.createSequentialGroup()
                .addGroup(Vehicle_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Vehicle_PanelLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel9))
                    .addGroup(Vehicle_PanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(type_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(34, 34, 34)
                .addGroup(Vehicle_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(Vehicle_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(v_brand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(23, 23, 23)
                .addGroup(Vehicle_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(v_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Vehicle_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(v_model, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGroup(Vehicle_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Vehicle_PanelLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel26))
                    .addGroup(Vehicle_PanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(verify_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addComponent(Vehicle_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(142, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(194, 194, 194)
                .addComponent(Vehicle_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(406, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Vehicle Detail", jPanel1);

        Source_Panel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel14.setText("Source City");

        jLabel25.setText("Destination City");

        source.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        source.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sourceActionPerformed(evt);
            }
        });

        dest.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        month.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        month.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monthActionPerformed(evt);
            }
        });

        day.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        day.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dayActionPerformed(evt);
            }
        });

        buttonGroup4.add(jRadioButton7);
        jRadioButton7.setText("How many times per month?");
        jRadioButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton7ActionPerformed(evt);
            }
        });

        buttonGroup4.add(jRadioButton8);
        jRadioButton8.setText("How many times per day?");
        jRadioButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Source_PanelLayout = new javax.swing.GroupLayout(Source_Panel);
        Source_Panel.setLayout(Source_PanelLayout);
        Source_PanelLayout.setHorizontalGroup(
            Source_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Source_PanelLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(Source_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Source_PanelLayout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Source_PanelLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(source, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Source_PanelLayout.createSequentialGroup()
                        .addComponent(jRadioButton8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(day, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Source_PanelLayout.createSequentialGroup()
                        .addComponent(jRadioButton7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                        .addComponent(month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(284, 284, 284))
        );
        Source_PanelLayout.setVerticalGroup(
            Source_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Source_PanelLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(Source_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14)
                    .addComponent(source, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(Source_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(dest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(Source_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton7))
                .addGap(28, 28, 28)
                .addGroup(Source_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(day, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton8))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addComponent(Source_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(102, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(175, 175, 175)
                .addComponent(Source_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(501, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Travelling Detail", jPanel4);

        jLabel27.setText("Driver");

        driver.setBorder(new javax.swing.border.MatteBorder(null));

        javax.swing.GroupLayout driverLayout = new javax.swing.GroupLayout(driver);
        driver.setLayout(driverLayout);
        driverLayout.setHorizontalGroup(
            driverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 406, Short.MAX_VALUE)
        );
        driverLayout.setVerticalGroup(
            driverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 138, Short.MAX_VALUE)
        );

        jLabel28.setText("Owner");

        owner.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout ownerLayout = new javax.swing.GroupLayout(owner);
        owner.setLayout(ownerLayout);
        ownerLayout.setHorizontalGroup(
            ownerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 236, Short.MAX_VALUE)
        );
        ownerLayout.setVerticalGroup(
            ownerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 83, Short.MAX_VALUE)
        );

        jLabel30.setText("Which card will you prefer?");

        card.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel37.setText("Issue Date");

        jLabel38.setText("Expire Date");

        jLabel39.setBackground(new java.awt.Color(255, 102, 102));
        jLabel39.setText("jLabel39");

        jLabel40.setBackground(new java.awt.Color(255, 102, 0));
        jLabel40.setText("jLabel40");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel40))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel37)
                        .addGap(78, 78, 78)
                        .addComponent(jLabel39)))
                .addContainerGap(81, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(jLabel39))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(jLabel40))
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout cardLayout = new javax.swing.GroupLayout(card);
        card.setLayout(cardLayout);
        cardLayout.setHorizontalGroup(
            cardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(233, Short.MAX_VALUE))
        );
        cardLayout.setVerticalGroup(
            cardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(159, Short.MAX_VALUE))
        );

        jButton3.setText("Submit");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Reset");

        javax.swing.GroupLayout button_panel1Layout = new javax.swing.GroupLayout(button_panel1);
        button_panel1.setLayout(button_panel1Layout);
        button_panel1Layout.setHorizontalGroup(
            button_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(button_panel1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addGap(70, 70, 70))
        );
        button_panel1Layout.setVerticalGroup(
            button_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(button_panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(button_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout No_Doc_PanelLayout = new javax.swing.GroupLayout(No_Doc_Panel);
        No_Doc_Panel.setLayout(No_Doc_PanelLayout);
        No_Doc_PanelLayout.setHorizontalGroup(
            No_Doc_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(No_Doc_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(No_Doc_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, No_Doc_PanelLayout.createSequentialGroup()
                        .addGroup(No_Doc_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28))
                        .addGap(49, 49, 49)
                        .addGroup(No_Doc_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(driver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(No_Doc_PanelLayout.createSequentialGroup()
                                .addComponent(owner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(57, 57, 57)
                                .addComponent(button_panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, No_Doc_PanelLayout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addGap(31, 31, 31)
                        .addComponent(card, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(133, Short.MAX_VALUE))
        );
        No_Doc_PanelLayout.setVerticalGroup(
            No_Doc_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, No_Doc_PanelLayout.createSequentialGroup()
                .addGroup(No_Doc_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(No_Doc_PanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(driver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(33, 33, 33)
                .addGroup(No_Doc_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(owner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button_panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(No_Doc_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel30)
                    .addComponent(card, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(267, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(No_Doc_Panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(No_Doc_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 844, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 908, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Document_Nedded(n)", jPanel8);

        Yes_Doc_Panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Document Nedded"));

        jLabel29.setText("Driver With Owner");

        dopanel.setBorder(new javax.swing.border.MatteBorder(null));

        javax.swing.GroupLayout dopanelLayout = new javax.swing.GroupLayout(dopanel);
        dopanel.setLayout(dopanelLayout);
        dopanelLayout.setHorizontalGroup(
            dopanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );
        dopanelLayout.setVerticalGroup(
            dopanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 115, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout Yes_Doc_PanelLayout = new javax.swing.GroupLayout(Yes_Doc_Panel);
        Yes_Doc_Panel.setLayout(Yes_Doc_PanelLayout);
        Yes_Doc_PanelLayout.setHorizontalGroup(
            Yes_Doc_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Yes_Doc_PanelLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel29)
                .addGap(62, 62, 62)
                .addComponent(dopanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Yes_Doc_PanelLayout.setVerticalGroup(
            Yes_Doc_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Yes_Doc_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel29)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(Yes_Doc_PanelLayout.createSequentialGroup()
                .addComponent(dopanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 46, Short.MAX_VALUE))
        );

        jLabel31.setText("Which card will you prefer?");

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel32.setText("Issue Date");

        jLabel33.setText("Expire Date");

        jLabel35.setBackground(new java.awt.Color(255, 102, 102));
        jLabel35.setText("jLabel35");

        jLabel36.setBackground(new java.awt.Color(255, 102, 0));
        jLabel36.setText("jLabel36");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel36))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel32)
                        .addGap(78, 78, 78)
                        .addComponent(jLabel35)))
                .addContainerGap(81, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(jLabel35))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(jLabel36))
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(176, 176, 176)
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(188, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel34)
                        .addGap(87, 87, 87))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))))
        );

        jButton1.setText("Submit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Reset");

        javax.swing.GroupLayout button_panelLayout = new javax.swing.GroupLayout(button_panel);
        button_panel.setLayout(button_panelLayout);
        button_panelLayout.setHorizontalGroup(
            button_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(button_panelLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(70, 70, 70))
        );
        button_panelLayout.setVerticalGroup(
            button_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(button_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(button_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Yes_Doc_Panel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addContainerGap(105, Short.MAX_VALUE)
                                .addComponent(jLabel31)
                                .addGap(68, 68, 68))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(button_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(57, 57, 57))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(Yes_Doc_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel31)
                        .addGap(158, 158, 158)
                        .addComponent(button_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(259, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 844, Short.MAX_VALUE)
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 908, Short.MAX_VALUE)
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Document_Needed(y)", jPanel9);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(171, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 936, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton8ActionPerformed
        // TODO add your handling code here:
        for (Enumeration<AbstractButton> os = buttonGroup4.getElements(); os.hasMoreElements();) {
            AbstractButton on = os.nextElement();
            if (on.isSelected()) {
                flg=2;

            }
        }
    }//GEN-LAST:event_jRadioButton8ActionPerformed

    private void jRadioButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton7ActionPerformed
        // TODO add your handling code here:
        //montth
        for (Enumeration<AbstractButton> so = buttonGroup4.getElements(); so.hasMoreElements();) {
            AbstractButton no = so.nextElement();
            if (no.isSelected()) {

                flg=1;
            }
        }
    }//GEN-LAST:event_jRadioButton7ActionPerformed

    private void dayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dayActionPerformed
        // TODO add your handling code here:
        /*        try
        {
            Connection con10=connection.getConnect();
            Statement st36=con10.createStatement();
            String dayi="";
            String dayq="select Day_ID from day_duration where duration='" + dayi + "'";
            if (flg==2) {
                dayi=toString().valueOf(day.getSelectedItem());
                ResultSet r36=st36.executeQuery(dayq);
                while(r36.next())
                {
                    d_id=r36.getInt("Day_ID");
                    System.out.println("day id is "+d_id);
                }
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(tab_user_form.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(tab_user_form.class.getName()).log(Level.SEVERE, null, ex);
        }//9913382323 9925103667*/
    }//GEN-LAST:event_dayActionPerformed

    private void monthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_monthActionPerformed

    private void sourceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sourceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sourceActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        try {//try start
            String type = "";
            String verify = "";
            String sourcet="";
            String destt="";
            String o_cty="";
            String monthi="";
            String driver_doc="";
            String cnm="";
            String y_doc="";
            String d_insert="";
            String y_insert="";
            if(!telly.equals(null))
            {
                // o_mobile=Integer.parseInt(telly.getText());//can error in this
                o_mobile=999;
            }
            mobile=toString().valueOf(mob.getText());
            tele=toString().valueOf(tel.getText());
            System.out.println("telephone is"+tele);
            String dist_nm =toString().valueOf( district.getSelectedItem());
            String model =toString().valueOf( v_model.getSelectedItem());
            sourcet=toString().valueOf(source.getSelectedItem());
            System.out.println("Source table "+sourcet);
            destt=toString().valueOf(dest.getSelectedItem());
            System.out.println("Dest table "+destt);
            String dayi=toString().valueOf(day.getSelectedItem());
            System.out.println("selected day is"+dayi);
            monthi=toString().valueOf(month.getSelectedItem());
            System.out.println("selected month is "+monthi);
            o_name=toString().valueOf(o_namet.getText());
            o_add=toString().valueOf(o_addt.getText());
            o_email=toString().valueOf(o_mailt.getText());
            o_cty=toString().valueOf(o_cityt.getSelectedItem());
            v_number=jTextField8.getText();
            Connection con8 = connection.getConnect();
            Statement st26 = con8.createStatement();
            Statement st27 = con8.createStatement();
            Statement st28 = con8.createStatement();
            Statement st29 = con8.createStatement();
            Statement st30 = con8.createStatement();
            Statement st31 = con8.createStatement();
            Statement st32 = con8.createStatement();
            Statement st33 = con8.createStatement();
            Statement st34 = con8.createStatement();
            Statement st35 = con8.createStatement();
            Statement st36 = con8.createStatement();
            Statement st40 = con8.createStatement();
            Statement st41 = con8.createStatement();
            Statement st42 = con8.createStatement();
            Statement st43 = con8.createStatement();
            Statement st44 = con8.createStatement();
            Statement st90 = con8.createStatement();
            Statement st91 = con8.createStatement();
            String vtypeq = "select Vehicle_ID from vehicle_type where Vehicle_Type='" + type + "'";
            String distq = "select District_ID from district where District_Name='" + dist_nm + "'";
            String numq = "select Vm_Number_ID from model_number where Model_Number='" + model + "'";
           // String vq = "select Verify_ID from verification where Reason='" + verify + "'";
            String sourceq="select City_ID from city where City_Name='" + sourcet + "'";
            String destq="select City_ID from city where City_Name='" + destt + "'";
            String dayq="select Day_ID from day_duration where days='" + dayi + "'";
            String o_cityq="select City_ID from city where City_Name='" + o_cty + "'";
            String monthq="select Month_ID from month_duration where times='" + monthi + "'";
            /*String insertq="insert into user_db values(1,gender,dist_id,mobile,tele,v_type_id,v_number,v_model_id,flag,verify_id,source_id,dest_id,m_id,day_id,8,doc_id1)";
            st43.execute(insertq);
            System.out.println("insert fire");*/
            int doc_id1=3,verify_id=0;
            String docq="",docq1="";
            ResultSet r30=st30.executeQuery(sourceq);
            ResultSet r31=st31.executeQuery(destq);
            ResultSet r33=st33.executeQuery(dayq);
            ResultSet r34=st34.executeQuery(o_cityq);
            d_name = d_txt.getText();
            email=mail.getText();
            add = address.getText();
            /*if(!mobi.equals(null) && tl.equals(null))
            {
                mobile = Integer.parseInt(mobi);
                tele = Integer.parseInt(tl);
            }*/
            ResultSet r27 = st27.executeQuery(distq);
            while (r27.next()) {
                dist_id = r27.getInt("District_ID");
            }
            ResultSet r28 = st28.executeQuery(numq);
            while (r28.next()) {
                v_model_id = r28.getInt("Vm_Number_ID");
            }
            //month
          /*  if (flg==1) {
                ResultSet r32=st32.executeQuery(monthq);
                while(r32.next())
                {
                    m_id=r32.getInt("Month_ID");
                    System.out.println("Month id is "+m_id);
                }
                d_id=5;
            }
             if(flg==2)
            {
                ResultSet r35=st35.executeQuery(dayq);
                while(r35.next())
                {
                    d_id=r35.getInt("Day_ID");
                    System.out.println("Day id is "+d_id);
                }
                m_id=4;
            }*/
            //city id
            while(r34.next())
            {
                o_city_id=r34.getInt("City_ID");
            }
            //source
            while(r30.next())
            {
                source_id=r30.getInt("City_ID");
                //% System.out.println("selected source city is "+source_id);
            }
            //dest
            while(r31.next())
            {
                dest_id=r31.getInt("City_ID");
                //System.out.println("selected dest city is "+dest_id);
            }

            //day
            while(r33.next())
            {
                d_id=r33.getInt("Day_ID");
            }
            //document
            if(temp==1)
            {
                for (JCheckBox dchk : d_chkl)
                {
                    //  System.out.println("selected documents are "+dchk.getText());
                    if (dchk.isSelected() == true) {

                        driver_doc=dchk.getText();
                        docq="select Doc_ID from documents where Doc_Name='" + driver_doc + "'";

                        ResultSet r40=st40.executeQuery(docq);
                        //  System.out.println("selected documents driver are "+dchk.getText());
                        while(r40.next())
                        {
                            System.out.println("driver id "+r40.getInt("Doc_ID"));
                            docstr=docstr+r40.getInt("Doc_ID")+",";
                            temp=2;
                        }

                    }

                }
                String owner_dc="";
                for (JCheckBox ochk : o_chkl)
                {

                    if (ochk.isSelected() == true) {
                        System.out.println("selected documents owner are "+ochk.getText());
                        owner_dc=ochk.getText();
                        docq1="select Doc_ID from documents where Doc_Name='" + owner_dc + "'";
                        ResultSet r41=st41.executeQuery(docq1);
                        //System.out.println("selected documents driver are "+ochk.getText());
                        while(r41.next())
                        {
                            System.out.println("owner id "+r41.getInt("Doc_ID"));
                            owner_doc=owner_doc+r41.getInt("Doc_ID")+",";
                            System.out.println("owner doc are "+owner_doc);
                            temp=2;
                        }
                    }
                }
                tr=docstr.substring(0,docstr.length()-1);
                o_tr=owner_doc.substring(0,owner_doc.length()-1);
                System.out.println("driver array "+tr);
                System.out.println("Owner array "+o_tr);
            }//temp 1 over
            String ye_doc="";
            String docq2="";
            if(temp==2)
            {
                for (JCheckBox ychk : ychkl)
                {

                    if (ychk.isSelected() == true) {
                        //System.out.println("selected documents owner are "+ochk.getText());
                        ye_doc=ychk.getText();
                        docq2="select Doc_ID from documents where Doc_Name='" + ye_doc + "'";

                        ResultSet r36=st36.executeQuery(docq2);
                        //System.out.println("selected documents driver are "+ochk.getText());
                        while(r36.next())
                        {
                            System.out.println("owner id "+r36.getInt("Doc_ID"));
                            yes_doc=yes_doc+r36.getInt("Doc_ID")+",";
                            temp=4;
                        }
                    }
                }

                               y_tr=yes_doc.substring(0,yes_doc.length()-1);
                             System.out.println("Ownere+driver array "+y_tr);
            }//temp 2

            //CARD_TYPE
            /*  String ctypeq="";
            for (Enumeration<AbstractButton> btn2 = b5.getElements(); btn2.hasMoreElements();) {
                AbstractButton butn2 = btn2.nextElement();
                if (butn2.isSelected()) {
                    cnm = butn2.getText();
                    System.out.println("selected radio is for card is  " + cnm);
                    ctypeq="select Card_ID from card_type where Card_Name='" + cnm + "'";
                    ResultSet r42 = st42.executeQuery(ctypeq);
                    while (r42.next()) {
                        card_id = r42.getInt("Card_ID");
                        System.out.println("Card ID " + card_id);
                    }
                }
            }*/
            //for verification
            for (Enumeration<AbstractButton> btn = b2.getElements(); btn.hasMoreElements();) {
                AbstractButton butn = btn.nextElement();
                if (butn.isSelected()) {
                    verify = butn.getText();
                    System.out.println("selected radio is for verificaton is  "+verify);
                   String vq = "select Verify_ID from verification where Reason='" + verify + "'";
                    ResultSet r29 = st29.executeQuery(vq);
                    while (r29.next()) {
                        verify_id = r29.getInt("Verify_ID");
                        System.out.println("Verify ID " + verify_id);
                    }
                }
            }
            //for v_type
            for (Enumeration<AbstractButton> btns = b1.getElements(); btns.hasMoreElements();) {
                AbstractButton btn = btns.nextElement();
                if (btn.isSelected()) {
                    System.out.println("Selected vehicle is " + btn.getText());
                    type = btn.getText();

                    ResultSet r26 = st26.executeQuery(vtypeq);
                    while (r26.next()) {
                        v_type_id = r26.getInt("Vehicle_ID");
                        System.out.println("Selected vehicle id is"+v_type_id);
                    }
                }
            }
            //for month and day
            for (Enumeration<AbstractButton> btns1 = buttonGroup4.getElements(); btns1.hasMoreElements();) {
                AbstractButton btn1 = btns1.nextElement();
                if (btn1.isSelected()) {
                    System.out.println("Selected radio is for button4  " + btn1.getText());
                    type1 = btn1.getText();
                    System.out.println("type 1 is"+type1);
                    if(type1.equals("How many times per month?"))
                    {
                        System.out.println("Selected is month"+type1);
                        flg=1;
                    }
                      if(type1.equals("How many times per day?"))
                    {
                        System.out.println("Selected is day"+type1);
                        flg=2;
                    }
                  if (flg==1) {
                ResultSet r32=st32.executeQuery(monthq);
                while(r32.next())
                {
                    m_id=r32.getInt("Month_ID");
                    System.out.println("Month id is "+m_id);
                    d_id=5;
                }
                
            }
             if(flg==2)
            {
                ResultSet r35=st35.executeQuery(dayq);
                while(r35.next())
                {
                    d_id=r35.getInt("Day_ID");
                    System.out.println("Day id is "+d_id);
                     m_id=4;
                }
               
            }   
                   
                }
            }

            //adding document
            if(flag=="N")
            {
                
                m_id=2;
                d_id=4;
                System.out.println("source id is "+source_id+"destination id is"+dest_id+"vehicle id is"+vid);
                 qry="select id from dumy where Source_ID='"+source_id+"' and Dest_ID='"+dest_id+"' and Vehicle_ID='"+vid+"'";
                 ResultSet r90=st90.executeQuery(qry);
                 while(r90.next())
                 {
                     price_id=r90.getInt("id");
                     
                 }
                 
                o_insert="insert into user_owner(Address,Gender,Email,Mobile,Name,Doc_ID,City_ID) values('"+o_add+"','"+o_gender+"','"+o_email+"','"+o_mobile+"','"+o_name+"','"+o_tr+"','"+o_city_id+"')";
                 st42.execute(o_insert,Statement.RETURN_GENERATED_KEYS);
                 ResultSet rd=st42.getGeneratedKeys();
                 while(rd.next())
                 {
                     l_id=rd.getLong(1);
                     System.out.println("last owner id is "+l_id);
                 }
                st42.execute(o_insert);
                 i_date=jLabel39.getText();
                 e_date=jLabel40.getText();
                 System.out.println("price id is"+price_id);
                 d_insert="insert into user_db(Gender,District_ID,Mobile,Telephone,Vehicle_ID,Vehicle_Number,Model_Number_ID,Selected_Option,Verify_ID,Source_ID,Dest_ID,Month_ID,Day_ID,Doc_ID,Card_ID,Name,Address,Email,Owner_ID,Issue_Date,Expire_Date,Price_ID) values('"+gender+"',"+dist_id+","+mobile+","+tele+","+vid+",'"+v_number+"',"+v_model_id+",'"+flag+"',"+verify_id+","+source_id+","+dest_id+","+m_id+","+d_id+",'"+tr+"',"+card_id+",'"+d_name+"','"+add+"','"+email+"',"+l_id+",'"+i_date+"','"+e_date+"','"+price_id+"')";               
                
                st43.execute(d_insert);
                System.out.println("No selected query fire hell");
            }
            if(flag=="Y")
            {
               /* m_id=4;
                d_id=5;*/
                i_date=jLabel35.getText();
                e_date=jLabel36.getText();
                 System.out.println("source id is "+source_id+"destination id is"+dest_id+"vehicle id is"+vid);
                 qry1="select id from dumy where Source_ID='"+source_id+"' and Dest_ID='"+dest_id+"' and Vehicle_ID='"+vid+"'";
                 ResultSet r91=st91.executeQuery(qry1);
                 while(r91.next())
                 {
                     price_id=r91.getInt("id");
                     System.out.println("price id is"+price_id);
                 }
                
                y_insert="insert into user_db(Gender,District_ID,Mobile,Telephone,Vehicle_ID,Vehicle_Number,Model_Number_ID,Selected_Option,Verify_ID,Source_ID,Dest_ID,Month_ID,Day_ID,Doc_ID,Card_ID,Name,Address,Email,Owner_ID,Issue_Date,Expire_Date,Price_ID) values('"+gender+"',"+dist_id+","+mobile+","+tele+","+vid+",'"+v_number+"',"+v_model_id+",'"+flag+"',"+verify_id+","+source_id+","+dest_id+","+m_id+","+d_id+",'"+y_tr+"',"+card_id+",'"+d_name+"','"+add+"','"+email+"',"+l_id+",'"+i_date+"','"+e_date+"','"+price_id+"')";
                st44.execute(y_insert);
                System.out.println("Yes selected query fire");

            }
            o_tr="";
            tr="";
            docstr="";
            y_tr="";
            jLabel35.setText("");
            jLabel36.setText("");
            jLabel39.setText("");
            jLabel40.setText("");
        }
        catch (SQLException ex) {
            Logger.getLogger(user_temp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(user_temp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void v_modelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_v_modelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_v_modelActionPerformed

    private void v_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_v_nameActionPerformed
        // TODO add your handling code here:
        String nm9 = toString().valueOf(v_name.getSelectedItem());
        System.out.println("selected vehicle Name Is "+nm9);
        int id8 = 0;
        String nameidq = "select V_Name_ID from vehicle_name where Vehicle_Name='" + nm9 + "'";
        String numberq;
        try {
            Connection con7 = connection.getConnect();
            Statement st14 = con7.createStatement();
            Statement st15 = con7.createStatement();
            ResultSet r14 = st14.executeQuery(nameidq);
            while (r14.next()) {
                id8 = r14.getInt("V_Name_ID");
                System.out.println("selected name is id" + id8);
            }
            numberq = "select Model_Number from model_number where vname_id=" + id8 + "";
            ResultSet r15 = st15.executeQuery(numberq);
            v_model.removeAllItems();
            while (r15.next()) {
                System.out.println("Model Number is " + r15.getString("Model_Number"));
                v_model.addItem(r15.getString("Model_Number"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(tab_user_form.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(tab_user_form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_v_nameActionPerformed

    private void v_brandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_v_brandActionPerformed
        // TODO add your handling code here:
        String nm2 = toString().valueOf(v_brand.getSelectedItem());
        int id4 = 0;
        String nameq;
        String brandidq = "select Brand_ID from vehicle_brand where Brand_Name='" + nm2 + "'";
        try {
            Connection con6 = connection.getConnect();
            Statement st12 = con6.createStatement();
            Statement st13 = con6.createStatement();
            ResultSet r12 = st12.executeQuery(brandidq);
            while (r12.next()) {
                id4 = r12.getInt("Brand_ID");
                System.out.println("Brand id is " + id4);
            }
            nameq = "select Vehicle_Name from vehicle_name where Brand_ID=" + id4 + "";
            ResultSet r13 = st13.executeQuery(nameq);
            v_name.removeAllItems();
            while (r13.next()) {
                v_name.addItem(r13.getString("Vehicle_Name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(tab_user_form.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(tab_user_form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_v_brandActionPerformed

    private void o_stateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_o_stateActionPerformed
        // TODO add your handling code here:
        String nmo1 = String.valueOf(o_state.getSelectedItem());
        int ido1 = 0;
        String ostateq = "select State_ID from state where State_Name='" + nmo1 + "'";
        String cityoq;
        try {
            Connection con5 = connection.getConnect();
            Statement st10 = con5.createStatement();
            Statement st11 = con5.createStatement();
            ResultSet r10 = st10.executeQuery(ostateq);
            while (r10.next()) {
                ido1 = r10.getInt("State_ID");
            }
            cityoq = "select City_Name from city where State_ID=" + ido1 + "";
            ResultSet r11 = st11.executeQuery(cityoq);
            o_cityt.removeAllItems();
            while (r11.next()) {
                o_cityt.addItem(r11.getString("City_Name"));
            }

            jPanel4.revalidate();
            jPanel4.repaint();

        } catch (SQLException ex) {
            Logger.getLogger(user_form.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(tab_user_form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_o_stateActionPerformed

    private void o_countryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_o_countryActionPerformed
        // TODO add your handling code here:
        String nmo = String.valueOf(o_country.getSelectedItem());
        String countryoidq = "select Country_ID from country where Country_Name='" + nmo + "'";
        String stateoq;
        int ido = 0;
        try {
            Connection con4 = connection.getConnect();
            Statement st8 = con4.createStatement();
            Statement st9 = con4.createStatement();
            ResultSet r8 = st8.executeQuery(countryoidq);
            while (r8.next()) {
                ido = r8.getInt("Country_ID");
            }
            stateoq = "select State_Name from state where Country_ID=" + ido + "";
            ResultSet r9 = st9.executeQuery(stateoq);
            o_state.removeAllItems();
            while (r9.next()) {
                o_state.addItem(r9.getString("State_Name"));
            }
            jPanel4.revalidate();
            jPanel4.repaint();

        } catch (SQLException ex) {
            Logger.getLogger(tab_user_form.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(tab_user_form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_o_countryActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        // TODO add your handling code here:
        for (Enumeration<AbstractButton> s = buttonGroup3.getElements(); s.hasMoreElements();) {
            AbstractButton n = s.nextElement();
            if (n.isSelected()) {

                o_gender = "Male";
            }
        }
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        // TODO add your handling code here:
        for (Enumeration<AbstractButton> s = buttonGroup3.getElements(); s.hasMoreElements();) {
            AbstractButton n = s.nextElement();
            if (n.isSelected()) {

                o_gender = "Male";
            }
        }
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jRadioButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton6ActionPerformed
        // TODO add your handling code here:
        for (Enumeration<AbstractButton> buttons = buttonGroup1.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                System.out.println("No selected");
                flag="N";
                jTabbedPane1.setEnabledAt(1,true); // onwer Panel show
                jTabbedPane1.setEnabledAt(3,true); // onwer Panel show
                jTabbedPane1.setEnabledAt(5,false); // No tab pane show-----------------------------
                jTabbedPane1.setEnabledAt(4,true); // Yes tab pane hide
                jTabbedPane1.revalidate();
                temp=1;
                jTabbedPane1.repaint();
                jLabel35.setText(" ");
                jLabel36.setText(" ");
                jLabel39.setText(" ");
                jLabel40.setText(" ");
            }
        }

    }//GEN-LAST:event_jRadioButton6ActionPerformed

    private void jRadioButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton5ActionPerformed
        // TODO add your handling code here:
        for (Enumeration<AbstractButton> buttons = buttonGroup1.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                System.out.println("Yes selected");
                flag="Y";
                temp=2;
                jTabbedPane1.setEnabledAt(1,false); // onwer Panel show
                jTabbedPane1.setEnabledAt(5,true); // No tab pane show
                jTabbedPane1.setEnabledAt(4,false); // Yes tab pane hide
                jLabel35.setText(" ");
                jLabel36.setText(" ");
                jLabel39.setText(" ");
                jLabel40.setText(" ");
                jTabbedPane1.revalidate();
                jTabbedPane1.repaint();
            }
        }

    }//GEN-LAST:event_jRadioButton5ActionPerformed

    private void cityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cityActionPerformed
        // TODO add your handling code here:
        String nm3 = String.valueOf(city.getSelectedItem());
        System.out.println("selected city is " + nm3);
        int id3 = 0;
        String cityidq = "select City_ID from city where City_Name='" + nm3 + "'";
        String distq;
        try {
            Connection con3 = connection.getConnect();
            Statement st6 = con3.createStatement();
            Statement st7 = con3.createStatement();
            ResultSet r6 = st6.executeQuery(cityidq);
            while (r6.next()) {
                id3 = r6.getInt("City_ID");
                System.out.println("City id is " + id3);
            }
            distq = "select District_Name from district where City_ID=" + id3 + "";
            ResultSet r7 = st7.executeQuery(distq);
            district.removeAllItems();
            while (r7.next()) {
                district.addItem(r7.getString("District_Name"));
            }
            jPanel4.revalidate();
            jPanel4.repaint();

        } catch (SQLException ex) {
            Logger.getLogger(user_form.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(tab_user_form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cityActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
        for (Enumeration<AbstractButton> buttons = buttonGroup2.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                gender="female";
            }
        }
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
        for (Enumeration<AbstractButton> buttons = buttonGroup2.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                gender="male";
            }
        }

    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void mobActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mobActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mobActionPerformed

    private void districtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_districtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_districtActionPerformed

    private void stateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stateActionPerformed
        // TODO add your handling code here:
        String nm1 = String.valueOf(state.getSelectedItem());
        int id1 = 0;
        String stateq = "select State_ID from state where State_Name='" + nm1 + "'";
        String cityq;
        try {
            Connection con2 = connection.getConnect();
            Statement st4 = con2.createStatement();
            Statement st5 = con2.createStatement();
            ResultSet r4 = st4.executeQuery(stateq);
            while (r4.next()) {
                id1 = r4.getInt("State_ID");
            }
            cityq = "select City_Name from city where State_ID=" + id1 + "";
            ResultSet r5 = st5.executeQuery(cityq);
            city.removeAllItems();
            while (r5.next()) {
                city.addItem(r5.getString("City_Name"));
            }
            jPanel4.revalidate();
            jPanel4.repaint();

        } catch (SQLException ex) {
            Logger.getLogger(user_form.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(tab_user_form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_stateActionPerformed

    private void Country_ComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Country_ComboActionPerformed
        // TODO add your handling code here:
        String nm = String.valueOf(Country_Combo.getSelectedItem());
        String countryidq = "select Country_ID from country where Country_Name='" + nm + "'";
        String stateq;
        int id = 0;
        try {
            Connection con2 = connection.getConnect();
            Statement st2 = con2.createStatement();
            Statement st3 = con2.createStatement();
            ResultSet r2 = st2.executeQuery(countryidq);
            while (r2.next()) {
                id = r2.getInt("Country_ID");
            }
            stateq = "select State_Name from state where Country_ID=" + id + "";
            ResultSet r3 = st3.executeQuery(stateq);
            state.removeAllItems();
            while (r3.next()) {
                System.out.println(r3.getString("State_Name"));
                state.addItem(r3.getString("State_Name"));
            }
            jPanel4.revalidate();
            jPanel4.repaint();

        } catch (SQLException ex) {
            Logger.getLogger(tab_user_form.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(tab_user_form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Country_ComboActionPerformed

    private void Country_ComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Country_ComboItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_Country_ComboItemStateChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try {//try start
            String type = "";
            String verify = "";
            String sourcet="";
            String destt="";
            String o_cty="";
            String monthi="";
            String driver_doc="";
            String d_insert="";
            String y_insert="";
            if(!telly.equals(null))
            {
                // o_mobile=Integer.parseInt(telly.getText());//can error in this
                o_mobile=999;
            }
            mobile=toString().valueOf(mob.getText());
            tele=toString().valueOf(tel.getText());
            System.out.println("telephone is "+tele);
            String dist_nm =toString().valueOf( district.getSelectedItem());
            String model =toString().valueOf( v_model.getSelectedItem());
            sourcet=toString().valueOf(source.getSelectedItem());
            
            destt=toString().valueOf(dest.getSelectedItem());
            
            String dayi=toString().valueOf(day.getSelectedItem());
            monthi=toString().valueOf(month.getSelectedItem());
            System.out.println("selected month name is "+monthi);
            System.out.println("selected day name is "+dayi);
            o_name=toString().valueOf(o_namet.getText());
            o_add=toString().valueOf(o_addt.getText());
            o_email=toString().valueOf(o_mailt.getText());
            o_cty=toString().valueOf(o_cityt.getSelectedItem());
            v_number=jTextField8.getText();
            Connection con8 = connection.getConnect();
            Statement st26 = con8.createStatement();
            Statement st27 = con8.createStatement();
            Statement st28 = con8.createStatement();
            Statement st29 = con8.createStatement();
            Statement st30 = con8.createStatement();
            Statement st31 = con8.createStatement();
            Statement st32 = con8.createStatement();
            Statement st33 = con8.createStatement();
            Statement st34 = con8.createStatement();
            Statement st35 = con8.createStatement();
            Statement st36 = con8.createStatement();
            Statement st40 = con8.createStatement();
            Statement st41 = con8.createStatement();
            Statement st42 = con8.createStatement();
            Statement st43 = con8.createStatement();
            Statement st44 = con8.createStatement();
            Statement st92 = con8.createStatement();
            Statement st93 = con8.createStatement();
            String vtypeq = "select Vehicle_ID from vehicle_type where Vehicle_Type='" + type + "'";
            String distq = "select District_ID from district where District_Name='" + dist_nm + "'";
            String numq = "select Vm_Number_ID from model_number where Model_Number='" + model + "'";
            
            String sourceq="select City_ID from city where City_Name='" + sourcet + "'";
            String destq="select City_ID from city where City_Name='" + destt + "'";
            String dayq="select Day_ID from day_duration where days='" + dayi + "'";
            String o_cityq="select City_ID from city where City_Name='" + o_cty + "'";
            String monthq="select Month_ID from month_duration where times='" + monthi + "'";
            /*String insertq="insert into user_db values(1,gender,dist_id,mobile,tele,v_type_id,v_number,v_model_id,flag,verify_id,source_id,dest_id,m_id,day_id,8,doc_id1)";
            st43.execute(insertq);
            System.out.println("insert fire");*/
            int doc_id1=3;
            String docq="",docq1="";
            ResultSet r30=st30.executeQuery(sourceq);
            ResultSet r31=st31.executeQuery(destq);
            ResultSet r33=st33.executeQuery(dayq);
            ResultSet r34=st34.executeQuery(o_cityq);
            d_name = d_txt.getText();
            email=mail.getText();
            add = address.getText();
            /*if(!mobi.equals(null) && tl.equals(null))
            {
                mobile = Integer.parseInt(mobi);
                tele = Integer.parseInt(tl);
            }*/
            ResultSet r27 = st27.executeQuery(distq);
            while (r27.next()) {
                dist_id = r27.getInt("District_ID");
            }
            ResultSet r28 = st28.executeQuery(numq);
            while (r28.next()) {
                v_model_id = r28.getInt("Vm_Number_ID");
            }
            //month
            if (flg==1) {
                ResultSet r32=st32.executeQuery(monthq);
                while(r32.next())
                {
                    m_id=r32.getInt("Month_ID");
                    System.out.println("Month id is "+m_id);
                }
            }
            else if(flg==2)
            {
                ResultSet r35=st35.executeQuery(dayq);
                while(r35.next())
                {
                    d_id=r35.getInt("Day_ID");
                    System.out.println("Day id is "+d_id);
                }
            }
            //city id
            while(r34.next())
            {
                o_city_id=r34.getInt("City_ID");
            }
            //source
            while(r30.next())
            {
                source_id=r30.getInt("City_ID");
                System.out.println("selected source city is "+source_id);
            }
            //dest
            while(r31.next())
            {
                dest_id=r31.getInt("City_ID");
                System.out.println("selected dest city is "+dest_id);
            }

            //day
            while(r33.next())
            {
                d_id=r33.getInt("Day_ID");
            }
            //document
            if(temp==1)
            {
                for (JCheckBox dchk : d_chkl)
                {
                    //  System.out.println("selected documents are "+dchk.getText());
                    if (dchk.isSelected() == true) {

                        driver_doc=dchk.getText();
                        docq="select Doc_ID from documents where Doc_Name='" + driver_doc + "'";

                        ResultSet r40=st40.executeQuery(docq);
                        //  System.out.println("selected documents driver are "+dchk.getText());
                        while(r40.next())
                        {
                            System.out.println("driver id "+r40.getInt("Doc_ID"));
                            docstr=docstr+r40.getInt("Doc_ID")+",";
                            temp=2;
                        }
                    }

                }
                String owner_dc="";
                for (JCheckBox ochk : o_chkl)
                {

                    if (ochk.isSelected() == true) {
                        System.out.println("selected documents owner are "+ochk.getText());
                        owner_dc=ochk.getText();
                        docq1="select Doc_ID from documents where Doc_Name='" + owner_dc + "'";
                        ResultSet r41=st41.executeQuery(docq1);
                        //System.out.println("selected documents driver are "+ochk.getText());
                        while(r41.next())
                        {
                            System.out.println("owner id "+r41.getInt("Doc_ID"));
                            owner_doc=owner_doc+r41.getInt("Doc_ID")+",";
                            System.out.println("owner doc are "+owner_doc);
                            temp=2;
                        }
                    }
                }
                tr=docstr.substring(0,docstr.length()-1);
                o_tr=owner_doc.substring(0,owner_doc.length()-1);
                System.out.println("driver array "+tr);
                System.out.println("Owner array "+o_tr);
            }//temp 1 over
            String ye_doc="";
            String docq2="";
            if(temp==2)
            {
                for (JCheckBox ychk : ychkl)
                {

                    if (ychk.isSelected() == true) {
                        //System.out.println("selected documents owner are "+ochk.getText());
                        ye_doc=ychk.getText();
                        docq2="select Doc_ID from documents where Doc_Name='" + ye_doc + "'";

                        ResultSet r36=st36.executeQuery(docq2);
                        //System.out.println("selected documents driver are "+ochk.getText());
                        while(r36.next())
                        {
                            System.out.println("owner id "+r36.getInt("Doc_ID"));
                            yes_doc=yes_doc+r36.getInt("Doc_ID")+",";
                            temp=4;
                        }
                    }
                }

                              // y_tr=yes_doc.substring(0,yes_doc.length()-1); //chnge
                             System.out.println("Ownere+driver array "+yes_doc);
            }//temp 2

            //CARD_TYPE
            /*  String ctypeq="";
            for (Enumeration<AbstractButton> btn2 = b5.getElements(); btn2.hasMoreElements();) {
                AbstractButton butn2 = btn2.nextElement();
                if (butn2.isSelected()) {
                    cnm = butn2.getText();
                    System.out.println("selected radio is for card is  " + cnm);
                    ctypeq="select Card_ID from card_type where Card_Name='" + cnm + "'";
                    ResultSet r42 = st42.executeQuery(ctypeq);
                    while (r42.next()) {
                        card_id = r42.getInt("Card_ID");
                        System.out.println("Card ID " + card_id);
                    }
                }
            }*/
            //for verification
            for (Enumeration<AbstractButton> btn = b2.getElements(); btn.hasMoreElements();) {
                AbstractButton butn = btn.nextElement();
                if (butn.isSelected()) {
                    verify = butn.getText();
                    System.out.println("selected radio is for verificaton is  "+verify);
                    String vq = "select Verify_ID from verification where Reason='" + verify + "'";
                    ResultSet r29 = st29.executeQuery(vq);
                    while (r29.next()) {
                        verify_id = r29.getInt("Verify_ID");
                        System.out.println("Verify ID " + verify_id);
                    }
                }
            }
           /*4444 //for v_type
            for (Enumeration<AbstractButton> btns = b1.getElements(); btns.hasMoreElements();) {
                AbstractButton btn = btns.nextElement();
                if (btn.isSelected()) {
                    System.out.println("Selected vehicle is " + btn.getText());
                    type = btn.getText();

                    ResultSet r26 = st26.executeQuery(vtypeq);
                    while (r26.next()) {
                        v_type_id = r26.getInt("Vehicle_ID");
                        System.out.println("Selected vehicle id is"+v_type_id);
                    }
                }
            }4444*/
            //for month and day
            for (Enumeration<AbstractButton> btns1 = buttonGroup4.getElements(); btns1.hasMoreElements();) {
                AbstractButton btn1 = btns1.nextElement();
                if (btn1.isSelected()) {
                    System.out.println("Selected radio is " + btn1.getText());
                    type1 = btn1.getText();
                    System.out.println("type 1 is"+type1);
                    if(type1.equals("How many times per month?"))
                    {
                        System.out.println("Selected is month"+type1);
                        flg=1;
                    }
                      if(type1.equals("How many times per day?"))
                    {
                        System.out.println("Selected is day"+type1);
                        flg=2;
                    }
                  if (flg==1) {
                ResultSet r32=st32.executeQuery(monthq);
                while(r32.next())
                {
                    m_id=r32.getInt("Month_ID");
                    System.out.println("Month id is "+m_id);
                    d_id=5;
                }
                
            }
             if(flg==2)
            {
                ResultSet r35=st35.executeQuery(dayq);
                while(r35.next())
                {
                    d_id=r35.getInt("Day_ID");
                    System.out.println("Day id is "+d_id);
                     m_id=4;
                }
               
            } 
                   
                }
            }

            //adding document
           if(flag=="N")
            {
                
                o_insert="insert into user_owner(Address,Gender,Email,Mobile,Name,Doc_ID,City_ID) values('"+o_add+"','"+o_gender+"','"+o_email+"','"+o_mobile+"','"+o_name+"','"+o_tr+"','"+o_city_id+"')";               
                 st42.execute(o_insert,Statement.RETURN_GENERATED_KEYS);
                 ResultSet rd=st42.getGeneratedKeys();
                 while(rd.next())
                 {
                     l_id=rd.getLong(1);
                     System.out.println("last owner id is "+l_id);
                 }
                //st42.execute(o_insert);
                 i_date=jLabel39.getText();
                 e_date=jLabel40.getText();
                 System.out.println("source id is "+source_id+"destination id is"+dest_id+"vehicle id is"+vid);
                 qry2="select id from dumy where Source_ID='"+source_id+"' and Dest_ID='"+dest_id+"' and Vehicle_ID='"+vid+"'";
                 ResultSet r92=st92.executeQuery(qry2);
                 while(r92.next())
                 {
                     price_id=r92.getInt("id");
                     System.out.println("price id is "+price_id);
                 }
                d_insert="insert into user_db(Gender,District_ID,Mobile,Telephone,Vehicle_ID,Vehicle_Number,Model_Number_ID,Selected_Option,Verify_ID,Source_ID,Dest_ID,Month_ID,Day_ID,Doc_ID,Card_ID,Name,Address,Email,Owner_ID,Issue_Date,Expire_Date,Price_ID) values('"+gender+"',"+dist_id+","+mobile+","+tele+","+vid+",'"+v_number+"',"+v_model_id+",'"+flag+"',"+verify_id+","+source_id+","+dest_id+","+m_id+","+d_id+",'"+tr+"',"+card_id+",'"+d_name+"','"+add+"','"+email+"',"+l_id+",'"+i_date+"','"+e_date+"','"+price_id+"')";               
                st43.execute(d_insert);
                System.out.println("No selected query fire jbtn 8");
            }
            if(flag=="Y")
            {
                m_id=2;
                d_id=4;
                i_date=jLabel35.getText();
                e_date=jLabel36.getText();
                 System.out.println("source id is "+source_id+"destination id is"+dest_id+"vehicle id is"+vid);
                 qry3="select id from dumy where Source_ID='"+source_id+"' and Dest_ID='"+dest_id+"' and Vehicle_ID='"+vid+"'";
                 ResultSet r93=st93.executeQuery(qry3);
                 while(r93.next())
                 {
                     price_id=r93.getInt("id");
                     System.out.println("price id is "+price_id);
                 }
                y_insert="insert into user_db(Gender,District_ID,Mobile,Telephone,Vehicle_ID,Vehicle_Number,Model_Number_ID,Selected_Option,Verify_ID,Source_ID,Dest_ID,Month_ID,Day_ID,Doc_ID,Card_ID,Name,Address,Email,Owner_ID) values('"+gender+"',"+dist_id+","+mobile+","+tele+","+vid+",'"+v_number+"',"+v_model_id+",'"+flag+"',"+verify_id+","+source_id+","+dest_id+","+m_id+","+d_id+",'"+y_tr+"',"+card_id+",'"+d_name+"','"+add+"','"+email+"',"+l_id+",'"+i_date+"','"+e_date+"')";
                st44.execute(y_insert);
                System.out.println("Yes selected query fire");

            }
            o_tr="";
            tr="";
            docstr="";
            y_tr="";
            jLabel35.setText("");
            jLabel36.setText("");
            jLabel39.setText("");
            jLabel40.setText("");
        }
        catch (SQLException ex) {
            Logger.getLogger(user_temp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(user_temp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(tab_user_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(tab_user_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(tab_user_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(tab_user_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new tab_user_form().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox Country_Combo;
    private javax.swing.JPanel No_Doc_Panel;
    private javax.swing.JPanel Owner_Panel;
    private javax.swing.JPanel Personal_Panel;
    private javax.swing.JPanel Source_Panel;
    private javax.swing.JPanel Vehicle_Panel;
    private javax.swing.JPanel Yes_Doc_Panel;
    private javax.swing.JTextArea address;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.JPanel button_panel;
    private javax.swing.JPanel button_panel1;
    private javax.swing.JPanel card;
    private javax.swing.JComboBox city;
    private javax.swing.JTextField d_txt;
    private javax.swing.JComboBox day;
    private javax.swing.JComboBox dest;
    private javax.swing.JComboBox district;
    private javax.swing.JPanel dopanel;
    private javax.swing.JPanel driver;
    private javax.swing.JLabel driver_name;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JRadioButton jRadioButton8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField mail;
    private javax.swing.JTextField mob;
    private javax.swing.JComboBox month;
    private javax.swing.JTextArea o_addt;
    private javax.swing.JComboBox o_cityt;
    private javax.swing.JComboBox o_country;
    private javax.swing.JTextField o_mailt;
    private javax.swing.JTextField o_namet;
    private javax.swing.JComboBox o_state;
    private javax.swing.JPanel owner;
    private javax.swing.JComboBox source;
    private javax.swing.JComboBox state;
    private javax.swing.JTextField tel;
    private javax.swing.JTextField telly;
    private javax.swing.JPanel type_panel;
    private javax.swing.JComboBox v_brand;
    private javax.swing.JComboBox v_model;
    private javax.swing.JComboBox v_name;
    private javax.swing.JPanel verify_panel;
    // End of variables declaration//GEN-END:variables
}
