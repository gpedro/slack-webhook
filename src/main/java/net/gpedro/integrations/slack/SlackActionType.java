package net.gpedro.integrations.slack;

/**
 * @author Galimov Ruslan
 */
public enum SlackActionType {

    /**
     * You can add simple click buttons actions to your message.
     * 
     * See https://api.slack.com/docs/message-buttons for more
     */
    BUTTON("button"),

    /**
     * Instead of buttons, users encounter drop downs, each containing a series
     * of options: perhaps a list of fellow members, a list of Slack channels,
     * or a simple list of actions you provide while creating the message.
     * Message menus can even be dynamically populated with options based
     * on your server's response.
     * 
     * See https://api.slack.com/docs/message-menus for more.
     */
    SELECT("select");

	private String code;

	SlackActionType(String code) {
		this.code = code;
	}

    /**
     * Get the code of the action type. They typpically are
     * <ul>
     * <li>button
     * <li>select
     * </ul>
     * @return code
     */
    public String getCode() {
		return code;
	}

}
