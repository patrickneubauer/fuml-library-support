
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
public class fUML_SemanticsAssembler extends ModelAssembler 
    implements RepositoryArtifact
{

    private static Log log = LogFactory.getLog(fUML_SemanticsAssembler.class);
    private ModelFactory factory;

    public fUML_SemanticsAssembler(Artifact artifact, RepositoryMapping mapping, Repository model) {
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
         
                     
        // Syntax            
    	pkg  = factory.createPackage("Syntax", "Syntax", "Syntax", this); // root package
    	mapping.mapPackage(pkg, null, this); 
                
                     
        // Syntax.CommonBehaviors                                                    
    	pkg  = factory.createPackage("CommonBehaviors", "Syntax.CommonBehaviors", "Syntax-CommonBehaviors", pkg, this);
    	mapping.mapPackage(pkg, "Syntax", this); 
                
                     
        // Syntax.CommonBehaviors.BasicBehaviors                                                    
    	pkg  = factory.createPackage("BasicBehaviors", "Syntax.CommonBehaviors.BasicBehaviors", "Syntax-CommonBehaviors-BasicBehaviors", pkg, this);
    	mapping.mapPackage(pkg, "Syntax.CommonBehaviors", this); 
                
                     
        // Syntax.CommonBehaviors.Communications                                                    
    	pkg  = factory.createPackage("Communications", "Syntax.CommonBehaviors.Communications", "Syntax-CommonBehaviors-Communications", pkg, this);
    	mapping.mapPackage(pkg, "Syntax.CommonBehaviors", this); 
                
                     
        // Syntax.Classes                                                    
    	pkg  = factory.createPackage("Classes", "Syntax.Classes", "Syntax-Classes", pkg, this);
    	mapping.mapPackage(pkg, "Syntax", this); 
                
                     
        // Syntax.Classes.Kernel                                                    
    	pkg  = factory.createPackage("Kernel", "Syntax.Classes.Kernel", "Syntax-Classes-Kernel", pkg, this);
    	mapping.mapPackage(pkg, "Syntax.Classes", this); 
                
                     
        // Syntax.Activities                                                    
    	pkg  = factory.createPackage("Activities", "Syntax.Activities", "Syntax-Activities", pkg, this);
    	mapping.mapPackage(pkg, "Syntax", this); 
                
                     
        // Syntax.Activities.IntermediateActivities                                                    
    	pkg  = factory.createPackage("IntermediateActivities", "Syntax.Activities.IntermediateActivities", "Syntax-Activities-IntermediateActivities", pkg, this);
    	mapping.mapPackage(pkg, "Syntax.Activities", this); 
                
                     
        // Syntax.Activities.CompleteStructuredActivities                                                    
    	pkg  = factory.createPackage("CompleteStructuredActivities", "Syntax.Activities.CompleteStructuredActivities", "Syntax-Activities-CompleteStructuredActivities", pkg, this);
    	mapping.mapPackage(pkg, "Syntax.Activities", this); 
                
                     
        // Syntax.Activities.ExtraStructuredActivities                                                    
    	pkg  = factory.createPackage("ExtraStructuredActivities", "Syntax.Activities.ExtraStructuredActivities", "Syntax-Activities-ExtraStructuredActivities", pkg, this);
    	mapping.mapPackage(pkg, "Syntax.Activities", this); 
                
                     
        // Syntax.Actions                                                    
    	pkg  = factory.createPackage("Actions", "Syntax.Actions", "Syntax-Actions", pkg, this);
    	mapping.mapPackage(pkg, "Syntax", this); 
                
                     
        // Syntax.Actions.BasicActions                                                    
    	pkg  = factory.createPackage("BasicActions", "Syntax.Actions.BasicActions", "Syntax-Actions-BasicActions", pkg, this);
    	mapping.mapPackage(pkg, "Syntax.Actions", this); 
                
                     
        // Syntax.Actions.IntermediateActions                                                    
    	pkg  = factory.createPackage("IntermediateActions", "Syntax.Actions.IntermediateActions", "Syntax-Actions-IntermediateActions", pkg, this);
    	mapping.mapPackage(pkg, "Syntax.Actions", this); 
                
                     
        // Syntax.Actions.CompleteActions                                                    
    	pkg  = factory.createPackage("CompleteActions", "Syntax.Actions.CompleteActions", "Syntax-Actions-CompleteActions", pkg, this);
    	mapping.mapPackage(pkg, "Syntax.Actions", this); 
                
                     
        // Syntax.L1                                                    
    	pkg  = factory.createPackage("L1", "Syntax.L1", "Syntax-L1", pkg, this);
    	mapping.mapPackage(pkg, "Syntax", this); 
                        
        mapping.mapPackageMerge(pkg, "Syntax-Classes-Kernel");
                        
        mapping.mapPackageMerge(pkg, "Syntax-CommonBehaviors-BasicBehaviors");
                        
        mapping.mapPackageMerge(pkg, "Syntax-CommonBehaviors-Communications");
                
                     
        // Syntax.L2                                                    
    	pkg  = factory.createPackage("L2", "Syntax.L2", "Syntax-L2", pkg, this);
    	mapping.mapPackage(pkg, "Syntax", this); 
                        
        mapping.mapPackageMerge(pkg, "Syntax-L1");
                        
        mapping.mapPackageMerge(pkg, "Syntax-Actions-IntermediateActions");
                        
        mapping.mapPackageMerge(pkg, "Syntax-Activities-IntermediateActivities");
                        
        mapping.mapPackageMerge(pkg, "Syntax-Actions-BasicActions");
                
                     
        // Syntax.L3                                                    
    	pkg  = factory.createPackage("L3", "Syntax.L3", "Syntax-L3", pkg, this);
    	mapping.mapPackage(pkg, "Syntax", this); 
                        
        mapping.mapPackageMerge(pkg, "Syntax-L2");
                        
        mapping.mapPackageMerge(pkg, "Syntax-Activities-CompleteStructuredActivities");
                        
        mapping.mapPackageMerge(pkg, "Syntax-Activities-ExtraStructuredActivities");
                        
        mapping.mapPackageMerge(pkg, "Syntax-Actions-CompleteActions");
                
                     
        // Semantics            
    	pkg  = factory.createPackage("Semantics", "Semantics", "Semantics", this); // root package
    	mapping.mapPackage(pkg, null, this); 
                
                     
        // Semantics.Actions                                                    
    	pkg  = factory.createPackage("Actions", "Semantics.Actions", "Semantics-Actions", pkg, this);
    	mapping.mapPackage(pkg, "Semantics", this); 
                
                     
        // Semantics.Actions.BasicActions                                                    
    	pkg  = factory.createPackage("BasicActions", "Semantics.Actions.BasicActions", "Semantics-Actions-BasicActions", pkg, this);
    	mapping.mapPackage(pkg, "Semantics.Actions", this); 
                
                     
        // Semantics.Actions.IntermediateActions                                                    
    	pkg  = factory.createPackage("IntermediateActions", "Semantics.Actions.IntermediateActions", "Semantics-Actions-IntermediateActions", pkg, this);
    	mapping.mapPackage(pkg, "Semantics.Actions", this); 
                
                     
        // Semantics.Actions.CompleteActions                                                    
    	pkg  = factory.createPackage("CompleteActions", "Semantics.Actions.CompleteActions", "Semantics-Actions-CompleteActions", pkg, this);
    	mapping.mapPackage(pkg, "Semantics.Actions", this); 
                
                     
        // Semantics.Activities                                                    
    	pkg  = factory.createPackage("Activities", "Semantics.Activities", "Semantics-Activities", pkg, this);
    	mapping.mapPackage(pkg, "Semantics", this); 
                
                     
        // Semantics.Activities.CompleteStructuredActivities                                                    
    	pkg  = factory.createPackage("CompleteStructuredActivities", "Semantics.Activities.CompleteStructuredActivities", "Semantics-Activities-CompleteStructuredActivities", pkg, this);
    	mapping.mapPackage(pkg, "Semantics.Activities", this); 
                
                     
        // Semantics.Activities.IntermediateActivities                                                    
    	pkg  = factory.createPackage("IntermediateActivities", "Semantics.Activities.IntermediateActivities", "Semantics-Activities-IntermediateActivities", pkg, this);
    	mapping.mapPackage(pkg, "Semantics.Activities", this); 
                
                     
        // Semantics.Activities.ExtraStructuredActivities                                                    
    	pkg  = factory.createPackage("ExtraStructuredActivities", "Semantics.Activities.ExtraStructuredActivities", "Semantics-Activities-ExtraStructuredActivities", pkg, this);
    	mapping.mapPackage(pkg, "Semantics.Activities", this); 
                
                     
        // Semantics.Classes                                                    
    	pkg  = factory.createPackage("Classes", "Semantics.Classes", "Semantics-Classes", pkg, this);
    	mapping.mapPackage(pkg, "Semantics", this); 
                
                     
        // Semantics.Classes.Kernel                                                    
    	pkg  = factory.createPackage("Kernel", "Semantics.Classes.Kernel", "Semantics-Classes-Kernel", pkg, this);
    	mapping.mapPackage(pkg, "Semantics.Classes", this); 
                
                     
        // Semantics.CommonBehaviors                                                    
    	pkg  = factory.createPackage("CommonBehaviors", "Semantics.CommonBehaviors", "Semantics-CommonBehaviors", pkg, this);
    	mapping.mapPackage(pkg, "Semantics", this); 
                
                     
        // Semantics.CommonBehaviors.BasicBehaviors                                                    
    	pkg  = factory.createPackage("BasicBehaviors", "Semantics.CommonBehaviors.BasicBehaviors", "Semantics-CommonBehaviors-BasicBehaviors", pkg, this);
    	mapping.mapPackage(pkg, "Semantics.CommonBehaviors", this); 
                
                     
        // Semantics.CommonBehaviors.Communications                                                    
    	pkg  = factory.createPackage("Communications", "Semantics.CommonBehaviors.Communications", "Semantics-CommonBehaviors-Communications", pkg, this);
    	mapping.mapPackage(pkg, "Semantics.CommonBehaviors", this); 
                
                     
        // Semantics.Loci                                                    
    	pkg  = factory.createPackage("Loci", "Semantics.Loci", "Semantics-Loci", pkg, this);
    	mapping.mapPackage(pkg, "Semantics", this); 
                
                     
        // Semantics.Loci.LociL1                                                    
    	pkg  = factory.createPackage("LociL1", "Semantics.Loci.LociL1", "Semantics-Loci-LociL1", pkg, this);
    	mapping.mapPackage(pkg, "Semantics.Loci", this); 
                
                     
        // Semantics.Loci.LociL2                                                    
    	pkg  = factory.createPackage("LociL2", "Semantics.Loci.LociL2", "Semantics-Loci-LociL2", pkg, this);
    	mapping.mapPackage(pkg, "Semantics.Loci", this); 
                
                     
        // Semantics.Loci.LociL3                                                    
    	pkg  = factory.createPackage("LociL3", "Semantics.Loci.LociL3", "Semantics-Loci-LociL3", pkg, this);
    	mapping.mapPackage(pkg, "Semantics.Loci", this); 
                
                     
        // Semantics.L1                                                    
    	pkg  = factory.createPackage("L1", "Semantics.L1", "Semantics-L1", pkg, this);
    	mapping.mapPackage(pkg, "Semantics", this); 
                        
        mapping.mapPackageMerge(pkg, "Semantics-Classes-Kernel");
                        
        mapping.mapPackageMerge(pkg, "Semantics-CommonBehaviors-BasicBehaviors");
                        
        mapping.mapPackageMerge(pkg, "Semantics-CommonBehaviors-Communications");
                        
        mapping.mapPackageMerge(pkg, "Semantics-Loci-LociL1");
                
                     
        // Semantics.L2                                                    
    	pkg  = factory.createPackage("L2", "Semantics.L2", "Semantics-L2", pkg, this);
    	mapping.mapPackage(pkg, "Semantics", this); 
                        
        mapping.mapPackageMerge(pkg, "Semantics-L1");
                        
        mapping.mapPackageMerge(pkg, "Semantics-Activities-IntermediateActivities");
                        
        mapping.mapPackageMerge(pkg, "Semantics-Actions-BasicActions");
                        
        mapping.mapPackageMerge(pkg, "Semantics-Actions-IntermediateActions");
                        
        mapping.mapPackageMerge(pkg, "Semantics-Loci-LociL2");
                
                     
        // Semantics.L3                                                    
    	pkg  = factory.createPackage("L3", "Semantics.L3", "Semantics-L3", pkg, this);
    	mapping.mapPackage(pkg, "Semantics", this); 
                        
        mapping.mapPackageMerge(pkg, "Semantics-L2");
                        
        mapping.mapPackageMerge(pkg, "Semantics-Activities-CompleteStructuredActivities");
                        
        mapping.mapPackageMerge(pkg, "Semantics-Actions-CompleteActions");
                        
        mapping.mapPackageMerge(pkg, "Semantics-Activities-ExtraStructuredActivities");
                        
        mapping.mapPackageMerge(pkg, "Semantics-Loci-LociL3");
            
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
                      
        // Syntax.CommonBehaviors.BasicBehaviors.OpaqueBehavior 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.CommonBehaviors.BasicBehaviors").getDelegate();       
    	clss  = factory.createClass("OpaqueBehavior", "Syntax-CommonBehaviors-BasicBehaviors-OpaqueBehavior", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.CommonBehaviors.BasicBehaviors", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-CommonBehaviors-BasicBehaviors";   
                      
        // Syntax.CommonBehaviors.BasicBehaviors.FunctionBehavior 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.CommonBehaviors.BasicBehaviors").getDelegate();       
    	clss  = factory.createClass("FunctionBehavior", "Syntax-CommonBehaviors-BasicBehaviors-FunctionBehavior", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.CommonBehaviors.BasicBehaviors", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-CommonBehaviors-BasicBehaviors";   
                      
        // Syntax.CommonBehaviors.BasicBehaviors.BehavioredClassifier 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.CommonBehaviors.BasicBehaviors").getDelegate();       
    	clss  = factory.createClass("BehavioredClassifier", "Syntax-CommonBehaviors-BasicBehaviors-BehavioredClassifier", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "Syntax.CommonBehaviors.BasicBehaviors", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-CommonBehaviors-BasicBehaviors";   
                      
        // Syntax.CommonBehaviors.BasicBehaviors.Behavior 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.CommonBehaviors.BasicBehaviors").getDelegate();       
    	clss  = factory.createClass("Behavior", "Syntax-CommonBehaviors-BasicBehaviors-Behavior", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "Syntax.CommonBehaviors.BasicBehaviors", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-CommonBehaviors-Communications";   
                      
        // Syntax.CommonBehaviors.Communications.Trigger 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.CommonBehaviors.Communications").getDelegate();       
    	clss  = factory.createClass("Trigger", "Syntax-CommonBehaviors-Communications-Trigger", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.CommonBehaviors.Communications", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-CommonBehaviors-Communications";   
                      
        // Syntax.CommonBehaviors.Communications.Signal 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.CommonBehaviors.Communications").getDelegate();       
    	clss  = factory.createClass("Signal", "Syntax-CommonBehaviors-Communications-Signal", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.CommonBehaviors.Communications", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-CommonBehaviors-Communications";   
                      
        // Syntax.CommonBehaviors.Communications.SignalEvent 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.CommonBehaviors.Communications").getDelegate();       
    	clss  = factory.createClass("SignalEvent", "Syntax-CommonBehaviors-Communications-SignalEvent", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.CommonBehaviors.Communications", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-CommonBehaviors-Communications";   
                      
        // Syntax.CommonBehaviors.Communications.Reception 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.CommonBehaviors.Communications").getDelegate();       
    	clss  = factory.createClass("Reception", "Syntax-CommonBehaviors-Communications-Reception", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.CommonBehaviors.Communications", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-CommonBehaviors-Communications";   
                      
        // Syntax.CommonBehaviors.Communications.MessageEvent 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.CommonBehaviors.Communications").getDelegate();       
    	clss  = factory.createClass("MessageEvent", "Syntax-CommonBehaviors-Communications-MessageEvent", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "Syntax.CommonBehaviors.Communications", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-CommonBehaviors-Communications";   
                      
        // Syntax.CommonBehaviors.Communications.Event 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.CommonBehaviors.Communications").getDelegate();       
    	clss  = factory.createClass("Event", "Syntax-CommonBehaviors-Communications-Event", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "Syntax.CommonBehaviors.Communications", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // Syntax.Classes.Kernel.ValueSpecification 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("ValueSpecification", "Syntax-Classes-Kernel-ValueSpecification", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // Syntax.Classes.Kernel.StructuralFeature 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("StructuralFeature", "Syntax-Classes-Kernel-StructuralFeature", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // Syntax.Classes.Kernel.RedefinableElement 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("RedefinableElement", "Syntax-Classes-Kernel-RedefinableElement", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // Syntax.Classes.Kernel.BehavioralFeature 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("BehavioralFeature", "Syntax-Classes-Kernel-BehavioralFeature", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // Syntax.Classes.Kernel.InstanceSpecification 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("InstanceSpecification", "Syntax-Classes-Kernel-InstanceSpecification", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // Syntax.Classes.Kernel.InstanceValue 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("InstanceValue", "Syntax-Classes-Kernel-InstanceValue", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // Syntax.Classes.Kernel.LiteralBoolean 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("LiteralBoolean", "Syntax-Classes-Kernel-LiteralBoolean", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // Syntax.Classes.Kernel.LiteralInteger 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("LiteralInteger", "Syntax-Classes-Kernel-LiteralInteger", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // Syntax.Classes.Kernel.LiteralNull 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("LiteralNull", "Syntax-Classes-Kernel-LiteralNull", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // Syntax.Classes.Kernel.LiteralSpecification 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("LiteralSpecification", "Syntax-Classes-Kernel-LiteralSpecification", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // Syntax.Classes.Kernel.LiteralString 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("LiteralString", "Syntax-Classes-Kernel-LiteralString", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // Syntax.Classes.Kernel.LiteralUnlimitedNatural 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("LiteralUnlimitedNatural", "Syntax-Classes-Kernel-LiteralUnlimitedNatural", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // Syntax.Classes.Kernel.MultiplicityElement 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("MultiplicityElement", "Syntax-Classes-Kernel-MultiplicityElement", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // Syntax.Classes.Kernel.NamedElement 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("NamedElement", "Syntax-Classes-Kernel-NamedElement", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // Syntax.Classes.Kernel.Namespace 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("Namespace", "Syntax-Classes-Kernel-Namespace", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // Syntax.Classes.Kernel.Operation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("Operation", "Syntax-Classes-Kernel-Operation", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // Syntax.Classes.Kernel.Package 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("Package", "Syntax-Classes-Kernel-Package", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // Syntax.Classes.Kernel.PackageableElement 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("PackageableElement", "Syntax-Classes-Kernel-PackageableElement", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // Syntax.Classes.Kernel.Parameter 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("Parameter", "Syntax-Classes-Kernel-Parameter", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // Syntax.Classes.Kernel.PrimitiveType 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("PrimitiveType", "Syntax-Classes-Kernel-PrimitiveType", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // Syntax.Classes.Kernel.Property 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("Property", "Syntax-Classes-Kernel-Property", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // Syntax.Classes.Kernel.Slot 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("Slot", "Syntax-Classes-Kernel-Slot", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // Syntax.Classes.Kernel.Type 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("Type", "Syntax-Classes-Kernel-Type", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // Syntax.Classes.Kernel.TypedElement 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("TypedElement", "Syntax-Classes-Kernel-TypedElement", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // Syntax.Classes.Kernel.Class 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("Class", "Syntax-Classes-Kernel-Class", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // Syntax.Classes.Kernel.Classifier 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("Classifier", "Syntax-Classes-Kernel-Classifier", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // Syntax.Classes.Kernel.DataType 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("DataType", "Syntax-Classes-Kernel-DataType", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // Syntax.Classes.Kernel.Element 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("Element", "Syntax-Classes-Kernel-Element", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // Syntax.Classes.Kernel.Enumeration 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("Enumeration", "Syntax-Classes-Kernel-Enumeration", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // Syntax.Classes.Kernel.EnumerationLiteral 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("EnumerationLiteral", "Syntax-Classes-Kernel-EnumerationLiteral", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // Syntax.Classes.Kernel.Feature 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("Feature", "Syntax-Classes-Kernel-Feature", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // Syntax.Classes.Kernel.Generalization 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("Generalization", "Syntax-Classes-Kernel-Generalization", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // Syntax.Classes.Kernel.Association 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("Association", "Syntax-Classes-Kernel-Association", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // Syntax.Classes.Kernel.Comment 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("Comment", "Syntax-Classes-Kernel-Comment", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // Syntax.Classes.Kernel.ElementImport 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("ElementImport", "Syntax-Classes-Kernel-ElementImport", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // Syntax.Classes.Kernel.PackageImport 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("PackageImport", "Syntax-Classes-Kernel-PackageImport", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-IntermediateActivities";   
                      
        // Syntax.Activities.IntermediateActivities.ObjectFlow 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Activities.IntermediateActivities").getDelegate();       
    	clss  = factory.createClass("ObjectFlow", "Syntax-Activities-IntermediateActivities-ObjectFlow", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Activities.IntermediateActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-IntermediateActivities";   
                      
        // Syntax.Activities.IntermediateActivities.ObjectNode 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Activities.IntermediateActivities").getDelegate();       
    	clss  = factory.createClass("ObjectNode", "Syntax-Activities-IntermediateActivities-ObjectNode", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "Syntax.Activities.IntermediateActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-IntermediateActivities";   
                      
        // Syntax.Activities.IntermediateActivities.MergeNode 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Activities.IntermediateActivities").getDelegate();       
    	clss  = factory.createClass("MergeNode", "Syntax-Activities-IntermediateActivities-MergeNode", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Activities.IntermediateActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-IntermediateActivities";   
                      
        // Syntax.Activities.IntermediateActivities.JoinNode 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Activities.IntermediateActivities").getDelegate();       
    	clss  = factory.createClass("JoinNode", "Syntax-Activities-IntermediateActivities-JoinNode", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Activities.IntermediateActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-IntermediateActivities";   
                      
        // Syntax.Activities.IntermediateActivities.InitialNode 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Activities.IntermediateActivities").getDelegate();       
    	clss  = factory.createClass("InitialNode", "Syntax-Activities-IntermediateActivities-InitialNode", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Activities.IntermediateActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-IntermediateActivities";   
                      
        // Syntax.Activities.IntermediateActivities.FinalNode 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Activities.IntermediateActivities").getDelegate();       
    	clss  = factory.createClass("FinalNode", "Syntax-Activities-IntermediateActivities-FinalNode", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "Syntax.Activities.IntermediateActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-IntermediateActivities";   
                      
        // Syntax.Activities.IntermediateActivities.ForkNode 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Activities.IntermediateActivities").getDelegate();       
    	clss  = factory.createClass("ForkNode", "Syntax-Activities-IntermediateActivities-ForkNode", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Activities.IntermediateActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-IntermediateActivities";   
                      
        // Syntax.Activities.IntermediateActivities.ControlFlow 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Activities.IntermediateActivities").getDelegate();       
    	clss  = factory.createClass("ControlFlow", "Syntax-Activities-IntermediateActivities-ControlFlow", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Activities.IntermediateActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-IntermediateActivities";   
                      
        // Syntax.Activities.IntermediateActivities.ControlNode 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Activities.IntermediateActivities").getDelegate();       
    	clss  = factory.createClass("ControlNode", "Syntax-Activities-IntermediateActivities-ControlNode", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "Syntax.Activities.IntermediateActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-IntermediateActivities";   
                      
        // Syntax.Activities.IntermediateActivities.DecisionNode 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Activities.IntermediateActivities").getDelegate();       
    	clss  = factory.createClass("DecisionNode", "Syntax-Activities-IntermediateActivities-DecisionNode", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Activities.IntermediateActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-IntermediateActivities";   
                      
        // Syntax.Activities.IntermediateActivities.ActivityFinalNode 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Activities.IntermediateActivities").getDelegate();       
    	clss  = factory.createClass("ActivityFinalNode", "Syntax-Activities-IntermediateActivities-ActivityFinalNode", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Activities.IntermediateActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-IntermediateActivities";   
                      
        // Syntax.Activities.IntermediateActivities.ActivityNode 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Activities.IntermediateActivities").getDelegate();       
    	clss  = factory.createClass("ActivityNode", "Syntax-Activities-IntermediateActivities-ActivityNode", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "Syntax.Activities.IntermediateActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-IntermediateActivities";   
                      
        // Syntax.Activities.IntermediateActivities.ActivityParameterNode 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Activities.IntermediateActivities").getDelegate();       
    	clss  = factory.createClass("ActivityParameterNode", "Syntax-Activities-IntermediateActivities-ActivityParameterNode", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Activities.IntermediateActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-IntermediateActivities";   
                      
        // Syntax.Activities.IntermediateActivities.ActivityEdge 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Activities.IntermediateActivities").getDelegate();       
    	clss  = factory.createClass("ActivityEdge", "Syntax-Activities-IntermediateActivities-ActivityEdge", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "Syntax.Activities.IntermediateActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-IntermediateActivities";   
                      
        // Syntax.Activities.IntermediateActivities.Activity 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Activities.IntermediateActivities").getDelegate();       
    	clss  = factory.createClass("Activity", "Syntax-Activities-IntermediateActivities-Activity", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Activities.IntermediateActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-CompleteStructuredActivities";   
                      
        // Syntax.Activities.CompleteStructuredActivities.StructuredActivityNode 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Activities.CompleteStructuredActivities").getDelegate();       
    	clss  = factory.createClass("StructuredActivityNode", "Syntax-Activities-CompleteStructuredActivities-StructuredActivityNode", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Activities.CompleteStructuredActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-CompleteStructuredActivities";   
                      
        // Syntax.Activities.CompleteStructuredActivities.LoopNode 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Activities.CompleteStructuredActivities").getDelegate();       
    	clss  = factory.createClass("LoopNode", "Syntax-Activities-CompleteStructuredActivities-LoopNode", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Activities.CompleteStructuredActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-CompleteStructuredActivities";   
                      
        // Syntax.Activities.CompleteStructuredActivities.ExecutableNode 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Activities.CompleteStructuredActivities").getDelegate();       
    	clss  = factory.createClass("ExecutableNode", "Syntax-Activities-CompleteStructuredActivities-ExecutableNode", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "Syntax.Activities.CompleteStructuredActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-CompleteStructuredActivities";   
                      
        // Syntax.Activities.CompleteStructuredActivities.Clause 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Activities.CompleteStructuredActivities").getDelegate();       
    	clss  = factory.createClass("Clause", "Syntax-Activities-CompleteStructuredActivities-Clause", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Activities.CompleteStructuredActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-CompleteStructuredActivities";   
                      
        // Syntax.Activities.CompleteStructuredActivities.ConditionalNode 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Activities.CompleteStructuredActivities").getDelegate();       
    	clss  = factory.createClass("ConditionalNode", "Syntax-Activities-CompleteStructuredActivities-ConditionalNode", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Activities.CompleteStructuredActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-ExtraStructuredActivities";   
                      
        // Syntax.Activities.ExtraStructuredActivities.ExpansionNode 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Activities.ExtraStructuredActivities").getDelegate();       
    	clss  = factory.createClass("ExpansionNode", "Syntax-Activities-ExtraStructuredActivities-ExpansionNode", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Activities.ExtraStructuredActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-ExtraStructuredActivities";   
                      
        // Syntax.Activities.ExtraStructuredActivities.ExpansionRegion 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Activities.ExtraStructuredActivities").getDelegate();       
    	clss  = factory.createClass("ExpansionRegion", "Syntax-Activities-ExtraStructuredActivities-ExpansionRegion", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Activities.ExtraStructuredActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-BasicActions";   
                      
        // Syntax.Actions.BasicActions.SendSignalAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Actions.BasicActions").getDelegate();       
    	clss  = factory.createClass("SendSignalAction", "Syntax-Actions-BasicActions-SendSignalAction", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Actions.BasicActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-BasicActions";   
                      
        // Syntax.Actions.BasicActions.OutputPin 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Actions.BasicActions").getDelegate();       
    	clss  = factory.createClass("OutputPin", "Syntax-Actions-BasicActions-OutputPin", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Actions.BasicActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-BasicActions";   
                      
        // Syntax.Actions.BasicActions.Pin 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Actions.BasicActions").getDelegate();       
    	clss  = factory.createClass("Pin", "Syntax-Actions-BasicActions-Pin", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "Syntax.Actions.BasicActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-BasicActions";   
                      
        // Syntax.Actions.BasicActions.InputPin 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Actions.BasicActions").getDelegate();       
    	clss  = factory.createClass("InputPin", "Syntax-Actions-BasicActions-InputPin", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Actions.BasicActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-BasicActions";   
                      
        // Syntax.Actions.BasicActions.InvocationAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Actions.BasicActions").getDelegate();       
    	clss  = factory.createClass("InvocationAction", "Syntax-Actions-BasicActions-InvocationAction", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "Syntax.Actions.BasicActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-BasicActions";   
                      
        // Syntax.Actions.BasicActions.CallAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Actions.BasicActions").getDelegate();       
    	clss  = factory.createClass("CallAction", "Syntax-Actions-BasicActions-CallAction", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "Syntax.Actions.BasicActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-BasicActions";   
                      
        // Syntax.Actions.BasicActions.CallBehaviorAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Actions.BasicActions").getDelegate();       
    	clss  = factory.createClass("CallBehaviorAction", "Syntax-Actions-BasicActions-CallBehaviorAction", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Actions.BasicActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-BasicActions";   
                      
        // Syntax.Actions.BasicActions.CallOperationAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Actions.BasicActions").getDelegate();       
    	clss  = factory.createClass("CallOperationAction", "Syntax-Actions-BasicActions-CallOperationAction", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Actions.BasicActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-BasicActions";   
                      
        // Syntax.Actions.BasicActions.Action 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Actions.BasicActions").getDelegate();       
    	clss  = factory.createClass("Action", "Syntax-Actions-BasicActions-Action", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "Syntax.Actions.BasicActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // Syntax.Actions.IntermediateActions.StructuralFeatureAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Actions.IntermediateActions").getDelegate();       
    	clss  = factory.createClass("StructuralFeatureAction", "Syntax-Actions-IntermediateActions-StructuralFeatureAction", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "Syntax.Actions.IntermediateActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // Syntax.Actions.IntermediateActions.TestIdentityAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Actions.IntermediateActions").getDelegate();       
    	clss  = factory.createClass("TestIdentityAction", "Syntax-Actions-IntermediateActions-TestIdentityAction", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Actions.IntermediateActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // Syntax.Actions.IntermediateActions.ValueSpecificationAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Actions.IntermediateActions").getDelegate();       
    	clss  = factory.createClass("ValueSpecificationAction", "Syntax-Actions-IntermediateActions-ValueSpecificationAction", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Actions.IntermediateActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // Syntax.Actions.IntermediateActions.WriteLinkAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Actions.IntermediateActions").getDelegate();       
    	clss  = factory.createClass("WriteLinkAction", "Syntax-Actions-IntermediateActions-WriteLinkAction", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "Syntax.Actions.IntermediateActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // Syntax.Actions.IntermediateActions.WriteStructuralFeatureAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Actions.IntermediateActions").getDelegate();       
    	clss  = factory.createClass("WriteStructuralFeatureAction", "Syntax-Actions-IntermediateActions-WriteStructuralFeatureAction", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "Syntax.Actions.IntermediateActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // Syntax.Actions.IntermediateActions.RemoveStructuralFeatureValueAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Actions.IntermediateActions").getDelegate();       
    	clss  = factory.createClass("RemoveStructuralFeatureValueAction", "Syntax-Actions-IntermediateActions-RemoveStructuralFeatureValueAction", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Actions.IntermediateActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // Syntax.Actions.IntermediateActions.ReadLinkAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Actions.IntermediateActions").getDelegate();       
    	clss  = factory.createClass("ReadLinkAction", "Syntax-Actions-IntermediateActions-ReadLinkAction", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Actions.IntermediateActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // Syntax.Actions.IntermediateActions.ReadSelfAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Actions.IntermediateActions").getDelegate();       
    	clss  = factory.createClass("ReadSelfAction", "Syntax-Actions-IntermediateActions-ReadSelfAction", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Actions.IntermediateActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // Syntax.Actions.IntermediateActions.ReadStructuralFeatureAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Actions.IntermediateActions").getDelegate();       
    	clss  = factory.createClass("ReadStructuralFeatureAction", "Syntax-Actions-IntermediateActions-ReadStructuralFeatureAction", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Actions.IntermediateActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // Syntax.Actions.IntermediateActions.LinkAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Actions.IntermediateActions").getDelegate();       
    	clss  = factory.createClass("LinkAction", "Syntax-Actions-IntermediateActions-LinkAction", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "Syntax.Actions.IntermediateActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // Syntax.Actions.IntermediateActions.LinkEndCreationData 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Actions.IntermediateActions").getDelegate();       
    	clss  = factory.createClass("LinkEndCreationData", "Syntax-Actions-IntermediateActions-LinkEndCreationData", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Actions.IntermediateActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // Syntax.Actions.IntermediateActions.LinkEndData 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Actions.IntermediateActions").getDelegate();       
    	clss  = factory.createClass("LinkEndData", "Syntax-Actions-IntermediateActions-LinkEndData", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Actions.IntermediateActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // Syntax.Actions.IntermediateActions.LinkEndDestructionData 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Actions.IntermediateActions").getDelegate();       
    	clss  = factory.createClass("LinkEndDestructionData", "Syntax-Actions-IntermediateActions-LinkEndDestructionData", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Actions.IntermediateActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // Syntax.Actions.IntermediateActions.ClearAssociationAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Actions.IntermediateActions").getDelegate();       
    	clss  = factory.createClass("ClearAssociationAction", "Syntax-Actions-IntermediateActions-ClearAssociationAction", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Actions.IntermediateActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // Syntax.Actions.IntermediateActions.ClearStructuralFeatureAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Actions.IntermediateActions").getDelegate();       
    	clss  = factory.createClass("ClearStructuralFeatureAction", "Syntax-Actions-IntermediateActions-ClearStructuralFeatureAction", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Actions.IntermediateActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // Syntax.Actions.IntermediateActions.CreateLinkAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Actions.IntermediateActions").getDelegate();       
    	clss  = factory.createClass("CreateLinkAction", "Syntax-Actions-IntermediateActions-CreateLinkAction", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Actions.IntermediateActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // Syntax.Actions.IntermediateActions.CreateObjectAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Actions.IntermediateActions").getDelegate();       
    	clss  = factory.createClass("CreateObjectAction", "Syntax-Actions-IntermediateActions-CreateObjectAction", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Actions.IntermediateActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // Syntax.Actions.IntermediateActions.DestroyLinkAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Actions.IntermediateActions").getDelegate();       
    	clss  = factory.createClass("DestroyLinkAction", "Syntax-Actions-IntermediateActions-DestroyLinkAction", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Actions.IntermediateActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // Syntax.Actions.IntermediateActions.DestroyObjectAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Actions.IntermediateActions").getDelegate();       
    	clss  = factory.createClass("DestroyObjectAction", "Syntax-Actions-IntermediateActions-DestroyObjectAction", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Actions.IntermediateActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-IntermediateActions";   
                      
        // Syntax.Actions.IntermediateActions.AddStructuralFeatureValueAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Actions.IntermediateActions").getDelegate();       
    	clss  = factory.createClass("AddStructuralFeatureValueAction", "Syntax-Actions-IntermediateActions-AddStructuralFeatureValueAction", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Actions.IntermediateActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-CompleteActions";   
                      
        // Syntax.Actions.CompleteActions.StartClassifierBehaviorAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Actions.CompleteActions").getDelegate();       
    	clss  = factory.createClass("StartClassifierBehaviorAction", "Syntax-Actions-CompleteActions-StartClassifierBehaviorAction", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Actions.CompleteActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-CompleteActions";   
                      
        // Syntax.Actions.CompleteActions.StartObjectBehaviorAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Actions.CompleteActions").getDelegate();       
    	clss  = factory.createClass("StartObjectBehaviorAction", "Syntax-Actions-CompleteActions-StartObjectBehaviorAction", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Actions.CompleteActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-CompleteActions";   
                      
        // Syntax.Actions.CompleteActions.ReduceAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Actions.CompleteActions").getDelegate();       
    	clss  = factory.createClass("ReduceAction", "Syntax-Actions-CompleteActions-ReduceAction", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Actions.CompleteActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-CompleteActions";   
                      
        // Syntax.Actions.CompleteActions.ReadExtentAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Actions.CompleteActions").getDelegate();       
    	clss  = factory.createClass("ReadExtentAction", "Syntax-Actions-CompleteActions-ReadExtentAction", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Actions.CompleteActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-CompleteActions";   
                      
        // Syntax.Actions.CompleteActions.ReadIsClassifiedObjectAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Actions.CompleteActions").getDelegate();       
    	clss  = factory.createClass("ReadIsClassifiedObjectAction", "Syntax-Actions-CompleteActions-ReadIsClassifiedObjectAction", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Actions.CompleteActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-CompleteActions";   
                      
        // Syntax.Actions.CompleteActions.ReclassifyObjectAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Actions.CompleteActions").getDelegate();       
    	clss  = factory.createClass("ReclassifyObjectAction", "Syntax-Actions-CompleteActions-ReclassifyObjectAction", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Actions.CompleteActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-CompleteActions";   
                      
        // Syntax.Actions.CompleteActions.AcceptEventAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Syntax.Actions.CompleteActions").getDelegate();       
    	clss  = factory.createClass("AcceptEventAction", "Syntax-Actions-CompleteActions-AcceptEventAction", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Syntax.Actions.CompleteActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Actions-BasicActions";   
                      
        // Semantics.Actions.BasicActions.SendSignalActionActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Actions.BasicActions").getDelegate();       
    	clss  = factory.createClass("SendSignalActionActivation", "Semantics-Actions-BasicActions-SendSignalActionActivation", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Actions.BasicActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Actions-BasicActions";   
                      
        // Semantics.Actions.BasicActions.PinActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Actions.BasicActions").getDelegate();       
    	clss  = factory.createClass("PinActivation", "Semantics-Actions-BasicActions-PinActivation", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "Semantics.Actions.BasicActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Actions-BasicActions";   
                      
        // Semantics.Actions.BasicActions.OutputPinActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Actions.BasicActions").getDelegate();       
    	clss  = factory.createClass("OutputPinActivation", "Semantics-Actions-BasicActions-OutputPinActivation", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Actions.BasicActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Actions-BasicActions";   
                      
        // Semantics.Actions.BasicActions.InvocationActionActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Actions.BasicActions").getDelegate();       
    	clss  = factory.createClass("InvocationActionActivation", "Semantics-Actions-BasicActions-InvocationActionActivation", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "Semantics.Actions.BasicActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Actions-BasicActions";   
                      
        // Semantics.Actions.BasicActions.InputPinActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Actions.BasicActions").getDelegate();       
    	clss  = factory.createClass("InputPinActivation", "Semantics-Actions-BasicActions-InputPinActivation", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Actions.BasicActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Actions-BasicActions";   
                      
        // Semantics.Actions.BasicActions.CallActionActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Actions.BasicActions").getDelegate();       
    	clss  = factory.createClass("CallActionActivation", "Semantics-Actions-BasicActions-CallActionActivation", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "Semantics.Actions.BasicActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Actions-BasicActions";   
                      
        // Semantics.Actions.BasicActions.CallBehaviorActionActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Actions.BasicActions").getDelegate();       
    	clss  = factory.createClass("CallBehaviorActionActivation", "Semantics-Actions-BasicActions-CallBehaviorActionActivation", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Actions.BasicActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Actions-BasicActions";   
                      
        // Semantics.Actions.BasicActions.CallOperationActionActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Actions.BasicActions").getDelegate();       
    	clss  = factory.createClass("CallOperationActionActivation", "Semantics-Actions-BasicActions-CallOperationActionActivation", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Actions.BasicActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Actions-BasicActions";   
                      
        // Semantics.Actions.BasicActions.ActionActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Actions.BasicActions").getDelegate();       
    	clss  = factory.createClass("ActionActivation", "Semantics-Actions-BasicActions-ActionActivation", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "Semantics.Actions.BasicActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Actions-IntermediateActions";   
                      
        // Semantics.Actions.IntermediateActions.WriteLinkActionActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Actions.IntermediateActions").getDelegate();       
    	clss  = factory.createClass("WriteLinkActionActivation", "Semantics-Actions-IntermediateActions-WriteLinkActionActivation", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "Semantics.Actions.IntermediateActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Actions-IntermediateActions";   
                      
        // Semantics.Actions.IntermediateActions.WriteStructuralFeatureActionActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Actions.IntermediateActions").getDelegate();       
    	clss  = factory.createClass("WriteStructuralFeatureActionActivation", "Semantics-Actions-IntermediateActions-WriteStructuralFeatureActionActivation", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "Semantics.Actions.IntermediateActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Actions-IntermediateActions";   
                      
        // Semantics.Actions.IntermediateActions.ValueSpecificationActionActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Actions.IntermediateActions").getDelegate();       
    	clss  = factory.createClass("ValueSpecificationActionActivation", "Semantics-Actions-IntermediateActions-ValueSpecificationActionActivation", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Actions.IntermediateActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Actions-IntermediateActions";   
                      
        // Semantics.Actions.IntermediateActions.TestIdentityActionActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Actions.IntermediateActions").getDelegate();       
    	clss  = factory.createClass("TestIdentityActionActivation", "Semantics-Actions-IntermediateActions-TestIdentityActionActivation", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Actions.IntermediateActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Actions-IntermediateActions";   
                      
        // Semantics.Actions.IntermediateActions.StructuralFeatureActionActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Actions.IntermediateActions").getDelegate();       
    	clss  = factory.createClass("StructuralFeatureActionActivation", "Semantics-Actions-IntermediateActions-StructuralFeatureActionActivation", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "Semantics.Actions.IntermediateActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Actions-IntermediateActions";   
                      
        // Semantics.Actions.IntermediateActions.RemoveStructuralFeatureValueActionActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Actions.IntermediateActions").getDelegate();       
    	clss  = factory.createClass("RemoveStructuralFeatureValueActionActivation", "Semantics-Actions-IntermediateActions-RemoveStructuralFeatureValueActionActivation", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Actions.IntermediateActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Actions-IntermediateActions";   
                      
        // Semantics.Actions.IntermediateActions.ReadLinkActionActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Actions.IntermediateActions").getDelegate();       
    	clss  = factory.createClass("ReadLinkActionActivation", "Semantics-Actions-IntermediateActions-ReadLinkActionActivation", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Actions.IntermediateActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Actions-IntermediateActions";   
                      
        // Semantics.Actions.IntermediateActions.ReadSelfActionActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Actions.IntermediateActions").getDelegate();       
    	clss  = factory.createClass("ReadSelfActionActivation", "Semantics-Actions-IntermediateActions-ReadSelfActionActivation", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Actions.IntermediateActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Actions-IntermediateActions";   
                      
        // Semantics.Actions.IntermediateActions.ReadStructuralFeatureActionActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Actions.IntermediateActions").getDelegate();       
    	clss  = factory.createClass("ReadStructuralFeatureActionActivation", "Semantics-Actions-IntermediateActions-ReadStructuralFeatureActionActivation", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Actions.IntermediateActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Actions-IntermediateActions";   
                      
        // Semantics.Actions.IntermediateActions.LinkActionActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Actions.IntermediateActions").getDelegate();       
    	clss  = factory.createClass("LinkActionActivation", "Semantics-Actions-IntermediateActions-LinkActionActivation", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "Semantics.Actions.IntermediateActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Actions-IntermediateActions";   
                      
        // Semantics.Actions.IntermediateActions.DestroyLinkActionActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Actions.IntermediateActions").getDelegate();       
    	clss  = factory.createClass("DestroyLinkActionActivation", "Semantics-Actions-IntermediateActions-DestroyLinkActionActivation", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Actions.IntermediateActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Actions-IntermediateActions";   
                      
        // Semantics.Actions.IntermediateActions.DestroyObjectActionActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Actions.IntermediateActions").getDelegate();       
    	clss  = factory.createClass("DestroyObjectActionActivation", "Semantics-Actions-IntermediateActions-DestroyObjectActionActivation", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Actions.IntermediateActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Actions-IntermediateActions";   
                      
        // Semantics.Actions.IntermediateActions.CreateLinkActionActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Actions.IntermediateActions").getDelegate();       
    	clss  = factory.createClass("CreateLinkActionActivation", "Semantics-Actions-IntermediateActions-CreateLinkActionActivation", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Actions.IntermediateActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Actions-IntermediateActions";   
                      
        // Semantics.Actions.IntermediateActions.CreateObjectActionActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Actions.IntermediateActions").getDelegate();       
    	clss  = factory.createClass("CreateObjectActionActivation", "Semantics-Actions-IntermediateActions-CreateObjectActionActivation", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Actions.IntermediateActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Actions-IntermediateActions";   
                      
        // Semantics.Actions.IntermediateActions.ClearAssociationActionActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Actions.IntermediateActions").getDelegate();       
    	clss  = factory.createClass("ClearAssociationActionActivation", "Semantics-Actions-IntermediateActions-ClearAssociationActionActivation", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Actions.IntermediateActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Actions-IntermediateActions";   
                      
        // Semantics.Actions.IntermediateActions.ClearStructuralFeatureActionActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Actions.IntermediateActions").getDelegate();       
    	clss  = factory.createClass("ClearStructuralFeatureActionActivation", "Semantics-Actions-IntermediateActions-ClearStructuralFeatureActionActivation", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Actions.IntermediateActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Actions-IntermediateActions";   
                      
        // Semantics.Actions.IntermediateActions.AddStructuralFeatureValueActionActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Actions.IntermediateActions").getDelegate();       
    	clss  = factory.createClass("AddStructuralFeatureValueActionActivation", "Semantics-Actions-IntermediateActions-AddStructuralFeatureValueActionActivation", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Actions.IntermediateActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Actions-CompleteActions";   
                      
        // Semantics.Actions.CompleteActions.StartClassifierBehaviorActionActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Actions.CompleteActions").getDelegate();       
    	clss  = factory.createClass("StartClassifierBehaviorActionActivation", "Semantics-Actions-CompleteActions-StartClassifierBehaviorActionActivation", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Actions.CompleteActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Actions-CompleteActions";   
                      
        // Semantics.Actions.CompleteActions.StartObjectBehaviorActionActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Actions.CompleteActions").getDelegate();       
    	clss  = factory.createClass("StartObjectBehaviorActionActivation", "Semantics-Actions-CompleteActions-StartObjectBehaviorActionActivation", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Actions.CompleteActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Actions-CompleteActions";   
                      
        // Semantics.Actions.CompleteActions.ReduceActionActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Actions.CompleteActions").getDelegate();       
    	clss  = factory.createClass("ReduceActionActivation", "Semantics-Actions-CompleteActions-ReduceActionActivation", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Actions.CompleteActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Actions-CompleteActions";   
                      
        // Semantics.Actions.CompleteActions.ReadIsClassifiedObjectActionActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Actions.CompleteActions").getDelegate();       
    	clss  = factory.createClass("ReadIsClassifiedObjectActionActivation", "Semantics-Actions-CompleteActions-ReadIsClassifiedObjectActionActivation", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Actions.CompleteActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Actions-CompleteActions";   
                      
        // Semantics.Actions.CompleteActions.ReclassifyObjectActionActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Actions.CompleteActions").getDelegate();       
    	clss  = factory.createClass("ReclassifyObjectActionActivation", "Semantics-Actions-CompleteActions-ReclassifyObjectActionActivation", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Actions.CompleteActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Actions-CompleteActions";   
                      
        // Semantics.Actions.CompleteActions.ReadExtentActionActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Actions.CompleteActions").getDelegate();       
    	clss  = factory.createClass("ReadExtentActionActivation", "Semantics-Actions-CompleteActions-ReadExtentActionActivation", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Actions.CompleteActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Actions-CompleteActions";   
                      
        // Semantics.Actions.CompleteActions.AcceptEventActionActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Actions.CompleteActions").getDelegate();       
    	clss  = factory.createClass("AcceptEventActionActivation", "Semantics-Actions-CompleteActions-AcceptEventActionActivation", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Actions.CompleteActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Actions-CompleteActions";   
                      
        // Semantics.Actions.CompleteActions.AcceptEventActionEventAccepter 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Actions.CompleteActions").getDelegate();       
    	clss  = factory.createClass("AcceptEventActionEventAccepter", "Semantics-Actions-CompleteActions-AcceptEventActionEventAccepter", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Actions.CompleteActions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Activities-CompleteStructuredActivities";   
                      
        // Semantics.Activities.CompleteStructuredActivities.StructuredActivityNodeActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Activities.CompleteStructuredActivities").getDelegate();       
    	clss  = factory.createClass("StructuredActivityNodeActivation", "Semantics-Activities-CompleteStructuredActivities-StructuredActivityNodeActivation", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Activities.CompleteStructuredActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Activities-CompleteStructuredActivities";   
                      
        // Semantics.Activities.CompleteStructuredActivities.LoopNodeActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Activities.CompleteStructuredActivities").getDelegate();       
    	clss  = factory.createClass("LoopNodeActivation", "Semantics-Activities-CompleteStructuredActivities-LoopNodeActivation", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Activities.CompleteStructuredActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Activities-CompleteStructuredActivities";   
                      
        // Semantics.Activities.CompleteStructuredActivities.ConditionalNodeActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Activities.CompleteStructuredActivities").getDelegate();       
    	clss  = factory.createClass("ConditionalNodeActivation", "Semantics-Activities-CompleteStructuredActivities-ConditionalNodeActivation", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Activities.CompleteStructuredActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Activities-CompleteStructuredActivities";   
                      
        // Semantics.Activities.CompleteStructuredActivities.ClauseActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Activities.CompleteStructuredActivities").getDelegate();       
    	clss  = factory.createClass("ClauseActivation", "Semantics-Activities-CompleteStructuredActivities-ClauseActivation", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Activities.CompleteStructuredActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Activities-CompleteStructuredActivities";   
                      
        // Semantics.Activities.CompleteStructuredActivities.Values 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Activities.CompleteStructuredActivities").getDelegate();       
    	clss  = factory.createClass("Values", "Semantics-Activities-CompleteStructuredActivities-Values", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Activities.CompleteStructuredActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Activities-IntermediateActivities";   
                      
        // Semantics.Activities.IntermediateActivities.Token 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Activities.IntermediateActivities").getDelegate();       
    	clss  = factory.createClass("Token", "Semantics-Activities-IntermediateActivities-Token", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "Semantics.Activities.IntermediateActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Activities-IntermediateActivities";   
                      
        // Semantics.Activities.IntermediateActivities.Offer 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Activities.IntermediateActivities").getDelegate();       
    	clss  = factory.createClass("Offer", "Semantics-Activities-IntermediateActivities-Offer", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Activities.IntermediateActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Activities-IntermediateActivities";   
                      
        // Semantics.Activities.IntermediateActivities.ObjectToken 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Activities.IntermediateActivities").getDelegate();       
    	clss  = factory.createClass("ObjectToken", "Semantics-Activities-IntermediateActivities-ObjectToken", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Activities.IntermediateActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Activities-IntermediateActivities";   
                      
        // Semantics.Activities.IntermediateActivities.ObjectNodeActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Activities.IntermediateActivities").getDelegate();       
    	clss  = factory.createClass("ObjectNodeActivation", "Semantics-Activities-IntermediateActivities-ObjectNodeActivation", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "Semantics.Activities.IntermediateActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Activities-IntermediateActivities";   
                      
        // Semantics.Activities.IntermediateActivities.MergeNodeActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Activities.IntermediateActivities").getDelegate();       
    	clss  = factory.createClass("MergeNodeActivation", "Semantics-Activities-IntermediateActivities-MergeNodeActivation", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Activities.IntermediateActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Activities-IntermediateActivities";   
                      
        // Semantics.Activities.IntermediateActivities.JoinNodeActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Activities.IntermediateActivities").getDelegate();       
    	clss  = factory.createClass("JoinNodeActivation", "Semantics-Activities-IntermediateActivities-JoinNodeActivation", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Activities.IntermediateActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Activities-IntermediateActivities";   
                      
        // Semantics.Activities.IntermediateActivities.ForkedToken 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Activities.IntermediateActivities").getDelegate();       
    	clss  = factory.createClass("ForkedToken", "Semantics-Activities-IntermediateActivities-ForkedToken", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Activities.IntermediateActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Activities-IntermediateActivities";   
                      
        // Semantics.Activities.IntermediateActivities.ForkNodeActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Activities.IntermediateActivities").getDelegate();       
    	clss  = factory.createClass("ForkNodeActivation", "Semantics-Activities-IntermediateActivities-ForkNodeActivation", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Activities.IntermediateActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Activities-IntermediateActivities";   
                      
        // Semantics.Activities.IntermediateActivities.InitialNodeActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Activities.IntermediateActivities").getDelegate();       
    	clss  = factory.createClass("InitialNodeActivation", "Semantics-Activities-IntermediateActivities-InitialNodeActivation", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Activities.IntermediateActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Activities-IntermediateActivities";   
                      
        // Semantics.Activities.IntermediateActivities.DecisionNodeActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Activities.IntermediateActivities").getDelegate();       
    	clss  = factory.createClass("DecisionNodeActivation", "Semantics-Activities-IntermediateActivities-DecisionNodeActivation", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Activities.IntermediateActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Activities-IntermediateActivities";   
                      
        // Semantics.Activities.IntermediateActivities.ControlToken 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Activities.IntermediateActivities").getDelegate();       
    	clss  = factory.createClass("ControlToken", "Semantics-Activities-IntermediateActivities-ControlToken", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Activities.IntermediateActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Activities-IntermediateActivities";   
                      
        // Semantics.Activities.IntermediateActivities.ControlNodeActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Activities.IntermediateActivities").getDelegate();       
    	clss  = factory.createClass("ControlNodeActivation", "Semantics-Activities-IntermediateActivities-ControlNodeActivation", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "Semantics.Activities.IntermediateActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Activities-IntermediateActivities";   
                      
        // Semantics.Activities.IntermediateActivities.ActivityEdgeInstance 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Activities.IntermediateActivities").getDelegate();       
    	clss  = factory.createClass("ActivityEdgeInstance", "Semantics-Activities-IntermediateActivities-ActivityEdgeInstance", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Activities.IntermediateActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Activities-IntermediateActivities";   
                      
        // Semantics.Activities.IntermediateActivities.ActivityExecution 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Activities.IntermediateActivities").getDelegate();       
    	clss  = factory.createClass("ActivityExecution", "Semantics-Activities-IntermediateActivities-ActivityExecution", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Activities.IntermediateActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Activities-IntermediateActivities";   
                      
        // Semantics.Activities.IntermediateActivities.ActivityFinalNodeActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Activities.IntermediateActivities").getDelegate();       
    	clss  = factory.createClass("ActivityFinalNodeActivation", "Semantics-Activities-IntermediateActivities-ActivityFinalNodeActivation", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Activities.IntermediateActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Activities-IntermediateActivities";   
                      
        // Semantics.Activities.IntermediateActivities.ActivityNodeActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Activities.IntermediateActivities").getDelegate();       
    	clss  = factory.createClass("ActivityNodeActivation", "Semantics-Activities-IntermediateActivities-ActivityNodeActivation", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "Semantics.Activities.IntermediateActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Activities-IntermediateActivities";   
                      
        // Semantics.Activities.IntermediateActivities.ActivityNodeActivationGroup 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Activities.IntermediateActivities").getDelegate();       
    	clss  = factory.createClass("ActivityNodeActivationGroup", "Semantics-Activities-IntermediateActivities-ActivityNodeActivationGroup", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Activities.IntermediateActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Activities-IntermediateActivities";   
                      
        // Semantics.Activities.IntermediateActivities.ActivityParameterNodeActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Activities.IntermediateActivities").getDelegate();       
    	clss  = factory.createClass("ActivityParameterNodeActivation", "Semantics-Activities-IntermediateActivities-ActivityParameterNodeActivation", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Activities.IntermediateActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Activities-ExtraStructuredActivities";   
                      
        // Semantics.Activities.ExtraStructuredActivities.ExpansionActivationGroup 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Activities.ExtraStructuredActivities").getDelegate();       
    	clss  = factory.createClass("ExpansionActivationGroup", "Semantics-Activities-ExtraStructuredActivities-ExpansionActivationGroup", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Activities.ExtraStructuredActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Activities-ExtraStructuredActivities";   
                      
        // Semantics.Activities.ExtraStructuredActivities.ExpansionNodeActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Activities.ExtraStructuredActivities").getDelegate();       
    	clss  = factory.createClass("ExpansionNodeActivation", "Semantics-Activities-ExtraStructuredActivities-ExpansionNodeActivation", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Activities.ExtraStructuredActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Activities-ExtraStructuredActivities";   
                      
        // Semantics.Activities.ExtraStructuredActivities.ExpansionRegionActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Activities.ExtraStructuredActivities").getDelegate();       
    	clss  = factory.createClass("ExpansionRegionActivation", "Semantics-Activities-ExtraStructuredActivities-ExpansionRegionActivation", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Activities.ExtraStructuredActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Activities-ExtraStructuredActivities";   
                      
        // Semantics.Activities.ExtraStructuredActivities.TokenSet 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Activities.ExtraStructuredActivities").getDelegate();       
    	clss  = factory.createClass("TokenSet", "Semantics-Activities-ExtraStructuredActivities-TokenSet", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Activities.ExtraStructuredActivities", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Classes-Kernel";   
                      
        // Semantics.Classes.Kernel.StructuredValue 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("StructuredValue", "Semantics-Classes-Kernel-StructuredValue", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "Semantics.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Classes-Kernel";   
                      
        // Semantics.Classes.Kernel.UnlimitedNaturalValue 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("UnlimitedNaturalValue", "Semantics-Classes-Kernel-UnlimitedNaturalValue", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Classes-Kernel";   
                      
        // Semantics.Classes.Kernel.Value 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("Value", "Semantics-Classes-Kernel-Value", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "Semantics.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Classes-Kernel";   
                      
        // Semantics.Classes.Kernel.StringValue 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("StringValue", "Semantics-Classes-Kernel-StringValue", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Classes-Kernel";   
                      
        // Semantics.Classes.Kernel.Reference 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("Reference", "Semantics-Classes-Kernel-Reference", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Classes-Kernel";   
                      
        // Semantics.Classes.Kernel.PrimitiveValue 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("PrimitiveValue", "Semantics-Classes-Kernel-PrimitiveValue", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "Semantics.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Classes-Kernel";   
                      
        // Semantics.Classes.Kernel.Object 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("Object", "Semantics-Classes-Kernel-Object", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Classes-Kernel";   
                      
        // Semantics.Classes.Kernel.LiteralBooleanEvaluation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("LiteralBooleanEvaluation", "Semantics-Classes-Kernel-LiteralBooleanEvaluation", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Classes-Kernel";   
                      
        // Semantics.Classes.Kernel.LiteralEvaluation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("LiteralEvaluation", "Semantics-Classes-Kernel-LiteralEvaluation", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "Semantics.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Classes-Kernel";   
                      
        // Semantics.Classes.Kernel.LiteralIntegerEvaluation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("LiteralIntegerEvaluation", "Semantics-Classes-Kernel-LiteralIntegerEvaluation", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Classes-Kernel";   
                      
        // Semantics.Classes.Kernel.LiteralNullEvaluation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("LiteralNullEvaluation", "Semantics-Classes-Kernel-LiteralNullEvaluation", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Classes-Kernel";   
                      
        // Semantics.Classes.Kernel.LiteralStringEvaluation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("LiteralStringEvaluation", "Semantics-Classes-Kernel-LiteralStringEvaluation", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Classes-Kernel";   
                      
        // Semantics.Classes.Kernel.LiteralUnlimitedNaturalEvaluation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("LiteralUnlimitedNaturalEvaluation", "Semantics-Classes-Kernel-LiteralUnlimitedNaturalEvaluation", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Classes-Kernel";   
                      
        // Semantics.Classes.Kernel.Link 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("Link", "Semantics-Classes-Kernel-Link", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Classes-Kernel";   
                      
        // Semantics.Classes.Kernel.InstanceValueEvaluation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("InstanceValueEvaluation", "Semantics-Classes-Kernel-InstanceValueEvaluation", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Classes-Kernel";   
                      
        // Semantics.Classes.Kernel.IntegerValue 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("IntegerValue", "Semantics-Classes-Kernel-IntegerValue", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Classes-Kernel";   
                      
        // Semantics.Classes.Kernel.FeatureValue 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("FeatureValue", "Semantics-Classes-Kernel-FeatureValue", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Classes-Kernel";   
                      
        // Semantics.Classes.Kernel.ExtensionalValue 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("ExtensionalValue", "Semantics-Classes-Kernel-ExtensionalValue", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "Semantics.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Classes-Kernel";   
                      
        // Semantics.Classes.Kernel.Evaluation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("Evaluation", "Semantics-Classes-Kernel-Evaluation", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "Semantics.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Classes-Kernel";   
                      
        // Semantics.Classes.Kernel.EnumerationValue 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("EnumerationValue", "Semantics-Classes-Kernel-EnumerationValue", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Classes-Kernel";   
                      
        // Semantics.Classes.Kernel.DataValue 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("DataValue", "Semantics-Classes-Kernel-DataValue", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Classes-Kernel";   
                      
        // Semantics.Classes.Kernel.CompoundValue 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("CompoundValue", "Semantics-Classes-Kernel-CompoundValue", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "Semantics.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Classes-Kernel";   
                      
        // Semantics.Classes.Kernel.BooleanValue 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("BooleanValue", "Semantics-Classes-Kernel-BooleanValue", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Classes-Kernel";   
                      
        // Semantics.Classes.Kernel.DispatchStrategy 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("DispatchStrategy", "Semantics-Classes-Kernel-DispatchStrategy", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "Semantics.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Classes-Kernel";   
                      
        // Semantics.Classes.Kernel.RedefinitionBasedDispatchStrategy 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Classes.Kernel").getDelegate();       
    	clss  = factory.createClass("RedefinitionBasedDispatchStrategy", "Semantics-Classes-Kernel-RedefinitionBasedDispatchStrategy", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Classes.Kernel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-CommonBehaviors-BasicBehaviors";   
                      
        // Semantics.CommonBehaviors.BasicBehaviors.ParameterValue 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.CommonBehaviors.BasicBehaviors").getDelegate();       
    	clss  = factory.createClass("ParameterValue", "Semantics-CommonBehaviors-BasicBehaviors-ParameterValue", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.CommonBehaviors.BasicBehaviors", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-CommonBehaviors-BasicBehaviors";   
                      
        // Semantics.CommonBehaviors.BasicBehaviors.OpaqueBehaviorExecution 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.CommonBehaviors.BasicBehaviors").getDelegate();       
    	clss  = factory.createClass("OpaqueBehaviorExecution", "Semantics-CommonBehaviors-BasicBehaviors-OpaqueBehaviorExecution", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "Semantics.CommonBehaviors.BasicBehaviors", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-CommonBehaviors-BasicBehaviors";   
                      
        // Semantics.CommonBehaviors.BasicBehaviors.Execution 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.CommonBehaviors.BasicBehaviors").getDelegate();       
    	clss  = factory.createClass("Execution", "Semantics-CommonBehaviors-BasicBehaviors-Execution", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "Semantics.CommonBehaviors.BasicBehaviors", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-CommonBehaviors-Communications";   
                      
        // Semantics.CommonBehaviors.Communications.EventAccepter 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.CommonBehaviors.Communications").getDelegate();       
    	clss  = factory.createClass("EventAccepter", "Semantics-CommonBehaviors-Communications-EventAccepter", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "Semantics.CommonBehaviors.Communications", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-CommonBehaviors-Communications";   
                      
        // Semantics.CommonBehaviors.Communications.ClassifierBehaviorExecution 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.CommonBehaviors.Communications").getDelegate();       
    	clss  = factory.createClass("ClassifierBehaviorExecution", "Semantics-CommonBehaviors-Communications-ClassifierBehaviorExecution", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.CommonBehaviors.Communications", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-CommonBehaviors-Communications";   
                      
        // Semantics.CommonBehaviors.Communications.SignalInstance 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.CommonBehaviors.Communications").getDelegate();       
    	clss  = factory.createClass("SignalInstance", "Semantics-CommonBehaviors-Communications-SignalInstance", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.CommonBehaviors.Communications", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-CommonBehaviors-Communications";   
                      
        // Semantics.CommonBehaviors.Communications.ObjectActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.CommonBehaviors.Communications").getDelegate();       
    	clss  = factory.createClass("ObjectActivation", "Semantics-CommonBehaviors-Communications-ObjectActivation", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.CommonBehaviors.Communications", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-CommonBehaviors-Communications";   
                      
        // Semantics.CommonBehaviors.Communications.GetNextEventStrategy 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.CommonBehaviors.Communications").getDelegate();       
    	clss  = factory.createClass("GetNextEventStrategy", "Semantics-CommonBehaviors-Communications-GetNextEventStrategy", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "Semantics.CommonBehaviors.Communications", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-CommonBehaviors-Communications";   
                      
        // Semantics.CommonBehaviors.Communications.FIFOGetNextEventStrategy 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.CommonBehaviors.Communications").getDelegate();       
    	clss  = factory.createClass("FIFOGetNextEventStrategy", "Semantics-CommonBehaviors-Communications-FIFOGetNextEventStrategy", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.CommonBehaviors.Communications", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Loci-LociL1";   
                      
        // Semantics.Loci.LociL1.ChoiceStrategy 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Loci.LociL1").getDelegate();       
    	clss  = factory.createClass("ChoiceStrategy", "Semantics-Loci-LociL1-ChoiceStrategy", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "Semantics.Loci.LociL1", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Loci-LociL1";   
                      
        // Semantics.Loci.LociL1.ExecutionFactory 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Loci.LociL1").getDelegate();       
    	clss  = factory.createClass("ExecutionFactory", "Semantics-Loci-LociL1-ExecutionFactory", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "Semantics.Loci.LociL1", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Loci-LociL1";   
                      
        // Semantics.Loci.LociL1.Executor 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Loci.LociL1").getDelegate();       
    	clss  = factory.createClass("Executor", "Semantics-Loci-LociL1-Executor", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Loci.LociL1", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Loci-LociL1";   
                      
        // Semantics.Loci.LociL1.FirstChoiceStrategy 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Loci.LociL1").getDelegate();       
    	clss  = factory.createClass("FirstChoiceStrategy", "Semantics-Loci-LociL1-FirstChoiceStrategy", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Loci.LociL1", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Loci-LociL1";   
                      
        // Semantics.Loci.LociL1.Locus 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Loci.LociL1").getDelegate();       
    	clss  = factory.createClass("Locus", "Semantics-Loci-LociL1-Locus", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Loci.LociL1", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Loci-LociL1";   
                      
        // Semantics.Loci.LociL1.SemanticStrategy 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Loci.LociL1").getDelegate();       
    	clss  = factory.createClass("SemanticStrategy", "Semantics-Loci-LociL1-SemanticStrategy", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "Semantics.Loci.LociL1", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Loci-LociL1";   
                      
        // Semantics.Loci.LociL1.SemanticVisitor 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Loci.LociL1").getDelegate();       
    	clss  = factory.createClass("SemanticVisitor", "Semantics-Loci-LociL1-SemanticVisitor", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "Semantics.Loci.LociL1", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Loci-LociL1";   
                      
        // Semantics.Loci.LociL1.ExecutionFactoryL1 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Loci.LociL1").getDelegate();       
    	clss  = factory.createClass("ExecutionFactoryL1", "Semantics-Loci-LociL1-ExecutionFactoryL1", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Loci.LociL1", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Loci-LociL2";   
                      
        // Semantics.Loci.LociL2.ExecutionFactoryL2 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Loci.LociL2").getDelegate();       
    	clss  = factory.createClass("ExecutionFactoryL2", "Semantics-Loci-LociL2-ExecutionFactoryL2", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Loci.LociL2", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Loci-LociL3";   
                      
        // Semantics.Loci.LociL3.ExecutionFactoryL3 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("Semantics.Loci.LociL3").getDelegate();       
    	clss  = factory.createClass("ExecutionFactoryL3", "Semantics-Loci-LociL3-ExecutionFactoryL3", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "Semantics.Loci.LociL3", this); 
    
    }   

    private void constructEnumerations()
    {
        Enumeration enumeration = null;
        EnumerationLiteral literal = null;
                                                      
        // CallConcurrencyKind
    	enumeration  = factory.createEnumeration("CallConcurrencyKind", "Syntax-CommonBehaviors-BasicBehaviors-CallConcurrencyKind");
    	mapping.mapEnumeration(enumeration, "Syntax.CommonBehaviors.BasicBehaviors", this); 
        
        literal = factory.createEnumerationLiteral(enumeration, 
            "sequential",
            "Syntax-CommonBehaviors-BasicBehaviors-CallConcurrencyKind-sequential");
    	mapping.mapEnumerationLiteral(literal, "Syntax.CommonBehaviors.BasicBehaviors", this); 
                                                          
        // AggregationKind
    	enumeration  = factory.createEnumeration("AggregationKind", "Syntax-Classes-Kernel-AggregationKind");
    	mapping.mapEnumeration(enumeration, "Syntax.Classes.Kernel", this); 
        
        literal = factory.createEnumerationLiteral(enumeration, 
            "none",
            "Syntax-Classes-Kernel-AggregationKind-none");
    	mapping.mapEnumerationLiteral(literal, "Syntax.Classes.Kernel", this); 
        
        literal = factory.createEnumerationLiteral(enumeration, 
            "shared",
            "Syntax-Classes-Kernel-AggregationKind-shared");
    	mapping.mapEnumerationLiteral(literal, "Syntax.Classes.Kernel", this); 
        
        literal = factory.createEnumerationLiteral(enumeration, 
            "composite",
            "Syntax-Classes-Kernel-AggregationKind-composite");
    	mapping.mapEnumerationLiteral(literal, "Syntax.Classes.Kernel", this); 
                                                          
        // ParameterDirectionKind
    	enumeration  = factory.createEnumeration("ParameterDirectionKind", "Syntax-Classes-Kernel-ParameterDirectionKind");
    	mapping.mapEnumeration(enumeration, "Syntax.Classes.Kernel", this); 
        
        literal = factory.createEnumerationLiteral(enumeration, 
            "in",
            "Syntax-Classes-Kernel-ParameterDirectionKind-in");
    	mapping.mapEnumerationLiteral(literal, "Syntax.Classes.Kernel", this); 
        
        literal = factory.createEnumerationLiteral(enumeration, 
            "inout",
            "Syntax-Classes-Kernel-ParameterDirectionKind-inout");
    	mapping.mapEnumerationLiteral(literal, "Syntax.Classes.Kernel", this); 
        
        literal = factory.createEnumerationLiteral(enumeration, 
            "out",
            "Syntax-Classes-Kernel-ParameterDirectionKind-out");
    	mapping.mapEnumerationLiteral(literal, "Syntax.Classes.Kernel", this); 
        
        literal = factory.createEnumerationLiteral(enumeration, 
            "return",
            "Syntax-Classes-Kernel-ParameterDirectionKind-return");
    	mapping.mapEnumerationLiteral(literal, "Syntax.Classes.Kernel", this); 
                                                          
        // VisibilityKind
    	enumeration  = factory.createEnumeration("VisibilityKind", "Syntax-Classes-Kernel-VisibilityKind");
    	mapping.mapEnumeration(enumeration, "Syntax.Classes.Kernel", this); 
        
        literal = factory.createEnumerationLiteral(enumeration, 
            "public",
            "Syntax-Classes-Kernel-VisibilityKind-public");
    	mapping.mapEnumerationLiteral(literal, "Syntax.Classes.Kernel", this); 
        
        literal = factory.createEnumerationLiteral(enumeration, 
            "private",
            "Syntax-Classes-Kernel-VisibilityKind-private");
    	mapping.mapEnumerationLiteral(literal, "Syntax.Classes.Kernel", this); 
        
        literal = factory.createEnumerationLiteral(enumeration, 
            "protected",
            "Syntax-Classes-Kernel-VisibilityKind-protected");
    	mapping.mapEnumerationLiteral(literal, "Syntax.Classes.Kernel", this); 
        
        literal = factory.createEnumerationLiteral(enumeration, 
            "package",
            "Syntax-Classes-Kernel-VisibilityKind-package");
    	mapping.mapEnumerationLiteral(literal, "Syntax.Classes.Kernel", this); 
                                                          
        // ExpansionKind
    	enumeration  = factory.createEnumeration("ExpansionKind", "Syntax-Activities-ExtraStructuredActivities-ExpansionKind");
    	mapping.mapEnumeration(enumeration, "Syntax.Activities.ExtraStructuredActivities", this); 
        
        literal = factory.createEnumerationLiteral(enumeration, 
            "parallel",
            "Syntax-Activities-ExtraStructuredActivities-ExpansionKind-parallel");
    	mapping.mapEnumerationLiteral(literal, "Syntax.Activities.ExtraStructuredActivities", this); 
        
        literal = factory.createEnumerationLiteral(enumeration, 
            "iterative",
            "Syntax-Activities-ExtraStructuredActivities-ExpansionKind-iterative");
    	mapping.mapEnumerationLiteral(literal, "Syntax.Activities.ExtraStructuredActivities", this); 
        
        literal = factory.createEnumerationLiteral(enumeration, 
            "stream",
            "Syntax-Activities-ExtraStructuredActivities-ExpansionKind-stream");
    	mapping.mapEnumerationLiteral(literal, "Syntax.Activities.ExtraStructuredActivities", this); 
        
    }   

    private void constructProperties()
    {
        Class_ clss = null;
        Property prop = null;
        
    
        // OpaqueBehavior
    	clss  = (Class_)model.getElementById("Syntax-CommonBehaviors-BasicBehaviors-OpaqueBehavior").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "body", 
    	    "Syntax-CommonBehaviors-BasicBehaviors-OpaqueBehavior-body",
    	    "http://www.omg.org/spec/UML/20090901/uml.xml#String",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "language", 
    	    "Syntax-CommonBehaviors-BasicBehaviors-OpaqueBehavior-language",
    	    "http://www.omg.org/spec/UML/20090901/uml.xml#String",
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
    	    "http://www.omg.org/spec/UML/20090901/uml.xml#Boolean",
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
    	    "http://www.omg.org/spec/UML/20090901/uml.xml#Boolean",
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
    	    "http://www.omg.org/spec/UML/20090901/uml.xml#Boolean",
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
    	    "http://www.omg.org/spec/UML/20090901/uml.xml#Boolean",
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
    	    "http://www.omg.org/spec/UML/20090901/uml.xml#Boolean",
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
    	    "http://www.omg.org/spec/UML/20090901/uml.xml#Integer",
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
    	    "http://www.omg.org/spec/UML/20090901/uml.xml#String",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // LiteralUnlimitedNatural
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-LiteralUnlimitedNatural").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "value", 
    	    "Syntax-Classes-Kernel-LiteralUnlimitedNatural-value",
    	    "http://www.omg.org/spec/UML/20090901/uml.xml#UnlimitedNatural",
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
    	
        // MultiplicityElement
    	clss  = (Class_)model.getElementById("Syntax-Classes-Kernel-MultiplicityElement").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "isOrdered", 
    	    "Syntax-Classes-Kernel-MultiplicityElement-isOrdered",
    	    "http://www.omg.org/spec/UML/20090901/uml.xml#Boolean",
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
    	    "http://www.omg.org/spec/UML/20090901/uml.xml#Boolean",
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
    	    "http://www.omg.org/spec/UML/20090901/uml.xml#UnlimitedNatural",
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
    	    "http://www.omg.org/spec/UML/20090901/uml.xml#Integer",
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
    	    "http://www.omg.org/spec/UML/20090901/uml.xml#String",
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
    	    "http://www.omg.org/spec/UML/20090901/uml.xml#String",
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
    	    "http://www.omg.org/spec/UML/20090901/uml.xml#Boolean",
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
    	    "http://www.omg.org/spec/UML/20090901/uml.xml#Boolean",
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
    	    "http://www.omg.org/spec/UML/20090901/uml.xml#Boolean",
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
    	    "http://www.omg.org/spec/UML/20090901/uml.xml#Integer",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "upper", 
    	    "Syntax-Classes-Kernel-Operation-upper",
    	    "http://www.omg.org/spec/UML/20090901/uml.xml#UnlimitedNatural",
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
    	    "http://www.omg.org/spec/UML/20090901/uml.xml#Boolean",
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
    	    "http://www.omg.org/spec/UML/20090901/uml.xml#Boolean",
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
    	    "http://www.omg.org/spec/UML/20090901/uml.xml#Boolean",
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
    	    "http://www.omg.org/spec/UML/20090901/uml.xml#Boolean",
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
    	    "http://www.omg.org/spec/UML/20090901/uml.xml#Boolean",
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
    	    "http://www.omg.org/spec/UML/20090901/uml.xml#Boolean",
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
    	    "http://www.omg.org/spec/UML/20090901/uml.xml#Boolean",
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
    	    "http://www.omg.org/spec/UML/20090901/uml.xml#Boolean",
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
    	    "http://www.omg.org/spec/UML/20090901/uml.xml#Boolean",
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
    	    "http://www.omg.org/spec/UML/20090901/uml.xml#Boolean",
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
    	    "http://www.omg.org/spec/UML/20090901/uml.xml#Boolean",
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
    	    "http://www.omg.org/spec/UML/20090901/uml.xml#String",
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
    	    "http://www.omg.org/spec/UML/20090901/uml.xml#String",
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
    	    "http://www.omg.org/spec/UML/20090901/uml.xml#Boolean",
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
    	    "http://www.omg.org/spec/UML/20090901/uml.xml#Boolean",
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
    	    "http://www.omg.org/spec/UML/20090901/uml.xml#Boolean",
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
    	    "http://www.omg.org/spec/UML/20090901/uml.xml#Boolean",
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
    	    "http://www.omg.org/spec/UML/20090901/uml.xml#Boolean",
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
    	    "http://www.omg.org/spec/UML/20090901/uml.xml#Boolean",
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
    	    "http://www.omg.org/spec/UML/20090901/uml.xml#Boolean",
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
    	    "http://www.omg.org/spec/UML/20090901/uml.xml#Boolean",
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
    	    "http://www.omg.org/spec/UML/20090901/uml.xml#Boolean",
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
    	    "http://www.omg.org/spec/UML/20090901/uml.xml#Boolean",
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
    	    "http://www.omg.org/spec/UML/20090901/uml.xml#Boolean",
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
    	    "http://www.omg.org/spec/UML/20090901/uml.xml#Boolean",
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
    	    "http://www.omg.org/spec/UML/20090901/uml.xml#Boolean",
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
    	    "http://www.omg.org/spec/UML/20090901/uml.xml#Boolean",
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
    	    "http://www.omg.org/spec/UML/20090901/uml.xml#Boolean",
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
    	    "http://www.omg.org/spec/UML/20090901/uml.xml#Boolean",
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
    	    "http://www.omg.org/spec/UML/20090901/uml.xml#Boolean",
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
     	
    	
        // SendSignalActionActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-BasicActions-SendSignalActionActivation").getDelegate();
    	
        // PinActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-BasicActions-PinActivation").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "actionActivation", 
    	    "Semantics-Actions-BasicActions-PinActivation-actionActivation",
    	    "Semantics-Actions-BasicActions-ActionActivation",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // OutputPinActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-BasicActions-OutputPinActivation").getDelegate();
    	
        // InvocationActionActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-BasicActions-InvocationActionActivation").getDelegate();
    	
        // InputPinActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-BasicActions-InputPinActivation").getDelegate();
    	
        // CallActionActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-BasicActions-CallActionActivation").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "callExecutions", 
    	    "Semantics-Actions-BasicActions-CallActionActivation-callExecutions",
    	    "Semantics-CommonBehaviors-BasicBehaviors-Execution",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // CallBehaviorActionActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-BasicActions-CallBehaviorActionActivation").getDelegate();
    	
        // CallOperationActionActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-BasicActions-CallOperationActionActivation").getDelegate();
    	
        // ActionActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-BasicActions-ActionActivation").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "pinActivations", 
    	    "Semantics-Actions-BasicActions-ActionActivation-pinActivations",
    	    "Semantics-Actions-BasicActions-PinActivation",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "firing", 
    	    "Semantics-Actions-BasicActions-ActionActivation-firing",
    	    "http://www.omg.org/spec/UML/20090901/uml.xml#Boolean",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // WriteLinkActionActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-IntermediateActions-WriteLinkActionActivation").getDelegate();
    	
        // WriteStructuralFeatureActionActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-IntermediateActions-WriteStructuralFeatureActionActivation").getDelegate();
    	
        // ValueSpecificationActionActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-IntermediateActions-ValueSpecificationActionActivation").getDelegate();
    	
        // TestIdentityActionActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-IntermediateActions-TestIdentityActionActivation").getDelegate();
    	
        // StructuralFeatureActionActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-IntermediateActions-StructuralFeatureActionActivation").getDelegate();
    	
        // RemoveStructuralFeatureValueActionActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-IntermediateActions-RemoveStructuralFeatureValueActionActivation").getDelegate();
    	
        // ReadLinkActionActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-IntermediateActions-ReadLinkActionActivation").getDelegate();
    	
        // ReadSelfActionActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-IntermediateActions-ReadSelfActionActivation").getDelegate();
    	
        // ReadStructuralFeatureActionActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-IntermediateActions-ReadStructuralFeatureActionActivation").getDelegate();
    	
        // LinkActionActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-IntermediateActions-LinkActionActivation").getDelegate();
    	
        // DestroyLinkActionActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-IntermediateActions-DestroyLinkActionActivation").getDelegate();
    	
        // DestroyObjectActionActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-IntermediateActions-DestroyObjectActionActivation").getDelegate();
    	
        // CreateLinkActionActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-IntermediateActions-CreateLinkActionActivation").getDelegate();
    	
        // CreateObjectActionActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-IntermediateActions-CreateObjectActionActivation").getDelegate();
    	
        // ClearAssociationActionActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-IntermediateActions-ClearAssociationActionActivation").getDelegate();
    	
        // ClearStructuralFeatureActionActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-IntermediateActions-ClearStructuralFeatureActionActivation").getDelegate();
    	
        // AddStructuralFeatureValueActionActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-IntermediateActions-AddStructuralFeatureValueActionActivation").getDelegate();
    	
        // StartClassifierBehaviorActionActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-CompleteActions-StartClassifierBehaviorActionActivation").getDelegate();
    	
        // StartObjectBehaviorActionActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-CompleteActions-StartObjectBehaviorActionActivation").getDelegate();
    	
        // ReduceActionActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-CompleteActions-ReduceActionActivation").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "currentExecution", 
    	    "Semantics-Actions-CompleteActions-ReduceActionActivation-currentExecution",
    	    "Semantics-CommonBehaviors-BasicBehaviors-Execution",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // ReadIsClassifiedObjectActionActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-CompleteActions-ReadIsClassifiedObjectActionActivation").getDelegate();
    	
        // ReclassifyObjectActionActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-CompleteActions-ReclassifyObjectActionActivation").getDelegate();
    	
        // ReadExtentActionActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-CompleteActions-ReadExtentActionActivation").getDelegate();
    	
        // AcceptEventActionActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-CompleteActions-AcceptEventActionActivation").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "eventAccepter", 
    	    "Semantics-Actions-CompleteActions-AcceptEventActionActivation-eventAccepter",
    	    "Semantics-Actions-CompleteActions-AcceptEventActionEventAccepter",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "waiting", 
    	    "Semantics-Actions-CompleteActions-AcceptEventActionActivation-waiting",
    	    "http://www.omg.org/spec/UML/20090901/uml.xml#Boolean",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // AcceptEventActionEventAccepter
    	clss  = (Class_)model.getElementById("Semantics-Actions-CompleteActions-AcceptEventActionEventAccepter").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "actionActivation", 
    	    "Semantics-Actions-CompleteActions-AcceptEventActionEventAccepter-actionActivation",
    	    "Semantics-Actions-CompleteActions-AcceptEventActionActivation",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // StructuredActivityNodeActivation
    	clss  = (Class_)model.getElementById("Semantics-Activities-CompleteStructuredActivities-StructuredActivityNodeActivation").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "activationGroup", 
    	    "Semantics-Activities-CompleteStructuredActivities-StructuredActivityNodeActivation-activationGroup",
    	    "Semantics-Activities-IntermediateActivities-ActivityNodeActivationGroup",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // LoopNodeActivation
    	clss  = (Class_)model.getElementById("Semantics-Activities-CompleteStructuredActivities-LoopNodeActivation").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "bodyOutputLists", 
    	    "Semantics-Activities-CompleteStructuredActivities-LoopNodeActivation-bodyOutputLists",
    	    "Semantics-Activities-CompleteStructuredActivities-Values",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // ConditionalNodeActivation
    	clss  = (Class_)model.getElementById("Semantics-Activities-CompleteStructuredActivities-ConditionalNodeActivation").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "clauseActivations", 
    	    "Semantics-Activities-CompleteStructuredActivities-ConditionalNodeActivation-clauseActivations",
    	    "Semantics-Activities-CompleteStructuredActivities-ClauseActivation",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "selectedClauses", 
    	    "Semantics-Activities-CompleteStructuredActivities-ConditionalNodeActivation-selectedClauses",
    	    "Syntax-Activities-CompleteStructuredActivities-Clause",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // ClauseActivation
    	clss  = (Class_)model.getElementById("Semantics-Activities-CompleteStructuredActivities-ClauseActivation").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "conditionalNodeActivation", 
    	    "Semantics-Activities-CompleteStructuredActivities-ClauseActivation-conditionalNodeActivation",
    	    "Semantics-Activities-CompleteStructuredActivities-ConditionalNodeActivation",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "clause", 
    	    "Semantics-Activities-CompleteStructuredActivities-ClauseActivation-clause",
    	    "Syntax-Activities-CompleteStructuredActivities-Clause",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // Values
    	clss  = (Class_)model.getElementById("Semantics-Activities-CompleteStructuredActivities-Values").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "values", 
    	    "Semantics-Activities-CompleteStructuredActivities-Values-values",
    	    "Semantics-Classes-Kernel-Value",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // Token
    	clss  = (Class_)model.getElementById("Semantics-Activities-IntermediateActivities-Token").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "holder", 
    	    "Semantics-Activities-IntermediateActivities-Token-holder",
    	    "Semantics-Activities-IntermediateActivities-ActivityNodeActivation",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // Offer
    	clss  = (Class_)model.getElementById("Semantics-Activities-IntermediateActivities-Offer").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "offeredTokens", 
    	    "Semantics-Activities-IntermediateActivities-Offer-offeredTokens",
    	    "Semantics-Activities-IntermediateActivities-Token",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // ObjectToken
    	clss  = (Class_)model.getElementById("Semantics-Activities-IntermediateActivities-ObjectToken").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "value", 
    	    "Semantics-Activities-IntermediateActivities-ObjectToken-value",
    	    "Semantics-Classes-Kernel-Value",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // ObjectNodeActivation
    	clss  = (Class_)model.getElementById("Semantics-Activities-IntermediateActivities-ObjectNodeActivation").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "offeredTokenCount", 
    	    "Semantics-Activities-IntermediateActivities-ObjectNodeActivation-offeredTokenCount",
    	    "http://www.omg.org/spec/UML/20090901/uml.xml#Integer",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // MergeNodeActivation
    	clss  = (Class_)model.getElementById("Semantics-Activities-IntermediateActivities-MergeNodeActivation").getDelegate();
    	
        // JoinNodeActivation
    	clss  = (Class_)model.getElementById("Semantics-Activities-IntermediateActivities-JoinNodeActivation").getDelegate();
    	
        // ForkedToken
    	clss  = (Class_)model.getElementById("Semantics-Activities-IntermediateActivities-ForkedToken").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "baseToken", 
    	    "Semantics-Activities-IntermediateActivities-ForkedToken-baseToken",
    	    "Semantics-Activities-IntermediateActivities-Token",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "remainingOffersCount", 
    	    "Semantics-Activities-IntermediateActivities-ForkedToken-remainingOffersCount",
    	    "http://www.omg.org/spec/UML/20090901/uml.xml#Integer",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // ForkNodeActivation
    	clss  = (Class_)model.getElementById("Semantics-Activities-IntermediateActivities-ForkNodeActivation").getDelegate();
    	
        // InitialNodeActivation
    	clss  = (Class_)model.getElementById("Semantics-Activities-IntermediateActivities-InitialNodeActivation").getDelegate();
    	
        // DecisionNodeActivation
    	clss  = (Class_)model.getElementById("Semantics-Activities-IntermediateActivities-DecisionNodeActivation").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "decisionInputExecution", 
    	    "Semantics-Activities-IntermediateActivities-DecisionNodeActivation-decisionInputExecution",
    	    "Semantics-CommonBehaviors-BasicBehaviors-Execution",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // ControlToken
    	clss  = (Class_)model.getElementById("Semantics-Activities-IntermediateActivities-ControlToken").getDelegate();
    	
        // ControlNodeActivation
    	clss  = (Class_)model.getElementById("Semantics-Activities-IntermediateActivities-ControlNodeActivation").getDelegate();
    	
        // ActivityEdgeInstance
    	clss  = (Class_)model.getElementById("Semantics-Activities-IntermediateActivities-ActivityEdgeInstance").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "edge", 
    	    "Semantics-Activities-IntermediateActivities-ActivityEdgeInstance-edge",
    	    "Syntax-Activities-IntermediateActivities-ActivityEdge",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "group", 
    	    "Semantics-Activities-IntermediateActivities-ActivityEdgeInstance-group",
    	    "Semantics-Activities-IntermediateActivities-ActivityNodeActivationGroup",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "source", 
    	    "Semantics-Activities-IntermediateActivities-ActivityEdgeInstance-source",
    	    "Semantics-Activities-IntermediateActivities-ActivityNodeActivation",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "target", 
    	    "Semantics-Activities-IntermediateActivities-ActivityEdgeInstance-target",
    	    "Semantics-Activities-IntermediateActivities-ActivityNodeActivation",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "offers", 
    	    "Semantics-Activities-IntermediateActivities-ActivityEdgeInstance-offers",
    	    "Semantics-Activities-IntermediateActivities-Offer",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // ActivityExecution
    	clss  = (Class_)model.getElementById("Semantics-Activities-IntermediateActivities-ActivityExecution").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "activationGroup", 
    	    "Semantics-Activities-IntermediateActivities-ActivityExecution-activationGroup",
    	    "Semantics-Activities-IntermediateActivities-ActivityNodeActivationGroup",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // ActivityFinalNodeActivation
    	clss  = (Class_)model.getElementById("Semantics-Activities-IntermediateActivities-ActivityFinalNodeActivation").getDelegate();
    	
        // ActivityNodeActivation
    	clss  = (Class_)model.getElementById("Semantics-Activities-IntermediateActivities-ActivityNodeActivation").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "group", 
    	    "Semantics-Activities-IntermediateActivities-ActivityNodeActivation-group",
    	    "Semantics-Activities-IntermediateActivities-ActivityNodeActivationGroup",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "node", 
    	    "Semantics-Activities-IntermediateActivities-ActivityNodeActivation-node",
    	    "Syntax-Activities-IntermediateActivities-ActivityNode",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "incomingEdges", 
    	    "Semantics-Activities-IntermediateActivities-ActivityNodeActivation-incomingEdges",
    	    "Semantics-Activities-IntermediateActivities-ActivityEdgeInstance",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "outgoingEdges", 
    	    "Semantics-Activities-IntermediateActivities-ActivityNodeActivation-outgoingEdges",
    	    "Semantics-Activities-IntermediateActivities-ActivityEdgeInstance",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "running", 
    	    "Semantics-Activities-IntermediateActivities-ActivityNodeActivation-running",
    	    "http://www.omg.org/spec/UML/20090901/uml.xml#Boolean",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "heldTokens", 
    	    "Semantics-Activities-IntermediateActivities-ActivityNodeActivation-heldTokens",
    	    "Semantics-Activities-IntermediateActivities-Token",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // ActivityNodeActivationGroup
    	clss  = (Class_)model.getElementById("Semantics-Activities-IntermediateActivities-ActivityNodeActivationGroup").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "edgeInstances", 
    	    "Semantics-Activities-IntermediateActivities-ActivityNodeActivationGroup-edgeInstances",
    	    "Semantics-Activities-IntermediateActivities-ActivityEdgeInstance",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "nodeActivations", 
    	    "Semantics-Activities-IntermediateActivities-ActivityNodeActivationGroup-nodeActivations",
    	    "Semantics-Activities-IntermediateActivities-ActivityNodeActivation",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "activityExecution", 
    	    "Semantics-Activities-IntermediateActivities-ActivityNodeActivationGroup-activityExecution",
    	    "Semantics-Activities-IntermediateActivities-ActivityExecution",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "containingNodeActivation", 
    	    "Semantics-Activities-IntermediateActivities-ActivityNodeActivationGroup-containingNodeActivation",
    	    "Semantics-Activities-CompleteStructuredActivities-StructuredActivityNodeActivation",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // ActivityParameterNodeActivation
    	clss  = (Class_)model.getElementById("Semantics-Activities-IntermediateActivities-ActivityParameterNodeActivation").getDelegate();
    	
        // ExpansionActivationGroup
    	clss  = (Class_)model.getElementById("Semantics-Activities-ExtraStructuredActivities-ExpansionActivationGroup").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "regionInputs", 
    	    "Semantics-Activities-ExtraStructuredActivities-ExpansionActivationGroup-regionInputs",
    	    "Semantics-Actions-BasicActions-OutputPinActivation",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "groupInputs", 
    	    "Semantics-Activities-ExtraStructuredActivities-ExpansionActivationGroup-groupInputs",
    	    "Semantics-Actions-BasicActions-OutputPinActivation",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "regionActivation", 
    	    "Semantics-Activities-ExtraStructuredActivities-ExpansionActivationGroup-regionActivation",
    	    "Semantics-Activities-ExtraStructuredActivities-ExpansionRegionActivation",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "groupOutputs", 
    	    "Semantics-Activities-ExtraStructuredActivities-ExpansionActivationGroup-groupOutputs",
    	    "Semantics-Actions-BasicActions-OutputPinActivation",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "index", 
    	    "Semantics-Activities-ExtraStructuredActivities-ExpansionActivationGroup-index",
    	    "http://www.omg.org/spec/UML/20090901/uml.xml#Integer",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // ExpansionNodeActivation
    	clss  = (Class_)model.getElementById("Semantics-Activities-ExtraStructuredActivities-ExpansionNodeActivation").getDelegate();
    	
        // ExpansionRegionActivation
    	clss  = (Class_)model.getElementById("Semantics-Activities-ExtraStructuredActivities-ExpansionRegionActivation").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "activationGroups", 
    	    "Semantics-Activities-ExtraStructuredActivities-ExpansionRegionActivation-activationGroups",
    	    "Semantics-Activities-ExtraStructuredActivities-ExpansionActivationGroup",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "inputTokens", 
    	    "Semantics-Activities-ExtraStructuredActivities-ExpansionRegionActivation-inputTokens",
    	    "Semantics-Activities-ExtraStructuredActivities-TokenSet",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "inputExpansionTokens", 
    	    "Semantics-Activities-ExtraStructuredActivities-ExpansionRegionActivation-inputExpansionTokens",
    	    "Semantics-Activities-ExtraStructuredActivities-TokenSet",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // TokenSet
    	clss  = (Class_)model.getElementById("Semantics-Activities-ExtraStructuredActivities-TokenSet").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "tokens", 
    	    "Semantics-Activities-ExtraStructuredActivities-TokenSet-tokens",
    	    "Semantics-Activities-IntermediateActivities-Token",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // StructuredValue
    	clss  = (Class_)model.getElementById("Semantics-Classes-Kernel-StructuredValue").getDelegate();
    	
        // UnlimitedNaturalValue
    	clss  = (Class_)model.getElementById("Semantics-Classes-Kernel-UnlimitedNaturalValue").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "value", 
    	    "Semantics-Classes-Kernel-UnlimitedNaturalValue-value",
    	    "http://www.omg.org/spec/UML/20090901/uml.xml#UnlimitedNatural",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // Value
    	clss  = (Class_)model.getElementById("Semantics-Classes-Kernel-Value").getDelegate();
    	
        // StringValue
    	clss  = (Class_)model.getElementById("Semantics-Classes-Kernel-StringValue").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "value", 
    	    "Semantics-Classes-Kernel-StringValue-value",
    	    "http://www.omg.org/spec/UML/20090901/uml.xml#String",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // Reference
    	clss  = (Class_)model.getElementById("Semantics-Classes-Kernel-Reference").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "referent", 
    	    "Semantics-Classes-Kernel-Reference-referent",
    	    "Semantics-Classes-Kernel-Object",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // PrimitiveValue
    	clss  = (Class_)model.getElementById("Semantics-Classes-Kernel-PrimitiveValue").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "type", 
    	    "Semantics-Classes-Kernel-PrimitiveValue-type",
    	    "Syntax-Classes-Kernel-PrimitiveType",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // Object
    	clss  = (Class_)model.getElementById("Semantics-Classes-Kernel-Object").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "types", 
    	    "Semantics-Classes-Kernel-Object-types",
    	    "Syntax-Classes-Kernel-Class",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "objectActivation", 
    	    "Semantics-Classes-Kernel-Object-objectActivation",
    	    "Semantics-CommonBehaviors-Communications-ObjectActivation",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // LiteralBooleanEvaluation
    	clss  = (Class_)model.getElementById("Semantics-Classes-Kernel-LiteralBooleanEvaluation").getDelegate();
    	
        // LiteralEvaluation
    	clss  = (Class_)model.getElementById("Semantics-Classes-Kernel-LiteralEvaluation").getDelegate();
    	
        // LiteralIntegerEvaluation
    	clss  = (Class_)model.getElementById("Semantics-Classes-Kernel-LiteralIntegerEvaluation").getDelegate();
    	
        // LiteralNullEvaluation
    	clss  = (Class_)model.getElementById("Semantics-Classes-Kernel-LiteralNullEvaluation").getDelegate();
    	
        // LiteralStringEvaluation
    	clss  = (Class_)model.getElementById("Semantics-Classes-Kernel-LiteralStringEvaluation").getDelegate();
    	
        // LiteralUnlimitedNaturalEvaluation
    	clss  = (Class_)model.getElementById("Semantics-Classes-Kernel-LiteralUnlimitedNaturalEvaluation").getDelegate();
    	
        // Link
    	clss  = (Class_)model.getElementById("Semantics-Classes-Kernel-Link").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "type", 
    	    "Semantics-Classes-Kernel-Link-type",
    	    "Syntax-Classes-Kernel-Association",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // InstanceValueEvaluation
    	clss  = (Class_)model.getElementById("Semantics-Classes-Kernel-InstanceValueEvaluation").getDelegate();
    	
        // IntegerValue
    	clss  = (Class_)model.getElementById("Semantics-Classes-Kernel-IntegerValue").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "value", 
    	    "Semantics-Classes-Kernel-IntegerValue-value",
    	    "http://www.omg.org/spec/UML/20090901/uml.xml#Integer",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // FeatureValue
    	clss  = (Class_)model.getElementById("Semantics-Classes-Kernel-FeatureValue").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "feature", 
    	    "Semantics-Classes-Kernel-FeatureValue-feature",
    	    "Syntax-Classes-Kernel-StructuralFeature",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "values", 
    	    "Semantics-Classes-Kernel-FeatureValue-values",
    	    "Semantics-Classes-Kernel-Value",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "position", 
    	    "Semantics-Classes-Kernel-FeatureValue-position",
    	    "http://www.omg.org/spec/UML/20090901/uml.xml#Integer",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // ExtensionalValue
    	clss  = (Class_)model.getElementById("Semantics-Classes-Kernel-ExtensionalValue").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "locus", 
    	    "Semantics-Classes-Kernel-ExtensionalValue-locus",
    	    "Semantics-Loci-LociL1-Locus",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // Evaluation
    	clss  = (Class_)model.getElementById("Semantics-Classes-Kernel-Evaluation").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "specification", 
    	    "Semantics-Classes-Kernel-Evaluation-specification",
    	    "Syntax-Classes-Kernel-ValueSpecification",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "locus", 
    	    "Semantics-Classes-Kernel-Evaluation-locus",
    	    "Semantics-Loci-LociL1-Locus",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // EnumerationValue
    	clss  = (Class_)model.getElementById("Semantics-Classes-Kernel-EnumerationValue").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "literal", 
    	    "Semantics-Classes-Kernel-EnumerationValue-literal",
    	    "Syntax-Classes-Kernel-EnumerationLiteral",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "type", 
    	    "Semantics-Classes-Kernel-EnumerationValue-type",
    	    "Syntax-Classes-Kernel-Enumeration",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // DataValue
    	clss  = (Class_)model.getElementById("Semantics-Classes-Kernel-DataValue").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "type", 
    	    "Semantics-Classes-Kernel-DataValue-type",
    	    "Syntax-Classes-Kernel-DataType",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // CompoundValue
    	clss  = (Class_)model.getElementById("Semantics-Classes-Kernel-CompoundValue").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "featureValues", 
    	    "Semantics-Classes-Kernel-CompoundValue-featureValues",
    	    "Semantics-Classes-Kernel-FeatureValue",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // BooleanValue
    	clss  = (Class_)model.getElementById("Semantics-Classes-Kernel-BooleanValue").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "value", 
    	    "Semantics-Classes-Kernel-BooleanValue-value",
    	    "http://www.omg.org/spec/UML/20090901/uml.xml#Boolean",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // DispatchStrategy
    	clss  = (Class_)model.getElementById("Semantics-Classes-Kernel-DispatchStrategy").getDelegate();
    	
        // RedefinitionBasedDispatchStrategy
    	clss  = (Class_)model.getElementById("Semantics-Classes-Kernel-RedefinitionBasedDispatchStrategy").getDelegate();
    	
        // ParameterValue
    	clss  = (Class_)model.getElementById("Semantics-CommonBehaviors-BasicBehaviors-ParameterValue").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "parameter", 
    	    "Semantics-CommonBehaviors-BasicBehaviors-ParameterValue-parameter",
    	    "Syntax-Classes-Kernel-Parameter",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "values", 
    	    "Semantics-CommonBehaviors-BasicBehaviors-ParameterValue-values",
    	    "Semantics-Classes-Kernel-Value",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // OpaqueBehaviorExecution
    	clss  = (Class_)model.getElementById("Semantics-CommonBehaviors-BasicBehaviors-OpaqueBehaviorExecution").getDelegate();
    	
        // Execution
    	clss  = (Class_)model.getElementById("Semantics-CommonBehaviors-BasicBehaviors-Execution").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "context", 
    	    "Semantics-CommonBehaviors-BasicBehaviors-Execution-context",
    	    "Semantics-Classes-Kernel-Object",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "parameterValues", 
    	    "Semantics-CommonBehaviors-BasicBehaviors-Execution-parameterValues",
    	    "Semantics-CommonBehaviors-BasicBehaviors-ParameterValue",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // EventAccepter
    	clss  = (Class_)model.getElementById("Semantics-CommonBehaviors-Communications-EventAccepter").getDelegate();
    	
        // ClassifierBehaviorExecution
    	clss  = (Class_)model.getElementById("Semantics-CommonBehaviors-Communications-ClassifierBehaviorExecution").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "execution", 
    	    "Semantics-CommonBehaviors-Communications-ClassifierBehaviorExecution-execution",
    	    "Semantics-CommonBehaviors-BasicBehaviors-Execution",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "classifier", 
    	    "Semantics-CommonBehaviors-Communications-ClassifierBehaviorExecution-classifier",
    	    "Syntax-Classes-Kernel-Class",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "objectActivation", 
    	    "Semantics-CommonBehaviors-Communications-ClassifierBehaviorExecution-objectActivation",
    	    "Semantics-CommonBehaviors-Communications-ObjectActivation",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // SignalInstance
    	clss  = (Class_)model.getElementById("Semantics-CommonBehaviors-Communications-SignalInstance").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "type", 
    	    "Semantics-CommonBehaviors-Communications-SignalInstance-type",
    	    "Syntax-CommonBehaviors-Communications-Signal",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "1");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // ObjectActivation
    	clss  = (Class_)model.getElementById("Semantics-CommonBehaviors-Communications-ObjectActivation").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "classifierBehaviorExecutions", 
    	    "Semantics-CommonBehaviors-Communications-ObjectActivation-classifierBehaviorExecutions",
    	    "Semantics-CommonBehaviors-Communications-ClassifierBehaviorExecution",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "waitingEventAccepters", 
    	    "Semantics-CommonBehaviors-Communications-ObjectActivation-waitingEventAccepters",
    	    "Semantics-CommonBehaviors-Communications-EventAccepter",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "eventPool", 
    	    "Semantics-CommonBehaviors-Communications-ObjectActivation-eventPool",
    	    "Semantics-CommonBehaviors-Communications-SignalInstance",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "object", 
    	    "Semantics-CommonBehaviors-Communications-ObjectActivation-object",
    	    "Semantics-Classes-Kernel-Object",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // GetNextEventStrategy
    	clss  = (Class_)model.getElementById("Semantics-CommonBehaviors-Communications-GetNextEventStrategy").getDelegate();
    	
        // FIFOGetNextEventStrategy
    	clss  = (Class_)model.getElementById("Semantics-CommonBehaviors-Communications-FIFOGetNextEventStrategy").getDelegate();
    	
        // ChoiceStrategy
    	clss  = (Class_)model.getElementById("Semantics-Loci-LociL1-ChoiceStrategy").getDelegate();
    	
        // ExecutionFactory
    	clss  = (Class_)model.getElementById("Semantics-Loci-LociL1-ExecutionFactory").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "locus", 
    	    "Semantics-Loci-LociL1-ExecutionFactory-locus",
    	    "Semantics-Loci-LociL1-Locus",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "primitiveBehaviorPrototypes", 
    	    "Semantics-Loci-LociL1-ExecutionFactory-primitiveBehaviorPrototypes",
    	    "Semantics-CommonBehaviors-BasicBehaviors-OpaqueBehaviorExecution",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "builtInTypes", 
    	    "Semantics-Loci-LociL1-ExecutionFactory-builtInTypes",
    	    "Syntax-Classes-Kernel-PrimitiveType",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "strategies", 
    	    "Semantics-Loci-LociL1-ExecutionFactory-strategies",
    	    "Semantics-Loci-LociL1-SemanticStrategy",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // Executor
    	clss  = (Class_)model.getElementById("Semantics-Loci-LociL1-Executor").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "locus", 
    	    "Semantics-Loci-LociL1-Executor-locus",
    	    "Semantics-Loci-LociL1-Locus",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // FirstChoiceStrategy
    	clss  = (Class_)model.getElementById("Semantics-Loci-LociL1-FirstChoiceStrategy").getDelegate();
    	
        // Locus
    	clss  = (Class_)model.getElementById("Semantics-Loci-LociL1-Locus").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "executor", 
    	    "Semantics-Loci-LociL1-Locus-executor",
    	    "Semantics-Loci-LociL1-Executor",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "factory", 
    	    "Semantics-Loci-LociL1-Locus-factory",
    	    "Semantics-Loci-LociL1-ExecutionFactory",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "extensionalValues", 
    	    "Semantics-Loci-LociL1-Locus-extensionalValues",
    	    "Semantics-Classes-Kernel-ExtensionalValue",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // SemanticStrategy
    	clss  = (Class_)model.getElementById("Semantics-Loci-LociL1-SemanticStrategy").getDelegate();
    	
        // SemanticVisitor
    	clss  = (Class_)model.getElementById("Semantics-Loci-LociL1-SemanticVisitor").getDelegate();
    	
        // ExecutionFactoryL1
    	clss  = (Class_)model.getElementById("Semantics-Loci-LociL1-ExecutionFactoryL1").getDelegate();
    	
        // ExecutionFactoryL2
    	clss  = (Class_)model.getElementById("Semantics-Loci-LociL2-ExecutionFactoryL2").getDelegate();
    	
        // ExecutionFactoryL3
    	clss  = (Class_)model.getElementById("Semantics-Loci-LociL3-ExecutionFactoryL3").getDelegate();
    	
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
    	
        // SendSignalActionActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-BasicActions-SendSignalActionActivation").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Actions-BasicActions-InvocationActionActivation");
    	
        // PinActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-BasicActions-PinActivation").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Activities-IntermediateActivities-ObjectNodeActivation");
    	
        // OutputPinActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-BasicActions-OutputPinActivation").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Actions-BasicActions-PinActivation");
    	
        // InvocationActionActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-BasicActions-InvocationActionActivation").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Actions-BasicActions-ActionActivation");
    	
        // InputPinActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-BasicActions-InputPinActivation").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Actions-BasicActions-PinActivation");
    	
        // CallActionActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-BasicActions-CallActionActivation").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Actions-BasicActions-InvocationActionActivation");
    	
        // CallBehaviorActionActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-BasicActions-CallBehaviorActionActivation").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Actions-BasicActions-CallActionActivation");
    	
        // CallOperationActionActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-BasicActions-CallOperationActionActivation").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Actions-BasicActions-CallActionActivation");
    	
        // ActionActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-BasicActions-ActionActivation").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Activities-IntermediateActivities-ActivityNodeActivation");
    	
        // WriteLinkActionActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-IntermediateActions-WriteLinkActionActivation").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Actions-IntermediateActions-LinkActionActivation");
    	
        // WriteStructuralFeatureActionActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-IntermediateActions-WriteStructuralFeatureActionActivation").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Actions-IntermediateActions-StructuralFeatureActionActivation");
    	
        // ValueSpecificationActionActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-IntermediateActions-ValueSpecificationActionActivation").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Actions-BasicActions-ActionActivation");
    	
        // TestIdentityActionActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-IntermediateActions-TestIdentityActionActivation").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Actions-BasicActions-ActionActivation");
    	
        // StructuralFeatureActionActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-IntermediateActions-StructuralFeatureActionActivation").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Actions-BasicActions-ActionActivation");
    	
        // RemoveStructuralFeatureValueActionActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-IntermediateActions-RemoveStructuralFeatureValueActionActivation").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Actions-IntermediateActions-WriteStructuralFeatureActionActivation");
    	
        // ReadLinkActionActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-IntermediateActions-ReadLinkActionActivation").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Actions-IntermediateActions-LinkActionActivation");
    	
        // ReadSelfActionActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-IntermediateActions-ReadSelfActionActivation").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Actions-BasicActions-ActionActivation");
    	
        // ReadStructuralFeatureActionActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-IntermediateActions-ReadStructuralFeatureActionActivation").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Actions-IntermediateActions-StructuralFeatureActionActivation");
    	
        // LinkActionActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-IntermediateActions-LinkActionActivation").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Actions-BasicActions-ActionActivation");
    	
        // DestroyLinkActionActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-IntermediateActions-DestroyLinkActionActivation").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Actions-IntermediateActions-WriteLinkActionActivation");
    	
        // DestroyObjectActionActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-IntermediateActions-DestroyObjectActionActivation").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Actions-BasicActions-ActionActivation");
    	
        // CreateLinkActionActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-IntermediateActions-CreateLinkActionActivation").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Actions-IntermediateActions-WriteLinkActionActivation");
    	
        // CreateObjectActionActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-IntermediateActions-CreateObjectActionActivation").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Actions-BasicActions-ActionActivation");
    	
        // ClearAssociationActionActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-IntermediateActions-ClearAssociationActionActivation").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Actions-BasicActions-ActionActivation");
    	
        // ClearStructuralFeatureActionActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-IntermediateActions-ClearStructuralFeatureActionActivation").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Actions-IntermediateActions-StructuralFeatureActionActivation");
    	
        // AddStructuralFeatureValueActionActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-IntermediateActions-AddStructuralFeatureValueActionActivation").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Actions-IntermediateActions-WriteStructuralFeatureActionActivation");
    	
        // StartClassifierBehaviorActionActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-CompleteActions-StartClassifierBehaviorActionActivation").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Actions-BasicActions-ActionActivation");
    	
        // StartObjectBehaviorActionActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-CompleteActions-StartObjectBehaviorActionActivation").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Actions-BasicActions-InvocationActionActivation");
    	
        // ReduceActionActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-CompleteActions-ReduceActionActivation").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Actions-BasicActions-ActionActivation");
    	
        // ReadIsClassifiedObjectActionActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-CompleteActions-ReadIsClassifiedObjectActionActivation").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Actions-BasicActions-ActionActivation");
    	
        // ReclassifyObjectActionActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-CompleteActions-ReclassifyObjectActionActivation").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Actions-BasicActions-ActionActivation");
    	
        // ReadExtentActionActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-CompleteActions-ReadExtentActionActivation").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Actions-BasicActions-ActionActivation");
    	
        // AcceptEventActionActivation
    	clss  = (Class_)model.getElementById("Semantics-Actions-CompleteActions-AcceptEventActionActivation").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Actions-BasicActions-ActionActivation");
    	
        // AcceptEventActionEventAccepter
    	clss  = (Class_)model.getElementById("Semantics-Actions-CompleteActions-AcceptEventActionEventAccepter").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-CommonBehaviors-Communications-EventAccepter");
    	
        // StructuredActivityNodeActivation
    	clss  = (Class_)model.getElementById("Semantics-Activities-CompleteStructuredActivities-StructuredActivityNodeActivation").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Actions-BasicActions-ActionActivation");
    	
        // LoopNodeActivation
    	clss  = (Class_)model.getElementById("Semantics-Activities-CompleteStructuredActivities-LoopNodeActivation").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Activities-CompleteStructuredActivities-StructuredActivityNodeActivation");
    	
        // ConditionalNodeActivation
    	clss  = (Class_)model.getElementById("Semantics-Activities-CompleteStructuredActivities-ConditionalNodeActivation").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Activities-CompleteStructuredActivities-StructuredActivityNodeActivation");
    	
        // ClauseActivation
    	clss  = (Class_)model.getElementById("Semantics-Activities-CompleteStructuredActivities-ClauseActivation").getDelegate();
    	
        // Values
    	clss  = (Class_)model.getElementById("Semantics-Activities-CompleteStructuredActivities-Values").getDelegate();
    	
        // Token
    	clss  = (Class_)model.getElementById("Semantics-Activities-IntermediateActivities-Token").getDelegate();
    	
        // Offer
    	clss  = (Class_)model.getElementById("Semantics-Activities-IntermediateActivities-Offer").getDelegate();
    	
        // ObjectToken
    	clss  = (Class_)model.getElementById("Semantics-Activities-IntermediateActivities-ObjectToken").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Activities-IntermediateActivities-Token");
    	
        // ObjectNodeActivation
    	clss  = (Class_)model.getElementById("Semantics-Activities-IntermediateActivities-ObjectNodeActivation").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Activities-IntermediateActivities-ActivityNodeActivation");
    	
        // MergeNodeActivation
    	clss  = (Class_)model.getElementById("Semantics-Activities-IntermediateActivities-MergeNodeActivation").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Activities-IntermediateActivities-ControlNodeActivation");
    	
        // JoinNodeActivation
    	clss  = (Class_)model.getElementById("Semantics-Activities-IntermediateActivities-JoinNodeActivation").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Activities-IntermediateActivities-ControlNodeActivation");
    	
        // ForkedToken
    	clss  = (Class_)model.getElementById("Semantics-Activities-IntermediateActivities-ForkedToken").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Activities-IntermediateActivities-Token");
    	
        // ForkNodeActivation
    	clss  = (Class_)model.getElementById("Semantics-Activities-IntermediateActivities-ForkNodeActivation").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Activities-IntermediateActivities-ControlNodeActivation");
    	
        // InitialNodeActivation
    	clss  = (Class_)model.getElementById("Semantics-Activities-IntermediateActivities-InitialNodeActivation").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Activities-IntermediateActivities-ControlNodeActivation");
    	
        // DecisionNodeActivation
    	clss  = (Class_)model.getElementById("Semantics-Activities-IntermediateActivities-DecisionNodeActivation").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Activities-IntermediateActivities-ControlNodeActivation");
    	
        // ControlToken
    	clss  = (Class_)model.getElementById("Semantics-Activities-IntermediateActivities-ControlToken").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Activities-IntermediateActivities-Token");
    	
        // ControlNodeActivation
    	clss  = (Class_)model.getElementById("Semantics-Activities-IntermediateActivities-ControlNodeActivation").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Activities-IntermediateActivities-ActivityNodeActivation");
    	
        // ActivityEdgeInstance
    	clss  = (Class_)model.getElementById("Semantics-Activities-IntermediateActivities-ActivityEdgeInstance").getDelegate();
    	
        // ActivityExecution
    	clss  = (Class_)model.getElementById("Semantics-Activities-IntermediateActivities-ActivityExecution").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-CommonBehaviors-BasicBehaviors-Execution");
    	
        // ActivityFinalNodeActivation
    	clss  = (Class_)model.getElementById("Semantics-Activities-IntermediateActivities-ActivityFinalNodeActivation").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Activities-IntermediateActivities-ControlNodeActivation");
    	
        // ActivityNodeActivation
    	clss  = (Class_)model.getElementById("Semantics-Activities-IntermediateActivities-ActivityNodeActivation").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Loci-LociL1-SemanticVisitor");
    	
        // ActivityNodeActivationGroup
    	clss  = (Class_)model.getElementById("Semantics-Activities-IntermediateActivities-ActivityNodeActivationGroup").getDelegate();
    	
        // ActivityParameterNodeActivation
    	clss  = (Class_)model.getElementById("Semantics-Activities-IntermediateActivities-ActivityParameterNodeActivation").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Activities-IntermediateActivities-ObjectNodeActivation");
    	
        // ExpansionActivationGroup
    	clss  = (Class_)model.getElementById("Semantics-Activities-ExtraStructuredActivities-ExpansionActivationGroup").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Activities-IntermediateActivities-ActivityNodeActivationGroup");
    	
        // ExpansionNodeActivation
    	clss  = (Class_)model.getElementById("Semantics-Activities-ExtraStructuredActivities-ExpansionNodeActivation").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Activities-IntermediateActivities-ObjectNodeActivation");
    	
        // ExpansionRegionActivation
    	clss  = (Class_)model.getElementById("Semantics-Activities-ExtraStructuredActivities-ExpansionRegionActivation").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Actions-BasicActions-ActionActivation");
    	
        // TokenSet
    	clss  = (Class_)model.getElementById("Semantics-Activities-ExtraStructuredActivities-TokenSet").getDelegate();
    	
        // StructuredValue
    	clss  = (Class_)model.getElementById("Semantics-Classes-Kernel-StructuredValue").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Classes-Kernel-Value");
    	
        // UnlimitedNaturalValue
    	clss  = (Class_)model.getElementById("Semantics-Classes-Kernel-UnlimitedNaturalValue").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Classes-Kernel-PrimitiveValue");
    	
        // Value
    	clss  = (Class_)model.getElementById("Semantics-Classes-Kernel-Value").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Loci-LociL1-SemanticVisitor");
    	
        // StringValue
    	clss  = (Class_)model.getElementById("Semantics-Classes-Kernel-StringValue").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Classes-Kernel-PrimitiveValue");
    	
        // Reference
    	clss  = (Class_)model.getElementById("Semantics-Classes-Kernel-Reference").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Classes-Kernel-StructuredValue");
    	
        // PrimitiveValue
    	clss  = (Class_)model.getElementById("Semantics-Classes-Kernel-PrimitiveValue").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Classes-Kernel-Value");
    	
        // Object
    	clss  = (Class_)model.getElementById("Semantics-Classes-Kernel-Object").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Classes-Kernel-ExtensionalValue");
    	
        // LiteralBooleanEvaluation
    	clss  = (Class_)model.getElementById("Semantics-Classes-Kernel-LiteralBooleanEvaluation").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Classes-Kernel-LiteralEvaluation");
    	
        // LiteralEvaluation
    	clss  = (Class_)model.getElementById("Semantics-Classes-Kernel-LiteralEvaluation").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Classes-Kernel-Evaluation");
    	
        // LiteralIntegerEvaluation
    	clss  = (Class_)model.getElementById("Semantics-Classes-Kernel-LiteralIntegerEvaluation").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Classes-Kernel-LiteralEvaluation");
    	
        // LiteralNullEvaluation
    	clss  = (Class_)model.getElementById("Semantics-Classes-Kernel-LiteralNullEvaluation").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Classes-Kernel-LiteralEvaluation");
    	
        // LiteralStringEvaluation
    	clss  = (Class_)model.getElementById("Semantics-Classes-Kernel-LiteralStringEvaluation").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Classes-Kernel-LiteralEvaluation");
    	
        // LiteralUnlimitedNaturalEvaluation
    	clss  = (Class_)model.getElementById("Semantics-Classes-Kernel-LiteralUnlimitedNaturalEvaluation").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Classes-Kernel-LiteralEvaluation");
    	
        // Link
    	clss  = (Class_)model.getElementById("Semantics-Classes-Kernel-Link").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Classes-Kernel-ExtensionalValue");
    	
        // InstanceValueEvaluation
    	clss  = (Class_)model.getElementById("Semantics-Classes-Kernel-InstanceValueEvaluation").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Classes-Kernel-Evaluation");
    	
        // IntegerValue
    	clss  = (Class_)model.getElementById("Semantics-Classes-Kernel-IntegerValue").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Classes-Kernel-PrimitiveValue");
    	
        // FeatureValue
    	clss  = (Class_)model.getElementById("Semantics-Classes-Kernel-FeatureValue").getDelegate();
    	
        // ExtensionalValue
    	clss  = (Class_)model.getElementById("Semantics-Classes-Kernel-ExtensionalValue").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Classes-Kernel-CompoundValue");
    	
        // Evaluation
    	clss  = (Class_)model.getElementById("Semantics-Classes-Kernel-Evaluation").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Loci-LociL1-SemanticVisitor");
    	
        // EnumerationValue
    	clss  = (Class_)model.getElementById("Semantics-Classes-Kernel-EnumerationValue").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Classes-Kernel-Value");
    	
        // DataValue
    	clss  = (Class_)model.getElementById("Semantics-Classes-Kernel-DataValue").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Classes-Kernel-CompoundValue");
    	
        // CompoundValue
    	clss  = (Class_)model.getElementById("Semantics-Classes-Kernel-CompoundValue").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Classes-Kernel-StructuredValue");
    	
        // BooleanValue
    	clss  = (Class_)model.getElementById("Semantics-Classes-Kernel-BooleanValue").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Classes-Kernel-PrimitiveValue");
    	
        // DispatchStrategy
    	clss  = (Class_)model.getElementById("Semantics-Classes-Kernel-DispatchStrategy").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Loci-LociL1-SemanticStrategy");
    	
        // RedefinitionBasedDispatchStrategy
    	clss  = (Class_)model.getElementById("Semantics-Classes-Kernel-RedefinitionBasedDispatchStrategy").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Classes-Kernel-DispatchStrategy");
    	
        // ParameterValue
    	clss  = (Class_)model.getElementById("Semantics-CommonBehaviors-BasicBehaviors-ParameterValue").getDelegate();
    	
        // OpaqueBehaviorExecution
    	clss  = (Class_)model.getElementById("Semantics-CommonBehaviors-BasicBehaviors-OpaqueBehaviorExecution").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-CommonBehaviors-BasicBehaviors-Execution");
    	
        // Execution
    	clss  = (Class_)model.getElementById("Semantics-CommonBehaviors-BasicBehaviors-Execution").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Classes-Kernel-Object");
    	
        // EventAccepter
    	clss  = (Class_)model.getElementById("Semantics-CommonBehaviors-Communications-EventAccepter").getDelegate();
    	
        // ClassifierBehaviorExecution
    	clss  = (Class_)model.getElementById("Semantics-CommonBehaviors-Communications-ClassifierBehaviorExecution").getDelegate();
    	
        // SignalInstance
    	clss  = (Class_)model.getElementById("Semantics-CommonBehaviors-Communications-SignalInstance").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Classes-Kernel-CompoundValue");
    	
        // ObjectActivation
    	clss  = (Class_)model.getElementById("Semantics-CommonBehaviors-Communications-ObjectActivation").getDelegate();
    	
        // GetNextEventStrategy
    	clss  = (Class_)model.getElementById("Semantics-CommonBehaviors-Communications-GetNextEventStrategy").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Loci-LociL1-SemanticStrategy");
    	
        // FIFOGetNextEventStrategy
    	clss  = (Class_)model.getElementById("Semantics-CommonBehaviors-Communications-FIFOGetNextEventStrategy").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-CommonBehaviors-Communications-GetNextEventStrategy");
    	
        // ChoiceStrategy
    	clss  = (Class_)model.getElementById("Semantics-Loci-LociL1-ChoiceStrategy").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Loci-LociL1-SemanticStrategy");
    	
        // ExecutionFactory
    	clss  = (Class_)model.getElementById("Semantics-Loci-LociL1-ExecutionFactory").getDelegate();
    	
        // Executor
    	clss  = (Class_)model.getElementById("Semantics-Loci-LociL1-Executor").getDelegate();
    	
        // FirstChoiceStrategy
    	clss  = (Class_)model.getElementById("Semantics-Loci-LociL1-FirstChoiceStrategy").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Loci-LociL1-ChoiceStrategy");
    	
        // Locus
    	clss  = (Class_)model.getElementById("Semantics-Loci-LociL1-Locus").getDelegate();
    	
        // SemanticStrategy
    	clss  = (Class_)model.getElementById("Semantics-Loci-LociL1-SemanticStrategy").getDelegate();
    	
        // SemanticVisitor
    	clss  = (Class_)model.getElementById("Semantics-Loci-LociL1-SemanticVisitor").getDelegate();
    	
        // ExecutionFactoryL1
    	clss  = (Class_)model.getElementById("Semantics-Loci-LociL1-ExecutionFactoryL1").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Loci-LociL1-ExecutionFactory");
    	
        // ExecutionFactoryL2
    	clss  = (Class_)model.getElementById("Semantics-Loci-LociL2-ExecutionFactoryL2").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Loci-LociL1-ExecutionFactoryL1");
    	
        // ExecutionFactoryL3
    	clss  = (Class_)model.getElementById("Semantics-Loci-LociL3-ExecutionFactoryL3").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-Loci-LociL2-ExecutionFactoryL2");
    	
    }

    private void constructAssociations()
    {
        Package pkg = null;
        String packageId = null;
        Association assoc = null;
        Property prop = null;
        
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-CommonBehaviors-BasicBehaviors";   
                      
        // Syntax.CommonBehaviors.BasicBehaviors.A_context_behavior 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_context_behavior", "Syntax-CommonBehaviors-BasicBehaviors-A_context_behavior");
        mapping.mapAssociation(assoc, "Syntax.CommonBehaviors.BasicBehaviors", this);
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
                      
        // Syntax.CommonBehaviors.BasicBehaviors.A_behavior_ownedParameter 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_behavior_ownedParameter", "Syntax-CommonBehaviors-BasicBehaviors-A_behavior_ownedParameter");
        mapping.mapAssociation(assoc, "Syntax.CommonBehaviors.BasicBehaviors", this);
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
                      
        // Syntax.CommonBehaviors.BasicBehaviors.A_behavioredClassifier_ownedBehavior 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_behavioredClassifier_ownedBehavior", "Syntax-CommonBehaviors-BasicBehaviors-A_behavioredClassifier_ownedBehavior");
        mapping.mapAssociation(assoc, "Syntax.CommonBehaviors.BasicBehaviors", this);
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
                      
        // Syntax.CommonBehaviors.BasicBehaviors.A_classifierBehavior_behavioredClassifier 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_classifierBehavior_behavioredClassifier", "Syntax-CommonBehaviors-BasicBehaviors-A_classifierBehavior_behavioredClassifier");
        mapping.mapAssociation(assoc, "Syntax.CommonBehaviors.BasicBehaviors", this);
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
                      
        // Syntax.CommonBehaviors.Communications.A_reception_signal 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_reception_signal", "Syntax-CommonBehaviors-Communications-A_reception_signal");
        mapping.mapAssociation(assoc, "Syntax.CommonBehaviors.Communications", this);
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
                      
        // Syntax.CommonBehaviors.Communications.A_signal_signalEvent 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_signal_signalEvent", "Syntax-CommonBehaviors-Communications-A_signal_signalEvent");
        mapping.mapAssociation(assoc, "Syntax.CommonBehaviors.Communications", this);
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
                      
        // Syntax.CommonBehaviors.Communications.A_ownedAttribute_owningSignal 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_ownedAttribute_owningSignal", "Syntax-CommonBehaviors-Communications-A_ownedAttribute_owningSignal");
        mapping.mapAssociation(assoc, "Syntax.CommonBehaviors.Communications", this);
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
                      
        // Syntax.CommonBehaviors.Communications.A_event_trigger 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_event_trigger", "Syntax-CommonBehaviors-Communications-A_event_trigger");
        mapping.mapAssociation(assoc, "Syntax.CommonBehaviors.Communications", this);
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
                      
        // Syntax.Classes.Kernel.A_navigableOwnedEnd_association 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_navigableOwnedEnd_association", "Syntax-Classes-Kernel-A_navigableOwnedEnd_association");
        mapping.mapAssociation(assoc, "Syntax.Classes.Kernel", this);
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
                      
        // Syntax.Classes.Kernel.A_endType_association 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_endType_association", "Syntax-Classes-Kernel-A_endType_association");
        mapping.mapAssociation(assoc, "Syntax.Classes.Kernel", this);
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
                      
        // Syntax.Classes.Kernel.A_memberEnd_association 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_memberEnd_association", "Syntax-Classes-Kernel-A_memberEnd_association");
        mapping.mapAssociation(assoc, "Syntax.Classes.Kernel", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Syntax-Classes-Kernel-Association-memberEnd Syntax-Classes-Kernel-Property-association");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // Syntax.Classes.Kernel.A_ownedEnd_owningAssociation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_ownedEnd_owningAssociation", "Syntax-Classes-Kernel-A_ownedEnd_owningAssociation");
        mapping.mapAssociation(assoc, "Syntax.Classes.Kernel", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Syntax-Classes-Kernel-Association-ownedEnd Syntax-Classes-Kernel-Property-owningAssociation");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // Syntax.Classes.Kernel.A_general_generalization 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_general_generalization", "Syntax-Classes-Kernel-A_general_generalization");
        mapping.mapAssociation(assoc, "Syntax.Classes.Kernel", this);
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
                      
        // Syntax.Classes.Kernel.A_feature_featuringClassifier 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_feature_featuringClassifier", "Syntax-Classes-Kernel-A_feature_featuringClassifier");
        mapping.mapAssociation(assoc, "Syntax.Classes.Kernel", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Syntax-Classes-Kernel-Classifier-feature Syntax-Classes-Kernel-Feature-featuringClassifier");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // Syntax.Classes.Kernel.A_ownedLiteral_enumeration 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_ownedLiteral_enumeration", "Syntax-Classes-Kernel-A_ownedLiteral_enumeration");
        mapping.mapAssociation(assoc, "Syntax.Classes.Kernel", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Syntax-Classes-Kernel-Enumeration-ownedLiteral Syntax-Classes-Kernel-EnumerationLiteral-enumeration");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // Syntax.Classes.Kernel.A_ownedElement_owner 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_ownedElement_owner", "Syntax-Classes-Kernel-A_ownedElement_owner");
        mapping.mapAssociation(assoc, "Syntax.Classes.Kernel", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Syntax-Classes-Kernel-Element-ownedElement Syntax-Classes-Kernel-Element-owner");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // Syntax.Classes.Kernel.A_attribute_classifier 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_attribute_classifier", "Syntax-Classes-Kernel-A_attribute_classifier");
        mapping.mapAssociation(assoc, "Syntax.Classes.Kernel", this);
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
                      
        // Syntax.Classes.Kernel.A_generalization_specific 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_generalization_specific", "Syntax-Classes-Kernel-A_generalization_specific");
        mapping.mapAssociation(assoc, "Syntax.Classes.Kernel", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Syntax-Classes-Kernel-Classifier-generalization Syntax-Classes-Kernel-Generalization-specific");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // Syntax.Classes.Kernel.A_general_classifier 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_general_classifier", "Syntax-Classes-Kernel-A_general_classifier");
        mapping.mapAssociation(assoc, "Syntax.Classes.Kernel", this);
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
                      
        // Syntax.Classes.Kernel.A_inheritedMember_classifier 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_inheritedMember_classifier", "Syntax-Classes-Kernel-A_inheritedMember_classifier");
        mapping.mapAssociation(assoc, "Syntax.Classes.Kernel", this);
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
                      
        // Syntax.Classes.Kernel.A_ownedAttribute_class 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_ownedAttribute_class", "Syntax-Classes-Kernel-A_ownedAttribute_class");
        mapping.mapAssociation(assoc, "Syntax.Classes.Kernel", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Syntax-Classes-Kernel-Class-ownedAttribute Syntax-Classes-Kernel-Property-class");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // Syntax.Classes.Kernel.A_ownedReception_class 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_ownedReception_class", "Syntax-Classes-Kernel-A_ownedReception_class");
        mapping.mapAssociation(assoc, "Syntax.Classes.Kernel", this);
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
                      
        // Syntax.Classes.Kernel.A_superClass_class 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_superClass_class", "Syntax-Classes-Kernel-A_superClass_class");
        mapping.mapAssociation(assoc, "Syntax.Classes.Kernel", this);
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
                      
        // Syntax.Classes.Kernel.A_type_typedElement 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_type_typedElement", "Syntax-Classes-Kernel-A_type_typedElement");
        mapping.mapAssociation(assoc, "Syntax.Classes.Kernel", this);
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
                      
        // Syntax.Classes.Kernel.A_value_owningSlot 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_value_owningSlot", "Syntax-Classes-Kernel-A_value_owningSlot");
        mapping.mapAssociation(assoc, "Syntax.Classes.Kernel", this);
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
                      
        // Syntax.Classes.Kernel.A_definingFeature_slot 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_definingFeature_slot", "Syntax-Classes-Kernel-A_definingFeature_slot");
        mapping.mapAssociation(assoc, "Syntax.Classes.Kernel", this);
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
                      
        // Syntax.Classes.Kernel.A_opposite_property 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_opposite_property", "Syntax-Classes-Kernel-A_opposite_property");
        mapping.mapAssociation(assoc, "Syntax.Classes.Kernel", this);
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
                      
        // Syntax.Classes.Kernel.A_ownedAttribute_datatype 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_ownedAttribute_datatype", "Syntax-Classes-Kernel-A_ownedAttribute_datatype");
        mapping.mapAssociation(assoc, "Syntax.Classes.Kernel", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Syntax-Classes-Kernel-DataType-ownedAttribute Syntax-Classes-Kernel-Property-datatype");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // Syntax.Classes.Kernel.A_ownedParameter_operation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_ownedParameter_operation", "Syntax-Classes-Kernel-A_ownedParameter_operation");
        mapping.mapAssociation(assoc, "Syntax.Classes.Kernel", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Syntax-Classes-Kernel-Operation-ownedParameter Syntax-Classes-Kernel-Parameter-operation");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // Syntax.Classes.Kernel.A_packageElement_owningPackage 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_packageElement_owningPackage", "Syntax-Classes-Kernel-A_packageElement_owningPackage");
        mapping.mapAssociation(assoc, "Syntax.Classes.Kernel", this);
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
                      
        // Syntax.Classes.Kernel.A_ownedOperation_class 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_ownedOperation_class", "Syntax-Classes-Kernel-A_ownedOperation_class");
        mapping.mapAssociation(assoc, "Syntax.Classes.Kernel", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Syntax-Classes-Kernel-Class-ownedOperation Syntax-Classes-Kernel-Operation-class");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // Syntax.Classes.Kernel.A_redefinedOperation_operation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_redefinedOperation_operation", "Syntax-Classes-Kernel-A_redefinedOperation_operation");
        mapping.mapAssociation(assoc, "Syntax.Classes.Kernel", this);
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
                      
        // Syntax.Classes.Kernel.A_type_operation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_type_operation", "Syntax-Classes-Kernel-A_type_operation");
        mapping.mapAssociation(assoc, "Syntax.Classes.Kernel", this);
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
                      
        // Syntax.Classes.Kernel.A_member_namespace 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_member_namespace", "Syntax-Classes-Kernel-A_member_namespace");
        mapping.mapAssociation(assoc, "Syntax.Classes.Kernel", this);
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
                      
        // Syntax.Classes.Kernel.A_ownedMember_namespace 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_ownedMember_namespace", "Syntax-Classes-Kernel-A_ownedMember_namespace");
        mapping.mapAssociation(assoc, "Syntax.Classes.Kernel", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Syntax-Classes-Kernel-Namespace-ownedMember Syntax-Classes-Kernel-NamedElement-namespace");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // Syntax.Classes.Kernel.A_lowerValue_owningLower 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_lowerValue_owningLower", "Syntax-Classes-Kernel-A_lowerValue_owningLower");
        mapping.mapAssociation(assoc, "Syntax.Classes.Kernel", this);
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
                      
        // Syntax.Classes.Kernel.A_upperValue_owningUpper 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_upperValue_owningUpper", "Syntax-Classes-Kernel-A_upperValue_owningUpper");
        mapping.mapAssociation(assoc, "Syntax.Classes.Kernel", this);
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
                      
        // Syntax.Classes.Kernel.A_instance_instanceValue 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_instance_instanceValue", "Syntax-Classes-Kernel-A_instance_instanceValue");
        mapping.mapAssociation(assoc, "Syntax.Classes.Kernel", this);
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
                      
        // Syntax.Classes.Kernel.A_slot_owningInstance 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_slot_owningInstance", "Syntax-Classes-Kernel-A_slot_owningInstance");
        mapping.mapAssociation(assoc, "Syntax.Classes.Kernel", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Syntax-Classes-Kernel-InstanceSpecification-slot Syntax-Classes-Kernel-Slot-owningInstance");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // Syntax.Classes.Kernel.A_classifier_instanceSpecification 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_classifier_instanceSpecification", "Syntax-Classes-Kernel-A_classifier_instanceSpecification");
        mapping.mapAssociation(assoc, "Syntax.Classes.Kernel", this);
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
                      
        // Syntax.Classes.Kernel.A_method_specification 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_method_specification", "Syntax-Classes-Kernel-A_method_specification");
        mapping.mapAssociation(assoc, "Syntax.Classes.Kernel", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Syntax-Classes-Kernel-BehavioralFeature-method Syntax-CommonBehaviors-BasicBehaviors-Behavior-specification");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // Syntax.Classes.Kernel.A_ownedParameter_ownerFormalParam 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_ownedParameter_ownerFormalParam", "Syntax-Classes-Kernel-A_ownedParameter_ownerFormalParam");
        mapping.mapAssociation(assoc, "Syntax.Classes.Kernel", this);
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
                      
        // Syntax.Classes.Kernel.A_redefinitionContext_redefinableElement 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_redefinitionContext_redefinableElement", "Syntax-Classes-Kernel-A_redefinitionContext_redefinableElement");
        mapping.mapAssociation(assoc, "Syntax.Classes.Kernel", this);
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
                      
        // Syntax.Classes.Kernel.A_redefinedElement_redefinableElement 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_redefinedElement_redefinableElement", "Syntax-Classes-Kernel-A_redefinedElement_redefinableElement");
        mapping.mapAssociation(assoc, "Syntax.Classes.Kernel", this);
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
                      
        // Syntax.Classes.Kernel.A_ownedComment_owningElement 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_ownedComment_owningElement", "Syntax-Classes-Kernel-A_ownedComment_owningElement");
        mapping.mapAssociation(assoc, "Syntax.Classes.Kernel", this);
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
                      
        // Syntax.Classes.Kernel.A_annotatedElement_comment 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_annotatedElement_comment", "Syntax-Classes-Kernel-A_annotatedElement_comment");
        mapping.mapAssociation(assoc, "Syntax.Classes.Kernel", this);
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
                      
        // Syntax.Classes.Kernel.A_elementImport_importingNamespace 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_elementImport_importingNamespace", "Syntax-Classes-Kernel-A_elementImport_importingNamespace");
        mapping.mapAssociation(assoc, "Syntax.Classes.Kernel", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Syntax-Classes-Kernel-Namespace-elementImport Syntax-Classes-Kernel-ElementImport-importingNamespace");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // Syntax.Classes.Kernel.A_importedElement_elementImport 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_importedElement_elementImport", "Syntax-Classes-Kernel-A_importedElement_elementImport");
        mapping.mapAssociation(assoc, "Syntax.Classes.Kernel", this);
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
                      
        // Syntax.Classes.Kernel.A_packageImport_importingNamespace 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_packageImport_importingNamespace", "Syntax-Classes-Kernel-A_packageImport_importingNamespace");
        mapping.mapAssociation(assoc, "Syntax.Classes.Kernel", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Syntax-Classes-Kernel-Namespace-packageImport Syntax-Classes-Kernel-PackageImport-importingNamespace");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // Syntax.Classes.Kernel.A_importedPackage_packageImport 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_importedPackage_packageImport", "Syntax-Classes-Kernel-A_importedPackage_packageImport");
        mapping.mapAssociation(assoc, "Syntax.Classes.Kernel", this);
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
                      
        // Syntax.Classes.Kernel.A_nestedClassifier_class 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_nestedClassifier_class", "Syntax-Classes-Kernel-A_nestedClassifier_class");
        mapping.mapAssociation(assoc, "Syntax.Classes.Kernel", this);
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
                      
        // Syntax.Classes.Kernel.A_type_package 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_type_package", "Syntax-Classes-Kernel-A_type_package");
        mapping.mapAssociation(assoc, "Syntax.Classes.Kernel", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Syntax-Classes-Kernel-Package-ownedType Syntax-Classes-Kernel-Type-package");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // Syntax.Classes.Kernel.A_nestedPackage_nestingPackage 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_nestedPackage_nestingPackage", "Syntax-Classes-Kernel-A_nestedPackage_nestingPackage");
        mapping.mapAssociation(assoc, "Syntax.Classes.Kernel", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Syntax-Classes-Kernel-Package-nestedPackage Syntax-Classes-Kernel-Package-nestingPackage");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Classes-Kernel";   
                      
        // Syntax.Classes.Kernel.A_importedMember_namespace 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_importedMember_namespace", "Syntax-Classes-Kernel-A_importedMember_namespace");
        mapping.mapAssociation(assoc, "Syntax.Classes.Kernel", this);
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
                      
        // Syntax.Activities.IntermediateActivities.A_activity_edge 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_activity_edge", "Syntax-Activities-IntermediateActivities-A_activity_edge");
        mapping.mapAssociation(assoc, "Syntax.Activities.IntermediateActivities", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Syntax-Activities-IntermediateActivities-ActivityEdge-activity Syntax-Activities-IntermediateActivities-Activity-edge");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-IntermediateActivities";   
                      
        // Syntax.Activities.IntermediateActivities.A_activityEdge_guard 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_activityEdge_guard", "Syntax-Activities-IntermediateActivities-A_activityEdge_guard");
        mapping.mapAssociation(assoc, "Syntax.Activities.IntermediateActivities", this);
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
                      
        // Syntax.Activities.IntermediateActivities.A_parameter_activityParameterNode 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_parameter_activityParameterNode", "Syntax-Activities-IntermediateActivities-A_parameter_activityParameterNode");
        mapping.mapAssociation(assoc, "Syntax.Activities.IntermediateActivities", this);
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
                      
        // Syntax.Activities.IntermediateActivities.A_outgoing_source 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_outgoing_source", "Syntax-Activities-IntermediateActivities-A_outgoing_source");
        mapping.mapAssociation(assoc, "Syntax.Activities.IntermediateActivities", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Syntax-Activities-IntermediateActivities-ActivityNode-outgoing Syntax-Activities-IntermediateActivities-ActivityEdge-source");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-IntermediateActivities";   
                      
        // Syntax.Activities.IntermediateActivities.A_node_activity 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_node_activity", "Syntax-Activities-IntermediateActivities-A_node_activity");
        mapping.mapAssociation(assoc, "Syntax.Activities.IntermediateActivities", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Syntax-Activities-IntermediateActivities-Activity-node Syntax-Activities-IntermediateActivities-ActivityNode-activity");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-IntermediateActivities";   
                      
        // Syntax.Activities.IntermediateActivities.A_target_incoming 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_target_incoming", "Syntax-Activities-IntermediateActivities-A_target_incoming");
        mapping.mapAssociation(assoc, "Syntax.Activities.IntermediateActivities", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Syntax-Activities-IntermediateActivities-ActivityEdge-target Syntax-Activities-IntermediateActivities-ActivityNode-incoming");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-IntermediateActivities";   
                      
        // Syntax.Activities.IntermediateActivities.A_decisionNode_decisionInputFlow 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_decisionNode_decisionInputFlow", "Syntax-Activities-IntermediateActivities-A_decisionNode_decisionInputFlow");
        mapping.mapAssociation(assoc, "Syntax.Activities.IntermediateActivities", this);
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
                      
        // Syntax.Activities.IntermediateActivities.A_decisionInput_decisionNode 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_decisionInput_decisionNode", "Syntax-Activities-IntermediateActivities-A_decisionInput_decisionNode");
        mapping.mapAssociation(assoc, "Syntax.Activities.IntermediateActivities", this);
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
                      
        // Syntax.Activities.CompleteStructuredActivities.A_result_conditionalNode 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_result_conditionalNode", "Syntax-Activities-CompleteStructuredActivities-A_result_conditionalNode");
        mapping.mapAssociation(assoc, "Syntax.Activities.CompleteStructuredActivities", this);
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
                      
        // Syntax.Activities.CompleteStructuredActivities.A_clause_conditionalNode 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_clause_conditionalNode", "Syntax-Activities-CompleteStructuredActivities-A_clause_conditionalNode");
        mapping.mapAssociation(assoc, "Syntax.Activities.CompleteStructuredActivities", this);
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
                      
        // Syntax.Activities.CompleteStructuredActivities.A_clause_test 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_clause_test", "Syntax-Activities-CompleteStructuredActivities-A_clause_test");
        mapping.mapAssociation(assoc, "Syntax.Activities.CompleteStructuredActivities", this);
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
                      
        // Syntax.Activities.CompleteStructuredActivities.A_predecessorClause_successorClause 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_predecessorClause_successorClause", "Syntax-Activities-CompleteStructuredActivities-A_predecessorClause_successorClause");
        mapping.mapAssociation(assoc, "Syntax.Activities.CompleteStructuredActivities", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Syntax-Activities-CompleteStructuredActivities-Clause-predecessorClause Syntax-Activities-CompleteStructuredActivities-Clause-successorClause");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-CompleteStructuredActivities";   
                      
        // Syntax.Activities.CompleteStructuredActivities.A_bodyOutput_clause 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_bodyOutput_clause", "Syntax-Activities-CompleteStructuredActivities-A_bodyOutput_clause");
        mapping.mapAssociation(assoc, "Syntax.Activities.CompleteStructuredActivities", this);
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
                      
        // Syntax.Activities.CompleteStructuredActivities.A_clause_body 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_clause_body", "Syntax-Activities-CompleteStructuredActivities-A_clause_body");
        mapping.mapAssociation(assoc, "Syntax.Activities.CompleteStructuredActivities", this);
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
                      
        // Syntax.Activities.CompleteStructuredActivities.A_decider_clause 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_decider_clause", "Syntax-Activities-CompleteStructuredActivities-A_decider_clause");
        mapping.mapAssociation(assoc, "Syntax.Activities.CompleteStructuredActivities", this);
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
                      
        // Syntax.Activities.CompleteStructuredActivities.A_test_loopNode 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_test_loopNode", "Syntax-Activities-CompleteStructuredActivities-A_test_loopNode");
        mapping.mapAssociation(assoc, "Syntax.Activities.CompleteStructuredActivities", this);
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
                      
        // Syntax.Activities.CompleteStructuredActivities.A_loopVariableInput_loopNode 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_loopVariableInput_loopNode", "Syntax-Activities-CompleteStructuredActivities-A_loopVariableInput_loopNode");
        mapping.mapAssociation(assoc, "Syntax.Activities.CompleteStructuredActivities", this);
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
                      
        // Syntax.Activities.CompleteStructuredActivities.A_bodyOutput_loopNode 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_bodyOutput_loopNode", "Syntax-Activities-CompleteStructuredActivities-A_bodyOutput_loopNode");
        mapping.mapAssociation(assoc, "Syntax.Activities.CompleteStructuredActivities", this);
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
                      
        // Syntax.Activities.CompleteStructuredActivities.A_decider_loopNode 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_decider_loopNode", "Syntax-Activities-CompleteStructuredActivities-A_decider_loopNode");
        mapping.mapAssociation(assoc, "Syntax.Activities.CompleteStructuredActivities", this);
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
                      
        // Syntax.Activities.CompleteStructuredActivities.A_node_inStructuredNode 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_node_inStructuredNode", "Syntax-Activities-CompleteStructuredActivities-A_node_inStructuredNode");
        mapping.mapAssociation(assoc, "Syntax.Activities.CompleteStructuredActivities", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Syntax-Activities-CompleteStructuredActivities-StructuredActivityNode-node Syntax-Activities-IntermediateActivities-ActivityNode-inStructuredNode");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-CompleteStructuredActivities";   
                      
        // Syntax.Activities.CompleteStructuredActivities.A_edge_inStructuredNode 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_edge_inStructuredNode", "Syntax-Activities-CompleteStructuredActivities-A_edge_inStructuredNode");
        mapping.mapAssociation(assoc, "Syntax.Activities.CompleteStructuredActivities", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Syntax-Activities-CompleteStructuredActivities-StructuredActivityNode-edge Syntax-Activities-IntermediateActivities-ActivityEdge-inStructuredNode");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-CompleteStructuredActivities";   
                      
        // Syntax.Activities.CompleteStructuredActivities.A_activity_structuredNode 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_activity_structuredNode", "Syntax-Activities-CompleteStructuredActivities-A_activity_structuredNode");
        mapping.mapAssociation(assoc, "Syntax.Activities.CompleteStructuredActivities", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Syntax-Activities-CompleteStructuredActivities-StructuredActivityNode-activity Syntax-Activities-IntermediateActivities-Activity-structuredNode");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-CompleteStructuredActivities";   
                      
        // Syntax.Activities.CompleteStructuredActivities.A_loopNode_bodyPart 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_loopNode_bodyPart", "Syntax-Activities-CompleteStructuredActivities-A_loopNode_bodyPart");
        mapping.mapAssociation(assoc, "Syntax.Activities.CompleteStructuredActivities", this);
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
                      
        // Syntax.Activities.CompleteStructuredActivities.A_result_loopNode 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_result_loopNode", "Syntax-Activities-CompleteStructuredActivities-A_result_loopNode");
        mapping.mapAssociation(assoc, "Syntax.Activities.CompleteStructuredActivities", this);
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
                      
        // Syntax.Activities.CompleteStructuredActivities.A_loopVariable_loopNode 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_loopVariable_loopNode", "Syntax-Activities-CompleteStructuredActivities-A_loopVariable_loopNode");
        mapping.mapAssociation(assoc, "Syntax.Activities.CompleteStructuredActivities", this);
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
                      
        // Syntax.Activities.CompleteStructuredActivities.A_loopNode_setupPart 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_loopNode_setupPart", "Syntax-Activities-CompleteStructuredActivities-A_loopNode_setupPart");
        mapping.mapAssociation(assoc, "Syntax.Activities.CompleteStructuredActivities", this);
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
                      
        // Syntax.Activities.CompleteStructuredActivities.A_structuredNodeOutput_structuredActivityNode 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_structuredNodeOutput_structuredActivityNode", "Syntax-Activities-CompleteStructuredActivities-A_structuredNodeOutput_structuredActivityNode");
        mapping.mapAssociation(assoc, "Syntax.Activities.CompleteStructuredActivities", this);
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
                      
        // Syntax.Activities.CompleteStructuredActivities.A_structuredNodeInput_structuredActivityNode 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_structuredNodeInput_structuredActivityNode", "Syntax-Activities-CompleteStructuredActivities-A_structuredNodeInput_structuredActivityNode");
        mapping.mapAssociation(assoc, "Syntax.Activities.CompleteStructuredActivities", this);
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
                      
        // Syntax.Activities.ExtraStructuredActivities.A_outputElement_regionAsOutput 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_outputElement_regionAsOutput", "Syntax-Activities-ExtraStructuredActivities-A_outputElement_regionAsOutput");
        mapping.mapAssociation(assoc, "Syntax.Activities.ExtraStructuredActivities", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Syntax-Activities-ExtraStructuredActivities-ExpansionRegion-outputElement Syntax-Activities-ExtraStructuredActivities-ExpansionNode-regionAsOutput");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Activities-ExtraStructuredActivities";   
                      
        // Syntax.Activities.ExtraStructuredActivities.A_inputElement_regionAsInput 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_inputElement_regionAsInput", "Syntax-Activities-ExtraStructuredActivities-A_inputElement_regionAsInput");
        mapping.mapAssociation(assoc, "Syntax.Activities.ExtraStructuredActivities", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Syntax-Activities-ExtraStructuredActivities-ExpansionRegion-inputElement Syntax-Activities-ExtraStructuredActivities-ExpansionNode-regionAsInput");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Syntax-Actions-BasicActions";   
                      
        // Syntax.Actions.BasicActions.A_action_output 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_action_output", "Syntax-Actions-BasicActions-A_action_output");
        mapping.mapAssociation(assoc, "Syntax.Actions.BasicActions", this);
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
                      
        // Syntax.Actions.BasicActions.A_context_action 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_context_action", "Syntax-Actions-BasicActions-A_context_action");
        mapping.mapAssociation(assoc, "Syntax.Actions.BasicActions", this);
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
                      
        // Syntax.Actions.BasicActions.A_operation_callOperationAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_operation_callOperationAction", "Syntax-Actions-BasicActions-A_operation_callOperationAction");
        mapping.mapAssociation(assoc, "Syntax.Actions.BasicActions", this);
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
                      
        // Syntax.Actions.BasicActions.A_target_callOperationAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_target_callOperationAction", "Syntax-Actions-BasicActions-A_target_callOperationAction");
        mapping.mapAssociation(assoc, "Syntax.Actions.BasicActions", this);
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
                      
        // Syntax.Actions.BasicActions.A_behavior_callBehaviorAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_behavior_callBehaviorAction", "Syntax-Actions-BasicActions-A_behavior_callBehaviorAction");
        mapping.mapAssociation(assoc, "Syntax.Actions.BasicActions", this);
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
                      
        // Syntax.Actions.BasicActions.A_result_callAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_result_callAction", "Syntax-Actions-BasicActions-A_result_callAction");
        mapping.mapAssociation(assoc, "Syntax.Actions.BasicActions", this);
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
                      
        // Syntax.Actions.BasicActions.A_signal_sendSignalAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_signal_sendSignalAction", "Syntax-Actions-BasicActions-A_signal_sendSignalAction");
        mapping.mapAssociation(assoc, "Syntax.Actions.BasicActions", this);
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
                      
        // Syntax.Actions.BasicActions.A_target_sendSignalAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_target_sendSignalAction", "Syntax-Actions-BasicActions-A_target_sendSignalAction");
        mapping.mapAssociation(assoc, "Syntax.Actions.BasicActions", this);
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
                      
        // Syntax.Actions.BasicActions.A_action_input 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_action_input", "Syntax-Actions-BasicActions-A_action_input");
        mapping.mapAssociation(assoc, "Syntax.Actions.BasicActions", this);
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
                      
        // Syntax.Actions.BasicActions.A_argument_invocationAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_argument_invocationAction", "Syntax-Actions-BasicActions-A_argument_invocationAction");
        mapping.mapAssociation(assoc, "Syntax.Actions.BasicActions", this);
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
                      
        // Syntax.Actions.IntermediateActions.A_target_destroyObjectAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_target_destroyObjectAction", "Syntax-Actions-IntermediateActions-A_target_destroyObjectAction");
        mapping.mapAssociation(assoc, "Syntax.Actions.IntermediateActions", this);
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
                      
        // Syntax.Actions.IntermediateActions.A_endData_destroyLinkAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_endData_destroyLinkAction", "Syntax-Actions-IntermediateActions-A_endData_destroyLinkAction");
        mapping.mapAssociation(assoc, "Syntax.Actions.IntermediateActions", this);
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
                      
        // Syntax.Actions.IntermediateActions.A_result_createObjectAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_result_createObjectAction", "Syntax-Actions-IntermediateActions-A_result_createObjectAction");
        mapping.mapAssociation(assoc, "Syntax.Actions.IntermediateActions", this);
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
                      
        // Syntax.Actions.IntermediateActions.A_classifier_createObjectAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_classifier_createObjectAction", "Syntax-Actions-IntermediateActions-A_classifier_createObjectAction");
        mapping.mapAssociation(assoc, "Syntax.Actions.IntermediateActions", this);
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
                      
        // Syntax.Actions.IntermediateActions.A_endData_createLinkAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_endData_createLinkAction", "Syntax-Actions-IntermediateActions-A_endData_createLinkAction");
        mapping.mapAssociation(assoc, "Syntax.Actions.IntermediateActions", this);
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
                      
        // Syntax.Actions.IntermediateActions.A_result_clearStructuralFeatureAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_result_clearStructuralFeatureAction", "Syntax-Actions-IntermediateActions-A_result_clearStructuralFeatureAction");
        mapping.mapAssociation(assoc, "Syntax.Actions.IntermediateActions", this);
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
                      
        // Syntax.Actions.IntermediateActions.A_object_clearAssociationAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_object_clearAssociationAction", "Syntax-Actions-IntermediateActions-A_object_clearAssociationAction");
        mapping.mapAssociation(assoc, "Syntax.Actions.IntermediateActions", this);
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
                      
        // Syntax.Actions.IntermediateActions.A_association_clearAssociationAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_association_clearAssociationAction", "Syntax-Actions-IntermediateActions-A_association_clearAssociationAction");
        mapping.mapAssociation(assoc, "Syntax.Actions.IntermediateActions", this);
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
                      
        // Syntax.Actions.IntermediateActions.A_destroyAt_linkEndDestructionData 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_destroyAt_linkEndDestructionData", "Syntax-Actions-IntermediateActions-A_destroyAt_linkEndDestructionData");
        mapping.mapAssociation(assoc, "Syntax.Actions.IntermediateActions", this);
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
                      
        // Syntax.Actions.IntermediateActions.A_value_linkEndData 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_value_linkEndData", "Syntax-Actions-IntermediateActions-A_value_linkEndData");
        mapping.mapAssociation(assoc, "Syntax.Actions.IntermediateActions", this);
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
                      
        // Syntax.Actions.IntermediateActions.A_end_linkEndData 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_end_linkEndData", "Syntax-Actions-IntermediateActions-A_end_linkEndData");
        mapping.mapAssociation(assoc, "Syntax.Actions.IntermediateActions", this);
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
                      
        // Syntax.Actions.IntermediateActions.A_insertAt_linkEndCreationData 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_insertAt_linkEndCreationData", "Syntax-Actions-IntermediateActions-A_insertAt_linkEndCreationData");
        mapping.mapAssociation(assoc, "Syntax.Actions.IntermediateActions", this);
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
                      
        // Syntax.Actions.IntermediateActions.A_endData_linkAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_endData_linkAction", "Syntax-Actions-IntermediateActions-A_endData_linkAction");
        mapping.mapAssociation(assoc, "Syntax.Actions.IntermediateActions", this);
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
                      
        // Syntax.Actions.IntermediateActions.A_result_readStructuralFeatureAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_result_readStructuralFeatureAction", "Syntax-Actions-IntermediateActions-A_result_readStructuralFeatureAction");
        mapping.mapAssociation(assoc, "Syntax.Actions.IntermediateActions", this);
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
                      
        // Syntax.Actions.IntermediateActions.A_result_readSelfAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_result_readSelfAction", "Syntax-Actions-IntermediateActions-A_result_readSelfAction");
        mapping.mapAssociation(assoc, "Syntax.Actions.IntermediateActions", this);
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
                      
        // Syntax.Actions.IntermediateActions.A_result_readLinkAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_result_readLinkAction", "Syntax-Actions-IntermediateActions-A_result_readLinkAction");
        mapping.mapAssociation(assoc, "Syntax.Actions.IntermediateActions", this);
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
                      
        // Syntax.Actions.IntermediateActions.A_removeAt_removeStructuralFeatureValueAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_removeAt_removeStructuralFeatureValueAction", "Syntax-Actions-IntermediateActions-A_removeAt_removeStructuralFeatureValueAction");
        mapping.mapAssociation(assoc, "Syntax.Actions.IntermediateActions", this);
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
                      
        // Syntax.Actions.IntermediateActions.A_result_writeStructuralFeatureAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_result_writeStructuralFeatureAction", "Syntax-Actions-IntermediateActions-A_result_writeStructuralFeatureAction");
        mapping.mapAssociation(assoc, "Syntax.Actions.IntermediateActions", this);
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
                      
        // Syntax.Actions.IntermediateActions.A_value_writeStructuralFeatureAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_value_writeStructuralFeatureAction", "Syntax-Actions-IntermediateActions-A_value_writeStructuralFeatureAction");
        mapping.mapAssociation(assoc, "Syntax.Actions.IntermediateActions", this);
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
                      
        // Syntax.Actions.IntermediateActions.A_value_valueSpecificationAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_value_valueSpecificationAction", "Syntax-Actions-IntermediateActions-A_value_valueSpecificationAction");
        mapping.mapAssociation(assoc, "Syntax.Actions.IntermediateActions", this);
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
                      
        // Syntax.Actions.IntermediateActions.A_result_valueSpecificationAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_result_valueSpecificationAction", "Syntax-Actions-IntermediateActions-A_result_valueSpecificationAction");
        mapping.mapAssociation(assoc, "Syntax.Actions.IntermediateActions", this);
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
                      
        // Syntax.Actions.IntermediateActions.A_first_testIdentityAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_first_testIdentityAction", "Syntax-Actions-IntermediateActions-A_first_testIdentityAction");
        mapping.mapAssociation(assoc, "Syntax.Actions.IntermediateActions", this);
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
                      
        // Syntax.Actions.IntermediateActions.A_result_testIdentityAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_result_testIdentityAction", "Syntax-Actions-IntermediateActions-A_result_testIdentityAction");
        mapping.mapAssociation(assoc, "Syntax.Actions.IntermediateActions", this);
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
                      
        // Syntax.Actions.IntermediateActions.A_second_testIdentityAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_second_testIdentityAction", "Syntax-Actions-IntermediateActions-A_second_testIdentityAction");
        mapping.mapAssociation(assoc, "Syntax.Actions.IntermediateActions", this);
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
                      
        // Syntax.Actions.IntermediateActions.A_object_structuralFeatureAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_object_structuralFeatureAction", "Syntax-Actions-IntermediateActions-A_object_structuralFeatureAction");
        mapping.mapAssociation(assoc, "Syntax.Actions.IntermediateActions", this);
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
                      
        // Syntax.Actions.IntermediateActions.A_structuralFeatureAction_structuralFeature 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_structuralFeatureAction_structuralFeature", "Syntax-Actions-IntermediateActions-A_structuralFeatureAction_structuralFeature");
        mapping.mapAssociation(assoc, "Syntax.Actions.IntermediateActions", this);
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
                      
        // Syntax.Actions.IntermediateActions.A_insertAt_addStructuralFeatureValueAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_insertAt_addStructuralFeatureValueAction", "Syntax-Actions-IntermediateActions-A_insertAt_addStructuralFeatureValueAction");
        mapping.mapAssociation(assoc, "Syntax.Actions.IntermediateActions", this);
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
                      
        // Syntax.Actions.IntermediateActions.A_inputValue_linkAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_inputValue_linkAction", "Syntax-Actions-IntermediateActions-A_inputValue_linkAction");
        mapping.mapAssociation(assoc, "Syntax.Actions.IntermediateActions", this);
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
                      
        // Syntax.Actions.CompleteActions.A_result_acceptEventAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_result_acceptEventAction", "Syntax-Actions-CompleteActions-A_result_acceptEventAction");
        mapping.mapAssociation(assoc, "Syntax.Actions.CompleteActions", this);
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
                      
        // Syntax.Actions.CompleteActions.A_trigger_acceptEventAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_trigger_acceptEventAction", "Syntax-Actions-CompleteActions-A_trigger_acceptEventAction");
        mapping.mapAssociation(assoc, "Syntax.Actions.CompleteActions", this);
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
                      
        // Syntax.Actions.CompleteActions.A_reclassifyObjectAction_object 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_reclassifyObjectAction_object", "Syntax-Actions-CompleteActions-A_reclassifyObjectAction_object");
        mapping.mapAssociation(assoc, "Syntax.Actions.CompleteActions", this);
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
                      
        // Syntax.Actions.CompleteActions.A_oldClassifier_reclassifyObjectAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_oldClassifier_reclassifyObjectAction", "Syntax-Actions-CompleteActions-A_oldClassifier_reclassifyObjectAction");
        mapping.mapAssociation(assoc, "Syntax.Actions.CompleteActions", this);
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
                      
        // Syntax.Actions.CompleteActions.A_object_readIsClassifiedObjectAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_object_readIsClassifiedObjectAction", "Syntax-Actions-CompleteActions-A_object_readIsClassifiedObjectAction");
        mapping.mapAssociation(assoc, "Syntax.Actions.CompleteActions", this);
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
                      
        // Syntax.Actions.CompleteActions.A_result_readIsClassifiedObjectAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_result_readIsClassifiedObjectAction", "Syntax-Actions-CompleteActions-A_result_readIsClassifiedObjectAction");
        mapping.mapAssociation(assoc, "Syntax.Actions.CompleteActions", this);
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
                      
        // Syntax.Actions.CompleteActions.A_classifier_readIsClassifiedObjectAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_classifier_readIsClassifiedObjectAction", "Syntax-Actions-CompleteActions-A_classifier_readIsClassifiedObjectAction");
        mapping.mapAssociation(assoc, "Syntax.Actions.CompleteActions", this);
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
                      
        // Syntax.Actions.CompleteActions.A_classifier_readExtentAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_classifier_readExtentAction", "Syntax-Actions-CompleteActions-A_classifier_readExtentAction");
        mapping.mapAssociation(assoc, "Syntax.Actions.CompleteActions", this);
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
                      
        // Syntax.Actions.CompleteActions.A_result_readExtentAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_result_readExtentAction", "Syntax-Actions-CompleteActions-A_result_readExtentAction");
        mapping.mapAssociation(assoc, "Syntax.Actions.CompleteActions", this);
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
                      
        // Syntax.Actions.CompleteActions.A_result_reduceAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_result_reduceAction", "Syntax-Actions-CompleteActions-A_result_reduceAction");
        mapping.mapAssociation(assoc, "Syntax.Actions.CompleteActions", this);
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
                      
        // Syntax.Actions.CompleteActions.A_collection_reduceAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_collection_reduceAction", "Syntax-Actions-CompleteActions-A_collection_reduceAction");
        mapping.mapAssociation(assoc, "Syntax.Actions.CompleteActions", this);
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
                      
        // Syntax.Actions.CompleteActions.A_reducer_reduceAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_reducer_reduceAction", "Syntax-Actions-CompleteActions-A_reducer_reduceAction");
        mapping.mapAssociation(assoc, "Syntax.Actions.CompleteActions", this);
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
                      
        // Syntax.Actions.CompleteActions.A_object_startObjectBehaviorAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_object_startObjectBehaviorAction", "Syntax-Actions-CompleteActions-A_object_startObjectBehaviorAction");
        mapping.mapAssociation(assoc, "Syntax.Actions.CompleteActions", this);
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
                      
        // Syntax.Actions.CompleteActions.A_object_startClassifierBehaviorAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_object_startClassifierBehaviorAction", "Syntax-Actions-CompleteActions-A_object_startClassifierBehaviorAction");
        mapping.mapAssociation(assoc, "Syntax.Actions.CompleteActions", this);
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
                      
        // Syntax.Actions.CompleteActions.A_newClassifier_reclassifyObjectAction 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_newClassifier_reclassifyObjectAction", "Syntax-Actions-CompleteActions-A_newClassifier_reclassifyObjectAction");
        mapping.mapAssociation(assoc, "Syntax.Actions.CompleteActions", this);
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
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Actions-BasicActions";   
                      
        // Semantics.Actions.BasicActions.A_pinActivations_actionActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_pinActivations_actionActivation", "Semantics-Actions-BasicActions-A_pinActivations_actionActivation");
        mapping.mapAssociation(assoc, "Semantics.Actions.BasicActions", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Semantics-Actions-BasicActions-ActionActivation-pinActivations Semantics-Actions-BasicActions-PinActivation-actionActivation");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Actions-BasicActions";   
                      
        // Semantics.Actions.BasicActions.A_callExecutions_callActionActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_callExecutions_callActionActivation", "Semantics-Actions-BasicActions-A_callExecutions_callActionActivation");
        mapping.mapAssociation(assoc, "Semantics.Actions.BasicActions", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "", 
    	    "Semantics-Actions-BasicActions-A_callExecutions_callActionActivation-_ownedEnd.0",
    	    "Semantics-Actions-BasicActions-CallActionActivation",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Semantics-Actions-BasicActions-CallActionActivation-callExecutions Semantics-Actions-BasicActions-A_callExecutions_callActionActivation-_ownedEnd.0");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Actions-CompleteActions";   
                      
        // Semantics.Actions.CompleteActions.A_actionActivation_eventAccepter 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_actionActivation_eventAccepter", "Semantics-Actions-CompleteActions-A_actionActivation_eventAccepter");
        mapping.mapAssociation(assoc, "Semantics.Actions.CompleteActions", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Semantics-Actions-CompleteActions-AcceptEventActionEventAccepter-actionActivation Semantics-Actions-CompleteActions-AcceptEventActionActivation-eventAccepter");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Actions-CompleteActions";   
                      
        // Semantics.Actions.CompleteActions.A_currentExecution_reduceActionActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_currentExecution_reduceActionActivation", "Semantics-Actions-CompleteActions-A_currentExecution_reduceActionActivation");
        mapping.mapAssociation(assoc, "Semantics.Actions.CompleteActions", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "", 
    	    "Semantics-Actions-CompleteActions-A_currentExecution_reduceActionActivation-_ownedEnd.0",
    	    "Semantics-Actions-CompleteActions-ReduceActionActivation",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Semantics-Actions-CompleteActions-ReduceActionActivation-currentExecution Semantics-Actions-CompleteActions-A_currentExecution_reduceActionActivation-_ownedEnd.0");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Activities-CompleteStructuredActivities";   
                      
        // Semantics.Activities.CompleteStructuredActivities.A_clause_clauseActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_clause_clauseActivation", "Semantics-Activities-CompleteStructuredActivities-A_clause_clauseActivation");
        mapping.mapAssociation(assoc, "Semantics.Activities.CompleteStructuredActivities", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "", 
    	    "Semantics-Activities-CompleteStructuredActivities-A_clause_clauseActivation-_ownedEnd.0",
    	    "Semantics-Activities-CompleteStructuredActivities-ClauseActivation",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Semantics-Activities-CompleteStructuredActivities-ClauseActivation-clause Semantics-Activities-CompleteStructuredActivities-A_clause_clauseActivation-_ownedEnd.0");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Activities-CompleteStructuredActivities";   
                      
        // Semantics.Activities.CompleteStructuredActivities.A_clauseActivations_conditionalNodeActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_clauseActivations_conditionalNodeActivation", "Semantics-Activities-CompleteStructuredActivities-A_clauseActivations_conditionalNodeActivation");
        mapping.mapAssociation(assoc, "Semantics.Activities.CompleteStructuredActivities", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Semantics-Activities-CompleteStructuredActivities-ConditionalNodeActivation-clauseActivations Semantics-Activities-CompleteStructuredActivities-ClauseActivation-conditionalNodeActivation");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Activities-CompleteStructuredActivities";   
                      
        // Semantics.Activities.CompleteStructuredActivities.A_selectedClauses_clauseActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_selectedClauses_clauseActivation", "Semantics-Activities-CompleteStructuredActivities-A_selectedClauses_clauseActivation");
        mapping.mapAssociation(assoc, "Semantics.Activities.CompleteStructuredActivities", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "", 
    	    "Semantics-Activities-CompleteStructuredActivities-A_selectedClauses_clauseActivation-_ownedEnd.0",
    	    "Semantics-Activities-CompleteStructuredActivities-ConditionalNodeActivation",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Semantics-Activities-CompleteStructuredActivities-ConditionalNodeActivation-selectedClauses Semantics-Activities-CompleteStructuredActivities-A_selectedClauses_clauseActivation-_ownedEnd.0");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Activities-CompleteStructuredActivities";   
                      
        // Semantics.Activities.CompleteStructuredActivities.A_activationGroup_containingNodeActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_activationGroup_containingNodeActivation", "Semantics-Activities-CompleteStructuredActivities-A_activationGroup_containingNodeActivation");
        mapping.mapAssociation(assoc, "Semantics.Activities.CompleteStructuredActivities", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Semantics-Activities-CompleteStructuredActivities-StructuredActivityNodeActivation-activationGroup Semantics-Activities-IntermediateActivities-ActivityNodeActivationGroup-containingNodeActivation");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Activities-CompleteStructuredActivities";   
                      
        // Semantics.Activities.CompleteStructuredActivities.A_bodyOutputLists_loopNodeActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_bodyOutputLists_loopNodeActivation", "Semantics-Activities-CompleteStructuredActivities-A_bodyOutputLists_loopNodeActivation");
        mapping.mapAssociation(assoc, "Semantics.Activities.CompleteStructuredActivities", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "", 
    	    "Semantics-Activities-CompleteStructuredActivities-A_bodyOutputLists_loopNodeActivation-_ownedEnd.0",
    	    "Semantics-Activities-CompleteStructuredActivities-LoopNodeActivation",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Semantics-Activities-CompleteStructuredActivities-LoopNodeActivation-bodyOutputLists Semantics-Activities-CompleteStructuredActivities-A_bodyOutputLists_loopNodeActivation-_ownedEnd.0");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Activities-CompleteStructuredActivities";   
                      
        // Semantics.Activities.CompleteStructuredActivities.A_values_values 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_values_values", "Semantics-Activities-CompleteStructuredActivities-A_values_values");
        mapping.mapAssociation(assoc, "Semantics.Activities.CompleteStructuredActivities", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "", 
    	    "Semantics-Activities-CompleteStructuredActivities-A_values_values-_ownedEnd.0",
    	    "Semantics-Activities-CompleteStructuredActivities-Values",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Semantics-Activities-CompleteStructuredActivities-Values-values Semantics-Activities-CompleteStructuredActivities-A_values_values-_ownedEnd.0");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Activities-IntermediateActivities";   
                      
        // Semantics.Activities.IntermediateActivities.A_nodeActivations_group 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_nodeActivations_group", "Semantics-Activities-IntermediateActivities-A_nodeActivations_group");
        mapping.mapAssociation(assoc, "Semantics.Activities.IntermediateActivities", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Semantics-Activities-IntermediateActivities-ActivityNodeActivationGroup-nodeActivations Semantics-Activities-IntermediateActivities-ActivityNodeActivation-group");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Activities-IntermediateActivities";   
                      
        // Semantics.Activities.IntermediateActivities.A_edgeInstances_group 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_edgeInstances_group", "Semantics-Activities-IntermediateActivities-A_edgeInstances_group");
        mapping.mapAssociation(assoc, "Semantics.Activities.IntermediateActivities", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Semantics-Activities-IntermediateActivities-ActivityNodeActivationGroup-edgeInstances Semantics-Activities-IntermediateActivities-ActivityEdgeInstance-group");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Activities-IntermediateActivities";   
                      
        // Semantics.Activities.IntermediateActivities.A_heldTokens_holder 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_heldTokens_holder", "Semantics-Activities-IntermediateActivities-A_heldTokens_holder");
        mapping.mapAssociation(assoc, "Semantics.Activities.IntermediateActivities", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Semantics-Activities-IntermediateActivities-ActivityNodeActivation-heldTokens Semantics-Activities-IntermediateActivities-Token-holder");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Activities-IntermediateActivities";   
                      
        // Semantics.Activities.IntermediateActivities.A_node_activityNodeActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_node_activityNodeActivation", "Semantics-Activities-IntermediateActivities-A_node_activityNodeActivation");
        mapping.mapAssociation(assoc, "Semantics.Activities.IntermediateActivities", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "", 
    	    "Semantics-Activities-IntermediateActivities-A_node_activityNodeActivation-_ownedEnd.0",
    	    "Semantics-Activities-IntermediateActivities-ActivityNodeActivation",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Semantics-Activities-IntermediateActivities-ActivityNodeActivation-node Semantics-Activities-IntermediateActivities-A_node_activityNodeActivation-_ownedEnd.0");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Activities-IntermediateActivities";   
                      
        // Semantics.Activities.IntermediateActivities.A_incomingEdges_target 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_incomingEdges_target", "Semantics-Activities-IntermediateActivities-A_incomingEdges_target");
        mapping.mapAssociation(assoc, "Semantics.Activities.IntermediateActivities", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Semantics-Activities-IntermediateActivities-ActivityNodeActivation-incomingEdges Semantics-Activities-IntermediateActivities-ActivityEdgeInstance-target");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Activities-IntermediateActivities";   
                      
        // Semantics.Activities.IntermediateActivities.A_activationGroup_activityExecution 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_activationGroup_activityExecution", "Semantics-Activities-IntermediateActivities-A_activationGroup_activityExecution");
        mapping.mapAssociation(assoc, "Semantics.Activities.IntermediateActivities", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Semantics-Activities-IntermediateActivities-ActivityExecution-activationGroup Semantics-Activities-IntermediateActivities-ActivityNodeActivationGroup-activityExecution");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Activities-IntermediateActivities";   
                      
        // Semantics.Activities.IntermediateActivities.A_edge_activityEdgeInstance 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_edge_activityEdgeInstance", "Semantics-Activities-IntermediateActivities-A_edge_activityEdgeInstance");
        mapping.mapAssociation(assoc, "Semantics.Activities.IntermediateActivities", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "", 
    	    "Semantics-Activities-IntermediateActivities-A_edge_activityEdgeInstance-_ownedEnd.0",
    	    "Semantics-Activities-IntermediateActivities-ActivityEdgeInstance",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Semantics-Activities-IntermediateActivities-ActivityEdgeInstance-edge Semantics-Activities-IntermediateActivities-A_edge_activityEdgeInstance-_ownedEnd.0");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Activities-IntermediateActivities";   
                      
        // Semantics.Activities.IntermediateActivities.A_source_outgoingEdges 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_source_outgoingEdges", "Semantics-Activities-IntermediateActivities-A_source_outgoingEdges");
        mapping.mapAssociation(assoc, "Semantics.Activities.IntermediateActivities", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Semantics-Activities-IntermediateActivities-ActivityEdgeInstance-source Semantics-Activities-IntermediateActivities-ActivityNodeActivation-outgoingEdges");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Activities-IntermediateActivities";   
                      
        // Semantics.Activities.IntermediateActivities.A_offers_activityEdgeInstance 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_offers_activityEdgeInstance", "Semantics-Activities-IntermediateActivities-A_offers_activityEdgeInstance");
        mapping.mapAssociation(assoc, "Semantics.Activities.IntermediateActivities", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "", 
    	    "Semantics-Activities-IntermediateActivities-A_offers_activityEdgeInstance-_ownedEnd.0",
    	    "Semantics-Activities-IntermediateActivities-ActivityEdgeInstance",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Semantics-Activities-IntermediateActivities-ActivityEdgeInstance-offers Semantics-Activities-IntermediateActivities-A_offers_activityEdgeInstance-_ownedEnd.0");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Activities-IntermediateActivities";   
                      
        // Semantics.Activities.IntermediateActivities.A_decisionInputExecution_decisionNodeActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_decisionInputExecution_decisionNodeActivation", "Semantics-Activities-IntermediateActivities-A_decisionInputExecution_decisionNodeActivation");
        mapping.mapAssociation(assoc, "Semantics.Activities.IntermediateActivities", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "", 
    	    "Semantics-Activities-IntermediateActivities-A_decisionInputExecution_decisionNodeActivation-_ownedEnd.0",
    	    "Semantics-Activities-IntermediateActivities-DecisionNodeActivation",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Semantics-Activities-IntermediateActivities-DecisionNodeActivation-decisionInputExecution Semantics-Activities-IntermediateActivities-A_decisionInputExecution_decisionNodeActivation-_ownedEnd.0");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Activities-IntermediateActivities";   
                      
        // Semantics.Activities.IntermediateActivities.A_baseToken_forkedToken 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_baseToken_forkedToken", "Semantics-Activities-IntermediateActivities-A_baseToken_forkedToken");
        mapping.mapAssociation(assoc, "Semantics.Activities.IntermediateActivities", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "", 
    	    "Semantics-Activities-IntermediateActivities-A_baseToken_forkedToken-_ownedEnd.0",
    	    "Semantics-Activities-IntermediateActivities-ForkedToken",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Semantics-Activities-IntermediateActivities-ForkedToken-baseToken Semantics-Activities-IntermediateActivities-A_baseToken_forkedToken-_ownedEnd.0");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Activities-IntermediateActivities";   
                      
        // Semantics.Activities.IntermediateActivities.A_value_objectToken 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_value_objectToken", "Semantics-Activities-IntermediateActivities-A_value_objectToken");
        mapping.mapAssociation(assoc, "Semantics.Activities.IntermediateActivities", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "", 
    	    "Semantics-Activities-IntermediateActivities-A_value_objectToken-_ownedEnd.0",
    	    "Semantics-Activities-IntermediateActivities-ObjectToken",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Semantics-Activities-IntermediateActivities-ObjectToken-value Semantics-Activities-IntermediateActivities-A_value_objectToken-_ownedEnd.0");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Activities-IntermediateActivities";   
                      
        // Semantics.Activities.IntermediateActivities.A_offeredTokens_offer 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_offeredTokens_offer", "Semantics-Activities-IntermediateActivities-A_offeredTokens_offer");
        mapping.mapAssociation(assoc, "Semantics.Activities.IntermediateActivities", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "", 
    	    "Semantics-Activities-IntermediateActivities-A_offeredTokens_offer-_ownedEnd.0",
    	    "Semantics-Activities-IntermediateActivities-Offer",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Semantics-Activities-IntermediateActivities-Offer-offeredTokens Semantics-Activities-IntermediateActivities-A_offeredTokens_offer-_ownedEnd.0");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Activities-ExtraStructuredActivities";   
                      
        // Semantics.Activities.ExtraStructuredActivities.A_activationGroups_regionActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_activationGroups_regionActivation", "Semantics-Activities-ExtraStructuredActivities-A_activationGroups_regionActivation");
        mapping.mapAssociation(assoc, "Semantics.Activities.ExtraStructuredActivities", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Semantics-Activities-ExtraStructuredActivities-ExpansionRegionActivation-activationGroups Semantics-Activities-ExtraStructuredActivities-ExpansionActivationGroup-regionActivation");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Activities-ExtraStructuredActivities";   
                      
        // Semantics.Activities.ExtraStructuredActivities.A_regionInputs_expansionActivationGroup 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_regionInputs_expansionActivationGroup", "Semantics-Activities-ExtraStructuredActivities-A_regionInputs_expansionActivationGroup");
        mapping.mapAssociation(assoc, "Semantics.Activities.ExtraStructuredActivities", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "", 
    	    "Semantics-Activities-ExtraStructuredActivities-A_regionInputs_expansionActivationGroup-_ownedEnd.0",
    	    "Semantics-Activities-ExtraStructuredActivities-ExpansionActivationGroup",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Semantics-Activities-ExtraStructuredActivities-ExpansionActivationGroup-regionInputs Semantics-Activities-ExtraStructuredActivities-A_regionInputs_expansionActivationGroup-_ownedEnd.0");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Activities-ExtraStructuredActivities";   
                      
        // Semantics.Activities.ExtraStructuredActivities.A_groupInputs_expansionActivationGroup 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_groupInputs_expansionActivationGroup", "Semantics-Activities-ExtraStructuredActivities-A_groupInputs_expansionActivationGroup");
        mapping.mapAssociation(assoc, "Semantics.Activities.ExtraStructuredActivities", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "", 
    	    "Semantics-Activities-ExtraStructuredActivities-A_groupInputs_expansionActivationGroup-_ownedEnd.0",
    	    "Semantics-Activities-ExtraStructuredActivities-ExpansionActivationGroup",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Semantics-Activities-ExtraStructuredActivities-ExpansionActivationGroup-groupInputs Semantics-Activities-ExtraStructuredActivities-A_groupInputs_expansionActivationGroup-_ownedEnd.0");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Activities-ExtraStructuredActivities";   
                      
        // Semantics.Activities.ExtraStructuredActivities.A_groupOutputs_expansionActivationGroup 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_groupOutputs_expansionActivationGroup", "Semantics-Activities-ExtraStructuredActivities-A_groupOutputs_expansionActivationGroup");
        mapping.mapAssociation(assoc, "Semantics.Activities.ExtraStructuredActivities", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "", 
    	    "Semantics-Activities-ExtraStructuredActivities-A_groupOutputs_expansionActivationGroup-_ownedEnd.0",
    	    "Semantics-Activities-ExtraStructuredActivities-ExpansionActivationGroup",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Semantics-Activities-ExtraStructuredActivities-ExpansionActivationGroup-groupOutputs Semantics-Activities-ExtraStructuredActivities-A_groupOutputs_expansionActivationGroup-_ownedEnd.0");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Activities-ExtraStructuredActivities";   
                      
        // Semantics.Activities.ExtraStructuredActivities.A_inputTokens_expansionRegionActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_inputTokens_expansionRegionActivation", "Semantics-Activities-ExtraStructuredActivities-A_inputTokens_expansionRegionActivation");
        mapping.mapAssociation(assoc, "Semantics.Activities.ExtraStructuredActivities", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "", 
    	    "Semantics-Activities-ExtraStructuredActivities-A_inputTokens_expansionRegionActivation-_ownedEnd.0",
    	    "Semantics-Activities-ExtraStructuredActivities-ExpansionRegionActivation",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Semantics-Activities-ExtraStructuredActivities-ExpansionRegionActivation-inputTokens Semantics-Activities-ExtraStructuredActivities-A_inputTokens_expansionRegionActivation-_ownedEnd.0");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Activities-ExtraStructuredActivities";   
                      
        // Semantics.Activities.ExtraStructuredActivities.A_inputExpansionTokens_expansionRegionActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_inputExpansionTokens_expansionRegionActivation", "Semantics-Activities-ExtraStructuredActivities-A_inputExpansionTokens_expansionRegionActivation");
        mapping.mapAssociation(assoc, "Semantics.Activities.ExtraStructuredActivities", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "", 
    	    "Semantics-Activities-ExtraStructuredActivities-A_inputExpansionTokens_expansionRegionActivation-_ownedEnd.0",
    	    "Semantics-Activities-ExtraStructuredActivities-ExpansionRegionActivation",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Semantics-Activities-ExtraStructuredActivities-ExpansionRegionActivation-inputExpansionTokens Semantics-Activities-ExtraStructuredActivities-A_inputExpansionTokens_expansionRegionActivation-_ownedEnd.0");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Activities-ExtraStructuredActivities";   
                      
        // Semantics.Activities.ExtraStructuredActivities.A_tokens_tokenSet 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_tokens_tokenSet", "Semantics-Activities-ExtraStructuredActivities-A_tokens_tokenSet");
        mapping.mapAssociation(assoc, "Semantics.Activities.ExtraStructuredActivities", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "", 
    	    "Semantics-Activities-ExtraStructuredActivities-A_tokens_tokenSet-_ownedEnd.0",
    	    "Semantics-Activities-ExtraStructuredActivities-TokenSet",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Semantics-Activities-ExtraStructuredActivities-TokenSet-tokens Semantics-Activities-ExtraStructuredActivities-A_tokens_tokenSet-_ownedEnd.0");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Classes-Kernel";   
                      
        // Semantics.Classes.Kernel.A_featureValues_compoundValue 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_featureValues_compoundValue", "Semantics-Classes-Kernel-A_featureValues_compoundValue");
        mapping.mapAssociation(assoc, "Semantics.Classes.Kernel", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "", 
    	    "Semantics-Classes-Kernel-A_featureValues_compoundValue-_ownedEnd.0",
    	    "Semantics-Classes-Kernel-CompoundValue",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Semantics-Classes-Kernel-CompoundValue-featureValues Semantics-Classes-Kernel-A_featureValues_compoundValue-_ownedEnd.0");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Classes-Kernel";   
                      
        // Semantics.Classes.Kernel.A_type_dataValue 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_type_dataValue", "Semantics-Classes-Kernel-A_type_dataValue");
        mapping.mapAssociation(assoc, "Semantics.Classes.Kernel", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "", 
    	    "Semantics-Classes-Kernel-A_type_dataValue-_ownedEnd.0",
    	    "Semantics-Classes-Kernel-DataValue",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Semantics-Classes-Kernel-DataValue-type Semantics-Classes-Kernel-A_type_dataValue-_ownedEnd.0");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Classes-Kernel";   
                      
        // Semantics.Classes.Kernel.A_type_enumerationValue 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_type_enumerationValue", "Semantics-Classes-Kernel-A_type_enumerationValue");
        mapping.mapAssociation(assoc, "Semantics.Classes.Kernel", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "", 
    	    "Semantics-Classes-Kernel-A_type_enumerationValue-_ownedEnd.0",
    	    "Semantics-Classes-Kernel-EnumerationValue",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Semantics-Classes-Kernel-EnumerationValue-type Semantics-Classes-Kernel-A_type_enumerationValue-_ownedEnd.0");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Classes-Kernel";   
                      
        // Semantics.Classes.Kernel.A_literal_enumerationValue 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_literal_enumerationValue", "Semantics-Classes-Kernel-A_literal_enumerationValue");
        mapping.mapAssociation(assoc, "Semantics.Classes.Kernel", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "", 
    	    "Semantics-Classes-Kernel-A_literal_enumerationValue-_ownedEnd.0",
    	    "Semantics-Classes-Kernel-EnumerationValue",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Semantics-Classes-Kernel-EnumerationValue-literal Semantics-Classes-Kernel-A_literal_enumerationValue-_ownedEnd.0");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Classes-Kernel";   
                      
        // Semantics.Classes.Kernel.A_specification_evaluation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_specification_evaluation", "Semantics-Classes-Kernel-A_specification_evaluation");
        mapping.mapAssociation(assoc, "Semantics.Classes.Kernel", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "", 
    	    "Semantics-Classes-Kernel-A_specification_evaluation-_ownedEnd.0",
    	    "Semantics-Classes-Kernel-Evaluation",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Semantics-Classes-Kernel-Evaluation-specification Semantics-Classes-Kernel-A_specification_evaluation-_ownedEnd.0");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Classes-Kernel";   
                      
        // Semantics.Classes.Kernel.A_locus_evaluation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_locus_evaluation", "Semantics-Classes-Kernel-A_locus_evaluation");
        mapping.mapAssociation(assoc, "Semantics.Classes.Kernel", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "", 
    	    "Semantics-Classes-Kernel-A_locus_evaluation-_ownedEnd.0",
    	    "Semantics-Classes-Kernel-Evaluation",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Semantics-Classes-Kernel-Evaluation-locus Semantics-Classes-Kernel-A_locus_evaluation-_ownedEnd.0");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Classes-Kernel";   
                      
        // Semantics.Classes.Kernel.A_locus_extensionalValues 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_locus_extensionalValues", "Semantics-Classes-Kernel-A_locus_extensionalValues");
        mapping.mapAssociation(assoc, "Semantics.Classes.Kernel", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Semantics-Classes-Kernel-ExtensionalValue-locus Semantics-Loci-LociL1-Locus-extensionalValues");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Classes-Kernel";   
                      
        // Semantics.Classes.Kernel.A_values_featureValue 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_values_featureValue", "Semantics-Classes-Kernel-A_values_featureValue");
        mapping.mapAssociation(assoc, "Semantics.Classes.Kernel", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "", 
    	    "Semantics-Classes-Kernel-A_values_featureValue-_ownedEnd.0",
    	    "Semantics-Classes-Kernel-FeatureValue",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Semantics-Classes-Kernel-FeatureValue-values Semantics-Classes-Kernel-A_values_featureValue-_ownedEnd.0");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Classes-Kernel";   
                      
        // Semantics.Classes.Kernel.A_feature_featureValue 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_feature_featureValue", "Semantics-Classes-Kernel-A_feature_featureValue");
        mapping.mapAssociation(assoc, "Semantics.Classes.Kernel", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "", 
    	    "Semantics-Classes-Kernel-A_feature_featureValue-_ownedEnd.0",
    	    "Semantics-Classes-Kernel-FeatureValue",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Semantics-Classes-Kernel-FeatureValue-feature Semantics-Classes-Kernel-A_feature_featureValue-_ownedEnd.0");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Classes-Kernel";   
                      
        // Semantics.Classes.Kernel.A_type_Link 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_type_Link", "Semantics-Classes-Kernel-A_type_Link");
        mapping.mapAssociation(assoc, "Semantics.Classes.Kernel", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "", 
    	    "Semantics-Classes-Kernel-A_type_Link-_ownedEnd.0",
    	    "Semantics-Classes-Kernel-Link",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Semantics-Classes-Kernel-Link-type Semantics-Classes-Kernel-A_type_Link-_ownedEnd.0");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Classes-Kernel";   
                      
        // Semantics.Classes.Kernel.A_objectActivation_object 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_objectActivation_object", "Semantics-Classes-Kernel-A_objectActivation_object");
        mapping.mapAssociation(assoc, "Semantics.Classes.Kernel", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Semantics-Classes-Kernel-Object-objectActivation Semantics-CommonBehaviors-Communications-ObjectActivation-object");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Classes-Kernel";   
                      
        // Semantics.Classes.Kernel.A_types_object 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_types_object", "Semantics-Classes-Kernel-A_types_object");
        mapping.mapAssociation(assoc, "Semantics.Classes.Kernel", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "", 
    	    "Semantics-Classes-Kernel-A_types_object-_ownedEnd.0",
    	    "Semantics-Classes-Kernel-Object",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Semantics-Classes-Kernel-Object-types Semantics-Classes-Kernel-A_types_object-_ownedEnd.0");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Classes-Kernel";   
                      
        // Semantics.Classes.Kernel.A_type_primitiveValue 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_type_primitiveValue", "Semantics-Classes-Kernel-A_type_primitiveValue");
        mapping.mapAssociation(assoc, "Semantics.Classes.Kernel", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "", 
    	    "Semantics-Classes-Kernel-A_type_primitiveValue-_ownedEnd.0",
    	    "Semantics-Classes-Kernel-PrimitiveValue",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Semantics-Classes-Kernel-PrimitiveValue-type Semantics-Classes-Kernel-A_type_primitiveValue-_ownedEnd.0");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Classes-Kernel";   
                      
        // Semantics.Classes.Kernel.A_reference_reference 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_reference_reference", "Semantics-Classes-Kernel-A_reference_reference");
        mapping.mapAssociation(assoc, "Semantics.Classes.Kernel", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "", 
    	    "Semantics-Classes-Kernel-A_reference_reference-_ownedEnd.0",
    	    "Semantics-Classes-Kernel-Reference",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Semantics-Classes-Kernel-Reference-referent Semantics-Classes-Kernel-A_reference_reference-_ownedEnd.0");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-CommonBehaviors-BasicBehaviors";   
                      
        // Semantics.CommonBehaviors.BasicBehaviors.A_context_execution 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_context_execution", "Semantics-CommonBehaviors-BasicBehaviors-A_context_execution");
        mapping.mapAssociation(assoc, "Semantics.CommonBehaviors.BasicBehaviors", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "", 
    	    "Semantics-CommonBehaviors-BasicBehaviors-A_context_execution-_ownedEnd.0",
    	    "Semantics-CommonBehaviors-BasicBehaviors-Execution",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Semantics-CommonBehaviors-BasicBehaviors-Execution-context Semantics-CommonBehaviors-BasicBehaviors-A_context_execution-_ownedEnd.0");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-CommonBehaviors-BasicBehaviors";   
                      
        // Semantics.CommonBehaviors.BasicBehaviors.A_parameterValues_execution 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_parameterValues_execution", "Semantics-CommonBehaviors-BasicBehaviors-A_parameterValues_execution");
        mapping.mapAssociation(assoc, "Semantics.CommonBehaviors.BasicBehaviors", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "", 
    	    "Semantics-CommonBehaviors-BasicBehaviors-A_parameterValues_execution-_ownedEnd.0",
    	    "Semantics-CommonBehaviors-BasicBehaviors-Execution",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Semantics-CommonBehaviors-BasicBehaviors-Execution-parameterValues Semantics-CommonBehaviors-BasicBehaviors-A_parameterValues_execution-_ownedEnd.0");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-CommonBehaviors-BasicBehaviors";   
                      
        // Semantics.CommonBehaviors.BasicBehaviors.A_parameter_parameterValue 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_parameter_parameterValue", "Semantics-CommonBehaviors-BasicBehaviors-A_parameter_parameterValue");
        mapping.mapAssociation(assoc, "Semantics.CommonBehaviors.BasicBehaviors", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "", 
    	    "Semantics-CommonBehaviors-BasicBehaviors-A_parameter_parameterValue-_ownedEnd.0",
    	    "Semantics-CommonBehaviors-BasicBehaviors-ParameterValue",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Semantics-CommonBehaviors-BasicBehaviors-ParameterValue-parameter Semantics-CommonBehaviors-BasicBehaviors-A_parameter_parameterValue-_ownedEnd.0");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-CommonBehaviors-BasicBehaviors";   
                      
        // Semantics.CommonBehaviors.BasicBehaviors.A_values_parameterValue 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_values_parameterValue", "Semantics-CommonBehaviors-BasicBehaviors-A_values_parameterValue");
        mapping.mapAssociation(assoc, "Semantics.CommonBehaviors.BasicBehaviors", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "", 
    	    "Semantics-CommonBehaviors-BasicBehaviors-A_values_parameterValue-_ownedEnd.0",
    	    "Semantics-CommonBehaviors-BasicBehaviors-ParameterValue",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Semantics-CommonBehaviors-BasicBehaviors-ParameterValue-values Semantics-CommonBehaviors-BasicBehaviors-A_values_parameterValue-_ownedEnd.0");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-CommonBehaviors-Communications";   
                      
        // Semantics.CommonBehaviors.Communications.A_classifierBehaviorExecutions_objectActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_classifierBehaviorExecutions_objectActivation", "Semantics-CommonBehaviors-Communications-A_classifierBehaviorExecutions_objectActivation");
        mapping.mapAssociation(assoc, "Semantics.CommonBehaviors.Communications", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Semantics-CommonBehaviors-Communications-ObjectActivation-classifierBehaviorExecutions Semantics-CommonBehaviors-Communications-ClassifierBehaviorExecution-objectActivation");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-CommonBehaviors-Communications";   
                      
        // Semantics.CommonBehaviors.Communications.A_waitingEventAcceptors_objectActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_waitingEventAcceptors_objectActivation", "Semantics-CommonBehaviors-Communications-A_waitingEventAcceptors_objectActivation");
        mapping.mapAssociation(assoc, "Semantics.CommonBehaviors.Communications", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "", 
    	    "Semantics-CommonBehaviors-Communications-A_waitingEventAcceptors_objectActivation-_ownedEnd.0",
    	    "Semantics-CommonBehaviors-Communications-ObjectActivation",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Semantics-CommonBehaviors-Communications-ObjectActivation-waitingEventAccepters Semantics-CommonBehaviors-Communications-A_waitingEventAcceptors_objectActivation-_ownedEnd.0");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-CommonBehaviors-Communications";   
                      
        // Semantics.CommonBehaviors.Communications.A_eventPool_objectActivation 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_eventPool_objectActivation", "Semantics-CommonBehaviors-Communications-A_eventPool_objectActivation");
        mapping.mapAssociation(assoc, "Semantics.CommonBehaviors.Communications", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "", 
    	    "Semantics-CommonBehaviors-Communications-A_eventPool_objectActivation-_ownedEnd.0",
    	    "Semantics-CommonBehaviors-Communications-ObjectActivation",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Semantics-CommonBehaviors-Communications-ObjectActivation-eventPool Semantics-CommonBehaviors-Communications-A_eventPool_objectActivation-_ownedEnd.0");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-CommonBehaviors-Communications";   
                      
        // Semantics.CommonBehaviors.Communications.A_type_signalInstance 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_type_signalInstance", "Semantics-CommonBehaviors-Communications-A_type_signalInstance");
        mapping.mapAssociation(assoc, "Semantics.CommonBehaviors.Communications", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "", 
    	    "Semantics-CommonBehaviors-Communications-A_type_signalInstance-_ownedEnd.0",
    	    "Semantics-CommonBehaviors-Communications-SignalInstance",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Semantics-CommonBehaviors-Communications-SignalInstance-type Semantics-CommonBehaviors-Communications-A_type_signalInstance-_ownedEnd.0");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-CommonBehaviors-Communications";   
                      
        // Semantics.CommonBehaviors.Communications.A_classifier_classifierBehaviorExecution 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_classifier_classifierBehaviorExecution", "Semantics-CommonBehaviors-Communications-A_classifier_classifierBehaviorExecution");
        mapping.mapAssociation(assoc, "Semantics.CommonBehaviors.Communications", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "", 
    	    "Semantics-CommonBehaviors-Communications-A_classifier_classifierBehaviorExecution-_ownedEnd.0",
    	    "Semantics-CommonBehaviors-Communications-ClassifierBehaviorExecution",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Semantics-CommonBehaviors-Communications-ClassifierBehaviorExecution-classifier Semantics-CommonBehaviors-Communications-A_classifier_classifierBehaviorExecution-_ownedEnd.0");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-CommonBehaviors-Communications";   
                      
        // Semantics.CommonBehaviors.Communications.A_execution_classifierBehaviorExecution 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_execution_classifierBehaviorExecution", "Semantics-CommonBehaviors-Communications-A_execution_classifierBehaviorExecution");
        mapping.mapAssociation(assoc, "Semantics.CommonBehaviors.Communications", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "", 
    	    "Semantics-CommonBehaviors-Communications-A_execution_classifierBehaviorExecution-_ownedEnd.0",
    	    "Semantics-CommonBehaviors-Communications-ClassifierBehaviorExecution",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Semantics-CommonBehaviors-Communications-ClassifierBehaviorExecution-execution Semantics-CommonBehaviors-Communications-A_execution_classifierBehaviorExecution-_ownedEnd.0");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Loci-LociL1";   
                      
        // Semantics.Loci.LociL1.A_executor_locus 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_executor_locus", "Semantics-Loci-LociL1-A_executor_locus");
        mapping.mapAssociation(assoc, "Semantics.Loci.LociL1", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Semantics-Loci-LociL1-Locus-executor Semantics-Loci-LociL1-Executor-locus");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Loci-LociL1";   
                      
        // Semantics.Loci.LociL1.A_factory_locus 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_factory_locus", "Semantics-Loci-LociL1-A_factory_locus");
        mapping.mapAssociation(assoc, "Semantics.Loci.LociL1", this);
        // create owned ends
    	    
        factory.createAssociationEnds(assoc, "Semantics-Loci-LociL1-Locus-factory Semantics-Loci-LociL1-ExecutionFactory-locus");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Loci-LociL1";   
                      
        // Semantics.Loci.LociL1.A_builtInTypes_executionFactory 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_builtInTypes_executionFactory", "Semantics-Loci-LociL1-A_builtInTypes_executionFactory");
        mapping.mapAssociation(assoc, "Semantics.Loci.LociL1", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "", 
    	    "Semantics-Loci-LociL1-A_builtInTypes_executionFactory-_ownedEnd.0",
    	    "Semantics-Loci-LociL1-ExecutionFactory",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Semantics-Loci-LociL1-ExecutionFactory-builtInTypes Semantics-Loci-LociL1-A_builtInTypes_executionFactory-_ownedEnd.0");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Loci-LociL1";   
                      
        // Semantics.Loci.LociL1.A_strategies_executionFactory 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_strategies_executionFactory", "Semantics-Loci-LociL1-A_strategies_executionFactory");
        mapping.mapAssociation(assoc, "Semantics.Loci.LociL1", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "", 
    	    "Semantics-Loci-LociL1-A_strategies_executionFactory-_ownedEnd.0",
    	    "Semantics-Loci-LociL1-ExecutionFactory",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Semantics-Loci-LociL1-ExecutionFactory-strategies Semantics-Loci-LociL1-A_strategies_executionFactory-_ownedEnd.0");
                                                      
        packageId = this.artifact.getUrn() + "#" + "Semantics-Loci-LociL1";   
                      
        // Semantics.Loci.LociL1.A_primitiveBehaviorPrototypes_executionFactory 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("A_primitiveBehaviorPrototypes_executionFactory", "Semantics-Loci-LociL1-A_primitiveBehaviorPrototypes_executionFactory");
        mapping.mapAssociation(assoc, "Semantics.Loci.LociL1", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "", 
    	    "Semantics-Loci-LociL1-A_primitiveBehaviorPrototypes_executionFactory-_ownedEnd.0",
    	    "Semantics-Loci-LociL1-ExecutionFactory",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "Semantics-Loci-LociL1-ExecutionFactory-primitiveBehaviorPrototypes Semantics-Loci-LociL1-A_primitiveBehaviorPrototypes_executionFactory-_ownedEnd.0");
    
    }

}
    
    