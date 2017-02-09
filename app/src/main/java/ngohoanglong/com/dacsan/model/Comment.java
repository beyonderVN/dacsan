package ngohoanglong.com.dacsan.model;

import java.io.Serializable;

/**
 * Created by Long on 11/29/2016.
 */

public class Comment implements Serializable{
    public User user;
    public String message;
    public String createAt;
    public String updateAt;
    public Comment() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }
}
