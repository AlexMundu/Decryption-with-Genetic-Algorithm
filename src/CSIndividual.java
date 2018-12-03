public class CSIndividual extends Individual<Character, Character> {

	/**
	 * Constructor calls the constructor of the Individual class
	 * @param gen
	 * @param phen
	 */
	public CSIndividual(Genotype<Character> gen, Phenotype<Character> phen) {
		super(gen, phen);
	}

	@Override
	/**
	 * Updates the phenotype of the individual to set its array equal to its genotype's array
	 */
	public void updatePhenotype() {
		for (int i = 0; i < 27; i++)
			this.phenotype.setValue(i, this.genotype.getGene(i));
	}
	
	@Override
	/**
	 * @return a clone of the individual calling the clone methods of genotype and phenotype and setting the fitness 
	 */
	public Individual<Character, Character> clone() {
		Individual<Character, Character> clone = new CSIndividual(super.genotype.clone(), super.phenotype.clone());
		clone.fitness = this.fitness;
		return clone;
	}
	
	@Override
	/** 
	 * @return the phenotype of the individual
	 */
	public Phenotype<Character> getPhenotype() {
		return this.phenotype;
	}

	/**
	 * @return the infromation regarding the individual as a string
	 */
	public String toString() {
		return super.genotype.toString() + "\n" + super.phenotype.toString() + "\n" + "Fitness: " + super.getFitness();
	}

}