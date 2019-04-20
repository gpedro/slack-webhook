package net.gpedro.integrations.slack.attachment.action;

import com.google.gson.JsonObject;
import net.gpedro.integrations.slack.JsonSerializable;

/**
 * @author Galimov Ruslan
 * @author Desmond Vehar
 */
public abstract class SlackAction implements JsonSerializable {

	private static final String NAME = "name";
	private static final String TEXT = "text";
	private static final String TYPE = "type";

	private String name;
	private String text;
	private Type type;

	static void checkArgForNull(String argName, Object arg) {
		if (arg == null) {
			throw new IllegalArgumentException(argName + " can not be null");
		}
	}

	SlackAction(String name, String text, Type type) {
		checkArgForNull(NAME, name);
		checkArgForNull(TEXT, text);
		checkArgForNull(TYPE, type);
		this.name = name;
		this.text = text;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public JsonObject toJson() {
		final JsonObject data = new JsonObject();

		data.addProperty(NAME, name);
		data.addProperty(TEXT, text);
		data.addProperty(TYPE, type.getCode());

		return data;
	}

	public enum Type {
		BUTTON("button"), SELECT("select");

		private String code;

		Type(String code) {
			this.code = code;
		}

		public String getCode() {
			return code;
		}
	}
}
