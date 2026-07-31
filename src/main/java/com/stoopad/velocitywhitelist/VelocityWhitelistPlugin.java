package com.stoopad.velocitywhitelist;

import com.stoopad.velocitywhitelist.listener.BotCommandListener;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.messaging.ChannelIdentifier;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

@Plugin(
        id = "huhostdwhitelist",
        name = "HuHoSTDWhiteList-Velocity",
        version = "1.0.0",
        authors = {"stoopad"}
)
public class VelocityWhitelistPlugin {

    private static final String CHANNEL_NAMESPACE = "huhostdwhitelist";
    private static final String CHANNEL_NAME = "main";

    private final ProxyServer server;
    private final Logger logger;
    private final Path dataDirectory;

    private ChannelIdentifier channel;

    @Inject
    public VelocityWhitelistPlugin(ProxyServer server, Logger logger, @DataDirectory Path dataDirectory) {
        this.server = server;
        this.logger = logger;
        this.dataDirectory = dataDirectory;
    }

    @Subscribe
    public void onProxyInitialize(ProxyInitializeEvent event) {
        channel = ChannelIdentifier.create(CHANNEL_NAMESPACE, CHANNEL_NAME);
        server.getChannelRegistrar().register(channel);
        server.getEventManager().register(this, new BotCommandListener(this));
        logger.info("HuHoSTDWhiteList-Velocity loaded  channel={}:{}", CHANNEL_NAMESPACE, CHANNEL_NAME);
    }

    /**
     * 广播绑定请求到所有 Paper 子服。
     * 消息格式: BIND|code|openId
     * Paper 端通过 CodeManager.consumeCode(code) 解析玩家名并执行绑定
     */
    public void sendBindToAll(String code, String openId) {
        String message = "BIND|" + code + "|" + openId;
        byte[] data = message.getBytes(StandardCharsets.UTF_8);
        server.getAllServers().forEach(s -> {
            try {
                s.sendPluginMessage(channel, data);
            } catch (Exception e) {
                logger.warn("Send bind to {} failed: {}", s.getServerInfo().getName(), e.getMessage());
            }
        });
        logger.info("Broadcast bind: code={} openId={}", code, openId);
    }

    public ProxyServer getServer() { return server; }
    public Logger getLogger() { return logger; }
    public Path getDataDirectory() { return dataDirectory; }
}