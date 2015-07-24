package di.unipi.matchmaker;

import java.util.ArrayList;
import java.util.List;

import di.unipi.model.tosca.*;

/**
 * Class which implements the exact matchmaker.
 * <br>
 * Copyright 2013 Jacopo Soldani (Computer Science department, University of Pisa)
 *
 * @author http://www.di.unipi.it/~soldani
 *
 * @version 1.0
 */
public class ExactMatchmaker extends Matchmaker {
    /**
     * It stores the set of exactly matched capabilities (of st).
     */
    private List<Capability> exactlyMatchedCapabilities;
    /**
     * It stores the set of exactly matched requirements (of st).
     */
    private List<Requirement> exactlyMatchedRequirements;
    /**
     * It stores the set of exactly matched properties (of st).
     */
    private List<Property> exactlyMatchedProperties;
    /**
     * It stores the set of exactly matched policies (of st).
     */
    private List<Policy> exactlyMatchedPolicies;
    /**
     * It stores the set of exactly matched interfaces (of st).
     */
    private List<Interface> exactlyMatchedInterfaces;

    /**
     * Constructor.
     * @param n NodeType to be exactly matched.
     * @param st ServiceTemplate to be exactly matched.
     */
    public ExactMatchmaker(NodeType n, ServiceTemplate st) {
        super(n,st);
        //Initially, nothing is matched.
        exactlyMatchedCapabilities = null;
        exactlyMatchedRequirements = null;
        exactlyMatchedProperties = null;
        exactlyMatchedPolicies = null;
        exactlyMatchedInterfaces = null;
    }

    @Override
    public boolean match() {
        //According to the paper, the matchmaking is performed in a step-wise way.
        matchCapabilities();

        matchRequirements();

        matchPolicies();

        matchProperties();

        matchInterfaces();


        //We directly implement the verbose matching
        return (areCapabilitiesMatched && areRequirementsMatched &&
        		arePoliciesMatched && arePropertiesMatched && areInterfacesMatched);
    }

    @Override
    protected void matchCapabilities() {
    	//Initially, the list of matched capabilities is empty.
    	exactlyMatchedCapabilities = new ArrayList<Capability>();

    	//The environment is suitably set so as to ease the
    	//(writing of) matchmaking.
        areCapabilitiesMatched = false;
        unmatchedCapabilities = n.getCapabilityDefinitions().getList();
        List<Capability> stCaps = st.getBoundaryDefinitions().getCapabilities().getList();
        List<CapabilityDefinition> newUnmatchedCapabilities = new ArrayList<CapabilityDefinition>();

        //Are all n's capabilities present on st's boundaries?
        boolean matched;
        for(CapabilityDefinition cDef : unmatchedCapabilities) {
        	matched = false;
        	for(Capability c : stCaps) {
        		matched = match(cDef, c);
        		if(matched) {
        			exactlyMatchedCapabilities.add(c);
        			break;
        		}
        	}
        	if(!matched)
        		newUnmatchedCapabilities.add(cDef);
        }
        unmatchedCapabilities = newUnmatchedCapabilities;

        //Do both n and st expose the same number of capabilities?
        //(Needed for 1-to-1 correspondence)
        if(n.getCapabilityDefinitions().getList().size() != stCaps.size())
            return;

        if(unmatchedCapabilities.isEmpty())
        	areCapabilitiesMatched = true;
    }

    /**
     * Method which checks whether a CapabilityDefinition exactly matches
     * a Capability.
     *
     * @param cDef CapabilityDefinition to be matched.
     * @param c Capability to be matched.
     * @return A boolean which represent the match result.
     */
    protected boolean match(CapabilityDefinition cDef, Capability c) {
        return (cDef.getName().equals(c.getName()) &&
                cDef.getCapabilityType().getName().equals(c.getType().getName()));
    }

