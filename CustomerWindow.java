import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JTextField;
import java.util.List;
import javax.swing.*;


public class CustomerWindow extends JFrame implements ActionListener{

    private JButton but_add, but_del, but_fresh, but_edit;
    private JPanel buttonPanel;
    private Components birth;
    private JComboBox editList;
    private JTextField prize;

    public CustomerWindow() {

    setTitle("CustomerWindow");
    Toolkit tk = Toolkit.getDefaultToolkit();
    Dimension dim = tk.getScreenSize();
    setSize(600, 600);
    setLocation(new Point(dim.width/4, dim.height/4));
    Container contentPane = this.getContentPane();

	buttonPanel = new JPanel(new GridLayout(0,3));

	// create buttons
	but_add = new JButton("Add");
	but_del = new JButton("Delete");
	but_fresh = new JButton("Fresh");
	but_edit = new JButton("Edit");

	// add buttons to panel
	buttonPanel.add(but_add);
	buttonPanel.add(but_fresh);

	// associate actions to buttons
	but_add.addActionListener(this);
	but_del.addActionListener(this);
	but_fresh.addActionListener(this);
	but_edit.addActionListener(this);

    // create Type panels
    birth = new Components();
    List<String> dataList = R_or_W_Data.importCsv(new File("./customerlist.csv"));
	String[] IDs = new String[dataList.size()];
    for(int i = 0; i<dataList.size();i++){
        IDs[i] = dataList.get(i).split(",",-1)[0];//ID
    }
    editList = birth.makeComboBox(buttonPanel, IDs, "Choose ID: " , this);

	buttonPanel.add(but_del);
	buttonPanel.add(but_edit);

    //the whole 
    List<String> money = R_or_W_Data.importCsv(new File("./customerlist.csv")); 
            
    int sum = 0;
    for(int i=0; i<dataList.size();i++){
        sum = sum+Integer.parseInt(money.get(i).split(",",-1)[11]);
    }
    JLabel Prize = new JLabel("Income: ");
    buttonPanel.add(Prize);
    prize = new JTextField(Integer.toString(sum),16);
    prize.setEnabled(false);
    buttonPanel.add(prize);
    prize.addActionListener(this);

	// add panel to container
	contentPane.add(buttonPanel,BorderLayout.NORTH);

    /**display the whole .csv */
    contentPane.add(importCsv(),BorderLayout.SOUTH);
}

public static void main(String args[]) {
	JFrame frm = new CustomerWindow();
	frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frm.setVisible(true);

    //exportCsv();
    }

    /**Button Action */
    public void actionPerformed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        System.out.println(actionEvent.getActionCommand());
        if (source == but_add){
            JFrame frm = new AddWindow();
            frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frm.setVisible(true);
        }
        else if (source == but_del){
			String delID = (String) editList.getSelectedItem();
            List<String> dataList = R_or_W_Data.importCsv(new File("./customerlist.csv")); 
            for(int i=0; i<dataList.size();i++){
                if(dataList.get(i).split(",",-1)[0].equals(delID)){
                    dataList.remove(i);
                }
            }
            List<String> emptyList = new ArrayList<String>();
            R_or_W_Data.clearCsv(new File("./customerlist.csv"),emptyList);
            boolean isSuccess = R_or_W_Data.exportCsv(new File("./customerlist.csv"),dataList);
            System.out.println(isSuccess);
            CustomerWindow.this.dispose();
            JFrame frm = new CustomerWindow();
            frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frm.setVisible(true);
        }else if (source == but_fresh){
            CustomerWindow.this.dispose();
            JFrame frm = new CustomerWindow();
            frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frm.setVisible(true);
        }else if (source == but_edit){
			String delID = (String) editList.getSelectedItem();
            String list[] = new String[12];
            List<String> dataList = R_or_W_Data.importCsv(new File("./customerlist.csv")); 
            for(int i=0; i<dataList.size();i++){
                if(dataList.get(i).split(",",-1)[0].equals(delID)){
                    list[0]=dataList.get(i).split(",",-1)[0];//ID
                    list[1]=dataList.get(i).split(",",-1)[1];//type
                    list[2]=dataList.get(i).split(",",-1)[2];//first name
                    list[3]=dataList.get(i).split(",",-1)[3];//second name
                    list[4]=dataList.get(i).split(",",-1)[4];//brith
                    list[5]=dataList.get(i).split(",",-1)[5];//gender
                    list[6]=dataList.get(i).split(",",-1)[6];//postcode
                    list[7]=dataList.get(i).split(",",-1)[7];//telephone
                    list[8]=dataList.get(i).split(",",-1)[8];//Age
                    list[9]=dataList.get(i).split(",",-1)[9];//start date
                    list[10]=dataList.get(i).split(",",-1)[10];//end date
                    list[11]=dataList.get(i).split(",",-1)[11];//state
                }
            }
            if(list[1].equals("visitor")){
                JOptionPane.showMessageDialog(this, "Sorry visitor do not need to change data");
            }else{
                JFrame frm = new EditWindow(delID);
                frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frm.setVisible(true);
            }
        }
    }
    /**display the all data */
    public JScrollPane importCsv(){
        List<String> dataList = R_or_W_Data.importCsv(new File("./customerlist.csv"));  
        JScrollPane scrollPane = null;
        if(dataList != null && !dataList.isEmpty()){
             String[] columnNames = {"ID","Type","First Name","Second Name","Age","Gender","End Date","State"};
             String data[][] = new String[dataList.size()][];
             for(int i = 0; i<dataList.size();i++){
                 data[i] = new String[8];
                 data[i][0]=dataList.get(i).split(",",-1)[0];//ID
                 data[i][1]=dataList.get(i).split(",",-1)[1];//Type
                 data[i][2]=dataList.get(i).split(",",-1)[2];//First name
                 data[i][3]=dataList.get(i).split(",",-1)[3];//Second name
                 data[i][4]=dataList.get(i).split(",",-1)[8];//Age
                 data[i][5]=dataList.get(i).split(",",-1)[5];//Gender
                 data[i][6]=dataList.get(i).split(",",-1)[10];//End time
                 data[i][7]=dataList.get(i).split(",",-1)[11];//State
             }
             JTable table = new JTable(data, columnNames);
             scrollPane = new JScrollPane(table);
             scrollPane.setPreferredSize(new Dimension(600, 480));
             table.setFillsViewportHeight(true);
        }
        return scrollPane;
    }

    /**test code */
    public static void exportCsv(){
        List<String> dataList = new ArrayList<String>();
        //dataList.add("ID,Type,First Name,Second Name, Birth, Gender, Postcode, Telephone, Age, Start Date, End Date, State");
        dataList.add("1,individual,Jack,Ben,2000-1-1,Male,1234,5678,21,2021-12-17,2022-06-17,108");
        dataList.add("2,family,Brian,Sam,1996-1-1,Male,0000,1111,25,2021-12-17,2022-09-17,108");
        dataList.add("3,family,Kitty,Sally,1997-1-1,Female,9999,8888,26,2021-12-17,2022-03-17,108");
        boolean isSuccess = R_or_W_Data.exportCsv(new File("./customerlist1.csv"),dataList);
        System.out.println(isSuccess);
    }
}

    


    