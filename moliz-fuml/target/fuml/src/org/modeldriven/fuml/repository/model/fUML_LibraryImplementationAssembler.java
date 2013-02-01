
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
public class fUML_LibraryImplementationAssembler extends ModelAssembler 
    implements RepositoryArtifact
{

    private static Log log = LogFactory.getLog(fUML_LibraryImplementationAssembler.class);
    private ModelFactory factory;

    public fUML_LibraryImplementationAssembler(Artifact artifact, RepositoryMapping mapping, Repository model) {
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
         
                     
        // org.modeldriven                                                    
    	pkg  = factory.createPackage("modeldriven", "org.modeldriven", "_zLHE2IA_zd2xsvzRuzHZYz", pkg, this);
    	mapping.mapPackage(pkg, "org", this); 
                
                     
        // org.modeldriven.fuml                                                    
    	pkg  = factory.createPackage("fuml", "org.modeldriven.fuml", "_xLHE2IA_xd2dsvzxuxHZYx", pkg, this);
    	mapping.mapPackage(pkg, "org.modeldriven", this); 
                
                     
        // org.modeldriven.fuml.library                                                    
    	pkg  = factory.createPackage("library", "org.modeldriven.fuml.library", "_wLHE2IA_Ed2xsvzRuEHZYg", pkg, this);
    	mapping.mapPackage(pkg, "org.modeldriven.fuml", this); 
                
                     
        // org.modeldriven.fuml.library.integerfunctions                                                    
    	pkg  = factory.createPackage("integerfunctions", "org.modeldriven.fuml.library.integerfunctions", "_wLHE5oA_Ed2xsvzRuEHZYg", pkg, this);
    	mapping.mapPackage(pkg, "org.modeldriven.fuml.library", this); 
                
                     
        // org.modeldriven.fuml.library.booleanfunctions                                                    
    	pkg  = factory.createPackage("booleanfunctions", "org.modeldriven.fuml.library.booleanfunctions", "_cLHEcoA_Ed2xsczRuEHZYg", pkg, this);
    	mapping.mapPackage(pkg, "org.modeldriven.fuml.library", this); 
                
                     
        // org.modeldriven.fuml.library.stringfunctions                                                    
    	pkg  = factory.createPackage("stringfunctions", "org.modeldriven.fuml.library.stringfunctions", "_gLgEcoA_Ed2gsczRgEHZYg", pkg, this);
    	mapping.mapPackage(pkg, "org.modeldriven.fuml.library", this); 
                
                     
        // org.modeldriven.fuml.library.unlimitednaturalfunctions                                                    
    	pkg  = factory.createPackage("unlimitednaturalfunctions", "org.modeldriven.fuml.library.unlimitednaturalfunctions", "_gfgEcoA_Ed2gsdfzRgEHZYg", pkg, this);
    	mapping.mapPackage(pkg, "org.modeldriven.fuml.library", this); 
                
                     
        // org.modeldriven.fuml.library.listfunctions                                                    
    	pkg  = factory.createPackage("listfunctions", "org.modeldriven.fuml.library.listfunctions", "_hLgEhoA_hd2ghczRghHZYg", pkg, this);
    	mapping.mapPackage(pkg, "org.modeldriven.fuml.library", this); 
                
                     
        // org.modeldriven.fuml.library.io                                                    
    	pkg  = factory.createPackage("io", "org.modeldriven.fuml.library.io", "_wLHFU4A_Ed2xsvzRuEHZYg", pkg, this);
    	mapping.mapPackage(pkg, "org.modeldriven.fuml.library", this); 
                
                     
        // org.modeldriven.fuml.library.channel                                                    
    	pkg  = factory.createPackage("channel", "org.modeldriven.fuml.library.channel", "_wLHFkoA_Ed2xsvzRuEHZYg", pkg, this);
    	mapping.mapPackage(pkg, "org.modeldriven.fuml.library", this); 
                
                     
        // org.modeldriven.fuml.library.libraryclass                                                    
    	pkg  = factory.createPackage("libraryclass", "org.modeldriven.fuml.library.libraryclass", "_wLHGUYA_Ed2xsvzRuEHZYg", pkg, this);
    	mapping.mapPackage(pkg, "org.modeldriven.fuml.library", this); 
                
                     
        // org.modeldriven.fuml.library.pipe                                                    
    	pkg  = factory.createPackage("pipe", "org.modeldriven.fuml.library.pipe", "_wLHGloA_Ed2xsvzRuEHZYg", pkg, this);
    	mapping.mapPackage(pkg, "org.modeldriven.fuml.library", this); 
            
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
                                                      
        packageId = this.artifact.getUrn() + "#" + "_wLHE2IA_Ed2xsvzRuEHZYg";   
                      
        // org.modeldriven.fuml.library.PrimitiveTypes 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("org.modeldriven.fuml.library").getDelegate();       
    	clss  = factory.createClass("PrimitiveTypes", "_wLHE2YA_Ed2xsvzRuEHZYg", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "org.modeldriven.fuml.library", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "_wLHE5oA_Ed2xsvzRuEHZYg";   
                      
        // org.modeldriven.fuml.library.integerfunctions.IntegerFunctionBehaviorExecution 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("org.modeldriven.fuml.library.integerfunctions").getDelegate();       
    	clss  = factory.createClass("IntegerFunctionBehaviorExecution", "_wLHE54A_Ed2xsvzRuEHZYg", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "org.modeldriven.fuml.library.integerfunctions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "_wLHE5oA_Ed2xsvzRuEHZYg";   
                      
        // org.modeldriven.fuml.library.integerfunctions.IntegerPlusFunctionBehaviorExecution 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("org.modeldriven.fuml.library.integerfunctions").getDelegate();       
    	clss  = factory.createClass("IntegerPlusFunctionBehaviorExecution", "_wLHE94A_Ed2xsvzRuEHZYg", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "org.modeldriven.fuml.library.integerfunctions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "_wLHE5oA_Ed2xsvzRuEHZYg";   
                      
        // org.modeldriven.fuml.library.integerfunctions.IntegerMinusFunctionBehaviorExecution 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("org.modeldriven.fuml.library.integerfunctions").getDelegate();       
    	clss  = factory.createClass("IntegerMinusFunctionBehaviorExecution", "_wLHFAoA_Ed2xsvzRuEHZYg", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "org.modeldriven.fuml.library.integerfunctions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "_wLHE5oA_Ed2xsvzRuEHZYg";   
                      
        // org.modeldriven.fuml.library.integerfunctions.IntegerTimesFunctionBehaviorExecution 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("org.modeldriven.fuml.library.integerfunctions").getDelegate();       
    	clss  = factory.createClass("IntegerTimesFunctionBehaviorExecution", "_wLHFDYA_Ed2xsvzRuEHZYg", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "org.modeldriven.fuml.library.integerfunctions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "_wLHE5oA_Ed2xsvzRuEHZYg";   
                      
        // org.modeldriven.fuml.library.integerfunctions.IntegerDivideFunctionBehaviorExecution 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("org.modeldriven.fuml.library.integerfunctions").getDelegate();       
    	clss  = factory.createClass("IntegerDivideFunctionBehaviorExecution", "_wLHFGIA_Ed2xsvzRuEHZYg", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "org.modeldriven.fuml.library.integerfunctions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "_wLHE5oA_Ed2xsvzRuEHZYg";   
                      
        // org.modeldriven.fuml.library.integerfunctions.IntegerNegateFunctionBehaviorExecution 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("org.modeldriven.fuml.library.integerfunctions").getDelegate();       
    	clss  = factory.createClass("IntegerNegateFunctionBehaviorExecution", "_wLHFI4A_Ed2xsvzRuEHZYg", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "org.modeldriven.fuml.library.integerfunctions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "_wLHE5oA_Ed2xsvzRuEHZYg";   
                      
        // org.modeldriven.fuml.library.integerfunctions.IntegerModFunctionBehaviorExecution 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("org.modeldriven.fuml.library.integerfunctions").getDelegate();       
    	clss  = factory.createClass("IntegerModFunctionBehaviorExecution", "x_wLHFI4A_Ed2xxvzRuxHZYg", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "org.modeldriven.fuml.library.integerfunctions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "_wLHE5oA_Ed2xsvzRuEHZYg";   
                      
        // org.modeldriven.fuml.library.integerfunctions.IntegerMaxFunctionBehaviorExecution 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("org.modeldriven.fuml.library.integerfunctions").getDelegate();       
    	clss  = factory.createClass("IntegerMaxFunctionBehaviorExecution", "y_wLHFI4A_Ed2xxvzRuxHZYg", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "org.modeldriven.fuml.library.integerfunctions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "_wLHE5oA_Ed2xsvzRuEHZYg";   
                      
        // org.modeldriven.fuml.library.integerfunctions.IntegerMinFunctionBehaviorExecution 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("org.modeldriven.fuml.library.integerfunctions").getDelegate();       
    	clss  = factory.createClass("IntegerMinFunctionBehaviorExecution", "z_wLHFI4A_Ed2xxvzRuxHZYg", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "org.modeldriven.fuml.library.integerfunctions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "_wLHE5oA_Ed2xsvzRuEHZYg";   
                      
        // org.modeldriven.fuml.library.integerfunctions.IntegerAbsFunctionBehaviorExecution 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("org.modeldriven.fuml.library.integerfunctions").getDelegate();       
    	clss  = factory.createClass("IntegerAbsFunctionBehaviorExecution", "a_wLHFI4A_Ed2xxvzRuxHZYg", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "org.modeldriven.fuml.library.integerfunctions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "_wLHE5oA_Ed2xsvzRuEHZYg";   
                      
        // org.modeldriven.fuml.library.integerfunctions.IntegerLessThanFunctionBehaviorExecution 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("org.modeldriven.fuml.library.integerfunctions").getDelegate();       
    	clss  = factory.createClass("IntegerLessThanFunctionBehaviorExecution", "b_wLHFI4A_Ed2xxvzRuxHZYg", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "org.modeldriven.fuml.library.integerfunctions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "_wLHE5oA_Ed2xsvzRuEHZYg";   
                      
        // org.modeldriven.fuml.library.integerfunctions.IntegerLessThanEqualFunctionBehaviorExecution 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("org.modeldriven.fuml.library.integerfunctions").getDelegate();       
    	clss  = factory.createClass("IntegerLessThanEqualFunctionBehaviorExecution", "c_wLHFI4A_Ed2xxvzRuxHZYg", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "org.modeldriven.fuml.library.integerfunctions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "_wLHE5oA_Ed2xsvzRuEHZYg";   
                      
        // org.modeldriven.fuml.library.integerfunctions.IntegerGreaterThanFunctionBehaviorExecution 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("org.modeldriven.fuml.library.integerfunctions").getDelegate();       
    	clss  = factory.createClass("IntegerGreaterThanFunctionBehaviorExecution", "d_wLHFI4A_Ed2xxvzRuxHZYg", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "org.modeldriven.fuml.library.integerfunctions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "_wLHE5oA_Ed2xsvzRuEHZYg";   
                      
        // org.modeldriven.fuml.library.integerfunctions.IntegerGreaterThanEqualFunctionBehaviorExecution 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("org.modeldriven.fuml.library.integerfunctions").getDelegate();       
    	clss  = factory.createClass("IntegerGreaterThanEqualFunctionBehaviorExecution", "e_wLHFI4A_Ed2xxvzRuxHZYg", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "org.modeldriven.fuml.library.integerfunctions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "_wLHE5oA_Ed2xsvzRuEHZYg";   
                      
        // org.modeldriven.fuml.library.integerfunctions.IntegerToIntegerFunctionBehaviorExecution 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("org.modeldriven.fuml.library.integerfunctions").getDelegate();       
    	clss  = factory.createClass("IntegerToIntegerFunctionBehaviorExecution", "s_wLHFI4A_Ed2xxvzRuxHZYg", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "org.modeldriven.fuml.library.integerfunctions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "_wLHE5oA_Ed2xsvzRuEHZYg";   
                      
        // org.modeldriven.fuml.library.integerfunctions.IntegerToStringFunctionBehaviorExecution 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("org.modeldriven.fuml.library.integerfunctions").getDelegate();       
    	clss  = factory.createClass("IntegerToStringFunctionBehaviorExecution", "t_wLHFI4A_Ed2xxvzRuxHZYg", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "org.modeldriven.fuml.library.integerfunctions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "_wLHE5oA_Ed2xsvzRuEHZYg";   
                      
        // org.modeldriven.fuml.library.integerfunctions.IntegerToUnlimitedNaturalFunctionBehaviorExecution 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("org.modeldriven.fuml.library.integerfunctions").getDelegate();       
    	clss  = factory.createClass("IntegerToUnlimitedNaturalFunctionBehaviorExecution", "u_wLHFI4A_Ed2xxvzRuxHZYg", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "org.modeldriven.fuml.library.integerfunctions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "_cLHEcoA_Ed2xsczRuEHZYg";   
                      
        // org.modeldriven.fuml.library.booleanfunctions.BooleanFunctionBehaviorExecution 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("org.modeldriven.fuml.library.booleanfunctions").getDelegate();       
    	clss  = factory.createClass("BooleanFunctionBehaviorExecution", "_cLHEc4A_Ec2xsvcRuEcZYg", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "org.modeldriven.fuml.library.booleanfunctions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "_cLHEcoA_Ed2xsczRuEHZYg";   
                      
        // org.modeldriven.fuml.library.booleanfunctions.BooleanAndFunctionBehaviorExecution 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("org.modeldriven.fuml.library.booleanfunctions").getDelegate();       
    	clss  = factory.createClass("BooleanAndFunctionBehaviorExecution", "a_cLHEc4A_Ec2xsvcRuEcZYg", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "org.modeldriven.fuml.library.booleanfunctions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "_cLHEcoA_Ed2xsczRuEHZYg";   
                      
        // org.modeldriven.fuml.library.booleanfunctions.BooleanImpliesFunctionBehaviorExecution 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("org.modeldriven.fuml.library.booleanfunctions").getDelegate();       
    	clss  = factory.createClass("BooleanImpliesFunctionBehaviorExecution", "b_cLHEc4A_Ec2xsvcRuEcZYg", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "org.modeldriven.fuml.library.booleanfunctions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "_cLHEcoA_Ed2xsczRuEHZYg";   
                      
        // org.modeldriven.fuml.library.booleanfunctions.BooleanNotFunctionBehaviorExecution 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("org.modeldriven.fuml.library.booleanfunctions").getDelegate();       
    	clss  = factory.createClass("BooleanNotFunctionBehaviorExecution", "c_cLHEc4A_Ec2xsvcRuEcZYg", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "org.modeldriven.fuml.library.booleanfunctions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "_cLHEcoA_Ed2xsczRuEHZYg";   
                      
        // org.modeldriven.fuml.library.booleanfunctions.BooleanOrFunctionBehaviorExecution 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("org.modeldriven.fuml.library.booleanfunctions").getDelegate();       
    	clss  = factory.createClass("BooleanOrFunctionBehaviorExecution", "d_cLHEc4A_Ec2xsvcRuEcZYg", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "org.modeldriven.fuml.library.booleanfunctions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "_cLHEcoA_Ed2xsczRuEHZYg";   
                      
        // org.modeldriven.fuml.library.booleanfunctions.BooleanToBooleanFunctionBehaviorExecution 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("org.modeldriven.fuml.library.booleanfunctions").getDelegate();       
    	clss  = factory.createClass("BooleanToBooleanFunctionBehaviorExecution", "e_cLHEc4A_Ec2xsvcRuEcZYg", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "org.modeldriven.fuml.library.booleanfunctions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "_cLHEcoA_Ed2xsczRuEHZYg";   
                      
        // org.modeldriven.fuml.library.booleanfunctions.BooleanToStringFunctionBehaviorExecution 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("org.modeldriven.fuml.library.booleanfunctions").getDelegate();       
    	clss  = factory.createClass("BooleanToStringFunctionBehaviorExecution", "f_cLHEc4A_Ec2xsvcRuEcZYg", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "org.modeldriven.fuml.library.booleanfunctions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "_cLHEcoA_Ed2xsczRuEHZYg";   
                      
        // org.modeldriven.fuml.library.booleanfunctions.BooleanXorFunctionBehaviorExecution 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("org.modeldriven.fuml.library.booleanfunctions").getDelegate();       
    	clss  = factory.createClass("BooleanXorFunctionBehaviorExecution", "g_cLHEc4A_Ec2xsvcRuEcZYg", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "org.modeldriven.fuml.library.booleanfunctions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "_gLgEcoA_Ed2gsczRgEHZYg";   
                      
        // org.modeldriven.fuml.library.stringfunctions.StringSizeFunctionBehaviorExecution 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("org.modeldriven.fuml.library.stringfunctions").getDelegate();       
    	clss  = factory.createClass("StringSizeFunctionBehaviorExecution", "_gLHEg4A_gc2xgvcRgEcZYg", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "org.modeldriven.fuml.library.stringfunctions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "_gLgEcoA_Ed2gsczRgEHZYg";   
                      
        // org.modeldriven.fuml.library.stringfunctions.StringConcatFunctionBehaviorExecution 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("org.modeldriven.fuml.library.stringfunctions").getDelegate();       
    	clss  = factory.createClass("StringConcatFunctionBehaviorExecution", "_hLHEg4A_gc2xgvcRgEcZYg", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "org.modeldriven.fuml.library.stringfunctions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "_gLgEcoA_Ed2gsczRgEHZYg";   
                      
        // org.modeldriven.fuml.library.stringfunctions.StringSubstringFunctionBehaviorExecution 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("org.modeldriven.fuml.library.stringfunctions").getDelegate();       
    	clss  = factory.createClass("StringSubstringFunctionBehaviorExecution", "_iLHEg4A_gc2xgvcRgEcZYg", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "org.modeldriven.fuml.library.stringfunctions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "_gfgEcoA_Ed2gsdfzRgEHZYg";   
                      
        // org.modeldriven.fuml.library.unlimitednaturalfunctions.UnlimitedNaturalLessThanFunctionBehaviorExecution 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("org.modeldriven.fuml.library.unlimitednaturalfunctions").getDelegate();       
    	clss  = factory.createClass("UnlimitedNaturalLessThanFunctionBehaviorExecution", "_gfHEg4A_gd2xgdfcRgEcZYg", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "org.modeldriven.fuml.library.unlimitednaturalfunctions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "_gfgEcoA_Ed2gsdfzRgEHZYg";   
                      
        // org.modeldriven.fuml.library.unlimitednaturalfunctions.UnlimitedNaturalLessThanEqualFunctionBehaviorExecution 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("org.modeldriven.fuml.library.unlimitednaturalfunctions").getDelegate();       
    	clss  = factory.createClass("UnlimitedNaturalLessThanEqualFunctionBehaviorExecution", "_ggfHEg4A_gd2xgdfcRgEcZYg", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "org.modeldriven.fuml.library.unlimitednaturalfunctions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "_gfgEcoA_Ed2gsdfzRgEHZYg";   
                      
        // org.modeldriven.fuml.library.unlimitednaturalfunctions.UnlimitedNaturalMaxFunctionBehaviorExecution 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("org.modeldriven.fuml.library.unlimitednaturalfunctions").getDelegate();       
    	clss  = factory.createClass("UnlimitedNaturalMaxFunctionBehaviorExecution", "_hfHEg4A_gd2xdfvcRgEcZYg", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "org.modeldriven.fuml.library.unlimitednaturalfunctions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "_gfgEcoA_Ed2gsdfzRgEHZYg";   
                      
        // org.modeldriven.fuml.library.unlimitednaturalfunctions.UnlimitedNaturalMinFunctionBehaviorExecution 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("org.modeldriven.fuml.library.unlimitednaturalfunctions").getDelegate();       
    	clss  = factory.createClass("UnlimitedNaturalMinFunctionBehaviorExecution", "_ifHEg4A_gd2xdfvcRgEcZYg", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "org.modeldriven.fuml.library.unlimitednaturalfunctions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "_gfgEcoA_Ed2gsdfzRgEHZYg";   
                      
        // org.modeldriven.fuml.library.unlimitednaturalfunctions.UnlimitedNaturalGreaterThanFunctionBehaviorExecution 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("org.modeldriven.fuml.library.unlimitednaturalfunctions").getDelegate();       
    	clss  = factory.createClass("UnlimitedNaturalGreaterThanFunctionBehaviorExecution", "_jfHSg4A_gd2xdSvcRgEcZYg", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "org.modeldriven.fuml.library.unlimitednaturalfunctions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "_gfgEcoA_Ed2gsdfzRgEHZYg";   
                      
        // org.modeldriven.fuml.library.unlimitednaturalfunctions.UnlimitedNaturalToUnlimitedNaturalFunctionBehaviorExecution 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("org.modeldriven.fuml.library.unlimitednaturalfunctions").getDelegate();       
    	clss  = factory.createClass("UnlimitedNaturalToUnlimitedNaturalFunctionBehaviorExecution", "_kfHSg4A_gd2xdSvcRgEcZYg", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "org.modeldriven.fuml.library.unlimitednaturalfunctions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "_gfgEcoA_Ed2gsdfzRgEHZYg";   
                      
        // org.modeldriven.fuml.library.unlimitednaturalfunctions.UnlimitedNaturalToStringFunctionBehaviorExecution 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("org.modeldriven.fuml.library.unlimitednaturalfunctions").getDelegate();       
    	clss  = factory.createClass("UnlimitedNaturalToStringFunctionBehaviorExecution", "_lfHSg4A_gd2xdSvcRgEcZYg", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "org.modeldriven.fuml.library.unlimitednaturalfunctions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "_gfgEcoA_Ed2gsdfzRgEHZYg";   
                      
        // org.modeldriven.fuml.library.unlimitednaturalfunctions.UnlimitedNaturalGreaterThanEqualFunctionBehaviorExecution 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("org.modeldriven.fuml.library.unlimitednaturalfunctions").getDelegate();       
    	clss  = factory.createClass("UnlimitedNaturalGreaterThanEqualFunctionBehaviorExecution", "_mfHSg4A_gd2xdSvcRgEcZYg", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "org.modeldriven.fuml.library.unlimitednaturalfunctions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "_gfgEcoA_Ed2gsdfzRgEHZYg";   
                      
        // org.modeldriven.fuml.library.unlimitednaturalfunctions.UnlimitedNaturalToIntegerFunctionBehaviorExecution 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("org.modeldriven.fuml.library.unlimitednaturalfunctions").getDelegate();       
    	clss  = factory.createClass("UnlimitedNaturalToIntegerFunctionBehaviorExecution", "_nfHSg4A_gd2xdSvcRgEcZYg", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "org.modeldriven.fuml.library.unlimitednaturalfunctions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "_hLgEhoA_hd2ghczRghHZYg";   
                      
        // org.modeldriven.fuml.library.listfunctions.ListSizeFunctionBehaviorExecution 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("org.modeldriven.fuml.library.listfunctions").getDelegate();       
    	clss  = factory.createClass("ListSizeFunctionBehaviorExecution", "_fLgEhoA_hd2ghczRghHZYg", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "org.modeldriven.fuml.library.listfunctions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "_hLgEhoA_hd2ghczRghHZYg";   
                      
        // org.modeldriven.fuml.library.listfunctions.ListGetFunctionBehaviorExecution 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("org.modeldriven.fuml.library.listfunctions").getDelegate();       
    	clss  = factory.createClass("ListGetFunctionBehaviorExecution", "_gLgEhoA_hd2ghczRghHZYg", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "org.modeldriven.fuml.library.listfunctions", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "_wLHE2IA_Ed2xsvzRuEHZYg";   
                      
        // org.modeldriven.fuml.library.PrimitiveFunctions 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("org.modeldriven.fuml.library").getDelegate();       
    	clss  = factory.createClass("PrimitiveFunctions", "_wLHFLoA_Ed2xsvzRuEHZYg", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "org.modeldriven.fuml.library", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "_wLHFU4A_Ed2xsvzRuEHZYg";   
                      
        // org.modeldriven.fuml.library.io.SystemWriteLineBehaviorExecution 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("org.modeldriven.fuml.library.io").getDelegate();       
    	clss  = factory.createClass("SystemWriteLineBehaviorExecution", "_wLHFVIA_Ed2xsvzRuEHZYg", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "org.modeldriven.fuml.library.io", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "_wLHE2IA_Ed2xsvzRuEHZYg";   
                      
        // org.modeldriven.fuml.library.SystemIO 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("org.modeldriven.fuml.library").getDelegate();       
    	clss  = factory.createClass("SystemIO", "_wLHFYYA_Ed2xsvzRuEHZYg", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "org.modeldriven.fuml.library", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "_wLHE2IA_Ed2xsvzRuEHZYg";   
                      
        // org.modeldriven.fuml.library.PrimitiveBehaviors 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("org.modeldriven.fuml.library").getDelegate();       
    	clss  = factory.createClass("PrimitiveBehaviors", "_wLHFZ4A_Ed2xsvzRuEHZYg", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "org.modeldriven.fuml.library", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "_wLHFkoA_Ed2xsvzRuEHZYg";   
                      
        // org.modeldriven.fuml.library.channel.ChannelObject 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("org.modeldriven.fuml.library.channel").getDelegate();       
    	clss  = factory.createClass("ChannelObject", "_wLHFk4A_Ed2xsvzRuEHZYg", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "org.modeldriven.fuml.library.channel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "_wLHFkoA_Ed2xsvzRuEHZYg";   
                      
        // org.modeldriven.fuml.library.channel.OutputChannelObject 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("org.modeldriven.fuml.library.channel").getDelegate();       
    	clss  = factory.createClass("OutputChannelObject", "_wLHFn4A_Ed2xsvzRuEHZYg", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "org.modeldriven.fuml.library.channel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "_wLHFkoA_Ed2xsvzRuEHZYg";   
                      
        // org.modeldriven.fuml.library.channel.TextOutputChannelObject 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("org.modeldriven.fuml.library.channel").getDelegate();       
    	clss  = factory.createClass("TextOutputChannelObject", "_wLHFqYA_Ed2xsvzRuEHZYg", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "org.modeldriven.fuml.library.channel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "_wLHFkoA_Ed2xsvzRuEHZYg";   
                      
        // org.modeldriven.fuml.library.channel.StandardOutputChannelObject 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("org.modeldriven.fuml.library.channel").getDelegate();       
    	clss  = factory.createClass("StandardOutputChannelObject", "_wLHFvoA_Ed2xsvzRuEHZYg", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "org.modeldriven.fuml.library.channel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "_wLHFkoA_Ed2xsvzRuEHZYg";   
                      
        // org.modeldriven.fuml.library.channel.InputChannelObject 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("org.modeldriven.fuml.library.channel").getDelegate();       
    	clss  = factory.createClass("InputChannelObject", "_wLHF3IA_Ed2xsvzRuEHZYg", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "org.modeldriven.fuml.library.channel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "_wLHFkoA_Ed2xsvzRuEHZYg";   
                      
        // org.modeldriven.fuml.library.channel.StandardOutputWriteExecution 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("org.modeldriven.fuml.library.channel").getDelegate();       
    	clss  = factory.createClass("StandardOutputWriteExecution", "_wLHF6IA_Ed2xsvzRuEHZYg", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "org.modeldriven.fuml.library.channel", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "_wLHE2IA_Ed2xsvzRuEHZYg";   
                      
        // org.modeldriven.fuml.library.Classes 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("org.modeldriven.fuml.library").getDelegate();       
    	clss  = factory.createClass("Classes", "_wLHGDoA_Ed2xsvzRuEHZYg", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "org.modeldriven.fuml.library", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "_wLHE2IA_Ed2xsvzRuEHZYg";   
                      
        // org.modeldriven.fuml.library.StandardIOClasses 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("org.modeldriven.fuml.library").getDelegate();       
    	clss  = factory.createClass("StandardIOClasses", "_wLHGN4A_Ed2xsvzRuEHZYg", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "org.modeldriven.fuml.library", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "_wLHGUYA_Ed2xsvzRuEHZYg";   
                      
        // org.modeldriven.fuml.library.libraryclass.OperationExecution 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("org.modeldriven.fuml.library.libraryclass").getDelegate();       
    	clss  = factory.createClass("OperationExecution", "_wLHGUoA_Ed2xsvzRuEHZYg", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "org.modeldriven.fuml.library.libraryclass", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "_wLHGUYA_Ed2xsvzRuEHZYg";   
                      
        // org.modeldriven.fuml.library.libraryclass.ImplementationObject 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("org.modeldriven.fuml.library.libraryclass").getDelegate();       
    	clss  = factory.createClass("ImplementationObject", "_wLHGc4A_Ed2xsvzRuEHZYg", pkg);
    	clss.isAbstract = true;
    	((Classifier)clss).isAbstract = true;
    	mapping.mapClass(clss, "org.modeldriven.fuml.library.libraryclass", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "_wLHGloA_Ed2xsvzRuEHZYg";   
                      
        // org.modeldriven.fuml.library.pipe.PipeInputChannelObject 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("org.modeldriven.fuml.library.pipe").getDelegate();       
    	clss  = factory.createClass("PipeInputChannelObject", "_wLHGm4A_Ed2xsvzRuEHZYg", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "org.modeldriven.fuml.library.pipe", this); 
                                                      
        packageId = this.artifact.getUrn() + "#" + "_wLHGloA_Ed2xsvzRuEHZYg";   
                      
        // org.modeldriven.fuml.library.pipe.PipeOutputChannelObject 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
        //pkg = model.getPackageByQualifiedName("org.modeldriven.fuml.library.pipe").getDelegate();       
    	clss  = factory.createClass("PipeOutputChannelObject", "_wLHGu4A_Ed2xsvzRuEHZYg", pkg);
    	clss.isAbstract = false;
    	((Classifier)clss).isAbstract = false;
    	mapping.mapClass(clss, "org.modeldriven.fuml.library.pipe", this); 
    
    }   

    private void constructEnumerations()
    {
        Enumeration enumeration = null;
        EnumerationLiteral literal = null;
    
    }   

    private void constructProperties()
    {
        Class_ clss = null;
        Property prop = null;
        
    
        // PrimitiveTypes
    	clss  = (Class_)model.getElementById("_wLHE2YA_Ed2xsvzRuEHZYg").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "Boolean", 
    	    "_wLHE2oA_Ed2xsvzRuEHZYg",
    	    "Infrastructure.uml#Core-PrimitiveTypes-Boolean",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "String", 
    	    "_wLHE24A_Ed2xsvzRuEHZYg",
    	    "Infrastructure.uml#Core-PrimitiveTypes-String",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "Integer", 
    	    "_wLHE3IA_Ed2xsvzRuEHZYg",
    	    "Infrastructure.uml#Core-PrimitiveTypes-Integer",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "UnlimitedNatural", 
    	    "_wLHE3YA_Ed2xsvzRuEHZYg",
    	    "Infrastructure.uml#Core-PrimitiveTypes-UnlimitedNatural",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // IntegerFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("_wLHE54A_Ed2xsvzRuEHZYg").getDelegate();
    	
        // IntegerPlusFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("_wLHE94A_Ed2xsvzRuEHZYg").getDelegate();
    	
        // IntegerMinusFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("_wLHFAoA_Ed2xsvzRuEHZYg").getDelegate();
    	
        // IntegerTimesFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("_wLHFDYA_Ed2xsvzRuEHZYg").getDelegate();
    	
        // IntegerDivideFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("_wLHFGIA_Ed2xsvzRuEHZYg").getDelegate();
    	
        // IntegerNegateFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("_wLHFI4A_Ed2xsvzRuEHZYg").getDelegate();
    	
        // IntegerModFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("x_wLHFI4A_Ed2xxvzRuxHZYg").getDelegate();
    	
        // IntegerMaxFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("y_wLHFI4A_Ed2xxvzRuxHZYg").getDelegate();
    	
        // IntegerMinFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("z_wLHFI4A_Ed2xxvzRuxHZYg").getDelegate();
    	
        // IntegerAbsFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("a_wLHFI4A_Ed2xxvzRuxHZYg").getDelegate();
    	
        // IntegerLessThanFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("b_wLHFI4A_Ed2xxvzRuxHZYg").getDelegate();
    	
        // IntegerLessThanEqualFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("c_wLHFI4A_Ed2xxvzRuxHZYg").getDelegate();
    	
        // IntegerGreaterThanFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("d_wLHFI4A_Ed2xxvzRuxHZYg").getDelegate();
    	
        // IntegerGreaterThanEqualFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("e_wLHFI4A_Ed2xxvzRuxHZYg").getDelegate();
    	
        // IntegerToIntegerFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("s_wLHFI4A_Ed2xxvzRuxHZYg").getDelegate();
    	
        // IntegerToStringFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("t_wLHFI4A_Ed2xxvzRuxHZYg").getDelegate();
    	
        // IntegerToUnlimitedNaturalFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("u_wLHFI4A_Ed2xxvzRuxHZYg").getDelegate();
    	
        // BooleanFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("_cLHEc4A_Ec2xsvcRuEcZYg").getDelegate();
    	
        // BooleanAndFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("a_cLHEc4A_Ec2xsvcRuEcZYg").getDelegate();
    	
        // BooleanImpliesFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("b_cLHEc4A_Ec2xsvcRuEcZYg").getDelegate();
    	
        // BooleanNotFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("c_cLHEc4A_Ec2xsvcRuEcZYg").getDelegate();
    	
        // BooleanOrFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("d_cLHEc4A_Ec2xsvcRuEcZYg").getDelegate();
    	
        // BooleanToBooleanFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("e_cLHEc4A_Ec2xsvcRuEcZYg").getDelegate();
    	
        // BooleanToStringFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("f_cLHEc4A_Ec2xsvcRuEcZYg").getDelegate();
    	
        // BooleanXorFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("g_cLHEc4A_Ec2xsvcRuEcZYg").getDelegate();
    	
        // StringSizeFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("_gLHEg4A_gc2xgvcRgEcZYg").getDelegate();
    	
        // StringConcatFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("_hLHEg4A_gc2xgvcRgEcZYg").getDelegate();
    	
        // StringSubstringFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("_iLHEg4A_gc2xgvcRgEcZYg").getDelegate();
    	
        // UnlimitedNaturalLessThanFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("_gfHEg4A_gd2xgdfcRgEcZYg").getDelegate();
    	
        // UnlimitedNaturalLessThanEqualFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("_ggfHEg4A_gd2xgdfcRgEcZYg").getDelegate();
    	
        // UnlimitedNaturalMaxFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("_hfHEg4A_gd2xdfvcRgEcZYg").getDelegate();
    	
        // UnlimitedNaturalMinFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("_ifHEg4A_gd2xdfvcRgEcZYg").getDelegate();
    	
        // UnlimitedNaturalGreaterThanFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("_jfHSg4A_gd2xdSvcRgEcZYg").getDelegate();
    	
        // UnlimitedNaturalToUnlimitedNaturalFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("_kfHSg4A_gd2xdSvcRgEcZYg").getDelegate();
    	
        // UnlimitedNaturalToStringFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("_lfHSg4A_gd2xdSvcRgEcZYg").getDelegate();
    	
        // UnlimitedNaturalGreaterThanEqualFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("_mfHSg4A_gd2xdSvcRgEcZYg").getDelegate();
    	
        // UnlimitedNaturalToIntegerFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("_nfHSg4A_gd2xdSvcRgEcZYg").getDelegate();
    	
        // ListSizeFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("_fLgEhoA_hd2ghczRghHZYg").getDelegate();
    	
        // ListGetFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("_gLgEhoA_hd2ghczRghHZYg").getDelegate();
    	
        // PrimitiveFunctions
    	clss  = (Class_)model.getElementById("_wLHFLoA_Ed2xsvzRuEHZYg").getDelegate();
    	
        // SystemWriteLineBehaviorExecution
    	clss  = (Class_)model.getElementById("_wLHFVIA_Ed2xsvzRuEHZYg").getDelegate();
    	
        // SystemIO
    	clss  = (Class_)model.getElementById("_wLHFYYA_Ed2xsvzRuEHZYg").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "WriteLine", 
    	    "_wLHFY4A_Ed2xsvzRuEHZYg",
    	    "_wLG4RYA_Ed2xsvzRuEHZYg",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // PrimitiveBehaviors
    	clss  = (Class_)model.getElementById("_wLHFZ4A_Ed2xsvzRuEHZYg").getDelegate();
    	
        // ChannelObject
    	clss  = (Class_)model.getElementById("_wLHFk4A_Ed2xsvzRuEHZYg").getDelegate();
    	
        // OutputChannelObject
    	clss  = (Class_)model.getElementById("_wLHFn4A_Ed2xsvzRuEHZYg").getDelegate();
    	
        // TextOutputChannelObject
    	clss  = (Class_)model.getElementById("_wLHFqYA_Ed2xsvzRuEHZYg").getDelegate();
    	
        // StandardOutputChannelObject
    	clss  = (Class_)model.getElementById("_wLHFvoA_Ed2xsvzRuEHZYg").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "opened", 
    	    "_wLHFwIA_Ed2xsvzRuEHZYg",
    	    "pathmap://UML_LIBRARIES/UMLPrimitiveTypes.library.uml#Boolean",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // InputChannelObject
    	clss  = (Class_)model.getElementById("_wLHF3IA_Ed2xsvzRuEHZYg").getDelegate();
    	
        // StandardOutputWriteExecution
    	clss  = (Class_)model.getElementById("_wLHF6IA_Ed2xsvzRuEHZYg").getDelegate();
    	
        // Classes
    	clss  = (Class_)model.getElementById("_wLHGDoA_Ed2xsvzRuEHZYg").getDelegate();
    	
        // StandardIOClasses
    	clss  = (Class_)model.getElementById("_wLHGN4A_Ed2xsvzRuEHZYg").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "Channel", 
    	    "_wLHGOoA_Ed2xsvzRuEHZYg",
    	    "_wLG7K4A_Ed2xsvzRuEHZYg",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "OutputChannel", 
    	    "_wLHGO4A_Ed2xsvzRuEHZYg",
    	    "_wLG7K4A_Ed2xsvzRuEHZYg",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "TextOutputChannel", 
    	    "_wLHGPIA_Ed2xsvzRuEHZYg",
    	    "_wLG7K4A_Ed2xsvzRuEHZYg",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "StandardOutputChannel", 
    	    "_wLHGPYA_Ed2xsvzRuEHZYg",
    	    "_wLG7K4A_Ed2xsvzRuEHZYg",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "InputChannel", 
    	    "_wLHGPoA_Ed2xsvzRuEHZYg",
    	    "_wLG7K4A_Ed2xsvzRuEHZYg",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // OperationExecution
    	clss  = (Class_)model.getElementById("_wLHGUoA_Ed2xsvzRuEHZYg").getDelegate();
    	
        // ImplementationObject
    	clss  = (Class_)model.getElementById("_wLHGc4A_Ed2xsvzRuEHZYg").getDelegate();
    	
        // PipeInputChannelObject
    	clss  = (Class_)model.getElementById("_wLHGm4A_Ed2xsvzRuEHZYg").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "values", 
    	    "_wLHGnoA_Ed2xsvzRuEHZYg",
    	    "_wK-Bm4A_Ed2xsvzRuEHZYg",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "*");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "opened", 
    	    "_wLHGoYA_Ed2xsvzRuEHZYg",
    	    "pathmap://UML_LIBRARIES/UMLPrimitiveTypes.library.uml#Boolean",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "name", 
    	    "_wLHGooA_Ed2xsvzRuEHZYg",
    	    "pathmap://UML_LIBRARIES/UMLPrimitiveTypes.library.uml#String",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
        // PipeOutputChannelObject
    	clss  = (Class_)model.getElementById("_wLHGu4A_Ed2xsvzRuEHZYg").getDelegate();
    	    	
    	prop = factory.createProperty(clss, "otherEnd", 
    	    "_wLHGvYA_Ed2xsvzRuEHZYg",
    	    "_wLHGm4A_Ed2xsvzRuEHZYg",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "opened", 
    	    "_wLHGwIA_Ed2xsvzRuEHZYg",
    	    "pathmap://UML_LIBRARIES/UMLPrimitiveTypes.library.uml#Boolean",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	    	
    	prop = factory.createProperty(clss, "name", 
    	    "_wLHGwYA_Ed2xsvzRuEHZYg",
    	    "pathmap://UML_LIBRARIES/UMLPrimitiveTypes.library.uml#String",
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(clss, prop, this);    
     	
    	
    }
    
    private void constructGeneralizations()
    {
        Class_ clss = null;
        
    
        // PrimitiveTypes
    	clss  = (Class_)model.getElementById("_wLHE2YA_Ed2xsvzRuEHZYg").getDelegate();
    	
        // IntegerFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("_wLHE54A_Ed2xsvzRuEHZYg").getDelegate();
    	
        // IntegerPlusFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("_wLHE94A_Ed2xsvzRuEHZYg").getDelegate();
    	
    	factory.createGeneralization(clss, "_wLHE54A_Ed2xsvzRuEHZYg");
    	
        // IntegerMinusFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("_wLHFAoA_Ed2xsvzRuEHZYg").getDelegate();
    	
    	factory.createGeneralization(clss, "_wLHE54A_Ed2xsvzRuEHZYg");
    	
        // IntegerTimesFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("_wLHFDYA_Ed2xsvzRuEHZYg").getDelegate();
    	
    	factory.createGeneralization(clss, "_wLHE54A_Ed2xsvzRuEHZYg");
    	
        // IntegerDivideFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("_wLHFGIA_Ed2xsvzRuEHZYg").getDelegate();
    	
    	factory.createGeneralization(clss, "_wLHE54A_Ed2xsvzRuEHZYg");
    	
        // IntegerNegateFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("_wLHFI4A_Ed2xsvzRuEHZYg").getDelegate();
    	
    	factory.createGeneralization(clss, "_wLHE54A_Ed2xsvzRuEHZYg");
    	
        // IntegerModFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("x_wLHFI4A_Ed2xxvzRuxHZYg").getDelegate();
    	
    	factory.createGeneralization(clss, "_wLHE54A_Ed2xsvzRuEHZYg");
    	
        // IntegerMaxFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("y_wLHFI4A_Ed2xxvzRuxHZYg").getDelegate();
    	
    	factory.createGeneralization(clss, "_wLHE54A_Ed2xsvzRuEHZYg");
    	
        // IntegerMinFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("z_wLHFI4A_Ed2xxvzRuxHZYg").getDelegate();
    	
    	factory.createGeneralization(clss, "_wLHE54A_Ed2xsvzRuEHZYg");
    	
        // IntegerAbsFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("a_wLHFI4A_Ed2xxvzRuxHZYg").getDelegate();
    	
    	factory.createGeneralization(clss, "_wLHE54A_Ed2xsvzRuEHZYg");
    	
        // IntegerLessThanFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("b_wLHFI4A_Ed2xxvzRuxHZYg").getDelegate();
    	
    	factory.createGeneralization(clss, "_wLHE54A_Ed2xsvzRuEHZYg");
    	
        // IntegerLessThanEqualFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("c_wLHFI4A_Ed2xxvzRuxHZYg").getDelegate();
    	
    	factory.createGeneralization(clss, "_wLHE54A_Ed2xsvzRuEHZYg");
    	
        // IntegerGreaterThanFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("d_wLHFI4A_Ed2xxvzRuxHZYg").getDelegate();
    	
    	factory.createGeneralization(clss, "_wLHE54A_Ed2xsvzRuEHZYg");
    	
        // IntegerGreaterThanEqualFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("e_wLHFI4A_Ed2xxvzRuxHZYg").getDelegate();
    	
    	factory.createGeneralization(clss, "_wLHE54A_Ed2xsvzRuEHZYg");
    	
        // IntegerToIntegerFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("s_wLHFI4A_Ed2xxvzRuxHZYg").getDelegate();
    	
    	factory.createGeneralization(clss, "_wLHE54A_Ed2xsvzRuEHZYg");
    	
        // IntegerToStringFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("t_wLHFI4A_Ed2xxvzRuxHZYg").getDelegate();
    	
    	factory.createGeneralization(clss, "_wLHE54A_Ed2xsvzRuEHZYg");
    	
        // IntegerToUnlimitedNaturalFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("u_wLHFI4A_Ed2xxvzRuxHZYg").getDelegate();
    	
    	factory.createGeneralization(clss, "_wLHE54A_Ed2xsvzRuEHZYg");
    	
        // BooleanFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("_cLHEc4A_Ec2xsvcRuEcZYg").getDelegate();
    	
        // BooleanAndFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("a_cLHEc4A_Ec2xsvcRuEcZYg").getDelegate();
    	
    	factory.createGeneralization(clss, "_cLHEc4A_Ec2xsvcRuEcZYg");
    	
        // BooleanImpliesFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("b_cLHEc4A_Ec2xsvcRuEcZYg").getDelegate();
    	
    	factory.createGeneralization(clss, "_cLHEc4A_Ec2xsvcRuEcZYg");
    	
        // BooleanNotFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("c_cLHEc4A_Ec2xsvcRuEcZYg").getDelegate();
    	
    	factory.createGeneralization(clss, "_cLHEc4A_Ec2xsvcRuEcZYg");
    	
        // BooleanOrFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("d_cLHEc4A_Ec2xsvcRuEcZYg").getDelegate();
    	
    	factory.createGeneralization(clss, "_cLHEc4A_Ec2xsvcRuEcZYg");
    	
        // BooleanToBooleanFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("e_cLHEc4A_Ec2xsvcRuEcZYg").getDelegate();
    	
    	factory.createGeneralization(clss, "_cLHEc4A_Ec2xsvcRuEcZYg");
    	
        // BooleanToStringFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("f_cLHEc4A_Ec2xsvcRuEcZYg").getDelegate();
    	
    	factory.createGeneralization(clss, "_cLHEc4A_Ec2xsvcRuEcZYg");
    	
        // BooleanXorFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("g_cLHEc4A_Ec2xsvcRuEcZYg").getDelegate();
    	
    	factory.createGeneralization(clss, "_cLHEc4A_Ec2xsvcRuEcZYg");
    	
        // StringSizeFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("_gLHEg4A_gc2xgvcRgEcZYg").getDelegate();
    	
        // StringConcatFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("_hLHEg4A_gc2xgvcRgEcZYg").getDelegate();
    	
        // StringSubstringFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("_iLHEg4A_gc2xgvcRgEcZYg").getDelegate();
    	
        // UnlimitedNaturalLessThanFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("_gfHEg4A_gd2xgdfcRgEcZYg").getDelegate();
    	
        // UnlimitedNaturalLessThanEqualFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("_ggfHEg4A_gd2xgdfcRgEcZYg").getDelegate();
    	
        // UnlimitedNaturalMaxFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("_hfHEg4A_gd2xdfvcRgEcZYg").getDelegate();
    	
        // UnlimitedNaturalMinFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("_ifHEg4A_gd2xdfvcRgEcZYg").getDelegate();
    	
        // UnlimitedNaturalGreaterThanFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("_jfHSg4A_gd2xdSvcRgEcZYg").getDelegate();
    	
        // UnlimitedNaturalToUnlimitedNaturalFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("_kfHSg4A_gd2xdSvcRgEcZYg").getDelegate();
    	
        // UnlimitedNaturalToStringFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("_lfHSg4A_gd2xdSvcRgEcZYg").getDelegate();
    	
        // UnlimitedNaturalGreaterThanEqualFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("_mfHSg4A_gd2xdSvcRgEcZYg").getDelegate();
    	
        // UnlimitedNaturalToIntegerFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("_nfHSg4A_gd2xdSvcRgEcZYg").getDelegate();
    	
        // ListSizeFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("_fLgEhoA_hd2ghczRghHZYg").getDelegate();
    	
        // ListGetFunctionBehaviorExecution
    	clss  = (Class_)model.getElementById("_gLgEhoA_hd2ghczRghHZYg").getDelegate();
    	
        // PrimitiveFunctions
    	clss  = (Class_)model.getElementById("_wLHFLoA_Ed2xsvzRuEHZYg").getDelegate();
    	
    	factory.createGeneralization(clss, "_wLHFZ4A_Ed2xsvzRuEHZYg");
    	
        // SystemWriteLineBehaviorExecution
    	clss  = (Class_)model.getElementById("_wLHFVIA_Ed2xsvzRuEHZYg").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-CommonBehaviors-BasicBehaviors-OpaqueBehaviorExecution");
    	
        // SystemIO
    	clss  = (Class_)model.getElementById("_wLHFYYA_Ed2xsvzRuEHZYg").getDelegate();
    	
    	factory.createGeneralization(clss, "_wLHFZ4A_Ed2xsvzRuEHZYg");
    	
        // PrimitiveBehaviors
    	clss  = (Class_)model.getElementById("_wLHFZ4A_Ed2xsvzRuEHZYg").getDelegate();
    	
        // ChannelObject
    	clss  = (Class_)model.getElementById("_wLHFk4A_Ed2xsvzRuEHZYg").getDelegate();
    	
    	factory.createGeneralization(clss, "_wLHGc4A_Ed2xsvzRuEHZYg");
    	
        // OutputChannelObject
    	clss  = (Class_)model.getElementById("_wLHFn4A_Ed2xsvzRuEHZYg").getDelegate();
    	
    	factory.createGeneralization(clss, "_wLHFk4A_Ed2xsvzRuEHZYg");
    	
        // TextOutputChannelObject
    	clss  = (Class_)model.getElementById("_wLHFqYA_Ed2xsvzRuEHZYg").getDelegate();
    	
    	factory.createGeneralization(clss, "_wLHFn4A_Ed2xsvzRuEHZYg");
    	
        // StandardOutputChannelObject
    	clss  = (Class_)model.getElementById("_wLHFvoA_Ed2xsvzRuEHZYg").getDelegate();
    	
    	factory.createGeneralization(clss, "_wLHFqYA_Ed2xsvzRuEHZYg");
    	
        // InputChannelObject
    	clss  = (Class_)model.getElementById("_wLHF3IA_Ed2xsvzRuEHZYg").getDelegate();
    	
    	factory.createGeneralization(clss, "_wLHFk4A_Ed2xsvzRuEHZYg");
    	
        // StandardOutputWriteExecution
    	clss  = (Class_)model.getElementById("_wLHF6IA_Ed2xsvzRuEHZYg").getDelegate();
    	
    	factory.createGeneralization(clss, "Semantics-CommonBehaviors-BasicBehaviors-OpaqueBehaviorExecution");
    	
        // Classes
    	clss  = (Class_)model.getElementById("_wLHGDoA_Ed2xsvzRuEHZYg").getDelegate();
    	
        // StandardIOClasses
    	clss  = (Class_)model.getElementById("_wLHGN4A_Ed2xsvzRuEHZYg").getDelegate();
    	
    	factory.createGeneralization(clss, "_wLHGDoA_Ed2xsvzRuEHZYg");
    	
        // OperationExecution
    	clss  = (Class_)model.getElementById("_wLHGUoA_Ed2xsvzRuEHZYg").getDelegate();
    	
    	factory.createGeneralization(clss, "_AsdFpCsHEd-ZSaSyWIWnJA");
    	
        // ImplementationObject
    	clss  = (Class_)model.getElementById("_wLHGc4A_Ed2xsvzRuEHZYg").getDelegate();
    	
    	factory.createGeneralization(clss, "_AsmOnisHEd-ZSaSyWIWnJA");
    	
        // PipeInputChannelObject
    	clss  = (Class_)model.getElementById("_wLHGm4A_Ed2xsvzRuEHZYg").getDelegate();
    	
    	factory.createGeneralization(clss, "_wLHF3IA_Ed2xsvzRuEHZYg");
    	
        // PipeOutputChannelObject
    	clss  = (Class_)model.getElementById("_wLHGu4A_Ed2xsvzRuEHZYg").getDelegate();
    	
    	factory.createGeneralization(clss, "_wLHFn4A_Ed2xsvzRuEHZYg");
    	
    }

    private void constructAssociations()
    {
        Package pkg = null;
        String packageId = null;
        Association assoc = null;
        Property prop = null;
        
                                                      
        packageId = this.artifact.getUrn() + "#" + "_wLHGloA_Ed2xsvzRuEHZYg";   
                      
        // org.modeldriven.fuml.library.pipe. 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("", "_wLHGl4A_Ed2xsvzRuEHZYg");
        mapping.mapAssociation(assoc, "org.modeldriven.fuml.library.pipe", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "", 
    	    "_wLHGmIA_Ed2xsvzRuEHZYg",
    	    "_wLHGm4A_Ed2xsvzRuEHZYg",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, true, "");
        factory.createUpperValue(prop, true, "1");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "_wLHGnoA_Ed2xsvzRuEHZYg _wLHGmIA_Ed2xsvzRuEHZYg");
                                                      
        packageId = this.artifact.getUrn() + "#" + "_wLHGloA_Ed2xsvzRuEHZYg";   
                      
        // org.modeldriven.fuml.library.pipe. 
        pkg = (Package)model.getElementById(packageId).getDelegate();       
     	assoc  = factory.createAssociation("", "_wLHG1oA_Ed2xsvzRuEHZYg");
        mapping.mapAssociation(assoc, "org.modeldriven.fuml.library.pipe", this);
        // create owned ends
    	    	
    	prop = factory.createProperty(assoc, "", 
    	    "_wLHG14A_Ed2xsvzRuEHZYg",
    	    "_wLHGu4A_Ed2xsvzRuEHZYg",
    	    "",    	    
    	    "",    	    
    		false, false, false);    	
        factory.createLowerValue(prop, false, "");
        factory.createUpperValue(prop, false, "");
    	mapping.mapProperty(assoc, prop, this);
    	    
        factory.createAssociationEnds(assoc, "_wLHGvYA_Ed2xsvzRuEHZYg _wLHG14A_Ed2xsvzRuEHZYg");
    
    }

}
    
    