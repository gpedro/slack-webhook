package net.gpedro.integrations.slack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

/**
 * Represents an attachment in a slack webhook JSON message.
 * 
 * @author Gabriel Pedro
 * @author David Webb
 *
 */
public class SlackAttachment {

    private static final String HEX_REGEX = "^([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$";
    private static final String FALLBACK = "fallback";
    private static final String TEXT = "text";
    private static final String PRETEXT = "pretext";
    private static final String COLOR = "color";
    private static final String FIELDS = "fields";
    private static final String AUTHOR_NAME = "author_name";
    private static final String AUTHOR_LINK = "author_link";
    private static final String AUTHOR_ICON = "author_icon";
    private static final String TITLE = "title";
    private static final String TITLE_LINK = "title_link";
    private static final String IMAGE_URL = "image_url";
    private static final String THUMB_URL = "thumb_url";
    private static final String MRKDWN_IN = "mrkdwn_in";

    private String fallback;
    private String text;
    private String pretext;
    private String color;
    private String authorName;
    private String authorLink;
    private String authorIcon;
    private String title;
    private String titleLink;
    private String imageUrl;
    private String thumbUrl;
    private Set<String> markdownAttributes = new HashSet<String>();
    private List<SlackField> fields = new ArrayList<SlackField>();

    public SlackAttachment addFields(SlackField field) {
	this.fields.add(field);

	return this;
    }

    public SlackAttachment addMarkdownAttribute(String attr) {
	this.markdownAttributes.add(attr);

	return this;
    }

    private boolean isHex(String pair) {
	return pair.matches(HEX_REGEX);
    }

    private JsonArray prepareFields() {
	final JsonArray data = new JsonArray();
	for (SlackField field : fields) {
	    data.add(field.toJson());
	}

	return data;
    }

    public SlackAttachment removeFields(int index) {
	this.fields.remove(index);

	return this;
    }

    private JsonArray prepareMarkdownAttributes() {
	final JsonArray data = new JsonArray();
	for (String attr : markdownAttributes) {
	    data.add(new JsonPrimitive(attr));
	}

	return data;
    }

    public SlackAttachment removeMarkdownAttribute(String attr) {
	this.markdownAttributes.remove(attr);

	return this;
    }

    public SlackAttachment setColor(String color) {
	if (color != null) {
	    if (color.charAt(0) == '#') {
		if (!isHex(color.substring(1))) {
		    throw new IllegalArgumentException("Invalid Hex Color @ SlackAttachment");
		}
	    } else if (!color.matches("^(good|warning|danger)$")) {
		throw new IllegalArgumentException("Invalid PreDefined Color @ SlackAttachment");
	    }
	}

	this.color = color;

	return this;
    }

    public SlackAttachment setFallback(String fallback) {
	this.fallback = fallback;

	return this;
    }

