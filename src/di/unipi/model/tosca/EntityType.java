package di.unipi.model.tosca;

import java.util.*;

import di.unipi.model.exceptions.AlreadyDefinedException;

/**
 * Abstract class which models the "TEntityType" element type (defined in the XML schema of TOSCA).
 * <br>
 * Copyright 2013 Jacopo Soldani (Computer Science department, University of Pisa)
 *
 * @author http://www.di.unipi.it/~soldani
 *
 * @version 1.0
 */
public abstract class EntityType extends ExtensibleElements {

	/**
	 * It stores a reference to the parent type definition.
	 */
	protected EntityType derivedFrom;

	/**
	 * It stores the name of the type under definition.
	 */
	protected String name;

	/**
	 * It stores the definition of the properties exposed by the type under definition.
	 */
	protected EntityType.PropertiesDefinition propertiesDefinition;

	/**
	 * Constructor.
	 * @param derivedFrom EntityType from which the current one is derived.
	 * @param name Name of the current entity type.
	 */
	public EntityType(EntityType derivedFrom, String name) {
		this.derivedFrom = derivedFrom;
		this.name = name;
		this.propertiesDefinition = new PropertiesDefinition();
	}

	/**
	 * Constructor.
	 * @param name Name of the current entity type.
	 */
	public EntityType(String name) {
		this.derivedFrom = null;
		this.name = name;
		this.propertiesDefinition = new PropertiesDefinition();
	}

	/**
	 * Abstract method which provides access to the entity type from which the current
	 * one is derived from.
	 */
	public abstract EntityType derivedFrom();

	/**
	 * Method which provides access to the (qualified) name of the considered entity type.
	 * @return Name of the considered entity type.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Method which provides access to the defined properties of the considered entity type.
	 * @return Defined properties of the considered entity type.
	 */
	public PropertiesDefinition getPropertiesDefinition() {
		return propertiesDefinition;
	}

	/**
	 * Class which models the set of properties definition for the "TEntityType" under consideration.
	 */
	@SuppressWarnings({"rawtypes" })
	public class PropertiesDefinition {
		/**
		 * It stores the set of property definitions.
		 */
		private List<PropertyDefinition> properties;

		/**
		 * Constructor.
		 */
		public PropertiesDefinition(){
			properties = new ArrayList<PropertyDefinition>();
		}

		/**
		 * Method which allows to add a new property definition.
		 *
		 * @param name Name of the property to be defined.
		 * @param type Type of the property to be defined.
		 *
		 * @throws AlreadyDefinedException
		 */
		public void addDefinition(String name, Class type) throws AlreadyDefinedException{
			PropertyDefinition pDef = new PropertyDefinition(name, type);
			if(properties.contains(pDef))
				throw new AlreadyDefinedException("The property <" + name + ", " + type.toString() + " is already defined");
			properties.add(pDef);
		}

		/**
		 * Method which allows to retrieve (by name) a property definition.
		 *
		 * @param name Name of the property to be retrieved.
		 *
		 * @return A PropertyDefinition if the desired property is present, null otherwise.
		 */
		public PropertyDefinition getDefinition(String name) {
			for(PropertyDefinition pDef : properties) {
				if(pDef.getName().equals(name))
					return pDef;
			}
			return null;
		}

		/**
		 * Method which provides access to the set of property definitions.
		 * @return The List of PropertyDefinition(s).
		 */
		public List<PropertyDefinition> getList() {
			return properties;
		}
	}

}
