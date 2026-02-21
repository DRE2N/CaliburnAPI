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
import de.erethon.xlib.builder.BuildToolsTask;
import de.erethon.xlib.builder.JDKProfile;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


final String PROJECT_NAME = "XLib";
final String BUILDTOOLS_URL = "https://hub.spigotmc.org/jenkins/job/BuildTools/lastSuccessfulBuild/artifact/target/BuildTools.jar";
final String BUILDTOOLS_DIR = "BuildTools";
final String MVN_CHECK_URL = "https://dlcdn.apache.org/maven/maven-3";
final String MVN_URL = "https://dlcdn.apache.org/maven/maven-%major/%v/binaries/apache-maven-%v-bin.zip";
final String GOAL_INSTALL = "install";
final String GOAL_PACKAGE = "package";

List<JDKProfile> jdkProfiles = new ArrayList<>();
String goal = "install";
String mvnVersion;
List<String> revisions = new ArrayList<>();
int threads = -1;
boolean verbose = false;

void main(String[] args) {
    if (!parseArgs(args)) {
        return;
    }

    // BUILDTOOLS
    loadProfiles();
    if (!checkJDKs()) {
        return;
    }
    File buildTools;
    try {
        buildTools = downloadBuildTools();
    } catch(IOException exception) {
        System.out.println("Error: Could not download BuildTools.");
        return;
    }
    if (!installServers(buildTools)) {
        return;
    }

    // MAVEN
    if (mvnVersion == null) {
        try {
            mvnVersion = getLatestMvn();
        } catch (IOException exception) {
            System.out.println("Error: Could not fetch latest Maven version.");
            return;
        }
    }
    String bin = "apache-maven-" + mvnVersion + File.separatorChar + "bin";
    if (!new File(bin).exists()) {
        try {
            downloadMvn(mvnVersion);
        } catch (Exception exception) {
            System.out.println("Could not download Maven.");
            return;
        }
    }
    if (!new File("pom.xml").exists()) {
        System.out.println("No project to build found.");
        return;
    }
    try {
        mvnBuild(bin);
    } catch (IOException exception) {
        System.out.println("Error: Couldn't install " + PROJECT_NAME + " - IOException.");
    }
}

boolean parseArgs(String[] args) {
    for (int i = 0; i < args.length; i++) {
        if (args[i].equalsIgnoreCase("--clean") || args[i].equalsIgnoreCase("-c")) {
            delete(new File(BUILDTOOLS_DIR));
            deleteTargets(new File(""));
            return false;
        } else if (args[i].equalsIgnoreCase("--goal") || args[i].equalsIgnoreCase("-g")) {
            if (args.length <= i + 1) {
                continue;
            }
            if (args[i + 1].equalsIgnoreCase(GOAL_PACKAGE) || args[i + 1].equalsIgnoreCase(GOAL_INSTALL)) {
                goal = args[i + 1];
            }
        } else if (args[i].equalsIgnoreCase("--mvnver") || args[i].equalsIgnoreCase("-mv")) {
            if (args.length > i + 1) {
                mvnVersion = args[i + 1];
            }
        } else if (args[i].equalsIgnoreCase("--revision") || args[i].equalsIgnoreCase("--rev") || args[i].equalsIgnoreCase("-r")) {
            revisions.add(args[i + 1]);
        } else if (args[i].equalsIgnoreCase("--threads") || args[i].equalsIgnoreCase("-t")) {
            try {
                threads = Integer.parseInt(args[i + 1]);
            } catch (NumberFormatException exception) {
                System.out.println("Error: " + args[i + 1] + "is not a number.");
                return false;
            }
        } else if (args[i].equalsIgnoreCase("--verbose") || args[i].equalsIgnoreCase("-v")) {
            verbose = true;
        }
    }
    return true;
}

void loadProfiles() {
    InputStream stream = MethodHandles.lookup().lookupClass().getResourceAsStream("/build.cfg");
    BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
    jdkProfiles = new ArrayList<>();
    reader.lines().forEach(l -> {
        JDKProfile profile = JDKProfile.fromString(l);
        if (profile != null) {
            jdkProfiles.add(profile);
        }
    });
    System.out.println("JDK profiles loaded.");
}

boolean checkJDKs() {
    for (JDKProfile profile : jdkProfiles) {
        if (Files.isExecutable(profile.getPath())) {
            continue;
        }
        System.out.println("Error: JDK not found! Install JDK " + profile.getVersion() + " from https://www.azul.com/downloads/?package=jdk#zulu");
        System.out.println("If it is already installed, change the value in builder.jar/build.cfg to the correct path.");
        return false;
    }
    System.out.println("JDKs found.");
    return true;
}

File downloadBuildTools() throws IOException {
    System.out.println("Downloading BuildTools...");
    URL url = new URL(BUILDTOOLS_URL);
    ReadableByteChannel readableByteChannel = Channels.newChannel(url.openStream());
    File file = new File("BuildTools.jar");
    FileOutputStream fileOutputStream = new FileOutputStream(file);
    fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
    fileOutputStream.close();
    System.out.println("BuildTools downloaded.");
    return file;
}

boolean installServers(File buildTools) {
    System.out.println("Installing Spigot servers to local repository...");
    Path btDir = Path.of(BUILDTOOLS_DIR);
    try {
        Files.createDirectory(btDir);
    } catch (IOException exception) {
        System.out.println("Error: Couldn't create installation environment.");
        return false;
    }

    int i  = setupDirs(buildTools.toPath(), btDir);
    if (i == Integer.MIN_VALUE) {
        return false;
    }
    if (threads == -1 || threads > i) {
        threads = i;
    }
    buildTools.delete();

    Collection<Future<Boolean>> tasks = setupTasks();
    return watchdog(tasks);
}

