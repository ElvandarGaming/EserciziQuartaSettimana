package esercizioVenerdi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class findModeInString {

	public static void main(String[] args) {

		String frase = "prova prova prova culo culo culo culo";
		System.out.println(findMode(frase));
		
	}

	
	public static String findMode(String frase) {
		Map<String,ContaStringa> ripetiz = new HashMap<>();
		
		String[] parole = frase.split(" ");
		
		for(int i = 0;i<parole.length;i++) {
			if(ripetiz.containsKey(parole[i])) {
				ripetiz.get(parole[i]).add();
			}else {
				ripetiz.put(parole[i], new ContaStringa(parole[i]));
			}
		}
		/*
		int max =0;
		String mode = null;
		for (Map.Entry<String,ContaStringa> kv : ripetiz.entrySet()) {
			if(kv.getValue().getNumeroRipetizione()>max) {
				max=kv.getValue().getNumeroRipetizione();
				mode=kv.getValue().getStringa();
			}
		}
		*/
		ContaStringa x = ripetiz.entrySet().stream().map(Map.Entry::getValue).max((cs1,cs2)-> cs1.getNumeroRipetizione() - cs2.getNumeroRipetizione()).get();
		//int in =ripetiz.values().stream().map(cs -> cs.getNumeroRipetizione()).max(Integer::compareTo).get();
		String mode = x.getStringa();
		//String s =ripetiz.values().stream().map(cs -> cs.getNumeroRipetizione());
		return  mode;
	}
	
	public static List<String> findModes(String frase) { //non finito
		Map<String,ContaStringa> ripetiz = new HashMap<>();
		String[] parole = frase.split(" ");
		
		for(int i = 0;i<parole.length;i++) {
			if(ripetiz.containsKey(parole[i])) {
				ripetiz.get(parole[i]).add();
			}else {
				ripetiz.put(parole[i], new ContaStringa(parole[i]));
			}
		}
		int max =0;
		List<String> mode = new ArrayList<>();
		for (Map.Entry<String,ContaStringa> freq : ripetiz.entrySet()) {
			if(freq.getValue().getNumeroRipetizione()>max) {
				max=freq.getValue().getNumeroRipetizione();
				mode.add(freq.getValue().getStringa());
			}
		}
		
		//int x =ripetiz.values().stream().map(cs -> cs.getNumeroRipetizione()).max(Integer::compareTo).get();
		//String s =ripetiz.values().stream().map(cs -> cs.getNumeroRipetizione());
		return  mode; //TODO
	}
	
	
	
}
