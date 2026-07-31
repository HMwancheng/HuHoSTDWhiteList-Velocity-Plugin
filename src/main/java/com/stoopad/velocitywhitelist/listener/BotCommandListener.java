package com.stoopad.velocitywhitelist.listener;

import cn.huohuas001.huhobot.velocity.api.BotCustomCommand;
import com.alibaba.fastjson2.JSONObject;
import com.stoopad.velocitywhitelist.VelocityWhitelistPlugin;
import com.velocitypowered.api.event.Subscribe;

import java.util.List;

/**
 * 监听 HuHoBot-Velocity 的 QQ 群命令事件，将绑定请求转发到 Paper 子服。
 */
public class BotCommandListener {

    private final VelocityWhitelistPlugin plugin;

    public BotCommandListener(VelocityWhitelistPlugin plugin) {
        this.plugin = plugin;
    }

    @Subscribe
    public void onBotCommand(BotCustomCommand event) {
        String command = event.getCommand();
        List<String> params = event.getParam();

        if (!"验证码".equals(command)) return;

        event.setCancelled(true);

        if (params.isEmpty()) {
            event.respone("用法: /验证码 <验证码>", "success");
            return;
        }

        String code = params.get(0);
        JSONObject data = event.getData();
        JSONObject author = data.getJSONObject("author");
        String openId = author.getString("openId");

        // 转发到所有 Paper 子服，Paper 端通过 CodeManager.consumeCode 解析玩家名
        plugin.sendBindToAll(code, openId);
        event.respone("绑定请求已发送", "success");
    }
}