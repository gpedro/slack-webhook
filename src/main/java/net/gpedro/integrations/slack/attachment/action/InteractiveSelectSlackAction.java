package net.gpedro.integrations.slack.attachment.action;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.gpedro.integrations.slack.JsonSerializable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Desmond Vehar
 */
public class InteractiveSelectSlackAction extends SlackAction {

	private static final String OPTIONS = "options";

	private List<Option> options;

	public InteractiveSelectSlackAction(String name, String text) {
		super(name, text, Type.SELECT);

		this.options = new ArrayList<Option>();
	}

	public InteractiveSelectSlackAction(String name, String text, List<Option> options) {
		super(name, text, Type.SELECT);

		checkArgForNull(OPTIONS, options);
		this.options = options;
	}

	public JsonObject toJson() {
		final JsonObject data = super.toJson();

		JsonArray array = new JsonArray();
		for (Option option : options) {
			JsonObject obj = new JsonObject();
			obj.addProperty("text", option.text);
			obj.addProperty("value", option.value);
			array.add(obj);
		}
		data.add(OPTIONS, array);

		return data;
	}

	public static class Option implements JsonSerializable {
		private String text;
		private String value;

		public Option(String text, String value) {
			this.text = text;
			this.value = value;
		}

		public JsonObject toJson() {
			return new JsonObject();
		}
	}
}
