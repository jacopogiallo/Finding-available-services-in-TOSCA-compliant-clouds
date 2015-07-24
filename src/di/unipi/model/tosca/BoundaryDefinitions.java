package di.unipi.model.tosca;

import java.util.ArrayList;
import java.util.List;

import di.unipi.model.exceptions.AlreadyDefinedException;
import di.unipi.model.exceptions.AlreadyPresentException;

/**
 * Class which models the "TBoundaryDefinitions" element type (defined in the XML schema of TOSCA).
 * <br>
 * Copyright 2013 Jacopo Soldani (Computer Science department, University of Pisa)
 *
 * @author http://www.di.unipi.it/~soldani
 *
 * @version 1.0
 */
public class BoundaryDefinitions {
	/**
	 * It stores the requirements exposed by these boundary definitions.
	 */
	private Requirements requirements;
	/**
	 * It stores the capabilities exposed by these boundary definitions.
	 */
	private Capabilities capabilities;
	/**
	 * It stores the policies exposed by these boundary definitions.
	 */
	private Policies policies;
	/**
	 * It stores the properties exposed by these boundary definitions.
	 */
	private Properties properties;
	/**
	 * It stores the interfaces exposed by these boundary definitions.
	 */
	private Interfaces interfaces;

	public BoundaryDefinitions() {
		this.requirements = new Requirements();
		this.capabilities = new Capabilities();
		this.policies = new Policies();
		this.properties = new Properties();
		this.interfaces = new Interfaces();
	}
	/**
	 * Method which provides access to the set of requirements.
	 * @return The Requirements.
	 */
	public Requirements getRequirements() {
		return requirements;
	}

	/**
	 * Method which provides access to the set of capabilities.
	 * @return The Capabilities.
	 */
	public Capabilities getCapabilities() {
		return capabilities;
	}

	/**
	 * Method which provides access to the set of requirements.
	 * @return The Requirements.
	 */
	public Policies getPolicies() {
		return policies;
	}

	/**
	 * Method which provides access to the set of properties.
	 * @return The Properties.
	 */
	public Properties getProperties() {
		return properties;
	}

	/**
	 * Method which provides access to the set of interfaces.
	 * @return The Interfaces.
	 */
	public Interfaces getInterfaces() {
		return interfaces;
	}

	/**
	 * Method which adds a Requirement to the set of requirements.
	 * @param r The Requirement to be added.
	 * @throws AlreadyPresentException If the requirement is already present.
	 */
	public void add(Requirement r) throws AlreadyPresentException {
		requirements.addReference(r);
	}

	/**
	 * Method which adds a Capability to the set of capabilities.
	 * @param c The Capability to be added.
	 * @throws AlreadyPresentException If the capability is already present.
	 */
	public void add(Capability c) throws AlreadyPresentException {
		capabilities.addReference(c);
	}

	/**
	 * Method which adds a Policy to the set of policies.
	 * @param pol The Policy to be added.
	 * @throws AlreadyPresentException If the Policy is already present.
	 */
	public void add(Policy pol) throws AlreadyPresentException {
		policies.add(pol);
	}

	/**
	 * Method which adds a Property to the set of properties.
	 * @param p The Property to be added.
	 * @throws AlreadyPresentException If the property is already present.
	 */
	public void add(Property p) throws AlreadyPresentException {
		properties.addReference(p);
	}

	/**
	 * Method which adds an Interface to the set of interfaces.
	 * @param intf The Interface to be added.
	 * @throws AlreadyDefinedException If the interface is already defined.
	 */
	public void add(Interface intf) throws AlreadyDefinedException {
		interfaces.add(intf);
	}

	/**
     * Class which models the set of references to requirements in a "TBoundaryDefinitions" TOSCA element.
     *
     * Copyright 2013 Jacopo Soldani (Computer Science department, University of Pisa)
     *
     * @author http://www.di.unipi.it/~soldani
     *
     * @version 1.0
     */
    public class Requirements {
        /**
         * It stores the set of references to requirements.
         */
        private List<Requirement> reqs;

        /**
         * Constructor.
         */
        public Requirements() {
            reqs = new ArrayList<Requirement>();
        }

        /**
         * Method which allows to add a new reference to a requirement.
         *
         * @param r Requirement to be added.
         *
         * @throws AlreadyDefinedException
         */
        public void addReference(Requirement r) throws AlreadyPresentException{
            if(reqs.contains(r))
                throw new AlreadyPresentException("The reference to requirement <" + r.getName() + " is already defined");
            reqs.add(r);
        }

        /**
         * Method which allows to retrieve (by name) a requirement.
         *
         * @param name Name of the requirement to be retrieved.
         *
         * @return A Requirement reference if the desired requirement is present, null otherwise.
         */
        public Requirement getReference(String name) {
            for(Requirement r : reqs) {
                if(r.getName().equals(name))
                    return r;
            }
            return null;
        }

