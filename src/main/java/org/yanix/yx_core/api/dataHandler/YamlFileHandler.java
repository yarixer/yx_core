package org.yanix.yx_core.api.dataHandler;

import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class YamlFileHandler {
    private final File pluginFolder;
    private final Yaml yaml;
    private final Logger logger = Logger.getLogger(YamlFileHandler.class.getName());

    public YamlFileHandler(File pluginFolder) {
        this.pluginFolder = pluginFolder;
        this.yaml = new Yaml();

        if (!pluginFolder.exists() && pluginFolder.mkdirs()) {
            logger.info("Plugin folder created: " + pluginFolder.getAbsolutePath());
        }
    }

    public synchronized <T> Optional<T> readSection(String fileName, String section, Class<T> type) {
        File file = new File(pluginFolder, fileName);
        Map<String, Object> data = loadAll(file);

        if (!data.containsKey(section)) {
            logger.warning("Section '" + section + "' does not exist in file: " + fileName);
            return Optional.empty();
        }

        Object rawData = data.get(section);
        if (!type.isInstance(rawData)) {
            logger.warning("Section '" + section + "' in file '" + fileName + "' is not of expected type: " + type.getSimpleName());
            return Optional.empty();
        }

        return Optional.of(type.cast(rawData));
    }

    public synchronized <T> void writeSection(String fileName, String section, T value) {
        File file = new File(pluginFolder, fileName);
        Map<String, Object> data = loadAll(file);

        data.put(section, value);
        saveAll(file, data);
        logger.info("Section '" + section + "' successfully written to file: " + fileName);
    }

    public synchronized void deleteSection(String fileName, String section, String keyToDelete) {
        File file = new File(pluginFolder, fileName);
        Map<String, Object> data = loadAll(file);

        if (!data.containsKey(section)) {
            logger.warning("Section '" + section + "' does not exist in file: " + fileName);
            return;
        }

        Object sectionData = data.get(section);

        if (keyToDelete == null) {
            data.remove(section);
            logger.info("Section '" + section + "' successfully deleted from file: " + fileName);
        } else if (sectionData instanceof Map<?, ?>) {
            Map<String, Object> sectionMap = (Map<String, Object>) sectionData;
            if (sectionMap.containsKey(keyToDelete)) {
                sectionMap.remove(keyToDelete);
                logger.info("Key '" + keyToDelete + "' successfully deleted from section '" + section + "' in file: " + fileName);
            } else {
                logger.warning("Key '" + keyToDelete + "' does not exist in section '" + section + "' in file: " + fileName);
            }
        } else {
            logger.warning("Section '" + section + "' is not a valid map in file: " + fileName);
        }

        saveAll(file, data);
    }

    private synchronized Map<String, Object> loadAll(File file) {
        if (!file.exists()) {
            return new HashMap<>();
        }
        try (FileInputStream inputStream = new FileInputStream(file)) {
            Map<String, Object> data = yaml.load(inputStream);
            return data != null ? data : new HashMap<>();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading YAML file: " + file.getAbsolutePath(), e);
            return new HashMap<>();
        }
    }

    private synchronized void saveAll(File file, Map<String, Object> data) {
        try (FileWriter writer = new FileWriter(file)) {
            yaml.dump(data, writer);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error writing to YAML file: " + file.getAbsolutePath(), e);
        }
    }
}
