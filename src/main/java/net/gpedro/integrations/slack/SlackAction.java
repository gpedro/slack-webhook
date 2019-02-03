package net.gpedro.integrations.slack;

import com.google.gson.JsonObject;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Galimov Ruslan
 */
@Data
@Accessors(chain = true)
public class SlackAction {

	private static final String NAME = "name";
	private static final String TEXT = "text";
	private static final String TYPE = "type";
	private static final String VALUE = "value";
	private static final String CONFIRM = "confirm";
	private static final String STYLE = "style";
	private static final String OPTIONS = "options";
	private static final String OPTION_GROUPS = "option_groups";
	private static final String DATA_SOURCE = "data_source";
	private static final String SELECTED_OPTIONS = "selected_options";
	private static final String MIN_QUERY_LENGTH = "min_query_length";

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
