
@SuppressWarnings("rawtypes")
public class StarStorage implements Comparable{

	private int Hip;		//Hipparcos Star Number
	private double Vmag;	//Visual Band Magnitude (log measurement of brightness)
	private double Ra;		//Right Ascension (Degrees) EARTH COORDINATES
	private double De;		//Declination (Degrees) EARTH COORDINATES
	private double Plx;		//Parallax Angle (Milli-arc-seconds or mas) 1000/Plx = parsecs
	private double MV;		//Magnitude = M_V = V + 5*log(Par/100)
	private double BV;		//Color of Star (magnitude of B - V) HR Diagram B and V values.
	private String SP; 		//Spectral classification

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
	public StarStorage( String hip, String vmag, 
						String ra, String de, String plx, 
						String bv, String mv, String sp){
		Hip = Integer.parseInt(hip);
		Vmag = Double.parseDouble(vmag);
		
		Ra = Double.parseDouble(ra);
		De = Double.parseDouble(de);
		Plx = Double.parseDouble(plx);

		BV = Double.parseDouble(bv);
		MV = Double.parseDouble(mv);
		SP = sp;
		
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
	public void ChangeStorage( 	String hip, String vmag, 
								String ra, String de, String plx, 
								String bv, String mv, String sp){
		
		Hip = Integer.parseInt(hip);
		Vmag = Double.parseDouble(vmag);
		
		Ra = Double.parseDouble(ra);
		De = Double.parseDouble(de);
		Plx = Double.parseDouble(plx);

		BV = Double.parseDouble(bv);
		MV = Double.parseDouble(mv);
		SP = sp;
		
	}
	
	/**
	 * Start a new instance of a star object, and all the values.
	 * 
	 * @param hip Hipparcos Star Number
	 * @param vmag Visual Band Magnitude (log measurement of brightness)
	 * @param ra Right Ascension (Degrees) EARTH COORDINATES
	 * @param de Declination (Degrees) EARTH COORDINATES
	 * @param plx Parallax Angle (Milli-arc-seconds or mas) 1000/Plx = parsecs
	 * @param pmra Proper Motion in Right Ascension (Milli-arc-seconds or mas) CELESTIAL COORD.
	 * @param pmde Proper Motion in Declination (Milli-arc-seconds or mas) CELESTIAL COORD.
	 * @param eplx Error bounds for Parallax Measurement
	 * @param bv Color of Star (magnitude of B - V) HR Diagram B and V values.
	 */
	public void ChangeStorage( 	int hip, double vmag, 
								double ra, double de, double plx, 
								double mv, double bv, String sp){
		
		Hip = hip;
		Vmag = vmag;
		
		Ra = ra;
		De = de;
		Plx = plx;
		
		BV = bv;
		MV = mv;
		SP = sp;
		
	}
	

	//Mutators Strings
	public void setHip(String s){	Hip = Integer.parseInt(s);}
	public void setVmag(String s){	Vmag = Double.parseDouble(s);}
	public void setRa(String s){	Ra = Double.parseDouble(s);}
	public void setDe(String s){	De = Double.parseDouble(s);}
	public void setPlx(String s){	Plx = Double.parseDouble(s);}
	public void setMV(String s){	MV = Double.parseDouble(s);}
	public void setBV(String s){	BV = Double.parseDouble(s);}
	public void setSP(String s){	SP = s;}
	
	//Mutators Numbers
	public void setHip(int n){ 		Hip = n;}
	public void setVmag(double n){	Vmag = n;}
	public void setRa(double n){	Ra = n;}
	public void setDe(double n){	De = n;}
	public void setPlx(double n){	Plx = n;}
	public void setMV(double n){	MV = n;}
	public void setBV(double n){	BV = n;}
	
	//numbers - Accessers
	public int getHip(){		return Hip;}
	public double getVmag(){	return Vmag;}
	public double getRa(){		return Ra;}
	public double getDe(){		return De;}
	public double getPlx(){		return Plx;}
	public double getMV(){		return MV;}
	public double getBV(){		return BV;}
	
	//String value - Accessers
	public String getHipString(){	return Integer.toString(Hip);}
	public String getVmagString(){	return Double.toString(Vmag);}
	public String getRaString(){	return Double.toString(Ra);}
	public String getDeString(){	return Double.toString(De);}
	public String getPlxString(){	return Double.toString(Plx);}
	public String getMVString(){	return Double.toString(MV);}
	public String getBVString(){	return Double.toString(BV);}
	public String getSPString(){	return SP;}
	
	public String toString(){
        
		String temp = 	"\t" + Integer.toString(Hip) + "\t" + 
						Double.toString(Vmag) + "\t" + 
						Double.toString(Ra) + "\t" + 		
						Double.toString(De) + "\t" + 
						Double.toString(Plx) + "\t" +
						Double.toString(BV) + "\t" +
						Double.toString(MV) + "\t" +
						SP;
		return temp;
	}
	@Override
	public boolean equals(Object h){
		boolean out = false;
		if(h.equals(getHipString())){
			out = true;
		}
		return out;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		int out =0;
		if(((StarStorage)o).getHip() < this.Hip){
			out = 1;
		}
		else if(((StarStorage)o).getHip() == this.Hip){
			out = 0;
		}
		else{
			out = -1;
		}
		return out;
	}

	


	
}
