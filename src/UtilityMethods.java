
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.filechooser.FileSystemView;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import list.ADTExceptions;
import list.IndexedList;
import restaurantLookup.GeoLocation;
import restaurantLookup.Restaurant;

public class UtilityMethods {
	 protected File f = new File(FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + "\\" + "userInfo.dat");
	
	 private final static int rowcount = 59;
	 
	 
	//Utility Methods (REGEX)http://www.ocpsoft.org/opensource/guide-to-regular-expressions-in-java-part-1/
	 boolean passwordCheck(String password){
		Pattern reg = Pattern.compile("((?=.*[a-z])(?=.*d)(?=.*[@#$%])(?=.*[A-Z]))");
		Matcher eml = reg.matcher(password);
		if(eml.find()==true){
			return true; 
		}else{return false;}
	}
	 boolean emailCheck(String email){
		Pattern reg = Pattern.compile("^(.+)@(.+)$");
		Matcher eml = reg.matcher(email);
		if(eml.find()==true){
			return true; 
		}else{return false;}
	}
	 boolean phoneNumberCheck(String phoneNumber){
		Pattern reg = Pattern.compile("^\\d{0,3}[^s]\\d{0,3}[^s]\\d{0,10}"); //do you know how many phone number styles there are? A LOT hopefully this will handle most of them.
		Matcher eml = reg.matcher(phoneNumber);
		if(eml.find()==true){
			return true; 
		}else{return false;}
	 }
	 public boolean ssnCheck(String ssn){
	 	Pattern reg = Pattern.compile("^\\d{3}-\\d{2}-\\d{4}$");
	 	Matcher eml = reg.matcher(ssn);
	 	if(eml.find()==true){
	 		return true; 
	 	}else{return false;}
	 }
	 public boolean usernameCheck(String username) throws IOException{
		boolean found; 
		FileInputStream fis;
		ObjectInputStream ois = null;
		//List<String> arr = new ArrayList<String>(); 
		IndexedList arr = new IndexedList<String>();
		
		
		try { //this is one of the dirtier ways I've written something in a while...
			//Load serialized file to an array, now check if the thing has a match
			fis = new FileInputStream("src/restaurantLookup/relFiles/userInfo.dat");
			
			while(true){
			ois = new ObjectInputStream(fis);
			User u = (User) ois.readObject(); 
			arr.Add(u.getUsername()); 
			//System.out.println(u.getUsername() + " " + arr.toString());
			}
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}  catch (IOException e) {
			//e.addSuppressed(e); //Because when it does this, it's 
			//ignore
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (ADTExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
				ois.close();
		}
		if(arr.contains(username)){
			found = true; 
			return found; 
		}else{found = false; return found;}
		//return found;
	 }

	 public void writeFile(User u){
		
	        try{ 	
	        		if(!f.exists()){
		        		//new FileOutputStream(FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + "\\" + "userInfo.dat", true).close();
		        		new FileOutputStream("src/restaurantLookup/relFiles/userInfo.dat", true).close();
		        		System.out.println("object flushed");
	        		}

	        		//FileOutputStream fos = new FileOutputStream(FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + "\\" + "userInfo.dat", true);
	        		FileOutputStream fos = new FileOutputStream("src/restaurantLookup/relFiles/userInfo.dat", true);
	        		ObjectOutputStream oos = new ObjectOutputStream(fos);
					oos.reset();
					oos.writeObject(u);
					oos.flush();
					oos.close();
	        		
	        } catch (IOException e) {
	        	System.err.println("already exists: " + e.getMessage());
	        }
	     
	 }
	 public void readFile(){
		        try{ 	//FileInputStream fis = new FileInputStream(FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + "\\" + "userInfo.dat");
		        		FileInputStream fis = new FileInputStream("src/restaurantLookup/relFiles/userInfo.dat");
						ObjectInputStream ois = new ObjectInputStream(fis);
						User result = (User) ois.readObject();
						System.out.println(result.getUsername());
						ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
	   	 }
	 public User getProfile(String username) throws IOException, ADTExceptions{

		 FileInputStream fis ; 
		ObjectInputStream ois = null;
		 User u;
		 int spot = 0; int grab = 0; 
			//List<User> arr = new ArrayList<User>(); 
			IndexedList arr = new IndexedList<String>();
			try {
				//fis = new FileInputStream(FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + "\\" + "userInfo.dat");
				fis = new FileInputStream("src/restaurantLookup/relFiles/userInfo.dat");
				while(true){
				ois = new ObjectInputStream(fis);
				arr.Add(u = (User) ois.readObject()); 
				//System.out.println(u.getUsername() + " " + arr.toString());

				}
			} catch (FileNotFoundException e1) {
				//e1.printStackTrace();
			}  catch (IOException e) {
				//e.printStackTrace();
			} catch (ClassNotFoundException e) {
				//e.printStackTrace();
			} finally{	ois.close();}

			for(int i = 0; i < arr.size(); i++){
					if(username.equals(arr.get(i))){
						grab = i; 

					}	 
			}		
			u = (User) arr.get(grab);
		 return u; 
	 }
	 
	 
/*____________________________________________________NEW TO ASSIGNMENT 3__________________________________________________________________________________________________________*/
	 
	 
	public boolean isNumeric(String s){
		 return s != null && s.matches("[-+]?\\d*\\.?\\d+");  
	}
	 @SuppressWarnings("rawtypes")
	public static ArrayList ReadExcel(String file) throws Exception{
		 ArrayList<ArrayList<HSSFCell>> tuple = new ArrayList(); 
		 FileInputStream fis = null; 
		 
		 try{
			 fis = new FileInputStream(file);
			 HSSFWorkbook wb = new HSSFWorkbook(fis);
			 HSSFSheet s = wb.getSheetAt(0);
			 Iterator row = s.rowIterator();
			 
			 //String rowCSV = "";
			 while(row.hasNext()){
				 HSSFRow rows = (HSSFRow) row.next();
				 Iterator cells = rows.cellIterator();
				 ArrayList<HSSFCell> fields = new ArrayList(); 
				 while(cells.hasNext()){
					 HSSFCell cell = (HSSFCell) cells.next(); 
					 if(cell.getCellType() == Cell.CELL_TYPE_BLANK){
						 
					 }else{
						 	fields.add(cell);						 
					 }

					// rowCSV = rowCSV + Character.toString((char) (34)) + cell.getStringCellValue() + Character.toString((char) (34)) + ",";
					 //System.out.println(rowCSV);
				 }
				 //tuple.add(rowCSV);
				 tuple.add(fields);
			 }	
			 
		 }catch( Exception exc){
			 exc.getStackTrace();
		 }finally{
			 if(fis!=null)fis.close();
		 }
		 
		 
		 return tuple; 
	 }
	 
	 @SuppressWarnings("deprecation")
	public static String excelDataToString(ArrayList data){
		String rowCSV = "";
		 
		// for(int i = 0; i<data.size(); i++){
			 for(int i = 0; i<rowcount; i++){	  //hard coding this because I keep coming out with extras.
			 ArrayList datum = (ArrayList) data.get(i);
			 for(int j = 0; j < datum.size();j++){
				 Cell cell = (Cell) datum.get(j);
				 String v = "";
				 if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
					 v = Double.toString(cell.getNumericCellValue());
				 }else if(cell.getCellType() == Cell.CELL_TYPE_STRING){
					 v = cell.getStringCellValue();
				 }
				 
				 rowCSV = rowCSV + Character.toString((char) (34)) + v + Character.toString((char) (34)) + ",";
				// System.out.println(rowCSV);
			 }
			 rowCSV  = rowCSV + Character.toString((char) (13));
		 }
		 return rowCSV;
	 }
	 
	 @SuppressWarnings("deprecation")
	public static String[] excelDataToStringArray(ArrayList data){
		String[] allItems = new String[rowcount];
		// for(int i = 0; i<data.size(); i++){
			 for(int i = 0; i<rowcount; i++){	
				 //hard coding this because I keep coming out with extras.
				 String rowCSV = "";
				 ArrayList datum = (ArrayList) data.get(i);
			 for(int j = 0; j < datum.size();j++){
				 Cell cell = (Cell) datum.get(j);
				 String v = "";
				 if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
					 v = Double.toString(cell.getNumericCellValue());
				 }else if(cell.getCellType() == Cell.CELL_TYPE_STRING){
					 v = cell.getStringCellValue();
				 }
				 
				 rowCSV = rowCSV + Character.toString((char) (34)) + v + Character.toString((char) (34)) + ",";
				// System.out.println(rowCSV);
			 }
			 allItems[i] = rowCSV;
		 }
		 return allItems;
	 }
	 
	 
	 
