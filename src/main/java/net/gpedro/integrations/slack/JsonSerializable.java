package net.gpedro.integrations.slack;

import com.google.gson.JsonObject;

public interface JsonSerializable {
    JsonObject toJson();
}
