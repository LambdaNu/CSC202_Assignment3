import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;

public class LoginPortal {

	protected Shell shell;
	private Text usernameText;
	private Text passwordText;

//Launcher
	public static void main(String[] args) {
		try {
			LoginPortal window = new LoginPortal();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//Open Window
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

//Create Contents
	protected void createContents() {
		shell = new Shell();
		shell.setSize(369, 300);
		shell.setText("Login");
		//Buttons
		Button btnSignUp = new Button(shell, SWT.NONE);
		btnSignUp.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try{
					AccountWindow myAccount = new AccountWindow();
					 myAccount.open(); 
			
				}catch(Exception exc){
					MessageDialog.openError(shell, "File I/O Error", "Bro, you might be SOL: " + exc.getMessage());
				}
			}
			
		});
		btnSignUp.setBounds(260, 226, 75, 25);
		btnSignUp.setText("Sign Up");
		
		Button btnLogin = new Button(shell, SWT.NONE);
		btnLogin.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try{
					UtilityMethods check = new UtilityMethods(); 
					if(!check.usernameCheck(usernameText.getText())){
						//if username is not found alert the people..
						MessageBox messagebox = new MessageBox(shell, SWT.ICON_WARNING);
						messagebox.setMessage("That account cannot be found. Please sign up or try a different username.");
						messagebox.open();
					}else{ User u = check.getProfile(usernameText.getText());
						if(u.getPassword().equals(passwordText.getText())){						
							RestaurantGUI restaurantgui = new RestaurantGUI();
							restaurantgui.open();
							/*MessageBox success = new MessageBox(shell,SWT.ICON_INFORMATION);
							success.setMessage("Login successful!");
							success.open(); */
						} else{
							MessageBox messagebox = new MessageBox(shell, SWT.ICON_WARNING);
							messagebox.setMessage("Username/Password Mismatch.");
							messagebox.open();
						}
						
						
						
					}

				}catch(Exception exc){
					MessageDialog.openError(shell, "error", exc.getMessage() +"\n" + exc.getLocalizedMessage() + "\n" + exc.getCause() + "\n"+ exc.getStackTrace() + "\n");
				}
			}
		});	
		btnLogin.setBounds(260, 134, 75, 25);
		btnLogin.setText("Login");
		
		//Text and Labels
		usernameText = new Text(shell, SWT.BORDER);
		usernameText.setBounds(99, 57, 236, 21);
		
		Label lblUsername = new Label(shell, SWT.NONE);
		lblUsername.setBounds(21, 60, 72, 15);
		lblUsername.setText("Username");

		
		passwordText = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		passwordText.setBounds(99, 97, 236, 21);
		
		Label lblPassword = new Label(shell, SWT.NONE);
		lblPassword.setText("Password");
		lblPassword.setBounds(21, 100, 72, 15);
		
		
		//Misc Labels and Separators
		Label lblDontHaveA = new Label(shell, SWT.NONE);
		lblDontHaveA.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		lblDontHaveA.setBounds(21, 195, 243, 25);
		lblDontHaveA.setText("Don't have a login? Please sign up!");

		Label lblPleaseLogIn = new Label(shell, SWT.NONE);
		lblPleaseLogIn.setText("Please log in to view content.");
		lblPleaseLogIn.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		lblPleaseLogIn.setBounds(21, 10, 243, 25);
		
		Label label = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(0, 180, 353, 2);

	}
}
