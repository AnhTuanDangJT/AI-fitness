package Clients;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.io.*;

public class Infoclient {
	private static int nextID=0;
	private int ID;
	private double weight;
	private double height;
	private String[]array;
	private int age;
	private String name , UserName;
	private String password;
	private double WHR, WHtR,TDEE,BMR;
	private double BMI;
	private double Calo;
	private double Waist, Hip;
	private boolean sex;
	private int caloGoal; //lose weight(1), maintain(2), gain muscle(3), gain muscle and lose fat(4)
	private int actFloor;
	private String sexString;
	private double BodyFat;
	// Macro nutrition
	private double protein;
	private double Fat;
	private double Carb;
	private int Fiber; // 38g/day for Men and 25g/day for Women 
	private double Water; // 3.7L/day for Men 2.7/day for Women 
	
	//Micronutrient Daily Requirements
	private int Iron;
	private int Calcium;
	private int VitaminD;
	private int Magnesium;
	private int Zinc;
	private double VitaminB12;
	private int Potassium;
	private int Sodium;
	
	//all in cm
	
	public final double Sedentary = 1.2;    //(no exercise) 1 (actFloor)
	public final double Lightly = 1.375 ;   //(1–3×/week)   2 (actFloor)
	public final double Moderately = 1.55 ; //(3–5×/week)   3 (actFloor)
	public final double Very = 1.725 ;      //(6–7×/week)   4 (actFloor)
	public final double Extra = 1.9 ;      //(2×/day)       5 (actFloor)
	
	public String[] tablesign() throws IOException {
		 ArrayList<String> array = new ArrayList<>();
		 String line;
		 BufferedReader br = new BufferedReader(new FileReader("tablesign.txt"));
	        while ((line = br.readLine()) != null) {
	            array.add(line);
	        } 
	        br.close();
	        
	        String[] allsign = new String[array.size()];
	        for(int i=0; i<array.size() ;i++) {
	        	allsign[i] = array.get(i);
	        }
	        String[] encrypt = new String[array.size()];
	        ArrayList<Integer> intarray = new ArrayList<>();
	        for(int i=0;i<encrypt.length;i++) {
	        	intarray.add(i); 	
	        }
	        Collections.shuffle(intarray);
	        for(int i=0;i<encrypt.length;i++) {
	        	encrypt[intarray.get(i)] = allsign[i];
	        }
	     return encrypt;
	}
	
	public Infoclient(String name,String password, String UserName, double weight, double height, double Waist, double Hip, int age, boolean sex, int actFloor, int caloGoal) {
			try {
			this.array = tablesign();
			}
			catch(IOException IOE) {
				System.out.println("File \"tablesign.txt\" is not found !");
		}
		this.name = name;
		this.height =height;
		this.weight = weight;
		this.UserName = UserName;
		this.password = password;
		this.Waist = Waist;
		this.Hip = Hip;
		this.age = age;
		this.caloGoal = caloGoal;
		this.actFloor = actFloor;
		this.sex = sex;
	    this.ID = nextID++;
		this.BMI = weight * 10000 / (height*height);
		this.WHR = Waist/Hip;
		this.WHtR = Waist/height;
		//true: male, false: female
		if(this.sex == true) {
			this.BMR = 10*weight+ 6.25*height - 5*age +5;
			this.sexString = "Male";
		}
		else {
			this.BMR = 10*weight+ 6.25*height - 5*age - 161;
			this.sexString = "Female";
		}
		
		if(this.actFloor == 1) {
			this.TDEE = this.BMR * Sedentary;
		}
		else if(this.actFloor == 2){
			this.TDEE = this.BMR * Lightly;
		}
        else if(this.actFloor == 3){
        	this.TDEE = this.BMR * Moderately;
		}
        else if(this.actFloor == 4){
        	this.TDEE = this.BMR * Very;
		}
        else if(this.actFloor == 5){
        	this.TDEE = this.BMR * Extra;
		}
		
		if(this.caloGoal == 1) {
			this.Calo = this.TDEE -500 ;
		}
		else if(this.caloGoal == 2) {
			this.Calo = this.TDEE ;
		}
        else if(this.caloGoal == 3) {
        	this.Calo = this.TDEE + 300 ;
		}
        else if(this.caloGoal == 4) {
        	this.Calo = this.TDEE;
        }
		this.protein = setProtein(this.caloGoal, this.weight);
		this.Fat = 0.8 * this.weight;// each gram 9 calo
		this.Carb = (this.Calo - (this.protein * 4 + this.Fat * 9)) / 4; // each gram 4 calo
		NutritionBasedOnSex(this.sex);
		BodyFatCal(this.sex, this.BMI, this.age);
		
		
	}
	