int setupDirs(Path buildTools, Path btDir) {
    int i = 0;
    for (JDKProfile profile : jdkProfiles) {
        for (String revision : profile.getRevisions()) {
            Path dir = btDir.resolve(revision);
            try {
                Files.createDirectory(dir);
                Files.copy(buildTools, dir.resolve("BuildTools.jar"));
            } catch (IOException exception) {
                System.out.println("Error: Couldn't create installation environment for Spigot " + revision + ".");
                return Integer.MIN_VALUE;
            }
            i++;
        }
    }
    return i;
}

Collection<Future<Boolean>> setupTasks() {
    ExecutorService exe = Executors.newFixedThreadPool(threads);
    Collection<Future<Boolean>> tasks = new ArrayList<>();
    for (JDKProfile profile : jdkProfiles) {
        for (String revision : profile.getRevisions()) {
            BuildToolsTask task = new BuildToolsTask(
                    profile.getPath().toString(),
                    revision,
                    new File(BUILDTOOLS_DIR.toString(), revision),
                    verbose
            );
            tasks.add(exe.submit(task));
        }
    }
    return tasks;
}

boolean watchdog(Collection<Future<Boolean>> tasks) {
    wait:
    do {
        for (Future<Boolean> task : tasks) {
            if (task.isCancelled()) {
                System.out.println("Error: Task is cancelled.");
                return false;
            } else if (!task.isDone()) {
                continue wait;
            } else try {
                if (!task.get()) {
                    return false;
                }
            } catch (InterruptedException | ExecutionException exception) {
                return false;
            }
        }
    } while (false);
    return true;
}

String getLatestMvn() throws IOException {
    URL url = new URL(MVN_CHECK_URL);
    System.out.println("Fetching latest stable Maven version...");
    BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
    String line = reader.readLine();
    List<String> versions = new ArrayList<>();
    while (line != null) {
        if (line.contains("/icons/folder.gif")) {
            line = line.substring(51);
            line = line.split("/")[0];
            versions.add(line);
        }
        line = reader.readLine();
    }
    reader.close();

    String version = getHighestVersion(versions, 1);
    System.out.println("Latest stable Maven version is " + version + ".");
    return version;
}

String getHighestVersion(List<String> versions, int i) {
    int verNum = 0;
    List<String> versionsEqual = new ArrayList<>(versions.size());
    for (String compV : versions) {
        String[] numsToCompare = compV.split("\\.");
        if (numsToCompare.length <= i) {
            continue;
        }
        int compNum = Integer.parseInt(numsToCompare[i]);

        if (compNum < verNum) {
            continue;
        }
        if (compNum > verNum) {
            verNum = compNum;
            versionsEqual.clear();
        }
        versionsEqual.add(compV);
    }

    if (versionsEqual.size() == 1) {
        return versionsEqual.get(0);
    } else {
        return getHighestVersion(versionsEqual, ++i);
    }
}

void downloadMvn(String version) throws Exception {
    String link = MVN_URL.replace("%major", version.split("\\.")[0]).replace("%v", version);
    System.out.println("Downloading Apache Maven from " + link + "...");

    URL url = new URL(link);
    ReadableByteChannel readableByteChannel = Channels.newChannel(url.openStream());
    FileOutputStream fileOutputStream = new FileOutputStream("mvn.zip");
    fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
    fileOutputStream.close();

    File zipFile = new File("mvn.zip").getAbsoluteFile();
    InputStream zipFileStream = new FileInputStream(zipFile);
    Path parent = Path.of(zipFile.getParent());
    ZipInputStream zipInputStream = new ZipInputStream(zipFileStream);

    ZipEntry entry = zipInputStream.getNextEntry();
    while (entry != null) {
        Path target = parent.resolve(entry.getName()).normalize();
        if (entry.isDirectory()) {
            Files.createDirectories(target);
        } else {
            Files.createDirectories(target.getParent());
            Files.copy(zipInputStream, target);
        }
        entry = zipInputStream.getNextEntry();
    }
    zipInputStream.close();
    zipFileStream.close();
    zipFile.delete();
}

void mvnBuild(String bin) throws IOException {
    System.out.println("Building " + PROJECT_NAME + "...");
    String[] arguments = {bin + File.separatorChar + getMvnScript(), "clean", goal};
    Process process = new ProcessBuilder(arguments).start();
    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
    String line = reader.readLine();
    while (line != null) {
        System.out.println(line);
        line = reader.readLine();
    }
    System.out.println("See \"target\" directory.");
}

String getMvnScript() {
    if (System.getProperty("os.name").toUpperCase().contains("WINDOWS")) {
        return "mvn.cmd";
    } else {
        return "mvn";
    }
}

void delete(File file) {
    if (file.isDirectory()) {
        for (File f : file.listFiles()) {
            delete(f);
        }
    }
    if (file.exists() && !file.delete()) {
        System.out.println("Error: Could not clean up " + file.toString() + ".");
    }
}

void deleteTargets(File file) {
    if (!file.isDirectory()) {
        return;
    }
    for (File f : file.listFiles()) {
        if (!f.isDirectory()) {
            continue;
        }
        if (f.getName().equals("target")) {
            delete(f);
            continue;
        }
        deleteTargets(f);
    }
}
