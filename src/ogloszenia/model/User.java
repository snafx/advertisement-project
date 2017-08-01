package ogloszenia.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true)
    private Integer id;

    @Column(nullable=false,unique=true)
    private String nick;

    @Column(nullable=false)
    private String password;

    @Lob
    private byte[] avatar;

    @Column(nullable=false)
    private String email;

    @Column(nullable=false)
    private String cityName;

    @ManyToMany(mappedBy = "watchers")
    private Set<Advertisement> followedAdvertisements;

    @OneToMany(mappedBy = "owner") //tutaj jest zawsze nazwa pola ktora powiazuje
    private Set<Advertisement> ads;

    @OneToMany(mappedBy="conversationSender")
    private Set<Conversation> sendConversations;

    @OneToMany(mappedBy="conversationReceiver")
    private Set<Conversation> receivedConversations;

    @OneToMany(mappedBy = "owner")
    private Set<ConversationMessage> conversationMessage;

    public User() {
    }

    public User(String nick, String password, String email, String location) {
        this.nick = nick;
        this.password = password;
        this.email = email;
        this.cityName = location;
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

    public Set<Advertisement> getFollowedAdvertisements() {
        return followedAdvertisements;
    }

    public void setFollowedAdvertisements(Set<Advertisement> followedAdvertisements) {
        this.followedAdvertisements = followedAdvertisements;
    }

    public Set<Advertisement> getAds() {
        return ads;
    }

    public void setAds(Set<Advertisement> ads) {
        this.ads = ads;
    }

    public Set<Conversation> getSendConversations() {
        return sendConversations;
    }

    public void setSendConversations(Set<Conversation> sendConversations) {
        this.sendConversations = sendConversations;
    }

    public Set<Conversation> getReceivedConversations() {
        return receivedConversations;
    }

    public void setReceivedConversations(Set<Conversation> receivedConversations) {
        this.receivedConversations = receivedConversations;
    }

    public Set<ConversationMessage> getConversationMessage() {
        return conversationMessage;
    }

    public void setConversationMessage(Set<ConversationMessage> conversationMessage) {
        this.conversationMessage = conversationMessage;
    }
}
