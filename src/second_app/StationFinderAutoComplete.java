/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.example.second_app;
import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.TextFilterator;
import ca.odell.glazedlists.matchers.TextMatcherEditor;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import ca.odell.glazedlists.swing.EventComboBoxModel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import second_app.connection;

public class StationFinderAutoComplete {

    private JFrame mainFrame;
    private JComboBox stationsComboBox;
    private EventList<Station> stations = new BasicEventList<Station>();

    public StationFinderAutoComplete() {
        populateStations();
        createGui();
        
        mainFrame.setVisible(true);
    }

    private void populateStations() {
       /* stations.add(new Station("Key 103", "Manchester"));
        stations.add(new Station("Capital FM", "London"));
        stations.add(new Station("Capital FM2", "London1"));
        stations.add(new Station("Capital FM3", "London2"));
        stations.add(new Station("Capital FM", "London"));
        stations.add(new Station("BBC Radio Leeds", "Leeds"));
        stations.add(new Station("BBC Radio 4", "London"));*/
        try
        {
            Connection con=connection.getConnect();
            String result="";
            Statement st1=con.createStatement();
            String vNameQ="select Vehicle_Number from user_db";
            ResultSet rs=st1.executeQuery(vNameQ);
            while(rs.next())
            {
                result=rs.getString("Vehicle_Number");
                stations.add(new Station(result));
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StationFinderAutoComplete.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(StationFinderAutoComplete.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
    }
    /*protected static ImageIcon createImageIcon(String path) {
    java.net.URL imgURL = StationFinderAutoComplete.class.getResource(path);
    
    return new ImageIcon(imgURL);
}*/

    private void createGui() {
        mainFrame = new JFrame("GlazedLists Autocomplete Example");
        mainFrame.setSize(600, 400);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Use a GlazedLists EventComboBoxModel to connect the JComboBox with an EventList.
        EventComboBoxModel<Station> model = new EventComboBoxModel<Station>(stations);
        stationsComboBox = new JComboBox(model);

        AutoCompleteSupport autocomplete = AutoCompleteSupport.install(stationsComboBox, stations, new StationTextFilterator());
        // Try without the filterator to see the difference.
        //AutoCompleteSupport autocomplete = AutoCompleteSupport.install(stationsComboBox, stations);
        
        autocomplete.setFilterMode(TextMatcherEditor.CONTAINS);
        
        JButton add=new JButton("Search");
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(stationsComboBox, BorderLayout.NORTH);
        add.addActionListener((ActionListener) this);
        panel.add(add);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.getContentPane().add(panel, BorderLayout.CENTER);

    }
public void actionPerformed(ActionEvent e) {
System.out.println("hello");
} 
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StationFinderAutoComplete();
            }
        });
    }

    class Station {

        private String title;
        private String location;

        public Station(String title/*, String location*/) {
            this.title = title;
           // this.location = location;
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
            return title ;
        }
    }

    class StationTextFilterator implements TextFilterator<Station> {
        @Override
        public void getFilterStrings(List<String> baseList, Station station) {
            baseList.add(station.getTitle());
        }
    }
}