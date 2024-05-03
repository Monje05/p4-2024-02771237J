package ule.ed.recursivelist;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

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

	@Test
	public void testGetPosFirst() {
		lista.addLast("4");
		lista.addLast("4");
		lista.addLast("6");
		lista.addLast("3");
		lista.addLast("3");
		lista.addLast("5");
		Assert.assertEquals(4, lista.getPosFirst("3"));
	}

	@Test(expected = NullPointerException.class)
	public void testGetPosFirstNullException() {
		lista.getPosFirst(null);
	}

	@Test(expected = NoSuchElementException.class) 
	public void testGetPosFirstNoSuchException() {
		lista.addLast("4");
		lista.addLast("4");
		lista.addLast("6");
		lista.addLast("3");
		lista.addLast("3");
		lista.addLast("5");
		Assert.assertEquals(-1, lista.getPosFirst("9"));
	}

	@Test
	public void testGetPosLast() {
		lista.addLast("4");
		lista.addLast("4");
		lista.addLast("6");
		lista.addLast("3");
		lista.addLast("3");
		lista.addLast("5");
		Assert.assertEquals(2, lista.getPosLast("4"));
	}

	@Test(expected = NullPointerException.class)
	public void testGetPosLastNullException() {
		lista.getPosLast(null);
	}

	@Test(expected = NoSuchElementException.class) 
	public void testGetPosLastNoSuchException() {
		lista.addLast("4");
		lista.addLast("4");
		lista.addLast("6");
		lista.addLast("3");
		lista.addLast("3");
		lista.addLast("5");
		Assert.assertEquals(-1, lista.getPosLast("9"));
	}

	@Test
	public void testRemoveLast() throws EmptyCollectionException {
		lista.addLast("4");
		lista.addLast("4");
		lista.addLast("6");
		lista.addLast("3");
		lista.addLast("3");
		lista.addLast("5");
		lista.removelast();
		Assert.assertEquals("(4 4 6 3 3 )", lista.toString());
	}

	@Test(expected = EmptyCollectionException.class)
	public void testRemoveLastEmptyException() throws EmptyCollectionException{
		lista.removelast();
	}

	@Test
	public void testRemoveLastElem() {
		lista.addLast("4");
		lista.addLast("4");
		lista.addLast("6");
		lista.addLast("3");
		lista.addLast("3");
		lista.addLast("5");
		Assert.assertEquals(6, lista.removeLastElem("5"));
	}

	@Test(expected = NullPointerException.class)
	public void testRemoveLastNullException() {
		lista.removeLastElem(null);
	}

	@Test(expected = NoSuchElementException.class)
	public void testRemoveLastNoSuchExcepion() {
		lista.removeLastElem("4");
		lista.addLast("4");
		lista.addLast("4");
		lista.addLast("6");
		lista.addLast("3");
		lista.addLast("3");
		lista.addLast("5");
		lista.removeLastElem("9");
	}

	@Test
	public void testReverse() {
		lista.addLast("4");
		lista.addLast("4");
		lista.addLast("6");
		lista.addLast("3");
		lista.addLast("3");
		lista.addLast("5");
		Assert.assertEquals("(5 3 3 6 4 4 )", lista.reverse().toString());
	}

	@Test
	public void testRemoveOddElements() {
		lista.addLast("1");
		lista.addLast("4");
		lista.addLast("6");
		lista.addLast("3");
		lista.addLast("7");
		lista.addLast("5");
		lista.removeOddElements();
		Assert.assertEquals("(4 3 5 )", lista.toString());
	}

	@Test
	public void testRemoveConsecutive() {
		lista.addLast("1");
		lista.addLast("4");
		lista.addLast("4");
		lista.addLast("3");
		lista.addLast("3");
		lista.addLast("3");
		Assert.assertEquals(3, lista.removeConsecDuplicates());
	}

	@Test
	public void testToStringFromUntil() {
		lista.addLast("1");
		lista.addLast("4");
		lista.addLast("4");
		lista.addLast("3");
		lista.addLast("6");
		lista.addLast("3");
		Assert.assertEquals("(4 4 1 )", lista.toSringExceptFromUntilReverse(6, 4));
		Assert.assertEquals("(4 1 )", lista.toSringExceptFromUntilReverse(15, 3));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testToStringFromUntilException() {
		lista.toSringExceptFromUntilReverse(-1, 4);
		lista.toSringExceptFromUntilReverse(4, -2);
		lista.toSringExceptFromUntilReverse(2, 4);
	}

	@Test
	public void testLengthEquals() {
		lista.addLast("1");
		lista.addLast("4");
		lista.addLast("4");
		lista.addLast("3");
		lista.addLast("6");
		lista.addLast("3");
		Assert.assertTrue(lista.lengthEqualsTo(6));
		Assert.assertFalse(lista.lengthEqualsTo(8));
		Assert.assertFalse(lista.lengthEqualsTo(3));
	}

	@Test
	public void testRemoveFirstElem() {
		lista.addLast("1");
		lista.addLast("4");
		lista.addLast("4");
		lista.addLast("3");
		lista.addLast("6");
		lista.addLast("3");
		Assert.assertEquals(4, lista.removeFirstElem("3"));
	}

	@Test(expected = NullPointerException.class)
	public void testRemoveFirstElemNullException() {
		lista.removeFirstElem(null);
	}

	@Test(expected = NoSuchElementException.class)
	public void testRemoveFirstElemNoSuch() {
		lista.addLast("1");
		lista.addLast("4");
		lista.addLast("4");
		lista.addLast("3");
		lista.addLast("6");
		lista.addLast("3");
		lista.removeFirstElem("2");
	}

	@Test
	public void testRemoveEvenElements() {
		lista.addLast("1");
		lista.addLast("4");
		lista.addLast("4");
		lista.addLast("3");
		lista.addLast("6");
		lista.addLast("3");
		lista.removeEvenElements();
		Assert.assertEquals("(1 4 6 )", lista.toString());
	}

	@Test
	public void testAddBefore() {
		lista.addLast("1");
		lista.addLast("4");
		lista.addLast("4");
		lista.addLast("3");
		lista.addLast("6");
		lista.addLast("3");
		Assert.assertTrue(lista.addBefore("5", "6"));
		Assert.assertFalse(lista.addBefore("8", "9"));
	}

	@Test(expected = NullPointerException.class)
	public void testAddBeforeNull() {
		lista.addBefore(null, null);
	}
	
	@Test(expected=NoSuchElementException.class)
	public void test_RemoveLastElem_Vacia() throws EmptyCollectionException{
		lista.removeLastElem("A");
	}
	
	// TODO  AÑADIR RESTO DE METODOS DE TESTS
	
	
}
