package tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import org.junit.Test;


@SuppressWarnings({ "rawtypes", "unchecked" })
public class Q1V4Tests {

	// ############################################# Classes' Paths  ############################################# //

	
	
	String cityPath = "engine.City";
	
	String citizenPath= "citizens.Citizen";
	String farmerPath= "citizens.Farmer";
	String sellerPath= "citizens.Seller";
	
	// ############################################# Test Methods  ############################################# //
	@Test(timeout = 100)
	public void testClassIsAbstractCitizen() throws Exception {
		testClassIsAbstract(Class.forName(citizenPath));
	}

	@Test(timeout = 100)
	public void testConstructorCitizen() throws Exception {
		Class[] inputs = { String.class, int.class };
		testConstructorExists(Class.forName(citizenPath), inputs);
		
	}

	@Test(timeout = 100)
	public void testCitizenInstanceVariableName() throws Exception {
		testInstanceVariableIsPresent(Class.forName(citizenPath), "name", true);
		
		testInstanceVariableIsPresent(Class.forName(farmerPath), "name", false);
		testInstanceVariableIsPresent(Class.forName(sellerPath), "name", false);
		
		
		testInstanceVariableIsPrivate(Class.forName(citizenPath), "name");
		testInstanceVariableOfType(Class.forName(citizenPath), "name", String.class);
	}

	@Test(timeout = 100)
	public void testCitizenNameSetter() throws Exception {
		testSetterMethodExistsInClass(Class.forName(citizenPath), "setName", String.class, false);
		String[] subClasses = { farmerPath, sellerPath};
		testSetterAbsentInSubclasses("name", subClasses);
	}

	@Test(timeout = 100)
	public void testCitizenNameGetter() throws Exception {

		testGetterMethodExistsInClass(Class.forName(citizenPath), "getName", String.class, true);
		String[] subClasses ={ farmerPath, sellerPath };
		testGetterAbsentInSubclasses("name", subClasses, String.class);
		
		Constructor<?> constructor = Class.forName(farmerPath).getConstructor(String.class, int.class);
		int randomAge= (int) (Math.random() * 10) + 30;
		Object b = constructor.newInstance("Name",randomAge);

		testGetterLogic(b, "name", "Name" );
	}

	@Test(timeout = 100)
	public void testCitizenInstanceVariableAge() throws Exception {
		testInstanceVariableIsPresent(Class.forName(citizenPath), "age", true);
		
		testInstanceVariableIsPresent(Class.forName(farmerPath), "age", false);
		testInstanceVariableIsPresent(Class.forName(sellerPath), "age", false);
				
		testInstanceVariableIsPrivate(Class.forName(citizenPath), "age");
		testInstanceVariableOfType(Class.forName(citizenPath), "age", int.class);
	}

	@Test(timeout = 100)
	public void testCitizenAgeSetter() throws Exception {
		testSetterMethodExistsInClass(Class.forName(citizenPath), "setAge", int.class, true);
		String[] subClasses = { farmerPath, sellerPath };
		testSetterAbsentInSubclasses("age", subClasses);

		Constructor<?> constructor = Class.forName(farmerPath).getConstructor(String.class, int.class);
		int randomAge= (int) (Math.random() * 10) + 30;
		Object b = constructor.newInstance("Name",randomAge);
		randomAge= (int) (Math.random() * 10) + 30;
		testSetterLogic(b, "age", randomAge, randomAge, int.class);
		
	}

	@Test(timeout = 100)
	public void testCitizenAgeGetter() throws Exception {

		testGetterMethodExistsInClass(Class.forName(citizenPath), "getAge", int.class, true);
		String[] subClasses ={ farmerPath, sellerPath };
		testGetterAbsentInSubclasses("name", subClasses, String.class);
		
		Constructor<?> constructor = Class.forName(farmerPath).getConstructor(String.class, int.class);
		int randomAge= (int) (Math.random() * 10) + 30;
		Object b = constructor.newInstance("Name",randomAge);
		randomAge= (int) (Math.random() * 10) + 30;
		testGetterLogic(b, "age", randomAge );
	}

