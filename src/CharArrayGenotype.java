import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CharArrayGenotype extends Genotype<Character>{

	private Character [] geneArray = null;

	/**
	 * Constructor
	 */
	public CharArrayGenotype() {
		this.geneArray = new Character[27];
	}

	@Override
	/** 
	 * @param index the index of the array of Characters which contains the Character you want to set
	 * @param value the value of the Character you want to set
	 */
	public void setGene(int index, Character value) {
		this.geneArray[index]= value;		
	}

	@Override
	/**
	 * @param index the index of the array of Characters which contains the Character you want to get
	 * @return the Character in the specified index
	 */
	public Character getGene(int index) {
		return geneArray[index];
	}

	@Override
	/**
	 * @return the length of the array of the genotype
	 */
	public int length() {
		return geneArray.length;
	}

	@Override
	/**
	 * Shuffles the array of Characters using the shuffle method of the Collections class 
	 */
	public void randomize() {
		Character[] InitialCharArray = {'a','b','c', 'd', 'e', 'f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',' '};
		List<Character> TemporalList = Arrays.asList(InitialCharArray);
		Collections.shuffle(TemporalList);
		Character[] shuffledArray = (Character[]) TemporalList.toArray();	
		for (int i = 0; i < this.length(); i++)
			geneArray[i] = shuffledArray[i];		
	}

	@Override
	/**
	 * @return a clone of the CharArrayGenotype
	 */
	public CharArrayGenotype clone() {
		CharArrayGenotype clone = new CharArrayGenotype();
		for (int i = 0; i < this.length(); i++)
			clone.geneArray[i] = this.geneArray[i];
		return clone;
	}
	
	/**
	 * @return the gene array of the genotype as a string
	 */
	public String toString() {
		return "Genotype:\n" + Arrays.toString(geneArray);
	}
}
