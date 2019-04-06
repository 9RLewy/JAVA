package src;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
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
                          value.close();
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
   
   public Point lowestPoint() {
	   if(pointlist.size()<2) {
		   throw new GPSException("The points are not enough!");
	   }else {   
	   Point t = new Point();
           t = pointlist.get(0);
	   for(int i=0;i<pointlist.size();i++) {
		   if(t.getElevation()>=pointlist.get(i).getElevation()) {
			   t=pointlist.get(i); 
		 }
	   }
	   return t;
	   }
	   
   }
   public Point highestPoint() {
	   if(pointlist.size()<2) {
		   throw new GPSException("The points are not enough!");
	   }else {
	   Point l = new Point();
           l = pointlist.get(0);
	   for(int i=0;i<pointlist.size();i++) {
		   if(l.getElevation()<=pointlist.get(i).getElevation()) {
			  l=pointlist.get(i);
		   }
	   } 
	   return l;
	   }
   }
   
   public double totalDistance() {
	   if(pointlist.size()<2) {
		   throw new GPSException("The points are not enough!");
	   }else {
	 double a;
	 double t=0.0;
	 for(int i=0;i<pointlist.size()-1;i++) {
		 a= Point.greatCircleDistance(pointlist.get(i), pointlist.get(i+1));
		 t=t+a;
	 }
	 return t;
	 }
	   
   }
   
   public double averageSpeed() {
	   if(pointlist.size()<2) {
		   throw new GPSException("The points are not enough!");
	   }else {
	   double s;
	   double t;
	   double w=0.0;
	   double a;
	   t= totalDistance();
	   for(int i=0;i<pointlist.size()-1;i++) {
	   s=ChronoUnit.SECONDS.between(pointlist.get(i).getTime(), pointlist.get(i+1).getTime());
	   w=w+s;
	   }
	   a = t/w;
	   return a;
	   }
   }

}
