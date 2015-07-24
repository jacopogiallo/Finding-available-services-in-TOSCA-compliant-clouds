package di.unipi.model.tosca;

import java.util.ArrayList;
import java.util.List;

import di.unipi.model.exceptions.AlreadyPresentException;

/**
 * Class which models the "TPolicyType" element type (defined in the XML schema of TOSCA).
 * <br>
 * Copyright 2013 Jacopo Soldani (Computer Science department, University of Pisa)
 *
 * @author http://www.di.unipi.it/~soldani
 *
 * @version 1.0
 */
public class PolicyType extends EntityType {
	/**
	 * Constructor.
	 * @param derivedFrom PolicyType from which the under definition one is derived from.
	 * @param name Name of the policy under definition.
	 */
	public PolicyType(PolicyType derivedFrom, String name) {
		super(derivedFrom, name);
		this.appliesTo = new AppliesTo();
	}

	/**
	 * Constructor.
	 * @param name Name of the policy under definition.
	 */
	public PolicyType(String name) {
		super(name);
		this.appliesTo = new AppliesTo();
	}

	/**
	 * It stores the set of NodeType to which the policy is applicable.
	 */
	protected AppliesTo appliesTo;

	@Override
	public PolicyType derivedFrom() {
		return (PolicyType) derivedFrom;
	}

	public void setApplicableTo(NodeType n) throws AlreadyPresentException {
		if(appliesTo.getList().contains(n))
			throw new AlreadyPresentException("The PolicyType [" + name + "] is already applicable to " + n.getName());
		appliesTo.getList().add(n);
	}
	/**
     * Method which checks whether a the current PolicyType is applicable
     * to a NodeType.
     * @param n NodeType
     * @return A boolean which represents the checking result.
     */
    public boolean isApplicableTo(NodeType n) {
    	if(appliesTo.getList().isEmpty())
    		return true;
    	for(NodeType appN : appliesTo.getList())
    		if(appN.getName().equals(n.getName()))
    			return true;
    	return false;
    }

    /**
     * Class which models the "TBoundaryDefinitions" element type (defined in the XML schema of TOSCA).
     */
	public class AppliesTo{
		/**
		 * It stores the set of nodes to which a policy is applicable to.
		 */
		private List<NodeType> nodeTypeReference;

		/**
		 * Constructor.
		 */
		public AppliesTo(){
			nodeTypeReference = new ArrayList<NodeType>();
		}

		/**
		 * Method which provides access to the applicability list.
		 * @return The set of nodes to which the policy is applicable to.
		 */
		public List<NodeType> getList(){
			return nodeTypeReference;
		}

		/**
		 * Method which allows the addition of a NodeType to the list of applicable node types.
		 * @param n The NodeType to be added.
		 */
		public void add(NodeType n) {
			nodeTypeReference.add(n);
		}

		/*
		 * Method which allows the addition of a NodeType to the list of applicable node types.
		 * @param n The NodeType to be added.
		 * @throws AlreadyDefinedException If the NodeType is already present in the set.
		 */
//		public void add(NodeType n) throws AlreadyDefinedException {
//			if(nodeTypeReference.contains(n))
//				throw new AlreadyDefinedException("The node type" + n.getName() + "is already in the list.");
//			nodeTypeReference.add(n);
//		}

	}

}
