package samples.other;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Java17 {
	
	int i;
	
	static public void main(String args[]) {
		/*
		testNewStringFeatures();
		System.out.println("******************************");
		testTimeUnit();*/
		int a;
		System.out.println(new Java17().i);
	}
	
	
	public static void testNewStringFeatures() {
		
		String str1;
		String str2;

		// isBlank()
		str1 = "";
		str2 = "this is a test";
		
		System.out.println("Is str1 blank? "+ str1.isBlank());
		System.out.println("Is str2 blank? "+ str2.isBlank());
		
		// lines();		
		str1 = "First line\nSecond Line\n Third Line";
		System.out.println(str1.lines().collect(Collectors.toList()));

		
		// repeat()		
		str1 = "This is a test!";
		System.out.println(str1.repeat(4));
		
		
		// strip methods
		str1 = "     This is a test!   ";
		System.out.println(str1.stripLeading());
		System.out.println(str1.stripTrailing());
		System.out.println(str1.strip());
		System.out.println(str1.strip());
	}
	
	public static void testTimeUnit() {
		TimeUnit c = TimeUnit.DAYS;		
		System.out.println(c.convert(Duration.ofHours(50)));
	}
}
