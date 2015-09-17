package net.gpedro.integrations.slack;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class SlackAttachment {

    private static final String HEX_REGEX = "^([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$";
    private static final String FALLBACK = "fallback";
    private static final String TEXT = "text";
    private static final String PRETEXT = "pretext";
    private static final String COLOR = "color";
    private static final String FIELDS = "fields";

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
        return pair.matches(HEX_REGEX);
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
            data.addProperty(FALLBACK, fallback);
        }

        if (text != null) {
            data.addProperty(TEXT, text);
        }

        if (pretext != null) {
            data.addProperty(PRETEXT, pretext);
        }

        if (color != null) {
            data.addProperty(COLOR, color);
        }

        if (fields != null && fields.size() > 0) {
            data.add(FIELDS, prepareFields());
        }

        return data;
    }

}
