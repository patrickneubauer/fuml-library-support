



/*
 * Initial version copyright 2008 Lockheed Martin Corporation, except  
 * as stated in the file entitled Licensing-Information. 
 * 
 * All modifications copyright 2009 Data Access Technologies, Inc.
 *
 * Licensed under the Academic Free License version 3.0 
 * (http://www.opensource.org/licenses/afl-3.0.php), except as stated 
 * in the file entitled Licensing-Information. 
 *
 * Contributors:
 *   MDS - initial API and implementation
 *
 */

package fUML.Syntax.Classes.Kernel;

import fUML.Debug;
import UMLPrimitiveTypes.*;



/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>fUML::Syntax::Classes::Kernel::Namespace</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 	 *   <li>{@link Namespace#addOwnedMember <em>addOwnedMember</em>}</li>
	 *   <li>{@link Namespace#addMember <em>addMember</em>}</li>
	 *   <li>{@link Namespace#addElementImport <em>addElementImport</em>}</li>
	 *   <li>{@link Namespace#addPackageImport <em>addPackageImport</em>}</li>
	 *   <li>{@link Namespace#addImportedMember <em>addImportedMember</em>}</li>
	 	 *   <li>{@link Namespace#member <em>member</em>}</li>
	 *   <li>{@link Namespace#ownedMember <em>ownedMember</em>}</li>
	 *   <li>{@link Namespace#elementImport <em>elementImport</em>}</li>
	 *   <li>{@link Namespace#packageImport <em>packageImport</em>}</li>
	 *   <li>{@link Namespace#importedMember <em>importedMember</em>}</li>
	 * </ul>
 * </p>
 *
 * @generated
 */

public  abstract class Namespace extends fUML.Syntax.Classes.Kernel.PackageableElement    {
    
  public   fUML.Syntax.Classes.Kernel.NamedElementList member = new fUML.Syntax.Classes.Kernel.NamedElementList();
  public   fUML.Syntax.Classes.Kernel.NamedElementList ownedMember = new fUML.Syntax.Classes.Kernel.NamedElementList();
  public   fUML.Syntax.Classes.Kernel.ElementImportList elementImport = new fUML.Syntax.Classes.Kernel.ElementImportList();
  public   fUML.Syntax.Classes.Kernel.PackageImportList packageImport = new fUML.Syntax.Classes.Kernel.PackageImportList();
  public   fUML.Syntax.Classes.Kernel.PackageableElementList importedMember = new fUML.Syntax.Classes.Kernel.PackageableElementList();
    
  /**
   * operation addOwnedMember
   * <!-- begin-user-doc -->
   		   * <!-- end-user-doc -->
   * @generated
   */
  protected      void addOwnedMember(fUML.Syntax.Classes.Kernel.NamedElement ownedMember)   {
this.addOwnedElement(ownedMember);

this.ownedMember.addValue(ownedMember);
ownedMember.namespace = this;

this.addMember(ownedMember);
	  } // addOwnedMember

  /**
   * operation addMember
   * <!-- begin-user-doc -->
   		   * <!-- end-user-doc -->
   * @generated
   */
  protected      void addMember(fUML.Syntax.Classes.Kernel.NamedElement member)   {
// Note: This operation should not be used for owned members. The operation addOwnedMember should be used instead.

// Debug.println("[addMember] member is a " + member.getClass().getName() + "; name = " + member.name);

this.member.addValue(member);

	  } // addMember

  /**
   * operation addElementImport
   * <!-- begin-user-doc -->
   		   * <!-- end-user-doc -->
   * @generated
   */
  public      void addElementImport(fUML.Syntax.Classes.Kernel.ElementImport elementImport)   {
this.addOwnedElement(elementImport);

this.elementImport.addValue(elementImport);
elementImport.importingNamespace = this;

this.addImportedMember(elementImport.importedElement);
	  } // addElementImport

  /**
   * operation addPackageImport
   * <!-- begin-user-doc -->
   		   * <!-- end-user-doc -->
   * @generated
   */
  public      void addPackageImport(fUML.Syntax.Classes.Kernel.PackageImport packageImport)   {
this.addOwnedElement(packageImport);

this.packageImport.addValue(packageImport);
packageImport.importingNamespace = this;

PackageableElementList importedElements = packageImport.importedPackage.visibleMembers();
for (int i = 0; i < importedElements.size(); i++) {
  PackageableElement importedElement = importedElements.getValue(i);
  this.addImportedMember(importedElement);
}
	  } // addPackageImport

  /**
   * operation addImportedMember
   * <!-- begin-user-doc -->
   		   * <!-- end-user-doc -->
   * @generated
   */
  private      void addImportedMember(fUML.Syntax.Classes.Kernel.PackageableElement importedMember)   {
this.addMember(importedMember);
this.importedMember.addValue(importedMember);
	  } // addImportedMember

} //Namespace
