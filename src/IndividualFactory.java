public abstract class IndividualFactory<G, P> {
	/** 
	 * @return a randomly created individual
	 */
	public abstract Individual<G, P> create();	
}
