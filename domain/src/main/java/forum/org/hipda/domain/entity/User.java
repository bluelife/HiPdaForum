package forum.org.hipda.domain.entity;

/**
 * Created by slomka.jin on 2015/6/30.
 */

/**
 * Created by silong on 2015/6/28.
 */

public class User {
    private String avatarUrl;
    private String detail;
    private String username;
    private String id;
    private String password;

    public User() {
    }

    public String getAvatarUrl() {
        return this.avatarUrl;
    }

    public String getDetail() {
        return this.detail;
    }

    public String getUsername() {
        return this.username;
    }

    public String getId() {
        return this.id;
    }

    public String getPassword() {
        return this.password;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof User)) return false;
        final User other = (User) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$avatarUrl = this.avatarUrl;
        final Object other$avatarUrl = other.avatarUrl;
        if (this$avatarUrl == null ? other$avatarUrl != null : !this$avatarUrl.equals(other$avatarUrl))
            return false;
        final Object this$detail = this.detail;
        final Object other$detail = other.detail;
        if (this$detail == null ? other$detail != null : !this$detail.equals(other$detail))
            return false;
        final Object this$username = this.username;
        final Object other$username = other.username;
        if (this$username == null ? other$username != null : !this$username.equals(other$username))
            return false;
        final Object this$id = this.id;
        final Object other$id = other.id;
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$password = this.password;
        final Object other$password = other.password;
        if (this$password == null ? other$password != null : !this$password.equals(other$password))
            return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $avatarUrl = this.avatarUrl;
        result = result * PRIME + ($avatarUrl == null ? 0 : $avatarUrl.hashCode());
        final Object $detail = this.detail;
        result = result * PRIME + ($detail == null ? 0 : $detail.hashCode());
        final Object $username = this.username;
        result = result * PRIME + ($username == null ? 0 : $username.hashCode());
        final Object $id = this.id;
        result = result * PRIME + ($id == null ? 0 : $id.hashCode());
        final Object $password = this.password;
        result = result * PRIME + ($password == null ? 0 : $password.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof User;
    }

    public String toString() {
        return "forum.org.hipda.domain.entity.User(avatarUrl=" + this.avatarUrl + ", detail=" + this.detail + ", username=" + this.username + ", id=" + this.id + ", password=" + this.password + ")";
    }
}