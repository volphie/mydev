package my.exercise.java.properties;

/**
 * An example for getting System Properties and Environmental Variables
 */

public class SystemProperties  {

    public static void main(String[] args) {
				
		System.out.println("\n[System Properties]");
		System.getProperties().forEach((key, value) -> System.out.println(key + " : " + value));
		
		System.out.println("\n[Environment Variables]");
		System.getenv().forEach((key, value) -> System.out.println(key + " : " + value));
		
    }

}