    @Override
    protected void matchRequirements() {
    	//Initially, the list of matched requirements is empty.
    	exactlyMatchedRequirements = new ArrayList<Requirement>();

    	//The environment is suitably set so as to ease the
    	//(writing of) matchmaking.
    	areRequirementsMatched = false;
        unmatchedRequirements = st.getBoundaryDefinitions().getRequirements().getList();
        List<Requirement> newUnmatchedRequirements = new ArrayList<Requirement>();
        List<RequirementDefinition> nReqDefs = n.getRequirementDefinitions().getList();

        //Are all st's requirements present on n's boundaries?
        boolean matched;
        for(Requirement r : unmatchedRequirements) {
        	matched = false;
        	for(RequirementDefinition rDef : nReqDefs) {
        		matched = match(r, rDef);
        		if(matched) {
        			exactlyMatchedRequirements.add(r);
        			break;
        		}
        	}
        	if(!matched) {
        		newUnmatchedRequirements.add(r);
        	}
        }
        unmatchedRequirements = newUnmatchedRequirements;

        //Do both n and st expose the same number of requirements?
        //(Needed for 1-to-1 correspondence)
        if(st.getBoundaryDefinitions().getRequirements().getList().size() != nReqDefs.size())
        	return;

        if(unmatchedRequirements.isEmpty()) {
        	areRequirementsMatched = true;
        }
    }

    /**
     * Method which checks whether a RequirementDefinition exactly matches
     * a Requirement.
     *
     * @param rDef RequirementDefinition to be matched.
     * @param r Requirement to be matched.
     * @return A boolean which represent the match result.
     */
    protected boolean match(Requirement r, RequirementDefinition rDef) {
        return (r.getName().equals(rDef.getName()) &&
                r.getType().getName().equals(rDef.getRequirementType().getName()));
    }

    @Override
    protected void matchPolicies() {
    	//Initially, the list of matched policies is empty
    	exactlyMatchedPolicies = new ArrayList<Policy>();

    	//The environment is suitably set so as to ease the
    	//(writing of) matchmaking.
    	arePoliciesMatched = false;
        unmatchedPolicies = st.getBoundaryDefinitions().getPolicies().getList();
        List<Policy> newUnmatchedPolicies = new ArrayList<Policy>();

        //Are all st's policies applicable to n?
        for(Policy p : unmatchedPolicies) {
        	if(p.getType().isApplicableTo(n))
        		exactlyMatchedPolicies.add(p);
        	else
        		newUnmatchedPolicies.add(p);
        }
        unmatchedPolicies = newUnmatchedPolicies;
        if(unmatchedPolicies.isEmpty())
        	arePoliciesMatched = true;
    }

    @Override
    protected void matchProperties() {
    	//Initially, the list of matched properties is empty.
    	exactlyMatchedProperties = new ArrayList<Property>();

    	//The environment is suitably set so as to ease the
    	//(writing of) matchmaking.
    	arePropertiesMatched = false;
        unmatchedProperties = n.getPropertiesDefinition().getList();
        List<PropertyDefinition> newUnmatchedProperties = new ArrayList<PropertyDefinition>();
        List<Property> stProps = st.getBoundaryDefinitions().getProperties().getList();

        //Are all n's properties present on st's boundaries?
        boolean matched;
        for(PropertyDefinition pDef : unmatchedProperties) {
        	matched = false;
        	for(Property p : stProps) {
        		matched = match(pDef, p);
        		if(matched) {
        			exactlyMatchedProperties.add(p);
        			break;
        		}
        	}
        	if(!matched) {
        		newUnmatchedProperties.add(pDef);
        	}
        }
        unmatchedProperties = newUnmatchedProperties;

        //Do both n and st expose the same number of properties?
        //(Needed for 1-to-1 correspondence)
        if(n.getPropertiesDefinition().getList().size() != stProps.size())
        	return;

        if(unmatchedProperties.isEmpty()) {
        	arePropertiesMatched = true;
        }
    }

    /**
     * Method which checks whether a PropertyDefinition exactly matches
     * a Property.
     *
     * @param pDef PropertyDefinition to be matched.
     * @param p Property to be matched.
     * @return A boolean which represent the match result.
     */
    protected boolean match(PropertyDefinition pDef, Property p) {
        return (pDef.getName().equals(p.getName()) &&
        		pDef.getType().equals(p.getType()));
    }

