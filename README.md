# OpenCOM
component based

OpenCOM provides no kernel-level support for creating composite components.

The design of this component framework model is based upon the concept of composite components and promotes the following key properties:
1. A component framework in OpenCOM is a composite OpenCOM component.
2. A component framework provides an additional meta-interface for inspection
and dynamic adaptation of the local architecture of the composite component.
3. The integrity of each component framework is maintained in the face of dynamic change, using developer specified architectural rules plugged into the
component framework.

I implemented a simple calculator (multiple.divide.subtract.add) using the openCOM framework model.
That is,the classes  implement the same base interfaces (IMetaInterface, ILifeCycle and IConnections), custom service interfaces and 
receptacles.
Each component is made up from a set of Java classes.
o One class from this set defines the component type; For example, a component of type A includes a foundation class named A.
o The foundation class must implement a set of OpenCOM specific interfaces:
􏰀 IMetaInterface 􏰀 ILifeCycle
􏰀 IUnknown
o The component must be third party deployable; hence each component must be available within a JAR; a single JAR can contain one or more OpenCOMJ components.
o Each OpenCOMJ component lists one or more provides interfaces. Each interface must be an OpenCOM interface, which inherits the IUnknown interface.
o An OpenCOMJ component optionally lists a set of required interfaces. This consists as a set of receptacles (that must inherit from the OCM_Receptacle class). Each receptacle is a publicly accessible field of the foundation class.
o Apart from receptacles, no java field must be publicly accessible from classes external to the component classes.
o A composite component or component framework must implement the ICFMetaInterface interface, and define a single receptacle 
of type IAccept.
