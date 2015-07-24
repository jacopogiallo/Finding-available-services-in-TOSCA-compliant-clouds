package di.unipi.model.tosca;

import java.util.ArrayList;
import java.util.List;

import di.unipi.model.exceptions.AlreadyDefinedException;

/**
 * Class which models the "TNodeType" element type (defined in the XML schema of TOSCA).
 * <br>
 * Copyright 2013 Jacopo Soldani (Computer Science department, University of Pisa)
 *
 * @author http://www.di.unipi.it/~soldani
 *
 * @version 1.0
 */
public class NodeType extends EntityType {
	/**
	 * It stores the requirement definitions of the node type under consideration.
	 */
	protected RequirementDefinitions requirementDefinitions;

	/**
	 * It stores the requirement definitions of the node type under consideration.
	 */
	protected CapabilityDefinitions capabilityDefinitions;

	/**
	 * It stores the set of interfaces of the node type under consideration.
	 */
	protected Interfaces interfaces;

	/**
	 * Constructor.
	 * @param derivedFrom NodeType from which the current one is derived.
	 * @param name Name of the current node type.
	 */
	public NodeType(NodeType derivedFrom, String name) {
		super(derivedFrom, name);
		this.requirementDefinitions = new RequirementDefinitions();
		this.capabilityDefinitions = new CapabilityDefinitions();
		this.interfaces = new Interfaces();
	}

	/**
	 * Constructor.
	 * @param name Name of the current node type.
	 */
	public NodeType(String name) {
		super(name);
		this.requirementDefinitions = new RequirementDefinitions();
		this.capabilityDefinitions = new CapabilityDefinitions();
		this.interfaces = new Interfaces();
	}

	/**
	 * Method which provides access to the requirement definitions of the current node type.
	 * @return The requirement definitions of the node type under consideration
	 */
	public RequirementDefinitions getRequirementDefinitions() {
		return requirementDefinitions;
	}

	/**
	 * Method which provides access to the capability definitions of the current node type.
	 * @return The capability definitions of the node type under consideration
	 */
	public CapabilityDefinitions getCapabilityDefinitions() {
		return capabilityDefinitions;
	}

	/**
	 * Method which provides access to the interfaces of the current node type.
	 * @return The interfacesof the node type under consideration
	 */
	public Interfaces getInterfaces() {
		return interfaces;
	}

	@Override
	public NodeType derivedFrom() {
		return (NodeType) derivedFrom;
	}

	/**
	 * Class which models the set of requirements defined in a "TNodeType" TOSCA element.
	 *
	 * Copyright 2013 Jacopo Soldani (Computer Science department, University of Pisa)
	 *
	 * @author http://www.di.unipi.it/~soldani
	 *
	 * @version 1.0
	 */
	public class RequirementDefinitions {
		/**
		 * It stores the set of requirement definitions.
		 */
		private List<RequirementDefinition> reqDefs;

		/**
		 * Constructor.
		 */
		public RequirementDefinitions() {
			reqDefs = new ArrayList<RequirementDefinition>();
		}

		/**
		 * Method which allows to add a new requirement definition.
		 *
		 * @param name Name of the requirement to be defined.
		 * @param type Type of the requirement to be defined.
		 *
		 * @throws AlreadyDefinedException
		 */
		public void addDefinition(String name, RequirementType type) throws AlreadyDefinedException{
			RequirementDefinition rDef = new RequirementDefinition(name, type);
			if(reqDefs.contains(rDef))
				throw new AlreadyDefinedException("The requirement <" + name + ", " + type.toString() + " is already defined");
			reqDefs.add(rDef);
		}

		/**
		 * Method which allows to retrieve (by name) a requirement definition.
		 *
		 * @param name Name of the requirement to be retrieved.
		 *
		 * @return A RequirementDefinition if the desired requirement is present, null otherwise.
		 */
		public RequirementDefinition getDefinition(String name) {
			for(RequirementDefinition rDef : reqDefs) {
				if(rDef.getName().equals(name))
					return rDef;
			}
			return null;
		}

		/**
		 * Method which provides access to the set of requirement definitions.
		 * @return The List of RequirementDefinition.
		 */
		public List<RequirementDefinition> getList() {
			return reqDefs;
		}
	}

	/**
	 * Class which models the set of capabilities defined in a "TNodeType" TOSCA element.
	 *
	 * Copyright 2013 Jacopo Soldani (Computer Science department, University of Pisa)
	 *
	 * @author http://www.di.unipi.it/~soldani
	 *
	 * @version 1.0
	 */
	public class CapabilityDefinitions {
		/**
		 * It stores the set of capability definitions.
		 */
		private List<CapabilityDefinition> capDefs;

		/**
		 * Constructor.
		 */
		public CapabilityDefinitions() {
			capDefs = new ArrayList<CapabilityDefinition>();
		}

		/**
		 * Method which allows to add a new capability definition.
		 *
		 * @param name Name of the requirement to be defined.
		 * @param type Type of the requirement to be defined.
		 *
		 * @throws AlreadyDefinedException
		 */
		public void addDefinition(String name, CapabilityType type) throws AlreadyDefinedException{
			CapabilityDefinition cDef = new CapabilityDefinition(name, type);
			if(capDefs.contains(cDef))
				throw new AlreadyDefinedException("The capability <" + name + ", " + type.toString() + " is already defined");
			capDefs.add(cDef);
		}

		/**
		 * Method which allows to retrieve (by name) a requirement definition.
		 *
		 * @param name Name of the requirement to be retrieved.
		 *
		 * @return A CapabilityDefinition if the desired requirement is present, null otherwise.
		 */
		public CapabilityDefinition getDefinition(String name) {
			for(CapabilityDefinition cDef : capDefs) {
				if(cDef.getName().equals(name))
					return cDef;
			}
			return null;
		}

		/**
		 * Method which provides access to the set of capability definitions.
		 * @return The List of CapabilityDefinition.
		 */
		public List<CapabilityDefinition> getList() {
			return capDefs;
		}
	}

	/**
	 * Class which models the set of interfaces defined in a "TNodeType" TOSCA element.
	 *
	 * Copyright 2013 Jacopo Soldani (Computer Science department, University of Pisa)
	 *
	 * @author http://www.di.unipi.it/~soldani
	 *
	 * @version 1.0
	 */
	public class Interfaces {
		/**
		 * It stores the set of interfaces.
		 */
		private List<Interface> interfaces;

		/**
		 * Constructor.
		 */
		public Interfaces() {
			interfaces = new ArrayList<Interface>();
		}

		/**
		 * Method which allows to add a new interface.
		 *
		 * @param intf Interface to be added.
		 *
		 * @throws AlreadyDefinedException
		 */
		public void add(Interface intf) throws AlreadyDefinedException{
			if(interfaces.contains(intf))
				throw new AlreadyDefinedException("The interface <" + intf.getName() + " is already defined");
			interfaces.add(intf);
		}

		/**
		 * Method which allows access to the set of interfaces.
		 *
		 * @return The List of interfaces.
		 */
		public List<Interface> getList() {
			return interfaces;
		}
	}
}
