package net.gpedro.integrations.slack;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class SlackAttachment {

    private String fallback;
    private String text;
    private String pretext;
    private String color;
    private List<SlackField> fields;

    public SlackAttachment addFields(SlackField field) {
        if (this.fields == null) {
            this.fields = new ArrayList<SlackField>();
        }

        this.fields.add(field);

        return this;
    }

    private boolean isHex(String pair) {
        return pair.matches("^([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$");
    }

    private JsonArray prepareFields() {
        JsonArray data = new JsonArray();
        for (SlackField field : fields) {
            data.add(field.toJson());
        }

        return data;
    }

    public SlackAttachment removeFields(Integer index) {
        if (this.fields != null) {
            this.fields.remove(index);
        }

        return this;
    }

    public SlackAttachment setColor(String color) {
        if (color != null) {
            if (color.charAt(0) == '#') {
                if (!isHex(color.substring(1))) {
                    throw new IllegalArgumentException(
                            "Invalid Hex Color @ SlackAttachment");
                }
            } else
                if (!color.matches("^(good|warning|danger)$")) {
                    throw new IllegalArgumentException(
                            "Invalid PreDefined Color @ SlackAttachment");
                }
        }

        this.color = color;

        return this;
    }

    public SlackAttachment setFallback(String fallback) {
        this.fallback = fallback;

        return this;
    }

    public SlackAttachment setFields(ArrayList<SlackField> fields) {
        this.fields = fields;

        return this;
    }

    public SlackAttachment setPretext(String pretext) {
        this.pretext = pretext;

        return this;
    }

    public SlackAttachment setText(String text) {
        this.text = text;

        return this;
    }

    public JsonObject toJson() {
        JsonObject data = new JsonObject();

        if (fallback == null) {
            throw new IllegalArgumentException(
                    "Missing Fallback @ SlackAttachment");
        } else {
            data.addProperty("fallback", fallback);
        }

        if (text != null) {
            data.addProperty("text", text);
        }

        if (pretext != null) {
            data.addProperty("pretext", pretext);
        }

        if (color != null) {
            data.addProperty("color", color);
        }

        if (fields != null && fields.size() > 0) {
            data.add("fields", prepareFields());
        }

        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SlackAttachment that = (SlackAttachment) o;

        if (fallback != null ? !fallback.equals(that.fallback) : that.fallback != null) return false;
        if (text != null ? !text.equals(that.text) : that.text != null) return false;
        if (pretext != null ? !pretext.equals(that.pretext) : that.pretext != null) return false;
        if (color != null ? !color.equals(that.color) : that.color != null) return false;
        return !(fields != null ? !fields.equals(that.fields) : that.fields != null);

    }

    @Override
    public int hashCode() {
        int result = fallback != null ? fallback.hashCode() : 0;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (pretext != null ? pretext.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (fields != null ? fields.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SlackAttachment{" +
                "fallback='" + fallback + '\'' +
                ", text='" + text + '\'' +
                ", pretext='" + pretext + '\'' +
                ", color='" + color + '\'' +
                ", fields=" + fields +
                '}';
    }
}
