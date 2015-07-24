package di.unipi.model.tosca;

/**
 * Class which models the "TRequirementType" element type (defined in the XML schema of TOSCA).
 * <br>
 * Copyright 2013 Jacopo Soldani (Computer Science department, University of Pisa)
 *
 * @author http://www.di.unipi.it/~soldani
 *
 * @version 1.0
 */
public class RequirementType extends EntityType {
	/**
	 * It stores (if present) the CapabilityType required to satisfy the requirement type under definition.
	 */
	private CapabilityType requiredCapabilityType;

	/**
	 * Constructor.
	 * @param derivedFrom RequirementType from which the under definition one is derived.
	 * @param name Name of the under definition requirement type.
	 */
	public RequirementType(RequirementType derivedFrom, String name) {
		super(derivedFrom, name);
		requiredCapabilityType = null;
	}

	/**
	 * Constructor.
	 * @param derivedFrom RequirementType from which the under definition one is derived.
	 * @param name Name of the under definition requirement type.
	 * @param required CapabilityType required to satisfy the requirement type under definition.
	 */
	public RequirementType(RequirementType derivedFrom, String name, CapabilityType required) {
		super(derivedFrom, name);
		requiredCapabilityType = required;
	}

	/**
	 * Constructor.
	 * @param name Name of the under definition requirement type.
	 */
	public RequirementType(String name) {
		super(name);
		requiredCapabilityType = null;
	}

	/**
	 * Constructor.
	 * @param name Name of the under definition requirement type.
	 * @param required CapabilityType required to satisfy the requirement type under definition.
	 */
	public RequirementType(String name, CapabilityType required) {
		super(name);
		requiredCapabilityType = required;
	}

	/**
	 * Provides access to the CapabilityType required to satisfy the requirement type under definition.
	 * @return The CapabilityType required to satisfy the requirement type under definition.
	 */
	public CapabilityType getRequiredCapabilityType() {
		return requiredCapabilityType;
	}

//	@Override
//	public boolean equals(Object obj) {
//		RequirementType r = (RequirementType) obj;
//		return (name.equals(r.getName()) &&
//				requiredCapabilityType.equals(r.getRequiredCapabilityType()));
//	}

	@Override
	public RequirementType derivedFrom() {
		return (RequirementType) derivedFrom;
	}
}
