package feups;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

public class Parser {
	
	JSONObject json = null;
	
	public Parser(){
	
		
		FileInputStream is;
		try {
			is = new FileInputStream("maps/map01.json");
		} catch (FileNotFoundException e1) {
			System.err.println("File not found.");
			return;
		}
		
		String jsonTxt = null;
		try{
			jsonTxt = IOUtils.toString(is);
		} catch(Exception e){
			System.err.println("File error.");
			return;
		}
		
		json = (JSONObject) JSONSerializer.toJSON( jsonTxt );
		
	}
	
	public boolean getDetails(){
		if(json==null)
			return false;
		
	    String name = json.getString( "name" );
	    String mapFile = json.getString( "map" );
	    
	    // Getting cities
	    System.out.println("\n##### CITIES #####");
	    JSONArray cities = json.getJSONArray("cities");
	    
	    for(int n = 0; n < cities.size(); n++)
	    {
	        JSONObject city = cities.getJSONObject(n);
	        String cityName = city.getString("name"); //City Name
	        System.out.println("City: " + cityName);
	        
	        JSONObject position = city.getJSONObject("position"); //City position
	        Integer pos_x = position.getInt("x");
	        Integer pos_y = position.getInt("y");
	        System.out.println("\t x=" + pos_x + " y=" + pos_y);
	    }
	    
	    // Getting parcels
	    System.out.println("\n##### PARCELS #####");
	    JSONArray parcels = json.getJSONArray("parcels");
	    
	    for(int n = 0; n < parcels.size(); n++)
	    {
	        JSONObject parcel = parcels.getJSONObject(n); //Parcel Name
	        String parcelName = parcel.getString("name");
	        System.out.println("Parcel: " + parcelName);
	        
	        String parcelPosition = parcel.getString("position"); //Parcel Position
	        System.out.println("\tPosition: " + parcelPosition);
	        
	        String parcelDestination = parcel.getString("destination"); //Parcel Destination
	        System.out.println("\tDestination: " + parcelDestination);
	    }
	    
	    
	    // Getting trucks
	    System.out.println("\n##### TRUCKS #####");
	    JSONArray trucks = json.getJSONArray("trucks");
	    
	    for(int n= 0; n<trucks.size(); n++){
	        JSONObject truck = trucks.getJSONObject(n);
	        
	        String truckName = truck.getString("name");
	        String truckPosition = truck.getString("position");
	        
	        System.out.println("Truck: " + truckName);
	        
	        JSONArray truckParcels = truck.getJSONArray("parcels");
	        
	        // Getting parcels on truck
	        for(int i= 0; i<truckParcels.size(); i++){
	        	JSONObject truckParcel = truckParcels.getJSONObject(i);
	        	String truckParcelName = truckParcel.getString("name");
	        	System.out.println("\tTruck " + truckName + " has parcel " + truckParcelName);
	        }
	        
	        
	        
	        
	    	
	    }
	    
	    System.out.println("Name is " + name + " and map file is " + mapFile);
	    return true;
	}
}