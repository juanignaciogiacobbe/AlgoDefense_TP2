package edu.fiuba.algo3.modelo;

/**
 * The CustomLogger class provides a singleton logger instance that can be used for logging messages.
 * It follows the Singleton design pattern, ensuring that only one instance of the logger is created.
 * To use the logger, create an instance of CustomLogger in each class that requires logging, and use it as a field.
 */
public class CustomLogger {
	private static CustomLogger instance;

	/**
	 * Private constructor to prevent direct instantiation of the CustomLogger class.
	 * The constructor is empty as there is no initialization required.
	 */
	private CustomLogger() {
		// Empty constructor
	}

	/**
	 * Returns the instance of the CustomLogger class.
	 * If an instance does not exist, a new instance is created.
	 *
	 * @return The instance of the CustomLogger class.
	 */
	public static synchronized CustomLogger getInstance() {
		if (instance == null) {
			instance = new CustomLogger();
		}
		return instance;
	}

	/**
	 * Logs a message to the console.
	 *
	 * @param message The message to be logged.
	 */
	public void log(String message) {
		System.out.println(message);
	}
}
