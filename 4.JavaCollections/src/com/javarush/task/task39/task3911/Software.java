package com.javarush.task.task39.task3911;

import java.util.*;

public class Software {
    int currentVersion;

    private Map<Integer, String> versionHistoryMap = new LinkedHashMap<>();

    public void addNewVersion(int version, String description) {
        if (version > currentVersion) {
            versionHistoryMap.put(version, description);
            currentVersion = version;
        }
    }

    public int getCurrentVersion() {
        return currentVersion;
    }

    public Map<Integer, String> getVersionHistoryMap() {
        return Collections.unmodifiableMap(versionHistoryMap);
    }

    public boolean rollback(int rollbackVersion) {
        String description = versionHistoryMap.get(rollbackVersion);
        if (description == null) {
            return false;
        }
        Map<Integer, String> newVersionHistoryMap = new LinkedHashMap<>();
        for (Map.Entry<Integer, String> entry : versionHistoryMap.entrySet()) {
            if (entry.getKey() <= rollbackVersion) {
                newVersionHistoryMap.put(entry.getKey(), entry.getValue());
            }
        }
        versionHistoryMap = newVersionHistoryMap;
        currentVersion = rollbackVersion;
        return true;
    }
}
