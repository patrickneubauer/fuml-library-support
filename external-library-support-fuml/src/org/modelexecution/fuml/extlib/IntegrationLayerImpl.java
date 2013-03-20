/**
 * 
 */
package org.modelexecution.fuml.extlib;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;

import org.apache.commons.collections.CollectionUtils;
import org.modelexecution.fumldebug.core.ExecutionContext;
import org.modelexecution.fumldebug.core.event.Event;

import fUML.Semantics.Classes.Kernel.ExtensionalValueList;
import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Syntax.Activities.IntermediateActivities.Activity;
import fUML.Syntax.Classes.Kernel.Class_;

/**
 * Implementation of the {@link IntegrationLayer}
 * 
 * @author Patrick Neubauer
 * 
 */
public class IntegrationLayerImpl implements IntegrationLayer {

	private final ExecutionContext executionContext = ExecutionContext.getInstance();

	private DynamicClassLoader dynamicClassLoader;

	private HashMap<Object_, Object> fUmlJavaMap = new HashMap<Object_, Object>();
	private HashMap<Object, Object_> javaFUmlMap = new HashMap<Object, Object_>();

	private ExtensionalValueList previousExtensionalValueList = new ExtensionalValueList();

	/*
	 * Whenever the {@link IntegrationLayer} is added as an {@link
	 * ExecutionEventListener} to the {@link ExecutionContext}, this method is
	 * called on each {@link Event}.
	 * 
	 * @see org.modelexecution.fumldebug.core.ExecutionEventListener#notify(org.
	 * modelexecution.fumldebug.core.event.Event)
	 */
	@Override
	public void notify(Event event) {
		/*
		 * Whenever this method is reached, the execution is stopped s.t. the IL
		 * can do its job. Afterwards, the execution can be resumed
		 * (ExecutionContext.resume(int executionID)).
		 */
		System.out.println(event);
		if (EventHelper.isExternalCreateObjectActionEntry(event)) {
			// store current ExtensionalValueList to compare later on
			previousExtensionalValueList = (ExtensionalValueList) executionContext.getExtensionalValues().clone();

		} else if (EventHelper.isExternalCreateObjectActionExit(event)) {
			Object_ fUmlPlaceholderObject = obtainFUmlPlaceholderObject();
			Object javaObject = obtainJavaObject(event);

			Object_Builder object_Builder = new Object_Builder(event, javaObject);
			Object_ fUmlObject = object_Builder.getObject_();

			System.out.println("fUML Placeholder Object = " + fUmlPlaceholderObject);

			replaceLocusObject(fUmlPlaceholderObject, fUmlObject);
			addObjects(fUmlObject, javaObject);

		} else if (EventHelper.isExternalCallOperationAction(event)) {
			// TODO do something with this CallOperationAction
		}
	}

	/**
	 * Obtains the previously created fUML {@link Object_}
	 * 
	 * @return the previously created fUML {@link Object_}
	 */
	private Object_ obtainFUmlPlaceholderObject() {
		Collection<ExtensionalValueList> extValList = CollectionUtils.subtract(executionContext.getExtensionalValues(), previousExtensionalValueList);
		Object_ fUmlPlaceholderObject = (Object_) extValList.toArray()[0];

		return fUmlPlaceholderObject;
	}

	private void replaceLocusObject(Object_ fUmlPlaceholderObject, Object_ fUmlObject) {
		/*
		 * TODO 1) Remove corresponding Object_ from Locus 2) Add the newly
		 * created Object_ to the Locus
		 * 
		 * The Locus can be obtained from the ExecutionContext by calling
		 * getLocus()
		 */
		executionContext.getLocus().remove(fUmlPlaceholderObject);
		executionContext.getLocus().add(fUmlObject);
	}

	/**
	 * Obtains a Java {@link Object} from a given {@code event} using the
	 * {@link DynamicClassLoader}
	 * 
	 * @param event
	 *            from which the {@link Object} is obtained
	 * @return Java {@link Object} instance if found, null otherwise
	 */
	private Object obtainJavaObject(Event event) {
		Object javaObject = null;

		try {
			String jarPath = EventHelper.obtainClassJarPath(event);
			String className = EventHelper.obtainClassName(event);
			String classNamespace = EventHelper.obtainClassNamespace(event);

			dynamicClassLoader = new DynamicClassLoader(jarPath);
			ClassLoader classLoader = dynamicClassLoader.getClassLoader();

			Class<?> clazz = classLoader.loadClass(classNamespace + "." + className);
			javaObject = clazz.newInstance();

		} catch (Exception e) {
			System.out.println("Error occured while trying to obtain the Java object. " + e);
		}

		return javaObject;
	}

	public IntegrationLayerImpl() {

		initialize();

	}

	private void initialize() {

		// IL registers itself at the ExecutionContext instance
		executionContext.addEventListener(this);

	}

	private void addObjects(Object_ fUmlObject, Object javaObject) {
		javaFUmlMap.put(javaObject, fUmlObject);
		fUmlJavaMap.put(fUmlObject, javaObject);
	}

	@Override
	public Object_ getFUmlObject(Object javaObject) {
		return javaFUmlMap.get(javaObject);
	}

	@Override
	public Object getJavaObject(Object_ fUmlObject) {
		return fUmlJavaMap.get(fUmlObject);
	}

	@Override
	public ExecutionContext getExecutionContext() {
		return this.executionContext;
	}

}
