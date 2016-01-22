package Tests;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;

public class MapDataTest 
{
	public static void main(String[] args) {
		HashMap<String, Integer> myMap= new HashMap<String,Integer>();
		myMap.put(null, 123);
		myMap.put("Test2", null);
		myMap.put("test3", 12345);
		
		
		for (int i = 0; i < 3; i++) 
		{
			System.out.println(myMap.keySet().toArray()[i]);
			System.out.println(myMap.values().toArray()[i]);
		}

		/*
		SimpleTableDemo s = new SimpleTableDemo();
		s.setVisible(true);
		JFrame f = new JFrame();
		f.add(s);
		f.setVisible(true);
		*/
	}
	
	
}
