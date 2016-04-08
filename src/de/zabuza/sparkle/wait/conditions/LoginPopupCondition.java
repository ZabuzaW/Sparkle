package de.zabuza.sparkle.wait.conditions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

/**
 * Condition that outputs whether the login pop-up is present or not.
 * 
 * @author Zabuza
 *
 */
public final class LoginPopupCondition implements ExpectedCondition<Boolean> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.common.base.Function#apply(java.lang.Object)
	 */
	@Override
	public Boolean apply(final WebDriver driver) {
		return driver.getWindowHandles().size() > 1;
	}

}
