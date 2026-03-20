/*
 * Copyright 2016 inventivetalent. All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without modification, are
 *  permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright notice, this list of
 *        conditions and the following disclaimer.
 *
 *     2. Redistributions in binary form must reproduce the above copyright notice, this list
 *        of conditions and the following disclaimer in the documentation and/or other materials
 *        provided with the distribution.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE AUTHOR ''AS IS'' AND ANY EXPRESS OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 *  FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR OR
 *  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *  SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 *  ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 *  ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *  The views and conclusions contained in the software and documentation are those of the
 *  authors and contributors and should not be interpreted as representing official policies,
 *  either expressed or implied, of anybody else.
 */
package de.erethon.xlib.spiget;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import org.bukkit.plugin.Plugin;

/**
 * Fix for legacy versions (older than 1.18) that include an outdated version of Gson.
 *
 * @author Haylee Schäfer
 * @see <a href="https://github.com/InventivetalentDev/Spiget-Update/blob/master/Core/src/main/java/org/inventivetalent/update/spiget/SpigetUpdateAbstract.java">Original code</a>
 */
public class SpigetUpdateLegacy extends SpigetUpdate {

    public SpigetUpdateLegacy(Plugin plugin, int resourceId) {
        super(plugin, resourceId);
    }

    @Override
    public void checkForUpdate(final UpdateCallback callback) {
        dispatch(() -> {
            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(String.format(RESOURCE_INFO, resourceId, System.currentTimeMillis())).openConnection();
                connection.setRequestProperty("User-Agent", getUserAgent());
                JsonParser jsonParser = new JsonParser();
                JsonObject jsonObject = jsonParser.parse(new InputStreamReader(connection.getInputStream())).getAsJsonObject();
                latestResourceInfo = new Gson().fromJson(jsonObject, ResourceInfo.class);

                connection = (HttpURLConnection) new URL(String.format(RESOURCE_VERSION, resourceId, System.currentTimeMillis())).openConnection();
                connection.setRequestProperty("User-Agent", getUserAgent());
                jsonObject = jsonParser.parse(new InputStreamReader(connection.getInputStream())).getAsJsonObject();
                latestResourceInfo.latestVersion = new Gson().fromJson(jsonObject, ResourceVersion.class);

                if (isVersionNewer(currentVersion, latestResourceInfo.latestVersion.name)) {
                    callback.updateAvailable(latestResourceInfo.latestVersion.name, "https://spigotmc.org/" + latestResourceInfo.file.url, !latestResourceInfo.external && !latestResourceInfo.premium);
                } else {
                    callback.upToDate();
                }
            } catch (Exception e) {
                log.log(Level.WARNING, "Failed to get resource info from spiget.org", e);
            }
        });
    }

}
