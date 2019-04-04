//package src;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.ZonedDateTime;
import java.util.Scanner;

public class Track {
	ArrayList<Point>pointlist =new ArrayList<Point>();
    Track(){
    	
    }
	void readFile(String fn) throws FileNotFoundException{
    	int t;
    	ZonedDateTime x = null;
    	double a =0.0;
    	double b =0.0;
    	double c = 0.0;
    	int z=0;
    	File file = new File(fn);
    	Scanner scanner = new Scanner(file);
    	Scanner value = null;
	pointlist.clear();
    	while(scanner.hasNextLine()) {
    		if(z==0) {
    			z=z+1;
			scanner.nextLine();
    		}else if(z==1) {
    		value = new Scanner(scanner.nextLine());
    		value.useDelimiter(",");
    		t=0;
    		while(value.hasNext()) {
                         
    			
                    String  data = value.next();
    			if(t==0) {
   
    				x=ZonedDateTime.parse(data);
    			}else if(t==1) {
    				
    				a=Double.parseDouble(data);
    				
    			}else if(t==2) {
    				
    				b=Double.parseDouble(data);
    	
    			}else if(t==3) {
    				
    				c=Double.parseDouble(data);

    				
    			}
    			t=t+1;
    		}	
                        if(t!=4){
                          throw new GPSException("The value is not exist!");
                         }else{
                        
    			Point point=new Point(x,a,b,c);
    			pointlist.add(point);}
    	}	
    	}
    	scanner.close();
    	
    	}

    	
    	
   	
    
   public  void add(Point n){
    	pointlist.add(n);
    	
    }
   public int size() {
    	int m;
    	m=pointlist.size();
    	return m;
    }
   public Point get(int p) {
       if(p>=pointlist.size()||p<0){
    		throw new GPSException("This position is not exist!");
    	}else {
    		Point a  = pointlist.get(p);
    		return a;
    	}
    	
    }


}