	public void BodyFatCal(boolean sex, double BMI, int age) {
		if(sex == true) { //men
			this.BodyFat = 1.20 * this.BMI + 0.23 * this.age - 16.2 ;
		}
		else if(sex == false) {//female
			this.BodyFat = 1.20 * this.BMI + 0.23 * this.age - 5.4  ;
		}
	}
	
	public void NutritionBasedOnSex(boolean sex) {
		if(sex == true) { //male
			this.Fiber = 38;
			this.Water = 3.7;
			this.Iron = 8;          //mg
			this.Calcium = 1000;    // mg
			this.VitaminD = 15 ;    // µg
			this.Magnesium = 400 ;  // mg
			this.Zinc = 11 ;        // mg
			this.VitaminB12 = 2.4 ; // mg
			this.Potassium = 3400 ; // mg
			this.Sodium = 2300 ;    // mg (max)
			this.Fiber = 38 ;       //g
			
		}
		else if(sex == false) { //female
			this.Fiber = 25;
			this.Water = 2.7;
			this.Iron = 18;          //mg
			this.Calcium = 1000;    // mg
			this.VitaminD = 15 ;    // µg
			this.Magnesium = 310 ;  // mg
			this.Zinc = 8 ;        // mg
			this.VitaminB12 = 2.4 ; // mg
			this.Potassium = 2600 ; // mg
			this.Sodium = 2300 ;    // mg (max)
			this.Fiber = 25 ;       //g
		}
		
	}
	
	public double setProtein(int caloGoal,double weight) {
		if(caloGoal == 1) {
			this.protein = 2.0 * this.weight ;
		}
		else if(caloGoal == 2) {
			this.protein = 1.4 * this.weight;
		}
        else if(caloGoal == 3) {
        	this.protein = 1.8 * this.weight;
		}
        else if(caloGoal == 4) {
        	this.protein = 2.2 * this.weight;
        }
		
		return this.protein;
	}
	
	
	public void changeTDEE(double BMR) {
		if(this.actFloor == 1) {
			this.TDEE = this.BMR * Sedentary;
		}
		else if(this.actFloor == 2){
			this.TDEE = this.BMR * Lightly;
		}
        else if(this.actFloor == 3){
        	this.TDEE = this.BMR * Moderately;
		}
        else if(this.actFloor == 4){
        	this.TDEE = this.BMR * Very;
		}
        else if(this.actFloor == 5){
        	this.TDEE = this.BMR * Extra;
		}
		changecaloGoal(this.TDEE);
	}
	
	public void changeactFloor(int actFloor) {
		this.actFloor = actFloor;
		if(this.actFloor == 1) {
			this.TDEE = this.BMR * Sedentary;
		}
		else if(this.actFloor == 2){
			this.TDEE = this.BMR * Lightly;
		}
        else if(this.actFloor == 3){
        	this.TDEE = this.BMR * Moderately;
		}
        else if(this.actFloor == 4){
        	this.TDEE = this.BMR * Very;
		}
        else if(this.actFloor == 5){
        	this.TDEE = this.BMR * Extra;
		}
		changecaloGoal(this.TDEE);
	}
	
