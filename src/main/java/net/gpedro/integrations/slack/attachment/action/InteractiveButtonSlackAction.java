package net.gpedro.integrations.slack.attachment.action;

import com.google.gson.JsonObject;
import net.gpedro.integrations.slack.JsonSerializable;

/**
 * @author Desmond Vehar
 */
public class InteractiveButtonSlackAction extends ButtonSlackAction {

	private static final String VALUE = "value";
	private static final String CONFIRM = "confirm";

	private String value;
	private Confirm confirm;

	public InteractiveButtonSlackAction(String name, String text, String value) {
		super(name, text, Type.BUTTON);

		checkArgForNull(VALUE, value);
		this.value = value;
	}

	public void setConfirm(Confirm confirm) {
		this.confirm = confirm;
	}

	public JsonObject toJson() {
		final JsonObject data = super.toJson();

		data.addProperty(VALUE, value);

		if (confirm != null) {
			data.add(CONFIRM, confirm.toJson());
		}

		return data;
	}

	public static class Confirm implements JsonSerializable {
		private String title;
		private String text;
		private String ok_text;
		private String dismiss_text;

		public Confirm(String title, String text, String ok_text, String dismiss_text) {
			this.title = title;
			this.text = text;
			this.ok_text = ok_text;
			this.dismiss_text = dismiss_text;
		}

		public JsonObject toJson() {
			JsonObject data = new JsonObject();
			data.addProperty("title", title);
			data.addProperty("text", text);
			data.addProperty("ok_text", ok_text);
			data.addProperty("dismiss_text", dismiss_text);
			return data;
		}
	}
}
