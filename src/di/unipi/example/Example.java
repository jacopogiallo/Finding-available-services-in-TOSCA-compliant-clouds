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
		//Creation of the required RequirementType element.
		RequirementType osContainer = new RequirementType("OSContanierRequirementType");
		
		//Creation of the required CapabilityType elements.
		CapabilityType wsRuntimeCapType = new CapabilityType("WebAppRuntimeCapabilityType");
		CapabilityType webAppRuntimeCapType = new CapabilityType(wsRuntimeCapType, "WSRuntimeCapabilityType");
		
		//Creation of the required Property elements.
		Property hostName = new Property("HostName", String.class);
		Property host= new Property("Host", String.class);
		Property version = new Property("Version", String.class);
		Property release = new Property("Release", String.class);
		

		//Creation of the required Operation elements.
		//start
		Operation start = new Operation("Start", new ArrayList<Parameter>(), new ArrayList<Parameter>());
		//stop
		Operation stop = new Operation("Stop", new ArrayList<Parameter>(), new ArrayList<Parameter>());
		//configure
		Operation configure = new Operation("Configure", new ArrayList<Parameter>(), new ArrayList<Parameter>());

		//Creation of the required Interface elements.
		//lifecycle
		List<Operation> lifecycleIfOps = new ArrayList<Operation>();
		lifecycleIfOps.add(start);
		lifecycleIfOps.add(stop);
		Interface lifecycleIf = new Interface("Lifecycle", lifecycleIfOps);
		//start
		List<Operation> startIfOps = new ArrayList<Operation>();
		startIfOps.add(start);
		Interface startIf = new Interface("Start", startIfOps);
		//stop
		List<Operation> stopIfOps = new ArrayList<Operation>();
		stopIfOps.add(stop);
		Interface stopIf = new Interface("Stop", stopIfOps);
		//configure
		List<Operation> configureIfOps = new ArrayList<Operation>();
		configureIfOps.add(configure);
		Interface configureIf = new Interface("Configure", configureIfOps);

		//Creation of ServerNodeType
		NodeType serverNodeType = new NodeType("Server");

		serverNodeType.getRequirementDefinitions().addDefinition("OSContainer", osContainer);
		
		serverNodeType.getCapabilityDefinitions().addDefinition("WSRuntime", wsRuntimeCapType);

		serverNodeType.getPropertiesDefinition().addDefinition("HostName", String.class);
		serverNodeType.getPropertiesDefinition().addDefinition("Version", String.class);

		serverNodeType.getInterfaces().add(lifecycleIf);

		//Creation of the required PolicyType and Policy elements.
		PolicyType highAvPolType = new PolicyType("HighAvailabilityPolicyType");
		highAvPolType.setApplicableTo(serverNodeType);
		Policy highAvPol = new Policy("HighAvailabilityPolicy", highAvPolType);

		//Creation of SummerService
		ServiceTemplate apacheServer = new ServiceTemplate("ApacheServer");

		Requirement osContainerReq = new Requirement("OSContainer", osContainer);
		apacheServer.getBoundaryDefinitions().add(osContainerReq);
		
		Capability wsRuntimeCap = new Capability("WSRuntime", wsRuntimeCapType);
		apacheServer.getBoundaryDefinitions().add(wsRuntimeCap);

		apacheServer.getBoundaryDefinitions().add(highAvPol);

		apacheServer.getBoundaryDefinitions().add(hostName);
		apacheServer.getBoundaryDefinitions().add(version);

		apacheServer.getBoundaryDefinitions().add(lifecycleIf);


		//Creation of CalculatorService
		ServiceTemplate paasServer = new ServiceTemplate("PaaS_Server");

		paasServer.getBoundaryDefinitions().add(osContainerReq);
		
		Capability webAppRuntimeCap = new Capability("WSRuntime", webAppRuntimeCapType);
		paasServer.getBoundaryDefinitions().add(webAppRuntimeCap);

		paasServer.getBoundaryDefinitions().add(highAvPol);

		paasServer.getBoundaryDefinitions().add(hostName);
		paasServer.getBoundaryDefinitions().add(version);
		paasServer.getBoundaryDefinitions().add(release);

		paasServer.getBoundaryDefinitions().add(startIf);
		paasServer.getBoundaryDefinitions().add(stopIf);
		paasServer.getBoundaryDefinitions().add(configureIf);

		//Creation of CalculatorServiceV1.1
		ServiceTemplate paasServer2 = new ServiceTemplate("PaaS_Server_2");

		paasServer2.getBoundaryDefinitions().add(osContainerReq);
		
		paasServer2.getBoundaryDefinitions().add(webAppRuntimeCap);

		paasServer2.getBoundaryDefinitions().add(highAvPol);

		paasServer2.getBoundaryDefinitions().add(host);
		paasServer2.getBoundaryDefinitions().add(version);
		paasServer2.getBoundaryDefinitions().add(release);

		paasServer2.getBoundaryDefinitions().add(startIf);
		paasServer2.getBoundaryDefinitions().add(stopIf);
		paasServer2.getBoundaryDefinitions().add(configureIf);


		//EXACT: SummerNodeType vs. SummerService
		{ ExactMatchmaker summerNodeTypeVSSummerService = new ExactMatchmaker(serverNodeType, apacheServer);
		if(summerNodeTypeVSSummerService.match())
			System.out.println(apacheServer.getName() + " exactly matches " + serverNodeType.getName());
		else
			System.out.println(apacheServer.getName() + " does not exactly match " + serverNodeType.getName());
		}

		//EXACT: SummerNodeType vs. CalculatorService
		{ ExactMatchmaker summerNodeTypeVSCalculatorService = new ExactMatchmaker(serverNodeType, paasServer);
		if(summerNodeTypeVSCalculatorService.match())
			System.out.println(paasServer.getName() + " exactly matches " + serverNodeType.getName());
		else
			System.out.println(paasServer.getName() + " does not exactly match " + serverNodeType.getName());
		}

		//PLUG-IN: SummerNodeType vs. CalculatorService
		{ PlugInMatchmaker summerNodeTypeVSCalculatorService = new PlugInMatchmaker(serverNodeType, paasServer);
		if(summerNodeTypeVSCalculatorService.match())
			System.out.println(paasServer.getName() + " plug-in matches " + serverNodeType.getName());
		else
			System.out.println(paasServer.getName() + " does not plug-in match " + serverNodeType.getName());
		}

		//PLUG-IN: SummerNodeType vs. CalculatorServiceV1.1
		{ PlugInMatchmaker summerNodeTypeVSCalculatorServiceV11 = new PlugInMatchmaker(serverNodeType, paasServer2);
		if(summerNodeTypeVSCalculatorServiceV11.match())
			System.out.println(paasServer2.getName() + " plug-in matches " + serverNodeType.getName());
		else
			System.out.println(paasServer2.getName() + " does not plug-in match " + serverNodeType.getName());
		}
	}
}
