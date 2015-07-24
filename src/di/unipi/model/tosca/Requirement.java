package di.unipi.model.tosca;

/**
 * Class which models the "TRequirement" element type (defined in the XML schema of TOSCA).
 * <br>
 * Copyright 2013 Jacopo Soldani (Computer Science department, University of Pisa)
 *
 * @author http://www.di.unipi.it/~soldani
 *
 * @version 1.0
 */
public class Requirement extends EntityTemplate{
	public Requirement(String name, RequirementType rType) {
		super(rType);
		this.name = name;
	}

	/**
	 * It stores the name of the requirement under consideration.
	 */
	protected String name;

	/**
	 * Method which provides access to the name of the current requirement.
	 * @return The name of the requirement under consideration.
	 */
	public String getName(){
		return name;
	}

	@Override
	public RequirementType getType() {
		return (RequirementType) type;
	}
}
