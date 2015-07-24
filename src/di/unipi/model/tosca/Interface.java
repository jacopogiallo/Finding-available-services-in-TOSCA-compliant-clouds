package di.unipi.model.tosca;

import java.util.List;

/**
 * Class which models the "TInterface" element type (defined in the XML schema of TOSCA).
 * <br>
 * Copyright 2013 Jacopo Soldani (Computer Science department, University of Pisa)
 *
 * @author http://www.di.unipi.it/~soldani
 *
 * @version 1.0
 */
public class Interface {
    /**
     * It stores the set of operations of the interface under definition.
     */
    private List<Operation> operations;

    /**
     * It stores the name of the interface under definition.
     */
    private String name;

    /**
     * Constructor.
     *
     * @param name Name of the interface under definition.
     * @param operations Operations of the interface under definition.
     */
    public Interface(String name, List<Operation> operations) {
        this.name = name;
        this.operations = operations;
    }

    /**
     * Method which provides access to the name of the interface.
     * @return The name of the interface.
     */
    public String getName() {
    	return name;
    }

    /**
     * Method which provides access to the operations of the interface.
     * @return The operations of the interface.
     */
    public List<Operation> getOperations() {
    	return operations;
    }

//    @Override
//    public boolean equals(Object obj) {
//    	Interface inf = (Interface) obj;
//    	if(!name.equals(inf.getName())) return false;
//    	boolean found;
//    	for(Operation o1 : operations){
//    		found = false;
//    		for(Operation o2 : inf.getOperations())
//    			if(o1.equals(o2)) found = true;
//    		if(!found) return false;
//    	}
//    	return true;
//    }
}
