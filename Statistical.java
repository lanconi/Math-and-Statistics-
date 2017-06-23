package rsd.math;


import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

/**
 * Statistical is an Object Oriented Class
 * which provides some of the most commonly used
 * utility methods for performing statistical calculations.<br>
 * The statistical calculations provided are:<br>
 * mean<br>
 * median<br>
 * standard of deviation<br>
 * coefficient of variation<br>
 * variance<br>
 * <p>
 *  For your convenience, the constructor has been overloaded to accommodate 
 *  four different parameter types, as follows:<br>
 *   	{@code double[] }		<br>	
 *  	{@code Double[]  }		<br>		
 *  	{@code List<Double>  }	<br>	
 *  	{@code Set<Double> }	<p>
 *  Internally, there are three methods used to convert the
 *  Collection types with generic indicator, into a double[], which is 
 *  the sole field of this Object.<br>
 *  convertXXXtoToPrimitiveArray<p>
 * @author Lance Dooley, Robotic Systems Design (rsd)
 * @since 2017
 *
 */
public final class Statistical 
{
	private double[] data;
	
	public Statistical(double[] data)
	{
		if( data == null )
			throw new IllegalArgumentException("null array passed to Statistical");
		
		// create a new double[] and fill it with the double primitives 
		// There will be no dependency or risk original array
		double[] arr = new double[data.length];		
		for( int i = 0; i < data.length; i++ )
		{
			arr[i] = data[i];
		}
		this.data = arr;
	}
	
	public Statistical(Double[] data)
	{
		if( data == null )
			throw new IllegalArgumentException("null array passed to Statistical");
		
		this.data = convertDoubleArrayToPrimitiveArray(data);
	}
	
	public Statistical(List<Double> list)
	{
		if( list == null )
			throw new IllegalArgumentException("null List passed to Statistical");
		
		this.data = convertListDoubleToPrimitiveArray(list);
	}
	
	public Statistical(Set<Double> set)
	{
		if( set == null )
			throw new IllegalArgumentException("null Set passed to Statistical");
		
		this.data = convertSetDoubleToPrimitiveArray(set);
	}
	
	/**
	 * Returns the mean value  x&#x0304.<br>
	 * The mean is simply the average; sum of all elements, 
	 * divided by number of elements.<br>
	 * @param data of type double[]
	 * @return double
	 */
	public double mean()
	{
		double sum = 0.0d;
		for( int i = 0; i < data.length; i++ )
		{
			sum += data[i];
		}
		
		return sum/data.length;
	}
	
	
	/**
	 * Returns the median value, which is the 50th percentile.<br>
	 * @param data of type double[]
	 * @return double
	 */
	public double median()
	{
		
		if( data.length == 1 )
			return data[0];
		
		// sort the array in ascending order
		Arrays.sort(data);
		
		// if array length is not even, then return the
		// number in the exact middle
		if( data.length % 2 > 0)
		{
			//int index = (int)(data.length/2) + 1;
			// would be index + 1, but since arrays are
			// indexed at 0, we do not add 1
			int index = (int)(data.length/2);
			return data[index];
		} else {
			// array length is even number, so we must
			// return the average of the two numbers surrounding
			// the nonexistent middle
			int index = (int)(data.length/2);
			double val = (data[index] + data[index-1])/2;
			return val;
		}
		
	} // end median
	
	/**
	 * Calculates the Variance.<p>
	 * 
	 * In probability theory and statistics, variance is the expectation of the 
	 * squared deviation of a random variable from its mean, and it informally 
	 * measures how far a set of (random) numbers are spread out from their mean.
	 * @param data of type double[]
	 * @return double
	 */
	public double variance()
	{
		if( data.length == 1 )
			return 0.0d;
		
		// first, get the mean
		double mean = mean();
		
		// variable to store the sum of (element - mean)^2
		double sigmaElementMinusMeanSquared = 0.0d;
		
		for( int i = 0; i < data.length; i++ )
		{
			sigmaElementMinusMeanSquared += 
					(data[i] - mean)*(data[i] - mean);
		}
		
		return sigmaElementMinusMeanSquared / (data.length-1);
	}
	
	
	
	/**
	 * Calculates the Standard Deviation &sigma;.<p>
	 * 
 	 * The Standard Deviation &sigma; is a measure that is used to quantify the amount of 
 	 * variation or dispersion of a set of data values.<p>
 	 * 
 	 * A low Standard Deviation &sigma; indicates that the data points tend to be close to 
 	 * the mean (also called the expected value) of the set, while a high 
 	 * standard deviation indicates that the data points are spread out over a 
 	 * wider range of values.
	 * @param data of type double[]
	 * @return double
	 */
	public double standardDeviation()
	{
		// if only one data point, then there is no deviation
		if( data.length == 1 )
			return 0.0d;
		
		return java.lang.Math.sqrt( variance() );
	}
	
	
	/**
	 * Returns the coefficient of variation.<p>
	 * 
	 * In probability theory and statistics, the coefficient of variation (CV), 
	 * also known as relative standard deviation (RSD), is a standardized measure of 
	 * dispersion of a probability distribution or frequency distribution. 
	 * It is often expressed as a percentage, and is defined as the ratio of the 
	 * standard deviation &sigma; to the mean &#181.
	 * 
	 * @param data of type double[]
	 * @return double
	 */
	public double coefficientOfVariation()
	{
	
		return standardDeviation() / mean();
	}
	
