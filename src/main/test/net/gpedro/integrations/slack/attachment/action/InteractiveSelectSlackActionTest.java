package net.gpedro.integrations.slack.attachment.action;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.gpedro.integrations.slack.JsonSerializable;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author Desmond Vehar
 */
public class InteractiveSelectSlackActionTest {
    @Test
    public void toJsonWithNullOptions() {
        try {
            new InteractiveSelectSlackAction("name", "text", null);
            Assert.fail("options is null: an exception should have been thrown");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("options can not be null", e.getMessage());
        }
    }

    @Test
    public void toJsonWithEmptyOptions() {
        InteractiveSelectSlackAction action = new InteractiveSelectSlackAction("name", "text");
        JsonObject json = action.toJson();
        Assert.assertEquals(json.entrySet().size(), 4);
        Assert.assertEquals(json.get("name").getAsString(), "name");
        Assert.assertEquals(json.get("text").getAsString(), "text");
        Assert.assertEquals(json.get("type").getAsString(), SlackAction.Type.SELECT.getCode());
        Assert.assertEquals(json.get("options").getAsJsonArray(), new JsonArray());
    }


    @Test
    public void toJsonWithNonEmptyOptions() {
        List<InteractiveSelectSlackAction.Option> options = Arrays.asList(
            new InteractiveSelectSlackAction.Option("text1", "value1"),
            new InteractiveSelectSlackAction.Option("text2", "value2"));
        InteractiveSelectSlackAction action = new InteractiveSelectSlackAction("name", "text", options);
        JsonObject json = action.toJson();
        Assert.assertEquals(json.entrySet().size(), 4);
        Assert.assertEquals(json.get("name").getAsString(), "name");
        Assert.assertEquals(json.get("text").getAsString(), "text");
        Assert.assertEquals(json.get("type").getAsString(), SlackAction.Type.SELECT.getCode());
        Assert.assertEquals(json.get("options").getAsJsonArray().size(), 2);
        Assert.assertEquals(json.get("options").getAsJsonArray().get(0).getAsJsonObject().get("text").getAsString(), "text1");
        Assert.assertEquals(json.get("options").getAsJsonArray().get(0).getAsJsonObject().get("value").getAsString(), "value1");
        Assert.assertEquals(json.get("options").getAsJsonArray().get(1).getAsJsonObject().get("text").getAsString(), "text2");
        Assert.assertEquals(json.get("options").getAsJsonArray().get(1).getAsJsonObject().get("value").getAsString(), "value2");
    }
}
