
package org.modeldriven.fuml.repository.model;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import fUML.Syntax.Classes.Kernel.Association;
import fUML.Syntax.Classes.Kernel.Class_;
import fUML.Syntax.Classes.Kernel.Generalization;
import fUML.Syntax.Classes.Kernel.Classifier;
import fUML.Syntax.Classes.Kernel.LiteralInteger;
import fUML.Syntax.Classes.Kernel.LiteralUnlimitedNatural;
import fUML.Syntax.Classes.Kernel.PrimitiveType;
import UMLPrimitiveTypes.UnlimitedNatural;
import fUML.Syntax.Classes.Kernel.ValueSpecification;
import fUML.Syntax.Classes.Kernel.Enumeration;
import fUML.Syntax.Classes.Kernel.EnumerationLiteral;
import fUML.Syntax.Classes.Kernel.Package;
import fUML.Syntax.Classes.Kernel.Property;

import org.modeldriven.fuml.repository.RepositoryArtifact;
import org.modeldriven.fuml.repository.RepositoryMapping;
import org.modeldriven.fuml.repository.Repository;

import org.modeldriven.fuml.repository.config.Artifact;

// this needs to read the artifact namespace info from the content
public class fUML_SyntaxAssembler extends ModelAssembler 
    implements RepositoryArtifact
{

    private static Log log = LogFactory.getLog(fUML_SyntaxAssembler.class);
    private ModelFactory factory;

    public fUML_SyntaxAssembler(Artifact artifact, RepositoryMapping mapping, Repository model) {
        super(artifact, mapping, model);
        this.factory = new ModelFactory(mapping, model);
        construct();
    }

    private void construct() {
        log.info("initializing...");
        constructPackages();
        constructPrimitiveTypes();
        constructEnumerations();
        constructClasses();
        constructProperties();
        constructGeneralizations();
        constructAssociations();
    } 

    public String getURN() {
        return this.artifact.getUrn();
    }
    
    public String getNamespaceURI() {
        return this.artifact.getNamespaceURI();
    }
    
    private void constructPackages()
    {
        Package pkg = null;
         
                     
        // fUML.Syntax                                                    
    	pkg  = factory.createPackage("Syntax", "fUML.Syntax", "Syntax", pkg, this);
    	mapping.mapPackage(pkg, "fUML", this); 
                
                     
        // fUML.Syntax.CommonBehaviors                                                    
    	pkg  = factory.createPackage("CommonBehaviors", "fUML.Syntax.CommonBehaviors", "Syntax-CommonBehaviors", pkg, this);
    	mapping.mapPackage(pkg, "fUML.Syntax", this); 
                
                     
        // fUML.Syntax.CommonBehaviors.BasicBehaviors                                                    
    	pkg  = factory.createPackage("BasicBehaviors", "fUML.Syntax.CommonBehaviors.BasicBehaviors", "Syntax-CommonBehaviors-BasicBehaviors", pkg, this);
    	mapping.mapPackage(pkg, "fUML.Syntax.CommonBehaviors", this); 
                
                     
        // fUML.Syntax.CommonBehaviors.Communications                                                    
    	pkg  = factory.createPackage("Communications", "fUML.Syntax.CommonBehaviors.Communications", "Syntax-CommonBehaviors-Communications", pkg, this);
    	mapping.mapPackage(pkg, "fUML.Syntax.CommonBehaviors", this); 
                
                     
        // fUML.Syntax.Classes                                                    
    	pkg  = factory.createPackage("Classes", "fUML.Syntax.Classes", "Syntax-Classes", pkg, this);
    	mapping.mapPackage(pkg, "fUML.Syntax", this); 
                
                     
        // fUML.Syntax.Classes.Kernel                                                    
    	pkg  = factory.createPackage("Kernel", "fUML.Syntax.Classes.Kernel", "Syntax-Classes-Kernel", pkg, this);
    	mapping.mapPackage(pkg, "fUML.Syntax.Classes", this); 
                
                     
        // fUML.Syntax.Activities                                                    
    	pkg  = factory.createPackage("Activities", "fUML.Syntax.Activities", "Syntax-Activities", pkg, this);
    	mapping.mapPackage(pkg, "fUML.Syntax", this); 
                
                     
        // fUML.Syntax.Activities.IntermediateActivities                                                    
    	pkg  = factory.createPackage("IntermediateActivities", "fUML.Syntax.Activities.IntermediateActivities", "Syntax-Activities-IntermediateActivities", pkg, this);
    	mapping.mapPackage(pkg, "fUML.Syntax.Activities", this); 
                
                     
        // fUML.Syntax.Activities.CompleteStructuredActivities                                                    
    	pkg  = factory.createPackage("CompleteStructuredActivities", "fUML.Syntax.Activities.CompleteStructuredActivities", "Syntax-Activities-CompleteStructuredActivities", pkg, this);
    	mapping.mapPackage(pkg, "fUML.Syntax.Activities", this); 
                
                     
        // fUML.Syntax.Activities.ExtraStructuredActivities                                                    
    	pkg  = factory.createPackage("ExtraStructuredActivities", "fUML.Syntax.Activities.ExtraStructuredActivities", "Syntax-Activities-ExtraStructuredActivities", pkg, this);
    	mapping.mapPackage(pkg, "fUML.Syntax.Activities", this); 
                
                     
        // fUML.Syntax.Actions                                                    
    	pkg  = factory.createPackage("Actions", "fUML.Syntax.Actions", "Syntax-Actions", pkg, this);
    	mapping.mapPackage(pkg, "fUML.Syntax", this); 
                
                     
        // fUML.Syntax.Actions.BasicActions                                                    
    	pkg  = factory.createPackage("BasicActions", "fUML.Syntax.Actions.BasicActions", "Syntax-Actions-BasicActions", pkg, this);
    	mapping.mapPackage(pkg, "fUML.Syntax.Actions", this); 
                
                     
        // fUML.Syntax.Actions.IntermediateActions                                                    
    	pkg  = factory.createPackage("IntermediateActions", "fUML.Syntax.Actions.IntermediateActions", "Syntax-Actions-IntermediateActions", pkg, this);
    	mapping.mapPackage(pkg, "fUML.Syntax.Actions", this); 
                
                     
        // fUML.Syntax.Actions.CompleteActions                                                    
    	pkg  = factory.createPackage("CompleteActions", "fUML.Syntax.Actions.CompleteActions", "Syntax-Actions-CompleteActions", pkg, this);
    	mapping.mapPackage(pkg, "fUML.Syntax.Actions", this); 
                
                     
        // fUML.Syntax.L1                                                    
    	pkg  = factory.createPackage("L1", "fUML.Syntax.L1", "Syntax-L1", pkg, this);
    	mapping.mapPackage(pkg, "fUML.Syntax", this); 
                        
        mapping.mapPackageMerge(pkg, "Syntax-Classes-Kernel");
                        
        mapping.mapPackageMerge(pkg, "Syntax-CommonBehaviors-BasicBehaviors");
                        
        mapping.mapPackageMerge(pkg, "Syntax-CommonBehaviors-Communications");
                
                     
        // fUML.Syntax.L2                                                    
    	pkg  = factory.createPackage("L2", "fUML.Syntax.L2", "Syntax-L2", pkg, this);
    	mapping.mapPackage(pkg, "fUML.Syntax", this); 
                        
        mapping.mapPackageMerge(pkg, "Syntax-L1");
                        
        mapping.mapPackageMerge(pkg, "Syntax-Actions-IntermediateActions");
                        
        mapping.mapPackageMerge(pkg, "Syntax-Activities-IntermediateActivities");
                        
        mapping.mapPackageMerge(pkg, "Syntax-Actions-BasicActions");
                
                     
        // fUML.Syntax.L3                                                    
    	pkg  = factory.createPackage("L3", "fUML.Syntax.L3", "Syntax-L3", pkg, this);
    	mapping.mapPackage(pkg, "fUML.Syntax", this); 
                        
        mapping.mapPackageMerge(pkg, "Syntax-L2");
                        
        mapping.mapPackageMerge(pkg, "Syntax-Activities-CompleteStructuredActivities");
                        
        mapping.mapPackageMerge(pkg, "Syntax-Activities-ExtraStructuredActivities");
                        
        mapping.mapPackageMerge(pkg, "Syntax-Actions-CompleteActions");
            
    }   

    private void constructPrimitiveTypes()
    {
        PrimitiveType type = null;
    
    }   
       
    private void constructClasses()
    {
        Package pkg = null;
        String packageId = null;
        Class_ clss = null;
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-CommonBehaviors-BasicBehaviors";   
                      
        // fUML.Syntax.CommonBehaviors.BasicBehaviors.OpaqueBehavior 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.CommonBehaviors.BasicBehaviors").getDelegate();       
    	clss  = factory.createClass("OpaqueBehavior", "Syntax-CommonBehaviors-BasicBehaviors-OpaqueBehavior", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.CommonBehaviors.BasicBehaviors", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-CommonBehaviors-BasicBehaviors";   
                      
        // fUML.Syntax.CommonBehaviors.BasicBehaviors.FunctionBehavior 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.CommonBehaviors.BasicBehaviors").getDelegate();       
    	clss  = factory.createClass("FunctionBehavior", "Syntax-CommonBehaviors-BasicBehaviors-FunctionBehavior", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.CommonBehaviors.BasicBehaviors", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-CommonBehaviors-BasicBehaviors";   
                      
        // fUML.Syntax.CommonBehaviors.BasicBehaviors.BehavioredClassifier 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.CommonBehaviors.BasicBehaviors").getDelegate();       
    	clss  = factory.createClass("BehavioredClassifier", "Syntax-CommonBehaviors-BasicBehaviors-BehavioredClassifier", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "fUML.Syntax.CommonBehaviors.BasicBehaviors", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-CommonBehaviors-BasicBehaviors";   
                      
        // fUML.Syntax.CommonBehaviors.BasicBehaviors.Behavior 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.CommonBehaviors.BasicBehaviors").getDelegate();       
    	clss  = factory.createClass("Behavior", "Syntax-CommonBehaviors-BasicBehaviors-Behavior", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "fUML.Syntax.CommonBehaviors.BasicBehaviors", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-CommonBehaviors-Communications";   
                      
        // fUML.Syntax.CommonBehaviors.Communications.Trigger 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.CommonBehaviors.Communications").getDelegate();       
    	clss  = factory.createClass("Trigger", "Syntax-CommonBehaviors-Communications-Trigger", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.CommonBehaviors.Communications", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-CommonBehaviors-Communications";   
                      
        // fUML.Syntax.CommonBehaviors.Communications.Signal 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.CommonBehaviors.Communications").getDelegate();       
    	clss  = factory.createClass("Signal", "Syntax-CommonBehaviors-Communications-Signal", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.CommonBehaviors.Communications", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-CommonBehaviors-Communications";   
                      
        // fUML.Syntax.CommonBehaviors.Communications.SignalEvent 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.CommonBehaviors.Communications").getDelegate();       
    	clss  = factory.createClass("SignalEvent", "Syntax-CommonBehaviors-Communications-SignalEvent", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.CommonBehaviors.Communications", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-CommonBehaviors-Communications";   
                      
        // fUML.Syntax.CommonBehaviors.Communications.Reception 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.CommonBehaviors.Communications").getDelegate();       
    	clss  = factory.createClass("Reception", "Syntax-CommonBehaviors-Communications-Reception", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.CommonBehaviors.Communications", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-CommonBehaviors-Communications";   
                      
        // fUML.Syntax.CommonBehaviors.Communications.MessageEvent 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.CommonBehaviors.Communications").getDelegate();       
    	clss  = factory.createClass("MessageEvent", "Syntax-CommonBehaviors-Communications-MessageEvent", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "fUML.Syntax.CommonBehaviors.Communications", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-CommonBehaviors-Communications";   
                      
        // fUML.Syntax.CommonBehaviors.Communications.Event 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.CommonBehaviors.Communications").getDelegate();       
    	clss  = factory.createClass("Event", "Syntax-CommonBehaviors-Communications-Event", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "fUML.Syntax.CommonBehaviors.Communications", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.ValueSpecification 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("ValueSpecification", "Syntax-Classes-Kernel-ValueSpecification", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "fUML.Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.StructuralFeature 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("StructuralFeature", "Syntax-Classes-Kernel-StructuralFeature", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "fUML.Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.RedefinableElement 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("RedefinableElement", "Syntax-Classes-Kernel-RedefinableElement", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "fUML.Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.BehavioralFeature 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("BehavioralFeature", "Syntax-Classes-Kernel-BehavioralFeature", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "fUML.Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.InstanceSpecification 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("InstanceSpecification", "Syntax-Classes-Kernel-InstanceSpecification", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.InstanceValue 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("InstanceValue", "Syntax-Classes-Kernel-InstanceValue", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.LiteralBoolean 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("LiteralBoolean", "Syntax-Classes-Kernel-LiteralBoolean", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.LiteralInteger 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("LiteralInteger", "Syntax-Classes-Kernel-LiteralInteger", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.LiteralNull 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("LiteralNull", "Syntax-Classes-Kernel-LiteralNull", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.LiteralSpecification 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("LiteralSpecification", "Syntax-Classes-Kernel-LiteralSpecification", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "fUML.Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.LiteralString 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("LiteralString", "Syntax-Classes-Kernel-LiteralString", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.LiteralUnlimitedNatural 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("LiteralUnlimitedNatural", "Syntax-Classes-Kernel-LiteralUnlimitedNatural", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.OpaqueExpression 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("OpaqueExpression", "fUML-Classes-Kernel-OpaqueExpression", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.MultiplicityElement 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("MultiplicityElement", "Syntax-Classes-Kernel-MultiplicityElement", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.NamedElement 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("NamedElement", "Syntax-Classes-Kernel-NamedElement", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "fUML.Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.Namespace 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("Namespace", "Syntax-Classes-Kernel-Namespace", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "fUML.Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.Operation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("Operation", "Syntax-Classes-Kernel-Operation", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.Package 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("Package", "Syntax-Classes-Kernel-Package", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.Model 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("Model", "Syntax-Classes-Kernel-Model", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.PackageableElement 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("PackageableElement", "Syntax-Classes-Kernel-PackageableElement", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "fUML.Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.Parameter 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("Parameter", "Syntax-Classes-Kernel-Parameter", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.PrimitiveType 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("PrimitiveType", "Syntax-Classes-Kernel-PrimitiveType", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.Property 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("Property", "Syntax-Classes-Kernel-Property", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.Slot 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("Slot", "Syntax-Classes-Kernel-Slot", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.Type 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("Type", "Syntax-Classes-Kernel-Type", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "fUML.Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.TypedElement 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("TypedElement", "Syntax-Classes-Kernel-TypedElement", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.Class 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("Class", "Syntax-Classes-Kernel-Class", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.Classifier 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("Classifier", "Syntax-Classes-Kernel-Classifier", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "fUML.Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.DataType 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("DataType", "Syntax-Classes-Kernel-DataType", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.Element 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("Element", "Syntax-Classes-Kernel-Element", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "fUML.Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.Enumeration 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("Enumeration", "Syntax-Classes-Kernel-Enumeration", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.EnumerationLiteral 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("EnumerationLiteral", "Syntax-Classes-Kernel-EnumerationLiteral", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.Feature 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("Feature", "Syntax-Classes-Kernel-Feature", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "fUML.Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.Generalization 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("Generalization", "Syntax-Classes-Kernel-Generalization", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.Association 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("Association", "Syntax-Classes-Kernel-Association", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.Comment 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("Comment", "Syntax-Classes-Kernel-Comment", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.ElementImport 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("ElementImport", "Syntax-Classes-Kernel-ElementImport", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.PackageImport 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("PackageImport", "Syntax-Classes-Kernel-PackageImport", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-IntermediateActivities";   
                      
        // fUML.Syntax.Activities.IntermediateActivities.ObjectFlow 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Activities.IntermediateActivities").getDelegate();       
    	clss  = factory.createClass("ObjectFlow", "Syntax-Activities-IntermediateActivities-ObjectFlow", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Activities.IntermediateActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-IntermediateActivities";   
                      
        // fUML.Syntax.Activities.IntermediateActivities.ObjectNode 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Activities.IntermediateActivities").getDelegate();       
    	clss  = factory.createClass("ObjectNode", "Syntax-Activities-IntermediateActivities-ObjectNode", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "fUML.Syntax.Activities.IntermediateActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-IntermediateActivities";   
                      
        // fUML.Syntax.Activities.IntermediateActivities.MergeNode 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Activities.IntermediateActivities").getDelegate();       
    	clss  = factory.createClass("MergeNode", "Syntax-Activities-IntermediateActivities-MergeNode", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Activities.IntermediateActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-IntermediateActivities";   
                      
        // fUML.Syntax.Activities.IntermediateActivities.JoinNode 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Activities.IntermediateActivities").getDelegate();       
    	clss  = factory.createClass("JoinNode", "Syntax-Activities-IntermediateActivities-JoinNode", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Activities.IntermediateActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-IntermediateActivities";   
                      
        // fUML.Syntax.Activities.IntermediateActivities.InitialNode 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Activities.IntermediateActivities").getDelegate();       
    	clss  = factory.createClass("InitialNode", "Syntax-Activities-IntermediateActivities-InitialNode", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Activities.IntermediateActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-IntermediateActivities";   
                      
        // fUML.Syntax.Activities.IntermediateActivities.FinalNode 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Activities.IntermediateActivities").getDelegate();       
    	clss  = factory.createClass("FinalNode", "Syntax-Activities-IntermediateActivities-FinalNode", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "fUML.Syntax.Activities.IntermediateActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-IntermediateActivities";   
                      
        // fUML.Syntax.Activities.IntermediateActivities.ForkNode 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Activities.IntermediateActivities").getDelegate();       
    	clss  = factory.createClass("ForkNode", "Syntax-Activities-IntermediateActivities-ForkNode", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Activities.IntermediateActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-IntermediateActivities";   
                      
        // fUML.Syntax.Activities.IntermediateActivities.ControlFlow 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Activities.IntermediateActivities").getDelegate();       
    	clss  = factory.createClass("ControlFlow", "Syntax-Activities-IntermediateActivities-ControlFlow", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Activities.IntermediateActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-IntermediateActivities";   
                      
        // fUML.Syntax.Activities.IntermediateActivities.ControlNode 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Activities.IntermediateActivities").getDelegate();       
    	clss  = factory.createClass("ControlNode", "Syntax-Activities-IntermediateActivities-ControlNode", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "fUML.Syntax.Activities.IntermediateActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-IntermediateActivities";   
                      
        // fUML.Syntax.Activities.IntermediateActivities.DecisionNode 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Activities.IntermediateActivities").getDelegate();       
    	clss  = factory.createClass("DecisionNode", "Syntax-Activities-IntermediateActivities-DecisionNode", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Activities.IntermediateActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-IntermediateActivities";   
                      
        // fUML.Syntax.Activities.IntermediateActivities.ActivityFinalNode 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Activities.IntermediateActivities").getDelegate();       
    	clss  = factory.createClass("ActivityFinalNode", "Syntax-Activities-IntermediateActivities-ActivityFinalNode", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Activities.IntermediateActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-IntermediateActivities";   
                      
        // fUML.Syntax.Activities.IntermediateActivities.ActivityNode 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Activities.IntermediateActivities").getDelegate();       
    	clss  = factory.createClass("ActivityNode", "Syntax-Activities-IntermediateActivities-ActivityNode", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "fUML.Syntax.Activities.IntermediateActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-IntermediateActivities";   
                      
        // fUML.Syntax.Activities.IntermediateActivities.ActivityParameterNode 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Activities.IntermediateActivities").getDelegate();       
    	clss  = factory.createClass("ActivityParameterNode", "Syntax-Activities-IntermediateActivities-ActivityParameterNode", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Activities.IntermediateActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-IntermediateActivities";   
                      
        // fUML.Syntax.Activities.IntermediateActivities.ActivityEdge 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Activities.IntermediateActivities").getDelegate();       
    	clss  = factory.createClass("ActivityEdge", "Syntax-Activities-IntermediateActivities-ActivityEdge", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "fUML.Syntax.Activities.IntermediateActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-IntermediateActivities";   
                      
        // fUML.Syntax.Activities.IntermediateActivities.Activity 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Activities.IntermediateActivities").getDelegate();       
    	clss  = factory.createClass("Activity", "Syntax-Activities-IntermediateActivities-Activity", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Activities.IntermediateActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-CompleteStructuredActivities";   
                      
        // fUML.Syntax.Activities.CompleteStructuredActivities.StructuredActivityNode 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Activities.CompleteStructuredActivities").getDelegate();       
    	clss  = factory.createClass("StructuredActivityNode", "Syntax-Activities-CompleteStructuredActivities-StructuredActivityNode", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Activities.CompleteStructuredActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-CompleteStructuredActivities";   
                      
        // fUML.Syntax.Activities.CompleteStructuredActivities.LoopNode 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Activities.CompleteStructuredActivities").getDelegate();       
    	clss  = factory.createClass("LoopNode", "Syntax-Activities-CompleteStructuredActivities-LoopNode", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Activities.CompleteStructuredActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-CompleteStructuredActivities";   
                      
        // fUML.Syntax.Activities.CompleteStructuredActivities.ExecutableNode 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Activities.CompleteStructuredActivities").getDelegate();       
    	clss  = factory.createClass("ExecutableNode", "Syntax-Activities-CompleteStructuredActivities-ExecutableNode", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "fUML.Syntax.Activities.CompleteStructuredActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-CompleteStructuredActivities";   
                      
        // fUML.Syntax.Activities.CompleteStructuredActivities.Clause 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Activities.CompleteStructuredActivities").getDelegate();       
    	clss  = factory.createClass("Clause", "Syntax-Activities-CompleteStructuredActivities-Clause", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Activities.CompleteStructuredActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-CompleteStructuredActivities";   
                      
        // fUML.Syntax.Activities.CompleteStructuredActivities.ConditionalNode 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Activities.CompleteStructuredActivities").getDelegate();       
    	clss  = factory.createClass("ConditionalNode", "Syntax-Activities-CompleteStructuredActivities-ConditionalNode", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Activities.CompleteStructuredActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-ExtraStructuredActivities";   
                      
        // fUML.Syntax.Activities.ExtraStructuredActivities.ExpansionNode 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Activities.ExtraStructuredActivities").getDelegate();       
    	clss  = factory.createClass("ExpansionNode", "Syntax-Activities-ExtraStructuredActivities-ExpansionNode", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Activities.ExtraStructuredActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-ExtraStructuredActivities";   
                      
        // fUML.Syntax.Activities.ExtraStructuredActivities.ExpansionRegion 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Activities.ExtraStructuredActivities").getDelegate();       
    	clss  = factory.createClass("ExpansionRegion", "Syntax-Activities-ExtraStructuredActivities-ExpansionRegion", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Activities.ExtraStructuredActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-BasicActions";   
                      
        // fUML.Syntax.Actions.BasicActions.SendSignalAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Actions.BasicActions").getDelegate();       
    	clss  = factory.createClass("SendSignalAction", "Syntax-Actions-BasicActions-SendSignalAction", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Actions.BasicActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-BasicActions";   
                      
        // fUML.Syntax.Actions.BasicActions.OutputPin 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Actions.BasicActions").getDelegate();       
    	clss  = factory.createClass("OutputPin", "Syntax-Actions-BasicActions-OutputPin", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Actions.BasicActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-BasicActions";   
                      
        // fUML.Syntax.Actions.BasicActions.Pin 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Actions.BasicActions").getDelegate();       
    	clss  = factory.createClass("Pin", "Syntax-Actions-BasicActions-Pin", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "fUML.Syntax.Actions.BasicActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-BasicActions";   
                      
        // fUML.Syntax.Actions.BasicActions.InputPin 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Actions.BasicActions").getDelegate();       
    	clss  = factory.createClass("InputPin", "Syntax-Actions-BasicActions-InputPin", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Actions.BasicActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-BasicActions";   
                      
        // fUML.Syntax.Actions.BasicActions.InvocationAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Actions.BasicActions").getDelegate();       
    	clss  = factory.createClass("InvocationAction", "Syntax-Actions-BasicActions-InvocationAction", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "fUML.Syntax.Actions.BasicActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-BasicActions";   
                      
        // fUML.Syntax.Actions.BasicActions.CallAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Actions.BasicActions").getDelegate();       
    	clss  = factory.createClass("CallAction", "Syntax-Actions-BasicActions-CallAction", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "fUML.Syntax.Actions.BasicActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-BasicActions";   
                      
        // fUML.Syntax.Actions.BasicActions.CallBehaviorAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Actions.BasicActions").getDelegate();       
    	clss  = factory.createClass("CallBehaviorAction", "Syntax-Actions-BasicActions-CallBehaviorAction", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Actions.BasicActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-BasicActions";   
                      
        // fUML.Syntax.Actions.BasicActions.CallOperationAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Actions.BasicActions").getDelegate();       
    	clss  = factory.createClass("CallOperationAction", "Syntax-Actions-BasicActions-CallOperationAction", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Actions.BasicActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-BasicActions";   
                      
        // fUML.Syntax.Actions.BasicActions.Action 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Actions.BasicActions").getDelegate();       
    	clss  = factory.createClass("Action", "Syntax-Actions-BasicActions-Action", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "fUML.Syntax.Actions.BasicActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // fUML.Syntax.Actions.IntermediateActions.StructuralFeatureAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Actions.IntermediateActions").getDelegate();       
    	clss  = factory.createClass("StructuralFeatureAction", "Syntax-Actions-IntermediateActions-StructuralFeatureAction", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "fUML.Syntax.Actions.IntermediateActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // fUML.Syntax.Actions.IntermediateActions.TestIdentityAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Actions.IntermediateActions").getDelegate();       
    	clss  = factory.createClass("TestIdentityAction", "Syntax-Actions-IntermediateActions-TestIdentityAction", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Actions.IntermediateActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // fUML.Syntax.Actions.IntermediateActions.ValueSpecificationAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Actions.IntermediateActions").getDelegate();       
    	clss  = factory.createClass("ValueSpecificationAction", "Syntax-Actions-IntermediateActions-ValueSpecificationAction", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Actions.IntermediateActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // fUML.Syntax.Actions.IntermediateActions.WriteLinkAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Actions.IntermediateActions").getDelegate();       
    	clss  = factory.createClass("WriteLinkAction", "Syntax-Actions-IntermediateActions-WriteLinkAction", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "fUML.Syntax.Actions.IntermediateActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // fUML.Syntax.Actions.IntermediateActions.WriteStructuralFeatureAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Actions.IntermediateActions").getDelegate();       
    	clss  = factory.createClass("WriteStructuralFeatureAction", "Syntax-Actions-IntermediateActions-WriteStructuralFeatureAction", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "fUML.Syntax.Actions.IntermediateActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // fUML.Syntax.Actions.IntermediateActions.RemoveStructuralFeatureValueAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Actions.IntermediateActions").getDelegate();       
    	clss  = factory.createClass("RemoveStructuralFeatureValueAction", "Syntax-Actions-IntermediateActions-RemoveStructuralFeatureValueAction", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Actions.IntermediateActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // fUML.Syntax.Actions.IntermediateActions.ReadLinkAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Actions.IntermediateActions").getDelegate();       
    	clss  = factory.createClass("ReadLinkAction", "Syntax-Actions-IntermediateActions-ReadLinkAction", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Actions.IntermediateActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // fUML.Syntax.Actions.IntermediateActions.ReadSelfAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Actions.IntermediateActions").getDelegate();       
    	clss  = factory.createClass("ReadSelfAction", "Syntax-Actions-IntermediateActions-ReadSelfAction", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Actions.IntermediateActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // fUML.Syntax.Actions.IntermediateActions.ReadStructuralFeatureAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Actions.IntermediateActions").getDelegate();       
    	clss  = factory.createClass("ReadStructuralFeatureAction", "Syntax-Actions-IntermediateActions-ReadStructuralFeatureAction", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Actions.IntermediateActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // fUML.Syntax.Actions.IntermediateActions.LinkAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Actions.IntermediateActions").getDelegate();       
    	clss  = factory.createClass("LinkAction", "Syntax-Actions-IntermediateActions-LinkAction", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "fUML.Syntax.Actions.IntermediateActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // fUML.Syntax.Actions.IntermediateActions.LinkEndCreationData 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Actions.IntermediateActions").getDelegate();       
    	clss  = factory.createClass("LinkEndCreationData", "Syntax-Actions-IntermediateActions-LinkEndCreationData", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Actions.IntermediateActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // fUML.Syntax.Actions.IntermediateActions.LinkEndData 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Actions.IntermediateActions").getDelegate();       
    	clss  = factory.createClass("LinkEndData", "Syntax-Actions-IntermediateActions-LinkEndData", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Actions.IntermediateActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // fUML.Syntax.Actions.IntermediateActions.LinkEndDestructionData 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Actions.IntermediateActions").getDelegate();       
    	clss  = factory.createClass("LinkEndDestructionData", "Syntax-Actions-IntermediateActions-LinkEndDestructionData", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Actions.IntermediateActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // fUML.Syntax.Actions.IntermediateActions.ClearAssociationAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Actions.IntermediateActions").getDelegate();       
    	clss  = factory.createClass("ClearAssociationAction", "Syntax-Actions-IntermediateActions-ClearAssociationAction", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Actions.IntermediateActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // fUML.Syntax.Actions.IntermediateActions.ClearStructuralFeatureAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Actions.IntermediateActions").getDelegate();       
    	clss  = factory.createClass("ClearStructuralFeatureAction", "Syntax-Actions-IntermediateActions-ClearStructuralFeatureAction", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Actions.IntermediateActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // fUML.Syntax.Actions.IntermediateActions.CreateLinkAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Actions.IntermediateActions").getDelegate();       
    	clss  = factory.createClass("CreateLinkAction", "Syntax-Actions-IntermediateActions-CreateLinkAction", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Actions.IntermediateActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // fUML.Syntax.Actions.IntermediateActions.CreateObjectAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Actions.IntermediateActions").getDelegate();       
    	clss  = factory.createClass("CreateObjectAction", "Syntax-Actions-IntermediateActions-CreateObjectAction", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Actions.IntermediateActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // fUML.Syntax.Actions.IntermediateActions.DestroyLinkAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Actions.IntermediateActions").getDelegate();       
    	clss  = factory.createClass("DestroyLinkAction", "Syntax-Actions-IntermediateActions-DestroyLinkAction", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Actions.IntermediateActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // fUML.Syntax.Actions.IntermediateActions.DestroyObjectAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Actions.IntermediateActions").getDelegate();       
    	clss  = factory.createClass("DestroyObjectAction", "Syntax-Actions-IntermediateActions-DestroyObjectAction", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Actions.IntermediateActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // fUML.Syntax.Actions.IntermediateActions.AddStructuralFeatureValueAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Actions.IntermediateActions").getDelegate();       
    	clss  = factory.createClass("AddStructuralFeatureValueAction", "Syntax-Actions-IntermediateActions-AddStructuralFeatureValueAction", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Actions.IntermediateActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-CompleteActions";   
                      
        // fUML.Syntax.Actions.CompleteActions.StartClassifierBehaviorAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Actions.CompleteActions").getDelegate();       
    	clss  = factory.createClass("StartClassifierBehaviorAction", "Syntax-Actions-CompleteActions-StartClassifierBehaviorAction", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Actions.CompleteActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-CompleteActions";   
                      
        // fUML.Syntax.Actions.CompleteActions.StartObjectBehaviorAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Actions.CompleteActions").getDelegate();       
    	clss  = factory.createClass("StartObjectBehaviorAction", "Syntax-Actions-CompleteActions-StartObjectBehaviorAction", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Actions.CompleteActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-CompleteActions";   
                      
        // fUML.Syntax.Actions.CompleteActions.ReduceAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Actions.CompleteActions").getDelegate();       
    	clss  = factory.createClass("ReduceAction", "Syntax-Actions-CompleteActions-ReduceAction", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Actions.CompleteActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-CompleteActions";   
                      
        // fUML.Syntax.Actions.CompleteActions.ReadExtentAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Actions.CompleteActions").getDelegate();       
    	clss  = factory.createClass("ReadExtentAction", "Syntax-Actions-CompleteActions-ReadExtentAction", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Actions.CompleteActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-CompleteActions";   
                      
        // fUML.Syntax.Actions.CompleteActions.ReadIsClassifiedObjectAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Actions.CompleteActions").getDelegate();       
    	clss  = factory.createClass("ReadIsClassifiedObjectAction", "Syntax-Actions-CompleteActions-ReadIsClassifiedObjectAction", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Actions.CompleteActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-CompleteActions";   
                      
        // fUML.Syntax.Actions.CompleteActions.ReclassifyObjectAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Actions.CompleteActions").getDelegate();       
    	clss  = factory.createClass("ReclassifyObjectAction", "Syntax-Actions-CompleteActions-ReclassifyObjectAction", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Actions.CompleteActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-CompleteActions";   
                      
        // fUML.Syntax.Actions.CompleteActions.AcceptEventAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("fUML.Syntax.Actions.CompleteActions").getDelegate();       
    	clss  = factory.createClass("AcceptEventAction", "Syntax-Actions-CompleteActions-AcceptEventAction", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "fUML.Syntax.Actions.CompleteActions", this); 
    
    }   

    private void constructEnumerations()
    {
        Enumeration enumeration = null;
        EnumerationLiteral literal = null;
                                                      
        // CallConcurrencyKind
    	enumeration  = factory.createEnumeration("CallConcurrencyKind", "Syntax-CommonBehaviors-BasicBehaviors-CallConcurrencyKind");
    	mapping.mapEnumeration(enumeration, "fUML.Syntax.CommonBehaviors.BasicBehaviors", this); 
        
        literal = factory.createEnumerationLiteral(enumeration, 
            "sequential",
            "Syntax-CommonBehaviors-BasicBehaviors-CallConcurrencyKind-sequential");
    	mapping.mapEnumerationLiteral(literal, "fUML.Syntax.CommonBehaviors.BasicBehaviors", this); 
                                                          
        // AggregationKind
    	enumeration  = factory.createEnumeration("AggregationKind", "Syntax-Classes-Kernel-AggregationKind");
    	mapping.mapEnumeration(enumeration, "fUML.Syntax.Classes.Kernel", this); 
        
        literal = factory.createEnumerationLiteral(enumeration, 
            "none",
            "Syntax-Classes-Kernel-AggregationKind-none");
    	mapping.mapEnumerationLiteral(literal, "fUML.Syntax.Classes.Kernel", this); 
        
        literal = factory.createEnumerationLiteral(enumeration, 
            "shared",
            "Syntax-Classes-Kernel-AggregationKind-shared");
    	mapping.mapEnumerationLiteral(literal, "fUML.Syntax.Classes.Kernel", this); 
        
        literal = factory.createEnumerationLiteral(enumeration, 
            "composite",
            "Syntax-Classes-Kernel-AggregationKind-composite");
    	mapping.mapEnumerationLiteral(literal, "fUML.Syntax.Classes.Kernel", this); 
                                                          
        // ParameterDirectionKind
    	enumeration  = factory.createEnumeration("ParameterDirectionKind", "Syntax-Classes-Kernel-ParameterDirectionKind");
    	mapping.mapEnumeration(enumeration, "fUML.Syntax.Classes.Kernel", this); 
        
        literal = factory.createEnumerationLiteral(enumeration, 
            "in",
            "Syntax-Classes-Kernel-ParameterDirectionKind-in");
    	mapping.mapEnumerationLiteral(literal, "fUML.Syntax.Classes.Kernel", this); 
        
        literal = factory.createEnumerationLiteral(enumeration, 
            "inout",
            "Syntax-Classes-Kernel-ParameterDirectionKind-inout");
    	mapping.mapEnumerationLiteral(literal, "fUML.Syntax.Classes.Kernel", this); 
        
        literal = factory.createEnumerationLiteral(enumeration, 
            "out",
            "Syntax-Classes-Kernel-ParameterDirectionKind-out");
    	mapping.mapEnumerationLiteral(literal, "fUML.Syntax.Classes.Kernel", this); 
        
        literal = factory.createEnumerationLiteral(enumeration, 
            "return",
            "Syntax-Classes-Kernel-ParameterDirectionKind-return");
    	mapping.mapEnumerationLiteral(literal, "fUML.Syntax.Classes.Kernel", this); 
                                                          
        // VisibilityKind
    	enumeration  = factory.createEnumeration("VisibilityKind", "Syntax-Classes-Kernel-VisibilityKind");
    	mapping.mapEnumeration(enumeration, "fUML.Syntax.Classes.Kernel", this); 
        
        literal = factory.createEnumerationLiteral(enumeration, 
            "public",
            "Syntax-Classes-Kernel-VisibilityKind-public");
    	mapping.mapEnumerationLiteral(literal, "fUML.Syntax.Classes.Kernel", this); 
        
        literal = factory.createEnumerationLiteral(enumeration, 
            "private",
            "Syntax-Classes-Kernel-VisibilityKind-private");
    	mapping.mapEnumerationLiteral(literal, "fUML.Syntax.Classes.Kernel", this); 
        
        literal = factory.createEnumerationLiteral(enumeration, 
            "protected",
            "Syntax-Classes-Kernel-VisibilityKind-protected");
    	mapping.mapEnumerationLiteral(literal, "fUML.Syntax.Classes.Kernel", this); 
        
        literal = factory.createEnumerationLiteral(enumeration, 
            "package",
            "Syntax-Classes-Kernel-VisibilityKind-package");
    	mapping.mapEnumerationLiteral(literal, "fUML.Syntax.Classes.Kernel", this); 
                                                          
        // ExpansionKind
    	enumeration  = factory.createEnumeration("ExpansionKind", "Syntax-Activities-ExtraStructuredActivities-ExpansionKind");
    	mapping.mapEnumeration(enumeration, "fUML.Syntax.Activities.ExtraStructuredActivities", this); 
        
        literal = factory.createEnumerationLiteral(enumeration, 
            "parallel",
            "Syntax-Activities-ExtraStructuredActivities-ExpansionKind-parallel");
    	mapping.mapEnumerationLiteral(literal, "fUML.Syntax.Activities.ExtraStructuredActivities", this); 
        
        literal = factory.createEnumerationLiteral(enumeration, 
            "iterative",
            "Syntax-Activities-ExtraStructuredActivities-ExpansionKind-iterative");
    	mapping.mapEnumerationLiteral(literal, "fUML.Syntax.Activities.ExtraStructuredActivities", this); 
        
        literal = factory.createEnumerationLiteral(enumeration, 
            "stream",
            "Syntax-Activities-ExtraStructuredActivities-ExpansionKind-stream");
    	mapping.mapEnumerationLiteral(literal, "fUML.Syntax.Activities.ExtraStructuredActivities", this); 
        
    }   

    private void constructProperties()
    {
        Class_ clss = null;
        Property prop = null;
        
    
        // OpaqueBehavior
    	clss  = (Class_)model.getElementById("Syntax-CommonBehaviors-BasicBehaviors-OpaqueBehavior").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "body", 
    	    "Syntax-CommonBehaviors-BasicBehaviors-OpaqueBehavior-body",
    	    "http://schema.omg.org/spec/UML/2.2/uml.xml#String",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "language", 
    	    "Syntax-CommonBehaviors-BasicBehaviors-OpaqueBehavior-language",
    	    "http://schema.omg.org/spec/UML/2.2/uml.xml#String",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // FunctionBehavior
    	clss  = (Class_)model.getElementById("Syntax-CommonBehaviors-BasicBehaviors-FunctionBehavior").getDelegate();
    	
        // BehavioredClassifier
    	clss  = (Class_)model.getElementById("Syntax-CommonBehaviors-BasicBehaviors-BehavioredClassifier").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "ownedBehavior", 
    	    "Syntax-CommonBehaviors-BasicBehaviors-BehavioredClassifier-ownedBehavior",
    	    "Syntax-CommonBehaviors-BasicBehaviors-Behavior",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "classifierBehavior", 
    	    "Syntax-CommonBehaviors-BasicBehaviors-BehavioredClassifier-classifierBehavior",
    	    "Syntax-CommonBehaviors-BasicBehaviors-Behavior",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // Behavior
    	clss  = (Class_)model.getElementById("Syntax-CommonBehaviors-BasicBehaviors-Behavior").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "isReentrant", 
    	    "Syntax-CommonBehaviors-BasicBehaviors-Behavior-isReentrant",
    	    "http://schema.omg.org/spec/UML/2.2/uml.xml#Boolean",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
     	factory.createDefault(prop,
    	   new Boolean(true),
    	   "",
    	   "Syntax-CommonBehaviors-BasicBehaviors-Behavior-isReentrant-_defaultValue",  
    	   "uml:LiteralBoolean",  
    	   "");
    	    	
    	prop = factory.createProperty(clss, "specification", 
    	    "Syntax-CommonBehaviors-BasicBehaviors-Behavior-specification",
    	    "Syntax-Classes-Kernel-BehavioralFeature",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "ownedParameter", 
    	    "Syntax-CommonBehaviors-BasicBehaviors-Behavior-ownedParameter",
    	    "Syntax-Classes-Kernel-Parameter",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "context", 
    	    "Syntax-CommonBehaviors-BasicBehaviors-Behavior-context",
    	    "Syntax-CommonBehaviors-BasicBehaviors-BehavioredClassifier",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // Trigger
    	clss  = (Class_)model.getElementById("Syntax-CommonBehaviors-Communications-Trigger").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "event", 
    	    "Syntax-CommonBehaviors-Communications-Trigger-event",
    	    "Syntax-CommonBehaviors-Communications-Event",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // Signal
    	clss  = (Class_)model.getElementById("Syntax-CommonBehaviors-Communications-Signal").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "ownedAttribute", 
    	    "Syntax-CommonBehaviors-Communications-Signal-ownedAttribute",
    	    "Syntax-Classes-Kernel-Property",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // SignalEvent
    	clss  = (Class_)model.getElementById("Syntax-CommonBehaviors-Communications-SignalEvent").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "signal", 
    	    "Syntax-CommonBehaviors-Communications-SignalEvent-signal",
    	    "Syntax-CommonBehaviors-Communications-Signal",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // Reception
    	clss  = (Class_)model.getElementById("Syntax-CommonBehaviors-Communications-Reception").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "signal", 
    	    "Syntax-CommonBehaviors-Communications-Reception-signal",
    	    "Syntax-CommonBehaviors-Communications-Signal",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // MessageEvent
    	clss  = (Class_)model.getElementById("Syntax-CommonBehaviors-Communications-MessageEvent").getDelegate();
    	
        // Event
    	clss  = (Class_)model.getElementById("Syntax-CommonBehaviors-Communications-Event").getDelegate();
    	
        // ValueSpecification
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-ValueSpecification").getDelegate();
    	
        // StructuralFeature
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-StructuralFeature").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "isReadOnly", 
    	    "Syntax-Classes-Kernel-StructuralFeature-isReadOnly",
    	    "http://schema.omg.org/spec/UML/2.2/uml.xml#Boolean",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
     	factory.createDefault(prop,
    	   new Boolean(false),
    	   "",
    	   "Syntax-Classes-Kernel-StructuralFeature-isReadOnly-_defaultValue",  
    	   "uml:LiteralBoolean",  
    	   "");
    	
        // RedefinableElement
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-RedefinableElement").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "isLeaf", 
    	    "Syntax-Classes-Kernel-RedefinableElement-isLeaf",
    	    "http://schema.omg.org/spec/UML/2.2/uml.xml#Boolean",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
     	factory.createDefault(prop,
    	   new Boolean(false),
    	   "",
    	   "Syntax-Classes-Kernel-RedefinableElement-isLeaf-_defaultValue",  
    	   "uml:LiteralBoolean",  
    	   "");
    	    	
    	prop = factory.createProperty(clss, "redefinedElement", 
    	    "Syntax-Classes-Kernel-RedefinableElement-redefinedElement",
    	    "Syntax-Classes-Kernel-RedefinableElement",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "redefinitionContext", 
    	    "Syntax-Classes-Kernel-RedefinableElement-redefinitionContext",
    	    "Syntax-Classes-Kernel-Classifier",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // BehavioralFeature
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-BehavioralFeature").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "ownedParameter", 
    	    "Syntax-Classes-Kernel-BehavioralFeature-ownedParameter",
    	    "Syntax-Classes-Kernel-Parameter",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "isAbstract", 
    	    "Syntax-Classes-Kernel-BehavioralFeature-isAbstract",
    	    "http://schema.omg.org/spec/UML/2.2/uml.xml#Boolean",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
     	factory.createDefault(prop,
    	   new Boolean(false),
    	   "",
    	   "Syntax-Classes-Kernel-BehavioralFeature-isAbstract-_defaultValue",  
    	   "uml:LiteralBoolean",  
    	   "");
    	    	
    	prop = factory.createProperty(clss, "method", 
    	    "Syntax-Classes-Kernel-BehavioralFeature-method",
    	    "Syntax-CommonBehaviors-BasicBehaviors-Behavior",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "concurrency", 
    	    "Syntax-Classes-Kernel-BehavioralFeature-concurrency",
    	    "Syntax-CommonBehaviors-BasicBehaviors-CallConcurrencyKind",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
     	factory.createDefault(prop,
    	   new String(""),
    	   "Syntax-CommonBehaviors-BasicBehaviors-CallConcurrencyKind-sequential",
    	   "Syntax-Classes-Kernel-BehavioralFeature-concurrency-sequential",  
    	   "uml:InstanceValue",  
    	   "Syntax-CommonBehaviors-BasicBehaviors-CallConcurrencyKind");
    	
        // InstanceSpecification
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-InstanceSpecification").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "classifier", 
    	    "Syntax-Classes-Kernel-InstanceSpecification-classifier",
    	    "Syntax-Classes-Kernel-Classifier",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "slot", 
    	    "Syntax-Classes-Kernel-InstanceSpecification-slot",
    	    "Syntax-Classes-Kernel-Slot",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // InstanceValue
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-InstanceValue").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "instance", 
    	    "Syntax-Classes-Kernel-InstanceValue-instance",
    	    "Syntax-Classes-Kernel-InstanceSpecification",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // LiteralBoolean
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-LiteralBoolean").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "value", 
    	    "Syntax-Classes-Kernel-LiteralBoolean-value",
    	    "http://schema.omg.org/spec/UML/2.2/uml.xml#Boolean",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
     	factory.createDefault(prop,
    	   new Boolean(false),
    	   "",
    	   "Syntax-Classes-Kernel-LiteralBoolean-value-_defaultValue",  
    	   "uml:LiteralBoolean",  
    	   "");
    	
        // LiteralInteger
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-LiteralInteger").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "value", 
    	    "Syntax-Classes-Kernel-LiteralInteger-value",
    	    "http://schema.omg.org/spec/UML/2.2/uml.xml#Integer",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
     	factory.createDefault(prop,
    	   new Integer(0)
                ,
    	   "",
    	   "Syntax-Classes-Kernel-LiteralInteger-value-_defaultValue",  
    	   "uml:LiteralInteger",  
    	   "");
    	
        // LiteralNull
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-LiteralNull").getDelegate();
    	
        // LiteralSpecification
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-LiteralSpecification").getDelegate();
    	
        // LiteralString
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-LiteralString").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "value", 
    	    "Syntax-Classes-Kernel-LiteralString-value",
    	    "http://schema.omg.org/spec/UML/2.2/uml.xml#String",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // LiteralUnlimitedNatural
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-LiteralUnlimitedNatural").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "value", 
    	    "Syntax-Classes-Kernel-LiteralUnlimitedNatural-value",
    	    "http://schema.omg.org/spec/UML/2.2/uml.xml#UnlimitedNatural",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
     	factory.createDefault(prop,
    	   new UnlimitedNatural(0),
    	   "",
    	   "Syntax-Classes-Kernel-LiteralUnlimitedNatural-value-_defaultValue",  
    	   "uml:OpaqueExpression",  
    	   "");
    	
        // OpaqueExpression
    	clss  = (Class_)model.getElementById("fUML-Classes-Kernel-OpaqueExpression").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "body", 
    	    "fUML-Classes-Kernel-OpaqueExpression-body",
    	    "http://schema.omg.org/spec/UML/2.2/uml.xml#String",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "language", 
    	    "fUML-Classes-Kernel-OpaqueExpression-language",
    	    "http://schema.omg.org/spec/UML/2.2/uml.xml#String",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // MultiplicityElement
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-MultiplicityElement").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "isOrdered", 
    	    "Syntax-Classes-Kernel-MultiplicityElement-isOrdered",
    	    "http://schema.omg.org/spec/UML/2.2/uml.xml#Boolean",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
     	factory.createDefault(prop,
    	   new Boolean(false),
    	   "",
    	   "Syntax-Classes-Kernel-MultiplicityElement-isOrdered-_defaultValue",  
    	   "uml:LiteralBoolean",  
    	   "");
    	    	
    	prop = factory.createProperty(clss, "isUnique", 
    	    "Syntax-Classes-Kernel-MultiplicityElement-isUnique",
    	    "http://schema.omg.org/spec/UML/2.2/uml.xml#Boolean",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
     	factory.createDefault(prop,
    	   new Boolean(true),
    	   "",
    	   "Syntax-Classes-Kernel-MultiplicityElement-isUnique-_defaultValue",  
    	   "uml:LiteralBoolean",  
    	   "");
    	    	
    	prop = factory.createProperty(clss, "upper", 
    	    "Syntax-Classes-Kernel-MultiplicityElement-upper",
    	    "http://schema.omg.org/spec/UML/2.2/uml.xml#UnlimitedNatural",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
     	factory.createDefault(prop,
    	   new UnlimitedNatural(1),
    	   "",
    	   "Syntax-Classes-Kernel-MultiplicityElement-upper-_defaultValue",  
    	   "uml:OpaqueExpression",  
    	   "");
    	    	
    	prop = factory.createProperty(clss, "lower", 
    	    "Syntax-Classes-Kernel-MultiplicityElement-lower",
    	    "http://schema.omg.org/spec/UML/2.2/uml.xml#Integer",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
     	factory.createDefault(prop,
    	   new Integer(1)
                ,
    	   "",
    	   "Syntax-Classes-Kernel-MultiplicityElement-lower-_defaultValue",  
    	   "uml:LiteralInteger",  
    	   "");
    	    	
    	prop = factory.createProperty(clss, "upperValue", 
    	    "Syntax-Classes-Kernel-MultiplicityElement-upperValue",
    	    "Syntax-Classes-Kernel-ValueSpecification",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "lowerValue", 
    	    "Syntax-Classes-Kernel-MultiplicityElement-lowerValue",
    	    "Syntax-Classes-Kernel-ValueSpecification",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // NamedElement
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-NamedElement").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "name", 
    	    "Syntax-Classes-Kernel-NamedElement-name",
    	    "http://schema.omg.org/spec/UML/2.2/uml.xml#String",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "visibility", 
    	    "Syntax-Classes-Kernel-NamedElement-visibility",
    	    "Syntax-Classes-Kernel-VisibilityKind",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "qualifiedName", 
    	    "Syntax-Classes-Kernel-NamedElement-qualifiedName",
    	    "http://schema.omg.org/spec/UML/2.2/uml.xml#String",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "namespace", 
    	    "Syntax-Classes-Kernel-NamedElement-namespace",
    	    "Syntax-Classes-Kernel-Namespace",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // Namespace
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-Namespace").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "member", 
    	    "Syntax-Classes-Kernel-Namespace-member",
    	    "Syntax-Classes-Kernel-NamedElement",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "ownedMember", 
    	    "Syntax-Classes-Kernel-Namespace-ownedMember",
    	    "Syntax-Classes-Kernel-NamedElement",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "elementImport", 
    	    "Syntax-Classes-Kernel-Namespace-elementImport",
    	    "Syntax-Classes-Kernel-ElementImport",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "packageImport", 
    	    "Syntax-Classes-Kernel-Namespace-packageImport",
    	    "Syntax-Classes-Kernel-PackageImport",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "importedMember", 
    	    "Syntax-Classes-Kernel-Namespace-importedMember",
    	    "Syntax-Classes-Kernel-PackageableElement",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // Operation
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-Operation").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "isQuery", 
    	    "Syntax-Classes-Kernel-Operation-isQuery",
    	    "http://schema.omg.org/spec/UML/2.2/uml.xml#Boolean",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
     	factory.createDefault(prop,
    	   new Boolean(false),
    	   "",
    	   "Syntax-Classes-Kernel-Operation-isQuery-_defaultValue",  
    	   "uml:LiteralBoolean",  
    	   "");
    	    	
    	prop = factory.createProperty(clss, "isOrdered", 
    	    "Syntax-Classes-Kernel-Operation-isOrdered",
    	    "http://schema.omg.org/spec/UML/2.2/uml.xml#Boolean",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
     	factory.createDefault(prop,
    	   new Boolean(false),
    	   "",
    	   "Syntax-Classes-Kernel-Operation-isOrdered-_defaultValue",  
    	   "uml:LiteralBoolean",  
    	   "");
    	    	
    	prop = factory.createProperty(clss, "isUnique", 
    	    "Syntax-Classes-Kernel-Operation-isUnique",
    	    "http://schema.omg.org/spec/UML/2.2/uml.xml#Boolean",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
     	factory.createDefault(prop,
    	   new Boolean(true),
    	   "",
    	   "Syntax-Classes-Kernel-Operation-isUnique-_defaultValue",  
    	   "uml:LiteralBoolean",  
    	   "");
    	    	
    	prop = factory.createProperty(clss, "lower", 
    	    "Syntax-Classes-Kernel-Operation-lower",
    	    "http://schema.omg.org/spec/UML/2.2/uml.xml#Integer",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "upper", 
    	    "Syntax-Classes-Kernel-Operation-upper",
    	    "http://schema.omg.org/spec/UML/2.2/uml.xml#UnlimitedNatural",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "class", 
    	    "Syntax-Classes-Kernel-Operation-class",
    	    "Syntax-Classes-Kernel-Class",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "redefinedOperation", 
    	    "Syntax-Classes-Kernel-Operation-redefinedOperation",
    	    "Syntax-Classes-Kernel-Operation",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "type", 
    	    "Syntax-Classes-Kernel-Operation-type",
    	    "Syntax-Classes-Kernel-Type",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "ownedParameter", 
    	    "Syntax-Classes-Kernel-Operation-ownedParameter",
    	    "Syntax-Classes-Kernel-Parameter",
    	    "Syntax-Classes-Kernel-BehavioralFeature-ownedParameter",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // Package
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-Package").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "packagedElement", 
    	    "Syntax-Classes-Kernel-Package-packagedElement",
    	    "Syntax-Classes-Kernel-PackageableElement",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "ownedType", 
    	    "Syntax-Classes-Kernel-Package-ownedType",
    	    "Syntax-Classes-Kernel-Type",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "nestedPackage", 
    	    "Syntax-Classes-Kernel-Package-nestedPackage",
    	    "Syntax-Classes-Kernel-Package",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "nestingPackage", 
    	    "Syntax-Classes-Kernel-Package-nestingPackage",
    	    "Syntax-Classes-Kernel-Package",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // Model
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-Model").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "viewpoint", 
    	    "_X0KpUDqBEd6eqrfLG8Mk-gv",
    	    "http://schema.omg.org/spec/UML/2.2/uml.xml#String",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // PackageableElement
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-PackageableElement").getDelegate();
    	
        // Parameter
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-Parameter").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "direction", 
    	    "Syntax-Classes-Kernel-Parameter-direction",
    	    "Syntax-Classes-Kernel-ParameterDirectionKind",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
     	factory.createDefault(prop,
    	   new String("in"),
    	   "",
    	   "_PwO01m_uEd2dGPXl6bl1XwZ",  
    	   "uml:LiteralString",  
    	   "");
    	    	
    	prop = factory.createProperty(clss, "operation", 
    	    "Syntax-Classes-Kernel-Parameter-operation",
    	    "Syntax-Classes-Kernel-Operation",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // PrimitiveType
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-PrimitiveType").getDelegate();
    	
        // Property
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-Property").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "isDerived", 
    	    "Syntax-Classes-Kernel-Property-isDerived",
    	    "http://schema.omg.org/spec/UML/2.2/uml.xml#Boolean",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
     	factory.createDefault(prop,
    	   new Boolean(false),
    	   "",
    	   "Syntax-Classes-Kernel-Property-isDerived-_defaultValue",  
    	   "uml:LiteralBoolean",  
    	   "");
    	    	
    	prop = factory.createProperty(clss, "isReadOnly", 
    	    "Syntax-Classes-Kernel-Property-isReadOnly",
    	    "http://schema.omg.org/spec/UML/2.2/uml.xml#Boolean",
    	    "Syntax-Classes-Kernel-StructuralFeature-isReadOnly",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
     	factory.createDefault(prop,
    	   new Boolean(false),
    	   "",
    	   "Syntax-Classes-Kernel-Property-isReadOnly-_defaultValue",  
    	   "uml:LiteralBoolean",  
    	   "");
    	    	
    	prop = factory.createProperty(clss, "isDerivedUnion", 
    	    "Syntax-Classes-Kernel-Property-isDerivedUnion",
    	    "http://schema.omg.org/spec/UML/2.2/uml.xml#Boolean",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
     	factory.createDefault(prop,
    	   new Boolean(false),
    	   "",
    	   "Syntax-Classes-Kernel-Property-isDerivedUnion-_defaultValue",  
    	   "uml:LiteralBoolean",  
    	   "");
    	    	
    	prop = factory.createProperty(clss, "aggregation", 
    	    "Syntax-Classes-Kernel-Property-aggregation",
    	    "Syntax-Classes-Kernel-AggregationKind",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
     	factory.createDefault(prop,
    	   new String(""),
    	   "Syntax-Classes-Kernel-AggregationKind-none",
    	   "Syntax-Classes-Kernel-Property-aggregation-none",  
    	   "uml:InstanceValue",  
    	   "Syntax-Classes-Kernel-AggregationKind");
    	    	
    	prop = factory.createProperty(clss, "isComposite", 
    	    "Syntax-Classes-Kernel-Property-isComposite",
    	    "http://schema.omg.org/spec/UML/2.2/uml.xml#Boolean",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
     	factory.createDefault(prop,
    	   new Boolean(false),
    	   "",
    	   "Syntax-Classes-Kernel-Property-isComposite-_defaultValue",  
    	   "uml:LiteralBoolean",  
    	   "");
    	    	
    	prop = factory.createProperty(clss, "owningAssociation", 
    	    "Syntax-Classes-Kernel-Property-owningAssociation",
    	    "Syntax-Classes-Kernel-Association",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "datatype", 
    	    "Syntax-Classes-Kernel-Property-datatype",
    	    "Syntax-Classes-Kernel-DataType",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "association", 
    	    "Syntax-Classes-Kernel-Property-association",
    	    "Syntax-Classes-Kernel-Association",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "class", 
    	    "Syntax-Classes-Kernel-Property-class",
    	    "Syntax-Classes-Kernel-Class",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "opposite", 
    	    "Syntax-Classes-Kernel-Property-opposite",
    	    "Syntax-Classes-Kernel-Property",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "defaultValue", 
    	    "Syntax-Classes-Kernel-Property-defaultValue",
    	    "Classes-Kernel-ValueSpecification",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // Slot
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-Slot").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "owningInstance", 
    	    "Syntax-Classes-Kernel-Slot-owningInstance",
    	    "Syntax-Classes-Kernel-InstanceSpecification",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "definingFeature", 
    	    "Syntax-Classes-Kernel-Slot-definingFeature",
    	    "Syntax-Classes-Kernel-StructuralFeature",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "value", 
    	    "Syntax-Classes-Kernel-Slot-value",
    	    "Syntax-Classes-Kernel-ValueSpecification",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // Type
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-Type").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "package", 
    	    "Syntax-Classes-Kernel-Type-package",
    	    "Syntax-Classes-Kernel-Package",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // TypedElement
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-TypedElement").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "type", 
    	    "Syntax-Classes-Kernel-TypedElement-type",
    	    "Syntax-Classes-Kernel-Type",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // Class
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-Class").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "isAbstract", 
    	    "Syntax-Classes-Kernel-Class-isAbstract",
    	    "http://schema.omg.org/spec/UML/2.2/uml.xml#Boolean",
    	    "Syntax-Classes-Kernel-Classifier-isAbstract",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
     	factory.createDefault(prop,
    	   new Boolean(false),
    	   "",
    	   "Syntax-Classes-Kernel-Class-isAbstract-_defaultValue",  
    	   "uml:LiteralBoolean",  
    	   "");
    	    	
    	prop = factory.createProperty(clss, "ownedOperation", 
    	    "Syntax-Classes-Kernel-Class-ownedOperation",
    	    "Syntax-Classes-Kernel-Operation",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "superClass", 
    	    "Syntax-Classes-Kernel-Class-superClass",
    	    "Syntax-Classes-Kernel-Class",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "isActive", 
    	    "Syntax-Classes-Kernel-Class-isActive",
    	    "http://schema.omg.org/spec/UML/2.2/uml.xml#Boolean",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
     	factory.createDefault(prop,
    	   new Boolean(false),
    	   "",
    	   "Syntax-Classes-Kernel-Class-isActive-_defaultValue",  
    	   "uml:LiteralBoolean",  
    	   "");
    	    	
    	prop = factory.createProperty(clss, "ownedReception", 
    	    "Syntax-Classes-Kernel-Class-ownedReception",
    	    "Syntax-CommonBehaviors-Communications-Reception",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "ownedAttribute", 
    	    "Syntax-Classes-Kernel-Class-ownedAttribute",
    	    "Syntax-Classes-Kernel-Property",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "nestedClassifier", 
    	    "Syntax-Classes-Kernel-Class-nestedClassifier",
    	    "Syntax-Classes-Kernel-Classifier",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // Classifier
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-Classifier").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "isAbstract", 
    	    "Syntax-Classes-Kernel-Classifier-isAbstract",
    	    "http://schema.omg.org/spec/UML/2.2/uml.xml#Boolean",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
     	factory.createDefault(prop,
    	   new Boolean(false),
    	   "",
    	   "Syntax-Classes-Kernel-Classifier-isAbstract-_defaultValue",  
    	   "uml:LiteralBoolean",  
    	   "");
    	    	
    	prop = factory.createProperty(clss, "generalization", 
    	    "Syntax-Classes-Kernel-Classifier-generalization",
    	    "Syntax-Classes-Kernel-Generalization",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "feature", 
    	    "Syntax-Classes-Kernel-Classifier-feature",
    	    "Syntax-Classes-Kernel-Feature",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "inheritedMember", 
    	    "Syntax-Classes-Kernel-Classifier-inheritedMember",
    	    "Syntax-Classes-Kernel-NamedElement",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "attribute", 
    	    "Syntax-Classes-Kernel-Classifier-attribute",
    	    "Syntax-Classes-Kernel-Property",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "general", 
    	    "Syntax-Classes-Kernel-Classifier-general",
    	    "Syntax-Classes-Kernel-Classifier",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "isFinalSpecialization", 
    	    "Syntax-Classes-Kernel-Classifier-isFinalSpecialization",
    	    "http://schema.omg.org/spec/UML/2.2/uml.xml#Boolean",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
     	factory.createDefault(prop,
    	   new Boolean(false),
    	   "",
    	   "Syntax-Classes-Kernel-Classifier-isFinalSpecialization-_defaultValue",  
    	   "uml:LiteralBoolean",  
    	   "");
    	
        // DataType
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-DataType").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "ownedAttribute", 
    	    "Syntax-Classes-Kernel-DataType-ownedAttribute",
    	    "Syntax-Classes-Kernel-Property",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // Element
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-Element").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "ownedElement", 
    	    "Syntax-Classes-Kernel-Element-ownedElement",
    	    "Syntax-Classes-Kernel-Element",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "owner", 
    	    "Syntax-Classes-Kernel-Element-owner",
    	    "Syntax-Classes-Kernel-Element",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "ownedComment", 
    	    "Syntax-Classes-Kernel-Element-ownedComment",
    	    "Syntax-Classes-Kernel-Comment",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // Enumeration
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-Enumeration").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "ownedLiteral", 
    	    "Syntax-Classes-Kernel-Enumeration-ownedLiteral",
    	    "Syntax-Classes-Kernel-EnumerationLiteral",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // EnumerationLiteral
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-EnumerationLiteral").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "enumeration", 
    	    "Syntax-Classes-Kernel-EnumerationLiteral-enumeration",
    	    "Syntax-Classes-Kernel-Enumeration",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // Feature
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-Feature").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "isStatic", 
    	    "Syntax-Classes-Kernel-Feature-isStatic",
    	    "http://schema.omg.org/spec/UML/2.2/uml.xml#Boolean",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
     	factory.createDefault(prop,
    	   new Boolean(false),
    	   "",
    	   "Syntax-Classes-Kernel-Feature-isStatic-_defaultValue",  
    	   "uml:LiteralBoolean",  
    	   "");
    	    	
    	prop = factory.createProperty(clss, "featuringClassifier", 
    	    "Syntax-Classes-Kernel-Feature-featuringClassifier",
    	    "Syntax-Classes-Kernel-Classifier",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // Generalization
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-Generalization").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "isSubstitutable", 
    	    "Syntax-Classes-Kernel-Generalization-isSubstitutable",
    	    "http://schema.omg.org/spec/UML/2.2/uml.xml#Boolean",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "specific", 
    	    "Syntax-Classes-Kernel-Generalization-specific",
    	    "Syntax-Classes-Kernel-Classifier",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "general", 
    	    "Syntax-Classes-Kernel-Generalization-general",
    	    "Syntax-Classes-Kernel-Classifier",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // Association
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-Association").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "isDerived", 
    	    "Syntax-Classes-Kernel-Association-isDerived",
    	    "http://schema.omg.org/spec/UML/2.2/uml.xml#Boolean",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
     	factory.createDefault(prop,
    	   new Boolean(false),
    	   "",
    	   "Syntax-Classes-Kernel-Association-isDerived-_defaultValue",  
    	   "uml:LiteralBoolean",  
    	   "");
    	    	
    	prop = factory.createProperty(clss, "ownedEnd", 
    	    "Syntax-Classes-Kernel-Association-ownedEnd",
    	    "Syntax-Classes-Kernel-Property",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "endType", 
    	    "Syntax-Classes-Kernel-Association-endType",
    	    "Syntax-Classes-Kernel-Type",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "memberEnd", 
    	    "Syntax-Classes-Kernel-Association-memberEnd",
    	    "Syntax-Classes-Kernel-Property",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "2");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "navigableOwnedEnd", 
    	    "Syntax-Classes-Kernel-Association-navigableOwnedEnd",
    	    "Syntax-Classes-Kernel-Property",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // Comment
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-Comment").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "annotatedElement", 
    	    "Syntax-Classes-Kernel-Comment-annotatedElement",
    	    "Syntax-Classes-Kernel-Element",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "body", 
    	    "Syntax-Classes-Kernel-Comment-body",
    	    "http://schema.omg.org/spec/UML/2.2/uml.xml#String",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // ElementImport
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-ElementImport").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "visibility", 
    	    "Syntax-Classes-Kernel-ElementImport-visibility",
    	    "Syntax-Classes-Kernel-VisibilityKind",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "alias", 
    	    "Syntax-Classes-Kernel-ElementImport-alias",
    	    "http://schema.omg.org/spec/UML/2.2/uml.xml#String",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "importedElement", 
    	    "Syntax-Classes-Kernel-ElementImport-importedElement",
    	    "Syntax-Classes-Kernel-PackageableElement",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "importingNamespace", 
    	    "Syntax-Classes-Kernel-ElementImport-importingNamespace",
    	    "Syntax-Classes-Kernel-Namespace",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // PackageImport
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-PackageImport").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "visibility", 
    	    "Syntax-Classes-Kernel-PackageImport-visibility",
    	    "Syntax-Classes-Kernel-VisibilityKind",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "importingNamespace", 
    	    "Syntax-Classes-Kernel-PackageImport-importingNamespace",
    	    "Syntax-Classes-Kernel-Namespace",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "importedPackage", 
    	    "Syntax-Classes-Kernel-PackageImport-importedPackage",
    	    "Syntax-Classes-Kernel-Package",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // ObjectFlow
    	clss  = (Class_)model.getElementById("Syntax-Activities-IntermediateActivities-ObjectFlow").getDelegate();
    	
        // ObjectNode
    	clss  = (Class_)model.getElementById("Syntax-Activities-IntermediateActivities-ObjectNode").getDelegate();
    	
        // MergeNode
    	clss  = (Class_)model.getElementById("Syntax-Activities-IntermediateActivities-MergeNode").getDelegate();
    	
        // JoinNode
    	clss  = (Class_)model.getElementById("Syntax-Activities-IntermediateActivities-JoinNode").getDelegate();
    	
        // InitialNode
    	clss  = (Class_)model.getElementById("Syntax-Activities-IntermediateActivities-InitialNode").getDelegate();
    	
        // FinalNode
    	clss  = (Class_)model.getElementById("Syntax-Activities-IntermediateActivities-FinalNode").getDelegate();
    	
        // ForkNode
    	clss  = (Class_)model.getElementById("Syntax-Activities-IntermediateActivities-ForkNode").getDelegate();
    	
        // ControlFlow
    	clss  = (Class_)model.getElementById("Syntax-Activities-IntermediateActivities-ControlFlow").getDelegate();
    	
        // ControlNode
    	clss  = (Class_)model.getElementById("Syntax-Activities-IntermediateActivities-ControlNode").getDelegate();
    	
        // DecisionNode
    	clss  = (Class_)model.getElementById("Syntax-Activities-IntermediateActivities-DecisionNode").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "decisionInput", 
    	    "Syntax-Activities-IntermediateActivities-DecisionNode-decisionInput",
    	    "Syntax-CommonBehaviors-BasicBehaviors-Behavior",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "decisionInputFlow", 
    	    "Syntax-Activities-IntermediateActivities-DecisionNode-decisionInputFlow",
    	    "Syntax-Activities-IntermediateActivities-ObjectFlow",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // ActivityFinalNode
    	clss  = (Class_)model.getElementById("Syntax-Activities-IntermediateActivities-ActivityFinalNode").getDelegate();
    	
        // ActivityNode
    	clss  = (Class_)model.getElementById("Syntax-Activities-IntermediateActivities-ActivityNode").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "inStructuredNode", 
    	    "Syntax-Activities-IntermediateActivities-ActivityNode-inStructuredNode",
    	    "Syntax-Activities-CompleteStructuredActivities-StructuredActivityNode",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "activity", 
    	    "Syntax-Activities-IntermediateActivities-ActivityNode-activity",
    	    "Syntax-Activities-IntermediateActivities-Activity",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "outgoing", 
    	    "Syntax-Activities-IntermediateActivities-ActivityNode-outgoing",
    	    "Syntax-Activities-IntermediateActivities-ActivityEdge",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "incoming", 
    	    "Syntax-Activities-IntermediateActivities-ActivityNode-incoming",
    	    "Syntax-Activities-IntermediateActivities-ActivityEdge",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // ActivityParameterNode
    	clss  = (Class_)model.getElementById("Syntax-Activities-IntermediateActivities-ActivityParameterNode").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "parameter", 
    	    "Syntax-Activities-IntermediateActivities-ActivityParameterNode-parameter",
    	    "Syntax-Classes-Kernel-Parameter",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // ActivityEdge
    	clss  = (Class_)model.getElementById("Syntax-Activities-IntermediateActivities-ActivityEdge").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "activity", 
    	    "Syntax-Activities-IntermediateActivities-ActivityEdge-activity",
    	    "Syntax-Activities-IntermediateActivities-Activity",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "source", 
    	    "Syntax-Activities-IntermediateActivities-ActivityEdge-source",
    	    "Syntax-Activities-IntermediateActivities-ActivityNode",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "target", 
    	    "Syntax-Activities-IntermediateActivities-ActivityEdge-target",
    	    "Syntax-Activities-IntermediateActivities-ActivityNode",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "guard", 
    	    "Syntax-Activities-IntermediateActivities-ActivityEdge-guard",
    	    "Syntax-Classes-Kernel-ValueSpecification",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "inStructuredNode", 
    	    "Syntax-Activities-IntermediateActivities-ActivityEdge-inStructuredNode",
    	    "Syntax-Activities-CompleteStructuredActivities-StructuredActivityNode",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // Activity
    	clss  = (Class_)model.getElementById("Syntax-Activities-IntermediateActivities-Activity").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "structuredNode", 
    	    "Syntax-Activities-IntermediateActivities-Activity-structuredNode",
    	    "Syntax-Activities-CompleteStructuredActivities-StructuredActivityNode",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "node", 
    	    "Syntax-Activities-IntermediateActivities-Activity-node",
    	    "Syntax-Activities-IntermediateActivities-ActivityNode",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "isReadOnly", 
    	    "Syntax-Activities-IntermediateActivities-Activity-isReadOnly",
    	    "http://schema.omg.org/spec/UML/2.2/uml.xml#Boolean",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
     	factory.createDefault(prop,
    	   new Boolean(false),
    	   "",
    	   "Syntax-Activities-IntermediateActivities-Activity-isReadOnly-_defaultValue",  
    	   "uml:LiteralBoolean",  
    	   "");
    	    	
    	prop = factory.createProperty(clss, "edge", 
    	    "Syntax-Activities-IntermediateActivities-Activity-edge",
    	    "Syntax-Activities-IntermediateActivities-ActivityEdge",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // StructuredActivityNode
    	clss  = (Class_)model.getElementById("Syntax-Activities-CompleteStructuredActivities-StructuredActivityNode").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "node", 
    	    "Syntax-Activities-CompleteStructuredActivities-StructuredActivityNode-node",
    	    "Syntax-Activities-IntermediateActivities-ActivityNode",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "activity", 
    	    "Syntax-Activities-CompleteStructuredActivities-StructuredActivityNode-activity",
    	    "Syntax-Activities-IntermediateActivities-Activity",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "mustIsolate", 
    	    "Syntax-Activities-CompleteStructuredActivities-StructuredActivityNode-mustIsolate",
    	    "http://schema.omg.org/spec/UML/2.2/uml.xml#Boolean",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
     	factory.createDefault(prop,
    	   new Boolean(false),
    	   "",
    	   "Syntax-Activities-CompleteStructuredActivities-StructuredActivityNode-mustIsolate-_defaultValue",  
    	   "uml:LiteralBoolean",  
    	   "");
    	    	
    	prop = factory.createProperty(clss, "edge", 
    	    "Syntax-Activities-CompleteStructuredActivities-StructuredActivityNode-edge",
    	    "Syntax-Activities-IntermediateActivities-ActivityEdge",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "structuredNodeOutput", 
    	    "Syntax-Activities-CompleteStructuredActivities-StructuredActivityNode-structuredNodeOutput",
    	    "Syntax-Actions-BasicActions-OutputPin",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "structuredNodeInput", 
    	    "Syntax-Activities-CompleteStructuredActivities-StructuredActivityNode-structuredNodeInput",
    	    "Syntax-Actions-BasicActions-InputPin",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // LoopNode
    	clss  = (Class_)model.getElementById("Syntax-Activities-CompleteStructuredActivities-LoopNode").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "isTestedFirst", 
    	    "Syntax-Activities-CompleteStructuredActivities-LoopNode-isTestedFirst",
    	    "http://schema.omg.org/spec/UML/2.2/uml.xml#Boolean",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
     	factory.createDefault(prop,
    	   new Boolean(false),
    	   "",
    	   "Syntax-Activities-CompleteStructuredActivities-LoopNode-isTestedFirst-_defaultValue",  
    	   "uml:LiteralBoolean",  
    	   "");
    	    	
    	prop = factory.createProperty(clss, "decider", 
    	    "Syntax-Activities-CompleteStructuredActivities-LoopNode-decider",
    	    "Syntax-Actions-BasicActions-OutputPin",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "test", 
    	    "Syntax-Activities-CompleteStructuredActivities-LoopNode-test",
    	    "Syntax-Activities-CompleteStructuredActivities-ExecutableNode",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "bodyOutput", 
    	    "Syntax-Activities-CompleteStructuredActivities-LoopNode-bodyOutput",
    	    "Syntax-Actions-BasicActions-OutputPin",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "loopVariableInput", 
    	    "Syntax-Activities-CompleteStructuredActivities-LoopNode-loopVariableInput",
    	    "Syntax-Actions-BasicActions-InputPin",
    	    "Syntax-Activities-CompleteStructuredActivities-StructuredActivityNode-structuredNodeInput",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "bodyPart", 
    	    "Syntax-Activities-CompleteStructuredActivities-LoopNode-bodyPart",
    	    "Syntax-Activities-CompleteStructuredActivities-ExecutableNode",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "result", 
    	    "Syntax-Activities-CompleteStructuredActivities-LoopNode-result",
    	    "Syntax-Actions-BasicActions-OutputPin",
    	    "Syntax-Activities-CompleteStructuredActivities-StructuredActivityNode-structuredNodeOutput",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "loopVariable", 
    	    "Syntax-Activities-CompleteStructuredActivities-LoopNode-loopVariable",
    	    "Syntax-Actions-BasicActions-OutputPin",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "setupPart", 
    	    "Syntax-Activities-CompleteStructuredActivities-LoopNode-setupPart",
    	    "Syntax-Activities-CompleteStructuredActivities-ExecutableNode",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // ExecutableNode
    	clss  = (Class_)model.getElementById("Syntax-Activities-CompleteStructuredActivities-ExecutableNode").getDelegate();
    	
        // Clause
    	clss  = (Class_)model.getElementById("Syntax-Activities-CompleteStructuredActivities-Clause").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "test", 
    	    "Syntax-Activities-CompleteStructuredActivities-Clause-test",
    	    "Syntax-Activities-CompleteStructuredActivities-ExecutableNode",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "body", 
    	    "Syntax-Activities-CompleteStructuredActivities-Clause-body",
    	    "Syntax-Activities-CompleteStructuredActivities-ExecutableNode",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "predecessorClause", 
    	    "Syntax-Activities-CompleteStructuredActivities-Clause-predecessorClause",
    	    "Syntax-Activities-CompleteStructuredActivities-Clause",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "successorClause", 
    	    "Syntax-Activities-CompleteStructuredActivities-Clause-successorClause",
    	    "Syntax-Activities-CompleteStructuredActivities-Clause",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "decider", 
    	    "Syntax-Activities-CompleteStructuredActivities-Clause-decider",
    	    "Syntax-Actions-BasicActions-OutputPin",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "bodyOutput", 
    	    "Syntax-Activities-CompleteStructuredActivities-Clause-bodyOutput",
    	    "Syntax-Actions-BasicActions-OutputPin",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // ConditionalNode
    	clss  = (Class_)model.getElementById("Syntax-Activities-CompleteStructuredActivities-ConditionalNode").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "isDeterminate", 
    	    "Syntax-Activities-CompleteStructuredActivities-ConditionalNode-isDeterminate",
    	    "http://schema.omg.org/spec/UML/2.2/uml.xml#Boolean",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
     	factory.createDefault(prop,
    	   new Boolean(false),
    	   "",
    	   "Syntax-Activities-CompleteStructuredActivities-ConditionalNode-isDeterminate-_defaultValue",  
    	   "uml:LiteralBoolean",  
    	   "");
    	    	
    	prop = factory.createProperty(clss, "isAssured", 
    	    "Syntax-Activities-CompleteStructuredActivities-ConditionalNode-isAssured",
    	    "http://schema.omg.org/spec/UML/2.2/uml.xml#Boolean",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
     	factory.createDefault(prop,
    	   new Boolean(false),
    	   "",
    	   "Syntax-Activities-CompleteStructuredActivities-ConditionalNode-isAssured-_defaultValue",  
    	   "uml:LiteralBoolean",  
    	   "");
    	    	
    	prop = factory.createProperty(clss, "clause", 
    	    "Syntax-Activities-CompleteStructuredActivities-ConditionalNode-clause",
    	    "Syntax-Activities-CompleteStructuredActivities-Clause",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "result", 
    	    "Syntax-Activities-CompleteStructuredActivities-ConditionalNode-result",
    	    "Syntax-Actions-BasicActions-OutputPin",
    	    "Syntax-Activities-CompleteStructuredActivities-StructuredActivityNode-structuredNodeOutput",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // ExpansionNode
    	clss  = (Class_)model.getElementById("Syntax-Activities-ExtraStructuredActivities-ExpansionNode").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "regionAsOutput", 
    	    "Syntax-Activities-ExtraStructuredActivities-ExpansionNode-regionAsOutput",
    	    "Syntax-Activities-ExtraStructuredActivities-ExpansionRegion",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "regionAsInput", 
    	    "Syntax-Activities-ExtraStructuredActivities-ExpansionNode-regionAsInput",
    	    "Syntax-Activities-ExtraStructuredActivities-ExpansionRegion",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // ExpansionRegion
    	clss  = (Class_)model.getElementById("Syntax-Activities-ExtraStructuredActivities-ExpansionRegion").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "mode", 
    	    "Syntax-Activities-ExtraStructuredActivities-ExpansionRegion-mode",
    	    "Syntax-Activities-ExtraStructuredActivities-ExpansionKind",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
     	factory.createDefault(prop,
    	   new String(""),
    	   "Syntax-Activities-ExtraStructuredActivities-ExpansionKind-iterative",
    	   "Syntax-Activities-ExtraStructuredActivities-ExpansionRegion-mode-iterative",  
    	   "uml:InstanceValue",  
    	   "Syntax-Activities-ExtraStructuredActivities-ExpansionKind");
    	    	
    	prop = factory.createProperty(clss, "outputElement", 
    	    "Syntax-Activities-ExtraStructuredActivities-ExpansionRegion-outputElement",
    	    "Syntax-Activities-ExtraStructuredActivities-ExpansionNode",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "inputElement", 
    	    "Syntax-Activities-ExtraStructuredActivities-ExpansionRegion-inputElement",
    	    "Syntax-Activities-ExtraStructuredActivities-ExpansionNode",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // SendSignalAction
    	clss  = (Class_)model.getElementById("Syntax-Actions-BasicActions-SendSignalAction").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "target", 
    	    "Syntax-Actions-BasicActions-SendSignalAction-target",
    	    "Syntax-Actions-BasicActions-InputPin",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "signal", 
    	    "Syntax-Actions-BasicActions-SendSignalAction-signal",
    	    "Syntax-CommonBehaviors-Communications-Signal",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // OutputPin
    	clss  = (Class_)model.getElementById("Syntax-Actions-BasicActions-OutputPin").getDelegate();
    	
        // Pin
    	clss  = (Class_)model.getElementById("Syntax-Actions-BasicActions-Pin").getDelegate();
    	
        // InputPin
    	clss  = (Class_)model.getElementById("Syntax-Actions-BasicActions-InputPin").getDelegate();
    	
        // InvocationAction
    	clss  = (Class_)model.getElementById("Syntax-Actions-BasicActions-InvocationAction").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "argument", 
    	    "Syntax-Actions-BasicActions-InvocationAction-argument",
    	    "Syntax-Actions-BasicActions-InputPin",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // CallAction
    	clss  = (Class_)model.getElementById("Syntax-Actions-BasicActions-CallAction").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "isSynchronous", 
    	    "Syntax-Actions-BasicActions-CallAction-isSynchronous",
    	    "http://schema.omg.org/spec/UML/2.2/uml.xml#Boolean",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
     	factory.createDefault(prop,
    	   new Boolean(true),
    	   "",
    	   "Syntax-Actions-BasicActions-CallAction-isSynchronous-_defaultValue",  
    	   "uml:LiteralBoolean",  
    	   "");
    	    	
    	prop = factory.createProperty(clss, "result", 
    	    "Syntax-Actions-BasicActions-CallAction-result",
    	    "Syntax-Actions-BasicActions-OutputPin",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // CallBehaviorAction
    	clss  = (Class_)model.getElementById("Syntax-Actions-BasicActions-CallBehaviorAction").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "behavior", 
    	    "Syntax-Actions-BasicActions-CallBehaviorAction-behavior",
    	    "Syntax-CommonBehaviors-BasicBehaviors-Behavior",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // CallOperationAction
    	clss  = (Class_)model.getElementById("Syntax-Actions-BasicActions-CallOperationAction").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "operation", 
    	    "Syntax-Actions-BasicActions-CallOperationAction-operation",
    	    "Syntax-Classes-Kernel-Operation",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "target", 
    	    "Syntax-Actions-BasicActions-CallOperationAction-target",
    	    "Syntax-Actions-BasicActions-InputPin",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // Action
    	clss  = (Class_)model.getElementById("Syntax-Actions-BasicActions-Action").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "output", 
    	    "Syntax-Actions-BasicActions-Action-output",
    	    "Syntax-Actions-BasicActions-OutputPin",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "context", 
    	    "Syntax-Actions-BasicActions-Action-context",
    	    "Syntax-Classes-Kernel-Classifier",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "input", 
    	    "Syntax-Actions-BasicActions-Action-input",
    	    "Syntax-Actions-BasicActions-InputPin",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "isLocallyReentrant", 
    	    "Syntax-Actions-BasicActions-Action-isLocallyReentrant",
    	    "http://schema.omg.org/spec/UML/2.2/uml.xml#Boolean",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
     	factory.createDefault(prop,
    	   new Boolean(false),
    	   "",
    	   "Syntax-Actions-BasicActions-Action-isLocallyReentrant-_defaultValue",  
    	   "uml:LiteralBoolean",  
    	   "");
    	
        // StructuralFeatureAction
    	clss  = (Class_)model.getElementById("Syntax-Actions-IntermediateActions-StructuralFeatureAction").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "structuralFeature", 
    	    "Syntax-Actions-IntermediateActions-StructuralFeatureAction-structuralFeature",
    	    "Syntax-Classes-Kernel-StructuralFeature",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "object", 
    	    "Syntax-Actions-IntermediateActions-StructuralFeatureAction-object",
    	    "Syntax-Actions-BasicActions-InputPin",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // TestIdentityAction
    	clss  = (Class_)model.getElementById("Syntax-Actions-IntermediateActions-TestIdentityAction").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "second", 
    	    "Syntax-Actions-IntermediateActions-TestIdentityAction-second",
    	    "Syntax-Actions-BasicActions-InputPin",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "result", 
    	    "Syntax-Actions-IntermediateActions-TestIdentityAction-result",
    	    "Syntax-Actions-BasicActions-OutputPin",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "first", 
    	    "Syntax-Actions-IntermediateActions-TestIdentityAction-first",
    	    "Syntax-Actions-BasicActions-InputPin",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // ValueSpecificationAction
    	clss  = (Class_)model.getElementById("Syntax-Actions-IntermediateActions-ValueSpecificationAction").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "value", 
    	    "Syntax-Actions-IntermediateActions-ValueSpecificationAction-value",
    	    "Syntax-Classes-Kernel-ValueSpecification",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "result", 
    	    "Syntax-Actions-IntermediateActions-ValueSpecificationAction-result",
    	    "Syntax-Actions-BasicActions-OutputPin",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // WriteLinkAction
    	clss  = (Class_)model.getElementById("Syntax-Actions-IntermediateActions-WriteLinkAction").getDelegate();
    	
        // WriteStructuralFeatureAction
    	clss  = (Class_)model.getElementById("Syntax-Actions-IntermediateActions-WriteStructuralFeatureAction").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "value", 
    	    "Syntax-Actions-IntermediateActions-WriteStructuralFeatureAction-value",
    	    "Syntax-Actions-BasicActions-InputPin",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "result", 
    	    "Syntax-Actions-IntermediateActions-WriteStructuralFeatureAction-result",
    	    "Syntax-Actions-BasicActions-OutputPin",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // RemoveStructuralFeatureValueAction
    	clss  = (Class_)model.getElementById("Syntax-Actions-IntermediateActions-RemoveStructuralFeatureValueAction").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "isRemoveDuplicates", 
    	    "Syntax-Actions-IntermediateActions-RemoveStructuralFeatureValueAction-isRemoveDuplicates",
    	    "http://schema.omg.org/spec/UML/2.2/uml.xml#Boolean",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
     	factory.createDefault(prop,
    	   new Boolean(false),
    	   "",
    	   "Syntax-Actions-IntermediateActions-RemoveStructuralFeatureValueAction-isRemoveDuplicates-_defaultValue",  
    	   "uml:LiteralBoolean",  
    	   "");
    	    	
    	prop = factory.createProperty(clss, "removeAt", 
    	    "Syntax-Actions-IntermediateActions-RemoveStructuralFeatureValueAction-removeAt",
    	    "Syntax-Actions-BasicActions-InputPin",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // ReadLinkAction
    	clss  = (Class_)model.getElementById("Syntax-Actions-IntermediateActions-ReadLinkAction").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "result", 
    	    "Syntax-Actions-IntermediateActions-ReadLinkAction-result",
    	    "Syntax-Actions-BasicActions-OutputPin",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // ReadSelfAction
    	clss  = (Class_)model.getElementById("Syntax-Actions-IntermediateActions-ReadSelfAction").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "result", 
    	    "Syntax-Actions-IntermediateActions-ReadSelfAction-result",
    	    "Syntax-Actions-BasicActions-OutputPin",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // ReadStructuralFeatureAction
    	clss  = (Class_)model.getElementById("Syntax-Actions-IntermediateActions-ReadStructuralFeatureAction").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "result", 
    	    "Syntax-Actions-IntermediateActions-ReadStructuralFeatureAction-result",
    	    "Syntax-Actions-BasicActions-OutputPin",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // LinkAction
    	clss  = (Class_)model.getElementById("Syntax-Actions-IntermediateActions-LinkAction").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "endData", 
    	    "Syntax-Actions-IntermediateActions-LinkAction-endData",
    	    "Syntax-Actions-IntermediateActions-LinkEndData",
    	    "Syntax-Actions-IntermediateActions-LinkAction-endData",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "2");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "inputValue", 
    	    "Syntax-Actions-IntermediateActions-LinkAction-inputValue",
    	    "Syntax-Actions-BasicActions-InputPin",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // LinkEndCreationData
    	clss  = (Class_)model.getElementById("Syntax-Actions-IntermediateActions-LinkEndCreationData").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "isReplaceAll", 
    	    "Syntax-Actions-IntermediateActions-LinkEndCreationData-isReplaceAll",
    	    "http://schema.omg.org/spec/UML/2.2/uml.xml#Boolean",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
     	factory.createDefault(prop,
    	   new Boolean(false),
    	   "",
    	   "Syntax-Actions-IntermediateActions-LinkEndCreationData-isReplaceAll-_defaultValue",  
    	   "uml:LiteralBoolean",  
    	   "");
    	    	
    	prop = factory.createProperty(clss, "insertAt", 
    	    "Syntax-Actions-IntermediateActions-LinkEndCreationData-insertAt",
    	    "Syntax-Actions-BasicActions-InputPin",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // LinkEndData
    	clss  = (Class_)model.getElementById("Syntax-Actions-IntermediateActions-LinkEndData").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "value", 
    	    "Syntax-Actions-IntermediateActions-LinkEndData-value",
    	    "Syntax-Actions-BasicActions-InputPin",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "end", 
    	    "Syntax-Actions-IntermediateActions-LinkEndData-end",
    	    "Syntax-Classes-Kernel-Property",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // LinkEndDestructionData
    	clss  = (Class_)model.getElementById("Syntax-Actions-IntermediateActions-LinkEndDestructionData").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "isDestroyDuplicates", 
    	    "Syntax-Actions-IntermediateActions-LinkEndDestructionData-isDestroyDuplicates",
    	    "http://schema.omg.org/spec/UML/2.2/uml.xml#Boolean",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
     	factory.createDefault(prop,
    	   new Boolean(false),
    	   "",
    	   "Syntax-Actions-IntermediateActions-LinkEndDestructionData-isDestroyDuplicates-_defaultValue",  
    	   "uml:LiteralBoolean",  
    	   "");
    	    	
    	prop = factory.createProperty(clss, "destroyAt", 
    	    "Syntax-Actions-IntermediateActions-LinkEndDestructionData-destroyAt",
    	    "Syntax-Actions-BasicActions-InputPin",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // ClearAssociationAction
    	clss  = (Class_)model.getElementById("Syntax-Actions-IntermediateActions-ClearAssociationAction").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "association", 
    	    "Syntax-Actions-IntermediateActions-ClearAssociationAction-association",
    	    "Syntax-Classes-Kernel-Association",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "object", 
    	    "Syntax-Actions-IntermediateActions-ClearAssociationAction-object",
    	    "Syntax-Actions-BasicActions-InputPin",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // ClearStructuralFeatureAction
    	clss  = (Class_)model.getElementById("Syntax-Actions-IntermediateActions-ClearStructuralFeatureAction").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "result", 
    	    "Syntax-Actions-IntermediateActions-ClearStructuralFeatureAction-result",
    	    "Syntax-Actions-BasicActions-OutputPin",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // CreateLinkAction
    	clss  = (Class_)model.getElementById("Syntax-Actions-IntermediateActions-CreateLinkAction").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "endData", 
    	    "Syntax-Actions-IntermediateActions-CreateLinkAction-endData",
    	    "Syntax-Actions-IntermediateActions-LinkEndCreationData",
    	    "Syntax-Actions-IntermediateActions-LinkAction-endData",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "2");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // CreateObjectAction
    	clss  = (Class_)model.getElementById("Syntax-Actions-IntermediateActions-CreateObjectAction").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "result", 
    	    "Syntax-Actions-IntermediateActions-CreateObjectAction-result",
    	    "Syntax-Actions-BasicActions-OutputPin",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "classifier", 
    	    "Syntax-Actions-IntermediateActions-CreateObjectAction-classifier",
    	    "Syntax-Classes-Kernel-Classifier",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // DestroyLinkAction
    	clss  = (Class_)model.getElementById("Syntax-Actions-IntermediateActions-DestroyLinkAction").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "endData", 
    	    "Syntax-Actions-IntermediateActions-DestroyLinkAction-endData",
    	    "Syntax-Actions-IntermediateActions-LinkEndDestructionData",
    	    "Syntax-Actions-IntermediateActions-LinkAction-endData",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "2");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // DestroyObjectAction
    	clss  = (Class_)model.getElementById("Syntax-Actions-IntermediateActions-DestroyObjectAction").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "isDestroyLinks", 
    	    "Syntax-Actions-IntermediateActions-DestroyObjectAction-isDestroyLinks",
    	    "http://schema.omg.org/spec/UML/2.2/uml.xml#Boolean",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
     	factory.createDefault(prop,
    	   new Boolean(false),
    	   "",
    	   "Syntax-Actions-IntermediateActions-DestroyObjectAction-isDestroyLinks-_defaultValue",  
    	   "uml:LiteralBoolean",  
    	   "");
    	    	
    	prop = factory.createProperty(clss, "isDestroyOwnedObjects", 
    	    "Syntax-Actions-IntermediateActions-DestroyObjectAction-isDestroyOwnedObjects",
    	    "http://schema.omg.org/spec/UML/2.2/uml.xml#Boolean",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
     	factory.createDefault(prop,
    	   new Boolean(false),
    	   "",
    	   "Syntax-Actions-IntermediateActions-DestroyObjectAction-isDestroyOwnedObjects-_defaultValue",  
    	   "uml:LiteralBoolean",  
    	   "");
    	    	
    	prop = factory.createProperty(clss, "target", 
    	    "Syntax-Actions-IntermediateActions-DestroyObjectAction-target",
    	    "Syntax-Actions-BasicActions-InputPin",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // AddStructuralFeatureValueAction
    	clss  = (Class_)model.getElementById("Syntax-Actions-IntermediateActions-AddStructuralFeatureValueAction").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "isReplaceAll", 
    	    "Syntax-Actions-IntermediateActions-AddStructuralFeatureValueAction-isReplaceAll",
    	    "http://schema.omg.org/spec/UML/2.2/uml.xml#Boolean",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
     	factory.createDefault(prop,
    	   new Boolean(false),
    	   "",
    	   "Syntax-Actions-IntermediateActions-AddStructuralFeatureValueAction-isReplaceAll-_defaultValue",  
    	   "uml:LiteralBoolean",  
    	   "");
    	    	
    	prop = factory.createProperty(clss, "insertAt", 
    	    "Syntax-Actions-IntermediateActions-AddStructuralFeatureValueAction-insertAt",
    	    "Syntax-Actions-BasicActions-InputPin",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // StartClassifierBehaviorAction
    	clss  = (Class_)model.getElementById("Syntax-Actions-CompleteActions-StartClassifierBehaviorAction").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "object", 
    	    "Syntax-Actions-CompleteActions-StartClassifierBehaviorAction-object",
    	    "Syntax-Actions-BasicActions-InputPin",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // StartObjectBehaviorAction
    	clss  = (Class_)model.getElementById("Syntax-Actions-CompleteActions-StartObjectBehaviorAction").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "object", 
    	    "Syntax-Actions-CompleteActions-StartObjectBehaviorAction-object",
    	    "Syntax-Actions-BasicActions-InputPin",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // ReduceAction
    	clss  = (Class_)model.getElementById("Syntax-Actions-CompleteActions-ReduceAction").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "reducer", 
    	    "Syntax-Actions-CompleteActions-ReduceAction-reducer",
    	    "Syntax-CommonBehaviors-BasicBehaviors-Behavior",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "result", 
    	    "Syntax-Actions-CompleteActions-ReduceAction-result",
    	    "Syntax-Actions-BasicActions-OutputPin",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "collection", 
    	    "Syntax-Actions-CompleteActions-ReduceAction-collection",
    	    "Syntax-Actions-BasicActions-InputPin",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "isOrdered", 
    	    "Syntax-Actions-CompleteActions-ReduceAction-isOrdered",
    	    "http://schema.omg.org/spec/UML/2.2/uml.xml#Boolean",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
     	factory.createDefault(prop,
    	   new Boolean(false),
    	   "",
    	   "Syntax-Actions-CompleteActions-ReduceAction-isOrdered-_defaultValue",  
    	   "uml:LiteralBoolean",  
    	   "");
    	
        // ReadExtentAction
    	clss  = (Class_)model.getElementById("Syntax-Actions-CompleteActions-ReadExtentAction").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "result", 
    	    "Syntax-Actions-CompleteActions-ReadExtentAction-result",
    	    "Syntax-Actions-BasicActions-OutputPin",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "classifier", 
    	    "Syntax-Actions-CompleteActions-ReadExtentAction-classifier",
    	    "Syntax-Classes-Kernel-Classifier",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // ReadIsClassifiedObjectAction
    	clss  = (Class_)model.getElementById("Syntax-Actions-CompleteActions-ReadIsClassifiedObjectAction").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "isDirect", 
    	    "Syntax-Actions-CompleteActions-ReadIsClassifiedObjectAction-isDirect",
    	    "http://schema.omg.org/spec/UML/2.2/uml.xml#Boolean",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
     	factory.createDefault(prop,
    	   new Boolean(false),
    	   "",
    	   "Syntax-Actions-CompleteActions-ReadIsClassifiedObjectAction-isDirect-_defaultValue",  
    	   "uml:LiteralBoolean",  
    	   "");
    	    	
    	prop = factory.createProperty(clss, "classifier", 
    	    "Syntax-Actions-CompleteActions-ReadIsClassifiedObjectAction-classifier",
    	    "Syntax-Classes-Kernel-Classifier",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "result", 
    	    "Syntax-Actions-CompleteActions-ReadIsClassifiedObjectAction-result",
    	    "Syntax-Actions-BasicActions-OutputPin",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "object", 
    	    "Syntax-Actions-CompleteActions-ReadIsClassifiedObjectAction-object",
    	    "Syntax-Actions-BasicActions-InputPin",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // ReclassifyObjectAction
    	clss  = (Class_)model.getElementById("Syntax-Actions-CompleteActions-ReclassifyObjectAction").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "isReplaceAll", 
    	    "Syntax-Actions-CompleteActions-ReclassifyObjectAction-isReplaceAll",
    	    "http://schema.omg.org/spec/UML/2.2/uml.xml#Boolean",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
     	factory.createDefault(prop,
    	   new Boolean(false),
    	   "",
    	   "Syntax-Actions-CompleteActions-ReclassifyObjectAction-isReplaceAll-_defaultValue",  
    	   "uml:LiteralBoolean",  
    	   "");
    	    	
    	prop = factory.createProperty(clss, "oldClassifier", 
    	    "Syntax-Actions-CompleteActions-ReclassifyObjectAction-oldClassifier",
    	    "Syntax-Classes-Kernel-Classifier",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "object", 
    	    "Syntax-Actions-CompleteActions-ReclassifyObjectAction-object",
    	    "Syntax-Actions-BasicActions-InputPin",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "newClassifier", 
    	    "Syntax-Actions-CompleteActions-ReclassifyObjectAction-newClassifier",
    	    "Syntax-Classes-Kernel-Classifier",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // AcceptEventAction
    	clss  = (Class_)model.getElementById("Syntax-Actions-CompleteActions-AcceptEventAction").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "isUnmarshall", 
    	    "Syntax-Actions-CompleteActions-AcceptEventAction-isUnmarshall",
    	    "http://schema.omg.org/spec/UML/2.2/uml.xml#Boolean",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
     	factory.createDefault(prop,
    	   new Boolean(false),
    	   "",
    	   "Syntax-Actions-CompleteActions-AcceptEventAction-isUnmarshall-_defaultValue",  
    	   "uml:LiteralBoolean",  
    	   "");
    	    	
    	prop = factory.createProperty(clss, "result", 
    	    "Syntax-Actions-CompleteActions-AcceptEventAction-result",
    	    "Syntax-Actions-BasicActions-OutputPin",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "trigger", 
    	    "Syntax-Actions-CompleteActions-AcceptEventAction-trigger",
    	    "Syntax-CommonBehaviors-Communications-Trigger",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
    }
    
    private void constructGeneralizations()
    {
        Class_ clss = null;
        
    
        // OpaqueBehavior
    	clss  = (Class_)model.getElementById("Syntax-CommonBehaviors-BasicBehaviors-OpaqueBehavior").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-CommonBehaviors-BasicBehaviors-Behavior");
    	
        // FunctionBehavior
    	clss  = (Class_)model.getElementById("Syntax-CommonBehaviors-BasicBehaviors-FunctionBehavior").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-CommonBehaviors-BasicBehaviors-OpaqueBehavior");
    	
        // BehavioredClassifier
    	clss  = (Class_)model.getElementById("Syntax-CommonBehaviors-BasicBehaviors-BehavioredClassifier").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Classes-Kernel-Classifier");
    	
        // Behavior
    	clss  = (Class_)model.getElementById("Syntax-CommonBehaviors-BasicBehaviors-Behavior").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Classes-Kernel-Class");
    	
        // Trigger
    	clss  = (Class_)model.getElementById("Syntax-CommonBehaviors-Communications-Trigger").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Classes-Kernel-NamedElement");
    	
        // Signal
    	clss  = (Class_)model.getElementById("Syntax-CommonBehaviors-Communications-Signal").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Classes-Kernel-Classifier");
    	
        // SignalEvent
    	clss  = (Class_)model.getElementById("Syntax-CommonBehaviors-Communications-SignalEvent").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-CommonBehaviors-Communications-MessageEvent");
    	
        // Reception
    	clss  = (Class_)model.getElementById("Syntax-CommonBehaviors-Communications-Reception").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Classes-Kernel-BehavioralFeature");
    	
        // MessageEvent
    	clss  = (Class_)model.getElementById("Syntax-CommonBehaviors-Communications-MessageEvent").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-CommonBehaviors-Communications-Event");
    	
        // Event
    	clss  = (Class_)model.getElementById("Syntax-CommonBehaviors-Communications-Event").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Classes-Kernel-PackageableElement");
    	
        // ValueSpecification
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-ValueSpecification").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Classes-Kernel-TypedElement");
    	
        // StructuralFeature
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-StructuralFeature").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Classes-Kernel-Feature");
    	
    	factory.createGeneralization(clss, "Syntax-Classes-Kernel-TypedElement");
    	
    	factory.createGeneralization(clss, "Syntax-Classes-Kernel-MultiplicityElement");
    	
        // RedefinableElement
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-RedefinableElement").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Classes-Kernel-NamedElement");
    	
        // BehavioralFeature
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-BehavioralFeature").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Classes-Kernel-Feature");
    	
        // InstanceSpecification
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-InstanceSpecification").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Classes-Kernel-NamedElement");
    	
        // InstanceValue
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-InstanceValue").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Classes-Kernel-ValueSpecification");
    	
        // LiteralBoolean
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-LiteralBoolean").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Classes-Kernel-LiteralSpecification");
    	
        // LiteralInteger
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-LiteralInteger").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Classes-Kernel-LiteralSpecification");
    	
        // LiteralNull
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-LiteralNull").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Classes-Kernel-LiteralSpecification");
    	
        // LiteralSpecification
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-LiteralSpecification").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Classes-Kernel-ValueSpecification");
    	
        // LiteralString
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-LiteralString").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Classes-Kernel-LiteralSpecification");
    	
        // LiteralUnlimitedNatural
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-LiteralUnlimitedNatural").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Classes-Kernel-LiteralSpecification");
    	
        // OpaqueExpression
    	clss  = (Class_)model.getElementById("fUML-Classes-Kernel-OpaqueExpression").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Classes-Kernel-ValueSpecification");
    	
        // MultiplicityElement
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-MultiplicityElement").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Classes-Kernel-Element");
    	
        // NamedElement
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-NamedElement").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Classes-Kernel-Element");
    	
        // Namespace
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-Namespace").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Classes-Kernel-NamedElement");
    	
        // Operation
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-Operation").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Classes-Kernel-BehavioralFeature");
    	
        // Package
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-Package").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Classes-Kernel-Namespace");
    	
    	factory.createGeneralization(clss, "Syntax-Classes-Kernel-PackageableElement");
    	
        // Model
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-Model").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Classes-Kernel-Package");
    	
        // PackageableElement
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-PackageableElement").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Classes-Kernel-NamedElement");
    	
        // Parameter
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-Parameter").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Classes-Kernel-TypedElement");
    	
    	factory.createGeneralization(clss, "Syntax-Classes-Kernel-MultiplicityElement");
    	
        // PrimitiveType
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-PrimitiveType").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Classes-Kernel-DataType");
    	
        // Property
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-Property").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Classes-Kernel-StructuralFeature");
    	
        // Slot
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-Slot").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Classes-Kernel-Element");
    	
        // Type
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-Type").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Classes-Kernel-PackageableElement");
    	
        // TypedElement
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-TypedElement").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Classes-Kernel-NamedElement");
    	
        // Class
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-Class").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-CommonBehaviors-BasicBehaviors-BehavioredClassifier");
    	
        // Classifier
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-Classifier").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Classes-Kernel-Type");
    	
    	factory.createGeneralization(clss, "Syntax-Classes-Kernel-Namespace");
    	
        // DataType
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-DataType").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Classes-Kernel-Classifier");
    	
        // Element
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-Element").getDelegate();
    	
        // Enumeration
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-Enumeration").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Classes-Kernel-DataType");
    	
        // EnumerationLiteral
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-EnumerationLiteral").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Classes-Kernel-InstanceSpecification");
    	
        // Feature
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-Feature").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Classes-Kernel-RedefinableElement");
    	
        // Generalization
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-Generalization").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Classes-Kernel-Element");
    	
        // Association
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-Association").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Classes-Kernel-Classifier");
    	
        // Comment
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-Comment").getDelegate();
    	
        // ElementImport
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-ElementImport").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Classes-Kernel-Element");
    	
        // PackageImport
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-PackageImport").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Classes-Kernel-Element");
    	
        // ObjectFlow
    	clss  = (Class_)model.getElementById("Syntax-Activities-IntermediateActivities-ObjectFlow").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Activities-IntermediateActivities-ActivityEdge");
    	
        // ObjectNode
    	clss  = (Class_)model.getElementById("Syntax-Activities-IntermediateActivities-ObjectNode").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Activities-IntermediateActivities-ActivityNode");
    	
    	factory.createGeneralization(clss, "Syntax-Classes-Kernel-TypedElement");
    	
        // MergeNode
    	clss  = (Class_)model.getElementById("Syntax-Activities-IntermediateActivities-MergeNode").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Activities-IntermediateActivities-ControlNode");
    	
        // JoinNode
    	clss  = (Class_)model.getElementById("Syntax-Activities-IntermediateActivities-JoinNode").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Activities-IntermediateActivities-ControlNode");
    	
        // InitialNode
    	clss  = (Class_)model.getElementById("Syntax-Activities-IntermediateActivities-InitialNode").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Activities-IntermediateActivities-ControlNode");
    	
        // FinalNode
    	clss  = (Class_)model.getElementById("Syntax-Activities-IntermediateActivities-FinalNode").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Activities-IntermediateActivities-ControlNode");
    	
        // ForkNode
    	clss  = (Class_)model.getElementById("Syntax-Activities-IntermediateActivities-ForkNode").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Activities-IntermediateActivities-ControlNode");
    	
        // ControlFlow
    	clss  = (Class_)model.getElementById("Syntax-Activities-IntermediateActivities-ControlFlow").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Activities-IntermediateActivities-ActivityEdge");
    	
        // ControlNode
    	clss  = (Class_)model.getElementById("Syntax-Activities-IntermediateActivities-ControlNode").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Activities-IntermediateActivities-ActivityNode");
    	
        // DecisionNode
    	clss  = (Class_)model.getElementById("Syntax-Activities-IntermediateActivities-DecisionNode").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Activities-IntermediateActivities-ControlNode");
    	
        // ActivityFinalNode
    	clss  = (Class_)model.getElementById("Syntax-Activities-IntermediateActivities-ActivityFinalNode").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Activities-IntermediateActivities-FinalNode");
    	
        // ActivityNode
    	clss  = (Class_)model.getElementById("Syntax-Activities-IntermediateActivities-ActivityNode").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Classes-Kernel-RedefinableElement");
    	
        // ActivityParameterNode
    	clss  = (Class_)model.getElementById("Syntax-Activities-IntermediateActivities-ActivityParameterNode").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Activities-IntermediateActivities-ObjectNode");
    	
        // ActivityEdge
    	clss  = (Class_)model.getElementById("Syntax-Activities-IntermediateActivities-ActivityEdge").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Classes-Kernel-RedefinableElement");
    	
        // Activity
    	clss  = (Class_)model.getElementById("Syntax-Activities-IntermediateActivities-Activity").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-CommonBehaviors-BasicBehaviors-Behavior");
    	
        // StructuredActivityNode
    	clss  = (Class_)model.getElementById("Syntax-Activities-CompleteStructuredActivities-StructuredActivityNode").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Actions-BasicActions-Action");
    	
        // LoopNode
    	clss  = (Class_)model.getElementById("Syntax-Activities-CompleteStructuredActivities-LoopNode").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Activities-CompleteStructuredActivities-StructuredActivityNode");
    	
        // ExecutableNode
    	clss  = (Class_)model.getElementById("Syntax-Activities-CompleteStructuredActivities-ExecutableNode").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Activities-IntermediateActivities-ActivityNode");
    	
        // Clause
    	clss  = (Class_)model.getElementById("Syntax-Activities-CompleteStructuredActivities-Clause").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Classes-Kernel-Element");
    	
        // ConditionalNode
    	clss  = (Class_)model.getElementById("Syntax-Activities-CompleteStructuredActivities-ConditionalNode").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Activities-CompleteStructuredActivities-StructuredActivityNode");
    	
        // ExpansionNode
    	clss  = (Class_)model.getElementById("Syntax-Activities-ExtraStructuredActivities-ExpansionNode").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Activities-IntermediateActivities-ObjectNode");
    	
        // ExpansionRegion
    	clss  = (Class_)model.getElementById("Syntax-Activities-ExtraStructuredActivities-ExpansionRegion").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Activities-CompleteStructuredActivities-StructuredActivityNode");
    	
        // SendSignalAction
    	clss  = (Class_)model.getElementById("Syntax-Actions-BasicActions-SendSignalAction").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Actions-BasicActions-InvocationAction");
    	
        // OutputPin
    	clss  = (Class_)model.getElementById("Syntax-Actions-BasicActions-OutputPin").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Actions-BasicActions-Pin");
    	
        // Pin
    	clss  = (Class_)model.getElementById("Syntax-Actions-BasicActions-Pin").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Activities-IntermediateActivities-ObjectNode");
    	
    	factory.createGeneralization(clss, "Syntax-Classes-Kernel-MultiplicityElement");
    	
        // InputPin
    	clss  = (Class_)model.getElementById("Syntax-Actions-BasicActions-InputPin").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Actions-BasicActions-Pin");
    	
        // InvocationAction
    	clss  = (Class_)model.getElementById("Syntax-Actions-BasicActions-InvocationAction").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Actions-BasicActions-Action");
    	
        // CallAction
    	clss  = (Class_)model.getElementById("Syntax-Actions-BasicActions-CallAction").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Actions-BasicActions-InvocationAction");
    	
        // CallBehaviorAction
    	clss  = (Class_)model.getElementById("Syntax-Actions-BasicActions-CallBehaviorAction").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Actions-BasicActions-CallAction");
    	
        // CallOperationAction
    	clss  = (Class_)model.getElementById("Syntax-Actions-BasicActions-CallOperationAction").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Actions-BasicActions-CallAction");
    	
        // Action
    	clss  = (Class_)model.getElementById("Syntax-Actions-BasicActions-Action").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Activities-CompleteStructuredActivities-ExecutableNode");
    	
        // StructuralFeatureAction
    	clss  = (Class_)model.getElementById("Syntax-Actions-IntermediateActions-StructuralFeatureAction").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Actions-BasicActions-Action");
    	
        // TestIdentityAction
    	clss  = (Class_)model.getElementById("Syntax-Actions-IntermediateActions-TestIdentityAction").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Actions-BasicActions-Action");
    	
        // ValueSpecificationAction
    	clss  = (Class_)model.getElementById("Syntax-Actions-IntermediateActions-ValueSpecificationAction").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Actions-BasicActions-Action");
    	
        // WriteLinkAction
    	clss  = (Class_)model.getElementById("Syntax-Actions-IntermediateActions-WriteLinkAction").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Actions-IntermediateActions-LinkAction");
    	
        // WriteStructuralFeatureAction
    	clss  = (Class_)model.getElementById("Syntax-Actions-IntermediateActions-WriteStructuralFeatureAction").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Actions-IntermediateActions-StructuralFeatureAction");
    	
        // RemoveStructuralFeatureValueAction
    	clss  = (Class_)model.getElementById("Syntax-Actions-IntermediateActions-RemoveStructuralFeatureValueAction").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Actions-IntermediateActions-WriteStructuralFeatureAction");
    	
        // ReadLinkAction
    	clss  = (Class_)model.getElementById("Syntax-Actions-IntermediateActions-ReadLinkAction").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Actions-IntermediateActions-LinkAction");
    	
        // ReadSelfAction
    	clss  = (Class_)model.getElementById("Syntax-Actions-IntermediateActions-ReadSelfAction").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Actions-BasicActions-Action");
    	
        // ReadStructuralFeatureAction
    	clss  = (Class_)model.getElementById("Syntax-Actions-IntermediateActions-ReadStructuralFeatureAction").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Actions-IntermediateActions-StructuralFeatureAction");
    	
        // LinkAction
    	clss  = (Class_)model.getElementById("Syntax-Actions-IntermediateActions-LinkAction").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Actions-BasicActions-Action");
    	
        // LinkEndCreationData
    	clss  = (Class_)model.getElementById("Syntax-Actions-IntermediateActions-LinkEndCreationData").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Actions-IntermediateActions-LinkEndData");
    	
        // LinkEndData
    	clss  = (Class_)model.getElementById("Syntax-Actions-IntermediateActions-LinkEndData").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Classes-Kernel-Element");
    	
        // LinkEndDestructionData
    	clss  = (Class_)model.getElementById("Syntax-Actions-IntermediateActions-LinkEndDestructionData").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Actions-IntermediateActions-LinkEndData");
    	
        // ClearAssociationAction
    	clss  = (Class_)model.getElementById("Syntax-Actions-IntermediateActions-ClearAssociationAction").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Actions-BasicActions-Action");
    	
        // ClearStructuralFeatureAction
    	clss  = (Class_)model.getElementById("Syntax-Actions-IntermediateActions-ClearStructuralFeatureAction").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Actions-IntermediateActions-StructuralFeatureAction");
    	
        // CreateLinkAction
    	clss  = (Class_)model.getElementById("Syntax-Actions-IntermediateActions-CreateLinkAction").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Actions-IntermediateActions-WriteLinkAction");
    	
        // CreateObjectAction
    	clss  = (Class_)model.getElementById("Syntax-Actions-IntermediateActions-CreateObjectAction").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Actions-BasicActions-Action");
    	
        // DestroyLinkAction
    	clss  = (Class_)model.getElementById("Syntax-Actions-IntermediateActions-DestroyLinkAction").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Actions-IntermediateActions-WriteLinkAction");
    	
        // DestroyObjectAction
    	clss  = (Class_)model.getElementById("Syntax-Actions-IntermediateActions-DestroyObjectAction").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Actions-BasicActions-Action");
    	
        // AddStructuralFeatureValueAction
    	clss  = (Class_)model.getElementById("Syntax-Actions-IntermediateActions-AddStructuralFeatureValueAction").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Actions-IntermediateActions-WriteStructuralFeatureAction");
    	
        // StartClassifierBehaviorAction
    	clss  = (Class_)model.getElementById("Syntax-Actions-CompleteActions-StartClassifierBehaviorAction").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Actions-BasicActions-Action");
    	
        // StartObjectBehaviorAction
    	clss  = (Class_)model.getElementById("Syntax-Actions-CompleteActions-StartObjectBehaviorAction").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Actions-BasicActions-CallAction");
    	
        // ReduceAction
    	clss  = (Class_)model.getElementById("Syntax-Actions-CompleteActions-ReduceAction").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Actions-BasicActions-Action");
    	
        // ReadExtentAction
    	clss  = (Class_)model.getElementById("Syntax-Actions-CompleteActions-ReadExtentAction").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Actions-BasicActions-Action");
    	
        // ReadIsClassifiedObjectAction
    	clss  = (Class_)model.getElementById("Syntax-Actions-CompleteActions-ReadIsClassifiedObjectAction").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Actions-BasicActions-Action");
    	
        // ReclassifyObjectAction
    	clss  = (Class_)model.getElementById("Syntax-Actions-CompleteActions-ReclassifyObjectAction").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Actions-BasicActions-Action");
    	
        // AcceptEventAction
    	clss  = (Class_)model.getElementById("Syntax-Actions-CompleteActions-AcceptEventAction").getDelegate();
    	
    	factory.createGeneralization(clss, "Syntax-Actions-BasicActions-Action");
    	
    }

    private void constructAssociations()
    {
        Package pkg = null;
        String packageId = null;
        Association assoc = null;
        Property prop = null;
        
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-CommonBehaviors-BasicBehaviors";   
                      
        // fUML.Syntax.CommonBehaviors.BasicBehaviors.A_context_behavior 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_context_behavior", "Syntax-CommonBehaviors-BasicBehaviors-A_context_behavior");
        mapping.mapAssociation(assoc, "fUML.Syntax.CommonBehaviors.BasicBehaviors", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "behavior", 
    	    "Syntax-CommonBehaviors-BasicBehaviors-A_context_behavior-behavior",
    	    "Syntax-CommonBehaviors-BasicBehaviors-Behavior",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-CommonBehaviors-BasicBehaviors-Behavior-context Syntax-CommonBehaviors-BasicBehaviors-A_context_behavior-behavior");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-CommonBehaviors-BasicBehaviors";   
                      
        // fUML.Syntax.CommonBehaviors.BasicBehaviors.A_behavior_ownedParameter 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_behavior_ownedParameter", "Syntax-CommonBehaviors-BasicBehaviors-A_behavior_ownedParameter");
        mapping.mapAssociation(assoc, "fUML.Syntax.CommonBehaviors.BasicBehaviors", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "behavior", 
    	    "Syntax-CommonBehaviors-BasicBehaviors-A_behavior_ownedParameter-behavior",
    	    "Syntax-CommonBehaviors-BasicBehaviors-Behavior",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-CommonBehaviors-BasicBehaviors-A_behavior_ownedParameter-behavior Syntax-CommonBehaviors-BasicBehaviors-Behavior-ownedParameter");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-CommonBehaviors-BasicBehaviors";   
                      
        // fUML.Syntax.CommonBehaviors.BasicBehaviors.A_behavioredClassifier_ownedBehavior 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_behavioredClassifier_ownedBehavior", "Syntax-CommonBehaviors-BasicBehaviors-A_behavioredClassifier_ownedBehavior");
        mapping.mapAssociation(assoc, "fUML.Syntax.CommonBehaviors.BasicBehaviors", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "behavioredClassifier", 
    	    "Syntax-CommonBehaviors-BasicBehaviors-A_behavioredClassifier_ownedBehavior-behavioredClassifier",
    	    "Syntax-CommonBehaviors-BasicBehaviors-BehavioredClassifier",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-CommonBehaviors-BasicBehaviors-A_behavioredClassifier_ownedBehavior-behavioredClassifier Syntax-CommonBehaviors-BasicBehaviors-BehavioredClassifier-ownedBehavior");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-CommonBehaviors-BasicBehaviors";   
                      
        // fUML.Syntax.CommonBehaviors.BasicBehaviors.A_classifierBehavior_behavioredClassifier 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_classifierBehavior_behavioredClassifier", "Syntax-CommonBehaviors-BasicBehaviors-A_classifierBehavior_behavioredClassifier");
        mapping.mapAssociation(assoc, "fUML.Syntax.CommonBehaviors.BasicBehaviors", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "behavioredClassifier", 
    	    "Syntax-CommonBehaviors-BasicBehaviors-A_classifierBehavior_behavioredClassifier-behavioredClassifier",
    	    "Syntax-CommonBehaviors-BasicBehaviors-BehavioredClassifier",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-CommonBehaviors-BasicBehaviors-BehavioredClassifier-classifierBehavior Syntax-CommonBehaviors-BasicBehaviors-A_classifierBehavior_behavioredClassifier-behavioredClassifier");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-CommonBehaviors-Communications";   
                      
        // fUML.Syntax.CommonBehaviors.Communications.A_reception_signal 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_reception_signal", "Syntax-CommonBehaviors-Communications-A_reception_signal");
        mapping.mapAssociation(assoc, "fUML.Syntax.CommonBehaviors.Communications", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "reception", 
    	    "Syntax-CommonBehaviors-Communications-A_reception_signal-reception",
    	    "Syntax-CommonBehaviors-Communications-Reception",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-CommonBehaviors-Communications-A_reception_signal-reception Syntax-CommonBehaviors-Communications-Reception-signal");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-CommonBehaviors-Communications";   
                      
        // fUML.Syntax.CommonBehaviors.Communications.A_signal_signalEvent 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_signal_signalEvent", "Syntax-CommonBehaviors-Communications-A_signal_signalEvent");
        mapping.mapAssociation(assoc, "fUML.Syntax.CommonBehaviors.Communications", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "signalEvent", 
    	    "Syntax-CommonBehaviors-Communications-A_signal_signalEvent-signalEvent",
    	    "Syntax-CommonBehaviors-Communications-SignalEvent",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-CommonBehaviors-Communications-SignalEvent-signal Syntax-CommonBehaviors-Communications-A_signal_signalEvent-signalEvent");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-CommonBehaviors-Communications";   
                      
        // fUML.Syntax.CommonBehaviors.Communications.A_ownedAttribute_owningSignal 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_ownedAttribute_owningSignal", "Syntax-CommonBehaviors-Communications-A_ownedAttribute_owningSignal");
        mapping.mapAssociation(assoc, "fUML.Syntax.CommonBehaviors.Communications", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "owningSignal", 
    	    "Syntax-CommonBehaviors-Communications-A_ownedAttribute_owningSignal-owningSignal",
    	    "Syntax-CommonBehaviors-Communications-Signal",
    	    "Syntax-Classes-Kernel-NamedElement-namespace Syntax-Classes-Kernel-Feature-featuringClassifier Syntax-Classes-Kernel-A_attribute_classifier-classifier",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-CommonBehaviors-Communications-Signal-ownedAttribute Syntax-CommonBehaviors-Communications-A_ownedAttribute_owningSignal-owningSignal");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-CommonBehaviors-Communications";   
                      
        // fUML.Syntax.CommonBehaviors.Communications.A_event_trigger 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_event_trigger", "Syntax-CommonBehaviors-Communications-A_event_trigger");
        mapping.mapAssociation(assoc, "fUML.Syntax.CommonBehaviors.Communications", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "trigger", 
    	    "Syntax-CommonBehaviors-Communications-A_event_trigger-trigger",
    	    "Syntax-CommonBehaviors-Communications-Trigger",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-CommonBehaviors-Communications-Trigger-event Syntax-CommonBehaviors-Communications-A_event_trigger-trigger");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.A_navigableOwnedEnd_association 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_navigableOwnedEnd_association", "Syntax-Classes-Kernel-A_navigableOwnedEnd_association");
        mapping.mapAssociation(assoc, "fUML.Syntax.Classes.Kernel", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "association", 
    	    "Syntax-Classes-Kernel-A_navigableOwnedEnd_association-association",
    	    "Syntax-Classes-Kernel-Association",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Classes-Kernel-Association-navigableOwnedEnd Syntax-Classes-Kernel-A_navigableOwnedEnd_association-association");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.A_endType_association 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_endType_association", "Syntax-Classes-Kernel-A_endType_association");
        mapping.mapAssociation(assoc, "fUML.Syntax.Classes.Kernel", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "association", 
    	    "Syntax-Classes-Kernel-A_endType_association-association",
    	    "Syntax-Classes-Kernel-Association",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Classes-Kernel-Association-endType Syntax-Classes-Kernel-A_endType_association-association");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.A_memberEnd_association 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_memberEnd_association", "Syntax-Classes-Kernel-A_memberEnd_association");
        mapping.mapAssociation(assoc, "fUML.Syntax.Classes.Kernel", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Syntax-Classes-Kernel-Association-memberEnd Syntax-Classes-Kernel-Property-association");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.A_ownedEnd_owningAssociation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_ownedEnd_owningAssociation", "Syntax-Classes-Kernel-A_ownedEnd_owningAssociation");
        mapping.mapAssociation(assoc, "fUML.Syntax.Classes.Kernel", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Syntax-Classes-Kernel-Association-ownedEnd Syntax-Classes-Kernel-Property-owningAssociation");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.A_general_generalization 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_general_generalization", "Syntax-Classes-Kernel-A_general_generalization");
        mapping.mapAssociation(assoc, "fUML.Syntax.Classes.Kernel", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "generalization", 
    	    "Syntax-Classes-Kernel-A_general_generalization-generalization",
    	    "Syntax-Classes-Kernel-Generalization",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Classes-Kernel-Generalization-general Syntax-Classes-Kernel-A_general_generalization-generalization");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.A_feature_featuringClassifier 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_feature_featuringClassifier", "Syntax-Classes-Kernel-A_feature_featuringClassifier");
        mapping.mapAssociation(assoc, "fUML.Syntax.Classes.Kernel", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Syntax-Classes-Kernel-Classifier-feature Syntax-Classes-Kernel-Feature-featuringClassifier");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.A_ownedLiteral_enumeration 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_ownedLiteral_enumeration", "Syntax-Classes-Kernel-A_ownedLiteral_enumeration");
        mapping.mapAssociation(assoc, "fUML.Syntax.Classes.Kernel", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Syntax-Classes-Kernel-Enumeration-ownedLiteral Syntax-Classes-Kernel-EnumerationLiteral-enumeration");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.A_ownedElement_owner 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_ownedElement_owner", "Syntax-Classes-Kernel-A_ownedElement_owner");
        mapping.mapAssociation(assoc, "fUML.Syntax.Classes.Kernel", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Syntax-Classes-Kernel-Element-ownedElement Syntax-Classes-Kernel-Element-owner");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.A_attribute_classifier 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_attribute_classifier", "Syntax-Classes-Kernel-A_attribute_classifier");
        mapping.mapAssociation(assoc, "fUML.Syntax.Classes.Kernel", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "classifier", 
    	    "Syntax-Classes-Kernel-A_attribute_classifier-classifier",
    	    "Syntax-Classes-Kernel-Classifier",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Classes-Kernel-Classifier-attribute Syntax-Classes-Kernel-A_attribute_classifier-classifier");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.A_generalization_specific 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_generalization_specific", "Syntax-Classes-Kernel-A_generalization_specific");
        mapping.mapAssociation(assoc, "fUML.Syntax.Classes.Kernel", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Syntax-Classes-Kernel-Classifier-generalization Syntax-Classes-Kernel-Generalization-specific");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.A_general_classifier 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_general_classifier", "Syntax-Classes-Kernel-A_general_classifier");
        mapping.mapAssociation(assoc, "fUML.Syntax.Classes.Kernel", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "classifier", 
    	    "Syntax-Classes-Kernel-A_general_classifier-classifier",
    	    "Syntax-Classes-Kernel-Classifier",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Classes-Kernel-Classifier-general Syntax-Classes-Kernel-A_general_classifier-classifier");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.A_inheritedMember_classifier 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_inheritedMember_classifier", "Syntax-Classes-Kernel-A_inheritedMember_classifier");
        mapping.mapAssociation(assoc, "fUML.Syntax.Classes.Kernel", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "classifier", 
    	    "Syntax-Classes-Kernel-A_inheritedMember_classifier-classifier",
    	    "Syntax-Classes-Kernel-Classifier",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Classes-Kernel-Classifier-inheritedMember Syntax-Classes-Kernel-A_inheritedMember_classifier-classifier");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.A_ownedAttribute_class 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_ownedAttribute_class", "Syntax-Classes-Kernel-A_ownedAttribute_class");
        mapping.mapAssociation(assoc, "fUML.Syntax.Classes.Kernel", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Syntax-Classes-Kernel-Class-ownedAttribute Syntax-Classes-Kernel-Property-class");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.A_ownedReception_class 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_ownedReception_class", "Syntax-Classes-Kernel-A_ownedReception_class");
        mapping.mapAssociation(assoc, "fUML.Syntax.Classes.Kernel", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "class", 
    	    "Syntax-Classes-Kernel-A_ownedReception_class-class",
    	    "Syntax-Classes-Kernel-Class",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Classes-Kernel-Class-ownedReception Syntax-Classes-Kernel-A_ownedReception_class-class");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.A_superClass_class 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_superClass_class", "Syntax-Classes-Kernel-A_superClass_class");
        mapping.mapAssociation(assoc, "fUML.Syntax.Classes.Kernel", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "class", 
    	    "Syntax-Classes-Kernel-A_superClass_class-class",
    	    "Syntax-Classes-Kernel-Class",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Classes-Kernel-Class-superClass Syntax-Classes-Kernel-A_superClass_class-class");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.A_type_typedElement 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_type_typedElement", "Syntax-Classes-Kernel-A_type_typedElement");
        mapping.mapAssociation(assoc, "fUML.Syntax.Classes.Kernel", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "typedElement", 
    	    "Syntax-Classes-Kernel-A_type_typedElement-typedElement",
    	    "Syntax-Classes-Kernel-TypedElement",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Classes-Kernel-TypedElement-type Syntax-Classes-Kernel-A_type_typedElement-typedElement");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.A_value_owningSlot 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_value_owningSlot", "Syntax-Classes-Kernel-A_value_owningSlot");
        mapping.mapAssociation(assoc, "fUML.Syntax.Classes.Kernel", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "owningSlot", 
    	    "Syntax-Classes-Kernel-A_value_owningSlot-owningSlot",
    	    "Syntax-Classes-Kernel-Slot",
    	    "Syntax-Classes-Kernel-Element-ownedElement",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Classes-Kernel-Slot-value Syntax-Classes-Kernel-A_value_owningSlot-owningSlot");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.A_definingFeature_slot 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_definingFeature_slot", "Syntax-Classes-Kernel-A_definingFeature_slot");
        mapping.mapAssociation(assoc, "fUML.Syntax.Classes.Kernel", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "slot", 
    	    "Syntax-Classes-Kernel-A_definingFeature_slot-slot",
    	    "Syntax-Classes-Kernel-Slot",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Classes-Kernel-Slot-definingFeature Syntax-Classes-Kernel-A_definingFeature_slot-slot");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.A_opposite_property 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_opposite_property", "Syntax-Classes-Kernel-A_opposite_property");
        mapping.mapAssociation(assoc, "fUML.Syntax.Classes.Kernel", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "property", 
    	    "Syntax-Classes-Kernel-A_opposite_property-property",
    	    "Syntax-Classes-Kernel-Property",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Classes-Kernel-Property-opposite Syntax-Classes-Kernel-A_opposite_property-property");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.A_ownedAttribute_datatype 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_ownedAttribute_datatype", "Syntax-Classes-Kernel-A_ownedAttribute_datatype");
        mapping.mapAssociation(assoc, "fUML.Syntax.Classes.Kernel", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Syntax-Classes-Kernel-DataType-ownedAttribute Syntax-Classes-Kernel-Property-datatype");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.A_ownedParameter_operation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_ownedParameter_operation", "Syntax-Classes-Kernel-A_ownedParameter_operation");
        mapping.mapAssociation(assoc, "fUML.Syntax.Classes.Kernel", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Syntax-Classes-Kernel-Operation-ownedParameter Syntax-Classes-Kernel-Parameter-operation");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.A_packageElement_owningPackage 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_packageElement_owningPackage", "Syntax-Classes-Kernel-A_packageElement_owningPackage");
        mapping.mapAssociation(assoc, "fUML.Syntax.Classes.Kernel", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "owningPackage", 
    	    "Syntax-Classes-Kernel-A_packageElement_owningPackage-owningPackage",
    	    "Syntax-Classes-Kernel-Package",
    	    "Syntax-Classes-Kernel-NamedElement-namespace",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Classes-Kernel-Package-packagedElement Syntax-Classes-Kernel-A_packageElement_owningPackage-owningPackage");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.A_ownedOperation_class 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_ownedOperation_class", "Syntax-Classes-Kernel-A_ownedOperation_class");
        mapping.mapAssociation(assoc, "fUML.Syntax.Classes.Kernel", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Syntax-Classes-Kernel-Class-ownedOperation Syntax-Classes-Kernel-Operation-class");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.A_redefinedOperation_operation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_redefinedOperation_operation", "Syntax-Classes-Kernel-A_redefinedOperation_operation");
        mapping.mapAssociation(assoc, "fUML.Syntax.Classes.Kernel", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "operation", 
    	    "Syntax-Classes-Kernel-A_redefinedOperation_operation-operation",
    	    "Syntax-Classes-Kernel-Operation",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Classes-Kernel-Operation-redefinedOperation Syntax-Classes-Kernel-A_redefinedOperation_operation-operation");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.A_type_operation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_type_operation", "Syntax-Classes-Kernel-A_type_operation");
        mapping.mapAssociation(assoc, "fUML.Syntax.Classes.Kernel", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "operation", 
    	    "Syntax-Classes-Kernel-A_type_operation-operation",
    	    "Syntax-Classes-Kernel-Operation",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Classes-Kernel-Operation-type Syntax-Classes-Kernel-A_type_operation-operation");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.A_member_namespace 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_member_namespace", "Syntax-Classes-Kernel-A_member_namespace");
        mapping.mapAssociation(assoc, "fUML.Syntax.Classes.Kernel", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "namespace", 
    	    "Syntax-Classes-Kernel-A_member_namespace-namespace",
    	    "Syntax-Classes-Kernel-Namespace",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Classes-Kernel-Namespace-member Syntax-Classes-Kernel-A_member_namespace-namespace");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.A_ownedMember_namespace 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_ownedMember_namespace", "Syntax-Classes-Kernel-A_ownedMember_namespace");
        mapping.mapAssociation(assoc, "fUML.Syntax.Classes.Kernel", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Syntax-Classes-Kernel-Namespace-ownedMember Syntax-Classes-Kernel-NamedElement-namespace");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.A_lowerValue_owningLower 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_lowerValue_owningLower", "Syntax-Classes-Kernel-A_lowerValue_owningLower");
        mapping.mapAssociation(assoc, "fUML.Syntax.Classes.Kernel", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "owningLower", 
    	    "Syntax-Classes-Kernel-A_lowerValue_owningLower-owningLower",
    	    "Syntax-Classes-Kernel-MultiplicityElement",
    	    "Syntax-Classes-Kernel-Element-owner",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Classes-Kernel-MultiplicityElement-lowerValue Syntax-Classes-Kernel-A_lowerValue_owningLower-owningLower");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.A_upperValue_owningUpper 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_upperValue_owningUpper", "Syntax-Classes-Kernel-A_upperValue_owningUpper");
        mapping.mapAssociation(assoc, "fUML.Syntax.Classes.Kernel", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "owningUpper", 
    	    "Syntax-Classes-Kernel-A_upperValue_owningUpper-owningUpper",
    	    "Syntax-Classes-Kernel-MultiplicityElement",
    	    "Syntax-Classes-Kernel-Element-owner",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Classes-Kernel-MultiplicityElement-upperValue Syntax-Classes-Kernel-A_upperValue_owningUpper-owningUpper");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.A_instance_instanceValue 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_instance_instanceValue", "Syntax-Classes-Kernel-A_instance_instanceValue");
        mapping.mapAssociation(assoc, "fUML.Syntax.Classes.Kernel", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "instanceValue", 
    	    "Syntax-Classes-Kernel-A_instance_instanceValue-instanceValue",
    	    "Syntax-Classes-Kernel-InstanceValue",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Classes-Kernel-InstanceValue-instance Syntax-Classes-Kernel-A_instance_instanceValue-instanceValue");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.A_slot_owningInstance 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_slot_owningInstance", "Syntax-Classes-Kernel-A_slot_owningInstance");
        mapping.mapAssociation(assoc, "fUML.Syntax.Classes.Kernel", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Syntax-Classes-Kernel-InstanceSpecification-slot Syntax-Classes-Kernel-Slot-owningInstance");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.A_classifier_instanceSpecification 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_classifier_instanceSpecification", "Syntax-Classes-Kernel-A_classifier_instanceSpecification");
        mapping.mapAssociation(assoc, "fUML.Syntax.Classes.Kernel", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "instanceSpecification", 
    	    "Syntax-Classes-Kernel-A_classifier_instanceSpecification-instanceSpecification",
    	    "Syntax-Classes-Kernel-InstanceSpecification",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Classes-Kernel-InstanceSpecification-classifier Syntax-Classes-Kernel-A_classifier_instanceSpecification-instanceSpecification");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.A_method_specification 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_method_specification", "Syntax-Classes-Kernel-A_method_specification");
        mapping.mapAssociation(assoc, "fUML.Syntax.Classes.Kernel", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Syntax-Classes-Kernel-BehavioralFeature-method Syntax-CommonBehaviors-BasicBehaviors-Behavior-specification");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.A_ownedParameter_ownerFormalParam 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_ownedParameter_ownerFormalParam", "Syntax-Classes-Kernel-A_ownedParameter_ownerFormalParam");
        mapping.mapAssociation(assoc, "fUML.Syntax.Classes.Kernel", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "ownerFormalParam", 
    	    "Syntax-Classes-Kernel-A_ownedParameter_ownerFormalParam-ownerFormalParam",
    	    "Syntax-Classes-Kernel-BehavioralFeature",
    	    "Syntax-Classes-Kernel-NamedElement-namespace",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Classes-Kernel-BehavioralFeature-ownedParameter Syntax-Classes-Kernel-A_ownedParameter_ownerFormalParam-ownerFormalParam");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.A_redefinitionContext_redefinableElement 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_redefinitionContext_redefinableElement", "Syntax-Classes-Kernel-A_redefinitionContext_redefinableElement");
        mapping.mapAssociation(assoc, "fUML.Syntax.Classes.Kernel", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "redefinableElement", 
    	    "Syntax-Classes-Kernel-A_redefinitionContext_redefinableElement-redefinableElement",
    	    "Syntax-Classes-Kernel-RedefinableElement",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Classes-Kernel-RedefinableElement-redefinitionContext Syntax-Classes-Kernel-A_redefinitionContext_redefinableElement-redefinableElement");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.A_redefinedElement_redefinableElement 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_redefinedElement_redefinableElement", "Syntax-Classes-Kernel-A_redefinedElement_redefinableElement");
        mapping.mapAssociation(assoc, "fUML.Syntax.Classes.Kernel", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "redefinableElement", 
    	    "Syntax-Classes-Kernel-A_redefinedElement_redefinableElement-redefinableElement",
    	    "Syntax-Classes-Kernel-RedefinableElement",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Classes-Kernel-RedefinableElement-redefinedElement Syntax-Classes-Kernel-A_redefinedElement_redefinableElement-redefinableElement");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.A_ownedComment_owningElement 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_ownedComment_owningElement", "Syntax-Classes-Kernel-A_ownedComment_owningElement");
        mapping.mapAssociation(assoc, "fUML.Syntax.Classes.Kernel", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "owningElement", 
    	    "Syntax-Classes-Kernel-A_ownedComment_owningElement-owningElement",
    	    "Syntax-Classes-Kernel-Element",
    	    "Syntax-Classes-Kernel-Element-owner",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Classes-Kernel-Element-ownedComment Syntax-Classes-Kernel-A_ownedComment_owningElement-owningElement");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.A_annotatedElement_comment 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_annotatedElement_comment", "Syntax-Classes-Kernel-A_annotatedElement_comment");
        mapping.mapAssociation(assoc, "fUML.Syntax.Classes.Kernel", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "comment", 
    	    "Syntax-Classes-Kernel-A_annotatedElement_comment-comment",
    	    "Syntax-Classes-Kernel-Comment",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Classes-Kernel-Comment-annotatedElement Syntax-Classes-Kernel-A_annotatedElement_comment-comment");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.A_elementImport_importingNamespace 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_elementImport_importingNamespace", "Syntax-Classes-Kernel-A_elementImport_importingNamespace");
        mapping.mapAssociation(assoc, "fUML.Syntax.Classes.Kernel", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Syntax-Classes-Kernel-Namespace-elementImport Syntax-Classes-Kernel-ElementImport-importingNamespace");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.A_importedElement_elementImport 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_importedElement_elementImport", "Syntax-Classes-Kernel-A_importedElement_elementImport");
        mapping.mapAssociation(assoc, "fUML.Syntax.Classes.Kernel", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "elementImport", 
    	    "Syntax-Classes-Kernel-A_importedElement_elementImport-elementImport",
    	    "Syntax-Classes-Kernel-ElementImport",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Classes-Kernel-ElementImport-importedElement Syntax-Classes-Kernel-A_importedElement_elementImport-elementImport");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.A_packageImport_importingNamespace 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_packageImport_importingNamespace", "Syntax-Classes-Kernel-A_packageImport_importingNamespace");
        mapping.mapAssociation(assoc, "fUML.Syntax.Classes.Kernel", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Syntax-Classes-Kernel-Namespace-packageImport Syntax-Classes-Kernel-PackageImport-importingNamespace");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.A_importedPackage_packageImport 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_importedPackage_packageImport", "Syntax-Classes-Kernel-A_importedPackage_packageImport");
        mapping.mapAssociation(assoc, "fUML.Syntax.Classes.Kernel", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "packageImport", 
    	    "Syntax-Classes-Kernel-A_importedPackage_packageImport-packageImport",
    	    "Syntax-Classes-Kernel-PackageImport",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Classes-Kernel-PackageImport-importedPackage Syntax-Classes-Kernel-A_importedPackage_packageImport-packageImport");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.A_nestedClassifier_class 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_nestedClassifier_class", "Syntax-Classes-Kernel-A_nestedClassifier_class");
        mapping.mapAssociation(assoc, "fUML.Syntax.Classes.Kernel", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "class", 
    	    "Syntax-Classes-Kernel-A_nestedClassifier_class-class",
    	    "Syntax-Classes-Kernel-Class",
    	    "Syntax-Classes-Kernel-NamedElement-namespace",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Classes-Kernel-Class-nestedClassifier Syntax-Classes-Kernel-A_nestedClassifier_class-class");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.A_type_package 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_type_package", "Syntax-Classes-Kernel-A_type_package");
        mapping.mapAssociation(assoc, "fUML.Syntax.Classes.Kernel", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Syntax-Classes-Kernel-Package-ownedType Syntax-Classes-Kernel-Type-package");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.A_nestedPackage_nestingPackage 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_nestedPackage_nestingPackage", "Syntax-Classes-Kernel-A_nestedPackage_nestingPackage");
        mapping.mapAssociation(assoc, "fUML.Syntax.Classes.Kernel", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Syntax-Classes-Kernel-Package-nestedPackage Syntax-Classes-Kernel-Package-nestingPackage");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // fUML.Syntax.Classes.Kernel.A_importedMember_namespace 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_importedMember_namespace", "Syntax-Classes-Kernel-A_importedMember_namespace");
        mapping.mapAssociation(assoc, "fUML.Syntax.Classes.Kernel", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "namespace", 
    	    "Syntax-Classes-Kernel-A_importedMember_namespace-namespace",
    	    "Syntax-Classes-Kernel-Namespace",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Classes-Kernel-Namespace-importedMember Syntax-Classes-Kernel-A_importedMember_namespace-namespace");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-IntermediateActivities";   
                      
        // fUML.Syntax.Activities.IntermediateActivities.A_activity_edge 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_activity_edge", "Syntax-Activities-IntermediateActivities-A_activity_edge");
        mapping.mapAssociation(assoc, "fUML.Syntax.Activities.IntermediateActivities", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Syntax-Activities-IntermediateActivities-ActivityEdge-activity Syntax-Activities-IntermediateActivities-Activity-edge");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-IntermediateActivities";   
                      
        // fUML.Syntax.Activities.IntermediateActivities.A_activityEdge_guard 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_activityEdge_guard", "Syntax-Activities-IntermediateActivities-A_activityEdge_guard");
        mapping.mapAssociation(assoc, "fUML.Syntax.Activities.IntermediateActivities", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "activityEdge", 
    	    "Syntax-Activities-IntermediateActivities-A_activityEdge_guard-activityEdge",
    	    "Syntax-Activities-IntermediateActivities-ActivityEdge",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Activities-IntermediateActivities-A_activityEdge_guard-activityEdge Syntax-Activities-IntermediateActivities-ActivityEdge-guard");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-IntermediateActivities";   
                      
        // fUML.Syntax.Activities.IntermediateActivities.A_parameter_activityParameterNode 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_parameter_activityParameterNode", "Syntax-Activities-IntermediateActivities-A_parameter_activityParameterNode");
        mapping.mapAssociation(assoc, "fUML.Syntax.Activities.IntermediateActivities", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "activityParameterNode", 
    	    "Syntax-Activities-IntermediateActivities-A_parameter_activityParameterNode-activityParameterNode",
    	    "Syntax-Activities-IntermediateActivities-ActivityParameterNode",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Activities-IntermediateActivities-ActivityParameterNode-parameter Syntax-Activities-IntermediateActivities-A_parameter_activityParameterNode-activityParameterNode");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-IntermediateActivities";   
                      
        // fUML.Syntax.Activities.IntermediateActivities.A_outgoing_source 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_outgoing_source", "Syntax-Activities-IntermediateActivities-A_outgoing_source");
        mapping.mapAssociation(assoc, "fUML.Syntax.Activities.IntermediateActivities", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Syntax-Activities-IntermediateActivities-ActivityNode-outgoing Syntax-Activities-IntermediateActivities-ActivityEdge-source");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-IntermediateActivities";   
                      
        // fUML.Syntax.Activities.IntermediateActivities.A_node_activity 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_node_activity", "Syntax-Activities-IntermediateActivities-A_node_activity");
        mapping.mapAssociation(assoc, "fUML.Syntax.Activities.IntermediateActivities", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Syntax-Activities-IntermediateActivities-Activity-node Syntax-Activities-IntermediateActivities-ActivityNode-activity");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-IntermediateActivities";   
                      
        // fUML.Syntax.Activities.IntermediateActivities.A_target_incoming 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_target_incoming", "Syntax-Activities-IntermediateActivities-A_target_incoming");
        mapping.mapAssociation(assoc, "fUML.Syntax.Activities.IntermediateActivities", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Syntax-Activities-IntermediateActivities-ActivityEdge-target Syntax-Activities-IntermediateActivities-ActivityNode-incoming");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-IntermediateActivities";   
                      
        // fUML.Syntax.Activities.IntermediateActivities.A_decisionNode_decisionInputFlow 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_decisionNode_decisionInputFlow", "Syntax-Activities-IntermediateActivities-A_decisionNode_decisionInputFlow");
        mapping.mapAssociation(assoc, "fUML.Syntax.Activities.IntermediateActivities", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "decisionNode", 
    	    "Syntax-Activities-IntermediateActivities-A_decisionNode_decisionInputFlow-decisionNode",
    	    "Syntax-Activities-IntermediateActivities-DecisionNode",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Activities-IntermediateActivities-A_decisionNode_decisionInputFlow-decisionNode Syntax-Activities-IntermediateActivities-DecisionNode-decisionInputFlow");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-IntermediateActivities";   
                      
        // fUML.Syntax.Activities.IntermediateActivities.A_decisionInput_decisionNode 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_decisionInput_decisionNode", "Syntax-Activities-IntermediateActivities-A_decisionInput_decisionNode");
        mapping.mapAssociation(assoc, "fUML.Syntax.Activities.IntermediateActivities", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "decisionNode", 
    	    "Syntax-Activities-IntermediateActivities-A_decisionInput_decisionNode-decisionNode",
    	    "Syntax-Activities-IntermediateActivities-DecisionNode",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Activities-IntermediateActivities-DecisionNode-decisionInput Syntax-Activities-IntermediateActivities-A_decisionInput_decisionNode-decisionNode");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-CompleteStructuredActivities";   
                      
        // fUML.Syntax.Activities.CompleteStructuredActivities.A_result_conditionalNode 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_result_conditionalNode", "Syntax-Activities-CompleteStructuredActivities-A_result_conditionalNode");
        mapping.mapAssociation(assoc, "fUML.Syntax.Activities.CompleteStructuredActivities", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "conditionalNode", 
    	    "Syntax-Activities-CompleteStructuredActivities-A_result_conditionalNode-conditionalNode",
    	    "Syntax-Activities-CompleteStructuredActivities-ConditionalNode",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Activities-CompleteStructuredActivities-ConditionalNode-result Syntax-Activities-CompleteStructuredActivities-A_result_conditionalNode-conditionalNode");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-CompleteStructuredActivities";   
                      
        // fUML.Syntax.Activities.CompleteStructuredActivities.A_clause_conditionalNode 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_clause_conditionalNode", "Syntax-Activities-CompleteStructuredActivities-A_clause_conditionalNode");
        mapping.mapAssociation(assoc, "fUML.Syntax.Activities.CompleteStructuredActivities", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "conditionalNode", 
    	    "Syntax-Activities-CompleteStructuredActivities-A_clause_conditionalNode-conditionalNode",
    	    "Syntax-Activities-CompleteStructuredActivities-ConditionalNode",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Activities-CompleteStructuredActivities-ConditionalNode-clause Syntax-Activities-CompleteStructuredActivities-A_clause_conditionalNode-conditionalNode");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-CompleteStructuredActivities";   
                      
        // fUML.Syntax.Activities.CompleteStructuredActivities.A_clause_test 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_clause_test", "Syntax-Activities-CompleteStructuredActivities-A_clause_test");
        mapping.mapAssociation(assoc, "fUML.Syntax.Activities.CompleteStructuredActivities", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "clause", 
    	    "Syntax-Activities-CompleteStructuredActivities-A_clause_test-clause",
    	    "Syntax-Activities-CompleteStructuredActivities-Clause",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Activities-CompleteStructuredActivities-A_clause_test-clause Syntax-Activities-CompleteStructuredActivities-Clause-test");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-CompleteStructuredActivities";   
                      
        // fUML.Syntax.Activities.CompleteStructuredActivities.A_predecessorClause_successorClause 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_predecessorClause_successorClause", "Syntax-Activities-CompleteStructuredActivities-A_predecessorClause_successorClause");
        mapping.mapAssociation(assoc, "fUML.Syntax.Activities.CompleteStructuredActivities", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Syntax-Activities-CompleteStructuredActivities-Clause-predecessorClause Syntax-Activities-CompleteStructuredActivities-Clause-successorClause");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-CompleteStructuredActivities";   
                      
        // fUML.Syntax.Activities.CompleteStructuredActivities.A_bodyOutput_clause 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_bodyOutput_clause", "Syntax-Activities-CompleteStructuredActivities-A_bodyOutput_clause");
        mapping.mapAssociation(assoc, "fUML.Syntax.Activities.CompleteStructuredActivities", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "clause", 
    	    "Syntax-Activities-CompleteStructuredActivities-A_bodyOutput_clause-clause",
    	    "Syntax-Activities-CompleteStructuredActivities-Clause",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Activities-CompleteStructuredActivities-Clause-bodyOutput Syntax-Activities-CompleteStructuredActivities-A_bodyOutput_clause-clause");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-CompleteStructuredActivities";   
                      
        // fUML.Syntax.Activities.CompleteStructuredActivities.A_clause_body 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_clause_body", "Syntax-Activities-CompleteStructuredActivities-A_clause_body");
        mapping.mapAssociation(assoc, "fUML.Syntax.Activities.CompleteStructuredActivities", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "clause", 
    	    "Syntax-Activities-CompleteStructuredActivities-A_clause_body-clause",
    	    "Syntax-Activities-CompleteStructuredActivities-Clause",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Activities-CompleteStructuredActivities-A_clause_body-clause Syntax-Activities-CompleteStructuredActivities-Clause-body");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-CompleteStructuredActivities";   
                      
        // fUML.Syntax.Activities.CompleteStructuredActivities.A_decider_clause 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_decider_clause", "Syntax-Activities-CompleteStructuredActivities-A_decider_clause");
        mapping.mapAssociation(assoc, "fUML.Syntax.Activities.CompleteStructuredActivities", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "clause", 
    	    "Syntax-Activities-CompleteStructuredActivities-A_decider_clause-clause",
    	    "Syntax-Activities-CompleteStructuredActivities-Clause",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Activities-CompleteStructuredActivities-Clause-decider Syntax-Activities-CompleteStructuredActivities-A_decider_clause-clause");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-CompleteStructuredActivities";   
                      
        // fUML.Syntax.Activities.CompleteStructuredActivities.A_test_loopNode 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_test_loopNode", "Syntax-Activities-CompleteStructuredActivities-A_test_loopNode");
        mapping.mapAssociation(assoc, "fUML.Syntax.Activities.CompleteStructuredActivities", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "loopNode", 
    	    "Syntax-Activities-CompleteStructuredActivities-A_test_loopNode-loopNode",
    	    "Syntax-Activities-CompleteStructuredActivities-LoopNode",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Activities-CompleteStructuredActivities-LoopNode-test Syntax-Activities-CompleteStructuredActivities-A_test_loopNode-loopNode");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-CompleteStructuredActivities";   
                      
        // fUML.Syntax.Activities.CompleteStructuredActivities.A_loopVariableInput_loopNode 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_loopVariableInput_loopNode", "Syntax-Activities-CompleteStructuredActivities-A_loopVariableInput_loopNode");
        mapping.mapAssociation(assoc, "fUML.Syntax.Activities.CompleteStructuredActivities", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "loopNode", 
    	    "Syntax-Activities-CompleteStructuredActivities-A_loopVariableInput_loopNode-loopNode",
    	    "Syntax-Activities-CompleteStructuredActivities-LoopNode",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Activities-CompleteStructuredActivities-LoopNode-loopVariableInput Syntax-Activities-CompleteStructuredActivities-A_loopVariableInput_loopNode-loopNode");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-CompleteStructuredActivities";   
                      
        // fUML.Syntax.Activities.CompleteStructuredActivities.A_bodyOutput_loopNode 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_bodyOutput_loopNode", "Syntax-Activities-CompleteStructuredActivities-A_bodyOutput_loopNode");
        mapping.mapAssociation(assoc, "fUML.Syntax.Activities.CompleteStructuredActivities", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "loopNode", 
    	    "Syntax-Activities-CompleteStructuredActivities-A_bodyOutput_loopNode-loopNode",
    	    "Syntax-Activities-CompleteStructuredActivities-LoopNode",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Activities-CompleteStructuredActivities-LoopNode-bodyOutput Syntax-Activities-CompleteStructuredActivities-A_bodyOutput_loopNode-loopNode");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-CompleteStructuredActivities";   
                      
        // fUML.Syntax.Activities.CompleteStructuredActivities.A_decider_loopNode 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_decider_loopNode", "Syntax-Activities-CompleteStructuredActivities-A_decider_loopNode");
        mapping.mapAssociation(assoc, "fUML.Syntax.Activities.CompleteStructuredActivities", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "loopNode", 
    	    "Syntax-Activities-CompleteStructuredActivities-A_decider_loopNode-loopNode",
    	    "Syntax-Activities-CompleteStructuredActivities-LoopNode",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Activities-CompleteStructuredActivities-LoopNode-decider Syntax-Activities-CompleteStructuredActivities-A_decider_loopNode-loopNode");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-CompleteStructuredActivities";   
                      
        // fUML.Syntax.Activities.CompleteStructuredActivities.A_node_inStructuredNode 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_node_inStructuredNode", "Syntax-Activities-CompleteStructuredActivities-A_node_inStructuredNode");
        mapping.mapAssociation(assoc, "fUML.Syntax.Activities.CompleteStructuredActivities", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Syntax-Activities-CompleteStructuredActivities-StructuredActivityNode-node Syntax-Activities-IntermediateActivities-ActivityNode-inStructuredNode");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-CompleteStructuredActivities";   
                      
        // fUML.Syntax.Activities.CompleteStructuredActivities.A_edge_inStructuredNode 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_edge_inStructuredNode", "Syntax-Activities-CompleteStructuredActivities-A_edge_inStructuredNode");
        mapping.mapAssociation(assoc, "fUML.Syntax.Activities.CompleteStructuredActivities", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Syntax-Activities-CompleteStructuredActivities-StructuredActivityNode-edge Syntax-Activities-IntermediateActivities-ActivityEdge-inStructuredNode");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-CompleteStructuredActivities";   
                      
        // fUML.Syntax.Activities.CompleteStructuredActivities.A_activity_structuredNode 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_activity_structuredNode", "Syntax-Activities-CompleteStructuredActivities-A_activity_structuredNode");
        mapping.mapAssociation(assoc, "fUML.Syntax.Activities.CompleteStructuredActivities", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Syntax-Activities-CompleteStructuredActivities-StructuredActivityNode-activity Syntax-Activities-IntermediateActivities-Activity-structuredNode");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-CompleteStructuredActivities";   
                      
        // fUML.Syntax.Activities.CompleteStructuredActivities.A_loopNode_bodyPart 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_loopNode_bodyPart", "Syntax-Activities-CompleteStructuredActivities-A_loopNode_bodyPart");
        mapping.mapAssociation(assoc, "fUML.Syntax.Activities.CompleteStructuredActivities", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "loopNode", 
    	    "Syntax-Activities-CompleteStructuredActivities-A_loopNode_bodyPart-loopNode",
    	    "Syntax-Activities-CompleteStructuredActivities-LoopNode",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Activities-CompleteStructuredActivities-A_loopNode_bodyPart-loopNode Syntax-Activities-CompleteStructuredActivities-LoopNode-bodyPart");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-CompleteStructuredActivities";   
                      
        // fUML.Syntax.Activities.CompleteStructuredActivities.A_result_loopNode 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_result_loopNode", "Syntax-Activities-CompleteStructuredActivities-A_result_loopNode");
        mapping.mapAssociation(assoc, "fUML.Syntax.Activities.CompleteStructuredActivities", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "loopNode", 
    	    "Syntax-Activities-CompleteStructuredActivities-A_result_loopNode-loopNode",
    	    "Syntax-Activities-CompleteStructuredActivities-LoopNode",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Activities-CompleteStructuredActivities-LoopNode-result Syntax-Activities-CompleteStructuredActivities-A_result_loopNode-loopNode");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-CompleteStructuredActivities";   
                      
        // fUML.Syntax.Activities.CompleteStructuredActivities.A_loopVariable_loopNode 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_loopVariable_loopNode", "Syntax-Activities-CompleteStructuredActivities-A_loopVariable_loopNode");
        mapping.mapAssociation(assoc, "fUML.Syntax.Activities.CompleteStructuredActivities", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "loopNode", 
    	    "Syntax-Activities-CompleteStructuredActivities-A_loopVariable_loopNode-loopNode",
    	    "Syntax-Activities-CompleteStructuredActivities-LoopNode",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Activities-CompleteStructuredActivities-LoopNode-loopVariable Syntax-Activities-CompleteStructuredActivities-A_loopVariable_loopNode-loopNode");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-CompleteStructuredActivities";   
                      
        // fUML.Syntax.Activities.CompleteStructuredActivities.A_loopNode_setupPart 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_loopNode_setupPart", "Syntax-Activities-CompleteStructuredActivities-A_loopNode_setupPart");
        mapping.mapAssociation(assoc, "fUML.Syntax.Activities.CompleteStructuredActivities", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "loopNode", 
    	    "Syntax-Activities-CompleteStructuredActivities-A_loopNode_setupPart-loopNode",
    	    "Syntax-Activities-CompleteStructuredActivities-LoopNode",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Activities-CompleteStructuredActivities-A_loopNode_setupPart-loopNode Syntax-Activities-CompleteStructuredActivities-LoopNode-setupPart");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-CompleteStructuredActivities";   
                      
        // fUML.Syntax.Activities.CompleteStructuredActivities.A_structuredNodeOutput_structuredActivityNode 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_structuredNodeOutput_structuredActivityNode", "Syntax-Activities-CompleteStructuredActivities-A_structuredNodeOutput_structuredActivityNode");
        mapping.mapAssociation(assoc, "fUML.Syntax.Activities.CompleteStructuredActivities", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "structuredActivityNode", 
    	    "Syntax-Activities-CompleteStructuredActivities-A_structuredNodeOutput_structuredActivityNode-structuredActivityNode",
    	    "Syntax-Activities-CompleteStructuredActivities-StructuredActivityNode",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Activities-CompleteStructuredActivities-StructuredActivityNode-structuredNodeOutput Syntax-Activities-CompleteStructuredActivities-A_structuredNodeOutput_structuredActivityNode-structuredActivityNode");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-CompleteStructuredActivities";   
                      
        // fUML.Syntax.Activities.CompleteStructuredActivities.A_structuredNodeInput_structuredActivityNode 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_structuredNodeInput_structuredActivityNode", "Syntax-Activities-CompleteStructuredActivities-A_structuredNodeInput_structuredActivityNode");
        mapping.mapAssociation(assoc, "fUML.Syntax.Activities.CompleteStructuredActivities", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "structuredActivityNode", 
    	    "Syntax-Activities-CompleteStructuredActivities-A_structuredNodeInput_structuredActivityNode-structuredActivityNode",
    	    "Syntax-Activities-CompleteStructuredActivities-StructuredActivityNode",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Activities-CompleteStructuredActivities-StructuredActivityNode-structuredNodeInput Syntax-Activities-CompleteStructuredActivities-A_structuredNodeInput_structuredActivityNode-structuredActivityNode");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-ExtraStructuredActivities";   
                      
        // fUML.Syntax.Activities.ExtraStructuredActivities.A_outputElement_regionAsOutput 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_outputElement_regionAsOutput", "Syntax-Activities-ExtraStructuredActivities-A_outputElement_regionAsOutput");
        mapping.mapAssociation(assoc, "fUML.Syntax.Activities.ExtraStructuredActivities", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Syntax-Activities-ExtraStructuredActivities-ExpansionRegion-outputElement Syntax-Activities-ExtraStructuredActivities-ExpansionNode-regionAsOutput");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-ExtraStructuredActivities";   
                      
        // fUML.Syntax.Activities.ExtraStructuredActivities.A_inputElement_regionAsInput 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_inputElement_regionAsInput", "Syntax-Activities-ExtraStructuredActivities-A_inputElement_regionAsInput");
        mapping.mapAssociation(assoc, "fUML.Syntax.Activities.ExtraStructuredActivities", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Syntax-Activities-ExtraStructuredActivities-ExpansionRegion-inputElement Syntax-Activities-ExtraStructuredActivities-ExpansionNode-regionAsInput");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-BasicActions";   
                      
        // fUML.Syntax.Actions.BasicActions.A_action_output 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_action_output", "Syntax-Actions-BasicActions-A_action_output");
        mapping.mapAssociation(assoc, "fUML.Syntax.Actions.BasicActions", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "action", 
    	    "Syntax-Actions-BasicActions-A_action_output-action",
    	    "Syntax-Actions-BasicActions-Action",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Actions-BasicActions-A_action_output-action Syntax-Actions-BasicActions-Action-output");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-BasicActions";   
                      
        // fUML.Syntax.Actions.BasicActions.A_context_action 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_context_action", "Syntax-Actions-BasicActions-A_context_action");
        mapping.mapAssociation(assoc, "fUML.Syntax.Actions.BasicActions", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "action", 
    	    "Syntax-Actions-BasicActions-A_context_action-action",
    	    "Syntax-Actions-BasicActions-Action",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Actions-BasicActions-Action-context Syntax-Actions-BasicActions-A_context_action-action");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-BasicActions";   
                      
        // fUML.Syntax.Actions.BasicActions.A_operation_callOperationAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_operation_callOperationAction", "Syntax-Actions-BasicActions-A_operation_callOperationAction");
        mapping.mapAssociation(assoc, "fUML.Syntax.Actions.BasicActions", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "callOperationAction", 
    	    "Syntax-Actions-BasicActions-A_operation_callOperationAction-callOperationAction",
    	    "Syntax-Actions-BasicActions-CallOperationAction",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Actions-BasicActions-CallOperationAction-operation Syntax-Actions-BasicActions-A_operation_callOperationAction-callOperationAction");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-BasicActions";   
                      
        // fUML.Syntax.Actions.BasicActions.A_target_callOperationAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_target_callOperationAction", "Syntax-Actions-BasicActions-A_target_callOperationAction");
        mapping.mapAssociation(assoc, "fUML.Syntax.Actions.BasicActions", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "callOperationAction", 
    	    "Syntax-Actions-BasicActions-A_target_callOperationAction-callOperationAction",
    	    "Syntax-Actions-BasicActions-CallOperationAction",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Actions-BasicActions-CallOperationAction-target Syntax-Actions-BasicActions-A_target_callOperationAction-callOperationAction");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-BasicActions";   
                      
        // fUML.Syntax.Actions.BasicActions.A_behavior_callBehaviorAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_behavior_callBehaviorAction", "Syntax-Actions-BasicActions-A_behavior_callBehaviorAction");
        mapping.mapAssociation(assoc, "fUML.Syntax.Actions.BasicActions", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "callBehaviorAction", 
    	    "Syntax-Actions-BasicActions-A_behavior_callBehaviorAction-callBehaviorAction",
    	    "Syntax-Actions-BasicActions-CallBehaviorAction",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Actions-BasicActions-CallBehaviorAction-behavior Syntax-Actions-BasicActions-A_behavior_callBehaviorAction-callBehaviorAction");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-BasicActions";   
                      
        // fUML.Syntax.Actions.BasicActions.A_result_callAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_result_callAction", "Syntax-Actions-BasicActions-A_result_callAction");
        mapping.mapAssociation(assoc, "fUML.Syntax.Actions.BasicActions", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "callAction", 
    	    "Syntax-Actions-BasicActions-A_result_callAction-callAction",
    	    "Syntax-Actions-BasicActions-CallAction",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Actions-BasicActions-CallAction-result Syntax-Actions-BasicActions-A_result_callAction-callAction");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-BasicActions";   
                      
        // fUML.Syntax.Actions.BasicActions.A_signal_sendSignalAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_signal_sendSignalAction", "Syntax-Actions-BasicActions-A_signal_sendSignalAction");
        mapping.mapAssociation(assoc, "fUML.Syntax.Actions.BasicActions", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "sendSignalAction", 
    	    "Syntax-Actions-BasicActions-A_signal_sendSignalAction-sendSignalAction",
    	    "Syntax-Actions-BasicActions-SendSignalAction",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Actions-BasicActions-SendSignalAction-signal Syntax-Actions-BasicActions-A_signal_sendSignalAction-sendSignalAction");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-BasicActions";   
                      
        // fUML.Syntax.Actions.BasicActions.A_target_sendSignalAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_target_sendSignalAction", "Syntax-Actions-BasicActions-A_target_sendSignalAction");
        mapping.mapAssociation(assoc, "fUML.Syntax.Actions.BasicActions", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "sendSignalAction", 
    	    "Syntax-Actions-BasicActions-A_target_sendSignalAction-sendSignalAction",
    	    "Syntax-Actions-BasicActions-SendSignalAction",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Actions-BasicActions-SendSignalAction-target Syntax-Actions-BasicActions-A_target_sendSignalAction-sendSignalAction");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-BasicActions";   
                      
        // fUML.Syntax.Actions.BasicActions.A_action_input 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_action_input", "Syntax-Actions-BasicActions-A_action_input");
        mapping.mapAssociation(assoc, "fUML.Syntax.Actions.BasicActions", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "action", 
    	    "Syntax-Actions-BasicActions-A_action_input-action",
    	    "Syntax-Actions-BasicActions-Action",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Actions-BasicActions-A_action_input-action Syntax-Actions-BasicActions-Action-input");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-BasicActions";   
                      
        // fUML.Syntax.Actions.BasicActions.A_argument_invocationAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_argument_invocationAction", "Syntax-Actions-BasicActions-A_argument_invocationAction");
        mapping.mapAssociation(assoc, "fUML.Syntax.Actions.BasicActions", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "invocationAction", 
    	    "Syntax-Actions-BasicActions-A_argument_invocationAction-invocationAction",
    	    "Syntax-Actions-BasicActions-InvocationAction",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Actions-BasicActions-InvocationAction-argument Syntax-Actions-BasicActions-A_argument_invocationAction-invocationAction");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // fUML.Syntax.Actions.IntermediateActions.A_target_destroyObjectAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_target_destroyObjectAction", "Syntax-Actions-IntermediateActions-A_target_destroyObjectAction");
        mapping.mapAssociation(assoc, "fUML.Syntax.Actions.IntermediateActions", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "destroyObjectAction", 
    	    "Syntax-Actions-IntermediateActions-A_target_destroyObjectAction-destroyObjectAction",
    	    "Syntax-Actions-IntermediateActions-DestroyObjectAction",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Actions-IntermediateActions-DestroyObjectAction-target Syntax-Actions-IntermediateActions-A_target_destroyObjectAction-destroyObjectAction");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // fUML.Syntax.Actions.IntermediateActions.A_endData_destroyLinkAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_endData_destroyLinkAction", "Syntax-Actions-IntermediateActions-A_endData_destroyLinkAction");
        mapping.mapAssociation(assoc, "fUML.Syntax.Actions.IntermediateActions", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "destroyLinkAction", 
    	    "Syntax-Actions-IntermediateActions-A_endData_destroyLinkAction-destroyLinkAction",
    	    "Syntax-Actions-IntermediateActions-DestroyLinkAction",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Actions-IntermediateActions-DestroyLinkAction-endData Syntax-Actions-IntermediateActions-A_endData_destroyLinkAction-destroyLinkAction");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // fUML.Syntax.Actions.IntermediateActions.A_result_createObjectAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_result_createObjectAction", "Syntax-Actions-IntermediateActions-A_result_createObjectAction");
        mapping.mapAssociation(assoc, "fUML.Syntax.Actions.IntermediateActions", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "createObjectAction", 
    	    "Syntax-Actions-IntermediateActions-A_result_createObjectAction-createObjectAction",
    	    "Syntax-Actions-IntermediateActions-CreateObjectAction",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Actions-IntermediateActions-CreateObjectAction-result Syntax-Actions-IntermediateActions-A_result_createObjectAction-createObjectAction");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // fUML.Syntax.Actions.IntermediateActions.A_classifier_createObjectAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_classifier_createObjectAction", "Syntax-Actions-IntermediateActions-A_classifier_createObjectAction");
        mapping.mapAssociation(assoc, "fUML.Syntax.Actions.IntermediateActions", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "createObjectAction", 
    	    "Syntax-Actions-IntermediateActions-A_classifier_createObjectAction-createObjectAction",
    	    "Syntax-Actions-IntermediateActions-CreateObjectAction",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Actions-IntermediateActions-CreateObjectAction-classifier Syntax-Actions-IntermediateActions-A_classifier_createObjectAction-createObjectAction");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // fUML.Syntax.Actions.IntermediateActions.A_endData_createLinkAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_endData_createLinkAction", "Syntax-Actions-IntermediateActions-A_endData_createLinkAction");
        mapping.mapAssociation(assoc, "fUML.Syntax.Actions.IntermediateActions", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "createLinkAction", 
    	    "Syntax-Actions-IntermediateActions-A_endData_createLinkAction-createLinkAction",
    	    "Syntax-Actions-IntermediateActions-CreateLinkAction",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Actions-IntermediateActions-CreateLinkAction-endData Syntax-Actions-IntermediateActions-A_endData_createLinkAction-createLinkAction");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // fUML.Syntax.Actions.IntermediateActions.A_result_clearStructuralFeatureAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_result_clearStructuralFeatureAction", "Syntax-Actions-IntermediateActions-A_result_clearStructuralFeatureAction");
        mapping.mapAssociation(assoc, "fUML.Syntax.Actions.IntermediateActions", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "clearStructuralFeatureAction", 
    	    "Syntax-Actions-IntermediateActions-A_result_clearStructuralFeatureAction-clearStructuralFeatureAction",
    	    "Syntax-Actions-IntermediateActions-ClearStructuralFeatureAction",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Actions-IntermediateActions-ClearStructuralFeatureAction-result Syntax-Actions-IntermediateActions-A_result_clearStructuralFeatureAction-clearStructuralFeatureAction");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // fUML.Syntax.Actions.IntermediateActions.A_object_clearAssociationAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_object_clearAssociationAction", "Syntax-Actions-IntermediateActions-A_object_clearAssociationAction");
        mapping.mapAssociation(assoc, "fUML.Syntax.Actions.IntermediateActions", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "clearAssociationAction", 
    	    "Syntax-Actions-IntermediateActions-A_object_clearAssociationAction-clearAssociationAction",
    	    "Syntax-Actions-IntermediateActions-ClearAssociationAction",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Actions-IntermediateActions-ClearAssociationAction-object Syntax-Actions-IntermediateActions-A_object_clearAssociationAction-clearAssociationAction");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // fUML.Syntax.Actions.IntermediateActions.A_association_clearAssociationAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_association_clearAssociationAction", "Syntax-Actions-IntermediateActions-A_association_clearAssociationAction");
        mapping.mapAssociation(assoc, "fUML.Syntax.Actions.IntermediateActions", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "clearAssociationAction", 
    	    "Syntax-Actions-IntermediateActions-A_association_clearAssociationAction-clearAssociationAction",
    	    "Syntax-Actions-IntermediateActions-ClearAssociationAction",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Actions-IntermediateActions-ClearAssociationAction-association Syntax-Actions-IntermediateActions-A_association_clearAssociationAction-clearAssociationAction");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // fUML.Syntax.Actions.IntermediateActions.A_destroyAt_linkEndDestructionData 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_destroyAt_linkEndDestructionData", "Syntax-Actions-IntermediateActions-A_destroyAt_linkEndDestructionData");
        mapping.mapAssociation(assoc, "fUML.Syntax.Actions.IntermediateActions", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "linkEndDestructionData", 
    	    "Syntax-Actions-IntermediateActions-A_destroyAt_linkEndDestructionData-linkEndDestructionData",
    	    "Syntax-Actions-IntermediateActions-LinkEndDestructionData",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Actions-IntermediateActions-LinkEndDestructionData-destroyAt Syntax-Actions-IntermediateActions-A_destroyAt_linkEndDestructionData-linkEndDestructionData");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // fUML.Syntax.Actions.IntermediateActions.A_value_linkEndData 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_value_linkEndData", "Syntax-Actions-IntermediateActions-A_value_linkEndData");
        mapping.mapAssociation(assoc, "fUML.Syntax.Actions.IntermediateActions", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "linkEndData", 
    	    "Syntax-Actions-IntermediateActions-A_value_linkEndData-linkEndData",
    	    "Syntax-Actions-IntermediateActions-LinkEndData",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Actions-IntermediateActions-LinkEndData-value Syntax-Actions-IntermediateActions-A_value_linkEndData-linkEndData");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // fUML.Syntax.Actions.IntermediateActions.A_end_linkEndData 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_end_linkEndData", "Syntax-Actions-IntermediateActions-A_end_linkEndData");
        mapping.mapAssociation(assoc, "fUML.Syntax.Actions.IntermediateActions", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "linkEndData", 
    	    "Syntax-Actions-IntermediateActions-A_end_linkEndData-linkEndData",
    	    "Syntax-Actions-IntermediateActions-LinkEndData",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Actions-IntermediateActions-LinkEndData-end Syntax-Actions-IntermediateActions-A_end_linkEndData-linkEndData");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // fUML.Syntax.Actions.IntermediateActions.A_insertAt_linkEndCreationData 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_insertAt_linkEndCreationData", "Syntax-Actions-IntermediateActions-A_insertAt_linkEndCreationData");
        mapping.mapAssociation(assoc, "fUML.Syntax.Actions.IntermediateActions", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "linkEndCreationData", 
    	    "Syntax-Actions-IntermediateActions-A_insertAt_linkEndCreationData-linkEndCreationData",
    	    "Syntax-Actions-IntermediateActions-LinkEndCreationData",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Actions-IntermediateActions-LinkEndCreationData-insertAt Syntax-Actions-IntermediateActions-A_insertAt_linkEndCreationData-linkEndCreationData");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // fUML.Syntax.Actions.IntermediateActions.A_endData_linkAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_endData_linkAction", "Syntax-Actions-IntermediateActions-A_endData_linkAction");
        mapping.mapAssociation(assoc, "fUML.Syntax.Actions.IntermediateActions", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "linkAction", 
    	    "Syntax-Actions-IntermediateActions-A_endData_linkAction-linkAction",
    	    "Syntax-Actions-IntermediateActions-LinkAction",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Actions-IntermediateActions-LinkAction-endData Syntax-Actions-IntermediateActions-A_endData_linkAction-linkAction");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // fUML.Syntax.Actions.IntermediateActions.A_result_readStructuralFeatureAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_result_readStructuralFeatureAction", "Syntax-Actions-IntermediateActions-A_result_readStructuralFeatureAction");
        mapping.mapAssociation(assoc, "fUML.Syntax.Actions.IntermediateActions", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "readStructuralFeatureAction", 
    	    "Syntax-Actions-IntermediateActions-A_result_readStructuralFeatureAction-readStructuralFeatureAction",
    	    "Syntax-Actions-IntermediateActions-ReadStructuralFeatureAction",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Actions-IntermediateActions-ReadStructuralFeatureAction-result Syntax-Actions-IntermediateActions-A_result_readStructuralFeatureAction-readStructuralFeatureAction");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // fUML.Syntax.Actions.IntermediateActions.A_result_readSelfAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_result_readSelfAction", "Syntax-Actions-IntermediateActions-A_result_readSelfAction");
        mapping.mapAssociation(assoc, "fUML.Syntax.Actions.IntermediateActions", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "readSelfAction", 
    	    "Syntax-Actions-IntermediateActions-A_result_readSelfAction-readSelfAction",
    	    "Syntax-Actions-IntermediateActions-ReadSelfAction",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Actions-IntermediateActions-ReadSelfAction-result Syntax-Actions-IntermediateActions-A_result_readSelfAction-readSelfAction");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // fUML.Syntax.Actions.IntermediateActions.A_result_readLinkAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_result_readLinkAction", "Syntax-Actions-IntermediateActions-A_result_readLinkAction");
        mapping.mapAssociation(assoc, "fUML.Syntax.Actions.IntermediateActions", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "readLinkAction", 
    	    "Syntax-Actions-IntermediateActions-A_result_readLinkAction-readLinkAction",
    	    "Syntax-Actions-IntermediateActions-ReadLinkAction",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Actions-IntermediateActions-ReadLinkAction-result Syntax-Actions-IntermediateActions-A_result_readLinkAction-readLinkAction");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // fUML.Syntax.Actions.IntermediateActions.A_removeAt_removeStructuralFeatureValueAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_removeAt_removeStructuralFeatureValueAction", "Syntax-Actions-IntermediateActions-A_removeAt_removeStructuralFeatureValueAction");
        mapping.mapAssociation(assoc, "fUML.Syntax.Actions.IntermediateActions", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "removeStructuralFeatureValueAction", 
    	    "Syntax-Actions-IntermediateActions-A_removeAt_removeStructuralFeatureValueAction-removeStructuralFeatureValueAction",
    	    "Syntax-Actions-IntermediateActions-RemoveStructuralFeatureValueAction",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Actions-IntermediateActions-RemoveStructuralFeatureValueAction-removeAt Syntax-Actions-IntermediateActions-A_removeAt_removeStructuralFeatureValueAction-removeStructuralFeatureValueAction");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // fUML.Syntax.Actions.IntermediateActions.A_result_writeStructuralFeatureAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_result_writeStructuralFeatureAction", "Syntax-Actions-IntermediateActions-A_result_writeStructuralFeatureAction");
        mapping.mapAssociation(assoc, "fUML.Syntax.Actions.IntermediateActions", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "writeStructuralFeatureAction", 
    	    "Syntax-Actions-IntermediateActions-A_result_writeStructuralFeatureAction-writeStructuralFeatureAction",
    	    "Syntax-Actions-IntermediateActions-WriteStructuralFeatureAction",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Actions-IntermediateActions-WriteStructuralFeatureAction-result Syntax-Actions-IntermediateActions-A_result_writeStructuralFeatureAction-writeStructuralFeatureAction");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // fUML.Syntax.Actions.IntermediateActions.A_value_writeStructuralFeatureAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_value_writeStructuralFeatureAction", "Syntax-Actions-IntermediateActions-A_value_writeStructuralFeatureAction");
        mapping.mapAssociation(assoc, "fUML.Syntax.Actions.IntermediateActions", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "writeStructuralFeatureAction", 
    	    "Syntax-Actions-IntermediateActions-A_value_writeStructuralFeatureAction-writeStructuralFeatureAction",
    	    "Syntax-Actions-IntermediateActions-WriteStructuralFeatureAction",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Actions-IntermediateActions-WriteStructuralFeatureAction-value Syntax-Actions-IntermediateActions-A_value_writeStructuralFeatureAction-writeStructuralFeatureAction");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // fUML.Syntax.Actions.IntermediateActions.A_value_valueSpecificationAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_value_valueSpecificationAction", "Syntax-Actions-IntermediateActions-A_value_valueSpecificationAction");
        mapping.mapAssociation(assoc, "fUML.Syntax.Actions.IntermediateActions", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "valueSpecificationAction", 
    	    "Syntax-Actions-IntermediateActions-A_value_valueSpecificationAction-valueSpecificationAction",
    	    "Syntax-Actions-IntermediateActions-ValueSpecificationAction",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Actions-IntermediateActions-ValueSpecificationAction-value Syntax-Actions-IntermediateActions-A_value_valueSpecificationAction-valueSpecificationAction");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // fUML.Syntax.Actions.IntermediateActions.A_result_valueSpecificationAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_result_valueSpecificationAction", "Syntax-Actions-IntermediateActions-A_result_valueSpecificationAction");
        mapping.mapAssociation(assoc, "fUML.Syntax.Actions.IntermediateActions", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "valueSpecificationAction", 
    	    "Syntax-Actions-IntermediateActions-A_result_valueSpecificationAction-valueSpecificationAction",
    	    "Syntax-Actions-IntermediateActions-ValueSpecificationAction",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Actions-IntermediateActions-ValueSpecificationAction-result Syntax-Actions-IntermediateActions-A_result_valueSpecificationAction-valueSpecificationAction");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // fUML.Syntax.Actions.IntermediateActions.A_first_testIdentityAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_first_testIdentityAction", "Syntax-Actions-IntermediateActions-A_first_testIdentityAction");
        mapping.mapAssociation(assoc, "fUML.Syntax.Actions.IntermediateActions", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "testIdentityAction", 
    	    "Syntax-Actions-IntermediateActions-A_first_testIdentityAction-testIdentityAction",
    	    "Syntax-Actions-IntermediateActions-TestIdentityAction",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Actions-IntermediateActions-TestIdentityAction-first Syntax-Actions-IntermediateActions-A_first_testIdentityAction-testIdentityAction");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // fUML.Syntax.Actions.IntermediateActions.A_result_testIdentityAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_result_testIdentityAction", "Syntax-Actions-IntermediateActions-A_result_testIdentityAction");
        mapping.mapAssociation(assoc, "fUML.Syntax.Actions.IntermediateActions", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "testIdentityAction", 
    	    "Syntax-Actions-IntermediateActions-A_result_testIdentityAction-testIdentityAction",
    	    "Syntax-Actions-IntermediateActions-TestIdentityAction",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Actions-IntermediateActions-TestIdentityAction-result Syntax-Actions-IntermediateActions-A_result_testIdentityAction-testIdentityAction");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // fUML.Syntax.Actions.IntermediateActions.A_second_testIdentityAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_second_testIdentityAction", "Syntax-Actions-IntermediateActions-A_second_testIdentityAction");
        mapping.mapAssociation(assoc, "fUML.Syntax.Actions.IntermediateActions", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "testIdentityAction", 
    	    "Syntax-Actions-IntermediateActions-A_second_testIdentityAction-testIdentityAction",
    	    "Syntax-Actions-IntermediateActions-TestIdentityAction",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Actions-IntermediateActions-TestIdentityAction-second Syntax-Actions-IntermediateActions-A_second_testIdentityAction-testIdentityAction");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // fUML.Syntax.Actions.IntermediateActions.A_object_structuralFeatureAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_object_structuralFeatureAction", "Syntax-Actions-IntermediateActions-A_object_structuralFeatureAction");
        mapping.mapAssociation(assoc, "fUML.Syntax.Actions.IntermediateActions", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "structuralFeatureAction", 
    	    "Syntax-Actions-IntermediateActions-A_object_structuralFeatureAction-structuralFeatureAction",
    	    "Syntax-Actions-IntermediateActions-StructuralFeatureAction",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Actions-IntermediateActions-StructuralFeatureAction-object Syntax-Actions-IntermediateActions-A_object_structuralFeatureAction-structuralFeatureAction");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // fUML.Syntax.Actions.IntermediateActions.A_structuralFeatureAction_structuralFeature 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_structuralFeatureAction_structuralFeature", "Syntax-Actions-IntermediateActions-A_structuralFeatureAction_structuralFeature");
        mapping.mapAssociation(assoc, "fUML.Syntax.Actions.IntermediateActions", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "structuralFeatureAction", 
    	    "Syntax-Actions-IntermediateActions-A_structuralFeatureAction_structuralFeature-structuralFeatureAction",
    	    "Syntax-Actions-IntermediateActions-StructuralFeatureAction",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Actions-IntermediateActions-A_structuralFeatureAction_structuralFeature-structuralFeatureAction Syntax-Actions-IntermediateActions-StructuralFeatureAction-structuralFeature");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // fUML.Syntax.Actions.IntermediateActions.A_insertAt_addStructuralFeatureValueAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_insertAt_addStructuralFeatureValueAction", "Syntax-Actions-IntermediateActions-A_insertAt_addStructuralFeatureValueAction");
        mapping.mapAssociation(assoc, "fUML.Syntax.Actions.IntermediateActions", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "addStructuralFeatureValueAction", 
    	    "Syntax-Actions-IntermediateActions-A_insertAt_addStructuralFeatureValueAction-addStructuralFeatureValueAction",
    	    "Syntax-Actions-IntermediateActions-AddStructuralFeatureValueAction",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Actions-IntermediateActions-AddStructuralFeatureValueAction-insertAt Syntax-Actions-IntermediateActions-A_insertAt_addStructuralFeatureValueAction-addStructuralFeatureValueAction");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // fUML.Syntax.Actions.IntermediateActions.A_inputValue_linkAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_inputValue_linkAction", "Syntax-Actions-IntermediateActions-A_inputValue_linkAction");
        mapping.mapAssociation(assoc, "fUML.Syntax.Actions.IntermediateActions", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "linkAction", 
    	    "Syntax-Actions-IntermediateActions-A_inputValue_linkAction-linkAction",
    	    "Syntax-Actions-IntermediateActions-LinkAction",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Actions-IntermediateActions-LinkAction-inputValue Syntax-Actions-IntermediateActions-A_inputValue_linkAction-linkAction");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-CompleteActions";   
                      
        // fUML.Syntax.Actions.CompleteActions.A_result_acceptEventAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_result_acceptEventAction", "Syntax-Actions-CompleteActions-A_result_acceptEventAction");
        mapping.mapAssociation(assoc, "fUML.Syntax.Actions.CompleteActions", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "acceptEventAction", 
    	    "Syntax-Actions-CompleteActions-A_result_acceptEventAction-acceptEventAction",
    	    "Syntax-Actions-CompleteActions-AcceptEventAction",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Actions-CompleteActions-AcceptEventAction-result Syntax-Actions-CompleteActions-A_result_acceptEventAction-acceptEventAction");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-CompleteActions";   
                      
        // fUML.Syntax.Actions.CompleteActions.A_trigger_acceptEventAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_trigger_acceptEventAction", "Syntax-Actions-CompleteActions-A_trigger_acceptEventAction");
        mapping.mapAssociation(assoc, "fUML.Syntax.Actions.CompleteActions", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "acceptEventAction", 
    	    "Syntax-Actions-CompleteActions-A_trigger_acceptEventAction-acceptEventAction",
    	    "Syntax-Actions-CompleteActions-AcceptEventAction",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Actions-CompleteActions-AcceptEventAction-trigger Syntax-Actions-CompleteActions-A_trigger_acceptEventAction-acceptEventAction");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-CompleteActions";   
                      
        // fUML.Syntax.Actions.CompleteActions.A_reclassifyObjectAction_object 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_reclassifyObjectAction_object", "Syntax-Actions-CompleteActions-A_reclassifyObjectAction_object");
        mapping.mapAssociation(assoc, "fUML.Syntax.Actions.CompleteActions", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "reclassifyObjectAction", 
    	    "Syntax-Actions-CompleteActions-A_reclassifyObjectAction_object-reclassifyObjectAction",
    	    "Syntax-Actions-CompleteActions-ReclassifyObjectAction",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Actions-CompleteActions-A_reclassifyObjectAction_object-reclassifyObjectAction Syntax-Actions-CompleteActions-ReclassifyObjectAction-object");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-CompleteActions";   
                      
        // fUML.Syntax.Actions.CompleteActions.A_oldClassifier_reclassifyObjectAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_oldClassifier_reclassifyObjectAction", "Syntax-Actions-CompleteActions-A_oldClassifier_reclassifyObjectAction");
        mapping.mapAssociation(assoc, "fUML.Syntax.Actions.CompleteActions", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "reclassifyObjectAction", 
    	    "Syntax-Actions-CompleteActions-A_oldClassifier_reclassifyObjectAction-reclassifyObjectAction",
    	    "Syntax-Actions-CompleteActions-ReclassifyObjectAction",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Actions-CompleteActions-ReclassifyObjectAction-oldClassifier Syntax-Actions-CompleteActions-A_oldClassifier_reclassifyObjectAction-reclassifyObjectAction");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-CompleteActions";   
                      
        // fUML.Syntax.Actions.CompleteActions.A_object_readIsClassifiedObjectAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_object_readIsClassifiedObjectAction", "Syntax-Actions-CompleteActions-A_object_readIsClassifiedObjectAction");
        mapping.mapAssociation(assoc, "fUML.Syntax.Actions.CompleteActions", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "readIsClassifiedObjectAction", 
    	    "Syntax-Actions-CompleteActions-A_object_readIsClassifiedObjectAction-readIsClassifiedObjectAction",
    	    "Syntax-Actions-CompleteActions-ReadIsClassifiedObjectAction",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Actions-CompleteActions-ReadIsClassifiedObjectAction-object Syntax-Actions-CompleteActions-A_object_readIsClassifiedObjectAction-readIsClassifiedObjectAction");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-CompleteActions";   
                      
        // fUML.Syntax.Actions.CompleteActions.A_result_readIsClassifiedObjectAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_result_readIsClassifiedObjectAction", "Syntax-Actions-CompleteActions-A_result_readIsClassifiedObjectAction");
        mapping.mapAssociation(assoc, "fUML.Syntax.Actions.CompleteActions", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "readIsClassifiedObjectAction", 
    	    "Syntax-Actions-CompleteActions-A_result_readIsClassifiedObjectAction-readIsClassifiedObjectAction",
    	    "Syntax-Actions-CompleteActions-ReadIsClassifiedObjectAction",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Actions-CompleteActions-ReadIsClassifiedObjectAction-result Syntax-Actions-CompleteActions-A_result_readIsClassifiedObjectAction-readIsClassifiedObjectAction");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-CompleteActions";   
                      
        // fUML.Syntax.Actions.CompleteActions.A_classifier_readIsClassifiedObjectAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_classifier_readIsClassifiedObjectAction", "Syntax-Actions-CompleteActions-A_classifier_readIsClassifiedObjectAction");
        mapping.mapAssociation(assoc, "fUML.Syntax.Actions.CompleteActions", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "readIsClassifiedObjectAction", 
    	    "Syntax-Actions-CompleteActions-A_classifier_readIsClassifiedObjectAction-readIsClassifiedObjectAction",
    	    "Syntax-Actions-CompleteActions-ReadIsClassifiedObjectAction",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Actions-CompleteActions-ReadIsClassifiedObjectAction-classifier Syntax-Actions-CompleteActions-A_classifier_readIsClassifiedObjectAction-readIsClassifiedObjectAction");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-CompleteActions";   
                      
        // fUML.Syntax.Actions.CompleteActions.A_classifier_readExtentAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_classifier_readExtentAction", "Syntax-Actions-CompleteActions-A_classifier_readExtentAction");
        mapping.mapAssociation(assoc, "fUML.Syntax.Actions.CompleteActions", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "readExtentAction", 
    	    "Syntax-Actions-CompleteActions-A_classifier_readExtentAction-readExtentAction",
    	    "Syntax-Actions-CompleteActions-ReadExtentAction",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Actions-CompleteActions-ReadExtentAction-classifier Syntax-Actions-CompleteActions-A_classifier_readExtentAction-readExtentAction");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-CompleteActions";   
                      
        // fUML.Syntax.Actions.CompleteActions.A_result_readExtentAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_result_readExtentAction", "Syntax-Actions-CompleteActions-A_result_readExtentAction");
        mapping.mapAssociation(assoc, "fUML.Syntax.Actions.CompleteActions", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "readExtentAction", 
    	    "Syntax-Actions-CompleteActions-A_result_readExtentAction-readExtentAction",
    	    "Syntax-Actions-CompleteActions-ReadExtentAction",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Actions-CompleteActions-ReadExtentAction-result Syntax-Actions-CompleteActions-A_result_readExtentAction-readExtentAction");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-CompleteActions";   
                      
        // fUML.Syntax.Actions.CompleteActions.A_result_reduceAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_result_reduceAction", "Syntax-Actions-CompleteActions-A_result_reduceAction");
        mapping.mapAssociation(assoc, "fUML.Syntax.Actions.CompleteActions", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "reduceAction", 
    	    "Syntax-Actions-CompleteActions-A_result_reduceAction-reduceAction",
    	    "Syntax-Actions-CompleteActions-ReduceAction",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Actions-CompleteActions-ReduceAction-result Syntax-Actions-CompleteActions-A_result_reduceAction-reduceAction");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-CompleteActions";   
                      
        // fUML.Syntax.Actions.CompleteActions.A_collection_reduceAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_collection_reduceAction", "Syntax-Actions-CompleteActions-A_collection_reduceAction");
        mapping.mapAssociation(assoc, "fUML.Syntax.Actions.CompleteActions", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "reduceAction", 
    	    "Syntax-Actions-CompleteActions-A_collection_reduceAction-reduceAction",
    	    "Syntax-Actions-CompleteActions-ReduceAction",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Actions-CompleteActions-ReduceAction-collection Syntax-Actions-CompleteActions-A_collection_reduceAction-reduceAction");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-CompleteActions";   
                      
        // fUML.Syntax.Actions.CompleteActions.A_reducer_reduceAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_reducer_reduceAction", "Syntax-Actions-CompleteActions-A_reducer_reduceAction");
        mapping.mapAssociation(assoc, "fUML.Syntax.Actions.CompleteActions", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "reduceAction", 
    	    "Syntax-Actions-CompleteActions-A_reducer_reduceAction-reduceAction",
    	    "Syntax-Actions-CompleteActions-ReduceAction",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Actions-CompleteActions-ReduceAction-reducer Syntax-Actions-CompleteActions-A_reducer_reduceAction-reduceAction");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-CompleteActions";   
                      
        // fUML.Syntax.Actions.CompleteActions.A_object_startObjectBehaviorAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_object_startObjectBehaviorAction", "Syntax-Actions-CompleteActions-A_object_startObjectBehaviorAction");
        mapping.mapAssociation(assoc, "fUML.Syntax.Actions.CompleteActions", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "startObjectBehaviorAction", 
    	    "Syntax-Actions-CompleteActions-A_object_startObjectBehaviorAction-startObjectBehaviorAction",
    	    "Syntax-Actions-CompleteActions-StartObjectBehaviorAction",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Actions-CompleteActions-StartObjectBehaviorAction-object Syntax-Actions-CompleteActions-A_object_startObjectBehaviorAction-startObjectBehaviorAction");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-CompleteActions";   
                      
        // fUML.Syntax.Actions.CompleteActions.A_object_startClassifierBehaviorAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_object_startClassifierBehaviorAction", "Syntax-Actions-CompleteActions-A_object_startClassifierBehaviorAction");
        mapping.mapAssociation(assoc, "fUML.Syntax.Actions.CompleteActions", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "startClassifierBehaviorAction", 
    	    "Syntax-Actions-CompleteActions-A_object_startClassifierBehaviorAction-startClassifierBehaviorAction",
    	    "Syntax-Actions-CompleteActions-StartClassifierBehaviorAction",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Actions-CompleteActions-StartClassifierBehaviorAction-object Syntax-Actions-CompleteActions-A_object_startClassifierBehaviorAction-startClassifierBehaviorAction");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-CompleteActions";   
                      
        // fUML.Syntax.Actions.CompleteActions.A_newClassifier_reclassifyObjectAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_newClassifier_reclassifyObjectAction", "Syntax-Actions-CompleteActions-A_newClassifier_reclassifyObjectAction");
        mapping.mapAssociation(assoc, "fUML.Syntax.Actions.CompleteActions", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "reclassifyObjectAction", 
    	    "Syntax-Actions-CompleteActions-A_newClassifier_reclassifyObjectAction-reclassifyObjectAction",
    	    "Syntax-Actions-CompleteActions-ReclassifyObjectAction",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Syntax-Actions-CompleteActions-ReclassifyObjectAction-newClassifier Syntax-Actions-CompleteActions-A_newClassifier_reclassifyObjectAction-reclassifyObjectAction");
    
    }

}
    
    