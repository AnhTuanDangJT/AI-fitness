package Clients;
import java.util.*;
import java.io.*;

public class mainOne {
	
	public static void PressContinue() {
		System.out.println("Press Enter to continue...");
		Scanner sc = new Scanner(System.in);
		sc.nextLine();
		System.out.println("=====================================================================");
	}
	
	public static String EncryptPass(String[]arrayOrgin , String[]arrayclient, String passOrigin) {
		String [] passEncryptarray = new String[passOrigin.length()];
		for(int i=0; i<passOrigin.length();i++) {
			passEncryptarray[i] = Character.toString(passOrigin.charAt(i));
		}
		for(int i=0;i<passEncryptarray.length;i++) {
			for(int a=0;a<arrayOrgin.length;a++) {
			if(passEncryptarray[i].equals(arrayOrgin[a])) {
				passEncryptarray[i]= arrayclient[a];
				break;
			}
			}
		}
		String passEncrypt = "";
		for(int i=0;i<passEncryptarray.length;i++) {
			passEncrypt = passEncrypt + passEncryptarray[i];
		}
		return passEncrypt;
	}
	
    public static String DecryptPass(String[]arrayOrgin , String[]arrayclient, String passEncrypt) {
    	String [] passDecryptarray = new String[passEncrypt.length()];
    	for(int i=0; i<passEncrypt.length();i++) {
    		passDecryptarray[i] = Character.toString(passEncrypt.charAt(i));
		}
		for(int i=0;i<passDecryptarray.length;i++) {
			for(int a=0;a<arrayclient.length;a++) {
			if(passDecryptarray[i].equals(arrayclient[a])) {
				passDecryptarray[i]= arrayOrgin[a];
				break;
			}
			}
		}
		String passOrigin = "";
		for(int i=0;i<passDecryptarray.length;i++) {
			passOrigin = passOrigin + passDecryptarray[i];
		}
		return passOrigin;
	}
	
	public static void createFile(Infoclient client) {
		try {
			FileWriter writer = new FileWriter(client.getUserName()+ ".txt");
			writer.write(client.toString());
			writer.close();
		}
		catch(IOException e) {
			System.out.println("Something went wrong....");
			e.printStackTrace();
		}
	}
	
	public static String[] OrginalTableSign() throws IOException {
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
	      return allsign;
	}
	
