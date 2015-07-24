package di.unipi.model.tosca;

import java.util.List;

/**
 * Class which models the "TOperation" element type (defined in the XML schema of TOSCA).
 * <br>
 * Copyright 2013 Jacopo Soldani (Computer Science department, University of Pisa)
 *
 * @author http://www.di.unipi.it/~soldani
 *
 * @version 1.0
 */
public class Operation {
    /**
     * It stores the set of input parameters of the operation under definition.
     */
    private List<Parameter> inputParameters;

    /**
     * It stores the set of output parameters of the operation under definition.
     */
    private List<Parameter> outputParameters;

    /**
     * It stores the name of the operation under definition.
     */
    private String name;

    /**
     * Constructor.
     *
     * @param name Name of the operation under definition.
     * @param inputParameters Input parameters of the operation under definition.
     * @param outputParameters Output parameters of the operation under definition.
     */
    public Operation(String name, List<Parameter> inputParameters, List<Parameter> outputParameters) {
        this.name = name;
        this.inputParameters = inputParameters;
        this.outputParameters = outputParameters;
    }

    /**
     * Method which provides access to the name of the operation.
     * @return The name of the operation.
     */
    public String getName() {
    	return name;
    }

    /**
     * Method which provides access to the input parameters of the operation.
     * @return The input parameters of the operation.
     */
    public List<Parameter> getInputParameters() {
    	return inputParameters;
    }

    /**
     * Method which provides access to the output parameters of the operation.
     * @return The output parameters of the operation.
     */
    public List<Parameter> getOutputParameters() {
    	return outputParameters;
    }

//    @Override
//    public boolean equals(Object obj) {
//    	Operation op = (Operation) obj;
//    	return (name.equals(op.getName()) &&
//    			inputParameters.equals(op.getInputParameters()) &&
//    			outputParameters.equals(op.getOutputParameters()));
//    }
}
