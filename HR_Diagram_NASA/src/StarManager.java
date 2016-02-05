
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;




public class StarManager {

	private ArrayList<StarStorage> starlist;

	
	public StarManager(){
		starlist = new ArrayList<StarStorage>();

	}
	
	
	public ArrayList<StarStorage> getList(){
		
		return starlist;
		
	}

	/**
	 * Start a new instance of a star object, and all the values.
	 * 
	 * @param hip Hipparcos Star Number
	 * @param vmag Visual Band Magnitude (log measurement of brightness)
	 * @param ra Right Ascension (Degrees) EARTH COORDINATES
	 * @param de Declination (Degrees) EARTH COORDINATES
	 * @param plx Parallax Angle (Milli-arc-seconds or mas) 1000/Plx = parsecs
	 * @param bv Color of Star (magnitude of B - V) HR Diagram B and V values.
	 * @param mv - visual absolute magnitude;  M_V = V + 5*log(Par/100)
	 * @param sp - Spectral Classification
	 */

	@SuppressWarnings("unchecked")
	public void addStar(String hip, String ra, String de, String plx,
						String vmag, String bv, String sp) throws Exception{
		
		
		String[] temp = new String[8];


		temp[0] = hip;
		temp[1] = vmag;
		temp[2] = ra;
		temp[3] = de;
		temp[4] = plx;
		temp[5] = bv;
		temp[7] = sp;
		
		if(!hip.matches("-?\\d+(\\.\\d+)?")){
			throw new Exception("Error: Invalid Input \n"
								+"The Hipparcos Star Number (HIP Number) must be a whole number.");
		}
		else if(!plx.matches("-?\\d+(\\.\\d+)?")){
			throw new Exception("Error: Invalid Input\n"
					+ "The Parallax must be a number with a decimal in Milli-Arc-Seconds.\n"
					+ "Example: 14.0");
		}
		else if(!vmag.matches("-?\\d+(\\.\\d+)?")){
			throw new Exception("Error: Invalid Input\n"
					+ "The V Apparent must be a number with a decimal in Johnson Magnitude.\n"
					+ "Example: 0.0");
		}
		else if(!bv.matches("-?\\d+(\\.\\d+)?")){
			throw new Exception("Error: Invalid Input\n"
								+ "The B-V must be a number with a decimal in Johnson Magnitude.\n"
								+ "Example: 0.0");
		}
		else if(!ra.matches("-?\\d+(\\.\\d+)?")){
			throw new Exception("Error: Invalid Input\n"
								+ "The Right Ascension must be a number with a decimal in Degrees.\n"
								+ "Example: 360.0");
		}
		else if(!de.matches("-?\\d+(\\.\\d+)?")){
			throw new Exception("Error: Invalid Input\n"
					+ "The Declination must be a number with a decimal in Degrees.\n"
					+ "Example: 360.0");
		}
		else if(sp.equalsIgnoreCase("") || sp.equals(null)){
			temp[7] = "(N/A)";
		}
		
		//calculate and convert here
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		double tempmv = calcVmag(Double.parseDouble(temp[1]), Double.parseDouble(temp[4]));
		temp[6] = df.format(tempmv);
				
		
		StarStorage StarTemp = new StarStorage(	temp[0], temp[1], 
												temp[2], temp[3], temp[4], 
												temp[5], temp[6], temp[7]);
		
		
		if(starlist.contains(StarTemp)){
			int optionPane = JOptionPane.showConfirmDialog(null, 
						"This Star already exist in the list.\n"
				    + "Would you like to edit existing star?\n", "Confirm Change",JOptionPane.YES_NO_OPTION);
			
			if(optionPane ==0){
				

					starlist.set(starlist.indexOf(StarTemp), StarTemp);
				

			}
			else{
				throw new Exception("Star Was Not Added.");
			}
		}
		else{
		
				starlist.add(StarTemp);
				Collections.sort(starlist);
		

		}

		
	}



	
	
	public double calcVmag(double vApparent, double Paralax){
		
		//Magnitude = M_V = V + 5*log(Par/100)	
		double out = 0.0;
	
			out = vApparent + (5*Math.log10(Paralax/100));
			
		return out;
	}
	
	public void deleteStars(int rowFirst, int rowCount){
		
		for(int j = 0; j < rowCount; j++){
			
			starlist.remove(rowFirst);
			
		}
	}
	
	public void SaveStars(File selectedFile)throws FileNotFoundException{
		PrintWriter input = new PrintWriter(selectedFile + ".txt");
		String firstline = "\tHip\tV\tRA (deg)\tDec (deg)\tPar(mas)\tB-V\tM_V\tSp";

		input.println(firstline);
		for(int j = 0; j < starlist.size(); j++){
		
			input.println(starlist.get(j).toString());
		}
		
		input.close();
	}
	
	@SuppressWarnings("unchecked")
	public void ReadStarsOther(String line){

		
		String[] temp = line.split("\\s+");

		StarStorage StarTemp;

		if(temp.length < 9){
			

			
			StarTemp = new StarStorage(	temp[1], temp[2], 
					temp[3], temp[4], temp[5], 
					temp[6], temp[7], "(N/A)");
		}
		else{
			

			
			StarTemp = new StarStorage(	temp[1], temp[2], 
										temp[3], temp[4], temp[5], 
										temp[6], temp[7], temp[8]);
		}	
		
		if(starlist.contains(StarTemp)){

			starlist.set(starlist.indexOf(StarTemp), StarTemp);

			
		}
		else{
			starlist.add(StarTemp);
		}
		Collections.sort(starlist);
	}
	@SuppressWarnings("unchecked")
	public void ReadStars(String line){

		String[] temp = line.split("\\s+");

		StarStorage StarTemp;

		if(temp.length < 9){
			

			
			StarTemp = new StarStorage(	temp[1], temp[2], 
					temp[3], temp[4], temp[5], 
					temp[6], temp[7], "(N/A)");
		}
		else{
			

			
			StarTemp = new StarStorage(	temp[1], temp[2], 
										temp[3], temp[4], temp[5], 
										temp[6], temp[7], temp[8]);
		}	
		
		if(starlist.contains(StarTemp)){

			starlist.set(starlist.indexOf(StarTemp), StarTemp);

			
		}
		else{
			starlist.add(StarTemp);
		}
		Collections.sort(starlist);
	}
	
	//TODO writeStars method to write to a file.
	
	public void test(){
		ArrayList<Double> valueV = new ArrayList<Double>();
		ArrayList<Double> valueB = new ArrayList<Double>();
		for(StarStorage a : starlist){
			
			valueV.add(a.getMV());
			valueB.add(a.getBV());
			
//			double o = (15.0 - a.getVmag() - (5.0 *Math.log(a.getPlx()))  )/2.5;
//			System.out.println(a.getVmag() + "  " + a.getPlx() + "  "+o);
		}
		
		Collections.sort(valueV);
		Collections.sort(valueB);
		
		System.out.println(valueV.size());
		for(int i = 0; i < valueV.size(); i++){
			System.out.println(valueV.get(i) + "   " + valueB.get(i));
		}
		
	}
	
	
	
	
}

