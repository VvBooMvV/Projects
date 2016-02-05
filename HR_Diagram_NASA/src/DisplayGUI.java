import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 * 
 * @author Harry Kim
 *
 */
public class DisplayGUI {
	

	ArrayList<String> readlines = new ArrayList<String>();
	StarManager Man = new StarManager();
	JFrame frame = new JFrame();
	
	String[] columns = {"HIP Num",  "B-V Index", "V Magnitude","R Ascension",
						"Declination", "Parallax", "V Apparent",  "Spectrum"};
	
	@SuppressWarnings("serial")
	DefaultTableModel model = new DefaultTableModel(){
		public boolean isCellEditable(int row, int column){
	      return false;//This causes all cells to be not editable
	    }
		
		
	};

	String[] columnToolTip = {	"Hipparcos Star Number", 
								"Magnitude of (B - V) Color Index.",
								"V Magnitude: MV = V_Apparent + 5*ln(Parallax/100)",
								"Right Ascension (Degrees): Earth Coordinates",
								"Declination (Degrees): Earth Coordinates",
								"Parallax (Milli-Arc-Seconds)",
								"V Apparent Magnitude (Johnson)", 
								"Spectrum Classification (Type)"};
	
	@SuppressWarnings("serial")
	JTable table = new JTable(model){
		
	    protected JTableHeader createDefaultTableHeader() {
	        return new JTableHeader(columnModel) {
	            public String getToolTipText(MouseEvent e) {
//	                String tip = null;
	                java.awt.Point p = e.getPoint();
	                int index = columnModel.getColumnIndexAtX(p.x);
	                int realIndex = 
	                        columnModel.getColumn(index).getModelIndex();
	                return columnToolTip[realIndex];
	            }
	        };
	    }
	};
	
	JScrollPane pane = new JScrollPane(table);
	JPanel HRgraph;

	JButton btnEdit = new JButton("Edit Line");
	JButton btnAdd = new JButton("Add Star");
	JButton btnDelete = new JButton("Remove Star");
	JButton btnRead = new JButton("Upload Data");
	JButton btnSave = new JButton("Save Data");

	JPanel pan = new JPanel();
	
	boolean plotSwitch  = false;

	
	ArrayList<StarStorage> selectedStar = new ArrayList<StarStorage>();
	JLabel link = new JLabel("<html> Data Set Source (Pre-Selected) : <a href=\"\">VizieR Website</a></html>");

	JPanel starPan = new JPanel();
	JLabel lblHip = 	new JLabel("HIP Number (XXX):      ");
	JLabel lblRa = 		new JLabel("Right Ascension(Deg):");
	JLabel lblDe = 		new JLabel("Declination (Deg):  ");
	JLabel lblPlx =		new JLabel("Parallax (xx.xx):     ");
	JLabel lblVmag = 	new JLabel(" V Apparent (x.xx):      ");
	JLabel lblBV = 		new JLabel("B-V Index (x.xx):     ");
	JLabel lblSP = 		new JLabel("Spectrum (OBAFGKM): ");
	JLabel lblSpacer = 	new JLabel(" V Magnitude  [Will Be Calculated]   ");
	JLabel lblgap = new JLabel("                *Fill in all the boxes before adding to the list (Spectrum can be excluded).                  ");

	
	JTextField txtHip = 	new JTextField(7);
	JTextField txtRa = 		new JTextField(7);
	JTextField txtDe = 		new JTextField(7);
	JTextField txtPlx = 	new JTextField(7);
	JTextField txtVmag = 	new JTextField(7);
	JTextField txtBV = 		new JTextField(7);
	JTextField txtSP = 		new JTextField(7);
	
	boolean isAdding = true;  //fixes weird error when adding, deleting or reading
	
