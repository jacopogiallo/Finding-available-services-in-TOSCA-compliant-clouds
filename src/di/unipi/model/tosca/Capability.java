package di.unipi.model.tosca;

/**
 * Class which models the "TCapability" element type (defined in the XML schema of TOSCA).
 * <br>
 * Copyright 2013 Jacopo Soldani (Computer Science department, University of Pisa)
 *
 * @author http://www.di.unipi.it/~soldani
 *
 * @version 1.0
 */
public class Capability extends EntityTemplate{
	/**
	 * It stores the name of the requirement under consideration.
	 */
	protected String name;

	/**
	 * Constructor.
	 * @param name Name of the capability under definition.
	 * @param cType CapabilityType of the capability under definition.
	 */
	public Capability(String name, CapabilityType cType) {
		super(cType);
		this.name = name;
	}

	/**
	 * Method which provides access to the name of the current requirement.
	 * @return The name of the requirement under consideration.
	 */
	public String getName(){
		return name;
	}

	@Override
	public CapabilityType getType() {
		return (CapabilityType) type;
	}
}