	public void changecaloGoal(double TDEE) { // change calo following TDEE
		if(this.caloGoal == 1) {
			this.Calo = this.TDEE -500 ;
		}
		else if(this.caloGoal == 2) {
			this.Calo = this.TDEE ;
		}
        else if(this.caloGoal == 3) {
        	this.Calo = this.TDEE + 300;
		}
        else if(this.caloGoal == 4) {
			this.Calo = this.TDEE ;
		}
		this.protein = setProtein(this.caloGoal, this.weight);
	    this.Fat = 0.8 * this.weight;
	    this.Carb = (this.Calo - (this.protein * 4 + this.Fat * 9)) / 4;
	}

	public double getWeight() {
		return this.weight;
	}
	
	// WEIGHT: change BMI.BMR.TDEE
	public void setWeight(double weight) {
		this.weight = weight;
		this.BMI = this.weight * 10000 / (this.height*this.height); // change BMI
		BodyFatCal(this.sex, this.BMI, this.age);
		if(this.sex == true) {                                      // change BMR
			this.BMR = 10*weight+ 6.25*height - 5*age +5;
		}
		else {
			this.BMR = 10*weight+ 6.25*height - 5*age - 161;
		}
		changeTDEE(this.BMR);
		this.protein = setProtein(this.caloGoal, this.weight);
	    this.Fat = 0.8 * this.weight;
	    this.Carb = (this.Calo - (this.protein * 4 + this.Fat * 9)) / 4;
		
	}

	public String[] getArray() {
		return this.array;
	}

	public double getHeight() {
		return this.height;
	}

