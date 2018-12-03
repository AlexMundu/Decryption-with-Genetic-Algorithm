import java.util.Arrays;

public class Test {

	public static void main(String[] args) {
		Character[] userEncryptionKey = {'s', ' ', 'p', 'x', 'm', 'o', 'h', 'q', 'f', 'k', 'j', 'i', 'z', 'g', 'e', 'w', 'n', 'd', 'l', 't', 'y', 'c', 'r', 'v', 'b', 'u', 'a'};
		String originalMsg = "Notice the difference? Instead of sending HTML, the server now sends data, and the \"data to HTML\" conversion step happens on the".toLowerCase()
				+ " client instead (which is why you're also sending along the code that tells the client how to perform said conversion)".toLowerCase()
				+ " and just to complement this message I have to say query, zone, binary, killer and exam, in order to get 27 of 27 correct matches or as many as we can".toLowerCase();

		GeneticAlgorithm<Character, Character> ga = new GeneticAlgorithm<>();
		ga.setGenerations(200);
		ga.setPopulationSize(800);

		GeneticAlgorithm.setEncryptedMsg(encryptMsg(originalMsg, userEncryptionKey));
		System.out.println("Encrypted msg: " + GeneticAlgorithm.getEncryptedMsg());

		ObjectiveFunction<Character, Character> function  = FrequencyEvaluation.getInstance();

		IndividualFactory<Character, Character> factory   = new CSIndividualFactory();

		Selector<Character, Character>          selector  = new CSSelector<Character, Character>();
		Crossover<Character>                 crossover = CSCrossover.getInstance();
		Mutator<Character>                   mutator   = CSMutator.getInstance();

		ga.setObjectiveFunction(function);
		ga.setIndividualFactory(factory);
		ga.setSelector (selector);		
		ga.setCrossover(crossover);
		ga.setMutator  (mutator);
		ga.run();

		Phenotype<Character> phen = ga.getBest();

		//Alphabet to check solution
		Character[] alphabet = {'a','b','c', 'd', 'e', 'f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',' '};
		System.out.println("\nEncrypted Message:\n" + GeneticAlgorithm.getEncryptedMsg());
		System.out.println("\nAlphabet:" + Arrays.toString(alphabet));
		System.out.println("\nBest decryption key solution:\n" + phen);
		System.out.println("Solution key:\n" + Arrays.toString(userEncryptionKey));
		System.out.println("Possible Decrypted Message: \n" + decryptMsg(phen, GeneticAlgorithm.getEncryptedMsg()));
		System.out.println("\nOrginal Message: \n" + originalMsg);
		System.out.println();
		System.out.println("Correct matches within the solution: " + countCorrectGenes(phen, userEncryptionKey));

	}
	//Use this to check the success rate of the algorithm
	public static int countCorrectGenes(Phenotype <Character> phenotype, Character[] keyMap) {
		int corrects = 0;
		for(int i = 0; i < keyMap.length; i++) 
			if(phenotype.getValue(i) == keyMap[i])	corrects ++;
		return corrects;
	}
	
	//User uses this to encrypt msg and then feed this to the algorithm
	public static String encryptMsg(String s, Character[] keyMap) {

		String res = "";
		for (int i = 0, index; i < s.length(); i++) {
			if (s.charAt(i) == ' ' || s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
				index = s.charAt(i) == ' ' ? 26 
						: s.charAt(i) - 'a'; // converts index to a value from 0 to 26 (key'length)
				res += keyMap[index];	//Uses the index and the value stored in index to decrypt
			} else {
				res += s.charAt(i);	//not used, simply copied
			}
		}
		return res;
	}
	
	//Main uses this to decrypt msg and then check success of algorithm
	public static String decryptMsg(Phenotype <Character> phenotype, String encryptedMsg) {
		String decryptedMsg = "";

		for (int i = 0, index; i < encryptedMsg.length(); i++) {
			if (encryptedMsg.charAt(i) == ' ' || encryptedMsg.charAt(i) >= 'a' && encryptedMsg.charAt(i) <= 'z') {
				for(index = 0; index < 27; index++){
					if(encryptedMsg.charAt(i) == phenotype.getValue(index)) break;
				}
				if(index == 26) decryptedMsg += ' ';	//Uses the index and the value stored in index to decrypt
				else decryptedMsg += (char)(index + 97);
			} else {
				decryptedMsg += encryptedMsg.charAt(i);	//not used, simply copied
			}
		}
		return decryptedMsg;
	}

}
