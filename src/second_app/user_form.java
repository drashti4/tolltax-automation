/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package second_app;

/**
 *
 * @author NEXT
 */
import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.TextFilterator;
import ca.odell.glazedlists.matchers.TextMatcherEditor;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import ca.odell.glazedlists.swing.EventComboBoxModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.RadioButton;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
//import java.awt.Component;
import java.awt.Rectangle;
import javax.swing.JRadioButton;
class Station {

        private String title;
        private String location;

        public Station(String title, String location) {
            this.title = title;
            this.location = location;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        @Override
        public String toString() {
            return title + " " + location + " ";
        }
    }

public class user_form extends javax.swing.JFrame implements TextFilterator<Station>{

    /**
     * Creates new form user_form
     */
    String flag;
    String d_name;
    String gender, add, email, v_number;
    int dist_id, mobile, tele, v_type_id, v_model_id, verify_id,flg=0;
    String o_name, o_add, o_gender,o_email;
    int source_id, dest_id, m_id, d_id, doc_id, o_city_id, o_mobile,temp=0;
    int card_id=0;
            public void getFilterStrings(List<String> baseList, Station station) {
            baseList.add(station.getTitle());
        }

    JRadioButton chk1, ver,cardr;
    JCheckBox d_chk, o_chk, ychk;
    ArrayList<JRadioButton> chkl = new ArrayList<JRadioButton>();
    ArrayList<JRadioButton> vl = new ArrayList<JRadioButton>();
    ArrayList<JRadioButton> cardrl = new ArrayList<JRadioButton>();
    ArrayList<JCheckBox> d_chkl = new ArrayList<JCheckBox>();
    ArrayList<JCheckBox> o_chkl = new ArrayList<JCheckBox>();
    ArrayList<JCheckBox> ychkl = new ArrayList<JCheckBox>();
    ButtonGroup b1 = new ButtonGroup();
    ButtonGroup b2 = new ButtonGroup();
     ButtonGroup b5 = new ButtonGroup();
    String[] col={"User_DB_ID","Gender","District_ID","Mobile","Telephone","Vehicle_ID","Model_Number_ID","Selected_Option","Verify_ID","Source_ID","Dest_ID","Month_ID","Day_ID","Doc_ID"};
    String docstr="";
    String owner_doc="";
    String yes_doc="";
    String rsn="";
    private JComboBox stationsComboBox;
    private EventList<Station> stations = new BasicEventList<Station>();