	 @SuppressWarnings("deprecation")
	public Restaurant[] excelDataToRestaurant(ArrayList data){
		Restaurant[] restaurantarr = new Restaurant[rowcount];
		 
		// for(int i = 0; i<data.size(); i++){
			 for(int i = 0; i<rowcount; i++){	  //hard coding this because I keep coming out with extras.
			 Restaurant r = new Restaurant(); 
			 GeoLocation l = new GeoLocation(); 
			 ArrayList datum = (ArrayList) data.get(i);
			 String address = ""; 
			 for(int j = 0; j < datum.size();j++){
				 Cell cell = (Cell) datum.get(j);

				 if(j ==1){
					/* if(cell.getCellType() == cell.CELL_TYPE_BLANK){
						 break; 
					 }*/
					 r.setRestaurantName(cell.getStringCellValue());
				 }else if (j==2){address = address + cell.getStringCellValue();
				 }else if (j==3){address = address + " " + cell.getStringCellValue() + ",";
				 }else if (j==4){address = address + " " + cell.getStringCellValue() + " ";
				 }else if (j==5){
					 String zip = Double.toString(cell.getNumericCellValue()).substring(0, 5);
					 //address = address + " " + cell.getNumericCellValue();
					 address = address + " " + zip;
				// }else if (j==5){address = address + " " + Double.toString(cell.getNumericCellValue()) + " ";
				 
				 }else if(j==6){
					 if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
						 //System.out.println("hit 1st condition.");
						 l.setLatitude(cell.getNumericCellValue());
					 }else if(cell.getCellType() == Cell.CELL_TYPE_BLANK){
						 
					 }else if(cell.getCellType() == Cell.CELL_TYPE_STRING){
						//System.out.println("hit this condition.");
						double e = Float.parseFloat(cell.getStringCellValue());
						//double d = (double) e; 
						// double d = Double.parseDouble(cell.getStringCellValue());
						 l.setLatitude(e);
					 }
				 }else if(j==7){
					 if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
						 //System.out.println("hit 1st condition.");
						 l.setLongitude(cell.getNumericCellValue());
					 }else if(cell.getCellType() == Cell.CELL_TYPE_BLANK){
						 
					 }else if(cell.getCellType() == Cell.CELL_TYPE_STRING){
						//System.out.println("hit this condition.");
						double e = Double.parseDouble(cell.getStringCellValue());
						//double d = (double) e; 
						// double d = Double.parseDouble(cell.getStringCellValue());
						 l.setLongitude(e);}
					 
				 }else if(j==8){
					 if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
						 //System.out.println("hit 1st condition.");
						 String str = (Double.toString(cell.getNumericCellValue()));
						/* String str1 = str.substring(0, 1);
						 String str2 = "";
						 if(str.indexOf("E") == -1){
							 str2 = str.substring(2,str.length());
						 }else{
							 str2 = str.substring(2,str.indexOf("E"));
							 str2 = str.substring(2,str.length());
						 } */
						 
						 //String str2 = str.substring(2,str.length()-2);
						// r.setPhonenubmer(Double.toString(cell.getNumericCellValue())); 
						r.setPhoneNumber(str);
					 }else if(cell.getCellType() == Cell.CELL_TYPE_STRING){
						//System.out.println("hit this condition.");
						r.setPhonenubmer(cell.getStringCellValue());}
				 }else if(j==9){
					 r.setRestaurantImage(cell.getStringCellValue());
				 }
				 
				 
				


				// System.out.println(rowCSV);
			 }
			 r.setRestaurantAddress(address);
			 r.setLocation(l);
			 restaurantarr[i] = r; 
			// System.out.println(restaurantarr[i].toString());
			// System.out.println(r.getPhonenubmer());
		 }
		 return restaurantarr;
	 }
	 
	 
	 
} 