	// HEIGHT : change BMI.BMR.WHtR
	public void setHeight(double height) {
		this.height = height;
		this.BMI = this.weight * 10000 / (this.height*this.height); // change BMI
		this.WHtR = this.Waist/this.height; // change WHtR
		if(this.sex == true) {                                      // change BMR
			this.BMR = 10*weight+ 6.25*height - 5*age +5;
		}
		else {
			this.BMR = 10*weight+ 6.25*height - 5*age - 161;
		}
		changeTDEE(this.BMR);
	    this.Fat = 0.8 * this.weight;
	    this.Carb = (this.Calo - (this.protein * 4 + this.Fat * 9)) / 4;
		
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getHip() {
		return this.Hip;
	}
	
	// HIP: change WHR
	public void setHip(double hip) {
		this.Hip = hip;
		this.WHR = this.Waist/ this.Hip; // change WHR
	}

	public double getWaist() {
		return this.Waist;
	}
	
	// Waist: change WHtR
	public void setWaist(double waist) {
		this.Waist = waist;
		this.WHR = this.Waist/ this.Hip; // change WHR
		this.WHtR = (double)this.Waist/this.height; // change WHtR
	}

	public int getAge() {
		return this.age;
	}
	
	// AGE: change BMR.TDEE
	public void setAge(int age) {
		this.age = age;
		if(this.sex == true) {
			this.BMR = 10*weight+ 6.25*height - 5*age +5;
		}
		else {
			this.BMR = 10*weight+ 6.25*height - 5*age - 161;
		}
		changeTDEE(this.BMR);
		BodyFatCal(this.sex, this.BMI, this.age);
	    this.Fat = 0.8 * this.weight;
	    this.Carb = (this.Calo - (this.protein * 4 + this.Fat * 9)) / 4;
	}

	public boolean getSex() {
		return this.sex;
	}
	
	public String getsexString() {
		return this.sexString;
	}
	
	// SEX: change BMR.TDEE
	public void setSex(boolean sex) {
		this.sex = sex;
		if(sex == true) {
			this.BMR = 10*weight+ 6.25*height - 5*age +5;
			this.sexString = "Male";
		}
		else {
			this.BMR = 10*weight+ 6.25*height - 5*age - 161;
			this.sexString = "Female";
		}
		changeTDEE(this.BMR);
		NutritionBasedOnSex(sex);
		BodyFatCal(sex, this.BMI, this.age);
	    this.Fat = 0.8 * this.weight;
	    this.Carb = (this.Calo - (this.protein * 4 + this.Fat * 9)) / 4;

	}

	public double getCalo() {
		return this.Calo;
	}

    public void setCalo(double calo) {
		this.Calo = calo;
	}
    
    public double getTDEE() {
    	return this.TDEE;
    }

	public int getCaloGoal() {
		return this.caloGoal;
	}

	public void setCaloGoal(int caloGoal) {
		this.caloGoal = caloGoal;
		changecaloGoal(this.TDEE);
	}
	
	public double getBMI() {
		return this.BMI;
	}
	
	public double getWHR() {
		return this.WHR;
	}
	
	public double getWHtR() {
		return this.WHtR;
	}

	public int getID() {
		return this.ID ;
	}

	public void setID(int ID) {
		this.ID = ID;
	}
	
	public String getUserName() {
		return this.UserName;
	}
	
	public void setUserName(String UserName) {
		this.UserName = UserName;
	}
	
	
	
	
	public String toString() {
		return  "========== PERSONAL FITNESS PROFILE ==========\n" +
	            "ID: " + this.ID + "\n" +
	            "Name: " + this.name + "\n" +
	            "Username: " + this.UserName + "\n" +
	            "Age: " + this.age + " years\n" +
	            "Sex: " + this.getsexString() + "\n" +
	            "Weight: " + this.weight + " kg\n" +
	            "Height: " + this.height + " cm\n" +
	            "Waist: " + this.Waist + " cm\n" +
	            "Hip: " + this.Hip + " cm\n\n" +

	            "========== BODY INDEX ==========\n" +
	            "BMI (Body Mass Index): " + String.format("%.2f", this.BMI) + "\n" +
	            "WHR (Waist-Hip Ratio): " + String.format("%.2f", this.WHR) + "\n" +
	            "WHtR (Waist-Height Ratio): " + String.format("%.2f", this.WHtR) + "\n" +
	            "Body Fat (%): " + String.format("%.2f", this.BodyFat) + "\n\n" +

	            "========== ENERGY & CALORIES ==========\n" +
	            "BMR (Basal Metabolic Rate): " + String.format("%.2f", this.BMR) + " kcal\n" +
	            "TDEE (Total Daily Energy Expenditure): " + String.format("%.2f", this.TDEE) + " kcal\n" +
	            "Goal Calories: " + String.format("%.2f", this.Calo) + " kcal\n\n" +

	            "========== MACRONUTRIENTS ==========\n" +
	            "Protein: " + String.format("%.2f", this.protein) + " g\n" +
	            "Fat: " + String.format("%.2f", this.Fat) + " g\n" +
	            "Carbohydrates: " + String.format("%.2f", this.Carb) + " g\n" +
	            "Fiber: " + this.Fiber + " g/day\n" +
	            "Water: " + String.format("%.2f", this.Water) + " L/day\n\n" +

	            "========== MICRONUTRIENTS ==========\n" +
	            "Iron: " + this.Iron + " mg\n" +
	            "Calcium: " + this.Calcium + " mg\n" +
	            "Vitamin D: " + this.VitaminD + " IU\n" +
	            "Magnesium: " + this.Magnesium + " mg\n" +
	            "Zinc: " + this.Zinc + " mg\n" +
	            "Vitamin B12: " + String.format("%.2f", this.VitaminB12) + " µg\n" +
	            "Potassium: " + this.Potassium + " mg\n" +
	            "Sodium: " + this.Sodium + " mg\n" +
	            "===============================================";
	}

	public double getBodyFat() {
		return BodyFat;
	}

	public double getCarb() {
		return Carb;
	}

	public int getFiber() {
		return Fiber;
	}

	public double getWater() {
		return Water;
	}

	public int getIron() {
		return Iron;
	}

	public int getCalcium() {
		return Calcium;
	}

	public int getVitaminD() {
		return VitaminD;
	}

	public int getMagnesium() {
		return Magnesium;
	}

	public int getZinc() {
		return Zinc;
	}

	public int getPotassium() {
		return Potassium;
	}

	public double getVitaminB12() {
		return VitaminB12;
	}

	public int getSodium() {
		return Sodium;
	}
	
	public double getProtein() {
		return this.protein;
	}
	
	public double getFat() {
		return this.Fat;
	}

}

