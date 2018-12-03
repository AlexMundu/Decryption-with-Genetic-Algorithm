public class MissingStageException extends RuntimeException {
	
	private String message;
	
	/**
	 * @param message to be displayed along with exception
	 */
	public MissingStageException(String message) {
		this.message = message;
	}

	/**
	 * @return String when exception is thrown
	 */
	public String toString() {
		return String.format("%s\n%s", super.toString(), this.message);
	}	
}
