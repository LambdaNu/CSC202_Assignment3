import java.io.IOException;
import java.util.Calendar;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;


public class AccountWindow {

	protected Shell shell;
	private Text txtFirstname;
	private Text txtLastName;
	private Text txtEmail;
	private Label lblSSN;
	private Text txtSSN1;
	private Text txtSSN2;
	private Text txtSSN3;
	private Label lblPhone;
	private Text txtCountryCode;
	private Text txtAreaCode;
	private Text txtPhoneNumber;
	private Label lblBirthday;
	private Label lblUsername;
	private Text txtUsername;
	private Text txtPassword;
	private Text txtPasswordConfirm;
	private Text txtPhotopath;

	UtilityMethods check = new UtilityMethods(); 
	Image profilePhoto;
	boolean valid; 
	private Canvas canvas;
	String gender;
	Calendar dob = Calendar.getInstance();

/*
	public static void main(String[] args) {
		try {
			AccountWindow window = new AccountWindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
*/

	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(415, 643);
		shell.setText("Set Account Details");

		
		
//Buttons
		Button btnBrowse = new Button(shell, SWT.NONE);
		btnBrowse.setBounds(117, 371, 75, 32);
		btnBrowse.setText("Browse");
		btnBrowse.addSelectionListener(new SelectionAdapter() {


			@Override
			public void widgetSelected(SelectionEvent e) {
				try{    
				   //Open the file dialog for browsing through files. Should work fine with most OS'es
				   FileDialog dialog = new FileDialog(shell, SWT.OPEN);
				   		//boolean inJar = true; 
				   dialog.setFilterExtensions(new String [] {"*.jpg",".png",".gif", "*"});
				   dialog.setFilterPath("c:\\");
				   String result = dialog.open();
				   String escaped = result.replace("\\", "\\\\");
				   canvas.setBackgroundImage(SWTResourceManager.getImage(escaped));
				   //canvas.setBackgroundImage(SWTResourceManager.getImage(result));
				   //File f = new File(result);
				   System.out.println(result);
				   System.out.println(escaped);
				   //profilePhoto = new Image(Display.getDefault(),f.getPath());
					txtPhotopath.setText(result);
					//canvas.setBackgroundImage(profilePhoto);
					//canvas.getBackgroundImage();
					//canvas.setForeground(null);
					//canvas.setBackgroundMode(SWT.INHERIT_FORCE);
					
					
				   			//profilePhoto = check.loadImage(result, inJar);
				   
						   //because I APPARENTLY can't go from string-->File-->Image I've got to convert it into a bytestream and then back out...things can't be easy.
						   /*File f = new File(result);
						   FileInputStream fis = new FileInputStream(f);
						   ByteArrayOutputStream bos = new ByteArrayOutputStream();
					        byte[] buf = new byte[1024];
					        try {
					            for (int readNum; (readNum = fis.read(buf)) != -1;) {
					                //Writes to this byte array output stream
					                bos.write(buf, 0, readNum); 
					                System.out.println("read " + readNum + " bytes,");
					            }
					        } catch (IOException ex) {
					        	System.out.println (ex.toString());
					            System.out.println("Could not find file " + result);
					        }
					 
					        byte[] bytes = bos.toByteArray();
					        //Now BACK to an image...
					        
						   */
						  // File f = dialog.open();
							//go from String to setting the image
						   /*Image image = new Image();
							
						   	lblProfilePhoto.setImage(image); */
						  // lblProfilePhoto
				}catch(Exception exc){
					MessageDialog.openError(shell, "Descriptive Error Message Header",  exc.getMessage() +"\n" + exc.getLocalizedMessage() + "\n" + exc.getCause() + "\n"+ exc.getStackTrace() + "\n" );
				}

			}
		});	
		
