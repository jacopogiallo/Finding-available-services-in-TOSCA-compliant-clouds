package di.unipi.matchmaker;

import java.util.List;

import di.unipi.model.tosca.*;

/**
 * Class which envelops the functionalities common to both the exact and plug-in matchmakers.
 * <br>
 * Copyright 2013 Jacopo Soldani (Computer Science department, University of Pisa)
 *
 * @author http://www.di.unipi.it/~soldani
 *
 * @version 1.0
 */
public abstract class Matchmaker {
	/**
	 * It stores the NodeType to be matched.
	 */
	protected NodeType n;
	/**
	 * It stores the ServiceTemplate to be matched.
	 */
	protected ServiceTemplate st;
	/**
	 * It stores the set of n's capabilities which still need to be matched.
	 */
	protected List<CapabilityDefinition> unmatchedCapabilities;
	/**
	 * It stores the set of st's requirements which still need to be matched.
	 */
	protected List<Requirement> unmatchedRequirements;
	/**
	 * It stores the set of st's policies which still need to be matched.
	 */
	protected List<Policy> unmatchedPolicies;
	/**
	 * It stores the set of n's properties which still need to be matched.
	 */
	protected List<PropertyDefinition> unmatchedProperties;
	/**
	 * It stores the set of n's interfaces which still need to be matched.
	 */
	protected List<Interface> unmatchedInterfaces;
	/**
	 * It stores the result of the capabilities matchmaking process.
	 */
	protected boolean areCapabilitiesMatched;
	/**
	 * It stores the result of the requirements matchmaking process.
	 */
	protected boolean areRequirementsMatched;
	/**
	 * It stores the result of the properties matchmaking process.
	 */
	protected boolean arePropertiesMatched;
	/**
	 * It stores the result of the policies matchmaking process.
	 */
	protected boolean arePoliciesMatched;
	/**
	 * It stores the result of the interfaces matchmaking process.
	 */
	protected boolean areInterfacesMatched;

	/**
	 * Constructor.
	 */
	public Matchmaker(NodeType n, ServiceTemplate st) {
		this.n = n;
		this.st = st;
		//Initially, nothing is unmatched (since the matchmaking is not yet started).
		unmatchedCapabilities = null;
		unmatchedRequirements = null;
		unmatchedProperties = null;
		unmatchedInterfaces = null;
		//Initially, the matchmaking is not obtained (since it is not yet started)
		areCapabilitiesMatched = false;
		areRequirementsMatched = false;
		arePropertiesMatched = false;
		arePoliciesMatched = false;
		areInterfacesMatched = false;

	}

	/**
	 * Method which performs the whole matchmaking process.
	 */
	public abstract boolean match();

	/**
	 * Method which performs the matchmaking process for capabilities.
	 */
	protected abstract void matchCapabilities();
	/**
	 * Method which performs the matchmaking process for requirements.
	 */
	protected abstract void matchRequirements();
	/**
	 * Method which performs the matchmaking process for policies.
	 */
	protected abstract void matchPolicies();
	/**
	 * Method which performs the matchmaking process for properties.
	 */
	protected abstract void matchProperties();
	/**
	 * Method which performs the matchmaking process for interfaces.
	 */
	protected abstract void matchInterfaces();

	/**
	 * Method which provides access to n's unmatched capabilities definition.
	 * @return The List of unmatched CapabilityDefinition(s).
	 */
	public List<CapabilityDefinition> getUnmatchedCapabilities() {
		return unmatchedCapabilities;
	}
	/**
	 * Method which provides access to st's unmatched requirements.
	 * @return The List of unmatched Requirement(s).
	 */
	public List<Requirement> getUnmatchedRequirements() {
		return unmatchedRequirements;
	}
	/**
	 * Method which provides access to st's unmatched policies.
	 * @return The List of unmatched Policy(s).
	 */
	public List<Policy> getUnmatchedPolicies() {
		return unmatchedPolicies;
	}
	/**
	 * Method which provides access to n's unmatched properties definition.
	 * @return The List of unmatched PropertyDefinition(s).
	 */
	public List<PropertyDefinition> getUnmatchedProperties() {
		return unmatchedProperties;
	}
	/**
	 * Method which provides access to n's unmatched interfaces.
	 * @return The List of unmatched Interface.
	 */
	public List<Interface> getUnmatchedInterfaces() {
		return unmatchedInterfaces;
	}
}
