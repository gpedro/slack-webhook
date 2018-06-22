package net.gpedro.integrations.slack.attachment.action;

import com.google.gson.JsonObject;

/**
 * @author Desmond Vehar
 */
public class BasicButtonSlackAction extends SlackAction {

	private static final String URL = "url";

	private String url;

	public BasicButtonSlackAction(String name, String text, String url) {
		super(name, text, Type.BUTTON);

		checkArgForNull(URL, url);
		this.url = url;
	}

	public JsonObject toJson() {
		final JsonObject data = super.toJson();

		data.addProperty(URL, url);

		return data;
	}

}
