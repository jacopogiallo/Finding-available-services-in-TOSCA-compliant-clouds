package di.unipi.model.tosca;

/**
 * Class which models the "TCapabilityType" element type (defined in the XML schema of TOSCA).
 * <br>
 * Copyright 2013 Jacopo Soldani (Computer Science department, University of Pisa)
 *
 * @author http://www.di.unipi.it/~soldani
 *
 * @version 1.0
 */
public class CapabilityType extends EntityType {
	/**
	 * Constructor.
	 * @param derivedFrom CapabilityType from which the under definition one is derived.
	 * @param name Name of the capability type under definition.
	 */
	public CapabilityType(CapabilityType derivedFrom, String name) {
		super(derivedFrom, name);
	}

	/**
	 * Constructor.
	 * @param name Name of the capability type under definition.
	 */
	public CapabilityType(String name) {
		super(name);
	}

	@Override
	public CapabilityType derivedFrom() {
		return (CapabilityType) derivedFrom;
	}
}
