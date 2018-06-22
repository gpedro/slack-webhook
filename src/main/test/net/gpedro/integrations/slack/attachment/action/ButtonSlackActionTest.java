package net.gpedro.integrations.slack.attachment.action;

import com.google.gson.JsonObject;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Desmond Vehar
 */
public class ButtonSlackActionTest {
	@Test
	public void toJsonWithStyle() {
		ButtonSlackAction action = new ButtonSlackAction("name", "text", SlackAction.Type.BUTTON) {};
		action.setStyle(ButtonSlackAction.Style.PRIMARY);

		JsonObject json = action.toJson();
		Assert.assertEquals(json.entrySet().size(), 4);
		Assert.assertEquals(json.get("name").getAsString(), "name");
		Assert.assertEquals(json.get("text").getAsString(), "text");
		Assert.assertEquals(json.get("type").getAsString(), "button");
		Assert.assertEquals(json.get("style").getAsString(), ButtonSlackAction.Style.PRIMARY.getCode());
	}

	@Test
	public void toJsonWithoutStyle() {
		ButtonSlackAction action = new ButtonSlackAction("name", "text", SlackAction.Type.BUTTON) {};

		JsonObject json = action.toJson();
		Assert.assertEquals(json.entrySet().size(), 3);
		Assert.assertEquals(json.get("name").getAsString(), "name");
		Assert.assertEquals(json.get("text").getAsString(), "text");
		Assert.assertEquals(json.get("type").getAsString(), "button");
	}
}
