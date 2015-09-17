package net.gpedro.integrations.slack;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.ArrayList;
import java.util.List;

public class SlackField {

    private List<String> allowMarkdown = null;
    private boolean shorten = false;
    private String title = null;
    private String value = null;

    public void addAllowedMarkdown(String field) {
        if (this.allowMarkdown == null) {
            this.allowMarkdown = new ArrayList<String>();
        }

        if (field.matches("^(pretext|text|title|fields|fallback)$")) {
            this.allowMarkdown.add(field);
        } else {
            throw new IllegalArgumentException(
                    field
                            + " is not allowed. Allowed: pretext, text, title, fields and fallback");
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
        data.addProperty("title", title);
        data.addProperty("value", value);
        data.addProperty("short", shorten);
        if (allowMarkdown != null && allowMarkdown.size() > 0) {
            data.add("mrkdwn_in", prepareMarkdown());
        }

        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final SlackField that = (SlackField) o;

        if (shorten != that.shorten) return false;
        if (allowMarkdown != null ? !allowMarkdown.equals(that.allowMarkdown) : that.allowMarkdown != null)
            return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        return !(value != null ? !value.equals(that.value) : that.value != null);

    }

    @Override
    public int hashCode() {
        int result = allowMarkdown != null ? allowMarkdown.hashCode() : 0;
        result = 31 * result + (shorten ? 1 : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SlackField{" +
                "allowMarkdown=" + allowMarkdown +
                ", shorten=" + shorten +
                ", title='" + title + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
