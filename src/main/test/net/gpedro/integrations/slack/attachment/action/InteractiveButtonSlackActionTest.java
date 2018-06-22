package net.gpedro.integrations.slack.attachment.action;

import com.google.gson.JsonObject;
import net.gpedro.integrations.slack.JsonSerializable;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Desmond Vehar
 */
public class InteractiveButtonSlackActionTest {
	@Test
	public void toJsonWithoutValue() {
		try {
			new InteractiveButtonSlackAction("name", "text", null);
			Assert.fail("value is null: an exception should have been thrown");
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("value can not be null", e.getMessage());
		}
	}

	@Test
	public void toJsonWithoutConfirm() {
		InteractiveButtonSlackAction action = new InteractiveButtonSlackAction("name", "text", "value");
		JsonObject json = action.toJson();
		Assert.assertEquals(json.entrySet().size(), 4);
		Assert.assertEquals(json.get("name").getAsString(), "name");
		Assert.assertEquals(json.get("text").getAsString(), "text");
		Assert.assertEquals(json.get("type").getAsString(), "button");
		Assert.assertEquals(json.get("value").getAsString(), "value");
	}

	@Test
	public void toJsonWithConfirm() {
		InteractiveButtonSlackAction action = new InteractiveButtonSlackAction("name", "text", "value");
		action.setConfirm(new InteractiveButtonSlackAction.Confirm("confirm_title", "confirm_text", "confirm_ok_text", "confirm_dismiss_text"));

		JsonObject json = action.toJson();
		Assert.assertEquals(json.entrySet().size(), 5);
		Assert.assertEquals(json.get("name").getAsString(), "name");
		Assert.assertEquals(json.get("text").getAsString(), "text");
		Assert.assertEquals(json.get("type").getAsString(), "button");
		Assert.assertEquals(json.get("value").getAsString(), "value");
		Assert.assertEquals(json.getAsJsonObject("confirm").get("title").getAsString(), "confirm_title");
		Assert.assertEquals(json.getAsJsonObject("confirm").get("text").getAsString(), "confirm_text");
		Assert.assertEquals(json.getAsJsonObject("confirm").get("ok_text").getAsString(), "confirm_ok_text");
		Assert.assertEquals(json.getAsJsonObject("confirm").get("dismiss_text").getAsString(), "confirm_dismiss_text");
	}
}
