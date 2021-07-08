package eserciziConnessioni;

import java.sql.SQLException;
//import java.util.Arrays;
import java.util.List;
//import java.util.stream.Stream;

public class Start {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		TirocinioDbConnector d = new TirocinioDbConnector();
		List<Object[]> ls = d.cercaTirocinanti();
		//Arrays.toString(ls);
		
		for(Object[] f : ls) {
			
			System.out.print(f[0]+" ");
			System.out.print(f[1]+" ");
			System.out.print(f[2]+" ");
			System.out.println(f[3]);
		}
		
		List<Object[]> tut = d.cercaTutor("Inf");
		System.out.println();
		for(Object[] f : tut) {
			System.out.print(f[0]+" ");
			System.out.print(f[1]+" ");
			System.out.print(f[2]+" ");
			System.out.println(f[3]);
		}
		//d.insertTirocinante(9, "3235", "Ciccii", "Pasticcio", "5E", 2, 3);
		d.deleteTirocinante(9);
		//d.deleteTirocinante(10);
		//d.updateTirocinante(9, "9235", "Ciccii", "Pacio", "5E", 2, 3);
		System.out.println();
		List<Object[]> ls1 = d.cercaTirocinantiPerAzienda("Microsoft");
		//Arrays.toString(ls);
		
		for(Object[] f : ls1) {
			
			System.out.print(f[0]+" ");
			System.out.print(f[1]+" ");
			System.out.print(f[2]+" ");
			System.out.println(f[3]);
		}
	}

}
