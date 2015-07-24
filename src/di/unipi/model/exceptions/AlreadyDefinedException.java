package di.unipi.model.exceptions;

/**
 * Class which models the exception to be raised when adding TOSCA elements which are already defined.
 * <br>
 * Copyright 2013 Jacopo Soldani (Computer Science department, University of Pisa)
 *
 * @author http://www.di.unipi.it/~soldani
 *
 * @version 1.0
 */
@SuppressWarnings("serial")
public class AlreadyDefinedException extends Exception {
	/**
	 * Constructor.
	 *
	 * @param info Further information about the exception.
	 */
	public AlreadyDefinedException(String info){
		super(info);
	}

}
