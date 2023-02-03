import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

import java.util.List;
import java.util.ArrayList;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.*;
import javax.swing.*;

public class EditWindow extends JFrame implements ActionListener{

    private Components birth;
	private JButton exit, confirm;
	private String[] year, month, day;
	private JPanel Information, action;
    private JComboBox Year, Month, Day, genderList,VIPTimeList,typeList;
	private JTextField firstname,secondname,postcode,telephone;
    private String list[] = new String[12];
	private String[] genders = { "Male", "Female"};
	private String[] VIPTime = { "3", "6","9","12"};
	private String[] Types = { "individual", "family","visitor"};

    public EditWindow(String checkID){
        List<String> dataList = R_or_W_Data.importCsv(new File("./customerlist.csv")); 
            for(int i=0; i<dataList.size();i++){
                if(dataList.get(i).split(",",-1)[0].equals(checkID)){
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

        setTitle("EditWindow");
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dim = tk.getScreenSize();
        setSize(500, 500);
        setLocation(new Point(dim.width/4, dim.height/4));
        Container contentPane = this.getContentPane();

        birth = new Components();
		
		// create date string array
		year  = birth.DateList(1990, 2018);
		month = birth.DateList(1, 12);
		day   = birth.DateList(1, 31);

        
		Information = new JPanel(new GridLayout(0,2));

        // create Type panels
        if(!Types[0].equals(list[1])){
            for(int i = 0;i<Types.length;i++){
                if(Types[i].equals(list[1])){
                    String temp = Types[i];
                    Types[i]  = Types[0];
                    Types[0]  = temp;
                }
            }
        }
		typeList = birth.makeComboBox(Information, Types, "Types: " , this);


        //First Name
        JLabel firstName = new JLabel("First Name: ");
        Information.add(firstName);
        firstname = new JTextField(list[2],16);
        Information.add(firstname);
        firstname.addActionListener(this);

        //Second Name
        JLabel secondName = new JLabel("Second Name: ");
        Information.add(secondName);
        secondname = new JTextField(list[3],16);
        Information.add(secondname);
        secondname.addActionListener(this);

        // create gender panels
        if(!genders[0].equals(list[5])){
            String temp = genders[1];
            genders[1]  = genders[0];
            genders[0]  = temp;
        }
		genderList = birth.makeComboBox(Information, genders, "Gender: " , this);

        //Postcode
        JLabel Postcode = new JLabel("Postcode: ");
        Information.add(Postcode);
        postcode = new JTextField(list[6],16);
        Information.add(postcode);
        postcode.addActionListener(this);

        //Telephone
        JLabel Telephone = new JLabel("Telephone: ");
        Information.add(Telephone);
        telephone = new JTextField(list[7],16);
        Information.add(telephone);
        telephone.addActionListener(this);

        // create how long customer will buy panels
		VIPTimeList = birth.makeComboBox(Information, VIPTime, "VIP time: " , this);

        // birth panel
        String y = list[4].split("-")[0];

        if(!year[0].equals(y)){
            for(int i = 0;i<year.length;i++){
                if(year[i].equals(y)){
                    String temp = year[i];
                    year[i]  = year[0];
                    year[0]  = temp;
                }
            }
        }

        String m = list[4].split("-")[1];

        if(!month[0].equals(m)){
            for(int i = 0;i<month.length;i++){
                if(month[i].equals(m)){
                    String temp = month[i];
                    month[i]  = month[0];
                    month[0]  = temp;
                }
            }
        }

        String d = list[4].split("-")[2];

        if(!day[0].equals(d)){
            for(int i = 0;i<day.length;i++){
                if(day[i].equals(d)){
                    String temp = day[i];
                    day[i]  = day[0];
                    day[0]  = temp;
                }
            }
        }

		Year  = birth.makeComboBox(Information, year , "Year:" , this);
		Month = birth.makeComboBox(Information, month, "Month:", this);
		Day   = birth.makeComboBox(Information, day  , "Day:", this);
		contentPane.add(Information,BorderLayout.NORTH);

        // confirm and exit panel
		action = new JPanel();
		confirm = birth.makeJButton(action, "Confirm", this);
		exit = birth.makeJButton(action, "Exit", this);
		contentPane.add(action,BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (source == exit) {
			//System.exit(0);
            EditWindow.this.dispose();
		}else if (source == confirm) {
			String type = (String) typeList.getSelectedItem();
			String first_name = (String) firstname.getText();
			String second_name = (String) secondname.getText();
			String gender = (String) genderList.getSelectedItem();
			String post_code = (String) postcode.getText();
			String tele_phone = (String) telephone.getText();
			String VIPtime = (String) VIPTimeList.getSelectedItem();
			String Ybirth = (String) Year.getSelectedItem();
			String Mbirth = (String) Month.getSelectedItem();
			String Dbirth = (String) Day.getSelectedItem();

            List<String> dataList = new ArrayList<String>();

            int ID = Integer.parseInt(list[0]);//id

            String born = Ybirth+"-"+Mbirth+"-"+Dbirth;//born date yyyy-mm-dd

            int Age = 2021-Integer.parseInt(Ybirth);//age

            Date day=new Date();//start Date
            String startdate = null;
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            startdate = df.format(day);

            String enddate = null;//end date
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, Integer.parseInt(VIPtime));
            enddate = df.format(cal.getTime());

            String a = ID+","+type+","+first_name+","+second_name+","+born+","+gender+","+post_code+","+tele_phone+","+Age+","+startdate+","+enddate+",";
            System.out.println(a);
            //check the age limitation
            if(type=="individual" && Age>=12){
                int State = Integer.parseInt(VIPtime)*36;
                a = a+State;
                List<String> above = R_or_W_Data.importCsv(new File("./customerlist.csv")); 
                for(int i=0; i<above.size();i++){
                    if(above.get(i).split(",",-1)[0].equals(list[0])){
                        above.remove(i);
                    }
                }

                //clear csv
                List<String> emptyList = new ArrayList<String>();
                R_or_W_Data.clearCsv(new File("./customerlist.csv"),emptyList);
                R_or_W_Data.exportCsv(new File("./customerlist.csv"),above);
                dataList.add(a);
                boolean isSuccess = R_or_W_Data.exportCsv(new File("./customerlist.csv"),dataList);

                System.out.println(isSuccess);
                if(isSuccess){
                    JOptionPane.showMessageDialog(this, "Congratulations you have successfully change individual information, you need to pay: "+Integer.parseInt(VIPtime)*36);
                }
            }else if(type=="family" && Age>=18){
                int State = Integer.parseInt(VIPtime)*60;
                a = a+State;
                List<String> above = R_or_W_Data.importCsv(new File("./customerlist.csv")); 
                for(int i=0; i<above.size();i++){
                    if(above.get(i).split(",",-1)[0].equals(list[0])){
                        above.remove(i);
                    }
                }

                //clear csv
                List<String> emptyList = new ArrayList<String>();
                R_or_W_Data.clearCsv(new File("./customerlist.csv"),emptyList);
                R_or_W_Data.exportCsv(new File("./customerlist.csv"),above);
                
                dataList.add(a);
                boolean isSuccess = R_or_W_Data.exportCsv(new File("./customerlist.csv"),dataList);
                System.out.println(isSuccess);
                if(isSuccess){
                    JOptionPane.showMessageDialog(this, "Congratulations you have successfully change family information, you need to pay: "+Integer.parseInt(VIPtime)*60);
                }
            }else if(type=="visitor" && Age>=12){
                JOptionPane.showMessageDialog(this, "Thank you for you coming, you need to pay: 10 in this time");
            }else{
                JOptionPane.showMessageDialog(this, "Sorry you did meet the age limitation");
            }
		}
        
    }
}
