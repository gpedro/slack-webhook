slack-webhook
=============

Slack WebHook Integration for Java

# Basic Examples

```java

// Send simple message
SlackApi api = new SlackApi("my team", "my token");
api.call(new SlackMessage("my message"));

// Send simple message with custom name
SlackApi api = new SlackApi("my team", "my token");
api.call(new SlackMessage("Mafagafo", "my message"));

// Send simple message in different room
SlackApi api = new SlackApi("my team", "my token");
api.call(new SlackMessage("#general", null, "my message"));

// Send simple message in different room with custom name
SlackApi api = new SlackApi("my team", "my token");
api.call(new SlackMessage("#general", "Mafagafo", "my message"));

```
