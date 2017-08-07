package ogloszenia.model;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Conversation {

    /*
 * konwersacja - watek wiadomosci dotyczacy danego ogloszenia pomiedzy wlascicielem a jakims innym userem
    id ogloszenia
    id nadawca
    id odbiorca
    zbior wiadomosci w tym wątku
    data
 */

    @Id
    @Column(name="id", unique=true)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(nullable=false)
    private Advertisement advertisement;

    @JoinColumn(nullable=false)
    @ManyToOne    // (cascade=CascadeType.ALL)
    private User conversationSender;

    @JoinColumn(nullable=false)
    @ManyToOne    // (cascade=CascadeType.ALL)
    private User conversationReceiver;


    @OneToMany(mappedBy="conversation")
    Set<ConversationMessage> conversationMessage;

    @Column(nullable=false)
    private LocalDate messageDate;


    public Conversation() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Advertisement getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(Advertisement advertisement) {
        this.advertisement = advertisement;
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
