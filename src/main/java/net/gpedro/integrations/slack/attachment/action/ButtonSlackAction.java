package net.gpedro.integrations.slack.attachment.action;

import com.google.gson.JsonObject;
import net.gpedro.integrations.slack.JsonSerializable;

/**
 * @author Desmond Vehar
 */
public abstract class ButtonSlackAction extends SlackAction {
	private static final String STYLE = "style";

	private Style style;

	ButtonSlackAction(String name, String text, Type type) {
		super(name, text, type);
	}

	public void setStyle(Style style) {
		this.style = style;
	}

	public JsonObject toJson() {
		final JsonObject data = super.toJson();

		if (style != null) {
			data.addProperty(STYLE, style.getCode());
		}

		return data;
	}

	public enum Style {
		DEFAULT("default"), PRIMARY("primary"), DANGER("danger");

		private String code;

		Style(String code) {
			this.code = code;
		}

		public String getCode() {
			return code;
		}
	}
}
