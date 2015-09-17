package net.gpedro.integrations.slack;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class SlackMessage {

    private static final String CHANNEL = "channel";
    private static final String USERNAME = "username";
    private static final String HTTP = "http";
    private static final String ICON_URL = "icon_url";
    private static final String ICON_EMOJI = "icon_emoji";
    private static final String UNFURL_MEDIA = "unfurl_media";
    private static final String UNFURL_LINKS = "unfurl_links";
    private static final String TEXT = "text";
    private static final String ATTACHMENTS = "attachments";
    private List<SlackAttachment> attach = null;
    private String channel = null;
    private String icon = null;
    private JsonObject slackMessage = new JsonObject();

    private String text = null;
    private String username = null;

    private boolean unfurlMedia = false;
    private boolean unfurlLinks = false;

    public SlackMessage() {
    }

    public SlackMessage(String text) {
        this(null, null, text);
    }

    public SlackMessage(String username, String text) {
        this(null, username, text);
    }

    public SlackMessage(String channel, String username, String text) {
        if (channel != null) {
            this.channel = channel;
        }

        if (username != null) {
            this.username = username;
        }

        this.text = text;
    }

    public SlackMessage addAttachments(SlackAttachment attach) {
        if (this.attach == null) {
            this.attach = new ArrayList<SlackAttachment>();
        }
        this.attach.add(attach);

        return this;
    }

    /**
     * Convert SlackMessage to JSON
     * 
     * @return JsonObject
     */
    public JsonObject prepare() {
        if (channel != null) {
            slackMessage.addProperty(CHANNEL, channel);
        }

        if (username != null) {
            slackMessage.addProperty(USERNAME, username);
        }

        if (icon != null) {
            if (icon.contains(HTTP)) {
                slackMessage.addProperty(ICON_URL, icon);
            } else {
                slackMessage.addProperty(ICON_EMOJI, icon);
            }
        }

        slackMessage.addProperty(UNFURL_MEDIA, unfurlMedia);
        slackMessage.addProperty(UNFURL_LINKS, unfurlLinks);

        if (text == null) {
            throw new IllegalArgumentException(
                    "Missing Text field @ SlackMessage");
        } else {
            slackMessage.addProperty(TEXT, text);
        }

        if (attach != null && attach.size() > 0) {
            slackMessage.add(ATTACHMENTS, this.prepareAttach());
        }

        return slackMessage;
    }

    private JsonArray prepareAttach() {
        JsonArray attachs = new JsonArray();
        for (SlackAttachment attach : this.attach) {
            attachs.add(attach.toJson());
        }

        return attachs;
    }

    public SlackMessage removeAttachment(int index) {
        if (this.attach != null) {
            this.attach.remove(index);
        }

        return this;
    }

    public SlackMessage setAttachments(List<SlackAttachment> attach) {
        this.attach = attach;

        return this;
    }

    public SlackMessage setChannel(String channel) {
        if (channel != null) {
            this.channel = channel;
        }

        return this;
    }

    /**
     * See more icons in http://www.emoji-cheat-sheet.com/
     * 
     * @param icon
     *            Avatar
     * @return SlackMessage
     */
    public SlackMessage setIcon(String icon) {
        if (icon != null) {
            this.icon = icon;
        }

        return this;
    }

    public SlackMessage setText(String message) {
        if (message != null) {
            this.text = message;
        }

        return this;
    }

    public SlackMessage setUsername(String username) {
        if (username != null) {
            this.username = username;
        }

        return this;
    }

    public SlackMessage setUnfurlMedia(boolean unfurlMedia) {
        this.unfurlMedia = unfurlMedia;

        return this;
    }

    public SlackMessage setUnfurlLinks(boolean unfurlLinks) {
        this.unfurlLinks = unfurlLinks;

        return this;
    }

}
