package di.unipi.model.tosca;

/**
 * Class which models the "TRequirementDefinition" element type (defined in the XML schema of TOSCA).
 * <br>
 * Copyright 2013 Jacopo Soldani (Computer Science department, University of Pisa)
 *
 * @author http://www.di.unipi.it/~soldani
 *
 * @version 1.0
 */
public class RequirementDefinition extends ExtensibleElements {
	/**
	 * It stores the name of the requirement under definition.
	 */
	private String name;

	/**
	 * It stores the type of the requirement under definition.
	 */
	private RequirementType requirementType;

	/**
	 * Constructor.
	 *
	 * @param name Name of the requirement under definition.
	 * @param requirementType Type of the requirement under definition.
	 */
	public RequirementDefinition(String name, RequirementType requirementType){
		this.name = name;
		this.requirementType = requirementType;
	}

	/**
	 * Provides access to the name of the requirement under definition.
	 * @return Name of the requirement under definition.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Provides access to the type of the requirement under definition
	 * @return Type of the requirement under definition
	 */
	public RequirementType getRequirementType() {
		return requirementType;
	}

//	@Override
//	public boolean equals(Object obj) {
//		RequirementDefinition r = (RequirementDefinition) obj;
//		return (name.equals(r.getName()) &&
//				requirementType.equals(r.getRequirementType()));
//
//	}
}
