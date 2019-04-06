package src;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.text.DecimalFormat;

public class TrackInfo {

	public static void main(String[] args) {
		int a;
		Point t = new Point();
		Point u = new Point();
		double e;
		double f;
		// read file
		Track file = new Track();
		System.out.println("The file you want to read is:");
		Scanner read = new Scanner(System.in);
		String d = read.next();
		try {
			file.readFile(d);
		} catch (FileNotFoundException e1) {

			e1.printStackTrace();
			System.exit(1);
		}
		a = file.pointlist.size();
		t = file.highestPoint();
		u = file.lowestPoint();
		e = file.totalDistance() / 1000;
		f = file.averageSpeed();
		DecimalFormat df = new DecimalFormat(".000");
        // print the information of a file
		System.out.println(a + " points in the Track");
		System.out.println("Lowest point is " + u.toString());
		System.out.println("Highest point is " + t.toString());
		System.out.println("Total distance =" + df.format(e) + "km");
		System.out.println("Average speed =" + df.format(f) + "m/s");

		read.close();
		
		//advanced task
		file.writeKML("d:/path.kml");
	}

}