	public static void main (String[]args) {
		Scanner sc = new Scanner(System.in);
		String[] allsign = null;
		try {
		allsign = OrginalTableSign();
		}
		catch(IOException IOE) {
			System.out.println("File \"tablesign.txt\" is not found");
		}
		
		System.out.println(" - Welcome to AI Fitness - \n");
		ArrayList<Infoclient> clientlist = new ArrayList<>();
		boolean Out = true;
		while(Out != false) {
		System.out.println("-------------Menu------------");
		System.out.println("=============================");
		System.out.println("1: Create an account");
		System.out.println("2: Sign in");
		System.out.println("3: Exit");
		System.out.println("=============================");
		System.out.println("Enter: ");
		int enternum = sc.nextInt();
		//open account and create a detail profile
		if(enternum == 1) {
			System.out.println("Enter User Name: ");
			String UserName = sc.next();
			System.out.println("Enter PassWord:  ");
			String password = sc.next();
			sc.nextLine();
			System.out.println("============= Profile has been created =============");
			System.out.println("== We need some other information to assist you ! ==");
			System.out.println("What is your name ? ");
			String name = sc.nextLine();
			System.out.println("What is your weight (in kg) ? ");
			double weight = sc.nextInt();
			System.out.println("What is your height (in cm) ? ");
			double height = sc.nextInt();
			System.out.println("What is your age ? ");
			int age = sc.nextInt();
			System.out.println("What is your waist (in cm) ? ");
			double waist = sc.nextDouble();
			System.out.println("What is your hip ?(in cm) ");
			double hip = sc.nextDouble();
			System.out.println("What is your sex ? ");
			System.out.println("press 0: Female ");
			System.out.println("press 1: Male ");
			int ansSex = sc.nextInt();
			boolean sex;
			if(ansSex == 0) {
				sex = false;
			}
			else {
				sex = true;
			}
			System.out.println("How do you want to work out for a week ?");
			System.out.println("Press 1: Sedentary (no exercise)"        );
			System.out.println("Press 2: Lightly active (1–3×/week)"     );
			System.out.println("Press 3: Moderately active (3–5×/week)"  );
			System.out.println("Press 4: Very active (6–7×/week)"        );
			System.out.println("Press 5: Extra active (2×/day)"          );
			int actFloor = sc.nextInt();
			boolean keep = true;
			while(keep != false) {
			if(actFloor == 1) {
				System.out.println("Selected Activity : Sedentary(no exercise)");
				break;
			}
			else if(actFloor == 2) {
				System.out.println("Selected Activity : Lightly active(1–3×/week)");
				break;
			}
            else if(actFloor == 3) {
            	System.out.println("Selected Activity : Moderately active(3–5×/week)");
				break;
			}
            else if(actFloor == 4) {
            	System.out.println("Selected Activity : Very active(6–7×/week)");
				break;
			}
            else if(actFloor == 5) {
            	System.out.println("Selected Activity : Extra active(2×/day)");
				break;
			}
            else {
            	keep = true;
            	System.out.println("Please try again, it is not the right input ! ");
            }
			}
			System.out.println("What is your Goal ? ");
			System.out.println("Press 1: Lose weight ");
			System.out.println("Press 2: Maintain weight ");
			System.out.println("Press 3: Gain muscle ");
			System.out.println("Press 4: Gain muscle and lose fat ");
			boolean still = true;
			int caloGoal = sc.nextInt();
			String caloGoalString = "";
			while(still != false) {
				if(caloGoal == 1) {
					System.out.println("Selected Goal Calories: Lose weight ");
					caloGoalString = "Lose weight";
					break;
				}
				else if(caloGoal == 2) {
					System.out.println("Selected Goal Calories: Maintain weight");
					caloGoalString = "Maintain weight" ;
					break;
				}
				else if(caloGoal == 3) {
					System.out.println("Selected Goal Calories: Gain muscle");
					caloGoalString = "Gain muscle";
					break;
				}
				else if(caloGoal == 4) {
					System.out.println("Selected Goal Calories: Gain muscle and lose fat");
					caloGoalString = "Gain muscle and lose fat";
					break;
				}
				else {
					still = true;
	            	System.out.println("Please try again, it is not the right input !");
				}
			}
			System.out.println("Your calorie goal and amount of protein are saved (Based on your goal)");
			Infoclient client = new Infoclient(name, password, UserName, weight, height, waist, hip, age, sex, actFloor, caloGoal);
			clientlist.add(client);
			//String name,String password, String UserName, double weight, double height, double Waist, double Hip, int age, boolean sex, int actFloor, int caloGoal
			String [] passENR = new String[client.getPassword().length()];
			//==========================================================
			// Encrypt password
			for(int i=0; i<client.getPassword().length() ;i++) {
				passENR[i] = Character.toString(client.getPassword().charAt(i));
			}
			for(int i=0; i<passENR.length ;i++) {
				for(int a=0; a<allsign.length ;a++) {
				if(passENR[i].equals(allsign[a])) {
					passENR[i] = client.getArray()[a]; //encrypted
					break;
				}
				}
			}
			String passAfter = "";
			for(int i=0; i<passENR.length ;i++) {
				passAfter = passAfter + passENR[i];
			}
			client.setPassword(passAfter);
			//==========================================================
			System.out.println(" === All the information has been saved successfully ! === ");
			System.out.println(" ====== Your password is excrypted for your safety ======= ");
			System.out.println(" =============== Here is the details below =============== ");
			
			System.out.printf("BMI (Body Mass Index): %2f\n", client.getBMI());
			if(client.getBMI() < 18.5) {
				System.out.println(" This BMI means you are Underweight");
			}
			else if(client.getBMI() >= 18.5 && client.getBMI() <= 24.9) {
				System.out.println("This BMI means you are Normal");
			}
			else if(client.getBMI() >= 25 && client.getBMI() <= 29.9) {
				System.out.println("This BMI means you are Overweight");
			}
			else if(client.getBMI() >= 30 && client.getBMI()<= 34.9){
				System.out.println("This BMI means you are Obese (Class I)");
			}
			else if(client.getBMI() >= 35 && client.getBMI()<= 39.9){
				System.out.println("This BMI means you are Obese (Class II)");
			}
			else if(client.getBMI() >= 40){
				System.out.println("This BMI means you are Obese (Class III)");
			}
			PressContinue();
			System.out.printf("WHR (Waist-to-Hip Ratio): %2f\n", client.getWHR());
			System.out.println("WHR measures fat distribution — whether someone stores more fat around the abdomen (visceral fat) or the hips/thighs (subcutaneous fat).");
			System.out.println("Visceral fat (around the waist) is more dangerous because it surrounds internal organs and raises the risk of: ");
			System.out.println("> Heart disease");
			System.out.println("> Type 2 diabetes");
			System.out.println("> Stroke");
			System.out.println("> Metabolic syndrome");
			if(client.getSex() == true) {
				if(client.getWHR() < 0.9) {
					System.out.println("Your WHR is below 0.9, which means you are in a good condition");
				}
				else {
					System.out.println("Your WHR is above 0.9, which means you are at risk of those problems below");
				}
			}
			else if(client.getSex() == false) {
				if(client.getWHR() < 0.85) {
					System.out.println("Your WHR is below 0.85, which means you are in a good condition");
				}
				else {
					System.out.println("Your WHR is above 0.85, which means you are at risk of those problems below");
				}
			}
			PressContinue();
			System.out.printf("Your body fat is: %2f%% \n", client.getBodyFat());
			PressContinue();
			System.out.printf("WHtR (Waist-to-Height Ratio): %2f\n", client.getWHtR());
			System.out.println("WHtR indicates central obesity relative to height — it’s considered more accurate than BMI for predicting cardiovascular and metabolic risks.");
			if(client.getWHtR() < 0.4) {
				System.out.println("You are too lean");
			}
			else if(client.getWHtR()>= 0.4 && client.getWHtR()<= 0.49) {
				System.out.println("You are too normal");
			}
			else if(client.getWHtR()>= 0.5 && client.getWHtR()<= 0.59) {
				System.out.println("You are at Central fat accumulation");
			}
			else if(client.getWHtR()>= 0.6) {
				System.out.println("You are High visceral fat");
			}
			PressContinue();
			System.out.println("Purpose: " + caloGoalString);
			System.out.println("The information below is suggested for your selected goal !");
			System.out.printf("Your goal calories: %2f kcal/day\n", client.getCalo());
			PressContinue();
			System.out.println("Macro nutrition: ");
			System.out.printf("Protein: %2f g/day\n", client.getProtein());
			System.out.printf("Fat: %2f g/day\n", client.getFat());
			System.out.printf("Carbohydrates: %2f g/day\n", client.getCarb());
			System.out.println("Fiber: " + client.getFiber()+ " g/day");
			System.out.printf("Water Intake: %1f L/day\n ",client.getWater());
			System.out.println(" ");
			System.out.println("=== Micronutrient Daily Requirements ===");
			System.out.println("Iron: " + client.getIron() + " mg/day");
			System.out.println("Calcium: " + client.getCalcium() + " mg/day");
			System.out.println("Vitamin D: " + client.getVitaminD() + " µg/day");
			System.out.println("Magnesium: " + client.getMagnesium()+ " mg/day");
			System.out.println("Zinc: " + client.getZinc() + " mg/day");
			System.out.printf("Vitamin B12: %1f µg/day\n", client.getVitaminB12() );
			System.out.println("Potassium: " + client.getPotassium()+ " mg/day");
			System.out.println("Sodium: " + client.getSodium()+ " mg/day");
			System.out.println("Fiber: " + client.getFiber()+ " g/day");
			PressContinue();
			System.out.println("Do you want to print a txt file for those information ?");
			System.out.println("Press 1 to print");
			System.out.println("Press any keys to go back to the menu");
			int printornot = 0;
			printornot = sc.nextInt();
			while(true) {
				if(printornot == 1) {
					createFile(client);
					System.out.println("Your file has been created: " + client.getUserName()+ ".txt");
					break;
				}
				else {
					break;
				}
			}
		}
		// Sign in the already existed profile and change the information if user wants
		if(enternum == 2) { //sign in
			if(clientlist.size() == 0) {
				System.out.println("There is no account in the system !");
				PressContinue();
			}
			else {
				loginLoop:
				while(true) {
				boolean exist = false;
				boolean backtoMenu = false;
				String usenameSign ="";
				String passwordSign = "";
				System.out.println("Enter UserName");
				usenameSign = sc.next();
				System.out.println("Enter Password");
				passwordSign = sc.next();
				Infoclient clientSignin = null;
				for(int i=0 ;i<clientlist.size();i++) {
					if(clientlist.get(i).getUserName().equals(usenameSign)) {
						String passOrigin = DecryptPass(allsign,clientlist.get(i).getArray(), clientlist.get(i).getPassword());
						if(passOrigin.equals(passwordSign)) {
							clientSignin = clientlist.get(i);
							exist = true;
							break;
						}
					}
				}
					if(exist != true) {
						System.out.println("Wrong User Name or Password !");
						while(true) {
						System.out.println("Press 1: Sign in again");
						System.out.println("Press 2: go back to menu");
						int ans = sc.nextInt();
						if(ans == 1) {
							continue loginLoop;
						}
						else if(ans == 2) {
							backtoMenu = true;
							break;
						}
						else {
							System.out.println("Wrong input");
						}
						}
					}
				
				
				if(backtoMenu == true) {
					break;
				}
				if(exist == true) {
					System.out.println("======================");
					System.out.println("===Login successful===");
					System.out.println("======================");
					System.out.println("Hi, " + clientSignin.getName() + " !");
					PressContinue();
					while(true) {
					System.out.println("=====Edit Your Profile=====");
					System.out.println("Note: Password is always encrypted for your safety of privacy");
					System.out.println("1: See your information");
					System.out.println("2: Update Password");
					System.out.println("3: Update Username");
					System.out.println("4: Update Name");
					System.out.println("5: Update Age");
					System.out.println("6: Update Sex(Male/Female)");
					System.out.println("7: Update Weight");
					System.out.println("8: Update Height");
					System.out.println("9: Update Waist");
					System.out.println("10: Update Hip");
					System.out.println("11: Update Calorie Goal");
					System.out.println("12: Update Activity Level");
					System.out.println("0: Back to Main menu");
					System.out.println("Select: ");
					int chooseSign = sc.nextInt();
					if(chooseSign == 1) {
						System.out.println(clientSignin.toString());
						PressContinue();
					}
					else if(chooseSign == 2) {
						while(true) {
						System.out.println("Enter your current password: ");
						String thispass = sc.next();
						String thispassEncryp = EncryptPass(allsign, clientSignin.getArray(),thispass);
							if(thispassEncryp.equals(clientSignin.getPassword())) {
								System.out.println("Enter your new password: ");
								String newPass = sc.next();
								String newPassEncrypt = EncryptPass(allsign, clientSignin.getArray(),newPass);
								clientSignin.setPassword(newPassEncrypt);
								System.out.println("Password updated successfully !");
								break;
							}
							else {
								System.out.println("Wrong password !");
								PressContinue();
								boolean getout = false;
								while(true) {
								System.out.println("You want to try again ? ");
								System.out.println("Enter 1: try again");
								System.out.println("Enter 2: back to menu");
								int trypass = sc.nextInt();
								if(trypass == 1) {
									break;
								}
								else if(trypass == 2) {
									getout = true;
									break;
								}
								else {
									System.out.println("Wrong input !");
									PressContinue();
								}
								}
								if(getout == true) {
									break;
								}
							}
						}
					}
					else if(chooseSign == 3) {
						System.out.print("Enter new username: ");
						sc.nextLine(); 
						clientSignin.setUserName(sc.nextLine());
			            System.out.println("Username updated successfully!");
			            continue;
						
					}
                    else if(chooseSign == 4) {
                    	System.out.print("Enter new name: ");
                    	sc.nextLine();
                        clientSignin.setName(sc.nextLine());
                        System.out.println("Name updated successfully!");
                        continue;
					}
                    else if(chooseSign == 5) {
                    	System.out.print("Enter new age: ");
                        clientSignin.setAge(sc.nextInt());
                        System.out.println("Age updated successfully!");
                        continue;
					}
                    else if(chooseSign == 6) {
                    	System.out.print("Enter sex (M/F): ");
                        char sex = sc.next().toUpperCase().charAt(0);
                        boolean isMale = (sex == 'M');
                        clientSignin.setSex(isMale);
                        System.out.println("Sex updated successfully!");
                        continue;

					}
                    else if(chooseSign == 7) {
                    	System.out.print("Enter new weight (kg): ");
                        double newWeight = sc.nextDouble();
                        clientSignin.setWeight(newWeight);
                        System.out.println("Weight updated successfully!");
                        continue;
					}
                    else if(chooseSign == 8) {
                    	System.out.print("Enter new height (cm): ");
                        double newHeight = sc.nextDouble();
                        clientSignin.setHeight(newHeight);
                        System.out.println("Height updated successfully!");
                        continue;
						
					}
                    else if(chooseSign == 9) {
                    	System.out.print("Enter new waist (cm): ");
                        double newWaist = sc.nextDouble();
                        clientSignin.setWaist(newWaist);
                        System.out.println("Waist updated successfully!");
                        continue;
					}
                    else if(chooseSign == 10) {
                    	  System.out.print("Enter new hip (cm): ");
                          double newHip = sc.nextDouble();
                          clientSignin.setHip(newHip);
                          System.out.println("Hip updated successfully!");
                          continue;
					}
                    else if(chooseSign == 11) {
                    	System.out.println("Select your calorie goal:");
                        System.out.println("1. Lose Weight");
                        System.out.println("2. Maintain Weight");
                        System.out.println("3. Gain Muscle");
                        System.out.println("4. Recomposition");
                        System.out.print("Enter: ");
                        int newGoal = sc.nextInt();
                        clientSignin.setCaloGoal(newGoal);
                        System.out.println("Calorie goal updated successfully!");
                        continue;
					}
                    else if(chooseSign == 12) {
                    	System.out.println("Select Activity Level:");
                        System.out.println("1. Sedentary (no exercise)");
                        System.out.println("2. Lightly active (1–3 days/week)");
                        System.out.println("3. Moderately active (3–5 days/week)");
                        System.out.println("4. Very active (6–7 days/week)");
                        System.out.println("5. Extra active (twice daily training)");
                        System.out.print("Enter: ");
                        int newAct = sc.nextInt();
                        clientSignin.changeactFloor(newAct);
                        System.out.println("Activity level updated successfully!");
                        continue;

					}
                    else if(chooseSign == 0) {
                    	System.out.println("Would you like to print or save your updated profile?");
                    	System.out.println("1: Print to screen");
                    	System.out.println("2: Save to file");
                    	int opt = sc.nextInt();
                    	if(opt == 1) System.out.println(clientSignin.toString());
                    	else if(opt == 2) createFile(clientSignin);
                    	break;
                    }
					}
					break;
				}
				}
			}
		}
		
		//
		if(enternum == 3) {
			break;
		}
		
		}
		System.out.println("Thank you for using my model !");
		System.out.println("Goodbye !");
		sc.close();
	}
	
	
	
	

}

