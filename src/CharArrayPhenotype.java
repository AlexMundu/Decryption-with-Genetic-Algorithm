import java.util.Arrays;

public class CharArrayPhenotype extends Phenotype<Character> {

	private Character [] geneArray = null;

	/**
	 * Constructor
	 */
	public CharArrayPhenotype() {
		this.geneArray = new Character[27];
	}

	@Override
	/** 
	 * @param index the index of the array of Characters which contains the Character you want to set
	 * @param value the value of the Character you want to set
	 */
	public void setValue(int index, Character value) {
		this.geneArray[index]= value;		
	}

	@Override
	/**
	 * @param index the index of the array of Characters which contains the Character you want to get
	 * @return the Character in the specified index
	 */
	public Character getValue(int index) {
		return geneArray[index];
	}

	@Override
	/**
	 * @return the length of the array of the phenotype
	 */
	public int length() {
		return geneArray.length;
	}

	@Override
	/**
	 * @return a clone of the CharArrayGenotype
	 */
	public CharArrayPhenotype clone() {
		CharArrayPhenotype clone = new CharArrayPhenotype();
		for (int i = 0; i < this.length(); i++)
			clone.geneArray[i] = this.geneArray[i];
		return clone;
	}

	/**
	 * @return the phenotype as a string
	 */
	public String toString() {
		return "Phenotype:\n" + Arrays.toString(geneArray);
	}

}