	public DisplayGUI(){
		frame.setTitle("Document Manager");
		frame.setSize( 1330,  730);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		frame.setTitle("Hertzsprung-Russel Diagram");

	

		//adds columns for model
		for(String a : columns){
			model.addColumn(a);
		}
			
		Dimension buttonDimen = new Dimension(110,25);
		
		btnAdd.setPreferredSize(buttonDimen);
		btnEdit.setPreferredSize(buttonDimen);
		btnDelete.setPreferredSize(buttonDimen);
		btnSave.setPreferredSize(buttonDimen);
		btnRead.setPreferredSize(buttonDimen);
		
		pan.setBorder(BorderFactory.createTitledBorder("Actions"));
		pan.add(btnAdd);
		pan.add(btnEdit);
		pan.add(btnDelete);
		pan.add(btnSave);
		pan.add(btnRead);
		pan.setToolTipText("<html><b>Add Star Button:</b> Adds the information above to the list."+
							"<br><b>Edit Line Button:</b> Auto-fills area to be added.</br>"+
							"<br><b>Remove Star:</b> Delete the selected star (table above).</br><html>"
							+ "<br><b>Save Data:</b> Save to a text file. Example: \"File_Name.txt\"</br>"
							+ "<br><b>Upload Data:</b>Upload a data set file. Try: \"StarData.txt\"</br>");
		pan.setBounds(700, 510, 140, 180);  //(x, y, width, height)

		
		table.setToolTipText("Highlight row(s) to plot a purple point(s) of selected star(s).");
		pane.setBounds(700, 10, 600, 500);
		frame.add(pan);
		frame.add(pane);
		
		HRgraph = new HRGraph();
		HRgraph.setBounds(0, 0, 700, 700);
		frame.add(HRgraph);
		
		link.setBounds(940, 670, 280, 20);
		frame.add(link);
		
		lblSpacer.setToolTipText("V_Magnitude = V_Apparent + 5*ln(Parallax/100)");
		starPan.setBorder(BorderFactory.createTitledBorder("Add (Edit) Star"));
		starPan.setBounds(845, 510, 460, 160);


		starPan.add(lblgap);
		starPan.add(lblHip);
		starPan.add(txtHip);
		
		starPan.add(lblPlx);
		starPan.add(txtPlx);
		
		starPan.add(lblVmag);
		starPan.add(txtVmag);
		
		starPan.add(lblBV);
		starPan.add(txtBV);
		
		starPan.add(lblRa);
		starPan.add(txtRa);
		
		starPan.add(lblDe);
		starPan.add(txtDe);
		
		starPan.add(lblSP);
		starPan.add(txtSP);
		
		starPan.add(lblSpacer);

		frame.add(starPan);
		
		JRootPane enterPane = SwingUtilities.getRootPane(btnAdd);
		enterPane.setDefaultButton(btnAdd);
		
		
		
		//TODO finish add, edit, and delete gui, finish actionlisteners
		
		link.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("http://vizier.u-strasbg.fr/viz-bin/"+
                    		"VizieR-4?-ref=VIZ554825b5285b&-to=-2b&-from=-2&-this=-2&%2F%2Fsource=I%2F239%2F"+
                    		"hip_main&%2F%2Ftables=I%2F239%2Fhip_main&-out.max=1000&%2F%2FCDSportal=http%3A%2"+
                    		"F%2Fcdsportal.u-strasbg.fr%2FStoreVizierData.html&-out.form=HTML+Table&%2F%2Fouta"+
                    		"ddvalue=default&-oc.form=sexa&-nav=cat%3AI%2F239%26tab%3A%7BI%2F239%2Fhip_main%7D%"+
                    		"26key%3Asource%3DI%2F239%2Fhip_main%26HTTPPRM%3A&-c=&-c.eq=J2000&-c.r=++2&-c.u=arcmin"+
                    		"&-c.geom=r&-source=I%2F239%2Fhip_main&-order=I&recno=&-out=HIP&HIP=&Proxy=&RAhms=&DEdms"+
                    		"=&-out=Vmag&Vmag=&VarFlag=&r_Vmag=&-out=RA%28ICRS%29&RA%28ICRS%29=&-out=DE%28ICRS%29&DE%"+
                    		"28ICRS%29=&AstroRef=&-out=Plx&Plx=&pmRA=&pmDE=&e_RAdeg=&e_DEdeg=&e_Plx=&e_pmRA=&e_pmDE=&DE"+
                    		"%3ARA=&Plx%3ARA=&Plx%3ADE=&pmRA%3ARA=&pmRA%3ADE=&pmRA%3APlx=&pmDE%3ARA=&pmDE%3ADE=&pmDE%"
                    		+ "3APlx=&pmDE%3ApmRA=&F1=&F2=&BTmag=&e_BTmag=&VTmag=&e_VTmag=&m_BTmag=&-out=B-V&B-V=&e_B-"
                    		+ "V=&r_B-V=&V-I=&e_V-I=&r_V-I=&CombMag=&Hpmag=&e_Hpmag=&Hpscat=&o_Hpmag=&m_Hpmag=&Hpmax=&"
                    		+ "HPmin=&Period=&HvarType=&moreVar=&morePhoto=&CCDM=&n_CCDM=&Nsys=&Ncomp=&MultFlag=&Sourc"
                    		+ "e=&Qual=&m_HIP=&theta=&rho=&e_rho=&dHp=&e_dHp=&Survey=&Chart=&Notes=&HD=&BD=&CoD=&CPD=&%"
                    		+ "28V-I%29red=&-out=SpType&SpType=&r_SpType=&-ignore=HIPep%3D*&HIPep=HIPep&-ignore=Erratum"
                    		+ "%3D*&Erratum=Erratum&%2F%2Fnoneucd1p=on&-file=.&-meta.ucd=2&-meta=1&-meta.foot=1&-usenav="
                    		+ "1&-bmark=GET"));  //link for Dataset source with correct selections.
                } catch (URISyntaxException | IOException ex) {
                	isAdding = true;
                }
            }
        });
	
		table.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						if(isAdding){
							for(int i =0; i <table.getSelectedRowCount(); i++){
	
								int index = table.getSelectedRow() + i;
								
								selectedStar.add(Man.getList().get(index));
								
							}
	
							HRgraph.repaint();
						}						
						
					}//end of valueChanged method
					
				}//end of ListSelectionListener
		        
		);//end of table Selection Listener
	    
		//Button Listeners
		btnRead.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			
				try {
					isAdding = false;
					readFile();
					replot();
					isAdding = true;
				
				} 
				catch (FileNotFoundException e1) {
					isAdding = true;
				}
			}
		}); //end of action listener
		
		btnDelete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
		
				
					try {
						int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete?",
																	"Confirm Delete",JOptionPane.YES_NO_OPTION);
							if(confirm == 0){
								deleteStar();
							}
						
					} catch (Exception e1) {
				
						isAdding = true;
						JOptionPane.showMessageDialog(null,
								e1.getMessage(),
							    "Select Error",
							    JOptionPane.ERROR_MESSAGE);
					}
				

				//TODO plot points

			}
		}); //end of action listener
		
		btnAdd.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				try {
					
					isAdding = false;
					Man.addStar(txtHip.getText(), txtRa.getText(), txtDe.getText(), txtPlx.getText(), 
								txtVmag.getText(), txtBV.getText(), txtSP.getText());
					
					replot();

					
					isAdding = true;
				} catch (Exception e1) {
					isAdding = true;
					JOptionPane.showMessageDialog(null,
							e1.getMessage(),
						    "Input Error",
						    JOptionPane.ERROR_MESSAGE);
				}
					
				
			}
		}); //end of action listener
		
		btnEdit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			
				if(model.getRowCount() == 0 || table.getSelectedRow() < 0){
					JOptionPane.showMessageDialog(null,
							"Please add a star, and select it in the table above.",
						    "Missing Value",
						    JOptionPane.ERROR_MESSAGE);
				}
				else if(table.getSelectedRowCount() > 1){
					JOptionPane.showMessageDialog(null,
							"Please only select one star before editing/adding.",
						    "Select One",
						    JOptionPane.ERROR_MESSAGE);
				}
				else{
								
					txtHip.setText(model.getValueAt(table.getSelectedRow(), 0).toString());
					txtPlx.setText(model.getValueAt(table.getSelectedRow(), 5).toString());
					txtVmag.setText(model.getValueAt(table.getSelectedRow(), 6).toString());
					txtBV.setText(model.getValueAt(table.getSelectedRow(), 1).toString());
					txtRa.setText(model.getValueAt(table.getSelectedRow(), 3).toString());
					txtDe.setText(model.getValueAt(table.getSelectedRow(), 4).toString());
					txtSP.setText(model.getValueAt(table.getSelectedRow(), 7).toString());
				}
				//TODO Edit a selected button.  may need a ItemselectionListner
			}
		}); //end of action listener
				
		btnSave.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			
				//TODO save current list
				try {
					saveFile();
				} catch (FileNotFoundException e1) {
					isAdding = true;
				}
			}
		}); //end of action listener
				
