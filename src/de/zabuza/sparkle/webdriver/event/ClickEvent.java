package de.zabuza.sparkle.webdriver.event;

import org.openqa.selenium.WebElement;

/**
 * Delayable event that performs a click on a given web element. See also
 * {@link WebElement#click()}.
 * 
 * @author Zabuza {@literal <zabuza.dev@gmail.com>}
 *
 */
public final class ClickEvent implements IDelayableEvent {

	/**
	 * Web element to click, should not be an instance of
	 * {@link de.zabuza.sparkle.webdriver.DelayedWebElement DelayedWebElement}.
	 */
	private final WebElement mElement;

	/**
	 * Creates a new instance of this object with a given web element.
	 * 
	 * @param element
	 *            Element to click, should not be an instance of
	 *            {@link de.zabuza.sparkle.webdriver.DelayedWebElement
	 *            DelayedWebElement}.
	 */
	public ClickEvent(final WebElement element) {
		this.mElement = element;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.zabuza.sparkle.webdriver.event.IDelayableEvent#execute()
	 */
	@Override
	public void execute() {
		this.mElement.click();
	}

}
