package dinerFunctionalTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({

	BoundaryTestsForDinerName.class,
	BoundaryTestsForDescription.class,
	DeleteButtonTest.class,
	HeadLineTest.class,
	LastModifiedAndCreatedTest.class
})
public class AllFunctionalTests {
}
