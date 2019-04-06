package src;

import java.time.ZonedDateTime;
import java.text.DecimalFormat;
import static java.lang.Math.*;

/**
 * Represents a point in space and time, recorded by a GPS sensor.
 *
 * @author Nick Efford & YOUR NAME
 */
public class Point {
	// Constants useful for bounds checking, etc

	private static final double MIN_LONGITUDE = -180.0;
	private static final double MAX_LONGITUDE = 180.0;
	private static final double MIN_LATITUDE = -90.0;
	private static final double MAX_LATITUDE = 90.0;
	private static final double MEAN_EARTH_RADIUS = 6.371009e+6;

	private ZonedDateTime time;
	private double longitude;
	private double latitude;
	private double elevation;
	private String f;
	private String g;

	public Point() {

	}

	public Point(ZonedDateTime time, double longitude, double latitude, double elevation) throws GPSException {
		if (MIN_LONGITUDE <= longitude && longitude <= MAX_LONGITUDE && MIN_LATITUDE <= latitude
				&& latitude <= MAX_LATITUDE) {
			this.time = time;
			this.longitude = longitude;
			this.latitude = latitude;
			this.elevation = elevation;
			DecimalFormat df = new DecimalFormat(".00000");
			f = df.format(latitude);
			g = df.format(longitude);
		} else {
			throw new GPSException("Values are invalid!");
		}
	}
    //Getter methods
	public ZonedDateTime getTime() {
		return time;
	}

	public double getLongitude() {
		return longitude;
	}

	public double getLatitude() {

		return latitude;
	}

	public double getElevation() {
		return elevation;
	}
    // toString method:
	public String toString() {
		return "(" + g + "," + " " + f + ")" + ", " + elevation + " m";
	}
	
	//advanced task
	public String  toString1() {
		return longitude+","+latitude+","+elevation;
	}
	// TODO: Define fields for time, longitude, latitude and elevation

	// TODO: Define a constructor

	// TODO: Define getters for the fields

	// TODO: Define a toString() method that meets requirements

	// Do not alter anything beneath this comment

	/**
	 * Computes the great-circle distance or orthodromic distance between two points
	 * on a spherical surface, using Vincenty's formula.
	 *
	 * @param p First point
	 * @param q Second point
	 * @return Distance between the points, in metres
	 */
	public static double greatCircleDistance(Point p, Point q) {
		double phi1 = toRadians(p.getLatitude());
		double phi2 = toRadians(q.getLatitude());

		double lambda1 = toRadians(p.getLongitude());
		double lambda2 = toRadians(q.getLongitude());
		double delta = abs(lambda1 - lambda2);

		double firstTerm = cos(phi2) * sin(delta);
		double secondTerm = cos(phi1) * sin(phi2) - sin(phi1) * cos(phi2) * cos(delta);
		double top = sqrt(firstTerm * firstTerm + secondTerm * secondTerm);

		double bottom = sin(phi1) * sin(phi2) + cos(phi1) * cos(phi2) * cos(delta);

		return MEAN_EARTH_RADIUS * atan2(top, bottom);
	}
}