        /**
         * Method which provides access to the set of requirements.
         *
         * @return A List which represent the set of requirements.
         */
        public List<Requirement> getList() {
        	return reqs;
        }
    }

    /**
     * Class which models the set of references to capabilities defined in a "TBoundaryDefinitions" TOSCA element.
     *
     * Copyright 2013 Jacopo Soldani (Computer Science department, University of Pisa)
     *
     * @author http://www.di.unipi.it/~soldani
     *
     * @version 1.0
     */
    public class Capabilities {
        /**
         * It stores the set of references to capabilities.
         */
        private List<Capability> caps;

        /**
         * Constructor.
         */
        public Capabilities() {
            caps = new ArrayList<Capability>();
        }

        /**
         * Method which allows to add a new reference to a capability.
         *
         * @param c Capability reference to be added.
         *
         * @throws AlreadyDefinedException
         */
        public void addReference(Capability c) throws AlreadyPresentException{
            if(caps.contains(c))
                throw new AlreadyPresentException("The capability <" + c.getName() + " is already defined");
            caps.add(c);
        }

        /**
         * Method which allows to retrieve (by name) a capability.
         *
         * @param name Name of the capability to be retrieved.
         *
         * @return A Capability reference if the desired capability is present, null otherwise.
         */
        public Capability getReference(String name) {
            for(Capability c : caps) {
                if(c.getName().equals(name))
                    return c;
            }
            return null;
        }

        /**
         * Method which provides access to the set of capabilities.
         *
         * @return A List which represent the set of capabilities.
         */
        public List<Capability> getList() {
        	return caps;
        }
    }

    /**
     * Class which models the set of references to properties defined in a "TBoundaryDefinitions" TOSCA element.
     *
     * Copyright 2013 Jacopo Soldani (Computer Science department, University of Pisa)
     *
     * @author http://www.di.unipi.it/~soldani
     *
     * @version 1.0
     */
    public class Properties {
    	/**
         * It stores the set of references to properties.
         */
        private List<Property> props;

        /**
         * Constructor.
         */
        public Properties() {
            props = new ArrayList<Property>();
        }

        /**
         * Method which allows to add a new reference to a property.
         *
         * @param p Property reference to be added.
         *
         * @throws AlreadyPresentException
         */
        public void addReference(Property p) throws AlreadyPresentException{
            if(props.contains(p))
                throw new AlreadyPresentException("The property <" + p.getName() + " is already defined");
            props.add(p);
        }

        /**
         * Method which allows to retrieve (by name) a property.
         *
         * @param name Name of the property to be retrieved.
         *
         * @return A Property reference if the desired capability is present, null otherwise.
         */
        public Property getReference(String name) {
            for(Property p : props) {
                if(p.getName().equals(name))
                    return p;
            }
            return null;
        }

        /**
         * Method which provides access to the set of properties.
         *
         * @return A List which represent the set of properties.
         */
        public List<Property> getList() {
        	return props;
        }
    }

    /**
     * Class which models the set of references to properties defined in a "TBoundaryDefinitions" TOSCA element.
     *
     * Copyright 2013 Jacopo Soldani (Computer Science department, University of Pisa)
     *
     * @author http://www.di.unipi.it/~soldani
     *
     * @version 1.0
     */
    public class Policies {
    	/**
         * It stores the set of policies.
         */
        private List<Policy> pols;

        /**
         * Constructor.
         */
        public Policies() {
            pols = new ArrayList<Policy>();
        }

        /**
         * Method which allows to add a new policy.
         *
         * @param pol Policy to be added.
         *
         * @throws AlreadyPresentException
         */
        public void add(Policy pol) throws AlreadyPresentException{
            if(pols.contains(pol))
                throw new AlreadyPresentException("The capability <" + pol.getName() + " is already defined");
            pols.add(pol);
        }

        /**
         * Method which provides access to the set of policies.
         * @return The List of Policy.
         */
        public List<Policy> getList() {
            return pols;
        }
    }

    /**
     * Class which models the set of references to interfaces defined in a "TBoundaryDefinitions" TOSCA element.
     *
     * Copyright 2013 Jacopo Soldani (Computer Science department, University of Pisa)
     *
     * @author http://www.di.unipi.it/~soldani
     *
     * @version 1.0
     */
    public class Interfaces {
        /**
         * It stores the set of references to interfaces.
         */
        private List<Interface> interfaces;

        /**
         * Constructor.
         */
        public Interfaces() {
            interfaces = new ArrayList<Interface>();
        }

        /**
         * Method which allows to add a new reference to an interface.
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
         * Method which allows access to the set of references to interfaces.
         *
         * @return The List of interfaces.
         */
        public List<Interface> getInterfaces() {
            return interfaces;
        }

        /**
         * Method which provides access to the set of interfaces.
         *
         * @return A List which represent the set of interfaces.
         */
        public List<Interface> getList() {
        	return interfaces;
        }
    }
}