		Button btnSubmit = new Button(shell, SWT.NONE);
		btnSubmit.setBounds(296, 546, 75, 25);
		btnSubmit.setText("Submit");
		btnSubmit.addListener(SWT.Selection, new Listener(){
			@Override
			public void handleEvent(Event a) {
				//now let's run through all our checks for valid stuff before submitting... This is so so bad...
				MessageBox messagebox = new MessageBox(shell, SWT.ICON_WARNING);
				String phoneNumber = txtCountryCode.getText() + "+" + txtAreaCode.getText() + "+" + txtPhoneNumber.getText();
				String ssn = txtSSN1.getText() + "-" + txtSSN2.getText() + "-" + txtSSN3.getText(); 
						//System.out.println(dob.getTime());
				if(check.phoneNumberCheck(phoneNumber)){
					//if valid
					System.out.println("Valid Phone Number");
				}else{messagebox.setMessage("Invalid Phone Number"); messagebox.open(); }; 
				if(!check.emailCheck(txtEmail.getText())){
					//if email does not match pattern
					messagebox.setMessage("Invalid email. Please Retry."); 
					messagebox.open(); }
				else if(!check.ssnCheck(ssn)){
					//if SSN doesn't match regex.
					messagebox.setMessage("Invalid SSN. Please Retry."); 
					messagebox.open(); }
				else if(!check.passwordCheck(txtPassword.getText())){
					//if initial password doesn't fit the bill...
					messagebox.setMessage("Invalid Password. Please Retry.\n Your password must have an uppercase character, a lowercase character, a number,and a special character."); 
					messagebox.open(); }
				else if(!txtPassword.getText().equals(txtPasswordConfirm.getText())){
					//if passwords dont match
					messagebox.setMessage("Your passwords do not match.\n Your password must have an uppercase character, a lowercase character, a number,and a special character."); 
					messagebox.open();}
				else if(txtFirstname.getText() == null || txtLastName.getText() == null || txtUsername.getText() == null || txtPassword.getText() == null || txtPasswordConfirm.getText() == null){
					//DOB can't be null so there's a little bit no point in dealing with it...
					messagebox.setMessage("All bolded fields are required."); 
					messagebox.open();} else
					try {
						if(check.usernameCheck(txtUsername.getText())){
							//Validate username
							messagebox.setMessage("Username Already in use. Please try a different one."); 
							messagebox.open();} 
						else{ User u = new User( txtUsername.getText(), txtPassword.getText(), txtEmail.getText(), 
								phoneNumber, txtPhotopath.getText(),txtFirstname.getText(), txtLastName.getText(), ssn, gender, dob
								);
							check.writeFile(u);
							//check.readFile();
							MessageBox success = new MessageBox(shell,SWT.ICON_INFORMATION);
									success.setMessage("Sign Up successful!");
									success.open();
							
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			}
			
		});
//Label and Text Pairing

		Label lblFirstName = new Label(shell, SWT.NONE);
		lblFirstName.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblFirstName.setBounds(10, 4, 74, 15);
		lblFirstName.setText("First Name");
		
		txtFirstname = new Text(shell, SWT.BORDER);
		txtFirstname.setBounds(152, 4, 219, 21);
		
		
		Label lblLastName = new Label(shell, SWT.NONE);
		lblLastName.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblLastName.setBounds(10, 31, 74, 15);
		lblLastName.setText("Last Name");
		
		txtLastName = new Text(shell, SWT.BORDER);
		txtLastName.setBounds(152, 31, 219, 21);
		
		
		Label lblEmail = new Label(shell, SWT.NONE);
		lblEmail.setText("Email");
		lblEmail.setBounds(10, 58, 74, 15);
		
		txtEmail = new Text(shell, SWT.BORDER);
		txtEmail.setBounds(152, 58, 219, 21);
		
		
		lblSSN = new Label(shell, SWT.NONE);
		lblSSN.setText("SSN");
		lblSSN.setBounds(10, 85, 74, 15);
		
		txtSSN1 = new Text(shell, SWT.BORDER);
		txtSSN1.setBounds(152, 85, 50, 21);
		
		txtSSN2 = new Text(shell, SWT.BORDER);
		txtSSN2.setBounds(208, 85, 50, 21);
		
		txtSSN3 = new Text(shell, SWT.BORDER);
		txtSSN3.setBounds(266, 85, 50, 21);
	
		
		lblPhone = new Label(shell, SWT.WRAP);
		lblPhone.setText("Phone Number +1(XXX)XXXXXXXX");
		lblPhone.setBounds(10, 111, 136, 32);
		
		txtCountryCode = new Text(shell, SWT.BORDER);
		txtCountryCode.setBounds(152, 112, 50, 21);
		
		txtAreaCode = new Text(shell, SWT.BORDER);
		txtAreaCode.setBounds(208, 112, 50, 21);
		
		txtPhoneNumber = new Text(shell, SWT.BORDER);
		txtPhoneNumber.setBounds(266, 112, 105, 21);
		
		
		lblBirthday = new Label(shell, SWT.WRAP);
		lblBirthday.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblBirthday.setText("Birthday (YYYY/MM/DD)");
		lblBirthday.setBounds(10, 149, 136, 21);
		
		DateTime dateTime = new DateTime(shell, SWT.BORDER);
		dateTime.setBounds(152, 146, 91, 24);
		dateTime.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				dob.set(dateTime.getYear(), dateTime.getMonth(), dateTime.getDay());
			}});
		


	/*		//this.dob.setYear(dateTime.getYear()); this.dob.setMonth(dateTime.getMonth()); this.dob.setDate(dateTime.getDay()); //in the event someone actually was born on unix 0
		dateTime.addSelectionListener(new SelectionAdapter (){
			this.dob.setYear(dateTime.getYear()); this.dob.setMonth(dateTime.getMonth()); this.dob.setDate(dateTime.getDay()); }
		);*/

		Label lblGender = new Label(shell, SWT.NONE);
		lblGender.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblGender.setBounds(10, 176, 55, 15);
		lblGender.setText("Gender");
		
		
		Combo comboGender = new Combo(shell, SWT.NONE);
		comboGender.setBounds(152, 176, 91, 23);
		comboGender.add("FEMALE", 0);
		comboGender.add("MALE", 1);
		comboGender.addSelectionListener(new SelectionAdapter (){
			@Override
			public void widgetSelected(SelectionEvent a){
				if(comboGender.getText().equals("Female")){
					gender = comboGender.getText();
				}else{gender = "Male";}
			};
		});

		
		
		lblUsername = new Label(shell, SWT.NONE);
		lblUsername.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblUsername.setBounds(10, 253, 55, 15);
		lblUsername.setText("Username");
		
		txtUsername = new Text(shell, SWT.BORDER);
		txtUsername.setBounds(152, 250, 219, 21);
		
		
		Label lblPassword = new Label(shell, SWT.NONE);
		lblPassword.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblPassword.setText("Password");
		lblPassword.setBounds(10, 280, 55, 15);
		
		txtPassword = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		txtPassword.setBounds(152, 277, 219, 21);
		
		txtPasswordConfirm = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		txtPasswordConfirm.setBounds(152, 304, 219, 21);
		
		Label lblPasswordConfirm = new Label(shell, SWT.NONE);
		lblPasswordConfirm.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblPasswordConfirm.setText("Confirm Password");
		lblPasswordConfirm.setBounds(10, 307, 136, 15);
		

		
		Label lblSetProfilePhoto = new Label(shell, SWT.WRAP);
		lblSetProfilePhoto.setBounds(10, 380, 101, 15);
		lblSetProfilePhoto.setText("Set Profile Photo");
		
		txtPhotopath = new Text(shell, SWT.BORDER);
		txtPhotopath.setBounds(8, 419, 184, 32);

		canvas = new Canvas(shell, SWT.BORDER_DOT);
		//canvas.setBackgroundImage(SWTResourceManager.getImage("C:\\Users\\MQ\\Pictures\\tinyriiick.png"));
		canvas.setBackgroundMode(SWT.INHERIT_DEFAULT);
		canvas.setBounds(208, 371, 163, 169);
		

		
		/*Label lblProfilePhoto = new Label(shell, SWT.BORDER);
		lblProfilePhoto.setBounds(198, 354, 173, 170);
		lblProfilePhoto.getBackgroundImage();
		lblProfilePhoto.setImage(profilePhoto);
		lblProfilePhoto.getImage(); */ 


		/*txtPhotopath.addModifyListener(new ModifyListener(){
		      public void modifyText(ModifyEvent event) {
		    	//  canvas.getBackgroundImage();
		  		//canvas.getDisplay();
		          // Get the widget whose text was modified
		         lblProfilePhoto.setImage(profilePhoto);
		          lblProfilePhoto.getImage(); 
		        }});*/
		/*	txtYear = new Text(shell, SWT.BORDER);
		txtYear.setBounds(152, 149, 50, 21);
		
		txtMonth = new Text(shell, SWT.BORDER);
		txtMonth.setBounds(208, 149, 50, 21);
		
		txtDay = new Text(shell, SWT.BORDER);
		txtDay.setBounds(266, 149, 50, 21); */
		

	}
}
