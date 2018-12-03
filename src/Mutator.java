public abstract class Mutator<T> {
	/**
	 * Genetic operator which alters one or more gene values in a genotype
	 * Helps introduce and maintain diversity within the population and prevent premature convergence
	 * @param genotype
	 */
	public abstract void mutate(Genotype<T> genotype);

}
