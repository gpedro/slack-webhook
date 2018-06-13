package net.gpedro.integrations.slack;

import com.google.gson.JsonObject;

/**
 * @author Galimov Ruslan
 * @author Desmond Vehar
 */
public class SlackAction {

	private static final String NAME = "name";
	private static final String TEXT = "text";
	private static final String TYPE = "type";
	private static final String VALUE = "value";
	private static final String STYLE = "style";

	private String name;
	private String text;
	private SlackActionType type;
	private String value;
	private SlackActionStyle style;

	public SlackAction(String name, String text, SlackActionType type, String value) {
		this.name = name;
		this.text = text;
		this.type = type;
		this.value = value;
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

	public SlackActionType getType() {
		return type;
	}

	public void setType(SlackActionType type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public SlackActionStyle getStyle() {
		return style;
	}

	public void setStyle(SlackActionStyle style) {
		this.style = style;
	}

	public JsonObject toJson() {
		final JsonObject data = new JsonObject();
		data.addProperty(NAME, name);
		data.addProperty(TEXT, text);

		if (type != null) {
			data.addProperty(TYPE, type.getCode());
		}

		data.addProperty(VALUE, value);

		if (style != null) {
			data.addProperty(STYLE, style.getCode());
		}

		return data;
	}
}
