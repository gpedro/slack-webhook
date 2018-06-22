package net.gpedro.integrations.slack.attachment.action;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.gpedro.integrations.slack.JsonSerializable;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Desmond Vehar
 */
public class SlackActionTest {
    @Test
    public void nullName() {
        try {
            new SlackAction(null, "text", SlackAction.Type.BUTTON) { };
            Assert.fail("name is null: an exception should have been thrown");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("name can not be null", e.getMessage());
        }
    }

    @Test
    public void nullText() {
        try {
            new SlackAction("name", null, SlackAction.Type.BUTTON) { };
            Assert.fail("text is null: an exception should have been thrown");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("text can not be null", e.getMessage());
        }
    }


    @Test
    public void nullType() {
        try {
            new SlackAction("name", "text", null) { };
            Assert.fail("type is null: an exception should have been thrown");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("type can not be null", e.getMessage());
        }
    }

    @Test
    public void toJson() {
        SlackAction action = new SlackAction("name", "text", SlackAction.Type.SELECT) { };
        JsonObject json = action.toJson();
        Assert.assertEquals(json.entrySet().size(), 3);
        Assert.assertEquals(json.get("name").getAsString(), "name");
        Assert.assertEquals(json.get("text").getAsString(), "text");
        Assert.assertEquals(json.get("type").getAsString(), SlackAction.Type.SELECT.getCode());
    }
}
