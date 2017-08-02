package ogloszenia.model;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Conversation {

    @Id
    @Column(name="id", unique=true)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(nullable=false)
    private Advertisement advertisementId;

    @JoinColumn(nullable=false)
    @ManyToOne
    private User conversationSender;

    @JoinColumn(nullable=false)
    @ManyToOne
    private User conversationReceiver;

    @OneToMany(mappedBy = "conversation")
    private Set<ConversationMessage> conversationMessage;

    @Column(nullable=false)
    private LocalDate messageDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Advertisement getAdvertisementId() {
        return advertisementId;
    }

    public void setAdvertisementId(Advertisement advertisementId) {
        this.advertisementId = advertisementId;
    }

    public User getConversationSender() {
        return conversationSender;
    }

    public void setConversationSender(User conversationSender) {
        this.conversationSender = conversationSender;
    }

    public User getConversationReceiver() {
        return conversationReceiver;
    }

    public void setConversationReceiver(User conversationReceiver) {
        this.conversationReceiver = conversationReceiver;
    }

    public Set<ConversationMessage> getConversationMessage() {
        return conversationMessage;
    }

    public void setConversationMessage(Set<ConversationMessage> conversationMessage) {
        this.conversationMessage = conversationMessage;
    }

    public LocalDate getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(LocalDate messageDate) {
        this.messageDate = messageDate;
    }
}