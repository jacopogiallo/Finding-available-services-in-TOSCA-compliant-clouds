package di.unipi.model.tosca;

/**
 * Class which models the "TServiceTemplate" element type (defined in the XML schema of TOSCA).
 * <br>
 * Copyright 2013 Jacopo Soldani (Computer Science department, University of Pisa)
 *
 * @author http://www.di.unipi.it/~soldani
 *
 * @version 1.0
 */
public class ServiceTemplate extends ExtensibleElements {
	/**
	 * It stores the name of the service template under consideration.
	 */
	protected String name;

	/**
	 * It stores the boundary definitions of the service template under consideration.
	 */
	protected BoundaryDefinitions bounds;

	/**
	 * Constructor.
	 * @param name Name of the service template under definition.
	 */
	public ServiceTemplate(String name) {
		this.name = name;
		bounds = new BoundaryDefinitions();
	}

	/**
	 * Method which provides access to the name of the current service template.
	 * @return The name of the service template under consideration.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Method which provides access to the boundary definitions of the current service template.
	 * @return The BoundaryDefinitions of the service template under consideration.
	 */
	public BoundaryDefinitions getBoundaryDefinitions() {
		return bounds;
	}

}
