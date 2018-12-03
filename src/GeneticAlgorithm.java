import java.util.ArrayList;
import java.util.List;

public class GeneticAlgorithm<G, P> {

	private IndividualFactory<G, P> individualFactory = null;
	private Selector<G, P> selector = null;
	private Crossover<G> crossover = null;
	private Mutator<G> mutator = null;
	private ObjectiveFunction<G, P> objectiveFunction = null;

	private List<Individual<G, P>> population = null;
	private int populationSize = 0;
	private int generations = 0;
	private int bestIndividualIndex = -1;
	private static String encryptedMsg = "";

	/**
	 * @param s Encrypted Message the user provides for the algorithm to decrypt
	 */
	public static void setEncryptedMsg(String s) {
		encryptedMsg = s;
	}

	/**
	 * @return Encrypted Message the algorithm aims to decrypt
	 */
	public static String getEncryptedMsg() {
		return(encryptedMsg);
	}

	/**
	 * Sets the number of generations the population "evolves" toward an optimal solution
	 * @param generations
	 * @throws IllegalArgumentException
	 */
	public void setGenerations(int generations) throws IllegalArgumentException {
		if(generations <= 0) throw new IllegalArgumentException("Generations number must be greater than 0");
		this.generations = generations;
	}

	/**
	 * Sets the size of the population of individual solutions
	 * @param populationSize
	 */
	public void setPopulationSize(int populationSize) {
		if(populationSize <= 1) throw new IllegalArgumentException("Population size must be greater than 1");
		this.populationSize = populationSize;
	}

	/**
	 * Sets the objective funtion used to evaluate the solutions
	 * @param objectiveFunction
	 * @throws IllegalArgumentException
	 */
	public void setObjectiveFunction(ObjectiveFunction<G, P> objectiveFunction) throws IllegalArgumentException {
		if(objectiveFunction == null) throw new IllegalArgumentException("Objective function must not be null");
		this.objectiveFunction = objectiveFunction;
	}

	/**
	 * Sets the selection method used
	 * @param selector
	 * @throws IllegalArgumentException
	 */
	public void setSelector(Selector<G, P> selector) throws IllegalArgumentException {
		if(selector == null) throw new IllegalArgumentException();
		this.selector = selector;
	}

	/**
	 * Sets the crossover method used
	 * @param crossover
	 * @throws IllegalArgumentException
	 */
	public void setCrossover(Crossover<G> crossover) throws IllegalArgumentException {
		if(crossover == null) throw new IllegalArgumentException();
		this.crossover = crossover;
	}

	/**
	 * Sets the mutator method used
	 * @param mutator
	 * @throws IllegalArgumentException
	 */
	public void setMutator(Mutator<G> mutator) throws IllegalArgumentException {
		if(mutator == null) throw new IllegalArgumentException();
		this.mutator = mutator;
	}

	/**
	 * Performs selection method on the current population
	 * @param best
	 */
	public void selection(int best) {
		this.population = selector.select(this.population, best);
	}

	/**
	 * Calculates and sets all of the individuals' fitness values 
	 * @return best individual of the current population
	 */
	public int updateFitness() {

		int bestIndividual = -1;
		double bestFitness = -1;
		int i;
		for (i = 0; i < this.populationSize; i++) {
			Individual<G, P> individual = this.population.get(i);
			double fitness = this.objectiveFunction.calcFitness(individual.phenotype, encryptedMsg);
			individual.setFitness(fitness);
			if(fitness > bestFitness) {
				bestFitness = fitness;
				bestIndividual = i;
			}
		}
		return bestIndividual;
	}
	/**
	 * Performs crossover on a certain (large) portion of the population, always excluding the best individual (elitist)
	 * This is to ensure the survival of individuals with high fitness values
	 * Probability of crossover: 65%
	 * @param best
	 */
	public void crossover(int best) {
		for (int i = 0; i < this.populationSize; i += 2) {
			if(i == best || i + 1 == best) continue;	
			Individual<G, P> individual0 = this.population.get(i);
			Individual<G, P> individual1 = this.population.get(i + 1);
			if (Math.random() < 0.65)	crossover.cross(individual0.genotype, individual1.genotype);
		}
	}

	/**
	 * Performs mutation on a certain (small) portion of the population, always excluding the best individual (elitist)
	 * This is to ensure the survival of individuals with high fitness values
	 * Probability of mutation: 20%
	 * @param best
	 */
	public void mutation(int best) {
		for (int i = 0; i < this.populationSize; i++) {
			if(i == best) continue;
			Individual<G, P> individual = this.population.get(i);
			if (Math.random() < 0.2)	mutator.mutate(individual.genotype);
		}
	}

	/**
	 * Sets the individual factory method used
	 * @param individualFactory
	 */
	public void setIndividualFactory(IndividualFactory<G, P> individualFactory) {
		this.individualFactory = individualFactory;
	}

	/**
	 * Creates a new population and fills it with randomly created solutions using the individual factory
	 */
	private void initPopulation() {
		this.population = new ArrayList<>(this.populationSize);
		for (int i = 0; i < this.populationSize; i++) {
			Individual<G, P> individual = this.individualFactory.create();
			this.population.add(individual);
		}
	}

	/**
	 * The algorithm's main method performs the entire evolution cycle
	 */
	public void run() {
		if(this.objectiveFunction == null) throw new MissingStageException("Objective function is not specified");
		if(this.individualFactory == null) throw new MissingStageException("Individual factory is not specified");
		if(this.selector  == null) throw new MissingStageException("Selector method is not specified");
		if(this.crossover == null) throw new MissingStageException("Crossover method is not specified");
		if(this.mutator   == null) throw new MissingStageException("Mutator method is not specified");

		initPopulation();

		for (int g = 0; g < this.generations; g++) {	//evolution cycles
			bestIndividualIndex = updateFitness();
			selection(bestIndividualIndex);
			crossover(bestIndividualIndex);
			mutation(bestIndividualIndex);
			for(Individual<G, P> each : this.population)
				each.updatePhenotype();	//assures the genotype and phenotype are always identical

			System.out.printf("%d: %s\n", g, this.population.get(bestIndividualIndex));	//prints the best solution in each generation
		}
	}

	/** 
	 * @return the best individual's phenotype
	 */
	public Phenotype<P> getBest() {
		if(bestIndividualIndex == -1) return null;
		Individual<G, P> bestIndividual = population.get(bestIndividualIndex);
		Phenotype<P> phenotype = bestIndividual.getPhenotype();
		return phenotype;
	}

}
