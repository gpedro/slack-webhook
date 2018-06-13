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
	private static final String URL = "url";
	private static final String STYLE = "style";

	private String name;
	private String text;
	private SlackActionType type;
	private String url;
	private SlackActionStyle style;

	public SlackAction(String name, String text, SlackActionType type, String url) {
		this.name = name;
		this.text = text;
		this.type = type;
		this.url = url;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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
		data.addProperty(URL, url);

		if (type != null) {
			data.addProperty(TYPE, type.getCode());
		}

		if (style != null) {
			data.addProperty(STYLE, style.getCode());
		}

		return data;
	}
}
