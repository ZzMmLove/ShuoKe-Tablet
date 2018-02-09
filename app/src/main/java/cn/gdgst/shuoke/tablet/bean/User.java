package cn.gdgst.shuoke.tablet.bean;

/**
 * Created by Administrator on 2/7 0007.
 */

public class User {

    /**
     * id : 13
     * mob : 18877826701
     * email : 1525011984@qq.com
     * access_token : 780m0fmt4x2925twss14x0842ff5ms67
     * nickname : zm
     * type : student
     * name : zm
     * avatar : ../Public/images/q.jpg
     * theme_url : /Public/Uploads/Member/20170810/theme_13.jpg
     * sex : 0
     * createdate : 1498628165
     * login_count : 1
     * lastLogindate : 1505195666
     * lastloginip : 14.222.40.185
     * addr :
     * school : 中山大学
     * className : 中大136班
     * teacher : 杨老师
     * remark : 是打发的说法放大收到货的时候打上
     */

    private String id;
    private String mob;
    private String email;
    private String access_token;
    private String nickname;
    private String type;
    private String name;
    private String avatar;
    private String theme_url;
    private int sex;
    private String createdate;
    private String login_count;
    private String lastLogindate;
    private String lastloginip;
    private String addr;
    private String school;
    private String className;
    private String teacher;
    private String remark;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMob() {
        return mob;
    }

    public void setMob(String mob) {
        this.mob = mob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTheme_url() {
        return theme_url;
    }

    public void setTheme_url(String theme_url) {
        this.theme_url = theme_url;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getLogin_count() {
        return login_count;
    }

    public void setLogin_count(String login_count) {
        this.login_count = login_count;
    }

    public String getLastLogindate() {
        return lastLogindate;
    }

    public void setLastLogindate(String lastLogindate) {
        this.lastLogindate = lastLogindate;
    }

    public String getLastloginip() {
        return lastloginip;
    }

    public void setLastloginip(String lastloginip) {
        this.lastloginip = lastloginip;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "DataBean{" +
                "id='" + id + '\'' +
                ", mob='" + mob + '\'' +
                ", email='" + email + '\'' +
                ", access_token='" + access_token + '\'' +
                ", nickname='" + nickname + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", theme_url='" + theme_url + '\'' +
                ", sex='" + sex + '\'' +
                ", createdate='" + createdate + '\'' +
                ", login_count='" + login_count + '\'' +
                ", lastLogindate='" + lastLogindate + '\'' +
                ", lastloginip='" + lastloginip + '\'' +
                ", addr='" + addr + '\'' +
                ", school='" + school + '\'' +
                ", className='" + className + '\'' +
                ", teacher='" + teacher + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

}
