package com.stoopad.velocitywhitelist.listener;

import com.stoopad.velocitywhitelist.VelocityWhitelistPlugin;
import com.velocitypowered.api.event.Subscribe;

import java.util.List;

/**
 * 监听 HuHoBot-Velocity 的 QQ 群命令事件，将绑定请求转发到 Paper 子服。
 *
 * 集成说明：
 * 1. 将事件参数类型 Object 替换为 HuHoBot-Velocity 的实际事件类
 * 2. 根据实际事件类的方法名调整 getCommand/getParam/getData/respone
 * 3. 事件类可能位于 cn.huohuas001.huhobot.velocity.api 包下
 */
public class BotCommandListener {

    private final VelocityWhitelistPlugin plugin;

    public BotCommandListener(VelocityWhitelistPlugin plugin) {
        this.plugin = plugin;
    }

    @Subscribe
    public void onBotCommand(Object event) {
        /*
         * 替换以下注释块中的 Object 为实际事件类后取消注释。
         * 预期的事件接口（与 Spigot 版 HuHoBot 一致）:
         *   getCommand()  -> String      命令名
         *   getParam()    -> List<String> 参数
         *   getData()     -> JSONObject   author.openId
         *   respone(msg, type)           回复消息
         *   setCancelled(boolean)        取消事件
         */

        /*
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
        */
    }
}
