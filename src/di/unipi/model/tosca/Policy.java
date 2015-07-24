package di.unipi.model.tosca;

/**
 * Class which models the "TPolicy" element type (defined in the XML schema of TOSCA).
 * <br>
 * Copyright 2013 Jacopo Soldani (Computer Science department, University of Pisa)
 *
 * @author http://www.di.unipi.it/~soldani
 *
 * @version 1.0
 */
public class Policy extends ExtensibleElements {
	/**
	 * It stores the name of the policy under consideration.
	 */
	protected String name;

	/**
	 * It stores the type of the policy under consideration.
	 */
	protected PolicyType type;

	/**
	 * Constructor.
	 */
	public Policy(String name, PolicyType type) {
		this.name = name;
		this.type = type;
	}

	/**
	 * Method which provides access to the name of the current policy.
	 * @return The name of the policy under consideration.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Method which provides access to the type of the current policy.
	 * @return The PolicyType of the policy under consideration.
	 */
	public PolicyType getType() {
		return type;
	}
}
