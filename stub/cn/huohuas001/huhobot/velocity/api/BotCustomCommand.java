package cn.huohuas001.huhobot.velocity.api;

import com.alibaba.fastjson2.JSONObject;

import java.util.Collections;
import java.util.List;

/**
 * HuHoBot-Velocity 自定义命令事件 stub（编译时占位，实际由 HuHoBot-Velocity 提供）。
 * 与 Spigot 版 API 接口保持一致。
 */
public class BotCustomCommand {

    private boolean cancelled = false;

    public String getCommand() {
        return "";
    }

    public List<String> getParam() {
        return Collections.emptyList();
    }

    public JSONObject getData() {
        return new JSONObject();
    }

    public void respone(String message, String type) {
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}