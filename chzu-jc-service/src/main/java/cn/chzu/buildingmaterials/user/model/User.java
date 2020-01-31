package cn.chzu.buildingmaterials.user.model;

/**
 * @description:
 * @author: zhu_hao
 * @date: Created in 2020/1/8 16:47
 * @version: 1.0.0
 * @modified By:
 */
public class User {
    //唯一id
    private String id;
    //用户名
    private String username;
    //用户密码
    private String password;
    //提示信息
    private String msg;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