	@Test(timeout = 100)
	public void testClassIsSubclassFarmer() throws Exception {
		testClassIsSubclass(Class.forName(farmerPath), Class.forName(citizenPath));
	}
	@Test(timeout = 100)
	public void testClassIsSubclassSeller() throws Exception {
		testClassIsSubclass(Class.forName(sellerPath), Class.forName(citizenPath));
	}
	
	
	@Test(timeout = 100)
	public void testConstructorFarmer() throws Exception {
		Class[] inputs = { String.class, int.class };
		testConstructorExists(Class.forName(farmerPath), inputs);
		
	}
	@Test(timeout = 100)
	public void testConstructorSeller() throws Exception {
		Class[] inputs = { String.class, int.class };
		testConstructorExists(Class.forName(sellerPath), inputs);
		
	}
	@Test(timeout = 100)
	public void testConstructorFarmerConstructorInitialization() throws Exception {
		Constructor<?> constructor = Class.forName(farmerPath).getConstructor(String.class,int.class);
		int random = (int) (Math.random() * 50);
		String name = "name" + random;
		int random1 = (int) (Math.random() * 4);
		ArrayList<String> tools = new ArrayList<>();
		Object myObj = constructor.newInstance(name,random1);
		String[] names = { "name" ,"age","tools"};
		Object[] values = { name,random1 ,tools};
		testConstructorInitialization(myObj, names, values);

	}
	@Test(timeout = 100)
	public void testConstructorSellerConstructorInitialization() throws Exception {
		Constructor<?> constructor = Class.forName(sellerPath).getConstructor(String.class,int.class);
		int random = (int) (Math.random() * 50);
		String name = "name" + random;
		int random1 = (int) (Math.random() * 4);
		
		Object myObj = constructor.newInstance(name,random1);
		String[] names = { "name" ,"age","products"};
		Object[] values = { name,random1,10 };
		testConstructorInitialization(myObj, names, values);

	}
	@Test(timeout = 100)
	public void testFarmerInstanceVariableTools() throws Exception {
		testInstanceVariableIsPresent(Class.forName(farmerPath), "tools", true);
		
		
		
		testInstanceVariableIsPrivate(Class.forName(farmerPath), "tools");
		testInstanceVariableOfType(Class.forName(farmerPath), "tools", ArrayList.class);
	}
	@Test(timeout = 100)
	public void testSellerInstanceVariableProducts() throws Exception {
		testInstanceVariableIsPresent(Class.forName(sellerPath), "products", true);
		testInstanceVariableOfType(Class.forName(sellerPath), "products", int.class);
		testInstanceVariableIsFinalAndPrivate(Class.forName(sellerPath), "products");
	}
	@Test(timeout = 100)
	public void testFarmerToolsGetter() throws Exception {

		testGetterMethodExistsInClass(Class.forName(farmerPath), "getTools", ArrayList.class, true);

	}
	
	@Test(timeout = 100)
	public void testFarmerToolsSetter() throws Exception {

		testSetterMethodExistsInClass(Class.forName(farmerPath), "setTools", ArrayList.class, false);

	}
	@Test(timeout = 100)
	public void testSellerProductsGetter() throws Exception {

		testGetterMethodExistsInClass(Class.forName(sellerPath), "getProducts", int.class, true);

	}
	
	@Test(timeout = 100)
	public void testSellerProductsSetter() throws Exception {

		testSetterMethodExistsInClass(Class.forName(sellerPath), "setProducts", int.class, false);

	}
	
	
	
	
	
	//#####################################################################################
	private void testInstanceVariableIsPresent(Class aClass, String varName, boolean implementedVar)
			throws SecurityException {

		boolean thrown = false;
		try {
			aClass.getDeclaredField(varName);
		} catch (NoSuchFieldException e) {
			thrown = true;
		}
		if (implementedVar) {
			assertFalse(
					"There should be \"" + varName + "\" instance variable in class " + aClass.getSimpleName() + ".",
					thrown);
		} else {
			assertTrue("The instance variable \"" + varName + "\" should not be declared in class "
					+ aClass.getSimpleName() + ".", thrown);
		}
	}

	private void testInstanceVariableOfType(Class aClass, String varName, Class expectedType) {
		Field f = null;
		try {
			f = aClass.getDeclaredField(varName);
		} catch (NoSuchFieldException e) {
			return;
		}
		Class varType = f.getType();
		assertEquals(
				"the attribute " + varType.getSimpleName() + " should be of the type " + expectedType.getSimpleName(),
				expectedType, varType);
	}

	
	
