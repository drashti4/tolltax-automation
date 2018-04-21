package second_app;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.TextFilterator;
import ca.odell.glazedlists.matchers.TextMatcherEditor;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import ca.odell.glazedlists.swing.EventComboBoxModel;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.xml.soap.Detail;
import second_app.SerialTest;
import second_app.connection;

public class WelcomePage extends javax.swing.JFrame {

    /**
     * Creates new form WelcomePage
     */
    SerialPort serialPort;
    private JFrame mainFrame;
    private JFrame Details;
    private JComboBox stationsComboBox;
    private EventList<Station> stations = new BasicEventList<Station>();
    JLabel info, Name, MobileNumber, EmailAddrs, Gender, VehicleType, VehicleBrand, VehicleName, VehicleModelNumber, Return, Arrival, remainingamnt;
    JButton bt;
    Connection con;
    JPanel panel = new JPanel(new GridLayout(10, 3));
    private static final String PORT_NAMES[] = {
        /*
         * "/dev/tty.usbserial-A9007UX1", // Mac OS X "/dev/ttyACM0", // Raspberry
         * Pi "/dev/ttyUSB0", // Linux
         */
        "COM18" // Windows
    };
    private BufferedReader input;
    /**
     * The output stream to the port
     */
    private OutputStream output;
    /**
     * Milliseconds to block while waiting for port open
     */
    private static final int TIME_OUT = 2000;
    /**
     * Default bits per second for COM port.
     */
    private static final int DATA_RATE = 9600;

    public JPanel contentPane;

    public WelcomePage() throws SQLException {
        contentPane = new JPanel() {

            
        };
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);

        initComponents();
        createGui();
        populateStations();
        mainFrame.setVisible(false);
        Details.setVisible(false);

        jButton1.setOpaque(false);
        jButton1.setContentAreaFilled(false);

        //jButton2.setOpaque(false);
        //  jButton2.setContentAreaFilled(false);
        bt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.revalidate();
                Name.revalidate();
                Name.setText("");
                Details.repaint();
                Details.revalidate();
                Details.setVisible(true);
                int in = stationsComboBox.getSelectedIndex();
                String VehicleID;
                Object str = stationsComboBox.getSelectedItem();
                System.out.println(str);
                info.setText(String.valueOf(str));
               // Details.revalidate();
                //  Details.repaint();
                //   Details.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                try {
                    con = connection.getConnect();
                  //  String qry1 = "SELECT u.*,v.*,b.*,n.*,mn.*,c.* FROM mydb.userdb as u inner join vehicle_type as v  inner join vehicle_brand as b inner join vehiclename as n inner join vehiclemodel_number as mn inner join cptissue as c where v.Vehicle_ID = u.Vehicle_ID and v.Vehicle_ID = b.Vehicle_ID and b.Brand_ID = n.Brand_ID and n.VehicleName_ID = mn.VehicleName_ID and c.UserDBID = u.UserDBID and Vehicle_Number = '" + info.getText() + "'";
            //        String qry2 = "SELECT u.*,v.*,b.*,n.*,mn.* FROM mydb.userdb as u inner join vehicle_type as v  inner join vehicle_brand as b inner join vehiclename as n inner join vehiclemodel_number as mn where v.Vehicle_ID = u.Vehicle_ID and v.Vehicle_ID = b.Vehicle_ID and b.Brand_ID = n.Brand_ID and n.VehicleName_ID = mn.VehicleName_ID and Vehicle_Number = '" + info.getText() + "'";
                    //      String qry3 = "SELECT u.*,c.* FROM mydb.cptissue as c inner join mydb.userdb as u where u.UserDBID = c.UserDBID '" + info.getText() + "'";

                    //Statement st = con.createStatement();
                    //ResultSet rs = st.executeQuery(qry1);
                 //   Statement st1 = con.createStatement();
                    //  ResultSet rs1 = st1.executeQuery(qry3);

                   /* while (rs.next()) {
                        System.out.println(rs.getString("D_Name") + " " + rs.getString("Address"));
                        Name.setText(" " + rs.getString("D_Name"));
                        MobileNumber.setText(rs.getString("Mobile"));
                        EmailAddrs.setText(rs.getString("Email"));
                        Gender.setText(rs.getString("Gender"));
                        VehicleID = rs.getString("Vehicle_ID");
                        VehicleType.setText(rs.getString("Vehicle_Type"));
                        VehicleBrand.setText(rs.getString("Brand_Name"));
                        VehicleName.setText(rs.getString("VehicleName"));
                        VehicleModelNumber.setText(rs.getString("Model_Number"));
                        Return.setText(rs.getString("CPT_ReturnD&T_1"));
                        Arrival.setText(rs.getString("CPT_ArrivalD&T_1"));
                        remainingamnt.setText(rs.getString("RemainingAmnt"));
                   }*/

