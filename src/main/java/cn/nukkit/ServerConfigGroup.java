package cn.nukkit;

import cn.nukkit.utils.Config;

import java.util.HashMap;

public final class ServerConfigGroup {

  private Config nukkitYml, rootiYml;
  private Config serverProperties;
  private HashMap<String, Object> propertyCache = new HashMap<>();

  public ServerConfigGroup(Config nukkitYml, Config rootiYml, Config serverProperties) {
    this.nukkitYml = nukkitYml;
    this.rootiYml = rootiYml;
    this.serverProperties = serverProperties;
  }

  public Object getAdvancedProperty(String variable) {
    return this.getAdvancedProperty(variable, null);
  }

  public Object getAdvancedProperty(String variable, Object defaultValue) {
    if (!propertyCache.containsKey(variable)) {
      Object value = this.rootiYml.get(variable);
      propertyCache.put(variable, value == null ? defaultValue : value);
    }
    return propertyCache.get(variable);
  }

  public Config getProperty() {
    return nukkitYml;
  }

  public Object getProperty(String variable) {
    return this.getProperty(variable, null);
  }

  public Object getProperty(String variable, Object defaultValue) {
    if (!propertyCache.containsKey(variable)) {
      Object value = this.nukkitYml.get(variable);
      propertyCache.put(variable, value == null ? defaultValue : value);
    }
    return propertyCache.get(variable);
  }

  public Boolean getPropertyBoolean(String variable, Object defaultValue) {
    return (boolean) this.getProperty(variable, defaultValue);
  }

  public String getPropertyString(String variable, Object defaultValue) {
    return (String) this.getProperty(variable, defaultValue);
  }

  public int getPropertyInt(String variable, Object defaultValue) {
    return (int) this.getProperty(variable, defaultValue);
  }

  public Object getConfig(String variable) {
    return this.getConfig(variable, null);
  }

  public Object getConfig(String variable, Object defaultValue) {
    if (!propertyCache.containsKey(variable)) {
      Object value = this.serverProperties.get(variable);
      propertyCache.put(variable, value == null ? defaultValue : value);
    }
    return propertyCache.get(variable);
  }

  public void setConfigString(String variable, String value) {
    serverProperties.set(variable, value);
    serverProperties.save();
    propertyCache.put(variable, value);
  }

  public String getConfigString(String variable) {
    return this.getConfigString(variable, null);
  }

  public String getConfigString(String variable, String defaultValue) {
    return (String) this.getConfig(variable, defaultValue);
  }

  public int getConfigInt(String variable) {
    return this.getPropertyInt(variable, null);
  }

  public int getConfigInt(String variable, Integer defaultValue) {
    Object value = this.getConfig(variable, defaultValue);
    return value.equals("") ? Integer.parseInt(String.valueOf(value)) : defaultValue;
  }

  public void setConfigInt(String variable, int value) {
    this.serverProperties.set(variable, value);
    this.serverProperties.save();
    propertyCache.put(variable, value);
  }

  public boolean getConfigBoolean(String variable) {
    return this.getConfigBoolean(variable, null);
  }

  public boolean getConfigBoolean(String variable, Object defaultValue) {
    Object value = this.serverProperties.exists(variable) ? this.serverProperties.get(variable) : defaultValue;
    if (value instanceof Boolean) {
        return (Boolean) value;
    }
    switch (String.valueOf(value)) {
        case "on":
        case "true":
        case "1":
        case "yes":
            return true;
    }
    return false;
  }

  public void setConfigBoolean(String variable, boolean value) {
    this.serverProperties.set(variable, value ? "1" : "0");
    this.serverProperties.save();
  }

  public void reloadConfig() {
    serverProperties.reload();
  }

  public void saveAll() {
    serverProperties.save();
    rootiYml.save();
    nukkitYml.save();
  }
}
