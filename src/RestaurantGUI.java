
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.io.IOException;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Button;
import restaurantLookup.BST;
import restaurantLookup.GeoLocation;
import restaurantLookup.Restaurant;

import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.widgets.Canvas;


public class RestaurantGUI {
	//Display Variables
	protected Shell shell;
	private Table table;
	private Text txtLatitude;
	private Text txtLongitude;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private Text txtRestaurantName;
	private Text txtRestaurantAddress;
	private Text txtCoordinates;
	private Text txtPhoneNumber;

	//Logic Variables
	GeoLocation myLocation = new GeoLocation();
	UtilityMethods ul = new UtilityMethods();
	
	
//Launcher
/*	public static void main(String[] args) {
		try {
			RestaurantGUI window = new RestaurantGUI();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	} */

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
	protected void createContents(){
		shell = new Shell();
		shell.setSize(994, 850);
		shell.setText("Restaurant Lookup");
		
		
		Label lblLatitude = new Label(shell, SWT.NONE);
		lblLatitude.setBounds(10, 48, 55, 15);
		lblLatitude.setText("Latitude");
		
		txtLatitude = new Text(shell, SWT.BORDER);
		txtLatitude.setBounds(10, 69, 157, 21);
		
		Label lblLongitude = new Label(shell, SWT.NONE);
		lblLongitude.setText("Longitude");
		lblLongitude.setBounds(10, 100, 55, 15);
		
		txtLongitude = new Text(shell, SWT.BORDER);
		txtLongitude.setBounds(10, 121, 157, 21);
		
		Button btnSearchNearby = new Button(shell, SWT.NONE);
		btnSearchNearby.setBounds(10, 155, 157, 25);
		formToolkit.adapt(btnSearchNearby, true, true);
		btnSearchNearby.setText("Search Nearby");
		//Button does all the bst related sorting and such.
		btnSearchNearby.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try{    
				  if(txtLatitude.getText() != null && txtLongitude.getText()!= null //can't be blank
					 && ul.isNumeric(txtLatitude.getText()) && ul.isNumeric(txtLongitude.getText()) //gotta be numbers
					 && Math.abs((Double.parseDouble(txtLatitude.getText()))) <= 90   && Math.abs((Double.parseDouble(txtLongitude.getText()))) <= 180 /* Must be viable Lat and Long*/){
					  
					  table.removeAll(); //super inefficient

						String mypath = "src/restaurantLookup/relfiles/RESTAURANT_LIST.xls";
						Restaurant[] ind;
						try {
							ind = ul.excelDataToRestaurant(UtilityMethods.ReadExcel(mypath));
							Restaurant Mylocation = new Restaurant(); 
							GeoLocation myloc = new GeoLocation(Double.parseDouble(txtLatitude.getText()),Double.parseDouble(txtLongitude.getText()));
							Mylocation.setLocation(myloc);
							Collections.sort(Arrays.asList(ind), new Comparator<Restaurant>() {
							    @Override
							    public int compare(Restaurant c1, Restaurant c2) {
							    	//Double.compare(c1.compareGeoLocation(Mylocation), c2.compareGeoLocation(MyLocation))
							        return 	Double.compare(c1.compareGeoLocation(Mylocation), c2.compareGeoLocation(Mylocation));
							    }
							});
							BST<Restaurant> bst = new BST<Restaurant>();
							for(int i = 0; i < ind.length; i++){
								bst.add(ind[i]);
								//System.out.println(ind[i].getRestaurantName() + " " + ind[i].compareGeoLocation(Mylocation));
							}
							
							bst.reset(0);
							//for(int i = 0; i < ind.length; i++){
							for(int i = 0; i < bst.size(); i++){
								TableItem t = new TableItem(table, SWT.NONE);
								//System.out.println(ind[i].toString());
								/*Double dist = myLocation.getDistance(ind[i].getLocation());
								dist = Math.floor(dist*100) /100;
								String str = dist.toString(); */
								Restaurant j = bst.getNext(0);
								String[] st = new String[]{j.getRestaurantName(), j.getPhoneNumber(),j.getLocation().toString(),j.getRestaurantAddress(), j.getRestaurantImage(), Double.toString(Math.floor(j.compareGeoLocation(Mylocation)*100)/100)};
								
								//String[] st = new String[]{ind[i].getRestaurantName(), ind[i].getPhoneNumber(),ind[i].getLocation().toString(),ind[i].getRestaurantAddress(), ind[i].getRestaurantImage(), str};
								t.setText(st);
								
							}
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					/*	TableItem[] tableItems = table.getItems();
					  
					  for(int i = 0; i < table.getItemCount(); i++){
						  
						  Double lat,lon, dist;
						  String coord = tableItems[i].getText(2);
						  String str[] = coord.split(",");
						  lat = Double.parseDouble(str[0]);
						  lon = Double.parseDouble(str[1]);
						  dist = myLocation.getDistance(lat, lon);
						  coord = dist.toString() + " miles";
						  String[] rowArr = {tableItems[i].getText(0),tableItems[i].getText(1), tableItems[i].getText(2), tableItems[i].getText(3), coord};
						  table.getItem(i).setText(rowArr);
						//  item.setText(rowArr); 
						 
					  } */
					  
					  
					  
				  }else{
					  MessageBox mb = new MessageBox(shell, SWT.ICON_WARNING);
					  mb.setMessage("Missing or Invalid Latitude/Longitude value. Please fill in the required fields with viable numbers before reattempting.");
					  mb.open();
				  }					
				}catch(Exception exc){
					MessageDialog.openError(shell, "Descriptive Error Message Header",  exc.getMessage() +"\n" + exc.getLocalizedMessage() + "\n" + exc.getCause() + "\n"+ exc.getStackTrace() + "\n" );
				}
			}
		});	
		
		Canvas canvas = new Canvas(shell, SWT.NONE);
		canvas.setBounds(656, 39, 312, 299);
		formToolkit.adapt(canvas);
		formToolkit.paintBordersFor(canvas);
		
		Label label = new Label(shell, SWT.SEPARATOR | SWT.VERTICAL);
		label.setBounds(186, 0, 2, 375);
		formToolkit.adapt(label, true, true);
		
		txtRestaurantName = new Text(shell, SWT.BORDER);
		txtRestaurantName.setBounds(379, 39, 259, 21);
		formToolkit.adapt(txtRestaurantName, true, true);
		
		Label lblRestaurantName = new Label(shell, SWT.NONE);
		lblRestaurantName.setBounds(252, 39, 108, 21);
		lblRestaurantName.setText("Restaurant Name");
		
		Label lblRestaurantAddress = new Label(shell, SWT.NONE);
		lblRestaurantAddress.setText("Restaurant Address");
		lblRestaurantAddress.setBounds(252, 127, 108, 21);
		
		txtRestaurantAddress = new Text(shell, SWT.BORDER);
		txtRestaurantAddress.setBounds(379, 127, 259, 21);
		formToolkit.adapt(txtRestaurantAddress, true, true);
		
		Label lblCoordinates = new Label(shell, SWT.NONE);
		lblCoordinates.setText("Coordinates");
		lblCoordinates.setBounds(252, 100, 108, 21);
		
		txtCoordinates = new Text(shell, SWT.BORDER);
		txtCoordinates.setBounds(379, 100, 259, 21);
		formToolkit.adapt(txtCoordinates, true, true);
		
		Label lblPhoneNumber = new Label(shell, SWT.NONE);
		lblPhoneNumber.setText("Phone Number");
		lblPhoneNumber.setBounds(252, 69, 108, 21);
		
		txtPhoneNumber = new Text(shell, SWT.BORDER);
		txtPhoneNumber.setBounds(379, 69, 259, 21);
		formToolkit.adapt(txtPhoneNumber, true, true);
		
		
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		table.setBounds(10, 374, 958, 427);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnRestaurantName = new TableColumn(table, SWT.NONE);
		tblclmnRestaurantName.setWidth(180);
		tblclmnRestaurantName.setText("Restaurant Name");
		
		TableColumn tblclmnPhoneNumber = new TableColumn(table, SWT.NONE);
		tblclmnPhoneNumber.setWidth(100);
		tblclmnPhoneNumber.setText("Phone Number");
		
		TableColumn tblclmnCoordinates = new TableColumn(table, SWT.NONE);
		tblclmnCoordinates.setWidth(172);
		tblclmnCoordinates.setText("Coordinates");
		
		TableColumn tblclmnAddress = new TableColumn(table, SWT.NONE);
		tblclmnAddress.setWidth(401);
		tblclmnAddress.setText("Address");
		
		TableColumn tblclmnImage = new TableColumn(table, SWT.NONE);
		tblclmnImage.setWidth(1);
		tblclmnImage.setText("Image");
		
		TableColumn tblclmnDistance = new TableColumn(table, SWT.NONE);
		tblclmnDistance.setWidth(100);
		tblclmnDistance.setText("Distance (mi)");
		
		TableCursor tableCursor = new TableCursor(table, SWT.NONE);
		//TableItem t = new TableItem(table, SWT.NONE);
		//String[] st = new String[]{"YOLO", "WAT", "UHUH", "Whatever","https://www.gstatic.com/webp/gallery3/1.png" }; 
		
		

		String mypath = "src/restaurantLookup/relFiles/RESTAURANT_LIST.xls";
		Restaurant[] ind;
		try {
			ind = ul.excelDataToRestaurant(UtilityMethods.ReadExcel(mypath));
			for(int i = 0; i < ind.length; i++){
				TableItem t = new TableItem(table, SWT.NONE);
				//System.out.println(ind[i].toString());
				String[] st = new String[]{ind[i].getRestaurantName(), ind[i].getPhoneNumber(),ind[i].getLocation().toString(),ind[i].getRestaurantAddress(), ind[i].getRestaurantImage()};
				t.setText(st);
				
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
//Upon interaction, load restaurant details.		
		table.addSelectionListener(new SelectionListener (){

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				//Do nothing...
				
			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				int row = table.getSelectionIndex();
				TableItem[] tableItems = table.getItems();
				String mystring = tableItems[row].getText(0) + " " + tableItems[row].getText(1) + " " +  tableItems[row].getText(2) + " "+ tableItems[row].getText(3);
				System.out.println(mystring);
				txtRestaurantName.setText(tableItems[row].getText(0));
				txtRestaurantAddress.setText(tableItems[row].getText(3));
				txtCoordinates.setText(tableItems[row].getText(2));
				txtPhoneNumber.setText(tableItems[row].getText(1));
		
					try {
						URL url = new URL(tableItems[row].getText(4));
						
						canvas.setBackgroundImage(ImageDescriptor.createFromURL(url).createImage());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch(org.eclipse.swt.SWTException e){
						System.out.println("Specified URL is not an Image.");
						canvas.setBackground(null);
					}


			}
			
		});

	}


}