                    panel.add(Name);
                    panel.add(Gender);
                    panel.add(MobileNumber);
                    panel.add(EmailAddrs);
                    panel.add(VehicleType);
                    panel.add(VehicleBrand);
                    panel.add(VehicleName);
                    panel.add(VehicleModelNumber);
                    panel.add(Return);
                    panel.add(Arrival);
                    panel.add(remainingamnt);
                    Details.setLayout(new BorderLayout());
                    Details.getContentPane().add(panel);

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(WelcomePage.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(WelcomePage.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton13 = new javax.swing.JButton();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jpanel = new javax.swing.JPanel();

        jButton13.setBackground(new java.awt.Color(204, 204, 255));
        jButton13.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton13.setForeground(new java.awt.Color(102, 102, 102));
        jButton13.setText("PERSON_OPTION");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 102, 204));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Californian FB", 1, 48)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Toll Tax Automation System");

        jLabel6.setFont(new java.awt.Font("Californian FB", 3, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Pay Toll & Drive Safe ");

/*        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/splashdemo/Images/tollindia1.jpg"))); // NOI18N

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/splashdemo/Images/tollindia.jpg"))); // NOI18N

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/splashdemo/Images/user-alt-4.png"))); // NOI18N*/
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1MouseExited(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Admin Panel");

        jLabel8.setFont(new java.awt.Font("Californian FB", 3, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("No Cash Only Card");

        javax.swing.GroupLayout jpanelLayout = new javax.swing.GroupLayout(jpanel);
        jpanel.setLayout(jpanelLayout);
        jpanelLayout.setHorizontalGroup(
            jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 194, Short.MAX_VALUE)
        );
        jpanelLayout.setVerticalGroup(
            jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 183, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1148, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8))
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(157, 157, 157)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(632, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton13ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:


    }//GEN-LAST:event_formWindowOpened
    public void initialize() {
        // the next line is for Raspberry Pi and
        // gets us into the while loop and was suggested here was suggested
        // http://www.raspberrypi.org/phpBB3/viewtopic.php?f=81&t=32186
        System.setProperty("gnu.io.rxtx.SerialPorts", "COM18");

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

    private void populateStations() throws SQLException {
        Connection con;
        try {
            con = connection.getConnect();
            String qry = "select Vehicle_Number from userdb";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(qry);
            while (rs.next()) {
                stations.add(new Station(rs.getString("Vehicle_Number")));

            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(WelcomePage.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void createGui() {
        Details = new JFrame("Detail Information");
        Details.setSize(600, 400);
        //  Details.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame = new JFrame("GlazedLists Autocomplete Example");
        mainFrame.setSize(600, 400);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Use a GlazedLists EventComboBoxModel to connect the JComboBox with an EventList.
        EventComboBoxModel<Station> model = new EventComboBoxModel<Station>(stations);
        stationsComboBox = new JComboBox(model);
        bt = new JButton("Get Details");
        info = new JLabel();
        Name = new JLabel();
        MobileNumber = new JLabel();
        EmailAddrs = new JLabel();
        Gender = new JLabel();
        VehicleType = new JLabel();
        VehicleBrand = new JLabel();
        VehicleName = new JLabel();
        VehicleModelNumber = new JLabel();
        Return = new JLabel();
        Arrival = new JLabel();
        remainingamnt = new JLabel();

        AutoCompleteSupport autocomplete = AutoCompleteSupport.install(stationsComboBox, stations, new StationTextFilterator());
        // Try without the filterator to see the difference.
        //AutoCompleteSupport autocomplete = AutoCompleteSupport.install(stationsComboBox, stations);
        autocomplete.setFilterMode(TextMatcherEditor.CONTAINS);

        /*JPanel panel = new JPanel(new BorderLayout());
         panel.add(stationsComboBox, BorderLayout.NORTH);
         mainFrame.setLayout(new BorderLayout());
         mainFrame.getContentPane().add(panel, BorderLayout.CENTER);
        
         */
        JPanel panel = new JPanel(new GridLayout(20, 20));
        panel.add(bt);
        panel.add(stationsComboBox);
        panel.add(Name);
        panel.add(Gender);
        panel.add(MobileNumber);
        panel.add(EmailAddrs);
        panel.add(VehicleType);
        panel.add(VehicleBrand);
        panel.add(VehicleName);
        panel.add(VehicleModelNumber);
        panel.add(Return);
        panel.add(Arrival);
        panel.add(remainingamnt);

        jpanel.setLayout(new BorderLayout());
        jpanel.add(stationsComboBox, BorderLayout.NORTH);
        jpanel.add(bt, BorderLayout.SOUTH);

    }

    class Station {

        private String title;

        public Station(String title) {
            this.title = title;

        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return title;
        }
    }

    class StationTextFilterator implements TextFilterator<Station> {

        //@Override
        public void getFilterStrings(List<String> baseList, Station station) {
            baseList.add(station.getTitle());
        }
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
       /* samLogin lg = new samLogin();
        lg.setVisible(true);
        setVisible(false);*/
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseEntered
        // TODO add your handling code here:
        jButton1.setOpaque(true);
        jButton1.setBackground(Color.gray);
    }//GEN-LAST:event_jButton1MouseEntered

    private void jButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseExited
        // TODO add your handling code here:
        jButton1.setOpaque(false);
    }//GEN-LAST:event_jButton1MouseExited

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
            java.util.logging.Logger.getLogger(WelcomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WelcomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WelcomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WelcomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        SerialTest main = new SerialTest();

        main.initialize();
        Thread t = new Thread() {
            public void run() {
                // the following line will keep this app alive for 1000 seconds,
                // waiting for events to occur and responding to them (printing
                // incoming messages to console).
                try {
                    Thread.sleep(1000000);
                } catch (InterruptedException ie) {
                }
            }
        };
        t.start();
        System.out.println("Started");

        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    new WelcomePage().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(WelcomePage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton13;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jpanel;
    // End of variables declaration//GEN-END:variables
}
