public abstract class Individual<G, P> {
	
	protected Genotype<G>  genotype;
	protected Phenotype<P> phenotype;
	
	protected double fitness = 0;
	
	/**
	 * Constructor
	 * @param genotype
	 * @param phenotype
	 */
	public Individual(Genotype<G> genotype, Phenotype<P> phenotype) {
		this.genotype  = genotype;
		this.phenotype = phenotype;
		updatePhenotype();
	}
	
	/**
	 * Updates the phenotype of the individual
	 */
	public abstract void updatePhenotype();

	/**
	 * @return a clone of the individual
	 */
	public abstract Individual<G, P> clone();
	
	/** 
	 * @return the phenotype of the individual
	 */
	public abstract Phenotype<P> getPhenotype();
	
	/**
	 * Sets the fitness of the individual
	 * @param fitness
	 */
	public void setFitness(double fitness) {
		this.fitness = fitness;
	}
	
	/**
	 * Gets the fitness of the individual
	 */
	public double getFitness() {
		return this.fitness;
	}	
}
