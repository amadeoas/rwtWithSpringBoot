package uk.co.bocaditos.rwtwithspringboot.config;

import org.eclipse.rap.rwt.RWT;


/**
 * .
 */
public class Messages {

	private static final String BUNDLE_NAME = "messages";

	public String title;
	public String login;
	public String logout;
	
	public String logTimeout;

	public String cardBtnTitle;

	public String homeTitle;
	public String homeDescription;

	public String firstViewTitle;
	public String firstViewDescription;

	public String secondViewTitle;
	public String secondViewDescription;

	public String thirdViewTitle;
	public String thirdViewDescription;

	public String aboutTitle;
	public String aboutDescription;


	public static Messages get() {
	    return (Messages) RWT.NLS.getISO8859_1Encoded( BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	    // prevent instantiation
	}

} // end class Messages