	private void testInstanceVariableIsPrivate(Class aClass, String varName)
			throws NoSuchFieldException, SecurityException {
		Field f = aClass.getDeclaredField(varName);
		int modifiers = f.getModifiers();
		assertTrue(f.getName()+" variable in class " +aClass.getSimpleName()+ " should be private",(Modifier.isPrivate(modifiers)));
	}

	private void testInstanceVariableIsFinalAndPrivate(Class aClass, String varName)
			throws NoSuchFieldException, SecurityException {
		Field f = aClass.getDeclaredField(varName);
		int modifiers = f.getModifiers();
		assertTrue(f.getName()+" variable in class " +aClass.getSimpleName()+ " should be private",(Modifier.isPrivate(modifiers)));
		assertTrue(f.getName()+" variable in class "+ aClass.getSimpleName() +" should be final",(Modifier.isFinal(modifiers)));


	}

	private void testGetterMethodExistsInClass(Class aClass, String methodName, Class returnedType,
			boolean readvariable) {
		Method m = null;
		boolean found = true;
		try {
			m = aClass.getDeclaredMethod(methodName);
		} catch (NoSuchMethodException e) {
			found = false;
		}

		String varName = "";
		if (returnedType == boolean.class)
			varName = methodName.substring(2).toLowerCase();
		else
			varName = methodName.substring(3).toLowerCase();
		if (readvariable) {
			assertTrue("The \"" + varName + "\" instance variable in class " + aClass.getSimpleName()
					+ " is a READ variable.", found);
			assertTrue("Incorrect return type for " + methodName + " method in " + aClass.getSimpleName() + " class.",
					m.getReturnType().isAssignableFrom(returnedType));
		} else {
			assertFalse("The \"" + varName + "\" instance variable in class " + aClass.getSimpleName()
					+ " is not a READ variable.", found);
		}

	}

	private void testSetterMethodExistsInClass(Class aClass, String methodName, Class inputType,
			boolean writeVariable) {

		Method[] methods = aClass.getDeclaredMethods();
		String varName = methodName.substring(3).toLowerCase();
		if (writeVariable) {
			assertTrue("The \"" + varName + "\" instance variable in class " + aClass.getSimpleName()
					+ " is a WRITE variable.", containsMethodName(methods, methodName));
		} else {
			assertFalse("The \"" + varName + "\" instance variable in class " + aClass.getSimpleName()
					+ " is not a WRITE variable.", containsMethodName(methods, methodName));
			return;
		}
		Method m = null;
		boolean found = true;
		try {
			m = aClass.getDeclaredMethod(methodName, inputType);
		} catch (NoSuchMethodException e) {
			found = false;
		}

		assertTrue(aClass.getSimpleName() + " class should have " + methodName + " method that takes one "
				+ inputType.getSimpleName() + " parameter.", found);

		assertTrue("Incorrect return type for " + methodName + " method in " + aClass.getSimpleName() + ".",
				m.getReturnType().equals(Void.TYPE));

	}

	private void testSetterAbsentInSubclasses(String varName, String[] subclasses)
			throws SecurityException, ClassNotFoundException {
		String methodName = "set" + varName.substring(0, 1).toUpperCase() + varName.substring(1);
		boolean methodIsInSubclasses = false;
		for (String subclass : subclasses) {
			Method[] methods = Class.forName(subclass).getDeclaredMethods();
			methodIsInSubclasses = methodIsInSubclasses || containsMethodName(methods, methodName);

		}
		assertFalse("The " + methodName + " method should not be implemented in a subclasses.", methodIsInSubclasses);
	}

	private void testGetterAbsentInSubclasses(String varName, String[] subclasses, Class type)
			throws SecurityException, ClassNotFoundException {
		String methodName = "get" + varName.substring(0, 1).toUpperCase() + varName.substring(1);
		if (type == boolean.class) {
			methodName = "is" + varName.substring(0, 1).toUpperCase() + varName.substring(1);
		}
		boolean methodIsInSubclasses = false;
		for (String subclass : subclasses) {
			Method[] methods = Class.forName(subclass).getDeclaredMethods();
			methodIsInSubclasses = methodIsInSubclasses || containsMethodName(methods, methodName);

		}
		assertFalse("The " + methodName + " method should not be implemented in subclasses.", methodIsInSubclasses);
	}

