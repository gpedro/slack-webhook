slack-webhook
=============

Slack WebHook Integration for Java

# Basic Examples

```java

// Send simple message
SlackApi api = new SlackApi("https://hooks.slack.com/services/id_1/id_2/token");
api.call(new SlackMessage("my message"));

// Send simple message with custom name
SlackApi api = new SlackApi("https://hooks.slack.com/services/id_1/id_2/token");
api.call(new SlackMessage("Mafagafo", "my message"));

// Send simple message in different room
SlackApi api = new SlackApi("https://hooks.slack.com/services/id_1/id_2/token");
api.call(new SlackMessage("#general", null, "my message"));

// Send simple message in different room with custom name
SlackApi api = new SlackApi("https://hooks.slack.com/services/id_1/id_2/token");
api.call(new SlackMessage("#general", "Mafagafo", "my message"));

// Send simple message in different room with custom name and tag someone in the message so he will get notified
SlackApi api = new SlackApi("https://hooks.slack.com/services/id_1/id_2/token");
api.call(new SlackMessage("#general", "Mafagafo", "Hi @gpedro : your API rocks").setLinkNames(true));


```

# Installation
Add the following dependency in __pom.xml__
```xml
<dependency>
  <groupId>net.gpedro.integrations.slack</groupId>
  <artifactId>slack-webhook</artifactId>
  <version>1.4.0</version>
</dependency>
```

# Configuration

1. Go to *your_team.slack.com/services/new*
2. Search for *Incoming WebHook* and click in `Add`
3. Choose Channel to Post and press `Add Incoming WebHooks Integration`
4. Into *Setup Instructions*, you've a WebHook URL. He is the argument [you must pass the constructor](/src/main/java/net/gpedro/integrations/slack/SlackApi.java#L18). Then, copy it.

# Change Log
* 1.4.0
  - Added `footer`, `footer-icon`, `ts` (#24) and `link_names` (#25) attributes
* 1.3.0
  - Added Slack Actions (thanks @galimru)
* 1.2.1
  - Improved MatterMost Compatibility
* 1.2.0
  - Added Support for Proxy
* 1.1.2
  - Added Support for Markdown in SlackAttachment
* 1.1.1
  - General Improvements
* 1.0.0
  - Init (:
