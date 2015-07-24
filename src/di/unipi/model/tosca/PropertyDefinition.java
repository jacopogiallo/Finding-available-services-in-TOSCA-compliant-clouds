package di.unipi.model.tosca;

/**
 * Class which models a property definition.
 * <br>
 * Copyright 2013 Jacopo Soldani (Computer Science department, University of Pisa)
 *
 * @author http://www.di.unipi.it/~soldani
 *
 * @version 1.0
 */
@SuppressWarnings("rawtypes")
public class PropertyDefinition {
	/**
	 * It stores the name of the property under definition.
	 */
	private String name;
	/**
	 * It stores the type of the property under definition.
	 */
	private Class type;

	/**
	 * Constructor.
	 *
	 * @param name Name of the property under definition.
	 * @param type Type of the property under definition.
	 */
	public PropertyDefinition(String name, Class type) {
		this.name = name;
		this.type = type;
	}

	/**
	 * Method which provides access to the name of the defined property.
	 *
	 * @return The name of the defined property.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Method which provides access to the type of the defined property.
	 *
	 * @return The type of the defined property.
	 */
	public Class getType() {
		return type;
	}

//	@Override
//	public boolean equals(Object obj) {
//		PropertyDefinition pDef = (PropertyDefinition) obj;
//		return (name.equals(pDef.getName()) &&
//				type.equals(pDef.getType()));
//	}
}