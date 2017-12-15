package net.gpedro.integrations.slack;

import java.util.ArrayList;
import java.util.Date;
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
 * @author Galimov Ruslan
 */
public class SlackAttachment {

	private static final String HEX_REGEX = "^([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$";
	private static final String FALLBACK = "fallback";
	private static final String CALLBACK_ID = "callback_id";
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
	private static final String ACTIONS = "actions";
	private static final String TIMESTAMP = "ts";
	private static final String FOOTER = "footer";
	private static final String FOOTER_ICON = "footer_icon";
        
        private static final String COLOR_GOOD = "good";
        private static final String COLOR_WARNING = "warning";
        private static final String COLOR_DANGER = "danger";

	private String fallback;
	private String callbackId;
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
	private List<SlackAction> actions = new ArrayList<SlackAction>();
	private Long timestamp = null;
	private String footer = null;
	private String footerIcon = null;

    /**
     * Core attachment class.
     */
    public SlackAttachment() {
	}

    /**
     * Required plain-text summary of the attachment.
     * 
     * A plain-text summary of the attachment. This text will be used in clients
     * that don't show formatted text (eg. IRC, mobile notifications) and
     * should not contain any markup.
     * @param fallback
     */
    public SlackAttachment(String fallback) {
		this.fallback = fallback;
	}

    /**
     * Fields are defined as an array, and hashes contained within it will be
     * displayed in a table inside the message attachment.
     * @param field The Slack field
     * @return SlackAttachment
     */
    public SlackAttachment addFields(SlackField field) {
		this.fields.add(field);

		return this;
	}

    /**
     * Action fields
     * @param action The Slack action you want to add
     * @return SlackAttachment
     */
    public SlackAttachment addAction(SlackAction action) {
		this.actions.add(action);

		return this;
	}

    /**
     *
     * @param attr The markdwon attribute to add
     * @return SlackAttachment
     */
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

	private JsonArray prepareActions() {
		final JsonArray data = new JsonArray();
		for (SlackAction action : actions) {
			data.add(action.toJson());
		}

		return data;
	}

    /**
     *
     * @param index The index of action to remove
     * @return SlackAttachment
     */
    public SlackAttachment removeAction(int index) {
		this.actions.remove(index);

		return this;
	}

    /**
     *
     * @param index Remove fields at the given index
     * @return SlackAttachment
     */
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

    /**
     *
     * @param attr The mardwon attribute to remove
     * @return SlackAttachment
     */
    public SlackAttachment removeMarkdownAttribute(String attr) {
		this.markdownAttributes.remove(attr);

		return this;
	}

    /**
     *
     * @param color The attachment color
     * @return SlackAttachment
     * 
     * Like traffic signals, color-coding messages can quickly communicate
     * intent and help separate them from the flow of other messages in the timeline.
     * 
     * An optional value that can either be one of :
     * <ul>
     * <li>good
     * <li>warning
     * <li>danger
     * <li>any hex color code (eg. #439FE0)
     * </ul>
     * <p>
     * This value is used to color the border along the left side of the message
     * attachment.
     * 
     * @see <a href="https://api.slack.com/docs/message-attachments">https://api.slack.com/docs/message-attachments</a> for more.
     */
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

    /**
     * Set fallabck message of the attchment. Useful for terminal that have very little displays
     * like pagers, ...
     * @param fallback
     * @return SlackAttachment
     */
    public SlackAttachment setFallback(String fallback) {
		this.fallback = fallback;

		return this;
	}

    /**
     *
     * @param callbackId
     * @return SlackAttachment
     */
    public SlackAttachment setCallbackId(String callbackId) {
		this.callbackId = callbackId;

		return this;
	}

    /**
     * Setup fields of the slack attachment
     * @param fields
     * @return SlackAttachment
     */
    public SlackAttachment setFields(List<SlackField> fields) {
		this.fields = fields;

		return this;
	}

