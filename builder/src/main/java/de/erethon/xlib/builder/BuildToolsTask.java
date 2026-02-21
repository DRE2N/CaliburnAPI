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

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;

/**
 *
 * @author Daniel Saukel
 */
public class BuildToolsTask implements Callable<Boolean> {

    private String jdk;
    private String revision;
    private File dir;
    private boolean verbose;

    private String prefix;

    public BuildToolsTask(String jdk, String revision, File dir, boolean verbose) {
        this.jdk = jdk;
        this.revision = revision;
        this.dir = dir;
        this.verbose = verbose;

        prefix = prefix(revision);
    }

    @Override
    public Boolean call() {
        String[] arguments = {jdk, "-jar", "BuildTools.jar", "--rev", revision, "--remapped"};
        try {
            Process process = new ProcessBuilder(arguments)
                    .directory(dir)
                    .start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = reader.readLine();
            boolean confirmed = false;
            while (line != null) {
                if (verbose) {
                    System.out.println(prefix + line);
                } else {
                    if (line.contains(revision) && !confirmed) {
                        System.out.println("Installing Spigot " + revision + "...");
                        confirmed = true;
                    } else if (line.contains("BUILD FAILURE")) {
                        System.out.println("Error: Couldn't install Spigot " + revision + " - BUILD FAILURE.");
                        break;
                    } else if (line.contains("Everything completed successfully.")) {
                        System.out.println("Successfully installed Spigot " + revision + ".");
                        return true;
                    }
                }
                line = reader.readLine();
            }
        } catch (IOException exception) {
            System.out.println("Error: Couldn't install Spigot " + revision + " - IOException.");
        }
        return false;
    }

    private String prefix(String revision) {
        StringBuilder builder = new StringBuilder("[" + revision + "]");
        while (builder.length() < 10) {
            builder.append(" ");
        }
        return builder.toString();
    }

}
