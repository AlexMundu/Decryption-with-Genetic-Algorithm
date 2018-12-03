import java.util.List;

public interface Selector<G, P> {
	
	/**
	 * @param population the old population which is subjected to the selection process
	 * @param bestIndividualIndex the best individual of the population
	 * @return the new population (a list of individuals) after it has gone through the selection process
	 */
	List<Individual<G, P>> select(List<Individual<G, P>> population, int bestIndividualIndex);	
}
