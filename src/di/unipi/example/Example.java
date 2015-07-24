package di.unipi.example;

import java.util.ArrayList;
import java.util.List;

import di.unipi.matchmaker.ExactMatchmaker;
import di.unipi.matchmaker.PlugInMatchmaker;
import di.unipi.model.exceptions.AlreadyDefinedException;
import di.unipi.model.exceptions.AlreadyPresentException;
import di.unipi.model.tosca.*;

/**
 * Runnable test of the developed (exact and plug-in) matchmakers. More information can be found in
 * the FOCLASA Special issue paper of A. Brogi and J. Soldani.
 * <br>
 * Copyright 2013 Jacopo Soldani (Computer Science department, University of Pisa)
 *
 * @author http://www.di.unipi.it/~soldani
 *
 * @version 1.0
 */
public class Example {

	public static void main(String[] args) throws AlreadyDefinedException, AlreadyPresentException {
		//Creation of the required CapabilityType elements.
		CapabilityType sumSubCapType = new CapabilityType("SumSubCapabilityType");
		CapabilityType sumSubMulCapType = new CapabilityType(sumSubCapType, "SumSubMulCapabilityType");

		//Creation of the required Property elements.
		Property author = new Property("Author", String.class);
		Property version = new Property("Version", String.class);
		Property release = new Property("Release", String.class);
		Property developer = new Property("Developer", String.class);


		//Creation of the required Parameter elements.
		//input
		Parameter a = new Parameter("a", Integer.class);
		Parameter b = new Parameter("b", Integer.class);
		//output
		Parameter sumOut = new Parameter("sum", Integer.class);
		Parameter subOut = new Parameter("sub", Integer.class);
		Parameter mulOut = new Parameter("mul", Integer.class);

		//Creation of the required Operation elements.
		//sum
		List<Parameter> sumInputs = new ArrayList<Parameter>();
		sumInputs.add(a);
		sumInputs.add(b);
		List<Parameter> sumOutputs = new ArrayList<Parameter>();
		sumOutputs.add(sumOut);
		Operation sumOp = new Operation("Sum", sumInputs, sumOutputs);
		//sub
		List<Parameter> subInputs = new ArrayList<Parameter>();
		subInputs.add(a);
		subInputs.add(b);
		List<Parameter> subOutputs = new ArrayList<Parameter>();
		subOutputs.add(subOut);
		Operation subOp = new Operation("Sub", subInputs, subOutputs);
		//mul
		List<Parameter> mulInputs = new ArrayList<Parameter>();
		mulInputs.add(a);
		mulInputs.add(b);
		List<Parameter> mulOutputs = new ArrayList<Parameter>();
		mulOutputs.add(mulOut);
		Operation mulOp = new Operation("Sub", mulInputs, mulOutputs);

		//Creation of the required Interface elements.
		//ops
		List<Operation> opsList = new ArrayList<Operation>();
		opsList.add(sumOp);
		opsList.add(subOp);
		Interface ops = new Interface("Ops", opsList);
		//sum
		List<Operation> sumList = new ArrayList<Operation>();
		sumList.add(sumOp);
		Interface sum = new Interface("Sum", sumList);
		//sub
		List<Operation> subList = new ArrayList<Operation>();
		subList.add(subOp);
		Interface sub = new Interface("Sub", subList);
		//mul
		List<Operation> mulList = new ArrayList<Operation>();
		mulList.add(mulOp);
		Interface mul = new Interface("Mul", mulList);

		//Creation of SummerNodeType
		NodeType summerNT = new NodeType("SummerNodeType");

		summerNT.getCapabilityDefinitions().addDefinition("CalculatorCapability", sumSubCapType);

		summerNT.getPropertiesDefinition().addDefinition("Author", String.class);
		summerNT.getPropertiesDefinition().addDefinition("Version", String.class);

		summerNT.getInterfaces().add(ops);

		//Creation of the required PolicyType and Policy elements.
		PolicyType highAvPolType = new PolicyType("HighAvailabilityPolicyType");
		highAvPolType.setApplicableTo(summerNT);
		Policy highAvPol = new Policy("HighAvailabilityPolicy", highAvPolType);

		//Creation of SummerService
		ServiceTemplate summerST = new ServiceTemplate("SummerService");

		Capability sumSubCap = new Capability("CalculatorCapability", sumSubCapType);
		summerST.getBoundaryDefinitions().add(sumSubCap);

		summerST.getBoundaryDefinitions().add(highAvPol);

		summerST.getBoundaryDefinitions().add(author);
		summerST.getBoundaryDefinitions().add(version);

		summerST.getBoundaryDefinitions().add(ops);


		//Creation of CalculatorService
		ServiceTemplate calculatorST = new ServiceTemplate("CalculatorService");

		Capability sumSubMulCap = new Capability("CalculatorCapability", sumSubMulCapType);
		calculatorST.getBoundaryDefinitions().add(sumSubMulCap);

		calculatorST.getBoundaryDefinitions().add(highAvPol);

		calculatorST.getBoundaryDefinitions().add(author);
		calculatorST.getBoundaryDefinitions().add(version);
		calculatorST.getBoundaryDefinitions().add(release);

		calculatorST.getBoundaryDefinitions().add(sum);
		calculatorST.getBoundaryDefinitions().add(sub);
		calculatorST.getBoundaryDefinitions().add(mul);

		//Creation of CalculatorServiceV1.1
		ServiceTemplate calculatorSTV11 = new ServiceTemplate("CalculatorService2");

		calculatorSTV11.getBoundaryDefinitions().add(sumSubMulCap);

		calculatorSTV11.getBoundaryDefinitions().add(highAvPol);

		calculatorSTV11.getBoundaryDefinitions().add(developer);
		calculatorSTV11.getBoundaryDefinitions().add(version);
		calculatorSTV11.getBoundaryDefinitions().add(release);

		calculatorSTV11.getBoundaryDefinitions().add(sum);
		calculatorSTV11.getBoundaryDefinitions().add(sub);
		calculatorSTV11.getBoundaryDefinitions().add(mul);


		//EXACT: SummerNodeType vs. SummerService
		{ ExactMatchmaker summerNodeTypeVSSummerService = new ExactMatchmaker(summerNT, summerST);
		if(summerNodeTypeVSSummerService.match())
			System.out.println(summerST.getName() + " exactly matches " + summerNT.getName());
		else
			System.out.println(summerST.getName() + " does not exactly match " + summerNT.getName());
		}

		//EXACT: SummerNodeType vs. CalculatorService
		{ ExactMatchmaker summerNodeTypeVSCalculatorService = new ExactMatchmaker(summerNT, calculatorST);
		if(summerNodeTypeVSCalculatorService.match())
			System.out.println(calculatorST.getName() + " exactly matches " + summerNT.getName());
		else
			System.out.println(calculatorST.getName() + " does not exactly match " + summerNT.getName());
		}

		//PLUG-IN: SummerNodeType vs. CalculatorService
		{ PlugInMatchmaker summerNodeTypeVSCalculatorService = new PlugInMatchmaker(summerNT, calculatorST);
		if(summerNodeTypeVSCalculatorService.match())
			System.out.println(calculatorST.getName() + " plug-in matches " + summerNT.getName());
		else
			System.out.println(calculatorST.getName() + " does not plug-in match " + summerNT.getName());
		}

		//PLUG-IN: SummerNodeType vs. CalculatorServiceV1.1
		{ PlugInMatchmaker summerNodeTypeVSCalculatorServiceV11 = new PlugInMatchmaker(summerNT, calculatorSTV11);
		if(summerNodeTypeVSCalculatorServiceV11.match())
			System.out.println(calculatorSTV11.getName() + " plug-in matches " + summerNT.getName());
		else
			System.out.println(calculatorSTV11.getName() + " does not plug-in match " + summerNT.getName());
		}
	}
}