	private static boolean containsMethodName(Method[] methods, String name) {
		for (Method method : methods) {
			if (method.getName().equals(name))
				return true;
		}
		return false;
	}

	private void testConstructorExists(Class aClass, Class[] inputs) {
		boolean thrown = false;
		try {
			aClass.getConstructor(inputs);
		} catch (NoSuchMethodException e) {
			thrown = true;
		}

		if (inputs.length > 0) {
			String msg = "";
			int i = 0;
			do {
				msg += inputs[i].getSimpleName() + " and ";
				i++;
			} while (i < inputs.length);

			msg = msg.substring(0, msg.length() - 4);

			assertFalse(
					"Missing constructor with " + msg + " parameter" + (inputs.length > 1 ? "s" : "") + " in "
							+ aClass.getSimpleName() + " class.",

					thrown);
		} else
			assertFalse("Missing constructor with zero parameters in " + aClass.getSimpleName() + " class.",

					thrown);

	}
	
	
	

	private void testClassIsAbstract(Class aClass) {
		assertTrue("You should not be able to create new instances from " + aClass.getSimpleName() + " class.",
				Modifier.isAbstract(aClass.getModifiers()));
	}

	

	private void testConstructorInitialization(Object createdObject, String[] names, Object[] values)
			throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException {

		for (int i = 0; i < names.length; i++) {

			Field f = null;
			Class curr = createdObject.getClass();
			String currName = names[i];
			Object currValue = values[i];

			while (f == null) {

				if (curr == Object.class)
					fail("Class " + createdObject.getClass().getSimpleName() + " should have the instance variable \""
							+ currName + "\".");
				try {
					f = curr.getDeclaredField(currName);
				} catch (NoSuchFieldException e) {
					curr = curr.getSuperclass();
				}

			}

			f.setAccessible(true);

			assertEquals(
					"The constructor of the " + createdObject.getClass().getSimpleName()
							+ " class should initialize the instance variable \"" + currName + "\" correctly.",
					currValue, f.get(createdObject));

		}

	}

	private void testGetterLogic(Object createdObject, String name, Object value) throws Exception {

		Field f = null;
		Class curr = createdObject.getClass();

		while (f == null) {

			if (curr == Object.class)
				fail("Class " + createdObject.getClass().getSimpleName() + " should have the instance variable \""
						+ name + "\".");
			try {
				f = curr.getDeclaredField(name);
			} catch (NoSuchFieldException e) {
				curr = curr.getSuperclass();
			}

		}

		f.setAccessible(true);
		f.set(createdObject, value);

		Character c = name.charAt(0);

		String methodName = "get" + Character.toUpperCase(c) + name.substring(1, name.length());

		if (value.getClass().equals(Boolean.class))
			methodName = "is" + Character.toUpperCase(c) + name.substring(1, name.length());

		Method m = createdObject.getClass().getMethod(methodName);
		assertEquals(
				"The method \"" + methodName + "\" in class " + createdObject.getClass().getSimpleName()
						+ " should return the correct value of variable \"" + name + "\".",
				value, m.invoke(createdObject));

	}

	private void testSetterLogic(Object createdObject, String name, Object setValue, Object expectedValue, Class type)
			throws Exception {

		Field f = null;
		Class curr = createdObject.getClass();

		while (f == null) {

			if (curr == Object.class)
				fail("Class " + createdObject.getClass().getSimpleName() + " should have the instance variable \""
						+ name + "\".");
			try {
				f = curr.getDeclaredField(name);
			} catch (NoSuchFieldException e) {
				curr = curr.getSuperclass();
			}

		}

		f.setAccessible(true);

		Character c = name.charAt(0);
		String methodName = "set" + Character.toUpperCase(c) + name.substring(1, name.length());
		Method m = createdObject.getClass().getMethod(methodName, type);
		m.invoke(createdObject, setValue);

		assertEquals(
				"The method \"" + methodName + "\" in class " + createdObject.getClass().getSimpleName()
						+ " should set the correct value of variable \"" + name + "\".",
				expectedValue, f.get(createdObject));

	}

	private void testClassIsSubclass(Class subClass, Class superClass) {
		assertEquals(subClass.getSimpleName() + " class should be a subclass from " + superClass.getSimpleName() + ".",
				superClass, subClass.getSuperclass());
	}


	
}
