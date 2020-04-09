package testClasses;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import database.AccSys;

public class DatebaseTest {

	@Test
	public void testAccSys_initialize1() throws Exception {
		AccSys a = new AccSys();
		assertNotNull(a.accounts);
	}
	
	
	@Test
	public void testAccSys_initialize2() throws Exception {
		AccSys a = new AccSys();
		assertNull(AccSys.current);
	}
	
	@Test
	public void test_private_password() throws Exception {
		String pass = "password";
		String storage = "" + AccSys.getpwcode(pass);
		assertNotEquals(pass, storage);
	}
}
