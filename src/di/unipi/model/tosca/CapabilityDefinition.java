package di.unipi.model.tosca;

/**
 * Class which models the "TCapabilityDefinition" element type (defined in the XML schema of TOSCA).
 * <br>
 * Copyright 2013 Jacopo Soldani (Computer Science department, University of Pisa)
 *
 * @author http://www.di.unipi.it/~soldani
 *
 * @version 1.0
 */
public class CapabilityDefinition extends ExtensibleElements {
	/**
	 * It stores the name of the capability under definition.
	 */
	private String name;

	/**
	 * It stores the type of the capability under definition.
	 */
	private CapabilityType capabilityType;

	/**
	 * Constructor.
	 *
	 * @param name Name of the capability under definition.
	 * @param capabilityType Type of the capability under definition.
	 */
	public CapabilityDefinition(String name, CapabilityType capabilityType){
		this.name = name;
		this.capabilityType = capabilityType;
	}

	/**
	 * Provides access to the name of the capability under definition.
	 * @return Name of the capability under definition.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Provides access to the type of the capability under definition
	 * @return Type of the capability under definition
	 */
	public CapabilityType getCapabilityType() {
		return capabilityType;
	}

//	@Override
//	public boolean equals(Object obj) {
//		CapabilityDefinition cDef = (CapabilityDefinition) obj;
//		return (name.equals(cDef.getName()) &&
//				capabilityType.equals(getCapabilityType()));
//	}
}
