package com.example.czj.utouch.start;

/**
 * @Author: Czj
 * @Date: 2018/6/8 10:28
 * @Description:
 */

public class LoginBean {
    public int code;
    public String message;
    public Data data;

    public LoginBean() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {
        public int id;
        public String username;
        public String nickname;
        public int sex;
        public String grade;
        public String major;
        public int activityNum;
        public int subscriptionNum;
        public String avatar;
        public String profile;
        public String background;

        public Data() {
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public String getMajor() {
            return major;
        }

        public void setMajor(String major) {
            this.major = major;
        }

        public int getActivityNum() {
            return activityNum;
        }

        public void setActivityNum(int activityNum) {
            this.activityNum = activityNum;
        }

        public int getSubscriptionNum() {
            return subscriptionNum;
        }

        public void setSubscriptionNum(int subscriptionNum) {
            this.subscriptionNum = subscriptionNum;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getProfile() {
            return profile;
        }

        public void setProfile(String profile) {
            this.profile = profile;
        }

        public String getBackground() {
            return background;
        }

        public void setBackground(String background) {
            this.background = background;
        }
    }
}
