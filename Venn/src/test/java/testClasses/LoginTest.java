package testClasses;

//JUnit Imports
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

//TestFX Imports
import org.testfx.api.FxAssert;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.control.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

//import org.testfx.service.query.*;
import org.assertj.core.api.Assertions;

//Others
import login.Login;
import login.UserInterface;
import login.AlertBox;
import application.MainController;
import database.AccSys;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;



@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoginTest extends ApplicationTest{
	
	Login  login;
	
	@Before
	public void setUpClass() throws Exception {
		ApplicationTest.launch(Login.class);
	}

	@Test
	public void empty_input() {
		clickOn(Login.loginButton);
		assertTrue(AlertBox.get_stage());
	}
	
	@Test
	public void empty_register_fail() {
		clickOn(Login.nameInput).write("qwer");
		clickOn(Login.pwInput).write("anyway");
		clickOn(Login.register);
		assertTrue(AlertBox.get_stage());
	}
	
	@Test
	public void empty_pw() {
		clickOn(Login.nameInput).write("qwer");
		clickOn(Login.loginButton);
		assertTrue(AlertBox.get_stage());
	}
	
	@Test
	public void success_login() {
		clickOn(Login.nameInput).write("qwer");
		clickOn(Login.pwInput).write("123456");
		clickOn(Login.loginButton);
		assertFalse(Login.window.isShowing());
	}
	
	private void login() {
		clickOn(Login.nameInput).write("qwer");
		clickOn(Login.pwInput).write("123456");
		clickOn(Login.loginButton);
		assertFalse(Login.window.isShowing());
	}
	
	@Test
	public void addfunction() {
		login();
		clickOn(UserInterface.add);
		FxAssert.verifyThat(UserInterface.list, ListViewMatchers.hasListCell("NewVenn"));
		
	}
	
	@Test
	public void deletefunction() {
		login();
		UserInterface.list.getSelectionModel().select("NewVenn");
		clickOn(UserInterface.delete);
		assertFalse(UserInterface.list.getItems().contains("NowVenn"));
	}
	
	@Test
	public void modifyfunction_string() {
		String first;
		login();
		UserInterface.list.getSelectionModel().select(0);
		first = UserInterface.list.getItems().get(0);
		assertNull(AccSys.v);
		clickOn(UserInterface.modify);
		assertNotNull(AccSys.v);
	}
	
}
