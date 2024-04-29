package ule.ed.recursivelist;

import static org.junit.Assert.*;

import org.junit.*;


public class LinkedEDListTest {
	private LinkedEDList<String> lista;
	
	@Before
	public void test() {
		 lista= new LinkedEDList<String>();
	}

	@Test
	public void test_Vacia() {
		assertEquals(0,lista.size());
	}

	@Test
	public void testsize() {
		Assert.assertEquals(0, lista.size());
		lista.addLast("3");
		lista.addLast("4");
		lista.addLast("6");
		lista.addLast("7");
		Assert.assertEquals(4, lista.size());
	}

	@Test
	public void test_isEmpty_true() {
		Assert.assertTrue(lista.isEmpty());
	}

	@Test
	public void test_isEmpty_false() {
		lista.addLast("3");
		lista.addLast("4");
		Assert.assertFalse(lista.isEmpty());
	}
	
	@Test
	public void test_AddLast() {
		lista.addLast("2");
		Assert.assertFalse(lista.isEmpty());
		Assert.assertEquals("(2 )", lista.toString());
		lista.addLast("3");
		Assert.assertEquals("(2 3 )", lista.toString());
		lista.addLast("7");
		Assert.assertEquals("(2 3 7 )", lista.toString());
	}
	
	@Test
	public void testAddPoss() {
		lista.addLast("3");
		lista.addLast("4");
		lista.addLast("6");
		lista.addLast("7");
		lista.addPos("5", 3);
		Assert.assertEquals("(3 4 5 6 7 )", lista.toString());
		lista.addPos("8", 9);
		Assert.assertEquals("(3 4 5 6 7 8 )", lista.toString());
	}

	@Test(expected = NullPointerException.class)
	public void testAddPosNull() {
		lista.addPos(null, 6);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddPosIllegalArgument() {
		lista.addPos("4", -5);
	}

	@Test(expected=NullPointerException.class)
	public void test_addLast_ElementoNulo() {
			lista.addLast(null);
	}
	
	@Test
	public void linkedtestAddPos_Varios() {
		lista.addPos("2",1);
		Assert.assertFalse(lista.isEmpty());
		Assert.assertEquals("(2 )", lista.toString());
		lista.addPos("3",1);
		Assert.assertEquals("(3 2 )", lista.toString());
		lista.addPos("7",2);
		Assert.assertEquals("(3 7 2 )", lista.toString());
		lista.addPos("10",3);
		Assert.assertEquals("(3 7 10 2 )", lista.toString());
		
	}

	@Test
	public void testGetElemPos() {
		lista.addLast("3");
		lista.addLast("4");
		lista.addLast("6");
		lista.addLast("7");
		lista.addLast("3");
		lista.addLast("5");
		Assert.assertEquals("7", lista.getElemPos(4));
		Assert.assertEquals("3", lista.getElemPos(1));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetElemPosIllegalArgument() {
		lista.getElemPos(-3);
		lista.addLast("5");
		lista.getElemPos(7);
	}

	@Test(expected=EmptyCollectionException.class)
	public void test_RemoveLastElem_Vacia() throws EmptyCollectionException{
		lista.removeLastElem("A");
	}
	
	// TODO  AÑADIR RESTO DE METODOS DE TESTS
	
	
}
