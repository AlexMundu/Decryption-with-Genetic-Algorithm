public abstract class Phenotype<T> {
	
	/**
	 * @param index the index of the array which contains the value you want to set
	 * @param value the value of the value you want to set
	 */
	public abstract void setValue(int index, T value);
	
	/**
	 * @param index the index of the array which contains the value you want to get
	 * @return the value in the specified index
	 */
	public abstract T getValue(int index);
	
	/**
	 * @return the length of the phenotype
	 */
	public abstract int length();
	
	/**
	 * @return a clone of the phenotype
	 */
	public abstract Phenotype<T> clone();
	
}