    /**
     *
     * @param pretext
     * @return
     */
    public SlackAttachment setPretext(String pretext) {
		this.pretext = pretext;

		return this;
	}

    /**
     *
     * @param text
     * @return
     */
    public SlackAttachment setText(String text) {
		this.text = text;

		return this;
	}

    /**
     *
     * @param authorName
     * @return
     */
    public SlackAttachment setAuthorName(String authorName) {
		this.authorName = authorName;

		return this;
	}

    /**
     *
     * @param authorLink
     * @return
     */
    public SlackAttachment setAuthorLink(String authorLink) {
		this.authorLink = authorLink;

		return this;
	}

    /**
     *
     * @param authorIcon
     * @return
     */
    public SlackAttachment setAuthorIcon(String authorIcon) {
		this.authorIcon = authorIcon;

		return this;
	}

    /**
     *
     * @param title
     * @return
     */
    public SlackAttachment setTitle(String title) {
		this.title = title;

		return this;
	}

    /**
     *
     * @param titleLink
     * @return
     */
    public SlackAttachment setTitleLink(String titleLink) {
		this.titleLink = titleLink;

		return this;
	}

    /**
     *
     * @param imageUrl
     * @return
     */
    public SlackAttachment setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;

		return this;
	}

    /**
     *
     * @param thumbUrl
     * @return
     */
    public SlackAttachment setThumbUrl(String thumbUrl) {
		this.thumbUrl = thumbUrl;

		return this;
	}

    /**
     *
     * @param timestamp
     * @return
     */
    public SlackAttachment setTimestamp(Long timestamp) {
		this.timestamp = timestamp;

		return this;
	}

    /**
     *
     * @param date
     * @return
     */
    public SlackAttachment setTimestamp(Date date) {
		this.timestamp = date.getTime() / 1000;

		return this;
	}

    /**
     *
     * @param footer
     * @return
     */
    public SlackAttachment setFooter(String footer) {
		this.footer = footer;

		return this;
	}

    /**
     *
     * @param footerIcon
     * @return
     */
    public SlackAttachment setFooterIcon(String footerIcon) {
		this.footerIcon = footerIcon;

		return this;
	}

    /**
     *
     * @return
     */
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

		if (actions != null && actions.size() > 0) {
			data.add(ACTIONS, prepareActions());

			if (callbackId == null) {
				throw new IllegalArgumentException("Missing Callback ID @ SlackAttachment");
			} else {
				data.addProperty(CALLBACK_ID, callbackId);
			}
		}

		if (timestamp != null) {
			data.addProperty(TIMESTAMP, timestamp);
		}

		if (footer != null) {
			data.addProperty(FOOTER, footer);
		}

		if (footerIcon != null) {
			data.addProperty(FOOTER_ICON, footerIcon);
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

		if (timestamp != null ? !timestamp.equals(that.timestamp) : that.timestamp != null)
			return false;
		if (footer != null ? !footer.equals(that.footer) : that.footer != null)
			return false;
		if (footerIcon != null ? !footerIcon.equals(that.footerIcon) : that.footerIcon != null)
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
		result = 31 * result + (footer != null ? footer.hashCode() : 0);
		result = 31 * result + (footerIcon != null ? footerIcon.hashCode() : 0);
		result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "SlackAttachment{" + "fallback='" + fallback + '\'' + ", text='" + text + '\'' + ", pretext='" + pretext
				+ '\'' + ", color='" + color + '\'' + ", authorName='" + authorName + '\'' + ", authorLink='"
				+ authorLink + '\'' + ", authorIcon='" + authorIcon + '\'' + ", title='" + title + '\''
				+ ", titleLink='" + titleLink + '\'' + ", imageUrl='" + imageUrl + '\'' + ", thumbUrl='" + thumbUrl
				+ '\'' + ", markdownAttributes=" + markdownAttributes + ", fields=" + fields + ", ts=" + timestamp
				+ ", footer=" + footer + ", footerIcon=" + footerIcon + '}';
	}
}
