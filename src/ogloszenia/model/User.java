package ogloszenia.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true)
    Integer id;

    @Column(nullable=false,unique=true)
    String nick;

    @Column(nullable=false)
    String password;

    @Lob
    byte[] avatar;

    @Column(nullable=false)
    String email;

    @Column(nullable=false)
    String cityName;

    @ManyToMany(mappedBy = "watchers")
    private Set<Advertisement> followedAdvertisements;

    @OneToMany(mappedBy = "owner") //tutaj jest zawsze nazwa pola ktora powiazuje
    Set<Advertisement> ads;

    @OneToMany(mappedBy="messageSender")
    Set<Message> sendMessages;

    @OneToMany(mappedBy="messageReceiver")
    Set<Message> receivedMessages;

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Set<Advertisement> getAds() {
        return ads;
    }

    public void setAds(Set<Advertisement> ads) {
        this.ads = ads;
    }

    public Set<Message> getSendMessages() {
        return sendMessages;
    }

    public void setSendMessages(Set<Message> sendMessages) {
        this.sendMessages = sendMessages;
    }

    public Set<Message> getReceivedMessages() {
        return receivedMessages;
    }

    public void setReceivedMessages(Set<Message> receivedMessages) {
        this.receivedMessages = receivedMessages;
    }

    public Set<Advertisement> getFollowedAdvertisements() {
        return followedAdvertisements;
    }

    public void setFollowedAdvertisements(Set<Advertisement> followedAdvertisements) {
        this.followedAdvertisements = followedAdvertisements;
    }
}
