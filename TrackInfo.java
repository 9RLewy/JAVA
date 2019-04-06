package src;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.text.DecimalFormat;

public class TrackInfo {

	public static void main(String[] args) throws FileNotFoundException {
		int a;
		Point t = new Point();
		Point u = new Point();
		double e;
		double f;
		Track file = new Track();
		System.out.println("The file you want to read is:");
		Scanner read = new Scanner(System.in);
		String d = read.next();
		file.readFile(d);
		a=file.pointlist.size();
		t=file.highestPoint();
		u=file.lowestPoint();
		e=file.totalDistance();
		f=file.averageSpeed();
		DecimalFormat df = new DecimalFormat(".000");
		
		System.out.println(a+"points in the Track");
		System.out.println("Lowest point is"+u.toString());
		System.out.println("Highest point is"+t.toString());
		System.out.println("Total distance ="+df.format(e));
		System.out.println("Average speed ="+df.format(f));
		
        read.close();
	}
	

}