    public user_form() {
        initComponents(); 
          /*   Rectangle maxBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().
        getMaximumWindowBounds();
            this.setSize(maxBounds.width, maxBounds.height);*/
        try {
            Connection con = connection.getConnect();
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
            Statement st41 = con.createStatement();
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
            String cardq="select Card_Name from card_type ";
            ResultSet r1 = st1.executeQuery(countryq);
            ResultSet r16 = st16.executeQuery(cityq);
            ResultSet r17 = st17.executeQuery(month1);
            ResultSet r18 = st18.executeQuery(day1);
            ResultSet r19 = st19.executeQuery(doc_id1);
            ResultSet r20 = st20.executeQuery(doc_id2);
            ResultSet r23 = st23.executeQuery(doc_id3);
            ResultSet r41 = st41.executeQuery(cardq);
            source.removeAllItems();
            dest.removeAllItems();
            day.removeAllItems();
            month.removeAllItems();
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
                                   ResultSet r50=st50.executeQuery(verifyidq);
                                   while(r50.next())
                                   {
                                       verify_id=r50.getInt("Verify_ID");
                                   }
                               } catch (SQLException ex) {
                                   Logger.getLogger(user_form.class.getName()).log(Level.SEVERE, null, ex);
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
                    card.revalidate();
                    card.repaint();
            }
            Country_Combo.removeAllItems();
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
            for (JRadioButton jradio : chkl) {
                jradio.addActionListener(new ActionListener() {
                    String vnm = null;
                    String vnmq = null;
                    String bnmq = null;
                    int vid = 0;

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (jradio.isSelected() == true) {
                            v_brand.removeAllItems();
                            vnm = jradio.getText().toString();
                            System.out.println("selected vehicle name " + vnm);
                            vnmq = "select Vehicle_ID from vehicle_type where Vehicle_Type='" + vnm + "'";
                            try {
                                Connection con6 = connection.getConnect();
                                Statement st13 = con6.createStatement();
                                ResultSet r13 = st13.executeQuery(vnmq);
                                while (r13.next()) {
                                    vid = r13.getInt("Vehicle_ID");
                                }
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
                /*if(jradio.isSelected() == true)
                 {
                 vnm = jradio.getText();
                 vnmq="select Vehicle_ID from vehicle_type where Vehicle_Name='"+vnm+"'";
                 ResultSet r13=st13.executeQuery(vnmq);
                 while(r13.next())
                 {
                 vid=r13.getInt("Vehicle_ID");
                 }
                 bnmq="select Brand_Name from vehicle_Brand where Vehicle_ID="+vid+"";
                 ResultSet r14=st14.executeQuery(bnmq);
                 while(r14.next())
                 {
                 v_brand.addItem(r14.getString("Brand_Name"));
                 }
                 }*/
                Vehicle_Panel.revalidate();
                Vehicle_Panel.repaint();

            }
        } catch (SQLException ex) {
            Logger.getLogger(user_temp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(user_temp.class.getName()).log(Level.SEVERE, null, ex);
        }
        Personal_Panel.hide();
        Vehicle_Panel.hide();
        Owner_Panel.hide();
        Source_Panel.hide();
        Yes_Doc_Panel.hide();
        No_Doc_Panel.hide();
        card_type.hide();
        button_panel.hide();
       // jPanel2.hide();
         populateStations();
        createGui();
       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private void populateStations() {
        try {
            // TODO add your handling code here:
            Connection con10=connection.getConnect();
           // String search_item=toString().valueOf(stationsComboBox.getSelectedItem());
            Statement st45=con10.createStatement();
            String ctyq="select co.*,s.* from country as co inner join state as s where co.Country_ID=s.Country_ID";
            ResultSet r45=st45.executeQuery(ctyq);
            while(r45.next())
            {
                stations.add(new Station(r45.getString("Country_Name"),r45.getString("State_Name")));
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(user_form.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(user_form.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void createGui() {
       // mainFrame = new JFrame("GlazedLists Autocomplete Example");
       // mainFrame.setSize(600, 400);
        //mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Use a GlazedLists EventComboBoxModel to connect the JComboBox with an EventList.
        EventComboBoxModel<Station> model = new EventComboBoxModel<Station>(stations);
        stationsComboBox = new JComboBox(model);

        AutoCompleteSupport autocomplete = AutoCompleteSupport.install(stationsComboBox, stations);
        // Try without the filterator to see the difference.
        //AutoCompleteSupport autocomplete = AutoCompleteSupport.install(stationsComboBox, stations);
        autocomplete.setFilterMode(TextMatcherEditor.CONTAINS);

        //JPanel panel = new JPanel(new BorderLayout());
        jPanel1.setLayout(new FlowLayout());
        jPanel1.add(stationsComboBox, BorderLayout.NORTH);
       // mainFrame.setLayout(new BorderLayout());
        //mainFrame.getContentPane().add(panel, BorderLayout.CENTER);
        jPanel1.revalidate();
        jPanel1.repaint();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jRadioButton5 = new javax.swing.JRadioButton();
        jRadioButton6 = new javax.swing.JRadioButton();
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
        Yes_Doc_Panel = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        dopanel = new javax.swing.JPanel();
        card_type = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        card = new javax.swing.JPanel();
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
        No_Doc_Panel = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        driver = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        owner = new javax.swing.JPanel();
        Source_Panel = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        source = new javax.swing.JComboBox();
        dest = new javax.swing.JComboBox();
        month = new javax.swing.JComboBox();
        day = new javax.swing.JComboBox();
        jRadioButton7 = new javax.swing.JRadioButton();
        jRadioButton8 = new javax.swing.JRadioButton();
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
        button_panel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel21.setText("Are You Driver With Owner?");

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

        Personal_Panel.setBackground(new java.awt.Color(255, 255, 255));
        Personal_Panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Personal Detail"));

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

        buttonGroup2.add(jRadioButton2);
        jRadioButton2.setText("Female");

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
                .addContainerGap(196, Short.MAX_VALUE))
        );
        Yes_Doc_PanelLayout.setVerticalGroup(
            Yes_Doc_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Yes_Doc_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel29)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(Yes_Doc_PanelLayout.createSequentialGroup()
                .addComponent(dopanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 33, Short.MAX_VALUE))
        );

        jTextField1.setText("Which Card will you prefer?");

        javax.swing.GroupLayout cardLayout = new javax.swing.GroupLayout(card);
        card.setLayout(cardLayout);
        cardLayout.setHorizontalGroup(
            cardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 175, Short.MAX_VALUE)
        );
        cardLayout.setVerticalGroup(
            cardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 81, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout card_typeLayout = new javax.swing.GroupLayout(card_type);
        card_type.setLayout(card_typeLayout);
        card_typeLayout.setHorizontalGroup(
            card_typeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(card_typeLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(card, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(155, Short.MAX_VALUE))
        );
        card_typeLayout.setVerticalGroup(
            card_typeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(card_typeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(card_typeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(card, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Owner_Panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Owner Detail"));

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

        No_Doc_Panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Document Needed"));

        jLabel27.setText("Driver");

        driver.setBorder(new javax.swing.border.MatteBorder(null));

        javax.swing.GroupLayout driverLayout = new javax.swing.GroupLayout(driver);
        driver.setLayout(driverLayout);
        driverLayout.setHorizontalGroup(
            driverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        driverLayout.setVerticalGroup(
            driverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
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

        javax.swing.GroupLayout No_Doc_PanelLayout = new javax.swing.GroupLayout(No_Doc_Panel);
        No_Doc_Panel.setLayout(No_Doc_PanelLayout);
        No_Doc_PanelLayout.setHorizontalGroup(
            No_Doc_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(No_Doc_PanelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(No_Doc_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addGap(49, 49, 49)
                .addGroup(No_Doc_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(owner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(driver, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(221, Short.MAX_VALUE))
        );
        No_Doc_PanelLayout.setVerticalGroup(
            No_Doc_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, No_Doc_PanelLayout.createSequentialGroup()
                .addGroup(No_Doc_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(No_Doc_PanelLayout.createSequentialGroup()
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 54, Short.MAX_VALUE))
                    .addGroup(No_Doc_PanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(driver, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(No_Doc_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, No_Doc_PanelLayout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addGap(97, 97, 97))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, No_Doc_PanelLayout.createSequentialGroup()
                        .addComponent(owner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29))))
        );

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

        Vehicle_Panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Vehicle Detail"));

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

        jButton1.setText("Done");
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(Owner_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Personal_Panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Vehicle_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Source_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Yes_Doc_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(No_Doc_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(card_type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addComponent(button_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Personal_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Owner_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Vehicle_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(Source_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(No_Doc_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(Yes_Doc_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(card_type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(button_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jButton3.setText("Search");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(235, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addGap(39, 39, 39))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jButton3)
                .addContainerGap(130, Short.MAX_VALUE))
        );

        jScrollPane3.setViewportView(jPanel1);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(122, 122, 122)
                        .addComponent(jRadioButton5)
                        .addGap(76, 76, 76)
                        .addComponent(jRadioButton6))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(2499, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jRadioButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton6))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(647, Short.MAX_VALUE))
        );

        jScrollPane4.setViewportView(jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(703, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 2362, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:      
    }//GEN-LAST:event_formWindowOpened

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        try {
            String type = "";
            String verify = "";
            String sourcet="";
            String destt="";
            String o_cty="";
            String monthi="";
            String driver_doc="";
            String cnm="";
            String y_doc="";
            if(!telly.equals(null))
            {
                //            o_mobile=Integer.parseInt(telly.getText());
            }
            String mobi=toString().valueOf(mob.getText());
            String tl=toString().valueOf(tel.getText());
            String dist_nm =toString().valueOf( district.getSelectedItem());
            String model =toString().valueOf( v_model.getSelectedItem());
            sourcet=toString().valueOf(source.getSelectedItem());
            destt=toString().valueOf(source.getSelectedItem());
            String dayi=toString().valueOf(day.getSelectedItem());
            monthi=toString().valueOf(month.getSelectedItem());
            o_name=toString().valueOf(o_namet.getText());
            o_add=toString().valueOf(o_addt.getText());
            o_email=toString().valueOf(o_mailt.getText());
            o_cty=toString().valueOf(o_cityt.getSelectedItem());
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
            String vtypeq = "select Vehicle_ID from vehicle_type where Vehicle_Name='" + type + "'";
            String distq = "select District_ID from district where District_Name='" + dist_nm + "'";
            String numq = "select Vm_Number_ID from model_number where Model_Number='" + model + "'";
            String vq = "select Verify_ID from verification where Reason='" + verify + "'";
            String sourceq="select City_ID from city where City_Name='" + sourcet + "'";
            String destq="select City_ID from city where City_Name='" + destt + "'";
            String dayq="select Day_ID from day_duration where days='" + dayi + "'";
            String o_cityq="select City_ID from city where City_Name='" + o_cty + "'";
            String monthq="select Month_ID from month_duration where times='" + monthi + "'";
            /*  String d_name;
            String gender, add, email, v_number;
            int dist_id, mobile, tele, v_type_id, v_model_id, verify_id,flg=0;
            String o_name, o_add, o_gender,o_email;
            int source_id, dest_id, m_id, d_id, doc_id, o_city_id, o_mobile,temp=0;*/
            int doc_id1=3;
            String insertq="insert into user_db values(1,gender,dist_id,mobile,tele,v_type_id,v_number,v_model_id,flag,verify_id,source_id,dest_id,m_id,day_id,8,doc_id1)";

            String docq="",docq1="";

            ResultSet r30=st30.executeQuery(sourceq);
            ResultSet r31=st31.executeQuery(destq);
            ResultSet r33=st33.executeQuery(dayq);
            ResultSet r34=st34.executeQuery(o_cityq);
            st43.execute(insertq);
            System.out.println("insert fire");
            d_name = d_txt.getText();
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
                v_model_id = r28.getInt("numq");
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
                         System.out.println("selected documents driver are "+dchk.getText());
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
                        System.out.println("selected documents driver are "+ochk.getText());
                        while(r41.next())
                        {
                            System.out.println("owner id "+r41.getInt("Doc_ID"));
                            owner_doc=owner_doc+r41.getInt("Doc_ID")+",";
                            temp=2;
                        }
                    }
                }
                String tr=docstr.substring(0,docstr.length()-1);
                String o_tr=owner_doc.substring(0,owner_doc.length()-1);
                System.out.println("driver array "+tr);
                System.out.println("Owner array "+o_tr);
            }
            String ye_doc="";
            String docq2="";
            if(temp==2)
            {
                for (JCheckBox ychk : ychkl)
                {

                    if (ychk.isSelected() == true) {
                        System.out.println("selected documents temp=2 are "+ychk.getText());
                        ye_doc=ychk.getText();
                        docq2="select Doc_ID from documents where Doc_Name='" + ye_doc + "'";

                        ResultSet r36=st36.executeQuery(docq2);
                        System.out.println("selected documents temp = 2driver are "+ychk.getText());
                        while(r36.next())
                        {
                            System.out.println("owner id "+r36.getInt("Doc_ID"));
                            yes_doc=yes_doc+r36.getInt("Doc_ID")+",";
                            temp=4;
                        }
                    }
                }

                //               String y_tr=yes_doc.substring(0,yes_doc.length()-1);
                //             System.out.println("Ownere+driver array "+y_tr);
            }
            //CARD_TYPE
            String ctypeq="";
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
            }
            //for verification
            for (Enumeration<AbstractButton> btn = b2.getElements(); btn.hasMoreElements();) {
                AbstractButton butn = btn.nextElement();
                if (butn.isSelected()) {
                    verify = butn.getText();
                    System.out.println("selected radio is for verificaton is  "+verify);
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
                    }
                }
            }
        } catch (SQLException ex) {
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
            Logger.getLogger(user_temp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(user_temp.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(user_temp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(user_temp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_v_brandActionPerformed

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
            Logger.getLogger(user_temp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(user_temp.class.getName()).log(Level.SEVERE, null, ex);
        }//9913382323 9925103667*/
    }//GEN-LAST:event_dayActionPerformed

    private void monthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monthActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_monthActionPerformed

    private void sourceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sourceActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_sourceActionPerformed

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
            Logger.getLogger(user_temp.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(user_temp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(user_temp.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(user_temp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cityActionPerformed

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
            Logger.getLogger(user_temp.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(user_temp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(user_temp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Country_ComboActionPerformed

    private void Country_ComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Country_ComboItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_Country_ComboItemStateChanged

    private void jRadioButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton6ActionPerformed
        // TODO add your handling code here:
        jPanel4.revalidate();
        jPanel4.repaint();

        for (Enumeration<AbstractButton> buttons = buttonGroup1.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                System.out.println("NO selected");
                flag = "n";
                temp=1;
                System.out.println("temp = 1");
                Personal_Panel.show();
                Vehicle_Panel.show();
                Source_Panel.show();
                Owner_Panel.show();
                No_Doc_Panel.show();
              //  jPanel2.show();
            //    jPanel4.scrollRectToVisible(null);
                jScrollPane4.show();   
                jScrollPane4.revalidate();
            //    jScrollPane4.setPreferredSize(new Dimension(10000,10000));
             //   jScrollPane4.sete
              /*  JFrame.setDefaultLookAndFeelDecorated(rootPaneCheckingEnabled);
                Type t=JFrame.Type.POPUP;*/
            }
        }
        jPanel4.revalidate();
        jPanel4.repaint();
    }//GEN-LAST:event_jRadioButton6ActionPerformed

    private void jRadioButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton5ActionPerformed
        // TODO add your handling code here:
        jPanel4.revalidate();
        jPanel4.repaint();
        jPanel2.revalidate();
        jPanel2.repaint();
        for (Enumeration<AbstractButton> buttons = buttonGroup1.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                System.out.println("Yes selected");
                flag = "y";
                    temp=2;
                    System.out.println("temp = 2");
                /*Personal_Panel.hide();
                Source_Panel.hide();
                Personal_Panel.hide();
                Vehicle_Panel.hide();
                Yes_Doc_Panel.hide();
                No_Doc_Panel.hide();
                jPanel1.removeAll();
            
                /* Personal_Panel.show();
                Vehicle_Panel.show();
                Source_Panel.show();
                card_type.show();
                Yes_Doc_Panel.show();
                No_Doc_Panel.show();
                button_panel.show();
                Owner_Panel.show();
                jScrollPane4.setVisible(true);
                jScrollPane4.show();
                
                jPanel2.add(Owner_Panel);*/
                jPanel2.show();
                jPanel2.setBackground(Color.red);
                //jPanel2.setVisible(true);
            }
        }
        jPanel4.revalidate();
        jPanel4.repaint();
        jPanel2.revalidate();
        jPanel2.repaint();
        //pack();
    }//GEN-LAST:event_jRadioButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            // TODO add your handling code here:
            Connection con=connection.getConnect();
            String search_item=toString().valueOf(stationsComboBox.getSelectedItem());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(user_form.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(user_form.class.getName()).log(Level.SEVERE, null, ex);
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
            java.util.logging.Logger.getLogger(user_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(user_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(user_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(user_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new user_form().setVisible(true);
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
    private javax.swing.JPanel card;
    private javax.swing.JPanel card_type;
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
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
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField jTextField1;
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
