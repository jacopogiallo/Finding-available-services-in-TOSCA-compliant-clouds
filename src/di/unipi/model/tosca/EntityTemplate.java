package di.unipi.model.tosca;

/**
 * Abstract class which models the "TEntityTemplate" element type (defined in the XML schema of TOSCA).
 * <br>
 * Copyright 2013 Jacopo Soldani (Computer Science department, University of Pisa)
 *
 * @author http://www.di.unipi.it/~soldani
 *
 * @version 1.0
 */
public abstract class EntityTemplate extends ExtensibleElements {
//	/**
//	 * It stores the (unique) identifier of the entity template under consideration.
//	 */
//	protected String id;

	/**
	 * It stores a reference to the type of the entity template under consideration.
	 */
	protected EntityType type;

//	/**
//	 * Provides access to the identifier of the current entity template.
//	 * @return The identifier of the entity template under consideration.
//	 */
//	public String getId(){
//		return id;
//	}

	/**
	 * Constructor.
	 * @param type EntityType of the entity template under definition.
	 */
	public EntityTemplate(EntityType type) {
		this.type = type;
	}

	/**
	 * Method which provides access to the type of the current entity template.
	 * @return The type of the current entity template.
	 */
	public abstract EntityType getType();
}