    @Override
    protected void matchInterfaces() {
    	//Initially, the list of matched interfaces is empty.
    	exactlyMatchedInterfaces = new ArrayList<Interface>();

    	//The environment is suitably set so as to ease the
    	//(writing of) matchmaking.
        areInterfacesMatched = false;
        unmatchedInterfaces = n.getInterfaces().getList();
        List<Interface> stInfs = st.getBoundaryDefinitions().getInterfaces().getList();
        List<Interface> newUnmatchedInterfaces = new ArrayList<Interface>();

        //Are all n's interfaces present on st's boundaries?
        boolean matched;
        for(Interface infN : unmatchedInterfaces) {
        	matched = false;
        	for(Interface infST : stInfs) {
        		matched = match(infN, infST);
        		if(matched) {
        			exactlyMatchedInterfaces.add(infST);
        			break;
        		}
        	}
        	if(!matched)
        		newUnmatchedInterfaces.add(infN);
        }
        unmatchedInterfaces = newUnmatchedInterfaces;

        //Do both n and st expose the same number of interfacees?
        //(Needed for 1-to-1 correspondence)
        if(n.getInterfaces().getList().size() != stInfs.size())
            return;

        if(unmatchedInterfaces.isEmpty())
        	areInterfacesMatched = true;
    }

    /**
     * Method which checks whether two Interfaces exactly match.
     *
     * @param inf1 Interface to be matched.
     * @param inf2 Interface to be matched.
     * @return A boolean which represent the match result.
     */
    protected boolean match(Interface inf1, Interface inf2) {
    	//If the two interfaces have different names, then
    	//they cannot be considered in exact matching.
    	if(!inf1.getName().equals(inf2.getName()))
    		return false;

    	//Do both interfaces expose the same number of operations?
    	//(Needed for 1-to-1 correspondence)
    	if(inf1.getOperations().size() != inf2.getOperations().size())
    		return false;

    	//Are all inf1's operations present in inf2?
    	boolean matched;
    	for(Operation op1 : inf1.getOperations()) {
    		matched = false;
    		for(Operation op2 : inf2.getOperations()) {
    			matched = match(op1, op2);
    			if(matched)
    				break;
    		}
    		if(!matched)
    			return false;
    	}

    	return true;
    }

    protected boolean match(Operation op1, Operation op2) {
    	//If the two operations have different names, then
    	//they cannot be considered in exact matching.
    	if(!op1.getName().equals(op2.getName()))
    		return false;

    	//Do both operations expose the same number of input/output parameters?
    	//(Needed for 1-to-1 correspondence)
    	if(op1.getInputParameters().size() != op2.getInputParameters().size() ||
    			op1.getInputParameters().size() != op2.getInputParameters().size())
    		return false;

    	//Are all op1's input parameters present in op2?
    	boolean matched;
    	for(Parameter p1 : op1.getInputParameters()) {
    		matched = false;
    		for(Parameter p2 : op2.getInputParameters()) {
    			matched = match(p1, p2);
    			if(matched)
    				break;
    		}
    		if(!matched)
    			return false;
    	}

    	//Are all op1's output parameters present in op2?
    	for(Parameter p1 : op1.getOutputParameters()) {
    		matched = false;
    		for(Parameter p2 : op2.getOutputParameters()) {
    			matched = match(p1, p2);
    			if(matched)
    				break;
    		}
    		if(!matched)
    			return false;
    	}
    	return true;
    }

    protected boolean match(Parameter p1, Parameter p2) {
    	return (p1.getName().equals(p2.getName()) &&
    			p1.getType().equals(p2.getType()));
    }

    /**
     * Provides access to the list of exactly matched capabilities.
     *
     * @return The List of matched Capability elements.
     */
    public List<Capability> getExactlyMatchedCapabilities() {
    	return exactlyMatchedCapabilities;
    }

    /**
     * Provides access to the list of exactly matched requirement.
     *
     * @return The List of matched Requirement elements.
     */
    public List<Requirement> getExactlyMatchedRequirements() {
    	return exactlyMatchedRequirements;
    }

    /**
     * Provides access to the list of exactly matched policies.
     *
     * @return The List of matched Policy elements.
     */
    public List<Policy> getExactlyMatchedPolicies() {
    	return exactlyMatchedPolicies;
    }
    /**
     * Provides access to the list of exactly matched properties.
     *
     * @return The List of matched Property elements.
     */
    public List<Property> getExactlyMatchedProperties() {
    	return exactlyMatchedProperties;
    }
    /**
     * Provides access to the list of exactly matched interfaces.
     *
     * @return The List of matched Interface elements.
     */
    public List<Interface> getExactlyMatchedInterfaces() {
    	return exactlyMatchedInterfaces;
    }
}
