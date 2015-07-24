package di.unipi.matchmaker;

import java.util.ArrayList;
import java.util.List;

import di.unipi.model.tosca.Capability;
import di.unipi.model.tosca.CapabilityDefinition;
import di.unipi.model.tosca.CapabilityType;
import di.unipi.model.tosca.Interface;
import di.unipi.model.tosca.NodeType;
import di.unipi.model.tosca.Operation;
import di.unipi.model.tosca.Property;
import di.unipi.model.tosca.PropertyDefinition;
import di.unipi.model.tosca.Requirement;
import di.unipi.model.tosca.RequirementDefinition;
import di.unipi.model.tosca.RequirementType;
import di.unipi.model.tosca.ServiceTemplate;

/**
 * Class which implements the plug-in matchmaker.
 * <br>
 * Copyright 2013 Jacopo Soldani (Computer Science department, University of Pisa)
 *
 * @author http://www.di.unipi.it/~soldani
 *
 * @version 1.0
 */
public class PlugInMatchmaker extends ExactMatchmaker {
	/**
     * It stores the set of plug-in matched capabilities (of st).
     */
    private List<Capability> pluginMatchedCapabilities;
    /**
     * It stores the set of plug-in matched requirements (of st).
     */
    private List<Requirement> pluginMatchedRequirements;
    /**
     * It stores the set of plug-in matched properties (of st).
     */
    private List<Property> pluginMatchedProperties;
    /**
     * It stores the set of plug-in matched interfaces (of st).
     */
    private List<Interface> pluginMatchedInterfaces;
    /**
     * It stores the set of unmatched operations (of n).
     */
    protected List<Operation> unmatchedOperations;

    /**
     * Constructor.
     * @param n NodeType to be plug-in matched.
     * @param st ServiceTemplate to be plug-in matched.
     */
	public PlugInMatchmaker(NodeType n, ServiceTemplate st) {
		super(n, st);
		//Initially, nothing is matched.
        pluginMatchedCapabilities = null;
        pluginMatchedRequirements = null;
        pluginMatchedProperties = null;
        pluginMatchedInterfaces = null;
        unmatchedOperations = null;
	}

	@Override
    public boolean match() {
		//First, we check whether TOSCA definitions exactly match.
        super.matchCapabilities();
        super.matchRequirements();
        super.matchPolicies();
        super.matchProperties();
        super.matchInterfaces();
		if(areCapabilitiesMatched && areRequirementsMatched && arePoliciesMatched
				&& arePropertiesMatched && areInterfacesMatched) {
			return true;
		}

        //Then, according to the paper, the matchmaking is performed in a step-wise way.
		//
		//Please note that the plug-in matching of (each kind of) TOSCA elements
		//is performed only if the exact one is failed.
		if(!areCapabilitiesMatched)
			matchCapabilities();

		if(!areRequirementsMatched)
			matchRequirements();

		if(!arePropertiesMatched)
			matchProperties();

		if(!areInterfacesMatched)
			matchInterfaces();

        //We directly implement the verbose matching
        return (areCapabilitiesMatched && areRequirementsMatched &&
        		arePoliciesMatched && arePropertiesMatched && areInterfacesMatched);
    }

	@Override
    protected void matchCapabilities() {
    	//Initially, the list of matched capabilities is empty.
    	pluginMatchedCapabilities = new ArrayList<Capability>();

    	//The environment is suitably set so as to ease the
    	//(writing of) matchmaking.
        	//areCapabilitiesMatched is already set to false;
        List<Capability> stCaps = st.getBoundaryDefinitions().getCapabilities().getList();
        List<CapabilityDefinition> newUnmatchedCapabilities = new ArrayList<CapabilityDefinition>();

        //Are all n's unmatched capabilities compatible with those on st's boundaries?
        //
        //Please note that the matchmaking procedure works also if the exact matching fails
        //because of the missing 1-to-1 correspondence.
        boolean matched;
        for(CapabilityDefinition cDef : unmatchedCapabilities) {
        	matched = false;
        	for(Capability c : stCaps) {
        		matched = match(cDef, c);
        		if(matched) {
        			pluginMatchedCapabilities.add(c);
        			break;
        		}
        	}
        	if(!matched)
        		newUnmatchedCapabilities.add(cDef);
        }
        unmatchedCapabilities = newUnmatchedCapabilities;
        if(unmatchedCapabilities.isEmpty())
        	areCapabilitiesMatched = true;
    }

    /**
     * Method which checks whether a CapabilityDefinition plug-in matches
     * a Capability.
     *
     * @param cDef CapabilityDefinition to be matched.
     * @param c Capability to be matched.
     * @return A boolean which represent the match result.
     */
    protected boolean match(CapabilityDefinition cDef, Capability c) {
    	//Are cDef and c same named?
    	if(!cDef.getName().equals(c.getName()))
    		return false;
    	//Is c's type derived from cDef's type?
    	CapabilityType cType = c.getType();
    	while(cType != null) {
    		if(cDef.getCapabilityType().getName().equals(cType.getName()))
    			return true;
    		cType = cType.derivedFrom();
    	}
    	return false;
    }