	/**
	 * Converts a Double[] to a double[].
	 * @param array of type Double[]
	 * @return double
	 */
	public static double[] convertDoubleArrayToPrimitiveArray(final Double[] array)
	{
		if( array == null )
			throw new IllegalArgumentException(
								"null List passed to convertDoublArrayToPrimitive");
		
		// conver the List to an array
		double[] arrayPrimitive = new double[array.length];
		
		for( int i = 0; i < array.length; i++ )
		{
			arrayPrimitive[i] = array[i];
		}
		
		return arrayPrimitive;
	}
	
	
	/**
	 * Converts a {@code List<Double>} to a double[].
	 * @param list of type {@code List<Double>}
	 * @return double
	 */
	public static double[] 
			convertListDoubleToPrimitiveArray(final List<Double> list)
	{
		if( list == null )
			throw new IllegalArgumentException(
					"null List passed to convertListDoubleToPrimitiveArray");
		
		// conver the List to an array
		double[] arr = new double[list.size()];
		
		for( int i = 0; i < list.size(); i++ )
		{
			arr[i] = list.get(i);
		}
		
		return arr;
	}
	
	/**
	 * Converts a {@code Set<Double>} to a double[].
	 * @param set of type {@code Set<Double>}
	 * @return double
	 */
	public static double[] 
			convertSetDoubleToPrimitiveArray(final Set<Double> set)
	{
		if( set == null )
			throw new IllegalArgumentException(
					"null Set passed to convertSetDoubleToPrimitiveArray");
		
		// convert the Set<Double> to Double[]
		Double[] doubleArray = set.toArray(new Double[1]);
		
		// conver the List to an array
		double[] arr = new double[doubleArray.length];
		
		for( int i = 0; i < doubleArray.length; i++ )
		{
			arr[i] = doubleArray[i];
		}
		
		return arr;
	}
	
	
	/**
	 * Returns the range.<br>
	 * The range is the difference between the largest and smallest value.
	 * @param data of type double[]
	 * @return double
	 */
	public double range()
	{
		double min = data[0];
		double max = data[0];
		
		for( int i = 0; i < data.length; i++ )
		{
			if( data[i] < min )
				min = data[i];
			
			if( data[i] > max )
				max = data[i];
		}
		return max - min;
	}
	
	public String toString()
	{
		StringBuffer sb = new StringBuffer("[");
		for( int i = 0; i < data.length; i++ )
		{
			if( i > 0 )
				sb.append(",");
			
			sb.append(data[i]);
		}
		
		sb.append("]");
		
		return sb.toString();
	}
	
	
	/** 
	 * main function serves as a test harness. <br>
	 * Make sure the JVM is in your CLASSPATH and run the following
	 * command:<br>
	 * {@code java rsd.math.Statistics}
	 * @param args of type String[]
	 */
	// modify the data structures or create new ones
	// as needed to run test cases.
	public static void main(String[] args)
	{
		double[] data1 =  { 1.0, 4.0, 6.0, 19.0 };
		Double[] data2 =  { 1.0, 4.0, 8.0, 6.0, 14.0, 3.0, 19.0, 20.0 };
		
		List<Double> list = new ArrayList<>();
		for( int i = 5; i <= 10; i++  )
			list.add(new Double(i));
		
		Set<Double> set = new HashSet<>();
		for( int i = 30; i <= 39; i++  )
			set.add(new Double(i));
		
		Statistical stat1 = new Statistical(data1);
		Statistical stat2 = new Statistical(data2);
		Statistical stat3 = new Statistical(list);
		Statistical stat4 = new Statistical(set);
		
		System.out.println("Array: " 	+ 			stat1.toString() );
		System.out.println("range: " 	+ 			stat1.range() );
		System.out.println("mean: " 	+ 			stat1.mean() );
		System.out.println("median: " 	+ 			stat1.median() );
		System.out.println("standard deviation: " + stat1.standardDeviation() );
		System.out.println("coeff of variation: " + stat1.coefficientOfVariation() );
		System.out.println("variance: " 	+ 		stat1.variance() );

		System.out.println("");

		System.out.println("Array: " 	+ 			stat2.toString() );
		System.out.println("range: " 	+ 			stat2.range() );
		System.out.println("mean: " 	+ 			stat2.mean() );
		System.out.println("median: " 	+ 			stat2.median() );
		System.out.println("standard deviation: " + stat2.standardDeviation() );
		System.out.println("coeff of variation: " + stat2.coefficientOfVariation() );
		System.out.println("variance: " 	+ 		stat2.variance() );
		
		System.out.println("");
		
		System.out.println("Array: " 	+ 			stat3.toString() );
		System.out.println("range: " 	+ 			stat3.range() );
		System.out.println("mean: " 	+ 			stat3.mean() );
		System.out.println("median: " 	+ 			stat3.median() );
		System.out.println("standard deviation: " + stat3.standardDeviation() );
		System.out.println("coeff of variation: " + stat3.coefficientOfVariation() );
		System.out.println("variance: " 	+ 		stat3.variance() );
		
		System.out.println("");
		
		System.out.println("Array: " 	+ 			stat4.toString() );
		System.out.println("range: " 	+ 			stat4.range() );
		System.out.println("mean: " 	+ 			stat4.mean() );
		System.out.println("median: " 	+ 			stat4.median() );
		System.out.println("standard deviation: " + stat4.standardDeviation() );
		System.out.println("coeff of variation: " + stat4.coefficientOfVariation() );
		System.out.println("variance: " 	+ 		stat4.variance() );

	} // end main
}