package net.gpedro.integrations.slack;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class SlackField {

    private static final String TITLE = "title";
    private static final String VALUE = "value";
    private static final String SHORT = "short";
    private static final String MRKDWN_IN = "mrkdwn_in";
    private static final String FIELD_ALLOWS_MARKDOWN_REGEX = "^(pretext|text|title|fields|fallback)$";

    private List<String> allowMarkdown = null;
    private boolean shorten = false;
    private String title = null;
    private String value = null;

    public void addAllowedMarkdown(String field) {
        if (this.allowMarkdown == null) {
            this.allowMarkdown = new ArrayList<String>();
        }

        if (field.matches(FIELD_ALLOWS_MARKDOWN_REGEX)) {
            this.allowMarkdown.add(field);
        } else {
            throw new IllegalArgumentException(
                    field + " is not allowed. Allowed: pretext, text, title, fields and fallback");
        }
    }

    public boolean isShorten() {
        return shorten;
    }

    private JsonArray prepareMarkdown() {
        JsonArray data = new JsonArray();
        for (String item : this.allowMarkdown) {
            data.add(new JsonPrimitive(item));
        }

        return data;
    }

    public void setAllowedMarkdown(ArrayList<String> allowMarkdown) {
        if (allowMarkdown != null) {
            this.allowMarkdown = allowMarkdown;
        }
    }

    public void setShorten(boolean shorten) {
        this.shorten = shorten;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public JsonObject toJson() {
        JsonObject data = new JsonObject();
        data.addProperty(TITLE, title);
        data.addProperty(VALUE, value);
        data.addProperty(SHORT, shorten);
        if (allowMarkdown != null && allowMarkdown.size() > 0) {
            data.add(MRKDWN_IN, prepareMarkdown());
        }

        return data;
    }

}
