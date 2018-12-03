public abstract class Genotype<T> {
	
	/**
	 * @param index the index of the array (chromosome) which contains the gene you want to set
	 * @param value the value of the gene you want to set
	 */
	public abstract void setGene(int index, T value);
	
	/**
	 * @param index the index of the array (chromosome) which contains the gene you want to get
	 * @return the gene in the specified index
	 */
	public abstract T getGene(int index);
	
	/**
	 * @return the length of the chromosome of the genotype
	 */
	public abstract int length();
	
	/**
	 * Shuffles the chromosome of the genotype
	 */
	public abstract void randomize();
		
	/**
	 * @return a clone of the genotype
	 */
	public abstract Genotype<T> clone();
	
}
