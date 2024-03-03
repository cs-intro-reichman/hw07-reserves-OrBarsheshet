

public class HashTagTokenizer {

	public static void main(String[] args) {

		String hashTag = args[0];
		String [] dictionary = readDictionary("dictionary.txt");
		breakHashTag(hashTag, dictionary);
		System.out.println(existInDictionary(hashTag, dictionary));
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];
		In in = new In(fileName);
		for (int i = 1; i < dictionary.length; i++){
			String word = in.readLine();
			dictionary [i] = word;
			//System.out.println(dictionary[i]);
		}
		return dictionary;
	}

	public static boolean existInDictionary(String word, String [] dictionary) {
		for (int i = 0; i < dictionary.length; i++){
			if (dictionary[i] != null && dictionary[i].equals(word)){
				return true;
			}
		}
		return false;
	}

	public static void breakHashTag(String hashtag, String[] dictionary) {
		String hashtagLowerCase = hashtag.toLowerCase();
		//System.out.println(hashtagLowerCase);
		// Base case: do nothing (return) if hashtag is an empty string.
        if (hashtagLowerCase.isEmpty()) {
            return;
        }
 
        int N = hashtagLowerCase.length();
		int indexStart = 0;

        for (int i = 1; i <= N; i++) {
			String subSequence = (String) hashtagLowerCase.subSequence( indexStart , i);
			//System.out.println(subSequence);
			if (existInDictionary(subSequence, dictionary)){
				System.out.println(subSequence);
				indexStart= indexStart + subSequence.length();
				//System.out.println(indexStart);
				if (i<N){
				subSequence = (String) hashtag.subSequence(i,i+1);
				breakHashTag(subSequence, dictionary);
				}
			}
        }
    }

}