    public SlackAttachment setFields(List<SlackField> fields) {
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

    public SlackAttachment setAuthorName(String authorName) {
	this.authorName = authorName;

	return this;
    }

    public SlackAttachment setAuthorLink(String authorLink) {
	this.authorLink = authorLink;

	return this;
    }

    public SlackAttachment setAuthorIcon(String authorIcon) {
	this.authorIcon = authorIcon;

	return this;
    }

    public SlackAttachment setTitle(String title) {
	this.title = title;

	return this;
    }

    public SlackAttachment setTitleLink(String titleLink) {
	this.titleLink = titleLink;

	return this;
    }

    public SlackAttachment setImageUrl(String imageUrl) {
	this.imageUrl = imageUrl;

	return this;
    }

    public SlackAttachment setThumbUrl(String thumbUrl) {
	this.thumbUrl = thumbUrl;

	return this;
    }

    public JsonObject toJson() {
	JsonObject data = new JsonObject();

	if (fallback == null) {
	    throw new IllegalArgumentException("Missing Fallback @ SlackAttachment");
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

	if (authorName != null) {
	    data.addProperty(AUTHOR_NAME, authorName);
	}

	if (authorLink != null) {
	    data.addProperty(AUTHOR_LINK, authorLink);
	}

	if (authorIcon != null) {
	    data.addProperty(AUTHOR_ICON, authorIcon);
	}

	if (title != null) {
	    data.addProperty(TITLE, title);
	}

	if (titleLink != null) {
	    data.addProperty(TITLE_LINK, titleLink);
	}

	if (imageUrl != null) {
	    data.addProperty(IMAGE_URL, imageUrl);
	}

	if (thumbUrl != null) {
	    data.addProperty(THUMB_URL, thumbUrl);
	}

	if (markdownAttributes != null) {
	    data.add(MRKDWN_IN, prepareMarkdownAttributes());
	}

	if (fields != null && fields.size() > 0) {
	    data.add(FIELDS, prepareFields());
	}

	return data;
    }

    @Override
    public boolean equals(Object o) {
	if (this == o)
	    return true;
	if (o == null || getClass() != o.getClass())
	    return false;

	SlackAttachment that = (SlackAttachment) o;

	if (fallback != null ? !fallback.equals(that.fallback) : that.fallback != null)
	    return false;
	if (text != null ? !text.equals(that.text) : that.text != null)
	    return false;
	if (pretext != null ? !pretext.equals(that.pretext) : that.pretext != null)
	    return false;
	if (color != null ? !color.equals(that.color) : that.color != null)
	    return false;
	if (authorName != null ? !authorName.equals(that.authorName) : that.authorName != null)
	    return false;
	if (authorLink != null ? !authorLink.equals(that.authorLink) : that.authorLink != null)
	    return false;
	if (authorIcon != null ? !authorIcon.equals(that.authorIcon) : that.authorIcon != null)
	    return false;
	if (title != null ? !title.equals(that.title) : that.title != null)
	    return false;
	if (titleLink != null ? !titleLink.equals(that.titleLink) : that.titleLink != null)
	    return false;
	if (imageUrl != null ? !imageUrl.equals(that.imageUrl) : that.imageUrl != null)
	    return false;
	if (thumbUrl != null ? !thumbUrl.equals(that.thumbUrl) : that.thumbUrl != null)
	    return false;
	if (markdownAttributes != null ? !markdownAttributes.equals(that.markdownAttributes)
		: that.markdownAttributes != null)
	    return false;
	return !(fields != null ? !fields.equals(that.fields) : that.fields != null);

    }

    @Override
    public int hashCode() {
	int result = fallback != null ? fallback.hashCode() : 0;
	result = 31 * result + (text != null ? text.hashCode() : 0);
	result = 31 * result + (pretext != null ? pretext.hashCode() : 0);
	result = 31 * result + (color != null ? color.hashCode() : 0);
	result = 31 * result + (authorName != null ? authorName.hashCode() : 0);
	result = 31 * result + (authorLink != null ? authorLink.hashCode() : 0);
	result = 31 * result + (authorIcon != null ? authorIcon.hashCode() : 0);
	result = 31 * result + (title != null ? title.hashCode() : 0);
	result = 31 * result + (titleLink != null ? titleLink.hashCode() : 0);
	result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
	result = 31 * result + (thumbUrl != null ? thumbUrl.hashCode() : 0);
	result = 31 * result + (markdownAttributes != null ? markdownAttributes.hashCode() : 0);
	result = 31 * result + (fields != null ? fields.hashCode() : 0);
	return result;
    }

    @Override
    public String toString() {
	return "SlackAttachment{" + "fallback='" + fallback + '\'' + ", text='" + text + '\'' + ", pretext='" + pretext
		+ '\'' + ", color='" + color + '\'' + ", authorName='" + authorName + '\'' + ", authorLink='"
		+ authorLink + '\'' + ", authorIcon='" + authorIcon + '\'' + ", title='" + title + '\''
		+ ", titleLink='" + titleLink + '\'' + ", imageUrl='" + imageUrl + '\'' + ", thumbUrl='" + thumbUrl
		+ '\'' + ", markdownAttributes=" + markdownAttributes + ", fields=" + fields + '}';
    }
}