    @Override
    protected void matchRequirements() {
    	//Initially, the list of matched requirements is empty.
    	pluginMatchedRequirements = new ArrayList<Requirement>();

    	//The environment is suitably set so as to ease the
    	//(writing of) matchmaking.
    		//areRequirementsMatched is already set to false;
        List<Requirement> newUnmatchedRequirements = new ArrayList<Requirement>();
        List<RequirementDefinition> nReqDefs = n.getRequirementDefinitions().getList();

        //Are all st's requirements compatible with those on n's boundaries?
        //
        //Please note that the matchmaking procedure works also if the exact matching fails
        //because of the missing 1-to-1 correspondence.
        boolean matched;
        for(Requirement r : unmatchedRequirements) {
        	matched = false;
        	for(RequirementDefinition rDef : nReqDefs) {
        		matched = match(r, rDef);
        		if(matched) {
        			pluginMatchedRequirements.add(r);
        			break;
        		}
        	}
        	if(!matched) {
        		newUnmatchedRequirements.add(r);
        	}
        }
        unmatchedRequirements = newUnmatchedRequirements;
        if(unmatchedRequirements.isEmpty()) {
        	areRequirementsMatched = true;
        }
    }

    /**
     * Method which checks whether a Requirement plug-in matches
     * a RequirementDefinition.
     *
     * @param r Requirement to be matched.
     * @param rDef RequirementDefinition to be matched.
     * @return A boolean which represent the match result.
     */
    protected boolean match(Requirement r, RequirementDefinition rDef) {
    	//Are r and rDef same named?
    	if(!r.getName().equals(rDef.getName()))
    		return false;
    	//Is rDef's type derived from r's type?
    	RequirementType rType = rDef.getRequirementType();
    	while(rType != null) {
    		if(rType.getName().equals(r.getType().getName()))
    			return true;
    		rType = rType.derivedFrom();
    	}
    	return false;
    }

    @Override
    protected void matchProperties() {
    	//Initially, the list of matched properties is empty.
    	pluginMatchedProperties = new ArrayList<Property>();

    	//The environment is suitably set so as to ease the
    	//(writing of) matchmaking.
    		//arePropertiesMatched is already set to false;
        List<PropertyDefinition> newUnmatchedProperties = new ArrayList<PropertyDefinition>();
        List<Property> stProps = st.getBoundaryDefinitions().getProperties().getList();

        //Are all n's properties compatible with those on st's boundaries?
        boolean matched;
        for(PropertyDefinition pDef : unmatchedProperties) {
        	matched = false;
        	for(Property p : stProps) {
        		matched = match(pDef, p);
        		if(matched) {
        			pluginMatchedProperties.add(p);
        			break;
        		}
        	}
        	if(!matched) {
        		newUnmatchedProperties.add(pDef);
        	}
        }
        unmatchedProperties = newUnmatchedProperties;
        if(unmatchedProperties.isEmpty()) {
        	arePropertiesMatched = true;
        }
    }

    /**
     * Method which checks whether a PropertyDefinition plug-in matches
     * a Property.
     *
     * @param pDef PropertyDefinition to be matched.
     * @param p Property to be matched.
     * @return A boolean which represent the match result.
     */
    @SuppressWarnings("unchecked")
	protected boolean match(PropertyDefinition pDef, Property p) {
    	return 	//Are cDef and c same named?
    			pDef.getName().equals(p.getName()) &&
    			//Is p's type derived from pDef's type?
    			p.getType().isAssignableFrom(pDef.getType());

    }

    @Override
    protected void matchInterfaces() {
    	//Initially, the list of matched interfaces is empty.
    	//
    	//Please note that now it will contain "new" interfaces composed
    	//by st's operations which match n's ones.
    	pluginMatchedInterfaces = new ArrayList<Interface>();

    	//The environment is suitably set so as to ease the
    	//(writing of) matchmaking.
        	//areInterfacesMatched is already set to false;
    	List<Interface> stInfs = st.getBoundaryDefinitions().getInterfaces().getList();
        List<Interface> newUnmatchedInterfaces = new ArrayList<Interface>();
        unmatchedOperations = new ArrayList<Operation>();

        //Are all n's interface operations present on st's boundaries?
        boolean matched;
        boolean matchedAllInterfaceOperations;
        List<Operation> matchedOperations;
        for(Interface infN : unmatchedInterfaces) {
        	matchedOperations = new ArrayList<Operation>();
        	matchedAllInterfaceOperations = true;
        	for(Operation opN : infN.getOperations()) { //for each operation of each interface of n
        		matched = false;
            	for(Interface infST : stInfs) {
            		for(Operation opST : infST.getOperations()) { //there must exist a matching operation of an interface of st
            			matched = match(opN,opST);
            			if(matched) {
            				matchedOperations.add(opST);
            				break;
            			}
            		}
            		if(matched)
            			break;
            	}
            	if(matched)
        			pluginMatchedInterfaces.add(new Interface(infN.getName(), matchedOperations));
        		else {
        			unmatchedOperations.add(opN);
        			matchedAllInterfaceOperations = false;
        		}
            	if(!matchedAllInterfaceOperations)
            		newUnmatchedInterfaces.add(infN);
        	}
        	unmatchedInterfaces = newUnmatchedInterfaces;
        	if(unmatchedInterfaces.isEmpty())
        		areInterfacesMatched = true;
        }
    }

    /**
     * Provides access to the list of plug-in matched capabilities.
     *
     * @return The List of matched Capability elements.
     */
    public List<Capability> getPlugInMatchedCapabilities() {
    	return pluginMatchedCapabilities;
    }

    /**
     * Provides access to the list of plug-in matched requirement.
     *
     * @return The List of matched Requirement elements.
     */
    public List<Requirement> getPlugInMatchedRequirements() {
    	return pluginMatchedRequirements;
    }

    /**
     * Provides access to the list of plug-in matched properties.
     *
     * @return The List of matched Property elements.
     */
    public List<Property> getPlugInMatchedProperties() {
    	return pluginMatchedProperties;
    }
    /**
     * Provides access to the list of plug-in matched interfaces.
     *
     * @return The List of matched Interface elements.
     */
    public List<Interface> getPlugInMatchedInterfaces() {
    	return pluginMatchedInterfaces;
    }
}