//		starPan.repaint();
		frame.setVisible(true);
				
	}//end of constructor
	
	public void replot(){
		model.getDataVector().clear();
		ArrayList<StarStorage> SSgui = Man.getList();
		String[] temp = new String[8];

		for(int i = 0; i < SSgui.size(); i++){

	
			temp[0] = SSgui.get(i).getHipString();
			temp[1] = SSgui.get(i).getBVString();
			temp[2] = SSgui.get(i).getMVString();
			temp[3] = SSgui.get(i).getRaString();
			temp[4] = SSgui.get(i).getDeString();
			temp[5] = SSgui.get(i).getPlxString();
			temp[6] = SSgui.get(i).getVmagString();
			temp[7] = SSgui.get(i).getSPString();
			model.addRow(temp);
		}
		

		
		plotSwitch = true;
		HRgraph.repaint();
	}

	public void deleteStar() throws Exception{
		
		if(table.getSelectedRowCount() < 1){
			throw new Exception("Please select a star in the table above to remove.");
		}
		else{

			isAdding = false;
				
			Man.deleteStars(table.getSelectedRow(), table.getSelectedRowCount());
			replot();
		

			isAdding = true;
		}
	}
	public void saveFile() throws FileNotFoundException{
		JFileChooser chooser = new JFileChooser();
	
		chooser.setDialogTitle("Save As: .txt");

		if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
			
			try {
				File selectedFile = chooser.getSelectedFile();

				
				Man.SaveStars(selectedFile);
				
			} //End of try
			catch (FileNotFoundException e) {
				isAdding = true;
				JOptionPane.showMessageDialog(null, "File not found.");
		}	
		}
	}
	public void readFile() throws FileNotFoundException{
		
		JFileChooser chooser = new JFileChooser();
		Scanner input;		
		
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
			
			try {
				File selectedFile = chooser.getSelectedFile();
				input = new Scanner(selectedFile);
				ArrayList<String> temp = new ArrayList<String>();
				String testline = "    HIP   V     RA (deg)    Dec (deg)   Par(mas) B-V   M_V  Sp";
//				String testline2 = "\tHip\tV\tRA (deg)\tDec (deg)\tPar(mas)\tB-V\tM_V\tSp";
				String in = input.nextLine();
			
//				if(!in.equalsIgnoreCase(testline) || !in.equalsIgnoreCase(testline2)){
//					throw new FileNotFoundException();
//				}
				
				while(input.hasNext()){
					String line = input.nextLine();
					temp.add(line);
				}
				
				if(in.equalsIgnoreCase(testline)){

					for(int i = 0; i < temp.size(); i++){

						Man.ReadStars(temp.get(i));
					}
				}
				else{

					for(int i = 0; i < temp.size(); i++){

						Man.ReadStarsOther(temp.get(i));
					}
				}

				
				temp.clear();
			

				
				
				
			} //End of try
			catch (FileNotFoundException e) {
				isAdding = true;
				JOptionPane.showMessageDialog(null, "You have choosen an incorrect file. \n"+
						"The file must have the following on the first line: \n"
						+ "HIP   V     RA (deg)    Dec (deg)   Par(mas) B-V   M_V  Sp");
			}			
		}//End of if(file_selected)
	}//End of readFile Method
	

	
	@SuppressWarnings("serial")
	public class HRGraph extends JPanel{
		public void paintComponent(Graphics g){

			Graphics2D Graph = (Graphics2D) g;
			Graph.setColor(new Color(40,40,40));
			Graph.fillRect(0, 0, 680, 680);
			

			
			Graph.setColor(Color.WHITE);
			Graph.drawLine(40, 640, 670, 640); //x-axis
			Graph.drawLine(40, 10, 40, 640); //y-axis
			
			for(int i = 0; i < 12; i++){
				int marker = 52;
				marker *=i;
				int yMarks = -4 + 2*i;
				double xMarks = -0.2 + 0.2 *i;
				Graph.drawLine(35, marker+25, 40, marker+25); //markers on y
				Graph.drawString(Integer.toString(yMarks), 18, marker+30);
				Graph.drawLine(marker+80, 645, marker+80, 640 ); //markers on x
				Graph.drawString(String.format( "%.1f", xMarks) , marker+70, 660);
			}
			

			
			Graph.rotate(270*Math.PI/180);//rotate horizontal line on the left axis
			Graph.drawString("V  Magnitude", -350, 10);  //String, x (must be negative), and y is normal
			Graph.rotate(90*Math.PI/180);  //rotates everything back to normal				
			Graph.drawString("B-V Index", 320, 675);

			if(plotSwitch){
				

				for(int j = 0; j< Man.getList().size(); j++){
					
					int R=255,
						G=255,
						B=255;
					double 	vMag = Man.getList().get(j).getMV(),
							BV = Man.getList().get(j).getBV(),
							Vtemp = 4.0, //absolute min value (move every line up by 4)
							BVtemp = 0.4, //absolute min value (move every line up by 0.4)
							vPlot, bvPlot;
					
					Vtemp += vMag;
					BVtemp += BV;
					vPlot = Vtemp/24.0;
					bvPlot = BVtemp/2.4;
					
					if(BVtemp < 0.4){
						R = 215-(int) Math.abs((255 * (BV/.4)));
						G = 215-(int) Math.abs((255 * (BV/.4)));
						B = 255;
					}
					else if(BVtemp < 0.7 && BVtemp > 0.5){
						R = 255;
						G = 255 - (int) Math.abs((40 * (BV/.7)));
						B = 255 - (int) Math.abs((255 * (BV/.7)));
					}
					else if(BVtemp < 1.1 && BVtemp > 0.65){
						R = 255;
						G = 255 - (int) Math.abs((95 * (BV/1.0)));
						B = 0;
					}
					else if(BVtemp < 1.7 && BVtemp > 1.0){
						R = 255;
						G = 255- (int) Math.abs((200 * (BV/2.0)));
						B = 0;
					}
					else if(BVtemp < 3 && BVtemp > 1.7){
						R = 255;
						G = 255- (int) Math.abs((235 * (BV/2.0)));
						B = 0;
					}
					Color starColor = new Color(R,G,B);
					Graph.setColor(starColor);
					Graph.fillOval((int)Math.round(bvPlot*680), (int)Math.round(vPlot * 680), 4, 4);

					
				}
				for(int n = 0; n < selectedStar.size(); n++){
					double 	vMag = selectedStar.get(n).getMV(),
							BV = selectedStar.get(n).getBV(),
							Vtemp = 4.0, //absolute min value (move every line up by 4)
							BVtemp = 0.4, //absolute min value (move every line up by 0.4)
							vPlot, bvPlot;
					
					Vtemp += vMag;
					BVtemp += BV;
					vPlot = Vtemp/24.0;
					bvPlot = BVtemp/2.4;
					Graph.setColor(new Color(255,0,255));
					Graph.fillOval((int)Math.round(bvPlot*680), (int)Math.round(vPlot * 680), 4, 4);
				}
				
				selectedStar.clear();
				
			}
			
			
		}
		
		
	}

	
}//End of DisplayGUI Class
