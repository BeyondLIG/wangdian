package cn.wangdian.utils;

import org.springframework.ui.Model;

/**
 * 将参数封装到 Model 中
 */
public class AddAttributeToModel {
    public int addAttributeToModel(Model model, String username, String nickname, Integer status) {
        int parameterCount = 0;
        if (username != null && !"".equals(username)) {
            model.addAttribute("username", username);
            parameterCount++;
        }
        if (nickname != null && !"".equals(nickname)) {
            model.addAttribute("nickname", nickname);
            parameterCount++;
        }
        if (status != null) {
            model.addAttribute("status", status);
            parameterCount++;
        }
        return parameterCount;
    }
}
