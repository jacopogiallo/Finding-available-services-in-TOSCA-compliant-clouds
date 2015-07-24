package di.unipi.model.tosca;

/**
 * Class which models the "TParameter" element type (defined in the XML schema of TOSCA).
 * <br>
 * Copyright 2013 Jacopo Soldani (Computer Science department, University of Pisa)
 *
 * @author http://www.di.unipi.it/~soldani
 *
 * @version 1.0
 */
@SuppressWarnings("rawtypes")
public class Parameter {
	/**
	 * It stores the name of the parameter.
	 */
	private String name;

	/**
	 * It stores the type of the parameter.
	 */
	private Class type;

	/**
	 * It indicates whether the parameter is required.
	 */
	private boolean required;

	/**
	 * Constructor.
	 *
	 * @param name Name of the parameter.
	 * @param type Type of the parameter.
	 */
	public Parameter(String name, Class type) {
		this.name = name;
		this.type = type;
		this.required = true;
	}

	/**
	 * Constructor.
	 *
	 * @param name Name of the parameter.
	 * @param type Type of the parameter.
	 * @param required Boolean which indicates whether the parameter is required.
	 */
	public Parameter(String name, Class type, boolean required) {
		this.name = name;
		this.type = type;
		this.required = required;
	}

	/**
	 * Provides access to the name of the parameter.
	 * @return The name of the parameter.
	 */
	public String getName(){
		return name;
	}

	/**
	 * Provides access to the type of the parameter.
	 * @return The type of the parameter.
	 */
	public Class getType(){
		return type;
	}

	/**
	 * Indicates whether the parameter is required.
	 * @return A boolean which represents whether the parameter is required.
	 */
	public boolean isRequired(){
		return required;
	}

//	@Override
//	public boolean equals(Object o) {
//		Parameter p = (Parameter) o;
//		return (name.equals(p.getName()) && type.equals(p.getType()) &&
//				required==p.isRequired());
//	}
}
