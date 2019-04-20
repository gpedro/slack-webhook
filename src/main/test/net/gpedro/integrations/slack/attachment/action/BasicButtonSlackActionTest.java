package net.gpedro.integrations.slack.attachment.action;

import com.google.gson.JsonObject;
import org.junit.Assert;
import org.junit.Test;

public class BasicButtonSlackActionTest {
    @Test
    public void toJson() {
        BasicButtonSlackAction action = new BasicButtonSlackAction("name", "text", "url");
        action.setStyle(ButtonSlackAction.Style.DANGER);

        JsonObject json = action.toJson();
        Assert.assertEquals(json.entrySet().size(), 5);
        Assert.assertEquals(json.get("name").getAsString(), "name");
        Assert.assertEquals(json.get("text").getAsString(), "text");
        Assert.assertEquals(json.get("url").getAsString(), "url");
        Assert.assertEquals(json.get("type").getAsString(), "button");
        Assert.assertEquals(json.get("style").getAsString(), ButtonSlackAction.Style.DANGER.getCode());
    }
}