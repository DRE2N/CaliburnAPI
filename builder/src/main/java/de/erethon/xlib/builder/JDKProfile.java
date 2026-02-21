/*
 * Copyright (C) 2026 Daniel Saukel
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
package de.erethon.xlib.builder;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Daniel Saukel
 */
public class JDKProfile {

    private int version;
    private Path path;
    private String[] revisions;

    private JDKProfile() {
    }

    public static JDKProfile fromString(String string) {
        JDKProfile profile = new JDKProfile();

        String[] verBody = string.split("=");
        if (verBody.length != 2) {
            return null;
        }
        try {
            profile.version = Integer.parseInt(verBody[0]);
        } catch (NumberFormatException exception) {
            return null;
        }

        String[] pathRevs = verBody[1].split("\\|");
        if (pathRevs.length != 2) {
            return null;
        }
        profile.path = Paths.get(pathRevs[0]);

        profile.revisions = pathRevs[1].split(",");
        for (String rev : profile.revisions) {
            if (!rev.matches("[0-9][0-9]?\\.[0-9][0-9]?(\\.[0-9][0-9]?)?")) {
                return null;
            }
        }
        return profile;
    }

    public int getVersion() {
        return version;
    }

    public Path getPath() {
        return path;
    }

    public String[] getRevisions() {
        return revisions;
    }
}
