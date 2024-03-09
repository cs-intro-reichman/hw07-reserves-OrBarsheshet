
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		//System.out.println(dictionary[2]);
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
		//System.out.println(tail(word)); 
	}

	public static String tail(String str) {
		String tail = str.substring(1);
		return tail;
	}

	public static int levenshtein(String word1, String word2) {
		//int count = 0;
		String word1Lower = word1.toLowerCase(); 
		String word2Lower = word2.toLowerCase(); 
		if (word1Lower.isEmpty()){
			return word2Lower.length();
		}
		if (word2Lower.isEmpty()){
			return word1Lower.length();
		}
		if ( word1Lower.charAt(0) == word2Lower.charAt(0)){
			return levenshtein(tail(word1Lower), tail(word2Lower));
		}
		else{//return the min + 1 
			return (1 + Math.min(Math.min(levenshtein(tail(word1Lower), word2Lower), levenshtein(word1Lower, tail(word2Lower))), levenshtein(tail(word1Lower), tail(word2Lower))));

			// int min1 = Math.min(levenshtein(tail(word1Lower), word2Lower), levenshtein(tail(word2Lower), word2Lower));
			// int min2 = Math.min(levenshtein(tail(word2Lower), word2Lower), levenshtein(tail(word1Lower), tail(word2Lower)));
			// int min3 = Math.min(levenshtein(tail(word1Lower), word2Lower),  levenshtein(tail(word1Lower), tail(word2Lower)));
			// return (1+ Math.min(min1, Math.min(min2, min3)));


		}
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];
		In in = new In(fileName);
		for (int i = 0; i < dictionary.length; i++){
			String word = in.readLine();
			dictionary [i] = word;
			//System.out.println(dictionary[i]);
		}
		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		int index = 0;
		int levValue = levenshtein(word, dictionary[0]); 
		for (int i = 1; i < dictionary.length ; i++){
			if (levenshtein(word, dictionary[i]) < levValue){
				levValue = levenshtein(dictionary[i], word);
				index = i;
			} 
		}
		if (levValue <= threshold) return dictionary[index]; 
		else return word;
	}

}
