/*
 * Copyright (C) 2015-2017 Daniel Saukel
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.dre2n.caliburn.util;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.file.YamlConstructor;
import org.bukkit.configuration.file.YamlRepresenter;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.error.YAMLException;
import org.yaml.snakeyaml.representer.Representer;

/**
 * @author Daniel Saukel
 */
public class CaliConfiguration extends YamlConfiguration {

    DumperOptions yamlOptions = new DumperOptions();
    Representer yamlRepresenter = new YamlRepresenter();
    Yaml yaml = new Yaml(new YamlConstructor(), yamlRepresenter, yamlOptions);

    private Map args = new LinkedHashMap<>();

    @Override
    public void loadFromString(String contents) throws InvalidConfigurationException {
        Map input;
        try {
            input = (Map) yaml.load(contents);
        } catch (YAMLException exception) {
            throw new InvalidConfigurationException(exception);
        } catch (ClassCastException exception) {
            throw new InvalidConfigurationException("Top level is not a Map.");
        }

        String header = parseHeader(contents);
        if (header.length() > 0) {
            options().header(header);
        }

        if (input != null) {
            args = input;
            convertMapsToSections(input, this);
        }
    }

    public static CaliConfiguration loadConfiguration(File file) {
        CaliConfiguration config = new CaliConfiguration();

        try {
            config.load(file);
        } catch (IOException | InvalidConfigurationException exception) {
            exception.printStackTrace();
        }

        return config;
    }

    /**
     * @return
     * the pure YAML Map.
     */
    public Map getArgs() {
        return args;
    }

}
