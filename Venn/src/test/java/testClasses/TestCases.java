package testClasses;

// JUnit Imports
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

//TestFX Imports
import org.testfx.api.FxAssert;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.control.*;
//import org.testfx.service.query.*;
import org.assertj.core.api.Assertions;

//Others
import application.Main;
import application.MainController;
import javafx.scene.control.ListView;
import javafx.stage.Stage;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestCases extends ApplicationTest {
	/**
	 * Will be called with {@code @Before} semantics, i. e. before each test method.
	 */

	@Before
	public void setUpClass() throws Exception {
		ApplicationTest.launch(Main.class);
	}

	@Override
	public void start(Stage stage) {
		stage.show();
	}

	@Test
	public void dragDropTest() {
		
		clickOn("#inputText").write("A");
		clickOn("#test");
		
		clickOn("#inputText").write("B");
		clickOn("#test");	
		
		clickOn("#inputText").write("C");
		clickOn("#test");	
		
		drag("A").dropTo("#left");
		drag("B").dropTo("#middle");
		drag("C").dropTo("#right");
		
		
		FxAssert.verifyThat("#left", ListViewMatchers.hasListCell("A"));
		FxAssert.verifyThat("#middle", ListViewMatchers.hasListCell("B"));
		FxAssert.verifyThat("#right", ListViewMatchers.hasListCell("C"));
	}
	
	@Test
	public void whenAddClickedElementMovesToMasterList() {

		// when:
		clickOn("#inputText").write("Element A");

		sleep(100);

		clickOn("#test");

		FxAssert.verifyThat("#holder", ListViewMatchers.hasListCell("Element A"));

//    		verifyThat("#holder", hasText("Element A"));
		// then:
//    		Assertions.assertThat("#holder", );
//    		Assertions.assertThat(MainController.isDark());
//        // when:
//        clickOn(".button");
//
//        // then:
//        Assertions.assertThat(button).hasText("clicked!");
	}
	
	@Test
	public void backToLightModeTest() {

		clickOn("#About_menu");
		sleep(100);
		clickOn("Switch to Dark Mode");
		sleep(100);

		Assertions.assertThat(MainController.isDark());
		
		clickOn("#About_menu");
		sleep(100);
		clickOn("Switch to Light Mode");
		sleep(100);

		Assertions.assertThat(!MainController.isDark());

	}
	

	@Test
	public void addingMultipleElementsToMasterList() {

		clickOn("#inputText").write("Element A");

		sleep(100);

		clickOn("#test");

		clickOn("#inputText").write("Element B");

		sleep(100);

		clickOn("#test");

		FxAssert.verifyThat("#holder", ListViewMatchers.hasListCell("Element A"));
		FxAssert.verifyThat("#holder", ListViewMatchers.hasListCell("Element B"));

	}

	@Test
	public void AlwaysFailsTest() {

		FxAssert.verifyThat("#holder", ListViewMatchers.hasListCell("DestinedToFail"));

	}
	
//	@Test
//	public void intersectionTestRMenus() {
//		
//		clickOn("#inputText").write("Element A");
//		clickOn("#test");	
////		clickOn("#inputText").write("Element B");
////		clickOn("#test");	
//		
//		rightClickOn("Element A");
//		rightClickOn("Element A");
////		rightClickOn("#holder");
////		rightClickOn("#holder");
//		clickOn("Move Elements");
////		moveBy(10,-20);
//		sleep(50);
//		clickOn("Move Element To Second Set");
////		clickOn("Move All To Second Set");
//		
//		clickOn("#inputText").write("Element A");
//		clickOn("#test");	
////		clickOn("#inputText").write("Element B");
////		clickOn("#test");	
//		
//		sleep(1000);
//		rightClickOn(ListViewMatchers.hasListCell("Element A"));
//		rightClickOn(ListViewMatchers.hasListCell("Element A"));
////		rightClickOn("#holder");
////		rightClickOn("#holder");
//		clickOn("Move Elements");
////		moveBy(10,-20);
//		sleep(50);
//		clickOn("Move Element To Second Set");
////		clickOn("Move All To First Set");
//		
//		FxAssert.verifyThat("#middle", ListViewMatchers.hasListCell("Element A"));
//		
//	}

}