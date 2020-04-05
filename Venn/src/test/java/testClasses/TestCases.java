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
import org.assertj.core.api.Assertions;

//Others
import application.Main;
import application.MainController;
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

		clickOn("#About_menu");
		sleep(1000);
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
	public void darkModeTest() {

		clickOn("#About_menu");
		sleep(100);
		clickOn("Switch to Dark Mode");
		sleep(200);
//    	clickOn("");

		Assertions.assertThat(MainController.isDark());

	}

    @Test
public void AlwaysFailsTest() {
    	
    	FxAssert.verifyThat("#holder", ListViewMatchers.hasListCell("DestinedToFail"));
    	
    }

}